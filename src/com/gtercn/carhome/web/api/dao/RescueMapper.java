package com.gtercn.carhome.web.api.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gtercn.carhome.web.api.entity.Rescue;
import com.gtercn.carhome.web.api.entity.RescueDetail;
import com.gtercn.carhome.web.api.entity.RescueType;
import com.gtercn.carhome.web.api.entity.ServiceDetail;
import com.gtercn.carhome.web.api.entity.ServiceType;

@Repository
public interface RescueMapper {

    /**
     * 获取救援信息列表
     * @param map 参数
     * @return
     */
    List<Rescue> getInfoList(Map<String, Object> map);
    
    /**
     * 获取救援检索信息列表
     * @param map 参数
     * @return
     */
    List<Rescue> getInfoSearchList(Map<String, Object> map);
    
    /**
     * 获取单个救援公司信息
     * @param map 参数
     * @return
     */
    Rescue getOneRescueInfo(Map<String, Object> map);
    
   /**
    * 获取洗车修车等信息列表
    * @param map 参数
    * @return
    */
   List<Rescue> getServiceList(Map<String, Object> map);
   
   /**
    * 获取洗车修车等检索信息列表
    * @param map 参数
    * @return
    */
   List<Rescue> getServiceSearchList(Map<String, Object> map);
   
   /**
    * 获取单个洗车修车等信息
    * @param map 参数
    * @return
    */
   Rescue getServiceDifType(Map<String, Object> map);
   
   /**
    * 获取四类服务信息列表
    * @param map 参数
    * @return
    */
   List<ServiceType> getServiceTypeList(Map<String, Object> map);
   
   /**
    * 获取单个店铺信息
    * @param map 参数
    * @return
    */
   ServiceDetail getOneServiceInfo(Map<String, Object> map);
   
   /**
    * 获取救援服务信息列表
    * @param map 参数
    * @return
    */
   List<RescueType> getRescueTypeList(Map<String, Object> map);
   
   /**
    * 获取单个救援信息
    * @param map 参数
    * @return
    */
   RescueDetail getOneRescueTypeInfo(Map<String, Object> map);
}