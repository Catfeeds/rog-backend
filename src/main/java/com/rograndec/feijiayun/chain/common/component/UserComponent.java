package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.business.basic.user.constant.UserCoderRule;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.SimpleUserVO;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.exception.UserManagerBizException;
import com.rograndec.feijiayun.chain.business.basic.user.vo.AddUserVO;
import com.rograndec.feijiayun.chain.business.basic.user.vo.UserAdministrationVO;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatus;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.dao.DepartmentMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 
 * @ClassName: UserComponent  
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年8月24日 下午3:59:26  
 *
 */
@Component
public class UserComponent {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private ManageConfigMapper manageConfigMapper;


	@Autowired
	private CodeComponent codeComponent;

	@Autowired
	private DepartmentMapper departmentMapper;

	private static final String USER_CODE_NAME = "com.rograndec.feijiayun.chain.business.basic.user.entity.User";

	private static final Integer USER_CODE_LENGTH = 4;

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly=true)
	public List<User> findUserByEnterpriseId(Long enterpriseId) {
		if (enterpriseId != null) {
			List<User> list = userMapper.findUserByEnterpriseId(enterpriseId);
			return list;
		}
		return null;
	}

	public List<User> findUserByUserIds(Set<Long> userId) {
		if (!CollectionUtils.isEmpty(userId)) {
			List<Long> us = new ArrayList<>(userId);
			List<User> user = userMapper.selectByIds(us);
			return user;
		}
		return null;
	}

	public User findUserByUserId(Long userId) {
		if (null != userId) {
			User user = userMapper.selectByPrimaryKey(userId);
			return user;
		}
		return null;
	}

	public Long getUserHeadquarters(UserVO userVO){
		if(userVO.getChainType().equals(ChainType.Headquarters.getCode())){
			return userVO.getEnterpriseId();
		}else {
			return userVO.getParentId();
		}
	}

	/**
	 * 查询制定企业下的所有员工，PS:查询总部企业不会包含子门店的员工
	 * @param enterpriseId
	 * @param status
	 * @return
	 */
	public List<SimpleUserVO> getSimpleUserVOByEnterpriseId(Long enterpriseId, Integer status) {
		return userMapper.getSimpleUserVOByEnterpriseId(enterpriseId,status == 2 ? null: status );
	}

	public String getUserCode(UserVO userVO, AddUserVO addUserVO, Enterprise enterprise) throws Exception {
		UserAdministrationVO administrationVO = addUserVO.getUserAdministrationDTO();
		ManageConfig manageConfig = manageConfigMapper.getMangeConfigByEPId(userVO.getEnterpriseId());
		String code = addUserVO.getCode();

		Long enterpriseId = enterprise.getId();

		if(manageConfig.getUserCodeRule().equals(UserCoderRule.CUSTOM_NUM.getCode())){
			/**
			 * 当编码规则为自定义编码时,需要判断当前总部下该code是否有重复
			 */

			Long count = userMapper.selectByParentIdAndUserCode(enterprise.getId(), code);

			if(count > 0){
				throw new UserManagerBizException(UserManagerBizException.NOT_DATA,code+"自定义用户编码已经存在");
			}

		}

		if(manageConfig.getUserCodeRule().equals(UserCoderRule.SYSTEM_SET_SERIAL_NUM.getCode())){

			code = codeComponent.generate(USER_CODE_NAME
					,USER_CODE_LENGTH,enterpriseId);
			return code;

		}

		if(manageConfig.getUserCodeRule().equals(UserCoderRule.SYSTEM_SET_DEPT_SERIAL_NUM.getCode())){

			List<Long> depts = administrationVO.getDeptIds();

			List<Department> departments = departmentMapper.selectByIds(depts);

			if(CollectionUtils.isEmpty(departments)){
				throw new UserManagerBizException(UserManagerBizException.NOT_DATA,"部门不存在");
			}

			/**
			 * 取最小的部门code
			 */
			Department department = departments.get(0);
			code = codeComponent.generateWithPrefix(USER_CODE_NAME,department.getCode(),USER_CODE_LENGTH,enterpriseId);
			return code;
		}

		if(!manageConfig.getUserCodeRule().equals(UserCoderRule.CUSTOM_NUM.getCode()) && StringUtils.isEmpty(code)){

			/**
			 * 不是自定义编码 ,并且code为空 抛出异常
			 */
			throw new UserManagerBizException(UserManagerBizException.NOT_DATA,"员工编码不能为空");
		}
		return code;
	}

	private String generateDeptIdsStr(List<Long> depts){

		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i < depts.size() ; i ++){
			sb.append(depts.get(i));
			if(i != depts.size()-1){
				sb.append(",");
			}
		}
		return sb.toString();
	}

	public List<User> selectAllUsers() {
		List<User> list = new ArrayList<>();
		list = userMapper.selectAllUsers();
		return list;
	}

	/**
	 * 根据企业id，人员id查询，启用且审核通过的人员
	 * @param enterpriseId
	 * @param manId
	 * @return
	 */
	public User validationUser(Long enterpriseId, Long manId) {
		User user = userMapper.selectByIdAndEnterpriseIdAndStatusAndApproveStatus(enterpriseId, manId, EnableStatus.ENABLE.getStatus(), ApprovalFlowAuditStatus.DETAIL_STATUS_PASS.getValue());
		if (user == null) {
			throw new BusinessException("无效人员，请重新选择！");
		}
		return user;
	}
}
