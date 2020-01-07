package com.ruoyi.project.infomanage.mapper;

import java.util.List;

import com.ruoyi.common.utils.PageData;
import com.ruoyi.project.questionmanage.domain.Question;

public interface InfoManageMapper {

	public List<PageData> getInfoList(PageData pd);

	public List<PageData> getItemListByClass(PageData pd);

	public void addSave(PageData pd);

	public PageData getInfo(PageData pd);

	public void editSave(PageData pd);

	public void removeByIds(String[] idArray);

	public List<PageData> getVideoList(PageData pd);

	public void videoAddSave(PageData pd);

	public PageData getVideoInfo(PageData pd);

	public void videoEditSave(PageData pd);

	

}
