package com.rograndec.feijiayun.chain.inf.pos.sale.service;

import java.util.List;

import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSReturnSaleVO;

public interface POSReturnSaleService {

	String saveReturnSaleData(List<POSReturnSaleVO> returnSaleVOList,
			UserVO userVO)throws Exception;

}
