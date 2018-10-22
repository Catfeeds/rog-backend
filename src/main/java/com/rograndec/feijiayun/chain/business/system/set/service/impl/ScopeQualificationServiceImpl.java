/**
 * 
 */
package com.rograndec.feijiayun.chain.business.system.set.service.impl;

import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierQualificationConfigMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierSalerMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserAdministration;
import com.rograndec.feijiayun.chain.business.basic.user.vo.BusinessVarietysVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.QualificationValidateVO;
import com.rograndec.feijiayun.chain.business.system.set.dao.*;
import com.rograndec.feijiayun.chain.business.system.set.entity.*;
import com.rograndec.feijiayun.chain.business.system.set.service.ScopeQualificationService;
import com.rograndec.feijiayun.chain.business.system.set.vo.CheckDataVO;
import com.rograndec.feijiayun.chain.business.system.set.vo.GoodsQualificationVO;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.SysType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.string.ChineseString;
import com.rograndec.feijiayun.chain.utils.string.StringSplit;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**

 * @Description:TODO

 * @author:LeiSu

 * @time:2017年8月22日 上午10:15:44

 */
@Service
public class ScopeQualificationServiceImpl implements ScopeQualificationService{

	@Autowired
	private BusinessScopeMapper businessScopeMapper;
	
	@Autowired
	private EnterpriseQualificationMapper enterpriseQualificationMapper;
	
	@Autowired
	private UserQualificationMapper userQualificationMapper;
	
	@Autowired
	private GoodsQualificationMapper goodsQualificationMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private SupplierSalerMapper supplierSalerMapper;

	@Autowired
	private SupplierMapper supplierMapper;

	@Autowired
	private EnterpriseMapper enterpriseMapper;

	@Autowired
	private PositionMapper positionMapper;

	@Autowired
	private ManageConfigMapper manageConfigMapper;

	@Autowired
	private SupplierQualificationConfigMapper supplierQualificationConfigMapper;

	@Autowired
	private WarnSetMapper warnSetMapper;

	/* (非 Javadoc)  
	 * <p>Title: getScopeQualification</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return  
	 * @see com.rograndec.feijiayun.chain.system.set.service.ScopeQualificationService#getScopeQualification(com.rograndec.feijiayun.chain.common.vo.UserVO)  
	 */
	@Override
	public List<BusinessScope> getScopeQualification(UserVO user) {
		List<BusinessScope> businessScope = new ArrayList<BusinessScope>();
		//不是总部的情况
		if (user.getChainType() != ChainType.Headquarters.getCode()) {
			businessScope = businessScopeMapper.getScopeQualificationById(user);
		}else {
			businessScope = businessScopeMapper.getScopeQualification(user);
		}
		for (BusinessScope bs : businessScope) {
			bs.setChainType(user.getChainType());

			if(bs.getSysType().equals(SysType.SYSTEM.getCode())){
				bs.setDeleteFlag(Boolean.FALSE);
				bs.setUpdateFlag(Boolean.FALSE);
			} else if(!valid(bs)){
				bs.setDeleteFlag(Boolean.FALSE);
			}

		}
		return businessScope;
	}

    public Boolean valids(Object object) {

        if (((CheckDataVO) object).getType().equals("企业资质")) {
            EnterpriseQualification enterpriseQualification = enterpriseQualificationMapper.selectByPrimaryKey(((CheckDataVO) object).getId());
            if (enterpriseQualification.getSysType() == 1) {
                return false;
            } else {
            	if(enterpriseQualification.getSuitableUnit() == 0){
					Long num = enterpriseQualificationMapper.checkDeteleEnterpriseData(((CheckDataVO) object).getId());
					if (num == 0) {
						return true;
					} else {
						num = supplierQualificationConfigMapper.checkDeteleEnterpriseData(((CheckDataVO) object).getId());
						if (num == 0) {
							return true;
						} else {
							return false;
						}
					}
				}else if(enterpriseQualification.getSuitableUnit() == 1 || enterpriseQualification.getSuitableUnit() == 2){
					Long num = enterpriseQualificationMapper.checkDeteleEnterpriseData(((CheckDataVO) object).getId());
					if (num == 0) {
						return true;
					} else {
						return false;
					}
				}else if(enterpriseQualification.getSuitableUnit() == 3){
					Long num = supplierQualificationConfigMapper.checkDeteleEnterpriseData(((CheckDataVO) object).getId());
					if (num == 0) {
						return true;
					} else {
						return false;
					}
				}else
					return false;

            }

        } else if (((CheckDataVO) object).getType().equals("员工资质")) {
            UserQualification userQualification = userQualificationMapper.selectByPrimaryKey(((CheckDataVO) object).getId());
            if (userQualification.getSysType() == 1) {
                return false;
            } else {
                Long num = enterpriseQualificationMapper.checkDeteleUserData(((CheckDataVO) object).getId());
                if (num == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } else if (((CheckDataVO) object).getType().equals("商品资质")) {
            GoodsQualification goodsQualification = goodsQualificationMapper.selectByPrimaryKey(((CheckDataVO) object).getId());
            if (goodsQualification.getSysType() == 1) {
                return false;
            } else {
                Long num = enterpriseQualificationMapper.checkDeteleGoodsData(((CheckDataVO) object).getId());
                if (num == 0) {
                    return true;
                } else {
                    return false;
                }
            }

        } else
            return false;

    }

	/**
	 * @param id
	 * @return
	 */
	private boolean valid(BusinessScope bs) {

		/**
		 *
		 （1）系统默认的；
		 （2）被企业信息（总部门店）引用的；
		 （3）被供货单位信息引用的；
		 （4）被员工信息引用的；
		 */

		if(bs.getSysType()  == SysType.SYSTEM.getCode()){
			return false;
		}

		List<Enterprise> enterpriseList =   businessScopeMapper.selectEnterpriseByScopeId(bs.getId());
		if(enterpriseList.size() > 0) return false;

		List<Supplier>  supplierList = businessScopeMapper.selectSupplierByScopeId(bs.getId());
		if(supplierList.size() > 0) return false;

		List<UserAdministration> userList = businessScopeMapper.selectUserByScopeId(bs.getId());

		if(userList.size() > 0) return false;

		return true;
	}


	@Override
	public List<BusinessScope> getScopeQualificationByIdAndScopes(UserVO userVO, BusinessVarietysVO businessVarietysVO) {

		List<BusinessScope> businessScope = new ArrayList<BusinessScope>();

		Integer type = businessVarietysVO.getType();

		Map<String, Object> map = new HashMap<>();

		/**
		 * 员工管理:0;供应商员工:1,默认为0
		 */
		List<Long> scopes = new ArrayList<>();
		if(type == 0){

			Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(businessVarietysVO.getEnterpriseId());

			String businessScopeId = enterprise.getBusinessScopeId();

			scopes = StringSplit.strSplit(businessScopeId);

			map.put("varietys",businessVarietysVO.getBusinessVarietys());
			map.put("scopes",scopes);

		}else {

			Supplier supplier = supplierMapper.selectByPrimaryKey(businessVarietysVO.getEnterpriseId());

			String businessScopeId = supplier.getBusinessScopeId();

			scopes = StringSplit.strSplit(businessScopeId);

			map.put("varietys",businessVarietysVO.getBusinessVarietys());
			map.put("scopes",scopes);

		}
		if(!CollectionUtils.isEmpty(scopes) && !CollectionUtils.isEmpty(businessVarietysVO.getBusinessVarietys())){
			businessScope = businessScopeMapper.getScopeQualificationByDept(businessVarietysVO.getBusinessVarietys(),scopes);
		}

		return businessScope;
	}

	/* (非 Javadoc)  
	 * <p>Title: updateQualityUnqualified</p>  
	 * <p>Description: </p>  
	 * @param businessScope  
	 * @see com.rograndec.feijiayun.chain.system.set.service.ScopeQualificationService#updateQualityUnqualified(com.rograndec.feijiayun.chain.system.set.entity.BusinessScope)  
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void updateQualityUnqualified(BusinessScope businessScope,UserVO user) throws Exception{
		if ("".equals(businessScope.getName().trim())){
			throw new BusinessException(SysCode.FAIL.getCode(),"修改时名称不可为空!");
		}
		BusinessScope preBusinessScope = businessScopeMapper.selectByPrimaryKey(businessScope.getId());
		if (!businessScope.getName().equals(preBusinessScope.getName())){
			BusinessScope scopeName = businessScopeMapper.hasBusinessName(businessScope.getName(),user.getChainType() == ChainType.Headquarters.getCode() ? user.getEnterpriseId() : user.getParentId());
			if (scopeName != null){
				throw new BusinessException(SysCode.FAIL.getCode(),"名称已存在!");
			}
		}

		if(preBusinessScope.getSysType().equals(SysType.SYSTEM.getCode())){
			throw  new BusinessException(SysCode.FAIL.getCode(),"系统默认数据，不允许修改");

		}

		if(!preBusinessScope.getStatus().equals(businessScope.getStatus())){
			if(!valid(preBusinessScope)){
				// 系统默认或 关联数据不允许更新状态
				throw  new BusinessException(SysCode.FAIL.getCode(),"系统默认或者存在关联数据，不允许修改状态"); // 系统默认或 关联数据不允许更新状态
			}
		}




		Map<String,Object> map = new HashMap<String,Object>();
		map.put("businessScope", businessScope);
		map.put("user", user);
		if (user.getChainType() == ChainType.Headquarters.getCode()){
			businessScopeMapper.updateQualityUnqualified(map);
		}
		
		
	}
	/* (非 Javadoc)  
	 * <p>Title: addQualityUnqualified</p>  
	 * <p>Description: </p>  
	 * @param businessScope  
	 * @see com.rograndec.feijiayun.chain.system.set.service.ScopeQualificationService#addQualityUnqualified(com.rograndec.feijiayun.chain.system.set.entity.BusinessScope)  
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void addQualityUnqualified(BusinessScope businessScope,UserVO user) throws Exception{
		/**
		 * 编码和名称不可重复
		 */
		if ("".equals(businessScope.getCode().trim())){
			throw new BusinessException(SysCode.FAIL.getCode(),"编码不可为空！");
		}
		if ("".equals(businessScope.getName().trim())){
			throw new BusinessException(SysCode.FAIL.getCode(),"名称不可为空！");
		}
		if (ChineseString.isChinese(businessScope.getCode())){
			throw new BusinessException(SysCode.FAIL.getCode(),"编码不能有中文！");
		}
		String code = businessScope.getCode();
		String name = businessScope.getName();
		BusinessScope scopeCode = businessScopeMapper.hasBusinessCode(code,user.getChainType() == ChainType.Headquarters.getCode() ? user.getEnterpriseId() : user.getParentId());
		if (scopeCode != null){
			throw new BusinessException(SysCode.FAIL.getCode(),"编码已存在！");
		}
		BusinessScope scopeName = businessScopeMapper.hasBusinessName(name,user.getChainType() == ChainType.Headquarters.getCode() ? user.getEnterpriseId() : user.getParentId());
		if (scopeName != null){
			throw new BusinessException(SysCode.FAIL.getCode(),"名称已存在！");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("businessScope", businessScope);
		map.put("user", user);
		if (user.getChainType() == ChainType.Headquarters.getCode()){
			businessScopeMapper.addQualityUnqualified(map);
		}
	}
	/* (非 Javadoc)  
	 * <p>Title: deleteQualityUnqualified</p>  
	 * <p>Description: </p>  
	 * @param id  
	 * @see com.rograndec.feijiayun.chain.system.set.service.ScopeQualificationService#deleteQualityUnqualified(java.lang.Long)  
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void deleteQualityUnqualified(Long id) {

		BusinessScope businessScope = businessScopeMapper.selectByPrimaryKey(id);

		if(!valid(businessScope)){
			throw  new BusinessException(SysCode.FAIL.getCode(),"系统默认或者存在关联数据，不允许删除"); // 系统默认或 关联数据不允许更新状态
		}
		businessScopeMapper.deleteByPrimaryKey(id);
	}
	/* (非 Javadoc)  
	 * <p>Title: getEnterpriseQualification</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return  
	 * @see com.rograndec.feijiayun.chain.system.set.service.ScopeQualificationService#getEnterpriseQualification(com.rograndec.feijiayun.chain.common.vo.UserVO)  
	 */
	@Override
	public List<EnterpriseQualification> getEnterpriseQualification(UserVO user) {
		List<EnterpriseQualification> enterpriseQualification = new ArrayList<EnterpriseQualification>();
		//不是总部的情况
		if (user.getChainType() != ChainType.Headquarters.getCode()) {
			enterpriseQualification = enterpriseQualificationMapper.getEnterpriseQualificationById(user);
		}else {
			enterpriseQualification = enterpriseQualificationMapper.getEnterpriseQualification(user);
		}
		for (EnterpriseQualification eq : enterpriseQualification) {
			eq.setChainType(user.getChainType());
			CheckDataVO checkDataVO = new CheckDataVO();
			checkDataVO.setId(eq.getId());
			checkDataVO.setType("企业资质");
			eq.setDeleteFlag(valids(checkDataVO));
			eq.setUpdateFlag(checkUpdateEnterpriseData(eq.getId()));
		}
		return enterpriseQualification;
	}
	/* (非 Javadoc)  
	 * <p>Title: updateEnterpriseQualification</p>  
	 * <p>Description: </p>  
	 * @param enterpriseQualification  
	 * @see com.rograndec.feijiayun.chain.system.set.service.ScopeQualificationService#updateEnterpriseQualification(com.rograndec.feijiayun.chain.system.set.entity.EnterpriseQualification)  
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void updateEnterpriseQualification(EnterpriseQualification enterpriseQualification,UserVO user) throws Exception{
		if ("".equals(enterpriseQualification.getName().trim())){
			throw new BusinessException(SysCode.FAIL.getCode(),"修改时名称不可为空!");
		}
		EnterpriseQualification preEnterpriseQualification = enterpriseQualificationMapper.selectByPrimaryKey(enterpriseQualification.getId());
		if (!preEnterpriseQualification.getName().equals(enterpriseQualification.getName())){
			EnterpriseQualification enterPriseName = enterpriseQualificationMapper.hasEnterpriseName(enterpriseQualification.getName(),user.getChainType() == ChainType.Headquarters.getCode() ? user.getEnterpriseId() : user.getParentId());
			if (enterPriseName != null){
				throw new BusinessException(SysCode.FAIL.getCode(),"名称已存在!");
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("enterpriseQualification",enterpriseQualification);
		map.put("user", user);
		if (user.getChainType() == ChainType.Headquarters.getCode()){
			enterpriseQualificationMapper.updateEnterpriseQualification(map);
		}
	}
	/* (非 Javadoc)  
	 * <p>Title: addEnterpriseQualification</p>  
	 * <p>Description: </p>  
	 * @param enterpriseQualification  
	 * @see com.rograndec.feijiayun.chain.system.set.service.ScopeQualificationService#addEnterpriseQualification(com.rograndec.feijiayun.chain.system.set.entity.EnterpriseQualification)  
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void addEnterpriseQualification(EnterpriseQualification enterpriseQualification,UserVO user) throws Exception{
		if ("".equals(enterpriseQualification.getCode().trim())){
			throw new BusinessException(SysCode.FAIL.getCode(),"编码不可为空！");
		}
		if ("".equals(enterpriseQualification.getName().trim())){
			throw new BusinessException(SysCode.FAIL.getCode(),"名称不可为空！");
		}
		if (ChineseString.isChinese(enterpriseQualification.getCode())){
			throw new BusinessException(SysCode.FAIL.getCode(),"编码不能有中文！");
		}
		String code = enterpriseQualification.getCode();
		String name = enterpriseQualification.getName();
		EnterpriseQualification enterPriseCode = enterpriseQualificationMapper.hasEnterPriseCode(code,user.getChainType() == ChainType.Headquarters.getCode() ? user.getEnterpriseId() : user.getParentId());
		if (enterPriseCode != null){
			throw new BusinessException(SysCode.FAIL.getCode(),"编码已存在！");
		}
		EnterpriseQualification enterpriseName = enterpriseQualificationMapper.hasEnterpriseName(name,user.getChainType() == ChainType.Headquarters.getCode() ? user.getEnterpriseId() : user.getParentId());
		if (enterpriseName != null){
			throw new BusinessException(SysCode.FAIL.getCode(),"名称已存在！");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("enterpriseQualification",enterpriseQualification);
		map.put("user", user);
		if (user.getChainType() == ChainType.Headquarters.getCode()){
			enterpriseQualificationMapper.addEnterpriseQualification(map);
		}
	}
	/* (非 Javadoc)  
	 * <p>Title: deleteEnterpriseQualification</p>  
	 * <p>Description: </p>  
	 * @param id  
	 * @see com.rograndec.feijiayun.chain.system.set.service.ScopeQualificationService#deleteEnterpriseQualification(java.lang.Long)  
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void deleteEnterpriseQualification(Long id) {
		CheckDataVO checkDataVO = new CheckDataVO();
		checkDataVO.setId(id);
		checkDataVO.setType("企业资质");
		if (valids(checkDataVO)) {
			enterpriseQualificationMapper.deleteByPrimaryKey(id);
			//删除预警设置里对应的资质
			warnSetMapper.deleteByQualificationId(id);
		} else {
			throw new BusinessException(SysCode.FAIL.getCode(), "已被使用不可删除！");
		}
	}
	/* (非 Javadoc)  
	 * <p>Title: getUserQualification</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return  
	 * @see com.rograndec.feijiayun.chain.system.set.service.ScopeQualificationService#getUserQualification(com.rograndec.feijiayun.chain.common.vo.UserVO)  
	 */
	@Override
	public List<UserQualification> getUserQualification(UserVO user) {
		List<UserQualification> userQualification = new ArrayList<UserQualification>();
		//不是总部的情况
		if (user.getChainType() != ChainType.Headquarters.getCode()) {
			userQualification = userQualificationMapper.getUserQualificationById(user);
		}else {
			userQualification = userQualificationMapper.getUserQualification(user);
		}
		for (UserQualification uq : userQualification) {
			List<String> positionNames = new ArrayList<String>();
			uq.setChainType(user.getChainType());
			if (uq.getPositionIds() != null && !"".equals(uq.getPositionIds())){
				String[] positionStr = uq.getPositionIds().split(",");
				for (String s : positionStr) {
					if (!s.equals("")){
						Long id = Long.parseLong(s);
						Position position = positionMapper.selectByPrimaryKey(id);
						if (position != null){
							positionNames.add(position.getName());
						}
					}
				}
			}
			uq.setPositionName(positionNames);
			CheckDataVO checkDataVO = new CheckDataVO();
			checkDataVO.setId(uq.getId());
			checkDataVO.setType("员工资质");
			uq.setDeleteFlag(valids(checkDataVO));
			uq.setUpdateFlag(checkUpdateUserData(uq.getId()));
		}
		/**
		 *
		 */
		return userQualification;
	}

	@Override
	public List<UserQualification> getUserQualification4Enable(UserVO user) {
		List<UserQualification> userQualification = new ArrayList<UserQualification>();
		//不是总部的情况
		if (user.getChainType() != ChainType.Headquarters.getCode()) {
			userQualification = userQualificationMapper.getUserQualificationById4Enable(user);
		}else {
			userQualification = userQualificationMapper.getUserQualification4Enable(user);
		}
		for (UserQualification uq : userQualification) {
			uq.setChainType(user.getChainType());
		}
		return userQualification;
	}

	/* (非 Javadoc)
	 * <p>Title: updateUserQualification</p>  
	 * <p>Description: </p>  
	 * @param userQualification  
	 * @see com.rograndec.feijiayun.chain.system.set.service.ScopeQualificationService#updateUserQualification(com.rograndec.feijiayun.chain.system.set.entity.UserQualification)  
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void updateUserQualification(UserQualification userQualification,UserVO user) throws Exception{
		if ("".equals(userQualification.getName().trim())){
			throw new BusinessException(SysCode.FAIL.getCode(),"修改时名称不可为空!");
		}
		UserQualification preUserQualification = userQualificationMapper.selectByPrimaryKey(userQualification.getId());
		if (!preUserQualification.getName().equals(userQualification.getName())){
			UserQualification userName = userQualificationMapper.hasUserName(userQualification.getName(),user.getChainType() == ChainType.Headquarters.getCode() ? user.getEnterpriseId() : user.getParentId());
			if (userName != null){
				throw new BusinessException(SysCode.FAIL.getCode(),"名称已存在!");
			}
		}
		//转换postionIds供翟伟查询使用
		String positionIds = userQualification.getPositionIds();
		if (positionIds != null && !"".equals(positionIds)){
			positionIds = "," + positionIds + ",";
			userQualification.setPositionIds(positionIds);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userQualification",userQualification);
		map.put("user", user);
		//只有总部人员可修改
		if (user.getChainType() == ChainType.Headquarters.getCode()){
			userQualificationMapper.updateUserQualification(map);
		}
	}
	/* (非 Javadoc)  
	 * <p>Title: addUserQualification</p>  
	 * <p>Description: </p>  
	 * @param userQualification  
	 * @see com.rograndec.feijiayun.chain.system.set.service.ScopeQualificationService#addUserQualification(com.rograndec.feijiayun.chain.system.set.entity.UserQualification)  
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void addUserQualification(UserQualification userQualification,UserVO user) throws Exception{
		if ("".equals(userQualification.getCode().trim())){
			throw new BusinessException(SysCode.FAIL.getCode(),"编码不可为空！");
		}
		if ("".equals(userQualification.getName().trim())){
			throw new BusinessException(SysCode.FAIL.getCode(),"名称不可为空！");
		}
		if (ChineseString.isChinese(userQualification.getCode())){
			throw new BusinessException(SysCode.FAIL.getCode(),"编码不能有中文！");
		}
		String code = userQualification.getCode();
		String name = userQualification.getName();
		Long enterpriseId = user.getChainType() == ChainType.Headquarters.getCode() ? user.getEnterpriseId() : user.getParentId();
		UserQualification userCode = userQualificationMapper.hasUserCode(code,enterpriseId);
		if (userCode != null){
			throw new BusinessException(SysCode.FAIL.getCode(),"编码已存在！");
		}
		UserQualification userName = userQualificationMapper.hasUserName(name,enterpriseId);
		if (userName != null){
			throw new BusinessException(SysCode.FAIL.getCode(),"名称已存在！");
		}
		//转换postionIds供翟伟查询使用
		String positionIds = userQualification.getPositionIds();
		if (positionIds != null && !"".equals(positionIds)){
			positionIds = "," + positionIds + ",";
			userQualification.setPositionIds(positionIds);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userQualification",userQualification);
		map.put("user", user);
		//只有总部人员可修改
		if (user.getChainType() == ChainType.Headquarters.getCode()){
			userQualificationMapper.addUserQualification(map);
		}
	}
	/* (非 Javadoc)  
	 * <p>Title: deleteUserQualification</p>  
	 * <p>Description: </p>  
	 * @param id  
	 * @see com.rograndec.feijiayun.chain.system.set.service.ScopeQualificationService#deleteUserQualification(java.lang.Long)  
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void deleteUserQualification(Long id) {
		CheckDataVO checkDataVO = new CheckDataVO();
		checkDataVO.setId(id);
		checkDataVO.setType("员工资质");
		if (valids(checkDataVO)) {
			userQualificationMapper.deleteByPrimaryKey(id);
		} else {
			throw new BusinessException(SysCode.FAIL.getCode(), "已被使用不可删除！");
		}
	}
	/* (非 Javadoc)  
	 * <p>Title: getGoodsQualification</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return  
	 * @see com.rograndec.feijiayun.chain.system.set.service.ScopeQualificationService#getGoodsQualification(com.rograndec.feijiayun.chain.common.vo.UserVO)  
	 */
	@Override
	public List<GoodsQualification> getGoodsQualification(UserVO user) {
		List<GoodsQualification> goodsQualification = new ArrayList<GoodsQualification>();
		//不是总部的情况
		if (user.getChainType() != ChainType.Headquarters.getCode()) {
			goodsQualification = goodsQualificationMapper.getGoodsQualificationById(user);
		}else {
			goodsQualification = goodsQualificationMapper.getGoodsQualification(user);
		}
		for (GoodsQualification gq : goodsQualification) {
			gq.setChainType(user.getChainType());
			CheckDataVO checkDataVO = new CheckDataVO();
			checkDataVO.setId(gq.getId());
			checkDataVO.setType("商品资质");
			gq.setDeleteFlag(valids(checkDataVO));
			gq.setUpdateFlag(checkUpdateGoodsData(gq.getId()));
		}
		return goodsQualification;
	}
	/* (非 Javadoc)  
	 * <p>Title: updateGoodsQualification</p>  
	 * <p>Description: </p>  
	 * @param goodsQualification  
	 * @see com.rograndec.feijiayun.chain.system.set.service.ScopeQualificationService#updateGoodsQualification(com.rograndec.feijiayun.chain.system.set.entity.GoodsQualification)  
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void updateGoodsQualification(GoodsQualification goodsQualification,UserVO user) throws Exception{
		if ("".equals(goodsQualification.getName().trim())){
			throw new BusinessException(SysCode.FAIL.getCode(),"修改时名称不可为空!");
		}
		GoodsQualification preGoodsQualification = goodsQualificationMapper.selectByPrimaryKey(goodsQualification.getId());
		if (!preGoodsQualification.getName().equals(goodsQualification.getName())){
			GoodsQualification goodsName = goodsQualificationMapper.hasGoodsName(goodsQualification.getName(),user.getChainType() == ChainType.Headquarters.getCode() ? user.getEnterpriseId() : user.getParentId());
			if (goodsName != null){
				throw new BusinessException(SysCode.FAIL.getCode(),"名称已存在!");
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("goodsQualification", goodsQualification);
		map.put("user", user);
		if (user.getChainType() == ChainType.Headquarters.getCode()) {
			goodsQualificationMapper.updateGoodsQualification(map);
		}
		
	}
	/* (非 Javadoc)  
	 * <p>Title: addGoodsQualification</p>  
	 * <p>Description: </p>  
	 * @param goodsQualification  
	 * @see com.rograndec.feijiayun.chain.system.set.service.ScopeQualificationService#addGoodsQualification(com.rograndec.feijiayun.chain.system.set.entity.GoodsQualification)  
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void addGoodsQualification(GoodsQualification goodsQualification,UserVO user) throws Exception{
		if ("".equals(goodsQualification.getCode().trim())){
			throw new BusinessException(SysCode.FAIL.getCode(),"编码不可为空！");
		}
		if ("".equals(goodsQualification.getName().trim())){
			throw new BusinessException(SysCode.FAIL.getCode(),"名称不可为空！");
		}
		if (ChineseString.isChinese(goodsQualification.getCode())){
			throw new BusinessException(SysCode.FAIL.getCode(),"编码不能有中文！");
		}
		String code = goodsQualification.getCode();
		String name = goodsQualification.getName();
		GoodsQualification goodCode = goodsQualificationMapper.hasGoodsCode(code,user.getChainType() == ChainType.Headquarters.getCode() ? user.getEnterpriseId() : user.getParentId());
		if (goodCode != null){
			throw new BusinessException(SysCode.FAIL.getCode(),"编码已存在！");
		}
		GoodsQualification goodName = goodsQualificationMapper.hasGoodsName(name,user.getChainType() == ChainType.Headquarters.getCode() ? user.getEnterpriseId() : user.getParentId());
		if (goodName != null){
			throw new BusinessException(SysCode.FAIL.getCode(),"名称已存在！");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("goodsQualification", goodsQualification);
		map.put("user", user);
		if (user.getChainType() == ChainType.Headquarters.getCode()) {
			goodsQualificationMapper.addGoodsQualification(map);
		}
		
	}
	/* (非 Javadoc)  
	 * <p>Title: deleteGoodsQualification</p>  
	 * <p>Description: </p>  
	 * @param id  
	 * @see com.rograndec.feijiayun.chain.system.set.service.ScopeQualificationService#deleteGoodsQualification(java.lang.Long)  
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void deleteGoodsQualification(Long id) {
		CheckDataVO checkDataVO = new CheckDataVO();
		checkDataVO.setId(id);
		checkDataVO.setType("商品资质");
		if (valids(checkDataVO)) {
			goodsQualificationMapper.deleteByPrimaryKey(id);
		} else {
			throw new BusinessException(SysCode.FAIL.getCode(), "已被使用不可删除！");
		}
	}



	/**
	 * 根据条件获取商品资质
	 * @param enterpriseId (可选)
	 * @param checkTypeId (可选)
	 * @param type
	 *@param status (可选)  @return
	 */
	@Override
	public List<GoodsQualificationVO> getGoodQuaInfoVO(Long enterpriseId, Long checkTypeId, Integer type, Integer status, Long quId) {
		List<GoodsQualification> list = goodsQualificationMapper.getGoodQuaInfo(enterpriseId,checkTypeId,type,status,quId);
		List<GoodsQualificationVO> voList = new ArrayList<>();
		list.forEach(item->{
			GoodsQualificationVO vo = new GoodsQualificationVO();
			BeanUtils.copyProperties(item,vo);
			vo.setCode(null);
			voList.add(vo);
		});
		return voList;
	}

	@Override
	public GoodsQualification getGoodQuaInfoByKey(Long quId){
		return goodsQualificationMapper.selectByPrimaryKey(quId);
	}

	@Override
	public QualificationValidateVO queryUserQualificationValidateByEnterpriseIdAndId(Long enterpriseId, String qualificationId) {
		QualificationValidateVO bean = new QualificationValidateVO();
		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
		if(enterprise == null){
			return null;
		}
		ManageConfig manageConfig = manageConfigMapper.selectManageConfigByEnterpriseId(
				(enterprise.getParentId()==null||enterprise.getParentId()==0) ? enterprise.getId() : enterprise.getParentId());
		if(manageConfig == null){
			return null;
		}
		bean.setQualityControl(manageConfig.getQualityControl());
		UserQualification userQualification = userQualificationMapper.selectByPrimaryKey(Long.parseLong(qualificationId));
		if(userQualification == null){
			return null;
		}
		bean.setCodeMust(userQualification.getCodeMust());
		bean.setFileMust(userQualification.getFileMust());
		bean.setGradeMust(userQualification.getGradeMust());
		bean.setRegisterCodeMust(userQualification.getRegisterCodeMust());
		bean.setRegionMust(userQualification.getRegionMust());
		bean.setCategoryMust(userQualification.getCategoryMust());
		bean.setRangeMust(userQualification.getRangeMust());
		return bean;
	}

	private Boolean checkUpdateEnterpriseData(Long id) {
		EnterpriseQualification enterpriseQualification = enterpriseQualificationMapper.selectByPrimaryKey(id);
		if (enterpriseQualification.getSysType() != 1) {
			return true;
		} else {
			return false;
		}
	}

	private Boolean checkUpdateUserData(Long id) {
		UserQualification userQualification = userQualificationMapper.selectByPrimaryKey(id);
		if (userQualification.getSysType() != 1) {
			return true;
		} else {
			return false;
		}
	}

	private Boolean checkUpdateGoodsData(Long id) {
		GoodsQualification goodsQualification = goodsQualificationMapper.selectByPrimaryKey(id);
		if (goodsQualification.getSysType() != 1) {
			return true;
		} else {
			return false;
		}
	}

}
