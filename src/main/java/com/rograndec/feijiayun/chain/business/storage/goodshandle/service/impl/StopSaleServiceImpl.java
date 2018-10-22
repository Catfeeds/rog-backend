package com.rograndec.feijiayun.chain.business.storage.goodshandle.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.vo.GoodsInfoStockShelfVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.dao.StopSaleDetailMapper;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.dao.StopSaleMapper;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.dao.StopSaleShelfMapper;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.*;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.service.GoodsLockService;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.service.StopSaleService;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.*;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.ExcelComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.component.UserEnterpriseComponet;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.TwoTuple;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by dudy on 2017/9/27.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StopSaleServiceImpl implements StopSaleService {




    @Autowired
    private StopSaleMapper stopSaleMapper;

    @Autowired
    private StopSaleDetailMapper stopSaleDetailMapper;

    @Autowired
    private StopSaleShelfMapper  stopSaleShelfMapper;

    @Autowired
    private ManageConfigMapper manageConfigMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private UserEnterpriseComponet userEnterpriseComponet;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private GoodsLockService  lockService;

    @Autowired
    private ExcelComponent excelComponent;

    @Override
    public Page<List<StopSalePageVO>> selectStopSalePage(SaleOrderRequestVO requestVO, UserVO userVO) {

        Long eId = userVO.getEnterpriseId();

        requestVO.setEnterpriseId(eId);

        if (!"asc".equals(requestVO.getSort()) && !"desc".equals(requestVO.getSort())) {
            //sort = "desc";
            requestVO.setSort("desc");
        }
        if (StringUtils.isEmpty(requestVO.getOrder()) || "date".equals(requestVO.getOrder())) {
            //order = "plan_date"; // 默认计划日期倒序
            requestVO.setOrder("stop_date");
        }

        if (StringUtils.isEmpty(requestVO.getOrder()) || "stopFromName".equals(requestVO.getOrder())) {
            //order = "plan_date"; // 默认计划日期倒序
            requestVO.setOrder("stop_from");
        }
        Date start = DateUtils.StringToDate(requestVO.getStartDate());
        Date end = DateUtils.addDay(DateUtils.StringToDate(requestVO.getEndDate()),1);

        requestVO.setStart(start);
        requestVO.setEnd(end);


        Page<List<StopSalePageVO>> page = new Page<>(requestVO.getPageNo(), requestVO.getPageSize());
        requestVO.setPageStart(page.getStart());

        //com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());

        List<StopSalePageVO>  dataList =  stopSaleMapper.selectStopSalePage(requestVO);

        Integer count =  stopSaleMapper.selectStopSalePageCount(requestVO);
//
//        for (StopSalePageVO pageVO:dataList) {
//            pageVO.setStopFromName(StopSaleFromType.getStopSaleFromTypeEnum(pageVO.getStopFrom()).getValue());
//        }

        page.setResult(dataList);
        page.setTotalRecord(count);

        return page;
    }

    @Override
    public StopSaleReturnVO selectDetailById(Long id) {
        StopSaleReturnVO stopSaleReturnVO = stopSaleMapper.selectDetailById(id);

        stopSaleReturnVO.setStopFromName(StopSaleFromType.getStopSaleFromTypeEnum(stopSaleReturnVO.getStopFrom()).getValue());
        return stopSaleReturnVO;
    }

    @Override
    public Result<String> saveStopSale(UserVO userVO, StopSaleRequestVO stopSaleRequestVO) throws Exception{
        Result<String> result = new Result<>();


        List<StopSaleDtlRequestVO> stopSaleDtlRequestVOList = stopSaleRequestVO.getStopSaleDtlRequestVOList();

        if(CollectionUtils.isEmpty(stopSaleDtlRequestVOList)){
            throw new BusinessException(SysCode.FAIL.getCode(),"请添加停售商品明细");
        }

        Long eId = userVO.getEnterpriseId();
        String eCode = userVO.getEnterpriseCode();

        //=================插入停售主表(数量和种类没有)======================
        StopSale stopSale = new StopSale();
        BeanUtils.copyProperties(stopSaleRequestVO,stopSale);//
        stopSale.setEnterpriseId(eId);
        stopSale.setParentId(userVO.getParentId());
        stopSale.setOrderType(OrderRule.STOP_SALE.getType());// 类型编码
        String code = orderCodeComponent.generate(OrderRule.STOP_SALE.getCodePrefix(), eId, eCode);// 单号
        stopSale.setCode(code);
        stopSale.setStatus(EnableStatus.ENABLE.getStatus());

        ManageConfig manageConfig = manageConfigMapper.selectByCurrentUser(userVO);
        if(manageConfig.getBusinessControl() == 1){
            stopSale.setStopManId(userVO.getUserId());
            stopSale.setStopManCode(userVO.getUserCode());
            stopSale.setStopManName(userVO.getUserName());
        } else {
            User user = userMapper.selectByPrimaryKey(stopSaleRequestVO.getStopManId());
            if(user == null){
                throw  new BusinessException("请选择通知人员");
            }

            stopSale.setStopManId(user.getId());
            stopSale.setStopManCode(user.getCode());
            stopSale.setStopManName(user.getName());
        }
        userEnterpriseComponet.setUserEnterprise(stopSale,userVO,true);

        //==================停售通知明细========================

        BigDecimal quantityTotal = BigDecimal.ZERO;

        List<StopSaleDetail> stopSaleDetails = new ArrayList<>();

        for (StopSaleDtlRequestVO dtlRequestVO:stopSaleRequestVO.getStopSaleDtlRequestVOList()){
            StopSaleDetail stopSaleDetail = new StopSaleDetail();
            BeanUtils.copyProperties(dtlRequestVO,stopSaleDetail);// 复制相同属性
            Goods goodsVO = goodsMapper.selectByPrimaryKey(dtlRequestVO.getGoodsId());
            stopSaleDetail.setGoodsCode(goodsVO.getCode());
            stopSaleDetail.setApprovalNumber(goodsVO.getApprovalNumber());// 批准文号
            stopSaleDetail.setUnitId(goodsVO.getUnitId());
            stopSaleDetail.setUnitName(goodsVO.getUnitName());
            stopSaleDetail.setDosageId(goodsVO.getDosageId());
            stopSaleDetail.setDosageName(goodsVO.getDosageName());
            stopSaleDetail.setBarcode(goodsVO.getBarcode());// 条形码
            stopSaleDetail.setGoodsName(goodsVO.getName());
            stopSaleDetail.setGoodsGenericName(goodsVO.getGenericName());
            stopSaleDetail.setGoodsSpecification(goodsVO.getSpecification());
            stopSaleDetail.setManufacturer(goodsVO.getManufacturer());// 生产厂商
            stopSaleDetail.setManufacturerId(goodsVO.getManufacturerId());
            stopSaleDetail.setGoodsPlace(goodsVO.getPlace());// 产地
            stopSaleDetail.setStatus(EnableStatus.ENABLE.getStatus());
            stopSaleDetail.setEnterpriseId(eId);
            stopSaleDetail.setParentId(userVO.getParentId());
            //stopSaleDetail.setStopId(stopSale.getId());  // 停售通知单一些信息
            stopSaleDetail.setStopCode(stopSale.getCode());
            stopSaleDetail.setStopDate(stopSale.getStopDate());
            stopSaleDetail.setOrderType(stopSale.getOrderType());
            userEnterpriseComponet.setUserEnterprise(stopSaleDetail,userVO,true);

            // =====================商品批号、货位保存========================

            List<StopSaleShelf> stopSaleShelves = new ArrayList<>();
            BigDecimal quantity = BigDecimal.ZERO;
            for(StopSaleShelfRequestVO stopSaleShelfRequestVO:dtlRequestVO.getStopSaleShelfRequestVOList()){

                StopSaleShelf stopSaleShelf = new StopSaleShelf();
                BeanUtils.copyProperties(stopSaleShelfRequestVO,stopSaleShelf);
                // 查询  货位信息
                GoodsInfoStockShelfVO goodsInfoStockShelfVO = stockMapper.getGoodsStockInfoByGoodsIdAndShelfId(
                        userVO.getEnterpriseId(), dtlRequestVO.getGoodsId(), stopSaleShelfRequestVO.getShelfId(),
                        stopSaleShelfRequestVO.getLotId());
                if (goodsInfoStockShelfVO == null) {
                    throw new BusinessException("货位信息不存在,请检查货位ID=" + stopSaleShelfRequestVO.getShelfId());
                }
                stopSaleShelf.setGoodsName(goodsVO.getName());
                stopSaleShelf.setGoodsCode(goodsVO.getCode());
                stopSaleShelf.setProductDate(goodsInfoStockShelfVO.getProductDate());
                stopSaleShelf.setValidDate(goodsInfoStockShelfVO.getValidUtil());
                stopSaleShelf.setShelfName(goodsInfoStockShelfVO.getShelfName());
                stopSaleShelf.setLotNumber(goodsInfoStockShelfVO.getLotNum());
                stopSaleShelf.setLotId(goodsInfoStockShelfVO.getLotNumberId());
                stopSaleShelf.setShelfStatusDesc(goodsInfoStockShelfVO.getShelfStatusDesc());


                quantity = quantity.add(stopSaleShelf.getQuantity());
                stopSaleShelf.setEnterpriseId(eId);
                stopSaleShelf.setParentId(userVO.getParentId());
                stopSaleShelf.setStopId(stopSale.getId());
                stopSaleShelf.setGoodsId(goodsVO.getId());
                stopSaleShelf.setDtlId(stopSaleDetail.getId());
                stopSaleShelf.setStatus(EnableStatus.ENABLE.getStatus());
                userEnterpriseComponet.setUserEnterprise(stopSaleShelf,userVO,true);
                stopSaleShelves.add(stopSaleShelf);
            }


            quantityTotal = quantityTotal.add(quantity);
            stopSaleDetail.setQuantity(quantity);
            stopSaleDetails.add(stopSaleDetail);

            stopSaleDetail.setStopSaleShelves(stopSaleShelves);
        }

        stopSale.setStopSaleDetails(stopSaleDetails);
        // 更新数量
        stopSale.setQuantityTotal(quantityTotal);
        stopSale.setVarietiesQuantity(stopSaleDetails.size());//品种数量

        stopSaleMapper.insertSelective(stopSale);

        for (StopSaleDetail stopSaleDetail:stopSale.getStopSaleDetails()) {
            stopSaleDetail.setStopId(stopSale.getId());
            stopSaleDetailMapper.insertSelective(stopSaleDetail);
            for (StopSaleShelf stopSaleShelf: stopSaleDetail.getStopSaleShelves()) {
                stopSaleShelf.setStopId(stopSale.getId());
                stopSaleShelf.setDtlId(stopSaleDetail.getId());
                stopSaleShelfMapper.insertSelective(stopSaleShelf);
            }

        }

        //================新增一条 商品锁定 ======================

        if(!stopSaleRequestVO.getStopFrom().equals(StopSaleFromType.GOODS_LOCK.getCode())){//3-商品锁定
            createGoodsLock(userVO,stopSale);
        }
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,"新增停售通知成功");

        return result;
    }

    @Override
    public void exportExcel(OutputStream output, UserVO userVO, Long id) throws Exception {

        StopSaleReturnVO stopSaleReturnVO = stopSaleMapper.selectDetailById(id);

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("goodsCode", "商品编码");
        map.put("goodsGenericName", "通用名称");
        map.put("dosageName", "剂型");
        map.put("goodsSpecification", "规格");
        map.put("unitName","单位");
        map.put("manufacturer", "生产厂商");
        map.put("goodsPlace","产地");
        map.put("lotNumber","批号");
        map.put("productDate","生产日期");
        map.put("validDate","有效期");
        map.put("shelfName","货位");
        map.put("shelfStatusDesc","质量状况");
        map.put("quantity","数量");
        map.put("stopReason","停售原因");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // 格式化下日期

        List<SaleOrderExcelVO> saleOrderExcelVOS = new ArrayList<>();
        for (StopSaleDetailReturnVO detailReturnVO:stopSaleReturnVO.getStopSaleDetailReturnVOList()){

            SaleOrderExcelVO saleOrderExcelVO = new SaleOrderExcelVO();

            BeanUtils.copyProperties(detailReturnVO, saleOrderExcelVO);
            for (StopSaleShelfReturnVO shelfReturnVO:detailReturnVO.getStopSaleShelfReturnVOList()){
                BeanUtils.copyProperties(shelfReturnVO, saleOrderExcelVO);
                saleOrderExcelVOS.add(saleOrderExcelVO);
            }

        }

        List<TwoTuple<String,String>> subHeadingList  = new ArrayList<>();
        subHeadingList.add(new TwoTuple<>(" 通知单号",stopSaleReturnVO.getCode()));
        subHeadingList.add(new TwoTuple<>(" 通知日期",format.format(stopSaleReturnVO.getStopDate())));
        subHeadingList.add(new TwoTuple<>(" 通知人员",stopSaleReturnVO.getStopManName()));
        subHeadingList.add(new TwoTuple<>(" 通知来源",StopSaleFromType.getStopSaleFromTypeEnum(stopSaleReturnVO.getStopFrom()).getValue()));
        subHeadingList.add(new TwoTuple<>(" 来源单号",stopSaleReturnVO.getBaseOrderCode() == null? "":stopSaleReturnVO.getBaseOrderCode()));


        excelComponent.exportExcel(output, userVO.getEnterpriseName(), "停售通知", subHeadingList, map,saleOrderExcelVOS ,null);

    }

    /**
     * 生成商品锁定单子
     * @param userVO
     * @param stopSale
     * @throws Exception
     */
    private void createGoodsLock(UserVO userVO, StopSale stopSale) throws Exception {

        GoodsLockSaveOrupdateVO goodsLock = new GoodsLockSaveOrupdateVO();

        Integer stopFrom = stopSale.getStopFrom();
        Integer lockReason = 0;
        // 做下转换
        if(stopFrom == StopSaleFromType.DISPLAY_CHECK.getCode()){ //陈列检查
            lockReason = 3;
        } else if (stopFrom == StopSaleFromType.MAINTANCE.getCode()){// 药品养护
            lockReason = 2;
        }

        goodsLock.setBaseOrderId(stopSale.getId());
        goodsLock.setLockReason(lockReason);// 锁定原因
        goodsLock.setLockDateStr(DateUtils.DateToString(stopSale.getStopDate(),DateUtils.FMT_DATE));// 日期

        goodsLock.setLockManId(stopSale.getStopManId());
        goodsLock.setRemark("来自停售通知");
        List<GoodsLockDetailSaveOrupdateVO> goodslockDetailVO = new ArrayList<>();


        for (StopSaleDetail dtlRequestVO:stopSale.getStopSaleDetails()){
            GoodsLockDetailSaveOrupdateVO lockDetailSaveOrupdateVO = new GoodsLockDetailSaveOrupdateVO();
            lockDetailSaveOrupdateVO.setGoodsId(dtlRequestVO.getGoodsId());
            lockDetailSaveOrupdateVO.setLineNum(dtlRequestVO.getLineNum());
            lockDetailSaveOrupdateVO.setRemark("来自停售通知");
            lockDetailSaveOrupdateVO.setBaseOrderDtlId(dtlRequestVO.getId());
            List<GoodsLockShelfSaveOrupdateVO> goodsLockShelfVO = new ArrayList<>();
            //
            for (StopSaleShelf shelfRequestVO :dtlRequestVO.getStopSaleShelves()) {
                GoodsLockShelfSaveOrupdateVO lockShelfSaveOrupdateVO = new GoodsLockShelfSaveOrupdateVO();
                lockShelfSaveOrupdateVO.setLotId(shelfRequestVO.getLotId());
                lockShelfSaveOrupdateVO.setShelfId(shelfRequestVO.getShelfId());
                lockShelfSaveOrupdateVO.setLineNum(shelfRequestVO.getLineNum());
                lockShelfSaveOrupdateVO.setQuantity(shelfRequestVO.getQuantity());
                lockShelfSaveOrupdateVO.setRemark("来自停售通知");
                goodsLockShelfVO.add(lockShelfSaveOrupdateVO);
            }


            lockDetailSaveOrupdateVO.setGoodsLockShelfSaveOrupdateVOList(goodsLockShelfVO);
            goodslockDetailVO.add(lockDetailSaveOrupdateVO);
        }


        goodsLock.setGoodsLockDetailSaveOrupdateVOList(goodslockDetailVO);
        lockService.save(goodsLock,userVO);
    }
}
