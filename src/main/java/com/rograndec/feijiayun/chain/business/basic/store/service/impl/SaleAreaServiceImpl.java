package com.rograndec.feijiayun.chain.business.basic.store.service.impl;

import com.rograndec.feijiayun.chain.business.basic.store.dao.SaleAreaMapper;
import com.rograndec.feijiayun.chain.business.basic.store.dao.SaleAreaStoreMapper;
import com.rograndec.feijiayun.chain.business.basic.store.entity.SaleArea;
import com.rograndec.feijiayun.chain.business.basic.store.entity.SaleAreaStore;
import com.rograndec.feijiayun.chain.business.basic.store.service.SaleAreaService;
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
public class SaleAreaServiceImpl implements SaleAreaService {
    @Autowired
    SaleAreaMapper saleAreaMapper;
    @Autowired
    EnterpriseMapper enterpriseMapper;
    @Autowired
    PurchaseGeneralComponent purchaseGeneralComponent;


    @Override
    public List<TreePOJO> getSaleArea(Long enterpriseId) throws Exception {
        List<TreePOJO> listParent = new ArrayList<>();
        List<SaleArea> saleAreas = saleAreaMapper.selectByEnterpriseId(enterpriseId);
        for (SaleArea saleArea : saleAreas){
            if(0 == saleArea.getParentAreaId()){
                TreePOJO t = new TreePOJO();
                t.setData(saleArea);
                t.setId(saleArea.getId());
                t.setParentId(null);
                t.setLabel(saleArea.getCode()+"-"+saleArea.getName());
                listParent.add(t);
            }
        }
        for(TreePOJO tree : listParent){
            SaleArea saleArea = (SaleArea)tree.getData();
            List<TreePOJO> chileren = getChild(saleArea.getId(),saleAreas);
            tree.setChildren(chileren==null?new ArrayList<>():chileren);
        }
        return getListArea(enterpriseId,listParent);
    }

    private List<TreePOJO> getChild(Long id,List<SaleArea> p_area) {
        List<TreePOJO> childList = new ArrayList<>();
        for(SaleArea saleArea : p_area){
            if(saleArea.getParentAreaId().equals(id)){
                TreePOJO tree = new TreePOJO();
                tree.setData(saleArea);
                tree.setId(saleArea.getId());
                tree.setParentId(id);
                tree.setLabel(saleArea.getCode()+"-"+saleArea.getName());
                childList.add(tree);
            }
        }
        for(TreePOJO tree : childList){
            for(SaleArea saleArea : p_area){
                SaleArea area = (SaleArea)tree.getData();
                if (area.getId() == saleArea.getParentAreaId()){
                    tree.setChildren(getChild(area.getId(),p_area));
                }
            }
        }
        return childList;
    }

    private List<TreePOJO> getListArea(Long enterpriseId, List<TreePOJO> listParent){
        for(TreePOJO tree : listParent){
            if(tree.getChildren() == null){
                List<TreePOJO> storeTree = getStore(enterpriseId,((SaleArea)tree.getData()).getId());
                if(storeTree.isEmpty() || storeTree.size() == 0){
                    tree.setLeaf(true);
                    tree.setNodeShowDelete(true);

                }else{
                    tree.setNodeShowRemove(true);
                }
                tree.setNodeShowRelateBranch(true);
//                tree.setNodeShowRemove(true);
                tree.setChildren(storeTree);
            } else if(tree.getChildren().isEmpty() || tree.getChildren().size()==0){
                List<TreePOJO> storeTree = getStore(enterpriseId,((SaleArea)tree.getData()).getId());
                if(storeTree.isEmpty() || storeTree.size() == 0){
                    tree.setLeaf(true);
                    tree.setNodeShowDelete(true);
                }else{
                    tree.setNodeShowRemove(true);
                }
                tree.setNodeShowRelateBranch(true);
                tree.setChildren(storeTree);
            } else {
                getListArea(enterpriseId, tree.getChildren());
            }
        }
        return listParent;
    }

    /**
     * 获取片区下的门店
     * @param saleAreaId
     * @return
     */
    private List<TreePOJO> getStore(Long enterpriseId, Long saleAreaId){
        List<StoreVO> storeVOS = enterpriseMapper.selectShopByBusinessId(enterpriseId,saleAreaId,"area");
        List<TreePOJO> list = new ArrayList<>();
        for (StoreVO storeVO : storeVOS){
            TreePOJO tree = new TreePOJO();
            tree.setId(Long.valueOf(storeVO.getId()));
            tree.setParentId(saleAreaId);
            tree.setData(storeVO);
            tree.setChildren(new ArrayList<>());
            tree.setLeaf(true);
            tree.setLabel(storeVO.getName());
            tree.setNodeShowRemove(true);
            tree.setNodeShowDelete(false);
            tree.setSupplierShow(false);
            list.add(tree);
        }
        return list;
    }

    @Override
    public List<StoreVO> getShops(Long id, Long enterpriseId) throws Exception {
        return enterpriseMapper.selectNotChooseShop(enterpriseId,"area");

    }

    @Override
    public boolean canDelete(UserVO loginUser, Long id) throws Exception {
        List<StoreVO> storeVOlist = enterpriseMapper.selectShopByBusinessId(loginUser.getEnterpriseId(),id,"area");
        return storeVOlist.isEmpty();
    }


    @Override
    public int deleteSaleArea(Long id, Long enterpriseId) throws Exception {
        Map<String,Long> param = new HashMap();
        param.put("saleAreaId",id);
        param.put("enterpriseId",enterpriseId);
        List<SaleArea> list = saleAreaMapper.selectByEnterpriseId(enterpriseId);
        List<Long> ids = new ArrayList<>();
        getDeleteSaleAreaIds(list,id,ids);
        return saleAreaMapper.deleteByPrimaryKeys(ids);
    }

    private void getDeleteSaleAreaIds(List<SaleArea> list, Long id, List<Long> ids) {
        ids.add(id);
        int count = 0;
        for (SaleArea saleArea : list){
            if(saleArea.getParentAreaId() == id){
                getDeleteSaleAreaIds(list,saleArea.getId(),ids);
            }else{
                count++;
                if(count==list.size()){
                    return;
                }
            }
        }
    }

    @Override
    public int removeSaleAreaShop(Long id, Long enterpriseId,Long shopId) throws Exception {
        if(shopId == -1l){
            return enterpriseMapper.removeBusinessStore(enterpriseId,null,"area",id);
        }else {
            List<StoreVO> storeVOS = enterpriseMapper.selectShopByBusinessId(enterpriseId,id,"area");
            if(storeVOS.isEmpty()){
                throw new BusinessException("该区域下已经没有门店了~");
            }
            return enterpriseMapper.removeBusinessStore(enterpriseId,shopId,"area",id);
        }
    }

    @Override
    public int saveSaleArea(SaleArea saleArea, UserVO loginUser) throws Exception {
        saleArea.setCreaterId(loginUser.getUserId());
        saleArea.setCreaterCode(loginUser.getUserCode());
        saleArea.setCreaterName(loginUser.getUserName());
        saleArea.setModifierId(loginUser.getUserId());
        saleArea.setModifierCode(loginUser.getUserCode());
        saleArea.setModifierName(loginUser.getUserName());
        saleArea.setEnterpriseId(loginUser.getEnterpriseId());
        saleArea.setParentAreaId(saleArea.getParentAreaId()==null?0l:saleArea.getParentAreaId());
        saleArea.setStatus(1);//新增的默认状态为启用
        saleArea.setId(null);
        if(saleArea.getParentAreaId()==0l){
            saleArea.setLevel(0);
        }else {
            SaleArea area = saleAreaMapper.selectByPrimaryKey(saleArea.getParentAreaId());
            saleArea.setLevel(area.getLevel()+1);
        }
        return saleAreaMapper.insertSelective(saleArea);
    }

    @Override
    public int saveSaleAreaShops(List<Enterprise> shops, UserVO loginUser) throws Exception {
        return 0;
    }

    @Override
    public int updateSaleArea(SaleArea saleArea, UserVO loginUser) throws Exception {
        saleArea.setModifierId(loginUser.getUserId());
        saleArea.setModifierCode(loginUser.getUserCode());
        saleArea.setModifierName(loginUser.getUserName());
        return saleAreaMapper.updateByPrimaryKeySelective(saleArea);
    }

    @Override
    public int updateSaleAreaShops(Long id, List<Long> shopIds, UserVO loginUser) throws Exception {
        for(Long shopId : shopIds){
            enterpriseMapper.updateBusinessStore(loginUser.getEnterpriseId(),shopId,"area",id);
        }
        return 1;
    }

    @Override
    public void excelExport(UserVO loginUser, OutputStream output) throws Exception {
        List<ExportVO> list = saleAreaMapper.selectExport(loginUser.getEnterpriseId());
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("name","片区名称");
        map.put("storeCode","门店编码");
        map.put("storeName","门店名称");
        List<String> titleSecond = new ArrayList<>();
        StringBuilder end = new StringBuilder();
        List<String> needTotalName = new ArrayList<>();
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("销售片区");
        purchaseGeneralComponent.commExcelExport(output,map,list,name,titleSecond,end.toString(),false,needTotalName);
    }

    @Override
    public List<SaleArea> getSaleAreaParent(Long enterpriseId) {
        List<SaleArea> saleAreas = saleAreaMapper.getByEnterpriseId(enterpriseId);//返回所有的区域集合
        List<Long> saleAreaIds = new ArrayList<>();
        for (SaleArea saleArea : saleAreas){
            saleAreaIds.add(saleArea.getId());
        }
        List<Long> shopids = saleAreaMapper.selectStoreMapBySaleAreaId(enterpriseId);//返回所有有门店的区域id
        List<SaleArea> saleAreaHasShop = new ArrayList<>();//记录有门店的区域
        //先将有门店的区域从所有区域中去除
        Iterator<SaleArea> it = saleAreas.iterator();
        while (it.hasNext()){
            SaleArea saleArea = it.next();
            if(shopids.contains(saleArea.getId())){
                if(saleArea.getParentAreaId() != 0l)
                    saleAreaHasShop.add(saleArea);
                it.remove();
            }
        }

        return getNoShopArea(saleAreas,saleAreaHasShop);
    }

    /**
     * 递归删除所有有门店的父级区域
     * @param saleAreas
     * @param saleAreaHasShop
     * @return
     */
    private List<SaleArea> getNoShopArea(List<SaleArea> saleAreas, List<SaleArea> saleAreaHasShop) {
        List<SaleArea> hasShop = new ArrayList<>();//记录有门店的区域
        Iterator<SaleArea> it = saleAreas.iterator();
        while (it.hasNext()){
            SaleArea saleArea = it.next();
            for(SaleArea shop : saleAreaHasShop){
                if(saleArea.getId() == shop.getParentAreaId()){
                    if(saleArea.getParentAreaId() != 0l)
                        hasShop.add(saleArea);
                    it.remove();
                }
            }
        }
        return hasShop.isEmpty()?saleAreas:getNoShopArea(saleAreas,hasShop);
    }

    @Override
    public boolean checkCodeExists(UserVO loginUser, SaleArea saleArea) throws Exception {
        List<SaleArea> saleAreas = saleAreaMapper.selectByEnterpriseId(loginUser.getEnterpriseId());
        for(SaleArea sale : saleAreas){
            if(sale.getId().equals(saleArea.getId())){
                continue;
            }
            if(sale.getName().equals(saleArea.getName()) || sale.getCode().equals(saleArea.getCode())){
                return true;
            }
        }
        return false;
    }
}
