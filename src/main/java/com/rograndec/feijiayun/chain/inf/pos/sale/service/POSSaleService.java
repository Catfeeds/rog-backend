package com.rograndec.feijiayun.chain.inf.pos.sale.service;


import java.util.List;

import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSSaleVO;

public interface POSSaleService {

	String saveSaleData(List<POSSaleVO> saleVOList, UserVO userVO)throws Exception;

}
