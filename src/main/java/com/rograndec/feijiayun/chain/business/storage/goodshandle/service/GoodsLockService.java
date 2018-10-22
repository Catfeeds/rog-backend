package com.rograndec.feijiayun.chain.business.storage.goodshandle.service;

import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SelectPricingGoodsVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsLock;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsLockSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsLockVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.RequestGoodsLockListVo;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.GoodsParamStockVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;



 /**
 * 
 * @ClassName: GoodsLockService   
 * @Description:  储存管理-商品处理-药品锁定-接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-09-27 17:26:40
 */
public interface GoodsLockService {
	
	
	GoodsLock getGoodsLockData(Long id) throws Exception;
	
	int save(GoodsLockSaveOrupdateVO goodsLock, UserVO userVO) throws Exception;
	
	int update(GoodsLockSaveOrupdateVO goodsLock, UserVO userVO) throws Exception;
	
	int delete(Long id) throws Exception;

	/**
	 *
	 * <根据条件查询商品锁定单数据>
	 * @Author: Zhengbin.jin 金正斌
	 * @Email: Zhengbin.jin@rograndec.com
	 * @2017/9/27 20:15
	 */
	void getGoodsLockDateByParam(RequestGoodsLockListVo requestGoodsLockListVo, Page page);
	/**
	 *
	 * <取消锁定单>
	 * @Author: Zhengbin.jin 金正斌
	 * @Email: Zhengbin.jin@rograndec.com
	 * @param flag  当flag为true时生成解除通知单 解除通知调用此方法请传false
	 * @2017/9/29 18:15
	 */
    int cancelGoodsLock(UserVO userVO, Long lockId,boolean flag);
	 /**
	  *
	  * <导出商品锁定单>
	  * @Author: Zhengbin.jin 金正斌
	  * @Email: Zhengbin.jin@rograndec.com
	  * @2017/9/30 10:20
	  */
	 void exportExcel(GoodsLock goodsLock, UserVO userVO, OutputStream output, String name);

	 /**
	  * 查询商品
	  * @param goodsParamStockVO
	  * @return
	  */
     List<SelectPricingGoodsVO> selectGoodsByParam(GoodsParamStockVO goodsParamStockVO);
 }
