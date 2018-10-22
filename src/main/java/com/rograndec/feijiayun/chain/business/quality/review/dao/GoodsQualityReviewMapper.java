package com.rograndec.feijiayun.chain.business.quality.review.dao;

import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrder;
import com.rograndec.feijiayun.chain.business.quality.review.entity.GoodsQualityReview;
import com.rograndec.feijiayun.chain.business.quality.review.vo.GoodsReviewVO;
import com.rograndec.feijiayun.chain.business.quality.review.vo.ReviewPageVO;
import com.rograndec.feijiayun.chain.business.quality.review.vo.ReviewRequestVO;

import java.util.List;
import java.util.Map;

public interface GoodsQualityReviewMapper {
    /**
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @mbg.generated
     */
    int insert(GoodsQualityReview record);

    /**
     * @mbg.generated
     */
    int insertSelective(GoodsQualityReview record);

    /**
     * @mbg.generated
     */
    GoodsQualityReview selectByPrimaryKey(Long id);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(GoodsQualityReview record);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKey(GoodsQualityReview record);

    List<GoodsReviewVO> getGoods(Map<String, Object> map);

    List<ReviewPageVO> selectPageList(ReviewRequestVO requestVO);

    /**
     * 查询评审期间投诉笔数
     *
     * @param param
     * @return
     */
    Integer selectComplainCountByGIDBtwDate(Map<String, Object> param);

    /**
     * 查询评审期间不良反应笔数
     *
     * @param param
     * @return
     */
    Integer selectAdverseReactionCountByGIDBtwDate(Map<String, Object> param);

    /**
     * 查询召回数量和批次
     *
     * @param param
     * @return
     */
    Map<String, Object> selectRecallSumByGIDBtwDate(Map<String, Object> param);

    /**
     * 查询追回数量和批次
     *
     * @return
     */
    Map<String, Object> selectRecoverSumByGIDBtwDate(Map<String, Object> param);

    /**
     * 查询销毁笔数，批次，数量
     *
     * @param param
     * @return
     */
    Map<String, Object> selectDestoryByGIDBtwDate(Map<String, Object> param);


    /**
     * 查询销售/销退笔数，批次，数量
     *
     * @param param
     * @return
     */
    Map<String, Object> selectSaleByGIDBtwDate(Map<String, Object> param);

    /**
     * 查询购进退出 笔数，批次，数量
     *
     * @param param
     * @return
     */
    Map<String, Object> selectPurchaseReturnByGIDBtwDate(Map<String, Object> param);

    /**
     * 查询验收笔数，合格/不合格数量
     *
     * @param param
     * @return
     */
    Map<String, Object> selectCheckByGIDBtwDate(Map<String, Object> param);


    /**
     * 查询订单笔数
     *
     * @param param
     * @return
     */
    Map<String, Object> selectOrderCountByGIDBtwDate(Map<String, Object> param);

    /**
     * 查询到货数量，收货数量，拒收数量
     *
     * @param param
     * @return
     */
    Map<String, Object> selectReceiveByGIDBtwDate(Map<String, Object> param);

    /**
     * 查询期间供货单位
     *
     * @param param
     * @return
     */
    List<PurchaseOrder> selectSupplierByGIDBtwDate(Map<String, Object> param);


    /**
     * 查询分页总数
     * @param requestVO
     * @return
     */
    Integer selectPageListCount(ReviewRequestVO requestVO);
}