package com.rograndec.feijiayun.chain.business.system.set.dao;

import java.util.Map;

import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface ManageConfigMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(ManageConfig record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(ManageConfig record);

    /**
     *
     * @mbg.generated
     */
    ManageConfig selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ManageConfig record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ManageConfig record);
    
    /**  
	 * @Title: selectByCurrentUser  
	 * @Description: 查看管理设置的信息
	 * @param @param user
	 * @param @return    设定文件  
	 * @return ManageConfig    返回类型  
	 * @throws  
	 */
    ManageConfig selectByCurrentUser(UserVO user);


	/**
	 * @Title: updateDivision
	 * @Description: 修改分部信息
	 * @param @param map    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	ManageConfig updateByCurrentUser(int chainType, long parentId, long enterpriseId);

	/**
	 * @Description: 
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年8月23日 下午3:32:10 
	 * @param enterpriseId 
	 * @return void
	 */
	ManageConfig selectManageConfigByEnterpriseId(Long enterpriseId);

	/**
	 * @Title: getMangeConfigByEPId
	 * @Description: 根据企业id 查询企业配置信息
	 * @param enterpriseId
	 * @return
     */
	ManageConfig getMangeConfigByEPId(Long enterpriseId);

	int judgeCodeStyle(Long id);


    void updateDivisionByConfig(ManageConfig manageConfig);
}