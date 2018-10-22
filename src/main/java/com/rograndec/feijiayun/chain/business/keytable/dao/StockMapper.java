package com.rograndec.feijiayun.chain.business.keytable.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.aftersale.recover.vo.AfterSaleChooseGoodsVO;
import com.rograndec.feijiayun.chain.business.keytable.entity.Stock;
import com.rograndec.feijiayun.chain.business.keytable.vo.GoodsInfoStockShelfVO;
import com.rograndec.feijiayun.chain.business.keytable.vo.StockLockRecordVO;
import com.rograndec.feijiayun.chain.business.keytable.vo.StockVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.OnWayGoodsPageVO;

public interface StockMapper {
    /**
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @mbg.generated
     */
    int insert(Stock record);

    /**
     * @mbg.generated
     */
    int insertSelective(Stock record);

    /**
     * @mbg.generated
     */
    Stock selectByPrimaryKey(Long id);

    List<Stock> getDefShelf(Map<String, Object> paramMap);

    List<Stock> selectByIds(List<Long> list);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Stock record);

    /**
     * @mbg.generated
     */
    int updateByPrimaryKey(Stock record);

    /**
     * @param @param  paramMap
     * @param @return 设定文件
     * @return Stock    返回类型
     * @throws
     * @Title: selectByParamMap
     * @Description: 根据paramMap获取库存实体
     */
    List<Stock> selectByParamMap(Map<String, Object> paramMap);

    List<Stock> selectByParamMapWithFIFO(Map<String, Object> paramMap);

    /**
    * @Description: 根据先进先出原则 获取合格非过期库存列表
    * @return:
    * @Author: dongyang.du
    * @Date: 19/01/2018
    */
    List<Stock> selectQuantityByParamMapWithFIFO(Map<String, Object> paramMap);

    /**
     * @param @param  paramMap
     * @param @return
     * @return BigDecimal 返回类型
     * @throws
     * @Title: getStorageQuantityByParamMap
     * @Description: 火星
     */
    BigDecimal getStorageQuantityByParamMap(Map<String, Object> paramMap);

    /**
     * @param @param  shelfId
     * @param @return
     * @return BigDecimal 返回类型
     * @throws
     * @Title: getCountByShelfId
     * @Description: 根据货位id 查询库存数据
     */
    Integer getStockCountByShelfId(Long shelfId);

    /**
     * @param enterpriseId
     * @param goodsId
     * @return List<Map<Long,Double>>
     * @Description: TODO根据enterpriseId、goodsId查询库存数量
     * @author liuqun
     * @version 1.0
     * @date 2017年9月6日 上午10:08:06
     */
    List<Map<String, Object>> selectGoodsQuantityByEnterpriseIdAndGoodsId(
            @Param("enterpriseId") Long enterpriseId, @Param("goodsId") Long goodsId);

    /**
     * @param enterId
     * @param goodsId
     * @return List<Map<Long,Double>>
     * @Description: TODO根据enterpriseId、goodsId批量查询库存数量
     * @author liuqun
     * @version 1.0
     * @date 2017年9月6日 上午10:08:06
     */
    List<Map<String, Object>> selectGoodsQuantityInEnterpriseIdAndGoodsId(
            @Param("enterId") List<Long> enterId, @Param("goodsId") List<Long> goodsId);

    /**
     * @param enterpriseId
     * @param goodsId
     * @return List<Map<String,Object>>
     * @Description: TODO根据goodsId批量查询库存数量
     * @author liuqun
     * @version 1.0
     * @date 2017年9月9日 上午10:12:43
     */
    List<Map<String, Object>> selectGoodsQuantityByEnterpriseIdAndInGoodsId(
            @Param("enterpriseId") Long enterpriseId, @Param("goodsId") List<Long> goodsId);

    /**
     * 根据批号查询货位
     *
     * @param enterpriseId
     * @param goodsId
     * @param lotNum
     * @return
     */
    List<StockVO> getStockVOByParam(@Param("enterpriseId") Long enterpriseId, @Param("goodsId") Long goodsId, @Param("lotNum") String lotNum);

    BigDecimal getUsableQuantity(@Param("lotNum") String lotNum, @Param("enterpriseId") Long enterpriseId, @Param("goodsId") Long goodsId);

    BigDecimal getUsableQuantityFromStockLock(@Param("lotNum") String lotNum, @Param("enterpriseId") Long enterpriseId, @Param("goodsId") Long goodsId);


    /**
     * 根据商品ID 和企业ID 查询库存可用量
     * @param enterpriseId
     * @param goodsId
     * @return
     * @Author: dongyang.du
     */
    BigDecimal getUsableQuantityGroupByGoodsId(@Param("enterpriseId") Long enterpriseId, @Param("goodsId") Long goodsId);

    List<GoodsInfoStockShelfVO> getGoodsStockInfoByGoodsId(@Param("enterpriseId") Long enterpriseId, @Param("goodsId") Long goodsId);

    /**
     * <根据货位id和药品获取货位信息>
     *
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/25 11:06
     */
    GoodsInfoStockShelfVO getGoodsStockInfoByGoodsIdAndShelfId(@Param("enterpriseId") Long enterpriseId,
                                                               @Param("goodsId") Long goodsId,
                                                               @Param("shelfId") Long shelfId,
                                                               @Param("lotId") Long lotId);
    
	/**
	 * 按批次正序排序
	 * @param enterpriseId
	 * @param goodsId
	 * @return
	 */
	List<Stock> getStockOrderByShelfId(@Param("enterpriseId")Long enterpriseId,@Param("goodsId")Long goodsId);

    /**
     * 通过货品ID 和 原货位ID 和 批号ID  查询库存信息  zeshi.sun
     * @param enterpriseId
     * @param goodsId
     * @param srcShelfId
     * @param lotId
     * @return
     */
    Stock getStockOrderByGoodsIdAndShelfIdAndLotId(@Param("enterpriseId")Long enterpriseId, @Param("goodsId")Long goodsId, @Param("srcShelfId")Long srcShelfId,
                                                   @Param("lotId")Long lotId);
    /**
     *
     * <根据商品id,锁定数量,货位id,批次id,企业id 修改商品锁定数量和可用数量--锁定数量>
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/29 10:54
     */
    int updateLockQuantity(@Param("goodsId")Long goodsId,
                           @Param("quantity")BigDecimal quantity,
                           @Param("shelfId")Long shelfId,
                           @Param("lotId")Long lotId,
                           @Param("enterpriseId")Long enterpriseId,
                           @Param("modifierId")Long modifierId,
                           @Param("modifierName")String modifierName,
                           @Param("modifierCode")String modifierCode,
                           @Param("updateTime")Date updateTime);
    /**
     *
     * <根据商品id,锁定数量,货位id,批次id,企业id 修改商品锁定数量和可用数量--解除锁定>
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/29 19:07
     */
    int updateUnLockQuantity(@Param("goodsId")Long goodsId,
                             @Param("quantity")BigDecimal quantity,
                             @Param("shelfId")Long shelfId,
                             @Param("lotId")Long lotId,
                             @Param("enterpriseId")Long enterpriseId,
                             @Param("modifierId")Long modifierId,
                             @Param("modifierName")String modifierName,
                             @Param("modifierCode")String modifierCode,
                             @Param("updateTime")Date updateTime);


    List<StockLockRecordVO> selectByEIdAndGoodsId(Map<String, Object> paramMap);



    BigDecimal getQuantityTotalByGoodsId(@Param("goodsId")Long goodsId,@Param("enterpriseId")Long enterpriseId);
    int updateStockByEIdLotIdGIdSId(Stock stock);
    /**
     *
     * <根据批号集合,商品id,企业id获取货位信息,按效期排序>
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/10/10 15:44
     */
	List<GoodsInfoStockShelfVO> getGoodsInfoStockShelfVOList(Map map);
    /**
     *
     * <根据商品id,企业id获取合格品货位信息>
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/10/11 17:17
     */
	List<GoodsInfoStockShelfVO> getQualifiedGoodsInfoShelf(Map map);

    /**
     * 售后召回、追回，选择商品列表
     * @param enterpriseId 企业ID
     * @param param 搜索关键字
     * @return 商品列表
     */
    List<AfterSaleChooseGoodsVO> getAfterSaleGoodsList(@Param("enterpriseId")Long enterpriseId, @Param("param")String param);

    /**
     * 通过商品ID、批次ID获取商品信息
     * @param enterpriseId
     * @param goodsId
     * @param lotId
     * @return
     */
    AfterSaleChooseGoodsVO getAfterSaleGoodsWithGoodsIdAndLotId(@Param("enterpriseId")Long enterpriseId, @Param("goodsId")Long goodsId, @Param("lotId")Long lotId);

    List<OnWayGoodsPageVO> selectFatherOnWay(Map<String, Object> map);

    List<OnWayGoodsPageVO> selectSonOnWay(Map<String, Object> map);

    int updateStockInventoryStatus(@Param("enterpriseId")Long enterpriseId,@Param("goodsId")Long goodsId,@Param("lotId")Long lotId,@Param("shelfId")Long shelfId,@Param("inventoryStatus")Integer inventoryStatus);

    BigDecimal selectQuantityByEnterPriseIdGoodIdAndShelfIdAndLotId(@Param("enterpriseId")Long enterPriseId, @Param("goodsId")Long goodsId, @Param("shelfId")Long shelfId,@Param("lotId") Long lotId);

    /**
     * 根据参数获取合格品库存大于0 的库存
     * @param paramMap
     * @return
     */
    List<Stock> selectGTZEROByParamMap(Map<String, Object> paramMap);

    /**
     * 获取合格品或者(合格并且未过期) 库存,积分兑换扣除的是  合格品货位
     * @return
     * @Author  dongyang.du
     */
    BigDecimal getQualifiedUsableQuantity(Map<String, Object> paramMap);


    Integer getQuantityByShelfIdS(@Param("enterpriseId")Long enterpriseId,@Param("shelfIdList")List<Long> shelfIdList);

    Integer selectFatherTotalRecord(Map<String, Object> map);

    Integer selectSonTotalRecord(Map<String, Object> map);

    BigDecimal getUsableQuantityFromStock(@Param("lotNum") String lotNumber, @Param("enterpriseId") Long enterpriseId, @Param("goodsId") Long goodsId);

    BigDecimal getShelfUsableQuantity(@Param("lotNum") String lotNumber, @Param("enterpriseId") Long enterpriseId, @Param("goodsId") Long goodsId, @Param("shelfId") Long shelfId);

}