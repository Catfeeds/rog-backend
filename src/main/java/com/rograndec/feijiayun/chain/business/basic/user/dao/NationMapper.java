package com.rograndec.feijiayun.chain.business.basic.user.dao;

import com.rograndec.feijiayun.chain.business.basic.user.entity.Nation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NationMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(Nation record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Nation record);

    /**
     *
     * @mbg.generated
     */
    Nation selectByPrimaryKey(Long id);

    Nation selectByCode(@Param("code") String code);

    List<Nation> selectAll();

    String getNameByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Nation record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Nation record);
}