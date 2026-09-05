package com.ruoyi.nft.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.nft.mapper.ActivityMapper;
import com.ruoyi.nft.domain.Activity;
import com.ruoyi.nft.service.IActivityService;

/**
 * Service业务层处理
 * 
 * @author GuYue
 * @date 2023-03-15
 */
@Service
public class ActivityServiceImpl implements IActivityService 
{
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public int getMoneyByUserId(Long userId) {
        return activityMapper.getMoneyByUserId(userId);
    }

    @Override
    public int getMoneyByNftid(Long nftId) {
        return activityMapper.getMoneyByNftid(nftId);
    }

    @Override
    public int getCountByHeld(Date held) { return activityMapper.getCountByHeld(held); }

    /**
     * 查询所有buyer（id）通过seller（id）
     *
     * @param seller 卖家id
     * @return List<Long>
     */
    @Override
    public List<Long> selectBuyerListBySeller(Long seller) {
        return activityMapper.selectBuyerListBySeller(seller);
    }

    @Override
    public List<Long> selectSellerListByBuyer(Long buyer) { return activityMapper.selectSellerListByBuyer(buyer); }

    /**
     * 查询所有交易对象通过seller（id）
     *
     * @param seller
     * @return List<Activity>
     */
    @Override
    public List<Activity> selectActivityListBySeller(Long seller) {
        return activityMapper.selectActivityListBySeller(seller);
    }

    /**
     * 查询所有交易对象通过buyer（id）
     *
     * @param buyer
     * @return List<Activity>
     */
    @Override
    public List<Activity> selectActivityListByBuyer(Long buyer) {
        return activityMapper.selectActivityListByBuyer(buyer);
    }

    /**
     * 查询【请填写功能名称】
     * 
     * @param activityId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Activity selectActivityByActivityId(Long activityId)
    {
        return activityMapper.selectActivityByActivityId(activityId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param activity 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Activity> selectActivityList(Activity activity)
    {
        return activityMapper.selectActivityList(activity);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param activity 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertActivity(Activity activity)
    {
        return activityMapper.insertActivity(activity);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param activity 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateActivity(Activity activity)
    {
        return activityMapper.updateActivity(activity);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param activityIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteActivityByActivityIds(Long[] activityIds)
    {
        return activityMapper.deleteActivityByActivityIds(activityIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param activityId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteActivityByActivityId(Long activityId)
    {
        return activityMapper.deleteActivityByActivityId(activityId);
    }
}
