package com.rograndec.feijiayun.chain.business.member.set.service;

import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.member.set.vo.MemberCardLevelVO;
import com.rograndec.feijiayun.chain.business.member.set.vo.MemberSimpleCardLevelVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface MemberCardLevelService {

	/**
	 * 根据参数 查询 会员卡 列表
	 * 
	 * @param param
	 * @return
	 */
	List<MemberCardLevelVO> selectCardLevel(Map<String, Object> param, UserVO loginUser);

	Result<String> save(MemberCardLevelVO cardLevelVO, UserVO loginUser);

	void update(MemberCardLevelVO cardLevelVO, UserVO loginUser);

	Result<String> delete(Long id);

	boolean canDelete(Long id);

	/**
	 * 获取简单 会员卡级别
	 * @param eId
	 * @return
	 */
    List<MemberSimpleCardLevelVO> selectSimpleCardLevel(UserVO eId);
}
