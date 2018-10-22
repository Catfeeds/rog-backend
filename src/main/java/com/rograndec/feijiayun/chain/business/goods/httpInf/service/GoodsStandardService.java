package com.rograndec.feijiayun.chain.business.goods.httpInf.service;

import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.goods.httpInf.vo.GoodsExaminationVO;
import com.rograndec.feijiayun.chain.business.goods.httpInf.vo.GoodsPharmacyVO;
import com.rograndec.feijiayun.chain.business.goods.httpInf.vo.GoosMPHVO;
import com.rograndec.feijiayun.chain.business.goods.httpInf.vo.SupplierMPHVO;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.GoodsDictionaryVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.exception.BusinessException;

/**
 * 
 * @ClassName: GoodsStandardServiceImpl   
 * @Description: 根据标准库id获取商品信息
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月17日 下午7:57:07
 */
public interface GoodsStandardService {
	
	/**
	 * 
	 * @Description: 说明书、指导单
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月18日 下午8:45:52 
	 * @param nrId
	 * @return 
	 * @return GoodsPharmacyVO
	 */
	GoodsPharmacyVO goodsSpecifications(String nrId);
	/**
	 * 
	 * @Description: 用药检查
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月18日 上午11:09:46 
	 * @param nrIds
	 * @return 
	 * @return List<GoodsExaminationVO>
	 */
	List<GoodsExaminationVO> medicationExamination(List<String> nrIds);
	
	/**
	 * 
	 * @Description: TODO(描述该方法做什么)
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月20日 下午4:51:26 
	 * @param symptom
	 * @return 
	 * @return List<GoodsDictionaryVO>
	 */
	Page<List<GoodsDictionaryVO>> searchGoodsBySymptom(String symptom,Long pageNo);
	
	
	/**
	 * 
	 * @Description: 商品从标准库检索
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月28日 下午4:05:40 
	 * @param param
	 * @return 
	 * @return Page<List<GoosMPHVO>>
	 */
	Page<List<GoosMPHVO>> searchGoodsMPH(Map<String,String> param) throws BusinessException;
	
	
	/**
	 * 
	 * @Description: 供应商从标准库检索
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年11月1日 下午3:50:34 
	 * @param param
	 * @return
	 * @throws BusinessException 
	 * @return Page<List<SupplierMPHVO>>
	 */
	Page<List<SupplierMPHVO>> searchSupplierMPH(Map<String,String> param) throws BusinessException;
	
}
