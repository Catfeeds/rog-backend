package com.rograndec.feijiayun.chain.inf.pos.member.service.impl;

import com.rograndec.feijiayun.chain.business.member.info.dao.MemberInfoMapper;
import com.rograndec.feijiayun.chain.business.member.info.entity.MemberInfo;
import com.rograndec.feijiayun.chain.business.member.integral.dao.IntegralRecordMapper;
import com.rograndec.feijiayun.chain.business.member.integral.entity.IntegralRecord;
import com.rograndec.feijiayun.chain.business.member.set.dao.MemberCardTypeMapper;
import com.rograndec.feijiayun.chain.business.member.set.entity.MemberCardType;
import com.rograndec.feijiayun.chain.business.member.storedamount.dao.StoredAmountMapper;
import com.rograndec.feijiayun.chain.business.member.storedamount.entity.StoredAmount;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.inf.pos.member.dao.POSMemberMapper;
import com.rograndec.feijiayun.chain.inf.pos.member.service.POSMemberService;
import com.rograndec.feijiayun.chain.inf.pos.member.vo.POSSetMemberInfoVO;
import com.rograndec.feijiayun.chain.inf.pos.member.vo.SelectPOSMemberVO;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class POSMemberServiceImpl implements POSMemberService{
	
	@Autowired
	private POSMemberMapper pOSMemberMapper;
	
	@Autowired
    private MemberInfoMapper memberInfoMapper;
	
	@Autowired
    private MemberCardTypeMapper memberCardTypeMapper;
	
	@Autowired
	private IntegralRecordMapper integralRecordMapper;
	
	@Autowired
	private StoredAmountMapper storedAmountMapper;
	
	@Autowired
	private EnterpriseBusinessMapper enterpriseBusinessMapper;

	@Autowired
	private CommonComponent commonComponent;
	
	@Override
	public List<SelectPOSMemberVO> searchMember(String param,Long enterpriseId,Long parentId) throws Exception {
		return pOSMemberMapper.searchMember(param,enterpriseId,parentId);
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public String saveMember(POSSetMemberInfoVO madeCardInfoVO, UserVO loginUser) throws Exception {
		StringBuilder msg = new StringBuilder();
		/**
         * 先判断当前对象的type属性 0-积分+储值；1-仅积分(不需要获得储值金额,密码,确认密码)；2-仅储值
         * 发卡之后状态都变成未发卡
         */
		Long enterpriseId = loginUser.getEnterpriseId();
		EnterpriseBusiness enterpriseBus = commonComponent.queryEnterpriseBusinessByEnterpriseId(enterpriseId);
		if (enterpriseBus.getMemberInfo() == 0 && !loginUser.getChainType().equals(ChainType.Headquarters.getCode())){
			enterpriseId = loginUser.getParentId();
		}
		List<MemberInfo> memberExist = memberInfoMapper.selectMemberCardExist(madeCardInfoVO.getCardCode(),enterpriseId);
		if(memberExist.size()>0) {
			msg.append(madeCardInfoVO.getCardCode() + "该卡号已经存在；");
		}else {
			MemberCardType memberCardType = memberCardTypeMapper.selectByPrimaryKey(madeCardInfoVO.getCardTypeId());
			if(null != memberCardType) {
				madeCardInfoVO.setCardTypeName(memberCardType.getName());
			}
			MemberInfo memberInfo = convertEntity(madeCardInfoVO,loginUser,madeCardInfoVO.getCardCodeType());
			CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(madeCardInfoVO,memberInfo);
			// 总部控制
			EnterpriseBusiness enterpriseBusiness = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(loginUser.getEnterpriseId());
			if (!loginUser.getChainType().equals(ChainType.Headquarters.getCode())
					&& enterpriseBusiness != null && 0 == enterpriseBusiness.getMemberInfo()) {
				memberInfo.setEnterpriseId(loginUser.getParentId());
				memberInfo.setParentId(0L);
			}
			memberInfoMapper.insertSelective(memberInfo);
			// 细表添加数据
			saveMemberTypeData(memberCardType.getType(), memberInfo, loginUser);
			msg.append(memberInfo.getId());
		}
		return msg+"";
	}
	
	private void saveMemberTypeData(Integer type,MemberInfo memberInfo,UserVO loginUser) throws Exception {
		IntegralRecord irecord = setIntegralRecord(memberInfo,loginUser);
		StoredAmount srecord = setStoredAmount(memberInfo,loginUser);
		// 总部控制
		EnterpriseBusiness enterpriseBusiness = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(loginUser.getEnterpriseId());
		if (!loginUser.getChainType().equals(ChainType.Headquarters.getCode()) && enterpriseBusiness != null
				&& 0 == enterpriseBusiness.getMemberInfo()) {
			irecord.setEnterpriseId(loginUser.getParentId());
			irecord.setParentId(0L);
			//储值
			srecord.setEnterpriseId(loginUser.getParentId());
			srecord.setParentId(0L);
		}
		if(0 == type) {
			integralRecordMapper.insertSelective(irecord);
			storedAmountMapper.insertSelective(srecord);
		}
		if(1 == type) {
			integralRecordMapper.insertSelective(irecord);
		}
		if(2 == type) {
			storedAmountMapper.insertSelective(srecord);
		}
	}
	
	private IntegralRecord setIntegralRecord(MemberInfo memberInfo,UserVO loginUser) throws Exception {
		IntegralRecord irecord = new IntegralRecord();
		irecord = (IntegralRecord)EntityUtils.reflectAddSetDefaultValue(irecord.getClass(), loginUser);
		irecord.setEnterpriseId(memberInfo.getEnterpriseId());
		irecord.setParentId(memberInfo.getParentId());
		irecord.setChangeType(4);
		irecord.setMemberId(memberInfo.getId());
		irecord.setCardCode(memberInfo.getCardCode());
		irecord.setNewMemberId(memberInfo.getId());
		irecord.setNewCardCode(memberInfo.getCardCode());
		irecord.setTotalIntegral(memberInfo.getTotalIntegral());
		irecord.setCurrentIntegral(memberInfo.getCurrentIntegral());
		irecord.setChangeIntegral(BigDecimal.ZERO);
		irecord.setNewTotalIntegral(memberInfo.getTotalIntegral());
		irecord.setNewCurrentIntegral(memberInfo.getCurrentIntegral());
		irecord.setStatus(EnableStatus.ENABLE.getStatus());
		irecord.setRemark("POS开卡");
		return irecord;
	}
	
	private StoredAmount setStoredAmount(MemberInfo memberInfo,UserVO loginUser) throws Exception {
		StoredAmount srecord = new StoredAmount();
		srecord = (StoredAmount)EntityUtils.reflectAddSetDefaultValue(srecord.getClass(), loginUser);
		srecord.setEnterpriseId(memberInfo.getEnterpriseId());
		srecord.setParentId(memberInfo.getParentId());
		srecord.setChangeType(4);
		srecord.setMemberId(memberInfo.getId());
		srecord.setCardCode(memberInfo.getCardCode());
		srecord.setTotalStoredAmount(memberInfo.getTotalStoredAmount());
		srecord.setCurrentStoredAmount(memberInfo.getCurrentStoredAmount());
		srecord.setChangeStoredAmount(BigDecimal.ZERO);
		srecord.setNewTotalStoredAmount(memberInfo.getTotalStoredAmount());
		srecord.setNewCurrentStoredAmount(memberInfo.getCurrentStoredAmount());
		srecord.setStatus(EnableStatus.ENABLE.getStatus());
		srecord.setRemark("POS开卡");
		return srecord;
	}
	
	private static MemberInfo convertEntity(POSSetMemberInfoVO madeCardInfoVO, UserVO loginUser, Integer type) throws Exception {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo = (MemberInfo)EntityUtils.reflectAddSetDefaultValue(memberInfo.getClass(), loginUser);
        memberInfo.setEnterpriseId(madeCardInfoVO.getEnterpriseId());
        memberInfo.setParentId(madeCardInfoVO.getParentId());
        memberInfo.setCardTypeId(madeCardInfoVO.getId());
        memberInfo.setCardTypeName(madeCardInfoVO.getCardTypeName());
        memberInfo.setCardCode(madeCardInfoVO.getCardCode());
        //发卡信息
        memberInfo.setSendCardManId(loginUser.getUserId());
        memberInfo.setSendCardManCode(loginUser.getUserCode());
        memberInfo.setSendCardManName(loginUser.getUserName());
        memberInfo.setSendCardStorerId(loginUser.getEnterpriseId());
        memberInfo.setSendCardStorerCode(loginUser.getEnterpriseCode());
        memberInfo.setSendCardStorerName(loginUser.getEnterpriseName());
		memberInfo.setSendCardTime(new Date());
        /**
         * 因为制卡时只能制会员卡 0-会员卡 1-手机号
         */
        memberInfo.setCardCodeType(0);
        memberInfo.setStatus(0);
        if (1 != type){
            memberInfo.setStoredAmount(madeCardInfoVO.getStoredAmount());
            //初始化累计储值和当前储值
            memberInfo.setTotalStoredAmount(madeCardInfoVO.getStoredAmount());
            memberInfo.setCurrentStoredAmount(madeCardInfoVO.getStoredAmount());
            memberInfo.setTotalIntegral(BigDecimal.ZERO);
            memberInfo.setCurrentIntegral(BigDecimal.ZERO);
            memberInfo.setPassword(madeCardInfoVO.getPassword());
            memberInfo.setPasswordConfirm(madeCardInfoVO.getPasswordConfirm());
        }else{
            //初始化累计储值和当前储值
            memberInfo.setTotalStoredAmount(BigDecimal.ZERO);
            memberInfo.setCurrentStoredAmount(BigDecimal.ZERO);
            memberInfo.setTotalIntegral(BigDecimal.ZERO);
            memberInfo.setCurrentIntegral(BigDecimal.ZERO);
        }
        return memberInfo;
    }

}
