package com.ruoyi.project.questionmanage.mapper;

import java.util.List;

import com.ruoyi.common.utils.PageData;
import com.ruoyi.project.questionmanage.domain.Question;

public interface QuestionManageMapper {

	public Integer isHasType(String type);

	public void insertQuestion(Question question);

	public void insertQuestionOpt(PageData pd);

	public List<PageData> getQuestionList(PageData pd);

	public void addQuestion(PageData pd);

	public void addQuestionOption(PageData pd);

	public PageData getQuestionInfo(String id);

	public void editQuestion(PageData pd);

	public void deleteQuestionOpt(PageData pd);

	public void removeByIds(String[] idArray);

}
