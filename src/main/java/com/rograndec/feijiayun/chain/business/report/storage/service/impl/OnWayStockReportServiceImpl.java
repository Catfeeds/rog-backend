package com.rograndec.feijiayun.chain.business.report.storage.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInNoticeDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInNoticeMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInNotice;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInNoticeDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnNoticeDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnNoticeMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnNotice;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnNoticeDetail;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrder;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderDetail;
import com.rograndec.feijiayun.chain.business.report.storage.service.OnWayStockReportService;
import com.rograndec.feijiayun.chain.business.report.storage.vo.OnWayExcelVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.OnWayGoodsPageVO;
import com.rograndec.feijiayun.chain.business.report.storage.vo.OnWayGoodsVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.DistrInStatus;
import com.rograndec.feijiayun.chain.common.constant.status.DistrReturnStatus;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

@Service
public class OnWayStockReportServiceImpl implements OnWayStockReportService{

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    @Autowired
    private PurchaseOrderDetailMapper purchaseOrderDetailMapper;

    @Autowired
    private DistrReturnNoticeMapper distrReturnNoticeMapper;

    @Autowired
    private DistrReturnNoticeDetailMapper distrReturnNoticeDetailMapper;

    @Autowired
    private DistrInNoticeMapper distrInNoticeMapper;

    @Autowired
    private DistrInNoticeDetailMapper distrInNoticeDetailMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;


    @Override
    public OnWayGoodsVO queryOnWayStockList(Page page, String param, Long enterpriseId, String order, String sort, UserVO userVO, 
    		Integer chainType, String enterpriseCode, String enterpriseName) {
        OnWayGoodsVO pageVO = new OnWayGoodsVO();
        //合计的库存数量
        BigDecimal statsticStockQuantity = BigDecimal.ZERO;
        //合计的在途数量
        BigDecimal statsticOnWayQuantity = BigDecimal.ZERO;

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("param",param);
        map.put("sort",sort);
        map.put("order",order);
        map.put("chainType",chainType);
        map.put("loginEnterpriseId",userVO.getEnterpriseId());
        if(enterpriseCode != null) map.put("enterpriseCode", enterpriseCode.trim());
        if(enterpriseName != null) map.put("enterpriseName", enterpriseName.trim());
        //防止前端脏数据
        if(userVO.getChainType() != ChainType.Headquarters.getCode()) {
        	map.put("enterpriseCode",null);
            map.put("enterpriseName",null);
        }
        /**
         * 所有采购订单中在途状态的集合
         */
        List<Integer> purchaseStatus = new ArrayList<Integer>();
        purchaseStatus.add(PurchaseStatus.WAIT_RECEIVE.getStatus());
        purchaseStatus.add(PurchaseStatus.WAIT_CHECK.getStatus());
        purchaseStatus.add(PurchaseStatus.WAIT_STORAGE.getStatus());
        map.put("purchaseStatus",purchaseStatus);
        /**
         * 配后退回所有在途状态的集合
         */
        List<Integer> distrStatus = new ArrayList<>();
        distrStatus.add(DistrReturnStatus.WAIT_RECEIVE);
        distrStatus.add(DistrReturnStatus.WAIT_CHECK);
        distrStatus.add(DistrReturnStatus.WAIT_IN);
        map.put("distrStatus",distrStatus);
        /**
         * 分店配进订单状态的集合
         */
        List<Integer> distrInStatus = new ArrayList<Integer>();
        distrInStatus.add(DistrInStatus.WAIT_RECEIVE);
        distrInStatus.add(DistrInStatus.WAIT_CHECK);
        distrInStatus.add(DistrInStatus.WAIT_IN);
        map.put("distrInStatus",distrInStatus);
        map.put("pageSize",page.getPageSize());
        map.put("start",page.getStart());
        //查询出的结果集List
        List<OnWayGoodsPageVO> list = new ArrayList<OnWayGoodsPageVO>();
        Integer totalRecord = 0;
        //如果是总部或是高级搜索中默认为总部的时候
        if (userVO.getChainType() == ChainType.Headquarters.getCode() && (chainType == null || chainType.equals(ChainType.Headquarters.getCode()))){
            /**
             * 总部情况--只查询采购订单 + 配后退回的未入库订单
             */
            list = stockMapper.selectFatherOnWay(map);
            totalRecord = stockMapper.selectFatherTotalRecord(map);
            if (list != null && list.size() > 0){
                for (OnWayGoodsPageVO vo : list) {
                    //库存数量
                    BigDecimal quantity = BigDecimal.ZERO;
                    //订单类型
                    List<String> orderType = new ArrayList<String>();
                    //订单号
                    List<String> orderCode = new ArrayList<String>();
                    //订单数量
                    List<BigDecimal> orderQuantity = new ArrayList<BigDecimal>();
                    //在途数量
                    BigDecimal onWayQuantity = BigDecimal.ZERO;

                    /**
                     * 用于过滤采购订单
                     */
                    HashSet purchaseSet = new HashSet();
                    //封装数据
                    List<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrderDetailMapper.selectOrderByGoodsIdAndEnterpriseId(vo.getEnterPriseId(), vo.getGoodsId());
                    if (purchaseOrderDetails != null && purchaseOrderDetails.size() > 0){
                        for (PurchaseOrderDetail detail : purchaseOrderDetails) {
                            //当前采购明细的采购订单ID
                            Long orderId = detail.getOrderId();
                            purchaseSet.add(orderId);
                        }
                    }
                    if (purchaseSet.size() > 0){
                        for (Object purchase : purchaseSet) {
                            Long id = (Long) purchase;
                            orderType.add(OrderRule.PURCHASE_ORDER.getTypeName());
                            orderCode.add(purchaseOrderMapper.selectByPrimaryKey(id).getCode());
                            //Available parameters are [goodsId, id, enterPriseId, param3, param1, param2]
                            BigDecimal orderCount = purchaseOrderDetailMapper.selectCountByEnterPriseIdAndGoodsIdAndOrderId(vo.getEnterPriseId(),vo.getGoodsId(),id);
                            orderQuantity.add(orderCount);
                        }
                    }
                    /**
                     * 用于过滤配后退回订单
                     */
                    HashSet distrSet = new HashSet();
                    List<DistrReturnNoticeDetail> distrReturnNoticeDetails = distrReturnNoticeDetailMapper.selectByEnterPriseIdAndGoodsId(vo.getEnterPriseId(), vo.getGoodsId());
                    if (distrReturnNoticeDetails != null && distrReturnNoticeDetails.size() > 0){
                        for (DistrReturnNoticeDetail distrDetail : distrReturnNoticeDetails) {
                            Long noticeId = distrDetail.getNoticeId();
                            distrSet.add(noticeId);
                        }
                    }
                    if (distrSet.size() > 0){
                        for (Object distr : distrSet) {
                            Long id = (Long) distr;
                            orderType.add(OrderRule.DISTR_RETURN_NOTICE.getTypeName());
                             orderCode.add(distrReturnNoticeMapper.selectByPrimaryKey(id).getCode());
                            BigDecimal orderCount = distrReturnNoticeDetailMapper.selectCountByEnterPriseIdAndGoodsIdAndNoticeId(vo.getEnterPriseId(),vo.getGoodsId(),id);
                            orderQuantity.add(orderCount);
                        }
                    }
                    /**
                     * 更新VO数据
                     */
                    quantity = stockMapper.getQuantityTotalByGoodsId(vo.getGoodsId(),vo.getEnterPriseId());
                    vo.setQuantity(quantity == null ? BigDecimal.ZERO : quantity);
                    vo.setOrderType(orderType);
                    vo.setOrderCode(orderCode);
                    vo.setOrderQuantity(orderQuantity);
                    if (orderQuantity != null && orderQuantity.size() > 0){
                        for (BigDecimal b : orderQuantity) {
                            if (b == null){
                                onWayQuantity = onWayQuantity.add(BigDecimal.ZERO);
                            }else{
                                onWayQuantity = onWayQuantity.add(b);
                            }
                        }
                    }
                    vo.setOnWayQuantity(onWayQuantity);
                    statsticStockQuantity = statsticStockQuantity.add(quantity == null ? BigDecimal.ZERO : quantity);
                    statsticOnWayQuantity = statsticOnWayQuantity.add(onWayQuantity);
                }
            }
        }else {
            /**
             * 分部情况--只查询配进订单
             */
            list = stockMapper.selectSonOnWay(map);
            totalRecord = stockMapper.selectSonTotalRecord(map);
            if (list != null && list.size() > 0){
                for (OnWayGoodsPageVO vo : list) {
                    //库存数量
                    BigDecimal quantity = BigDecimal.ZERO;
                    //订单类型
                    List<String> orderType = new ArrayList<String>();
                    //订单号
                    List<String> orderCode = new ArrayList<String>();
                    //订单数量
                    List<BigDecimal> orderQuantity = new ArrayList<BigDecimal>();
                    //在途数量
                    BigDecimal onWayQuantity = BigDecimal.ZERO;

                    /**
                     * 过滤分店配进订单
                     */
                    HashSet distrIn = new HashSet();
                    //封装数据
                    List<DistrInNoticeDetail> detail = distrInNoticeDetailMapper.selectByGoodsIdAndEnterpriseId(vo.getGoodsId(),vo.getEnterPriseId());
                    if (detail != null && detail.size() > 0){
                        for (DistrInNoticeDetail d : detail) {
                            //当前采购明细的采购订单ID
                            Long orderId = d.getOrderId();
                            distrIn.add(orderId);
                        }
                    }
                    if (distrIn.size() > 0){
                        for (Object s : distrIn) {
                            Long id = (Long)s;
                            orderType.add(OrderRule.DISTR_IN_ORDER.getTypeName());
                            orderCode.add(distrInNoticeMapper.selectByPrimaryKey(id).getCode());
                            BigDecimal orderCount = distrInNoticeDetailMapper.selectCountByEnterPriseIdAndGoodsIdAndOrderId(vo.getEnterPriseId(),vo.getGoodsId(),id);
                            orderQuantity.add(orderCount);
                        }
                    }
                    /**
                     * 更新分店VO数据
                     */
                    quantity = stockMapper.getQuantityTotalByGoodsId(vo.getGoodsId(),vo.getEnterPriseId());
                    vo.setQuantity(quantity == null ? BigDecimal.ZERO : quantity);
                    vo.setOrderType(orderType);
                    vo.setOrderCode(orderCode);
                    vo.setOrderQuantity(orderQuantity);
                    if (orderQuantity != null && orderQuantity.size() > 0){
                        for (BigDecimal b : orderQuantity) {
                            if (b == null){
                                onWayQuantity = onWayQuantity.add(BigDecimal.ZERO);
                            }else{
                                onWayQuantity = onWayQuantity.add(b);
                            }
                        }
                    }
                    vo.setOnWayQuantity(onWayQuantity);
                    statsticStockQuantity = statsticStockQuantity.add(quantity == null ? BigDecimal.ZERO : quantity);
                    statsticOnWayQuantity = statsticOnWayQuantity.add(onWayQuantity);
                }
            }

        }
        pageVO.setQuantity(statsticStockQuantity);
        pageVO.setOnWayQuantity(statsticOnWayQuantity);
        page.setResult(list);
        page.setTotalRecord(totalRecord);
        pageVO.setPage(page);
        return pageVO;
    }

    @Override
    public List<OnWayGoodsPageVO> getOnWayStockExcel(String param, Long enterpriseId, String order, String sort, UserVO userVO, Integer chainType, String enterpriseCode, String enterpriseName) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("paran",param);
        map.put("enterpriseId",enterpriseId);
        map.put("sort",sort);
        map.put("order",order);
        map.put("chainType",chainType);
        map.put("loginEnterpriseId",userVO.getEnterpriseId());
        if(enterpriseCode != null) map.put("enterpriseCode", enterpriseCode.trim());
        if(enterpriseName != null) map.put("enterpriseName", enterpriseName.trim());
        //防止前端脏数据
        if(userVO.getChainType() != ChainType.Headquarters.getCode()) {
        	map.put("enterpriseCode",null);
            map.put("enterpriseName",null);
        }
        /**
         * 所有采购订单中在途状态的集合
         */
        List<Integer> purchaseStatus = new ArrayList<Integer>();
        purchaseStatus.add(PurchaseStatus.WAIT_RECEIVE.getStatus());
        purchaseStatus.add(PurchaseStatus.WAIT_CHECK.getStatus());
        purchaseStatus.add(PurchaseStatus.WAIT_STORAGE.getStatus());
        map.put("purchaseStatus",purchaseStatus);
        /**
         * 配后退回所有在途状态的集合
         */
        List<Integer> distrStatus = new ArrayList<>();
        distrStatus.add(DistrReturnStatus.WAIT_RECEIVE);
        distrStatus.add(DistrReturnStatus.WAIT_CHECK);
        distrStatus.add(DistrReturnStatus.WAIT_IN);
        map.put("distrStatus",distrStatus);
        /**
         * 分店配进订单状态的集合
         */
        List<Integer> distrInStatus = new ArrayList<Integer>();
        distrInStatus.add(DistrInStatus.WAIT_RECEIVE);
        distrInStatus.add(DistrInStatus.WAIT_CHECK);
        distrInStatus.add(DistrInStatus.WAIT_IN);
        map.put("distrInStatus",distrInStatus);
        //查询出的结果集List
        List<OnWayGoodsPageVO> list = new ArrayList<OnWayGoodsPageVO>();
        if (userVO.getChainType() == ChainType.Headquarters.getCode() && (chainType == null || chainType.equals(ChainType.Headquarters.getCode()))){
            /**
             * 总部情况--只查询采购订单 + 配后退回的未入库订单
             */
            list = stockMapper.selectFatherOnWay(map);
            if (list != null && list.size() > 0){
                for (OnWayGoodsPageVO vo : list) {
                    //库存数量
                    BigDecimal quantity = BigDecimal.ZERO;
                    //订单类型
                    List<String> orderType = new ArrayList<String>();
                    //订单号
                    List<String> orderCode = new ArrayList<String>();
                    //订单数量
                    List<BigDecimal> orderQuantity = new ArrayList<BigDecimal>();
                    //在途数量
                    BigDecimal onWayQuantity = BigDecimal.ZERO;

                    /**
                     * 用于过滤采购订单
                     */
                    HashSet purchaseSet = new HashSet();
                    //封装数据
                    List<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrderDetailMapper.selectOrderByGoodsIdAndEnterpriseId(vo.getEnterPriseId(), vo.getGoodsId());
                    if (purchaseOrderDetails != null){
                        for (PurchaseOrderDetail detail : purchaseOrderDetails) {
                            //当前采购明细的采购订单ID
                            Long orderId = detail.getOrderId();
                            purchaseSet.add(orderId);
                        }
                    }
                    if (purchaseSet.size() > 0){
                        for (Object purchase : purchaseSet) {
                            Long id = (Long) purchase;
                            orderType.add(OrderRule.PURCHASE_ORDER.getTypeName());
                            orderCode.add(purchaseOrderMapper.selectByPrimaryKey(id).getCode());
                            //Available parameters are [goodsId, id, enterPriseId, param3, param1, param2]
                            BigDecimal orderCount = purchaseOrderDetailMapper.selectCountByEnterPriseIdAndGoodsIdAndOrderId(vo.getEnterPriseId(),vo.getGoodsId(),id);
                            orderQuantity.add(orderCount);
                        }
                    }
                    /**
                     * 用于过滤配后退回订单
                     */
                    HashSet distrSet = new HashSet();
                    List<DistrReturnNoticeDetail> distrReturnNoticeDetails = distrReturnNoticeDetailMapper.selectByEnterPriseIdAndGoodsId(vo.getEnterPriseId(), vo.getGoodsId());
                    if (distrReturnNoticeDetails != null && distrReturnNoticeDetails.size() > 0){
                        for (DistrReturnNoticeDetail distrDetail : distrReturnNoticeDetails) {
                            Long noticeId = distrDetail.getNoticeId();
                            distrSet.add(noticeId);
                        }
                    }
                    if (distrSet.size() > 0){
                        for (Object distr : distrSet) {
                            Long id = (Long) distr;
                            orderType.add(OrderRule.DISTR_RETURN_NOTICE.getTypeName());
                            orderCode.add(distrReturnNoticeMapper.selectByPrimaryKey(id).getCode());
                            BigDecimal orderCount = distrReturnNoticeDetailMapper.selectCountByEnterPriseIdAndGoodsIdAndNoticeId(vo.getEnterPriseId(),vo.getGoodsId(),id);
                            orderQuantity.add(orderCount);
                        }
                    }
                    /**
                     * 更新VO数据
                     */
                    quantity = stockMapper.getQuantityTotalByGoodsId(vo.getGoodsId(),vo.getEnterPriseId());
                    vo.setQuantity(quantity == null ? BigDecimal.ZERO : quantity);
                    vo.setOrderType(orderType);
                    vo.setOrderCode(orderCode);
                    vo.setOrderQuantity(orderQuantity);
                    if (orderQuantity != null && orderQuantity.size() > 0){
                        for (BigDecimal b : orderQuantity) {
                            if (b == null){
                                onWayQuantity = onWayQuantity.add(BigDecimal.ZERO);
                            }else{
                                onWayQuantity = onWayQuantity.add(b);
                            }
                        }
                    }
                    vo.setOnWayQuantity(onWayQuantity);
                }
            }


        }else {
            /**
             * 分部情况--只查询配进订单
             */
            list = stockMapper.selectSonOnWay(map);
            if (list != null && list.size() > 0){
                for (OnWayGoodsPageVO vo : list) {
                    //库存数量
                    BigDecimal quantity = BigDecimal.ZERO;
                    //订单类型
                    List<String> orderType = new ArrayList<String>();
                    //订单号
                    List<String> orderCode = new ArrayList<String>();
                    //订单数量
                    List<BigDecimal> orderQuantity = new ArrayList<BigDecimal>();
                    //在途数量
                    BigDecimal onWayQuantity = BigDecimal.ZERO;

                    /**
                     * 过滤分店配进订单
                     */
                    HashSet distrIn = new HashSet();
                    //封装数据
                    List<DistrInNoticeDetail> detail = distrInNoticeDetailMapper.selectByGoodsIdAndEnterpriseId(vo.getGoodsId(),vo.getEnterPriseId());
                    if (detail != null && detail.size() > 0){
                        for (DistrInNoticeDetail d : detail) {
                            //当前采购明细的采购订单ID
                            Long orderId = d.getOrderId();
                            distrIn.add(orderId);
                        }
                    }
                    if (distrIn.size() > 0){
                        for (Object s : distrIn) {
                            Long id = (Long)s;
                            orderType.add(OrderRule.DISTR_IN_ORDER.getTypeName());
                            orderCode.add(distrInNoticeMapper.selectByPrimaryKey(id).getCode());
                            BigDecimal orderCount = distrInNoticeDetailMapper.selectCountByEnterPriseIdAndGoodsIdAndOrderId(vo.getEnterPriseId(),vo.getGoodsId(),id);
                            orderQuantity.add(orderCount);
                        }
                    }
                    /**
                     * 更新分店VO数据
                     */
                    quantity = stockMapper.getQuantityTotalByGoodsId(vo.getGoodsId(),vo.getEnterPriseId());
                    vo.setQuantity(quantity == null ? BigDecimal.ZERO : quantity);
                    vo.setOrderType(orderType);
                    vo.setOrderCode(orderCode);
                    vo.setOrderQuantity(orderQuantity);
                    if (orderQuantity != null && orderQuantity.size() > 0){
                        for (BigDecimal b : orderQuantity) {
                            if (b == null){
                                onWayQuantity = onWayQuantity.add(BigDecimal.ZERO);
                            }else{
                                onWayQuantity = onWayQuantity.add(b);
                            }
                        }
                    }
                    vo.setOnWayQuantity(onWayQuantity);
                }
            }
        }
        return list;
    }

    @Override
    public void export(OutputStream output, List<OnWayGoodsPageVO> vo, UserVO userVO) {
        List<OnWayExcelVO> list = new ArrayList<OnWayExcelVO>();
        for (OnWayGoodsPageVO pageVO : vo) {
            List<String> orderCode = pageVO.getOrderCode();
            if (orderCode != null && orderCode.size() > 0){
                for (int i = 0; i < orderCode.size(); i++){
                    OnWayExcelVO excel = new OnWayExcelVO();
                    CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(pageVO,excel);
                    excel.setOrderType(pageVO.getOrderType().get(i));
                    excel.setOrderCode(pageVO.getOrderCode().get(i));
                    excel.setOrderQuantity(pageVO.getOrderQuantity().get(i));
                    list.add(excel);
                }
            }
        }
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("enterpriseCode", "组织机构编码");
        map.put("enterpriseName", "组织机构名称");
        map.put("goodsCode", "商品编码");
        map.put("goodsGenericName", "通用名称");
        map.put("dosageName", "剂型");
        map.put("goodsSpecification", "规格");
        map.put("manufacturer", "生产厂商");
        map.put("unitName", "单位");
        map.put("quantity","库存数量");
        map.put("orderType","订单类型");
        map.put("orderCode","订单单号");
        map.put("orderQuantity","订单数量");
        map.put("onWayQuantity","在途数量");
        List<String> name = new ArrayList<>();
        name.add(userVO.getEnterpriseName());
        name.add("在途库存");
        purchaseGeneralComponent.commExcelExport(output, map, list, name, new ArrayList<>(), "", false, new ArrayList<>());
    }
}
