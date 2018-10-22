package com.rograndec.feijiayun.chain.business.distr.branch.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturn;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnDetail;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrReqPlanDetail;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInLotNumVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReqPlanDetailVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReturnParamVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReturnShelfVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrReqPlanDetailVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrReqPlanVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.ResponseDistrInDetailVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.ResponseDistrInReturnDetailInfoVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.ResponseDistrInReturnVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.ResponseDistrInVO;
import com.rograndec.feijiayun.chain.business.goods.info.entity.GoodsBusiness;

public interface DistrInReturnMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrInReturn record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrInReturn record);

    /**
     *
     * @mbg.generated
     */
    DistrInReturn selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrInReturn record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrInReturn record);
    
    /**
     * 获取配进入库列表
     * @return
     */
    List<ResponseDistrInVO> getDistrReturnInOrderList(DistrInReturnParamVO distrInReturnParamVO);
    
    /**
     * 获取配进入库单总页数
     * @param map
     * @return
     */
    Integer getDistrReturnInOrderListTotalNum(DistrInReturnParamVO distrInReturnParamVO);
    
    /**
     * 获取配进入库单明细列表
     * @return
     */
    List<ResponseDistrInDetailVO> getDistrReturnInOrderDtlList(DistrInReturnParamVO distrInReturnParamVO);
    
    /**
     * 获取配进入库单明细总条数
     * @param distrInReturnParamVO
     * @return
     */
    Integer getDistrReturnInOrderDtlListTotalNum(DistrInReturnParamVO distrInReturnParamVO);
    
    /**
     * 获取可用库存量
     * @param map
     * @return
     */
    BigDecimal getUsableQuantity(Map<String,Object> map);
    
    /**
     * 获取商品列表
     * @param map
     * @return
     */
    List<ResponseDistrInDetailVO> getGoodsStockList(Map<String,Object> map);
    
    /**
     * 获取购进推出单列表
     * @param map
     * @return
     */
    List<ResponseDistrInReturnVO> getDistrReturnOrderList(Map<String,Object> map);
    
    /**
     * 获取购进退出单总数
     * @param map
     * @return
     */
    Integer getDistrReturnOrderListTotalNum(Map<String,Object> map);
    
    /**
     * 获取配进退出单详情
     * @param map
     * @return
     */
    ResponseDistrInReturnDetailInfoVO getDistrReturnOrderDtlList(Map<String,Object> map);
    
    /**
     * 根据商品id和批号获取商品信息
     * @param map
     * @return
     */
    DistrInReturnDetail getDistrInReturnDetailGoodsInfo(Map<String,Object> map);
    
    /**
     * 根据id获取税率
     * @param taxTateId
     * @return
     */
    BigDecimal getGoodsTaxrateId(Long taxTateId);
    
    /**
     * 取消退出单
     * @param map
     */
    void cancelDistrReturnOrder(Map<String,Object> map);
    /**
     * 取消退出单
     * @param map
     */
    void cancelDistrReturnDetail(Map<String,Object> map);
    
    /**
     * 获取配进退出单状态
     * @param id
     * @return
     */
    Integer getDistrInReturnStatus(Long id);
    
    /**
     * 获取配进入库shelf表 可退数量
     * @param map
     * @return
     */
    BigDecimal getDisInDetailCanReturnQuantity(Map<String,Object> map);
    
    /**
     * 获取配进退出详情单退货数量
     * @param map
     * @return
     */
    BigDecimal getDisInReturnDetailReturnQuantity(Map<String,Object> map);
    
    /**
     * 获取配进入库shelf的合格品及不合格品的可退数量
     * @param map
     * @return
     */
    List<DistrInReturnShelfVO> getDistrInDtlSlfCanReturnQuantity(Map<String,Object> map);
    
    /**
     * 更改配进入库shelf的可退数量
     * @param map
     */
    void updateDistrInDtlSlfCanreturnQuantity(Map<String,Object> map);
    
    /**
     * 获取待审核，待出库的配进退出单退货数量
     * @param map
     * @return
     */
    BigDecimal getDisInReturnDtlRetQuantity(Map<String,Object> map);
    
    /**
     * 获取引用配进退出单的配进退出出库单的数量
     * @param id
     * @return
     */
    Integer getDistrInReturnOut(Long id);
    /**
     * 获取要货计划单列表
     * @param distrInReturnParamVO
     * @return
     */
    List<DistrReqPlanVO> getDistrReqPlanOrderList(DistrInReturnParamVO distrInReturnParamVO);
	/**
	 * 获取要货计划单总数
	 * @param distrInReturnParamVO
	 * @return
	 */
	Integer getDistrReqPlanOrderListTotalNum(DistrInReturnParamVO distrInReturnParamVO);
	
	/**
	 * 获取要货计划单详情
	 * @param distrInReturnParamVO
	 * @return
	 */
	List<DistrInReqPlanDetailVO> getDistrReqPlanOrderDtlList(DistrInReturnParamVO distrInReturnParamVO);
	/**
	 * 获取要货计划单详情总数
	 * @param distrInReturnParamVO
	 * @return
	 */
	Integer getDistrReqPlanOrderDtlListTotalNum(DistrInReturnParamVO distrInReturnParamVO);
	
	/**
	 * 根据商品id获取其对应库存批号
	 * @param param
	 * @return
	 */
	List<DistrInLotNumVO> getGoodsLotNums(Map<String,Object> param);
	
	/**
	 * 根据商品id获取其对应库存批号及库存可用量
	 * @param param
	 * @return
	 */
	List<DistrInLotNumVO> getGoodsStockLotNums(Map<String,Object> param);
	/**
	 * 获取要货计划单详情信息列表
	 * @param param
	 * @return
	 */
	List<DistrReqPlanDetail> getReqDtlList(Map<String,Object> param);
	
	  /**
     * @return
     */
    ResponseDistrInReturnVO getDistrReturnOrderById(Long id);
	
}