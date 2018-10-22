package com.rograndec.feijiayun.chain.business.retail.prescription.dao;

import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.PrescriptionRegisterReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.PrescriptionReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.RequestPrescriptionVO;
import com.rograndec.feijiayun.chain.business.retail.prescription.entity.PrescriptionRegister;
import com.rograndec.feijiayun.chain.business.retail.prescription.vo.RequestParamForListVO;
import com.rograndec.feijiayun.chain.business.retail.prescription.vo.ResponsePrescriptionRegisterDetailForDetail2ExcelVO;
import com.rograndec.feijiayun.chain.business.retail.prescription.vo.ResponsePrescriptionRegisterForDetailVO;
import com.rograndec.feijiayun.chain.business.retail.prescription.vo.ResponsePrescriptionRegisterForListVO;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SelectPricingGoodsVO;
import com.rograndec.feijiayun.chain.inf.pos.prescription.vo.PrescriptionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PrescriptionRegisterMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PrescriptionRegister record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PrescriptionRegister record);

    /**
     *
     * @mbg.generated
     */
    PrescriptionRegister selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PrescriptionRegister record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PrescriptionRegister record);


    List<ResponsePrescriptionRegisterForListVO> getPrescriptionList(@Param("paramForListVO")RequestParamForListVO paramForListVO,@Param("enterpriseId") Long enterpriseId,@Param("isSuper")Integer isSuper);

    Integer getCountPrescriptionList(@Param("paramForListVO")RequestParamForListVO paramForListVO,@Param("enterpriseId") Long enterpriseId,@Param("isSuper")Integer isSuper);

    /**
     * 中药处方登记详情查询
     * @param id
     * @param enterpriseId
     * @return
     */
    ResponsePrescriptionRegisterForDetailVO getRegisterDetailTCMById(@Param("id")Long id, @Param("enterpriseId")Long enterpriseId);
    ResponsePrescriptionRegisterForDetailVO getRegisterDetailById(@Param("id")Long id, @Param("enterpriseId")Long enterpriseId);

    List<ResponsePrescriptionRegisterDetailForDetail2ExcelVO> getRegisterDetailById2Excel(@Param("id")Long id, @Param("enterpriseId")Long enterpriseId);

	List<PrescriptionVO> selectPrescriptionDataByEnterpriseId(@Param("enterpriseId")Long enterpriseId, @Param("status")Integer status);

	List<PrescriptionRegisterReportVO> getPrescriptionListReport(RequestPrescriptionVO requestPrescriptionVO);
	
	Integer getPrescriptionListReportTotalNum(RequestPrescriptionVO requestPrescriptionVO);
	
	ResponsePrescriptionRegisterForDetailVO getRegisterDetailReportById(@Param("id")Long id, @Param("enterpriseId")Long enterpriseId);

	List<ResponsePrescriptionRegisterDetailForDetail2ExcelVO> getPrescriptionRecordListReportExcel(@Param("id")Long id, @Param("enterpriseId")Long enterpriseId);

	PrescriptionRegister getPrescriptionRegisterById(Long id);
	
	PrescriptionReportVO getPrescriptionReportVo(RequestPrescriptionVO requestPrescriptionVO);

    List<SelectPricingGoodsVO> getGoodsInfoForStock(@Param("enterpriseId")Long enterpriseId,@Param("param")String param,@Param("prescriptionType")Integer prescriptionType);

    List<SelectPricingGoodsVO> getGoodsInfoForStockByGoods(@Param("enterpriseId")Long enterpriseId,
                                                           @Param("param")String param,
                                                           @Param("prescriptionType")Integer prescriptionType,
                                                           @Param("isInChargeDrug") Integer isInChargeDrug);

}