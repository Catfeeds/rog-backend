package com.rograndec.feijiayun.chain.business.member.storedamount.service;

import com.rograndec.feijiayun.chain.business.member.storedamount.vo.RequestStoredAmountVO;
import com.rograndec.feijiayun.chain.business.member.storedamount.vo.StoredAmountPageVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/9/22 <br/>
 * 描述：会员管理-储值管理
 */
public interface StoredAmountService {
    /**
     * 储值管理列表
     * @param page 分页信息
     * @param param
     * @param cardTypeId 会员卡类型
     * @param cardLevelId 会员卡级别
     * @param startSendCardTime 搜索发卡起始时间
     * @param endSendCardTime 搜索发卡结束时间
     * @param chainType 发卡门店类型
     * @param sendCardStorerCode 发卡门店编码
     * @param sendCardStorerName 发卡门店名称
     * @param enterpriseId 企业ID
     * @return 储值信息列表
     */
    Page getMemberInfoPage(Page page, String param, Long cardTypeId, Long cardLevelId, Date startSendCardTime, Date endSendCardTime, Integer chainType, String sendCardStorerCode, String sendCardStorerName, UserVO loginUser);

    /**
     * 待导出的数据
     * @param param
     * @param cardTypeId
     * @param cardLevelId
     * @param startSendCardTime
     * @param endSendCardTime
     * @param chainType
     * @param sendCardStorerCode
     * @param sendCardStorerName
     * @param enterpriseId
     * @return
     */
    List<StoredAmountPageVO> exportStoredAmount(String param, Long cardTypeId, Long cardLevelId, Date startSendCardTime, Date endSendCardTime, Integer chainType, String sendCardStorerCode, String sendCardStorerName, Long enterpriseId);

    void exportExcel(OutputStream output, List<StoredAmountPageVO> list, UserVO loginUser);

    /**
     * 储值记录
     * @param loginUser 用户信息
     * @param requestStoredAmountVO
     * @return
     */
    int changeStoredAmount(UserVO loginUser, RequestStoredAmountVO requestStoredAmountVO) throws Exception;
}
