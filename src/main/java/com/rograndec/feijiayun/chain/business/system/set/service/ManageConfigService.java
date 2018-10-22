/**
 * 
 */
package com.rograndec.feijiayun.chain.business.system.set.service;


import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.business.system.set.vo.ValidateCodeRuleVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**

 * @Description:TODO

 * @author:LeiSu

 * @time:2017年8月21日 下午5:47:37

 */
public interface ManageConfigService {
	
	/**  
	 * @Title: getManageConfig  
	 * @Description: 获取管理设置的信息  
	 * @param @param manageConfig
	 * @param @param user
	 * @param @return    设定文件  
	 * @return ManageConfig    返回类型  
	 * @throws  
	 */
	public ManageConfig getManageConfig(UserVO user);

	/**  
	 * @Title: updateManageConfig  
	 * @Description: 修改管理设置的信息  
	 * @param @param manageConfig
	 * @param @param user
	 * @param @return    设定文件  
	 * @return ManageConfig    返回类型  
	 * @throws  
	 */
	public void updateManageConfig(ManageConfig manageConfig, UserVO user) throws Exception;

	public int judgeCodeStyle(Long id);

    ValidateCodeRuleVO judgeCodeRule(Long id, UserVO userVO);
}
