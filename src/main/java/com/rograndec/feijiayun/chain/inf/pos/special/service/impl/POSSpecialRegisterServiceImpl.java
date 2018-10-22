package com.rograndec.feijiayun.chain.inf.pos.special.service.impl;

import com.rograndec.feijiayun.chain.business.member.info.dao.MemberInfoMapper;
import com.rograndec.feijiayun.chain.business.member.info.entity.MemberInfo;
import com.rograndec.feijiayun.chain.business.retail.special.dao.SpecialRegisterMapper;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.inf.pos.special.service.POSSpecialRegisterService;
import com.rograndec.feijiayun.chain.inf.pos.special.vo.POSSpecialRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class POSSpecialRegisterServiceImpl implements POSSpecialRegisterService{

	@Autowired
	private SpecialRegisterMapper specialRegisterMapper;
	
	@Autowired
	private MemberInfoMapper memberInfoMapper;
	
	@Override
	public List<POSSpecialRegisterVO> selectSpecialRegisterDataByEnterpriseId(
			Long enterpriseId, Long parentId) {
		//,-1-已取消 0-全部 92-待收款 33-已完成
		List<POSSpecialRegisterVO> list = specialRegisterMapper.selectSpecialRegisterDataByEnterpriseId(enterpriseId, PurchaseStatus.WAIT_PAY.getStatus());
		
		if(list != null){
			for (POSSpecialRegisterVO vo : list) {
				
				if(vo.getMemberId()!=null&&vo.getMemberId()!=0){
					MemberInfo member = memberInfoMapper.selectByPrimaryKey(vo.getMemberId());
					if(member != null){
						vo.setCurrentIntegral(member.getCurrentIntegral());
					}else {
						vo.setMemberId(null);
						vo.setMemberCardCode("");
						vo.setMemberName("");
					}
				}
			}
		}
		return list;
	}

}
