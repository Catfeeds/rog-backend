package com.rograndec.feijiayun.chain.business.goods.set.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rograndec.feijiayun.chain.common.constant.SysType;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.string.ChineseString;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsDosageMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsDosage;
import com.rograndec.feijiayun.chain.business.goods.set.service.SetDosageService;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsDosageVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * Created by zeshi.sun on 2017/9/5.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SetDosageServiceImpl implements SetDosageService {

    private static final Log logger = LogFactory.getLog(SetDosageServiceImpl.class);

    @Autowired
    private GoodsDosageMapper goodsDosageMapper;

    @Override
    @Transactional
    public List<GoodsDosageVO> getSetDosage(Long enterpriseId, Integer status) throws Exception {
        List<GoodsDosageVO> goodsDosageVOs = goodsDosageMapper.selectAll(enterpriseId, status);

        for (GoodsDosageVO goodsDosageVO : goodsDosageVOs) {

            if(goodsDosageVO.getSysType().equals(SysType.SYSTEM.getCode())){
                goodsDosageVO.setDeleteFlag(Boolean.FALSE);
                goodsDosageVO.setUpdateFlag(Boolean.FALSE);

            } else if (!canDelete(goodsDosageVO.getId())){
                goodsDosageVO.setDeleteFlag(Boolean.FALSE);
            }

        }


        return goodsDosageVOs == null ? new ArrayList<>() : goodsDosageVOs;
    }

    @Override
    public Result<String> insertSetDosage(UserVO loginUser, GoodsDosage goodsDosage) throws Exception {
        // 编码和名称不能重复
        Result<String> result = new Result<>();

        // 汉字检查
        ChineseString.checkCode(goodsDosage.getCode());
        checkRepeated(goodsDosage, loginUser);

        goodsDosage.setEnterpriseId(loginUser.getEnterpriseId());
        goodsDosage.setCreaterId(loginUser.getUserId());
        goodsDosage.setCreaterCode(loginUser.getUserCode());
        goodsDosage.setCreaterName(loginUser.getUserName());
        goodsDosage.setCreateTime(new Date());
        goodsDosage.setSysType(SysType.CUSTOMIZE.getCode());
        goodsDosage.setId(null);
        goodsDosage.setModifierId(loginUser.getUserId());
        goodsDosage.setModifierCode(loginUser.getUserCode());
        goodsDosage.setModifierName(loginUser.getUserName());
        goodsDosage.setUpdateTime(new Date());
        goodsDosageMapper.insertSelective(goodsDosage);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存新增的商品设置-剂型成功");
        return result;
    }

    private void checkRepeated(GoodsDosage goodsDosage, UserVO userVO) {
        List<GoodsDosage> dosages = goodsDosageMapper.selectByCodeOrName(userVO.getEnterpriseId(), goodsDosage.getCode(), null, goodsDosage.getId());

        if (dosages.size() > 0) {
            throw new BusinessException("保存新增的商品设置-剂型错误：编码重复");
        }

        List<GoodsDosage> dosageList = goodsDosageMapper.selectByCodeOrName(userVO.getEnterpriseId(), null, goodsDosage.getName(), goodsDosage.getId());

        if (dosageList.size() > 0) {
            throw new BusinessException("保存新增的商品设置-剂型错误：名称重复");
        }

    }

    @Override
    public int updateSetDosage(UserVO loginUser, GoodsDosage goodsDosage) throws Exception {
        checkRepeated(goodsDosage, loginUser);

        goodsDosage.setModifierId(loginUser.getUserId());
        goodsDosage.setModifierCode(loginUser.getUserCode());
        goodsDosage.setModifierName(loginUser.getUserName());
        goodsDosage.setUpdateTime(new Date());

        GoodsDosage goodsDosage1 = goodsDosageMapper.selectByPrimaryKey(goodsDosage.getId());

        if (goodsDosage1.getSysType().equals(SysType.SYSTEM.getCode())) {
            throw new BusinessException(SysCode.FAIL.getCode(), "系统默认剂型，不允许修改");
        }

        if(!goodsDosage1.getStatus().equals(goodsDosage.getStatus())){

            if(!canDelete(goodsDosage.getId())){
                throw  new BusinessException(SysCode.FAIL.getCode(),"商品存在关联数据，不允许修改状态");
            }

        }


        return goodsDosageMapper.updateByPrimaryKeySelective(goodsDosage);
    }

    @Override
    public boolean canDelete(Long id) throws Exception {
        List<Goods> goods = goodsDosageMapper.selectGoodsByDosageId(id);
        return goods.size() == 0;
    }

    @Override
    public int deleteSetDosage(Long id) throws Exception {

        GoodsDosage goodsDosage = goodsDosageMapper.selectByPrimaryKey(id);
        if (goodsDosage == null) {
            return 1;
        }

        if (goodsDosage.getSysType().equals(SysType.SYSTEM.getCode())) {
            throw new BusinessException(SysCode.FAIL.getCode(), "系统默认剂型，不允许删除");
        }
        return goodsDosageMapper.deleteByPrimaryKey(id);
    }

}
