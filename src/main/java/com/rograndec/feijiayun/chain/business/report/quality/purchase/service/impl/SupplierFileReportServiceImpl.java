package com.rograndec.feijiayun.chain.business.report.quality.purchase.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierDetailVO;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderMapper;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.SupplierFileReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.RequestParamSupplierReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.SupplierFileReportDetailVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.SupplierQualificationReportVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.ExcelComponent;
import com.rograndec.feijiayun.chain.common.component.SupplierComponent;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
import com.rograndec.feijiayun.chain.utils.param.ParamUtils;
import com.rograndec.feijiayun.chain.utils.string.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 * Created by ST on 2017/10/19 13:44
 */
@Service
public class SupplierFileReportServiceImpl implements SupplierFileReportService{

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private ExcelComponent excelComponent;

    @Autowired
    private SupplierComponent supplierComponent;


    @Override
    public void getSupplierFileList(Page<List<SupplierFileReportDetailVO>> page, RequestParamSupplierReportVO supplierReportVO, UserVO userVO){
        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        ParamUtils.packParam(userVO,supplierReportVO);
        List<SupplierFileReportDetailVO> supplierFileReportDetailVOS = getSupplierFileReportDetailVOS(supplierReportVO, userVO);
        page.setResult(supplierFileReportDetailVOS);
        page.setTotalRecord((int) objects.getTotal());
    }

    private List<SupplierFileReportDetailVO> getSupplierFileReportDetailVOS(RequestParamSupplierReportVO supplierReportVO, UserVO userVO) {

        Integer status = EnableStatus.ENABLE.getStatus();
        List<SupplierQualificationReportVO> reportVOS = supplierMapper.getSupplierQualificationReportList(supplierReportVO, status);
        List<SupplierFileReportDetailVO> supplierFileReportDetailVOS = new ArrayList<>();
        reportVOS.forEach(item->{
            Long id = item.getId();
            supplierFileReportDetailVOS.add(getSupplierFileDetail(userVO,id));
        });
        return supplierFileReportDetailVOS;
    }

    /**
     *
     * @param userVO
     * @param supplierId
     */
    @Override
    public SupplierFileReportDetailVO getSupplierFileDetail(UserVO userVO, Long supplierId){
        SupplierDetailVO detailSupplier = supplierComponent.refactorSupplierDetail(supplierId);
        SupplierFileReportDetailVO reportDetailVO = new SupplierFileReportDetailVO();
        BeanUtils.copyProperties(detailSupplier,reportDetailVO);
        //采购订单统计
        Long enterpriseId = detailSupplier.getEnterpriseId();
        SupplierFileReportDetailVO purchaseOrderCount2Supplier = purchaseOrderMapper.getPurchaseOrderCount2Supplier(supplierId, enterpriseId,  PurchaseStatus.CANCELED.getStatus());
       //采购订单品种统计
        Long purchaseOrderVarietiesQuantity2Supplier = purchaseOrderMapper.getPurchaseOrderVarietiesQuantity2Supplier(supplierId, enterpriseId,  PurchaseStatus.CANCELED.getStatus());

        reportDetailVO.setOrderCount(purchaseOrderCount2Supplier.getOrderCount());
        reportDetailVO.setOrderAmountTotal(purchaseOrderCount2Supplier.getOrderAmountTotal());
        reportDetailVO.setOrderQuantityTotal(purchaseOrderCount2Supplier.getOrderQuantityTotal());
        reportDetailVO.setOrderVarietiesQuantity(purchaseOrderVarietiesQuantity2Supplier);


        //采购收货统计
        SupplierFileReportDetailVO purchaseReceiveCount2Supplier = purchaseOrderMapper.getPurchaseReceiveCount2Supplier(supplierId);
        //采购收货品种统计
        Long purchaseReceiveVarietiesQuantity2Supplier = purchaseOrderMapper.getPurchaseReceiveVarietiesQuantity2Supplier(supplierId);
        reportDetailVO.setReceiveRefuseCount(purchaseReceiveCount2Supplier.getReceiveRefuseCount());
        reportDetailVO.setReceiveRefuseQuantity(purchaseReceiveCount2Supplier.getReceiveRefuseQuantity());
        reportDetailVO.setReceiveRefuseVarietiesQuantity(purchaseReceiveVarietiesQuantity2Supplier);

        //采购验收统计
        SupplierFileReportDetailVO purchaseCheckStatistics2Supplier = purchaseOrderMapper.getPurchaseCheckStatistics2Supplier(supplierId);
        //采购验收品种统计
        Long purchaseCheckUnVarietiesQuantityStatistics2Supplier = purchaseOrderMapper.getPurchaseCheckUnVarietiesQuantityStatistics2Supplier(supplierId);

        reportDetailVO.setCheckUnqualifiedCount(purchaseCheckStatistics2Supplier.getCheckUnqualifiedCount());
        reportDetailVO.setCheckUnqualifiedQuantity(purchaseCheckStatistics2Supplier.getCheckUnqualifiedQuantity());
        reportDetailVO.setCheckVarietiesQuantity(purchaseCheckUnVarietiesQuantityStatistics2Supplier);

        //采购入库统计
        SupplierFileReportDetailVO purchaseInStorageStatistics2Supplier = purchaseOrderMapper.getPurchaseInStorageStatistics2Supplier(supplierId);
        //采购入库品种统计
        Long purchaseInStorageVarietiesQuantityStatistics2Supplier = purchaseOrderMapper.getPurchaseInStorageVarietiesQuantityStatistics2Supplier(supplierId);
        reportDetailVO.setInStorageCount(purchaseInStorageStatistics2Supplier.getInStorageCount());
        reportDetailVO.setInStorageQuantityTotal(purchaseInStorageStatistics2Supplier.getInStorageQuantityTotal());
        reportDetailVO.setInStorageAmountTotal(purchaseInStorageStatistics2Supplier.getInStorageAmountTotal());
        reportDetailVO.setInStorageVarietiesQuantity(purchaseInStorageVarietiesQuantityStatistics2Supplier);

        //购进退出出库统计
        SupplierFileReportDetailVO purchaseReturnOutStatistics2Supplier = purchaseOrderMapper.getPurchaseReturnOutStatistics2Supplier(supplierId);
        //购进退出出库品种统计
        Long purchaseReturnOutVarietiesQuantityStatistics2Supplier = purchaseOrderMapper.getPurchaseReturnOutVarietiesQuantityStatistics2Supplier(supplierId);
        reportDetailVO.setReturnOutCount(purchaseReturnOutStatistics2Supplier.getReturnOutCount());
        reportDetailVO.setReturnOutQuantityTotal(purchaseReturnOutStatistics2Supplier.getReturnOutQuantityTotal());
        reportDetailVO.setReturnOutAmountTotal(purchaseReturnOutStatistics2Supplier.getReturnOutAmountTotal());
        reportDetailVO.setReturnOutVarietiesQuantity(purchaseReturnOutVarietiesQuantityStatistics2Supplier);

        return reportDetailVO;

    }

    @Override
    public void exportSupplierFileList(OutputStream output, UserVO userVO, RequestParamSupplierReportVO paramForListVO) throws Exception {
        ParamUtils.packParam(userVO,paramForListVO);
        List<SupplierFileReportDetailVO> supplierFileReportDetailVOS = getSupplierFileReportDetailVOS(paramForListVO, userVO);

        String firstTitle = userVO.getEnterpriseName();
        String secondTitle = "供货单位质量档案";
        LinkedHashMap<String,String> headerHashMap = new LinkedHashMap<>();
        headerHashMap.put("natureName","企业类型");
        headerHashMap.put("code","供货单位编码");
        headerHashMap.put("name","供货单位名称");
        headerHashMap.put("groupName","分组");
        headerHashMap.put("economicTypeName","经济类型");
        headerHashMap.put("businessModeName","经营方式");
        headerHashMap.put("registeredCapital","注册资金");
        headerHashMap.put("regionalism","行政区域");
        headerHashMap.put("companyAddress","公司地址");
        headerHashMap.put("postcode","邮政编码");
        headerHashMap.put("tel","公司电话");
        headerHashMap.put("fax","公司传真");
        headerHashMap.put("email","公司邮箱");
        headerHashMap.put("site","公司网址");
        headerHashMap.put("storageAddress","仓库地址");
        headerHashMap.put("businessManName","企业负责人");
        headerHashMap.put("legalManName","法定代表人");
        headerHashMap.put("qualityOfficerName","质量负责人");
        headerHashMap.put("bankName","开户银行");
        headerHashMap.put("bankAccount","开户账号");
        headerHashMap.put("bankAccountName","开户户名");
        headerHashMap.put("businessVarietyName","经营品种");
        headerHashMap.put("businessScopeName","经营范围");

        headerHashMap.put("orderCount","订单笔数");
        headerHashMap.put("orderVarietiesQuantity","订单品种");
        headerHashMap.put("orderQuantityTotal","订单数量");
        headerHashMap.put("orderAmountTotal","订单金额");

        headerHashMap.put("receiveRefuseCount","拒收笔数");
        headerHashMap.put("receiveRefuseVarietiesQuantity","拒收品种");
        headerHashMap.put("receiveRefuseQuantity","拒收数量");

        headerHashMap.put("checkUnqualifiedCount","验证不合格笔数");
        headerHashMap.put("checkVarietiesQuantity","验证不合格品种");
        headerHashMap.put("checkUnqualifiedQuantity","验证不合格数量");

        headerHashMap.put("inStorageCount","入库笔数");
        headerHashMap.put("inStorageVarietiesQuantity","入库品种");
        headerHashMap.put("inStorageQuantityTotal","入库数量");
        headerHashMap.put("inStorageAmountTotal","入库金额");

        headerHashMap.put("returnOutCount","购进退出笔数");
        headerHashMap.put("returnOutVarietiesQuantity","购进退出品种");
        headerHashMap.put("returnOutQuantityTotal","购进退出数量");
        headerHashMap.put("returnOutAmountTotal","购进退出金额");

        excelComponent.exportExcel(output,firstTitle,secondTitle,new ArrayList<>(),headerHashMap,supplierFileReportDetailVOS,null);
    }

    @Override
    public void exportSupplierDetail(OutputStream output, UserVO userVO, Long supplierId) throws Exception {

        SupplierFileReportDetailVO supplierFileDetail = getSupplierFileDetail(userVO, supplierId);

        ExcelWriter writer = new ExcelWriter() {
            public void generate() throws Exception {

                int kk = 0;
                // 电子表格开始
                this.beginWorkSheet();
                this.beginSheet();
                Map<String, XSSFCellStyle> styleMap = this.getCellStyles();
                short cellStrIndex = styleMap.get("cell_string").getIndex();
                //第一行
                this.insertRow(kk++);
                this.createCell(0, "编码",cellStrIndex);
                this.createCell(1, StringUtil.transferTrimStr(supplierFileDetail.getCode()),cellStrIndex);
                this.createCell(2,"名称",cellStrIndex);
                this.createCell(3,StringUtil.transferTrimStr(supplierFileDetail.getName()),cellStrIndex);
                this.endRow();
//                //第二行
                this.insertRow(kk++);
                this.createCell(0, "类型",cellStrIndex);
                this.createCell(1, StringUtil.transferTrimStr(supplierFileDetail.getNatureName()),cellStrIndex);
                this.createCell(2,"注册资金",cellStrIndex);
                this.createCell(3,StringUtil.transferTrimStr(supplierFileDetail.getRegisteredCapital()),cellStrIndex);
                this.createCell(4,"行政区域",cellStrIndex);
                this.createCell(5,StringUtil.transferTrimStr(supplierFileDetail.getRegionalism()),cellStrIndex);
                this.endRow();
//                //第三行
                this.insertRow(kk++);
                this.createCell(0, "公司网址：",cellStrIndex);
                this.createCell(1, StringUtil.transferTrimStr(supplierFileDetail.getSite()),cellStrIndex);
                this.createCell(2, "公司地址：",cellStrIndex);
                this.createCell(3, StringUtil.transferTrimStr(supplierFileDetail.getCompanyAddress()),cellStrIndex);
                this.endRow();


                //第四行
                this.insertRow(kk++);
                this.createCell(0, "公司电话：" ,cellStrIndex);
                this.createCell(1,  StringUtil.transferTrimStr(supplierFileDetail.getTel()),cellStrIndex);
                this.createCell(2, "公司传真：",cellStrIndex);
                this.createCell(3, StringUtil.transferTrimStr(supplierFileDetail.getFax()),cellStrIndex);
                this.createCell(4, "公司邮箱：",cellStrIndex);
                this.createCell(5, StringUtil.transferTrimStr(supplierFileDetail.getEmail()),cellStrIndex);
                this.endRow();
                //第五行
                this.insertRow(kk++);
                this.createCell(0, "法定代表人：" ,cellStrIndex);
                this.createCell(1,StringUtil.transferTrimStr(supplierFileDetail.getLegalManName()),cellStrIndex);
                this.createCell(2, "企业负责人：" ,cellStrIndex);
                this.createCell(3,StringUtil.transferTrimStr(supplierFileDetail.getBusinessManName()),cellStrIndex);
                this.createCell(4, "质量负责人：",cellStrIndex);
                this.createCell(5,StringUtil.transferTrimStr(supplierFileDetail.getQualityOfficerName()),cellStrIndex);
                this.endRow();
                //第六行
                this.insertRow(kk++);
                this.createCell(0, "开户银行：" ,cellStrIndex);
                this.createCell(1,StringUtil.transferTrimStr(supplierFileDetail.getBankName()),cellStrIndex);
                this.createCell(2, "开户账户：" ,cellStrIndex);
                this.createCell(3,StringUtil.transferTrimStr(supplierFileDetail.getBankAccountName()),cellStrIndex);
                this.createCell(4, "开户户名：",cellStrIndex);
                this.createCell(5,StringUtil.transferTrimStr(supplierFileDetail.getBankAccount()),cellStrIndex);
                this.endRow();

                //第7行
                this.insertRow(kk++);
                this.createCell(0, "经营品种：" ,cellStrIndex);
                this.createCell(1,StringUtil.transferTrimStr(supplierFileDetail.getBusinessVarietyName()),cellStrIndex);
                this.createCell(2, "经营范围：" ,cellStrIndex);
                this.createCell(3,StringUtil.transferTrimStr(supplierFileDetail.getBusinessScopeName()),cellStrIndex);
                this.endRow();

                //第8行
                this.insertRow(kk++);
                this.createCell(0, "订单笔数" ,cellStrIndex);
                this.createCell(1,"订单品种",cellStrIndex);
                this.createCell(2, "订单数量",cellStrIndex);
                this.createCell(3,"订单金额",cellStrIndex);
                this.endRow();

                //第9行
                this.insertRow(kk++);
                this.createCell(0, StringUtil.transferTrimStr(supplierFileDetail.getOrderCount()),cellStrIndex);
                this.createCell(1,StringUtil.transferTrimStr(supplierFileDetail.getOrderVarietiesQuantity()),cellStrIndex);
                this.createCell(2, StringUtil.transferTrimStr(supplierFileDetail.getOrderQuantityTotal()),cellStrIndex);
                this.createCell(3,StringUtil.transferTrimStr(supplierFileDetail.getOrderAmountTotal()),cellStrIndex);
                this.endRow();

                //第10行
                this.insertRow(kk++);
                this.createCell(0, "拒收笔数",cellStrIndex );
                this.createCell(1,"拒收品种",cellStrIndex);
                this.createCell(2, "拒收数量",cellStrIndex);
                this.endRow();

                //第11行
                this.insertRow(kk++);
                this.createCell(0, StringUtil.transferTrimStr(supplierFileDetail.getReceiveRefuseCount()),cellStrIndex);
                this.createCell(1,StringUtil.transferTrimStr(supplierFileDetail.getReceiveRefuseVarietiesQuantity()),cellStrIndex);
                this.createCell(2, StringUtil.transferTrimStr(supplierFileDetail.getReceiveRefuseQuantity()),cellStrIndex);
                this.endRow();

                //第12行
                this.insertRow(kk++);
                this.createCell(0, "验收不合格笔数" ,cellStrIndex);
                this.createCell(1,"验收不合格品种",cellStrIndex);
                this.createCell(2, "验收不合格数量",cellStrIndex);
                this.endRow();

                //第13行
                this.insertRow(kk++);
                this.createCell(0, StringUtil.transferTrimStr(supplierFileDetail.getCheckUnqualifiedCount()),cellStrIndex);
                this.createCell(1,StringUtil.transferTrimStr(supplierFileDetail.getCheckVarietiesQuantity()),cellStrIndex);
                this.createCell(2, StringUtil.transferTrimStr(supplierFileDetail.getCheckUnqualifiedQuantity()),cellStrIndex);
                this.endRow();


                //第14行
                this.insertRow(kk++);
                this.createCell(0, "入库笔数",cellStrIndex );
                this.createCell(1,"入库品种",cellStrIndex);
                this.createCell(2, "入库数量",cellStrIndex);
                this.createCell(3,"入库金额",cellStrIndex);
                this.endRow();

                //第15行
                this.insertRow(kk++);
                this.createCell(0, StringUtil.transferTrimStr(supplierFileDetail.getInStorageCount()),cellStrIndex);
                this.createCell(1,StringUtil.transferTrimStr(supplierFileDetail.getInStorageVarietiesQuantity()),cellStrIndex);
                this.createCell(2, StringUtil.transferTrimStr(supplierFileDetail.getInStorageQuantityTotal()),cellStrIndex);
                this.createCell(3, StringUtil.transferTrimStr(supplierFileDetail.getInStorageAmountTotal()),cellStrIndex);
                this.endRow();

                //第16行
                this.insertRow(kk++);
                this.createCell(0, "购进退出笔数" ,cellStrIndex);
                this.createCell(1,"购进退出品种",cellStrIndex);
                this.createCell(2, "购进退出数量",cellStrIndex);
                this.createCell(3,"购进退出金额",cellStrIndex);
                this.endRow();

                //第17行
                this.insertRow(kk);
                this.createCell(0, StringUtil.transferTrimStr(supplierFileDetail.getReturnOutCount()),cellStrIndex);
                this.createCell(1,StringUtil.transferTrimStr(supplierFileDetail.getReturnOutVarietiesQuantity()),cellStrIndex);
                this.createCell(2, StringUtil.transferTrimStr(supplierFileDetail.getReturnOutQuantityTotal()),cellStrIndex);
                this.createCell(3, StringUtil.transferTrimStr(supplierFileDetail.getReturnOutAmountTotal()),cellStrIndex);
                this.endRow();

                // sheet表格结束
                this.endSheet();

//                合并单元格
                this.beginMergerCell();
                this.setMergeCell(0, 3, 0, 5);

                this.setMergeCell(2, 3, 2, 5);

                this.setMergeCell(6, 3, 6, 5);
                this.endMergerCell();
                this.endWorkSheet();


            }
        };
        writer.process(output);


    }


}
