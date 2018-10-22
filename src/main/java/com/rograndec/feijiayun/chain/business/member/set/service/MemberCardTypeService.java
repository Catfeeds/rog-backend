package com.rograndec.feijiayun.chain.business.member.set.service;

import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.member.set.vo.MemberCardTypeVO;
import com.rograndec.feijiayun.chain.business.member.set.vo.SimpleStoreVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface MemberCardTypeService {

	List<MemberCardTypeVO> selectCardType(Map<String, Object> param, UserVO userVO);

	Result<String> save(MemberCardTypeVO cardTypeVO, UserVO loginUser);

	void update(MemberCardTypeVO cardTypeVO, UserVO loginUser);

	Result<String> delete(Long id);

	Page<List<SimpleStoreVO>> simpleSelectStoreVOPage(Map<String, Object> param, Page<List<SimpleStoreVO>> page);

	List<SimpleStoreVO> getSimpleStoreVOsByIds(String ids);

}
