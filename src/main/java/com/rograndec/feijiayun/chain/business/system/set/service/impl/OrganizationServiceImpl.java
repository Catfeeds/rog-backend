/**
 * 
 */
package com.rograndec.feijiayun.chain.business.system.set.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserAdministrationMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.dao.DepartmentMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.PositionMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.SysRoleMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ConnectTree;
import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import com.rograndec.feijiayun.chain.business.system.set.entity.Position;
import com.rograndec.feijiayun.chain.business.system.set.entity.Tree;
import com.rograndec.feijiayun.chain.business.system.set.service.OrganizationService;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.deleteValid.DeleteValidSerivce;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.string.ChineseString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.util.*;

/**

 * @Description:组织机构相关实现

 * @author:LeiSu

 * @time:2017年8月22日 上午10:13:56

 */
@Service
public class OrganizationServiceImpl implements OrganizationService,DeleteValidSerivce{
	
	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Autowired
	private PositionMapper positionMapper;

	@Autowired
	private EnterpriseMapper enterpriseMapper;

	@Autowired
	private PurchaseGeneralComponent purchaseGeneralComponent;

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Autowired
	private UserAdministrationMapper userAdministrationMapper;

	/* (非 Javadoc)  
	 * <p>Title: getDepartMentOrganization</p>  
	 * <p>Description: </p>  
	 * @param department
	 * @param user
	 * @return  
	 * @see com.rograndec.feijiayun.chain.system.set.service.OrganizationService#getDepartMentOrganization(com.rograndec.feijiayun.chain.system.set.entityDepartment, com.rograndec.feijiayun.chain.common.vo.UserVO)  
	 */
	@Override
	public List<Department> getDepartMentOrganization(UserVO user) {
		List<Department> list = new ArrayList<Department>();
		/**
		 * 不管是总部登陆人还是分部登陆人----查询的部门都是总部的部门
		 * 2017-11-23 加盟店允许新增仅属于自己门店的部门、岗位，总部和其它门店不可见，允许查看系统默认数据
		 *
		 */
		Long enterpriseId = user.getEnterpriseId();

		if (user.getChainType() == ChainType.Selfoperatedshop.getCode()){// 分店获取的是总部的
			enterpriseId = user.getParentId();
		}
		list = departmentMapper.selectByCurrentUser(enterpriseId);
		return list;
	}

	/* (非 Javadoc)  
	 * <p>Title: updateDepartMentOrganization</p>  
	 * <p>Description: </p>  
	 * @param department
	 * @param user
	 * @return  
	 * @see com.rograndec.feijiayun.chain.system.set.service.OrganizationService#updateDepartMentOrganization(com.rograndec.feijiayun.chain.system.set.entity.Department, com.rograndec.feijiayun.chain.common.vo.UserVO)  
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void updateDepartMentOrganization(Department department,UserVO user) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("department", department);
		map.put("user",user);
		Long id = department.getId();
		Department priDepartment = departmentMapper.selectByPrimaryKey(id);
		/**
		 * 修改前姓名
		 */
		String name = priDepartment.getName();
		if (!name.equals(department.getName())){
			Department dName = departmentMapper.hasDepartmentName(department.getName(), user.getEnterpriseId());
			if (dName != null){
				throw new BusinessException(SysCode.FAIL.getCode(),"修改的部门名称已存在!");
			}
		}
		if ("".equals(department.getName().trim())){
			throw new BusinessException(SysCode.FAIL.getCode(),"修改的部门名称不能为空!");
		}
		departmentMapper.updateDepartMentOrganization(map);
	}

	/* (非 Javadoc)
	 * <p>Title: addDepartMentOrganization</p>
	 * <p>Description: </p>
	 * @param department
	 * @param user
	 * @return
	 * @see com.rograndec.feijiayun.chain.system.set.service.OrganizationService#addDepartMentOrganization(com.rograndec.feijiayun.chain.system.set.entity.Department, com.rograndec.feijiayun.chain.common.vo.UserVO)  
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void addDepartMentOrganization(Department department,UserVO user) throws Exception{
		if ("".equals(department.getCode().trim())){
			throw new BusinessException(SysCode.FAIL.getCode(),"新增的部门编码不能为空!");
		}
		if ("".equals(department.getName().trim())){
			throw new BusinessException(SysCode.FAIL.getCode(),"新增的部门名称不能为空!");
		}
		/**
		 * 不能有重复的部门名称和编码
		 */
		Department dName = departmentMapper.hasDepartmentName(department.getName(),user.getEnterpriseId());
		if (dName != null){
			throw new BusinessException(SysCode.FAIL.getCode(),"新增的部门名称已存在!");
		}
		Department dCode = departmentMapper.hasDepartmentCode(department.getCode(),user.getEnterpriseId());
		if (dCode != null){
			throw new BusinessException(SysCode.FAIL.getCode(),"新增的部门编码已存在!");
		}
		if (ChineseString.isChinese(department.getCode())){
			throw new BusinessException(SysCode.FAIL.getCode(),"部门编码不能含有中文!");
		}
		/**
		 * 如果所输入的上级部门中有岗位，则不能添加此岗位
		 */
		Long parentDeptId = department.getParentDeptId();
		List<Position> p = positionMapper.selectPositionByEnterPriseIdAndDepartmentId(user.getEnterpriseId() ,parentDeptId);
		if (p != null && p.size() > 0){
			throw new BusinessException(SysCode.FAIL.getCode(),"上级部门中包含岗位，无法添加下级部门!");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("department", department);
		map.put("user",user);
		departmentMapper.addDepartMent(map);
	}

	/* (非 Javadoc)  
	 * <p>Title: deleteDepartMentOrganization</p>  
	 * <p>Description: </p>  
	 * @param id  
	 * @see com.rograndec.feijiayun.chain.system.set.service.OrganizationService#deleteDepartMentOrganization(int)  
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public int deleteDepartMentOrganization(Long id,UserVO user) throws Exception{
		//如果当前部门有岗位那么当前部门无法删除
		if (positionMapper.selectPositionByDepartmentId(id) > 0){
			return 1;
		}
		if (departmentMapper.selectSonDepartment(id) > 0){
			return 2;
		}
		if (sysRoleMapper.selectRoleByDepartmentId(id) > 0){
			return 3;
		}
		if (userAdministrationMapper.selectUserHasDepartment(id) > 0){
			return 4;
		}
		Department department = departmentMapper.selectByPrimaryKey(id);
		if (department.getSysType() == 1){
			throw new BusinessException("111111","默认部门不能删除！");
		}
		departmentMapper.deleteByPrimaryKey(id);
		return 0;
	}

	/* (非 Javadoc)  
	 * <p>Title: getPositionOrganization</p>  
	 * <p>Description: </p>  
	 * @param position
	 * @param user
	 * @return  
	 * @see com.rograndec.feijiayun.chain.system.set.service.OrganizationService#getPositionOrganization(com.rograndec.feijiayun.chain.system.set.entity.Position, com.rograndec.feijiayun.chain.common.vo.UserVO)  
	 */
	@Override
	public List<Position> getPositionOrganization(UserVO user) {
		/**
		 * 不管是总部还是分部--查询出来的都是总部的岗位
		 */
		Long enterpriseId = user.getEnterpriseId();

		if (user.getChainType() == ChainType.Selfoperatedshop.getCode()){// 分店获取的是总部的
			enterpriseId = user.getParentId();
		}
		List<Position> position = positionMapper.selectByCurrentUser(enterpriseId);
		return position;
	}

	@Override
	public List<Position> selectByEnterpriseId2Report(Long eId) {
			List<Position> position = positionMapper.selectByEnterpriseId2Report(eId);
			return position;
	}

	/* (非 Javadoc)  
	 * <p>Title: updatePositionOrganization</p>  
	 * <p>Description: </p>  
	 * @param position  
	 * @see com.rograndec.feijiayun.chain.system.set.service.OrganizationService#updatePositionOrganization(com.rograndec.feijiayun.chain.system.set.entity.Position)  
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void updatePositionOrganization(Position position,UserVO user) throws Exception{
		if ("".equals(position.getName().trim())){
			throw new BusinessException(SysCode.FAIL.getCode(),"修改的岗位名称不能为空!");
		}
		Position priPosition = positionMapper.selectByPrimaryKey(position.getId());
		/**
		 * 修改前姓名
		 */
		String name = priPosition.getName();
		if (!name.equals(position.getName())){
			Position pName = positionMapper.hasPositionName(position.getName(),user.getEnterpriseId());
			if (pName != null){
				throw new BusinessException(SysCode.FAIL.getCode(),"修改的岗位名称已存在!");
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("position", position);
		map.put("user", user);
		positionMapper.updatePositionOrganization(map);
	}

	/* (非 Javadoc)  
	 * <p>Title: addPositionOrganization</p>  
	 * <p>Description: </p>  
	 * @param position  
	 * @see com.rograndec.feijiayun.chain.system.set.service.OrganizationService#addPositionOrganization(com.rograndec.feijiayun.chain.system.set.entity.Position)  
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void addPositionOrganization(Position position,UserVO user) throws Exception{
		if ("".equals(position.getCode().trim())){
			throw new BusinessException(SysCode.FAIL.getCode(),"新增的岗位编码不能为空!");
		}
		if ("".equals(position.getName().trim())){
			throw new BusinessException(SysCode.FAIL.getCode(),"新增的岗位名称不能为空!");
		}
		/**
		 * 不能有重复的岗位名称和编码
		 */
		Position pName = positionMapper.hasPositionName(position.getName(), user.getEnterpriseId());
		if (pName != null){
			throw new BusinessException(SysCode.FAIL.getCode(),"新增的岗位名称已存在!");
		}
		Position pCode = positionMapper.hasPositionCode(position.getCode(), user.getEnterpriseId());
		if (pCode != null){
			throw new BusinessException(SysCode.FAIL.getCode(),"新增的岗位编码已存在!");
		}
		if (ChineseString.isChinese(position.getCode())){
			throw new BusinessException(SysCode.FAIL.getCode(),"岗位编码不能含有中文!");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("position", position);
		map.put("user", user);
		positionMapper.addPosition(map);
	}

	/* (非 Javadoc)  
	 * <p>Title: deletePositionOrganization</p>  
	 * <p>Description: </p>  
	 * @param id  
	 * @see com.rograndec.feijiayun.chain.system.set.service.OrganizationService#deletePositionOrganization(int)  
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public int deletePositionOrganization(Long id,UserVO user) throws Exception{
		int status = 0;
		//通过当前岗位ID获得当前部门
		Long enterpriseId = user.getEnterpriseId();
		//Long departmentId = positionMapper.getDepartmentIdByUserAndPositionId(id,enterpriseId);
		//查看
		if (sysRoleMapper.getRoleByPosition(id) > 0) {
			status = 1;
			return status;
		}
		if (userAdministrationMapper.selectUserHasPosition(id) > 0){
			status =  2;
			return status;
		}
		if (positionMapper.selectByPrimaryKey(id).getSysType() == 1){
			throw new BusinessException("111111","当前岗位是默认岗位，无法删除！");
		}
		positionMapper.deleteByPrimaryKey(id);
		return status;
	}



	@Override
	public List<Position> getPositions4DeptIdAndEnterpriseId(Long depId){

		List<Position> positions = positionMapper.selectPositionsByDepartment(depId);

		return positions;
	}

	@Override
	public List<Position> getPositions4DeptIdAndEnterpriseIds(List<Long> depIds, Long enterpriseId){

		List<Position> positions = positionMapper.selectPositionsByDepartments(depIds,enterpriseId);

		return positions;
	}

	@Override
	public List<Tree> structurePositionTree(List<Tree> departMentTree, List<Position> position, UserVO userVO) {
		for(Tree tree : departMentTree){

			tree.setNodeShowDelete(false);
			tree.setNodeShowUpdate(false);
			if(!tree.getChildren().isEmpty() && tree.getChildren().size()>0){
				structurePositionTree(tree.getChildren(),position, userVO);
			}else{ 

				List<Tree> list = new ArrayList<Tree>();
				for (Position p : position) {
					if (p.getDeptId().equals(tree.getId())){
						Tree t = new Tree();
						t.setData(p);
						t.setId(p.getId());
						t.setLabel(p.getCode() + "-" + p.getName());
						t.setLeaf(true);
						t.setNodeShowDelete(true);
						t.setNodeShowUpdate(true);
						t.setPosition(true);
						/**
						 * 系统默认不显示修改和删除按钮
						 */
						if (p.getSysType() == 1) {
							t.setNodeShowDelete(false);
							t.setNodeShowUpdate(false);
						}else {
							boolean flag = valid(p);
							if (!flag){
								t.setNodeShowDelete(false);
							}
							/**
							 * 当前登陆人如果是分店人员，那么只能看不能增删改
							 */
							if (userVO.getChainType() == ChainType.Selfoperatedshop.getCode()){
								t.setNodeShowUpdate(false);
								t.setNodeShowDelete(false);
							}
						}

						list.add(t);
					}
				}
				if (list.size() > 0 && !list.isEmpty()){
					tree.setLeaf(false);
					tree.setChildren(list);
				}
			}
		}
		return departMentTree;
	}

	@Override
	public List<Department> getDepartMentOrganizationByIds(List<Long> ids) {
		List<Department> list = new ArrayList<Department>();
		if (ids != null && ids.size() > 0){
			for (Long id : ids) {
				Department department = departmentMapper.selectByPrimaryKey(id);
				list.add(department);
			}
		}
		return list;
	}

	/**
	 * 导出
	 *
	 * @param output
	 */
	@Override
	public void exportExcel(OutputStream output, UserVO user, Long enterpriseId) throws Exception {

		List<Position> positions = selectByEnterpriseId2Report(enterpriseId);

		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(user.getEnterpriseId());//企业
		List<String> headerList = new ArrayList<>();
		headerList.add(enterprise.getName());
		headerList.add("岗位");


		LinkedHashMap<String, String> rowHeaderMap = new LinkedHashMap<>();
		rowHeaderMap.put("name","岗位");
		rowHeaderMap.put("remark","职责");

		StringBuilder endString = new StringBuilder();

		// 单元格合并
		// 四个参数分别是：起始行，起始列，结束行，结束列
//        CellRangeAddress r1 = new CellRangeAddress(0, (short) 0, 0,
//                (short) (rowHeaderMap.size()-1));
//
//        CellRangeAddress r2 = new CellRangeAddress(1, (short) 1, 1,
//                (short) (rowHeaderMap.size()-1));

		purchaseGeneralComponent.commExcelExport(
				output
				,rowHeaderMap
				,positions
				,headerList
				,new ArrayList<>()
				,endString.toString()
				,true
				,new ArrayList<>()
		);
	}

	@Override
	public List<ConnectTree> structurePositionTreeWithOutDeleteAndUpdate(List<ConnectTree> departMentTree, List<Position> positions) {
		for(ConnectTree tree : departMentTree){
			if(!tree.getChildren().isEmpty() && tree.getChildren().size()>0){
				structurePositionTreeWithOutDeleteAndUpdate(tree.getChildren(),positions);
			}else{
				tree.setNodeShowDelete(false);
				tree.setNodeShowUpdate(false);
				List<ConnectTree> list = new ArrayList<ConnectTree>();
				for (Position p : positions) {
					if (p.getDeptId().equals(tree.getValue())){
						ConnectTree t = new ConnectTree();
						t.setData(p);
						t.setValue(p.getId());
						t.setLabel(p.getCode() + "-" + p.getName());
						t.setLeaf(true);
						t.setNodeShowDelete(false);
						t.setNodeShowUpdate(false);
						t.setPosition(true);
						list.add(t);
					}
				}
				if (list.size() > 0 && !list.isEmpty()){
					tree.setLeaf(false);
					tree.setChildren(list);
				}
			}
		}
		return departMentTree;
	}

	@Override
	public Boolean valid(Object object) {
		boolean flag = true;
        //（2）被角色引用的
        //（3）被员工引用的
		Position p = (Position)object;
		if (sysRoleMapper.getRoleByPosition(p.getId()) > 0){
			flag =  false;
		}
		if (userAdministrationMapper.selectUserHasPosition(p.getId()) > 0){
			flag =  false;
		}
		return flag;
	}
}
