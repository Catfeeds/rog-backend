package com.rograndec.feijiayun.chain.business.system.other.dao;

import com.rograndec.feijiayun.chain.business.system.other.entity.DataBackup;
import com.rograndec.feijiayun.chain.business.system.other.vo.DataBackupVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataBackupMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(DataBackup record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(DataBackup record);

    /**
     *
     * @mbg.generated
     */
    DataBackup selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(DataBackup record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(DataBackup record);

    List<DataBackupVO> getDataBackUp(@Param("enterpriseId")Long enterpriseId, @Param("parentId") Long parentId);
}