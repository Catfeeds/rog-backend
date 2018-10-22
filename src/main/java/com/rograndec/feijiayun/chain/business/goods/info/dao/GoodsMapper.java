package com.rograndec.feijiayun.chain.business.goods.info.dao;

import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrSendGoodsVO;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.vo.*;
import com.rograndec.feijiayun.chain.business.goods.manage.vo.GoodsHeadquartersVO;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.GoodsDictionaryVO;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.PrepayInvoiceGoodsVO;
import com.rograndec.feijiayun.chain.business.goods.price.vo.PriceAdjustGoodsVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inCheckQua.InCheckQuaGoodsReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inCheckQua.RequestGetInCheckQuaParamVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inReturn.InReturnGoodsReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inReturn.RequestGetInReturnParamVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inReturnOut.InReturnOutGoodsReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inReturnOut.RequestGetInReturnOutParamVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inStorage.InStorageGoodsReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inStorage.RequestGetInStorageParamVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.goods.FirstGoodsReviewPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.inCheckQua.PurchaseInCheckQuaGoodsReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.returnout.PurchaseReturnGoodsReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.returnout.PurchaseReturnOutGoodsReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.returnout.RequestGetReturnOutParamVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.returnout.RequestGetReturnParamVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.reviewCheck.PurchaseReviewCheckGoodsReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.reviewCheck.RequestGetReviewCheckParamVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.NearEffectPeriodGoodsVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestNearEffectVO;
import com.rograndec.feijiayun.chain.business.storage.split.vo.CanSpitGoodVO;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpeningGoodsGet;
import com.rograndec.feijiayun.chain.common.vo.CommonParamSupplierAndGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(Goods record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Goods record);

    /**
     *
     * @mbg.generated
     */
    Goods selectByPrimaryKey(Long id);

    List<Goods> selectByCode(@Param("list") List<String> list,@Param("enterpriseId") Long enterpriseId);
    List<Goods> selectByCodes(@Param("list") List<String> list,@Param("enterpriseId") Long enterpriseId);
    List<Goods> selectByOldCodes(@Param("list") List<String> list,@Param("enterpriseId") Long enterpriseId);


    List<Goods> selectByIds(@Param("list") List<Long> list,@Param("enterpriseId") Long enterpriseId);

    List<Goods> selectByIdsAndOwnerId(@Param("list") List<Long> list,@Param("enterpriseId") Long enterpriseId);

    List<Goods> selectByIdsNotStatus(@Param("list") List<Long> list);

    List<Goods> selectByIdsNotParentId(@Param("list") List<Long> list,@Param("enterpriseId") Long enterpriseId);


    List<Goods> selectByEnterpriseId(Long enterpriseId);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Goods record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Goods record);

    /**
     * 根据商品编码查询商品
     * @param goodsParamVO
     */
    Goods getGoodsByCode(GoodsParamVO goodsParamVO);

    Goods getGoodsByCode2(@Param("paramVO") CommonParamSupplierAndGoods paramSupplierAndGoods,@Param("param") String param);


    /**
     * 根据原始商品编码查询商品编码
     * @param oldCode
     * @param enterpriseId
     * @return
     */
    Goods getGoodsByOldCode(@Param("oldCode") String oldCode, @Param("enterpriseId") Long enterpriseId);



    /**
     * @Description: TODO 根据enterpriseId和key搜索，返回总数量
     * @author liuqun
     * @version 1.0 
     * @date 2017年9月5日 上午11:01:18 
     * @param enterpriseId
     * @param key
     * @return 
     * @return Long
     */
	Long queryCountByEnterpriseIdAndKey(@Param("enterpriseId")Long enterpriseId, @Param("key")String key,@Param("paramVO") CommonParamSupplierAndGoods paramVO);

	/**
	 * @Description: TODO根据enterpriseId和key搜索，分页返回数据
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年9月5日 上午11:02:05 
	 * @param start
	 * @param pageSize
	 * @param enterpriseId
	 * @param key
	 * @param order
	 * @param sort
	 * @return 
	 * @return List<GoodsHeadquartersVO>
	 */
	List<GoodsHeadquartersVO> selectByEnterpriseIdAndKey(@Param("start")int start,@Param("pageSize")int pageSize, 
			@Param("enterpriseId")Long enterpriseId, @Param("key")String key, @Param("order")String order,
			@Param("sort")String sort,@Param("paramVO") CommonParamSupplierAndGoods paramVO);



    /**
     * @Description: TODO查询总部下所有商品ID
     * @author liuqun
     * @version 1.0 
     * @date 2017年9月6日 下午5:57:40 
     * @param enterpriseId
     * @return 
     * @return List<Map<String,Long>>
     */
	List<Map<String, Object>> selectGoodsIdByEnterpriseId(Long enterpriseId);

    Integer getGoodsCountByParam(RequestGoodsVO2 requestGoodsVO2);

    List<PriceAdjustGoodsVO> getPriceOrderGoodsByParam(Map<String , Object> map);
    List<PriceAdjustGoodsVO> getPriceOrderGoodsByNotParam(Map<String , Object> map);

    List<PriceAdjustGoodsVO> getAdjustGoodsByParam(Map<String , Object> map);

    List<GoodsVO> getGoodsListByParam(RequestGoodsVO2 requestGoodsVO);

    List<GoodsVO> getGoodsListByParamForSale(RequestGoodsVO2 requestGoodsVO);
    Integer getGoodsCountByParamForSale(RequestGoodsVO2 requestGoodsVO2);

    GoodsVO selectGoodsInfoById(@Param("goodsId")Long goodsId);


    List<GoodsQualificationConfigVO> getGoodsQualificationConfig(@Param("goodsId")Long goodsId,@Param("enterpriseId")Long enterpriseId);


    //根据供应商获取商品
    List<Goods> getSupplierVarietiesGoods(Map map);
    //根据供应商获取商品
    Integer getSupplierVarietiesGoodsCount(Map map);

    //储存管理-拆零设置-根据商品id查询商品信息
    CanSpitGoodVO getSplitGoodById(Long id);

    //根据商品id列表获取多个商品信息
    List<Goods> getGoodsByIds(@Param("list")List<Long> list, @Param("enterpriseId")Long enterpriseId,@Param("ownerId")Long ownerId);

    /**
     * 以货位维度查询商品的库存信息
     * @param shelfIds
     * @param enterpriseId
     * @param param
     * @return
     */
    List<GoodsStockInfoVO> getGoodsInfoForInventory(@Param("shelfIds")List<Long> shelfIds,@Param("enterpriseId")Long enterpriseId,@Param("param")String param,
            @Param("start")Integer start,@Param("pageSize")Integer pageSize,@Param("selectAll") Integer selectAll);

   Integer getGoodsInfoForInventoryCount(@Param("shelfIds")List<Long> shelfIds,@Param("enterpriseId")Long enterpriseId,@Param("param")String param,
                                         @Param("start")Integer start,@Param("pageSize")Integer pageSize);

    /**
     * 以商品维度查询商品的库存信息
     * @param shelfIds
     * @param enterpriseId
     * @param param
     * @return
     */
    List<GoodsStockInfoVO> getGoodsInfoForInventoryGroupByGoodsId(@Param("shelfIds")List<Long> shelfIds,@Param("enterpriseId")Long enterpriseId,
                                                                  @Param("param")String param,@Param("start")Integer start,@Param("pageSize")Integer pageSize,
                                                                  @Param("selectAll") Integer selectAll);

    Integer getGoodsInfoForInventoryGroupByGoodsIdCount(@Param("shelfIds")List<Long> shelfIds,@Param("enterpriseId")Long enterpriseId,@Param("param")String param,
                                                        @Param("start")Integer start,@Param("pageSize")Integer pageSize);

    /**
     * 获取门店经营范围内的商品信息
     * @return
     */
    List<DistrSendGoodsVO> getDistrSendGoodsList(Map<String, Object> map);

    List<Goods> getFirstGoodsReviewReport(FirstGoodsReviewPageVO firstGoodsReviewPageVO);

    /**
     * 药学-药品词典、选药指导列表
     * @param enterpriseId
     * @param param
     * @return
     */
    List<GoodsDictionaryVO> getGoodsDictionaryList(@Param("enterpriseId")Long enterpriseId, @Param("param")String param, @Param("sort")String sort);

    List<Goods> getGoodsLicense(@Param("enterpriseId") Long enterpriseId, @Param("businessVariety") Integer businessVariety, @Param("param") String param, @Param("orderName") String orderName, @Param("orderType") String orderType);
    
    List<PurchaseReturnGoodsReportVO> getPurchaseReturnGoodsReport(@Param("returnParamVO")RequestGetReturnParamVO returnParamVO, @Param("enterpriseId")Long enterpriseId);

    List<PurchaseReturnOutGoodsReportVO> getPurchaseReturnOutGoodsReport(@Param("returnParamVO")RequestGetReturnOutParamVO returnParamVO, @Param("enterpriseId")Long enterpriseId);

    List<InReturnOutGoodsReportVO> getInReturnOutGoodsReport(@Param("returnParamVO")RequestGetInReturnOutParamVO returnParamVO, @Param("enterpriseId")Long enterpriseId);

    List<InReturnGoodsReportVO> getInReturnGoodsReport(@Param("returnParamVO")RequestGetInReturnParamVO getReturnParamVO, @Param("enterpriseId")Long enterpriseId);

    List<InStorageGoodsReportVO> getInStorageGoodsReport(@Param("returnParamVO")RequestGetInStorageParamVO getStorageParamVO,  @Param("enterpriseId")Long enterpriseId);

    List<InCheckQuaGoodsReportVO> getInCheckQuaGoodsReport(@Param("returnParamVO")RequestGetInCheckQuaParamVO getStorageParamVO, @Param("enterpriseId")Long enterpriseId);

    List<PurchaseInCheckQuaGoodsReportVO> getPurchaseInCheckQuaGoodsReport(@Param("returnParamVO")RequestGetInCheckQuaParamVO getStorageParamVO, @Param("enterpriseId")Long enterpriseId);

    List<PurchaseReviewCheckGoodsReportVO> getPurchaseReviewCheckGoodsReport(@Param("returnParamVO")RequestGetReviewCheckParamVO getStorageParamVO, @Param("enterpriseId")Long enterpriseId);

    Integer getCountByCode(@Param("code") String code,@Param("enterpriseId")Long enterpriseId);
    
    List<NearEffectPeriodGoodsVO> getNearEffectPeriodGoodsListNew(RequestNearEffectVO requestNearEffectVO);
    
    Map<String,Object> getNearEffectPeriodGoodsListTotalData(RequestNearEffectVO requestNearEffectVO);

    int updateGoodsStatus(@Param("goodsId")Long goodsId,@Param("status")Integer status,@Param("approveStatus")Integer approveStatus);

    GoodsVO getGoodsListByGoodsID(@Param("enterpriseId") Long enterpriseId, @Param("goodsId") Long goodsId);

    List<OpeningGoodsGet> getOpeningGoodsListByParam(@Param("param") String param,@Param("chainType")Integer chainType,@Param("enterpriseId") Long enterpriseId,@Param("parentId") Long parentId);

    List<Goods> selectExisitGoods(Long ownerId);
    List<PrepayInvoiceGoodsVO> selectPrepayInvoiceGoodsByEnterprise(@Param("enterpriseId") Long enterpriseId,
                                                        @Param("supplierId") Long supplierId,
                                                        @Param("ownerId") Long ownerId, @Param("param") String param);

    List<PrepayInvoiceGoodsVO> selectPrepayInvoiceGoodsBySupplier(@Param("enterpriseId") Long enterpriseId,
                                                        @Param("supplierId") Long supplierId,
                                                        @Param("ownerId") Long ownerId, @Param("param") String param);

    List<PrepayInvoiceGoodsVO> selectPrepayInvoiceGoodsByHeadEnterprise(@Param("enterpriseId") Long enterpriseId,
                                                        @Param("ownerId") Long ownerId, @Param("param") String param);

    List<DistrSendGoodsVO> getDistrSendGoodsListByPage(Map<String, Object> map);
    /**
     * 根据条件查询 加盟店 下面商品
     * @param joinGoodsRequestVo
     * @param start
     * @param pageSize
     * @return
     */
    List<Goods> selectJoinGoodsList(	
    	@Param("joinGoodsRequestVo")JoinGoodsRequestVo joinGoodsRequestVo,
    	@Param("start")int start,
    	@Param("pageSize")int pageSize);
    /**
     * 查询总数
     * @param joinGoodsRequestVo
     * @return
     */
    Long selectJoinGoodsListCount (@Param("joinGoodsRequestVo") JoinGoodsRequestVo joinGoodsRequestVo);

    List<GoodsVO> getMainGoodsList(@Param("requestGoodsVO")RequestGoodsVO2 requestGoodsVO);

}