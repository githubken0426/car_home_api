package com.gtercn.carhome.web.api.service.verifycode;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtercn.carhome.web.api.dao.VerifyCodeMapper;
import com.gtercn.carhome.web.api.entity.VerifyCode;

@Service(value="verifyCodeService")
public class VerifyCodeService implements IVerifyCodeService {
	@Autowired
	private VerifyCodeMapper verifyCodeDao;
	
	@Override
	public void insert(VerifyCode verify) {
		verifyCodeDao.insert(verify);
	}

	@Override
	public VerifyCode getByCondition(Map<String, Object> map) {
		return verifyCodeDao.getByCondition(map);
	}

}
