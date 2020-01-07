package com.ruoyi.project.questionmanage.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.PageData;
import com.ruoyi.framework.web.service.DictService;
import com.ruoyi.project.questionmanage.domain.Question;
import com.ruoyi.project.questionmanage.domain.QuestionOption;
import com.ruoyi.project.questionmanage.mapper.QuestionManageMapper;

@Service
public class QuestionManageService{

	@Autowired
	public QuestionManageMapper questionManageMapper;
	
	
	
	/**
	 * 批量导入试题
	 * @param file 		文件
	 * @param companyId	公司ID
	 * @return
	 * @throws IOException 
	 * @throws Exception
	 */
	@Transactional(rollbackFor = BusinessException.class)
	public String updateImportQuestionExcel(MultipartFile file, String recordId) throws IOException{
		// 	     datalist拼装List<String[]> deadliest,
		StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        int successNum = 0;//成功条数
        int failureNum = 0;//失败条数
        
		HSSFWorkbook wookbook = new HSSFWorkbook(file.getInputStream());
		HSSFSheet sheet = wookbook.getSheetAt(0);
		// 		指的行数，一共有多少行+
		int rows = sheet.getLastRowNum();
 
		for (int i = 1; i <= rows; i++) {
			// 读取左上端单元格
			HSSFRow row = sheet.getRow(i);
			// 行不为空
			if (row != null) {
				// 获取到Excel文件中的所有的列
				int maxcell = row.getLastCellNum();
				// **读取cell**
				String content = getCellValue(row.getCell((short) 0));// 试题内容
				String type = getCellValue(row.getCell((short) 1));// 题库类型ID
				type = trimZero(type);
				String qstType = getCellValue(row.getCell((short) 2));// 题型
				String answer = getCellValue(row.getCell((short) 3));// 正确答案
				String optionA = getCellValue(row.getCell((short) 4));  // A
				String optionB = getCellValue(row.getCell((short) 5));  // B
				String optionC = getCellValue(row.getCell((short) 6));  // C
				String optionD = getCellValue(row.getCell((short) 7)); // D
				String optionE = getCellValue(row.getCell((short) 8)); // E
				String optionF = getCellValue(row.getCell((short) 9)); // F
				String optionG = getCellValue(row.getCell((short) 10)); // G
				String optionH = getCellValue(row.getCell((short) 11)); // H
				String optionI = getCellValue(row.getCell((short) 12)); // I
				if(StringUtils.isEmpty(content)&&
						StringUtils.isEmpty(type)&&
						StringUtils.isEmpty(qstType)&&
						StringUtils.isEmpty(answer)){
					break;
				}
				
				
				// 试题内容，类别，试题类型，正确答案，选项A，选项B
				if (StringUtils.isEmpty(content) || StringUtils.isEmpty(type) || StringUtils.isEmpty(answer) || StringUtils.isEmpty(qstType)) {
					failureNum++;
					failureMsg.append("第" + i + "行，试题内容为<" + content + ">的那条数据数据不能为空（试题内容，题库类型ID，试题类型，正确答案）");
					break;
				}
				// 专业ID必须是大于0的正整数
				if (!StringUtils.isNumeric(type)||Long.parseLong(type)<=0) {
					failureNum++;
					failureMsg.append("第" + i + "行，试题内容为<" + content + ">的那条数据题库类型ID必须是大于0的正整数");break;
				}
				//该 专业ID必须是该公司所有
				Integer isHasType = questionManageMapper.isHasType(type);
				if(isHasType == 0){
					failureNum++;
					failureMsg.append("第" + i + "行，试题内容为<" + content + ">的那条数据题库类型ID不存在");break;
				}
				
				switch (qstType) {
					case "单选":
						qstType = "1";
						break;
					case "多选":
						qstType = "2";
						break;
					case "判断":
						qstType = "3";
						break;
					default:
						failureNum++;
						failureMsg.append("第" + i + "行，试题内容为<" + content + ">的那条数据试题类型不正确（试题类型只能输入单选,多选,判断）");break;
				}
 
				int qstTypeInt = Integer.parseInt(qstType);
				// 如果为判断题最多2个选项
				if (qstTypeInt == 3) {
					if (StringUtils.isEmpty(optionA) || StringUtils.isEmpty(optionB)) {
						failureNum++;
						failureMsg.append("第" + i + "行，试题内容为<" + content + ">的那条数据为判断题，选项A或选项B不能为空");break;
					}
					if (StringUtils.isNotEmpty(optionD) || StringUtils.isNotEmpty(optionE)
							|| StringUtils.isNotEmpty(optionF) || StringUtils.isNotEmpty(optionG)
							|| StringUtils.isNotEmpty(optionH) || StringUtils.isNotEmpty(optionI)) {
						failureNum++;
						failureMsg.append("第" + i + "行，试题内容为<" + content + ">的那条数据为判断题，选项D，E，F，G，H，I必须为空");break;
					}
				}
				// 如果不是判断题，选项必须大于等于4个小于等于7个选项
				if (qstTypeInt != 3) {
					if (StringUtils.isEmpty(optionA) || StringUtils.isEmpty(optionB)) {
						failureNum++;
						failureMsg.append("第" + i + "行，试题内容为<" + content + ">的那条数据为选择题，选项A或选项B不能为空");break;
					}
				}
				// 如果为多选题正确答案必须在两个以上
				if (qstTypeInt == 2 && answer.trim().length() < 2) {
					failureNum++;
					failureMsg.append("第" + i + "行，试题内容为<" + content + ">的那条数据的为多选题，正确答案必须在两个以上（例：AB）");break;
				}
				// 如果为单选题或者判断题答案只能有一个
				if (qstTypeInt == 1 || qstTypeInt == 3) {
					if (answer.trim().length() > 1) {
						failureNum++;
						failureMsg.append("第" + i + "行，试题内容为<" + content + ">的那条数据的正确答案只能有一个（例：A）");break;
					}
				}
				// 选项不能超过7个字符
				if (answer.trim().length() > 9) {
					failureNum++;
					failureMsg.append("第" + i + "行，试题内容为<" + content + ">的那条数据正确答案不能超过7个字符（例AB）");break;
				}
				// 验证正确答案不能输入其他字符
				char[] asr = answer.toString().trim().toCharArray();
				String asrStr = "";
				for (int y = 0; y < asr.length; y++) {
					asrStr += asr[y] + ",";
					if ("ABCDEFGHI".indexOf(String.valueOf(asr[y])) == -1) {
						failureNum++;
						failureMsg.append("第" + i + "行，试题内容为<" + content + ">的那条数据正确答案输入字符格式不正确（例AB）");break;
					}
				}
				answer = asrStr.substring(0, asrStr.length() - 1);
				Question question = new Question();
				question.setStatus(1);
				//题库类型
				question.setType(Integer.parseInt(type));
				// 试题类型
				question.setQstType(qstTypeInt);
				
				//调整答案顺序
				if(qstTypeInt == 2){//多选题
					String[] chars = answer.split(",");
					Arrays.sort(chars);
					if(chars != null && chars.length>1){
						StringBuilder isAsrs=new StringBuilder();
						for (String s:chars){
							isAsrs.append(s);
							isAsrs.append(",");
						}
						answer= isAsrs.substring(0,isAsrs.length()-1);
					}
				}
				question.setQstContent(content);
				question.setAnswer(answer);
				question.setRecordId(recordId);
				question.setRecordDate(new Date());
				questionManageMapper.insertQuestion(question);
				List<String> str = new ArrayList<String>();
				// 把选项的值放入list中
				str.add(optionA);
				str.add(optionB);
				str.add(optionC);
				str.add(optionD);
				str.add(optionE);
				str.add(optionF);
				str.add(optionG);
				str.add(optionH);
				str.add(optionI);
				List<QuestionOption> optionList = new ArrayList<>();
				int orderNum = 1;
				for (int k = 0; k < str.size(); k++) {
					// 如果选项为空字符串则不添加该选项
					if (!"".equals(str.get(k).trim())) {
						QuestionOption option = new QuestionOption();
						option.setOptContent(str.get(k).trim());
						option.setQstId(question.getQstId());
						option.setShowOrder(orderNum++);
						option.setRecordId(recordId);
						option.setRecordDate(new Date());
						optionList.add(option);
					}
				}
				PageData pd = new PageData();
				pd.put("optionList", optionList);
				questionManageMapper.insertQuestionOpt(pd);
				successNum++;
			}
		}
		wookbook.close();
		if (failureNum > 0) {
            //failureMsg.insert(0, "成功导入" + successNum + "条，失败导入 " + failureNum + "错误如下：");
			failureMsg.insert(0, "导入失败，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条");
        }
		return successMsg.toString();
	}
 
	/**
	 * 获得Hsscell内容
	 *
	 * @param cell
	 * @return
	 */
	public String getCellValue(HSSFCell cell) {
		String value = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_FORMULA:
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				value = cell.getNumericCellValue() + "";
				break;
			case HSSFCell.CELL_TYPE_STRING:
				value = cell.getStringCellValue().trim();
				break;
			default:
				value = "";
				break;
			}
		}
		return value.trim().replaceAll("^[　*| *| *|//s*]*", "").replaceAll("[　*| *| *|//s*]*$", "");
	}
 
	String trimZero(String str) {
		if (StringUtils.isNotEmpty(str)) {
			return str.replace(".0", "");
		}
		return str;
	}
	
	public List<PageData> getQuestionList(PageData pd) {
		
		return questionManageMapper.getQuestionList(pd);
	}
	
	@Transactional
	public void addSave(PageData pd) {
		pd.put("pdid", "");
		questionManageMapper.addQuestion(pd);//新增后pdid回显
		List<String> str = new ArrayList<String>();
		str.add(pd.getString("optionA"));
		str.add(pd.getString("optionB"));
		str.add(pd.getString("optionC"));
		str.add(pd.getString("optionD"));
		str.add(pd.getString("optionE"));
		str.add(pd.getString("optionF"));
		str.add(pd.getString("optionG"));
		str.add(pd.getString("optionH"));
		str.add(pd.getString("optionI"));
		List<PageData> optList = new ArrayList<PageData>();
		int orderNum = 1;
		for (int i = 0; i < str.size(); i++) {
			if(!"".equals(str.get(i))){
				PageData optpd = new PageData();
				optpd.put("qst_id", pd.getString("pdid"));
				optpd.put("opt_content", str.get(i));
				optpd.put("show_order", orderNum++);
				optpd.put("record_id", pd.getString("record_id"));
				optList.add(optpd);
			}
		}
		pd.put("optList", optList);
		questionManageMapper.addQuestionOption(pd);
	}

	public PageData getQuestionInfo(String id) {
		return questionManageMapper.getQuestionInfo(id);
	}

	public void editSave(PageData pd) {
		questionManageMapper.editQuestion(pd);
		List<String> str = new ArrayList<String>();
		str.add(pd.getString("optionA"));
		str.add(pd.getString("optionB"));
		str.add(pd.getString("optionC"));
		str.add(pd.getString("optionD"));
		str.add(pd.getString("optionE"));
		str.add(pd.getString("optionF"));
		str.add(pd.getString("optionG"));
		str.add(pd.getString("optionH"));
		str.add(pd.getString("optionI"));
		List<PageData> optList = new ArrayList<PageData>();
		int orderNum = 1;
		for (int i = 0; i < str.size(); i++) {
			if(!"".equals(str.get(i))){
				PageData optpd = new PageData();
				optpd.put("qst_id", pd.getString("id"));
				optpd.put("opt_content", str.get(i));
				optpd.put("show_order", orderNum++);
				optpd.put("record_id", pd.getString("record_id"));
				optList.add(optpd);
			}
		}
		pd.put("optList", optList);
		questionManageMapper.deleteQuestionOpt(pd);
		questionManageMapper.addQuestionOption(pd);
	}

	public void removeByIds(String[] idArray) {
		questionManageMapper.removeByIds(idArray);
		
	}
}
