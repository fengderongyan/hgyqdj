package com.ruoyi.mobile.app.mapper;

import java.util.List;

import com.ruoyi.common.utils.PageData;
import com.ruoyi.project.questionmanage.domain.ExamQuestion;

public interface MobileBaseInfoMapper {

	public PageData getExamCnt(PageData pd);

	public List<PageData> getMyExamList(PageData pd);

	public List<PageData> getPointRank(PageData pd);

	public PageData getMyPoint(PageData pd);

	public PageData getRankRule();

	public PageData getUserInfo(PageData pd);

	public void editPassword(PageData pd);

	public Integer getIsNoticeRead(PageData pd);

	public List<PageData> getMyNoticeList(PageData pd);

	public void updateMyNticeRead(PageData pd);

	public PageData getMyNoticeInfo(PageData pd);

	public PageData getAboutUs();

	public String getMyForumCnt(PageData pd);

	public String getMyCollectCnt(PageData pd);

	public String getMyWishCnt(PageData pd);

	public List<PageData> getMyForumList(PageData pd);

	public void deleteForum(PageData pd);

	public List<PageData> getMyCollectionList(PageData pd);

	public List<PageData> getMyReplyList(PageData pd);

	public void mobileLogout(String login_name);


}
