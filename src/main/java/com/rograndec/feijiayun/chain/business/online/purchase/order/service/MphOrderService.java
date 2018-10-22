package com.rograndec.feijiayun.chain.business.online.purchase.order.service;

import java.util.List;

import com.rograndec.feijiayun.chain.business.online.purchase.order.vo.MphOrderVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface MphOrderService {
	
	/**
	 * 
	 * @Description: 获取mph订单数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年11月23日 下午2:02:53 
	 * @param pageNo
	 * @param pageSize
	 * @param search
	 * @return 
	 * @return Page<List<MphOrderVO>>
	 */
	Page<List<MphOrderVO>> getMphOrder(Integer pageNo,Integer pageSize,String search,UserVO userVO);

    void reBuy(UserVO userVO, String mphResult) throws Exception;
}
