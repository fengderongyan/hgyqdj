package com.ruoyi.project.noticeuser.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.utils.JPushUtil;
import com.ruoyi.common.utils.PageData;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.noticeuser.service.NoticeUserService;

@Controller
@RequestMapping("/noticeuser")
public class NoticeUserController extends BaseController{

	@Autowired
	private NoticeUserService noticeUserService;
	
	@RequiresPermissions("noticeuser:view")
	@RequestMapping("/list")
	public String list(Model model){
		return "noticeuser/list";
	}
	
	@RequestMapping("/getInfoList")
	@ResponseBody
	@RequiresPermissions("noticeuser:view")
	public TableDataInfo getInfoList(){
		PageData pd = this.getPageData();
		startPage();
		List<PageData> infoList = noticeUserService.getInfoList(pd);
		return getDataTable(infoList);
	}
	
	@RequestMapping("/add")
	@RequiresPermissions("noticeuser:add")
	public String add(Model model){
		
		return "noticeuser/add";
	}
	
	@RequiresPermissions("noticeuser:add")
    @Log(title = "通知管理", businessType = BusinessType.INSERT)
    @PostMapping("/addSave")
    @ResponseBody
	public AjaxResult addSave(){
		PageData pd = this.getPageData();
		String loginNames = pd.getString("loginNames");
		String[] loginArray = loginNames.split(",");
		pd.put("record_id", this.getSysUser().getLoginName());
		pd.put("status", "1");
		pd.put("loginArray", loginArray);
		noticeUserService.addSave(pd);
		List<PageData> registrationIds = noticeUserService.findRegistrationIds(pd);
		if(registrationIds != null && registrationIds.size() > 0){
			JPushUtil jPushUtil = new JPushUtil();
			for (int i = 0; i < registrationIds.size(); i++) {
				PageData registrationIdpd = registrationIds.get(i);
				if(registrationIdpd != null){
					String registrationId = registrationIdpd.getString("registrationId");
					if(!"".equals(registrationId)){
						jPushUtil.pushMsg(registrationId, pd.getString("title"), "", "", "您有一条新的通知");
					}
				}
				
			}
		}
		return success();
	} 
	
	@RequestMapping("/edit/{id}")
	@RequiresPermissions("noticeuser:edit")
	public String edit(@PathVariable("id") String id, Model model){
		PageData pd = this.getPageData();
		pd.put("id", id);
		PageData info = noticeUserService.getInfo(pd);
		model.addAttribute("info", info);
		return "noticeuser/edit";
	}
	
	@RequiresPermissions("noticeuser:edit")
    @Log(title = "通知管理", businessType = BusinessType.UPDATE)
    @PostMapping("/editSave")
    @ResponseBody
	public AjaxResult editSave(){
		PageData pd = this.getPageData();
		pd.put("record_id", this.getSysUser().getLoginName());
		noticeUserService.editSave(pd);
		return success();
	}
	
	@RequiresPermissions("noticeuser:remove")
    @Log(title = "通知管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
	public AjaxResult remove(){
		PageData pd = this.getPageData();
		String ids = pd.getString("ids");
		String[] idArray = Convert.toStrArray(ids);
		noticeUserService.removeByIds(idArray);
		return success();
	} 
	
	@RequestMapping("/userSelect")
	public String userSelect(){
		
		return "noticeuser/userSelect";
	}
	
	@RequestMapping("/getUserSelectList")
	@ResponseBody
	public TableDataInfo getUserSelectList(){
		PageData pd = this.getPageData();
		startPage();
		List<PageData> infoList = noticeUserService.getUserSelectList(pd);
		return getDataTable(infoList);
	}
}
