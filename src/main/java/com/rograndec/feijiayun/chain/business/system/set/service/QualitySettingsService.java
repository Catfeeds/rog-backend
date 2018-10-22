/**
 * 
 */
package com.rograndec.feijiayun.chain.business.system.set.service;

import java.util.List;

import com.rograndec.feijiayun.chain.business.system.set.entity.QualitySet;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**

 * @Description:TODO

 * @author:LeiSu

 * @time:2017年8月21日 下午6:13:21

 */
public interface QualitySettingsService {

    List<QualitySet> getQualitySettings(UserVO user, Integer setType, Integer type);

    /**
	 * @Title: getQualitySettings  
	 * @Description: 查询所有质量设置拒收原因
	 * @param @param user
	 * @param @return    设定文件  
	 * @return List<QualitySet>    返回类型  
	 * @throws  
	 */
	List<QualitySet> getQualitySettings(UserVO user,Integer id);

	/**  
	 * @Title: updateQualitySettings  
	 * @Description: 修改拒收原因
	 * @param @param qualitySet    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void updateQualitySettings(QualitySet qualitySet,UserVO user) throws Exception;

	/**  
	 * @Title: addQualitySettings  
	 * @Description: 增加拒收原因 
	 * @param @param qualitySet    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void addQualitySettings(QualitySet qualitySet,UserVO user) throws Exception;

	/**  
	 * @Title: deleteQualitySettings  
	 * @Description: 删除拒收原因 
	 * @param @param id    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void deleteQualitySettings(Long id);

	List<QualitySet> selectCheckProductSelector(UserVO user);
}
