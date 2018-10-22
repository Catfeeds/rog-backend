package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
import com.rograndec.feijiayun.chain.utils.reflect.ReflectUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by sulei on 2017/9/21
 * 会员管理公用导出模板
 */
@Component
public class MemberExcelComponent<T> {
    public void commExcelExport(OutputStream output, LinkedHashMap<String, String> rowHeaderList, List<T> ts, List<String> name){
        try {
            ExcelWriter writer = new ExcelWriter() {
                public void generate() throws Exception {
                    this.beginWorkSheet();
                    this.beginSheet();
                    int contextStartRowNum = createRowHeader(this,rowHeaderList.values(),name,rowHeaderList.size());
                    List<Integer> needStaticIndex = new ArrayList<Integer>();
                    if (ts != null){
                        for (int rowNum = 0; rowNum < ts.size(); rowNum++) {
                            // 插入新行
                            this.insertRow(contextStartRowNum ++);
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


    private Integer createRowHeader(ExcelWriter writer, Collection<String> rowHeaderList, List<String> name, int size) throws IOException {
        int RowNum = 0;
        // 插入第一行---XX药店XX清单
        for (String title:name) {
            writer.insertRow(RowNum ++);
            writer.createCell(size >> 1,title);
            writer.endRow();
        }
        // 插入数据行的序号
        writer.insertRow(RowNum ++);
        Object[] rows = rowHeaderList.toArray();
        writer.createCell(0,"序号");
        for(int i = 0 ; i < rows.length ; i ++){
            // 建立新单元格,索引值从0开始,表示第一列
            writer.createCell(i+1, rows[i].toString());
        }
        // 结束行
        writer.endRow();
        return RowNum;
    }
}
