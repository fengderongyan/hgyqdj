package com.ruoyi.project.infomanage.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.PageData;
import com.ruoyi.framework.web.service.DictService;
import com.ruoyi.project.infomanage.mapper.InfoManageMapper;
import com.ruoyi.project.questionmanage.domain.Question;
import com.ruoyi.project.questionmanage.domain.QuestionOption;
import com.ruoyi.project.questionmanage.mapper.QuestionManageMapper;

@Service
public class InfoManageService{

	@Autowired
	public InfoManageMapper infoManageMapper;

	public List<PageData> getInfoList(PageData pd) {
		return infoManageMapper.getInfoList(pd);
	}

	public List<PageData> getItemListByClass(PageData pd) {
		// TODO Auto-generated method stub
		return infoManageMapper.getItemListByClass(pd);
	}

	public void addSave(PageData pd) {
		infoManageMapper.addSave(pd);
		
	}

	public PageData getInfo(PageData pd) {
		// TODO Auto-generated method stub
		return infoManageMapper.getInfo(pd);
	}

	public void editSave(PageData pd) {
		// TODO Auto-generated method stub
		infoManageMapper.editSave(pd);
	}

	public void removeByIds(String[] idArray) {
		// TODO Auto-generated method stub
		infoManageMapper.removeByIds(idArray);
	}

	public List<PageData> getVideoList(PageData pd) {
		// TODO Auto-generated method stub
		return infoManageMapper.getVideoList(pd);
	}

	public void videoAddSave(PageData pd) {
		// TODO Auto-generated method stub
		infoManageMapper.videoAddSave(pd);
	}

	public PageData getVideoInfo(PageData pd) {
		// TODO Auto-generated method stub
		return infoManageMapper.getVideoInfo(pd);
	}

	public void videoEditSave(PageData pd) {
		// TODO Auto-generated method stub
		infoManageMapper.videoEditSave(pd);
	}
	
}
