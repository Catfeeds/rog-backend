package com.rograndec.feijiayun.chain.business.system.set.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.system.set.entity.EnterpriseQualification;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface EnterpriseQualificationMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(EnterpriseQualification record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(EnterpriseQualification record);

    /**
     *
     * @mbg.generated
     */
    EnterpriseQualification selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(EnterpriseQualification record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(EnterpriseQualification record);

    /**
     * 查询企业资质数据
     * @author liuqun
     * @version 1.0 
     * @date 2017年8月23日 上午10:50:00 
     * @param id
     * @param suitableUnit
     * @return 
     * @return List<EnterpriseQualification>
     */
	List<EnterpriseQualification> selectEntrepriseOptionalQualification(@Param("enterpriseId")Long enterpriseId, 
			@Param("suitableUnit")String suitableUnit, @Param("typeMust") String typeMust);

	List<EnterpriseQualification> selectEntrepriseOptionalQualification2WarmSet(@Param("enterpriseId")Long enterpriseId,
			@Param("suitableUnit")String suitableUnit, @Param("typeMust") String typeMust);

	/**  
	 * @Title: getEnterpriseQualificationById  
	 * @Description: 分部通过上级企业ID查询企业资质  
	 * @param @param user
	 * @param @return    设定文件  
	 * @return List<EnterpriseQualification>    返回类型  
	 * @throws  
	 */
	List<EnterpriseQualification> getEnterpriseQualificationById(UserVO user);

	/**  
	 * @Title: getEnterpriseQualification  
	 * @Description: 总部查询自己的企业资质
	 * @param @param user
	 * @param @return    设定文件  
	 * @return List<EnterpriseQualification>    返回类型  
	 * @throws  
	 */
	List<EnterpriseQualification> getEnterpriseQualification(UserVO user);

	List<EnterpriseQualification> getDefaultEnterpriseQulification();

	List<EnterpriseQualification> getEnterpriseQulificationByCodes(List<String> list);

	/**  
	 * @Title: addEnterpriseQualification  
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @param @param enterpriseQualification    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void addEnterpriseQualification(Map<String, Object> map);

	/**  
	 * @Title: addEnterpriseQualification  
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @param @param enterpriseQualification    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void updateEnterpriseQualification(Map<String, Object> map);

	/**  
	 * @Title: addEnterpriseQualification  
	 * @Description: TODO(这里用一句话描述这个方法的作用)  
	 * @param @param enterpriseQualification    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */

	List<EnterpriseQualification> findEnterpriseQualificationSelectorByEnterpriseId(@Param("enterpriseId")Long enterpriseId,@Param("id")Long id);

    EnterpriseQualification hasEnterPriseCode(@Param("code") String code, @Param("enterpriseId") Long enterpriseId);

	EnterpriseQualification hasEnterpriseName(@Param("name") String name, @Param("enterpriseId") Long enterpriseId);

    List<EnterpriseQualification> queryDefEnterpriseQualificationList();

    Long checkDeteleEnterpriseData(Long id);
	Long checkDeteleUserData(Long id);
	Long checkDeteleGoodsData(Long id);
}