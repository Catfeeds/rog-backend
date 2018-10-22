package com.rograndec.feijiayun.chain.business.storage.maintance.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseAreaMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseArea;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseAreaVO;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsStorageMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.keytable.dao.LotNumberMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.LotNumber;
import com.rograndec.feijiayun.chain.business.keytable.entity.Stock;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.service.StopSaleService;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.StopSaleDtlRequestVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.StopSaleRequestVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.StopSaleShelfRequestVO;
import com.rograndec.feijiayun.chain.business.storage.maintance.constant.MaintanceGoodsType;
import com.rograndec.feijiayun.chain.business.storage.maintance.constant.MaintanceType;
import com.rograndec.feijiayun.chain.business.storage.maintance.dao.GoodsMaintanceDetailMapper;
import com.rograndec.feijiayun.chain.business.storage.maintance.dao.GoodsMaintanceMapper;
import com.rograndec.feijiayun.chain.business.storage.maintance.entity.GoodsMaintance;
import com.rograndec.feijiayun.chain.business.storage.maintance.entity.GoodsMaintanceDetail;
import com.rograndec.feijiayun.chain.business.storage.maintance.service.GoodsMaintanceService;
import com.rograndec.feijiayun.chain.business.storage.maintance.vo.*;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.QualitySetMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.business.system.set.vo.QualitySetVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.StopSaleFromType;
import com.rograndec.feijiayun.chain.common.constant.status.PubStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

@Service
public class GoodsMaintanceServiceImpl implements GoodsMaintanceService{
    @Autowired
    WarehouseAreaMapper warehouseAreaMapper;
    @Autowired
    QualitySetMapper qualitySetMapper;
    @Autowired
    GoodsMaintanceMapper goodsMaintanceMapper;
    @Autowired
    GoodsMaintanceDetailMapper goodsMaintanceDetailMapper;
    @Autowired
    ManageConfigMapper manageConfigMapper;
    @Autowired
    OrderCodeComponent orderCodeComponent;
    @Autowired
    UserMapper userMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    LotNumberMapper lotNumberMapper;
    @Autowired
    WarehouseShelfMapper warehouseShelfMapper;
    @Autowired
    StockMapper stockMapper;
    @Autowired
    private StopSaleService stopSaleService;
    @Autowired
    PurchaseGeneralComponent purchaseGeneralComponent;
    @Autowired
    GoodsStorageMapper goodsStorageMapper;

    @Override
    public List<WarehouseAreaVO> getWarehouseAreaList(UserVO loginUser) throws Exception {
        List<WarehouseArea> warehouseAreas = warehouseAreaMapper.getWarehouseAreaByEnterpId(loginUser.getEnterpriseId());
        List<WarehouseAreaVO> warehouseAreaVOS = new ArrayList<>();
        for(WarehouseArea warehouseArea : warehouseAreas){
            WarehouseAreaVO warehouseAreaVO = new WarehouseAreaVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(warehouseArea,warehouseAreaVO);
            warehouseAreaVOS.add(warehouseAreaVO);
        }
        return warehouseAreaVOS;
    }

    @Override
    public List<QualitySetVO> getMaintanceMeasuresList(UserVO loginUser, Integer setType, Integer type) throws Exception {
        List<QualitySetVO> qualitySetVOS = new ArrayList<>();
        switch (setType){
            case 1 : qualitySetVOS = qualitySetMapper.getQualitySetVOList(loginUser.getEnterpriseId(), setType,null,1);
                break;
            case 3 : qualitySetVOS = qualitySetMapper.getQualitySetVOList(loginUser.getEnterpriseId(), setType,null,1);
                break;
            case 6 : qualitySetVOS = qualitySetMapper.getQualitySetVOList(loginUser.getEnterpriseId(), setType,loginUser.getParentId()==0l?0:1,1);
                break;
            case 7 : qualitySetVOS = qualitySetMapper.getQualitySetVOList(loginUser.getEnterpriseId(), setType,1,1);
                     if(type == 0) {
                         qualitySetVOS.addAll(qualitySetMapper.getQualitySetVOList(loginUser.getEnterpriseId(), setType, 0, 1));
                     }
                break;
        }
        return qualitySetVOS;
    }

    @Override
    public List<SelectMaintanceGoodsVO> getGoodsStockList(UserVO loginUser, String param, Long warehouseAreaId, Integer maintanceType, Integer goodsType, Integer type) throws Exception {
        List<SelectMaintanceGoodsVO> returnList = new ArrayList<>();
        List<SelectMaintanceGoodsVO> selectMaintanceGoodsVOS = goodsMaintanceDetailMapper.selectMaintanceGoodsVO(loginUser.getEnterpriseId(),param,warehouseAreaId,type);
        if(maintanceType == null && goodsType == null)
            return selectMaintanceGoodsVOS;
        if(goodsType != null){
            switch (goodsType){//0-成药；1-中药饮片；2-拆零药品；3-近效期药品
                case 0 :
                    for(SelectMaintanceGoodsVO selectMaintanceGoodsVO : selectMaintanceGoodsVOS){
                        reCalcMaintance(selectMaintanceGoodsVO);
                        if(selectMaintanceGoodsVO.getGoodsAttribute()==0
                                && selectMaintanceGoodsVO.getGoodsNature() !=1
                                && selectMaintanceGoodsVO.getValidDays()>selectMaintanceGoodsVO.getNearEffectPeriod()
                                && selectMaintanceGoodsVO.getMaintenanceCycle() >= DateUtils.diffInDays(new Date(),selectMaintanceGoodsVO.getLastMaintainTime()))
                            returnList.add(selectMaintanceGoodsVO);
                    }
                    break;
                case 1 :
                    for(SelectMaintanceGoodsVO selectMaintanceGoodsVO : selectMaintanceGoodsVOS){
                        reCalcMaintance(selectMaintanceGoodsVO);
                        if((selectMaintanceGoodsVO.getGoodsAttribute()==1 || selectMaintanceGoodsVO.getGoodsAttribute()==2)
                                && selectMaintanceGoodsVO.getGoodsNature() !=1
                                && selectMaintanceGoodsVO.getValidDays()>selectMaintanceGoodsVO.getNearEffectPeriod()
                                && selectMaintanceGoodsVO.getMaintenanceCycle() >= DateUtils.diffInDays(new Date(),selectMaintanceGoodsVO.getLastMaintainTime()))
                            returnList.add(selectMaintanceGoodsVO);
                    }
                    break;
                case 2 :
                    for(SelectMaintanceGoodsVO selectMaintanceGoodsVO : selectMaintanceGoodsVOS){
                        reCalcMaintance(selectMaintanceGoodsVO);
                        if(selectMaintanceGoodsVO.getGoodsNature() ==1
                                && selectMaintanceGoodsVO.getValidDays()>selectMaintanceGoodsVO.getNearEffectPeriod()
                                && selectMaintanceGoodsVO.getMaintenanceCycle() >= DateUtils.diffInDays(new Date(),selectMaintanceGoodsVO.getLastMaintainTime()))
                            returnList.add(selectMaintanceGoodsVO);
                    }
                    break;
                case 3 :
                    for(SelectMaintanceGoodsVO selectMaintanceGoodsVO : selectMaintanceGoodsVOS){
                        reCalcMaintance(selectMaintanceGoodsVO);
                        if(selectMaintanceGoodsVO.getValidDays()<=selectMaintanceGoodsVO.getNearEffectPeriod())
                            returnList.add(selectMaintanceGoodsVO);
                    }
                    break;
            }
        }
        if(maintanceType != null){
            if(maintanceType == 1 && goodsType == null){//重点养护
                for(SelectMaintanceGoodsVO selectMaintanceGoodsVO : selectMaintanceGoodsVOS){
                    if((selectMaintanceGoodsVO.getGoodsAttribute()==1 || selectMaintanceGoodsVO.getGoodsAttribute()==2)
                            && selectMaintanceGoodsVO.getGoodsNature() !=1
                            && selectMaintanceGoodsVO.getValidDays()>selectMaintanceGoodsVO.getNearEffectPeriod())
                        returnList.add(selectMaintanceGoodsVO);
                    if(selectMaintanceGoodsVO.getGoodsNature() ==1
                            && selectMaintanceGoodsVO.getValidDays()>selectMaintanceGoodsVO.getNearEffectPeriod())
                        returnList.add(selectMaintanceGoodsVO);
                    if(selectMaintanceGoodsVO.getValidDays()<=selectMaintanceGoodsVO.getNearEffectPeriod())
                        returnList.add(selectMaintanceGoodsVO);
                    if(selectMaintanceGoodsVO.getMaintenanceType() == 1){
                        if(!returnList.contains(selectMaintanceGoodsVO)){
                            returnList.add(selectMaintanceGoodsVO);
                        }
                    }
                }
            }else if(maintanceType == 1 && goodsType != null){
                Iterator<SelectMaintanceGoodsVO> it = returnList.iterator();
                while (it.hasNext()){
                    if(it.next().getMaintenanceType()==0)
                        it.remove();
                }
            }else if(maintanceType == 0 && goodsType != null){//常规养护
                Iterator<SelectMaintanceGoodsVO> it = returnList.iterator();
                while (it.hasNext()){
                    if(it.next().getMaintenanceType()==1)
                        it.remove();
                }
            }else {
                for(SelectMaintanceGoodsVO selectMaintanceGoodsVO : selectMaintanceGoodsVOS){
                    if(selectMaintanceGoodsVO.getMaintenanceType() == 0){
                        returnList.add(selectMaintanceGoodsVO);
                    }
                }
            }
        }
        return returnList;
    }

    private void reCalcMaintance(SelectMaintanceGoodsVO selectMaintanceGoodsVO) {
        Long maintenanceCycle = selectMaintanceGoodsVO.getMaintenanceCycle();
        if(selectMaintanceGoodsVO.getMaintenanceCycleUnit() == 1){
            maintenanceCycle = maintenanceCycle * 30L;
        }else if(selectMaintanceGoodsVO.getMaintenanceCycleUnit() == 2){
            maintenanceCycle = maintenanceCycle * 365L;
        }
        selectMaintanceGoodsVO.setMaintenanceCycle(maintenanceCycle);
        Long nearEffectPeriod = selectMaintanceGoodsVO.getNearEffectPeriod();
        if(selectMaintanceGoodsVO.getNearEffectPeriodUnit() == 1){
            nearEffectPeriod = nearEffectPeriod * 30L;
        }
        if(selectMaintanceGoodsVO.getNearEffectPeriodUnit() == 2){
            nearEffectPeriod = nearEffectPeriod * 365L;
        }
        selectMaintanceGoodsVO.setNearEffectPeriod(nearEffectPeriod);
        selectMaintanceGoodsVO.setValidDays(DateUtils.diffInDays(new Date(),selectMaintanceGoodsVO.getValidUntil()));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int cancelMaintanceOrder(UserVO loginUser, Long id) throws Exception {
        /**
         * 修改总单状态
         */
        GoodsMaintance goodsMaintance = goodsMaintanceMapper.selectByPrimaryKey(id);
        goodsMaintance.setStatus(PubStatus.goodsMaintanceStatus.CANCELED);
        UserEnterpriseUtils.setUserCreateOrModify(goodsMaintance,loginUser,false);
        goodsMaintanceMapper.updateByPrimaryKeySelective(goodsMaintance);
        /**
         * 修改细单状态
         */
        List<GoodsMaintanceDetail> goodsMaintanceDetails = goodsMaintanceDetailMapper.selectByMaintanceId(id);
        for(GoodsMaintanceDetail goodsMaintanceDetail : goodsMaintanceDetails){
            goodsMaintanceDetail.setStatus(PubStatus.goodsMaintanceStatus.CANCELED);
            UserEnterpriseUtils.setUserCreateOrModify(goodsMaintanceDetail,loginUser,false);
            goodsMaintanceDetailMapper.updateByPrimaryKey(goodsMaintanceDetail);
        }
        return 0;
    }

    @Override
    public Page<List<ShowGoodsMaintanceVO>> getMaintanceOrderList(UserVO loginUser, Integer maintanceOrderType, String orderName, String orderType, Date startDate, Date endDate, Long warehouseAreaId, Integer maintanceType, Integer goodsType, String code, Integer pageSize, Integer pageNo) throws Exception {
        Boolean gspFlag = getGspFlag(loginUser);
        List<ShowGoodsMaintanceVO> showGoodsMaintanceVOS = new ArrayList<>();
//        if (maintanceOrderType == 0){
//            //养护计划
//            List<ShowGoodsMaintanceVO> showGoodsMaintanceVOList = goodsMaintanceMapper.getWillMaintanceOrderList(loginUser.getEnterpriseId(),orderName, orderType,startDate,endDate);
//            for (ShowGoodsMaintanceVO showGoodsMaintanceVO : showGoodsMaintanceVOList){
//                //set gsp信息
//                showGoodsMaintanceVO.setGspFlag(gspFlag);
//                //set默认的养护人员信息
//                showGoodsMaintanceVO.setMaintanceManId(loginUser.getUserId());
//                showGoodsMaintanceVO.setMaintanceManCode(loginUser.getUserCode());
//                showGoodsMaintanceVO.setMaintanceManName(loginUser.getUserName());
//                //set库区名
//                showGoodsMaintanceVO.setWarehouseAreaName(warehouseAreaMapper.selectByPrimaryKey(showGoodsMaintanceVO.getWarehouseAreaId()).getWarehouseName());
//                //set养护类型名
//                showGoodsMaintanceVO.setMaintanceTypeName(MaintanceType.getName(showGoodsMaintanceVO.getMaintanceType()));
//                //set药品类型名
//                showGoodsMaintanceVO.setGoodsTypeName(MaintanceGoodsType.getName(showGoodsMaintanceVO.getGoodsType()));
//                //如果是门店,只养护近效期,拆零,中药
//                if(loginUser.getParentId() != 0 && showGoodsMaintanceVO.getGoodsType() == 0)
//                    continue;
//                //根据查询条件筛选
//                if(warehouseAreaId != null)//库存ID
//                    if(!showGoodsMaintanceVO.getWarehouseAreaId().equals(warehouseAreaId)) continue;
//                if(maintanceType != null)//养护类型
//                    if(!showGoodsMaintanceVO.getMaintanceType().equals(maintanceType)) continue;
//                if(goodsType != null)//药品类型
//                    if(!showGoodsMaintanceVO.getGoodsType().equals(goodsType)) continue;
//                showGoodsMaintanceVOS.add(showGoodsMaintanceVO);
//            }
//        }else {
//            List<GoodsMaintance> goodsMaintances = goodsMaintanceMapper.getMaintanceOrderList(loginUser.getEnterpriseId(),maintanceOrderType,orderName, orderType,startDate,endDate,warehouseAreaId,maintanceType,goodsType,code);
//            for(GoodsMaintance goodsMaintance : goodsMaintances){
//                ShowGoodsMaintanceVO showGoodsMaintanceVO = new ShowGoodsMaintanceVO();
//                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsMaintance,showGoodsMaintanceVO);
//                //set gsp信息
//                showGoodsMaintanceVO.setGspFlag(gspFlag);
//                //set总单id
//                showGoodsMaintanceVO.setIds(goodsMaintance.getId().toString());
//                //set默认的养护人员信息
//                showGoodsMaintanceVO.setMaintanceManId(loginUser.getUserId());
//                showGoodsMaintanceVO.setMaintanceManCode(loginUser.getUserCode());
//                showGoodsMaintanceVO.setMaintanceManName(loginUser.getUserName());
//                //set养护类型名
//                showGoodsMaintanceVO.setMaintanceTypeName(MaintanceType.getName(showGoodsMaintanceVO.getMaintanceType()));
//                //set药品类型名
//                showGoodsMaintanceVO.setGoodsTypeName(MaintanceGoodsType.getName(showGoodsMaintanceVO.getGoodsType()));
//                showGoodsMaintanceVOS.add(showGoodsMaintanceVO);
//            }
//        }
        if(maintanceOrderType==1) maintanceOrderType = PubStatus.goodsMaintanceStatus.IN_MAINTANCE;
        if(maintanceOrderType==2) maintanceOrderType = PubStatus.goodsMaintanceStatus.FINISHED;
        if(maintanceOrderType==3) maintanceOrderType = PubStatus.goodsMaintanceStatus.CANCELED;
        if(orderName != null){
            if(orderName.equals("maintanceDate")) orderName = "maintance_date";
            else if(orderName.equals("warehouseAreaId")) orderName = "warehouse_area_id";
            else if(orderName.equals("maintanceType")) orderName = "maintance_type";
            else if(orderName.equals("goodsType")) orderName = "goods_type";
            else orderName = null;
        }
        List<GoodsMaintance> goodsMaintances = goodsMaintanceMapper.getMaintanceOrderList(loginUser.getEnterpriseId(),maintanceOrderType,orderName, orderType,startDate,endDate,warehouseAreaId,maintanceType,goodsType,code,pageSize,(pageNo-1)*pageSize);
        for(GoodsMaintance goodsMaintance : goodsMaintances){
            ShowGoodsMaintanceVO showGoodsMaintanceVO = new ShowGoodsMaintanceVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsMaintance,showGoodsMaintanceVO);
            //set gsp信息
            showGoodsMaintanceVO.setGspFlag(gspFlag);
            //set总单id
            showGoodsMaintanceVO.setIds(goodsMaintance.getId().toString());
            //set默认的养护人员信息
            showGoodsMaintanceVO.setMaintanceManId(loginUser.getUserId());
            showGoodsMaintanceVO.setMaintanceManCode(loginUser.getUserCode());
            showGoodsMaintanceVO.setMaintanceManName(loginUser.getUserName());
            //set养护类型名
            showGoodsMaintanceVO.setMaintanceTypeName(MaintanceType.getName(showGoodsMaintanceVO.getMaintanceType()));
            //set药品类型名
            showGoodsMaintanceVO.setGoodsTypeName(MaintanceGoodsType.getName(showGoodsMaintanceVO.getGoodsType()));
            showGoodsMaintanceVOS.add(showGoodsMaintanceVO);
        }
        Page<List<ShowGoodsMaintanceVO>> page = new Page(pageNo, pageSize);
        page.setResult(showGoodsMaintanceVOS);
        goodsMaintances = goodsMaintanceMapper.getMaintanceOrderList(loginUser.getEnterpriseId(),maintanceOrderType,orderName, orderType,startDate,endDate,warehouseAreaId,maintanceType,goodsType,code,null,null);
        page.setTotalRecord(goodsMaintances.size());
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<List<ShowMaintanceVO>> getWillMaintanceOrderList(UserVO loginUser, String orderName, String orderType, Date startDate, Date endDate, Long warehouseAreaId, Integer maintanceType, Integer goodsType, Integer pageNo, Integer pageSize) throws Exception{
    List<ShowMaintanceVO> showMaintanceVOS = new ArrayList<>();
//        List<SelectMaintanceGoodsVO> selectMaintanceGoodsVOS = goodsMaintanceDetailMapper.selectMaintanceGoodsVO(loginUser.getEnterpriseId(),null,null,0);
        List<SelectMaintanceGoodsVO> selectMaintanceGoodsVOS = getGoodsStockList(loginUser,null,warehouseAreaId,maintanceType,goodsType,0);
//        List<SelectMaintanceGoodsVO> goodsVOS = new ArrayList<>();//保存细单集合
        for(SelectMaintanceGoodsVO selectMaintanceGoodsVO : selectMaintanceGoodsVOS){
            List<SelectMaintanceGoodsVO> goodsVOS = new ArrayList<>();//保存细单集合
            ShowMaintanceVO showMaintanceVO = new ShowMaintanceVO();
            //set默认的养护人员信息
            showMaintanceVO.setMaintanceManId(loginUser.getUserId());
            showMaintanceVO.setMaintanceManCode(loginUser.getUserCode());
            showMaintanceVO.setMaintanceManName(loginUser.getUserName());
            //set库区名称
            showMaintanceVO.setWarehouseAreaId(selectMaintanceGoodsVO.getWarehouseAreaId());
            showMaintanceVO.setWarehouseAreaName(warehouseAreaMapper.selectByPrimaryKey(selectMaintanceGoodsVO.getWarehouseAreaId()).getName());

            //近效期商品
            if(goodsType == 3){
                if(maintanceType != null && maintanceType == 0) break;//按照养护类型过滤,如果是普通养护,则直接跳出循环
                if(warehouseAreaId != null){//按照库区过滤
                    if(!warehouseAreaId.equals(selectMaintanceGoodsVO.getWarehouseAreaId()))
                        continue;
                }

                if(selectMaintanceGoodsVO.getNearEffectPeriod()>=selectMaintanceGoodsVO.getValidDays()){
                    //set 药品类型（0-成药；1-中药饮片；2-拆零药品；3-近效期药品）
                    showMaintanceVO.setGoodsType(MaintanceGoodsType.NearMedicine.getCode());
                    showMaintanceVO.setGoodsTypeName(MaintanceGoodsType.NearMedicine.getName());
                    //set 养护类型（0-常规养护；1-重点养护）
                    showMaintanceVO.setMaintanceType(MaintanceType.ImportantMaintanceType.getCode());
                    showMaintanceVO.setMaintanceTypeName(MaintanceType.ImportantMaintanceType.getName());
                    //如果设置的是重点养护,则取养护设置的周期,若不是,则默认养护周期为30天
                    if(selectMaintanceGoodsVO.getMaintenanceType() == 0)
                        selectMaintanceGoodsVO.setMaintenanceCycle(30l);
                    //set 计划养护日期=上次养护日期+养护周期
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(selectMaintanceGoodsVO.getLastMaintainTime());
                    calendar.add(Calendar.DATE,selectMaintanceGoodsVO.getMaintenanceCycle().intValue());
                    if(startDate != null && endDate != null){//如果按照日期查询,则进行过滤
                        if(calendar.getTime().before(startDate) || calendar.getTime().after(endDate)){
                            continue;
                        }
                    }
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    showMaintanceVO.setMaintanceDate(calendar.getTime());

                    //如果总单存在,则将细单插入总单的细单列表
                    for(ShowMaintanceVO show : showMaintanceVOS){
                        if(show.equals(showMaintanceVO)){
                            show.getSelectMaintanceGoodsVOList().add(selectMaintanceGoodsVO);
                            continue;
                        }
                    }
                    //若不存在 则新增一条记录
//                    goodsVOS.clear();//先清空在插入
                    goodsVOS.add(selectMaintanceGoodsVO);
                    showMaintanceVO.setSelectMaintanceGoodsVOList(goodsVOS);
                    showMaintanceVOS.add(showMaintanceVO);
                    continue;
                }
            }else if (goodsType == 2){
                if(warehouseAreaId != null){//按照库区过滤
                    if(!warehouseAreaId.equals(selectMaintanceGoodsVO.getWarehouseAreaId()))
                        continue;
                }
                if(maintanceType != null && maintanceType == 0) break;//按照养护类型过滤,如果是普通养护,则直接跳出循环
                //拆零商品
                if(selectMaintanceGoodsVO.getGoodsNature()==1){
                    //set 药品类型（0-成药；1-中药饮片；2-拆零药品；3-近效期药品）
                    showMaintanceVO.setGoodsType(MaintanceGoodsType.DismountedDrugs.getCode());
                    showMaintanceVO.setGoodsTypeName(MaintanceGoodsType.DismountedDrugs.getName());
                    //set 养护类型（0-重点养护；1-常规养护）
                    showMaintanceVO.setMaintanceType(MaintanceType.ImportantMaintanceType.getCode());
                    showMaintanceVO.setMaintanceTypeName(MaintanceType.ImportantMaintanceType.getName());
                    //如果设置的是重点养护,则取养护设置的周期,若不是,则默认养护周期为30天
                    if(selectMaintanceGoodsVO.getMaintenanceType() == 0)
                        selectMaintanceGoodsVO.setMaintenanceCycle(30l);
                    //set 计划养护日期=上次养护日期+养护周期
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(selectMaintanceGoodsVO.getLastMaintainTime());
                    calendar.add(Calendar.DATE,selectMaintanceGoodsVO.getMaintenanceCycle().intValue());
                    if(startDate != null && endDate != null){//如果按照日期查询,则进行过滤
                        if(calendar.getTime().before(startDate) || calendar.getTime().after(endDate)){
                            continue;
                        }
                    }
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    showMaintanceVO.setMaintanceDate(calendar.getTime());
                    //如果总单存在,则将细单插入总单的细单列表
                    for(ShowMaintanceVO show : showMaintanceVOS){
                        if(show.equals(showMaintanceVO)){
                            show.getSelectMaintanceGoodsVOList().add(selectMaintanceGoodsVO);
                            continue;
                        }
                    }
                    //若不存在 则新增一条记录
//                    goodsVOS.clear();//先清空在插入
                    goodsVOS.add(selectMaintanceGoodsVO);
                    showMaintanceVO.setSelectMaintanceGoodsVOList(goodsVOS);
                    showMaintanceVOS.add(showMaintanceVO);
                    continue;
                }
            }else if(goodsType == 1){
                if(warehouseAreaId != null){//按照库区过滤
                    if(!warehouseAreaId.equals(selectMaintanceGoodsVO.getWarehouseAreaId()))
                        continue;
                }
                if(maintanceType != null && maintanceType == 0) break;//按照养护类型过滤,如果是普通养护,则直接跳出循环
                //中药
                if(selectMaintanceGoodsVO.getGoodsAttribute()!=0){
                    //set 药品类型（0-成药；1-中药饮片；2-拆零药品；3-近效期药品）
                    showMaintanceVO.setGoodsType(MaintanceGoodsType.ChineseMedicine.getCode());
                    showMaintanceVO.setGoodsTypeName(MaintanceGoodsType.ChineseMedicine.getName());
                    //set 养护类型（0-重点养护；1-常规养护）
                    showMaintanceVO.setMaintanceType(MaintanceType.ImportantMaintanceType.getCode());
                    showMaintanceVO.setMaintanceTypeName(MaintanceType.ImportantMaintanceType.getName());
                    //如果设置的是重点养护,则取养护设置的周期,若不是,则默认养护周期为30天
                    if(selectMaintanceGoodsVO.getMaintenanceType() == 0)
                        selectMaintanceGoodsVO.setMaintenanceCycle(30l);
                    //set 计划养护日期=上次养护日期+养护周期
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(selectMaintanceGoodsVO.getLastMaintainTime());
                    calendar.add(Calendar.DATE,selectMaintanceGoodsVO.getMaintenanceCycle().intValue());
                    if(startDate != null && endDate != null){//如果按照日期查询,则进行过滤
                        if(calendar.getTime().before(startDate) || calendar.getTime().after(endDate)){
                            continue;
                        }
                    }
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    showMaintanceVO.setMaintanceDate(calendar.getTime());
                    //如果总单存在,则将细单插入总单的细单列表
                    for(ShowMaintanceVO show : showMaintanceVOS){
                        if(show.equals(showMaintanceVO)){
                            show.getSelectMaintanceGoodsVOList().add(selectMaintanceGoodsVO);
                            continue;
                        }
                    }
                    //若不存在 则新增一条记录
//                    goodsVOS.clear();//先清空在插入
                    goodsVOS.add(selectMaintanceGoodsVO);
                    showMaintanceVO.setSelectMaintanceGoodsVOList(goodsVOS);
                    showMaintanceVOS.add(showMaintanceVO);
                    continue;
                }
            }else if(goodsType == 0){
                if(warehouseAreaId != null){//按照库区过滤
                    if(!warehouseAreaId.equals(selectMaintanceGoodsVO.getWarehouseAreaId()))
                        continue;
                }
                if(maintanceType != null) {//按照养护类型过滤
                    if(!maintanceType.equals(selectMaintanceGoodsVO.getMaintenanceType())) continue;
                }

                //成药
                if(selectMaintanceGoodsVO.getGoodsAttribute()==0){
                    //如果是门店直接跳出循环
                    if(loginUser.getParentId()!=0l){
                        continue;
                    }
                    //set 药品类型（0-成药；1-中药饮片；2-拆零药品；3-近效期药品）
                    showMaintanceVO.setGoodsType(MaintanceGoodsType.PatentMedicine.getCode());
                    showMaintanceVO.setGoodsTypeName(MaintanceGoodsType.PatentMedicine.getName());
                    //set 养护类型（0-重点养护；1-常规养护）
                    showMaintanceVO.setMaintanceType(selectMaintanceGoodsVO.getMaintenanceType());
                    showMaintanceVO.setMaintanceTypeName(MaintanceType.getName(selectMaintanceGoodsVO.getMaintenanceType()));
                    //set 计划养护日期=上次养护日期+养护周期
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(selectMaintanceGoodsVO.getLastMaintainTime());
                    calendar.add(Calendar.DATE,selectMaintanceGoodsVO.getMaintenanceCycle().intValue());
                    if(startDate != null && endDate != null){//如果按照日期查询,则进行过滤
                        if(calendar.getTime().before(startDate) || calendar.getTime().after(endDate)){
                            continue;
                        }
                    }
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    showMaintanceVO.setMaintanceDate(calendar.getTime());
                    //如果总单存在,则将细单插入总单的细单列表
                    for(ShowMaintanceVO show : showMaintanceVOS){
                        if(show.equals(showMaintanceVO)){
                            show.getSelectMaintanceGoodsVOList().add(selectMaintanceGoodsVO);
                            continue;
                        }
                    }
                    //若不存在 则新增一条记录
//                    goodsVOS.clear();//先清空在插入
                    goodsVOS.add(selectMaintanceGoodsVO);
                    showMaintanceVO.setSelectMaintanceGoodsVOList(goodsVOS);
                    showMaintanceVOS.add(showMaintanceVO);
                    continue;

                }
            }
        }
        //排序
        if(orderName != null && orderType != null){
            Collections.sort(showMaintanceVOS, new Comparator<ShowMaintanceVO>() {
                @Override
                public int compare(ShowMaintanceVO o1, ShowMaintanceVO o2) {
                    int i = 0;
                    if(orderName.equals("warehouseAreaId")){
                        if(orderType.equalsIgnoreCase("asc"))
                            return Long.compare(o1.getWarehouseAreaId(),o2.getWarehouseAreaId());
                        else
                            return Long.compare(o2.getWarehouseAreaId(),o1.getWarehouseAreaId());

                    }
                    if(orderName.equals("maintanceType")){
                        if(orderType.equalsIgnoreCase("asc"))
                            return Integer.compare(o1.getMaintanceType(),o2.getMaintanceType());
                        else
                            return Integer.compare(o2.getMaintanceType(),o1.getMaintanceType());

                    }
                    if(orderName.equals("goodsType")){
                        if(orderType.equalsIgnoreCase("asc"))
                            return Integer.compare(o1.getGoodsType(),o2.getGoodsType());
                        else
                            return Integer.compare(o2.getGoodsType(),o1.getGoodsType());

                    }
                    if(orderName.equals("maintanceDate")){
                        if(orderType.equalsIgnoreCase("asc"))
                            return Long.compare(o1.getMaintanceDate().getTime(),o2.getMaintanceDate().getTime());
                        else
                            return Long.compare(o2.getMaintanceDate().getTime(),o1.getMaintanceDate().getTime());
                    }
                    return i;
                }
            });
        }
        //分页
        Page<List<ShowMaintanceVO>> page = new Page<>(pageNo,pageSize);
        page.setTotalRecord(showMaintanceVOS.size());
        int start = (pageNo-1)*pageSize;
        int end = pageNo*pageSize;
        if(showMaintanceVOS.size()<start || showMaintanceVOS.size()==0){
            page.setResult(new ArrayList<>());
        }else if(showMaintanceVOS.size()>start && showMaintanceVOS.size()<end){
            page.setResult(showMaintanceVOS.subList(start,showMaintanceVOS.size()));
        }else
            page.setResult(showMaintanceVOS.subList(start,end));
        return page;
    }

    @Override
    public void exportExcel(OutputStream output, UserVO loginUser, Long id) throws Exception {
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("goodsCode","商品编码");
        map.put("goodsGenericName","通用名称");
        map.put("dosageName","剂型");
        map.put("goodsSpecification","规格");
        map.put("manufacturer","生产厂商");
        map.put("unitName","单位");
        map.put("productDate","生产日期");
        map.put("validDate","有效期至");
        map.put("shelfName","货位");
        map.put("quantity","数量");
        map.put("shelfStatusDesc","质量状况");
        map.put("measures","检查措施");
        map.put("qualifiedQuantity","合格数量");
        map.put("conclusion","检查结论");
        map.put("unqualifiedQuantity","不合格数量");
        map.put("reason","问题原因");
        map.put("handleMeasures","处理措施");
        List<String> titleSecond = new ArrayList<>();
        //标题栏下第一行
        GoodsMaintance goodsMaintance = goodsMaintanceMapper.selectByPrimaryKey(id);
        titleSecond.add("养护单号:"+goodsMaintance.getCode()+" 养护日期:"+goodsMaintance.getMaintanceDate()+" 养护人员:"+goodsMaintance.getModifierName()
                +" 库区:"+goodsMaintance.getWarehouseAreaName()+" 检查类型:"+MaintanceType.getName(goodsMaintance.getMaintanceType())
                +" 药品类型:"+MaintanceGoodsType.getName(goodsMaintance.getGoodsType())+" 备注:"+goodsMaintance.getRemark());
        StringBuilder end = new StringBuilder();
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("药品养护");
        List<String> needTotalName = new ArrayList<>();
        List<GoodsMaintanceDetailVO> goodsMaintanceDetailVOS = new ArrayList<>();
        List<GoodsMaintanceDetail> goodsMaintanceDetails = goodsMaintanceDetailMapper.selectByMaintanceId(id);
        for(GoodsMaintanceDetail goodsMaintanceDetail : goodsMaintanceDetails){
            GoodsMaintanceDetailVO goodsMaintanceDetailVO = new GoodsMaintanceDetailVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsMaintanceDetail,goodsMaintanceDetailVO);
            //set 养护措施
            //:养护措施:7 养护结论:6 处置措施:3 问题原因:1
            List<QualitySetVO> qualitySetVOS = getMaintanceMeasuresList(loginUser,7,0);
            StringBuilder reasonName = new StringBuilder();
            if(goodsMaintanceDetailVO.getMeasuresIds() != null){
                if(goodsMaintanceDetailVO.getMeasuresIds().contains(",")){
                    for(String rid : goodsMaintanceDetailVO.getMeasuresIds().split(",")){
                        for(QualitySetVO qualitySetVO : qualitySetVOS){
                            if(rid.trim().equals(qualitySetVO.getId()+"")){
                                reasonName.append(qualitySetVO.getDescription());
                                reasonName.append(";");
                            }
                        }
                    }
                }else {
                    for(QualitySetVO qualitySetVO : qualitySetVOS){
                        if(goodsMaintanceDetailVO.getMeasuresIds().trim().equals(qualitySetVO.getId()+"")){
                            reasonName.append(qualitySetVO.getDescription());
                            reasonName.append(";");
                        }
                    }
                }
            }
            goodsMaintanceDetailVO.setMeasures(reasonName.toString());
            //set养护结论
            qualitySetVOS.clear();
            qualitySetVOS = getMaintanceMeasuresList(loginUser,6,0);
            reasonName.delete(0,reasonName.length());
            if(goodsMaintanceDetailVO.getConclusionId() != null){
                for(QualitySetVO qualitySetVO : qualitySetVOS){
                    if(goodsMaintanceDetailVO.getConclusionId().equals(qualitySetVO.getId())){
                        reasonName.append(qualitySetVO.getDescription());
                    }
                }
            }
            goodsMaintanceDetailVO.setConclusion(reasonName.toString());
            //set问题原因
            qualitySetVOS.clear();
            qualitySetVOS = getMaintanceMeasuresList(loginUser,1,0);
            reasonName.delete(0,reasonName.length());
            if(goodsMaintanceDetailVO.getReasonIds() != null){
                if(goodsMaintanceDetailVO.getReasonIds().contains(",")){
                    for(String rid : goodsMaintanceDetailVO.getReasonIds().split(",")){
                        for(QualitySetVO qualitySetVO : qualitySetVOS){
                            if(rid.trim().equals(qualitySetVO.getId()+"")){
                                reasonName.append(qualitySetVO.getDescription());
                                reasonName.append(";");
                            }
                        }
                    }
                }else {
                    for(QualitySetVO qualitySetVO : qualitySetVOS){
                        if(goodsMaintanceDetailVO.getReasonIds().trim().equals(qualitySetVO.getId()+"")){
                            reasonName.append(qualitySetVO.getDescription());
                            reasonName.append(";");
                        }
                    }
                }
            }
            goodsMaintanceDetailVO.setReason(reasonName.toString());
            //set处理措施
            qualitySetVOS.clear();
            qualitySetVOS = getMaintanceMeasuresList(loginUser,3,0);
            reasonName.delete(0,reasonName.length());
            if(goodsMaintanceDetailVO.getHandleMeasuresIds() != null){
                if(goodsMaintanceDetailVO.getHandleMeasuresIds().contains(",")){
                    for(String rid : goodsMaintanceDetailVO.getHandleMeasuresIds().split(",")){
                        for(QualitySetVO qualitySetVO : qualitySetVOS){
                            if(rid.trim().equals(qualitySetVO.getId()+"")){
                                reasonName.append(qualitySetVO.getDescription());
                                reasonName.append(";");
                            }
                        }
                    }
                }else {
                    for(QualitySetVO qualitySetVO : qualitySetVOS){
                        if(goodsMaintanceDetailVO.getHandleMeasuresIds().trim().equals(qualitySetVO.getId()+"")){
                            reasonName.append(qualitySetVO.getDescription());
                            reasonName.append(";");
                        }
                    }
                }
            }
            goodsMaintanceDetailVO.setHandleMeasures(reasonName.toString());
            goodsMaintanceDetailVOS.add(goodsMaintanceDetailVO);
        }
        purchaseGeneralComponent.commExcelExport(output,map,goodsMaintanceDetailVOS,name,titleSecond,end.toString(),true,needTotalName);

    }

    @Override
    public List<GoodsMaintanceDetailVO> getMaintanceOrderDtlList(UserVO loginUser, String ids) throws Exception {
        List<GoodsMaintanceDetailVO> goodsMaintanceDetailVOS = new ArrayList<>();
//        if(maintanceOrderType==0){
//            List<SelectMaintanceGoodsVO> selectMaintanceGoodsVOS = getGoodsStockList(loginUser,null,warehouseAreaId,maintanceType,goodsType,0);
//            for(String id : ids.split(",")){
//                for(SelectMaintanceGoodsVO selectMaintanceGoodsVO : selectMaintanceGoodsVOS){
//                    if(Long.valueOf(id).equals(selectMaintanceGoodsVO.getGoodsId())){
//                        GoodsMaintanceDetailVO goodsMaintanceDetailVO = new GoodsMaintanceDetailVO();
//                        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(selectMaintanceGoodsVO,goodsMaintanceDetailVO);
//                        goodsMaintanceDetailVOS.add(goodsMaintanceDetailVO);
//                    }
//
//                }
//            }
//        }else {
//            List<GoodsMaintanceDetail> goodsMaintanceDetails = goodsMaintanceDetailMapper.selectByMaintanceId(Long.valueOf(ids));
//            for (GoodsMaintanceDetail goodsMaintanceDetail : goodsMaintanceDetails){
//                GoodsMaintanceDetailVO goodsMaintanceDetailVO = new GoodsMaintanceDetailVO();
//                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsMaintanceDetailVO,goodsMaintanceDetail);
//                goodsMaintanceDetailVOS.add(goodsMaintanceDetailVO);
//            }
//        }
        List<GoodsMaintanceDetail> goodsMaintanceDetails = goodsMaintanceDetailMapper.selectByMaintanceId(Long.valueOf(ids));
        for (GoodsMaintanceDetail goodsMaintanceDetail : goodsMaintanceDetails){
            GoodsMaintanceDetailVO goodsMaintanceDetailVO = new GoodsMaintanceDetailVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsMaintanceDetail,goodsMaintanceDetailVO);
            //set 养护措施
            //:养护措施:7 养护结论:6 处置措施:3 问题原因:1
            List<QualitySetVO> qualitySetVOS = getMaintanceMeasuresList(loginUser,7,0);
            StringBuilder reasonName = new StringBuilder();
            if(goodsMaintanceDetailVO.getMeasuresIds() != null){
                if(goodsMaintanceDetailVO.getMeasuresIds().contains(",")){
                    for(String id : goodsMaintanceDetailVO.getMeasuresIds().split(",")){
                        for(QualitySetVO qualitySetVO : qualitySetVOS){
                            if(id.trim().equals(qualitySetVO.getId()+"")){
                                reasonName.append(qualitySetVO.getDescription());
                                reasonName.append(";");
                            }
                        }
                    }
                }else {
                    for(QualitySetVO qualitySetVO : qualitySetVOS){
                        if(goodsMaintanceDetailVO.getMeasuresIds().trim().equals(qualitySetVO.getId())){
                            reasonName.append(qualitySetVO.getDescription());
                            reasonName.append(";");
                        }
                    }
                }
            }
            goodsMaintanceDetailVO.setMeasures(reasonName.toString());
            //set养护结论
            qualitySetVOS.clear();
            qualitySetVOS = getMaintanceMeasuresList(loginUser,6,0);
            reasonName.delete(0,reasonName.length());
            if(goodsMaintanceDetailVO.getConclusionId() != null){
                for(QualitySetVO qualitySetVO : qualitySetVOS){
                    if(goodsMaintanceDetailVO.getId() == qualitySetVO.getId()){
                        reasonName.append(qualitySetVO.getDescription());
                    }
                }
            }
            goodsMaintanceDetailVO.setConclusion(reasonName.toString());
            //set问题原因
            qualitySetVOS.clear();
            qualitySetVOS = getMaintanceMeasuresList(loginUser,1,0);
            reasonName.delete(0,reasonName.length());
            if(goodsMaintanceDetailVO.getReasonIds() != null){
                if(goodsMaintanceDetailVO.getReasonIds().contains(",")){
                    for(String id : goodsMaintanceDetailVO.getReasonIds().split(",")){
                        for(QualitySetVO qualitySetVO : qualitySetVOS){
                            if(id.trim().equals(qualitySetVO.getId()+"")){
                                reasonName.append(qualitySetVO.getDescription());
                                reasonName.append(";");
                            }
                        }
                    }
                }else {
                    for(QualitySetVO qualitySetVO : qualitySetVOS){
                        if(goodsMaintanceDetailVO.getReasonIds().trim().equals(qualitySetVO.getId())){
                            reasonName.append(qualitySetVO.getDescription());
                            reasonName.append(";");
                        }
                    }
                }
            }
            goodsMaintanceDetailVO.setReason(reasonName.toString());
            //set处理措施
            qualitySetVOS.clear();
            qualitySetVOS = getMaintanceMeasuresList(loginUser,3,0);
            reasonName.delete(0,reasonName.length());
            if(goodsMaintanceDetailVO.getHandleMeasuresIds() != null){
                if(goodsMaintanceDetailVO.getHandleMeasuresIds().contains(",")){
                    for(String id : goodsMaintanceDetailVO.getHandleMeasuresIds().split(",")){
                        for(QualitySetVO qualitySetVO : qualitySetVOS){
                            if(id.trim().equals(qualitySetVO.getId()+"")){
                                reasonName.append(qualitySetVO.getDescription());
                                reasonName.append(";");
                            }
                        }
                    }
                }else {
                    for(QualitySetVO qualitySetVO : qualitySetVOS){
                        if(goodsMaintanceDetailVO.getHandleMeasuresIds().trim().equals(qualitySetVO.getId())){
                            reasonName.append(qualitySetVO.getDescription());
                            reasonName.append(";");
                        }
                    }
                }
            }
            goodsMaintanceDetailVO.setHandleMeasures(reasonName.toString());

            goodsMaintanceDetailVOS.add(goodsMaintanceDetailVO);
        }
        return goodsMaintanceDetailVOS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveMaintanceOrder(UserVO loginUser, GoodsMaintacneInfoVO goodsMaintacneInfoVO) throws Exception {
        GoodsMaintance goodsMaintance = new GoodsMaintance();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsMaintacneInfoVO.getGoodsMaintanceVO(),goodsMaintance);
        //setCode
        goodsMaintance.setCode(orderCodeComponent.generate(OrderRule.MAINTANCE.getCodePrefix(),loginUser.getEnterpriseId(),loginUser.getEnterpriseCode()));
        //set orderType
        goodsMaintance.setOrderType(OrderRule.MAINTANCE.getType());
        //set 企业信息
        goodsMaintance.setEnterpriseId(loginUser.getEnterpriseId());
        goodsMaintance.setParentId(loginUser.getParentId());
        //set养护人员信息
        User user = userMapper.selectByPrimaryKey(goodsMaintance.getMaintanceManId());
        goodsMaintance.setMaintanceManName(user.getName());
        goodsMaintance.setMaintanceManCode(user.getCode());
        //set 库区名称
        goodsMaintance.setWarehouseAreaName(warehouseAreaMapper.selectByPrimaryKey(goodsMaintance.getWarehouseAreaId()).getName());
        //set 状态
        goodsMaintance.setStatus(PubStatus.goodsMaintanceStatus.IN_MAINTANCE);
        //备注
        goodsMaintance.setRemark(goodsMaintacneInfoVO.getGoodsMaintanceVO().getRemark());
        UserEnterpriseUtils.setUserCreateOrModify(goodsMaintance,loginUser,true);
        //
        goodsMaintanceMapper.insertSelective(goodsMaintance);
        //保存细单
        List<GoodsMaintanceDetail> goodsMaintanceDetails = new ArrayList<>();
        for(GoodsMaintanceDetailVO goodsMaintanceDetailVO : goodsMaintacneInfoVO.getGoodsMaintanceDetailVOS()){
            GoodsMaintanceDetail goodsMaintanceDetail = new GoodsMaintanceDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsMaintanceDetailVO,goodsMaintanceDetail);
            //根据 goodsId 查询商品信息然后set
            Goods goods = goodsMapper.selectByPrimaryKey(goodsMaintanceDetail.getGoodsId());
            goodsMaintanceDetail.setGoodsCode(goods.getCode());
            goodsMaintanceDetail.setBarcode(goods.getBarcode());
            goodsMaintanceDetail.setGoodsName(goods.getName());
            goodsMaintanceDetail.setGoodsGenericName(goods.getGenericName());
            goodsMaintanceDetail.setDosageId(goods.getDosageId());
            goodsMaintanceDetail.setDosageName(goods.getDosageName());
            goodsMaintanceDetail.setUnitId(goods.getUnitId());
            goodsMaintanceDetail.setUnitName(goods.getUnitName());
            goodsMaintanceDetail.setGoodsSpecification(goods.getSpecification());
            goodsMaintanceDetail.setManufacturerId(goods.getManufacturerId());
            goodsMaintanceDetail.setManufacturer(goods.getManufacturer());
            goodsMaintanceDetail.setGoodsPlace(goods.getPlace());
            goodsMaintanceDetail.setApprovalNumber(goods.getApprovalNumber());
            //根据批号id 查询批号的信息,并set
            LotNumber lotNumber = lotNumberMapper.selectByPrimaryKey(goodsMaintanceDetail.getLotId());
            goodsMaintanceDetail.setLotNumber(lotNumber.getLotNum());
            goodsMaintanceDetail.setProductDate(lotNumber.getProductDate());
            goodsMaintanceDetail.setValidDate(lotNumber.getValidUntil());
            //根据货位id查询货位信息 并set
            WarehouseShelf warehouseShelf = warehouseShelfMapper.selectByPrimaryKey(goodsMaintanceDetail.getShelfId());
            goodsMaintanceDetail.setShelfName(warehouseShelf.getName());
            //set 编码 养护日期 id 养护类型 状态
            goodsMaintanceDetail.setMaintanceCode(goodsMaintance.getCode());
            goodsMaintanceDetail.setMaintanceDate(goodsMaintance.getMaintanceDate());
            goodsMaintanceDetail.setMaintanceId(goodsMaintance.getId());
            goodsMaintanceDetail.setOrderType(goodsMaintance.getOrderType());
            goodsMaintanceDetail.setStatus(goodsMaintance.getStatus());
            goodsMaintanceDetail.setShelfStatusDesc(goodsMaintanceDetailVO.getShelfStatusDesc());
            //set 企业信息
            goodsMaintanceDetail.setEnterpriseId(loginUser.getEnterpriseId());
            goodsMaintanceDetail.setParentId(loginUser.getParentId());
            UserEnterpriseUtils.setUserCreateOrModify(goodsMaintanceDetail,loginUser,true);
            //set 默认默认养护措施(取系统设置-养护措施)
//            if(goodsMaintanceDetail.getMeasuresIds() == null || "".equals(goodsMaintanceDetail.getMeasuresIds())){
//                GoodsStorage goodsStorageMaintenance = goodsStorageMapper.getByGoodsId(goodsMaintanceDetail.getId());
//                goodsMaintanceDetail.setMeasuresIds(goodsStorageMaintenance.getMaintenanceMeasureIds());
//            }
            //set 默认合格数量为库存可用数量
            if(goodsMaintanceDetail.getQualifiedQuantity() == null)
                goodsMaintanceDetail.setQualifiedQuantity(goodsMaintanceDetail.getQuantity());
            //set 默认不合格数量为0
            if(goodsMaintanceDetail.getUnqualifiedQuantity() == null)
                goodsMaintanceDetail.setUnqualifiedQuantity(BigDecimal.ZERO);
            //set 默认养护结论为系统养护结论id最大的
            if(goodsMaintanceDetail.getConclusionId() == null){
                List<QualitySetVO> qualitySetVOS = getMaintanceMeasuresList(loginUser,6,0);
                goodsMaintanceDetail.setConclusionId(qualitySetVOS.isEmpty()?null:qualitySetVOS.get(0).getId());
            }
            goodsMaintanceDetails.add(goodsMaintanceDetail);
        }
        goodsMaintanceDetailMapper.batchInsert(goodsMaintanceDetails);
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int finishMaintanceOrder(UserVO loginUser, List<GoodsMaintanceDetailVO> goodsMaintanceDetailVOS) throws Exception {
        GoodsMaintance goodsMaintance = goodsMaintanceMapper.selectByPrimaryKey(goodsMaintanceDetailVOS.get(0).getMaintanceId());
        goodsMaintance.setStatus(PubStatus.goodsMaintanceStatus.FINISHED);
        goodsMaintanceMapper.updateByPrimaryKeySelective(goodsMaintance);
        //先根据总单查出数据库里的细单,以防前台删了数据,所以这里需要将数据库里的记录跟前台传过来的对比,前台没有而数据库里有的记录需要删掉
        List<GoodsMaintanceDetail> goodsMaintanceDetails = goodsMaintanceDetailMapper.selectByMaintanceId(goodsMaintanceDetailVOS.get(0).getMaintanceId());
        //前台返回的细单id集合,因为前台有可能会删掉细单,所以这里要记录下id,进行比对.没有的后台也要删掉
        List<Long> ids = new ArrayList<>();
        for(GoodsMaintanceDetailVO goodsMaintanceDetailVO : goodsMaintanceDetailVOS){
            ids.add(goodsMaintanceDetailVO.getId());
        }
        for(GoodsMaintanceDetail goodsMaintanceDetail : goodsMaintanceDetails){
            if(!ids.contains(goodsMaintanceDetail.getId())){
                goodsMaintanceDetailMapper.deleteByPrimaryKey(goodsMaintanceDetail.getId());
            }
        }
        //如果不合格数量不是0,那么生成停售通知跟药品锁定单据
        List<StopSaleDtlRequestVO> stopSaleDtlRequestVOS = new ArrayList<>();
        int lineNum = 0;//停售行号

        for(GoodsMaintanceDetailVO goodsMaintanceDetailVO : goodsMaintanceDetailVOS){
            LotNumber lotNumber = lotNumberMapper.selectByPrimaryKey(goodsMaintanceDetailVO.getLotId());
            if(lotNumber == null) throw new BusinessException(goodsMaintanceDetailVO.getLotNumber()+":该批号信息无效");
            goodsMaintanceDetailVO.setProductDate(lotNumber.getProductDate());
            goodsMaintanceDetailVO.setValidDate(lotNumber.getValidUntil());
            GoodsMaintanceDetail goodsMaintanceDetail = new GoodsMaintanceDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsMaintanceDetailVO,goodsMaintanceDetail);
            goodsMaintanceDetail.setStatus(PubStatus.goodsMaintanceStatus.FINISHED);
            if(goodsMaintanceDetail.getUnqualifiedQuantity().compareTo(BigDecimal.ZERO) != 0
                    && (StringUtils.isBlank(goodsMaintanceDetail.getReasonIds())
                    || StringUtils.isBlank(goodsMaintanceDetail.getHandleMeasuresIds()))){
                throw new BusinessException("不合格数量不为0,问题原因跟处理措施必填!");
            }
            goodsMaintanceDetail.setOrderType(goodsMaintance.getOrderType());
            goodsMaintanceDetail.setMaintanceCode(goodsMaintance.getCode());
            goodsMaintanceDetail.setMaintanceDate(goodsMaintance.getMaintanceDate());
            //set 默认养护结论为系统养护结论id最大的
            if(goodsMaintanceDetail.getConclusionId() == null){
                List<QualitySetVO> qualitySetVOS = getMaintanceMeasuresList(loginUser,6,0);
                goodsMaintanceDetail.setConclusionId(qualitySetVOS.isEmpty()?null:qualitySetVOS.get(0).getId());
            }
            //如果id为null或者为0,则说明是前端新增的单据,插入
            if(goodsMaintanceDetailVO.getId() == null || goodsMaintanceDetailVO.getId() == 0){
                UserEnterpriseUtils.setUserCreateOrModify(goodsMaintanceDetail,loginUser,true);
                goodsMaintanceDetail.setEnterpriseId(loginUser.getEnterpriseId());
                goodsMaintanceDetail.setParentId(loginUser.getParentId());
                goodsMaintanceDetailMapper.insertSelective(goodsMaintanceDetail);
            }else {
                UserEnterpriseUtils.setUserCreateOrModify(goodsMaintanceDetail,loginUser,false);
                goodsMaintanceDetailMapper.updateByPrimaryKeySelective(goodsMaintanceDetail);
            }
            //修改库存表的上次养护时间
            Stock stock = new Stock();
            stock.setGoodsId(goodsMaintanceDetail.getGoodsId());
            stock.setLotId(goodsMaintanceDetail.getLotId());
            stock.setShelfId(goodsMaintanceDetail.getShelfId());
            stock.setEnterpriseId(goodsMaintanceDetail.getEnterpriseId());
            stock.setParentId(goodsMaintanceDetail.getParentId());
            stock.setLastMaintainTime(new Date());
            stockMapper.updateByPrimaryKeySelective(stock);
            if(goodsMaintanceDetail.getUnqualifiedQuantity().compareTo(BigDecimal.ZERO) > 0){
                lineNum++;
                StopSaleDtlRequestVO stopSaleDtlRequestVO = new StopSaleDtlRequestVO();
                stopSaleDtlRequestVO.setGoodsId(goodsMaintanceDetail.getGoodsId());
                //获取不合格原因
                String stopReason = getStopReason(loginUser,goodsMaintanceDetail.getReasonIds()).toString();
                stopSaleDtlRequestVO.setStopReason(stopReason);
                stopSaleDtlRequestVO.setLineNum(lineNum);
                List<StopSaleShelfRequestVO> stopSaleShelfRequestVOS = new ArrayList<>();
                StopSaleShelfRequestVO stopSaleShelfRequestVO = new StopSaleShelfRequestVO();
                stopSaleShelfRequestVO.setLineNum(lineNum);
                stopSaleShelfRequestVO.setLotId(goodsMaintanceDetail.getLotId());
                stopSaleShelfRequestVO.setShelfId(goodsMaintanceDetail.getShelfId());
                stopSaleShelfRequestVO.setQuantity(goodsMaintanceDetail.getUnqualifiedQuantity());

                stopSaleShelfRequestVOS.add(stopSaleShelfRequestVO);
                stopSaleDtlRequestVO.setStopSaleShelfRequestVOList(stopSaleShelfRequestVOS);
                stopSaleDtlRequestVOS.add(stopSaleDtlRequestVO);
            }
        }
        if(stopSaleDtlRequestVOS.size()>0){
            StopSaleRequestVO stopSaleRequestVO = new StopSaleRequestVO();
            //set通知日期
            stopSaleRequestVO.setStopDate(new Date());
            //set通知人员
            stopSaleRequestVO.setStopManId(loginUser.getUserId());
            //set通知来源（0-主管单位通知；1-陈列检查；2-药品养护；3-商品锁定）
            stopSaleRequestVO.setStopFrom(StopSaleFromType.MAINTANCE.getCode());
            //set基础单据编码/id/类型/日期
            stopSaleRequestVO.setBaseOrderCode(goodsMaintance.getCode());
            stopSaleRequestVO.setBaseOrderDate(goodsMaintance.getMaintanceDate());
            stopSaleRequestVO.setBaseOrderId(goodsMaintance.getId());
            stopSaleRequestVO.setBaseOrderType(goodsMaintance.getOrderType());
            stopSaleRequestVO.setStopSaleDtlRequestVOList(stopSaleDtlRequestVOS);
            //生成生成停售通知跟药品锁定单据
            stopSaleService.saveStopSale(loginUser, stopSaleRequestVO);
        }
        return 0;
    }

    private List<String> getStopReason(UserVO loginUser,String reasonIds)throws Exception {
        List<QualitySetVO> qualitySetVOS = qualitySetMapper.getQualitySetVOList(loginUser.getEnterpriseId(), 1,null,1);
        List<String> resultList = new ArrayList<>();
        for(String reasonId : reasonIds.split(",")){
            for(QualitySetVO qualitySetVO : qualitySetVOS){
                if(qualitySetVO.getId().toString().equals(reasonId)){
                    resultList.add(qualitySetVO.getDescription());
                }
            }
        }
        return resultList;
    }

    /**
     * 是否质量控制
     * @param loginUser
     * @return
     * @throws Exception
     */
    private Boolean getGspFlag(UserVO loginUser) throws Exception {
        ManageConfig manageConfig = manageConfigMapper.selectByCurrentUser(loginUser);
        return manageConfig.getBusinessControl()==0?false:true;
    }

	@Override
	public List<WarehouseAreaVO> getWarehouseAreaListByEnterpriseId(
			Long enterpriseId) {
		List<WarehouseArea> warehouseAreas = warehouseAreaMapper.getWarehouseAreaByEnterpId(enterpriseId);
        List<WarehouseAreaVO> warehouseAreaVOS = new ArrayList<>();
        for(WarehouseArea warehouseArea : warehouseAreas){
            WarehouseAreaVO warehouseAreaVO = new WarehouseAreaVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(warehouseArea,warehouseAreaVO);
            warehouseAreaVOS.add(warehouseAreaVO);
        }
        return warehouseAreaVOS;
	}

}
