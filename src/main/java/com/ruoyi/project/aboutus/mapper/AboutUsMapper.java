package com.ruoyi.project.aboutus.mapper;

import java.util.List;

import com.ruoyi.common.utils.PageData;
import com.ruoyi.project.questionmanage.domain.Question;

public interface AboutUsMapper {

	public PageData getAboutUsInfo();

	public void saveAboutUs(PageData pd);

	


}
