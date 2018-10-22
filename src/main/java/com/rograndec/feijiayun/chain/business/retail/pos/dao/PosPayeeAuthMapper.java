package com.rograndec.feijiayun.chain.business.retail.pos.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosPayeeAuth;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayeeAuthVO;

public interface PosPayeeAuthMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(PosPayeeAuth record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(PosPayeeAuth record);

    /**
     *
     * @mbg.generated
     */
    PosPayeeAuth selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PosPayeeAuth record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PosPayeeAuth record);
    
    
    List<PosPayeeAuthVO> findByEnterpriseId(@Param("enterpriseId")Long enterpriseId);
    
    /**
     *根据用户id删除收款员信息
     */
    int deleteByUserId(@Param("id") Long id);
}