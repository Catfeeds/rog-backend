package com.rograndec.feijiayun.chain.business.system.enterprise.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseModifyRecord;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseModifyRecordWithBLOBs;

public interface EnterpriseModifyRecordMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(EnterpriseModifyRecordWithBLOBs record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(EnterpriseModifyRecordWithBLOBs record);

    /**
     *
     * @mbg.generated
     */
    EnterpriseModifyRecordWithBLOBs selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(EnterpriseModifyRecordWithBLOBs record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(EnterpriseModifyRecordWithBLOBs record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(EnterpriseModifyRecord record);

    /**
     * 查询药店修改记录总数
     * @author liuqun
     * @version 1.0 
     * @date 2017年8月23日 下午5:54:19 
     * @param enterpriseId
     * @return 
     * @return Long
     */
	Long queryCountByEnterpriseId(Long enterpriseId);

	/**
	 * 分页查询记录数
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年8月23日 下午5:55:32 
	 * @param enterpriseId
	 * @param pageNo
	 * @param pageSize
	 * @return 
	 * @return List<EnterpriseModifyRecordWithBLOBs>
	 */
	List<EnterpriseModifyRecordWithBLOBs> selectEnterpriseModifyRecord(
			@Param("entrepriseId")Long entrepriseId, @Param("start")int start, @Param("pageSize")int pageSize);
}