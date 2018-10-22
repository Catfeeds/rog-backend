/**
 * 
 */
package com.rograndec.feijiayun.chain.business.system.set.service;

import com.rograndec.feijiayun.chain.business.system.set.entity.ConnectTree;
import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import com.rograndec.feijiayun.chain.business.system.set.entity.Position;
import com.rograndec.feijiayun.chain.business.system.set.entity.Tree;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

/**

 * @Description:TODO

 * @author:LeiSu

 * @time:2017年8月21日 下午5:50:15

 */
public interface OrganizationService {

	/**  
	 * @Title: getDepartMentOrganization  
	 * @Description: 获得查看当前部门信息
	 * @param @param department
	 * @param @param user
	 * @param @return    设定文件  
	 * @return List<Department>    返回类型  
	 * @throws  
	 */
	List<Department> getDepartMentOrganization(UserVO user);

	/**  
	 * @Title: updateDepartMentOrganization  
	 * @Description: 修改部门信息
	 * @param @param department
	 * @param @param user
	 * @param @return    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void updateDepartMentOrganization(Department department,UserVO user) throws Exception;

	/**  
	 * @Title: addDepartMentOrganization  
	 * @Description: 增加部门信息
	 * @param @param department
	 * @param @param user
	 * @param @return    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void addDepartMentOrganization(Department department,UserVO user) throws Exception;

	/**  
	 * @Title: deleteDepartMentOrganization  
	 * @Description: 删除部门信息 
	 * @param @param id    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	int deleteDepartMentOrganization(Long id,UserVO user) throws Exception;

	/**  
	 * @Title: getPositionOrganization  
	 * @Description: 查看岗位信息 
	 * @param @param user
	 * @param @return    设定文件  
	 * @return Position    返回类型  
	 * @throws  
	 */
	List<Position> getPositionOrganization(UserVO user);

    List<Position> selectByEnterpriseId2Report(Long eId);

    /**
	 * @Title: updatePositionOrganization  
	 * @Description: 修改岗位信息 
	 * @param @param position    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void updatePositionOrganization(Position position,UserVO user) throws Exception;

	/**  
	 * @Title: addPositionOrganization  
	 * @Description: 增加岗位信息
	 * @param @param position    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	void addPositionOrganization(Position position,UserVO user) throws Exception;

	/**
	 * @return 
	 * @param user   
	 * @Title: deletePositionOrganization  
	 * @Description: 删除岗位信息
	 * @param @param id    设定文件  
	 * @return void    返回类型  
	 * @throws  
	 */
	int deletePositionOrganization(Long id, UserVO user) throws Exception;



    List<Position> getPositions4DeptIdAndEnterpriseId(Long deptId);

    List<Position> getPositions4DeptIdAndEnterpriseIds(List<Long> depIds, Long enterpriseId);

    List<Tree> structurePositionTree(List<Tree> departMentTree, List<Position> position, UserVO userVO);

    List<Department> getDepartMentOrganizationByIds(List<Long> ids);

    void exportExcel(OutputStream output, UserVO user, Long enterpriseId) throws Exception;

    List<ConnectTree> structurePositionTreeWithOutDeleteAndUpdate(List<ConnectTree> departMentTree, List<Position> positions);
}
