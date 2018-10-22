package com.rograndec.feijiayun.chain.inf.pos.pricing.service.impl;

import com.rograndec.feijiayun.chain.business.member.info.dao.MemberInfoMapper;
import com.rograndec.feijiayun.chain.business.member.info.entity.MemberInfo;
import com.rograndec.feijiayun.chain.business.retail.pricing.dao.SalePricingMapper;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.inf.pos.pricing.service.POSSalePricingService;
import com.rograndec.feijiayun.chain.inf.pos.pricing.vo.POSSalePricingVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class POSSalePricingServiceImpl implements POSSalePricingService{

	@Autowired
	private SalePricingMapper salePricingMapper;
	
	@Autowired
	private MemberInfoMapper memberInfoMapper;
	
	@Override
	public List<POSSalePricingVO> selectSalePricingDataByEnterpriseId(
			Long enterpriseId, Long parentId) {
		//状态（92-待支付,33-已完成,34-已取消）
		List<POSSalePricingVO> list = salePricingMapper.selectSalePricingDataByEnterpriseId(enterpriseId, PurchaseStatus.WAIT_PAY.getStatus());
		
		if(list != null){
			for (POSSalePricingVO salePricingVO : list) {
				
				if(StringUtils.isNotBlank(salePricingVO.getMemberCardCode())){
					List<MemberInfo> memberList = memberInfoMapper.selectByEnterpriseIdAndCode(enterpriseId, salePricingVO.getMemberCardCode(), parentId==null?0L:parentId);
					if(memberList != null && memberList.size() > 0){
						salePricingVO.setMemberName(memberList.get(0).getMemberName());
						salePricingVO.setCurrentIntegral(memberList.get(0).getCurrentIntegral());
					}else {
						salePricingVO.setMemberCardCode("");
					}
				}
			}
		}
		return list;
	}
	
	

}
