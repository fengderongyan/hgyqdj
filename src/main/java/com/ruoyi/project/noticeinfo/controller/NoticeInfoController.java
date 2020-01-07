package com.ruoyi.project.noticeinfo.controller;

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
import com.ruoyi.project.noticeinfo.service.NoticeInfoService;

@Controller
@RequestMapping("/noticeinfo")
public class NoticeInfoController extends BaseController{

	@Autowired
	private NoticeInfoService noticeInfoService;
	
	@RequiresPermissions("noticeinfo:view")
	@RequestMapping("/list")
	public String list(Model model){
		return "noticeinfo/list";
	}
	
	@RequestMapping("/getInfoList")
	@ResponseBody
	@RequiresPermissions("noticeinfo:view")
	public TableDataInfo getInfoList(){
		PageData pd = this.getPageData();
		startPage();
		List<PageData> infoList = noticeInfoService.getInfoList(pd);
		return getDataTable(infoList);
	}
	
	@RequestMapping("/add")
	@RequiresPermissions("noticeinfo:add")
	public String add(Model model){
		
		return "noticeinfo/add";
	}
	
	@RequiresPermissions("noticeinfo:add")
    @Log(title = "公告管理", businessType = BusinessType.INSERT)
    @PostMapping("/addSave")
    @ResponseBody
	public AjaxResult addSave(){
		PageData pd = this.getPageData();
		pd.put("record_id", this.getSysUser().getLoginName());
		pd.put("status", "1");
		noticeInfoService.addSave(pd);
		return success();
	} 
	
	@RequestMapping("/edit/{id}")
	@RequiresPermissions("noticeinfo:edit")
	public String edit(@PathVariable("id") String id, Model model){
		PageData pd = this.getPageData();
		pd.put("id", id);
		PageData info = noticeInfoService.getInfo(pd);
		model.addAttribute("info", info);
		return "noticeinfo/edit";
	}
	
	@RequiresPermissions("noticeinfo:edit")
    @Log(title = "公告管理", businessType = BusinessType.UPDATE)
    @PostMapping("/editSave")
    @ResponseBody
	public AjaxResult editSave(){
		PageData pd = this.getPageData();
		pd.put("record_id", this.getSysUser().getLoginName());
		noticeInfoService.editSave(pd);
		return success();
	}
	
	@RequiresPermissions("noticeinfo:remove")
    @Log(title = "公告管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
	public AjaxResult remove(){
		PageData pd = this.getPageData();
		String ids = pd.getString("ids");
		String[] idArray = Convert.toStrArray(ids);
		noticeInfoService.removeByIds(idArray);
		return success();
	} 
	
}
