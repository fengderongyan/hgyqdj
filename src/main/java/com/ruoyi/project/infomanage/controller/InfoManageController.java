package com.ruoyi.project.infomanage.controller;

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
import org.springframework.web.multipart.MultipartFile;

import com.ruoyi.common.utils.PageData;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.infomanage.service.InfoManageService;
import com.ruoyi.project.questionmanage.domain.Question;
import com.ruoyi.project.questionmanage.service.QuestionManageService;

@Controller
@RequestMapping("/infomanage")
public class InfoManageController extends BaseController{

	@Autowired
	private InfoManageService infoManageService;
	
	@RequiresPermissions("infomanage:view")
	@RequestMapping("/list")
	public String list(Model model){
		PageData pd = this.getPageData();
		List<PageData> itemList = infoManageService.getItemListByClass(pd);
		model.addAttribute("itemList", itemList);
		return "infomanage/list";
	}
	
	@RequestMapping("/getInfoList")
	@ResponseBody
	@RequiresPermissions("infomanage:view")
	public TableDataInfo getInfoList(){
		PageData pd = this.getPageData();
		startPage();
		List<PageData> questionList = infoManageService.getInfoList(pd);
		return getDataTable(questionList);
	}
	
	@RequestMapping("/add")
	@RequiresPermissions("infomanage:add")
	public String add(Model model){
		PageData pd = this.getPageData();
		List<PageData> itemList = infoManageService.getItemListByClass(pd);
		model.addAttribute("itemList", itemList);
		return "infomanage/add";
	}
	
	@RequiresPermissions("infomanage:add")
    @Log(title = "资讯管理", businessType = BusinessType.INSERT)
    @PostMapping("/addSave")
    @ResponseBody
	public AjaxResult addSave(){
		PageData pd = this.getPageData();
		pd.put("record_id", this.getSysUser().getLoginName());
		pd.put("status", "1");
		infoManageService.addSave(pd);
		return success();
	} 
	
	@RequestMapping("/edit/{id}")
	@RequiresPermissions("infomanage:edit")
	public String edit(@PathVariable("id") String id, Model model){
		PageData pd = this.getPageData();
		pd.put("id", id);
		List<PageData> itemList = infoManageService.getItemListByClass(pd);
		PageData info = infoManageService.getInfo(pd);
		model.addAttribute("itemList", itemList);
		model.addAttribute("info", info);
		return "infomanage/edit";
	}
	
	@RequiresPermissions("infomanage:edit")
    @Log(title = "资讯管理", businessType = BusinessType.UPDATE)
    @PostMapping("/editSave")
    @ResponseBody
	public AjaxResult editSave(){
		PageData pd = this.getPageData();
		pd.put("record_id", this.getSysUser().getLoginName());
		infoManageService.editSave(pd);
		return success();
	}
	
	@RequiresPermissions("infomanage:remove")
    @Log(title = "资讯管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
	public AjaxResult remove(){
		PageData pd = this.getPageData();
		String ids = pd.getString("ids");
		String[] idArray = Convert.toStrArray(ids);
		infoManageService.removeByIds(idArray);
		return success();
	} 
	
	/**
	 * 描述：视频教育
	 * @return
	 * @author yanbs
	 * @Date 2019-09-17
	 */
	@RequiresPermissions("infomanage:view")
	@RequestMapping("/videoList")
	public String videoList(){
		
		return "infomanage/video/list";
	}
	
	/**
	 * 描述：获取视频教育列表
	 * @return
	 * @author yanbs
	 * @Date 2019-09-17
	 */
	@RequestMapping("/getVideoList")
	@ResponseBody
	@RequiresPermissions("infomanage:list")
	public TableDataInfo getVideoList(){
		PageData pd = this.getPageData();
		startPage();
		List<PageData> questionList = infoManageService.getVideoList(pd);
		return getDataTable(questionList);
	}
	
	/**
	 * 描述：添加视频
	 * @return
	 * @author yanbs
	 * @Date 2019-09-18
	 */
	@RequestMapping("/videoAdd")
	@RequiresPermissions("infomanage:add")
	public String videoAdd(){
		return "infomanage/video/add";
	}
	
	@RequestMapping("/videoUpload")
	public String videoUpload(){
		return "infomanage/video/videoUpload";
	}
	
	/**
	 * 描述：保存视频
	 * @return
	 * @author yanbs
	 * @Date 2019-09-18
	 */
	@RequestMapping("/videoAddSave")
	@RequiresPermissions("infomanage:add")
	@ResponseBody
	public AjaxResult videoAddSave(){
		PageData pd = this.getPageData();
		pd.put("record_id", this.getSysUser().getLoginName());
		pd.put("status", "1");
		infoManageService.videoAddSave(pd);
		return success();
	}
	
	@RequestMapping("/videoEdit/{id}")
	@RequiresPermissions("infomanage:edit")
	public String videoEdit(@PathVariable("id") String id, Model model){
		PageData pd = this.getPageData();
		pd.put("id", id);
		PageData videoInfo = infoManageService.getVideoInfo(pd);
		model.addAttribute("videoInfo", videoInfo);
		return "infomanage/video/edit";
	}
	
	@RequestMapping("/videoEditSave")
	@RequiresPermissions("infomanage:edit")
	@ResponseBody
	public AjaxResult videoEditSave(){
		PageData pd = this.getPageData();
		pd.put("record_id", this.getSysUser().getLoginName());
		infoManageService.videoEditSave(pd);
		return success();
	}
}
