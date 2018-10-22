package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.PurchaseInstorageDetailVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.PurchaseInstorageShelfVO;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
import com.rograndec.feijiayun.chain.utils.reflect.ReflectUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * author:lei su
 * time : 2017/9/15 17:30
 * todo:采购入库导出---包含合并单元格
 */
@Component
public class PurchaseExcelComponent<T> {
    public void commExcelExport(OutputStream output, LinkedHashMap<String, String> rowHeaderList, List ts, List<String> name, String titleSecond, String end){
        try {
            ExcelWriter writer = new ExcelWriter() {
                @Override
                public void generate() throws Exception {
                    this.beginWorkSheet();
                    this.beginSheet();
                    int rowNumTitle = createRowHeader(this,rowHeaderList.values(),name,titleSecond,rowHeaderList.size());
                    int detailRow = createRowListDetail(this,ts);
                    List<PurchaseInstorageDetailVO> pi = (List<PurchaseInstorageDetailVO>) ts;
                    if (ts != null){
                        for (int rowNum = 0; rowNum < ts.size(); rowNum++) {
                            /**
                             * 需要合并的Size
                             */
                            int mergeSize = pi.get(rowNum).getPurchaseInstorageShelfVO().size();
                            // 插入新行
                            if (rowNum == 0){
                                this.insertRow(rowNumTitle);
                            }else{
                                int size = pi.get(rowNum - 1).getPurchaseInstorageShelfVO().size();
                                this.insertRow(rowNumTitle + size);
                                rowNumTitle = rowNumTitle + size;
                            }
                            T t = (T)ts.get(rowNum);
                            Set set = rowHeaderList.keySet();
                            Object[] objSet = set.toArray();

                            this.createCell(0, rowNum + 1);
                            // 建立新单元格,索引值从0开始,表示第一列
                            for(int k = 0 ; k < objSet.length ; k ++){
                                Object objValue = ReflectUtils.getValueOfGet(t,objSet[k].toString());
                                this.createCell(k+1, null == objValue ? "" : objValue.toString());
                            }
                            // 结束行
                            this.endRow();
                        }
                    }
                    //插入尾部的信息()
                    creatRowEnd(this,rowNumTitle,detailRow,rowHeaderList,end);
                    // 电子表格结束
                    this.endSheet();
                    //合并单元格
                    this.beginMergerCell();
                    /**
                     * 合并前三行头信息
                     */
                    this.setMergeCell(0, 0, 0, rowHeaderList.size());
                    this.setMergeCell(1, 0, 1, rowHeaderList.size());
                    if (titleSecond != null && !titleSecond.equals("")){
                        this.setMergeCell(2, 0, 2, rowHeaderList.size());
                    }
                    this.endMergerCell();
                    this.endWorkSheet();
                }
            };
            writer.process(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void creatRowEnd(ExcelWriter excelWriter, int rowLine, int detailRow, LinkedHashMap<String, String> rowHeaderList, String end) throws IOException{
        int startEndRow = Math.max(rowLine,detailRow);
        String[] endContext = end.split(";");
        for (String s:endContext) {
            excelWriter.insertRow(startEndRow + 1);
            startEndRow ++;
            excelWriter.createCell(rowHeaderList.size() - 1,s);
            excelWriter.endRow();
        }

    }

    //插入详细信息
    private Integer createRowListDetail(ExcelWriter excelWriter, List<T> ts) throws IOException{
        //ts指代的时多条详细信息
        //详细信息包含多条货位信息
        int rowStart = 4;//从第三条信息开始输入详细信息
        List<PurchaseInstorageDetailVO> pi = (List<PurchaseInstorageDetailVO>) ts;
        if (pi != null && pi.size() > 0){
            //遍历每一个detail信息
            for (PurchaseInstorageDetailVO detail : pi) {
                List<PurchaseInstorageShelfVO> shelfList = detail.getPurchaseInstorageShelfVO();
                if (shelfList != null && shelfList.size() > 0){
                    for (int i = 0;i < shelfList.size(); i++) {
                        excelWriter.insertRow(rowStart);
                        if (shelfList.get(i).getJobArea() != null){
                            excelWriter.createCell(7,shelfList.get(i).getJobArea());
                        }
                        if (shelfList.get(i).getLotNumber() != null) {
                            excelWriter.createCell(8,shelfList.get(i).getLotNumber());
                        }
                        if (shelfList.get(i).getProductDate().toString() != null) {
                            excelWriter.createCell(9,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(shelfList.get(i).getProductDate()));
                        }
                        if (shelfList.get(i).getValidDate().toString() != null){
                            excelWriter.createCell(10,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(shelfList.get(i).getValidDate()));
                        }
                        if (shelfList.get(i).getShelfName() != null){
                            excelWriter.createCell(11,shelfList.get(i).getShelfName());
                        }
                        excelWriter.endRow();
                        rowStart++;
                    }
                }
            }

        }
        return rowStart;
    }

    private int createRowHeader(ExcelWriter writer, Collection<String> rowHeaderList, List<String> name, String titleSecond, int size) throws IOException {
        int rowNum = 0;
        Map<String, XSSFCellStyle> styleMap = writer.getCellStyles();
        short cellStrIndex = styleMap.get("cell_string").getIndex();
        // 插入第一行---XX药店XX店
        for (String title:name) {
            writer.insertRow(rowNum ++);
            writer.createCell(0,title,cellStrIndex);
            writer.endRow();
        }

        //插入第二行---供货单位信息
        writer.insertRow(rowNum ++);
        writer.createCell(0,titleSecond,cellStrIndex);
        writer.endRow();
        // 插入数据行
        writer.insertRow(rowNum ++);
        Object[] rows = rowHeaderList.toArray();
        writer.createCell(0,"序号");
        for(int i = 0 ; i < rows.length ; i ++){
            // 建立新单元格,索引值从0开始,表示第一列
            writer.createCell(i+1, rows[i].toString());
        }
        // 结束行
        writer.endRow();
        return rowNum;
    }

}
