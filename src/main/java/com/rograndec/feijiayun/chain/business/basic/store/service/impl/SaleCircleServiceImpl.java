package com.rograndec.feijiayun.chain.business.basic.store.service.impl;

import com.rograndec.feijiayun.chain.business.basic.store.dao.SaleCircleMapper;
import com.rograndec.feijiayun.chain.business.basic.store.entity.SaleCircle;
import com.rograndec.feijiayun.chain.business.basic.store.service.SaleCircleService;
import com.rograndec.feijiayun.chain.business.basic.store.vo.ExportVO;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by madong on 2017/8/28.
 */
@Service
public class SaleCircleServiceImpl implements SaleCircleService {

    @Autowired
    SaleCircleMapper saleCircleMapper;
    @Autowired
    EnterpriseMapper enterpriseMapper;
    @Autowired
    PurchaseGeneralComponent purchaseGeneralComponent;

    @Override
    public List<TreePOJO> getAllSaleCircle(Long enterpriseId) throws Exception {
        List<SaleCircle> saleCircles =  saleCircleMapper.selectByEnterpriseId(enterpriseId);
        List<TreePOJO> treeList = new ArrayList<>();
        for (SaleCircle saleCircle : saleCircles){
            TreePOJO tree = new TreePOJO<>();
            tree.setId(saleCircle.getId());
            tree.setParentId(null);
            tree.setLabel(saleCircle.getCode()+"-"+saleCircle.getName());
            tree.setData(saleCircle);
            tree.setNodeShowRelateBranch(true);
            List<StoreVO> storeVOlist = enterpriseMapper.selectShopByBusinessId(enterpriseId, saleCircle.getId(),"circle");
            if(storeVOlist.isEmpty()){
                tree.setLeaf(true);
                tree.setNodeShowDelete(true);
                tree.setChildren(new ArrayList<>());
            }else {
//                String[] ids = saleCircle.getStoreIds().split(",");
//                List<Long> shopIds = new ArrayList<>();
//                for(String id : ids){
//                    shopIds.add(Long.valueOf(id));
//                }
//                List<StoreVO> storeVOlist = enterpriseMapper.selectShopByIds(shopIds);
                List<TreePOJO> list = new ArrayList<>();
                for (StoreVO storeVO : storeVOlist){
                    TreePOJO t = new TreePOJO();
                    t.setId(Long.valueOf(storeVO.getId()));
                    t.setParentId(saleCircle.getId());
                    t.setLabel(storeVO.getName());
                    t.setLeaf(true);
                    t.setData(storeVO);
                    t.setChildren(new ArrayList<>());
                    list.add(t);
                }
                tree.setChildren(list);
            }
            treeList.add(tree);
        }
        return treeList;
    }

    @Override
    public int insertSaleCircle(UserVO loginUser, SaleCircle saleCircle) throws Exception {
        saleCircle.setCreaterId(loginUser.getUserId());
        saleCircle.setCreaterCode(loginUser.getUserCode());
        saleCircle.setCreaterName(loginUser.getUserName());
        saleCircle.setModifierId(loginUser.getUserId());
        saleCircle.setModifierCode(loginUser.getUserCode());
        saleCircle.setModifierName(loginUser.getUserName());
        saleCircle.setEnterpriseId(loginUser.getEnterpriseId());
        saleCircle.setStatus(1);//新增的默认状态为启用
        saleCircle.setId(null);
        return saleCircleMapper.insert(saleCircle);
    }

    @Override
    public int saveSaleCircleShops(UserVO loginUser, Long id, List<Long> shopIds) throws Exception {
        for(Long shopId : shopIds){
            enterpriseMapper.updateBusinessStore(loginUser.getEnterpriseId(),shopId,"circle",id);
        }
        return 1;
    }

    @Override
    public List<StoreVO> getNoChooseShops(Long enterpriseId) throws Exception {
        return enterpriseMapper.selectNotChooseShop(enterpriseId,"circle");
    }

    @Override
    public int updateSaleCircle(UserVO loginUser, SaleCircle saleCircle) throws Exception {
        saleCircle.setModifierId(loginUser.getUserId());
        saleCircle.setModifierCode(loginUser.getUserCode());
        saleCircle.setModifierName(loginUser.getUserName());
        return saleCircleMapper.updateByPrimaryKeySelective(saleCircle);
    }

    @Override
    public int removeSaleCircleShop(UserVO loginUser, Long id,Long shopId) throws Exception {
//        SaleCircle saleCircle = saleCircleMapper.selectByPrimaryKey(id);
//        String[] shopIds = saleCircle.getStoreIds().split(",");
//        StringBuilder shopIdList = new StringBuilder();
//        for(int i = 0; i < shopIds.length; i++){
//            if(shopIds[i].equals(shopId+""))
//                continue;
//            shopIdList.append(shopIds[i]+",");
//        }
//        saleCircle.setStoreIds(shopIdList.length()==0?"":shopIdList.toString().substring(0,shopIdList.length()-1));
//        return saleCircleMapper.updateByPrimaryKey(saleCircle);
        List<StoreVO> storeVOS = enterpriseMapper.selectShopByBusinessId(loginUser.getEnterpriseId(),id,"circle");
        if(storeVOS.isEmpty()){
            throw new BusinessException("该商圈下已经没有门店了~");
        }
        return enterpriseMapper.removeBusinessStore(loginUser.getEnterpriseId(),shopId,"circle",id);

    }

    @Override
    public boolean canDelete(UserVO loginUser, Long id) throws Exception {
        List<StoreVO> storeVOlist = enterpriseMapper.selectShopByBusinessId(loginUser.getEnterpriseId(),id,"circle");
        return storeVOlist.isEmpty();
    }

    @Override
    public int deleteSaleCircle(Long id) throws Exception {
        return saleCircleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int removeAllSaleCircleShop(UserVO loginUser, Long id) {
        return enterpriseMapper.removeBusinessStore(loginUser.getEnterpriseId(),null,"circle",id);
    }

    @Override
    public void excelExport(UserVO loginUser, OutputStream output) throws Exception {
        List<ExportVO> list = saleCircleMapper.selectExport(loginUser.getEnterpriseId());
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("name","商圈名称");
        map.put("storeCode","门店编码");
        map.put("storeName","门店名称");
        List<String> titleSecond = new ArrayList<>();
        StringBuilder end = new StringBuilder();
        List<String> needTotalName = new ArrayList<>();
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("销售商圈");
        purchaseGeneralComponent.commExcelExport(output,map,list,name,titleSecond,end.toString(),false,needTotalName);
    }

    @Override
    public boolean checkCodeExists(UserVO loginUser, SaleCircle saleCircle) throws Exception {
        List<SaleCircle> saleCircles = saleCircleMapper.selectByEnterpriseId(loginUser.getEnterpriseId());
        for(SaleCircle sale : saleCircles){
            if(sale.getId().equals(saleCircle.getId())){
                continue;
            }
            if(sale.getName().equals(saleCircle.getName()) || sale.getCode().equals(saleCircle.getCode())){
                return true;
            }
        }
        return false;
    }
}
