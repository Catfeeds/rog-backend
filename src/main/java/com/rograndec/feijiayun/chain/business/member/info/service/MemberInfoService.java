package com.rograndec.feijiayun.chain.business.member.info.service;

import com.rograndec.feijiayun.chain.business.member.info.vo.MadeCardInfoVO;
import com.rograndec.feijiayun.chain.business.member.info.vo.MemberInfoSaveVO;
import com.rograndec.feijiayun.chain.business.member.info.vo.ResponseMemberExcelVO;
import com.rograndec.feijiayun.chain.business.member.set.entity.MemberCardLevel;
import com.rograndec.feijiayun.chain.business.member.set.entity.MemberCardType;
import com.rograndec.feijiayun.chain.business.member.storedamount.vo.StoredAmountPageVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface MemberInfoService {

    Page getMemberInfoPage(Page page, String param, Long cardTypeId, Long cardLevelId, Date startSendCardTime, Date endSendCardTime, Integer chainType, String sendCardStorerCode, String sendCardStorerName, String sendCardManName, Integer status, UserVO enterpriseId, String sort);

    List<MemberCardType> getCardType(UserVO userVO);

    List<MemberCardLevel> getCardLevel(UserVO userVO);

    int delete(Long id);

    List<MadeCardInfoVO> selectMadeCard(Long enterpriseId);

    String saveMadeCard(MadeCardInfoVO madeCardInfoVO, UserVO loginUser) throws Exception;

    void updateOrAddMember(MemberInfoSaveVO memberInfoSaveVO, UserVO loginUser) throws Exception;

    List<MemberInfoSaveVO> selectDefault(UserVO enterpriseId);

    MemberInfoSaveVO selectIssuingCards(Long id, UserVO user);

    void saveIssuingCards(MemberInfoSaveVO memberInfoSaveVO, UserVO loginUser) throws Exception;

    int selectMemberCardExist(UserVO loginUser, String cardCode);

    void reportedLoss(Long id, UserVO userVO) throws Exception;

    void writeOff(Long id, UserVO userVO) throws Exception;

    void solutionsHanging(Long id, UserVO userVO) throws Exception;

    /**
     * 通过会员卡号查询会员信息
     * @param cardCode 卡号
     * @return
     */
    StoredAmountPageVO selectMemberByCardCode(String cardCode);

    /**
     * 通过会员卡id查询当前所有信息
     * @param id 会员卡id
     * @return
     */
    StoredAmountPageVO selectCurrentStoredAmount(Long id);

    ResponseMemberExcelVO excelImport(HttpServletRequest request) throws Exception;

    MemberInfoSaveVO selectIssuingCardsById(Long id, UserVO loginUser);

    MemberInfoSaveVO selectDefaultUpdate(UserVO loginUser, Long id);

    void exportGoods(OutputStream output, String key, Integer type) throws Exception;

    void importSuccessMember(UserVO userVO, String key) throws Exception;

    List<MemberInfoSaveVO> exportMemberInfo(String param, Long cardTypeId, Long cardLevelId, Date startSendCardTime, Date endSendCardTime, Integer chainType, String sendCardStorerCode, String sendCardStorerName, String sendCardManName, UserVO userVO, Integer status, String sort);

    void exportExcel(OutputStream output, List<MemberInfoSaveVO> list, UserVO loginUser);

    /*Double checkSchedule(UserVO userVO, String key);

    void saveAsync(UserVO userVO,String key);*/
}
