package com.ruoyi.mobile.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.PageData;
import com.ruoyi.mobile.app.mapper.MobileExamMapper;
import com.ruoyi.project.questionmanage.domain.ExamQuestion;

@Service
public class MobileExamService {
	
	@Autowired
	private MobileExamMapper mobileExamMapper;

	public List<PageData> getExamList(PageData pd) {
		return mobileExamMapper.getExamList(pd);
	}

	public PageData getExamInfo(PageData pd) {
		// TODO Auto-generated method stub
		return mobileExamMapper.getExamInfo(pd);
	}

	public List<PageData> getExamQuestion(PageData pd) {
		// TODO Auto-generated method stub
		return mobileExamMapper.getExamQuestion(pd);
	}

	public List<PageData> getQuestionOptionList(PageData pd) {
		// TODO Auto-generated method stub
		return mobileExamMapper.getQuestionOptionList(pd);
	}

	public void insertMyAnswer(PageData pd) {
		mobileExamMapper.insertMyAnswer(pd);
	}

	public Integer getExamIsdone(PageData pd) {
		// TODO Auto-generated method stub
		return mobileExamMapper.getExamIsdone(pd);
	}

	public void insertMyExam(PageData pd) {
		// TODO Auto-generated method stub
		mobileExamMapper.insertMyExam(pd);
	}

	public void deleteMyExamAnswer(PageData pd) {
		// TODO Auto-generated method stub
		mobileExamMapper.deleteMyExamAnswer(pd);
	}
	

}
