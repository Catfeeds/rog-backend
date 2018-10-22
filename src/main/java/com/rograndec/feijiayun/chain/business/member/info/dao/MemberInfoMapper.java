package com.rograndec.feijiayun.chain.business.member.info.dao;

import com.rograndec.feijiayun.chain.business.member.info.entity.MemberInfo;
import com.rograndec.feijiayun.chain.business.member.info.vo.*;
import com.rograndec.feijiayun.chain.business.member.storedamount.vo.StoredAmountPageVO;
import com.rograndec.feijiayun.chain.business.member.storedamount.vo.StoredAmountTotalVO;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface MemberInfoMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(MemberInfo record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(MemberInfo record);

    /**
     *
     * @mbg.generated
     */
    MemberInfo selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(MemberInfo record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MemberInfo record);

    List<MemberInfoPageVO> getMemberInfoPage(Map<String, Object> map);

    List<MemberInfo> selectMemberCardExist(@Param("nowCardCode")String nowCardCode, @Param("enterpriseId")Long enterpriseId);

    MemberInfoSaveVO selectIssuingCards(Long id);

    List<MemberInfoPageVO> getIntegralRecordPage(Map<String, Object> map);

    /**
     * 会员管理-储值管理列表
     * @param map
     * @return
     */
    List<StoredAmountPageVO> getStoredAmountPage(Map<String, Object> map);

    /**
     * 会员管理-储值管理列表-合计（累计储值、储值余额）
     * @param map
     * @return
     */
    StoredAmountTotalVO countStoredAmountData(Map<String, Object> map);

    MemberInfo selectNewCardId(@Param("enterpriseId")Long enterpriseId, @Param("changeCardCode")String changeCardCode);

    MemberInfo judgeCardCode(@Param("changeCardCode") String changeCardCode, @Param("enterpriseId") Long enterpriseId, @Param("cardTypeId")Long cardTypeId);

    List<MemberInfo> selectByCardTypeId(@Param("cardTypeId") Long cardTypeId);

    StoredAmountPageVO selectMemberByCardCode(@Param("cardCode")String cardCode);

    StoredAmountPageVO selectCurrentStoredAmount(Long id);

    MemberInfoSaveVO selectIssuingCardsById(@Param("id") Long id, @Param("enterpriseId") Long enterpriseId);

    /**
     * 处方登记是查询会员信息
     * @param enterpriseId
     * @param param
     * @return
     */
    List<MemberForPrescVO> getMemberInfoForPresc(@Param("enterpriseId")Long enterpriseId, @Param("param")String param);

    /**
     * 积分兑换查询简单会员信息
     * @param enterpriseId
     * @param param
     * @return
     */
    List<SimpleMemberInfoVO> getSimpleMemberInfo(@Param("enterpriseId")Long enterpriseId, @Param("param")String param);

    List<MemberInfoSaveVO> getMemberInfoExport(Map<String, Object> map);

    BigDecimal selectRecordTotalIntegral(Map<String, Object> map);

    BigDecimal selectRecordCurrentIntegral(Map<String, Object> map);

    List<MemberInfo> selectByEnterpriseId(Long enterpriseId);

    MemberForPrescVO getMemberForPresc(@Param("enterpriseId")Long enterpriseId,@Param("id")Long id);

	List<MemberInfo> selectByEnterpriseIdAndCode(@Param("enterpriseId")Long enterpriseId,
			@Param("patientId")String patientId, @Param("parentId")Long parentId);

    MemberInfo selectMemberCardStatus(@Param("changeCardCode") String changeCardCode,@Param("enterpriseId") Long enterpriseId);

    MemberInfo selectMemberCardType(@Param("changeCardCode") String changeCardCode, @Param("cardTypeId") Long cardTypeId, @Param("enterpriseId") Long enterpriseId);

    MemberInfoStasticVO selectStasticTotal(Map<String, Object> map);

    Integer getTotalRecord(Map<String, Object> map);

    Integer getIntegralRecordTotal(Map<String, Object> map);

    List<MemberInfo> selectByStoreEnterPriseId(Long enterpriseId);

    List<Long> listEnterpriseIds(Long id);
}