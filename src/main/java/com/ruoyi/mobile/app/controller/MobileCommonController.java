package com.ruoyi.mobile.app.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.utils.PageData;
import com.ruoyi.common.utils.ResultUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.mobile.app.service.MobileCommonService;

@RestController
@RequestMapping("/mobile/app")
public class MobileCommonController extends BaseController{

	@Autowired
	private MobileCommonService mobileCommonService;
	
	/**
	 * 描述：获取组织列表
	 * @return
	 * @author yanbs
	 * @Date 2019-09-04
	 */
	@RequestMapping("/getDeptList")
	public Map getDeptList(){
		try {
			List<PageData> deptList = mobileCommonService.getDeptList();
			return ResultUtils.returnOk(deptList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
		
	}
	
	/**
	 * 描述：完善基础信息
	 * @return
	 * @author yanbs
	 * @Date 2019-09-04
	 */
	@RequestMapping("/finishBaseUserInfo")
	public Map finishBaseUserInfo(){
		try {
			PageData pd = this.getPageData();
			String userName = pd.getString("userName");//昵称
			String nation = pd.getString("nation");//民族
			String sex = pd.getString("sex");//性别
			String birthday = pd.getString("birthday");//出生日期
			String idcardNum = pd.getString("idcardNum");//身份证号
			String memberType = pd.getString("memberType");//党员类型
			String deptId = pd.getString("deptId");//所属党组织
			String phonenumber = pd.getString("phonenumber");//联系电话
			String homeAddress = pd.getString("homeAddress");//家庭住址
			if("".equals(userName)){
				return ResultUtils.returnError(12001, "userName不能为空");
			}
			if("".equals(nation)){
				return ResultUtils.returnError(12001, "nation不能为空");
			}
			if("".equals(sex)){
				return ResultUtils.returnError(12001, "sex不能为空");
			}
			if("".equals(birthday)){
				return ResultUtils.returnError(12001, "birthday不能为空");
			}
			if("".equals(idcardNum)){
				return ResultUtils.returnError(12001, "idcardNum不能为空");
			}
			if("".equals(memberType)){
				return ResultUtils.returnError(12001, "memberType不能为空");
			}
			if("".equals(deptId)){
				return ResultUtils.returnError(12001, "deptId不能为空");
			}
			if("".equals(phonenumber)){
				return ResultUtils.returnError(12001, "phonenumber不能为空");
			}
			if("".equals(homeAddress)){
				return ResultUtils.returnError(12001, "homeAddress不能为空");
			}
			
			String loginName = this.getMobileUser().getLoginName();
			pd.put("loginName", loginName);
			//判断号码是否存在
			Integer userCnt = mobileCommonService.getUserCntByMobile(pd);
			if(userCnt >= 1){
				return ResultUtils.returnError(12002, "联系人号码已存在");
			}
			mobileCommonService.finishBaseUserInfo(pd);
			return ResultUtils.returnOk();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
		

	}
	
	/**
	 * 描述：获取资讯头部类别
	 * @return
	 * @author yanbs
	 * @Date 2019-09-05
	 */
	@RequestMapping("/getInfoClassAndItemList")
	public Map getInfoClassAndItemList(){
		try {
			List<PageData> infoClassList = mobileCommonService.getInfoClassList();
			List<PageData> resultList = new ArrayList<PageData>();
			for (PageData pageData : infoClassList) {
				PageData resultpd = new PageData();
				resultpd.putAll(pageData);
				String is_has_item = pageData.getString("is_has_item");
				if("1".equals(is_has_item)){//有子类
					List<PageData> infoItemList = mobileCommonService.getInfoItemList(pageData);
					resultpd.put("infoItemList", infoItemList);
				}
				resultList.add(resultpd);
			}
			return ResultUtils.returnOk(resultList);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：获取banner列表
	 * @return
	 * @author yanbs
	 * @Date 2019-09-05
	 */
	@RequestMapping("/getBannerList")
	public Map getBannerList(){
		try {
			List<PageData> bannerList = mobileCommonService.getBannerList();
			return ResultUtils.returnOk(bannerList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：获取公告列表
	 * @return
	 * @author yanbs
	 * @Date 2019-09-05
	 */
	@RequestMapping("/getNoticeList")
	public Map getNoticeList(){
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
			startPage();
			List<PageData> noticeList = mobileCommonService.getNoticeList();
			return ResultUtils.returnOk(noticeList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：获取推荐资讯列表
	 * @return
	 * @author yanbs
	 * @Date 2019-09-05
	 */
	@RequestMapping("/getRecInformationList")
	public Map getRecInformationList(){
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
			startPage();
			List<PageData> informationList = mobileCommonService.getRecInformationList();
			return ResultUtils.returnOk(informationList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：获取banner详情
	 * @return
	 * @author yanbs
	 * @Date 2019-09-05
	 */
	@RequestMapping("/getBannerInfo")
	public Map getBannerInfo(){
		try {
			PageData pd = this.getPageData();
			String banner_id = pd.getString("banner_id");
			if("".equals(banner_id)){
				return ResultUtils.returnError(12001, "banner_id不能为空");
			}
			PageData bannerInfo = mobileCommonService.getBannerInfo(pd);
			return ResultUtils.returnOk(bannerInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：获取公告详情
	 * @return
	 * @author yanbs
	 * @Date 2019-09-05
	 */
	@RequestMapping("/getNoticeInfo")
	public Map getNoticeInfo(){
		try {
			PageData pd = this.getPageData();
			String notice_id = pd.getString("notice_id");
			if("".equals(notice_id)){
				return ResultUtils.returnError(12001, "notice_id不能为空");
			}
			PageData noticeInfo = mobileCommonService.getNoticeInfo(pd);
			return ResultUtils.returnOk(noticeInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：获取推荐资讯详情
	 * @return
	 * @author yanbs
	 * @Date 2019-09-05
	 */
	@RequestMapping("/getInformationDetail")
	public Map getInformationDetail(){
		try {
			PageData pd = this.getPageData();
			String info_id = pd.getString("info_id");
			if("".equals(info_id)){
				return ResultUtils.returnError(12001, "info_id不能为空");
			}
			PageData informationDetail = mobileCommonService.getInformationDetail(pd);
			return ResultUtils.returnOk(informationDetail);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	} 
	
	/**
	 * 描述：根据资讯类别获取资讯列表
	 * @return
	 * @author yanbs
	 * @Date 2019-09-05
	 */
	@RequestMapping("/getInfoListByClass")
	public Map getInfoListByClass(){
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
			String class_id = pd.getString("class_id");
			if("".equals(class_id)){
				return ResultUtils.returnError(12001, "class_id不能为空");
			}
			
			String item_id = pd.getString("item_id");
			if("1".equals(item_id) || "5".equals(item_id) || "10".equals(item_id) || "17".equals(item_id)){//各类别子类为全部时
				item_id = "";
			}
			pd.put("item_id", item_id);
			startPage();
			List<PageData> infoList = mobileCommonService.getInfoListByClass(pd);
			return ResultUtils.returnOk(infoList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
		
	}
	
	/**
	 * 描述：获取组织列表-介绍组织
	 * @return
	 * @author yanbs
	 * @Date 2019-09-05
	 */
	@RequestMapping("/introduceDepts")
	public Map introduceDepts(){
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
			startPage();
			List<PageData> deptList = mobileCommonService.introduceDepts();
			return ResultUtils.returnOk(deptList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：组织介绍详情
	 * @return
	 * @author yanbs
	 * @Date 2019-09-06
	 */
	@RequestMapping("/introduceDeptDetail")
	public Map introduceDeptDetail(){
		try {
			PageData pd = this.getPageData();
			String dept_id = pd.getString("dept_id");
			if("".equals(dept_id)){
				return ResultUtils.returnError(12001, "dept_id不能为空");
			}
			PageData deptDetail = mobileCommonService.introduceDeptDetail(pd);
			List<PageData>  memberList = mobileCommonService.getUserListByDept(pd);//获取组织成员
			List<PageData>  activityList = mobileCommonService.getActivityList(pd);//获取活动风采
			deptDetail.put("memberList", memberList);
			deptDetail.put("activityList", activityList);
			return ResultUtils.returnOk(deptDetail);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：查看全部组织成员列表
	 * @return
	 * @author yanbs
	 * @Date 2019-09-06
	 */
	@RequestMapping("/showAllDeptUser")
	public Map showAllDeptUser(){
		try {
			PageData pd = this.getPageData();
			String dept_id = pd.getString("dept_id");
			if("".equals(dept_id)){
				return ResultUtils.returnError(12001, "dept_id不能为空");
			}
			String pageNum = pd.getString("pageNum");
			String pageSize = pd.getString("pageSize");
			if("".equals(pageNum)){
				return ResultUtils.returnError(12001, "pageNum不能为空");
			}
			if("".equals(pageSize)){
				return ResultUtils.returnError(12001, "pageSize不能为空");
			}
			startPage();
			List<PageData>  memberList = mobileCommonService.getAllUserListByDept(pd);//获取组织成员
			return ResultUtils.returnOk(memberList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：查看活动风采列表
	 * @return
	 * @author yanbs
	 * @Date 2019-09-06
	 */
	@RequestMapping("/showAllDeptActivity")
	public Map showAllDeptActivity(){
		try {
			PageData pd = this.getPageData();
			String dept_id = pd.getString("dept_id");
			if("".equals(dept_id)){
				return ResultUtils.returnError(12001, "dept_id不能为空");
			}
			String pageNum = pd.getString("pageNum");
			String pageSize = pd.getString("pageSize");
			if("".equals(pageNum)){
				return ResultUtils.returnError(12001, "pageNum不能为空");
			}
			if("".equals(pageSize)){
				return ResultUtils.returnError(12001, "pageSize不能为空");
			}
			startPage();
			List<PageData>  activityList = mobileCommonService.getAllActivityList(pd);//获取活动风采
			return ResultUtils.returnOk(activityList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：获取活动风采明细
	 * @return
	 * @author yanbs
	 * @Date 2019-09-06
	 */
	@RequestMapping("/getActivityDetail")
	public Map getActivityDetail(){
		try {
			PageData pd = this.getPageData();
			String activity_id = pd.getString("activity_id");
			if("".equals(activity_id)){
				return ResultUtils.returnError(12001, "activity_id不能为空");
			}
			PageData activityDetail = mobileCommonService.getActivityDetail(pd);
			return ResultUtils.returnOk(activityDetail);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：获取组织列表-介绍组织
	 * @return
	 * @author yanbs
	 * @Date 2019-09-05
	 */
	@RequestMapping("/introduceBusiness")
	public Map introduceBusiness(){
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
			startPage();
			List<PageData> deptList = mobileCommonService.introduceBusiness();
			return ResultUtils.returnOk(deptList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：获取积分
	 * @return
	 * @author yanbs
	 * @Date 2019-09-10
	 */
	@RequestMapping("/getPoint")
	public Map getPoint(){
		try {
			PageData pd = this.getPageData();
			String getType = pd.getString("getType");//获取途径
			if("".equals(getType)){
				return ResultUtils.returnError(12001, "getType不能为空");
			}
			if(!"1".equals(getType) && !"2".equals(getType) && !"3".equals(getType) && !"4".equals(getType)){
				return ResultUtils.returnError(12001, "getType参数错误，请传（1、2、3、4）");
			}
			pd.put("login_name", this.getMobileUser().getLoginName());
			Integer has_point = mobileCommonService.hasPointInfo(pd);//查询是否有积分信息
			Integer dayPoint = mobileCommonService.pointIsFull(pd);//查询当日积
			dayPoint = dayPoint == null ? 0 : dayPoint;
			pd.put("has_point", has_point);
			if("1".equals(getType)){
				if(dayPoint < 5){//每日登录，积分上限5分
					pd.put("point", "5");
					mobileCommonService.insertPointInfo(pd);
				}
			}
			if("2".equals(getType)){//观看资讯
				if(dayPoint < 5){//浏览资讯每日积分上限5分
					pd.put("point", "1");
					mobileCommonService.insertPointInfo(pd);
				}
			}else if("3".equals(getType)){//考试
				
				if(dayPoint < 10){//考试每日积分上限10分
					pd.put("point", "5");
					mobileCommonService.insertPointInfo(pd);
				}
				
			}else if("4".equals(getType)){//领取心愿
				if(dayPoint < 10){
					pd.put("point", "10");
					mobileCommonService.insertPointInfo(pd);
				}
			}
			return ResultUtils.returnOk();
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：更新APP
	 * @return
	 * @author yanbs
	 * @Date 2019-09-23
	 */
	@RequestMapping("/updateApp")
	public Map updateApp(){
		try {
			PageData pd = this.getPageData();
			String system_flag = pd.getString("system_flag");
			if("".equals(system_flag)){
				return ResultUtils.returnError(12001, "system_flag不能为空");
			}
			PageData res = mobileCommonService.updateApp(pd);
			return ResultUtils.returnOk(res);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：领心愿列表
	 * @return
	 * @author yanbs
	 * @Date 2019-09-26
	 */
	@RequestMapping("/getWishList")
	public Map getWishList(){
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
			startPage();
			List<PageData> wishList = mobileCommonService.getWishList(pd);
			return ResultUtils.returnOk(wishList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：领心愿详情
	 * @return
	 * @author yanbs
	 * @Date 2019-09-26
	 */
	@RequestMapping("/getWishInfo")
	public Map getWishInfo(){
		try {
			PageData pd = this.getPageData();
			String wish_id = pd.getString("wish_id");
			if("".equals(wish_id)){
				return ResultUtils.returnError(12001, "wish_id不能为空");
			}
			PageData wishInfo = mobileCommonService.getWishInfo(pd);
			return ResultUtils.returnOk(wishInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：获取全部已认领列表
	 * @return
	 * @author yanbs
	 * @Date 2019-09-26
	 */
	@RequestMapping("/getClaim2List")
	public Map getClaim2List(){
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
			startPage();
			List<PageData> myClaimList = mobileCommonService.getMyClaimList(pd);
			return ResultUtils.returnOk(myClaimList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
		
	}
	
	
//	/**
//	 * 描述：获取已认领列表
//	 * @return
//	 * @author yanbs
//	 * @Date 2019-09-26
//	 */
//	@RequestMapping("/getMyClaimList")
//	public Map getMyClaimList(){
//		try {
//			PageData pd = this.getPageData();
//			String pageNum = pd.getString("pageNum");
//			String pageSize = pd.getString("pageSize");
//			if("".equals(pageNum)){
//				return ResultUtils.returnError(12001, "pageNum不能为空");
//			}
//			if("".equals(pageSize)){
//				return ResultUtils.returnError(12001, "pageSize不能为空");
//			}
//			pd.put("claim_id", this.getMobileUser().getLoginName());
//			startPage();
//			List<PageData> myClaimList = mobileCommonService.getMyClaimList(pd);
//			return ResultUtils.returnOk(myClaimList);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResultUtils.returnError(-1, "后台运行异常");
//		}
//		
//	}
	
	/**
	 * 描述：认领心愿
	 * @return
	 * @author yanbs
	 * @Date 2019-09-26
	 */
	@RequestMapping("/claimWish")
	public Map claimWish(){
		try {
			PageData pd = this.getPageData();
			String wish_id = pd.getString("wish_id");
			if("".equals(wish_id)){
				return ResultUtils.returnError(12001, "wish_id不能为空");
			}
			Integer status = mobileCommonService.getWishStatus(pd);
			if(status == null || status != 1){
				return ResultUtils.returnError(12002, "该心愿不存在或已被领取");
			}
			
			pd.put("claim_id", this.getMobileUser().getLoginName());
			mobileCommonService.claimWish(pd);
			return ResultUtils.returnOk();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
}
