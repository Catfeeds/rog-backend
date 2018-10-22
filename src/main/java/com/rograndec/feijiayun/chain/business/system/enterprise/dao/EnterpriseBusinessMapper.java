package com.rograndec.feijiayun.chain.business.system.enterprise.dao;

import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;

import java.util.List;
import java.util.Map;

public interface EnterpriseBusinessMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(EnterpriseBusiness record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(EnterpriseBusiness record);

    /**
     *
     * @mbg.generated
     */
    EnterpriseBusiness selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(EnterpriseBusiness record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(EnterpriseBusiness record);

    
    /**
     * 根据企业ID查询企业业务基表
     * @Description: TODO(描述该方法做什么)
     * @author liuqun
     * @version 1.0 
     * @date 2017年8月22日 下午8:42:57 
     * @param enterpriseId
     * @return 
     * @return EnterpriseBusiness
     */
	EnterpriseBusiness queryEnterpriseBusinessByEnterpriseId(Long enterpriseId);
	List<EnterpriseBusiness> queryEnterpriseBusinessByEnterpriseIds(List<Long> list);

    /**
     *
     * @param param
     * @return
     */
    List<EnterpriseBusiness> selectbyParam(Map<String, Object> param);
}