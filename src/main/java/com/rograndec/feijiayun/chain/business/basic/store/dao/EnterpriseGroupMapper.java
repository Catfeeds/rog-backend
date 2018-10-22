package com.rograndec.feijiayun.chain.business.basic.store.dao;

import com.rograndec.feijiayun.chain.business.basic.store.entity.EnterpriseGroup;
import com.rograndec.feijiayun.chain.common.SelectBean;
import com.rograndec.feijiayun.chain.common.SelectBeanWithCode;

import org.apache.commons.beanutils.BeanMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnterpriseGroupMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(EnterpriseGroup record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(EnterpriseGroup record);

    /**
     *
     * @mbg.generated
     */
    EnterpriseGroup selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(EnterpriseGroup record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(EnterpriseGroup record);

    /**
     * @Description: 查询所有分组信息
     * @author liuqun
     * @version 1.0 
     * @date 2017年8月28日 下午7:48:15 
     * @param enterpriseId
     * @return 
     * @return List<SelectBean>
     */
	List<SelectBeanWithCode> selectChildStoreGroupByEnterpriseId(@Param("enterpriseId")Long enterpriseId, @Param("type")Integer type);

	
	/**
     * @Description: 查询所有分组信息
     * @author liuqun
     * @version 1.0 
     * @date 2017年8月28日 下午7:48:15 
     * @param enterpriseId
     * @return 
     * @return List<SelectBean>
     */
	List<EnterpriseGroup> selectStroeGroupTreeByEnterpriseId(Long enterpriseId);

	/**
     * @Description: 查询所有根级分组信息
     * @author liuqun
     * @version 1.0 
     * @date 2017年8月28日 下午7:48:15 
     * @param enterpriseId
     * @return 
     * @return List<SelectBean>
     */
	List<SelectBean> selectRootStoreGroupByEnterpriseId(Long enterpriseId);
	
	/**
     * @Description: 查询store_ids不为空的门店分组信息
     * @author liuqun
     * @version 1.0 
     * @date 2017年8月28日 下午7:48:15 
     * @param enterpriseId
     * @return 
     * @return List<SelectBean>
     */
	List<EnterpriseGroup> selectStroeGroupHasStoreIdsByEnterpriseId(
			Long enterpriseId);

	/**
     * @Description: 查询store_ids为空的门店二级分组信息
     * @author liuqun
     * @version 1.0 
     * @date 2017年8月28日 下午7:48:15 
     * @param enterpriseId
     * @return 
     * @return List<SelectBean>
     */
	List<EnterpriseGroup> selectStroeGroupNoStoreIdsByEnterpriseId(
			Long enterpriseId);


    List<BeanMap> getGroupByEnterpriseId(@Param("enterpriseId") Long enterpriseId,@Param("type")Integer type);

	Long queryCountByParentIdAndCodeName(@Param("enterpriseId") Long enterpriseId, 
			@Param("parentId")Long parentId,
			@Param("code")String code, @Param("name")String name);

}