package com.ruoyi.project.noticeuser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.PageData;
import com.ruoyi.project.bannermanage.mapper.BannerManageMapper;
import com.ruoyi.project.noticeinfo.mapper.NoticeInfoMapper;
import com.ruoyi.project.noticeuser.mapper.NoticeUserMapper;

@Service
public class NoticeUserService{

	@Autowired
	public NoticeUserMapper noticeUserMapper;

	public List<PageData> getInfoList(PageData pd) {
		return noticeUserMapper.getInfoList(pd);
	}

	public void addSave(PageData pd) {
		noticeUserMapper.addSave(pd);
		
	}
	public PageData getInfo(PageData pd) {
		// TODO Auto-generated method stub
		return noticeUserMapper.getInfo(pd);
	}

	public void editSave(PageData pd) {
		// TODO Auto-generated method stub
		noticeUserMapper.editSave(pd);
	}

	public void removeByIds(String[] idArray) {
		// TODO Auto-generated method stub
		noticeUserMapper.removeByIds(idArray);
	}

	public List<PageData> getUserSelectList(PageData pd) {
		// TODO Auto-generated method stub
		return noticeUserMapper.getUserSelectList(pd);
	}

	public List<PageData> findRegistrationIds(PageData pd) {
		// TODO Auto-generated method stub
		return noticeUserMapper.findRegistrationIds(pd);
	}

	
}
