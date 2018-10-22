package com.rograndec.feijiayun.chain.business.storage.displaycheck.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseAreaMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsStorageMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.keytable.dao.LotNumberMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.LotNumber;
import com.rograndec.feijiayun.chain.business.keytable.entity.Stock;
import com.rograndec.feijiayun.chain.business.storage.displaycheck.dao.GoodsDisplayCheckDetailMapper;
import com.rograndec.feijiayun.chain.business.storage.displaycheck.dao.GoodsDisplayCheckMapper;
import com.rograndec.feijiayun.chain.business.storage.displaycheck.entity.GoodsDisplayCheck;
import com.rograndec.feijiayun.chain.business.storage.displaycheck.entity.GoodsDisplayCheckDetail;
import com.rograndec.feijiayun.chain.business.storage.displaycheck.service.GoodsDispalyCheckService;
import com.rograndec.feijiayun.chain.business.storage.displaycheck.vo.GoodsDisplayCheckDetailVO;
import com.rograndec.feijiayun.chain.business.storage.displaycheck.vo.GoodsDisplayCheckInfoVO;
import com.rograndec.feijiayun.chain.business.storage.displaycheck.vo.ShowDisplayCheckVO;
import com.rograndec.feijiayun.chain.business.storage.displaycheck.vo.ShowGoodsDisplayCheckVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.service.StopSaleService;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.StopSaleDtlRequestVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.StopSaleRequestVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.StopSaleShelfRequestVO;
import com.rograndec.feijiayun.chain.business.storage.maintance.constant.MaintanceGoodsType;
import com.rograndec.feijiayun.chain.business.storage.maintance.constant.MaintanceType;
import com.rograndec.feijiayun.chain.business.storage.maintance.dao.GoodsMaintanceDetailMapper;
import com.rograndec.feijiayun.chain.business.storage.maintance.dao.GoodsMaintanceMapper;
import com.rograndec.feijiayun.chain.business.storage.maintance.service.GoodsMaintanceService;
import com.rograndec.feijiayun.chain.business.storage.maintance.vo.SelectMaintanceGoodsVO;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.QualitySetMapper;
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
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

@Service
public class GoodsDispalyCheckServiceImpl implements GoodsDispalyCheckService{
    @Autowired
    GoodsDisplayCheckMapper displayCheckMapper;
    @Autowired
    GoodsDisplayCheckDetailMapper displayCheckDetailMapper;
    @Autowired
    OrderCodeComponent orderCodeComponent;
    @Autowired
    UserMapper userMapper;
    @Autowired
    WarehouseAreaMapper warehouseAreaMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    LotNumberMapper lotNumberMapper;
    @Autowired
    WarehouseShelfMapper warehouseShelfMapper;
    @Autowired
    QualitySetMapper qualitySetMapper;
    @Autowired
    StopSaleService stopSaleService;
    @Autowired
    ManageConfigMapper manageConfigMapper;
    @Autowired
    GoodsMaintanceMapper goodsMaintanceMapper;
    @Autowired
    GoodsMaintanceService goodsMaintanceService;
    @Autowired
    StockMapper stockMapper;
    @Autowired
    GoodsMaintanceDetailMapper goodsMaintanceDetailMapper;
    @Autowired
    PurchaseGeneralComponent purchaseGeneralComponent;
    @Autowired
    GoodsStorageMapper goodsStorageMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int cancelDisplayCheckOrder(UserVO loginUser, Long id) throws Exception {
        GoodsDisplayCheck goodsDisplayCheck = new GoodsDisplayCheck();
        goodsDisplayCheck.setId(id);
        UserEnterpriseUtils.setUserCreateOrModify(goodsDisplayCheck,loginUser,false);
        goodsDisplayCheck.setStatus(PubStatus.goodsDisplayCheckStatus.CANCELED);
        List<GoodsDisplayCheckDetail> goodsDisplayCheckDetails = displayCheckDetailMapper.selectByCheckId(id);
        for(GoodsDisplayCheckDetail goodsDisplayCheckDetail : goodsDisplayCheckDetails){
            goodsDisplayCheckDetail.setId(goodsDisplayCheck.getId());
            goodsDisplayCheckDetail.setStatus(PubStatus.goodsDisplayCheckStatus.CANCELED);
            UserEnterpriseUtils.setUserCreateOrModify(goodsDisplayCheckDetail,loginUser,false);
            displayCheckDetailMapper.updateByPrimaryKey(goodsDisplayCheckDetail);
        }
        return displayCheckMapper.updateByPrimaryKeySelective(goodsDisplayCheck);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveDisplayCheckOrder(UserVO loginUser, GoodsDisplayCheckInfoVO goodsDisplayCheckInfoVO) throws Exception {
        GoodsDisplayCheck goodsDisplayCheck = new GoodsDisplayCheck();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsDisplayCheckInfoVO.getGoodsDisplayCheckVO(),goodsDisplayCheck);
        //setCode
        goodsDisplayCheck.setCode(orderCodeComponent.generate(OrderRule.DISPLAY.getCodePrefix(),loginUser.getEnterpriseId(),loginUser.getEnterpriseCode()));
        //set orderType
        goodsDisplayCheck.setOrderType(OrderRule.DISPLAY.getType());
        //set 企业信息
        goodsDisplayCheck.setEnterpriseId(loginUser.getEnterpriseId());
        goodsDisplayCheck.setParentId(loginUser.getParentId());
        //set养护人员信息
        User user = userMapper.selectByPrimaryKey(goodsDisplayCheck.getCheckerId());
        goodsDisplayCheck.setCheckerName(user.getName());
        goodsDisplayCheck.setCheckerCode(user.getCode());
        //set 库区名称
        goodsDisplayCheck.setWarehouseAreaName(warehouseAreaMapper.selectByPrimaryKey(goodsDisplayCheck.getWarehouseAreaId()).getName());
        //set 状态
        goodsDisplayCheck.setStatus(PubStatus.goodsDisplayCheckStatus.IN_CHECK);
        goodsDisplayCheck.setRemark(goodsDisplayCheckInfoVO.getGoodsDisplayCheckVO().getRemark());
        UserEnterpriseUtils.setUserCreateOrModify(goodsDisplayCheck,loginUser,true);
        displayCheckMapper.insertSelective(goodsDisplayCheck);
        //保存细单
        List<GoodsDisplayCheckDetail> goodsDisplayCheckDetails = new ArrayList<>();
        for(GoodsDisplayCheckDetailVO goodsDisplayCheckDetailVO : goodsDisplayCheckInfoVO.getDisplayCheckDetailVOList()){
            GoodsDisplayCheckDetail goodsDisplayCheckDetail = new GoodsDisplayCheckDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsDisplayCheckDetailVO,goodsDisplayCheckDetail);
            //根据 goodsId 查询商品信息然后set
            Goods goods = goodsMapper.selectByPrimaryKey(goodsDisplayCheckDetail.getGoodsId());
            goodsDisplayCheckDetail.setGoodsCode(goods.getCode());
            goodsDisplayCheckDetail.setBarcode(goods.getBarcode());
            goodsDisplayCheckDetail.setGoodsName(goods.getName());
            goodsDisplayCheckDetail.setGoodsGenericName(goods.getGenericName());
            goodsDisplayCheckDetail.setDosageId(goods.getDosageId());
            goodsDisplayCheckDetail.setDosageName(goods.getDosageName());
            goodsDisplayCheckDetail.setUnitId(goods.getUnitId());
            goodsDisplayCheckDetail.setUnitName(goods.getUnitName());
            goodsDisplayCheckDetail.setGoodsSpecification(goods.getSpecification());
            goodsDisplayCheckDetail.setManufacturerId(goods.getManufacturerId());
            goodsDisplayCheckDetail.setManufacturer(goods.getManufacturer());
            goodsDisplayCheckDetail.setGoodsPlace(goods.getPlace());
            goodsDisplayCheckDetail.setApprovalNumber(goods.getApprovalNumber());
            //根据批号id 查询批号的信息,并set
            LotNumber lotNumber = lotNumberMapper.selectByPrimaryKey(goodsDisplayCheckDetail.getLotId());
            goodsDisplayCheckDetail.setLotNumber(lotNumber.getLotNum());
            goodsDisplayCheckDetail.setProductDate(lotNumber.getProductDate());
            goodsDisplayCheckDetail.setValidDate(lotNumber.getValidUntil());
            //根据货位id查询货位信息 并set
            WarehouseShelf warehouseShelf = warehouseShelfMapper.selectByPrimaryKey(goodsDisplayCheckDetail.getShelfId());
            goodsDisplayCheckDetail.setShelfName(warehouseShelf.getName());
            //set 编码 养护日期 id 养护类型 状态
            goodsDisplayCheckDetail.setCheckCode(goodsDisplayCheck.getCode());
            goodsDisplayCheckDetail.setCheckDate(goodsDisplayCheck.getCheckDate());
            goodsDisplayCheckDetail.setCheckId(goodsDisplayCheck.getId());
            goodsDisplayCheckDetail.setOrderType(goodsDisplayCheck.getOrderType());
            goodsDisplayCheckDetail.setStatus(goodsDisplayCheck.getStatus());
            goodsDisplayCheckDetail.setShelfStatusDesc(goodsDisplayCheckDetailVO.getShelfStatusDesc());
            //set 企业信息
            goodsDisplayCheckDetail.setEnterpriseId(loginUser.getEnterpriseId());
            goodsDisplayCheckDetail.setParentId(loginUser.getParentId());
            UserEnterpriseUtils.setUserCreateOrModify(goodsDisplayCheckDetail,loginUser,true);
            //set 默认默认养护措施(取系统设置-养护措施)
//            if(goodsDisplayCheckDetail.getMeasuresIds() == null || "".equals(goodsDisplayCheckDetail.getMeasuresIds())){
//                GoodsStorage goodsStorageMaintenance = goodsStorageMapper.getByGoodsId(goodsDisplayCheck.getId());
//                goodsDisplayCheckDetail.setMeasuresIds(goodsStorageMaintenance.getMaintenanceMeasureIds());
//            }
            //set 默认合格数量为库存可用数量
            if(goodsDisplayCheckDetail.getQualifiedQuantity() == null)
                goodsDisplayCheckDetail.setQualifiedQuantity(goodsDisplayCheckDetail.getQuantity());
            //set 默认不合格数量为0
            if(goodsDisplayCheckDetail.getUnqualifiedQuantity() == null)
                goodsDisplayCheckDetail.setUnqualifiedQuantity(BigDecimal.ZERO);
            //set 默认养护结论为系统养护结论id最大的
            if(goodsDisplayCheckDetail.getConclusionId() == null){
                List<QualitySetVO> qualitySetVOS = goodsMaintanceService.getMaintanceMeasuresList(loginUser,6,1);
                goodsDisplayCheckDetail.setConclusionId(qualitySetVOS.isEmpty()?null:qualitySetVOS.get(0).getId());
            }
            goodsDisplayCheckDetails.add(goodsDisplayCheckDetail);
        }
        displayCheckDetailMapper.batchInsert(goodsDisplayCheckDetails);
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int finishCheckOrder(UserVO loginUser, List<GoodsDisplayCheckDetailVO> goodsDisplayCheckDetailVOS) throws Exception {
        GoodsDisplayCheck goodsDisplayCheck = displayCheckMapper.selectByPrimaryKey(goodsDisplayCheckDetailVOS.get(0).getCheckId());
        goodsDisplayCheck.setStatus(PubStatus.goodsDisplayCheckStatus.FINISHED);
        displayCheckMapper.updateByPrimaryKeySelective(goodsDisplayCheck);
        //先根据总单查出数据库里的细单,以防前台删了数据,所以这里需要将数据库里的记录跟前台传过来的对比,前台没有而数据库里有的记录需要删掉
        List<GoodsDisplayCheckDetail> goodsDisplayCheckDetails = displayCheckDetailMapper.selectByCheckId(goodsDisplayCheckDetailVOS.get(0).getId());
        //前台返回的细单id集合,因为前台有可能会删掉细单,所以这里要记录下id,进行比对.没有的后台也要删掉
        List<Long> ids = new ArrayList<>();
        for(GoodsDisplayCheckDetailVO displayCheckDetailVO : goodsDisplayCheckDetailVOS){
            ids.add(displayCheckDetailVO.getId());
        }
        for(GoodsDisplayCheckDetail goodsDisplayCheckDetail : goodsDisplayCheckDetails){
            if(!ids.contains(goodsDisplayCheckDetail.getId())){
                displayCheckDetailMapper.deleteByPrimaryKey(goodsDisplayCheckDetail.getId());
            }
        }
        //如果不合格数量不是0,那么生成停售通知跟药品锁定单据
        List<StopSaleDtlRequestVO> stopSaleDtlRequestVOS = new ArrayList<>();
        int lineNum = 0;//停售行号
        for (GoodsDisplayCheckDetailVO goodsDisplayCheckDetailVO : goodsDisplayCheckDetailVOS){
            LotNumber lotNumber = lotNumberMapper.selectByPrimaryKey(goodsDisplayCheckDetailVO.getLotId());
            if(lotNumber == null) throw new BusinessException(goodsDisplayCheckDetailVO.getLotNumber()+":该批号信息无效");
            goodsDisplayCheckDetailVO.setProductDate(lotNumber.getProductDate());
            goodsDisplayCheckDetailVO.setValidDate(lotNumber.getValidUntil());
            GoodsDisplayCheckDetail goodsDisplayCheckDetail = new GoodsDisplayCheckDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsDisplayCheckDetailVO,goodsDisplayCheckDetail);
            goodsDisplayCheckDetail.setStatus(PubStatus.goodsDisplayCheckStatus.FINISHED);
            if(goodsDisplayCheckDetail.getUnqualifiedQuantity().compareTo(BigDecimal.ZERO) != 0
                    && (StringUtils.isBlank(goodsDisplayCheckDetail.getReasonIds())
                    || StringUtils.isBlank(goodsDisplayCheckDetail.getHandleMeasuresIds()))){
                throw new BusinessException("不合格数量不为0,问题原因跟处理措施必填!");
            }
            goodsDisplayCheckDetail.setOrderType(goodsDisplayCheck.getOrderType());
            goodsDisplayCheckDetail.setCheckCode(goodsDisplayCheck.getCode());
            goodsDisplayCheckDetail.setCheckDate(goodsDisplayCheck.getCheckDate());
            //set 默认养护结论为系统养护结论id最大的
            if(goodsDisplayCheckDetail.getConclusionId() == null){
                List<QualitySetVO> qualitySetVOS = goodsMaintanceService.getMaintanceMeasuresList(loginUser,6,1);
                goodsDisplayCheckDetail.setConclusionId(qualitySetVOS.isEmpty()?null:qualitySetVOS.get(0).getId());
            }
            //如果id为null或者为0,则说明是前端新增的单据,插入
            if(goodsDisplayCheckDetailVO.getId() == null || goodsDisplayCheckDetailVO.getId() == 0){
                UserEnterpriseUtils.setUserCreateOrModify(goodsDisplayCheckDetail,loginUser,true);
                goodsDisplayCheckDetail.setEnterpriseId(loginUser.getEnterpriseId());
                goodsDisplayCheckDetail.setParentId(loginUser.getParentId());
                displayCheckDetailMapper.insertSelective(goodsDisplayCheckDetail);
            }else {
                UserEnterpriseUtils.setUserCreateOrModify(goodsDisplayCheckDetail,loginUser,false);
                displayCheckDetailMapper.updateByPrimaryKeySelective(goodsDisplayCheckDetail);
            }
            //修改库存表的上次养护时间
            Stock stock = new Stock();
            stock.setGoodsId(goodsDisplayCheckDetail.getGoodsId());
            stock.setLotId(goodsDisplayCheckDetail.getLotId());
            stock.setShelfId(goodsDisplayCheckDetail.getShelfId());
            stock.setEnterpriseId(goodsDisplayCheckDetail.getEnterpriseId());
            stock.setParentId(goodsDisplayCheckDetail.getParentId());
            stock.setLastMaintainTime(new Date());
            stockMapper.updateByPrimaryKeySelective(stock);
            if(goodsDisplayCheckDetail.getUnqualifiedQuantity().compareTo(BigDecimal.ZERO)>0){
                lineNum++;
                StopSaleDtlRequestVO stopSaleDtlRequestVO = new StopSaleDtlRequestVO();
                stopSaleDtlRequestVO.setGoodsId(goodsDisplayCheckDetail.getGoodsId());
                //获取不合格原因
                String stopReason = getStopReason(loginUser,goodsDisplayCheckDetail.getReasonIds()).toString();
                stopSaleDtlRequestVO.setStopReason(stopReason);
                stopSaleDtlRequestVO.setLineNum(lineNum);
                List<StopSaleShelfRequestVO> stopSaleShelfRequestVOS = new ArrayList<>();
                StopSaleShelfRequestVO stopSaleShelfRequestVO = new StopSaleShelfRequestVO();
                stopSaleShelfRequestVO.setLineNum(lineNum);
                stopSaleShelfRequestVO.setLotId(goodsDisplayCheckDetail.getLotId());
                stopSaleShelfRequestVO.setShelfId(goodsDisplayCheckDetail.getShelfId());
                stopSaleShelfRequestVO.setQuantity(goodsDisplayCheckDetail.getUnqualifiedQuantity());

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
            stopSaleRequestVO.setBaseOrderCode(goodsDisplayCheck.getCode());
            stopSaleRequestVO.setBaseOrderDate(goodsDisplayCheck.getCheckDate());
            stopSaleRequestVO.setBaseOrderId(goodsDisplayCheck.getId());
            stopSaleRequestVO.setBaseOrderType(goodsDisplayCheck.getOrderType());
            stopSaleRequestVO.setStopSaleDtlRequestVOList(stopSaleDtlRequestVOS);
            //生成生成停售通知跟药品锁定单据
            stopSaleService.saveStopSale(loginUser, stopSaleRequestVO);
        }
        return 0;
    }

    @Override
    public Page<List<ShowGoodsDisplayCheckVO>> getDisplayCheckOrderList(UserVO loginUser, Integer checkOrderType, String orderName, String orderType, Date startDate, Date endDate, Long warehouseAreaId, Integer maintanceType, Integer goodsType, String code, Integer pageNo, Integer pageSize) throws Exception {
        List<ShowGoodsDisplayCheckVO> showGoodsDisplayCheckVOS = new ArrayList<>();
//        if(checkOrderType == 0){
//            //陈列检查计划 与养护计划通用
//            List<ShowGoodsMaintanceVO> showGoodsMaintanceVOList = goodsMaintanceMapper.getWillMaintanceOrderList(loginUser.getEnterpriseId(),orderName, orderType,startDate,endDate);
//            for (ShowGoodsMaintanceVO showGoodsMaintanceVO : showGoodsMaintanceVOList){
//                ShowGoodsDisplayCheckVO showGoodsDisplayCheckVO = new ShowGoodsDisplayCheckVO();
//                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(showGoodsMaintanceVO,showGoodsDisplayCheckVO);
//                //set检查日期
//                showGoodsDisplayCheckVO.setCheckDate(showGoodsMaintanceVO.getMaintanceDate());
//                //set默认的养护人员信息
//                showGoodsDisplayCheckVO.setCheckerId(loginUser.getUserId());
//                showGoodsDisplayCheckVO.setCheckerCode(loginUser.getUserCode());
//                showGoodsDisplayCheckVO.setCheckerName(loginUser.getUserName());
//                //set库区名
//                showGoodsDisplayCheckVO.setWarehouseAreaName(warehouseAreaMapper.selectByPrimaryKey(showGoodsMaintanceVO.getWarehouseAreaId()).getName());
//                //set养护类型名
//                showGoodsDisplayCheckVO.setMaintanceTypeName(MaintanceType.getName(showGoodsMaintanceVO.getMaintanceType()));
//                //set药品类型名
//                showGoodsDisplayCheckVO.setGoodsTypeName(MaintanceGoodsType.getName(showGoodsMaintanceVO.getGoodsType()));
//                //根据查询条件筛选
//                if(warehouseAreaId != null)//库存ID
//                    if(!showGoodsDisplayCheckVO.getWarehouseAreaId().equals(warehouseAreaId)) continue;
//                if(maintanceType != null)//养护类型
//                    if(!showGoodsDisplayCheckVO.getMaintanceType().equals(maintanceType)) continue;
//                if(goodsType != null)//药品类型
//                    if(!showGoodsDisplayCheckVO.getGoodsType().equals(goodsType)) continue;
//                showGoodsDisplayCheckVOS.add(showGoodsDisplayCheckVO);
//            }
//        }else{
//            List<GoodsDisplayCheck> goodsDisplayChecks = displayCheckMapper.getCheckOrderList(loginUser.getEnterpriseId(),checkOrderType,orderName, orderType,startDate,endDate,warehouseAreaId,maintanceType,goodsType,code);
//            for(GoodsDisplayCheck goodsDisplayCheck : goodsDisplayChecks){
//                ShowGoodsDisplayCheckVO showGoodsDisplayCheckVO = new ShowGoodsDisplayCheckVO();
//                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsDisplayCheck,showGoodsDisplayCheckVO);
//                //set总单id
//                showGoodsDisplayCheckVO.setIds(goodsDisplayCheck.getId().toString());
//                //set默认的养护人员信息
//                showGoodsDisplayCheckVO.setCheckerId(loginUser.getUserId());
//                showGoodsDisplayCheckVO.setCheckerCode(loginUser.getUserCode());
//                showGoodsDisplayCheckVO.setCheckerName(loginUser.getUserName());
//                //set养护类型名
//                showGoodsDisplayCheckVO.setMaintanceTypeName(MaintanceType.getName(showGoodsDisplayCheckVO.getMaintanceType()));
//                //set药品类型名
//                showGoodsDisplayCheckVO.setGoodsTypeName(MaintanceGoodsType.getName(showGoodsDisplayCheckVO.getGoodsType()));
//                showGoodsDisplayCheckVOS.add(showGoodsDisplayCheckVO);
//            }
//        }
        if(orderName != null){
            if(orderName.equals("checkDate")) orderName = "check_date";
            else if(orderName.equals("warehouseAreaId")) orderName = "warehouse_area_id";
            else if(orderName.equals("maintanceType")) orderName = "maintance_type";
            else if(orderName.equals("goodsType")) orderName = "goods_type";
            else orderName = null;
        }
        if(checkOrderType==1) checkOrderType = PubStatus.goodsDisplayCheckStatus.IN_CHECK;
        if(checkOrderType==2) checkOrderType = PubStatus.goodsDisplayCheckStatus.FINISHED;
        if(checkOrderType==3) checkOrderType = PubStatus.goodsDisplayCheckStatus.CANCELED;

        List<GoodsDisplayCheck> goodsDisplayChecks = displayCheckMapper.getCheckOrderList(loginUser.getEnterpriseId(), checkOrderType, orderName, orderType, startDate, endDate, warehouseAreaId, maintanceType, goodsType, code, pageSize,(pageNo-1)*pageSize);
        Page<List<ShowGoodsDisplayCheckVO>> page = new Page(pageNo, pageSize);

        for (GoodsDisplayCheck goodsDisplayCheck : goodsDisplayChecks) {
            ShowGoodsDisplayCheckVO showGoodsDisplayCheckVO = new ShowGoodsDisplayCheckVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsDisplayCheck, showGoodsDisplayCheckVO);
            //set总单id
            showGoodsDisplayCheckVO.setIds(goodsDisplayCheck.getId().toString());
            //set默认的养护人员信息
            showGoodsDisplayCheckVO.setCheckerId(loginUser.getUserId());
            showGoodsDisplayCheckVO.setCheckerCode(loginUser.getUserCode());
            showGoodsDisplayCheckVO.setCheckerName(loginUser.getUserName());
            //set养护类型名
            showGoodsDisplayCheckVO.setMaintanceTypeName(MaintanceType.getName(showGoodsDisplayCheckVO.getMaintanceType()));
            //set药品类型名
            showGoodsDisplayCheckVO.setGoodsTypeName(MaintanceGoodsType.getName(showGoodsDisplayCheckVO.getGoodsType()));
            showGoodsDisplayCheckVOS.add(showGoodsDisplayCheckVO);
        }
        page.setResult(showGoodsDisplayCheckVOS);
        goodsDisplayChecks = displayCheckMapper.getCheckOrderList(loginUser.getEnterpriseId(), checkOrderType, orderName, orderType, startDate, endDate, warehouseAreaId, maintanceType, goodsType, code, null, null);
        page.setTotalRecord(goodsDisplayChecks.size());

        return page;
    }
    @Override
    public List<GoodsDisplayCheckDetailVO> getDisplayCheckOrderDtlList(UserVO loginUser, String ids) throws Exception {
        List<GoodsDisplayCheckDetailVO> goodsDisplayCheckDetailVOS = new ArrayList<>();

//        if(checkOrderType == 0){
//            List<SelectMaintanceGoodsVO> selectMaintanceGoodsVOS = goodsMaintanceService.getGoodsStockList(loginUser,null,warehouseAreaId,maintanceType,goodsType,1);
//            for(String id : ids.split(",")){
//                for(SelectMaintanceGoodsVO selectMaintanceGoodsVO : selectMaintanceGoodsVOS){
//                    GoodsDisplayCheckDetailVO goodsDisplayCheckDetailVO = new GoodsDisplayCheckDetailVO();
//                    CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(selectMaintanceGoodsVO,goodsDisplayCheckDetailVO);
//                    goodsDisplayCheckDetailVOS.add(goodsDisplayCheckDetailVO);
//                }
//            }
//        }else {
//            List<GoodsDisplayCheckDetail> goodsDisplayCheckDetails = displayCheckDetailMapper.selectByCheckId(Long.valueOf(ids));
//            for(GoodsDisplayCheckDetail goodsDisplayCheckDetail : goodsDisplayCheckDetails){
//                GoodsDisplayCheckDetailVO goodsDisplayCheckDetailVO = new GoodsDisplayCheckDetailVO();
//                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsDisplayCheckDetail,goodsDisplayCheckDetailVO);
//                goodsDisplayCheckDetailVOS.add(goodsDisplayCheckDetailVO);
//            }
//        }
        List<GoodsDisplayCheckDetail> goodsDisplayCheckDetails = displayCheckDetailMapper.selectByCheckId(Long.valueOf(ids));
        for(GoodsDisplayCheckDetail goodsDisplayCheckDetail : goodsDisplayCheckDetails){
            GoodsDisplayCheckDetailVO goodsDisplayCheckDetailVO = new GoodsDisplayCheckDetailVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsDisplayCheckDetail,goodsDisplayCheckDetailVO);
            //set 养护措施
            //:养护措施:7 养护结论:6 处置措施:3 问题原因:1
            List<QualitySetVO> qualitySetVOS = goodsMaintanceService.getMaintanceMeasuresList(loginUser,7,1);
            StringBuilder reasonName = new StringBuilder();
            if(goodsDisplayCheckDetailVO.getMeasuresIds() != null){
                if(goodsDisplayCheckDetailVO.getMeasuresIds().contains(",")){
                    for(String id : goodsDisplayCheckDetailVO.getMeasuresIds().split(",")){
                        for(QualitySetVO qualitySetVO : qualitySetVOS){
                            if(id.trim().equals(qualitySetVO.getId()+"")){
                                reasonName.append(qualitySetVO.getDescription());
                                reasonName.append(";");
                            }
                        }
                    }
                }else {
                    for(QualitySetVO qualitySetVO : qualitySetVOS){
                        if(goodsDisplayCheckDetailVO.getMeasuresIds().trim().equals(qualitySetVO.getId()+"")){
                            reasonName.append(qualitySetVO.getDescription());
                            reasonName.append(";");
                        }
                    }
                }
            }
            goodsDisplayCheckDetailVO.setMeasures(reasonName.toString());
            //set养护结论
            qualitySetVOS.clear();
            qualitySetVOS = goodsMaintanceService.getMaintanceMeasuresList(loginUser,6,1);
            reasonName.delete(0,reasonName.length());
            if(goodsDisplayCheckDetailVO.getConclusionId() != null){
                for(QualitySetVO qualitySetVO : qualitySetVOS){
                    if(goodsDisplayCheckDetailVO.getConclusionId().equals(qualitySetVO.getId())){
                        reasonName.append(qualitySetVO.getDescription());
                    }
                }
            }
            goodsDisplayCheckDetailVO.setConclusion(reasonName.toString());
            //set问题原因
            qualitySetVOS.clear();
            qualitySetVOS = goodsMaintanceService.getMaintanceMeasuresList(loginUser,1,1);
            reasonName.delete(0,reasonName.length());
            if(goodsDisplayCheckDetailVO.getReasonIds() != null){
                if(goodsDisplayCheckDetailVO.getReasonIds().contains(",")){
                    for(String id : goodsDisplayCheckDetailVO.getReasonIds().split(",")){
                        for(QualitySetVO qualitySetVO : qualitySetVOS){
                            if(id.trim().equals(qualitySetVO.getId()+"")){
                                reasonName.append(qualitySetVO.getDescription());
                                reasonName.append(";");
                            }
                        }
                    }
                }else {
                    for(QualitySetVO qualitySetVO : qualitySetVOS){
                        if(goodsDisplayCheckDetailVO.getReasonIds().trim().equals(qualitySetVO.getId()+"")){
                            reasonName.append(qualitySetVO.getDescription());
                            reasonName.append(";");
                        }
                    }
                }
            }
            goodsDisplayCheckDetailVO.setReason(reasonName.toString());
            //set处理措施
            qualitySetVOS.clear();
            qualitySetVOS = goodsMaintanceService.getMaintanceMeasuresList(loginUser,3,1);
            reasonName.delete(0,reasonName.length());
            if(goodsDisplayCheckDetailVO.getHandleMeasuresIds() != null){
                if(goodsDisplayCheckDetailVO.getHandleMeasuresIds().contains(",")){
                    for(String id : goodsDisplayCheckDetailVO.getHandleMeasuresIds().split(",")){
                        for(QualitySetVO qualitySetVO : qualitySetVOS){
                            if(id.trim().equals(qualitySetVO.getId()+"")){
                                reasonName.append(qualitySetVO.getDescription());
                                reasonName.append(";");
                            }
                        }
                    }
                }else {
                    for(QualitySetVO qualitySetVO : qualitySetVOS){
                        if(goodsDisplayCheckDetailVO.getHandleMeasuresIds().trim().equals(qualitySetVO.getId()+"")){
                            reasonName.append(qualitySetVO.getDescription());
                            reasonName.append(";");
                        }
                    }
                }
            }
            goodsDisplayCheckDetailVO.setHandleMeasures(reasonName.toString());

            goodsDisplayCheckDetailVOS.add(goodsDisplayCheckDetailVO);
        }
        return goodsDisplayCheckDetailVOS;
    }

    private List<String> getStopReason(UserVO loginUser,String reasonIds)throws Exception {
        List<QualitySetVO> qualitySetVOS = qualitySetMapper.getQualitySetVOList(loginUser.getEnterpriseId(), 1,null,1);
        List<String> resultList = new ArrayList<>();
        if(reasonIds.contains(",")){
            for(String reasonId : reasonIds.split(",")){
                for(QualitySetVO qualitySetVO : qualitySetVOS){
                    if(qualitySetVO.getId().toString().equals(reasonId.trim())){
                        resultList.add(qualitySetVO.getDescription());
                    }
                }
            }
        }else {
            for(QualitySetVO qualitySetVO : qualitySetVOS){
                if(qualitySetVO.getId().toString().equals(reasonIds.trim())){
                    resultList.add(qualitySetVO.getDescription());
                }
            }
        }
        return resultList;
    }

    @Override
    public Page<List<ShowDisplayCheckVO>> getWillCheckOrderList(UserVO loginUser, String orderName, String orderType, Date startDate, Date endDate, Long warehouseAreaId, Integer maintanceType, Integer goodsType, Integer pageNo, Integer pageSize) throws Exception{
        List<ShowDisplayCheckVO> showDisplayCheckVOS = new ArrayList<>();
//        List<SelectMaintanceGoodsVO> selectMaintanceGoodsVOS = goodsMaintanceDetailMapper.selectMaintanceGoodsVO(loginUser.getEnterpriseId(),null,null,0);
        List<SelectMaintanceGoodsVO> selectMaintanceGoodsVOS = goodsMaintanceService.getGoodsStockList(loginUser,null,warehouseAreaId,maintanceType,goodsType,1);
        for(SelectMaintanceGoodsVO selectMaintanceGoodsVO : selectMaintanceGoodsVOS){
            List<SelectMaintanceGoodsVO> goodsVOS = new ArrayList<>();//保存细单集合
            ShowDisplayCheckVO showDisplayCheckVO = new ShowDisplayCheckVO();
            //set默认的养护人员信息
            showDisplayCheckVO.setCheckerId(loginUser.getUserId());
            showDisplayCheckVO.setCheckerCode(loginUser.getUserCode());
            showDisplayCheckVO.setCheckerName(loginUser.getUserName());
            //set库区名称
            showDisplayCheckVO.setWarehouseAreaId(selectMaintanceGoodsVO.getWarehouseAreaId());
            showDisplayCheckVO.setWarehouseAreaName(warehouseAreaMapper.selectByPrimaryKey(selectMaintanceGoodsVO.getWarehouseAreaId()).getName());

            //近效期商品
            if(goodsType == 3){
                if(maintanceType != null && maintanceType == 0) break;//按照养护类型过滤,如果是普通养护,则直接跳出循环
                if(warehouseAreaId != null){//按照库区过滤
                    if(!warehouseAreaId.equals(selectMaintanceGoodsVO.getWarehouseAreaId()))
                        continue;
                }

                if(selectMaintanceGoodsVO.getNearEffectPeriod()>=selectMaintanceGoodsVO.getValidDays()){
                    //set 药品类型（0-成药；1-中药饮片；2-拆零药品；3-近效期药品）
                    showDisplayCheckVO.setGoodsType(MaintanceGoodsType.NearMedicine.getCode());
                    showDisplayCheckVO.setGoodsTypeName(MaintanceGoodsType.NearMedicine.getName());
                    //set 养护类型（0-重点养护；1-常规养护）
                    showDisplayCheckVO.setMaintanceType(MaintanceType.ImportantMaintanceType.getCode());
                    showDisplayCheckVO.setMaintanceTypeName(MaintanceType.ImportantMaintanceType.getName());
                    //如果设置的是重点养护,则取养护设置的周期,若不是,则默认养护周期为30天
                    if(selectMaintanceGoodsVO.getMaintenanceType() == 0)
                        selectMaintanceGoodsVO.setMaintenanceCycle(30L);
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
                    showDisplayCheckVO.setCheckDate(calendar.getTime());

                    //如果总单存在,则将细单插入总单的细单列表
                    for(ShowDisplayCheckVO show : showDisplayCheckVOS){
                        if(show.equals(showDisplayCheckVO)){
                            show.getSelectMaintanceGoodsVOList().add(selectMaintanceGoodsVO);
                            continue;
                        }
                    }
                    //若不存在 则新增一条记录
//                    goodsVOS.clear();//先清空在插入
                    goodsVOS.add(selectMaintanceGoodsVO);
                    showDisplayCheckVO.setSelectMaintanceGoodsVOList(goodsVOS);
                    showDisplayCheckVOS.add(showDisplayCheckVO);
                    continue;
                }
            }else if(goodsType == 2){
                if(warehouseAreaId != null){//按照库区过滤
                    if(!warehouseAreaId.equals(selectMaintanceGoodsVO.getWarehouseAreaId()))
                        continue;
                }
                if(maintanceType != null && maintanceType == 0) break;//按照养护类型过滤,如果是普通养护,则直接跳出循环
                //拆零商
                if(selectMaintanceGoodsVO.getGoodsNature()==1){
                    //set 药品类型（0-成药；1-中药饮片；2-拆零药品；3-近效期药品）
                    showDisplayCheckVO.setGoodsType(MaintanceGoodsType.DismountedDrugs.getCode());
                    showDisplayCheckVO.setGoodsTypeName(MaintanceGoodsType.DismountedDrugs.getName());
                    //set 养护类型（0-重点养护；1-常规养护）
                    showDisplayCheckVO.setMaintanceType(MaintanceType.ImportantMaintanceType.getCode());
                    showDisplayCheckVO.setMaintanceTypeName(MaintanceType.ImportantMaintanceType.getName());
                    //如果设置的是重点养护,则取养护设置的周期,若不是,则默认养护周期为30天
                    if(selectMaintanceGoodsVO.getMaintenanceType() == 0)
                        selectMaintanceGoodsVO.setMaintenanceCycle(30L);
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
                    showDisplayCheckVO.setCheckDate(calendar.getTime());
                    //如果总单存在,则将细单插入总单的细单列表
                    for(ShowDisplayCheckVO show : showDisplayCheckVOS){
                        if(show.equals(showDisplayCheckVO)){
                            show.getSelectMaintanceGoodsVOList().add(selectMaintanceGoodsVO);
                            continue;
                        }
                    }
                    //若不存在 则新增一条记录
//                    goodsVOS.clear();//先清空在插入
                    goodsVOS.add(selectMaintanceGoodsVO);
                    showDisplayCheckVO.setSelectMaintanceGoodsVOList(goodsVOS);
                    showDisplayCheckVOS.add(showDisplayCheckVO);
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
                    showDisplayCheckVO.setGoodsType(MaintanceGoodsType.ChineseMedicine.getCode());
                    showDisplayCheckVO.setGoodsTypeName(MaintanceGoodsType.ChineseMedicine.getName());
                    //set 养护类型（0-重点养护；1-常规养护）
                    showDisplayCheckVO.setMaintanceType(MaintanceType.ImportantMaintanceType.getCode());
                    showDisplayCheckVO.setMaintanceTypeName(MaintanceType.ImportantMaintanceType.getName());
                    //如果设置的是重点养护,则取养护设置的周期,若不是,则默认养护周期为30天
                    if(selectMaintanceGoodsVO.getMaintenanceType() == 0)
                        selectMaintanceGoodsVO.setMaintenanceCycle(30L);
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
                    showDisplayCheckVO.setCheckDate(calendar.getTime());
                    //如果总单存在,则将细单插入总单的细单列表
                    for(ShowDisplayCheckVO show : showDisplayCheckVOS){
                        if(show.equals(showDisplayCheckVO)){
                            show.getSelectMaintanceGoodsVOList().add(selectMaintanceGoodsVO);
                            continue;
                        }
                    }
                    //若不存在 则新增一条记录
//                    goodsVOS.clear();//先清空在插入
                    goodsVOS.add(selectMaintanceGoodsVO);
                    showDisplayCheckVO.setSelectMaintanceGoodsVOList(goodsVOS);
                    showDisplayCheckVOS.add(showDisplayCheckVO);
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
                    //set 药品类型（0-成药；1-中药饮片；2-拆零药品；3-近效期药品）
                    showDisplayCheckVO.setGoodsType(MaintanceGoodsType.PatentMedicine.getCode());
                    showDisplayCheckVO.setGoodsTypeName(MaintanceGoodsType.PatentMedicine.getName());
                    //set 养护类型（0-重点养护；1-常规养护）
                    showDisplayCheckVO.setMaintanceType(selectMaintanceGoodsVO.getMaintenanceType());
                    showDisplayCheckVO.setMaintanceTypeName(MaintanceType.getName(selectMaintanceGoodsVO.getMaintenanceType()));
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
                    showDisplayCheckVO.setCheckDate(calendar.getTime());
                    //如果总单存在,则将细单插入总单的细单列表
                    for(ShowDisplayCheckVO show : showDisplayCheckVOS){
                        if(show.equals(showDisplayCheckVO)){
                            show.getSelectMaintanceGoodsVOList().add(selectMaintanceGoodsVO);
                            continue;
                        }
                    }
                    //若不存在 则新增一条记录
//                    goodsVOS.clear();//先清空在插入
                    goodsVOS.add(selectMaintanceGoodsVO);
                    showDisplayCheckVO.setSelectMaintanceGoodsVOList(goodsVOS);
                    showDisplayCheckVOS.add(showDisplayCheckVO);
                    continue;
                }
            }
        }
        //排序
        if(orderName != null && orderType != null){
            Collections.sort(showDisplayCheckVOS, new Comparator<ShowDisplayCheckVO>() {
                @Override
                public int compare(ShowDisplayCheckVO o1, ShowDisplayCheckVO o2) {
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
                    if(orderName.equals("checkDate")){
                        if(orderType.equalsIgnoreCase("asc"))
                            return Long.compare(o1.getCheckDate().getTime(),o2.getCheckDate().getTime());
                        else
                            return Long.compare(o2.getCheckDate().getTime(),o1.getCheckDate().getTime());
                    }
                    return i;
                }
            });
        }
        //分页
        Page<List<ShowDisplayCheckVO>> page = new Page<>(pageNo,pageSize);
        page.setTotalRecord(showDisplayCheckVOS.size());
        int start = (pageNo-1)*pageSize;
        int end = pageNo*pageSize;
        if(showDisplayCheckVOS.size()<start || showDisplayCheckVOS.size()==0){
            page.setResult(new ArrayList<>());
        }else if(showDisplayCheckVOS.size()>start && showDisplayCheckVOS.size()<end){
            page.setResult(showDisplayCheckVOS.subList(start,showDisplayCheckVOS.size()));
        }else
            page.setResult(showDisplayCheckVOS.subList(start,end));
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
        GoodsDisplayCheck goodsDisplayCheck = displayCheckMapper.selectByPrimaryKey(id);
        titleSecond.add("检查单号:"+goodsDisplayCheck.getCode()+" 检查日期:"+goodsDisplayCheck.getCheckDate()+" 检查人员:"+goodsDisplayCheck.getCheckerName()
                +" 库区:"+goodsDisplayCheck.getWarehouseAreaName()+" 检查类型:"+MaintanceType.getName(goodsDisplayCheck.getMaintanceType())
                +" 药品类型:"+MaintanceGoodsType.getName(goodsDisplayCheck.getGoodsType())+" 备注:"+goodsDisplayCheck.getRemark());
        StringBuilder end = new StringBuilder();
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("陈列检查");
        List<String> needTotalName = new ArrayList<>();
        List<GoodsDisplayCheckDetailVO> goodsDisplayCheckDetailVOS = new ArrayList<>();
        List<GoodsDisplayCheckDetail> goodsDisplayCheckDetails = displayCheckDetailMapper.selectByCheckId(id);
        for(GoodsDisplayCheckDetail goodsDisplayCheckDetail : goodsDisplayCheckDetails){
            GoodsDisplayCheckDetailVO goodsDisplayCheckDetailVO = new GoodsDisplayCheckDetailVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsDisplayCheckDetail,goodsDisplayCheckDetailVO);
            //set 养护措施
            //:养护措施:7 养护结论:6 处置措施:3 问题原因:1
            List<QualitySetVO> qualitySetVOS = goodsMaintanceService.getMaintanceMeasuresList(loginUser,7,1);
            StringBuilder reasonName = new StringBuilder();
            if(goodsDisplayCheckDetailVO.getMeasuresIds() != null){
                if(goodsDisplayCheckDetailVO.getMeasuresIds().contains(",")){
                    for(String rid : goodsDisplayCheckDetailVO.getMeasuresIds().split(",")){
                        for(QualitySetVO qualitySetVO : qualitySetVOS){
                            if(rid.trim().equals(qualitySetVO.getId()+"")){
                                reasonName.append(qualitySetVO.getDescription());
                                reasonName.append(";");
                            }
                        }
                    }
                }else {
                    for(QualitySetVO qualitySetVO : qualitySetVOS){
                        if(goodsDisplayCheckDetailVO.getMeasuresIds().trim().equals(qualitySetVO.getId())){
                            reasonName.append(qualitySetVO.getDescription());
                            reasonName.append(";");
                        }
                    }
                }
            }
            goodsDisplayCheckDetailVO.setMeasures(reasonName.toString());
            //set养护结论
            qualitySetVOS.clear();
            qualitySetVOS = goodsMaintanceService.getMaintanceMeasuresList(loginUser,6,1);
            reasonName.delete(0,reasonName.length());
            if(goodsDisplayCheckDetailVO.getConclusionId() != null){
                for(QualitySetVO qualitySetVO : qualitySetVOS){
                    if(goodsDisplayCheckDetailVO.getId() == qualitySetVO.getId()){
                        reasonName.append(qualitySetVO.getDescription());
                    }
                }
            }
            goodsDisplayCheckDetailVO.setConclusion(reasonName.toString());
            //set问题原因
            qualitySetVOS.clear();
            qualitySetVOS = goodsMaintanceService.getMaintanceMeasuresList(loginUser,1,1);
            reasonName.delete(0,reasonName.length());
            if(goodsDisplayCheckDetailVO.getReasonIds() != null){
                if(goodsDisplayCheckDetailVO.getReasonIds().contains(",")){
                    for(String rid : goodsDisplayCheckDetailVO.getReasonIds().split(",")){
                        for(QualitySetVO qualitySetVO : qualitySetVOS){
                            if(rid.trim().equals(qualitySetVO.getId()+"")){
                                reasonName.append(qualitySetVO.getDescription());
                                reasonName.append(";");
                            }
                        }
                    }
                }else {
                    for(QualitySetVO qualitySetVO : qualitySetVOS){
                        if(goodsDisplayCheckDetailVO.getReasonIds().trim().equals(qualitySetVO.getId())){
                            reasonName.append(qualitySetVO.getDescription());
                            reasonName.append(";");
                        }
                    }
                }
            }
            goodsDisplayCheckDetailVO.setReason(reasonName.toString());
            //set处理措施
            qualitySetVOS.clear();
            qualitySetVOS = goodsMaintanceService.getMaintanceMeasuresList(loginUser,3,1);
            reasonName.delete(0,reasonName.length());
            if(goodsDisplayCheckDetailVO.getHandleMeasuresIds() != null){
                if(goodsDisplayCheckDetailVO.getHandleMeasuresIds().contains(",")){
                    for(String rid : goodsDisplayCheckDetailVO.getHandleMeasuresIds().split(",")){
                        for(QualitySetVO qualitySetVO : qualitySetVOS){
                            if(rid.trim().equals(qualitySetVO.getId()+"")){
                                reasonName.append(qualitySetVO.getDescription());
                                reasonName.append(";");
                            }
                        }
                    }
                }else {
                    for(QualitySetVO qualitySetVO : qualitySetVOS){
                        if(goodsDisplayCheckDetailVO.getHandleMeasuresIds().trim().equals(qualitySetVO.getId())){
                            reasonName.append(qualitySetVO.getDescription());
                            reasonName.append(";");
                        }
                    }
                }
            }
            goodsDisplayCheckDetailVO.setHandleMeasures(reasonName.toString());
            goodsDisplayCheckDetailVOS.add(goodsDisplayCheckDetailVO);
        }
        purchaseGeneralComponent.commExcelExport(output,map,goodsDisplayCheckDetailVOS,name,titleSecond,end.toString(),true,needTotalName);

    }

}
