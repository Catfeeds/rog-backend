package com.rograndec.feijiayun.chain.business.index.warning.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserAdministrationMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserAdministration;
import com.rograndec.feijiayun.chain.business.index.constant.WarningType;
import com.rograndec.feijiayun.chain.business.index.warning.service.WarningService;
import com.rograndec.feijiayun.chain.business.index.warning.vo.EnterpriseWarnReportVO;
import com.rograndec.feijiayun.chain.business.index.warning.vo.WarningIndexVO;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.EnterpriseQualificationReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.QualificationConfigVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.goods.GoodsLicenseWarnVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.SupplierQualificationReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.UserWarningExcelVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.*;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.set.dao.WarnSetMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.WarnSet;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.string.StringSplit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WarningServiceImpl implements WarningService {

    @Autowired
    private WarnSetMapper warnSetMapper;

    @Autowired
    private UserAdministrationMapper userAdministrationMapper;

    @Autowired
    private EnterpriseBusinessMapper enterpriseBusinessMapper;

    @Override
    public List<WarningIndexVO> getWarningIndexVOs(
            List<EnterpriseWarnReportVO> enterpriseQualificationWarnReportList
            , List<SupplierQualificationReportVO> supplierQualificationList
            , List<UserWarningExcelVO> userWarningPage
            , List<GoodsLicenseWarnVO> goodsLicenseWarnNotPageNotPage
            , StockWarnExpireGoodsTotalVO stockWarnExpireGoodsTotalVO
            , StockWarnNearPeriodGoodsTotalVO stockWarnNearPeriodGoodsTotalVO
            , StockWarnStoreGoodsTotalVO stockWarnStoreGoodsTotalVO
            , StockWarnLackGoodsTotalVO stockWarnLackGoodsTotalVO
            , StockWarnLagSaleGoodsTotalVO stockWarnLagSaleGoodsTotalVO
            , List<StockWarnGoodsMaintanceVO> stockWarnGoodsMaintanceTotalVO
    ){

        List<WarningIndexVO> warningIndexVOS = new ArrayList<>();
        /**
         * 统计组织机构预警数量
         * @param enterpriseQualificationWarnReportList
         * @return
         */
        List<WarningIndexVO> enterpriseWarning = enterpriseQualificationWarnCount(enterpriseQualificationWarnReportList);
        warningIndexVOS.addAll(enterpriseWarning);


        if(!CollectionUtils.isEmpty(supplierQualificationList)) {
            /**
             * 统计供货单位机构预警数量
             * @param supplierQualificationList
             * @return
             */
            List<WarningIndexVO> supplierWarning = supplierQualificationWarnCount(supplierQualificationList);
            warningIndexVOS.addAll(supplierWarning);
        }

        /**
         * 员工预警数量
         */
        List<WarningIndexVO> userWarning = userQualificationWarnCount(userWarningPage);
        warningIndexVOS.addAll(userWarning);

        if(!CollectionUtils.isEmpty(goodsLicenseWarnNotPageNotPage)){
            /**
             * 商品品种预警统计数量
             */
            List<WarningIndexVO> goodsWarning = goodsQualificationWarnCount(goodsLicenseWarnNotPageNotPage);
            warningIndexVOS.addAll(goodsWarning);
        }


        /**
         * 过期商品预警统计数量
         */
        if(null != stockWarnExpireGoodsTotalVO){
            WarningIndexVO warnExpireGoods = stockWarnExpireGoodsWarnCount(stockWarnExpireGoodsTotalVO);
            warningIndexVOS.add(warnExpireGoods);
        }


        /**
         * 近效期商品预警数量
         * @param stockWarnNearPeriodGoodsTotalVO
         * @return
         */
        if(null != stockWarnNearPeriodGoodsTotalVO){
            WarningIndexVO warnNearPeriod = stockWarnNearPeriodGoodsCount(stockWarnNearPeriodGoodsTotalVO);
            warningIndexVOS.add(warnNearPeriod);
        }

        /**
         * 积货商品预警数量
         */
        if(null != stockWarnStoreGoodsTotalVO){
            WarningIndexVO warnNearStore = stockWarnStoreGoodsCount(stockWarnStoreGoodsTotalVO);
            warningIndexVOS.add(warnNearStore);
        }


        /**
         * 缺货商品预警数量
         * @param stockWarnLackGoodsTotalVO
         * @return
         */

        if(null != stockWarnLackGoodsTotalVO){
            WarningIndexVO warnLackGoods = stockWarnLackGoodsCount(stockWarnLackGoodsTotalVO);
            warningIndexVOS.add(warnLackGoods);
        }


        /**
         * 滞销商品预警数量
         * @param stockWarnLagSaleGoodsTotalVO
         * @return
         */
        if(null != stockWarnLagSaleGoodsTotalVO){
            WarningIndexVO warnLagSaleGoods = stockWarnLagSaleGoodsCount(stockWarnLagSaleGoodsTotalVO);
            warningIndexVOS.add(warnLagSaleGoods);
        }

        /**
         * 养护商品预警数量
         * @param stockWarnGoodsMaintanceTotalVO
         * @return
         */
        if(null != stockWarnGoodsMaintanceTotalVO){
            WarningIndexVO warnMaintance = stockWarnMaintanceCount(stockWarnGoodsMaintanceTotalVO);
            warningIndexVOS.add(warnMaintance);
        }
        return warningIndexVOS;
    }


    /**
     * 养护商品预警数量
     * @param stockWarnGoodsMaintanceTotalVO
     * @return
     */
    public WarningIndexVO stockWarnMaintanceCount( List<StockWarnGoodsMaintanceVO> stockWarnGoodsMaintanceTotalVO){


        WarningIndexVO warningIndexVO = new WarningIndexVO();

        warningIndexVO.setWarningContext("养护商品");
        if(!CollectionUtils.isEmpty(stockWarnGoodsMaintanceTotalVO)){
            warningIndexVO.setWarnInfoCount(String.valueOf(stockWarnGoodsMaintanceTotalVO.size()));
        }else{
            warningIndexVO.setWarnInfoCount("0");
        }
        warningIndexVO.setWarningType(WarningType.STOCK_CONSERVATION.getCode());
        warningIndexVO.setWarningTypeDesc("库存");

        return warningIndexVO;
    }


    /**
     * 滞销商品预警数量
     * @param stockWarnLagSaleGoodsTotalVO
     * @return
     */
    public WarningIndexVO stockWarnLagSaleGoodsCount(StockWarnLagSaleGoodsTotalVO stockWarnLagSaleGoodsTotalVO){

        List<StockWarnLagSaleGoodsVO> voList = stockWarnLagSaleGoodsTotalVO.getVoList();

        WarningIndexVO warningIndexVO = new WarningIndexVO();

        warningIndexVO.setWarningContext("滞销商品");
        if(!CollectionUtils.isEmpty(voList)){
            warningIndexVO.setWarnInfoCount(String.valueOf(voList.size()));
        }else{
            warningIndexVO.setWarnInfoCount("0");
        }
        warningIndexVO.setWarningType(WarningType.STOCK_LAGSALE_GOODS.getCode());
        warningIndexVO.setWarningTypeDesc("库存");

        return warningIndexVO;
    }

    /**
     * 缺货商品预警数量
     * @param stockWarnLackGoodsTotalVO
     * @return
     */
    public WarningIndexVO stockWarnLackGoodsCount(StockWarnLackGoodsTotalVO stockWarnLackGoodsTotalVO){

        List<StockWarnLackGoodsVO> voList = stockWarnLackGoodsTotalVO.getVoList();

        WarningIndexVO warningIndexVO = new WarningIndexVO();

        warningIndexVO.setWarningContext("缺货商品");
        if(!CollectionUtils.isEmpty(voList)){
            warningIndexVO.setWarnInfoCount(String.valueOf(voList.size()));
        }else{
            warningIndexVO.setWarnInfoCount("0");
        }
        warningIndexVO.setWarningType(WarningType.STOCK_LACK_GOODS.getCode());
        warningIndexVO.setWarningTypeDesc("库存");

        return warningIndexVO;
    }


    /**
     * 积货商品预警数量
     * @param stockWarnStoreGoodsTotalVO
     * @return
     */
    public WarningIndexVO stockWarnStoreGoodsCount(StockWarnStoreGoodsTotalVO stockWarnStoreGoodsTotalVO){

        List<StockWarnStoreGoodsVO> voList = stockWarnStoreGoodsTotalVO.getVoList();

        WarningIndexVO warningIndexVO = new WarningIndexVO();

        warningIndexVO.setWarningContext("积货商品");
        if(!CollectionUtils.isEmpty(voList)){
            warningIndexVO.setWarnInfoCount(String.valueOf(voList.size()));
        }else{
            warningIndexVO.setWarnInfoCount("0");
        }
        warningIndexVO.setWarningType(WarningType.STOCK_BACKLOG_GOODS.getCode());
        warningIndexVO.setWarningTypeDesc("库存");

        return warningIndexVO;
    }


    /**
     * 近效期商品预警数量
     * @param stockWarnNearPeriodGoodsTotalVO
     * @return
     */
    public WarningIndexVO stockWarnNearPeriodGoodsCount(StockWarnNearPeriodGoodsTotalVO stockWarnNearPeriodGoodsTotalVO){

        List<StockWarnNearPeriodGoodsVO> voList = stockWarnNearPeriodGoodsTotalVO.getVoList();

        WarningIndexVO warningIndexVO = new WarningIndexVO();

        warningIndexVO.setWarningContext("近效期商品");
        if(!CollectionUtils.isEmpty(voList)){
            warningIndexVO.setWarnInfoCount(String.valueOf(voList.size()));
        }else{
            warningIndexVO.setWarnInfoCount("0");
        }
        warningIndexVO.setWarningType(WarningType.STOCK_NEAR_PERIOD.getCode());
        warningIndexVO.setWarningTypeDesc("库存");

        return warningIndexVO;
    }


    /**
     * 过期商品预警数量
     * @param stockWarnExpireGoodsTotalVO
     * @return
     */
    public WarningIndexVO stockWarnExpireGoodsWarnCount(StockWarnExpireGoodsTotalVO stockWarnExpireGoodsTotalVO){

        if(null == stockWarnExpireGoodsTotalVO){
            return new WarningIndexVO();
        }

        List<StockWarnExpireGoodsVO> voList = stockWarnExpireGoodsTotalVO.getVoList();

        WarningIndexVO warningIndexVO = new WarningIndexVO();

        warningIndexVO.setWarningContext("过期商品");
        if(!CollectionUtils.isEmpty(voList)){
            warningIndexVO.setWarnInfoCount(String.valueOf(voList.size()));
        }else{
            warningIndexVO.setWarnInfoCount("0");
        }

        warningIndexVO.setWarningType(WarningType.STOCK_EXPIRE_GOODS.getCode());
        warningIndexVO.setWarningTypeDesc("库存");

        return warningIndexVO;
    }

    /**
     * 商品品种预警统计数量
     * @param goodsLicenseWarnNotPageNotPage
     * @return
     */
    public List<WarningIndexVO> goodsQualificationWarnCount(List<GoodsLicenseWarnVO> goodsLicenseWarnNotPageNotPage){


        List<WarningIndexVO> warningIndexVOS = new ArrayList<>();

        if(CollectionUtils.isEmpty(goodsLicenseWarnNotPageNotPage)){
            return warningIndexVOS;
        }

        Map<String,Integer> countMap = new HashMap<>();

        for(GoodsLicenseWarnVO goodsLicenseWarnVO : goodsLicenseWarnNotPageNotPage){

            Integer count = countMap.get(goodsLicenseWarnVO.getContent());
            if(null == count){
                countMap.put(goodsLicenseWarnVO.getContent(),1);
            }else {
                countMap.put(goodsLicenseWarnVO.getContent(),count+1);
            }
        }


        for(Map.Entry<String,Integer> nameEntry : countMap.entrySet()){

            WarningIndexVO warningIndexVO = new WarningIndexVO();

            warningIndexVO.setWarningContext(nameEntry.getKey());
            warningIndexVO.setWarnInfoCount(nameEntry.getValue().toString());
            warningIndexVO.setWarningType(WarningType.GOODS.getCode());
            warningIndexVO.setWarningTypeDesc(WarningType.GOODS.getValue());
            warningIndexVOS.add(warningIndexVO);
        }

        return warningIndexVOS;
    }


    /**
     * 统计员工预警数量
     * @param userWarningPage
     * @return
     */
    public List<WarningIndexVO> userQualificationWarnCount(List<UserWarningExcelVO> userWarningPage){

        if(CollectionUtils.isEmpty(userWarningPage)){
          return new ArrayList<>();
        }

        List<WarningIndexVO> warningIndexVOS = new ArrayList<>();

        Map<String,Integer> countMap = new HashMap<>();

        List<String> warningContexts = new ArrayList<>();

        for(UserWarningExcelVO userWarningPageVO : userWarningPage){
            warningContexts.add(userWarningPageVO.getWarningContext());
        }


        for(String wc : warningContexts){

            Integer count = countMap.get(wc);
            if(null == count){
                countMap.put(wc,1);
            }else {
                countMap.put(wc,count+1);
            }
        }


        for(Map.Entry<String,Integer> nameEntry : countMap.entrySet()){

            WarningIndexVO warningIndexVO = new WarningIndexVO();

            warningIndexVO.setWarningContext(nameEntry.getKey());
            warningIndexVO.setWarnInfoCount(nameEntry.getValue().toString());
            warningIndexVO.setWarningType(WarningType.USER.getCode());
            warningIndexVO.setWarningTypeDesc(WarningType.USER.getValue());
            warningIndexVOS.add(warningIndexVO);
        }

        return warningIndexVOS;
    }

    /**
     * 统计供货单位机构预警数量
     * @param supplierQualificationList
     * @return
     */
    public List<WarningIndexVO> supplierQualificationWarnCount(List<SupplierQualificationReportVO> supplierQualificationList){

        if(CollectionUtils.isEmpty(supplierQualificationList)){
            return new ArrayList<>();
        }

        List<WarningIndexVO> warningIndexVOS = new ArrayList<>();

        List<QualificationConfigVO> qualificationConfigAll = new ArrayList<>();

        Map<Long,Integer> countMap = new HashMap<>();

        Map<Long,String> qualificationNameMap = new HashMap<>();


        for(SupplierQualificationReportVO enterpriseQualificationReportVO : supplierQualificationList){

            List<QualificationConfigVO> qualificationConfigVOS = enterpriseQualificationReportVO.getQualificationConfigVOS();

            if(!CollectionUtils.isEmpty(qualificationConfigVOS)){
                qualificationConfigAll.addAll(qualificationConfigVOS);
            }
        }

        for(QualificationConfigVO qa : qualificationConfigAll){

            Integer count = countMap.get(qa.getQualificationId());

            if(null == count){
                countMap.put(qa.getQualificationId(),1);
            }else {
                countMap.put(qa.getQualificationId(),count+1);
            }

            qualificationNameMap.put(qa.getQualificationId(),qa.getWarnContent());
        }

        for(Map.Entry<Long,String> nameEntry : qualificationNameMap.entrySet()){

            WarningIndexVO warningIndexVO = new WarningIndexVO();

            Integer count = countMap.get(nameEntry.getKey());

            warningIndexVO.setWarningContext(nameEntry.getValue());
            warningIndexVO.setWarnInfoCount(count.toString());
            warningIndexVO.setWarningType(WarningType.SUPPLIER.getCode());
            warningIndexVO.setWarningTypeDesc(WarningType.SUPPLIER.getValue());
            warningIndexVOS.add(warningIndexVO);
        }

        return warningIndexVOS;
    }


    /**
     * 统计组织机构预警数量
     * @param enterpriseQualificationWarnReportList
     * @return
     */
    public List<WarningIndexVO> enterpriseQualificationWarnCount(List<EnterpriseWarnReportVO> enterpriseQualificationWarnReportList){

        if(CollectionUtils.isEmpty(enterpriseQualificationWarnReportList)){
            return new ArrayList<>();
        }
        List<WarningIndexVO> warningIndexVOS = new ArrayList<>();


        Map<Long,Integer> countMap = new HashMap<>();

        Map<Long,String> qualificationNameMap = new HashMap<>();



        for(EnterpriseWarnReportVO qa : enterpriseQualificationWarnReportList){

            Integer count = countMap.get(qa.getQualificationId());

            if(null == count){
                countMap.put(qa.getQualificationId(),1);
            }else {
                countMap.put(qa.getQualificationId(),count+1);
            }

            qualificationNameMap.put(qa.getQualificationId(),qa.getWarningContext());
        }

        for(Map.Entry<Long,String> nameEntry : qualificationNameMap.entrySet()){

            WarningIndexVO warningIndexVO = new WarningIndexVO();

            Integer count = countMap.get(nameEntry.getKey());

            warningIndexVO.setWarningContext(nameEntry.getValue());
            warningIndexVO.setWarnInfoCount(count.toString());
            warningIndexVO.setWarningType(WarningType.ENTERPRISE.getCode());
            warningIndexVO.setWarningTypeDesc(WarningType.ENTERPRISE.getValue());
            warningIndexVOS.add(warningIndexVO);
        }

        return warningIndexVOS;
    }

    @Override
    public Map<Integer, List<WarnSet>> getWarnSetTypeQualificationIdMaping(UserVO userVO){

        EnterpriseBusiness bus = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(userVO.getEnterpriseId());

        List<WarnSet> warnSetVOS = new ArrayList<>();
        /**
         * 总部控制
         */
        if(0 == bus.getWarnSet()){

            Long headEnterpriseId = ChainType.getHeadEnterpriseId(userVO);
            warnSetVOS = warnSetMapper.selectByEnterpriseId(headEnterpriseId);

        }else {
            warnSetVOS = warnSetMapper.selectByEnterpriseId(userVO.getEnterpriseId());

        }

        UserAdministration userAdministration = userAdministrationMapper.selectByUserId(userVO.getUserId());

        Map<Integer, List<WarnSet>> map = new HashMap<>();

        for(WarnSet warnSetVO : warnSetVOS){
            if(checkNotQuery(userAdministration,warnSetVO)){
                List<WarnSet> qualificationIds = map.get(warnSetVO.getSetType());
                if(CollectionUtils.isEmpty(qualificationIds)){
                    qualificationIds = new ArrayList<>();
                    qualificationIds.add(warnSetVO);
                    map.put(warnSetVO.getSetType(),qualificationIds);
                }else {
                    qualificationIds.add(warnSetVO);
                    map.put(warnSetVO.getSetType(),qualificationIds);
                }
            }
        }


        return map;
    }


    private boolean checkNotQuery(UserAdministration userAdministration,WarnSet warnSetVO){

        String roleIds = userAdministration.getRoleIds();
        List<Long> roles = StringSplit.strSplit(roleIds);
        String warnSetVORoleIds = warnSetVO.getRoleIds();

        if(warnSetVO.getStatus().equals(EnableStatus.ENABLE.getStatus()) && StringUtils.isEmpty(warnSetVORoleIds)){
            return true;
        }

        List<Long> warnSetVORoles = StringSplit.strSplit(warnSetVORoleIds);

        if(warnSetVO.getStatus().equals(EnableStatus.ENABLE.getStatus()) && checkRole(roles,warnSetVORoles)){
            /**
             * 预警设置开启,并且角色符合
             */
            return true;
        }


        return false;

    }


    private boolean checkRole(List<Long> roles,List<Long> warnSetVORoles){

        boolean flag = false;

        if(CollectionUtils.isEmpty(warnSetVORoles)){
            return true;
        }

        for(Long role : roles){
            for(Long warnSetVORole : warnSetVORoles){
                if(role.equals(warnSetVORole)){
                    return true;
                }
            }
        }

        return flag;
    }


}
