package com.rograndec.feijiayun.chain.business.online.purchase.centralized.service.Impl;

import com.alibaba.fastjson.JSON;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.dao.ActivityGoodsMapper;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.dao.ActivityMapper;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.entity.Activity;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.entity.ActivityGoods;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.service.AchieveActivityEntrepriseService;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.RedisComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class AchieveActivityEntrepriseServiceImpl implements AchieveActivityEntrepriseService {

    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    ActivityGoodsMapper activityGoodsMapper;
    @Autowired
    RedisComponent redisComponent;


    @Override
    public List<GetActivityEntrepriseVO> getActivityEntreprise(UserVO userVO) {

        List<GetActivityEntrepriseVO> getActivityEntrepriseVO = activityMapper.selectByEnterpriseId(userVO.getEnterpriseId());
        return getActivityEntrepriseVO;
    }

    @Override
    public SelectActivityEntrepriseVO selectActivityEntreprise(int pageNo, int pageSize, Page page, Long enterpriseId, Long id, String searchValues, String gcName1, String gcName2, String manufacturer, Long gcName1Type, Long gcName2Type, Long manufacturerType) {

        Activity activity = activityMapper.selectByPrimaryKey(Long.valueOf(id));

        SelectActivityEntrepriseVO selectActivityEntrepriseVO = new SelectActivityEntrepriseVO();
        selectActivityEntrepriseVO.setId(id);

        Long totalRecord = activityGoodsMapper.queryParams(page.getStart(), pageSize, id, searchValues, gcName1, gcName2, manufacturer);
        List<SelectActivityGoodsVO> selectActivityGoodsVOS = activityGoodsMapper.selectByActivityId(page.getStart(), pageSize, id, searchValues, gcName1, gcName2, manufacturer);

        SelectActivitySupplierVO selectActivitySupplierVO = new SelectActivitySupplierVO();
        selectActivitySupplierVO.setMphSupplierId(activity.getMphSupplierId());
        selectActivitySupplierVO.setMphSupplierName(activity.getMphSupplierName());
        selectActivitySupplierVO.setSelectActivityGoodsVO(selectActivityGoodsVOS);
        selectActivityEntrepriseVO.setSelectActivitySupplierVO(selectActivitySupplierVO);

        //遍历分类信息
        List<String> gcName1S = new ArrayList<>();
        List<String> gcName2S = new ArrayList<>();
        List<String> manufacturerS = new ArrayList<>();

        if (!selectActivityGoodsVOS.isEmpty()) {

            for (int i = 0; i < selectActivityGoodsVOS.size(); i++) {
            	
                SelectActivityGoodsVO selectActivityGoodsVO = selectActivityGoodsVOS.get(i);
                
                if(StringUtils.isNotBlank(selectActivityGoodsVO.getPictureAddress())){
                	selectActivityGoodsVO.setPictureAddress("//download.mypharma.com/"+selectActivityGoodsVO.getPictureAddress());
                }else{
                	selectActivityGoodsVO.setPictureAddress("https://www.mypharma.com/image/default.jpg");
                }
                	
                
                if (selectActivityGoodsVO.getRetailPrice() == null) {
                    selectActivityGoodsVO.setProposalRetailPrice("-");
                } else {
                    selectActivityGoodsVO.setProposalRetailPrice(String.valueOf(selectActivityGoodsVO.getRetailPrice()));
                }
                if (selectActivityGoodsVO.getGcName1() != null) {
                    if (i != 0) {
                        if (checkRepeat(gcName1S, selectActivityGoodsVO.getGcName1())) {
                            gcName1S.add(selectActivityGoodsVO.getGcName1());
                        }
                    } else {
                        gcName1S.add(selectActivityGoodsVO.getGcName1());
                    }
                }
                if (selectActivityGoodsVO.getGcName2() != null) {
                    if (i != 0) {
                        if (checkRepeat(gcName2S, selectActivityGoodsVO.getGcName2())) {
                            gcName2S.add(selectActivityGoodsVO.getGcName2());
                        }
                    } else {
                        gcName2S.add(selectActivityGoodsVO.getGcName2());
                    }
                }
                if (selectActivityGoodsVO.getManufacturer() != null) {
                    if (i != 0) {
                        if (checkRepeat(manufacturerS, selectActivityGoodsVO.getManufacturer())) {
                            manufacturerS.add(selectActivityGoodsVO.getManufacturer());
                        }
                    } else {
                        manufacturerS.add(selectActivityGoodsVO.getManufacturer());
                    }
                }
            }

            //对不展开的数据进行截取
            if (gcName1Type == 0) {
                substringGcName1Data(selectActivityEntrepriseVO, gcName1S);
            }

            if (gcName2Type == 0) {
                substringGcName2Data(selectActivityEntrepriseVO, gcName2S);
            }

            if (manufacturerType == 0) {
                substringManufacturerData(selectActivityEntrepriseVO, manufacturerS);
            }
            page.setTotalRecord(totalRecord == null ? 0 : totalRecord.intValue());
        }
        selectActivityEntrepriseVO.setGcName1S(gcName1S);
        selectActivityEntrepriseVO.setGcName2S(gcName2S);
        selectActivityEntrepriseVO.setManufacturer(manufacturerS);
        return selectActivityEntrepriseVO;
    }

    @Override
    public CentralizedCartVO setShoppingCart(String key, Integer activityId, String goodsId, Integer qty, UserVO loginUser) throws Exception {

        /*if (checkCanSplit(activityId, goodsId, qty)) {
            throw new BusinessException("存在拆零商品,拆零数量不符合规定,无法添加到购物车");
        }*/
//        redisComponent.remove(key);
        Activity activity = activityMapper.selectByPrimaryKey(Long.valueOf(activityId));
        
        CentralizedCartVO centralizedCartVOS = (CentralizedCartVO) redisComponent.get(key);
        CentralizedCartVO centralizedCartVO = new CentralizedCartVO();

        ActivityGoods activityGoods = activityGoodsMapper.selectByActivityIdAndGoodsId(activityId, goodsId);
        
        if (activityGoods.getCanSplit() == 0) {
        	qty = setQtyByCanSplit(qty, activityGoods.getMiddlePackage());
        }
        
        if (activityGoods.getInventoryQuantity() < qty) {
            throw new BusinessException("超出该货品的库存数量,无法添加到购物车");
        }
        if (activityGoods.getRestrictedQuantity() != null && activityGoods.getRestrictedQuantity() < qty) {
            throw new BusinessException("超出该货品的限购数量,无法添加到购物车");
        }

        if (centralizedCartVOS == null) {
            List<CentralizedCartBusinessVO> centralizedCartBusinessVOS = new ArrayList<>();
            CentralizedCartBusinessVO centralizedCartBusinessVO = new CentralizedCartBusinessVO();
            List<CentralizedCartGoodVO> centralizedCartGoodVOS = new ArrayList<>();
            CentralizedCartGoodVO centralizedCartGoodVO = new CentralizedCartGoodVO();

            centralizedCartVO.setSelectQuantity(qty);

            centralizedCartBusinessVO.setMphSupplierId(activity.getMphSupplierId());
            centralizedCartBusinessVO.setMphSupplierName(activity.getMphSupplierName());
            centralizedCartBusinessVO.setMatchingAmount(activity.getMatchingAmount());
            centralizedCartBusinessVO.setChecked(true);

            BigDecimal totalMoney = activityGoods.getPolicyPrice().multiply(BigDecimal.valueOf(qty));
            centralizedCartVO.setTotalAmount(totalMoney);
            centralizedCartBusinessVO.setBillingAmount(totalMoney);

            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(activityGoods, centralizedCartGoodVO);
            centralizedCartGoodVO.setgName(activityGoods.getGoodsName());
            centralizedCartGoodVO.setgId(activityGoods.getGoodsId());
            centralizedCartGoodVO.setgSpecification(activityGoods.getSpecification());
            centralizedCartGoodVO.setgManufacturer(activityGoods.getManufacturer());
            centralizedCartGoodVO.setUnitName(activityGoods.getUnitName());
            centralizedCartGoodVO.setPrice(activityGoods.getRetailPrice());
            centralizedCartGoodVO.setPicturePath(activityGoods.getPictureAddress());
            centralizedCartGoodVO.setRegistrationCode(activityGoods.getApprovalNumber());
            centralizedCartGoodVO.setEntrepriseId(loginUser.getEnterpriseId());
            centralizedCartGoodVO.setQuantity(qty);
            centralizedCartGoodVO.setSubtotal(totalMoney);
            centralizedCartGoodVO.setRestrictions(activityGoods.getRestrictedQuantity());
            centralizedCartGoodVO.setRetailPrice(activityGoods.getPolicyPrice());
            centralizedCartGoodVO.setGcost(activityGoods.getCostPrice());
            centralizedCartGoodVO.setAddCartTime(new Date());
            centralizedCartGoodVO.setChecked(true);
            centralizedCartGoodVO.setgCanSplit(activityGoods.getCanSplit());
            centralizedCartGoodVO.setgMiddlePackage(activityGoods.getMiddlePackage());
            centralizedCartGoodVO.setCreaterId(loginUser.getUserId());
            centralizedCartGoodVO.setCreaterName(loginUser.getUserName());
            centralizedCartGoodVOS.add(centralizedCartGoodVO);
            centralizedCartBusinessVO.setGoodsList(centralizedCartGoodVOS);
            centralizedCartBusinessVOS.add(centralizedCartBusinessVO);
            centralizedCartVO.setSupplierList(centralizedCartBusinessVOS);

            redisComponent.set(key, centralizedCartVO);
            return centralizedCartVO;
        } else {//购物车有信息
            List<CentralizedCartBusinessVO> centralizedCartBusinessNewS = new ArrayList<>();
            CentralizedCartBusinessVO centralizedCartBusinessNew = new CentralizedCartBusinessVO();
            List<CentralizedCartGoodVO> centralizedCartGoodNewS = new ArrayList<>();
            CentralizedCartGoodVO centralizedCartGoodNew = new CentralizedCartGoodVO();

            List<CentralizedCartBusinessVO> centralizedCartBusinessOldS = centralizedCartVOS.getSupplierList();
            BigDecimal totalMoney = BigDecimal.ZERO;

            //
            String mphSupplierIdS = "";
            for (int i = 0; i < centralizedCartBusinessOldS.size(); i++) {
                mphSupplierIdS += centralizedCartBusinessOldS.get(i).getMphSupplierId() + ",";
            }
            if (checkRepeatSupply(mphSupplierIdS, activity.getMphSupplierId())) {//判断供应商ID是否存在历史信息中
                for (CentralizedCartBusinessVO centralizedCartBusinessOld : centralizedCartBusinessOldS) {
                    if (centralizedCartBusinessOld.getMphSupplierId().equals(activity.getMphSupplierId())) {//供应商相同
                        totalMoney = activityGoods.getPolicyPrice().multiply(BigDecimal.valueOf(qty));
                        centralizedCartBusinessOld.setBillingAmount(centralizedCartBusinessOld.getBillingAmount().add(totalMoney));
                        centralizedCartBusinessOld.setChecked(true);

                        List<CentralizedCartGoodVO> centralizedCartGoodOldS = centralizedCartBusinessOld.getGoodsList();
                        for (CentralizedCartGoodVO centralizedCartGoodOld : centralizedCartGoodOldS) {
                            if (centralizedCartGoodOld.getgId().equals(goodsId)) {//货品ID相同
                                totalMoney = activityGoods.getPolicyPrice().multiply(BigDecimal.valueOf(qty));
                                centralizedCartGoodOld.setQuantity(centralizedCartGoodOld.getQuantity() + qty);
                                centralizedCartGoodOld.setSubtotal(centralizedCartGoodOld.getSubtotal().add(totalMoney));
                                centralizedCartGoodOld.setAddCartTime(new Date());
                                centralizedCartGoodOld.setChecked(true);
                            } else {//货品ID不相同
                                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(activityGoods, centralizedCartGoodNew);
                                centralizedCartGoodNew.setUnitName(activityGoods.getUnitName());
                                centralizedCartGoodNew.setPrice(activityGoods.getRetailPrice());
                                centralizedCartGoodNew.setPicturePath(activityGoods.getPictureAddress());
                                centralizedCartGoodNew.setRegistrationCode(activityGoods.getApprovalNumber());
                                centralizedCartGoodNew.setQuantity(qty);
                                centralizedCartGoodNew.setChecked(true);
                                centralizedCartGoodNew.setgName(activityGoods.getGoodsName());
                                centralizedCartGoodNew.setgId(activityGoods.getGoodsId());
                                centralizedCartGoodNew.setgSpecification(activityGoods.getSpecification());
                                centralizedCartGoodNew.setgManufacturer(activityGoods.getManufacturer());
                                centralizedCartGoodNew.setEntrepriseId(loginUser.getEnterpriseId());
                                centralizedCartGoodNew.setCreaterId(loginUser.getUserId());
                                centralizedCartGoodNew.setCreaterName(loginUser.getUserName());
                                centralizedCartGoodNew.setSubtotal(totalMoney);
                                centralizedCartGoodNew.setRestrictions(activityGoods.getRestrictedQuantity());
                                centralizedCartGoodNew.setRetailPrice(activityGoods.getPolicyPrice());
                                centralizedCartGoodNew.setGcost(activityGoods.getCostPrice());
                                centralizedCartGoodNew.setAddCartTime(new Date());
                                centralizedCartGoodNew.setgCanSplit(activityGoods.getCanSplit());
                                centralizedCartGoodNew.setgMiddlePackage(activityGoods.getMiddlePackage());
                            }
                        }
                        if (centralizedCartGoodNew.getgId() != null) {
                            centralizedCartGoodOldS.add(centralizedCartGoodNew);
                        }
                    }
                }
            } else {//不存在  直接新增
                centralizedCartBusinessNew.setMphSupplierId(activity.getMphSupplierId());
                centralizedCartBusinessNew.setMphSupplierName(activity.getMphSupplierName());
                centralizedCartBusinessNew.setMatchingAmount(activity.getMatchingAmount());
                centralizedCartBusinessNew.setChecked(true);

                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(activityGoods, centralizedCartGoodNew);
                centralizedCartGoodNew.setUnitName(activityGoods.getUnitName());
                centralizedCartGoodNew.setgName(activityGoods.getGoodsName());
                centralizedCartGoodNew.setgId(activityGoods.getGoodsId());
                centralizedCartGoodNew.setgSpecification(activityGoods.getSpecification());
                centralizedCartGoodNew.setgManufacturer(activityGoods.getManufacturer());
                centralizedCartGoodNew.setPrice(activityGoods.getRetailPrice());
                centralizedCartGoodNew.setPicturePath(activityGoods.getPictureAddress());
                centralizedCartGoodNew.setRegistrationCode(activityGoods.getApprovalNumber());
                centralizedCartGoodNew.setQuantity(qty);
                centralizedCartGoodNew.setChecked(true);
                centralizedCartGoodNew.setEntrepriseId(loginUser.getEnterpriseId());
                centralizedCartGoodNew.setCreaterId(loginUser.getUserId());
                centralizedCartGoodNew.setCreaterName(loginUser.getUserName());
                centralizedCartGoodNew.setSubtotal(totalMoney);
                centralizedCartGoodNew.setRestrictions(activityGoods.getRestrictedQuantity());
                centralizedCartGoodNew.setRetailPrice(activityGoods.getPolicyPrice());
                centralizedCartGoodNew.setGcost(activityGoods.getCostPrice());
                centralizedCartGoodNew.setAddCartTime(new Date());
                centralizedCartGoodNew.setgCanSplit(activityGoods.getCanSplit());
                centralizedCartGoodNew.setgMiddlePackage(activityGoods.getMiddlePackage());
                centralizedCartGoodNewS.add(centralizedCartGoodNew);
                centralizedCartBusinessNew.setGoodsList(centralizedCartGoodNewS);
                centralizedCartBusinessOldS.add(centralizedCartBusinessNew);
            }
            centralizedCartVOS.setSupplierList(centralizedCartBusinessOldS);
            centralizedCartVOS.setSelectQuantity(centralizedCartVOS.getSelectQuantity() + qty);
            centralizedCartVOS.setTotalAmount(centralizedCartVOS.getTotalAmount().add(totalMoney));
            redisComponent.set(key, centralizedCartVOS);

            return centralizedCartVOS;
        }
    }


    private Integer setQtyByCanSplit(Integer qty, Integer middlePackage) {
    	if(middlePackage != null && middlePackage != 0){
    		return qty/middlePackage*middlePackage;
    	}
    	return qty;
	}

	private boolean checkCanSplit(Integer activityId, String goodsId, Integer qty) {

        ActivityGoods activityGoods = activityGoodsMapper.selectByActivityIdAndGoodsId(activityId, goodsId);
        if (activityGoods.getCanSplit() != 0) {
            return false;
        } else {
            int a = qty % activityGoods.getMiddlePackage();
            if (a == 0) {
                return false;
            } else {
                return true;
            }
        }
    }


    public void test() {
        CentralizedCartVO centralizedCartVO = new CentralizedCartVO();
        redisComponent.set("key", centralizedCartVO);
        centralizedCartVO = JSON.parseObject((String) redisComponent.get("key"), CentralizedCartVO.class);


    }

    private void substringGcName1Data(SelectActivityEntrepriseVO selectActivityEntrepriseVO, List<String> datas) {

        List<String> dataList = new ArrayList<>();
        int rows = datas.size();
        if (datas.size() > 5) {
            rows = 5;
        }
        for (int i = 0; i < rows; i++) {
            dataList.add(datas.get(i));
        }
        selectActivityEntrepriseVO.setGcName1S(dataList);
    }

    private void substringGcName2Data(SelectActivityEntrepriseVO selectActivityEntrepriseVO, List<String> datas) {

        List<String> dataList = new ArrayList<>();
        int rows = datas.size();
        if (datas.size() > 5) {
            rows = 5;
        }
        for (int i = 0; i < rows; i++) {
            dataList.add(datas.get(i));
        }
        selectActivityEntrepriseVO.setGcName2S(dataList);
    }

    private void substringManufacturerData(SelectActivityEntrepriseVO selectActivityEntrepriseVO, List<String> datas) {

        List<String> dataList = new ArrayList<>();
        int rows = datas.size();
        if (datas.size() > 5) {
            rows = 5;
        }
        for (int i = 0; i < rows; i++) {
            dataList.add(datas.get(i));
        }
        selectActivityEntrepriseVO.setManufacturer(dataList);
    }

    private Boolean checkRepeat(List<String> data, String dataTwo) {

        String dataS = "";
        for (int i = 0; i < data.size(); i++) {
            dataS += data.get(i) + ",";
        }
        if (dataS.equals("") || dataS == null) {
            return true;
        }
        int num = activityGoodsMapper.checkRepeat(dataS.substring(0, dataS.length() - 1), dataTwo);
        if (num > 0) {
            return false;
        } else
            return true;
    }

    private boolean checkRepeatSupply(String mphSupplierIdS, Long mphSupplierId) {
        if ("".equals(mphSupplierIdS)){
            return false;
        }else{
            int num = activityGoodsMapper.checkRepeat(mphSupplierIdS.substring(0, mphSupplierIdS.length() - 1), String.valueOf(mphSupplierId));
            if (num > 0) {
                return true;
            } else {
                return false;
            }
        }

    }
}
