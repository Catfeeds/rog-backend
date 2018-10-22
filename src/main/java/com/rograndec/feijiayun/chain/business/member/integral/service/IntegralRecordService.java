package com.rograndec.feijiayun.chain.business.member.integral.service;

import com.rograndec.feijiayun.chain.business.member.info.entity.MemberInfo;
import com.rograndec.feijiayun.chain.business.member.info.vo.MemberInfoPageVO;
import com.rograndec.feijiayun.chain.business.member.set.entity.MemberCardType;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

public interface IntegralRecordService {

    Page getMemberInfoPage(Page page, String param, Long cardTypeId, Long cardLevelId, Date startSendCardTime, Date endSendCardTime, Integer chainType, String sendCardStorerCode, String sendCardStorerName, String sort, UserVO userVO);

    List<MemberCardType> getCardType(Long enterpriseId);

    void addOrSubIntegral(String operation, MemberInfoPageVO id, UserVO loginUser) throws Exception;

    List<MemberInfoPageVO> exportMemberIntegral(String param, Long cardTypeId, Long cardLevelId, Date startSendCardTime, Date endSendCardTime, Integer chainType, String sendCardStorerCode, String sendCardStorerName, String sort, UserVO userVO);

    void exportExcel(OutputStream output, List<MemberInfoPageVO> list, UserVO loginUser);

    MemberInfo getMemberInfo(Long id);

    void judgeCardCode(String changeCardCode, UserVO loginUser, Long cardTypeId) throws Exception;
}
