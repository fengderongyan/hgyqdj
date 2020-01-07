package com.ruoyi.project.noticeinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.PageData;
import com.ruoyi.project.bannermanage.mapper.BannerManageMapper;
import com.ruoyi.project.noticeinfo.mapper.NoticeInfoMapper;

@Service
public class NoticeInfoService{

	@Autowired
	public NoticeInfoMapper noticeInfoMapper;

	public List<PageData> getInfoList(PageData pd) {
		return noticeInfoMapper.getInfoList(pd);
	}

	public void addSave(PageData pd) {
		noticeInfoMapper.addSave(pd);
		
	}
	public PageData getInfo(PageData pd) {
		// TODO Auto-generated method stub
		return noticeInfoMapper.getInfo(pd);
	}

	public void editSave(PageData pd) {
		// TODO Auto-generated method stub
		noticeInfoMapper.editSave(pd);
	}

	public void removeByIds(String[] idArray) {
		// TODO Auto-generated method stub
		noticeInfoMapper.removeByIds(idArray);
	}

	
}
