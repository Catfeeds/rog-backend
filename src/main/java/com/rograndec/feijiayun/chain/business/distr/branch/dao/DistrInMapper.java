package com.rograndec.feijiayun.chain.business.distr.branch.dao;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrIn;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInstorageStasticVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo.PrepayInvoiceInStoreResponseVO;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.PendingInvoicingVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrInReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrInVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DistrInMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrIn record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrIn record);

    /**
     *
     * @mbg.generated
     */
    DistrIn selectByPrimaryKey(Long id);

    List<DistrIn> selectByIds(List<Long> list);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrIn record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrIn record);

    List<DistrIn> getInstorageHasBeenPage(Map<String, Object> map);

    List<DistrInReportVO> getDistrInReportList(RequestDistrInVO requestDistrInVO);
    
    Integer getDistrInReportListTotalNum(RequestDistrInVO requestDistrInVO);
    
    DistrReportVo<DistrInReportVO> getDistrReportVo(RequestDistrInVO requestDistrInVO);

    DistrIn selectByBaseOrderId(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    Integer getTotalRecord(Map<String, Object> map);

    DistrInstorageStasticVO selectStastic(Map<String, Object> map);

    /**
     * 查询加盟店应付总部和加盟店的供货单位账款未开票数据记录数
     * @param map
     * @return
     */
    Integer getPaymentPendingInvoicingCount(Map<String, Object> map);
    /**
     * 查询加盟店应付总部和加盟店的供货单位账款未开票分页数据
     * @param map
     * @return
     */
    List<PendingInvoicingVO> getPaymentPendingInvoicingList(Map<String, Object> map);

    List<DistrIn> selectSupplier2PaymentInvoice(@Param("enterpriseId") Long enterpriseId,
                                                              @Param("startDate") Date startDate,
                                                              @Param("endDate") Date endDate,
                                                              @Param("distrType") Integer distrType,
                                                              @Param("list") List<Integer> list,
                                                              @Param("outboundUnitId") Long outboundUnitId
    );

    List<DistrIn> selectEnterprisePaymentInvoice(@Param("enterpriseId") Long enterpriseId,
                                                              @Param("startDate") Date startDate,
                                                              @Param("endDate") Date endDate,
                                                              @Param("distrType") Integer distrType,
                                                              @Param("list") List<Integer> list,
                                                              @Param("outboundUnitId") Long outboundUnitId
    );
}