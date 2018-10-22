package com.rograndec.feijiayun.chain.inf.pos.goods.service;

import java.util.List;

import com.rograndec.feijiayun.chain.inf.pos.goods.vo.SelectPOSGoodsLotNumVO;
import com.rograndec.feijiayun.chain.inf.pos.goods.vo.SelectPOSGoodsParamVO;
import com.rograndec.feijiayun.chain.inf.pos.goods.vo.SelectPOSGoodsVO;

public interface POSGoodsService {
	
	/**
	 * 
	 * @Description: 获取分页数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年9月30日 下午3:05:03 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return Page<SelectPOSGoodsVO>
	 */
	List<SelectPOSGoodsVO> selectGoods(SelectPOSGoodsParamVO param) throws Exception;
	
	/**
	 * 
	 * @Description: 选择商品批号
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月6日 下午3:42:49 
	 * @param enterpriseId
	 * @param goodsId
	 * @return
	 * @throws Exception 
	 * @return List<SelectPOSGoodsLotNumVO>
	 */
	List<SelectPOSGoodsLotNumVO> selectGoodsLotNum(Long enterpriseId,Long goodsId,int usableQuantity) throws Exception;
	
	/**
	 * 
	 * @Description: 选择中药接口
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月9日 下午4:23:43 
	 * @param param
	 * @return
	 * @throws Exception 
	 * @return List<SelectPOSGoodsVO>
	 */
	List<SelectPOSGoodsVO> selectGoodsAttribute(SelectPOSGoodsParamVO param) throws Exception;
	
}
