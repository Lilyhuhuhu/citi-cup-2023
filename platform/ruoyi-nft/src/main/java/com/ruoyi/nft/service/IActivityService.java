package com.ruoyi.nft.service;

import java.util.Date;
import java.util.List;
import com.ruoyi.nft.domain.Activity;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author GuYue
 * @date 2023-03-15
 */
public interface IActivityService 
{
    /**
     * 获取交易金额通过user_id
     *
     * @param userId
     * @return int
     */
    public int getMoneyByUserId(Long userId);

    /**
     * 获取平台交易金额通过nft_id
     *
     * @param nftId
     * @return int
     */
    public int getMoneyByNftid(Long nftId);

    /**
     * 获取交易数通过交易时间held
     *
     * @param held
     * @return int
     */
    public int getCountByHeld(Date held);

    /**
     * 查询所有buyer（id）通过seller（id）
     *
     * @param seller 卖家id
     * @return List<Long>
     */
    public List<Long> selectBuyerListBySeller(Long seller);

    /**
     * 查询所有seller（id）通过buyer（id）
     *
     * @param buyer
     * @return List<Long>
     */
    public List<Long> selectSellerListByBuyer(Long buyer);

    /**
     * 查询所有交易对象通过seller（id）
     *
     * @param seller
     * @return List<Activity>
     */
    public List<Activity> selectActivityListBySeller(Long seller);

    /**
     * 查询所有交易对象通过buyer（id）
     *
     * @param buyer
     * @return List<Activity>
     */
    public List<Activity> selectActivityListByBuyer(Long buyer);

    /**
     * 查询【请填写功能名称】
     * 
     * @param activityId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Activity selectActivityByActivityId(Long activityId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param activity 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Activity> selectActivityList(Activity activity);

    /**
     * 新增【请填写功能名称】
     * 
     * @param activity 【请填写功能名称】
     * @return 结果
     */
    public int insertActivity(Activity activity);

    /**
     * 修改【请填写功能名称】
     * 
     * @param activity 【请填写功能名称】
     * @return 结果
     */
    public int updateActivity(Activity activity);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param activityIds 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteActivityByActivityIds(Long[] activityIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param activityId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteActivityByActivityId(Long activityId);
}
