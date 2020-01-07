package com.ruoyi.project.questionmanage.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import com.ruoyi.project.questionmanage.service.ExamManageService;

@Controller
@RequestMapping("/questionmanage/exam")
public class ExamManageController extends BaseController{

	private String prefix = "questionmanage/exam/";
	
	@Autowired
	private ExamManageService examManageService;
	
	@RequiresPermissions("questionmanage:exam:view")
	@RequestMapping("/examList")
	public String examList(){
		
		return prefix + "examList";
	}
	
	@RequestMapping("/getExamList")
	@ResponseBody
	@RequiresPermissions("questionmanage:exam:list")
	public TableDataInfo getExamList(){
		PageData pd = this.getPageData();
		startPage();
		List<PageData> examList = examManageService.getExamList(pd);
		return getDataTable(examList);
	}
	
	@RequestMapping("/add")
	@RequiresPermissions("questionmanage:exam:add")
	public String add(){
		
		return prefix + "add";
	}
	
	@RequiresPermissions("questionmanage:exam:add")
    @Log(title = "考试管理", businessType = BusinessType.INSERT)
    @PostMapping("/addSave")
    @ResponseBody
	public AjaxResult addSave(){
		PageData pd = this.getPageData();
		pd.put("record_id", this.getSysUser().getLoginName());
		pd.put("status", "1");
		examManageService.addSave(pd);
		return success();
	}
	
	@RequestMapping("/detail/{id}")
	@RequiresPermissions("questionmanage:exam:list")
	public String edit(@PathVariable("id") String id, Model model){
		PageData exampd = examManageService.getExamInfo(id);
		model.addAttribute("exam", exampd);
		return prefix + "detail";
	}
	
	
	@RequiresPermissions("questionmanage:exam:remove")
    @Log(title = "考试管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
	public AjaxResult remove(){
		PageData pd = this.getPageData();
		String ids = pd.getString("ids");
		String[] idArray = Convert.toStrArray(ids);
		examManageService.removeByIds(idArray);
		return success();
	}
	
}
