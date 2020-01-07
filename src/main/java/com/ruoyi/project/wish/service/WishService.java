package com.ruoyi.project.wish.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.PageData;
import com.ruoyi.project.wish.mapper.WishMapper;

@Service
public class WishService{

	@Autowired
	public WishMapper wishMapper;

	public List<PageData> getInfoList(PageData pd) {
		return wishMapper.getInfoList(pd);
	}

	public void addSave(PageData pd) {
		wishMapper.addSave(pd);
		
	}
	public PageData getInfo(PageData pd) {
		// TODO Auto-generated method stub
		return wishMapper.getInfo(pd);
	}

	public void editSave(PageData pd) {
		// TODO Auto-generated method stub
		wishMapper.editSave(pd);
	}

	public void removeByIds(String[] idArray) {
		// TODO Auto-generated method stub
		wishMapper.removeByIds(idArray);
	}

	public void finishWish(PageData pd) {
		// TODO Auto-generated method stub
		wishMapper.finishWish(pd);
	}

	
}
