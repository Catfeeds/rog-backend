package com.rograndec.feijiayun.chain.business.storage.move.service.impl;

import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserAdministrationMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserAdministration;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseCargoAreaMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseCargoArea;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.CargoQualityStateVO;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsBusinessMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.entity.GoodsBusiness;
import com.rograndec.feijiayun.chain.business.goods.manage.dao.SafetyStockMapper;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.keytable.dao.CostMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.Cost;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleShelfMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.Sale;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.SaleShelf;
import com.rograndec.feijiayun.chain.business.storage.move.dao.OtherInDetailMapper;
import com.rograndec.feijiayun.chain.business.storage.move.dao.OtherInMapper;
import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherIn;
import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherInDetail;
import com.rograndec.feijiayun.chain.business.storage.move.service.OtherInService;
import com.rograndec.feijiayun.chain.business.storage.move.vo.*;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatus;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.business.system.approval.vo.SubmitApprovalFlowVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.dao.DepartmentMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SelectBeanWithCode;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.CargoAreaWorkingArea;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.OrderDirection;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.model.OrderModel;
import com.rograndec.feijiayun.chain.common.vo.LastInPriceVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import com.sun.net.httpserver.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class OtherInServiceImpl implements OtherInService{

    @Autowired
    private OtherInMapper otherInMapper;

    @Autowired
    private OtherInDetailMapper otherInDetailMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private WarehouseShelfMapper warehouseShelfMapper;

    @Autowired
    private WarehouseCargoAreaMapper warehouseCargoAreaMapper;

    @Autowired
    private GoodsTaxRateMapper goodsTaxRateMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Autowired
    private CostMapper costMapper;

    @Autowired
    private SafetyStockMapper safetyStockMapper;

    @Autowired
    private CommonComponent commonComponent;

    @Autowired
    private InOutComponent inOutComponent;

    @Autowired
    private SaleMapper saleMapper;

    @Autowired
    private SaleShelfMapper saleShelfMapper;

    @Autowired
    private ApprovalFlowComponent approvalFlowComponent;

    @Autowired
    private UserAdministrationMapper userAdministrationMapper;

    @Autowired
    private GoodsBusinessMapper goodsBusinessMapper;

    @Autowired
    private FinanceComponent financeComponent;

    @Override
    public Page getOtherInPage(Page page, Date startTime, Date endTime, String code, String inManName, Integer inType, String order, String sort, Long enterpriseId,Integer approveStatus) {
        if("inDate".equals(order)){
            order = "in_date";
        }else if("code".equals(order)){
            order = "code";
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("code",code);
        map.put("inManName",inManName);
        map.put("inType",inType);
        map.put("order",order);
        map.put("sort",sort);
        map.put("enterpriseId",enterpriseId);
        if(null != approveStatus){
            map.put("approveStatus",approveStatus);
        }
        map.put("pageSize",page.getPageSize());
        map.put("start",(page.getPageNo()-1)*page.getPageSize());
        OtherInStasticVO stasticVO = otherInMapper.selectStastic(map);
        List<OtherIn> list = otherInMapper.getOtherInPage(map);
        List<OtherInPageVO> pageVOList = new ArrayList<OtherInPageVO>();
        for (OtherIn s:list) {
            OtherInPageVO vo = OtherInPageVO.convertToPageVO(s);
            if (stasticVO == null){
                //塞入金额统计
                vo.setStasticAmountTotal(BigDecimal.ZERO);
                //塞入不含税金额统计
                vo.setStasticNotaxAmountTotal(BigDecimal.ZERO);
                //塞入税额金额统计
                vo.setStasticTaxAmountTotal(BigDecimal.ZERO);
            }else {
                //塞入金额统计
                vo.setStasticAmountTotal(stasticVO.getStasticAmountTotal() == null ? BigDecimal.ZERO : stasticVO.getStasticAmountTotal());
                //塞入不含税金额统计
                vo.setStasticNotaxAmountTotal(stasticVO.getStasticNotaxAmountTotal() == null ? BigDecimal.ZERO : stasticVO.getStasticNotaxAmountTotal());
                //塞入税额金额统计
                vo.setStasticTaxAmountTotal(stasticVO.getStasticTaxAmountTotal() == null ? BigDecimal.ZERO : stasticVO.getStasticTaxAmountTotal());
            }
            Integer status = vo.getApproveStatus();
            if(null != status){
                ApprovalFlowAuditStatusRecom approvalStatus = ApprovalFlowAuditStatusRecom.getApprovalFlowAuditStatusRecomEnum(status);
                vo.setApproveStatusDesc(null == approvalStatus ? "" : approvalStatus.getName());
            }

            pageVOList.add(vo);
        }
        page.setResult(pageVOList);
        Integer totalRecord = otherInMapper.getTotalRecord(map);
        page.setTotalRecord(totalRecord);
        return page;
    }

    @Override
    public OtherInFormVO getOtherInDetail(Long enterpriseId, Long id) {
        OtherIn otherIn = otherInMapper.selectByPrimaryKey(id);
        OtherInFormVO formVO = new OtherInFormVO();
        formVO = OtherInFormVO.convertToVO(otherIn);
        //开始插入明细
        List<OtherInDetailVO> detailVOList = new ArrayList<OtherInDetailVO>();
        List<OtherInDetail> detailList = otherInDetailMapper.selectByEnterpriseIdAndInId(enterpriseId,id);
        for (OtherInDetail o:detailList) {
            OtherInDetailVO detailVO = new OtherInDetailVO();
            detailVO = OtherInDetailVO.converToVO(o);
            detailVOList.add(detailVO);
        }
        formVO.setOtherInDetailVOList(detailVOList);
        return formVO;
    }

    @Override
    public List<SelectBeanWithCode> getSrcUnit(UserVO loginUser, Integer srcUnitType) {
        /**
             1.部门
             总部和自营店显示总部的部门
             加盟店显示加盟店的部门
             2.总部
             显示门店所属的总部，只读
             3.门店
             总部选择所有门店
             自营店选择所属总部的所有自营单
             加盟店显示加盟店自己门店，只读
             4.供货单位
             总部和自营店显示总部添加的供货单位
             加盟店显示加盟店添加的供货单位
         */
        List<SelectBeanWithCode> list = new ArrayList<SelectBeanWithCode>();
        Long enterpriseId = loginUser.getEnterpriseId();
        switch (srcUnitType){
            //部门
            case 0:
                if (loginUser.getChainType().equals(ChainType.Selfoperatedshop.getCode())){
                    enterpriseId = loginUser.getParentId();
                }
                List<Department> departments = departmentMapper.selectByEnterpriseIdAndDefut(enterpriseId);
                for (Department d:departments) {
                    SelectBeanWithCode n = new SelectBeanWithCode();
                    n.setId(d.getId());
                    n.setCode(d.getCode());
                    n.setName(d.getName());
                    list.add(n);
                }
                break;
            //总部
            case 1:
                if (!loginUser.getChainType().equals(ChainType.Headquarters.getCode())){
                    enterpriseId = loginUser.getParentId();
                }
                List<Enterprise> enterprises = enterpriseMapper.selectByEnterpriseIdWithChainTypeAndDefut(enterpriseId);
                for (Enterprise e:enterprises) {
                    SelectBeanWithCode n = new SelectBeanWithCode();
                    n.setId(e.getId());
                    n.setCode(e.getCode());
                    n.setName(e.getName());
                    list.add(n);
                }
                break;
            //门店
            case 2:
                /*总部选择所有门店
                 自营店选择所属总部的所有自营单
                 加盟店显示加盟店自己门店，只读
                 */
                if (!loginUser.getChainType().equals(ChainType.Headquarters.getCode())){
                    enterpriseId = loginUser.getParentId();
                }
                List<Enterprise> enterpriseSon = enterpriseMapper.selectByEnterpriseIdWithChainTypeAndDefutSon(enterpriseId);
                enterpriseSon.forEach(e ->{
                    SelectBeanWithCode n = new SelectBeanWithCode();
                    if (loginUser.getChainType().equals(ChainType.Headquarters.getCode())){
                        n.setId(e.getId());
                        n.setCode(e.getCode());
                        n.setName(e.getName());
                        list.add(n);
                    }else if (loginUser.getChainType().equals(ChainType.Selfoperatedshop.getCode())){
                        if (e.getChainType().equals(ChainType.Selfoperatedshop.getCode())){
                            n.setId(e.getId());
                            n.setCode(e.getCode());
                            n.setName(e.getName());
                            list.add(n);
                        }
                    }else if (loginUser.getChainType().equals(ChainType.Division.getCode())){
                        /**
                         * 加盟店显示自己店
                         */
                        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(loginUser.getEnterpriseId());
                        if (enterprise.getId().equals(e.getId())){
                            n.setId(e.getId());
                            n.setCode(e.getCode());
                            n.setName(e.getName());
                            list.add(n);
                        }
                    }
                });
                break;
            //供货单位
            case 3:
                Integer chainType = loginUser.getChainType();
                List<Supplier> supplierList = new ArrayList<>();
                List<Long> paramList = new ArrayList<>();
                if(ChainType.Division.getCode() == chainType){
                    //加盟店
                    paramList.add(loginUser.getEnterpriseId());
                    supplierList = supplierMapper.selectByEnterpriseIdAndOwnerId(loginUser.getParentId(),paramList,ApprovalFlowAuditStatus.DETAIL_STATUS_PASS.getValue());
                } else if(ChainType.Headquarters.getCode() == chainType){
                    //总部
                    supplierList = supplierMapper.selectByEnterpriseId(loginUser.getEnterpriseId());
                } else if(ChainType.Selfoperatedshop.getCode() == chainType){
                    //自营店
                    supplierList = supplierMapper.selectByEnterpriseIdAndOwnerId(loginUser.getParentId(),Arrays.asList(loginUser.getParentId()),ApprovalFlowAuditStatus.DETAIL_STATUS_PASS.getValue());
                }
                for (Supplier s:supplierList) {
                    SelectBeanWithCode n = new SelectBeanWithCode();
                    n.setId(s.getId());
                    n.setCode(s.getCode());
                    n.setName(s.getName());
                    list.add(n);
                }
                break;
        }
        return list;
    }

    @Override
    public List<OtherInDetailVO> getGoodsList(UserVO loginUser, String operation, String param) throws InterruptedException {

        List<OtherInDetailVO> list = new ArrayList<OtherInDetailVO>();
        List<OtherInDetailVO> returnList = new ArrayList<OtherInDetailVO>();
        Long enterpriseId = (loginUser.getChainType() == ChainType.Headquarters.getCode() ? loginUser.getEnterpriseId() : loginUser.getParentId());
        Long divisionId=null;
        if(loginUser.getChainType().equals(ChainType.Division.getCode())){
        	divisionId=loginUser.getEnterpriseId();
        }
        Long selfShopId = null;
        if(loginUser.getChainType().equals(ChainType.Selfoperatedshop.getCode())){
            selfShopId=loginUser.getEnterpriseId();
        }
        if ("add".equals(operation)){

            List<OtherInDetailVO> goodsList  = otherInMapper.selectGoodsByAdd(selfShopId,divisionId,enterpriseId,null,param);

            if(CollectionUtils.isEmpty(goodsList)){
                return  new ArrayList<OtherInDetailVO>();
            }

            List<Long> goodsIds = OtherInDetailVO.getGoodsIds(goodsList);

            List<Long> shelfIds = OtherInDetailVO.getShelfIds(goodsList);

            ExecutorService executorService = Executors.newFixedThreadPool(3);

            /**
             * 如果是总部人员税率取得是进项税 || 如果是分部人员税率取的是配货税
             * 异步调用
             */
            Runnable syncRunnable = new Runnable(){

                @Override
                public void run() {
                    if (loginUser.getChainType() != ChainType.Headquarters.getCode()){


                        Map<String,Object> map = new HashMap<>(2);
                        map.put("enterpriseId",enterpriseId);
                        map.put("list",goodsIds);


                        List<GoodsBusiness> goodsBusiness = goodsBusinessMapper.selectByEnterpriseIdAndGoodsIds(map);

                        for (OtherInDetailVO o:goodsList) {
                            for(GoodsBusiness g : goodsBusiness){
                                if(o.getGoodsId().equals(g.getGoodsId())){
                                    o.setTaxRateId(g.getDistrTaxRateId());
                                    o.setTaxRate(g.getDistrTaxRate());
                                }
                            }
                        }
                    }
                }
            };

            executorService.execute(syncRunnable);


            Runnable syncRunnable1 = new Runnable(){

                @Override
                public void run() {

                    List<WarehouseShelf> warehouseShelfs = warehouseShelfMapper.selectByIds(shelfIds);

                    Map<Long, Long> shelfAndWarehoseShelfMap = warehouseShelfs.stream().collect(Collectors.toMap(whs -> whs.getId(), whs -> whs.getCargoAreaId()));

                    List<Long> cargoAreaIds = WarehouseShelf.getCargoAreaIds(warehouseShelfs);

                    if(!CollectionUtils.isEmpty(cargoAreaIds)){
                        List<WarehouseCargoArea> WarehouseCargoAreas = warehouseCargoAreaMapper.selectByIds(cargoAreaIds);

                        for(OtherInDetailVO o : goodsList){

                            Long cargoAreaId = shelfAndWarehoseShelfMap.get(o.getShelfId());
                            for(WarehouseCargoArea wc : WarehouseCargoAreas) {
                                Integer targetJobArea = wc.getJobArea();
                                if(wc.getId().equals(cargoAreaId)){
                                    if (targetJobArea == 0){
                                        o.setShelfStatusDesc("合格品");
                                    }else if (targetJobArea == 2){
                                        o.setShelfStatusDesc("不合格品");
                                    }
                                }
                            }

                        }

                    }

                }
            };

            executorService.execute(syncRunnable1);


            Runnable syncRunnable2 = new Runnable(){

                @Override
                public void run() {
                    List<Cost> costs = costMapper.selectMaxId(goodsIds,loginUser.getEnterpriseId());
                    for(Cost cost : costs){
                        for(OtherInDetailVO o : goodsList){
                            if(cost.getGoodsId().equals(o.getGoodsId())){
                                if (cost == null){
                                    o.setUnitPrice(new BigDecimal(0));
                                }else{
                                    o.setUnitPrice(cost.getRealPrice());
                                }
                            }

                            /**
                             * 因为安全库存里没有数据货位ID + 货位名称都为0，所以我要过滤不穿给前台
                             */
                            if (null != o.getShelfId() && (0 == o.getShelfId() || "0".equals(o.getShelfName()))){
                                o.setShelfId(null);
                            }
                            //将每一项的默认数量置为1
                            o.setQuantity(new BigDecimal(1));
                        }

                    }

                }

            };
            executorService.execute(syncRunnable2);

            // 启动一次顺序关闭，执行以前提交的任务，但不接受新任务。
            executorService.shutdown();
            // awaitTermination返回false即超时会继续循环，返回true即线程池中的线程执行完成主线程跳出循环往下执行，每隔1秒循环一次
            while (!executorService.awaitTermination(1, TimeUnit.SECONDS));

            return goodsList;

        }else if ("over".equals(operation)){
            //超量销售
            //用于记录外层单独的数据
            HashSet set = new HashSet();
            /**
             * 超量销售只能是分店有销售单据
             */
            Long minorEnterpriseId = loginUser.getEnterpriseId();
            list = otherInMapper.selectGoodsByOver(minorEnterpriseId,param);
            if (list != null && list.size() > 0){
                for (OtherInDetailVO detailVO : list) {
                    StringBuilder uId = new StringBuilder();
                    uId.append(detailVO.getEnterPriseId());
                    uId.append(detailVO.getGoodsId());
                    uId.append(detailVO.getLotId());
                    uId.append(detailVO.getShelfId());
                    set.add(uId.toString());
                }
            }
            
            /**
             * 记录数
             */
            int size = set.size();
            /**
             * 用于返回的超量销售list
             */
            if (size > 0){
                for (Object s : set) {
                    List<String> saleCode = new ArrayList<String>();
                    List<BigDecimal> saleQuantity = new ArrayList<BigDecimal>();
                    OtherInDetailVO newVO = new OtherInDetailVO();
                    if (list != null && list.size() > 0){
                        for (OtherInDetailVO detailVO : list) {
                            StringBuilder uId = new StringBuilder();
                            uId.append(detailVO.getEnterPriseId());
                            uId.append(detailVO.getGoodsId());
                            uId.append(detailVO.getLotId());
                            uId.append(detailVO.getShelfId());
                            if (s.toString().equals(uId.toString())){
                                Long id = detailVO.getId();
                                SaleShelf saleShelf = saleShelfMapper.selectByPrimaryKey(id);
                                if (saleShelf != null){
                                    Long saleId = saleShelf.getSaleId();
                                    Sale sale = saleMapper.selectByPrimaryKey(saleId);
                                    if (sale != null){
                                        saleCode.add(sale.getCode());
                                    }
                                }
                                saleQuantity.add(detailVO.getQuantity());
                                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(detailVO,newVO);
                            }
                        }
                    }
                    /**
                     * 记录销售单号和销售数量
                     */
                    newVO.setSaleCode(saleCode);
                    newVO.setSaleQuantity(saleQuantity);
                    /**
                     * 当是分店人员登入 tips：带过去的税率默认配货税
                     */
                    if (loginUser.getChainType() != ChainType.Headquarters.getCode()){
                        Map<String,Object> map = new HashMap<>(2);
                        map.put("enterpriseId",enterpriseId);
                        map.put("goodsId",newVO.getGoodsId());
                        GoodsBusiness goodsBusiness = goodsBusinessMapper.selectByEnterpriseIdAndGoodsId(map);
                        if (goodsBusiness != null){
                            newVO.setTaxRateId(goodsBusiness.getDistrTaxRateId());
                            newVO.setTaxRate(goodsBusiness.getDistrTaxRate());
                        }
                    }
                    /**
                     * 查询成本表最近一次入库单价 tips：如果没有那么就赋值为0
                     */
                    //取成本表当前商品同一批号的最新一次的实际单价（带到前台默认显示）
                    Cost cost = costMapper.selectByGoodIdLotIdEnterpriseId(newVO.getGoodsId(),newVO.getLotId(),minorEnterpriseId);
                    if (cost == null){
                        newVO.setUnitPrice(BigDecimal.ZERO);
                    }else {
                        newVO.setUnitPrice(cost.getRealPrice());
                    }
                    /**
                     * 设置差异数量
                     */
                    BigDecimal distanceQuantity = BigDecimal.ZERO;
                    for (BigDecimal d : saleQuantity) {
                        distanceQuantity = distanceQuantity.add(d);
                    }
                    distanceQuantity = distanceQuantity.subtract(newVO.getUsableQuantity());
                    newVO.setDifferencesQuantity(distanceQuantity);
                    //设置质量状况
                    CargoQualityStateVO cargo = warehouseCargoAreaMapper.getCargoByShelfId(newVO.getShelfId());
                    newVO.setShelfStatusDesc(cargo == null ? "" : (0 == cargo.getJobArea() ? "合格品" : (2 == cargo.getJobArea() ? "不合格品" : "")));
                    //当销售数量的和大于可用数量才显示
                    if (distanceQuantity.compareTo(BigDecimal.ZERO) == 1){
                        returnList.add(newVO);
                    }
                }
            }
            list = returnList;
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertOtherIn(UserVO loginUser, OtherInFormVO otherInFormVO) throws Exception{
        OtherIn oi = new OtherIn();
        //生成的入库单号
        String code = orderCodeComponent.
                generate(OrderRule.RECEIVE.getCodePrefix(),loginUser.getEnterpriseId(),loginUser.getEnterpriseCode());
        oi.setEnterpriseId(loginUser.getEnterpriseId());
        oi.setParentId(loginUser.getParentId());
        oi.setOrderType(OrderRule.RECEIVE.getType());
        oi.setCode(code);
        oi.setInDate(otherInFormVO.getInDate());
        if (otherInFormVO.getInManId() == null){
            oi.setInManId(loginUser.getUserId());
            oi.setInManCode(loginUser.getUserCode());
            oi.setInManName(loginUser.getUserName());
        }else {
            oi.setInManId(otherInFormVO.getInManId());
            User userIn = userMapper.getUserByIdAndEnterpriseId(otherInFormVO.getInManId(),loginUser.getEnterpriseId());
            oi.setInManCode(userIn.getCode());
            oi.setInManName(userIn.getName());
        }
        oi.setInType(otherInFormVO.getInType());
        oi.setSrcUnitType(otherInFormVO.getSrcUnitType());
        oi.setExcessiveSaleCodes(otherInFormVO.getExcessiveSaleCodes());
        if (otherInFormVO.getSrcUnitId() != null){
            switch(otherInFormVO.getSrcUnitType()){
                case 0:
                    //部门
                    Department department = departmentMapper.selectByPrimaryKey(otherInFormVO.getSrcUnitId());
                    if (department != null){
                        oi.setSrcUnitId(department.getId());
                        oi.setSrcUnitCode(department.getCode());
                        oi.setSrcUnitName(department.getName());
                    }
                    break;
                case 1:
                    //总部

                case 2:
                    Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(otherInFormVO.getSrcUnitId());
                    if (enterprise != null){
                        oi.setSrcUnitId(enterprise.getId());
                        oi.setSrcUnitCode(enterprise.getCode());
                        oi.setSrcUnitName(enterprise.getName());
                    }
                    //门店
                    break;
                case 3:
                    //供货单位
                    Supplier supplier = supplierMapper.selectByPrimaryKey(otherInFormVO.getSrcUnitId());
                    if (supplier != null){
                        oi.setSrcUnitId(supplier.getId());
                        oi.setSrcUnitCode(supplier.getCode());
                        oi.setSrcUnitName(supplier.getName());
                    }
                    break;
                default:
                    break;
            }
        }
        if (otherInFormVO.getFlowCode() != null){
            oi.setFlowCode(otherInFormVO.getFlowCode());
        }
        //数量+品种+金额+不含税金额+税额合计之类都是后期计算先设置为0
        oi.setQuantityTotal(BigDecimal.ZERO);
        oi.setVarietiesQuantity(0);
        oi.setAmountTotal(BigDecimal.ZERO);
        oi.setNotaxAmountTotal(BigDecimal.ZERO);
        oi.setTaxAmountTotal(BigDecimal.ZERO);
        BigDecimal quantityTotal = BigDecimal.ZERO;
        Set set = new HashSet();
        BigDecimal amountTotal = BigDecimal.ZERO;
        BigDecimal notaxAmountTotal = BigDecimal.ZERO;
        BigDecimal taxAmountTotal = BigDecimal.ZERO;
        oi.setStatus(ApprovalFlowAuditStatus.DETAIL_STATUS_WAIT.getValue());
        //插入其他入库主单
        UserEnterpriseUtils.setUserCreateOrModify(oi,loginUser,true);
        otherInMapper.insertSelective(oi);
        //构建明细单####################并插入
        if (otherInFormVO.getOtherInDetailVOList() != null){
            List<OtherInDetailVO> otherInDetailVOList = otherInFormVO.getOtherInDetailVOList();
            for (OtherInDetailVO o:otherInDetailVOList) {
                OtherInDetail otherInDetail = new OtherInDetail();
                otherInDetail.setEnterpriseId(loginUser.getEnterpriseId());
                otherInDetail.setParentId(loginUser.getParentId());
                otherInDetail.setInId(oi.getId());
                otherInDetail.setOrderType(OrderRule.RECEIVE.getType());
                otherInDetail.setInCode(code);
                otherInDetail.setInDate(oi.getInDate());
                otherInDetail.setGoodsId(o.getGoodsId());
                set.add(o.getGoodsId());
                //##########下面是通过商品ID查出插入的信息
                Goods goods = goodsMapper.selectByPrimaryKey(o.getGoodsId());
                otherInDetail.setGoodsCode(goods.getCode());
                otherInDetail.setBarcode(goods.getBarcode());
                otherInDetail.setGoodsName(goods.getName());
                otherInDetail.setGoodsGenericName(goods.getGenericName());
                otherInDetail.setDosageId(goods.getDosageId());
                otherInDetail.setDosageName(goods.getDosageName());
                otherInDetail.setUnitId(goods.getUnitId());
                otherInDetail.setUnitName(goods.getUnitName());
                otherInDetail.setGoodsSpecification(goods.getSpecification());
                otherInDetail.setManufacturerId(goods.getManufacturerId());
                otherInDetail.setManufacturer(goods.getManufacturer());
                otherInDetail.setGoodsPlace(goods.getPlace());
                otherInDetail.setApprovalNumber(goods.getApprovalNumber());
                //接受前台的数据 + 必填
                otherInDetail.setLotNumber(o.getLotNumber());
                otherInDetail.setProductDate(o.getProductDate());
                otherInDetail.setValidDate(o.getValidDate());
                otherInDetail.setShelfId(o.getShelfId());
                if (o.getLotNumber() == null){
                    throw new BusinessException(SysCode.FAIL.getCode(),o.getGoodsName() + "的批号必填！");
                }
                if (o.getProductDate() == null){
                    throw new BusinessException(SysCode.FAIL.getCode(),o.getGoodsName() + "的生产日期必填！");
                }
                if (o.getValidDate() == null){
                    throw new BusinessException(SysCode.FAIL.getCode(),o.getGoodsName() + "的有效期至必填！");
                }
                if (o.getShelfId() == null){
                    throw new BusinessException(SysCode.FAIL.getCode(),o.getGoodsName() + "的货位必填！");
                }
                if (o.getQuantity() == null){
                    throw new BusinessException(SysCode.FAIL.getCode(),o.getGoodsName() + "的数量必填！");
                }
                if (o.getUnitPrice() == null){
                    throw new BusinessException(SysCode.FAIL.getCode(),o.getGoodsName() + "的单价必填！");
                }
                /**
                 * 生产日期不能大于有效期至 && 生产日期不能大于当前日期
                 */
                if (o.getProductDate() != null && o.getValidDate() != null){
                    if (o.getProductDate().after(o.getValidDate())){
                        throw new BusinessException(SysCode.FAIL.getCode(),o.getGoodsName() + "的生产日期不能超过有效期至！");
                    }
                }
                if (o.getProductDate() != null){
                    if (o.getProductDate().after(new Date())){
                        throw new BusinessException(SysCode.FAIL.getCode(),o.getGoodsName() + "的生产日期不能超过今天的日期！");
                    }
                }
                //获取货位
                WarehouseShelf warehouseShelf = warehouseShelfMapper.selectByPrimaryKey(o.getShelfId());
                if(warehouseShelf == null){
                    throw new BusinessException("根据货位ID[" + o.getShelfId() + "]查询的货位不存在！");
                }
                otherInDetail.setShelfName(warehouseShelf.getName());
                //获取源货位状态描述
                WarehouseCargoArea srcWarehouseCargoArea = warehouseCargoAreaMapper.selectByPrimaryKey(warehouseShelf.getCargoAreaId());
                if (srcWarehouseCargoArea != null){
//                    Integer srcJobArea = srcWarehouseCargoArea.getJobArea();
//                    if (srcJobArea == CargoAreaWorkingArea.UALIFIED_AREA.getCode()){
//                        otherInDetail.setShelfStatusDesc("合格品");
//                    }else if (srcJobArea == CargoAreaWorkingArea.UNQUALIFIED_AREA.getCode()){
//                        otherInDetail.setShelfStatusDesc("不合格品");
//                    } else if (srcJobArea == CargoAreaWorkingArea.PENDING_AREA.getCode()){
//
//                    }

                    String shelfStatusDesc = CargoAreaWorkingArea.getName(srcWarehouseCargoArea.getJobArea());
                    otherInDetail.setShelfStatusDesc(shelfStatusDesc);
                }
                otherInDetail.setQuantity(o.getQuantity());
                quantityTotal = quantityTotal.add(o.getQuantity());
                otherInDetail.setUnitPrice(o.getUnitPrice());
                otherInDetail.setAmount(o.getAmount());
                amountTotal = amountTotal.add(o.getAmount());
                otherInDetail.setTaxRateId(o.getTaxRateId());
                GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(o.getTaxRateId());
                otherInDetail.setTaxRate(goodsTaxRate.getTaxRate());
                //计算金额的方法
                //getNotaxPriceByNotaxAmountAndQuantity
                //根据实际金额和税率获取不含税金额：金额/(1+税率)
                //* @param @param realAmount 实际金额
	            //* @param @param taxRate 税率
                BigDecimal notaxAmountByRealAmountAndTaxRate = CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(o.getAmount(), goodsTaxRate.getTaxRate());
                //* @Description: 根据不含税金额和数量获取不含税单价：金额/(1+税率)
	            //* @param @param notaxAmount 不含税金额
	            //* @param @param quantity 数量
                BigDecimal notaxPriceByNotaxAmountAndQuantity = CalculateComponent.getNotaxPriceByNotaxAmountAndQuantity(notaxAmountByRealAmountAndTaxRate, o.getQuantity());
                //* @Description: 根据实际金额和不含税金额获取税额：金额-不含税金额
	            //* @param @param realAmount 实际金额
	            //* @param @param notaxAmount 不含税金额
                BigDecimal taxAmountByRealAmountAndNotaxAmount = CalculateComponent.getTaxAmountByRealAmountAndNotaxAmount(o.getAmount(), notaxAmountByRealAmountAndTaxRate);
                otherInDetail.setNotaxPrice(notaxPriceByNotaxAmountAndQuantity);
                otherInDetail.setNotaxAmount(notaxAmountByRealAmountAndTaxRate);
                notaxAmountTotal = notaxAmountTotal.add(notaxAmountByRealAmountAndTaxRate);
                otherInDetail.setTaxAmount(taxAmountByRealAmountAndNotaxAmount);
                taxAmountTotal = taxAmountTotal.add(taxAmountByRealAmountAndNotaxAmount);
                otherInDetail.setLineNum(o.getLineNum());
                //预留状态后期用
                otherInDetail.setStatus(1);
                otherInDetail.setRemark(o.getRemark() == null ? "":o.getRemark());
                UserEnterpriseUtils.setUserCreateOrModify(otherInDetail,loginUser,true);
                otherInDetailMapper.insertSelective(otherInDetail);
                //更新安全库存
                //通过商品ID企业ID货位ID
                /*SafetyStock safetyStock = safetyStockMapper.selectByGoodsIdAndEnterpriseId(o.getGoodsId(),loginUser.getEnterpriseId());
                if (safetyStock.getDefaultShelfId() != o.getShelfId()){
                    SafetyStock newSafeStock = new SafetyStock();
                    newSafeStock.setId(safetyStock.getId());
                    newSafeStock.setDefaultShelfId(o.getShelfId());
                    WarehouseShelf warehouseShelfSafe = warehouseShelfMapper.selectByPrimaryKey(o.getShelfId());
                    newSafeStock.setDefaultShelfName(warehouseShelfSafe.getName());
                    safetyStockMapper.updateByPrimaryKeySelective(newSafeStock);
                    UserEnterpriseUtils.setUserCreateOrModify(newSafeStock,loginUser,false);
                }*/
                WarehouseShelf warehouseShelfSafe = warehouseShelfMapper.selectByPrimaryKey(o.getShelfId());
                commonComponent.updateGoodsDefShelf(loginUser.getEnterpriseId(),loginUser.getParentId(),loginUser.getChainType(),o.getGoodsId(),o.getShelfId(),warehouseShelfSafe.getName(),loginUser);

            }
        }
        //修改总单金额信息
        OtherIn newOi = new OtherIn();
        newOi.setId(oi.getId());
        newOi.setQuantityTotal(quantityTotal);
        newOi.setVarietiesQuantity(set.size());
        newOi.setAmountTotal(amountTotal);
        newOi.setNotaxAmountTotal(notaxAmountTotal);
        newOi.setTaxAmountTotal(taxAmountTotal);
        otherInMapper.updateByPrimaryKeySelective(newOi);

        OtherIn otherIn = otherInMapper.selectByPrimaryKey(oi.getId());
        //财务通用接口
        financeComponent.otherInToBalanceAndVoucher(loginUser,otherIn);
        //审批流添加
        Integer chainType = loginUser.getChainType();
        SubmitApprovalFlowVO submitApprovalFlowVO = new SubmitApprovalFlowVO(loginUser.getEnterpriseId(),loginUser.getEnterpriseName(),
                loginUser.getUserId(), loginUser.getUserName(), loginUser.getChainType(), loginUser.getParentId(),
                chainType.equals(ChainType.Headquarters.getCode()) ? loginUser.getEnterpriseId() : loginUser.getParentId(),
                "0402", otherIn.getId(), otherIn.getCode(),loginUser.getEnterpriseName()+"店的其他入库");
        approvalFlowComponent.apply(submitApprovalFlowVO,loginUser);
    }

    @Override
    public void export(OutputStream output, OtherInFormVO otherInFormVO, UserVO loginUser) {
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
        List<OtherInDetailVO> otherInDetailVOList = otherInFormVO.getOtherInDetailVOList();
        List<OtherInExcelVO> otherInDetailExcelVOList = new ArrayList<OtherInExcelVO>();
        for (OtherInDetailVO s:otherInDetailVOList) {
            OtherInExcelVO vo = new OtherInExcelVO();
            vo = OtherInExcelVO.convertToVO(s);
            otherInDetailExcelVOList.add(vo);
        }
        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("入库单号:");
        titleSecondRow.append(otherInFormVO.getCode() == null ? "" : otherInFormVO.getCode());
        titleSecondRow.append("     ");
        titleSecondRow.append("入库日期:");
        titleSecondRow.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(otherInFormVO.getInDate()));
        titleSecondRow.append("     ");
        titleSecondRow.append("入库人员:");
        titleSecondRow.append(otherInFormVO.getInManName() == null ? "" : otherInFormVO.getInManName());
        titleSecondRow.append("     ");
        titleSecondRow.append("入库类型:");
        titleSecondRow.append(otherInFormVO.getInType() == 0 ? "获赠" :(otherInFormVO.getInType() == 1 ? "报溢" :(otherInFormVO.getInType() == 2 ? "领用退回" :(otherInFormVO.getInType() == 3 ? "其他" : ""))));
        titleSecondRow.append("     ");
        titleSecondRow.append("来源单位:");
        titleSecondRow.append(otherInFormVO.getSrcUnitName() == null ? "" : otherInFormVO.getSrcUnitName());
        titleSecondRow.append("     ");
        titleSecondRow.append("流通监管码:");
        titleSecondRow.append(otherInFormVO.getFlowCode() == null ? "" : otherInFormVO.getFlowCode());
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("其他入库");
        List<String> secondTitle = new ArrayList<>();
        secondTitle.add(titleSecondRow.toString());
        purchaseGeneralComponent.commExcelExport(output,map,otherInDetailVOList,name,secondTitle,"",true,new ArrayList<>());

    }

    @Override
    public void handleKeyTable(Long id) throws Exception{
        OtherIn otherIn = otherInMapper.selectByPrimaryKey(id);
        List<OtherInDetail> otherInDetails = otherInDetailMapper.selectByEnterpriseIdAndInId(otherIn.getEnterpriseId(), otherIn.getId());
        Long inManId = otherIn.getInManId();
        User user = userMapper.selectByPrimaryKey(inManId);
        UserVO loginUser = convertTOVO(user);
        //生成关键表数据
        OrderModelBuilder builder = new OrderModelBuilder(loginUser);
        OrderModel orderModel = builder.buildOrderModel(OrderRule.RECEIVE, otherIn, otherInDetails);
        inOutComponent.generateKeyTableDatas(OrderDirection.IN, orderModel);

        // 判断是否调用超量销售生成的入库单：是，则调用通用出库接口将原来超量销售的单据进行出库
        Long enterpriseId = otherIn.getEnterpriseId();
        String excessiveSaleCodes = otherIn.getExcessiveSaleCodes();
        inOutComponent.handleExcessiveSaleData(enterpriseId, excessiveSaleCodes, loginUser);
        // 处理勾兑超量销售单进行退货的销售退货单（此类型销售退货单未进行实际入库）
        inOutComponent.handleExcessiveSaleReturnData(enterpriseId, excessiveSaleCodes, loginUser);

        /**
         * 修改商品的默认货位
         */
        otherInDetails.forEach(
                details -> {
                    commonComponent.updateGoodsDefShelf(
                            loginUser.getEnterpriseId()
                            ,loginUser.getParentId()
                            ,loginUser.getChainType()
                            ,details.getGoodsId()
                            ,details.getShelfId()
                            ,details.getShelfName()
                            ,loginUser
                    );
                    /**
                     *
                     */
                    LastInPriceVO lastInPriceVO = new LastInPriceVO();

                    /**
                     private Long inTaxRateId;// 入库税率ID
                     private BigDecimal inTaxRate;// 入库税率
                     BigDecimal inPrice;// 入库单价
                     */
                    lastInPriceVO.setEnterpriseId(loginUser.getEnterpriseId());
                    lastInPriceVO.setParentId(loginUser.getParentId());
                    lastInPriceVO.setChainType(loginUser.getChainType());
                    lastInPriceVO.setGoodsId(details.getGoodsId());
                    lastInPriceVO.setInTaxRateId(details.getTaxRateId());
                    lastInPriceVO.setInTaxRate(details.getTaxRate());
                    lastInPriceVO.setInPrice(details.getUnitPrice());
                    commonComponent.updateLastPriceInfo(lastInPriceVO);
                }
        );

    }

    private UserVO convertTOVO(User user) {
        UserVO userVO = new UserVO();
        userVO.setUserId(user.getId());
        userVO.setEnterpriseId(user.getEnterpriseId());
        Long enterpriseId = user.getEnterpriseId();
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
        if (enterprise != null){
            userVO.setEnterpriseCode(enterprise.getCode());
            userVO.setEnterpriseName(enterprise.getName());
        }
        userVO.setParentId(enterprise.getParentId());
        Enterprise parentEnterprise = enterpriseMapper.selectByPrimaryKey(enterprise.getParentId());
        if (parentEnterprise != null){
            userVO.setParentCode(parentEnterprise.getCode());
            userVO.setParentName(parentEnterprise.getName());
        }
        userVO.setChainType(enterprise.getChainType());
        userVO.setUserCode(user.getCode());
        userVO.setUserName(user.getName());
        List<UserAdministration> userAdministrations = userAdministrationMapper.selectUserAdministrationByUserId(user.getId());
        userVO.setLoginAccount(userAdministrations.get(0).getLoginAccount());
        userVO.setPassword(userAdministrations.get(0).getPassword());
        return userVO;
    }
}
