package com.rograndec.feijiayun.chain.business.aftersale.recover.service;

import com.rograndec.feijiayun.chain.business.aftersale.recover.vo.AfterSaleChooseGoodsVO;
import com.rograndec.feijiayun.chain.business.aftersale.recover.vo.RecoverPlanVO;
import com.rograndec.feijiayun.chain.business.aftersale.recover.vo.RecoverPlanSaveOrupdateVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;


/**
 * 
 * @ClassName: RecoverPlanService   
 * @Description:  售后管理-追回管理-追回计划-接口
 * @author xingjian.lan
 * @version 1.0 
 * @date 2017-10-16 17:51:12
 */
public interface RecoverPlanService {
	
	
	List<RecoverPlanVO> getRecoverPlanData(UserVO userVO) throws Exception;
	
	int save(RecoverPlanSaveOrupdateVO recoverPlan, UserVO userVO) throws Exception;
	
	int update(RecoverPlanSaveOrupdateVO recoverPlan, UserVO userVO) throws Exception;
	
	int delete(Long id) throws Exception;

    /**
     * 获取追回计划列表
     * @param page 分页信息
     * @param map 传参
     * @return
     */
    Page getRecoverPlanPage(Page page, Map<String, Object> map);

    /**
     * 通过ID查询追回计划明细
     * @param id 追回计划id
     * @return
     */
    RecoverPlanVO getRecoverPlanDetail(Long id);

    /**
     * 获取商品列表
     * @param enterpriseId
     * @param param
     * @return
     */
    List<AfterSaleChooseGoodsVO> getAfterSaleGoodsList(Long enterpriseId, String param);

    /**
     * 获取追回计划下的商品
     * @param id 追回计划ID
     * @return
     */
    List<AfterSaleChooseGoodsVO> getRecoverGoodsList(Long id, String param);

    /**
     * 追回计划导出
     */
    void excelExport(OutputStream outPut, Long id, UserVO userVO) throws Exception;
}
