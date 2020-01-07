package com.ruoyi.project.deptactivity.controller;

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
import com.ruoyi.project.deptactivity.service.DeptActivityService;

@Controller
@RequestMapping("/deptactivity")
public class DeptActivityController extends BaseController{

	@Autowired
	private DeptActivityService deptActivityService;
	
	@RequiresPermissions("deptactivity:view")
	@RequestMapping("/list")
	public String list(Model model){
		List<PageData> orgList = deptActivityService.getOrgList();
		model.addAttribute("orgList", orgList);
		return "deptactivity/list";
	}
	
	@RequestMapping("/getInfoList")
	@ResponseBody
	@RequiresPermissions("deptactivity:view")
	public TableDataInfo getInfoList(){
		PageData pd = this.getPageData();
		startPage();
		List<PageData> infoList = deptActivityService.getInfoList(pd);
		return getDataTable(infoList);
	}
	
	@RequestMapping("/add")
	@RequiresPermissions("deptactivity:add")
	public String add(Model model){
		List<PageData> orgList = deptActivityService.getOrgList();
		model.addAttribute("orgList", orgList);
		return "deptactivity/add";
	}
	
	@RequiresPermissions("deptactivity:add")
    @Log(title = "活动风采", businessType = BusinessType.INSERT)
    @PostMapping("/addSave")
    @ResponseBody
	public AjaxResult addSave(){
		PageData pd = this.getPageData();
		pd.put("record_id", this.getSysUser().getLoginName());
		pd.put("status", "1");
		deptActivityService.addSave(pd);
		return success();
	} 
	
	@RequestMapping("/edit/{id}")
	@RequiresPermissions("deptactivity:edit")
	public String edit(@PathVariable("id") String id, Model model){
		PageData pd = this.getPageData();
		pd.put("id", id);
		List<PageData> orgList = deptActivityService.getOrgList();
		PageData info = deptActivityService.getInfo(pd);
		model.addAttribute("orgList", orgList);
		model.addAttribute("info", info);
		return "deptactivity/edit";
	}
	
	@RequiresPermissions("deptactivity:edit")
    @Log(title = "活动风采", businessType = BusinessType.UPDATE)
    @PostMapping("/editSave")
    @ResponseBody
	public AjaxResult editSave(){
		PageData pd = this.getPageData();
		pd.put("record_id", this.getSysUser().getLoginName());
		deptActivityService.editSave(pd);
		return success();
	}
	
	@RequiresPermissions("deptactivity:remove")
    @Log(title = "活动风采", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
	public AjaxResult remove(){
		PageData pd = this.getPageData();
		String ids = pd.getString("ids");
		String[] idArray = Convert.toStrArray(ids);
		deptActivityService.removeByIds(idArray);
		return success();
	} 
	
}
