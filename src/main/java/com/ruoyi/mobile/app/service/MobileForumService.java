package com.ruoyi.mobile.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.PageData;
import com.ruoyi.mobile.app.mapper.MobileForumMapper;

@Service
public class MobileForumService {

	@Autowired
	private MobileForumMapper mobileForumMapper;

	public List<PageData> getForumTypeList() {
		// TODO Auto-generated method stub
		return mobileForumMapper.getForumTypeList();
	}

	public List<PageData> getForumList(PageData pd) {
		// TODO Auto-generated method stub
		return mobileForumMapper.getForumList(pd);
	}

	public PageData getForumInfo(PageData pd) {
		// TODO Auto-generated method stub
		return mobileForumMapper.getForumInfo(pd);
	}

	public List<PageData> getForumImgList(PageData pd) {
		// TODO Auto-generated method stub
		return mobileForumMapper.getForumImgList(pd);
	}

	public List<PageData> getForumReplyList(PageData pd) {
		// TODO Auto-generated method stub
		return mobileForumMapper.getForumReplyList(pd);
	}

	public void deleteReplyInfo(PageData pd) {
		// TODO Auto-generated method stub
		mobileForumMapper.deleteReplyInfo(pd);
	}

	public void insertReplyInfo(PageData pd) {
		// TODO Auto-generated method stub
		mobileForumMapper.insertReplyInfo(pd);
	}

	public Integer getCollectStatus(PageData pd) {
		// TODO Auto-generated method stub
		return mobileForumMapper.getCollectStatus(pd);
	}

	public void delCollectForum(PageData pd) {
		// TODO Auto-generated method stub
		mobileForumMapper.delCollectForum(pd);
	}

	public void collectForum(PageData pd) {
		mobileForumMapper.collectForum(pd);
		
	}

	public void insertForumContent(PageData pd) {
		// TODO Auto-generated method stub
		mobileForumMapper.insertForumContent(pd);
	}

	public void insertForumImg(PageData parampd) {
		// TODO Auto-generated method stub
		mobileForumMapper.insertForumImg(parampd);
	}

	public void updateReadCnt(PageData pd) {
		// TODO Auto-generated method stub
		mobileForumMapper.updateReadCnt(pd);
	}

	public String getRegByFormId(PageData pd) {
		// TODO Auto-generated method stub
		return mobileForumMapper.getRegByFormId(pd);
	}

	public void addIgnoreForum(PageData pd) {
		// TODO Auto-generated method stub
		mobileForumMapper.addIgnoreForum(pd);
	}

	public void addForumReport(PageData pd) {
		// TODO Auto-generated method stub
		mobileForumMapper.addForumReport(pd);
	}

	
}
