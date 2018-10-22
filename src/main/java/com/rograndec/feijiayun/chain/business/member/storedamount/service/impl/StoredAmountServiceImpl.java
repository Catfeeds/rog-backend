package com.rograndec.feijiayun.chain.business.member.storedamount.service.impl;

import com.github.pagehelper.PageHelper;
import com.netflix.discovery.converters.Auto;
import com.rograndec.feijiayun.chain.business.member.info.dao.MemberInfoMapper;
import com.rograndec.feijiayun.chain.business.member.info.entity.MemberInfo;
import com.rograndec.feijiayun.chain.business.member.info.utils.CardUtils;
import com.rograndec.feijiayun.chain.business.member.storedamount.dao.StoredAmountMapper;
import com.rograndec.feijiayun.chain.business.member.storedamount.entity.StoredAmount;
import com.rograndec.feijiayun.chain.business.member.storedamount.service.StoredAmountService;
import com.rograndec.feijiayun.chain.business.member.storedamount.vo.RequestStoredAmountVO;
import com.rograndec.feijiayun.chain.business.member.storedamount.vo.StoredAmountPageVO;
import com.rograndec.feijiayun.chain.business.member.storedamount.vo.StoredAmountTotalVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseBusinessService;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.MemberExcelComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import com.rograndec.feijiayun.chain.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/9/22 <br/>
 * 描述：积分-储值管理
 */

@Service
public class StoredAmountServiceImpl implements StoredAmountService {

    @Autowired
    private MemberInfoMapper memberInfoMapper;

    @Autowired
    private MemberExcelComponent memberExcelComponent;

    @Autowired
    private StoredAmountMapper storedAmountMapper;

    @Autowired
    private EnterpriseBusinessService enterpriseBusinessService;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private EnterpriseBusinessMapper enterpriseBusinessMapper;

    @Override
    public Page getMemberInfoPage(Page page, String param, Long cardTypeId, Long cardLevelId, Date startSendCardTime, Date endSendCardTime, Integer chainType, String sendCardStorerCode, String sendCardStorerName, UserVO userVO) {
        //门店: 会员管理由总部控制时，读取总部的设置
        Long enterpriseId = userVO.getEnterpriseId();
        EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(enterpriseId);
        if (!userVO.getChainType().equals(ChainType.Headquarters.getCode())
                && 0 == enterpriseBus.getMemberInfo()){
            enterpriseId = userVO.getParentId();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("param", param);
        map.put("cardTypeId", cardTypeId);
        map.put("cardLevelId", cardLevelId);
        map.put("startSendCardTime", startSendCardTime);
        map.put("endSendCardTime", endSendCardTime);
        map.put("chainType", chainType);
        map.put("sendCardStorerCode", sendCardStorerCode);
        map.put("sendCardStorerName", sendCardStorerName);
        map.put("enterpriseId", enterpriseId);
        //总计储值计算
        StoredAmountTotalVO totalVO = memberInfoMapper.countStoredAmountData(map);
        if (totalVO == null) {
            totalVO = new StoredAmountTotalVO();
        }

        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        //列表
        List<StoredAmountPageVO> list = memberInfoMapper.getStoredAmountPage(map);
        totalVO.setStoredAmountList(list);

        page.setResult(totalVO);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
        page.setTotalPage(hPage.getPages());
        return page;
    }

    @Override
    public List<StoredAmountPageVO> exportStoredAmount(String param, Long cardTypeId, Long cardLevelId, Date startSendCardTime, Date endSendCardTime, Integer chainType, String sendCardStorerCode, String sendCardStorerName, Long enterpriseId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("param", param);
        map.put("cardTypeId", cardTypeId);
        map.put("cardLevelId", cardLevelId);
        map.put("startSendCardTime", startSendCardTime);
        map.put("endSendCardTime", endSendCardTime);
        map.put("chainType", chainType);
        map.put("sendCardStorerCode", sendCardStorerCode);
        map.put("sendCardStorerName", sendCardStorerName);
        map.put("enterpriseId", enterpriseId);
        List<StoredAmountPageVO> list = memberInfoMapper.getStoredAmountPage(map);
        return list;
    }

    @Override
    public void exportExcel(OutputStream output, List<StoredAmountPageVO> list, UserVO loginUser) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("cardTypeName", "会员卡类型");
        map.put("levelName", "级别");
        map.put("cardCode", "会员卡号");
        map.put("memberName", "会员姓名");
        map.put("mobilePhone", "手机");
        map.put("totalStoredAmount", "累计储值");
        map.put("currentStoredAmount", "储值余额");
        map.put("sendCardStorerName", "发卡门店");
        map.put("sendCardTime", "发卡时间");
        List<String> name = new ArrayList<String>();
        name.add(loginUser.getEnterpriseName());
        name.add("储值管理");
        memberExcelComponent.commExcelExport(output, map, list, name);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int changeStoredAmount(UserVO loginUser, RequestStoredAmountVO requestStoredAmountVO) throws Exception {
        //****************************************************************************************************
        EnterpriseBusiness enterpriseBusiness = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(loginUser.getEnterpriseId());
        Long id = requestStoredAmountVO.getId();
        int changeType = requestStoredAmountVO.getChangeType();
        BigDecimal changeValue = requestStoredAmountVO.getChangeValue();
        String transferCardCode = requestStoredAmountVO.getTransferCardCode();
        //原密码
        String oldPassword = requestStoredAmountVO.getOldPassword();
        //新密码
        String newPassword = requestStoredAmountVO.getNewPassword();
        //输入密码
        String password = requestStoredAmountVO.getPassword();
        //确认密码
        String passwordConfirm = requestStoredAmountVO.getPasswordConfirm();

        //通过id事实获取会员卡相关信息
        StoredAmountPageVO storedAmountPageVO = memberInfoMapper.selectCurrentStoredAmount(id);

        //转账时，对方卡信息
        StoredAmountPageVO transferCardInfo = null;

        //储值改变金额为空或者0时的提示语
        String changeStoredIsEmpty = "";
        String changeStoredIsZero = "";
        //减少储值时，减少量不能大于当前余额提示语
        String changeGreaterThanCurrent = "储值余额不足！";
        switch (changeType) {
            case 0://充值
                changeStoredIsEmpty = "充值金额不能为空！";
                changeStoredIsZero = "充值金额必须大于0";
                break;
            case 1://扣款
                changeStoredIsEmpty = "扣款金额不能为空！";
                changeStoredIsZero = "扣款金额必须大于0";
                break;
            case 2://转账
                changeStoredIsEmpty = "转账金额不能为空！";
                changeStoredIsZero = "转账金额必须大于0";

                //转账卡号判空
                if (StringUtils.isEmpty(transferCardCode)) {
                    throw new BusinessException(SysCode.FAIL.getCode(),"转账卡号不能为空！");
                }

                //转账卡号为本卡
                if (transferCardCode.equals(storedAmountPageVO.getCardCode())) {
                    throw new BusinessException(SysCode.FAIL.getCode(),"转账对象不能为当前卡！");
                }

                // 当前为转账状态----用户填入的卡号必须存在且为积分储值卡或者储值卡
                transferCardInfo = memberInfoMapper.selectMemberByCardCode(transferCardCode);
                //会员卡不存在
                if (transferCardInfo == null) {
                    throw new BusinessException(SysCode.FAIL.getCode(),"转账卡号不存在！");
                }

                //会员卡为 积分卡,无储值功能
                if (transferCardInfo.getType() == 1) {
                    throw new BusinessException(SysCode.FAIL.getCode(),"转账卡号为积分卡，请重新输入！");
                }
                break;
            case 4:
                //修改密码
                if (StringUtils.isEmpty(oldPassword)){
                    throw new BusinessException(SysCode.FAIL.getCode(),"原始密码不能为空!");
                }
                if (StringUtils.isEmpty(newPassword)){
                    throw new BusinessException(SysCode.FAIL.getCode(),"新密码不能为空!");
                }
                if (StringUtils.isEmpty(passwordConfirm)){
                    throw new BusinessException(SysCode.FAIL.getCode(),"密码确认不能为空!");
                }
                if (!CardUtils.isNumeric(oldPassword) || !CardUtils.safeLength(oldPassword)){
                    throw new BusinessException(SysCode.FAIL.getCode(),"原始密码必须为6-20位的数字!");
                }
                if (!CardUtils.isNumeric(newPassword) || !CardUtils.safeLength(newPassword)){
                    throw new BusinessException(SysCode.FAIL.getCode(),"新密码必须为6-20位的数字!");
                }
                if (!CardUtils.isNumeric(passwordConfirm) || !CardUtils.safeLength(passwordConfirm)){
                    throw new BusinessException(SysCode.FAIL.getCode(),"密码确认必须为6-20位的数字!");
                }
                if (!passwordConfirm.equals(newPassword)){
                    throw new BusinessException(SysCode.FAIL.getCode(),"新密码与密码确认不一致!");
                }
                if (!oldPassword.equals(storedAmountPageVO.getPassword())){
                    throw new BusinessException(SysCode.FAIL.getCode(),"密码错误，请重新输入!");
                }
                break;
            default:
                throw new BusinessException(SysCode.FAIL.getCode(),"操作类型错误！");
        }
        //前三项都有的金钱判断,修改密oldPassword码没有这种选项
        //改变金额判空
        if (changeType == 0
                || changeType == 1
                || changeType == 2){
            if (changeValue == null) {
                throw new BusinessException(SysCode.FAIL.getCode(),changeStoredIsEmpty);
            }

            //改变金额等于小于0
            if (changeValue.compareTo(BigDecimal.ZERO) < 1) {
                throw new BusinessException(SysCode.FAIL.getCode(),changeStoredIsZero);
            }

            //当前储值余额
            BigDecimal currentStoredAmount = storedAmountPageVO.getCurrentStoredAmount();
            //扣款、转账金额判断：不能大于余额
            if ((changeType == 1 || changeType == 2) && changeValue.compareTo(currentStoredAmount) == 1) {
                throw new BusinessException(SysCode.FAIL.getCode(),changeGreaterThanCurrent);
            }
            //新增的密码之间的判断
            if (StringUtils.isEmpty(password)){
                throw new BusinessException(SysCode.FAIL.getCode(),"密码未输入!");
            }
            if (StringUtils.isEmpty(passwordConfirm)){
                throw new BusinessException(SysCode.FAIL.getCode(),"确认密码未输入!");
            }
            if (!CardUtils.isNumeric(password) || !CardUtils.safeLength(password)){
                throw new BusinessException(SysCode.FAIL.getCode(),"密码必须为5-20位的数字!");
            }
            if (!CardUtils.isNumeric(passwordConfirm) || !CardUtils.safeLength(passwordConfirm)){
                throw new BusinessException(SysCode.FAIL.getCode(),"密码确认必须为5-20位的数字!");
            }
            if (!password.equals(passwordConfirm)){
                throw new BusinessException(SysCode.FAIL.getCode(),"两次密码输入不一致!");
            }
            if (!password.equals(storedAmountPageVO.getPassword())){
                throw new BusinessException(SysCode.FAIL.getCode(),"密码错误，请重新输入!");
            }

        }
        //********************************************************************************************
        int result = -1;
        MemberInfo m = new MemberInfo();
        m.setId(storedAmountPageVO.getId());

        switch (changeType) {
            case 0://充值
                //第一步先增加会员信息表中的累计储值和当前储值
                m.setTotalStoredAmount(storedAmountPageVO.getTotalStoredAmount().add(changeValue));
                m.setCurrentStoredAmount(storedAmountPageVO.getCurrentStoredAmount().add(changeValue));
                result = memberInfoMapper.updateByPrimaryKeySelective(m);
                break;
            case 1://扣款
                //第一步扣除会员信息表中的当前储值
                m.setCurrentStoredAmount(storedAmountPageVO.getCurrentStoredAmount().subtract(changeValue));
                result = memberInfoMapper.updateByPrimaryKeySelective(m);
                break;
            case 2://转账
                //第一步扣除当前的储值
                m.setCurrentStoredAmount(storedAmountPageVO.getCurrentStoredAmount().subtract(changeValue));
                memberInfoMapper.updateByPrimaryKeySelective(m);

                //第二步，增加转账会员的储值金额：累计和当前
                m.setId(transferCardInfo.getId());
                m.setTotalStoredAmount(transferCardInfo.getTotalStoredAmount().add(changeValue));
                m.setCurrentStoredAmount(transferCardInfo.getCurrentStoredAmount().add(changeValue));
                result = memberInfoMapper.updateByPrimaryKeySelective(m);

                //转入人的操作编码
                StoredAmount storedAmount = StoredAmountPageVO.convertToStoredAmount(loginUser, transferCardInfo, 3, changeValue, storedAmountPageVO.getId(), storedAmountPageVO.getCardCode());
                storedAmount.setBaseOrderCode(orderCodeComponent.
                        generate(OrderRule.TRANSFER.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode()));
                /**
                 * 根据总部控制还是自主控制设置企业ID和商机企业ID
                 */
                if (!loginUser.getChainType().equals(ChainType.Headquarters.getCode()) && enterpriseBusiness != null
                        && 0 == enterpriseBusiness.getMemberInfo()) {
                    storedAmount.setEnterpriseId(loginUser.getParentId());
                    storedAmount.setParentId(0L);
                }
                //第三步，添加转账会员卡的转账记录
                storedAmountMapper.insertSelective(storedAmount);
                break;
            case 4:
                m.setPassword(requestStoredAmountVO.getNewPassword());
                m.setPasswordConfirm(requestStoredAmountVO.getNewPassword());
                memberInfoMapper.updateByPrimaryKeySelective(m);
                break;
            default:
                break;
        }
        if (changeType == 0
                || changeType == 1
                || changeType == 2){
            //最后添加当前账号的储值变动记录
            if (transferCardInfo == null) {
                StoredAmount storedAmount = StoredAmountPageVO.convertToStoredAmount(loginUser, storedAmountPageVO, changeType, changeValue, 0L, null);
                //设置储值记录操作码(充值的编码)
                if (changeType == 0){
                    storedAmount.setBaseOrderCode(orderCodeComponent.
                            generate(OrderRule.RECHARGE.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode()));
                }
                //扣款的编码
                if (changeType == 1){
                    storedAmount.setBaseOrderCode(orderCodeComponent.
                            generate(OrderRule.CHARGEBACK.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode()));
                }
                /**
                 * 根据总部控制还是自主控制设置企业ID和商机企业ID
                 */
                if (!loginUser.getChainType().equals(ChainType.Headquarters.getCode()) && enterpriseBusiness != null
                        && 0 == enterpriseBusiness.getMemberInfo()) {
                    storedAmount.setEnterpriseId(loginUser.getParentId());
                    storedAmount.setParentId(0L);
                }
                storedAmountMapper.insertSelective(storedAmount);
            } else {
                StoredAmount storedAmount = StoredAmountPageVO.convertToStoredAmount(loginUser, storedAmountPageVO, changeType, changeValue, transferCardInfo.getId(), transferCardInfo.getCardCode());
                storedAmount.setBaseOrderCode(orderCodeComponent.
                        generate(OrderRule.TRANSFER.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode()));
                /**
                 * 根据总部控制还是自主控制设置企业ID和商机企业ID
                 */
                if (!loginUser.getChainType().equals(ChainType.Headquarters.getCode()) && enterpriseBusiness != null
                        && 0 == enterpriseBusiness.getMemberInfo()) {
                    storedAmount.setEnterpriseId(loginUser.getParentId());
                    storedAmount.setParentId(0L);
                }
                storedAmountMapper.insertSelective(storedAmount);
            }
        }
        return result;
    }

}
