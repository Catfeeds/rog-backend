package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierQualificationConfigMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierSalerMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierSaler;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierDetailVO;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierQulificationVO;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierSalerVO;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.system.set.dao.BusinessScopeMapper;
import com.rograndec.feijiayun.chain.business.system.set.vo.BusinessScopeVO;
import com.rograndec.feijiayun.chain.common.constant.BusinessVariety;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 功能描述：
 * Created by ST on 2017/10/20 19:18
 */
@Component
public class SupplierComponent {
    @Autowired
    private SupplierSalerMapper supplierSalerMapper;
    @Autowired
    private BusinessScopeMapper businessScopeMapper;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private SupplierQualificationConfigMapper supplierQualificationConfigMapper;

    public List<SupplierQulificationVO> getDefaultQualification(UserVO user, List<Long> idList) {
        if (idList == null || idList.size() <= 0){
            return new ArrayList<>();
        }
        Long enterpriseId = 0L;
        enterpriseId = (user.getChainType() == 0 ? user.getEnterpriseId():user.getParentId());
        List<SupplierQulificationVO> list = supplierQualificationConfigMapper.getDefaultSupplierQulification(enterpriseId);
        //需要根据经营品种来展现对应所存在的默认资质信息
        List<SupplierQulificationVO> returnList = new ArrayList<>();
        if (list != null && list.size() > 0){
            list.forEach(l ->{
                String businessVariety = l.getBusinessVariety();
                String[] sp = businessVariety.split(",");
                boolean flag = false;
                for (String s : sp) {
                    //防止存放格式,2,3,4,
                    if ("".equals(s)){
                        continue;
                    }
                    Long id = Long.parseLong(s);
                    if (idList.contains(id)){
                        flag = true;
                    }
                }
                if (flag){
                    returnList.add(l);
                }
            });
        }
        return returnList;
    }

    /**
     * 获得总部的默认企业资质
     * @param user
     * @return
     */
    public List<SupplierQulificationVO> getDefaultQualification(User user) {
        List<SupplierQulificationVO> list = supplierQualificationConfigMapper.getDefaultSupplierQulification(user.getEnterpriseId());
        return list;
    }

    public List<SupplierSalerVO> getSupplierSalerVOS(Long enterpriseId,Integer status, Long supplierId ) {

        //经营范围
        List<BusinessScopeVO> businessScopeVOList = businessScopeMapper.getBusinessScopeVOList(null, status, enterpriseId);
        List<SupplierSaler> supplierSalers = supplierSalerMapper.selectBySuppliereId(supplierId);
        List<SupplierSalerVO> supplierSalerVOList = supplierSalers.stream().map(saler -> copyBean(saler)).collect(Collectors.toList());
        supplierSalerVOList.forEach(it->{
            //授权品种ID（多个用逗号分隔）
            String authorizedVariety = it.getAuthorizedVariety();
            if(StringUtils.isNotBlank(authorizedVariety)){
                String[] authorizedVarietys = authorizedVariety.split(",");
                if(authorizedVarietys.length > 0){
                    Stream.of(authorizedVarietys).forEach(a -> {
                        String authorizedVariety1 = it.getAuthorizedVariety();
                        it.setAuthorizedVarietyNames(authorizedVariety1 == null ? authorizedVariety1 : authorizedVariety1 +"," + BusinessVariety.getName(Integer.valueOf(a)));
                    });
                }

            }

            //授权品种范围ID（多个用逗号分隔）
            String businessScopeIds = it.getAuthorizedVarietyScope();
            if(StringUtils.isNotBlank(businessScopeIds)){
                String[] ids = businessScopeIds.split(",");
                if(ids.length > 0){
                    Stream.of(ids).forEach((String p)->{
                        String scopeName = businessScopeVOList.stream().filter(q-> p.equals(q.getId()+"")).findFirst().orElse(new BusinessScopeVO()).getName();
                        String businessScopeName = it.getAuthorizedVarietyScopeNames();
                        if(scopeName != null){
                            it.setAuthorizedVarietyScopeNames(businessScopeName == null ? scopeName : businessScopeName + "," + scopeName);
                        }
                    });
                }
            }


        });
        return supplierSalerVOList;
    }

    public SupplierDetailVO refactorSupplierDetail(Long supplierId){
        Integer status = EnableStatus.ENABLE.getStatus();
        SupplierDetailVO detailSupplier = supplierMapper.getDetailSupplier(supplierId);

        if(detailSupplier != null){
            Long enterpriseId = detailSupplier.getEnterpriseId();
            //经营范围
            List<BusinessScopeVO> businessScopeVOList = businessScopeMapper.getBusinessScopeVOList(null, status, enterpriseId);
            //行政区域
            detailSupplier.setRegionalism(Optional.ofNullable(detailSupplier.getProvinceName()).orElse("")
                    + Optional.ofNullable(detailSupplier.getCityName()).orElse("") + Optional.ofNullable(detailSupplier.getAreaName()).orElse(""));

            //经营范围
            String businessScopeIds = detailSupplier.getBusinessScopeId();
            if(StringUtils.isNotBlank(businessScopeIds)){
                String[] ids = businessScopeIds.split(",");
                if(ids.length > 0){
                    Stream.of(ids).forEach((String p)->{
                        String scopeName = businessScopeVOList.stream().filter(q-> p.equals(q.getId()+"")).findFirst().orElse(new BusinessScopeVO()).getName();
                        String businessScopeName = detailSupplier.getBusinessScopeName();
                        if(scopeName != null){
                            detailSupplier.setBusinessScopeName(businessScopeName == null ? scopeName : businessScopeName + "," + scopeName);
                        }
                    });
                }

            }
            //经营品种
            String businessVarietyId = detailSupplier.getBusinessVariety();
            if(StringUtils.isNotBlank(businessVarietyId)){
                String[] varietyIds = businessVarietyId.split(",");
                if(varietyIds.length > 0){
                    Stream.of(varietyIds).forEach((String v)->{
                        String name = BusinessVariety.getName(Integer.valueOf(v));
                        String businessVarietyName = detailSupplier.getBusinessVarietyName();
                        if(name != null){
                            detailSupplier.setBusinessVarietyName(businessVarietyName == null ? name : businessVarietyName + "," + name);
                        }
                    });
                }

            }
        }


        return detailSupplier;

    }

    private SupplierSalerVO copyBean(SupplierSaler saler){
        SupplierSalerVO supplierSalerVO = new SupplierSalerVO();
        BeanUtils.copyProperties(saler,supplierSalerVO);
        return supplierSalerVO;
    }

    public void setParamMap( UserVO userVO,Map<String,Object> map){
        List<Long> lt = new ArrayList<>();
        //企业ID
        if(ChainType.Division.getCode() == userVO.getChainType()){
            //加盟店
            lt.add(userVO.getParentId());
            lt.add(userVO.getEnterpriseId());
            map.put("list",lt);
            map.put("enterpriseId",userVO.getParentId());
        } else if(ChainType.Headquarters.getCode() == userVO.getChainType()){
            //总部
            map.put("enterpriseId",userVO.getEnterpriseId());
        } else if(ChainType.Selfoperatedshop.getCode() == userVO.getChainType()){
            //自营店
            lt.add(userVO.getParentId());
            map.put("list",lt);
            map.put("enterpriseId",userVO.getParentId());
        }
    }
    
    
    public void setDistinguishDivisionParamMap( UserVO userVO,Map<String,Object> map){
        List<Long> lt = new ArrayList<>();
        //企业ID
        if(ChainType.Division.getCode() == userVO.getChainType()){
            //加盟店
            lt.add(userVO.getEnterpriseId());
            map.put("list",lt);
            map.put("enterpriseId",userVO.getParentId());
        } else if(ChainType.Headquarters.getCode() == userVO.getChainType()){
            //总部
            map.put("enterpriseId",userVO.getEnterpriseId());
        } else if(ChainType.Selfoperatedshop.getCode() == userVO.getChainType()){
            //自营店
            lt.add(userVO.getParentId());
            map.put("list",lt);
            map.put("enterpriseId",userVO.getParentId());
        }
    }
    
}