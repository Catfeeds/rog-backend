package com.rograndec.feijiayun.chain.business.report.finance.account.service;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.report.finance.account.vo.AdjustPriceDetail;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.AdjustPriceTotalVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * 
 * @ClassName: AdjustPriceAnalysisService   
 * @Description: 应收/应付调价分析
 * @author dongdong.zhang
 * @version 1.0 
 * @date 2018年1月12日 下午15:33:43
 */
public interface AdjustPriceAnalysisService {
	

	AdjustPriceTotalVO getPayAdjustList(UserVO userVO, Page<AdjustPriceTotalVO> page, Map<String,Object> map, Integer type, Integer sign);
	
	AdjustPriceTotalVO getReceiveAdjustList(UserVO userVO, Page<AdjustPriceTotalVO> page, Map<String,Object> map, Integer type, Integer sign);
	
	List<AdjustPriceDetail> getPayAdjustDetail(UserVO userVO, Long id, Integer type, Integer sign);
	
	List<AdjustPriceDetail> getReceiveAdjustDetail(UserVO userVO, Long id, Integer type, Integer sign);
	
	void excelExport(OutputStream output, UserVO userVO, Map<String,Object> map,Integer type, Integer sign);
}
