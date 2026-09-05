package com.ruoyi.nft.mapper;

import java.util.List;
import com.ruoyi.nft.domain.User;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author GuYue
 * @date 2023-03-15
 */
public interface UserMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public User selectUserByUserId(Long userId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param user 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<User> selectUserList(User user);

    /**
     * 新增【请填写功能名称】
     * 
     * @param user 【请填写功能名称】
     * @return 结果
     */
    public int insertUser(User user);

    /**
     * 修改【请填写功能名称】
     * 
     * @param user 【请填写功能名称】
     * @return 结果
     */
    public int updateUser(User user);

    /**
     * 删除【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteUserByUserId(Long userId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserByUserIds(Long[] userIds);
}
