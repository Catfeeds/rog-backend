package com.rograndec.feijiayun.chain.business.member.set.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.member.info.dao.MemberInfoMapper;
import com.rograndec.feijiayun.chain.business.member.info.entity.MemberInfo;
import com.rograndec.feijiayun.chain.business.member.set.dao.MemberCardLevelMapper;
import com.rograndec.feijiayun.chain.business.member.set.dao.MemberCardTypeMapper;
import com.rograndec.feijiayun.chain.business.member.set.entity.MemberCardLevel;
import com.rograndec.feijiayun.chain.business.member.set.entity.MemberCardType;
import com.rograndec.feijiayun.chain.business.member.set.service.MemberCardTypeService;
import com.rograndec.feijiayun.chain.business.member.set.vo.MemberCardTypeVO;
import com.rograndec.feijiayun.chain.business.member.set.vo.SimpleStoreVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseBusinessService;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.SysType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.string.ChineseString;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 会员卡类型 service
 *
 * @author dongyang.du 2017-09-20 15:45:04
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MemberCardTypeServiceImpl implements MemberCardTypeService {

    private static final Log logger = LogFactory.getLog(MemberCardTypeServiceImpl.class);

    @Autowired
    private MemberCardTypeMapper cardTypeMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private MemberInfoMapper infoMapper;

    @Autowired
    private MemberCardLevelMapper cardLevelMapper;

    @Autowired
    private EnterpriseBusinessService enterpriseBusinessService;

    @Override
    public List<MemberCardTypeVO> selectCardType(Map<String, Object> param, UserVO userVO) {
        //门店: 会员管理由总部控制时，读取总部的设置
        Long enterpriseId = userVO.getEnterpriseId();
        EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(enterpriseId);
        if (!userVO.getChainType().equals(ChainType.Headquarters.getCode())
                && 0 == enterpriseBus.getMemberInfo()){
            enterpriseId = userVO.getParentId();
        }
        param.put("enterpriseId", enterpriseId);

        List<MemberCardTypeVO> memberCardTypeVOS = cardTypeMapper.selectBySelective(param);

        for (MemberCardTypeVO cardLevelVO: memberCardTypeVOS) {

            if(cardLevelVO.getSysType().equals(SysType.SYSTEM.getCode())){
                cardLevelVO.setDeleteFlag(Boolean.FALSE);
                cardLevelVO.setUpdateFlag(Boolean.FALSE);
            } else if(!canDelete(cardLevelVO.getId())){
                cardLevelVO.setDeleteFlag(Boolean.FALSE);
            }
            cardLevelVO.setIsOwner(cardLevelVO.getEnterpriseId().equals(userVO.getEnterpriseId())? 1:0);
        }

        return memberCardTypeVOS;
    }

    @Override
    public Result<String> save(MemberCardTypeVO cardTypeVO, UserVO loginUser) {
        Result<String> result = new Result<>();

        Long eId = loginUser.getEnterpriseId();
        //门店: 会员管理由总部控制时，不允许门店自己管理
        EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(eId);
        if (loginUser.getChainType().equals(ChainType.Division.getCode())
                && 0 == enterpriseBus.getMemberInfo()){
            throw new BusinessException(SysCode.FAIL.getCode(),"会员由总部进行管理，您没有权限");
        }

        // 规则检查
        checkRepeat(cardTypeVO, loginUser);

        MemberCardType type = new MemberCardType();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(cardTypeVO, type);

        MemberCardLevel level = cardLevelMapper.selectByPrimaryKey(type.getLevelId());
        if(level == null){
            throw new BusinessException(SysCode.FAIL.getCode(),"会员级别不能为空");
        }

        type.setLevelName(level.getName());

        type.setEnterpriseId(eId);
        type.setParentId(loginUser.getParentId());
        type.setCreaterId(loginUser.getUserId());
        type.setCreaterCode(loginUser.getUserCode());
        type.setCreaterName(loginUser.getUserName());
        type.setCreateTime(new Date());
        type.setModifierId(loginUser.getUserId());
        type.setModifierCode(loginUser.getUserCode());
        type.setModifierName(loginUser.getUserName());
        type.setUpdateTime(new Date());

        cardTypeMapper.insertSelective(type);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, "会员卡类型-保存成功");

        return result;
    }

    /**
     * 规则检查
     * @param cardTypeVO
     * @param loginUser
     */
    private void checkRepeat(MemberCardTypeVO cardTypeVO, UserVO loginUser) {


        // 汉字检查
        ChineseString.checkCode(cardTypeVO.getCode());

        if(cardTypeVO.getDiscountStrategy().compareTo(new BigDecimal(100)) > 0 ||
                cardTypeVO.getDiscountStrategy().compareTo(BigDecimal.ZERO) < 0){

            throw new BusinessException(SysCode.FAIL.getCode(),"折让策略数值范围应为0-100");
        }



        //重复校验  检查Code 是否重复
        List<MemberCardType> cardTypeList = cardTypeMapper.selectByCodeOrName(loginUser.getEnterpriseId(), cardTypeVO.getId(), cardTypeVO.getCode(), cardTypeVO.getName());

        if (cardTypeList.size() > 0) {

            throw new BusinessException(SysCode.FAIL.getCode(), "保存失败，名称和编码重复");
        }


    }

    @Override
    public void update(MemberCardTypeVO cardTypeVO, UserVO loginUser) {

        checkRepeat(cardTypeVO, loginUser);

        MemberCardType type = new MemberCardType();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(cardTypeVO, type);
        MemberCardLevel level = cardLevelMapper.selectByPrimaryKey(type.getLevelId());
        type.setLevelName(level.getName());
        type.setModifierId(loginUser.getUserId());
        type.setModifierCode(loginUser.getUserCode());
        type.setModifierName(loginUser.getUserName());
        type.setUpdateTime(new Date());

        MemberCardType memberCardType = cardTypeMapper.selectByPrimaryKey(type.getId());

        if(!memberCardType.getStatus().equals(type.getStatus())){
            if(memberCardType.getSysType().equals(SysType.SYSTEM.getCode())){
                throw new BusinessException("系统默认数据，不允许更新状态");
            }

            if(!canDelete(type.getId())){
                throw new BusinessException("会员信息存在关联数据，不允许更新状态");
            }
        }


        cardTypeMapper.updateByPrimaryKeySelective(type);
    }

    @Override
    public Result<String> delete(Long id) {

        Result<String> result = new Result<String>();
        if (this.canDelete(id)) {
            cardTypeMapper.deleteByPrimaryKey(id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "删除会员卡类型成功");
        } else {
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "当前会员卡级别在会员信息中已被使用，不允许删除");
        }

        return result;
    }

    private boolean canDelete(Long id) {
        List<MemberInfo> data = infoMapper.selectByCardTypeId(id);

        return data.size() == 0;
    }

    @Override
    public Page<List<SimpleStoreVO>> simpleSelectStoreVOPage(Map<String, Object> param, Page<List<SimpleStoreVO>> page) {

        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        /**
         * 总部 --->只能查到总部控制的门店
         */
        List<SimpleStoreVO> data = enterpriseMapper.simpleSelectStoreVOPage(param);
        page.setResult(data);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
        page.setTotalPage(hPage.getPages());
        return page;
    }

    @Override
    public List<SimpleStoreVO> getSimpleStoreVOsByIds(String ids) {

        List<Long> list = new ArrayList<>();

        for (String storeId : ids.split(",")) {
            if (StringUtils.isNotBlank(storeId)) {
                try {
                    list.add(Long.parseLong(storeId));
                } catch (Exception e) {
                    logger.error("门店分组绑定门店ID转Long失败！", e);
                    e.printStackTrace();
                }
            }
        }

        return enterpriseMapper.selectSimpleStoreVOsByIds(list);

    }

}
