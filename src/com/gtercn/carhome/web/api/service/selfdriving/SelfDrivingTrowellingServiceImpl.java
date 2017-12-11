package com.gtercn.carhome.web.api.service.selfdriving;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gtercn.carhome.web.api.CarHomeApiProperties;
import com.gtercn.carhome.web.api.dao.DictionaryMapper;
import com.gtercn.carhome.web.api.dao.ReplyMapper;
import com.gtercn.carhome.web.api.dao.SelfDrivingExtendMapper;
import com.gtercn.carhome.web.api.dao.SelfDrivingTrowellingMapper;
import com.gtercn.carhome.web.api.entity.Dictionary;
import com.gtercn.carhome.web.api.entity.Reply;
import com.gtercn.carhome.web.api.entity.SelfDrivingExtend;
import com.gtercn.carhome.web.api.entity.SelfDrivingTrowelling;
import com.gtercn.carhome.web.api.exception.ApiException;
import com.gtercn.carhome.web.api.utils.CommonUtil;
import com.gtercn.carhome.web.api.utils.FtpFileTools;

@Service(value = "selfDrivingTrowellingService")
public class SelfDrivingTrowellingServiceImpl implements SelfDrivingTrowellingService {
	@Autowired
	private SelfDrivingTrowellingMapper dao;
	
	@Autowired
	private SelfDrivingExtendMapper extendDao;
	
	@Autowired
	private DictionaryMapper dictionarydao;
	
	@Autowired
	private ReplyMapper replydao;
	
	@Override
	public List<SelfDrivingTrowelling> getInfoList(String userId, String cityCode) {
		
		List<SelfDrivingTrowelling> list = new ArrayList<SelfDrivingTrowelling>();
		List<String> picList = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Date time = new Date(date.getTime());
		Long befTime = time.getTime();
		Long updateTime1 = new Long(0);
		Long difTime = new Long(0); // 时间差值
		
		// 检索参数
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 获取自驾游信息列表(未登录状态下)
		if (StringUtils.isEmpty(userId)) {
			map.put("cityCode", cityCode);
			list = this.dao.getInfoListNoLogin(map);
		// 在登录状态下
		} else {
			map.put("userId", userId);
			map.put("cityCode", cityCode);
			list = this.dao.getInfoList(map);
		}
		
		// 获得规定的时间范围
		Dictionary dictionary = dictionarydao.selectByPrimaryKey("8100");
		Long overTime = Long.valueOf(dictionary.getDictionaryValue()) * 3600;
		
		// 超过活动日期十天的自驾游则不在列表中显示
		Iterator<SelfDrivingTrowelling> it = list.iterator();
		while(it.hasNext()){
			SelfDrivingTrowelling selfDrivingTrowelling = it.next();
			
			Date activityTime;
			try {
				activityTime = sdf1.parse(selfDrivingTrowelling.getActivityTime());
				Date date1 = new Date();
				String dateStr = sdf1.format(date1);
				date1 = sdf1.parse(dateStr);
				long t = (date1.getTime() - activityTime.getTime())/(3600 * 24 * 1000);
				if (t > 10) {
					it.remove();
				} 
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		// 对图片进行处理
		for (int i = 0; i < list.size(); i++) {
			
			if (StringUtils.isNotBlank(list.get(i).getPicUrls())) {
				String[] pictureUrls = list.get(i).getPicUrls().split(",");
				
				for (int j = 0; j < pictureUrls.length; j++) {
					picList.add(CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip")+ pictureUrls[j]);
				}
				list.get(i).setPicUrlsList(picList);
			}
			
			// 用户头像
			if (StringUtils.isNotBlank(list.get(i).getAvatarUrl())) {
				list.get(i).setAvatarUrl(CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip") + list.get(i).getAvatarUrl());
			}
			
			// 判断是否是发布者
			if (StringUtils.isNotBlank(userId) && StringUtils.equals(userId, list.get(i).getUserId())) {
				list.get(i).setPublicFlag("1");
			} else {
				list.get(i).setPublicFlag("0");
			}
			
			// 判断是否能报名
			// 先判断是否登录
			if (StringUtils.isNotBlank(userId)) {
				try {
					Date activityTime = sdf1.parse(list.get(i).getActivityTime());
					Date date1 = new Date();
					String dateStr = sdf1.format(date1);
					date1 = sdf1.parse(dateStr);
					long t = (activityTime.getTime() - date1.getTime())/(3600 * 24 * 1000);
					// 相差天数大于一天则可以报名，否则不能报名
					if (t >= 1) {
						list.get(i).setEnrollFlag("1");
					} else {
						list.get(i).setEnrollFlag("0");
					}
					
					// 活动时间
					list.get(i).setActivityTime(sdf1.format(sdf1.parse(list.get(i).getActivityTime())));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			try {
				updateTime1 = df.parse(list.get(i).getUpdateTime()).getTime();
				// 超过规定时间以yyyy-MM-dd HH:mm展示
				if (befTime - updateTime1 > overTime * 1000) {
					list.get(i).setUpdateTime(sdf.format(sdf.parse(list.get(i).getUpdateTime())));
				// 否则以 例：9分钟前 2016-12-30形式展示
				} else {
					difTime = (befTime - updateTime1)/1000;
					if (difTime < 3600) {
						difTime = difTime/60;
						list.get(i).setUpdateTime(difTime + "分钟前  " + sdf1.format(sdf1.parse(list.get(i).getUpdateTime())));
					} else {
						difTime = difTime/3600;
						list.get(i).setUpdateTime(difTime + "小时前  " + sdf1.format(sdf1.parse(list.get(i).getUpdateTime())));
					}
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			picList = new ArrayList<String>();
		}
		return list;
	}
	
	@Override
	public SelfDrivingTrowelling getInfoOne(String userId, String id) {
		
		List<String> picList = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Date time = new Date(date.getTime());
		Long befTime = time.getTime();
		Long updateTime1 = new Long(0);
		Long difTime = new Long(0); // 时间差值
		
		// 检索参数
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userId", userId);
		map.put("id", id);
		SelfDrivingTrowelling selfDrivingTrowelling = this.dao.getInfoOne(map);
		
		// 获得规定的时间范围
		Dictionary dictionary = dictionarydao.selectByPrimaryKey("8100");
		Long overTime = Long.valueOf(dictionary.getDictionaryValue()) * 3600;
		
		// 对图片进行处理
		if (StringUtils.isNotBlank(selfDrivingTrowelling.getPicUrls())) {
			String[] pictureUrls = selfDrivingTrowelling.getPicUrls().split(",");
			
			for (int j = 0; j < pictureUrls.length; j++) {
				picList.add(CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip")+ pictureUrls[j]);
			}
			selfDrivingTrowelling.setPicUrlsList(picList);
		}
		
		// 用户头像
		if (StringUtils.isNotBlank(selfDrivingTrowelling.getAvatarUrl())) {
			selfDrivingTrowelling.setAvatarUrl(CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip") + selfDrivingTrowelling.getAvatarUrl());
		}
		
		// 判断是否是发布者
		if (StringUtils.isNotBlank(userId) && StringUtils.equals(userId, selfDrivingTrowelling.getUserId())) {
			selfDrivingTrowelling.setPublicFlag("1");
		} else {
			selfDrivingTrowelling.setPublicFlag("0");
		}
		
		// 判断是否能报名
		// 先判断是否登录
		if (StringUtils.isNotBlank(userId)) {
			try {
				Date activityTime = sdf1.parse(selfDrivingTrowelling.getActivityTime());
				Date date1 = new Date();
				String dateStr = sdf1.format(date1);
				date1 = sdf1.parse(dateStr);
				long t = (activityTime.getTime() - date1.getTime())/(3600 * 24 * 1000);
				// 相差天数大于一天则可以报名，否则不能报名
				if (t >= 1) {
					selfDrivingTrowelling.setEnrollFlag("1");
				} else {
					selfDrivingTrowelling.setEnrollFlag("0");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		// 活动时间
		try {
			selfDrivingTrowelling.setActivityTime(sdf1.format(sdf1.parse(selfDrivingTrowelling.getActivityTime())));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		Integer count = dao.getCount(id);
		selfDrivingTrowelling.setCount(count);
		
		try {
			updateTime1 = df.parse(selfDrivingTrowelling.getUpdateTime()).getTime();
			// 超过规定时间以yyyy-MM-dd HH:mm展示
			if (befTime - updateTime1 > overTime * 1000) {
				selfDrivingTrowelling.setUpdateTime(sdf.format(sdf.parse(selfDrivingTrowelling.getUpdateTime())));
			// 否则以 例：9分钟前 2016-12-30形式展示
			} else {
				difTime = (befTime - updateTime1)/1000;
				if (difTime < 3600) {
					difTime = difTime/60;
					selfDrivingTrowelling.setUpdateTime(difTime + "分钟前  " + sdf1.format(sdf1.parse(selfDrivingTrowelling.getUpdateTime())));
				} else {
					difTime = difTime/3600;
					selfDrivingTrowelling.setUpdateTime(difTime + "小时前  " + sdf1.format(sdf1.parse(selfDrivingTrowelling.getUpdateTime())));
				}
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		picList = new ArrayList<String>();
			
		return selfDrivingTrowelling;
	}
	
	@Override
	public List<Reply> getCommentList(String selfDrivingId) {
		
		List<Reply> list = new ArrayList<Reply>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Date time = new Date(date.getTime());
		Long befTime = time.getTime();
		Long insertTime1 = new Long(0);
		Long difTime = new Long(0); // 时间差值
		Long overTime = new Long(0);
		
		// 检索参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("selfDrivingId", selfDrivingId);
		
		list = this.dao.getCommentList(map);
		
		// 获得规定的时间范围
		if (list.size() != 0) {
			Dictionary dictionary = dictionarydao.selectByPrimaryKey("8100");
			overTime = Long.valueOf(dictionary.getDictionaryValue()) * 3600;
		}
		
		for (int i = 0; i < list.size(); i++) {
			try {
				insertTime1 = df.parse(list.get(i).getInsertTime()).getTime();
				// 超过规定时间以yyyy-MM-dd HH:mm展示
				if (befTime - insertTime1 > overTime * 1000) {
					list.get(i).setInsertTime(sdf.format(sdf.parse(list.get(i).getInsertTime())));
				// 否则以 例：9分钟前 2016-12-30形式展示
				} else {
					difTime = (befTime - insertTime1)/1000;
					if (difTime < 3600) {
						difTime = difTime/60;
						list.get(i).setInsertTime(sdf1.format(sdf1.parse(list.get(i).getInsertTime())) + "  " + difTime + "分钟前");
					} else {
						difTime = difTime/3600;
						list.get(i).setInsertTime(sdf1.format(sdf1.parse(list.get(i).getInsertTime())) +  "  " + difTime + "小时前");
					}
				}
				list.get(i).setAvatarUrl(CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip") + list.get(i).getAvatarUrl());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	@Override
	public List<Reply> getCommentReply(String selfDrivingId, String itemId) {
		
		List<Reply> list = new ArrayList<Reply>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		Date time = new Date(date.getTime());
		Long befTime = time.getTime();
		Long insertTime1 = new Long(0);
		Long difTime = new Long(0); // 时间差值
		Long overTime = new Long(0);
		
		// 检索参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("selfDrivingId", selfDrivingId);
		map.put("itemId", itemId);
		
		list = this.dao.getCommentReply(map);
		
		// 获得规定的时间范围
		if (list.size() != 0) {
			Dictionary dictionary = dictionarydao.selectByPrimaryKey("8100");
			overTime = Long.valueOf(dictionary.getDictionaryValue()) * 3600;
		}
		
		for (int i = 0; i < list.size(); i++) {
			try {
				insertTime1 = df.parse(list.get(i).getInsertTime()).getTime();
				// 超过规定时间以yyyy-MM-dd HH:mm展示
				if (befTime - insertTime1 > overTime * 1000) {
					list.get(i).setInsertTime(sdf.format(sdf.parse(list.get(i).getInsertTime())));
				// 否则以 例：9分钟前 2016-12-30形式展示
				} else {
					difTime = (befTime - insertTime1)/1000;
					if (difTime < 3600) {
						difTime = difTime/60;
						list.get(i).setInsertTime(sdf1.format(sdf1.parse(list.get(i).getInsertTime())) + "  " + difTime + "分钟前");
					} else {
						difTime = difTime/3600;
						list.get(i).setInsertTime(sdf1.format(sdf1.parse(list.get(i).getInsertTime())) +  "  " + difTime + "小时前");
					}
				}
				
				list.get(i).setAvatarUrl(CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip") + list.get(i).getAvatarUrl());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	@Override
	public int getComment(String userId, String selfDrivingId, String content) {
		
		Reply reply = new Reply();
		
		// 检索参数
		reply.setId(CommonUtil.getUID());
		reply.setContent(content);
		reply.setUserId(userId);
		reply.setReferenceId(selfDrivingId);
		reply.setTypeId(2);
		reply.setDeleteFlag(0);

		int a = this.replydao.insert(reply);

		return a;
	}
	
	@Override
	public int getCommentSon(String userId, String selfDrivingId, String content, String toUserId, String itemId) {
		
		Reply reply = new Reply();
		
		// 检索参数
		reply.setId(CommonUtil.getUID());
		reply.setContent(content);
		reply.setUserId(userId);
		reply.setReferenceId(selfDrivingId);
		reply.setTypeId(2);
		reply.setDeleteFlag(0);
		reply.setToUserId(toUserId);
		reply.setItemId(itemId);

		int a = this.replydao.insert(reply);

		return a;
	}
	
	@Override
	public SelfDrivingTrowelling getRelease(List<MultipartFile> list, Map<String, String> map) throws ApiException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		
		String title = map.get("title");
		String userId = map.get("userId");
		String content = map.get("content");
		String cityCode = map.get("cityCode");
		String activityTime = map.get("activityTime");

		SelfDrivingTrowelling  selfDrivingTrowelling = new SelfDrivingTrowelling();
		selfDrivingTrowelling.setId(CommonUtil.getUID());
		selfDrivingTrowelling.setTitle(title);
		selfDrivingTrowelling.setUserId(userId);
		selfDrivingTrowelling.setContent(content);
		selfDrivingTrowelling.setCityCode(cityCode);
		try {
			selfDrivingTrowelling.setActivityTime(Timestamp.valueOf(sdf.format(sdf1.parse(activityTime))).toString());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		String picUrls = "";
		for (int i = 0; i < list.size(); i++) {
			if (i != 0) {
				picUrls += ",";
			}
			String filename = list.get(i).getOriginalFilename();
			String saveFileName = String.valueOf(System.currentTimeMillis());
			String suffix = filename.substring(filename.lastIndexOf("."));
			try {
				String[] savePath = {"carhome", "evaluation", selfDrivingTrowelling.getId()};
				FtpFileTools.uploadFile(savePath, saveFileName + suffix, list.get(i).getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			picUrls += File.separator + "carhome" + File.separator + "evaluation" + File.separator 
					+ selfDrivingTrowelling.getId() + File.separator + saveFileName + suffix;
		}
		selfDrivingTrowelling.setPicUrls(picUrls);
		selfDrivingTrowelling.setAvailableFlag(1);
		selfDrivingTrowelling.setDeleteFlag(0);
		
		this.dao.insert(selfDrivingTrowelling);
		
		return selfDrivingTrowelling;
	}
	
	@Override
	public SelfDrivingTrowelling getDataById(String id) throws Exception {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int updateData(SelfDrivingTrowelling o) throws Exception {
		return dao.updateByPrimaryKeySelective(o);
	}

	@Override
	public int addData(SelfDrivingTrowelling o) throws Exception {
		return dao.insert(o);		
	}

	@Override
	public int deleteData(String id) throws Exception {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int addEnrolData(SelfDrivingExtend entity) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("selfDrivingId", entity.getSelfDrivingId());
		map.put("userId", entity.getUserId());
		SelfDrivingExtend backeEtity = extendDao.selectInfo(map);
		//已经报过名但是取消了，继续报名
		if (backeEtity != null && backeEtity.getSignFlag() == 0) {
			backeEtity.setSignFlag(1);
			return extendDao.updateByPrimaryKey(backeEtity);
		//第一次报名
		} else {
			entity.setId(CommonUtil.getUID());
			entity.setSignFlag(1);
			entity.setDeleteFlag(0);
			return extendDao.insert(entity);
		}	
	}
	
	@Override
	public int removeEnrolData(String selfDrivingId, String userId) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("selfDrivingId", selfDrivingId);
		map.put("userId", userId);
		SelfDrivingExtend entity = extendDao.selectInfo(map);
		
		if (entity != null) {
			entity.setSignFlag(0);
			return extendDao.updateByPrimaryKey(entity);
		}

		return 0;		
	}
	
	@Override
	public int cancel(String selfDrivingId, String userId) throws Exception {
		
		SelfDrivingTrowelling selfDrivingTrowelling = dao.selectByPrimaryKey(selfDrivingId);
		if (selfDrivingTrowelling != null) {
			if (StringUtils.equals(userId, selfDrivingTrowelling.getUserId())) {
				selfDrivingTrowelling.setAvailableFlag(0);
				return dao.updateByPrimaryKeySelective(selfDrivingTrowelling);
			} 
		}

		return 0;		
	}
	
	@Override
	public List<SelfDrivingTrowelling> getActivityList(String userId, String cityCode) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<String> picList = new ArrayList<String>();
		Date date = new Date();
		Date time = new Date(date.getTime());
		Long befTime = time.getTime();
		Long updateTime1 = new Long(0);
		Long difTime = new Long(0); // 时间差值
		
		// 获得规定的时间范围
		Dictionary dictionary = dictionarydao.selectByPrimaryKey("8100");
		Long overTime = Long.valueOf(dictionary.getDictionaryValue()) * 3600;
		
		// 检索参数
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cityCode", cityCode);
		map.put("userId", userId);
		
		List<SelfDrivingTrowelling> list = dao.getActivityList(map);
		for (int i = 0; i < list.size(); i++) {
			
			if (StringUtils.isNotBlank(list.get(i).getPicUrls())) {
				String[] pictureUrls = list.get(i).getPicUrls().split(",");
				
				for (int j = 0; j < pictureUrls.length; j++) {
					picList.add(CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip")+ pictureUrls[j]);
				}
				list.get(i).setPicUrlsList(picList);
			}
			
			// 用户头像
			if (StringUtils.isNotBlank(list.get(i).getAvatarUrl())) {
				list.get(i).setAvatarUrl(CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip") + list.get(i).getAvatarUrl());
			}
			
			// 判断是否是发布者
			if (StringUtils.isNotBlank(userId) && StringUtils.equals(userId, list.get(i).getUserId())) {
				list.get(i).setPublicFlag("1");
			} else {
				list.get(i).setPublicFlag("0");
				list.get(i).setUserId(userId);
			}
			
			// 判断是否能报名
			// 先判断是否登录
			if (StringUtils.isNotBlank(userId)) {
				try {
					Date activityTime = sdf1.parse(list.get(i).getActivityTime());
					Date date1 = new Date();
					String dateStr = sdf1.format(date1);
					date1 = sdf1.parse(dateStr);
					long t = (activityTime.getTime() - date1.getTime())/(3600 * 24 * 1000);
					// 相差天数大于一天则可以报名，否则不能报名
					if (t >= 1) {
						list.get(i).setEnrollFlag("1");
					} else {
						list.get(i).setEnrollFlag("0");
					}
					
					// 活动时间
					list.get(i).setActivityTime(sdf1.format(sdf1.parse(list.get(i).getActivityTime())));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
			try {
				updateTime1 = df.parse(list.get(i).getUpdateTime()).getTime();
				// 超过规定时间以yyyy-MM-dd HH:mm展示
				if (befTime - updateTime1 > overTime * 1000) {
					list.get(i).setUpdateTime(sdf.format(sdf.parse(list.get(i).getUpdateTime())));
				// 否则以 例：9分钟前 2016-12-30形式展示
				} else {
					difTime = (befTime - updateTime1)/1000;
					if (difTime < 3600) {
						difTime = difTime/60;
						list.get(i).setUpdateTime(difTime + "分钟前  " + sdf1.format(sdf1.parse(list.get(i).getUpdateTime())));
					} else {
						difTime = difTime/3600;
						list.get(i).setUpdateTime(difTime + "小时前  " + sdf1.format(sdf1.parse(list.get(i).getUpdateTime())));
					}
				}
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			picList = new ArrayList<String>();
		}
		return list;		
	}
	
	@Override
	public List<Object> getSearchName(String selfDrivingId) {
		
		List<Object> list1 = new ArrayList<Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<SelfDrivingTrowelling> list = dao.getSearchName(selfDrivingId);
		
		for (int i = 0; i < list.size(); i++) {
			map.put("nickname", list.get(i).getNickname());
			map.put("avatarUrl", CarHomeApiProperties.CAR_HOME_API.getValue("resources_ip") + list.get(i).getAvatarUrl());
			list1.add(map);
			map = new HashMap<String, Object>();
		}

		return list1;		
	}
}
