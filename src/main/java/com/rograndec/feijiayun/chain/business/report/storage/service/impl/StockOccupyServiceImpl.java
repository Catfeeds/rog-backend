package com.rograndec.feijiayun.chain.business.report.storage.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrSendDetailMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockLockRecordMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.StockLockRecord;
import com.rograndec.feijiayun.chain.business.purchase.ret.dao.PurchaseReturnDetailMapper;
import com.rograndec.feijiayun.chain.business.report.storage.service.StockOccupyService;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockOccupyExcelPageVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockOccupyPageVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.StockOccupyVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class StockOccupyServiceImpl implements StockOccupyService {

    @Autowired
    private StockLockRecordMapper stockLockRecordMapper;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private PurchaseReturnDetailMapper purchaseReturnDetailMapper;

    @Autowired
    private DistrSendDetailMapper distrSendDetailMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Override
    public StockOccupyVO queryStockOccupyList(Page page, String param, Long enterpriseId, String order, String sort, UserVO userVO) {
        StockOccupyVO pageVO = new StockOccupyVO();

        //合计库存数量
        BigDecimal statsticQuantity = BigDecimal.ZERO;
        //合计占用数量
        BigDecimal statsticOccupyQuantity = BigDecimal.ZERO;
        //合计可用数量
        BigDecimal statsticUsableQuantity = BigDecimal.ZERO;

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("param",param);
        map.put("enterpriseId",enterpriseId);
        map.put("sort",sort);
        map.put("order",order);
        map.put("loginEnterpriseId",userVO.getEnterpriseId());
        map.put("pageSize",page.getPageSize());
        map.put("start",page.getStart());

        //所有锁定记录
        List<StockLockRecord> allList = stockLockRecordMapper.selectAllLockRecord();
        List<StockOccupyPageVO> list = new ArrayList<StockOccupyPageVO>();
        Integer totalRecord = 0;
        if (userVO.getChainType() == ChainType.Headquarters.getCode()){
            list = stockLockRecordMapper.selectFatherStockOccupy(map);
            totalRecord = stockLockRecordMapper.selectFatherTotalRecord(map);
        } else {
            list = stockLockRecordMapper.selectSonStockOccupy(map);
            totalRecord = stockLockRecordMapper.selectSonTotalRecord(map);
        }
        if (list != null && list.size() > 0){
            for (StockOccupyPageVO stockOccupy : list) {
                /**
                 * 取出唯一索引
                 */
                StringBuilder index = new StringBuilder();
                index.append(stockOccupy.getEnterPriseId());
                index.append(stockOccupy.getGoodsId());
                index.append(stockOccupy.getLotId());
                index.append(stockOccupy.getShelfId());
                /**
                 * 库存数量
                 */
                BigDecimal stockQuantity = BigDecimal.ZERO;
                /**
                 * 订单类型
                 */
                List<String> orderType = new ArrayList<String>();
                /**
                 * 订单单号
                 */
                List<String> orderCode = new ArrayList<String>();
                /**
                 *订单数量
                 */
                List<BigDecimal> orderQuantity = new ArrayList<BigDecimal>();
                /**
                 * 占用数量
                 */
                BigDecimal occupyQuantity = BigDecimal.ZERO;
                /**
                 * 可用数量
                 */
                BigDecimal usableQuantity = BigDecimal.ZERO;

                /**
                 * 总部库存数量
                 */
                stockQuantity = stockMapper.selectQuantityByEnterPriseIdGoodIdAndShelfIdAndLotId(stockOccupy.getEnterPriseId(),
                        stockOccupy.getGoodsId(),
                        stockOccupy.getShelfId(),
                        stockOccupy.getLotId());
                statsticQuantity = statsticQuantity.add(stockQuantity);
                /**
                 * 遍历所有库存记录表数据加入订单类型 + 订单单号 + 订单数量
                 */
                for (StockLockRecord stockLockRecord : allList) {
                    StringBuilder stockIndex = new StringBuilder();
                    stockIndex.append(stockLockRecord.getEnterpriseId());
                    stockIndex.append(stockLockRecord.getGoodsId());
                    stockIndex.append(stockLockRecord.getLotId());
                    stockIndex.append(stockLockRecord.getShelfId());
                    /**
                     * 匹配上之后累加订单类型 + 订单单号 + 订单数量
                     */
                    if (index.toString().equals(stockIndex.toString())){
                        orderType.add(OrderRule.getName(stockLockRecord.getBaseOrderType()));
                        orderCode.add(stockLockRecord.getBaseOrderCode());
                        orderQuantity.add(stockLockRecord.getLockQuantity());

                    }
                }
                //赋值
                stockOccupy.setQuantity(stockQuantity);
                stockOccupy.setOrderType(orderType);
                stockOccupy.setOrderCode(orderCode);
                stockOccupy.setOrderQuantity(orderQuantity);
                if (orderQuantity.size() > 0){
                    for (BigDecimal b : orderQuantity) {
                        occupyQuantity = occupyQuantity.add(b);
                    }
                }
                usableQuantity = stockQuantity.subtract(occupyQuantity);
                stockOccupy.setOccupyQuantity(occupyQuantity);
                stockOccupy.setUsableQuantity(usableQuantity);
                statsticOccupyQuantity = statsticOccupyQuantity.add(occupyQuantity);
                statsticUsableQuantity = statsticUsableQuantity.add(usableQuantity);
            }
        }
        pageVO.setQuantity(statsticQuantity);
        pageVO.setOccupyQuantity(statsticOccupyQuantity);
        pageVO.setUsableQuantity(statsticUsableQuantity);
        page.setResult(list);
        page.setTotalRecord(totalRecord);
        pageVO.setPage(page);
        return pageVO;
    }

    @Override
    public List<StockOccupyExcelPageVO> getExcel(String param, Long enterpriseId, String order, String sort, UserVO userVO) {
        List<StockOccupyExcelPageVO> excel = new ArrayList<StockOccupyExcelPageVO>();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("param",param);
        map.put("enterpriseId",enterpriseId);
        map.put("sort",sort);
        map.put("order",order);
        map.put("loginEnterpriseId",userVO.getEnterpriseId());

        List<StockLockRecord> allList = stockLockRecordMapper.selectAllLockRecord();
        List<StockOccupyPageVO> list = new ArrayList<StockOccupyPageVO>();
        if (userVO.getChainType() == ChainType.Headquarters.getCode()){
            list = stockLockRecordMapper.selectFatherStockOccupy(map);
        } else {
            list = stockLockRecordMapper.selectSonStockOccupy(map);
        }
        if (list != null && list.size() > 0){
            for (StockOccupyPageVO stockOccupy : list) {
                /**
                 * 取出唯一索引
                 */
                StringBuilder index = new StringBuilder();
                index.append(stockOccupy.getEnterPriseId());
                index.append(stockOccupy.getGoodsId());
                index.append(stockOccupy.getLotId());
                index.append(stockOccupy.getShelfId());
                /**
                 * 库存数量
                 */
                BigDecimal stockQuantity = BigDecimal.ZERO;
                /**
                 * 订单类型
                 */
                List<String> orderType = new ArrayList<String>();
                /**
                 * 订单单号
                 */
                List<String> orderCode = new ArrayList<String>();
                /**
                 *订单数量
                 */
                List<BigDecimal> orderQuantity = new ArrayList<BigDecimal>();
                /**
                 * 占用数量
                 */
                BigDecimal occupyQuantity = BigDecimal.ZERO;
                /**
                 * 可用数量
                 */
                BigDecimal usableQuantity = BigDecimal.ZERO;

                /**
                 * 总部库存数量
                 */
                stockQuantity = stockMapper.selectQuantityByEnterPriseIdGoodIdAndShelfIdAndLotId(stockOccupy.getEnterPriseId(),
                        stockOccupy.getGoodsId(),
                        stockOccupy.getShelfId(),
                        stockOccupy.getLotId());
                /**
                 * 遍历所有库存记录表数据加入订单类型 + 订单单号 + 订单数量
                 */
                for (StockLockRecord stockLockRecord : allList) {
                    StringBuilder stockIndex = new StringBuilder();
                    stockIndex.append(stockLockRecord.getEnterpriseId());
                    stockIndex.append(stockLockRecord.getGoodsId());
                    stockIndex.append(stockLockRecord.getLotId());
                    stockIndex.append(stockLockRecord.getShelfId());
                    /**
                     * 匹配上之后累加订单类型 + 订单单号 + 订单数量
                     */
                    if (index.toString().equals(stockIndex.toString())){
                        orderType.add(OrderRule.getName(stockLockRecord.getBaseOrderType()));
                        orderCode.add(stockLockRecord.getBaseOrderCode());
                        orderQuantity.add(stockLockRecord.getLockQuantity());

                    }
                }
                //赋值
                stockOccupy.setQuantity(stockQuantity);
                stockOccupy.setOrderType(orderType);
                stockOccupy.setOrderCode(orderCode);
                stockOccupy.setOrderQuantity(orderQuantity);
                if (orderQuantity.size() > 0){
                    for (BigDecimal b : orderQuantity) {
                        occupyQuantity = occupyQuantity.add(b);
                    }
                }
                usableQuantity = stockQuantity.subtract(occupyQuantity);
                stockOccupy.setOccupyQuantity(occupyQuantity);
                stockOccupy.setUsableQuantity(usableQuantity);
            }
        }

        /**
         * 转换为EXCEL的list
         */
        for (StockOccupyPageVO page : list) {
            List<String> orderCode = page.getOrderCode();
            if (orderCode != null && orderCode.size() > 0){
                for (int i = 0; i < orderCode.size(); i++){
                    StockOccupyExcelPageVO excelPageVO = new StockOccupyExcelPageVO();
                    CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(page,excelPageVO);
                    excelPageVO.setProductDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(page.getProductDate()));
                    excelPageVO.setValidUntil(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(page.getValidUntil()));
                    excelPageVO.setOrderType(page.getOrderType().get(i));
                    excelPageVO.setOrderCode(page.getOrderCode().get(i));
                    excelPageVO.setOrderQuantity(page.getOrderQuantity().get(i));
                    excel.add(excelPageVO);
                }

            }
        }

        return excel;
    }

    @Override
    public void export(OutputStream output, List<StockOccupyExcelPageVO> vo, UserVO userVO) {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("enterpriseCode", "组织机构编码");
        map.put("enterpriseName", "组织机构名称");
        map.put("goodsCode", "商品编码");
        map.put("goodsGenericName", "通用名称");
        map.put("dosageName", "剂型");
        map.put("goodsSpecification", "规格");
        map.put("unitName", "单位");
        map.put("manufacturer", "生产厂商");
        map.put("goodsPlace", "产地");
        map.put("lotNum", "批号");
        map.put("productDate", "生产日期");
        map.put("validUntil", "有效期至");
        map.put("shelfName","货位");
        map.put("quantity","库存数量");
        map.put("orderType","订单类型");
        map.put("orderCode","订单单号");
        map.put("orderQuantity","订单数量");
        map.put("occupyQuantity","占用数量");
        map.put("usableQuantity","可用数量");
        List<String> name = new ArrayList<>();
        name.add(userVO.getEnterpriseName());
        name.add("占用库存");
        purchaseGeneralComponent.commExcelExport(output, map, vo, name, new ArrayList<>(), "", false, new ArrayList<>());
    }
}
