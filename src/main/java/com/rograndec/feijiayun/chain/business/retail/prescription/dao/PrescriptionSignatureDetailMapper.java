package com.rograndec.feijiayun.chain.business.retail.prescription.dao;

import com.rograndec.feijiayun.chain.business.retail.prescription.entity.PrescriptionSignatureDetail;
import com.rograndec.feijiayun.chain.business.retail.prescription.vo.PrescriptionSignatureDetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrescriptionSignatureDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PrescriptionSignatureDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PrescriptionSignatureDetail record);

    /**
     *
     * @mbg.generated
     */
    PrescriptionSignatureDetail selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PrescriptionSignatureDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PrescriptionSignatureDetail record);

    List<PrescriptionSignatureDetailVO> selectBySignatureIdByEnterpriseId(@Param("signatureId") Long signatureId, @Param("enterpriseId") Long enterpriseId);

    int deleteBySignatureId(Long signatureId);
}