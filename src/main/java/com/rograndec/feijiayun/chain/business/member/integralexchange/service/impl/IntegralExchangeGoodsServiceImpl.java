package com.rograndec.feijiayun.chain.business.member.integralexchange.service.impl;

import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.member.integralexchange.dao.IntegralExchangeDtlMapper;
import com.rograndec.feijiayun.chain.business.member.integralexchange.dao.IntegralExchangeGoodsMapper;
import com.rograndec.feijiayun.chain.business.member.integralexchange.entity.IntegralExchangeDtl;
import com.rograndec.feijiayun.chain.business.member.integralexchange.entity.IntegralExchangeGoods;
import com.rograndec.feijiayun.chain.business.member.integralexchange.service.IntegralExchangeGoodsService;
import com.rograndec.feijiayun.chain.business.member.integralexchange.vo.IntegralExchangeGoodsRequestVO;
import com.rograndec.feijiayun.chain.business.member.integralexchange.vo.IntegralExchangeGoodsVO;
import com.rograndec.feijiayun.chain.business.member.integralexchange.vo.StockGoodsVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseBusinessService;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dongyang.du on 2017/9/21.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class IntegralExchangeGoodsServiceImpl implements IntegralExchangeGoodsService {


    private static final Log logger = LogFactory.getLog(IntegralExchangeGoodsServiceImpl.class);


    @Autowired
    private IntegralExchangeGoodsMapper exchangeGoodsMapper;

    @Autowired
    private IntegralExchangeDtlMapper integralExchangeDtlMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private EnterpriseBusinessService enterpriseBusinessService;

    @Override
    public List<String> batchSave(List<IntegralExchangeGoodsRequestVO> requestVOS, UserVO loginUser) {

        List<String> result = new ArrayList<>();

        Long eId = loginUser.getEnterpriseId();
        //门店: 会员管理由总部控制时，不允许门店自己管理
        EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(eId);
        if (!loginUser.getChainType().equals(ChainType.Headquarters.getCode())
                && 0 == enterpriseBus.getMemberInfo()){
            throw new BusinessException(SysCode.FAIL.getCode(),"积分商品由总部进行管理，您没有权限");
        }

        // 查找删除的IDS
        List<IntegralExchangeGoodsRequestVO> oldList = exchangeGoodsMapper.selectExchangeGoodsRequestVO(eId);
        logger.info("数据原有列表:" + oldList);
        logger.info("前台传递列表:" + requestVOS);

        oldList.removeAll(requestVOS);
        logger.info("删除的列表:" + oldList);

        for (IntegralExchangeGoodsRequestVO requestVO : oldList) {
            delete(requestVO.getId());
        }


        for (IntegralExchangeGoodsRequestVO requestVO : requestVOS) {


            if (requestVO.getId() == null) {
                // 查询商品信息
                IntegralExchangeGoods exchangeGoods = new IntegralExchangeGoods();

                Goods goodsVO = goodsMapper.selectByPrimaryKey(requestVO.getGoodsId());
                exchangeGoods.setGoodsId(goodsVO.getId());
                exchangeGoods.setBarcode(goodsVO.getBarcode());// 条形码
                exchangeGoods.setGoodsCode(goodsVO.getCode());
                exchangeGoods.setGoodsGenericName(goodsVO.getGenericName());
                exchangeGoods.setGoodsName(goodsVO.getName());
                exchangeGoods.setDosageId(goodsVO.getDosageId()); // 剂型
                exchangeGoods.setDosageName(goodsVO.getDosageName());
                exchangeGoods.setGoodsSpecification(goodsVO.getSpecification());// 规格
                exchangeGoods.setUnitId(goodsVO.getUnitId());
                exchangeGoods.setUnitName(goodsVO.getUnitName());
                exchangeGoods.setManufacturer(goodsVO.getManufacturer());// 生产厂商
                exchangeGoods.setManufacturerId(goodsVO.getManufacturerId());
                exchangeGoods.setUseableQuantity(stockMapper.getUsableQuantityGroupByGoodsId(eId, requestVO.getGoodsId()));// 可用库存

                // 积分
                exchangeGoods.setIntegralExchange(requestVO.getIntegralExchange());

                // 其他一些默认信息

                exchangeGoods.setEnterpriseId(eId);
                exchangeGoods.setParentId(loginUser.getParentId());
                exchangeGoods.setCreaterId(loginUser.getUserId());
                exchangeGoods.setCreaterCode(loginUser.getUserCode());
                exchangeGoods.setCreaterName(loginUser.getUserName());
                exchangeGoods.setCreateTime(new Date());
                exchangeGoods.setStatus(EnableStatus.ENABLE.getStatus());
                exchangeGoods.setUpdateTime(new Date());
                exchangeGoods.setModifierId(loginUser.getUserId());
                exchangeGoods.setModifierCode(loginUser.getUserCode());
                exchangeGoods.setModifierName(loginUser.getUserName());

                exchangeGoodsMapper.insertSelective(exchangeGoods);
            } else {
                IntegralExchangeGoods exchangeGoods = exchangeGoodsMapper.selectByPrimaryKey(requestVO.getId());
                exchangeGoods.setIntegralExchange(requestVO.getIntegralExchange());
                exchangeGoods.setUpdateTime(new Date());
                exchangeGoods.setModifierId(loginUser.getUserId());
                exchangeGoods.setModifierCode(loginUser.getUserCode());
                exchangeGoods.setModifierName(loginUser.getUserName());
                exchangeGoodsMapper.updateByPrimaryKeySelective(exchangeGoods);
            }

        }

        return result;
    }


    @Override
    public void delete(Long id) {

        List<IntegralExchangeDtl> resutlList = integralExchangeDtlMapper.selectByExchangeGoodsId(id);
        if (resutlList.size() > 0) {
            throw new BusinessException(SysCode.FAIL.getCode(), "[" +resutlList.get(0).getGoodsName() +"] 删除失败，与积分兑换存在关联关系");
        }
        exchangeGoodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<IntegralExchangeGoodsVO> selectAll(UserVO loginUser) {
        Long eId = loginUser.getEnterpriseId();
        //门店: 会员管理由总部控制时，读取总部的设置
        EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(eId);
        if (!loginUser.getChainType().equals(ChainType.Headquarters.getCode())
                && 0 == enterpriseBus.getMemberInfo()){
            eId = loginUser.getParentId();
        }

        return exchangeGoodsMapper.selectAll(eId);
    }

    @Override
    public List<StockGoodsVO> searchStockGoodsVO(String key, UserVO loginUser) {
        //门店: 会员管理选择自主控制时，允许门店对总部商品和自己门店商品进行设置
        List<Long> enterpriseIdList = new ArrayList<>();
        EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(loginUser.getEnterpriseId());
        if (!loginUser.getChainType().equals(ChainType.Headquarters.getCode())){
            if (1 == enterpriseBus.getMemberInfo()){
                enterpriseIdList.add(loginUser.getEnterpriseId());
                enterpriseIdList.add(loginUser.getParentId());
            }else {
                enterpriseIdList.add(loginUser.getParentId());
            }
        }else {
            enterpriseIdList.add(loginUser.getEnterpriseId());
        }
        return exchangeGoodsMapper.searchStockGoodsVO(enterpriseIdList, key);

    }

    @Override
    public List<IntegralExchangeGoodsVO> searchIntegralGoods(String key, UserVO loginUser) {
        //门店: 会员管理由总部控制时，读取总部的数据
        Long eId = loginUser.getEnterpriseId();
        EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(eId);
        if (!loginUser.getChainType().equals(ChainType.Headquarters.getCode())
                && 0 == enterpriseBus.getMemberInfo()){
            eId = loginUser.getParentId();
        }
        return exchangeGoodsMapper.searchIntegralGoods(loginUser.getEnterpriseId(),eId, key);
    }
}
