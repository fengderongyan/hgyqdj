package com.ruoyi.project.foruminfo.service;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.foruminfo.mapper.ForumInfoMapper;
import com.ruoyi.common.utils.text.Convert;

/**
 * 党员论坛Service业务层处理
 * 
 * @author yanbs
 * @date 2019-09-26
 */
@Service
public class ForumInfoService {
    @Autowired
    private ForumInfoMapper forumInfoMapper;

    /**
     * 查询党员论坛
     * 
     * @param id 党员论坛ID
     * @return 党员论坛
     */
    
    public PageData selectById(String id)
    {
        return forumInfoMapper.selectById(id);
    }

    /**
     * 查询党员论坛列表
     * 
     * @return 党员论坛
     */
    
    public List<PageData> selectList(PageData pd)
    {
        return forumInfoMapper.selectList(pd);
    }

    /**
     * 新增党员论坛
     * 
     * @param forumInfo 党员论坛
     * @return 结果
     */
    
    public int addSave(PageData pd){
    	pd.put("record_date", DateUtils.getNowDate());
        return forumInfoMapper.addSave(pd);
    }

    /**
     * 修改党员论坛
     * 
     * @param forumInfo 党员论坛
     * @return 结果
     */
    
    public int editSave(PageData pd)
    {
    	pd.put("record_date", DateUtils.getNowDate());
        return forumInfoMapper.editSave(pd);
    }

    /**
     * 删除党员论坛对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    
    public int deleteByIds(String ids)
    {
        return forumInfoMapper.deleteByIds(Convert.toStrArray(ids));
    }

	public List<PageData> selectImgsByForumId(String form_id) {
		// TODO Auto-generated method stub
		return forumInfoMapper.selectImgsByForumId(form_id);
	}

	public List<PageData> getReplyList(PageData pd) {
		// TODO Auto-generated method stub
		return forumInfoMapper.getReplyList(pd);
	}

	public int deleteReplyByIds(String ids) {
		// TODO Auto-generated method stub
		return forumInfoMapper.deleteReplyByIds(Convert.toStrArray(ids));
	}


}
