package com.ruoyi.nft.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 user
 * 
 * @author GuYue
 * @date 2023-03-15
 */
public class User extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户id */
    private Long userId;

    /** 用户姓名 */
    @Excel(name = "用户姓名")
    private String userName;

    /** 用户类型  -1有洗钱嫌疑 0正常 1洗过钱 */
    @Excel(name = "用户类型  -1有洗钱嫌疑 0正常 1洗过钱")
    private Long isFraud;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setIsFraud(Long isFraud) 
    {
        this.isFraud = isFraud;
    }

    public Long getIsFraud() 
    {
        return isFraud;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("isFraud", getIsFraud())
            .toString();
    }
}
