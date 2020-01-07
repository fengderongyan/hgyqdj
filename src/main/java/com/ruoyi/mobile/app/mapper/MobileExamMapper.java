package com.ruoyi.mobile.app.mapper;

import java.util.List;

import com.ruoyi.common.utils.PageData;
import com.ruoyi.project.questionmanage.domain.ExamQuestion;

public interface MobileExamMapper {

	public List<PageData> getExamList(PageData pd);

	public PageData getExamInfo(PageData pd);

	public List<PageData> getExamQuestion(PageData pd);

	public List<PageData> getQuestionOptionList(PageData pd);

	public void insertMyAnswer(PageData pd);

	public Integer getExamIsdone(PageData pd);

	public void insertMyExam(PageData pd);

	public void deleteMyExamAnswer(PageData pd);


}
