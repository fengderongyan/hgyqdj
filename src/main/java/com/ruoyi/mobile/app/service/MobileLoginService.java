package com.ruoyi.mobile.app.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.PageData;
import com.ruoyi.mobile.app.mapper.MobileLoginMapper;

@Service
public class MobileLoginService{

	@Autowired
	private MobileLoginMapper mobileLoginMapper;

	public Map checkLoginResult(PageData pd) {
		
		return null;
	}

	public void updateTokenAndResId(PageData pd) {
		
		mobileLoginMapper.updateTokenAndResId(pd);
	}

	public Integer phoneIsExist(PageData pd) {
		// TODO Auto-generated method stub
		return mobileLoginMapper.phoneIsExist(pd);
	}

	public void addSignUpInfo(PageData pd) {
		// TODO Auto-generated method stub
		mobileLoginMapper.addSignUpInfo(pd);
	}


	public void insertSysUser(PageData pd) {
		// TODO Auto-generated method stub
		mobileLoginMapper.insertSysUser(pd);
	}

	public void finishInsertUser(PageData pd) {
		// TODO Auto-generated method stub
		mobileLoginMapper.finishInsertUser(pd);
	}

	public void updateSignStatus(PageData pd) {
		// TODO Auto-generated method stub
		mobileLoginMapper.updateSignStatus(pd);
	}

	public PageData getPrivacyProtocol() {
		// TODO Auto-generated method stub
		return mobileLoginMapper.getPrivacyProtocol();
	}
}
