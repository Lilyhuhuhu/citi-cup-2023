package com.ruoyi.nft.mapper;

import java.util.List;
import com.ruoyi.nft.domain.Nft;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author GuYue
 * @date 2023-03-15
 */
public interface NftMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param nftId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Nft selectNftByNftId(Long nftId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param nft 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Nft> selectNftList(Nft nft);

    /**
     * 新增【请填写功能名称】
     * 
     * @param nft 【请填写功能名称】
     * @return 结果
     */
    public int insertNft(Nft nft);

    /**
     * 修改【请填写功能名称】
     * 
     * @param nft 【请填写功能名称】
     * @return 结果
     */
    public int updateNft(Nft nft);

    /**
     * 删除【请填写功能名称】
     * 
     * @param nftId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteNftByNftId(Long nftId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param nftIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNftByNftIds(Long[] nftIds);
}
