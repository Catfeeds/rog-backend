package com.rograndec.feijiayun.chain.business.storage.move.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseShelfDefVO;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.exception.GoodsBizException;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.keytable.dao.CostMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.LotNumberMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.LotNumber;
import com.rograndec.feijiayun.chain.business.keytable.entity.Stock;
import com.rograndec.feijiayun.chain.business.keytable.vo.AvgCostVO;
import com.rograndec.feijiayun.chain.business.storage.move.constant.FlowUnitType;
import com.rograndec.feijiayun.chain.business.storage.move.constant.OtherOutType;
import com.rograndec.feijiayun.chain.business.storage.move.dao.OtherOutDetailMapper;
import com.rograndec.feijiayun.chain.business.storage.move.dao.OtherOutMapper;
import com.rograndec.feijiayun.chain.business.storage.move.dao.OtherOutShelfMapper;
import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherOut;
import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherOutDetail;
import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherOutShelf;
import com.rograndec.feijiayun.chain.business.storage.move.service.OtherOutService;
import com.rograndec.feijiayun.chain.business.storage.move.vo.*;
import com.rograndec.feijiayun.chain.business.storage.move.vo.transfer.*;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.dao.DepartmentMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.OrderDirection;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.TaxRateType;
import com.rograndec.feijiayun.chain.common.model.OrderModel;
import com.rograndec.feijiayun.chain.common.vo.GoodsDefTaxRateVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OtherOutServiceImpl implements OtherOutService{

    @Autowired
    private CostMapper costMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private LotNumberMapper lotNumberMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private GoodsTaxRateMapper goodsTaxRateMapper;

    @Autowired
    private OtherOutDetailMapper otherOutDetailMapper;

    @Autowired
    private OtherOutMapper otherOutMapper;

    @Autowired
    private OtherOutShelfMapper otherOutShelfMapper;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private WarehouseShelfMapper warehouseShelfMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Autowired
    private InOutComponent inOutComponent;

    @Autowired
    private CommonComponent commonComponent;
    @Autowired
    private FinanceComponent financeComponent;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(UserVO userVO, OtherOutFormVO otherOutFormVO) throws Exception {

        List<OtherOutDetailFormVO> otherOutDetailFormVOS = otherOutFormVO.getOtherOutDetailFormVOS();

        List<AfterOtherOutDetailAndShlefs> afterOtherOutDetailAndShlefs = saveDetail(userVO, otherOutDetailFormVOS);

        List<OtherOutDetail> otherOutDetails = new ArrayList<>();
        afterOtherOutDetailAndShlefs.forEach(asf -> {
            otherOutDetails.add(asf.getOtherOutDetail());
        });

        OtherOutHeaderParam otherOutHeaderParam = new OtherOutHeaderParam();
        otherOutHeaderParam.setUserVO(userVO);
        otherOutHeaderParam.setOtherOutFormVO(otherOutFormVO);
        otherOutHeaderParam.setOtherOutDetails(otherOutDetails);
        String code = orderCodeComponent.generate(
                OrderRule.SEND.getCodePrefix()
                , userVO.getEnterpriseId()
                , userVO.getEnterpriseCode()
        );
        otherOutHeaderParam.setCode(code);

        Set<Long> outManIdAndAuditManId = OtherOutFormVO.getOutManIdAndAuditManId(otherOutFormVO);
        List<Long> us = new ArrayList<>(outManIdAndAuditManId);
        List<User> users = userMapper.selectByIds(us);

        users.forEach(user -> {
            if(user.getId().equals(otherOutFormVO.getAuditManId())){
                otherOutHeaderParam.setAuditMan(user);
            }
            if(user.getId().equals(otherOutFormVO.getOutManId())){
                otherOutHeaderParam.setOutMan(user);
            }
        });

        /**
         *
         DEPT(0,"部门"),
         HEADQUARTERS(1,"总部"),
         STORES(2,"门店"),
         SUPPLIER(3,"供货单位");
         */
        Integer flowUnitType = otherOutFormVO.getFlowUnitType();
        Long flowUnitId = otherOutFormVO.getFlowUnitId();
        if(null != flowUnitId){
            FlowUnit flowUnit4Type = getFlowUnit4Type(flowUnitType, flowUnitId);
            otherOutHeaderParam.setFlowUnit(flowUnit4Type);
        }
        OtherOut otherOut4VO = OtherOut.getOtherOut4VO(
                otherOutHeaderParam
        );

        otherOutMapper.insertSelective(otherOut4VO);

        List<Long> dtlIds = new ArrayList<>();
        for(AfterOtherOutDetailAndShlefs afd : afterOtherOutDetailAndShlefs){
            OtherOutDetail otherOutDetail = afd.getOtherOutDetail();
            OtherOutDetail.setAfter4OtherOut(
                    otherOutDetail
                    ,otherOut4VO
            );
            otherOutDetailMapper.insertSelective(otherOutDetail);
            dtlIds.add(otherOutDetail.getId());
            List<OtherOutShelf> otherOutShelves = afd.getOtherOutShelves();
            for(OtherOutShelf otherOutShelf : otherOutShelves){
                OtherOutShelf.setAfterOtherOutShelf(
                        otherOut4VO
                        , otherOutDetail
                        , otherOutShelf
                );

                otherOutShelfMapper.insertSelective(otherOutShelf);
            }
        }

        OtherOut otherOut = otherOutMapper.selectByPrimaryKey(otherOut4VO.getId());
        List<OtherOutShelf> dtlList = otherOutShelfMapper.selectByDtlIds(dtlIds);

        // 生成关键表数据
        OrderModelBuilder builder = new OrderModelBuilder(userVO);
        OrderModel orderModel = builder.buildOrderModel(OrderRule.SEND, otherOut, dtlList);
        inOutComponent.generateKeyTableDatas(OrderDirection.OUT, orderModel);

        /**
         * 生成其他出库关键表数据
         */
        financeComponent.otherOutToBalanceAndVoucher(userVO, otherOut);



    }

    public FlowUnit getFlowUnit4Type(Integer flowUnitType,Long flowUnitId ){
        FlowUnit flowUnit = null;
        //判断是否是部门
        if(FlowUnitType.DEPT.getCode() == flowUnitType){
            Department department = departmentMapper.selectByPrimaryKey(flowUnitId);
            flowUnit = new FlowUnit(flowUnitType,flowUnitId, department.getCode(), department.getName());
        }else if(FlowUnitType.HEADQUARTERS.getCode() == flowUnitType || FlowUnitType.STORES.getCode() == flowUnitType ){
            //判断是企业
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(flowUnitId);
            flowUnit = new FlowUnit(flowUnitType,flowUnitId, enterprise.getCode(), enterprise.getName());
        }else if(FlowUnitType.SUPPLIER.getCode() == flowUnitType ){
            //判断是供货商
            Supplier supplier = supplierMapper.selectByPrimaryKey(flowUnitId);
            flowUnit = new FlowUnit(flowUnitType,flowUnitId, supplier.getCode(), supplier.getName());
        }

        return flowUnit;
    }

    @Transactional(rollbackFor = Exception.class)
    public OtherOutShelf saveShelf(OtherOutShelfParam otherOutShelfParam) throws Exception {

        OtherOutShelf otherOutShelf = OtherOutShelf.getOtherOutShelf(otherOutShelfParam);
        return otherOutShelf;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<AfterOtherOutDetailAndShlefs> saveDetail(UserVO userVO, List<OtherOutDetailFormVO> otherOutDetailFormVOS) throws Exception {

//        Set<Long> costIds = OtherOutDetailFormVO.getCostIds(otherOutDetailFormVOS);
//        List<Long> clist = new ArrayList(costIds);
//        List<Cost> costs = costMapper.selectByIds(clist);

        Set<Long> goodsIds = OtherOutDetailFormVO.getGoodsIds(otherOutDetailFormVOS);
        List<Long> glist = new ArrayList(goodsIds);

        Long enterpriseId = userVO.getEnterpriseId();
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
        if(!enterprise.getChainType().equals(ChainType.Headquarters.getCode())){
            enterpriseId = enterprise.getParentId();
        }
        if(CollectionUtils.isEmpty(goodsIds)){
            throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"商品未找到");
        }
        List<Goods> goodsList = goodsMapper.selectByIds(glist,enterpriseId);

        List<OtherOutShelfFormVO> otherOutShelfFormVOs = OtherOutShelfFormVO.getOtherOutShelfFormVOs(otherOutDetailFormVOS);
        Set<Long> lotIds = OtherOutShelfFormVO.getLotIds(otherOutShelfFormVOs);
        List<Long> ls = new ArrayList(lotIds);
        List<LotNumber> lotNumbers = lotNumberMapper.selectByIds(ls);

        Set<Long> stockIds = OtherOutDetailFormVO.getStockIds(otherOutDetailFormVOS);
        List<Long> ss = new ArrayList(stockIds);
        List<Stock> stocks = stockMapper.selectByIds(ss);

        Set<Long> taxRateIds = OtherOutDetailFormVO.getTaxRateIds(otherOutDetailFormVOS);
        List<Long> tes = new ArrayList(taxRateIds);
        List<GoodsTaxRate> goodsTaxRates = goodsTaxRateMapper.selectByIds(tes);

        Set<Long> shelfIds = OtherOutDetailFormVO.getShelfIds(otherOutDetailFormVOS);
        List<Long> sfs = new ArrayList(shelfIds);
        List<WarehouseShelf> warehouseShelves = warehouseShelfMapper.selectByIds(sfs);

        List<AfterOtherOutDetailAndShlefs> otherOutDetails = new ArrayList<>();

        for(OtherOutDetailFormVO detailVO : otherOutDetailFormVOS){
            OtherOutDetailParam otherOutDetailParam = new OtherOutDetailParam();
            otherOutDetailParam.setUserVO(userVO);
            otherOutDetailParam.setLineNum(detailVO.getLineNum());
            otherOutDetailParam.setOtherOutDetailFormVO(detailVO);

//            costs.stream().filter(cost -> cost.getId().equals(detailVO.getCostId()))
//                    .forEach(cost -> otherOutDetailParam.setCost(cost));

            goodsList.stream().filter(goods -> goods.getId().equals(detailVO.getGoodsId()))
                    .forEach(goods -> otherOutDetailParam.setGoods(goods));

            goodsTaxRates.stream().filter(goodsTaxRate -> goodsTaxRate.getId().equals(detailVO.getTaxRateId()))
                    .forEach(goodsTaxRate -> otherOutDetailParam.setGoodsTaxRate(goodsTaxRate));


            OtherOutDetail otherOutDetail = OtherOutDetail.getOtherOutDetail(otherOutDetailParam);


            AfterOtherOutDetailAndShlefs afterOtherOutDetailAndShlefs = new AfterOtherOutDetailAndShlefs();
            afterOtherOutDetailAndShlefs.setOtherOutDetail(otherOutDetail);


            /**
             *
             private WarehouseShelf warehouseShelf;

             * 组装货位信息
             */
            List<OtherOutShelf> otherOutShelves = new ArrayList<>();
            OtherOutShelfParam otherOutShelfParam = new OtherOutShelfParam();
            for(OtherOutShelfFormVO otherOutShelfFormVO : detailVO.getOtherOutShelfFormVOS()){


                LotNumber lot = null;
                for(LotNumber lotNumber : lotNumbers){
                    if(lotNumber.getId().equals(otherOutShelfFormVO.getLotId())){
                        lot = lotNumber;
                    }
                }

                otherOutShelfParam.setUserVO(userVO);
                otherOutShelfParam.setOtherOutDetail(otherOutDetail);
                otherOutShelfParam.setOtherOutShelfFormVO(otherOutShelfFormVO);
                otherOutShelfParam.setLotNumber(lot);
                otherOutShelfParam.setLineNum(otherOutShelfFormVO.getLineNum());
                stocks.stream().filter(stock -> stock.getId().equals(otherOutShelfFormVO.getStockId()))
                        .forEach(stock -> {
                            otherOutShelfParam.setStock(stock);
                        });

                warehouseShelves.stream().filter(shelf -> shelf.getId().equals(otherOutShelfFormVO.getShelfId()))
                        .forEach(shelf -> {
                            otherOutShelfParam.setWarehouseShelf(shelf);
                        });
                OtherOutShelf otherOutShelf = saveShelf(otherOutShelfParam);
                otherOutShelves.add(otherOutShelf);
            }


            afterOtherOutDetailAndShlefs.setOtherOutShelves(otherOutShelves);
            otherOutDetails.add(afterOtherOutDetailAndShlefs);
        }


        return otherOutDetails;
    }

    @Override
    public List<OtherOut> getOtherOuts(OtherOutListParam otherOutListParam, com.rograndec.feijiayun.chain.common.Page page){

        Map<String,Object> map = new HashMap<>();
        map.put("enterpriseId",otherOutListParam.getUserVO().getEnterpriseId());
        map.put("parentId",otherOutListParam.getUserVO().getParentId());

        if(null != otherOutListParam.getStartTime() && null != otherOutListParam.getEndTime()){
            map.put("startTime",otherOutListParam.getStartTime());
            map.put("endTime",otherOutListParam.getEndTime());
        }

        if(!StringUtils.isEmpty(otherOutListParam.getCode())){
            map.put("code",otherOutListParam.getCode());
        }

        if(otherOutListParam.getOutManName() != null){
            map.put("outManName",otherOutListParam.getOutManName());
        }

        if(otherOutListParam.getOutType() != null){
            map.put("outType",otherOutListParam.getOutType());
        }

        if(!StringUtils.isEmpty(otherOutListParam.getOrder())){
            map.put("order",otherOutListParam.getOrder());
        }
        if(!StringUtils.isEmpty(otherOutListParam.getSort())){
            map.put("sort",otherOutListParam.getSort());
        }
       Page hPage = PageHelper.startPage(page.getPageNo(),page.getPageSize());

        List<OtherOut> otherOuts = otherOutMapper.selectByParam(map);

        OtherOutPageTotalVO pageTotalVO = otherOutMapper.sumAmountTotal(map);
        if(!CollectionUtils.isEmpty(otherOuts)){
            List<OtherOutPageVO> otherOutPageVOs = OtherOutPageVO.getOtherOutPageVOs(otherOuts);
            pageTotalVO.setOtherOutPageVOS(otherOutPageVOs);
            page.setResult(pageTotalVO);
        }

        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
        page.setTotalPage(hPage.getPages());



        return otherOuts;
    }


    @Override
    public OtherOutPageVO getOtherOutPageVO(Long outId){

        OtherOut otherOut = otherOutMapper.selectByPrimaryKey(outId);

        OtherOutPageVO otherOutPageVO = OtherOutPageVO.getOtherOutPageVO(otherOut);

        List<OtherOutDetail> otherOutDetails = otherOutDetailMapper.selectByOutId(outId);

        List<Long> ids = OtherOutDetail.getIds(otherOutDetails);
        List<OtherOutShelf> otherOutShelves = otherOutShelfMapper.selectByDtlIds(ids);

        List<OtherOutDetailPageVO> otherOutDetailPageVOs = OtherOutDetailPageVO.getOtherOutDetailPageVOs(otherOutDetails, otherOutShelves);

        otherOutPageVO.setOtherOutDetailFormVOS(otherOutDetailPageVOs);

        return otherOutPageVO;
    }

    @Override
    public List<OtherOutGoodsPageVO> getGoods(UserVO userVO, String name){
        Map<String,Object> map = new HashMap<>();


        /*if(userVO.getChainType().equals(ChainType.Headquarters.getCode())) {
            map.put("enterpriseId", userVO.getEnterpriseId());
        }else {
            map.put("enterpriseId", userVO.getParentId());
        }*/
        map.put("enterpriseId", userVO.getEnterpriseId());
//        map.put("parentId",userVO.getParentId());
        if(!StringUtils.isEmpty(name))
            map.put("name",name);

        List<OtherOutGoodsPageVO> otherOutGoodsPageVOS = otherOutMapper.selectGoodsLotByParam(map);

        List<OtherOutGoodsPageVO> newOtherOutGoodsPageVOs = new ArrayList<>();

        for(OtherOutGoodsPageVO otherOutGoodsPageVO : otherOutGoodsPageVOS){

            /**
             *
             * @Title: getGoodsDefTaxRateInfo
             * @Description: 获取商品默认税率
             * @param @param enterpriseId 企业ID
             * @param @param goodsId 商品ID
             * @param @param taxRateType 税率类型
             * @param @return 设定文件
             * @return GoodsDefTaxRateVO 商品默认税率对象
             * @throws
             */
            GoodsDefTaxRateVO goodsDefTaxRateInfo = commonComponent.getGoodsDefTaxRateInfo(userVO.getEnterpriseId(), otherOutGoodsPageVO.getGoodsId(), TaxRateType.SALE.getType());


            AvgCostVO avgCostVO = commonComponent.getAvgCostVO(userVO.getEnterpriseId(), otherOutGoodsPageVO.getGoodsId(), null, BigDecimal.ONE);
            BigDecimal costPrice = avgCostVO.getAvgCostPrice();
            BigDecimal notaxCostPrice = avgCostVO.getAvgNotaxCostPrice();

            otherOutGoodsPageVO.setUnitPrice(costPrice);

            otherOutGoodsPageVO.setTaxRate(null == goodsDefTaxRateInfo.getTaxRate() ? BigDecimal.ZERO : goodsDefTaxRateInfo.getTaxRate());
            otherOutGoodsPageVO.setTaxRateId(null == goodsDefTaxRateInfo.getTaxRateId() ? 0L :  goodsDefTaxRateInfo.getTaxRateId());

            BigDecimal amount = otherOutGoodsPageVO.getQuantity().multiply(costPrice).setScale(2, RoundingMode.HALF_UP);
            otherOutGoodsPageVO.setAmount(amount);

            BigDecimal notaxRealAmount = notaxCostPrice.multiply(otherOutGoodsPageVO.getQuantity()).setScale(2, RoundingMode.HALF_UP);
            otherOutGoodsPageVO.setNotaxRealPrice(notaxCostPrice);
            otherOutGoodsPageVO.setNotaxRealAmount(notaxRealAmount);


            /**
             *
             * @Title: getTaxAmountByRealAmountAndNotaxAmount
             * @Description: 根据实际金额和不含税金额获取税额：金额-不含税金额
             * @param @param realAmount 实际金额
             * @param @param notaxAmount 不含税金额
             * @param @return
             * @return BigDecimal 返回类型
             * @throws
             */
            BigDecimal taxAmount = CalculateComponent.getTaxAmountByRealAmountAndNotaxAmount(otherOutGoodsPageVO.getAmount(), otherOutGoodsPageVO.getNotaxRealAmount());
            otherOutGoodsPageVO.setTaxAmount(taxAmount);


            List<WarehouseShelfDefVO> defShefs = commonComponent.getDefShef(otherOutGoodsPageVO.getGoodsId(), userVO.getEnterpriseId(), otherOutGoodsPageVO.getLotId(), BigDecimal.ONE);

            for(WarehouseShelfDefVO vo : defShefs){
                OtherOutGoodsPageVO newVo = new OtherOutGoodsPageVO();
                BeanUtils.copyProperties(otherOutGoodsPageVO,newVo);

                newVo.setShelfId(vo.getShelfId());
                newVo.setShelfName(vo.getName());
                Stock stock = vo.getStock();
                newVo.setStockId(stock.getId());
                newVo.setShelfStatusDesc(vo.getShelfStatusDesc());
                newVo.setUsableQuantity(stock.getUsableQuantity());
                newOtherOutGoodsPageVOs.add(newVo);
            }

        }


        return newOtherOutGoodsPageVOs;
    }

    @Override
    public void export(OutputStream output, Long outId, UserVO loginUser) {

        OtherOutPageVO otherOutPageVO = getOtherOutPageVO(outId);

        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("goodsCode","商品编码");
        map.put("goodsGenericName","通用名称");
        map.put("dosageName","剂型");
        map.put("goodsSpecification","规格");
        map.put("manufacturer","生产厂商");
        map.put("unitName","单位");
        map.put("lotNumber","批号");
        map.put("productDate","生产日期");
        map.put("validDate","有效期至");
        map.put("shelfName","货位");

        map.put("quantity","数量");
        map.put("unitPrice","单价");
        map.put("amount","金额");

        map.put("taxRate","税率");
        map.put("notaxPrice","不含税单价");
        map.put("notaxAmount","不含税金额");
        map.put("taxAmount","税额");
        map.put("shelfStatusDesc","质量状况");
        map.put("remark","备注");
        List<OtherOutDetailPageVO> otherInDetailVOList = otherOutPageVO.getOtherOutDetailFormVOS();
        List<OtherOutExcelVO> otherInDetailExcelVOList = new ArrayList<OtherOutExcelVO>();
        for (OtherOutDetailPageVO otherOutDetailPageVO : otherInDetailVOList) {
            List<OtherOutShelfPageVO> otherOutShelfPageVOS = otherOutDetailPageVO.getOtherOutShelfFormVOS();
            for(OtherOutShelfPageVO otherOutShelfPageVO : otherOutShelfPageVOS){
                OtherOutExcelVO vo = OtherOutExcelVO.convertToVO(otherOutDetailPageVO,otherOutShelfPageVO);
                otherInDetailExcelVOList.add(vo);
            }

        }
        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("出库单号：");
        titleSecondRow.append(!StringUtils.isEmpty(otherOutPageVO.getCode()) ? otherOutPageVO.getCode() : "");
        titleSecondRow.append("     ");
        titleSecondRow.append("出库日期：");
        titleSecondRow.append(null != otherOutPageVO.getOutDate() ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(otherOutPageVO.getOutDate()) : "");
        titleSecondRow.append("     ");
        titleSecondRow.append("出库人员：");
        titleSecondRow.append(!StringUtils.isEmpty(otherOutPageVO.getOutManName()) ? otherOutPageVO.getOutManName() : "");
        titleSecondRow.append("     ");
        titleSecondRow.append("出库类型：");
        titleSecondRow.append(null != otherOutPageVO.getOutType() ? OtherOutType.getValue(otherOutPageVO.getOutType()) : "");
        titleSecondRow.append("     ");
        titleSecondRow.append("流向单位：");
        titleSecondRow.append(!StringUtils.isEmpty(otherOutPageVO.getFlowUnitName()) ? otherOutPageVO.getFlowUnitName() : "");
        titleSecondRow.append("     ");
        titleSecondRow.append("流通监管码:");
        titleSecondRow.append(!StringUtils.isEmpty(otherOutPageVO.getFlowCode()) ? otherOutPageVO.getFlowCode() : "");
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("其他出库");
        List<String> secondTitle = new ArrayList<>();
        secondTitle.add(titleSecondRow.toString());
        purchaseGeneralComponent.commExcelExport(output,map,otherInDetailVOList,name,secondTitle,"",true,new ArrayList<>());

    }

    @Override
    public List<StockAndShelfPageVO> getStockAndShelfPageVOs(UserVO userVO, Long lotId, Long goodsId){

        Map<String,Object> map = new HashMap<>();
        map.put("enterpriseId",userVO.getEnterpriseId());
        map.put("lotId",lotId);
        map.put("goodsId",goodsId);

        List<StockAndShelfPageVO> stockAndShelfPageVOS = otherOutMapper.selectStockByGoodsAndShelf(map);

        return stockAndShelfPageVOS;
    }
}
