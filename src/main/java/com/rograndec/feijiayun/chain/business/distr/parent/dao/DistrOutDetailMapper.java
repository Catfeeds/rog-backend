package com.rograndec.feijiayun.chain.business.distr.parent.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOutDetail;

public interface DistrOutDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrOutDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrOutDetail record);

    /**
     *
     * @mbg.generated
     */
    DistrOutDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrOutDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrOutDetail record);
    /**
     *
     * <根据出库单id获取明细信息>
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/10/8 16:47
     */
    List<DistrOutDetail> getDistrOutDetailList(Long id);

    void deleteByOutId( @Param("id")Long id,  @Param("enterpriseId")Long enterpriseId);

    DistrOutDetail selectByBaseOrderDtlId(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);
    /**
     * 根据商品id和要货单位ID查询最新出库一条记录
     * @param enterpriseId
     * @param goodsId
     * @return
     */
    DistrOutDetail selectByEnterpriseIdGoodsID(@Param("enterpriseId")Long enterpriseId, @Param("goodsId")Long goodsId);
}