package com.ruoyi.mobile.app.mapper;

import java.util.List;

import com.ruoyi.common.utils.PageData;

public interface MobileCommonMapper {

	public List<PageData> getDeptList();

	public Integer finishBaseUserInfo(PageData pd);

	public Integer getUserCntByMobile(PageData pd);

	public List<PageData> getInfoClassList();

	public List<PageData> getInfoItemList(PageData pageData);

	public List<PageData> getBannerList();

	public List<PageData> getNoticeList();

	public List<PageData> getRecInformationList();

	public PageData getBannerInfo(PageData pd);

	public PageData getNoticeInfo(PageData pd);

	public PageData getInformationDetail(PageData pd);

	public List<PageData> getInfoListByClass(PageData pd);

	public List<PageData> introduceDepts();

	public PageData introduceDeptDetail(PageData pd);

	public List<PageData> getUserListByDept(PageData pd);

	public List<PageData> getActivityList(PageData pd);

	public List<PageData> getAllUserListByDept(PageData pd);

	public List<PageData> getAllActivityList(PageData pd);

	public PageData getActivityDetail(PageData pd);

	public List<PageData> introduceBusiness();


	public Integer hasPointInfo(PageData pd);

	public Integer pointIsFull(PageData pd);


	public void insertPointInfo(PageData pd);

	public PageData updateApp(PageData pd);

	public List<PageData> getWishList(PageData pd);

	public PageData getWishInfo(PageData pd);

	public List<PageData> getMyClaimList(PageData pd);

	public void claimWish(PageData pd);

	public Integer getWishStatus(PageData pd);

}
