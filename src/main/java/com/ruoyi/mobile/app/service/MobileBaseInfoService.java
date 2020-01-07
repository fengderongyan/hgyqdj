package com.ruoyi.mobile.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.PageData;
import com.ruoyi.mobile.app.mapper.MobileBaseInfoMapper;

@Service
public class MobileBaseInfoService {

	@Autowired
	private MobileBaseInfoMapper mobileBaseInfoMapper;

	public PageData getExamCnt(PageData pd) {
		// TODO Auto-generated method stub
		return mobileBaseInfoMapper.getExamCnt(pd);
	}

	public List<PageData> getMyExamList(PageData pd) {
		// TODO Auto-generated method stub
		return mobileBaseInfoMapper.getMyExamList(pd);
	}

	public List<PageData> getPointRank(PageData pd) {
		// TODO Auto-generated method stub
		return mobileBaseInfoMapper.getPointRank(pd);
	}

	public PageData getMyPoint(PageData pd) {
		// TODO Auto-generated method stub
		return mobileBaseInfoMapper.getMyPoint(pd);
	}

	public PageData getRankRule() {
		// TODO Auto-generated method stub
		return mobileBaseInfoMapper.getRankRule();
	}

	public PageData getUserInfo(PageData pd) {
		// TODO Auto-generated method stub
		return mobileBaseInfoMapper.getUserInfo(pd);
	}

	public void editPassword(PageData pd) {
		// TODO Auto-generated method stub
		mobileBaseInfoMapper.editPassword(pd);
	}

	public Integer getIsNoticeRead(PageData pd) {
		// TODO Auto-generated method stub
		return mobileBaseInfoMapper.getIsNoticeRead(pd);
	}

	public List<PageData> getMyNoticeList(PageData pd) {
		// TODO Auto-generated method stub
		return mobileBaseInfoMapper.getMyNoticeList(pd);
	}

	public void updateMyNticeRead(PageData pd) {
		// TODO Auto-generated method stub
		mobileBaseInfoMapper.updateMyNticeRead(pd);
	}

	public PageData getMyNoticeInfo(PageData pd) {
		// TODO Auto-generated method stub
		return mobileBaseInfoMapper.getMyNoticeInfo(pd);
	}

	public PageData getAboutUs() {
		// TODO Auto-generated method stub
		return mobileBaseInfoMapper.getAboutUs();
	}

	public String getMyForumCnt(PageData pd) {
		// TODO Auto-generated method stub
		return mobileBaseInfoMapper.getMyForumCnt(pd);
	}

	public String getMyCollectCnt(PageData pd) {
		// TODO Auto-generated method stub
		return mobileBaseInfoMapper.getMyCollectCnt(pd);
	}

	public String getMyWishCnt(PageData pd) {
		// TODO Auto-generated method stub
		return mobileBaseInfoMapper.getMyWishCnt(pd);
	}

	public List<PageData> getMyForumList(PageData pd) {
		// TODO Auto-generated method stub
		return mobileBaseInfoMapper.getMyForumList(pd);
	}

	public void deleteForum(PageData pd) {
		// TODO Auto-generated method stub
		mobileBaseInfoMapper.deleteForum(pd);
	}

	public List<PageData> getMyCollectionList(PageData pd) {
		// TODO Auto-generated method stub
		return mobileBaseInfoMapper.getMyCollectionList(pd);
	}

	public List<PageData> getMyReplyList(PageData pd) {
		// TODO Auto-generated method stub
		return mobileBaseInfoMapper.getMyReplyList(pd);
	}

	public void mobileLogout(String login_name) {
		// TODO Auto-generated method stub
		mobileBaseInfoMapper.mobileLogout(login_name);
		
	}
}
