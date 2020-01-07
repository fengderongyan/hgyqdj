package com.ruoyi.project.questionmanage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.common.utils.PageData;
import com.ruoyi.project.questionmanage.mapper.ExamManageMapper;

@Service
public class ExamManageService{

	@Autowired
	public ExamManageMapper examManageMapper;
	
	@Transactional
	public void addSave(PageData pd) {
		pd.put("pdid", "");
		examManageMapper.addSave(pd);
		String  qstTypes = pd.getString("qstTypes");
		String[] qstTypeArr = qstTypes.split(",");
		pd.put("qstTypeArr", qstTypeArr);
		List<PageData> questionList = new  ArrayList<PageData>();
		//获取单选集合
		List<PageData> danxuanList = examManageMapper.getDanxuanList(pd);
		Integer danxuanCnt = pd.getString("danxuan_cnt") == "" ? 0 : Integer.parseInt(pd.getString("danxuan_cnt"));
		List<PageData> danxuanRandomList = this.getRandomList(danxuanList, danxuanCnt);
		questionList.addAll(danxuanRandomList);
		
		List<PageData> duoxuanList = examManageMapper.getDuoxuanList(pd);
		Integer duoxuanCnt = pd.getString("duoxuan_cnt") == "" ? 0 : Integer.parseInt(pd.getString("duoxuan_cnt"));
		List<PageData> duoxuanRandomList = this.getRandomList(duoxuanList, duoxuanCnt);
		questionList.addAll(duoxuanRandomList);
		
		List<PageData> panduanList = examManageMapper.getPanduanList(pd);
		Integer panduanCnt = pd.getString("panduan_cnt") == "" ? 0 : Integer.parseInt(pd.getString("panduan_cnt"));
		List<PageData> panduanRandomList = this.getRandomList(panduanList, panduanCnt);
		questionList.addAll(panduanRandomList);
		
		pd.put("questionList", questionList);
		examManageMapper.addExamQuestion(pd);
		
	}
	
	public void removeByIds(String[] idArray) {
		examManageMapper.removeByIds(idArray);
		
	}

	public List<PageData> getExamList(PageData pd) {
		return examManageMapper.getExamList(pd);
	}

	public PageData getExamInfo(String id) {
		return examManageMapper.getExamInfo(id);
	}
	
	public List<PageData> getRandomList(List<PageData> list, Integer num){
		if(list.size() < num){
			return list;
		}else{
			Random random = new Random();
			List<Integer> tempList = new ArrayList<Integer>();
			List<PageData> newList = new ArrayList<PageData>();
			int temp = 0;
			for(int i=0; i< num; i++) {
				temp = random.nextInt(list.size());
				if(!tempList.contains(temp)) {
					tempList.add(temp);
					newList.add(list.get(temp));
				}else {
					i--;
				}
			}
			return newList;
		}
		
		
	}
}
