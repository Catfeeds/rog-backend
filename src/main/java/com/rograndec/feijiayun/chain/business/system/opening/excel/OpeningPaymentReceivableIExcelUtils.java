package com.rograndec.feijiayun.chain.business.system.opening.excel;

import com.rograndec.feijiayun.chain.business.system.opening.vo.OpeningPaymentReceivableExcelVO;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpeningPaymentReceivableDetailVO;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpenningPaymentReceivableListVO;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
import com.rograndec.feijiayun.chain.utils.finance.DirectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * 期初应付（应收）的excel 操作类
 */
@Component
public class OpeningPaymentReceivableIExcelUtils {

    @Autowired
    private PurchaseGeneralComponent component;

    public void exportParityData(List<OpeningPaymentReceivableExcelVO> list, OutputStream output, String type) {
        try {
            ExcelWriter writer = new ExcelWriter() {
                @Override
                public void generate() throws Exception {
                    // 电子表格开始
                    this.beginWorkSheet();
                    this.beginSheet();
                    createRowHeader(this, type);
                    if (!CollectionUtils.isEmpty(list)) {
                        for (int rowNum = 0; rowNum < list.size(); rowNum++) {
                            OpeningPaymentReceivableExcelVO excelVO = list.get(rowNum);
                            // 插入新行
                            this.insertRow(rowNum + 1);
                            // 建立新单元格,索引值从0开始,表示第一列
                            int k = 0;
                            this.createCell(k++, excelVO.getCode());
                            this.createCell(k++, excelVO.getName());
                            this.createCell(k++, excelVO.getDirection());
                            this.createCell(k, excelVO.getAmount());
                            // 结束行
                            this.endRow();
                        }
                    }
                    // 电子表格结束
                    this.endSheet();
                    this.endWorkSheet();
                }
            };
            writer.process(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createRowHeader(ExcelWriter writer, String type) throws IOException {
        // 插入新行
        writer.insertRow(0);
        // 建立新单元格,索引值从0开始,表示第一列
        int k = 0;
        writer.createCell(k++, type + "货单位编码");
        writer.createCell(k++, type + "货单位名称");
        writer.createCell(k++, "方向");
        writer.createCell(k, "金额");
        // 结束行
        writer.endRow();
    }

    /**
     * 以表数据为准同步成功导入的excel数据和页面数据
     *
     * @param listVO
     * @param excelVOS
     * @param tableDetailVOS
     * @return
     */
    public OpenningPaymentReceivableListVO integration(OpenningPaymentReceivableListVO listVO, List<OpeningPaymentReceivableExcelVO> excelVOS, List<OpeningPaymentReceivableDetailVO> tableDetailVOS) {
        List<OpeningPaymentReceivableDetailVO> detailVOS = excel2DetailVO(excelVOS);
        if (!CollectionUtils.isEmpty(detailVOS)) {
            List<OpeningPaymentReceivableDetailVO> pageDetailVOS = listVO.getDetailVOS();
            Map<String, OpeningPaymentReceivableDetailVO> pageMap = OpeningPaymentReceivableDetailVO.getMap(pageDetailVOS);
            Map<String, OpeningPaymentReceivableDetailVO> excelMap = OpeningPaymentReceivableDetailVO.getMap(detailVOS);
            if (null != pageMap) {
                pageMap.putAll(excelMap);
            } else {
                pageMap = excelMap;
            }
            BigDecimal amountTotal = new BigDecimal(0.00);
            for (OpeningPaymentReceivableDetailVO tableDetailVO : tableDetailVOS) {
                OpeningPaymentReceivableDetailVO openingPaymentDetailVO = pageMap.get(tableDetailVO.getCode());
                if (null != openingPaymentDetailVO) {
                    String amount = openingPaymentDetailVO.getAmount();
                    tableDetailVO.setAmount(amount);
                    amountTotal = amountTotal.add(new BigDecimal(amount));
                }
            }
            listVO.setDetailVOS(tableDetailVOS);
            listVO.setAmountTotal(amountTotal);
        }
        return listVO;
    }

    /**
     * OpeningPaymentExcelVO转OpeningPaymentReceivableDetailVO
     *
     * @param list
     * @return
     */
    private List<OpeningPaymentReceivableDetailVO> excel2DetailVO(List<OpeningPaymentReceivableExcelVO> list) {
        List<OpeningPaymentReceivableDetailVO> detailVOS = null;
        if (!CollectionUtils.isEmpty(list)) {
            detailVOS = new ArrayList<>();
            for (OpeningPaymentReceivableExcelVO excelVO : list) {
                OpeningPaymentReceivableDetailVO detailVO = new OpeningPaymentReceivableDetailVO();
                detailVO.setCode(excelVO.getCode());
                detailVO.setName(excelVO.getName());
                detailVO.setAmount(getAmount(excelVO));
                detailVOS.add(detailVO);
            }
        }
        return detailVOS;
    }


    /**
     * OpeningPaymentReceivableDetailVO转OpeningPaymentExcelVO
     *
     * @param list
     * @return
     */
    private List<OpeningPaymentReceivableExcelVO> detailVO2Excel(List<OpeningPaymentReceivableDetailVO> list) {
        List<OpeningPaymentReceivableExcelVO> excelVOS = null;
        if (!CollectionUtils.isEmpty(list)) {
            excelVOS = new ArrayList<>();
            for (OpeningPaymentReceivableDetailVO detailVO : list) {
                OpeningPaymentReceivableExcelVO excelVO = new OpeningPaymentReceivableExcelVO();
                excelVO.setCode(detailVO.getCode());
                excelVO.setName(detailVO.getName());
                BigDecimal amount = new BigDecimal(detailVO.getAmount());
                excelVO.setAmount(DirectionUtils.setPositiveNumber(amount).toString());
                excelVO.setDirection(DirectionUtils.getDirection(amount));
                excelVOS.add(excelVO);
            }
        }
        return excelVOS;
    }

    /**
     * OpeningPaymentReceivableDetailVO转OpeningPaymentExcelVO
     *
     * @param list
     * @return
     */
    public List<OpeningPaymentReceivableExcelVO> detailVO2Excel(List<OpeningPaymentReceivableDetailVO> list, String direction) {
        List<OpeningPaymentReceivableExcelVO> excelVOS = null;
        if (!CollectionUtils.isEmpty(list)) {
            excelVOS = new ArrayList<>();
            for (OpeningPaymentReceivableDetailVO detailVO : list) {
                OpeningPaymentReceivableExcelVO excelVO = new OpeningPaymentReceivableExcelVO();
                excelVO.setCode(detailVO.getCode());
                excelVO.setName(detailVO.getName());
                excelVO.setAmount("0.00");
                excelVO.setDirection(direction);
                excelVOS.add(excelVO);
            }
        }
        return excelVOS;
    }

    private String getAmount(OpeningPaymentReceivableExcelVO excelVO) {
        String amount = null;
        switch (excelVO.getDirection()) {
            case "平":
                amount = "0.00";
                break;
            case "借":
                amount = excelVO.getAmount();
                break;
            case "贷":
                amount = "-" + excelVO.getAmount();
                break;
            default:
        }
        return amount;
    }

    public void exportData(OutputStream outputStream, List<OpeningPaymentReceivableDetailVO> detailVOS, String code,
                           String manName, Date date, String remark, BigDecimal amountTotal, Boolean flag, String enterpriseName) {
        List<OpeningPaymentReceivableExcelVO> excelVOS = detailVO2Excel(detailVOS);
        String type, name;
        /*
         * flag为true 期初应付，false 期初应收
         */
        if (flag) {
            type = "供货单位";
            name = "期初应付";
        } else {
            type = "购货单位";
            name = "期初应收";
        }
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("code", type + "编码");
        map.put("name", type + "名称");
        map.put("direction", "方向");
        map.put("amount", "金额");

        List<String> names = new ArrayList<>();
        names.add(enterpriseName);
        names.add(name);

        List<String> titles = new ArrayList<>();
        titles.add("单号：" + (code == null ? "" : code) + "  日期：" + (date == null ? "" : DateUtils.DateToString(date, DateUtils.FMT_DATE)) + "  人员：" + (manName == null ? "" : manName) + "  备注：" + (remark == null ? "" : remark));

        String end = ";";
        if (null != amountTotal) {
            end = DirectionUtils.getDirection(amountTotal) + ";" + amountTotal.abs().toString();
        }
        List<String> needTotalName = new ArrayList<>();
        needTotalName.add("direction");
        needTotalName.add("amount");
        component.commExcelExport(outputStream, map, excelVOS, names, titles, end, false, needTotalName);
    }
}