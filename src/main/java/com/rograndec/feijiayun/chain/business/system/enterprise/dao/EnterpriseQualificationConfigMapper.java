package com.rograndec.feijiayun.chain.business.system.enterprise.dao;

import java.util.List;

import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseQualificationConfig;

public interface EnterpriseQualificationConfigMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(EnterpriseQualificationConfig record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(EnterpriseQualificationConfig record);

    /**
     *
     * @mbg.generated
     */
    EnterpriseQualificationConfig selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(EnterpriseQualificationConfig record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(EnterpriseQualificationConfig record);

    /**
     * 获取企业资质信息
     * @author liuqun
     * @version 1.0 
     * @date 2017年8月22日 下午8:03:23 
     * @param enterpriseId
     * @return 
     * @return List<EnterpriseQualificationConfig>
     */
	List<EnterpriseQualificationConfig> selectEnterpriseQualificationByEnterpriseId(
			Long enterpriseId);
}