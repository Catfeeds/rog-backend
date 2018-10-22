package com.rograndec.feijiayun.chain.business.basic.supplier.service.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierVarietiesMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierVarieties;
import com.rograndec.feijiayun.chain.business.basic.supplier.service.SupplierVarietiesService;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierVarietiesExportVO;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderDetail;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.GoodsOrderVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.component.SupplierComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;

/**
 * Created by madong on 2017/8/31.
 */
@Service
public class SupplierVarietiesServiceImpl implements SupplierVarietiesService {
    @Autowired
    SupplierVarietiesMapper supplierVarietiesMapper;
    @Autowired
    SupplierMapper supplierMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsTaxRateMapper goodsTaxRateMapper;
    @Autowired
    PurchaseOrderDetailMapper purchaseOrderDetailMapper;
    @Autowired
    PurchaseGeneralComponent purchaseGeneralComponent;
    @Autowired
    private SupplierComponent supplierComponent;
    @Autowired
    EnterpriseMapper enterpriseMapper;

    @Override
    public List<Map> getSupplier(Long enterpriseId) throws Exception {
        return supplierMapper.selectIdNameByEnterpriseId(enterpriseId);
    }

    @Override
    public int saveSupplierVarieties(UserVO loginUser, List<SupplierVarieties> supplierVarieties) throws Exception {
        for(SupplierVarieties supplierVarietie : supplierVarieties){
            Long headEnterpriseId = ChainType.getHeadEnterpriseId(loginUser);
            supplierVarietie.setEnterpriseId(headEnterpriseId);
//            supplierVarietie.setParentId(loginUser.getParentId());
            supplierVarietie.setCreaterId(loginUser.getUserId());
            supplierVarietie.setCreaterCode(loginUser.getUserCode());
            supplierVarietie.setCreaterName(loginUser.getUserName());
            supplierVarietie.setModifierId(loginUser.getUserId());
            supplierVarietie.setModifierCode(loginUser.getUserCode());
            supplierVarietie.setModifierName(loginUser.getUserName());
            supplierVarietie.setTaxRate(goodsTaxRateMapper.selectByPrimaryKey(supplierVarietie.getTaxRateId()).getTaxRate());
            supplierVarietie.setId(null);
            supplierVarietie.setStatus(EnableStatus.ENABLE.getStatus());
        }
        int sucessTotal = supplierVarietiesMapper.batchInsert(supplierVarieties);
        return sucessTotal;
    }

    @Override
    public int updateSupplierVarietie(UserVO loginUser, SupplierVarieties supplierVarietie) throws Exception {
        SupplierVarieties supplierVarieties = supplierVarietiesMapper.selectByPrimaryKey(supplierVarietie.getId());
        //权限校验(加盟店不能更新总部数据)
        Supplier supplier = supplierMapper.selectByPrimaryKey(supplierVarieties.getSupplierId());
        if (!supplier.getOwnerId().equals(loginUser.getEnterpriseId())){
            throw new BusinessException(SysCode.FAIL.getCode(),"您没有修改权限");
        }
        Long headEnterpriseId = ChainType.getHeadEnterpriseId(loginUser);
        supplierVarietie.setEnterpriseId(headEnterpriseId);
        supplierVarietie.setModifierId(loginUser.getUserId());
        supplierVarietie.setModifierCode(loginUser.getUserCode());
        supplierVarietie.setModifierName(loginUser.getUserName());
        supplierVarietie.setTaxRate(goodsTaxRateMapper.selectByPrimaryKey(supplierVarietie.getTaxRateId()).getTaxRate());
        return supplierVarietiesMapper.updateByPrimaryKeySelective(supplierVarietie);
    }

    @Override
    public boolean isHappenedBusiness(Long id, Long goodsId, Long supplierId, UserVO loginUser) throws Exception {
        Long headEnterpriseId = ChainType.getHeadEnterpriseId(loginUser);
        List<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrderDetailMapper.selectOrderByGoodsIdAndEnterpriseIdAndSupplierId(goodsId,supplierId,headEnterpriseId);
        return purchaseOrderDetails == null || purchaseOrderDetails.isEmpty();
    }

    @Override
    public int deleteSupplierVarietie(Long id, UserVO loginUser) throws Exception {
        SupplierVarieties supplierVarieties = supplierVarietiesMapper.selectByPrimaryKey(id);
        //权限校验(加盟店不能更新总部数据)
        Supplier supplier = supplierMapper.selectByPrimaryKey(supplierVarieties.getSupplierId());
        if (!supplier.getOwnerId().equals(loginUser.getEnterpriseId())){
            throw new BusinessException(SysCode.FAIL.getCode(),"您没有删除权限");
        }
        return supplierVarietiesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void excelExport(UserVO loginUser, OutputStream output, String supplierInfo, String goodsInfo) throws Exception {
        //企业ID
         Map<String,Object> paramMap = new HashMap<>();
        supplierComponent.setDistinguishDivisionParamMap(loginUser,paramMap);
        paramMap.put("supplierInfo",supplierInfo);
        paramMap.put("goodsInfo",goodsInfo);
        List<SupplierVarietiesExportVO> list = supplierVarietiesMapper.selectExport(paramMap);
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("supplierCode", "供货单位编码");
        map.put("supplierName", "供货单位名称");
        map.put("goodsCode", "商品编码");
        map.put("goodsName", "通用名称");
        map.put("goodsUnitName", "单位");
        map.put("dosageName", "剂型");
        map.put("specification", "规格");
        map.put("manufacturer", "生产厂商");
        map.put("goodsUnitName", "单位");
        map.put("supplierGoodsCode", "供货单位商品编码");
        map.put("managementModeName", "经营方式");
        map.put("agreementPrice", "协议含税单价");
        map.put("taxRate", "税率");
        map.put("soleSupplierName", "唯一供货单位");
        map.put("lastPurPrice", "最新采购单价");

        List<String> titleSecond = new ArrayList<>();
        StringBuilder end = new StringBuilder();
        List<String> needTotalName = new ArrayList<>();
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("供货单位经营品种");
        purchaseGeneralComponent.commExcelExport(output,map,list,name,titleSecond,end.toString(),false,needTotalName);

    }

    @Override
    public Page getSupplierVarieties(UserVO userVO, String supplierInfo, String goodsInfo, String orderName, String orderType, Integer pageNo, Integer pageSize) {
        Map param = new HashMap();
        //企业ID
        supplierComponent.setDistinguishDivisionParamMap(userVO,param);
        if(supplierInfo==null || "-1".equals(supplierInfo) || "".equals(supplierInfo)){
            supplierInfo = null;
        }
        if(goodsInfo==null || "-1".equals(goodsInfo) || "".equals(goodsInfo)){
            goodsInfo = null;
        }
        param.put("supplierInfo",supplierInfo);
        param.put("goodsInfo",goodsInfo);
        if(orderName.equals("-1") && orderType.equals("-1")){
            param.put("orderName",null);
            param.put("orderType",null);
        }else {
            if(orderName.equals("supplierCode")){
                param.put("orderName","supplier_code");
            }
            if(orderName.equals("goodsCode")){
                param.put("orderName","goods_code");
            }

            param.put("orderType",orderType);
        }
        param.put("pageSize",pageSize);
        param.put("start",(pageNo-1)*pageSize);
        List<SupplierVarietiesExportVO> supplierVarieties = supplierVarietiesMapper.selectByEnterpriseId(param);
        for (SupplierVarietiesExportVO supplierVarietiesVO : supplierVarieties) {
            supplierVarietiesVO.setIsOwner(supplierVarietiesVO.getEnterpriseId().equals(userVO.getEnterpriseId())? 1:0);

            Goods goods = goodsMapper.selectByPrimaryKey(supplierVarietiesVO.getGoodsId());
            if(goods == null){
                throw new BusinessException("无效的商品ID："+ supplierVarietiesVO.getGoodsId());
            }
            Enterprise goodsEnterprise = enterpriseMapper.selectByPrimaryKey(goods.getOwnerId());

            Supplier supplier = supplierMapper.selectByPrimaryKey(supplierVarietiesVO.getSupplierId());
            if(supplier == null){
                throw new BusinessException("无效的供应商ID："+ supplierVarietiesVO.getSupplierId());
            }

            if(supplier.getOwnerId().equals(userVO.getEnterpriseId())){
                supplierVarietiesVO.setIsOwner(1);
            }else {
                supplierVarietiesVO.setIsOwner(0);
            }
            Enterprise supplierEnterprise = enterpriseMapper.selectByPrimaryKey(supplier.getOwnerId());

            if(goodsEnterprise.getChainType() == ChainType.Division.getCode() && supplierEnterprise.getChainType() == ChainType.Division.getCode()){
                supplierVarietiesVO.setFranchisedStoreFlag(1);
            }else{
                supplierVarietiesVO.setFranchisedStoreFlag(0);
            }
        }
        Page page = new Page(pageNo,pageSize);
        page.setResult(supplierVarieties);
//        param.replace("pageSize",null);
//        param.replace("start",null);
        page.setTotalRecord(supplierVarietiesMapper.selectCountByEnterpriseId(param));
        return page;
    }

    @Override
    public Page<List<GoodsOrderVO>> getSupplierVarietiesGoods(UserVO userVO, Long supplierId, Integer pageNo, Integer pageSize, String param) {
        Map map = new HashMap();

        supplierComponent.setParamMap(userVO,map);

        map.put("supplierId",supplierId);
        map.put("param",param);

        Supplier supplier = supplierMapper.selectByPrimaryKey(supplierId);
        List<Long> supplierIdList = new ArrayList<>();
        String businessScopeId = supplier.getBusinessScopeId();//供应商经营品种id
        if(businessScopeId == null || "".equals(businessScopeId.trim())){
            map.put("supplierIdList",null);
        }else {
            for (String id : supplier.getBusinessScopeId().split(",")) {
                supplierIdList.add(Long.valueOf(id.trim()));
            }
            map.put("supplierIdList", supplierIdList);
        }
        //如果选择唯一供货单位.则该条对应的商品不在选择
        Set goodsIdList = new HashSet<>();
        List<SupplierVarieties> suppliersVarietiesList = supplierVarietiesMapper.getEnterpriseId(map);
        for (SupplierVarieties supplierVarieties : suppliersVarietiesList){
            if(supplierVarieties.getSoleSupplier() != null){
                if (supplierVarieties.getSoleSupplier() == 1){//唯一供货单位
                    goodsIdList.add(supplierVarieties.getGoodsId());
                }
            }
            /*if(supplierVarieties.getSupplierId().equals(supplierId))//该供应商已经选择的商品不在显示
                goodsIdList.add(supplierVarieties.getGoodsId());*/
        }
        map.put("goodsIdList",goodsIdList.size()==0?null:goodsIdList);
        map.put("pageSize",pageSize);
        map.put("start",(pageNo-1)*pageSize);
        List<Goods> goodsList = goodsMapper.getSupplierVarietiesGoods(map);
        List<GoodsOrderVO> goodsOrderVOS = new ArrayList<>();
        for (Goods goods : goodsList){
            GoodsOrderVO goodsOrderVO = new GoodsOrderVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goods,goodsOrderVO);
            goodsOrderVOS.add(goodsOrderVO);
        }
        Page<List<GoodsOrderVO>> page = new Page(pageNo,pageSize);
        page.setResult(goodsOrderVOS);
        map.replace("pageSize",null);
        map.replace("start",null);
        page.setTotalRecord(goodsMapper.getSupplierVarietiesGoodsCount(map));
        return page;
    }

}
