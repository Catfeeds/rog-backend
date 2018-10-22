package com.rograndec.feijiayun.chain.business.retail.saleflow.dao;

import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.SaleShelf;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface SaleShelfMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SaleShelf record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SaleShelf record);

    /**
     *
     * @mbg.generated
     */
    SaleShelf selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SaleShelf record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SaleShelf record);
    /**
     *
     * <根据销售单明细id获取货位信息>
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/26 16:04
     */
    List<SaleShelf> getSaleShelfByDetailId(Long id);

	List<SaleShelf> queryByGidAndLotIdAndShelfIdAndLineNum(@Param("baseOrderCode")String baseOrderCode, 
			@Param("goodsId")Long goodsId, @Param("lotId")Long lotId, @Param("shelfId")Long shelfId, 
			@Param("oldLineNum")Integer oldLineNum, @Param("enterpriseId")Long enterpriseId);

    /**
     * 根据销售单ID获取货位明细列表
     * @param saleId
     * @param enterpriseId
     * @return
     */
    List<SaleShelf> queryBySaleIdAndEnterpriseId(@Param("saleId") Long saleId, @Param("enterpriseId") Long enterpriseId);

    List<SaleShelf> selectSaleShelfToVerificationByMap(Map<String, Object> map);

    List<SaleShelf> saleShelfReturnBySaleOutShelfId(Long id);

    List<SaleShelf> selectSaleShelfToWriteOffByMap(Map<String, Object> map);
}