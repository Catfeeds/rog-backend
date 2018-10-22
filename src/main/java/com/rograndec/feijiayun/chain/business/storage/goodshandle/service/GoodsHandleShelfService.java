package com.rograndec.feijiayun.chain.business.storage.goodshandle.service;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsHandleShelfVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsHandleShelfSaveOrupdateVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;



 /**
 * 
 * @ClassName: GoodsHandleShelfService   
 * @Description:  储存管理-商品处理-药品处理货位明细-接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-09-27 17:28:01
 */
public interface GoodsHandleShelfService {
	
	
	List<GoodsHandleShelfVO> getGoodsHandleShelfData(UserVO userVO) throws Exception;
	
	int save(GoodsHandleShelfSaveOrupdateVO goodsHandleShelf, UserVO userVO) throws Exception;
	
	int update(GoodsHandleShelfSaveOrupdateVO goodsHandleShelf, UserVO userVO) throws Exception;
	
	int delete(Long id) throws Exception;
	

}
