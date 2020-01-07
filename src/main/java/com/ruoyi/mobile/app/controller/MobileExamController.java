package com.ruoyi.mobile.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.utils.PageData;
import com.ruoyi.common.utils.ResultUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.mobile.app.service.MobileExamService;
import com.ruoyi.project.questionmanage.domain.ExamQuestion;

@RestController
@RequestMapping("/mobile/app")
public class MobileExamController extends BaseController{

	@Autowired
	private MobileExamService mobileExamService;
	
	/**
	 * 描述：获取考试列表
	 * @return
	 * @author yanbs
	 * @Date 2019-09-07
	 */
	@RequestMapping("/getExamList")
	public Map getExamList(){
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
			List<PageData> examList = mobileExamService.getExamList(pd);
			return ResultUtils.returnOk(examList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常！");
		}
	} 
	
	/**
	 * 描述：获取考试详情
	 * @return
	 * @author yanbs
	 * @Date 2019-09-07
	 */
	@RequestMapping("/getExamInfo")
	public Map getExamInfo(){
		try {
			PageData pd = this.getPageData();
			String exam_id = pd.getString("exam_id");
			if("".equals(exam_id)){
				return ResultUtils.returnError(12001, "exam_id不能为空");
			}
			pd.put("record_id", this.getMobileUser().getLoginName());
			PageData examInfo = mobileExamService.getExamInfo(pd);
			return ResultUtils.returnOk(examInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常！");
		}
	}
	
	/**
	 * 描述：开始考试
	 * @return
	 * @author yanbs
	 * @Date 2019-09-07
	 */
	@RequestMapping("/getExamQuestion")
	public Map getExamQuestion(){
		try {
			PageData pd = this.getPageData();
			String exam_id = pd.getString("exam_id");
			if("".equals(exam_id)){
				return ResultUtils.returnError(12001, "exam_id不能为空");
			}
			pd.put("record_id", this.getMobileUser().getLoginName());
			Integer isdone = mobileExamService.getExamIsdone(pd);
			if(isdone > 0){
				return ResultUtils.returnError(12002, "您已参加过该场考试");
			}
			//清空我的答题
			mobileExamService.deleteMyExamAnswer(pd);
			//生成答题
			List<PageData> examquestionList = mobileExamService.getExamQuestion(pd);
			List<PageData> questionOptionList = mobileExamService.getQuestionOptionList(pd);
			List<PageData> res = new ArrayList<PageData>();
			for (PageData eqpd : examquestionList) {
				String eqpd_qst_id = eqpd.getString("qst_id");
				List<PageData> optionList = new ArrayList<PageData>();
				for (PageData qopd : questionOptionList) {
					PageData optionpd = new PageData();
					String qopd_qst_id = qopd.getString("qst_id");
					if(eqpd_qst_id.equals(qopd_qst_id)){
						optionpd.put("opt_content", qopd.getString("opt_content"));
						optionList.add(optionpd);
					}
				}
				eqpd.put("optionList", optionList);
				res.add(eqpd);
			}
			
			return ResultUtils.returnOk(res);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常！");
		}
		
	}
	
	/**
	 * 描述：添加我的答案
	 * @return
	 * @author yanbs
	 * @Date 2019-09-08
	 */
	@RequestMapping("/insertMyAnswer")
	public Map insertMyAnswer(){
		try {
			PageData pd = this.getPageData();
			String exam_id = pd.getString("exam_id");
			String qst_id = pd.getString("qst_id");//题目ID
			String answer = pd.getString("answer");//正确答案
			String my_answer = pd.getString("my_answer");//我的答案
			String my_score = pd.getString("my_score");//得分
			if("".equals(exam_id)){
				return ResultUtils.returnError(12001, "exam_id不能为空");
			}
			if("".equals(qst_id)){
				return ResultUtils.returnError(12001, "qst_id不能为空");
			}
			if("".equals(answer)){
				return ResultUtils.returnError(12001, "answer不能为空");
			}
			if("".equals(my_answer)){
				return ResultUtils.returnError(12001, "my_answer不能为空");
			}
			if("".equals(my_score)){
				return ResultUtils.returnError(12001, "my_score不能为空");
			}
			pd.put("record_id", this.getMobileUser().getLoginName());
			mobileExamService.insertMyAnswer(pd);
			return ResultUtils.returnOk();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常！");
		}
	}
	
	/**
	 * 描述：考试完毕提交
	 * @return
	 * @author yanbs
	 * @Date 2019-09-08
	 */
	@RequestMapping("/insertMyExam")
	public Map insertMyExam(){
		try {
			PageData pd = this.getPageData();
			String exam_id = pd.getString("exam_id");
			String my_score = pd.getString("my_score");//总得分
			String my_time = pd.getString("my_time");//我的用时
			if("".equals(exam_id)){
				return ResultUtils.returnError(12001, "exam_id不能为空");
			}
			if("".equals(my_score)){
				return ResultUtils.returnError(12001, "my_score不能为空");
			}
			if("".equals(my_time)){
				return ResultUtils.returnError(12001, "my_time不能为空");
			}
			pd.put("record_id", this.getMobileUser().getLoginName());
			mobileExamService.insertMyExam(pd);
			return ResultUtils.returnOk();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常！");
		}
	}
	
}
