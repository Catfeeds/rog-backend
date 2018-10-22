package com.rograndec.feijiayun.chain.business.goods.set.service;

import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsUnit;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsUnitVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;

/**
 * Created by zeshi.sun on 2017/9/2.
 */
public interface SetUnitService {

	List<GoodsUnitVO> getSetUnit(Long enterpriseId, Integer status) throws Exception;

	Result<String> insertSetUnit(UserVO loginUser, GoodsUnit goodsUnit) throws Exception;

	int updateSetUnit(UserVO loginUser, GoodsUnit goodsUnit) throws Exception;

	boolean canDelete(Long id) throws Exception;

	int deleteSetUnit(Long id) throws Exception;

    /**
     * 获取企业下所有可用的单位（用于：储存管理-拆零设置获取拆零单位列表）
     * @param enterpriseId 企业id
     * @return 单位列表
     */
    List<GoodsUnitVO> getUsableUnit(Long enterpriseId);
}
