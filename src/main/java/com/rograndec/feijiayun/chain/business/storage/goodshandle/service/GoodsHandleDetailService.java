package com.rograndec.feijiayun.chain.business.storage.goodshandle.service;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsHandleDetailVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsHandleDetailSaveOrupdateVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;



 /**
 * 
 * @ClassName: GoodsHandleDetailService   
 * @Description:  储存管理-商品处理-药品处理明细-接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-09-27 17:27:49
 */
public interface GoodsHandleDetailService {
	
	
	List<GoodsHandleDetailVO> getGoodsHandleDetailData(UserVO userVO) throws Exception;
	
	int save(GoodsHandleDetailSaveOrupdateVO goodsHandleDetail, UserVO userVO) throws Exception;
	
	int update(GoodsHandleDetailSaveOrupdateVO goodsHandleDetail, UserVO userVO) throws Exception;
	
	int delete(Long id) throws Exception;
	

}
