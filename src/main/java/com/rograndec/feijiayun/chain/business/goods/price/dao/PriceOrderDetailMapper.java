package com.rograndec.feijiayun.chain.business.goods.price.dao;

import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import com.rograndec.feijiayun.chain.business.storage.split.vo.UpdateSplitSetVO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PriceOrderDetailMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    int deleteByPriceOrderId(Long priceOrderId);

    /**
     *
     * @mbg.generated
     */
    int insert(PriceOrderDetail record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PriceOrderDetail record);

    /**
     *
     * @mbg.generated
     */
    PriceOrderDetail selectByPrimaryKey(Long id);

    List<PriceOrderDetail> selectByPriceOrderId(Long priceOrderId);
    Integer selectByPriceOrderIdCount(Long priceOrderId);
    List<PriceOrderDetail> selectByPriceOrderIdPage(@Param("priceOrderId") Long priceOrderId,@Param("goodsIdList") List<Long> goodsIdList,@Param("start")Integer start,@Param("pageSize")Integer pageSize);

    List<PriceOrderDetail> selectByPriceOrderIdAndParam(@Param("priceOrderId") Long priceOrderId,@Param("param") String param,@Param("start")Integer start,@Param("pageSize")Integer pageSize);
    Integer selectByPriceOrderIdAndParamCount(@Param("priceOrderId") Long priceOrderId,@Param("param") String param);

    List<PriceOrderDetail> selectByIdAndGoodsIds(Long id , List<Long> list);

    List<PriceOrderDetail> selectByPriceOrderIds(List<Long> list);
    List<PriceOrderDetail> selectByIds(List<Long> list);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PriceOrderDetail record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PriceOrderDetail record);

    /**
     * @Description: TODO根据id查询PriceOrderDetail
     * @author liuqun
     * @version 1.0
     * @date 2017年9月6日 下午3:55:05
     * @return
     * @return List<PriceOrderDetail>
     */
    List<PriceOrderDetail> selectPriceOrderDetailByIds(List<Long> list);

    /**
     * @Description: TODO批量修改价格参数
     * @author liuqun
     * @version 1.0
     * @date 2017年9月6日 下午5:10:12
     * @return void
     */
    void batchUpdate(List<PriceOrderDetail> list);

    /**
     * @Description: TODO批量保存价格清单明细
     * @author liuqun
     * @version 1.0
     * @date 2017年9月6日 下午7:55:33
     * @return void
     */
    void batchInsert(List<PriceOrderDetail> list);

    /**
     * @Description: TODO根据价格清单ID店铺ID查询价格清单明细
     * @author liuqun
     * @version 1.0 
     * @date 2017年9月7日 下午8:12:37 
     * @param distrPriceOrderId
     * @param enterpriseId
     * @return 
     * @return Map<String,Double>
     */
    List<Map<String, Object>> selectDistrPriceByOrderIdAndEnterpriseId(
			@Param("distrPriceOrderId")Long distrPriceOrderId, @Param("enterpriseId")Long enterpriseId);

    /**
     * @Description: TODO根据商品ID店铺ID查询价格清单明细
     * @author liuqun
     * @version 1.0 
     * @date 2017年9月7日 下午8:12:37 
     * @param distrPriceOrderId
     * @param enterpriseId
     * @return 
     * @return Map<String,Double>
     */
	List<PriceOrderDetail> selectByGoodsIdsAndEnterpriseId(@Param("list")List<Long> list,
			@Param("enterpriseId")Long enterpriseId);


    /**
     *   where enterprise_id = #{enterpriseId}
     AND parent_id = #{parentId}
     AND price_order_id = #{priceOrderId}
     * @param list
     * @param enterpriseId
     * @return
     */
	List<PriceOrderDetail> selectByIndex(@Param("list")List<Long> list,
			@Param("enterpriseId")Long enterpriseId
            ,@Param("parentId")Long parentId
            ,@Param("priceOrderId")Long priceOrderId);



	List<PriceOrderDetail> selectByGIdsAndEIdAndSId(@Param("list")List<Long> list,
			@Param("enterpriseId")Long enterpriseId,@Param("supplierId")Long supplierId);


    /**
     * 根据企业id及商品id，查询商品对应的零售价格、会员价格
     * @param goodsId 商品id
     * @param enterpriseId 企业id
     * @return 价格清单明细
     */
    PriceOrderDetail selectByGoodsIdAndEnterpriseId(
            @Param("goodsId")Long goodsId, @Param("enterpriseId")Long enterpriseId);

    /**
     * 拆零商品修改-更新商品清单零售价格、会员价格
     * @param updateSplitSetVO
     * @return
     */
    int updateSplitGoodsPrices(UpdateSplitSetVO updateSplitSetVO);
    
    /**
     * 根据企业ID、价格清单ID、商品ID获取配送价格
     * @param enterpriseId
     * @param priceOrderId
     * @param goodsId
     * @return
     */
    BigDecimal selectDistrPriceByEnterpriseIdAndPriceOrderIdAndGoodId(@Param("enterpriseId")Long enterpriseId,
                                                                      @Param("priceOrderId")Long priceOrderId,
                                                                      @Param("goodsId")Long goodsId);

    PriceOrderDetail selectByEnterpriseIdAndPriceOrderIdAndGoodId(@Param("enterpriseId")Long enterpriseId,
                                                                      @Param("priceOrderId")Long priceOrderId,
                                                                      @Param("goodsId")Long goodsId);

    /**
     * 根据商品ID,价格清单ID，企业ID查询配送税率信息
     * @param goodsId
     * @param priceOrderId
     * @param enterpriseId
     * @return
     */
    Map<String, Object> selectDistrTaxRateByGoodsIdAndDistrPriceOrderId(
            @Param("goodsId")Long goodsId,
            @Param("priceOrderId")Long priceOrderId,
            @Param("enterpriseId")Long enterpriseId);
    /**
     * 获取总部默认的价格清单
     * @param goodsId
     * @param enterpriseId
     * @return
     */
    PriceOrderDetail getEnterpriseDefault( @Param("goodsId")Long goodsId,
            @Param("enterpriseId")Long enterpriseId);
}