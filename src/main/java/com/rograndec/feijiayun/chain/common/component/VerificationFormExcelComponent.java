package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.vo.VerificationFormDetailVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.vo.VerificationFormItemVO;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.vo.VerificationFormVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

@Component
public class VerificationFormExcelComponent<T> {


    public void commExcelExport(OutputStream output, LinkedHashMap<String, String> rowHeaderList, UserVO user, VerificationFormVO verificationFormVO) {

        try {
            ExcelWriter writer = new ExcelWriter() {
                public void generate() throws Exception {
                    Map<String, XSSFCellStyle> styleMap=this.getCellStyles();
                    short cellStrIndex = styleMap.get("cell_string").getIndex();
                    this.beginWorkSheet();
                    this.beginSheet();
                    //创建表头
                    createRowHeader(this, user, verificationFormVO, rowHeaderList);
                    //企业
                    //单据名称
                    //list表头
                    int contextStartRowNum = 4;

                    BigDecimal totalInQuantity=BigDecimal.ZERO;
                    BigDecimal totalVerificationQuantity=BigDecimal.ZERO;
                    BigDecimal totalUnverificationQuantity=BigDecimal.ZERO;
                    BigDecimal totalQuantity=BigDecimal.ZERO;

                    for (int i = 0; i < verificationFormVO.getVerificationFormItemVOList().size(); i++) {


                        VerificationFormItemVO verificationFormItemVO = verificationFormVO.getVerificationFormItemVOList().get(i);
                        // 插入新行
                        this.insertRow(contextStartRowNum++);
                        //序号
                        this.createCell(0, i+1,cellStrIndex);
                        int ii = 1;
                        //商品编码
                        this.createCell(ii++, verificationFormItemVO.getGoodsCode(),cellStrIndex);
                        //条形码
                        this.createCell(ii++, verificationFormItemVO.getBarcode(),cellStrIndex);
                        //通用名称
                        this.createCell(ii++, verificationFormItemVO.getGoodsGenericName(),cellStrIndex);
                        //商品名称
                        this.createCell(ii++, verificationFormItemVO.getGoodsName(),cellStrIndex);
                        //剂型
                        this.createCell(ii++, verificationFormItemVO.getDosageName(),cellStrIndex);
                        //规格
                        this.createCell(ii++, verificationFormItemVO.getGoodsSpecification(),cellStrIndex);
                        //单位
                        this.createCell(ii++, verificationFormItemVO.getUnitName(),cellStrIndex);
                        //生产厂商
                        this.createCell(ii++, verificationFormItemVO.getManufacturer(),cellStrIndex);
                        //产地
                        this.createCell(ii++, verificationFormItemVO.getGoodsPlace(),cellStrIndex);
                        //批准文号
                        this.createCell(ii++, verificationFormItemVO.getApprovalNumber(),cellStrIndex);
                        //批号
                        this.createCell(ii++, verificationFormItemVO.getLotNumber(),cellStrIndex);
                        //生产日期
                        this.createCell(ii++, DateUtils.DateToString(verificationFormItemVO.getProductDate(), DateUtils.FMT_DATE),cellStrIndex);
                        //有效期至
                        this.createCell(ii++, DateUtils.DateToString(verificationFormItemVO.getValidDate(), DateUtils.FMT_DATE),cellStrIndex);
                        //销售数量
                        this.createCell(ii++, verificationFormItemVO.getSaleQuantity().toString(),cellStrIndex);


                        for (int j = 0; j < verificationFormItemVO.getVerificationFormDetailList().size(); j++) {
                            VerificationFormDetailVO verificationFormDetailVO = verificationFormItemVO.getVerificationFormDetailList().get(j);
                            if (j > 0) {
                                this.insertRow(contextStartRowNum++);
                            }
                            int jj = ii;
                            //入库日期
                            this.createCell(jj++, DateUtils.DateToString(verificationFormDetailVO.getBaseOrderDate(), DateUtils.FMT_DATE));
                            //入库单号
                            this.createCell(jj++, verificationFormDetailVO.getBaseOrderCode());
                            //入库数量
                            this.createCell(jj++, verificationFormDetailVO.getInQuantity().toString());
                            //已核销数量
                            this.createCell(jj++, verificationFormDetailVO.getVerificationQuantity().toString());
                            //未核销数量
                            this.createCell(jj++, verificationFormDetailVO.getUnverificationQuantity().toString());
                            //本次核销数量
                            this.createCell(jj++, verificationFormDetailVO.getQuantity().toString());

                            totalInQuantity=totalInQuantity.add(verificationFormDetailVO.getInQuantity());
                            totalVerificationQuantity=totalVerificationQuantity.add(verificationFormDetailVO.getVerificationQuantity());
                            totalUnverificationQuantity=totalUnverificationQuantity.add(verificationFormDetailVO.getUnverificationQuantity());
                            totalQuantity=totalQuantity.add(verificationFormDetailVO.getQuantity());

                            this.endRow();
                            if (j + 1 > verificationFormItemVO.getVerificationFormDetailList().size()) {
                                for (int k = 0; k < 14; k++) {
                                    this.setMergeCell(contextStartRowNum - verificationFormItemVO.getVerificationFormDetailList().size(), k, contextStartRowNum, k);
                                }
                            }
                        }
                    }
                    this.insertRow(contextStartRowNum);
                    this.createCell(0, "合计");
                    this.createCell(17, totalInQuantity.toString());
                    this.createCell(18, totalVerificationQuantity.toString());
                    this.createCell(19, totalUnverificationQuantity.toString());
                    this.createCell(20, totalQuantity.toString());
                    this.endRow();
                    // 电子表格结束
                    this.endSheet();
	                //合并单元格
                    this.beginMergerCell();
                    //合并企业名称单元格
                    this.setMergeCell(0, 0, 0, 20);
                    //合并单据名称单元格
                    this.setMergeCell(1, 0, 1, 20);
                    //合并主表信息单元格
                    for(int i=0;i<7;i++){
                        this.setMergeCell(2, i*2, 2, i*2+1);
                    }
                    //合并二级表信息单元格
                    int beginRow=4; int endRow=0;int endMax=15;
                    for (int i = 0; i < verificationFormVO.getVerificationFormItemVOList().size(); i++) {
                        VerificationFormItemVO verificationFormItemVO=verificationFormVO.getVerificationFormItemVOList().get(i);
                        int detailListSize=verificationFormItemVO.getVerificationFormDetailList().size();
                        endRow=beginRow+detailListSize-1;
                        for(int j=0;j<endMax;j++){
                            this.setMergeCell(beginRow, j, endRow, j);
                        }
                        beginRow=endRow+1;
                    }

                    this.endMergerCell();
                    this.endWorkSheet();
                }
            };
            writer.processForVerificationForm(output,(short)21);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void createRowHeader(ExcelWriter writer, UserVO user, VerificationFormVO verificationFormVO, LinkedHashMap<String, String> rowHeaderList) throws IOException {
        Map<String, XSSFCellStyle> styleMap=writer.getCellStyles();
//        short cellStrIndex = styleMap.get("cell_string").getIndex();
//        short cellStringBorder = styleMap.get("cell_string_border").getIndex();
        short sheetTitle = styleMap.get("sheet_title").getIndex();

        //插入第一行
        writer.insertRow(0);
        writer.createCell(0, user.getEnterpriseName(),sheetTitle);
        writer.endRow();
        //插入第二行
        writer.insertRow(1);
        writer.createCell(0, "实销实结核销单",sheetTitle);
        writer.endRow();
//		 供货单位编码：                            供货单位名称：                           核销日期：                             核销人员：
// 销售日期（从）：                销售日期（至）：                        单号：                             备注：
        //插入第三行
        writer.insertRow(2);
        writer.createCell(0, "组织机构编码:" + user.getEnterpriseCode());
        writer.createCell(2, "组织机构名称:" + user.getEnterpriseName());
        writer.createCell(4, "核销日期:" + DateUtils.DateToString(verificationFormVO.getOrderDate(), DateUtils.FMT_DATE));
        writer.createCell(6, "计划人员:" + (verificationFormVO.getOperatorName() == null ? "" : verificationFormVO.getOperatorName()));
        writer.createCell(8, " 销售日期（从）:" + DateUtils.DateToString(verificationFormVO.getStartSaleDate(), DateUtils.FMT_DATE));
        writer.createCell(10, "销售日期（至）:" + DateUtils.DateToString(verificationFormVO.getEndSaleDate(), DateUtils.FMT_DATE));
        writer.createCell(12, "单号:" + verificationFormVO.getCode());
        writer.createCell(14, "备注:" + verificationFormVO.getRemark());

        writer.endRow();
        //插入第四行作为数据list的表头
        writer.insertRow(3);
        int colum = 0;
        writer.createCell(colum++, "序号");
        for (Map.Entry<String, String> entry : rowHeaderList.entrySet()) {
            writer.createCell(colum++, entry.getValue());
        }
        writer.endRow();

    }

}
