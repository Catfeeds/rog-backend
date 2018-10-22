package com.rograndec.feijiayun.chain.business.finance.commission.dao;

import com.rograndec.feijiayun.chain.business.finance.commission.entity.SaleCommission;
import com.rograndec.feijiayun.chain.business.finance.commission.vo.CommissionResponseTotalVO;
import com.rograndec.feijiayun.chain.business.finance.commission.vo.NoSaleCommissionDetailResponseVO;

import java.util.List;
import java.util.Map;

public interface SaleCommissionMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SaleCommission record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SaleCommission record);

    /**
     *
     * @mbg.generated
     */
    SaleCommission selectByPrimaryKey(Long id);

    List<SaleCommission> selectByParam(Map<String,Object> map);
    CommissionResponseTotalVO selectByParamSum(Map<String,Object> map);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SaleCommission record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SaleCommission record);

    List<NoSaleCommissionDetailResponseVO> selectNoCommissionsByParam(Map<String,Object> map);
}