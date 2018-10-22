package com.rograndec.feijiayun.chain.business.member.integral.service.impl;

import com.rograndec.feijiayun.chain.business.member.info.dao.MemberInfoMapper;
import com.rograndec.feijiayun.chain.business.member.info.entity.MemberInfo;
import com.rograndec.feijiayun.chain.business.member.info.vo.MemberInfoPageVO;
import com.rograndec.feijiayun.chain.business.member.integral.dao.IntegralRecordMapper;
import com.rograndec.feijiayun.chain.business.member.integral.entity.IntegralRecord;
import com.rograndec.feijiayun.chain.business.member.integral.service.IntegralRecordService;
import com.rograndec.feijiayun.chain.business.member.set.dao.MemberCardTypeMapper;
import com.rograndec.feijiayun.chain.business.member.set.entity.MemberCardType;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseBusinessService;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.component.MemberExcelComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class IntegralRecordServiceImpl implements IntegralRecordService{

    @Autowired
    private MemberInfoMapper memberInfoMapper;

    @Autowired
    private MemberCardTypeMapper memberCardTypeMapper;

    @Autowired
    private IntegralRecordMapper integralRecordMapper;

    @Autowired
    private MemberExcelComponent memberExcelComponent;

    @Autowired
    private EnterpriseBusinessService enterpriseBusinessService;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private EnterpriseBusinessMapper enterpriseBusinessMapper;

    @Autowired
    private CommonComponent commonComponent;

    @Override
    public Page getMemberInfoPage(Page page, String param, Long cardTypeId, Long cardLevelId, Date startSendCardTime, Date endSendCardTime, Integer chainType, String sendCardStorerCode, String sendCardStorerName, String sort, UserVO userVO) {
        Map<String,Object> map = new HashMap<String,Object>();
        Long enterpriseId = userVO.getEnterpriseId();
        //门店: 会员管理由总部控制时，读取总部的设置
        EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(enterpriseId);
        if (!userVO.getChainType().equals(ChainType.Headquarters.getCode())
                && 0 == enterpriseBus.getMemberInfo()){
            enterpriseId = userVO.getParentId();
        }

        if ("".equals(param)){
            param = null;
        }
        map.put("param",param);
        map.put("cardTypeId",cardTypeId);
        map.put("cardLevelId",cardLevelId);
        map.put("startSendCardTime",startSendCardTime);
        map.put("endSendCardTime",endSendCardTime);
        map.put("chainType",chainType);
        if ("".equals(sendCardStorerCode)){
            sendCardStorerCode = null;
        }
        if ("".equals(sendCardStorerName)){
            sendCardStorerName = null;
        }
        map.put("sendCardStorerCode",sendCardStorerCode);
        map.put("sendCardStorerName",sendCardStorerName);
        map.put("sort",sort);
        map.put("enterpriseId",enterpriseId);
        map.put("pageSize",page.getPageSize());
        map.put("start",(page.getPageNo()-1)*page.getPageSize());
        //右下角统计值的统计积分和当前积分
        BigDecimal stasticTotalIntegral = BigDecimal.ZERO;
        BigDecimal stasticCurrentIntegral = BigDecimal.ZERO;
        stasticTotalIntegral = memberInfoMapper.selectRecordTotalIntegral(map);
        stasticCurrentIntegral = memberInfoMapper.selectRecordCurrentIntegral(map);
        List<MemberInfoPageVO> list = memberInfoMapper.getIntegralRecordPage(map);
        for (MemberInfoPageVO m:list) {
            m.setStasticTotalIntegral(stasticTotalIntegral == null ? BigDecimal.ZERO : stasticTotalIntegral);
            m.setStasticCurrentIntegral(stasticCurrentIntegral == null ? BigDecimal.ZERO : stasticCurrentIntegral);
        }
        page.setResult(list);
        Integer totalRecord = memberInfoMapper.getIntegralRecordTotal(map);
        page.setTotalRecord(totalRecord);
        return page;
    }

    @Override
    public List<MemberCardType> getCardType(Long enterpriseId) {
        List<MemberCardType> list = memberCardTypeMapper.getCardTypeListByIntegralRecord(enterpriseId);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void addOrSubIntegral(String operation, MemberInfoPageVO memberInfoPageVO, UserVO loginUser) throws Exception{
        EnterpriseBusiness enterpriseBusiness = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(loginUser.getEnterpriseId());
        MemberInfo m = new MemberInfo();
        m.setId(memberInfoPageVO.getId());
        BigDecimal totalIntegral = memberInfoMapper.selectByPrimaryKey(memberInfoPageVO.getId()).getTotalIntegral();
        BigDecimal currentIntegral = memberInfoMapper.selectByPrimaryKey(memberInfoPageVO.getId()).getCurrentIntegral();
        memberInfoPageVO.setCurrentIntegral(currentIntegral);
        memberInfoPageVO.setTotalIntegral(totalIntegral);
        String cardCode = memberInfoMapper.selectByPrimaryKey(memberInfoPageVO.getId()).getCardCode();
        Long newMemberId = 0L;
        if ("add".equals(operation)){
            /**
             * 增加积分操作
             */
            totalIntegral = totalIntegral.add(memberInfoPageVO.getAddPoint());
            //第一步先增加会员信息表中的总积分和当前积分
            m.setTotalIntegral(totalIntegral);
            m.setCurrentIntegral(currentIntegral.add(memberInfoPageVO.getAddPoint()));
            memberInfoMapper.updateByPrimaryKeySelective(m);
        }else if ("sub".equals(operation)){
            /**
             * 减少积分操作
             */
            currentIntegral = currentIntegral.subtract(memberInfoPageVO.getSubPoint());
            m.setCurrentIntegral(currentIntegral);
            memberInfoMapper.updateByPrimaryKeySelective(m);
        }else if ("zero".equals(operation)){
            /**
             * 清零积分操作
             */
            m.setCurrentIntegral(new BigDecimal(0));
            memberInfoMapper.updateByPrimaryKeySelective(m);
        }else if ("change".equals(operation)){
            /**
             * 换卡操作
             */
            //第一部先将卡变为注销状态
            m.setStatus(3);
            memberInfoMapper.updateByPrimaryKeySelective(m);
            //新卡对象
            MemberInfo newMemberInfo = memberInfoMapper.selectNewCardId(loginUser.getChainType() == ChainType.Headquarters.getCode() ? loginUser.getEnterpriseId() : loginUser.getParentId(),memberInfoPageVO.getChangeCardCode());
            //插入新卡会员ID
            newMemberId = newMemberInfo.getId();
            //原卡对象
            MemberInfo oldMemberInfo = memberInfoMapper.selectByPrimaryKey(memberInfoPageVO.getId());
            //将原卡的基础信息转移到新卡上并且将新卡的状态变成正常
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(oldMemberInfo,newMemberInfo);
            newMemberInfo.setSendCardTime(new Date());
            newMemberInfo.setSendCardManId(loginUser.getUserId());
            newMemberInfo.setSendCardManCode(loginUser.getUserCode());
            newMemberInfo.setSendCardManName(loginUser.getUserName());
            newMemberInfo.setSendCardStorerId(loginUser.getEnterpriseId());
            newMemberInfo.setSendCardStorerCode(loginUser.getEnterpriseCode());
            newMemberInfo.setSendCardStorerName(loginUser.getEnterpriseName());
            //换卡之后将新卡的状态变为正常
            newMemberInfo.setId(newMemberId);
            newMemberInfo.setStatus(0);
            newMemberInfo.setCardCode(memberInfoPageVO.getChangeCardCode());
            UserEnterpriseUtils.setUserCreateOrModify(newMemberInfo,loginUser,false);
            //保存换卡新卡的信息
            memberInfoMapper.updateByPrimaryKeySelective(newMemberInfo);
            //将新卡ID塞到对象中
            memberInfoPageVO.setChangeCardId(newMemberInfo.getId());

        }
        //第二步插入积分修改信息
        //先把要插入的信息保存进去
        memberInfoPageVO.setCardCode(cardCode);
        memberInfoPageVO.setChangeCardId(newMemberId);
        IntegralRecord i = MemberInfoPageVO.convertToIntegralRecord(loginUser,memberInfoPageVO,operation);
        //新增积分操作编码
        switch (operation){
            case "add":
                i.setBaseOrderCode(orderCodeComponent.
                        generate(OrderRule.ADD_POINT.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode()));
                break;
            case "sub":
                i.setBaseOrderCode(orderCodeComponent.
                        generate(OrderRule.SUB_POINT.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode()));
                break;
            case "zero":
                i.setBaseOrderCode(orderCodeComponent.
                        generate(OrderRule.ZERO_POINT.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode()));
                break;
            case "change":
                i.setBaseOrderCode(orderCodeComponent.
                        generate(OrderRule.CHANGHE_CARD.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode()));
                break;
                default:
                    break;
        }
        if (!loginUser.getChainType().equals(ChainType.Headquarters.getCode()) && enterpriseBusiness != null
                && 0 == enterpriseBusiness.getMemberInfo()) {
            i.setEnterpriseId(loginUser.getParentId());
            i.setParentId(0L);
        }
        integralRecordMapper.insertSelective(i);
    }


    @Override
    public List<MemberInfoPageVO> exportMemberIntegral(String param, Long cardTypeId, Long cardLevelId, Date startSendCardTime, Date endSendCardTime, Integer chainType, String sendCardStorerCode, String sendCardStorerName, String sort, UserVO userVO) {
        Map<String,Object> map = new HashMap<String,Object>();
        Long enterpriseId = userVO.getEnterpriseId();
        //门店: 会员管理由总部控制时，读取总部的设置
        EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(enterpriseId);
        if (!userVO.getChainType().equals(ChainType.Headquarters.getCode())
                && 0 == enterpriseBus.getMemberInfo()){
            enterpriseId = userVO.getParentId();
        }
        map.put("param",param);
        map.put("cardTypeId",cardTypeId);
        map.put("cardLevelId",cardLevelId);
        map.put("startSendCardTime",startSendCardTime);
        map.put("endSendCardTime",endSendCardTime);
        map.put("chainType",chainType);
        map.put("sendCardStorerCode",sendCardStorerCode);
        map.put("sendCardStorerName",sendCardStorerName);
        map.put("sort",sort);
        map.put("enterpriseId",enterpriseId);
        List<MemberInfoPageVO> list = memberInfoMapper.getIntegralRecordPage(map);
        return list;
    }

    @Override
    public void exportExcel(OutputStream output, List<MemberInfoPageVO> list, UserVO loginUser) {
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("cardTypeName","会员卡类型");
        map.put("levelName","级别");
        map.put("cardCode","会员卡号");
        map.put("memberName","会员姓名");
        map.put("mobilePhone","手机");
        map.put("totalIntegral","累计积分");
        map.put("currentIntegral","当前积分");
        map.put("sendCardStorerName","发卡门店");
        map.put("sendCardTimeStr","发卡时间");
        for (MemberInfoPageVO vo : list) {
            if (vo.getSendCardTime() != null){
                vo.setSendCardTimeStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(vo.getSendCardTime()));
            }
        }
        List<String> name = new ArrayList<String>();
        name.add(loginUser.getEnterpriseName());
        name.add("积分管理");
        memberExcelComponent.commExcelExport(output,map,list,name);
    }

    @Override
    public MemberInfo getMemberInfo(Long id) {
        MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(id);
        return memberInfo;
    }

    @Override
    public void judgeCardCode(String changeCardCode, UserVO loginUser, Long cardTypeId) throws Exception{
//        Long enterpriseId = (loginUser.getChainType() == ChainType.Headquarters.getCode() ? loginUser.getEnterpriseId() : loginUser.getParentId());
        Long enterpriseId = loginUser.getEnterpriseId();
        EnterpriseBusiness enterpriseBus = commonComponent.queryEnterpriseBusinessByEnterpriseId(enterpriseId);
        if (enterpriseBus.getMemberInfo() == 0 && !loginUser.getChainType().equals(ChainType.Headquarters.getCode())){
            enterpriseId = loginUser.getParentId();
        }
        List<MemberInfo> mCode = memberInfoMapper.selectMemberCardExist(changeCardCode, enterpriseId);
        if (mCode.size()==0){
            throw new BusinessException(SysCode.FAIL.getCode(),"需要换的卡号不存在!");
        }
        MemberInfo mStatus = memberInfoMapper.selectMemberCardStatus(changeCardCode,enterpriseId);
        if (mStatus == null){
            throw new BusinessException(SysCode.FAIL.getCode(),"当前换卡的卡号状态不是未发卡状态!");
        }
        MemberInfo mType = memberInfoMapper.selectMemberCardType(changeCardCode,cardTypeId,enterpriseId);
        if (mType == null){
            throw new BusinessException(SysCode.FAIL.getCode(),"当前换卡的卡类型与原卡类型不一致!");
        }
    }
}
