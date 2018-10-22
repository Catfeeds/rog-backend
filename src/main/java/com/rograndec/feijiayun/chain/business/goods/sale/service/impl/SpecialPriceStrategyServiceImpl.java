package com.rograndec.feijiayun.chain.business.goods.sale.service.impl;

import com.rograndec.feijiayun.chain.business.goods.sale.dao.CheckCodeExistsMapper;
import com.rograndec.feijiayun.chain.business.goods.sale.dao.SpecialPriceGoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.sale.dao.SpecialPriceStrategyMapper;
import com.rograndec.feijiayun.chain.business.goods.sale.entity.SpecialPriceGoods;
import com.rograndec.feijiayun.chain.business.goods.sale.entity.SpecialPriceStrategy;
import com.rograndec.feijiayun.chain.business.goods.sale.service.SpecialPriceStrategyService;
import com.rograndec.feijiayun.chain.business.goods.sale.vo.SpecialPriceStrategyVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by madong on 2017/9/5.
 */
@Service
public class SpecialPriceStrategyServiceImpl implements SpecialPriceStrategyService{
    @Autowired
    SpecialPriceStrategyMapper specialPriceStrategyMapper;
    @Autowired
    SpecialPriceGoodsMapper specialPriceGoodsMapper;
    @Autowired
    CheckCodeExistsMapper checkCodeExistsMapper;
    @Autowired
    private EnterpriseBusinessMapper enterpriseBusinessMapper;
    @Override
    public int saveSpecialPrice(SpecialPriceStrategyVO specialPriceStrategyVO, UserVO loginUser) throws Exception{
        SpecialPriceStrategy specialPriceStrategy = new SpecialPriceStrategy();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialPriceStrategyVO,specialPriceStrategy);
        specialPriceStrategy.setCreaterId(loginUser.getUserId());
        specialPriceStrategy.setCreaterCode(loginUser.getUserCode());
        specialPriceStrategy.setCreaterName(loginUser.getUserName());
        specialPriceStrategy.setModifierId(loginUser.getUserId());
        specialPriceStrategy.setModifierCode(loginUser.getUserCode());
        specialPriceStrategy.setModifierName(loginUser.getUserName());
        specialPriceStrategy.setEnterpriseId(loginUser.getEnterpriseId());
        specialPriceStrategy.setParentId(loginUser.getParentId());
        //------检查code是否重复
        List<String> codeList = new ArrayList<>();
        codeList.add(specialPriceStrategy.getCode());
        Map param = new HashMap();
        param.put("codeList",codeList);
        param.put("enterpriseId",loginUser.getEnterpriseId());
        param.put("tableName","saas_special_price_strategy");
        if(!checkCodeExistsMapper.isExistsCode(param).equals(0l)){
            throw new Exception("该特价编码重复,请重新输入");
        }
        //------检查code是否重复
        return specialPriceStrategyMapper.insertSelective(specialPriceStrategy);
    }

    @Override
    public int updateSpecialPrice(SpecialPriceStrategyVO specialPriceStrategyVO, UserVO loginUser) throws Exception{
        SpecialPriceStrategy specialPriceStrategy = new SpecialPriceStrategy();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialPriceStrategyVO,specialPriceStrategy);
        specialPriceStrategy.setModifierId(loginUser.getUserId());
        specialPriceStrategy.setModifierCode(loginUser.getUserCode());
        specialPriceStrategy.setModifierName(loginUser.getUserName());
        return specialPriceStrategyMapper.updateByPrimaryKeySelective(specialPriceStrategy);
    }

    @Override
    public int deleteSpecialPrice(Long id, UserVO loginUser) throws Exception{
//        Map<String,Long> param = new HashMap<>();
//        param.put("enterpriseId",loginUser.getEnterpriseId());
//        param.put("strategyId",id);
//        specialPriceGoodsMapper.deleteByPrimaryStrategyIdByEnterpriseId(param);
        return specialPriceStrategyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<SpecialPriceStrategyVO> getSpecialPrice(UserVO loginUser, String orderName, String orderType) {
        Map param = new HashMap();
        //xinhao
        //0-总部；1-自营店；2-加盟
        EnterpriseBusiness enterpriseBusiness=enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(loginUser.getEnterpriseId());
        if(loginUser.getChainType().equals(0)){//总部
        	param.put("enterpriseId",loginUser.getEnterpriseId());
        }else{//自营店或者是加盟店
        		if(enterpriseBusiness.getSpecialPriceManage().equals(1)){//自主控制
        			param.put("enterpriseId",loginUser.getEnterpriseId());
        		}else{
        			param.put("enterpriseId",loginUser.getParentId());
        		}
        }
        param.put("orderName",orderName);
        param.put("orderType",orderType);
        List<SpecialPriceStrategy> list = specialPriceStrategyMapper.selectByEnterpriseId(param);
        List<SpecialPriceStrategyVO> specialPriceStrategyVOS = new ArrayList<>();
        for (SpecialPriceStrategy specialPriceStrategy : list){
            SpecialPriceStrategyVO specialPriceStrategyVO = new SpecialPriceStrategyVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialPriceStrategy,specialPriceStrategyVO);
            specialPriceStrategyVOS.add(specialPriceStrategyVO);
        }
        return specialPriceStrategyVOS;
    }

    @Override
    public boolean canDeleteSpecialPrice(Long id, UserVO loginUser) {
        Map<String,Long> param = new HashMap<>();
        param.put("enterpriseId",loginUser.getEnterpriseId());
        param.put("strategyId",id);
        List<SpecialPriceGoods> specialPriceGoods = specialPriceGoodsMapper.selectByStrategyId(param);
        return specialPriceGoods.isEmpty()||specialPriceGoods.size()==0;
    }

    @Override
    public Long checkExistsCode(UserVO loginUser, String code) throws Exception {
        List<String> codeList = new ArrayList<>();

        codeList.add(code);
        Map param = new HashMap();
        param.put("codeList",codeList);
        param.put("enterpriseId",loginUser.getEnterpriseId());
        param.put("tableName","saas_special_price_strategy");
        return checkCodeExistsMapper.isExistsCode(param);
    }
}
