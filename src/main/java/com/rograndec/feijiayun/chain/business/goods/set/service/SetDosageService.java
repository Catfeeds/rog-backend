package com.rograndec.feijiayun.chain.business.goods.set.service;

import java.util.List;

import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsDosage;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsDosageVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * Created by zeshi.sun on 2017/9/5.
 */
public interface SetDosageService {

	List<GoodsDosageVO> getSetDosage(Long enterpriseId, Integer status) throws Exception;

	Result<String> insertSetDosage(UserVO loginUser, GoodsDosage goodsDosage) throws Exception;

	int updateSetDosage(UserVO loginUser, GoodsDosage goodsDosage) throws Exception;

	boolean canDelete(Long id) throws Exception;

	int deleteSetDosage(Long id) throws Exception;

}
