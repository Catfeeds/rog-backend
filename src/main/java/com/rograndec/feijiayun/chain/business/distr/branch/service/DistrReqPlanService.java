package com.rograndec.feijiayun.chain.business.distr.branch.service;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrReqPlan;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.*;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrLack;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrReqPlanReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.OrderReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrReqPlan;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

import java.io.OutputStream;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Created by dudy on 2017/10/7.
 */
public interface DistrReqPlanService {
	 /**
     * 根据查询条件获取商品列表
     * */
    List<GoodsDistrReqPlanVO> getGoodsList(Map map,UserVO userVO);
    
    /**
     * 获取要货计划详情
     * */
    DistrReqPlanVO getById(Long id, UserVO loginUser);

    /**
     * 获取要货计划详情
     * */
    List<DistrReqPlanDetailVO> getReqPlanDtlByIds(String ids,UserVO userVO);
    
    /**
     * 获取配货单位列表
     * */
    List<EnterpriseReqPlanVO> getEnterprise(Map map);
   
    /**
     * 获取要货计划列表
     * */
    void getList(Page page,Map map);
	/**
	 * 根据要货分析获取商品列表
	 * */
	List<GoodsDistrReqPlanAnalysisVO> getGoodsDistrReqPlanVOlist(Map map, AnalyseStockVO analyseStockVO);
	
	public void save(UserVO userVO,DistrReqPlanVO distrReqPlanVO) throws Exception,BusinessException;
	
	public void update(UserVO userVO,DistrReqPlanVO distrReqPlanVO) throws Exception,BusinessException;
	
	public void changeStatus(UserVO userVO,DistrReqPlan plan) throws Exception;
	public void exportExcel(OutputStream output, Long id, UserVO loginUser);

	void getReqPlanOrderList(Page<OrderReportVo<DistrReqPlanReportVo>> page, RequestDistrReqPlan requestDistrReqPlan);

	void excelExport(OutputStream output, List<DistrReqPlanReportVo> distrReqPlanVOS, UserVO userVO);
	
	  /**
     * 根据配货类型获取配货单位列表
     * */
    List<EnterpriseReqPlanVO> getEnterpriseByDistrType(UserVO userVO,Integer distrType);
    
    /**
     * 根据配货类型获和查询条件获取商品列表
     * */
    List<GoodsDistrReqPlanVO> getGoodsListByDistrType(UserVO userVO,Integer distrType, Long supplierId,String param);
    void getGoodsListByDistrTypePage(Page page,Integer pageNO,Integer pageSize,String sortField,String sort,UserVO userVO,Integer distrType, Long supplierId,String param);
    List<DistrReqPlanOutVO> getSupplierList(UserVO userVO,Integer distrType);
    
    Integer getEnterpriseDistrType(UserVO userVO);
    void getGoodsByPage(Page page, Map map, UserVO userVO) throws ParseException;
    DraftCacheVO<DistrReqPlanVO> saveDraftCache(UserVO userVO, DraftCacheVO<DistrReqPlanVO> draftCacheVO);

	void removeDraftCach(Long enterpriseId, String type, String redisKeyValue);

	List<DraftCacheVO> getDraftCacheVO(UserVO userVO);


    AnalysisVO getAnalysisVO(List<GoodsDistrReqPlanAnalysisVO> goodsDistrReqPlanVOList, UserVO userVO, AnalyseStockVO analyseStockVO);

    /**
     * 查询缺配单明细
     * @param id
     * @param loginUser
     * @return
     */
    DistrLack getDistrLackDetailList(Long id, UserVO loginUser);
}
