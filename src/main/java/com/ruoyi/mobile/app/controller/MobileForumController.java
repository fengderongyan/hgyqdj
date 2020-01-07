package com.ruoyi.mobile.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.utils.JPushUtil;
import com.ruoyi.common.utils.PageData;
import com.ruoyi.common.utils.ResultUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.mobile.app.service.MobileForumService;

@RestController
@RequestMapping("/mobile/app")
public class MobileForumController extends BaseController{
	@Autowired
	private MobileForumService mobileForumService;
	
	/**
	 * 描述：获取论坛类型列表（包括全部）
	 * @return
	 * @author yanbs
	 * @Date 2019-09-26
	 */
	@RequestMapping("/getForumTypeList")
	public Map getForumTypeList(){
		try {
			List<PageData> typeList = mobileForumService.getForumTypeList();
			List<PageData> resList = new ArrayList<PageData>();
			PageData tmpPd = new PageData();
			tmpPd.put("forum_type_name", "全部");
			tmpPd.put("forum_type", "");
			resList.add(tmpPd);
			resList.addAll(typeList);
			return ResultUtils.returnOk(resList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：获取论坛类型列表（不包括全部）
	 * @return
	 * @author yanbs
	 * @Date 2019-09-26
	 */
	@RequestMapping("/getForumTypeList2")
	public Map getForumTypeList2(){
		try {
			List<PageData> typeList = mobileForumService.getForumTypeList();
			return ResultUtils.returnOk(typeList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：获取论坛列表
	 * @return
	 * @author yanbs
	 * @Date 2019-09-29
	 */
	@RequestMapping("/getForumList")
	public Map getForumList(){
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
			List<PageData> forumList = mobileForumService.getForumList(pd);
			return ResultUtils.returnOk(forumList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：获取论坛详情
	 * @return
	 * @author yanbs
	 * @Date 2019-09-30
	 */
	@RequestMapping("/getForumInfo")
	public Map getForumInfo(){
		try {
			PageData pd = this.getPageData();
			String forum_id = pd.getString("forum_id");
			if("".equals(forum_id)){
				return ResultUtils.returnError(12001, "forum_id不能为空");
			}
			pd.put("login_name", this.getMobileUser().getLoginName());
			mobileForumService.updateReadCnt(pd);
			PageData forumInfo = mobileForumService.getForumInfo(pd);
			if(forumInfo == null || forumInfo.size() ==  0){
				return ResultUtils.returnError(12002, "内容不存在或已被删除");
			}else{
				List<PageData> imgList = mobileForumService.getForumImgList(pd);//附属图片
				forumInfo.put("imgList", imgList);
				return ResultUtils.returnOk(forumInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：获取评论列表
	 * @return
	 * @author yanbs
	 * @Date 2019-10-08
	 */
	@RequestMapping("/getForumReplyList")
	public Map getForumReplyList(){
		try {
			PageData pd = this.getPageData();
			String forum_id = pd.getString("forum_id");
			String pageNum = pd.getString("pageNum");
			String pageSize = pd.getString("pageSize");
			if("".equals(forum_id)){
				return ResultUtils.returnError(12001, "forum_id不能为空");
			}
			if("".equals(pageNum)){
				return ResultUtils.returnError(12001, "pageNum不能为空");
			}
			if("".equals(pageSize)){
				return ResultUtils.returnError(12001, "pageSize不能为空");
			}
			pd.put("login_name", this.getMobileUser().getLoginName());
			startPage();
			List<PageData> replyList = mobileForumService.getForumReplyList(pd);//评论列表
			return ResultUtils.returnOk(replyList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：删除回复
	 * @return
	 * @author yanbs
	 * @Date 2019-10-08
	 */
	@RequestMapping("/deleteReplyInfo")
	public Map deleteReplyInfo(){
		try {
			PageData pd = this.getPageData();
			String reply_id = pd.getString("reply_id");
			if("".equals(reply_id)){
				return ResultUtils.returnError(12001, "reply_id不能为空");
			}
			mobileForumService.deleteReplyInfo(pd);
			return ResultUtils.returnOk();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：写评论
	 * @return
	 * @author yanbs
	 * @Date 2019-10-08
	 */
	@RequestMapping("/insertReplyInfo")
	public Map insertReplyInfo(){
		try {
			PageData pd = this.getPageData();
			String reply_content = pd.getString("reply_content");
			String forum_id = pd.getString("forum_id");
			if("".equals(reply_content)){
				return ResultUtils.returnError(12001, "reply_content不能为空");
			}
			if("".equals(forum_id)){
				return ResultUtils.returnError(12001, "forum_id不能为空");
			}
			pd.put("record_id", this.getMobileUser().getLoginName());
			mobileForumService.insertReplyInfo(pd);
			String registrationId = mobileForumService.getRegByFormId(pd);
			if(registrationId != null && !"".equals(registrationId)){
				JPushUtil jPushUtil = new JPushUtil();
				jPushUtil.pushMsg(registrationId, reply_content, "", "", "您的写的论坛有新的评论");
			}
			
			return ResultUtils.returnOk();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}

	/**
	 * 描述：获取收藏状态
	 * @return
	 * @author yanbs
	 * @Date 2019-10-08
	 */
	@RequestMapping("/getCollectStatus")
	public Map getCollectStatus(){
		try {
			PageData pd = this.getPageData();
			String forum_id = pd.getString("forum_id");
			if("".equals(forum_id)){
				return ResultUtils.returnError(12001, "forum_id不能为空");
			}
			pd.put("record_id", this.getMobileUser().getLoginName());
			Integer status = mobileForumService.getCollectStatus(pd);
			return ResultUtils.returnOk(status);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：收藏/取消收藏
	 * @return
	 * @author yanbs
	 * @Date 2019-10-08
	 */
	@RequestMapping("/collectForum")
	public Map collectForum(){
		try {
			PageData pd = this.getPageData();
			String forum_id = pd.getString("forum_id");
			if("".equals(forum_id)){
				return ResultUtils.returnError(12001, "forum_id不能为空");
			}
			String collectFlag = pd.getString("collectFlag");
			if("".equals(collectFlag)){
				return ResultUtils.returnError(12001, "collectFlag不能为空");
			}
			pd.put("record_id", this.getMobileUser().getLoginName());
			//获取收藏状态
			Integer status = mobileForumService.getCollectStatus(pd);
			if("0".equals(collectFlag) && status == 1){//取消收藏
				mobileForumService.delCollectForum(pd);
				return ResultUtils.returnOk();
				
			}else if("1".equals(collectFlag) && status == 0){//收藏
				mobileForumService.collectForum(pd);
				return ResultUtils.returnOk();
			}
			return ResultUtils.returnOk();
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：发布论坛内容
	 * @return
	 * @author yanbs
	 * @Date 2019-10-08
	 */
	@RequestMapping("/insertForumContent")
	public Map insertForumContent(){
		try {
			PageData pd = this.getPageData();
			String forum_type = pd.getString("forum_type");
			String title = pd.getString("title");
			String content = pd.getString("content");
			if("".equals(forum_type)){
				return ResultUtils.returnError(12001, "forum_type不能为空");
			}
			if("".equals(title)){
				return ResultUtils.returnError(12001, "title不能为空");
			}
			if("".equals(content)){
				return ResultUtils.returnError(12001, "content不能为空");
			}
			pd.put("record_id", this.getMobileUser().getLoginName());
			pd.put("pdid", "");
			mobileForumService.insertForumContent(pd);
			String forumImgs = pd.getString("forumImgs");
			if(!"".equals(forumImgs)){
				String[] forumImgArr = forumImgs.split(",");
				for (int i = 0; i < forumImgArr.length; i++) {
					PageData parampd = new PageData();
					parampd.put("forum_id", pd.get("pdid"));
					String img_url = forumImgArr[i];
					parampd.put("img_url", img_url);
					mobileForumService.insertForumImg(parampd);
				}
			}
			return ResultUtils.returnOk();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：忽略论坛发表人
	 * @return
	 * @author yanbs
	 * @Date 2019-10-28
	 */
	@RequestMapping("/addIgnoreForum")
	public Map addIgnoreForum(){
		try {
			PageData pd = this.getPageData();
			String ignore_id = pd.getString("record_id");//发布人ID
			if("".equals(ignore_id)){
				return ResultUtils.returnError(12001, "record_id不能为空");
			}
			String record_id = this.getMobileUser().getLoginName();
			if(ignore_id.equals(record_id)){
				return ResultUtils.returnError(12002, "无法屏蔽自己发布的信息");
			}
			pd.put("ignore_id", ignore_id);
			pd.put("record_id", record_id);
			mobileForumService.addIgnoreForum(pd);
			return ResultUtils.returnOk();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：举报文章/回复
	 * @return
	 * @author yanbs
	 * @Date 2019-10-28
	 */
	@RequestMapping("/addForumReport")
	public Map addForumReport(){
		try {
			PageData pd = this.getPageData();
			String forum_id = pd.getString("forum_id");
			if("".equals(forum_id)){
				return ResultUtils.returnError(12001, "forum_id不能为空");
			}
			String forum_flag = pd.getString("forum_flag");
			if("".equals(forum_flag)){
				return ResultUtils.returnError(12001, "forum_flag不能为空");
			}
			String report_type = pd.getString("report_type");
			if("".equals(report_type)){
				return ResultUtils.returnError(12001, "report_type不能为空");
			}
			pd.put("record_id", this.getMobileUser().getLoginName());
			mobileForumService.addForumReport(pd);
			return ResultUtils.returnOk();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
}
