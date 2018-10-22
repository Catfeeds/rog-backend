package com.rograndec.feijiayun.chain.business.basic.user.dao;

import com.rograndec.feijiayun.chain.business.basic.user.entity.EducationMajor;

import java.util.List;
import java.util.Map;

public interface EducationMajorMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(EducationMajor record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(EducationMajor record);

    /**
     *
     * @mbg.generated
     */
    EducationMajor selectByPrimaryKey(Long id);

    List<EducationMajor> selectByenterpriseId(Long enterpriseId);

    List<EducationMajor> selectByenterpriseIdAndType(Map<String,Object> map);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(EducationMajor record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(EducationMajor record);

    String getNameByPrimaryKey(Long id);
}