package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.utils.TwoTuple;
import com.rograndec.feijiayun.chain.utils.collection.SplitListUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
import com.rograndec.feijiayun.chain.utils.reflect.ReflectUtils;
import com.rograndec.feijiayun.chain.utils.string.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by zhaiwei on 2017/9/4.
 */
@Component
public class  ExcelComponent<T> {

    public void commExcelExport(OutputStream output, LinkedHashMap<String, String> rowHeaderList, List<T> ts){
        try {
            ExcelWriter writer = new ExcelWriter() {
                public void generate() throws Exception {
//
                    // 电子表格开始
                    this.beginWorkSheet();
                    this.beginSheet();
                    createRowHeader(this,rowHeaderList.values());
                    for (int rowNum = 0; rowNum < ts.size(); rowNum++) {
                        // 插入新行
                        this.insertRow(rowNum + 1);

                        T t = ts.get(rowNum);

                        Set set = rowHeaderList.keySet();
                        Object[] objSet = set.toArray();
                        //序号
                        this.createCell(0, rowNum+1);
                        // 建立新单元格,索引值从0开始,表示第一列
                        for(int k = 0 ; k < objSet.length ; k ++){

                            Object objValue = ReflectUtils.getValueOfGet(t,objSet[k].toString());
                            this.createCell(k+1, null == objValue ? "" : objValue.toString());
                        }


                        // 结束行
                        this.endRow();

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


    private void createRowHeader(ExcelWriter writer, Collection<String> rowHeaderList) throws IOException {
        // 插入新行
        writer.insertRow(0);
        Object[] rows = rowHeaderList.toArray();
        writer.createCell(0,"序号");
        for(int i = 0 ; i < rows.length ; i ++){
            // 建立新单元格,索引值从0开始,表示第一列

            writer.createCell(i+1, rows[i].toString());
        }

        // 结束行
        writer.endRow();
    }
    private void createRowHeader(ExcelWriter writer, Collection<String> rowHeaderList,int rownum,Map<String, XSSFCellStyle> styleMap) throws IOException {
        short cellStrIndex = styleMap.get("cell_string").getIndex();
        // 插入新行
        writer.insertRow(rownum);
        Object[] rows = rowHeaderList.toArray();
        writer.createCell(0,"序号",cellStrIndex);
        for(int i = 0 ; i < rows.length ; i ++){
            // 建立新单元格,索引值从0开始,表示第一列

            writer.createCell(i+1, rows[i].toString(),cellStrIndex);
        }

        // 结束行
        writer.endRow();
    }

    /**
     *
     * @param output
     * @param firstTitle 第一行标题
     * @param secondTitle 第二行标题
     * @param subHeadingList 副标题内容
     * @param rowHeaderList 表格的头部信息
     * @param contentDataList 表格的内容
     * @param totalFieldMap 合计的内容 key值与rowHeaderList的key相同；value 是合计的值
     * @throws Exception
     */
    public void exportExcel(OutputStream output,String firstTitle, String secondTitle, List<TwoTuple<String,String>> subHeadingList,
                     LinkedHashMap<String, String> rowHeaderList, List<T> contentDataList,Map<String,String> totalFieldMap) throws Exception {
        int columnNum = rowHeaderList.values().size();//列数
        int maxSubHeadingColNum = (int) Math.ceil((double) columnNum/2);//副标题最大显示列数


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
                this.createCell(0, firstTitle,cellStrIndex);
                this.endRow();
                //第二行
                this.insertRow(kk++);
                this.createCell(0, secondTitle,cellStrIndex);
                this.endRow();

                List<Integer> subHeadRowList = new ArrayList<>();//
                List<List> lists = new ArrayList<>();
                if(!CollectionUtils.isEmpty(subHeadingList)){
                    SplitListUtils splitListUtils = new SplitListUtils();
                    lists = splitListUtils.splitList(subHeadingList, maxSubHeadingColNum);
                    for(int i = 0; i < lists.size(); i++){
                        //副标题信息创建表格内容
                        int kkkk = kk++;
                        this.insertRow(kkkk);
                        subHeadRowList.add(kkkk);
                        int num = 0;
                        int jj = 0;
                        List<TwoTuple<String,String>> twoTupleList =  lists.get(i);
                        for(int j = num; j < twoTupleList.size(); j ++){
                            TwoTuple<String,String> twoTuple = twoTupleList.get(j);
                            String key = twoTuple.first;
                            String value = twoTuple.second;
                            this.createCell(jj, key +":" + StringUtil.transferTrimStr(value));
                            jj+=2;
                        }
                        this.endRow();
                    }

                }

                createRowHeader(this,rowHeaderList.values(),kk++, styleMap);
                for (int rowNum = 0; rowNum < contentDataList.size(); rowNum++) {
                    // 插入新行
                    this.insertRow(rowNum + kk);
                    T t = contentDataList.get(rowNum);
                    Set set = rowHeaderList.keySet();
                    Object[] objSet = set.toArray();
                    //序号
                    this.createCell(0, rowNum+1 +"",cellStrIndex);
                    // 建立新单元格,索引值从0开始,表示第一列
                    for(int k = 0 ; k < objSet.length ; k ++){
                        Object objValue = ReflectUtils.getValueOfGet(t,objSet[k].toString());
                        this.createCell(k+1, StringUtil.transferTrimStr(objValue),cellStrIndex);
                    }
                    // 结束行
                    this.endRow();

                }

                if(!CollectionUtils.isEmpty(totalFieldMap)){
                    //合计
                    this.insertRow(contentDataList.size() + kk);
                    this.createCell(0, "合计",cellStrIndex);
                    Set set = rowHeaderList.keySet();
                    Set<String> strings = totalFieldMap.keySet();
                    Object[] objSet = set.toArray();
                    for(int k = 0 ; k < objSet.length ; k ++){
                        String key = (String) objSet[k];
                        Iterator<String> iterator = strings.iterator();
                        while (iterator.hasNext()){
                            String next = iterator.next();
                            if(key.equals(next)){
                                this.createCell(k+1, StringUtil.transferTrimStr(totalFieldMap.get(next)),cellStrIndex);
                            }
                        }
                    }
                    this.endRow();
                }

                // 电子表格结束
                this.endSheet();
//                合并单元格
                this.beginMergerCell();

                this.setMergeCell(0, 0, 0, columnNum);
                this.setMergeCell(1, 0, 1, columnNum);

                //副标题单元格合并
                if(!CollectionUtils.isEmpty(subHeadRowList)){
                    for(int i = 0; i < subHeadRowList.size(); i++){
                        //副标题信息创建表格内容
                        Integer row = subHeadRowList.get(i);
                        int jj = 0;
                        List<TwoTuple<String,String>> twoTupleList =  lists.get(i);
                        for(int j = 0; j < twoTupleList.size(); j ++){
                            this.setMergeCell(row,jj,row,jj + 1);
                            jj+=2;
                        }
                    }
                }
                this.endMergerCell();
                this.endWorkSheet();
            }
        };
        writer.process(output);

    }


    public static void main(String[] args) throws Exception {


        ExcelComponent excelComponent = new ExcelComponent();

        String firstTitle = "第一行标题";
        String secondTitle = "第二行标题";

        //副标题内容
        List<TwoTuple<String,String>> twoTupleList = new ArrayList<>(Arrays.asList(new TwoTuple<String,String>("key1","value1"),
                new TwoTuple<String,String>("key2","value2"),
                new TwoTuple<>("key3","value4")));

        LinkedHashMap<String, String> rowHeaderList = new LinkedHashMap<>();
        rowHeaderList.put("A","AAAAA");
        rowHeaderList.put("B","BBBBB");
        rowHeaderList.put("C","CCCCC");
        rowHeaderList.put("D","DDDDD");
        rowHeaderList.put("E","DDDDD");
        rowHeaderList.put("F","DDDDD");
        rowHeaderList.put("G","DDDDD");

        List<ExcelTest> contentDataList = new ArrayList<>();
        contentDataList.add(new ExcelTest("1","2","3","4"));
        contentDataList.add(new ExcelTest("21","22","23","24"));
        contentDataList.add(new ExcelTest("31","32","33","34"));
        contentDataList.add(new ExcelTest("41","42","43","44"));


        String file = "D:\\q1111111111111111111111.xlsx";
        FileOutputStream outputStream = new FileOutputStream(new File(file));
        excelComponent.exportExcel(outputStream,firstTitle,secondTitle,twoTupleList,rowHeaderList,contentDataList,null);

    }
}
