package com.ruoyi.mobile.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.PageData;
import com.ruoyi.mobile.app.mapper.MobileCommonMapper;

@Service
public class MobileCommonService {

	@Autowired
	private MobileCommonMapper mobileCommonMapper;
	
	public List<PageData> getDeptList() {
		return mobileCommonMapper.getDeptList();
	}

	public void finishBaseUserInfo(PageData pd) {
		
		mobileCommonMapper.finishBaseUserInfo(pd);
	}

	public Integer getUserCntByMobile(PageData pd) {
		return mobileCommonMapper.getUserCntByMobile(pd);
	}

	public List<PageData> getInfoClassList() {
		
		return mobileCommonMapper.getInfoClassList();
	}

	public List<PageData> getInfoItemList(PageData pageData) {
		// TODO Auto-generated method stub
		return mobileCommonMapper.getInfoItemList(pageData);
	}

	public List<PageData> getBannerList() {
		return mobileCommonMapper.getBannerList();
	}

	public List<PageData> getNoticeList() {
		// TODO Auto-generated method stub
		return mobileCommonMapper.getNoticeList();
	}

	public List<PageData> getRecInformationList() {
		// TODO Auto-generated method stub
		return mobileCommonMapper.getRecInformationList();
	}

	public PageData getBannerInfo(PageData pd) {
		// TODO Auto-generated method stub
		return mobileCommonMapper.getBannerInfo(pd);
	}

	public PageData getNoticeInfo(PageData pd) {
		// TODO Auto-generated method stub
		return mobileCommonMapper.getNoticeInfo(pd);
	}

	public PageData getInformationDetail(PageData pd) {
		// TODO Auto-generated method stub
		return mobileCommonMapper.getInformationDetail(pd);
	}

	public List<PageData> getInfoListByClass(PageData pd) {
		// TODO Auto-generated method stub
		return mobileCommonMapper.getInfoListByClass(pd);
	}

	public List<PageData> introduceDepts() {
		// TODO Auto-generated method stub
		return mobileCommonMapper.introduceDepts();
	}

	public PageData introduceDeptDetail(PageData pd) {
		// TODO Auto-generated method stub
		return mobileCommonMapper.introduceDeptDetail(pd);
	}

	public List<PageData> getUserListByDept(PageData pd) {
		// TODO Auto-generated method stub
		return mobileCommonMapper.getUserListByDept(pd);
	}

	public List<PageData> getActivityList(PageData pd) {
		// TODO Auto-generated method stub
		return mobileCommonMapper.getActivityList(pd);
	}

	public List<PageData> getAllUserListByDept(PageData pd) {
		// TODO Auto-generated method stub
		return mobileCommonMapper.getAllUserListByDept(pd);
	}

	public List<PageData> getAllActivityList(PageData pd) {
		// TODO Auto-generated method stub
		return mobileCommonMapper.getAllActivityList(pd);
	}

	public PageData getActivityDetail(PageData pd) {
		// TODO Auto-generated method stub
		return mobileCommonMapper.getActivityDetail(pd);
	}

	public List<PageData> introduceBusiness() {
		// TODO Auto-generated method stub
		return mobileCommonMapper.introduceBusiness();
	}


	public Integer hasPointInfo(PageData pd) {
		// TODO Auto-generated method stub
		return mobileCommonMapper.hasPointInfo(pd);
	}

	public Integer pointIsFull(PageData pd) {
		// TODO Auto-generated method stub
		return mobileCommonMapper.pointIsFull(pd);
	}


	public void insertPointInfo(PageData pd) {
		// TODO Auto-generated method stub
		mobileCommonMapper.insertPointInfo(pd);
	}

	public PageData updateApp(PageData pd) {
		// TODO Auto-generated method stub
		return mobileCommonMapper.updateApp(pd);
	}

	public List<PageData> getWishList(PageData pd) {
		// TODO Auto-generated method stub
		return mobileCommonMapper.getWishList(pd);
	}

	public PageData getWishInfo(PageData pd) {
		// TODO Auto-generated method stub
		return mobileCommonMapper.getWishInfo(pd);
	}

	public List<PageData> getMyClaimList(PageData pd) {
		// TODO Auto-generated method stub
		return mobileCommonMapper.getMyClaimList(pd);
	}

	public void claimWish(PageData pd) {
		// TODO Auto-generated method stub
		mobileCommonMapper.claimWish(pd);
	}

	public Integer getWishStatus(PageData pd) {
		// TODO Auto-generated method stub
		return mobileCommonMapper.getWishStatus(pd);
	}

}
