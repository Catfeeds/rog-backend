package com.rograndec.feijiayun.chain.business.distr.branch.service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInLotNumVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInRetReqDtlVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReturnParamVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrReqPlanVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.RequsetDistrReturnInSaveOrUpdateVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.ResponseDistrInDetailVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.ResponseDistrInReturnDetailInfoVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.ResponseDistrInReturnVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.ResponseDistrInVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;


public interface DistrInReturnService {

	Page<List<ResponseDistrInVO>> getDistrReturnInOrderList(Page<List<ResponseDistrInVO>> page,
			DistrInReturnParamVO distrInReturnParamVO);
	
	Page<List<ResponseDistrInDetailVO>> getDistrReturnInOrderDtlList(Page<List<ResponseDistrInDetailVO>> page,
			DistrInReturnParamVO distrInReturnParamVO);
	
	List<ResponseDistrInDetailVO> getGoodsStockList(Long enterpriseId,String param,UserVO userVO,Integer distrType,Long supplierId);
	
	Page<List<ResponseDistrInReturnVO>> getDistrReturnOrderList(Long enterpriseId,String distrUnitCode,String distrUnitName, 
			String code,Integer distrType,String returnManName,Date startTime,Date endTime,Integer status,Integer dateOrder,Integer codeOrder,
                    Page<List<ResponseDistrInReturnVO>> page);
	
	ResponseDistrInReturnDetailInfoVO getDistrReturnOrderDtlList(UserVO userVO, Long recordId);
	
	String saveDistrReturnOrder(UserVO user,RequsetDistrReturnInSaveOrUpdateVO requsetDistrReturnInSaveOrUpdateVO) throws Exception;
	
	String updateDistrReturnOrder(UserVO user,RequsetDistrReturnInSaveOrUpdateVO requsetDistrReturnInSaveOrUpdateVO) throws Exception;
	
	String cancelDistrReturnOrder(Long enterpriseId,Long id,UserVO user);
	
	void exportExcel(OutputStream output,UserVO user,Long id);
	
	BigDecimal getUnitPriceByQuantity(Long enterpriseId,Long goodsId,Long lotId,BigDecimal quantity);
	
	void getDistrReqPlanOrderList(Page<List<DistrReqPlanVO>> page,DistrInReturnParamVO distrInReturnParamVO, UserVO userVO);
	
	void getDistrReqPlanOrderDtlList(Page<DistrInRetReqDtlVO> page,DistrInReturnParamVO distrInReturnParamVO,UserVO userVO);
	
	List<DistrInLotNumVO> getGoodsLotNums(Long goodsId,Long enterpriseId,BigDecimal quantity);
	
	BigDecimal getDistrPrice(Long parentId, Long enterpriseId, Long goodsId, List<Long> lotIds, BigDecimal quantity);
	
	ResponseDistrInReturnDetailInfoVO getDistrReturnReqOrderDtlList(UserVO userVO, Long recordId);
	
	BigDecimal getAvgUnitPrice(UserVO userVO, List<DistrInLotNumVO> distrInLotNumVOs);
	
	ResponseDistrInReturnVO getDistrReturnOrderById(Long id);
	
}
