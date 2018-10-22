package com.rograndec.feijiayun.chain.business.storage.lot.service;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.storage.lot.vo.GoodsLotNumberVO;
import com.rograndec.feijiayun.chain.business.storage.lot.vo.LotAdjustVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

/**
 * Created by ST on 2017/9/25.
 * @author 孙帮祥
 */
public interface LotAdjustService {
	/*
	 * 保存批号调整
	 * */
	void saveLotAdjust(UserVO userVO,LotAdjustVO lotAdjustVO) throws Exception,BusinessException;
	
	/**
	 * 获取批号调整详情
	 * */
	LotAdjustVO getLotAdjustById(Long Id);

	/**
	 * 获取批号调整列表
	 * */
	void getLotAdjustList(Page page, Map map);

	/**
	 * 导出excel
	 * */
	void exportExcel(OutputStream output, Long id, UserVO loginUser);
	
	/**
	 * 获取商品列表（以批号表的为主）
	 * */
    List<GoodsLotNumberVO> getGoodsList(Map map);
    
}
