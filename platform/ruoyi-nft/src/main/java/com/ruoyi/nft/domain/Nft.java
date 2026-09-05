package com.ruoyi.nft.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 nft
 * 
 * @author GuYue
 * @date 2023-03-15
 */
public class Nft extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** nft id主键 */
    private Long nftId;

    /** nft名字 */
    @Excel(name = "nft名字")
    private String nftName;

    /** nft图片的url */
    @Excel(name = "nft图片的url")
    private String img;

    /** nft稀有度 */
    @Excel(name = "nft稀有度")
    private Long rarity;

    public void setNftId(Long nftId) 
    {
        this.nftId = nftId;
    }

    public Long getNftId() 
    {
        return nftId;
    }
    public void setNftName(String nftName) 
    {
        this.nftName = nftName;
    }

    public String getNftName() 
    {
        return nftName;
    }
    public void setImg(String img) 
    {
        this.img = img;
    }

    public String getImg() 
    {
        return img;
    }
    public void setRarity(Long rarity) 
    {
        this.rarity = rarity;
    }

    public Long getRarity() 
    {
        return rarity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("nftId", getNftId())
            .append("nftName", getNftName())
            .append("img", getImg())
            .append("rarity", getRarity())
            .toString();
    }
}
