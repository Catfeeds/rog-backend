package com.rograndec.feijiayun.chain.business.system.opening.dao;

import com.rograndec.feijiayun.chain.business.system.opening.entity.OpeningPaymentDetail;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpeningPaymentReceivableDetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OpeningPaymentDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(OpeningPaymentDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(OpeningPaymentDetail record);

    /**
     *
     * @mbg.generated
     */
    OpeningPaymentDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(OpeningPaymentDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(OpeningPaymentDetail record);

    List<OpeningPaymentReceivableDetailVO> selectByPaymentId(@Param("paymentId") Long paymentId);

    void insertBatch(List<OpeningPaymentDetail> list);

    List<OpeningPaymentDetail> queryByPaymentId(@Param("paymentId") Long paymentId);
}