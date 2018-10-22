package com.rograndec.feijiayun.chain.business.system.set.dao;

import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import org.apache.ibatis.annotations.Param;


public interface DepartmentMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(Department record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Department record);

    /**
     *
     * @mbg.generated
     */
    Department selectByPrimaryKey(Long id);

    List<Department> selectByIds(List<Long> list);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Department record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Department record);

	/**  
	 * @Title: selectByCurrentUser  
	 * @Description: 查询部门信息 
	 * @param @param user
	 * @param @return    设定文件  
	 * @return List<Department>    返回类型  
	 * @throws  
	 */
	List<Department> selectByCurrentUser(Long enterpriseId);

	List<Department> selectByEnterpriseId2Report(Long enterpriseId);

	/**
	 * @Title: selectByCurrentUser
	 * @Description: 查询部门信息
	 * @param @param enterpriseId
	 * @param @return    设定文件
	 * @return Department    返回类型
	 * @throws
	 */
	List<Department> selectByEnterpriseId(long enterpriseId);
	List<Department> selectByEnterpriseIdAndDefut(long enterpriseId);

	/**
	 * @Title: selectByCurrentUser
	 * @Description: 查询部门信息
	 * @param @param enterpriseId
	 * @param @return    设定文件
	 * @return Department    返回类型
	 * @throws
	 */
	List<Department> selectByEnterpriseIdByHeadquarters(Map param);

	/**  
	 * @Title: updateById  
	 * @Description: 修改部门信息  
	 * @param @param department    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void updateById(Department department);

	/**  
	 * @Title: addDepartMent  
	 * @Description: 增加部门 
	 * @param @param map    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void addDepartMent(Map<String, Object> map);

	/**  
	 * @Title: deleteDepartMent  
	 * @Description: 删除部门信息
	 * @param @param id    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void deleteDepartMent(int id);
	
	/**  
	 * @Title: deleteDepartMent  
	 * @Description: 修改部门信息
	 * @param @param map
	 * @return void    返回类型  
	 * @throws  
	 */
	void updateDepartMentOrganization(Map<String, Object> map);

	/**  
	 * @Title: deleteDepartMent  
	 * @Description: 修改部门信息
	 * @param @param map
	 * @return void    返回类型  
	 * @throws  
	 */
	List<Department> findDepartMent(Long enterpriseId);

	int selectSonDepartment(Long id);


	List<Department> selectByDeptIds(@Param("deptIds")String[] deptIds, @Param("chainType")Integer chainType, @Param("enterpriseId")Long enterpriseId, @Param("parentId")Long parentId);

    Department hasDepartmentName(@Param("name") String name,@Param("enterpriseId") Long enterpriseId);

	Department hasDepartmentCode(@Param("code")String code, @Param("enterpriseId")Long enterpriseId);

    List<Department> selectAllByEnterprise(Long enterpriseId);
}