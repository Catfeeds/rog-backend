package com.rograndec.feijiayun.chain.business.distr.branch.dao;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInDetail;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo.PrepayInvoiceInStoreResponseVO;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.PrepayInvoiceGoodsVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface DistrInDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    PrepayInvoiceGoodsVO selectByPrepayInvoiceGoodses(@Param("distrType") Integer distrType,
                                                            @Param("list") List<Integer> list,
                                                            @Param("enterpriseId") Long enterpriseId,
                                                            @Param("goodsId") Long goodsId,
                                                            @Param("param") String param);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrInDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrInDetail record);

    /**
     *
     * @mbg.generated
     */
    DistrInDetail selectByPrimaryKey(Long id);

    List<DistrInDetail> selectByInId(@Param("inId") Long inId);

    List<DistrInDetail> selectByInIds(List<Long> list);

    List<DistrInDetail> selectByIds(List<Long> list);

    List<PrepayInvoiceInStoreResponseVO> select2PrepayInvoice(@Param("enterpriseId") Long enterpriseId,
                                                              @Param("goodsId") Long goodsId,
                                                              @Param("startDate") Date startDate,
                                                              @Param("endDate") Date endDate,
                                                              @Param("distrType") Integer distrType,
                                                              @Param("list") List<Integer> list,
                                                              @Param("outboundUnitId") Long outboundUnitId
    );


    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrInDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrInDetail record);

    List<DistrInDetail> selectByEnterpriseIdAndInId(@Param("id") Long id);
}