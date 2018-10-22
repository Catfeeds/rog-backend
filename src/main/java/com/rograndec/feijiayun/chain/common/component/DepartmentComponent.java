package com.rograndec.feijiayun.chain.common.component;

import java.util.ArrayList;
import java.util.List;

import com.rograndec.feijiayun.chain.business.system.set.dao.PositionMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rograndec.feijiayun.chain.business.system.set.dao.DepartmentMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.Department;

@Component
public class DepartmentComponent {
	
	@Autowired
	private DepartmentMapper departmentMapper;

	@Autowired
	private PositionMapper positionMapper;
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly=true)
	public List<Department> findDepartMent(Long enterpriseId) {
		if (enterpriseId != null) {
			List<Department> list = departmentMapper.findDepartMent(enterpriseId);

			List<Department> resultList = new ArrayList<>();
			for (Department d : list) {
				/**
				 * 如果当前的部门下面还有岗位则不能出现在下拉框
				 */
				List<Position> p = positionMapper.selectPositionByEnterPriseIdAndDepartmentId(enterpriseId,d.getId());
				if (p == null || p.size() == 0){
					resultList.add(d);
				}
			}
			List<Department> realDepartMent = new ArrayList<>();
			/**
			 * 如果存在五级的部门，需要过滤掉五级的部门，因为部门最多增加五级
			 */
			if (resultList.size() > 0){
				for(Department withOutFive : resultList){
					boolean flag = hasFiveLevel(withOutFive);
					if (!flag){
						realDepartMent.add(withOutFive);
					}
				}
			}
			return realDepartMent;
		}
		return null;
	}

	private boolean hasFiveLevel(Department withOutFive) {
		/**
		 * 五级的部门
		 */
		Long deptId = withOutFive.getParentDeptId();
		for (int i = 0; i < 5; i++){
			Department department = departmentMapper.selectByPrimaryKey(deptId);
			if (department != null){
				deptId = department.getParentDeptId();
			}else {
				return false;
			}
			if (i == 4){
				return true;
			}
		}
		return false;
	}

	public List<Department> findDepartMent4EnterpriseId(Long enterpriseId){
		if (enterpriseId != null) {
			List<Department> list = departmentMapper.selectByEnterpriseIdAndDefut(enterpriseId);
			return list;
		}
		return null;
	}


	public List<Department> findAllDepartMent(Long enterpriseId) {
		List<Department> list = new ArrayList<>();
		if (enterpriseId != null){
			list = departmentMapper.selectByEnterpriseId(enterpriseId);
		}
		return list;
	}
}
