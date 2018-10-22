package com.rograndec.feijiayun.chain.business.distr.branch.dao;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInNotice;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.*;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrInNoticeReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrInNotice;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.CommonParamSupplierAndGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistrInNoticeMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrInNotice record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrInNotice record);

    /**
     *
     * @mbg.generated
     */
    DistrInNotice selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrInNotice record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrInNotice record);

    void updateStatus(@Param("id") Long noticeId, @Param("status") Integer status);

    /**
     * 查询明细
     * @param id
     * @return
     */
    DistrInNoticeVO getDistrInOrderDtlList(@Param("id") Long id);

    /**
     * 查询分页列表
     * @param requestVO
     * @return
     */
    List<DistrInNoticePageVO> getDistrInNoticePageList(DistrInNoticeRequestVO requestVO);


    List<DistrInNoticeDetailVO> getDistrNoticeDetailPageList(@Param("noticeIds")String[] noticeIds,@Param("start")Integer start,@Param("pageSize")Integer pageSize);
    Integer getDistrNoticeDetailPageListCount(@Param("noticeIds")String[] noticeIds);
	int getDistrInNoticeListCount(RequestDistrInNotice requestDistrInNotice);

    List<DistrInNoticeReportVo> getDistrInNoticeList(RequestDistrInNotice requestDistrInNotice);

    List<GoodsInNoticeVO> getGoodsByParam(@Param("commonParam") CommonParamSupplierAndGoods commonParam,@Param("supplierId") Long supplierId,@Param("param")String param);

    /**
     * 查询总的数量
     * @param requestVO
     * @return
     */
    Integer getDistrInNoticePageCount(DistrInNoticeRequestVO requestVO);

    List<GoodsInNoticeVO> getGoodsByParamWithPage(@Param("commonParam")CommonParamSupplierAndGoods commonParam, @Param("supplierId")Long supplierId, @Param("param")String param, @Param("page")Page page);

    Integer getGoodsByParamWithPageCount(@Param("commonParam")CommonParamSupplierAndGoods commonParam, @Param("supplierId")Long supplierId, @Param("param")String param);
}