package com.ruoyi.project.bannermanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.PageData;
import com.ruoyi.project.bannermanage.mapper.BannerManageMapper;

@Service
public class BannerManageService{

	@Autowired
	public BannerManageMapper bannerManageMapper;

	public List<PageData> getInfoList(PageData pd) {
		return bannerManageMapper.getInfoList(pd);
	}

	public void addSave(PageData pd) {
		bannerManageMapper.addSave(pd);
		
	}
	public PageData getInfo(PageData pd) {
		// TODO Auto-generated method stub
		return bannerManageMapper.getInfo(pd);
	}

	public void editSave(PageData pd) {
		// TODO Auto-generated method stub
		bannerManageMapper.editSave(pd);
	}

	public void removeByIds(String[] idArray) {
		// TODO Auto-generated method stub
		bannerManageMapper.removeByIds(idArray);
	}

	
}
