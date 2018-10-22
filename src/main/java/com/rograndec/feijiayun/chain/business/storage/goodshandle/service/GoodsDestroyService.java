package com.rograndec.feijiayun.chain.business.storage.goodshandle.service;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsDestroy;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsDestroyRVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsDestroyVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.StockDestroyVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.UserDestroyVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

/**
 * Created by ST on 2017/9/25.
 * @author 孙帮祥
 */
public interface GoodsDestroyService {
	
    List<UserDestroyVO> getUserList(Map map);
	
	List<GoodsDestroyVO> getGoodsList(Map map);
	
	void saveGoodsDestroy(UserVO userVO,GoodsDestroyRVO goodsDestroyRVO) throws Exception;

	void updateGoodsDestroy(UserVO userVO, GoodsDestroyRVO goodsDestroyVO) throws Exception, BusinessException;
	
	GoodsDestroyRVO getGoodsDestroyById(Long Id);

	void getGoodsDestroyList(Page page, Map map);

	List<StockDestroyVO> getStockList(Map map);

	void exportExcel(OutputStream output, Long id, UserVO loginUser);

	void changeStatus(UserVO userVO, GoodsDestroy destroy) throws Exception;
}
