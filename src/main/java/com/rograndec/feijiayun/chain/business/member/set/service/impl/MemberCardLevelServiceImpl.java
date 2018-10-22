package com.rograndec.feijiayun.chain.business.member.set.service.impl;

import com.rograndec.feijiayun.chain.business.member.set.dao.MemberCardLevelMapper;
import com.rograndec.feijiayun.chain.business.member.set.dao.MemberCardTypeMapper;
import com.rograndec.feijiayun.chain.business.member.set.entity.MemberCardLevel;
import com.rograndec.feijiayun.chain.business.member.set.entity.MemberCardType;
import com.rograndec.feijiayun.chain.business.member.set.service.MemberCardLevelService;
import com.rograndec.feijiayun.chain.business.member.set.vo.MemberCardLevelVO;
import com.rograndec.feijiayun.chain.business.member.set.vo.MemberSimpleCardLevelVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseBusinessService;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.SysType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.string.ChineseString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author dongyang.du 2017-09-20 15:44:23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MemberCardLevelServiceImpl implements MemberCardLevelService {

    @Autowired
    private MemberCardLevelMapper cardLevelMapper;

    @Autowired
    private MemberCardTypeMapper cardTypeMapper;

    @Autowired
    private EnterpriseBusinessService enterpriseBusinessService;

    /**
     * 根据 参数 获取 会员卡
     */
    @Override
    public List<MemberCardLevelVO> selectCardLevel(Map<String, Object> param, UserVO userVO) {
        //门店: 会员管理由总部控制时，读取总部的配置
        Long enterpriseId = userVO.getEnterpriseId();
        EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(enterpriseId);
        if ((userVO.getChainType().equals(ChainType.Division.getCode()) && 0 == enterpriseBus.getMemberInfo())
                || userVO.getChainType().equals(ChainType.Selfoperatedshop.getCode())){
            enterpriseId = userVO.getParentId();
        }
        param.put("enterpriseId", enterpriseId);

        List<MemberCardLevelVO> memberCardLevelVOS = cardLevelMapper.selectBySelective(param);

        for (MemberCardLevelVO memberCardLevelVO:memberCardLevelVOS) {

            if(memberCardLevelVO.getSysType().equals(SysType.SYSTEM.getCode())){
                memberCardLevelVO.setDeleteFlag(Boolean.FALSE);
                memberCardLevelVO.setUpdateFlag(Boolean.FALSE);
            } else if(!canDelete(memberCardLevelVO.getId())){
                memberCardLevelVO.setDeleteFlag(Boolean.FALSE);
            }
            memberCardLevelVO.setIsOwner(memberCardLevelVO.getEnterpriseId().equals(userVO.getEnterpriseId())? 1:0);
        }

        return memberCardLevelVOS;
    }

    @Override
    public Result<String> save(MemberCardLevelVO cardLevelVO, UserVO loginUser) {

        Result<String> result = new Result<>();
        //门店: 会员管理由总部控制时，不允许门店自己管理
        EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(loginUser.getEnterpriseId());
        if (loginUser.getChainType().equals(ChainType.Division.getCode())
                && 0 == enterpriseBus.getMemberInfo()){
            throw new BusinessException(SysCode.FAIL.getCode(),"会员由总部进行管理，您没有权限");
        }

        // 汉字检验
        ChineseString.checkCode(cardLevelVO.getCode());

        // 重复校验

        checkRepeat(cardLevelVO, loginUser);

        MemberCardLevel cardLevel = new MemberCardLevel();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(cardLevelVO, cardLevel);
        cardLevel.setEnterpriseId(loginUser.getEnterpriseId());
        cardLevel.setParentId(loginUser.getParentId());
        cardLevel.setCreaterId(loginUser.getUserId());
        cardLevel.setCreaterCode(loginUser.getUserCode());
        cardLevel.setCreaterName(loginUser.getUserName());
        cardLevel.setCreateTime(new Date());
        cardLevel.setModifierId(loginUser.getUserId());
        cardLevel.setModifierCode(loginUser.getUserCode());
        cardLevel.setModifierName(loginUser.getUserName());
        cardLevel.setUpdateTime(new Date());

        cardLevelMapper.insertSelective(cardLevel);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, "新增会员卡级别-保存成功");

        return result;
    }

    /**
     * 检查名称和编码是否重复
     *
     * @param cardLevelVO
     * @param loginUser
     */
    private void checkRepeat(MemberCardLevelVO cardLevelVO, UserVO loginUser) {

        List<MemberCardType> cardTypeList = cardLevelMapper.selectByCodeOrName(loginUser.getEnterpriseId(), cardLevelVO.getId(), cardLevelVO.getCode(), cardLevelVO.getName());

        if (cardTypeList.size() > 0) {
            throw new BusinessException(SysCode.FAIL.getCode(), "保存失败，编码或名称重复");
        }

    }

    @Override
    public void update(MemberCardLevelVO cardLevelVO, UserVO loginUser) {

        MemberCardLevel cardLevel = new MemberCardLevel();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(cardLevelVO, cardLevel);

        // 校验名称和编码重复
        checkRepeat(cardLevelVO, loginUser);

        cardLevel.setModifierId(loginUser.getUserId());
        cardLevel.setModifierCode(loginUser.getUserCode());
        cardLevel.setModifierName(loginUser.getUserName());
        cardLevel.setUpdateTime(new Date());

        MemberCardLevel memberCardLevel = cardLevelMapper.selectByPrimaryKey(cardLevel.getId());

        if(!memberCardLevel.getStatus().equals(cardLevel.getStatus())){
            if(memberCardLevel.getSysType().equals(SysType.SYSTEM.getCode())){
                throw new BusinessException("系统默认数据，不允许更新状态");
            }

            if(!canDelete(cardLevel.getId())){
                throw new BusinessException("会员卡存在关联数据，不允许更新状态");
            }
        }

        cardLevelMapper.updateByPrimaryKeySelective(cardLevel);
    }

    @Override
    public Result<String> delete(Long id) {
        Result<String> result = new Result<String>();
        if (this.canDelete(id)) {
            cardLevelMapper.deleteByPrimaryKey(id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "删除会员卡级别成功");
        } else {
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "当前会员卡级别在会员卡类型中已被使用，不允许删除");
        }

        return result;
    }

    @Override
    public boolean canDelete(Long id) {
        List<MemberCardType> list = cardTypeMapper.selectByLevelId(id);
        return list.size() == 0;
    }

    @Override
    public List<MemberSimpleCardLevelVO> selectSimpleCardLevel(UserVO user) {
        Long eId = user.getEnterpriseId();
        EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(eId);
        //0是会员总部控制 1自主控制
        Integer memberInfo = enterpriseBus.getMemberInfo();
        //只要是总部控制都看总部的。自主控制都看自己的
        if (memberInfo == 0){
            eId = (user.getChainType() == ChainType.Headquarters.getCode() ? user.getEnterpriseId() : user.getParentId());
        }
        return cardLevelMapper.selectSimpleCardLevel(eId);
    }

}
