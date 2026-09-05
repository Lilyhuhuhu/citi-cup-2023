package com.ruoyi.nft.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 activity
 * 
 * @author GuYue
 * @date 2023-03-15
 */
public class Activity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 交易主键id */
    private Long activityId;

    /** 外键 */
    @Excel(name = "外键")
    private Long nftId;

    /** price 美元 */
    @Excel(name = "price 美元")
    private Long priceUsd;

    /** price 以太坊 */
    @Excel(name = "price 以太坊")
    private Long priceEth;

    /** 交易发生时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交易发生时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date held;

    /** 外键 卖家id */
    @Excel(name = "外键 卖家id")
    private Long seller;

    /** 外键 买家id */
    @Excel(name = "外键 买家id")
    private Long buyer;

    public void setActivityId(Long activityId) 
    {
        this.activityId = activityId;
    }

    public Long getActivityId() 
    {
        return activityId;
    }
    public void setNftId(Long nftId) 
    {
        this.nftId = nftId;
    }

    public Long getNftId() 
    {
        return nftId;
    }
    public void setPriceUsd(Long priceUsd) 
    {
        this.priceUsd = priceUsd;
    }

    public Long getPriceUsd() 
    {
        return priceUsd;
    }
    public void setPriceEth(Long priceEth) 
    {
        this.priceEth = priceEth;
    }

    public Long getPriceEth() 
    {
        return priceEth;
    }
    public void setHeld(Date held) 
    {
        this.held = held;
    }

    public Date getHeld() 
    {
        return held;
    }
    public void setSeller(Long seller) 
    {
        this.seller = seller;
    }

    public Long getSeller() 
    {
        return seller;
    }
    public void setBuyer(Long buyer) 
    {
        this.buyer = buyer;
    }

    public Long getBuyer() 
    {
        return buyer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("activityId", getActivityId())
            .append("nftId", getNftId())
            .append("priceUsd", getPriceUsd())
            .append("priceEth", getPriceEth())
            .append("held", getHeld())
            .append("seller", getSeller())
            .append("buyer", getBuyer())
            .toString();
    }
}
