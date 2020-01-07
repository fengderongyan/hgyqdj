package com.ruoyi.project.questionmanage.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.ruoyi.project.questionmanage.domain.Question;
import com.ruoyi.project.questionmanage.service.QuestionManageService;

@Controller
@RequestMapping("/questionmanage/question")
public class QuestionManageController extends BaseController{

	private String prefix = "questionmanage/question/";
	
	@Autowired
	private QuestionManageService questionManageService;
	
	@RequiresPermissions("questionmanage:question:view")
	@RequestMapping("/questionList")
	public String questionList(){
		
		return prefix + "questionList";
	}
	
	@RequestMapping("/getQuestionList")
	@ResponseBody
	@RequiresPermissions("questionmanage:question:list")
	public TableDataInfo getQuestionList(){
		PageData pd = this.getPageData();
		startPage();
		List<PageData> questionList = questionManageService.getQuestionList(pd);
		return getDataTable(questionList);
	}
	
	@RequestMapping("/add")
	@RequiresPermissions("questionmanage:question:add")
	public String add(){
		
		return prefix + "add";
	}
	
	@RequiresPermissions("questionmanage:question:add")
    @Log(title = "题库管理", businessType = BusinessType.INSERT)
    @PostMapping("/addSave")
    @ResponseBody
	public AjaxResult addSave(){
		PageData pd = this.getPageData();
		pd.put("record_id", this.getSysUser().getLoginName());
		pd.put("status", "1");
		questionManageService.addSave(pd);
		return success();
	}
	
	@RequestMapping("/edit/{id}")
	@RequiresPermissions("questionmanage:question:edit")
	public String edit(@PathVariable("id") String id, Model model){
		PageData questionpd = questionManageService.getQuestionInfo(id);
		model.addAttribute("question", questionpd);
		return prefix + "edit";
	}
	
	@RequestMapping("/editSave")
	@RequiresPermissions("questionmanage:question:edit")
	 @Log(title = "题库管理", businessType = BusinessType.UPDATE)
	@ResponseBody
	public AjaxResult editSave(){
		PageData pd = this.getPageData();
		pd.put("record_id", this.getSysUser().getLoginName());
		questionManageService.editSave(pd);
		return success();
	}
	
	@RequiresPermissions("questionmanage:question:remove")
    @Log(title = "题库管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
	public AjaxResult remove(){
		PageData pd = this.getPageData();
		String ids = pd.getString("ids");
		String[] idArray = Convert.toStrArray(ids);
		questionManageService.removeByIds(idArray);
		return success();
	}
	
	@RequiresPermissions("questionmanage:question:import")
    @RequestMapping("/importQuestion")
    @ResponseBody
    public AjaxResult importQuestion(MultipartFile file) throws Exception{
    	String recordId = this.getSysUser().getLoginName();
    	String message = questionManageService.updateImportQuestionExcel(file, recordId);
    	return AjaxResult.success(message);
    }
}
