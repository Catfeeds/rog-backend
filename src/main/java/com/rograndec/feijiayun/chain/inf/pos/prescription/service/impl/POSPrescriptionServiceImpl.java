package com.rograndec.feijiayun.chain.inf.pos.prescription.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.dao.PharmacySetMapper;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.PharmacySetForPrescVO;
import com.rograndec.feijiayun.chain.business.member.info.dao.MemberInfoMapper;
import com.rograndec.feijiayun.chain.business.member.info.entity.MemberInfo;
import com.rograndec.feijiayun.chain.business.retail.prescription.dao.PrescriptionRegisterMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.SysRoleMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.SysRole;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.SaleMode;
import com.rograndec.feijiayun.chain.common.constant.SexType;
import com.rograndec.feijiayun.chain.common.constant.status.PrescriptionStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.inf.pos.prescription.service.POSPrescriptionService;
import com.rograndec.feijiayun.chain.inf.pos.prescription.vo.PrescriptionParamVO;
import com.rograndec.feijiayun.chain.inf.pos.prescription.vo.PrescriptionUserVO;
import com.rograndec.feijiayun.chain.inf.pos.prescription.vo.PrescriptionVO;
import com.rograndec.feijiayun.chain.inf.pos.prescription.vo.UserParamVO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class POSPrescriptionServiceImpl implements POSPrescriptionService{

	private static final Logger logger = LoggerFactory.getLogger(POSPrescriptionServiceImpl.class);
	
	@Autowired
    private PrescriptionRegisterMapper prescriptionRegisterMapper;
	
	@Autowired
	private MemberInfoMapper memberInfoMapper;
	
	@Autowired
	private SaleMapper saleMapper;
	
	@Autowired
	private PharmacySetMapper pharmacySetMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Override
	public List<PrescriptionVO> selectPrescriptionDataByEnterpriseId(
			Long enterpriseId, Long parentId) {
		// 查询待支付状态的单据
		List<PrescriptionVO> list = prescriptionRegisterMapper.selectPrescriptionDataByEnterpriseId(enterpriseId, PrescriptionStatus.MIXED);
		
		if(list != null){
			for (PrescriptionVO prescriptionVO : list) {
				SexType sex = SexType.getSexType4Code(prescriptionVO.getSex());
				if(sex != null){
					prescriptionVO.setSexName(sex.getName());
				}
				
				if(StringUtils.isNotBlank(prescriptionVO.getPatientId())){
					List<MemberInfo> memberList = memberInfoMapper.selectByEnterpriseIdAndCode(enterpriseId, prescriptionVO.getPatientId(), parentId==null?0L:parentId);
					if(memberList != null && memberList.size() > 0){
						prescriptionVO.setMemberName(memberList.get(0).getMemberName());
						prescriptionVO.setCurrentIntegral(memberList.get(0).getCurrentIntegral());
					}else {
						prescriptionVO.setPatientId("");
					}
				}
			}
		}
		return list;
	}

	@Override
	public List<PharmacySetForPrescVO> selectPharmacySetData(Long enterpriseId,
			Long parentId, PrescriptionParamVO param) {
		List<PharmacySetForPrescVO> list = pharmacySetMapper.getPharmacySetByParam((parentId==null||parentId==0)?enterpriseId:parentId,
				param.getSetType(), EnableStatus.ENABLE.getStatus());
		return list;
	}

	@Override
	public List<PrescriptionUserVO> selectPrescriptionUserData(UserVO userVO,
			UserParamVO param) {
		
		String roleCode = "";
		//0-审核人；1-调配人；2-核对人；3-发药人
		//需要等默认数据出来后根据默认数据更改roleCode
		
		if(param.getSaleMode() != null && param.getSaleMode() == SaleMode.CONVENTIONAL.getCode()){
			switch (param.getUserRoleType()) {
			case 0:
				roleCode = "030601";
				break;
			case 1:
				roleCode = "030621";
				break;	
			case 2:
				roleCode = "030611";
				break;
			case 3:
				roleCode = "030631";
				break;	
			default:
				break;
			}
		}else if(param.getSaleMode() != null && param.getSaleMode() == SaleMode.CHINESE_MEDICINE.getCode()){
			switch (param.getUserRoleType()) {
			case 0:
				roleCode = "030602";
				break;
			case 1:
				roleCode = "030622";
				break;	
			case 2:
				roleCode = "030612";
				break;
			case 3:
				roleCode = "030632";
				break;	
			default:
				break;
			}
		}
		
		List<SysRole> roList = sysRoleMapper.selectRoleByRoleCode(roleCode, 
				(userVO.getParentId()==null||userVO.getParentId()==0)?userVO.getEnterpriseId():userVO.getParentId());
		
		List<PrescriptionUserVO> list = userMapper.selectUserByRoleCode(roList, userVO.getEnterpriseId());
		
		return list;
	}

	/*@Override
	public String updatePrescriptionDataByParam(PrescriptionParamVO param) throws Exception {
		PrescriptionRegister re = prescriptionRegisterMapper.selectByPrimaryKey(param.getId());
		if(!param.getEnterpriseId().equals(re.getEnterpriseId())){
			return "企业ID不符！"; 
		}

		List<Sale> saleList = saleMapper.queryByCodeAndEnterpriseId(param.getSaleCode(), param.getEnterpriseId());
		if(saleList != null && saleList.size() > 0){
			re.setBaseOrderId(saleList.get(0).getId());
		}else{
			ExecutorService executors = Executors.newFixedThreadPool(1);
			try {
				executors.execute(()->{
					try {
						Thread.sleep(5000);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					List<Sale> saleList2 = saleMapper.queryByCodeAndEnterpriseId(param.getSaleCode(), param.getEnterpriseId());
					if(saleList2 != null && saleList2.size() > 0){
						re.setBaseOrderId(saleList2.get(0).getId());
						prescriptionRegisterMapper.updateByPrimaryKeySelective(re);
					}
				});
			} catch (Exception e) {
				logger.error("生成单据线程执行出错，错误信息：", e);
			} finally {
				int waitTime = 1000;  
				executors.shutdown();
				executors.awaitTermination(waitTime, TimeUnit.MICROSECONDS);
			}
		}
		re.setStatus(33);
		re.setBaseOrderCode(param.getSaleCode());
		re.setBaseOrderType(6212);
		re.setBaseOrderDate(param.getSaleDate());
		
		prescriptionRegisterMapper.updateByPrimaryKeySelective(re);
		return "";
	}*/


}
