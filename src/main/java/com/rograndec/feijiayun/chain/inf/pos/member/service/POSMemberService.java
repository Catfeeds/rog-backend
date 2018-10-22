package com.rograndec.feijiayun.chain.inf.pos.member.service;

import java.util.List;

import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.inf.pos.member.vo.POSSetMemberInfoVO;
import com.rograndec.feijiayun.chain.inf.pos.member.vo.SelectPOSMemberVO;

public interface POSMemberService {
	
	List<SelectPOSMemberVO> searchMember(String param,Long enterpriseId,Long parentId) throws Exception;
	
	String saveMember(POSSetMemberInfoVO infoVO,UserVO loginUser) throws Exception;
	
}
