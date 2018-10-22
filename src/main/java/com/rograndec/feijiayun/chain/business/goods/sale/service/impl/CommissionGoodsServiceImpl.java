package com.rograndec.feijiayun.chain.business.goods.sale.service.impl;

import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.sale.dao.CommissionGoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.sale.dao.CommissionStrategyMapper;
import com.rograndec.feijiayun.chain.business.goods.sale.entity.CommissionGoods;
import com.rograndec.feijiayun.chain.business.goods.sale.service.CommissionGoodsService;
import com.rograndec.feijiayun.chain.business.goods.sale.vo.CommissionGoodsVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
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
public class CommissionGoodsServiceImpl implements CommissionGoodsService {
    @Autowired
    CommissionGoodsMapper commissionGoodsMapper;
    @Autowired
    CommissionStrategyMapper strategyMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    private EnterpriseBusinessMapper enterpriseBusinessMapper;
    @Override
    public int saveRoyaltyGoods(List<CommissionGoodsVO> commissionGoodsVOS, UserVO loginUser) throws Exception{
        List<CommissionGoods> list = new ArrayList<>();
        List<Long> goodsIds = new ArrayList<>();
        for (CommissionGoodsVO commissionGoodsVO : commissionGoodsVOS){
            CommissionGoods royaltyGoods = new CommissionGoods();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(commissionGoodsVO,royaltyGoods);
            UserEnterpriseUtils.setUserCreateOrModify(royaltyGoods,loginUser,true);
            royaltyGoods.setEnterpriseId(loginUser.getEnterpriseId());
            royaltyGoods.setParentId(loginUser.getParentId());
            //set商品信息
            Goods goods = goodsMapper.selectByPrimaryKey(commissionGoodsVO.getGoodsId());
            royaltyGoods.setGoodsName(goods.getName());
            royaltyGoods.setGoodsCode(goods.getCode());
            royaltyGoods.setDosageId(goods.getDosageId());
            royaltyGoods.setDosageName(goods.getDosageName());
            royaltyGoods.setUnitId(goods.getUnitId());
            royaltyGoods.setUnitName(goods.getUnitName());
            royaltyGoods.setManufacturerId(goods.getManufacturerId());
            royaltyGoods.setManufacturer(goods.getManufacturer());
            royaltyGoods.setSpecification(goods.getSpecification());
            royaltyGoods.setGoodsPlace(goods.getPlace());
            list.add(royaltyGoods);
            goodsIds.add(royaltyGoods.getGoodsId());
        }
        //-------------检查该商品是否已经设置过提成商品
        Map param = new HashMap();
        param.put("enterpriseId",loginUser.getEnterpriseId());
        param.put("id",null);
        param.put("goodsIds",goodsIds);
        Long count = commissionGoodsMapper.selectGoodsExists(param);
        if(count != 0l)
            throw new Exception("该商品已经是提成商品了!");
        //-------------检查该商品是否已经设置过提成商品
        return commissionGoodsMapper.batchInsert(list);
    }

    @Override
    public List<Map> getRoyaltyInfo(UserVO loginUser) throws Exception {
        return strategyMapper.selectByEnterpriseId(loginUser.getEnterpriseId());
    }

    @Override
    public int updateRoyaltyGoods(CommissionGoodsVO commissionGoodsVO, UserVO loginUser) throws Exception {
        CommissionGoods royaltyGoods = new CommissionGoods();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(commissionGoodsVO,royaltyGoods);
        royaltyGoods.setModifierId(loginUser.getUserId());
        royaltyGoods.setModifierCode(loginUser.getUserCode());
        royaltyGoods.setModifierName(loginUser.getUserName());
        //-------------检查该商品是否已经设置过提成商品
        Map param = new HashMap();
        List<Long> goodsIds = new ArrayList<>();
        param.put("enterpriseId",loginUser.getEnterpriseId());
        param.put("id",royaltyGoods.getId());
        goodsIds.add(royaltyGoods.getGoodsId());
        param.put("goodsIds",goodsIds);
        Long count = commissionGoodsMapper.selectGoodsExists(param);
        if(count != 0l)
            throw new BusinessException("该商品已经是提成商品了!");
        //-------------检查该商品是否已经设置过提成商品
        return commissionGoodsMapper.updateByPrimaryKeySelective(royaltyGoods);
    }

    @Override
    public int deleteRoyaltyGoods(Long id) throws Exception {
        return commissionGoodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Page getRoyaltyGoods(UserVO loginUser, String goodsInfo, String strategyId, String orderName, String orderType, Integer pageNo, Integer pageSize) throws Exception {
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
        List<CommissionGoods> royaltyGoodsList = commissionGoodsMapper.selectByEnterpriseId(param);
        List<CommissionGoodsVO> commissionGoodsVOS = new ArrayList<>();
        for (CommissionGoods royaltyGoods : royaltyGoodsList){
            CommissionGoodsVO commissionGoodsVO = new CommissionGoodsVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(royaltyGoods, commissionGoodsVO);
            commissionGoodsVOS.add(commissionGoodsVO);
        }
        Page page = new Page(pageNo,pageSize);
        page.setResult(commissionGoodsVOS);

        param.replace("pageSize",null);
        param.replace("start",null);
        page.setTotalRecord(commissionGoodsMapper.selectCountByEnterpriseId(param));
        return page;
    }
}
