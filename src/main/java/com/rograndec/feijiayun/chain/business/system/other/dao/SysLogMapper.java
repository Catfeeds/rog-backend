package com.rograndec.feijiayun.chain.business.system.other.dao;

import com.rograndec.feijiayun.chain.business.system.other.entity.SysLog;
import com.rograndec.feijiayun.chain.business.system.other.vo.SysLogParamVO;

import java.util.List;

public interface SysLogMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SysLog record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SysLog record);

    /**
     *
     * @mbg.generated
     */
    SysLog selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SysLog record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SysLog record);

    List<SysLog> getSysLogListByParam(SysLogParamVO sysLogParamVO);
}