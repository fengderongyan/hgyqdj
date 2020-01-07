package com.ruoyi.mobile.app.mapper;

import com.ruoyi.common.utils.PageData;

public interface MobileLoginMapper {

	public void updateTokenAndResId(PageData pd);

	public Integer phoneIsExist(PageData pd);

	public void addSignUpInfo(PageData pd);

	public void insertSysUser(PageData pd);

	public void finishInsertUser(PageData pd);

	public void updateSignStatus(PageData pd);

	public PageData getPrivacyProtocol();

}
