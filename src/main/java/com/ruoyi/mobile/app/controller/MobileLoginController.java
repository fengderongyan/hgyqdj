package com.ruoyi.mobile.app.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.JwtUtil;
import com.ruoyi.common.utils.PageData;
import com.ruoyi.common.utils.ResultUtils;
import com.ruoyi.framework.shiro.service.PasswordService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.mobile.app.service.MobileLoginService;
import com.ruoyi.project.service.RedisService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.UserServiceImpl;

@Controller
@RequestMapping(value="/mobile/app")
public class MobileLoginController extends BaseController{

	@Autowired
	private MobileLoginService mobileLoginService;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private PasswordService passwordService;
	
	@Autowired
	private RedisService redisService;
	
	/**
	 * 描述：注册
	 * @param request
	 * @return
	 * @author yanbs
	 * @Date 2019-10-15
	 */
	@RequestMapping("/signUp")
	@ResponseBody
	public Map signUp(HttpServletRequest request){
		try {
			PageData pd = this.getPageData();
			String dept_id = pd.getString("dept_id");
			String user_name = pd.getString("user_name");
			String phonenumber = pd.getString("phonenumber");
			String sex = pd.getString("sex");
			String password = pd.getString("password");
			if("".equals(dept_id)){
				return ResultUtils.returnError(12001, "dept_id不能为空");
			}
			if("".equals(user_name)){
				return ResultUtils.returnError(12001, "user_name不能为空");
			}
			if("".equals(phonenumber)){
				return ResultUtils.returnError(12001, "phonenumber不能为空");
			}
			if("".equals(sex)){
				return ResultUtils.returnError(12001, "sex不能为空");
			}
			if("".equals(password)){
				return ResultUtils.returnError(12001, "deptpassword_id不能为空");
			}
			Integer isExist = mobileLoginService.phoneIsExist(pd);
			if(isExist == 1){
				return ResultUtils.returnError(12002, "手机号码已存在");
			}
			SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
	        String salt = secureRandom.nextBytes(3).toHex();
	        pd.put("salt", salt);
	        pd.put("pdid", "");
			mobileLoginService.addSignUpInfo(pd);
			
			/************begin********************/
			pd.put("status", "1");
			mobileLoginService.updateSignStatus(pd);
			pd.put("pdid", "");
			mobileLoginService.insertSysUser(pd);
			String pwd = new Md5Hash(pd.getString("pdid") + password + salt).toHex().toString();
			pd.put("password", pwd);
			mobileLoginService.finishInsertUser(pd);
			/************end*******************/
			return ResultUtils.returnOk();	
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	
	/**
	 * 描述：手机用户登录
	 * @return
	 * @author yanbs
	 * @Date 2019-09-04
	 */
	@RequestMapping("/checkLogin")
	@ResponseBody
	public Map checkLogin(HttpServletRequest request){
		try {
			System.out.println("传参：：" + request.getParameter("loginName"));
			PageData pd = this.getPageData();
			System.out.println("pd::" + pd);
			String loginName = pd.getString("loginName");//登陆名
			String password = pd.getString("password");//密码
			String registrationId = pd.getString("registrationId");//设备ID
			if(loginName.equals("")){
				return ResultUtils.returnError(12001, "无法获取loginName！");
			}
			if(password.equals("")){
				return ResultUtils.returnError(12001, "无法获取password！");
			}
			if(registrationId.equals("")){
				return ResultUtils.returnError(12001, "无法获取registrationId！");
			}
			
			User user = userService.selectUserByLoginName(loginName);
			if(user == null){
				user = userService.selectUserByPhoneNumber(loginName);
				
			}
			if(user == null){
				return ResultUtils.returnError(11002, "用户名或密码错误！");
			}
			
			if(!passwordService.matches(user, password)){
				return ResultUtils.returnError(11002, "用户名或密码错误！");
			}
			//生成token
			String token = JwtUtil.createJWT(60*60*24*30*1000, user.getLoginName(), password, Constants.JWT_TOKEN_KEY);
			
			//更新token
			//保存or更新设备ID(保证registrationId唯一)
			int checkResult = this.updateTokenAndResId(user.getLoginName(), registrationId, token);
			if(checkResult == 0){
				return ResultUtils.returnError(-1, "后台运行错误！");
			}
			Session session  = SecurityUtils.getSubject().getSession();
			session.setAttribute("user", user);
			
			String tokenUserKey = Constants.TOKEN_KEY_FRIST + user.getLoginName() + "_info";
			
			//User userMap = (User)redisService.getObject(tokenUserKey);
			Map returnMap = new HashMap();
			if(user.getToken() == null || user.getToken() == ""){//token为空，说明是第一次登录
				returnMap.put("isFirstLogin", "1");
			}else{
				returnMap.put("isFirstLogin", "0");
			}
			returnMap.put("token", token);
			returnMap.put("avatar",user.getAvatar());//头像
			returnMap.put("userName",user.getUserName());//昵称
			returnMap.put("sex",user.getSex());//性别
			returnMap.put("deptId", user.getDept().getDeptId());//组织ID
			returnMap.put("deptName",user.getDept().getDeptName());//组织名称
			//returnMap.put("deptName", user.getDept().getDeptName());//组织名称
			returnMap.put("phonenumber",user.getPhonenumber());//联系电话
			
			returnMap.put("nation",user.getNation());//民族
			returnMap.put("birthday", user.getBirthday() == null ? "" : DateUtils.dateTime(user.getBirthday()));//出生日期
			returnMap.put("idcardNum",user.getIdcardNum());//身份证号
			returnMap.put("memberType",user.getMemberType());//党员类型
			returnMap.put("homeAddress",user.getHomeAddress());//联系电话
			returnMap.put("dealFlag", user.getDealFlag());//是否在审核
			
			user.setToken(token);
			this.setUserRedis(user, tokenUserKey);//设置用户redis
			return  ResultUtils.returnOk(returnMap);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
		
	}
	
	private void setUserRedis(User user, String tokenUserKey) {
		Map userMap = new HashMap();
		userMap.put("userName", user.getUserName());
		userMap.put("userName", user.getUserName());
		boolean isExistes =  redisService.hasKey(tokenUserKey);
		if(isExistes){//若存在，则删除
			redisService.delete(tokenUserKey);
		}
		redisService.addObjectWithTime(tokenUserKey, user, 60*60*24*30L);
	}

	/**
	 * 描述：更新设备ID及token
	 * @param loginName
	 * @param registrationId
	 * @param token
	 * @return
	 * @author yanbs
	 * @Date 2019-09-04
	 */
	public int updateTokenAndResId(String loginName, String registrationId, String token){
		PageData pd = new PageData();
		pd.put("loginName", loginName);
		pd.put("registrationId", registrationId);
		pd.put("token", token);
		try {
			mobileLoginService.updateTokenAndResId(pd);
			
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	@RequestMapping("/forgetPwd")
	@ResponseBody
	public Map forgetPwd(){
		try {
			PageData pd = this.getPageData();
			String phonenumber = pd.getString("phonenumber");
			if("".equals(phonenumber)){
				return ResultUtils.returnError(12001, "phonenumber不能为空！");
			}
			
			return ResultUtils.returnOk();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
	/**
	 * 描述：获取隐私协议
	 * @return
	 * @author yanbs
	 * @Date 2019-10-21
	 */
	@RequestMapping("/privacyProtocol")
	@ResponseBody
	public Map getPrivacyProtocol(){
		try {
			PageData pd =  mobileLoginService.getPrivacyProtocol();
			return ResultUtils.returnOk(pd);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
	}
	
}
