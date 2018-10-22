package com.rograndec.feijiayun.chain.business.goods.manage.service;

import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.goods.manage.vo.GoodsStoreDetailVO;
import com.rograndec.feijiayun.chain.business.goods.manage.vo.GoodsStorePageVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface GoodsManageStoreService {

	List<GoodsStorePageVO> selectGoodsManagePage(int pageNo, int pageSize,
			Long enterpriseId, Integer type, String key, String order, String sort, Page page);

	String saveGoodsStoreDetail(GoodsStoreDetailVO vo, UserVO loginUser)throws Exception;

	Map<String, String> batchOnShelfByIds(String[] ids, UserVO loginUser) throws Exception;

	Map<String, String> batchOffShelfByIds(String[] ids, UserVO loginUser);


}
