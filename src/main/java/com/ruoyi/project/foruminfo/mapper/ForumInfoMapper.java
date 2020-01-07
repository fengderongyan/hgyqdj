package com.ruoyi.project.foruminfo.mapper;

import com.ruoyi.common.utils.PageData;
import java.util.List;

/**
 * 党员论坛Mapper接口
 * 
 * @author yanbs
 * @date 2019-09-26
 */
public interface ForumInfoMapper 
{
    /**
     * 查询党员论坛
     * 
     * @param id 党员论坛ID
     * @return 党员论坛
     */
    public PageData selectById(String id);

    /**
     * 查询党员论坛列表
     * 
     * @return 党员论坛集合
     */
    public List<PageData> selectList(PageData pd);

    /**
     * 新增党员论坛
     * 
     * @return 结果
     */
   public int addSave(PageData pd);

    /**
     * 修改党员论坛
     * 
     * @param forumInfo 党员论坛
     * @return 结果
     */
   public int editSave(PageData pd);

    /**
     * 批量删除党员论坛
     * 
     * @return 结果
     */
    public int deleteByIds(String[] ids);

	public List<PageData> selectImgsByForumId(String form_id);

	public List<PageData> getReplyList(PageData pd);

	public int deleteReplyByIds(String[] strArray);
}
