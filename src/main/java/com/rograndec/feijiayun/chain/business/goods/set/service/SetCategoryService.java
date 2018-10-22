package com.rograndec.feijiayun.chain.business.goods.set.service;

import java.util.List;

import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsCategory;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsCategoryVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * Created by zeshi.sun on 2017/9/5.
 */
public interface SetCategoryService {

	Result<String> insertSetClassify(GoodsCategory goodsCategory, UserVO loginUser) throws Exception;

	int updateSetClassify(GoodsCategory goodsCategory, UserVO loginUser) throws Exception;

	int deleteSetClassify(Long id) throws Exception;

	boolean canDelete(Long id,Long enterpriseId) throws Exception;

	/**
	 * 会带有系统默认的,只用作查询
	 * 
	 * @param enterpriseId
	 * @return
	 * @throws Exception
	 */
	List<TreePOJO<GoodsCategoryVO>> getClassify(Long enterpriseId, Boolean maintain) throws Exception;

	List<GoodsCategory> getCategory(Long enterpriseId);

}
