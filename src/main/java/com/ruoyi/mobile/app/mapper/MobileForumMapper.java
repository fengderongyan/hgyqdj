package com.ruoyi.mobile.app.mapper;

import java.util.List;

import com.ruoyi.common.utils.PageData;

public interface MobileForumMapper {

	public List<PageData> getForumTypeList();

	public List<PageData> getForumList(PageData pd);

	public PageData getForumInfo(PageData pd);

	public List<PageData> getForumImgList(PageData pd);

	public List<PageData> getForumReplyList(PageData pd);

	public void deleteReplyInfo(PageData pd);

	public void insertReplyInfo(PageData pd);

	public Integer getCollectStatus(PageData pd);

	public void delCollectForum(PageData pd);

	public void collectForum(PageData pd);

	public void insertForumContent(PageData pd);

	public void insertForumImg(PageData parampd);

	public void updateReadCnt(PageData pd);

	public String getRegByFormId(PageData pd);

	public void addIgnoreForum(PageData pd);

	public void addForumReport(PageData pd);

}
