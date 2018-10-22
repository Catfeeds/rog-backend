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
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsUnitMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsUnit;
import com.rograndec.feijiayun.chain.business.goods.set.service.SetUnitService;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsUnitVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * Created by zeshi.sun on 2017/9/2.
 */
@Service
public class SetUnitServiceImpl implements SetUnitService {

    private static final Log logger = LogFactory.getLog(SetUnitServiceImpl.class);

    @Autowired
    private GoodsUnitMapper goodsUnitMapper;

    @Override
    @Transactional
    public List<GoodsUnitVO> getSetUnit(Long enterpriseId, Integer status) throws Exception {
        List<GoodsUnitVO> goodsUnitVOs = goodsUnitMapper.selectAll(enterpriseId, status);

        for (GoodsUnitVO goodsUnitVO : goodsUnitVOs) {//是否有关联数据

            if(goodsUnitVO.getSysType().equals(SysType.SYSTEM.getCode())){
                goodsUnitVO.setDeleteFlag(Boolean.FALSE);
                goodsUnitVO.setUpdateFlag(Boolean.FALSE);
            } else if(!canDelete(goodsUnitVO.getId())){
                goodsUnitVO.setDeleteFlag(Boolean.FALSE);
            }

        }

        return goodsUnitVOs == null ? new ArrayList<>() : goodsUnitVOs;
    }

    @Override
    public Result<String> insertSetUnit(UserVO loginUser, GoodsUnit goodsUnit) throws Exception {
        // 编码和名称不能重复
        Result<String> result = new Result<>();

        // 汉字检查
        ChineseString.checkCode(goodsUnit.getCode());
        checkRepeated(goodsUnit, loginUser);


        goodsUnit.setEnterpriseId(loginUser.getEnterpriseId());
        goodsUnit.setCreaterId(loginUser.getUserId());
        goodsUnit.setCreaterCode(loginUser.getUserCode());
        goodsUnit.setCreaterName(loginUser.getUserName());
        goodsUnit.setCreateTime(new Date());
        goodsUnit.setId(null);
        goodsUnit.setSysType(SysType.CUSTOMIZE.getCode());
        goodsUnit.setModifierId(loginUser.getUserId());
        goodsUnit.setModifierCode(loginUser.getUserCode());
        goodsUnit.setModifierName(loginUser.getUserName());
        goodsUnit.setUpdateTime(new Date());
        goodsUnitMapper.insertSelective(goodsUnit);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存新增的商品设置-单位成功");
        return result;
    }

    private void checkRepeated(GoodsUnit goodsUnit, UserVO userVO) {
        List<GoodsUnit> units = goodsUnitMapper.selectByCodeOrName(userVO.getEnterpriseId(), goodsUnit.getCode(), null, goodsUnit.getId());

        if (units.size() > 0) {
            throw new BusinessException("保存新增的商品设置-单位错误：编码重复");
        }


        List<GoodsUnit> unitList = goodsUnitMapper.selectByCodeOrName(userVO.getEnterpriseId(), null, goodsUnit.getName(), goodsUnit.getId());

        if (unitList.size() > 0) {
            throw new BusinessException("保存新增的商品设置-单位错误：名称重复");
        }
    }

    @Override
    public int updateSetUnit(UserVO loginUser, GoodsUnit goodsUnit) throws Exception {

        checkRepeated(goodsUnit, loginUser);
        goodsUnit.setModifierId(loginUser.getUserId());
        goodsUnit.setModifierCode(loginUser.getUserCode());
        goodsUnit.setModifierName(loginUser.getUserName());
        goodsUnit.setUpdateTime(new Date());

        GoodsUnit goodsUnit1 = goodsUnitMapper.selectByPrimaryKey(goodsUnit.getId());


        if (goodsUnit1.getSysType().equals(SysType.SYSTEM.getCode())) {
            throw new BusinessException(SysCode.FAIL.getCode(), "系统默认单位，不允许修改");
        }

        if(!goodsUnit1.getStatus().equals(goodsUnit.getStatus())){

            if(!canDelete(goodsUnit.getId())){
                throw new BusinessException(SysCode.FAIL.getCode(), "商品管理数据，不允许修改状态");
            }

        }


        return goodsUnitMapper.updateByPrimaryKeySelective(goodsUnit);
    }

    @Override
    public boolean canDelete(Long id) throws Exception {
        List<Goods> goods = goodsUnitMapper.selectGoodsByGoodsUnitId(id);
        return goods.size() == 0;
    }

    @Override
    public int deleteSetUnit(Long id) throws Exception {

        GoodsUnit goodsUnit = goodsUnitMapper.selectByPrimaryKey(id);

        if (goodsUnit == null) {
            return 1;
        }

        if (goodsUnit.getSysType().equals(SysType.SYSTEM.getCode())) {
            throw new BusinessException(SysCode.FAIL.getCode(), "系统默认单位，不允许删除");
        }
        return goodsUnitMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<GoodsUnitVO> getUsableUnit(Long enterpriseId) {
        List<GoodsUnitVO> goodsUnitVOList = goodsUnitMapper.getUsableUnit(enterpriseId);
        return goodsUnitVOList == null ? new ArrayList<>() : goodsUnitVOList;
    }

}
