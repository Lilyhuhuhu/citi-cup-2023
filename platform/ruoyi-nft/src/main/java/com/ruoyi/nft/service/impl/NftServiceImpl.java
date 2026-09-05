package com.ruoyi.nft.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.nft.mapper.NftMapper;
import com.ruoyi.nft.domain.Nft;
import com.ruoyi.nft.service.INftService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author GuYue
 * @date 2023-03-15
 */
@Service
public class NftServiceImpl implements INftService 
{
    @Autowired
    private NftMapper nftMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param nftId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Nft selectNftByNftId(Long nftId)
    {
        return nftMapper.selectNftByNftId(nftId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param nft 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Nft> selectNftList(Nft nft)
    {
        return nftMapper.selectNftList(nft);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param nft 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertNft(Nft nft)
    {
        return nftMapper.insertNft(nft);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param nft 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateNft(Nft nft)
    {
        return nftMapper.updateNft(nft);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param nftIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNftByNftIds(Long[] nftIds)
    {
        return nftMapper.deleteNftByNftIds(nftIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param nftId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNftByNftId(Long nftId)
    {
        return nftMapper.deleteNftByNftId(nftId);
    }
}
