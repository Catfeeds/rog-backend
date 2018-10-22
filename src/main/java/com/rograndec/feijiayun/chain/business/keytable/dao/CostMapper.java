package com.rograndec.feijiayun.chain.business.keytable.dao;

import com.rograndec.feijiayun.chain.business.keytable.entity.Cost;
import com.rograndec.feijiayun.chain.business.keytable.entity.Stock;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockBalanceReqVO;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockBalanceResultVO;
import com.rograndec.feijiayun.chain.business.report.finance.stock.vo.StockBalanceTotalVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.post.CostInfoPostVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.post.GoodsInfoPostVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CostMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(Cost record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Cost record);

    /**
     *
     * @mbg.generated
     */
    Cost selectByPrimaryKey(Long id);

    List<Cost> selectByIds(List<Long> list);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Cost record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Cost record);
    
    /**
     * 
     * @Title: selectByParamMap  
     * @Description: 根据parmMap获取成本列表
     * @param @param paramMap
     * @param @return    设定文件  
     * @return List<Cost>    返回类型  
     * @throws
     */
    List<Cost> selectByParamMap(Map<String,Object> paramMap);
    
    List<Cost> selectByParamMapWithFIFO(Map<String,Object> paramMap);

    /** 
    * @Description: 根据参数获取 非过期的成本列表,不合格的后期可能过滤
    * @return:  
    * @Author: dongyang.du
    * @Date: 19/01/2018 
    */ 
    List<Cost> selectQuantityByParamMapWithFIFO(Map<String,Object> paramMap);

    Cost selectByGoodsIdAndEnterpise(@Param("enterpriseId") Long enterpriseId,@Param("goodsId") Long goodsId);

    Cost selectByGoodIdLotIdEnterpriseId(@Param("goodsId") Long goodsId, @Param("lotId") Long lotId,@Param("enterpriseId") Long enterpriseId);

    GoodsInfoPostVO getCostInfoForPost(@Param("goodsId") Long goodsId, @Param("lotId") Long lotId, @Param("enterpriseId") Long enterpriseId);

    CostInfoPostVO getCostTotalQuantityAndAmount(@Param("goodsId") Long goodsId, @Param("lotId") Long lotId, @Param("enterpriseId") Long enterpriseId);

    CostInfoPostVO getCostTotalAmount(@Param("goodsId") Long goodsId, @Param("lotId") Long lotId, @Param("enterpriseId") Long enterpriseId);

    Cost selectByGoodIdEnterpriseId(@Param("goodsId") Long goodsId, @Param("enterpriseId") Long enterpriseId);

    List<Cost> selectMaxId(@Param("list") List<Long> list, @Param("enterpriseId") Long enterpriseId);

    List<GoodsInfoPostVO> getGoodsStockForPost(@Param("enterpriseId")Long enterpriseId, @Param("goodsId")Long goodsId, @Param("lotId")Long lotId);


    /**
     *  库存余额分页查询
     * @param balanceReqVO
     * @return
     */

    List<StockBalanceResultVO> getBalancesPage(StockBalanceReqVO balanceReqVO);

    /**
     *  库存余额分页总数
     * @param balanceReqVO
     * @return
     */
    Integer selectBalancesPageCount(StockBalanceReqVO balanceReqVO);

    /** 
    * @Description: 财务报表 - 查询总数
    * @return:  
    * @Author: dongyang.du
    * @Date: 11/01/2018 
    */ 
    StockBalanceTotalVO selectTotalQuantityAndAmout(StockBalanceReqVO balanceReqVO);

}