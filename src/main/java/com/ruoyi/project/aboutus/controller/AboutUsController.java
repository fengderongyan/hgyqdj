package com.ruoyi.project.aboutus.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.utils.PageData;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.aboutus.service.AboutUsService;
import com.ruoyi.project.bannermanage.service.BannerManageService;

@Controller
@RequestMapping("/aboutus")
public class AboutUsController extends BaseController{

	@Autowired
	private AboutUsService aboutUsManageService;
	

	@RequestMapping("/aboutusFrame")
	public String aboutusFrame(Model model){
		PageData info = aboutUsManageService.getAboutUsInfo();
		model.addAttribute("info", info);
		return "aboutus/aboutusFrame";
	}
	@RequestMapping("/saveAboutUs")
	@ResponseBody
	public AjaxResult saveAboutUs(){
		PageData pd = this.getPageData();
		aboutUsManageService.saveAboutUs(pd);
		return success();
	}
	
}
