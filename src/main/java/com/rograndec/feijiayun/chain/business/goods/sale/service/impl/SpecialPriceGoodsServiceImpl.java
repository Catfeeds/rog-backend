package com.rograndec.feijiayun.chain.business.goods.sale.service.impl;

import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.sale.dao.SpecialPriceGoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.sale.dao.SpecialPriceStrategyMapper;
import com.rograndec.feijiayun.chain.business.goods.sale.entity.SpecialPriceGoods;
import com.rograndec.feijiayun.chain.business.goods.sale.service.SpecialPriceGoodsService;
import com.rograndec.feijiayun.chain.business.goods.sale.vo.SpecialPriceGoodsVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.apache.commons.lang.StringUtils;
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
public class SpecialPriceGoodsServiceImpl implements SpecialPriceGoodsService {
    @Autowired
    SpecialPriceGoodsMapper specialPriceGoodsMapper;
    @Autowired
    SpecialPriceStrategyMapper specialPriceStrategyMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    private EnterpriseBusinessMapper enterpriseBusinessMapper;
    @Override
    public int saveSpecialPriceGoods(List<SpecialPriceGoodsVO> specialPriceGoodsVOs, UserVO loginUser) throws Exception {
        List<SpecialPriceGoods> list = new ArrayList<>();
        List<Long> goodsIds = new ArrayList<>();
        boolean isUpdate = false;
        for (SpecialPriceGoodsVO specialPriceGoodsVO : specialPriceGoodsVOs){
            SpecialPriceGoods specialPriceGoods = new SpecialPriceGoods();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialPriceGoodsVO,specialPriceGoods);
            UserEnterpriseUtils.setUserCreateOrModify(specialPriceGoods,loginUser,true);
            UserEnterpriseUtils.setUserCreateOrModify(specialPriceGoods,loginUser,false);
            specialPriceGoods.setEnterpriseId(loginUser.getEnterpriseId());
            specialPriceGoods.setParentId(loginUser.getParentId());
            //set商品信息
            Goods goods = goodsMapper.selectByPrimaryKey(specialPriceGoodsVO.getGoodsId());
            specialPriceGoods.setGoodsName(goods.getName());
            specialPriceGoods.setGoodsCode(goods.getCode());
            specialPriceGoods.setDosageId(goods.getDosageId());
            specialPriceGoods.setDosageName(goods.getDosageName());
            specialPriceGoods.setUnitId(goods.getUnitId());
            specialPriceGoods.setUnitName(goods.getUnitName());
            specialPriceGoods.setManufacturerId(goods.getManufacturerId());
            specialPriceGoods.setManufacturer(goods.getManufacturer());
            specialPriceGoods.setGoodsPlace(goods.getPlace());
            specialPriceGoods.setSpecification(goods.getSpecification());
            list.add(specialPriceGoods);
            goodsIds.add(specialPriceGoods.getGoodsId());
            if(!isUpdate) {
                isUpdate = specialPriceGoodsVO.getIsUpdate() == null ? false : specialPriceGoodsVO.getIsUpdate();
            }
        }
        Map param = new HashMap();
        param.put("enterpriseId",loginUser.getEnterpriseId());
        param.put("id",null);
        param.put("goodsIds",goodsIds);

        if(!isUpdate){
            //-------------检查该商品是否已经设置过特价商品
            Long count = specialPriceGoodsMapper.selectGoodsExists(param);
            if(count != 0l)
                throw new BusinessException("此商品已存在特价策略，是否修改!");
            //-------------检查该商品是否已经设置过提成商品
        }else {
            specialPriceGoodsMapper.deleteExistsGoods(param);
        }
        return specialPriceGoodsMapper.batchInsert(list);
    }

    @Override
    public int updateSpecialPriceGoods(SpecialPriceGoodsVO specialPriceGoodsVO, UserVO loginUser) throws Exception {
        SpecialPriceGoods specialPriceGoods = new SpecialPriceGoods();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialPriceGoodsVO,specialPriceGoods);
        specialPriceGoods.setModifierId(loginUser.getUserId());
        specialPriceGoods.setModifierCode(loginUser.getUserCode());
        specialPriceGoods.setModifierName(loginUser.getUserName());
        //-------------检查该商品是否已经设置过提成商品
        Map param = new HashMap();
        List<Long> goodsIds = new ArrayList<>();
        param.put("enterpriseId",loginUser.getEnterpriseId());
        param.put("id",specialPriceGoods.getId());
        goodsIds.add(specialPriceGoods.getGoodsId());
        param.put("goodsIds",goodsIds);
        Long count = specialPriceGoodsMapper.selectGoodsExists(param);
        if(count != 0l)
            throw new BusinessException("该商品已经是特价商品了!");
        //-------------检查该商品是否已经设置过提成商品
        return specialPriceGoodsMapper.updateByPrimaryKeySelective(specialPriceGoods);
    }

    @Override
    public int deleteSpecialPriceGoods(Long id) throws Exception {
        return specialPriceGoodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Map> getSpecialPriceInfo(UserVO loginUser) throws Exception {
        return specialPriceStrategyMapper.selectIdNameByEnterpriseId(loginUser.getEnterpriseId());
    }

    @Override
    public Page getSpecialgoods(UserVO loginUser, String goodsInfo, String strategyId, String orderName, String orderType, Integer pageNo, Integer pageSize) throws Exception {
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
        if(StringUtils.isBlank(goodsInfo))
            goodsInfo = null;
        if(StringUtils.isBlank(orderName))
            orderName = null;
        if(StringUtils.isBlank(orderType))
            orderType = null;

        param.put("goodsInfo",goodsInfo);
        param.put("strategyId",strategyId);
        param.put("orderName",orderName);
        param.put("orderType",orderType);
        param.put("pageSize",pageSize);
        param.put("start",(pageNo-1)*pageSize);
        List<SpecialPriceGoods> specialPriceGoodsList = specialPriceGoodsMapper.selectByEnterpriseId(param);
        List<SpecialPriceGoodsVO> specialPriceGoodsVOS = new ArrayList<>();
        for (SpecialPriceGoods specialPriceGoods : specialPriceGoodsList){
            SpecialPriceGoodsVO specialPriceGoodsVO = new SpecialPriceGoodsVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialPriceGoods,specialPriceGoodsVO);
            specialPriceGoodsVOS.add(specialPriceGoodsVO);
        }
        Page page = new Page(pageNo,pageSize);
        page.setResult(specialPriceGoodsVOS);

        param.replace("pageSize",null);
        param.replace("start",null);
        page.setTotalRecord(specialPriceGoodsMapper.selectCountByEnterpriseId(param));
        return page;
    }
}
