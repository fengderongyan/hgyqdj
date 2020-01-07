package com.ruoyi.mobile.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.PageData;
import com.ruoyi.common.utils.ResultUtils;
import com.ruoyi.framework.shiro.service.PasswordService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.mobile.app.service.MobileBaseInfoService;
import com.ruoyi.mobile.app.service.MobileCommonService;
import com.ruoyi.project.service.RedisService;

import net.bytebuddy.asm.Advice.This;

/**
 * 描述：我的基础信息页面
 * @author yanbs
 * @Date 2019-09-08
 */
@RestController
@RequestMapping("/mobile/app")
public class MobileBaseInfoController extends BaseController{

	@Autowired
	private MobileBaseInfoService mobileBaseInfoService;
	@Autowired
	private PasswordService passwordService;
	@Autowired
	private RedisService redisService;
	@Autowired
	private MobileCommonService mobileCommonService;
	
	/**
	 * 描述：个人中心
	 * @return
	 * @author yanbs
	 * @Date 2019-09-09
	 */
	@RequestMapping("/getMyIndexInfo")
	public Map getMyIndexInfo(){
		try {
			PageData result = new PageData();
			PageData pd = new PageData();
			pd.put("record_id", this.getMobileUser().getLoginName());
			//我的帖子
			String article_cnt = mobileBaseInfoService.getMyForumCnt(pd);
			result.put("article_cnt", article_cnt);
			//我的收藏
			String collection_cnt = mobileBaseInfoService.getMyCollectCnt(pd);
			result.put("collection_cnt", collection_cnt);
			//我的心愿
			String dream_cnt = mobileBaseInfoService.getMyWishCnt(pd);
			result.put("dream_cnt", dream_cnt);
			//我的考试
			PageData examcntpd = mobileBaseInfoService.getExamCnt(pd);
			String exam_cnt = examcntpd.getString("exam_cnt");
			result.put("exam_cnt", exam_cnt);
			return ResultUtils.returnOk(result);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常！");
		}
	}
	
	/**
	 * 描述：获取我的帖子列表
	 * @return
	 * @author yanbs
	 * @Date 2019-10-09
	 */
	@RequestMapping("/getMyForumList")
	public Map getMyForumList(){
		try {
			PageData pd = this.getPageData();
			String pageNum = pd.getString("pageNum");
			String pageSize = pd.getString("pageSize");
			if("".equals(pageNum)){
				return ResultUtils.returnError(12001, "pageNum不能为空");
			}
			if("".equals(pageSize)){
				return ResultUtils.returnError(12001, "pageSize不能为空");
			}
			pd.put("record_id", this.getMobileUser().getLoginName());
			startPage();
			List<PageData> myForumList = mobileBaseInfoService.getMyForumList(pd);
			return ResultUtils.returnOk(myForumList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：删除帖子
	 * @return
	 * @author yanbs
	 * @Date 2019-10-09
	 */
	@RequestMapping("/deleteForum")
	public Map deleteForum(){
		try {
			PageData pd = this.getPageData();
			String forum_id = pd.getString("forum_id");
			if("".equals(forum_id)){
				return ResultUtils.returnError(12001, "forum_id不能为空");
			}
			mobileBaseInfoService.deleteForum(pd);
			return ResultUtils.returnOk();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：获取我的收藏
	 * @return
	 * @author yanbs
	 * @Date 2019-10-09
	 */
	@RequestMapping("/getMyCollectionList")
	public Map getMyCollectionList(){
		try {
			PageData pd = this.getPageData();
			String pageNum = pd.getString("pageNum");
			String pageSize = pd.getString("pageSize");
			if("".equals(pageNum)){
				return ResultUtils.returnError(12001, "pageNum不能为空");
			}
			if("".equals(pageSize)){
				return ResultUtils.returnError(12001, "pageSize不能为空");
			}
			pd.put("record_id", this.getMobileUser().getLoginName());
			startPage();
			List<PageData> myForumList = mobileBaseInfoService.getMyCollectionList(pd);
			return ResultUtils.returnOk(myForumList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：获取我的心愿
	 * @return
	 * @author yanbs
	 * @Date 2019-10-09
	 */
	@RequestMapping("/getMyClaimList")
	public Map getMyClaimList(){
		try {
			PageData pd = this.getPageData();
			String pageNum = pd.getString("pageNum");
			String pageSize = pd.getString("pageSize");
			if("".equals(pageNum)){
				return ResultUtils.returnError(12001, "pageNum不能为空");
			}
			if("".equals(pageSize)){
				return ResultUtils.returnError(12001, "pageSize不能为空");
			}
			pd.put("claim_id", this.getMobileUser().getLoginName());
			startPage();
			List<PageData> myClaimList = mobileCommonService.getMyClaimList(pd);
			return ResultUtils.returnOk(myClaimList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：获取我的考试列表
	 * @return
	 * @author yanbs
	 * @Date 2019-09-09
	 */
	@RequestMapping("/getMyExamList")
	public Map getMyExamList(){
		try {
			PageData pd = this.getPageData();
			String pageNum = pd.getString("pageNum");
			String pageSize = pd.getString("pageSize");
			if("".equals(pageNum)){
				return ResultUtils.returnError(12001, "pageNum不能为空");
			}
			if("".equals(pageSize)){
				return ResultUtils.returnError(12001, "pageSize不能为空");
			}
			pd.put("record_id", this.getMobileUser().getLoginName());
			startPage();
			List<PageData> myExamList = mobileBaseInfoService.getMyExamList(pd);
			return ResultUtils.returnOk(myExamList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常！");
		}
	}
	
	/**
	 * 描述：积分排行
	 * @return
	 * @author yanbs
	 * @Date 2019-09-10
	 */
	@RequestMapping("/getPointRankList")
	public Map getPointRank(){
		try {
			PageData pd = this.getPageData();
			String pageNum = pd.getString("pageNum");
			String pageSize = pd.getString("pageSize");
			if("".equals(pageNum)){
				return ResultUtils.returnError(12001, "pageNum不能为空");
			}
			if("".equals(pageSize)){
				return ResultUtils.returnError(12001, "pageSize不能为空");
			}
			pd.put("login_name", this.getMobileUser().getLoginName());
			startPage();
			List<PageData> pointRankList = mobileBaseInfoService.getPointRank(pd);
			return ResultUtils.returnOk(pointRankList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常！");
		}
	}
	
	/**
	 * 描述：获取我的积分及排名
	 * @return
	 * @author yanbs
	 * @Date 2019-09-11
	 */
	@RequestMapping("/getMyPointAndRank")
	public Map getMyPointAndRank(){
		try {
			PageData pd = this.getPageData();
			pd.put("login_name", this.getMobileUser().getLoginName());
			PageData myPoint = mobileBaseInfoService.getMyPoint(pd);
			return ResultUtils.returnOk(myPoint);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常！");
		}
	}
	
	/**
	 * 描述：获取积分规则
	 * @return
	 * @author yanbs
	 * @Date 2019-09-10
	 */
	@RequestMapping("/getRankRule")
	public Map getRankRule(){
		try {
			PageData rankRule = mobileBaseInfoService.getRankRule();
			return ResultUtils.returnOk(rankRule);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常！");
		}
	}
	
	
	
	/**
	 * 描述：修改密码
	 * @return
	 * @author yanbs
	 * @Date 2019-09-10
	 */
	@RequestMapping("/editPassword")
	public Map editPassword(){
		try {
			PageData pd = this.getPageData();
			String oldpwd = pd.getString("oldpwd");
			String newpwd = pd.getString("newpwd");
			if("".equals(oldpwd)){
				return ResultUtils.returnError(12001, "oldpwd不能为空");
			}
			if("".equals(newpwd)){
				return ResultUtils.returnError(12001, "newpwd不能为空");
			}
			pd.put("login_name", this.getMobileUser().getLoginName());
			PageData userpd = mobileBaseInfoService.getUserInfo(pd);
			String oldPassword = passwordService.encryptPassword(this.getMobileUser().getLoginName(), oldpwd,  userpd.getString("salt"));
			System.out.println("oldPassword::" + oldPassword);
			if(!oldPassword.equals(userpd.getString("password"))){//原密码不符
				return ResultUtils.returnError(12002, "原密码不正确");
			}
			if(oldpwd.equals(newpwd)){
				return ResultUtils.returnError(12002, "原密码与新密码不能相同");
			}
			String newPassword = passwordService.encryptPassword(this.getMobileUser().getLoginName(), newpwd,  userpd.getString("salt"));
			pd.put("newPassword", newPassword);
			//修改密码
			mobileBaseInfoService.editPassword(pd);
			//删除redis缓存
			redisService.delete(Constants.TOKEN_KEY_FRIST + this.getMobileUser().getLoginName() + "_info");
			return ResultUtils.returnOk();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常！");
		}
	}
	
	/**
	 * 描述：通知是否已读
	 * @return
	 * @author yanbs
	 * @Date 2019-09-17
	 */
	@RequestMapping("/getIsNoticeRead")
	public Map getIsNoticeRead(){
		try {
			PageData pd = this.getPageData();
			pd.put("user_id", this.getMobileUser().getLoginName());
			Integer is_read = mobileBaseInfoService.getIsNoticeRead(pd);
			
			return ResultUtils.returnOk(is_read);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：更新通知为已读
	 * @return
	 * @author yanbs
	 * @Date 2019-09-17
	 */
	@RequestMapping("/updateMyNoticeRead")
	public Map updateMyNoticeRead(){
		try {
			PageData pd = this.getPageData();
			pd.put("user_id", this.getMobileUser().getLoginName());
			mobileBaseInfoService.updateMyNticeRead(pd);
			return ResultUtils.returnOk();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：获取通知列表
	 * @return
	 * @author yanbs
	 * @Date 2019-09-17
	 */
	@RequestMapping("/getMyNoticeList")
	public Map getMyNoticeList(){
		try {
			PageData pd = this.getPageData();
			String pageNum = pd.getString("pageNum");
			String pageSize = pd.getString("pageSize");
			if("".equals(pageNum)){
				return ResultUtils.returnError(12001, "pageNum不能为空");
			}
			if("".equals(pageSize)){
				return ResultUtils.returnError(12001, "pageSize不能为空");
			}
			pd.put("user_id", this.getMobileUser().getLoginName());
			startPage();
			List<PageData> myNoticeList = mobileBaseInfoService.getMyNoticeList(pd);
			return ResultUtils.returnOk(myNoticeList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：获取通知详情
	 * @return
	 * @author yanbs
	 * @Date 2019-09-17
	 */
	@RequestMapping("/getMyNoticeInfo")
	public Map getMyNoticeInfo(){
		try {
			PageData pd = this.getPageData();
			String mynotice_id = pd.getString("mynotice_id");
			if("".equals(mynotice_id)){
				return ResultUtils.returnError(12001, "mynotice_id不能为空");
			}
			PageData myNoticeInfo = mobileBaseInfoService.getMyNoticeInfo(pd);
			return ResultUtils.returnOk(myNoticeInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：关于我们
	 * @return
	 * @author yanbs
	 * @Date 2019-09-18
	 */
	@RequestMapping("/getAboutUs")
	public Map getAboutUs(){
		try {
			PageData abuotus = mobileBaseInfoService.getAboutUs();
			return ResultUtils.returnOk(abuotus);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：获取我的评论列表（别人评论我的文章）
	 * @return
	 * @author yanbs
	 * @Date 2019-10-09
	 */
	@RequestMapping("/getMyReplyList")
	public Map getMyReplyList(){
		try {
			PageData pd = this.getPageData();
			String pageNum = pd.getString("pageNum");
			String pageSize = pd.getString("pageSize");
			if("".equals(pageNum)){
				return ResultUtils.returnError(12001, "pageNum不能为空");
			}
			if("".equals(pageSize)){
				return ResultUtils.returnError(12001, "pageSize不能为空");
			}
			pd.put("record_id", this.getMobileUser().getLoginName());
			List<PageData> myReplyList = mobileBaseInfoService.getMyReplyList(pd);
			return ResultUtils.returnOk(myReplyList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	@RequestMapping("/mobileLogout")
	public Map mobileLogout(){
		try {
			String login_name = this.getMobileUser().getLoginName();
			mobileBaseInfoService.mobileLogout(login_name);
			return ResultUtils.returnOk();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
}
