package com.ruoyi.project.foruminfo.controller;

import java.util.List;
import com.ruoyi.common.utils.PageData;
import org.springframework.ui.Model;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.foruminfo.service.ForumInfoService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 党员论坛Controller
 * 
 * @author yanbs
 * @date 2019-09-26
 */
@Controller
@RequestMapping("/foruminfo")
public class ForumInfoController extends BaseController
{
    private String prefix = "foruminfo";

    @Autowired
    private ForumInfoService forumInfoService;

    @RequiresPermissions("foruminfo:view")
    @RequestMapping("/list")
    public String list()
    {
        return prefix + "/list";
    }

    /**
     * 查询党员论坛列表
     */
    @RequiresPermissions("foruminfo:list")
    @PostMapping("/selectList")
    @ResponseBody
    public TableDataInfo selectList()
    {
    	PageData pd = this.getPageData();
        startPage();
        List<PageData> list = forumInfoService.selectList(pd);
        return getDataTable(list);
    }


    /**
     * 新增党员论坛
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存党员论坛
     */
    @RequiresPermissions("foruminfo:add")
    @Log(title = "党员论坛", businessType = BusinessType.INSERT)
    @PostMapping("/addSave")
    @ResponseBody
    public AjaxResult addSave()
    {
    	PageData pd = this.getPageData();
    	pd.put("record_id", this.getSysUser().getLoginName());
        return toAjax(forumInfoService.addSave(pd));
    }

    /**
     * 党员论坛文章详情
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, Model model)
    {
        PageData info = forumInfoService.selectById(id);
        if(info != null){
        	List<PageData> imgList = forumInfoService.selectImgsByForumId(info.getString("id"));
        	info.put("imgList", imgList);
        }
        model.addAttribute("info", info);
        return prefix + "/detail";
    }


    /**
     * 删除党员论坛
     */
    @RequiresPermissions("foruminfo:remove")
    @Log(title = "党员论坛", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(forumInfoService.deleteByIds(ids));
    }
    
    /**
     * 描述：查看论坛回答
     * @return
     * @author yanbs
     * @Date 2019-10-09
     */
    @RequiresPermissions("foruminfo:view")
    @RequestMapping("/showReply")
    public String showReply()
    {
        return prefix + "/replyList";
    }
    
    /**
     * 查询论坛回答列表
     */
    @RequiresPermissions("foruminfo:list")
    @PostMapping("/getReplyList")
    @ResponseBody
    public TableDataInfo getReplyList()
    {
    	PageData pd = this.getPageData();
        startPage();
        List<PageData> list = forumInfoService.getReplyList(pd);
        return getDataTable(list);
    }
    
    @RequiresPermissions("foruminfo:remove")
    @Log(title = "论坛回答", businessType = BusinessType.DELETE)
    @PostMapping( "/replyRemove")
    @ResponseBody
    public AjaxResult replyRemove(String ids){
    	return toAjax(forumInfoService.deleteReplyByIds(ids));
    }
}
