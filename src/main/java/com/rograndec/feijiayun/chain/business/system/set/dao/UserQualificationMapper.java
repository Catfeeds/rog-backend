package com.rograndec.feijiayun.chain.business.system.set.dao;

import com.rograndec.feijiayun.chain.business.system.set.entity.UserQualification;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserQualificationMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(UserQualification record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(UserQualification record);

    /**
     *
     * @mbg.generated
     */
    UserQualification selectByPrimaryKey(Long id);

    UserQualification selectByDefCode(String code);


	List<UserQualification> selectByIds(List<Long> list);

    List<UserQualification> selectTypeMustByEnterpriseId(Map<String,Object> map);

    List<UserQualification> selectTypeMustByEnterpriseIds(Map<String,Object> map);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(UserQualification record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(UserQualification record);

	/**  
	 * @Title: getUserQualificationById  
	 * @Description: 分部查询员工资质 
	 * @param @param user
	 * @param @return    设定文件  
	 * @return List<UserQualificationController>    返回类型  
	 * @throws  
	 */
	List<UserQualification> getUserQualificationById(UserVO user);

	List<UserQualification> getUserQualificationById4Enable(UserVO user);

	List<UserQualification> getUserMustQualificationById4Enable(UserVO user);

	/**  
	 * @Title: getUserQualification  
	 * @Description: 总部查询员工资质 
	 * @param @param user
	 * @param @return    设定文件  
	 * @return List<UserQualificationController>    返回类型  
	 * @throws  
	 */
	List<UserQualification> getUserQualification(UserVO user);

	List<UserQualification> getUserQualification4Enable(UserVO user);

	List<UserQualification> getUserMustQualification4Enable(UserVO user);

	/**  
	 * @Title: addUserQualification  
	 * @Description: 增加员工资质  
	 * @param @param userQualification    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void addUserQualification(Map<String, Object> map);

	/**  
	 * @Title: addUserQualification  
	 * @Description: 增加员工资质  
	 * @param @param userQualification    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void updateUserQualification(Map<String, Object> map);

    UserQualification hasUserCode(@Param("code")String code, @Param("enterPriseId")Long enterPriseId);

	UserQualification hasUserName(@Param("name")String name, @Param("enterPriseId")Long enterPriseId);

    List<UserQualification> queryDefUserQualificationList();

}