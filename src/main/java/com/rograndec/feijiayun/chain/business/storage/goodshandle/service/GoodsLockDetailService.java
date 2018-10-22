package com.rograndec.feijiayun.chain.business.storage.goodshandle.service;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsLockDetailVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsLockDetailSaveOrupdateVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;



 /**
 * 
 * @ClassName: GoodsLockDetailService   
 * @Description:  储存管理-商品处理-药品锁定明细-接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-09-27 17:26:57
 */
public interface GoodsLockDetailService {
	
	
	List<GoodsLockDetailVO> getGoodsLockDetailData(UserVO userVO) throws Exception;
	
	int save(GoodsLockDetailSaveOrupdateVO goodsLockDetail, UserVO userVO) throws Exception;
	
	int update(GoodsLockDetailSaveOrupdateVO goodsLockDetail, UserVO userVO) throws Exception;
	
	int delete(Long id) throws Exception;
	

}
