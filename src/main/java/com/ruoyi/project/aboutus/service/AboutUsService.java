package com.ruoyi.project.aboutus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.PageData;
import com.ruoyi.project.aboutus.mapper.AboutUsMapper;
import com.ruoyi.project.bannermanage.mapper.BannerManageMapper;

@Service
public class AboutUsService{

	@Autowired
	public AboutUsMapper aboutUsMapper;

	public PageData getAboutUsInfo() {
		// TODO Auto-generated method stub
		return aboutUsMapper.getAboutUsInfo();
	}

	public void saveAboutUs(PageData pd) {
		// TODO Auto-generated method stub
		aboutUsMapper.saveAboutUs(pd);
	}

	
}
