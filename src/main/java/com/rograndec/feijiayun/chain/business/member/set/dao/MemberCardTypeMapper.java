package com.rograndec.feijiayun.chain.business.member.set.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.member.info.vo.MadeCardInfoVO;
import com.rograndec.feijiayun.chain.business.member.info.vo.MemberInfoSaveVO;
import com.rograndec.feijiayun.chain.business.member.set.entity.MemberCardType;
import com.rograndec.feijiayun.chain.business.member.set.vo.MemberCardTypeVO;

public interface MemberCardTypeMapper {
	/**
	 *
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 *
	 * @mbg.generated
	 */
	int insert(MemberCardType record);

	/**
	 *
	 * @mbg.generated
	 */
	int insertSelective(MemberCardType record);

	/**
	 *
	 * @mbg.generated
	 */
	MemberCardType selectByPrimaryKey(Long id);

	/**
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(MemberCardType record);

	/**
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKey(MemberCardType record);

	List<MemberCardType> getCardTypeList(@Param("enterpriseId") Long enterpriseId);

    List<MadeCardInfoVO> selectMadeCard(@Param("enterpriseId")Long enterpriseId);

    List<MemberInfoSaveVO> selectDefault(@Param("enterpriseId")Long enterpriseId);

    List<MemberCardType> getCardTypeListByIntegralRecord(Long enterpriseId);

	List<MemberCardTypeVO> selectBySelective(Map<String, Object> param);

	List<MemberCardType> selectByLevelId(@Param("levelId") Long levelId);

	/**
	 * 检查编码和名称是否重复
	 *
     * @param enterpriseId
     * @param id
     *@param code
     * @param name   @return
	 */
    List<MemberCardType> selectByCodeOrName(@Param("enterpriseId") Long enterpriseId,@Param("id") Long id, @Param("code") String code, @Param("name") String name);

    List<MemberCardType> selectByEnterPriseId(Long enterpriseId);
}