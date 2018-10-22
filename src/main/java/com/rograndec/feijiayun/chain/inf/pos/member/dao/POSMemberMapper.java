package com.rograndec.feijiayun.chain.inf.pos.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.inf.pos.member.vo.SelectPOSMemberVO;

/**
 * 
 * @ClassName: POSMemberMapper   
 * @Description: POS会员相关接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月6日 上午10:55:49
 */
public interface POSMemberMapper {
	
	List<SelectPOSMemberVO> searchMember(@Param("param")String param,@Param("enterpriseId")Long enterpriseId,@Param("parentId")Long parentId);

}
