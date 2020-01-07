package com.ruoyi.project.wish.controller;

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
import com.ruoyi.project.wish.service.WishService;

@Controller
@RequestMapping("/wish")
public class WishController extends BaseController{

	@Autowired
	private WishService wishService;
	
	@RequiresPermissions("wish:view")
	@RequestMapping("/list")
	public String list(Model model){
		return "wish/list";
	}
	
	@RequestMapping("/getInfoList")
	@ResponseBody
	@RequiresPermissions("wish:view")
	public TableDataInfo getInfoList(){
		PageData pd = this.getPageData();
		startPage();
		List<PageData> infoList = wishService.getInfoList(pd);
		return getDataTable(infoList);
	}
	
	@RequestMapping("/add")
	@RequiresPermissions("wish:add")
	public String add(Model model){
		
		return "wish/add";
	}
	
	@RequiresPermissions("wish:add")
    @Log(title = "惠及民生", businessType = BusinessType.INSERT)
    @PostMapping("/addSave")
    @ResponseBody
	public AjaxResult addSave(){
		PageData pd = this.getPageData();
		pd.put("record_id", this.getSysUser().getLoginName());
		pd.put("status", "1");
		wishService.addSave(pd);
		return success();
	} 
	
	@RequestMapping("/edit/{id}")
	@RequiresPermissions("wish:edit")
	public String edit(@PathVariable("id") String id, Model model){
		PageData pd = this.getPageData();
		pd.put("id", id);
		PageData info = wishService.getInfo(pd);
		model.addAttribute("info", info);
		return "wish/edit";
	}
	
	@RequiresPermissions("wish:edit")
    @Log(title = "惠及民生", businessType = BusinessType.UPDATE)
    @PostMapping("/editSave")
    @ResponseBody
	public AjaxResult editSave(){
		PageData pd = this.getPageData();
		pd.put("record_id", this.getSysUser().getLoginName());
		wishService.editSave(pd);
		return success();
	}
	
	@RequiresPermissions("wish:remove")
    @Log(title = "惠及民生", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
	public AjaxResult remove(){
		PageData pd = this.getPageData();
		String ids = pd.getString("ids");
		String[] idArray = Convert.toStrArray(ids);
		wishService.removeByIds(idArray);
		return success();
	} 
	
	@RequestMapping("/detail/{id}")
	public String detail(@PathVariable("id") String id, Model model){
		PageData pd = this.getPageData();
		pd.put("id", id);
		PageData info = wishService.getInfo(pd);
		model.addAttribute("info", info);
		return "wish/detail";
	}
	
	@RequestMapping("/finishWish")
	@ResponseBody
	public AjaxResult finishWish(){
		PageData pd = this.getPageData();
		wishService.finishWish(pd);
		return success();
	}
}
