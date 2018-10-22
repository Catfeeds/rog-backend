package com.rograndec.feijiayun.chain.business.member.set.dao;

import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.member.set.entity.MemberCardType;
import com.rograndec.feijiayun.chain.business.member.set.vo.MemberSimpleCardLevelVO;
import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.member.set.entity.MemberCardLevel;
import com.rograndec.feijiayun.chain.business.member.set.vo.MemberCardLevelVO;

public interface MemberCardLevelMapper {
	/**
	 *
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 *
	 * @mbg.generated
	 */
	int insert(MemberCardLevel record);

	/**
	 *
	 * @mbg.generated
	 */
	int insertSelective(MemberCardLevel record);

	/**
	 *
	 * @mbg.generated
	 */
	MemberCardLevel selectByPrimaryKey(Long id);

	/**
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(MemberCardLevel record);

	/**
	 *
	 * @mbg.generated
	 */
	int updateByPrimaryKey(MemberCardLevel record);

	List<MemberCardLevel> getCardLevelList(@Param("enterpriseId") Long enterpriseId);

	List<MemberCardLevelVO> selectBySelective(Map<String, Object> param);

	/**
	 * 获取简单 会员级别
	 * @param enterpriseId
	 * @return
	 */
    List<MemberSimpleCardLevelVO> selectSimpleCardLevel(@Param("enterpriseId") Long enterpriseId);

	/**
	 * 检验编码或名称是否重复
	 *
     * @param enterpriseId
     * @param id
     *@param code
     * @param name   @return
	 */
    List<MemberCardType> selectByCodeOrName(@Param("enterpriseId") Long enterpriseId, @Param("id") Long id, @Param("code") String code, @Param("name") String name);

    List<MemberCardLevel> selectByEnterpriseId(Long enterpriseId);
}