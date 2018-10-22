package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.dao;

import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.entity.ReceivableVoucherModifyRecord;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.vo.VoucherModifyRecordPageVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReceivableVoucherModifyRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(ReceivableVoucherModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(ReceivableVoucherModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    ReceivableVoucherModifyRecord selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ReceivableVoucherModifyRecord record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ReceivableVoucherModifyRecord record);

    Integer getVoucherModifyRecordPageCount(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    List<VoucherModifyRecordPageVO> getVoucherModifyRecordPage(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize, @Param("id")Long id);

    List<VoucherModifyRecordPageVO> getVoucherModifyRecordPageList(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);
}