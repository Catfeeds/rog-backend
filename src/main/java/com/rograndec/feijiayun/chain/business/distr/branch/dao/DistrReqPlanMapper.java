package com.rograndec.feijiayun.chain.business.distr.branch.dao;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrReqPlan;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.*;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrSendReqPlanVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrReqPlanReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrReqPlan;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DistrReqPlanMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DistrReqPlan record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DistrReqPlan record);

    /**
     *
     * @mbg.generated
     */
    DistrReqPlan selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DistrReqPlan record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DistrReqPlan record);
    
    /**
     * 根据查询条件获取商品列表
     * */
    List<GoodsDistrReqPlanVO> selectGoodsList(Map map);
    
    /**
     * 获取要货计划详情
     * */
    DistrReqPlanVO selectById(Long id);
    
    /**
     * 获取配货单位列表
     * */
    List<EnterpriseReqPlanVO> selectEnterprise(Map map);
   
    /**
     * 获取要货计划列表
     * */
    List<DistrReqPlanVO> selectList(Map map);
    Long selectCount(Map map);
    
    /**
     * 根据安全库存分析获取商品列表
     * */
    List<GoodsDistrReqPlanAnalysisVO> selectGoodsListBySafetyStock(Map map);
    
    /**
     * 按缺断货数量分析获取商品列表
     * */
    List<GoodsDistrReqPlanAnalysisVO> selectGoodsListByWeekLimit(Map map);
    
    /**
     * 按照动态存量分析获取商品列表
     * */
    List<GoodsDistrReqPlanAnalysisVO> selectGoodsListByWeekLimitHalfDay(Map map);
    
    /**
     * 在途库存
     * */
    List<OnPassageStockVO> selectOnPassageStock(Map map);

    /**
     * 查询总店的所有要货计划（总店-配货单-调用要货计划）
     * @param map
     * @return
     */
    List<DistrSendReqPlanVO> getReqPlanByDistrUnitId(Map map);

    /**
     * 根据商品ID获取单个商品
     * */
    GoodsDistrReqPlanVO selectGoods(Map map);

    /**
     * 总部-配送单，生成配送单后，修改要货计划的状态：31-待配货 改为 33-已配货
     * @param id
     * @return
     */
    int updateStatusById(DistrReqPlan distrReqPlan);

	int getReqPlanOrderListCount(RequestDistrReqPlan requestDistrReqPlan);

    List<DistrReqPlanReportVo> getReqPlanOrderList(RequestDistrReqPlan requestDistrReqPlan);
    
    /**
     * 根据配货类型获取配货单位列表
     * */
    List<EnterpriseReqPlanVO> getEnterpriseByDistrType(Map<String,Object> param);
    
    /**
     * 根据配货类型获和查询条件获取商品列表
     * */
    List<GoodsDistrReqPlanVO> getGoodsListByDistrType(Map<String,Object> param);
    
    List<GoodsDistrReqPlanVO> getGoodsListByDistrTypePage(Map<String,Object> param);
    Long getGoodsListByDistrTypeCount(Map<String,Object> param);
    /**
     * 获取供应商的信息列表
     * @param param
     * @return
     */
    List<Supplier> getSupplierList(Map<String,Object> param);
    
    /**
     * 根据企业id获取企业配送管理设置  0禁止直购  1允许直购
     * @param id
     * @return
     */
    Integer getEnterpriseDistrType(Long id);

    /**
     * 跟库企业ID和商品ID查询库存可用量
     * @param param
     * @return
     */
    List<DistrReqPlanSumVO> getStockSumQuantity(Map<String,Object> param);

    Long queryCountByDistrReqPlanParams(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize, @Param("startTimes")String startTimes, @Param("endTimes")String endTimes, @Param("type")Integer type, @Param("disType")Integer disType);

    List<DistrReqPlanPageVO> selectByRequestType(@Param("enterpriseId")Long enterpriseId, @Param("start")int start, @Param("pageSize")int pageSize, @Param("startTimes")String startTimes, @Param("endTimes")String endTimes, @Param("type")Integer type, @Param("disType")Integer disType,
                                                 @Param("orderName")String orderName, @Param("orderType")String orderType);

    List<DistrReqPlanDetailVO> getReqPlanDtlByIds(@Param("ids")String[] ids,@Param("enterpriseId")Long enterpriseId);
    //分页获取商品
    List<GoodsDistrReqPlanVO> queryGoodsList(Map map);
    Long queryGoodsCount(Map map);

    /** 
    * @Description: 门店间调剂类型的搜索合格未过期的商品
    * @return:  
    * @Author: dongyang.du
    * @Date: 06/02/2018 
    */ 
    List<GoodsDistrReqPlanVO> queryQuantityGoodsList(Map map);

    /** 
    * @Description: 门店间调剂类型的搜索合格未过期的商品 总和
    * @return:  
    * @Author: dongyang.du
    * @Date: 06/02/2018 
    */ 
    Long queryQuantityGoodsCount(Map map);
}