package com.ruoyi.project.bannermanage.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.ruoyi.project.bannermanage.service.BannerManageService;

@Controller
@RequestMapping("/bannermanage")
public class BannerManageController extends BaseController{

	@Autowired
	private BannerManageService bannerManageService;
	
	@RequiresPermissions("bannermanage:view")
	@RequestMapping("/list")
	public String list(Model model){
		return "bannermanage/list";
	}
	
	@RequestMapping("/getInfoList")
	@ResponseBody
	@RequiresPermissions("bannermanage:view")
	public TableDataInfo getInfoList(){
		PageData pd = this.getPageData();
		startPage();
		List<PageData> infoList = bannerManageService.getInfoList(pd);
		return getDataTable(infoList);
	}
	
	@RequestMapping("/add")
	@RequiresPermissions("bannermanage:add")
	public String add(Model model){
		
		return "bannermanage/add";
	}
	
	@RequiresPermissions("bannermanage:add")
    @Log(title = "首页轮播图", businessType = BusinessType.INSERT)
    @PostMapping("/addSave")
    @ResponseBody
	public AjaxResult addSave(){
		PageData pd = this.getPageData();
		pd.put("record_id", this.getSysUser().getLoginName());
		pd.put("status", "1");
		bannerManageService.addSave(pd);
		return success();
	} 
	
	@RequestMapping("/edit/{id}")
	@RequiresPermissions("bannermanage:edit")
	public String edit(@PathVariable("id") String id, Model model){
		PageData pd = this.getPageData();
		pd.put("id", id);
		PageData info = bannerManageService.getInfo(pd);
		model.addAttribute("info", info);
		return "bannermanage/edit";
	}
	
	@RequiresPermissions("bannermanage:edit")
    @Log(title = "首页轮播图", businessType = BusinessType.UPDATE)
    @PostMapping("/editSave")
    @ResponseBody
	public AjaxResult editSave(){
		PageData pd = this.getPageData();
		pd.put("record_id", this.getSysUser().getLoginName());
		bannerManageService.editSave(pd);
		return success();
	}
	
	@RequiresPermissions("bannermanage:remove")
    @Log(title = "首页轮播图", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
	public AjaxResult remove(){
		PageData pd = this.getPageData();
		String ids = pd.getString("ids");
		String[] idArray = Convert.toStrArray(ids);
		bannerManageService.removeByIds(idArray);
		return success();
	} 
	
}
