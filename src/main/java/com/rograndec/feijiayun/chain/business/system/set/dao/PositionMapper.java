package com.rograndec.feijiayun.chain.business.system.set.dao;

import com.rograndec.feijiayun.chain.business.system.set.entity.Position;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PositionMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(Position record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Position record);

    /**
     *
     * @mbg.generated
     */
    Position selectByPrimaryKey(Long id);

	/**
	 * in 查询
	 * @param list
	 * @return
	 */
	List<Position> selectByIds(List<Long> list);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Position record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Position record);

	/**  
	 * @Title: selectByCurrentUser  
	 * @Description: 查询岗位信息  
	 * @param @param chainType
	 * @param @param parentId
	 * @param @param enterpriseId
	 * @param @return    设定文件  
	 * @return Position    返回类型  
	 * @throws  
	 */
    List<Position> selectByCurrentUser(Long enterpriseId);

    List<Position> selectByEnterpriseId2Report(Long user);

	/**  
	 * @Title: updatePosition  
	 * @Description: 修改岗位信息 
	 * @param @param position    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void updatePosition(Position position);

	/**  
	 * @Title: addPosition  
	 * @Description: 添加岗位
	 * @param @param position    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void addPosition(Position position);

	/**  
	 * @Title: deletePositionById  
	 * @Description: 删除岗位 
	 * @param @param id    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void deletePositionById(Long id);

	List<Position> selectByEnterpriseIdByDeptId(Map<String, Long> param);

	int selectPositionByDepartmentId(Long id);

	List<Position> selectPositionsByDepartmentId(Long deptId);

	List<Position> selectPositionsByDepartments(@Param("list") List<Long> list,@Param("enterpriseId")Long enterpriseId);

	List<Position> selectPositionsByDepartment(Long deptId);

	void updatePositionOrganization(Map<String, Object> map);

	void addPosition(Map<String, Object> map);

	Long getDepartmentIdByUserAndPositionId(Long id, Long enterpriseId);

	int getRole(Long enterpriseId, Long departmentId, Long id);


	List<Position> selectByDeptIds(@Param("deptIds")String[] deptIds, @Param("chainType")Integer chainType, @Param("enterpriseId")Long enterpriseId, @Param("parentId")Long parentId);

    Position hasPositionName(@Param("name") String name,@Param("enterpriseId") Long enterpriseId);

	Position hasPositionCode(@Param("code") String code,@Param("enterpriseId") Long enterpriseId);

    List<Position> selectPositionByEnterPriseIdAndDepartmentId(@Param("enterpriseId") Long enterpriseId,@Param("id") Long id);
}