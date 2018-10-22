package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.business.basic.supplier.vo.RequestSupplierQualification;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierQulificationVO;
import com.rograndec.feijiayun.chain.business.system.set.dao.EnterpriseQualificationMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.UserQualificationMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.EnterpriseQualification;
import com.rograndec.feijiayun.chain.business.system.set.entity.UserQualification;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class SupplierQualificationComponent {

	@Autowired
	private EnterpriseQualificationMapper enterpriseQualificationMapper;

	@Autowired
	private UserQualificationMapper userQualificationMapper;

	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly=true)
	public List<EnterpriseQualification> findEnterpriseQualificationSelectorByEnterpriseId(Long enterpriseId, RequestSupplierQualification requestSupplierQualification) {
		//对于复制供货单位的情况
		if (requestSupplierQualification == null){
			return new ArrayList<>();
		}
		if (enterpriseId != null) {
			List<EnterpriseQualification> list = enterpriseQualificationMapper.findEnterpriseQualificationSelectorByEnterpriseId(enterpriseId,requestSupplierQualification.getId());
			List<Long> idList = requestSupplierQualification.getIdList();
			if (idList == null || idList.size() <= 0){
				return new ArrayList<>();
			}
			//需要根据经营品种来展现对应所存在的默认资质信息
			List<EnterpriseQualification> returnList = new ArrayList<>();
			if (list != null && list.size() > 0){
				list.forEach(l ->{
					String businessVariety = l.getBusinessVariety();
					String[] sp = businessVariety.split(",");
					boolean flag = false;
					for (String s : sp) {
						Long id = Long.parseLong(s);
						if (idList.contains(id)){
							flag = true;
						}
					}
					if (flag){
						returnList.add(l);
					}
				});
			}
			return returnList;
		}
		return null;
	}

	public List<UserQualification> getUserMustQualification4Enable(UserVO user) {
		List<UserQualification> userQualification = new ArrayList<UserQualification>();
		//不是总部的情况
		if (user.getChainType() != ChainType.Headquarters.getCode()) {
			userQualification = userQualificationMapper.getUserMustQualificationById4Enable(user);
		}else {
			userQualification = userQualificationMapper.getUserMustQualification4Enable(user);
		}
		for (UserQualification uq : userQualification) {
			uq.setChainType(user.getChainType());
		}
		return userQualification;
	}
}
