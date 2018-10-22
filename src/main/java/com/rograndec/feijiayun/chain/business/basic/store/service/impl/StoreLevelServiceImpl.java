package com.rograndec.feijiayun.chain.business.basic.store.service.impl;

import com.rograndec.feijiayun.chain.business.basic.store.dao.StoreLevelMapper;
import com.rograndec.feijiayun.chain.business.basic.store.entity.StoreLevel;
import com.rograndec.feijiayun.chain.business.basic.store.service.StoreLevelService;
import com.rograndec.feijiayun.chain.business.basic.store.vo.ExportVO;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.*;

/**
 * Created by madong on 2017/8/28.
 */
@Service
public class StoreLevelServiceImpl implements StoreLevelService {
    @Autowired
    StoreLevelMapper storeLevelMapper;
    @Autowired
    EnterpriseMapper enterpriseMapper;
    @Autowired
    PurchaseGeneralComponent purchaseGeneralComponent;

    @Override
    public List<TreePOJO> getAllStoreLevel(Long enterpriseId) throws Exception {
        List<StoreLevel> storeLevels =  storeLevelMapper.selectByEnterpriseId(enterpriseId);
        List<TreePOJO> treeList = new ArrayList<>();
        for (StoreLevel storeLevel : storeLevels){
            TreePOJO tree = new TreePOJO<>();
            tree.setId(storeLevel.getId());
            tree.setParentId(null);
            tree.setLabel(storeLevel.getCode()+"-"+storeLevel.getName());
            tree.setData(storeLevel);
            tree.setNodeShowRelateBranch(true);
            List<StoreVO> storeVOlist = enterpriseMapper.selectShopByBusinessId(enterpriseId,storeLevel.getId(),"level");
            if(storeVOlist.isEmpty()){
                tree.setLeaf(true);
                tree.setNodeShowDelete(true);
                tree.setChildren(new ArrayList<>());
            }else {
//                String[] ids = storeLevel.getStoreIds().split(",");
//                List<Long> shopIds = new ArrayList<>();
//                for(String id : ids){
//                    shopIds.add(Long.valueOf(id));
//                }
//                List<StoreVO> storeVOlist = enterpriseMapper.selectShopByIds(shopIds);
                List<TreePOJO> list = new ArrayList<>();
                for (StoreVO storeVO : storeVOlist){
                    TreePOJO t = new TreePOJO();
                    t.setId(Long.valueOf(storeVO.getId()));
                    t.setParentId(storeLevel.getId());
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
    public int insertStoreLevel(UserVO loginUser, StoreLevel storeLevel) throws Exception {
        storeLevel.setCreaterId(loginUser.getUserId());
        storeLevel.setCreaterCode(loginUser.getUserCode());
        storeLevel.setCreaterName(loginUser.getUserName());
        storeLevel.setModifierId(loginUser.getUserId());
        storeLevel.setModifierCode(loginUser.getUserCode());
        storeLevel.setModifierName(loginUser.getUserName());
        storeLevel.setEnterpriseId(loginUser.getEnterpriseId());
        storeLevel.setStatus(1);//新增的默认状态为启用
        storeLevel.setId(null);
        return storeLevelMapper.insert(storeLevel);
    }

    @Override
    public int saveStoreLevelShops(UserVO loginUser, Long id, List<Long> shopIds) throws Exception {
        for(Long shopId : shopIds){
            enterpriseMapper.updateBusinessStore(loginUser.getEnterpriseId(),shopId,"level",id);
        }
        return 1;
    }

    @Override
    public List<StoreVO> getNoChooseShops(Long enterpriseId) throws Exception {
        return enterpriseMapper.selectNotChooseShop(enterpriseId,"level");
    }

    @Override
    public int updateStoreLevel(UserVO loginUser, StoreLevel storeLevel) throws Exception {
        storeLevel.setModifierId(loginUser.getUserId());
        storeLevel.setModifierCode(loginUser.getUserCode());
        storeLevel.setModifierName(loginUser.getUserName());
        return storeLevelMapper.updateByPrimaryKeySelective(storeLevel);
    }

    @Override
    public int removeStoreLevelShop(UserVO loginUser, Long id,Long shopId) throws Exception {
//        StoreLevel storeLevel = storeLevelMapper.selectByPrimaryKey(id);
//        if(StringUtils.isBlank(storeLevel.getStoreIds())){
//            throw new BusinessException("该级别下已经没有门店了~");
//        }else if(storeLevel.getStoreIds().equals(shopId+"")){
//            storeLevel.setStoreIds(null);
//        }else {
//            String[] shopIds = storeLevel.getStoreIds().split(",");
//            StringBuilder shopIdList = new StringBuilder();
//            for(int i = 0; i < shopIds.length; i++){
//                if(shopIds[i].equals(shopId+""))
//                    continue;
//                shopIdList.append(shopIds[i]+",");
//            }
//            storeLevel.setStoreIds(shopIdList.length()==0?"":shopIdList.toString().substring(0,shopIdList.length()-1));
//        }
//        return storeLevelMapper.updateByPrimaryKey(storeLevel);
        List<StoreVO> storeVOS = enterpriseMapper.selectShopByBusinessId(loginUser.getEnterpriseId(),id,"level");
        if(storeVOS.isEmpty()){
            throw new BusinessException("该级别下已经没有门店了~");
        }
        return enterpriseMapper.removeBusinessStore(loginUser.getEnterpriseId(),shopId,"level",id);
    }

    @Override
    public boolean canDelete(UserVO loginUser, Long id) throws Exception {
        List<StoreVO> storeVOlist = enterpriseMapper.selectShopByBusinessId(loginUser.getEnterpriseId(),id,"level");
        return storeVOlist.isEmpty();
    }

    @Override
    public int deleteStoreLevel(Long id) throws Exception {
        return storeLevelMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int removeAllStoreLevelShop(UserVO loginUser, Long id) throws Exception {
        return enterpriseMapper.removeBusinessStore(loginUser.getEnterpriseId(),null,"level",id);
    }

    @Override
    public void excelExport(UserVO loginUser, OutputStream output) throws Exception {
        List<ExportVO> list = storeLevelMapper.selectExport(loginUser.getEnterpriseId());
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("name","级别名称");
        map.put("storeCode","门店编码");
        map.put("storeName","门店名称");
        List<String> titleSecond = new ArrayList<>();
        StringBuilder end = new StringBuilder();
        List<String> needTotalName = new ArrayList<>();
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("门店级别");
        purchaseGeneralComponent.commExcelExport(output,map,list,name,titleSecond,end.toString(),false,needTotalName);

    }

    @Override
    public boolean checkCodeExists(UserVO loginUser, StoreLevel storeLevel) throws Exception {
        List<StoreLevel> storeLevels = storeLevelMapper.selectByEnterpriseId(loginUser.getEnterpriseId());
        for(StoreLevel store : storeLevels){
            if(store.getId().equals(storeLevel.getId())){
                continue;
            }
            if(store.getName().equals(storeLevel.getName()) || store.getCode().equals(storeLevel.getCode())){
                return true;
            }
        }
        return false;
    }

}
