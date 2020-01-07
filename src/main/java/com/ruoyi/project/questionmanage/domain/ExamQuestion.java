package com.ruoyi.project.questionmanage.domain;

import java.util.Date;
import java.util.List;

import com.ruoyi.framework.web.domain.BaseEntity;

public class ExamQuestion extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer examId;
	private Question question;
	private Integer score;
	private Integer showOrder;
	
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getExamId() {
		return examId;
	}
	public void setExamId(Integer examId) {
		this.examId = examId;
	}
	
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}
	
	
}
