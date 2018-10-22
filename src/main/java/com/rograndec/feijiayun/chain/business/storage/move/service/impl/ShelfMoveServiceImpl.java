package com.rograndec.feijiayun.chain.business.storage.move.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseCargoAreaMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseCargoArea;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.CargoQualityStateVO;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.keytable.dao.*;
import com.rograndec.feijiayun.chain.business.keytable.entity.*;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.dao.GoodsHandleDetailMapper;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.dao.GoodsHandleMapper;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.dao.GoodsHandleShelfMapper;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.*;
import com.rograndec.feijiayun.chain.business.storage.move.constant.ShelfMoveCondition;
import com.rograndec.feijiayun.chain.business.storage.move.dao.ShelfMoveDetailMapper;
import com.rograndec.feijiayun.chain.business.storage.move.dao.ShelfMoveMapper;
import com.rograndec.feijiayun.chain.business.storage.move.entity.ShelfMove;
import com.rograndec.feijiayun.chain.business.storage.move.entity.ShelfMoveDetail;
import com.rograndec.feijiayun.chain.business.storage.move.service.ShelfMoveService;
import com.rograndec.feijiayun.chain.business.storage.move.vo.ShelfMoveDetailVO;
import com.rograndec.feijiayun.chain.business.storage.move.vo.ShelfMoveExcelVO;
import com.rograndec.feijiayun.chain.business.storage.move.vo.ShelfMoveFormVO;
import com.rograndec.feijiayun.chain.business.storage.move.vo.ShelfMovePageVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.OrderDirection;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.ShelfStatus;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.model.OrderModel;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ShelfMoveServiceImpl implements ShelfMoveService{

    @Autowired
    private ShelfMoveMapper shelfMoveMapper;

    @Autowired
    private ShelfMoveDetailMapper shelfMoveDetailMapper;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private LotNumberMapper lotNumberMapper;

    @Autowired
    private WarehouseShelfMapper warehouseShelfMapper;

    @Autowired
    private WarehouseCargoAreaMapper warehouseCargoAreaMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Autowired
    private InOutComponent inOutComponent;

    @Autowired
    private StockLockRecordMapper stockLockRecordMapper;

    @Autowired
    private GoodsHandleMapper goodsHandleMapper;

    @Autowired
    private GoodsHandleDetailMapper goodsHandleDetailMapper;

    @Autowired
    private GoodsHandleShelfMapper goodsHandleShelfMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private CostLockRecordMapper costLockRecordMapper;

    @Autowired
    private CostMapper costMapper;

    @Autowired
    private FinanceComponent financeComponent;

    @Override
    public Page getShelfMovePage(Page page, Date startTime, Date endTime, String code, String moveManName, String receiverName, String order, String sort, Long enterpriseId) {
        if("moveDate".equals(order)){
            order = "move_date";
        }else if("code".equals(order)){
            order = "code";
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("code",code);
        map.put("moveManName",moveManName);
        map.put("receiverName",receiverName);
        map.put("order",order);
        map.put("sort",sort);
        map.put("enterpriseId",enterpriseId);
        map.put("pageSize",page.getPageSize());
        map.put("start",(page.getPageNo()-1)*page.getPageSize());
        List<ShelfMove> list = shelfMoveMapper.selectShelfMovePage(map);
        List<ShelfMovePageVO> pageVOList = new ArrayList<ShelfMovePageVO>();
        for (ShelfMove s:list) {
            ShelfMovePageVO vo = ShelfMovePageVO.convertToPageVO(s);
            pageVOList.add(vo);
        }
        page.setResult(pageVOList);
        Integer totalRecord = shelfMoveMapper.getTotalRecord(map);
        page.setTotalRecord(totalRecord);
        return page;
    }

    @Override
    public ShelfMoveFormVO getMoveShelfDetail(Long enterpriseId, Long id) {
        ShelfMove shelfMove = shelfMoveMapper.selectByPrimaryKey(id);
        //要构建的明细VO
        ShelfMoveFormVO shelfMoveFormVO = new ShelfMoveFormVO();
        shelfMoveFormVO = ShelfMoveFormVO.converToVO(shelfMove);
        //插入List信息--先获得List信息
        List<ShelfMoveDetail> shelfMoveDetailList = shelfMoveDetailMapper.getByMoveId(enterpriseId,id);
        List<ShelfMoveDetailVO> voList = new ArrayList<ShelfMoveDetailVO>();
        //获得当前货位移动明细单的总计数量
        BigDecimal totalQuantity = shelfMoveDetailMapper.getTotalQuantity(enterpriseId,id);
        shelfMoveFormVO.setTotalQuantity(totalQuantity);
        for (ShelfMoveDetail detail:shelfMoveDetailList) {
            ShelfMoveDetailVO detailVO = new ShelfMoveDetailVO();
            detailVO = ShelfMoveDetailVO.convertToVO(detail);
            voList.add(detailVO);
        }
        shelfMoveFormVO.setShelfMoveDetailVOList(voList);
        return shelfMoveFormVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertShelfMove(UserVO loginUser, ShelfMoveFormVO shelfMoveFormVO) throws Exception{
        ShelfMove sm = new ShelfMove();
        //生成的移动单号
        String code = orderCodeComponent.
                generate(OrderRule.SHELF_MOVE.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode());
        sm.setEnterpriseId(loginUser.getEnterpriseId());
        sm.setParentId(loginUser.getParentId());
        sm.setOrderType(OrderRule.SHELF_MOVE.getType());
        sm.setCode(code);
        sm.setMoveDate(shelfMoveFormVO.getMoveDate());
        /**
         * 货位移动人员在质量状况开启与否的状态下
         */
        if (shelfMoveFormVO.getMoveManId() == null){
            sm.setMoveManId(loginUser.getUserId());
            sm.setMoveManCode(loginUser.getUserCode());
            sm.setMoveManName(loginUser.getUserName());
        }else {
            sm.setMoveManId(shelfMoveFormVO.getMoveManId());
            User userMove = userMapper.getUserByIdAndEnterpriseId(shelfMoveFormVO.getMoveManId(),loginUser.getEnterpriseId());
            sm.setMoveManCode(userMove.getCode() == null ? "":userMove.getCode());
            sm.setMoveManName(userMove.getName());
        }

        sm.setQuantityTotal(new BigDecimal(0));
        sm.setVarietiesQuantity(0);
        if (shelfMoveFormVO.getReceiverId() != null){
            sm.setReceiverId(shelfMoveFormVO.getReceiverId());
            //获取当前接收人员
            User user = userMapper.getUserByIdAndEnterpriseId(shelfMoveFormVO.getReceiverId(),loginUser.getEnterpriseId());
            sm.setReceiverCode(user.getCode());
            sm.setReceiverName(user.getName());
        }
        sm.setMoveReason(shelfMoveFormVO.getMoveReason() == null ? "" :shelfMoveFormVO.getMoveReason());
        //默认给个1---后续可能会用到
        sm.setStatus(1);
        UserEnterpriseUtils.setUserCreateOrModify(sm,loginUser,true);
        shelfMoveMapper.insertSelective(sm);
        //主表的数量合计
        BigDecimal quatityTotal = new BigDecimal(0);
        //####################################开始插入明细信息
        //品种数量
        Set set = new HashSet();
        if (shelfMoveFormVO.getShelfMoveDetailVOList() != null){
            List<ShelfMoveDetailVO> shelfMoveDetailVOList = shelfMoveFormVO.getShelfMoveDetailVOList();
            for (ShelfMoveDetailVO detailVO:shelfMoveDetailVOList) {
                ShelfMoveDetail sd = new ShelfMoveDetail();
                sd.setEnterpriseId(loginUser.getEnterpriseId());
                sd.setParentId(loginUser.getParentId());
                sd.setMoveId(sm.getId());
                sd.setOrderType(OrderRule.SHELF_MOVE.getType());
                sd.setMoveCode(code);
                sd.setMoveDate(shelfMoveFormVO.getMoveDate());
                sd.setGoodsId(detailVO.getGoodsId());
                set.add(detailVO.getGoodsId());
                //##########下面是通过商品ID查出插入的信息
                Goods goods = goodsMapper.selectByPrimaryKey(detailVO.getGoodsId());
                if (goods != null){
                    sd.setGoodsCode(goods.getCode());
                    sd.setBarcode(goods.getBarcode());
                    sd.setGoodsName(goods.getName());
                    sd.setGoodsGenericName(goods.getGenericName());
                    sd.setDosageId(goods.getDosageId());
                    sd.setDosageName(goods.getDosageName());
                    sd.setUnitId(goods.getUnitId());
                    sd.setUnitName(goods.getUnitName());
                    sd.setGoodsSpecification(goods.getSpecification());
                    sd.setManufacturerId(goods.getManufacturerId());
                    sd.setManufacturer(goods.getManufacturer());
                    sd.setGoodsPlace(goods.getPlace());
                    sd.setApprovalNumber(goods.getApprovalNumber());
                }

                //批号ID需要前台穿
                sd.setLotId(detailVO.getLotId());
                //批号内容我自己查找
                LotNumber lotNumber = lotNumberMapper.selectByPrimaryKey(detailVO.getLotId());
                if(lotNumber != null){
                    sd.setLotNumber(lotNumber.getLotNum());
                    sd.setProductDate(lotNumber.getProductDate());
                    sd.setValidDate(lotNumber.getValidUntil());
                }

                sd.setSrcShelfId(detailVO.getSrcShelfId());
                if (detailVO.getSrcShelfId().equals(detailVO.getTargetShelfId())){
                    throw new BusinessException(SysCode.FAIL.getCode(),"源货位与目标货位不能相同!");
                }
                //获取源货位名称
                WarehouseShelf srcWarehouseShelf = warehouseShelfMapper.selectByPrimaryKey(detailVO.getSrcShelfId());
                if (srcWarehouseShelf != null){
                    sd.setSrcShelfName(srcWarehouseShelf.getName());
                }

                //获取源货位状态描述
                WarehouseCargoArea srcWarehouseCargoArea = warehouseCargoAreaMapper.selectByPrimaryKey(srcWarehouseShelf.getCargoAreaId());
                if (srcWarehouseCargoArea != null){
                    Integer srcJobArea = srcWarehouseCargoArea.getJobArea();
                    if (srcJobArea.equals(ShelfStatus.QUALIFIED.getCode())){
                        sd.setSrcShelfStatusDesc(ShelfStatus.QUALIFIED.getName());
                    }else if (srcJobArea.equals(ShelfStatus.UNQULIFIED.getCode())){
                        sd.setSrcShelfStatusDesc(ShelfStatus.UNQULIFIED.getName());
                    }else if (srcJobArea.equals(ShelfStatus.PENDING.getCode())){
                        sd.setSrcShelfStatusDesc(ShelfStatus.PENDING.getName());
                    }
                }
                sd.setTargetShelfId(detailVO.getTargetShelfId());
                //获取目标货位名称
                WarehouseShelf targetWarehouseShelf = warehouseShelfMapper.selectByPrimaryKey(detailVO.getTargetShelfId());
                if (targetWarehouseShelf != null){
                    sd.setTargetShelfName(targetWarehouseShelf.getName());
                    //获取目标货位状态描述
                    WarehouseCargoArea targetWarehouseCargoArea = warehouseCargoAreaMapper.selectByPrimaryKey(targetWarehouseShelf.getCargoAreaId());
                    if (targetWarehouseCargoArea != null){
                        Integer targetJobArea = targetWarehouseCargoArea.getJobArea();
                        if (targetJobArea.equals(ShelfStatus.QUALIFIED.getCode())){
                            sd.setTargetShelfStatusDesc(ShelfStatus.QUALIFIED.getName());
                        }else if (targetJobArea.equals(ShelfStatus.UNQULIFIED.getCode())){
                            sd.setTargetShelfStatusDesc(ShelfStatus.UNQULIFIED.getName());
                        }
                    }
                }
                sd.setQuantity(detailVO.getQuantity());
                quatityTotal = quatityTotal.add(detailVO.getQuantity());
                sd.setLineNum(detailVO.getLineNum() == null ? 0:detailVO.getLineNum());
                //状态预留--先设置为1
                sd.setStatus(1);
                sd.setRemark(detailVO.getRemark() == null ? "":detailVO.getRemark());
                UserEnterpriseUtils.setUserCreateOrModify(sd,loginUser,true);
                shelfMoveDetailMapper.insertSelective(sd);
                /**
                 * 当货位移动调用的是锁定商品的情况
                 *
                 * 1.数量不够减锁定记录的数量
                 * 2.数量够了更新商品处理的状态
                 */
                if (shelfMoveFormVO.getFlag()){
                    updateGoodsHandle(detailVO,loginUser);
                    /**
                     * 按照细单解锁库存
                     */
                    releaseStock(detailVO,loginUser);

                    /**
                     * 释放锁定成本记录
                     */
                    releaseCost(detailVO,loginUser);
                }
            }
        }
        //数量合计和品种数量自己计算再塞入--更新主表的数量合计和品种数量########################
        ShelfMove updateShelfMove = new ShelfMove();
        updateShelfMove.setId(sm.getId());
        updateShelfMove.setQuantityTotal(quatityTotal);
        updateShelfMove.setVarietiesQuantity(set.size());
        shelfMoveMapper.updateByPrimaryKeySelective(updateShelfMove);
        //数量合计和品种数量自己计算再塞入--更新主表的数量合计和品种数量########################
        //构建总单
        ShelfMove constructShelfMove = shelfMoveMapper.selectByPrimaryKey(sm.getId());
        List<ShelfMoveDetail> dtlList = shelfMoveDetailMapper.getByMoveId(loginUser.getEnterpriseId(),sm.getId());
        OrderModelBuilder builder = new OrderModelBuilder(loginUser);
        OrderModel orderModel = builder.buildOrderModel(OrderRule.SHELF_MOVE, constructShelfMove, dtlList);
        inOutComponent.generateKeyTableDatas(OrderDirection.MOVE, orderModel);
        //加入财务通用接口
        financeComponent.shelfMoveToBalanceAndVoucher(loginUser,constructShelfMove);
    }

    /**
     * 释放锁定的成本可用量
     * @param detailVO
     * @param loginUser
     * @throws Exception
     */
    private void releaseCost(ShelfMoveDetailVO detailVO, UserVO loginUser) throws Exception {
        Map<String,Object> map = new HashMap<>();
        Long enterpriseId = loginUser.getEnterpriseId();
        Long parentId = loginUser.getParentId();
        Long goodsId = detailVO.getGoodsId();
        Long lotId = detailVO.getLotId();
        Long handleId = detailVO.getHandleId();
        String handleCode = detailVO.getHandleCode();

        map.put("enterpriseId", enterpriseId);
        map.put("parentId", parentId);
        map.put("baseOrderId", handleId);
        map.put("baseOrderCode", handleCode);
        map.put("goodsId", goodsId);
        map.put("lotId", lotId);

        List<CostLockRecord> costLockRecordList =  costLockRecordMapper.selectByParamMap(map);
        BigDecimal releaseQty = detailVO.getQuantity();
        for(CostLockRecord costLockRecord:costLockRecordList){
            BigDecimal lockQuantity = costLockRecord.getLockQuantity();
            if(lockQuantity.compareTo(releaseQty) <= 0){
                // 清除成本锁定记录
                costLockRecordMapper.deleteByPrimaryKey(costLockRecord.getId());
                // 释放成本表可用库存数量
                updateCostUseableQuantity(enterpriseId, parentId, costLockRecord, lockQuantity);
                releaseQty = releaseQty.subtract(lockQuantity);
            }else {
                // 更新成本锁定记录
                costLockRecord.setLockQuantity(costLockRecord.getLockQuantity().subtract(releaseQty));
                costLockRecordMapper.updateByPrimaryKey(costLockRecord);
                // 释放成本表可用库存数量
                updateCostUseableQuantity(enterpriseId, parentId, costLockRecord, releaseQty);
                releaseQty=BigDecimal.ZERO;
            }
            if(releaseQty.compareTo(BigDecimal.ZERO)<=0){
                break;
            }
        }
        if(releaseQty.compareTo(BigDecimal.ZERO) > 0){
            throw new BusinessException("货位移动，释放锁定的成本时发生异常：成本可用量不足！");
        }

    }

    private void updateCostUseableQuantity(Long enterpriseId, Long parentId, CostLockRecord costLockRecord, BigDecimal lockQuantity) {
        Map<String, Object> stockParamMap = new HashMap<>();
        stockParamMap.put("enterpriseId", enterpriseId);
        stockParamMap.put("parentId", parentId);
        stockParamMap.put("goodsId", costLockRecord.getGoodsId());
        stockParamMap.put("lotId", costLockRecord.getLotId());
        stockParamMap.put("batchId", costLockRecord.getBatchId());
        // 释放成本
        List<Cost> costList = costMapper.selectByParamMap(stockParamMap);
        if(CollectionUtils.isNotEmpty(costList)){
            Cost cost = costList.get(0);
            cost.setUsableQuantity(cost.getUsableQuantity().add(lockQuantity));
            costMapper.updateByPrimaryKeySelective(cost);
        }
    }

    /**
     * 解锁库存
     * @param detailVO
     * @param loginUser
     */
    private void releaseStock(ShelfMoveDetailVO detailVO, UserVO loginUser) throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("enterpriseId",loginUser.getEnterpriseId());
        map.put("goodsId",detailVO.getGoodsId());
        map.put("lotId",detailVO.getLotId());
        map.put("shelfId",detailVO.getSrcShelfId());
        map.put("parentId",loginUser.getParentId());
        List<Stock> stocks = stockMapper.selectByParamMap(map);
        if (stocks == null){
            throw new BusinessException(SysCode.FAIL.getCode(),"当前需要释放的库存不存在！商品ID =" +
                    detailVO.getGoodsId() + "货位ID = " + detailVO.getSrcShelfId() + "批号ID = " + detailVO.getLotId() + "企业ID = " + loginUser.getEnterpriseId() + "上级企业ID=" + loginUser.getParentId());
        }
        if (stocks.size() > 0){
            for (Stock s : stocks) {
                if (detailVO.getQuantity().compareTo(s.getLockQuantity()) == 1){
                    throw new BusinessException(SysCode.FAIL.getCode(),"当前锁定库存量小于移动的锁定数量");
                }
                s.setLockQuantity(s.getLockQuantity().subtract(detailVO.getQuantity()));
                s.setUsableQuantity(s.getUsableQuantity().add(detailVO.getQuantity()));
                UserEnterpriseUtils.setUserCreateOrModify(s,loginUser,false);
                stockMapper.updateByPrimaryKeySelective(s);
            }
        }
    }

    private void updateGoodsHandle(ShelfMoveDetailVO detail, UserVO loginUser) throws Exception {
        StockLockRecord stockLockRecord = stockLockRecordMapper.selectByGoodsIdAndLotIdAndShelfId(detail.getHandleId(),detail.getGoodsId(),detail.getLotId(),detail.getSrcShelfId(),loginUser.getEnterpriseId(),detail.getHandleCode());
        if(stockLockRecord == null){
            throw new BusinessException("没有查询到上级单据锁定库存信息！");
        }
        if((detail.getQuantity().subtract(stockLockRecord.getLockQuantity())).compareTo(BigDecimal.ZERO) > 0){
            throw new BusinessException("货位移动数量不能超出原锁定商品数量！");
        }
        int num = stockLockRecord.getLockQuantity().compareTo(detail.getQuantity());
        if(num > 0){//锁定数量 > 货位移动数量  更新
            BigDecimal lockQuantity = stockLockRecord.getLockQuantity().subtract(detail.getQuantity());
            stockLockRecord.setLockQuantity(lockQuantity);
            stockLockRecordMapper.updateByPrimaryKeySelective(stockLockRecord);
        }else {//锁定数量 = 处理数量   删除
            stockLockRecordMapper.deleteByPrimaryKey(stockLockRecord.getId());
            //判断商品锁定表是否已经处理完成
            if(ishandleMove(detail.getHandleId(), detail.getHandleCode())){
                //处理完成后更新商品处理的状态
                //1.更新主单状态
                GoodsHandle goodsHandle = goodsHandleMapper.selectByPrimaryKey(detail.getHandleId());
                goodsHandle.setStatus(PurchaseStatus.FINISHED.getStatus());
                UserEnterpriseUtils.setUserCreateOrModify(goodsHandle,loginUser,false);
                goodsHandleMapper.updateByPrimaryKeySelective(goodsHandle);
                //2.更新细单状态
                List<GoodsHandleDetail> handleDetail = goodsHandleDetailMapper.getGoodsHandleDetailByHandleId(detail.getHandleId());
                if (handleDetail.size() > 0){
                    for (GoodsHandleDetail d : handleDetail) {
                        d.setStatus(PurchaseStatus.FINISHED.getStatus());
                        UserEnterpriseUtils.setUserCreateOrModify(d,loginUser,false);
                        goodsHandleDetailMapper.updateByPrimaryKeySelective(d);
                        //3.更新货位单状态
                        List<GoodsHandleShelf> handleShelf = goodsHandleShelfMapper.getGoodsHandleShelfByDtId(d.getId());
                        if (handleShelf.size() > 0){
                            for (GoodsHandleShelf s : handleShelf) {
                                s.setStatus(PurchaseStatus.FINISHED.getStatus());
                                UserEnterpriseUtils.setUserCreateOrModify(s,loginUser,false);
                                goodsHandleShelfMapper.updateByPrimaryKeySelective(s);
                            }
                        }
                    }
                }
            }

        }
    }

    /**
     * 判断是否有商品处理单
     * @param handleId
     * @param handleCode
     * @return
     */
    private boolean ishandleMove(Long handleId, String handleCode) {
        List<StockLockRecord> stockLockRecord = stockLockRecordMapper.selectByLockId(handleId, handleCode);
        if(stockLockRecord.isEmpty() || (stockLockRecord == null)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<ShelfMoveDetailVO> getGoodsList(UserVO loginUser, String operation, String param) {
        List<ShelfMoveDetailVO> list = new ArrayList<ShelfMoveDetailVO>();
        if (ShelfMoveCondition.ADD.equals(operation)){
            list = shelfMoveMapper.selectGoodsByAdd(loginUser.getEnterpriseId(),param);
        }else if (ShelfMoveCondition.DOWN.equals(operation)){
            list = shelfMoveMapper.selectGoodsByDown(loginUser.getEnterpriseId(),param);
        }else if (ShelfMoveCondition.LOCK.equals(operation)){
            list = shelfMoveMapper.selectGoodsByLock(loginUser.getEnterpriseId(),param,OrderRule.HANDLE.getType());
            /**
             * 加上货位质量状态
             */
            if (list != null && list.size() > 0){
                for (ShelfMoveDetailVO vo : list) {
                    Long srcShelfId = vo.getSrcShelfId();
                    if (srcShelfId != null){
                        CargoQualityStateVO cargo = warehouseCargoAreaMapper.getCargoByShelfId(srcShelfId);
                        if (cargo != null){
                            if (cargo.getJobArea() == ShelfStatus.QUALIFIED.getCode()){
                                vo.setSrcShelfStatusDesc("合格");
                            }else if (cargo.getJobArea() == ShelfStatus.UNQULIFIED.getCode()){
                                vo.setSrcShelfStatusDesc("不合格");
                            }
                        }
                    }
                }
            }

        }else if (ShelfMoveCondition.OVER.equals(operation)){
            //获取今天的时间要求时今日的00:00:00
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            Date zero = calendar.getTime();
            list = shelfMoveMapper.selectGoodsByOver(loginUser.getEnterpriseId(),param,zero);
        }
        return list;
    }

    @Override
    public void export(OutputStream output, ShelfMoveFormVO shelfMoveFormVO, UserVO loginUser) {
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
        map.put("srcShelfName","源货位");

        map.put("srcShelfStatusDesc","质量状况");
        map.put("targetShelfName","目标货位");
        map.put("targetShelfStatusDesc","质量状况");

        map.put("quantity","数量");
        map.put("remark","备注");
        List<ShelfMoveDetailVO> shelfMoveDetailVOList = shelfMoveFormVO.getShelfMoveDetailVOList();
        List<ShelfMoveExcelVO> shelfMoveExcelVOList = new ArrayList<ShelfMoveExcelVO>();
        for (ShelfMoveDetailVO s:shelfMoveDetailVOList) {
            ShelfMoveExcelVO vo = new ShelfMoveExcelVO();
            vo = ShelfMoveExcelVO.convertToVO(s);
            shelfMoveExcelVOList.add(vo);
        }
        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("移动单号:");
        titleSecondRow.append(shelfMoveFormVO.getCode());
        titleSecondRow.append("     ");
        titleSecondRow.append("移动日期:");
        titleSecondRow.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(shelfMoveFormVO.getMoveDate()));
        titleSecondRow.append("     ");
        titleSecondRow.append("移动人员:");
        titleSecondRow.append(shelfMoveFormVO.getMoveManName());
        titleSecondRow.append("     ");
        titleSecondRow.append("接收人员:");
        titleSecondRow.append(shelfMoveFormVO.getReceiverName());
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("货位移动");
        List<String> secondTitle = new ArrayList<>();
        secondTitle.add(titleSecondRow.toString());
        purchaseGeneralComponent.commExcelExport(output,map,shelfMoveExcelVOList,name,secondTitle,"",true,new ArrayList<>());

    }
}
