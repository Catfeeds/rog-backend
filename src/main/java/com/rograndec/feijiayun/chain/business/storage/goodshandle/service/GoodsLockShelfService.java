package com.rograndec.feijiayun.chain.business.storage.goodshandle.service;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsLockShelfSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsLockShelfVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsLockShelfSaveOrupdateVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;



 /**
 * 
 * @ClassName: GoodsLockShelfService   
 * @Description:  储存管理-商品处理-药品锁定货位明细-接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-09-27 17:27:10
 */
public interface GoodsLockShelfService {
	
	
	List<GoodsLockShelfVO> getGoodsLockShelfData(UserVO userVO) throws Exception;
	
	int save(GoodsLockShelfSaveOrupdateVO goodsLockShelf, UserVO userVO) throws Exception;
	
	int update(GoodsLockShelfSaveOrupdateVO goodsLockShelf, UserVO userVO) throws Exception;
	
	int delete(Long id) throws Exception;
	

}
