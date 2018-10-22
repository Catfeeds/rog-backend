package com.rograndec.feijiayun.chain.business.system.opening.dao;

import com.rograndec.feijiayun.chain.business.system.opening.entity.OpeningReceivableDetail;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpeningPaymentReceivableDetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OpeningReceivableDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(OpeningReceivableDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(OpeningReceivableDetail record);

    /**
     *
     * @mbg.generated
     */
    OpeningReceivableDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(OpeningReceivableDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(OpeningReceivableDetail record);

    List<OpeningPaymentReceivableDetailVO> selectByReceivableId(@Param("receivableId")Long receivableId);

    void insertBatch(List<OpeningReceivableDetail> list);

    List<OpeningReceivableDetail> queryByReceivableId(@Param("receivableId")Long receivableId);
}