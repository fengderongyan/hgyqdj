package com.ruoyi.project.questionmanage.mapper;

import java.util.List;

import com.ruoyi.common.utils.PageData;
import com.ruoyi.project.questionmanage.domain.Question;

public interface ExamManageMapper {


	public void removeByIds(String[] idArray);

	public List<PageData> getExamList(PageData pd);

	public void addSave(PageData pd);

	public PageData getExamInfo(String id);

	public List<PageData> getDanxuanList(PageData pd);

	public List<PageData> getDuoxuanList(PageData pd);

	public List<PageData> getPanduanList(PageData pd);

	public void addExamQuestion(PageData pd);

}
