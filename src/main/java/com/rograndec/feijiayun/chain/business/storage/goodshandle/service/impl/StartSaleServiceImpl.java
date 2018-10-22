package com.rograndec.feijiayun.chain.business.storage.goodshandle.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockLockRecordMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.vo.GoodsInfoStockShelfVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.dao.StartSaleDetailMapper;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.dao.StartSaleMapper;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.dao.StartSaleShelfMapper;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.StartSale;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.StartSaleDetail;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.StartSaleShelf;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.service.GoodsHandleService;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.service.StartSaleService;
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
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dudy on 2017/9/27.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class StartSaleServiceImpl implements StartSaleService {


    @Autowired
    private StartSaleMapper startSaleMapper;

    @Autowired
    private StartSaleDetailMapper  startSaleDetailMapper;

    @Autowired
    private StartSaleShelfMapper  startSaleShelfMapper;

    @Autowired
    private ManageConfigMapper manageConfigMapper;

    @Autowired
    private UserMapper  userMapper;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private  UserEnterpriseComponet userEnterpriseComponet;

    @Autowired
    private GoodsMapper  goodsMapper;


    @Autowired
    private StockMapper  stockMapper;

    @Autowired
    @Lazy
    private GoodsHandleService  goodsHandleService;

    @Autowired
    private ExcelComponent excelComponent;


    @Override
    public Page<List<StartSalePageVO>> selectStartSalePage(SaleOrderRequestVO requestVO, UserVO userVO) {
        Long eId = userVO.getEnterpriseId();

        requestVO.setEnterpriseId(eId);

        if (!"asc".equals(requestVO.getSort()) && !"desc".equals(requestVO.getSort())) {
            //sort = "desc";
            requestVO.setSort("desc");
        }
        if (StringUtils.isEmpty(requestVO.getOrder()) || "date".equals(requestVO.getOrder())) {
            // 默认解停日期倒序
            requestVO.setOrder("start_date");
        }

        if (StringUtils.isEmpty(requestVO.getOrder()) || "startFromName".equals(requestVO.getOrder())) {
            //order = "plan_date"; // 默认计划日期倒序
            requestVO.setOrder("start_from");
        }
        Date start = DateUtils.StringToDate(requestVO.getStartDate());
        Date end = DateUtils.addDay(DateUtils.StringToDate(requestVO.getEndDate()),1);

        requestVO.setStart(start);
        requestVO.setEnd(end);


        Page<List<StartSalePageVO>> page = new Page<>(requestVO.getPageNo(), requestVO.getPageSize());

        requestVO.setPageStart(page.getStart());

        //com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());

        List<StartSalePageVO>  dataList =  startSaleMapper.selectStartSalePage(requestVO);
        Integer count = startSaleMapper.selectStartSalePageCount(requestVO);

        page.setResult(dataList);
        page.setTotalRecord(count);

        return page;
    }

    @Override
    public StartSaleReturnVO selectDetailById(Long id) {

        StartSaleReturnVO startSaleReturnVO = startSaleMapper.selectDetailById(id);
        startSaleReturnVO.setStartFromName(StartSaleFromType.getStartSaleFromTypeEnum(startSaleReturnVO.getStartFrom()).getValue());
        return startSaleReturnVO;
    }

    @Override
    public Result<String> saveStartSale(UserVO userVO, StartSaleRequestVO startSaleRequestVO) throws  Exception{


        List<StartSaleDtlRequestVO> startSaleDtlRequestVOList = startSaleRequestVO.getStartSaleDtlRequestVOList();
        if(CollectionUtils.isEmpty(startSaleDtlRequestVOList)){
            throw new BusinessException(SysCode.FAIL.getCode(),"请添加解停商品明细");
        }

        Result<String> result = new Result<>();

        Long eId = userVO.getEnterpriseId();
        String eCode = userVO.getEnterpriseCode();

        //=================插入解停主表======================
        StartSale  startSale = new StartSale();
        BeanUtils.copyProperties(startSaleRequestVO,startSale);//
        startSale.setEnterpriseId(eId);
        startSale.setParentId(userVO.getParentId());
        startSale.setOrderType(OrderRule.START_SALE.getType());// 类型编码
        String code = orderCodeComponent.generate(OrderRule.START_SALE.getCodePrefix(), eId, eCode);// 单号
        startSale.setCode(code);
        startSale.setStatus(EnableStatus.ENABLE.getStatus());

        ManageConfig manageConfig = manageConfigMapper.selectByCurrentUser(userVO);
        if(manageConfig.getBusinessControl() == 1){
            startSale.setStartManId(userVO.getUserId());
            startSale.setStartManCode(userVO.getUserCode());
            startSale.setStartManName(userVO.getUserName());
        } else {
            User user = userMapper.selectByPrimaryKey(startSaleRequestVO.getStartManId());
            if(user == null){
                throw  new BusinessException("请选择通知人员");
            }

            startSale.setStartManId(user.getId());
            startSale.setStartManCode(user.getCode());
            startSale.setStartManName(user.getName());
        }
        userEnterpriseComponet.setUserEnterprise(startSale,userVO,true);
        startSale.setVarietiesQuantity(startSaleRequestVO.getStartSaleDtlRequestVOList().size());//品种数量
        //==================解停通知明细========================

        BigDecimal quantityTotal = BigDecimal.ZERO;

        List<StartSaleDetail> startSaleDetails = new ArrayList<>();


        for (StartSaleDtlRequestVO dtlRequestVO:startSaleRequestVO.getStartSaleDtlRequestVOList()){
            StartSaleDetail  startSaleDetail = new StartSaleDetail();
            BeanUtils.copyProperties(dtlRequestVO,startSaleDetail);// 复制相同属性
            Goods goodsVO = goodsMapper.selectByPrimaryKey(dtlRequestVO.getGoodsId());
            startSaleDetail.setGoodsCode(goodsVO.getCode());
            startSaleDetail.setApprovalNumber(goodsVO.getApprovalNumber());// 批准文号
            startSaleDetail.setUnitId(goodsVO.getUnitId());
            startSaleDetail.setUnitName(goodsVO.getUnitName());
            startSaleDetail.setDosageId(goodsVO.getDosageId());
            startSaleDetail.setDosageName(goodsVO.getDosageName());
            startSaleDetail.setBarcode(goodsVO.getBarcode());// 条形码
            startSaleDetail.setGoodsName(goodsVO.getName());
            startSaleDetail.setGoodsGenericName(goodsVO.getGenericName());
            startSaleDetail.setGoodsSpecification(goodsVO.getSpecification());
            startSaleDetail.setManufacturer(goodsVO.getManufacturer());// 生产厂商
            startSaleDetail.setManufacturerId(goodsVO.getManufacturerId());
            startSaleDetail.setGoodsPlace(goodsVO.getPlace());// 产地
            startSaleDetail.setStatus(EnableStatus.ENABLE.getStatus());
            startSaleDetail.setEnterpriseId(eId);
            startSaleDetail.setParentId(userVO.getParentId());
            //  // 解停通知单一些信息
            startSaleDetail.setStartCode(startSale.getCode());
            startSaleDetail.setStartDate(startSale.getStartDate());
            startSaleDetail.setOrderType(startSale.getOrderType());

            userEnterpriseComponet.setUserEnterprise(startSaleDetail,userVO,true);
            startSaleDetails.add(startSaleDetail);
            //startSaleDetailMapper.insertSelective(startSaleDetail);

            // =====================商品批号、货位保存========================

            List<StartSaleShelf> startSaleShelves = new ArrayList<>();
            BigDecimal quantity  = BigDecimal.ZERO;
            for(StartSaleShelfRequestVO startSaleShelfRequestVO:dtlRequestVO.getStartSaleShelfRequestVOList()){

                StartSaleShelf  startSaleShelf = new StartSaleShelf();
                BeanUtils.copyProperties(startSaleShelfRequestVO,startSaleShelf);

                // 查询  货位信息
                GoodsInfoStockShelfVO goodsInfoStockShelfVO = stockMapper.getGoodsStockInfoByGoodsIdAndShelfId(
                        userVO.getEnterpriseId(), dtlRequestVO.getGoodsId(), startSaleShelfRequestVO.getShelfId(),startSaleShelfRequestVO.getLotId());
                if (goodsInfoStockShelfVO == null) {
                    throw new BusinessException("货位信息不存在,请检查货位ID=" + startSaleShelfRequestVO.getShelfId());
                }
                startSaleShelf.setGoodsCode(goodsVO.getCode());
                startSaleShelf.setGoodsName(goodsVO.getName());
                startSaleShelf.setProductDate(goodsInfoStockShelfVO.getProductDate());
                startSaleShelf.setValidDate(goodsInfoStockShelfVO.getValidUtil());
                startSaleShelf.setShelfName(goodsInfoStockShelfVO.getShelfName());
                startSaleShelf.setLotNumber(goodsInfoStockShelfVO.getLotNum());
                startSaleShelf.setShelfStatusDesc(goodsInfoStockShelfVO.getShelfStatusDesc());
                startSaleShelf.setLockId(startSaleShelfRequestVO.getLockId());
                quantity = quantity.add(startSaleShelf.getQuantity());

                startSaleShelf.setEnterpriseId(eId);
                startSaleShelf.setParentId(userVO.getParentId());
                //startSaleShelf.setStartId(startSale.getId());
                startSaleShelf.setGoodsId(goodsVO.getId());
                //startSaleShelf.setDtlId(startSaleDetail.getId());
                startSaleShelf.setStatus(EnableStatus.ENABLE.getStatus());
                userEnterpriseComponet.setUserEnterprise(startSaleShelf,userVO,true);
                //startSaleShelfMapper.insertSelective(startSaleShelf);
                startSaleShelves.add(startSaleShelf);

            }
            startSaleDetail.setQuantity(quantity);
            quantityTotal = quantityTotal.add(quantity);
            startSaleDetail.setStartSaleShelves(startSaleShelves);
        }

        // 更新数量
        startSale.setQuantityTotal(quantityTotal);
        startSale.setStartSaleDetails(startSaleDetails);
        startSaleMapper.insertSelective(startSale);


        for (StartSaleDetail startSaleDetail:startSale.getStartSaleDetails()) {
            startSaleDetail.setStartId(startSale.getId());
            startSaleDetailMapper.insertSelective(startSaleDetail);
            for (StartSaleShelf startSaleShelf: startSaleDetail.getStartSaleShelves()) {
                startSaleShelf.setStartId(startSale.getId());
                startSaleShelf.setDtlId(startSaleDetail.getId());
                startSaleShelfMapper.insertSelective(startSaleShelf);
            }

        }

        if(!startSaleRequestVO.getStartFrom().equals(StartSaleFromType.GOODS_HANDLE.getCode())){//1-商品处理
            createGoodsHandle(userVO,startSale);
        }

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,"新增解停通知成功");
        return result;
    }

    @Override
    public void exportExcel(OutputStream output, UserVO userVO, Long id) throws Exception {

        StartSaleReturnVO startSaleReturnVO = startSaleMapper.selectDetailById(id);

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("goodsCode", "商品编码");
        // map.put("goodsName", "商品名称");
        map.put("goodsGenericName", "通用名称");
        // map.put("dosageId", "剂型ID");
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
        map.put("startReason","解停原因");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); // 格式化下日期

        List<SaleOrderExcelVO> saleOrderExcelVOS = new ArrayList<>();
        for (StartSaleDetailReturnVO detailReturnVO:startSaleReturnVO.getStartSaleDetailReturnVOList()){

            SaleOrderExcelVO saleOrderExcelVO = new SaleOrderExcelVO();

            BeanUtils.copyProperties(detailReturnVO, saleOrderExcelVO);
            for (StartSaleShelfReturnVO shelfReturnVO:detailReturnVO.getStartSaleShelfReturnVOList()){
                BeanUtils.copyProperties(shelfReturnVO, saleOrderExcelVO);
                saleOrderExcelVOS.add(saleOrderExcelVO);
            }

        }


        List<TwoTuple<String,String>> subHeadingList  = new ArrayList<>();
        subHeadingList.add(new TwoTuple<>(" 通知单号",startSaleReturnVO.getCode()));
        subHeadingList.add(new TwoTuple<>(" 通知日期",format.format(startSaleReturnVO.getStartDate())));
        subHeadingList.add(new TwoTuple<>(" 通知人员",startSaleReturnVO.getStartManName()));
        subHeadingList.add(new TwoTuple<>(" 通知来源",StopSaleFromType.getStopSaleFromTypeEnum(startSaleReturnVO.getStartFrom()).getValue()));
        subHeadingList.add(new TwoTuple<>(" 来源单号",startSaleReturnVO.getBaseOrderCode() == null? "":startSaleReturnVO.getBaseOrderCode()));


        excelComponent.exportExcel(output, userVO.getEnterpriseName(), "解停通知", subHeadingList, map,saleOrderExcelVOS ,null);

    }

    @Override
    public List<StockDestroyVO> getLockStockList(Map<String, Object> map) {

        return startSaleMapper.getLockStockList(map);

    }

    private void createGoodsHandle(UserVO userVO, StartSale startSale) throws Exception{

        GoodsHandleSaveOrupdateVO goodsHandle = new GoodsHandleSaveOrupdateVO();

        goodsHandle.setBaseOrderId(startSale.getId());
        goodsHandle.setBaseOrderCode(startSale.getCode());
        goodsHandle.setBaseOrderDate(startSale.getStartDate());
        goodsHandle.setBaseOrderType(startSale.getOrderType());


        goodsHandle.setHandleResult(0);
        goodsHandle.setHandleDateStr(DateUtils.DateToString(startSale.getStartDate(),"yyyy-MM-dd HH:mm:ss"));// 日期

        goodsHandle.setHandleManId(startSale.getStartManId());
        goodsHandle.setRemark("来自停售通知");
        List<GoodsHandleDetailSaveOrupdateVO> goodslockDetailVOs = new ArrayList<>();


        for (StartSaleDetail dtlRequestVO:startSale.getStartSaleDetails()){
            GoodsHandleDetailSaveOrupdateVO handleDetailSaveOrupdateVO = new GoodsHandleDetailSaveOrupdateVO();
            handleDetailSaveOrupdateVO.setGoodsId(dtlRequestVO.getGoodsId());
            handleDetailSaveOrupdateVO.setLineNum(dtlRequestVO.getLineNum());
            handleDetailSaveOrupdateVO.setRemark("来自停售通知");
            handleDetailSaveOrupdateVO.setBaseOrderDtlId(dtlRequestVO.getId());

            List<GoodsHandleShelfSaveOrupdateVO> goodshandleShelfVOs = new ArrayList<>();
            //
            for (StartSaleShelf shelfRequestVO :dtlRequestVO.getStartSaleShelves()) {
                GoodsHandleShelfSaveOrupdateVO handleShelfSaveOrupdateVO = new GoodsHandleShelfSaveOrupdateVO();
                handleShelfSaveOrupdateVO.setLockId(shelfRequestVO.getLockId());
                handleShelfSaveOrupdateVO.setLotId(shelfRequestVO.getLotId());
                handleShelfSaveOrupdateVO.setShelfId(shelfRequestVO.getShelfId());
                handleShelfSaveOrupdateVO.setLineNum(shelfRequestVO.getLineNum());
                handleShelfSaveOrupdateVO.setQuantity(shelfRequestVO.getQuantity());

                handleShelfSaveOrupdateVO.setRemark("来自停售通知");
                goodshandleShelfVOs.add(handleShelfSaveOrupdateVO);
            }

            goodslockDetailVOs.add(handleDetailSaveOrupdateVO);
            handleDetailSaveOrupdateVO.setGoodsHandleShelfSaveOrupdateVOList(goodshandleShelfVOs);
        }

        goodsHandle.setGoodsHandleDetailSaveOrupdateVOList(goodslockDetailVOs);
        goodsHandleService.save(goodsHandle,userVO);
    }
}
