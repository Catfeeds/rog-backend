package com.rograndec.feijiayun.chain.business.retail.prescription.dao;

import com.rograndec.feijiayun.chain.business.retail.prescription.entity.PrescriptionSignature;
import com.rograndec.feijiayun.chain.business.retail.prescription.vo.ResponsePrescriptionSignatureForListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PrescriptionSignatureMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PrescriptionSignature record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PrescriptionSignature record);

    /**
     *
     * @mbg.generated
     */
    PrescriptionSignature selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PrescriptionSignature record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PrescriptionSignature record);

    /**
     * 获取签章列表
     * @param enterpriseId
     * @return
     */
    List<ResponsePrescriptionSignatureForListVO> getSignatureList(@Param("enterpriseId") Long enterpriseId,@Param("enterpriseId2")Long enterpriseId2,@Param("start")Integer start,@Param("pageSize")Integer pageSize);

    Integer getCountSignatureList(@Param("enterpriseId") Long enterpriseId,@Param("enterpriseId2")Long enterpriseId2);

    /**
     * 根据用户id获取关联的登记记录数
     * @param id
     * @return
     */
    Integer getCountAuditor(Long id);

    Integer getCountChecker(Long id);

    Integer getCountSwapMan(Long id);

    Integer getCountSender(Long userId);


    Integer getCountSignatureByUserId(@Param("userId")Long userId,@Param("enterpriseId")Long enterpriseId,@Param("prescriptionType")Integer prescriptionType);

    List<PrescriptionSignature> getSignatureByUserId(@Param("userId")Long userId,@Param("enterpriseId")Long enterpriseId,@Param("prescriptionType")Integer prescriptionType);

    List<Map<String, Object>> getSignatureMap(@Param("enterpriseId")Long enterpriseId, @Param("prescriptionType")Integer prescriptionType);


}