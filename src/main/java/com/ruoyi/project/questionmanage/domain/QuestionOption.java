package com.ruoyi.project.questionmanage.domain;

import java.util.Date;

import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 描述：
 * @author yanbs
 * @Date 2019-09-11
 */
public class QuestionOption extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private Long questionoptionId;
	private Long qstId;
	private String optContent;
	private Integer showOrder;
	private String recordId;
	private Date recordDate;
	
	private Question question;
	
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Long getQuestionoptionId() {
		return questionoptionId;
	}
	public void setQuestionoptionId(Long id) {
		this.questionoptionId = id;
	}
	public Long getQstId() {
		return qstId;
	}
	public void setQstId(Long qstId) {
		this.qstId = qstId;
	}
	public String getOptContent() {
		return optContent;
	}
	public void setOptContent(String optContent) {
		this.optContent = optContent;
	}
	public Integer getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
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
	
	
	
}
