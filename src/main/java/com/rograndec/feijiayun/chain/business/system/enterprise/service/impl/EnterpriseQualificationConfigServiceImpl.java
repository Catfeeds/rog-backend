package com.rograndec.feijiayun.chain.business.system.enterprise.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseQualificationConfigMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseQualificationConfig;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseModifyRecordService;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseQualificationConfigService;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.EnterpriseQualificationConfigBean;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.EnterpriseQualificationConfigVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.QualificationValidateVO;
import com.rograndec.feijiayun.chain.business.system.set.dao.EnterpriseQualificationMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.EnterpriseQualification;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.SelectBean;
import com.rograndec.feijiayun.chain.common.file.dao.FileMapper;
import com.rograndec.feijiayun.chain.common.file.entity.File;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

@Service
public class EnterpriseQualificationConfigServiceImpl implements EnterpriseQualificationConfigService{

	private static final Log logger = LogFactory.getLog(EnterpriseQualificationConfigServiceImpl.class);
	
	@Autowired
	EnterpriseQualificationConfigMapper enterpriseQualificationConfigMapper;
	
	@Autowired
	EnterpriseQualificationMapper enterpriseQualificationMapper;
	
	@Autowired
	ManageConfigMapper manageConfigMapper;
	
	@Autowired
	EnterpriseMapper enterpriseMapper;
	
	@Autowired
	EnterpriseModifyRecordService enterpriseModifyRecordService;
	
	@Autowired
	FileMapper fileMapper;
	
	@Override
	public List<EnterpriseQualificationConfigVO> selectEnterpriseQualificationByEnterpriseId(
			Enterprise enterprise) {
		List<EnterpriseQualificationConfigVO> voList = new ArrayList<EnterpriseQualificationConfigVO>();
		
		//查询企业资质表数据
		List<EnterpriseQualificationConfig> list = enterpriseQualificationConfigMapper.selectEnterpriseQualificationByEnterpriseId(enterprise.getId());
	
		String suitableUnit = "";//适用机构
		if(enterprise.getParentId() != 0){
			suitableUnit = "2";//分店
		}else {
			suitableUnit = "1";//总部
		}
		
		String typeMust = "1";//必选
		//查询资质基础表必选数据
		Long parentId = (enterprise.getParentId() == null || enterprise.getParentId() == 0) 
				? enterprise.getId() : enterprise.getParentId();
		List<EnterpriseQualification> listQualification = enterpriseQualificationMapper.selectEntrepriseOptionalQualification(
				parentId, suitableUnit, typeMust);
		
		Map<Long, Long> qualificationMap = new HashMap<Long, Long>();
		if(list != null){
			for (EnterpriseQualificationConfig config : list) {
				qualificationMap.put(config.getQualificationId(), config.getId());
				EnterpriseQualificationConfigVO vo = setQualificationConfigToConfigVO(config);
				vo.setEnterpriseId(enterprise.getId());
				vo.setParentId(enterprise.getParentId());
				voList.add(vo);
			}
		}
		
		//合并
		if(listQualification != null){
			for (EnterpriseQualification qualification : listQualification) {
				if(!qualificationMap.containsKey(qualification.getId())){
					qualificationMap.put(qualification.getId(), 0L);
					EnterpriseQualificationConfigVO vo = setQualificationToConfigVO(qualification);
					vo.setEnterpriseId(enterprise.getId());
					vo.setParentId(enterprise.getParentId());
					voList.add(vo);
				}
			}
		}
		
		return voList;
	}

	/**
	 * 企业信息资质表 转 vo
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年8月23日 上午11:44:37 
	 * @param qualification
	 * @return 
	 * @return EnterpriseQualificationConfig
	 */
	private EnterpriseQualificationConfigVO setQualificationConfigToConfigVO(
			EnterpriseQualificationConfig config) {
		EnterpriseQualificationConfigVO configVO = new EnterpriseQualificationConfigVO();
		configVO.setId(config.getId());
		configVO.setQualificationId(config.getQualificationId());
		configVO.setQualificationCode(config.getQualificationCode());
		File file = null;
		try {
			EnterpriseQualification qualification = enterpriseQualificationMapper.selectByPrimaryKey(config.getQualificationId());
			
			configVO.setTypeMust(qualification == null ? null : qualification.getTypeMust());
			String typeMustName = "";
			if(qualification!=null&&qualification.getTypeMust()==0){
				typeMustName = "可选";
			}else if(qualification!=null&&qualification.getTypeMust()==1){
				typeMustName = "必选";
			}
			configVO.setTypeMustName(typeMustName);
			
			configVO.setControlType(qualification == null ? null : qualification.getControlType());
			String controlTypeName = "";
			if(qualification!=null&&qualification.getControlType()==0){
				controlTypeName = "质量控制";
			}else if(qualification!=null&&qualification.getControlType()==1){
				controlTypeName = "仅提醒";
			}
			configVO.setControlTypeName(controlTypeName);
			
			configVO.setName(qualification == null ? "" : qualification.getName());

			file = fileMapper.selectByPrimaryKey(config.getFileId());
		} catch (Exception e) {
			logger.error("根据企业资质表资质ID查询资质基表失败！", e);
			e.printStackTrace();
		}
		
		configVO.setValidUntil(config.getValidUntil());//有效期至
		configVO.setFileId(config.getFileId()==0?null:config.getFileId());
		configVO.setFileName(file == null ? "" : file.getFileName());
		configVO.setSource(1);
		return configVO;
	}

	/**
	 * 资质基表 转 vo
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年8月23日 上午11:44:37 
	 * @param qualification
	 * @return 
	 * @return EnterpriseQualificationConfig
	 */
	private EnterpriseQualificationConfigVO setQualificationToConfigVO(
			EnterpriseQualification qualification) {
		EnterpriseQualificationConfigVO configVO = new EnterpriseQualificationConfigVO();
		configVO.setId(0L);
		configVO.setQualificationId(qualification.getId());
		configVO.setQualificationCode("");
		configVO.setTypeMust(qualification.getTypeMust());
		configVO.setControlType(qualification.getControlType());
		
		String typeMustName = "";
		if(qualification!=null&&qualification.getTypeMust()==0){
			typeMustName = "可选";
		}else if(qualification!=null&&qualification.getTypeMust()==1){
			typeMustName = "必选";
		}
		configVO.setTypeMustName(typeMustName);
		
		String controlTypeName = "";
		if(qualification!=null&&qualification.getControlType()==0){
			controlTypeName = "质量控制";
		}else if(qualification!=null&&qualification.getControlType()==1){
			controlTypeName = "仅提醒";
		}
		configVO.setControlTypeName(controlTypeName);
		
		configVO.setName(qualification.getName());
		configVO.setValidUntil(null);//有效期至
		configVO.setFileId(null);
		configVO.setFileName("");
		configVO.setSource(0);
		return configVO;
	}

	@Override
	public QualificationValidateVO queryEnterpriseQualificationValidateByEnterpriseIdAndId(
			Long enterpriseId, String entrepriseQualificationId) {
		QualificationValidateVO bean = new QualificationValidateVO();
//		ManageConfig manageConfig = manageConfigMapper.selectManageConfigByEnterpriseId(enterpriseId);
//		if(manageConfig == null){
			
			Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
			if(enterprise == null){
				
				return null;
			}
			ManageConfig manageConfig = manageConfigMapper.selectManageConfigByEnterpriseId(
					(enterprise.getParentId()==null||enterprise.getParentId()==0) ? enterprise.getId() : enterprise.getParentId());
			
			if(manageConfig == null){
				
				return null;
			}
//		}
		
		bean.setQualityControl(manageConfig.getQualityControl());

		//查询资质基表
		EnterpriseQualification qualification = enterpriseQualificationMapper.selectByPrimaryKey(Long.parseLong(entrepriseQualificationId));
		
		if(qualification == null){
		
			return null;
		}
		bean.setCodeMust(qualification.getCodeMust());
		bean.setValidUntilMust(qualification.getValidUntilMust());
		bean.setFileMust(qualification.getFileMust());
			
		return bean;	
	}

	@Override
	public List<SelectBean> selectEntrepriseOptionalQualification(
			Enterprise enterprise) {
		String suitableUnit = "";//适用机构
		if(enterprise.getParentId()==null || enterprise.getParentId() != 0){
			suitableUnit = "2";//分店
		}else {
			suitableUnit = "1";//总部
		}
		String typeMust = "0";//可选
		
		List<EnterpriseQualification> list = enterpriseQualificationMapper.selectEntrepriseOptionalQualification(
				(enterprise.getParentId()==null||enterprise.getParentId()==0)?enterprise.getId():enterprise.getParentId(),
						suitableUnit, typeMust);
		
		List<EnterpriseQualificationConfig> listEnterprise = enterpriseQualificationConfigMapper.
				selectEnterpriseQualificationByEnterpriseId(enterprise.getId());
		
		Map<Long, Long> map = new HashMap<>();
		if(listEnterprise != null){
			
			for (EnterpriseQualificationConfig enterpriseQualificationConfig : listEnterprise) {

				map.put(enterpriseQualificationConfig.getQualificationId(),enterpriseQualificationConfig.getQualificationId());
			}
		}
		
		List<SelectBean> beanList = new ArrayList<>(); 
		if(list != null){
			SelectBean bean = null;
			for (EnterpriseQualification enterpriseQualification : list) {
				if(!map.containsKey(enterpriseQualification.getId()) && enterpriseQualification.getTypeMust() == 0){
					bean = new SelectBean();
					bean.setId(enterpriseQualification.getId().toString());
					bean.setText(enterpriseQualification.getName());
					beanList.add(bean);
				}
			}
		}
		
		return beanList;
	}

	@Override
	public String saveOrUpdate(EnterpriseQualificationConfigBean configBean, Enterprise enterprise, 
			UserVO loginUser) {
		
		EnterpriseQualificationConfig config = new EnterpriseQualificationConfig();
		
		boolean typeMust = false;//是否必选
		
		try {
			BeanUtils.copyProperties(config, configBean);
		} catch (Exception e) {
			config = setEnterpriseQualificationConfigByBean(configBean);
		}
		
		//保存企业资质验证
		//基础设置-管理设置
		ManageConfig manageConfig = manageConfigMapper.selectManageConfigByEnterpriseId(loginUser.getEnterpriseId());
		if(manageConfig == null){
			
			manageConfig = manageConfigMapper.selectManageConfigByEnterpriseId(enterprise.getParentId());
			
		}
		
		//查询资质基表
		EnterpriseQualification qualification = enterpriseQualificationMapper.selectByPrimaryKey(config.getQualificationId());
		
		if(qualification == null){
			
			return "资质ID不在范围内!";
		}
		
		if(manageConfig != null && manageConfig.getQualityControl() != null && manageConfig.getQualityControl() == 1){//开启基础数据质量控制
			
			if(qualification.getTypeMust() == 1){
				
				typeMust = true;
			}
			
			if(1 == qualification.getCodeMust()){//资质编号必填
				
				if(StringUtils.isBlank(config.getQualificationCode())){
					return "开启基础数据质量控制,资质编号必填!";
				}
			}
			if(1 == qualification.getValidUntilMust()){//有效期必填
				
				if(config.getValidUntil() == null){
					return "开启基础数据质量控制,有效期必填!";
				}
			}
			if(1 == qualification.getFileMust()){//附件必填
				
				if(config.getFileId() == null || config.getFileId() == 0){
					return "开启基础数据质量控制,附件必填!";
				}
			}
			
		}
		
		if(config.getId() == null || config.getId() == 0){
			config.setCreaterId(loginUser.getUserId());
			config.setCreaterCode(loginUser.getUserCode());
			config.setCreaterName(loginUser.getUserName());
			config.setCreateTime(new Date());
			config.setModifierId(loginUser.getUserId());
			config.setModifierCode(loginUser.getUserCode());
			config.setModifierName(loginUser.getUserName());
			config.setUpdateTime(new Date());
			enterpriseQualificationConfigMapper.insertSelective(config);
			
			if(typeMust == true){
				
				saveEnterpriseConfigModifyRecord(null, config, loginUser, "");
			}
		}else{
			
			EnterpriseQualificationConfig oldConfig = enterpriseQualificationConfigMapper.selectByPrimaryKey(config.getId());
			
			config.setModifierId(loginUser.getUserId());
			config.setModifierCode(loginUser.getUserCode());
			config.setModifierName(loginUser.getUserName());
			config.setUpdateTime(new Date());
			enterpriseQualificationConfigMapper.updateByPrimaryKeySelective(config);
			
			saveEnterpriseConfigModifyRecord(oldConfig, config, loginUser, "");
		}
		
//		return setQualificationConfigToConfigVO(config);
		return "";
	}
	
	
	

	private void saveEnterpriseConfigModifyRecord(EnterpriseQualificationConfig oldConfig,
			EnterpriseQualificationConfig newConfig, UserVO loginUser,
			String string) {
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		try {
			singleThreadExecutor.execute(() -> {
				enterpriseModifyRecordService.saveEnterpriseConfigModifyRecord(oldConfig, newConfig, 
						loginUser, "");
			});
		} catch (Exception e) {
			logger.error("保存企业-资质修改记录失败！", e);
			e.printStackTrace();
		}finally{
			singleThreadExecutor.shutdown();
		}
		
	}

	/**
	 * @Description: 根据configBean -> config
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年8月25日 上午10:25:15 
	 * @param configBean
	 * @return 
	 * @return EnterpriseQualificationConfig
	 */
	private EnterpriseQualificationConfig setEnterpriseQualificationConfigByBean(
			EnterpriseQualificationConfigBean configBean) {
		
		EnterpriseQualificationConfig config = new EnterpriseQualificationConfig();
		config.setId(configBean.getId());
		config.setEnterpriseId(configBean.getEnterpriseId());
		config.setParentId(configBean.getParentId());
		config.setQualificationId(configBean.getQualificationId());
		config.setQualificationCode(configBean.getQualificationCode());
		config.setValidUntil(configBean.getValidUntil());
		config.setFileId(configBean.getFileId());
		config.setRemark(configBean.getRemark());
		return config;
	}

	@Override
	public int deleteEnterpriseQualificationConfig(String id, UserVO loginUser) {
		EnterpriseQualificationConfig config = enterpriseQualificationConfigMapper.selectByPrimaryKey(Long.parseLong(id));
		
		if(config == null){
			return 2;
		}
		
		EnterpriseQualification qua = enterpriseQualificationMapper.selectByPrimaryKey(config.getQualificationId());
		
		if(qua == null){
			return 3;
		}
		
		if(qua.getTypeMust() == 1){
			return 2;
		}
		int result = enterpriseQualificationConfigMapper.deleteByPrimaryKey(Long.parseLong(id));
		
		saveEnterpriseConfigModifyRecord(config, new EnterpriseQualificationConfig(), loginUser, "删除");
		
		return result;
	}

	@Override
	public String validateEnterpriseQualificationConfig(
			EnterpriseQualificationConfigBean configBean, Enterprise enterprise,
			UserVO loginUser) {
		
		//保存企业资质验证
		//基础设置-管理设置
		ManageConfig manageConfig = manageConfigMapper.selectManageConfigByEnterpriseId(loginUser.getEnterpriseId());
		if(manageConfig == null){
			
			manageConfig = manageConfigMapper.selectManageConfigByEnterpriseId(enterprise.getParentId());
			
		}
		
		//查询资质基表
		EnterpriseQualification qualification = enterpriseQualificationMapper.selectByPrimaryKey(configBean.getQualificationId());
		
		if(qualification == null){
			
			return "资质ID不在范围内!";
		}
		
		if(manageConfig != null && manageConfig.getQualityControl() != null && manageConfig.getQualityControl() == 1){//开启基础数据质量控制
			
			if(1 == qualification.getCodeMust()){//资质编号必填
				
				if(StringUtils.isBlank(configBean.getQualificationCode())){
					return "开启基础数据质量控制,资质编号必填!";
				}
			}
			if(1 == qualification.getValidUntilMust()){//有效期必填
				
				if(configBean.getValidUntil() == null){
					return "开启基础数据质量控制,有效期必填!";
				}
			}
			if(1 == qualification.getFileMust()){//附件必填
				
				if(configBean.getFileId() == null || configBean.getFileId() == 0){
					return "开启基础数据质量控制,附件必填!";
				}
			}
			
		}
		return "";
	}

	@Override
	public EnterpriseQualificationConfigVO transformationConfigToVo(
			EnterpriseQualificationConfigBean configBean) {
		EnterpriseQualificationConfig config = new EnterpriseQualificationConfig();
		
		try {
			BeanUtils.copyProperties(config, configBean);
		} catch (Exception e) {
			config = setEnterpriseQualificationConfigByBean(configBean);
		}
		
		return setQualificationConfigToConfigVO(config);
	}

}
