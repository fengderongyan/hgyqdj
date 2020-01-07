package com.ruoyi.project.questionmanage.domain;

import java.util.Date;
import java.util.List;

import com.ruoyi.framework.web.domain.BaseEntity;

public class Question extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private Long qstId;
	private Integer type;
	private Integer qstType;
	private Integer status;
	private String qstContent;
	private String answer;
	private String recordId;
	private Date recordDate;
	
	public Long getQstId() {
		return qstId;
	}
	public void setQstId(Long qstId) {
		this.qstId = qstId;
	}
	private List<QuestionOption> questionOptions;
	
	public List<QuestionOption> getQuestionOptions() {
		return questionOptions;
	}
	public void setQuestionOptions(List<QuestionOption> questionOptions) {
		this.questionOptions = questionOptions;
	}
	
	public Integer getQstType() {
		return qstType;
	}
	public void setQstType(Integer qstType) {
		this.qstType = qstType;
	}
	public String getQstContent() {
		return qstContent;
	}
	public void setQstContent(String qstContent) {
		this.qstContent = qstContent;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
