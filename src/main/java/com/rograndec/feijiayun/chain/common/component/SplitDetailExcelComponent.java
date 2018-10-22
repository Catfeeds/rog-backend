package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.business.storage.split.vo.ResponseSplitVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
import com.rograndec.feijiayun.chain.utils.reflect.ReflectUtils;

import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by dongdong.zhang@rograndec.com on 2017/9/30
 * 会员管理公用导出模板
 */
@Component
public class SplitDetailExcelComponent<T> {
	
	
	
	 public void commExcelExport(OutputStream output, ResponseSplitVO responseVO,LinkedHashMap<String, String> rowHeaderList, List<T> ts,UserVO user){
	        try {
	            ExcelWriter writer = new ExcelWriter() {
	                public void generate() throws Exception {
	                	this.beginWorkSheet();
	                    this.beginSheet();
	                    int contextStartRowNum =createRowHeader(this, responseVO,user);
	                    if (ts != null&&!ts.isEmpty()){
	                        for (int rowNum = 0; rowNum < ts.size(); rowNum++) {
	                            // 插入新行
	                            this.insertRow(contextStartRowNum++);
	                            T t = ts.get(rowNum);
	                            Set<String> set = rowHeaderList.keySet();
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
	            CellRangeAddress[] regions=getRegions();
	            writer.process(output,regions);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 
	 private CellRangeAddress[]  getRegions(){
		 //合并第一行
		 CellRangeAddress region1=new CellRangeAddress(0,0,0,15);
		//合并第二行
		 CellRangeAddress region2=new CellRangeAddress(1,1,0,15);
		//合并第三行
		 CellRangeAddress region31=new CellRangeAddress(2,2,0,2);
		 CellRangeAddress region32=new CellRangeAddress(2,2,3,4);
		 CellRangeAddress region33=new CellRangeAddress(2,2,5,6);
		 CellRangeAddress region34=new CellRangeAddress(2,2,7,9);
		 CellRangeAddress region35=new CellRangeAddress(2,2,10,15);
		//合并第四行
		 CellRangeAddress region41=new CellRangeAddress(3,4,0,0);
		 CellRangeAddress region42=new CellRangeAddress(3,4,1,1);
		 CellRangeAddress region43=new CellRangeAddress(3,4,2,2);
		 CellRangeAddress region44=new CellRangeAddress(3,4,3,3);
		 CellRangeAddress region47=new CellRangeAddress(3,4,4,4);
		 CellRangeAddress region45=new CellRangeAddress(3,3,5,9);
		 CellRangeAddress region46=new CellRangeAddress(3,3,10,15);
		 return new CellRangeAddress[]{region1,region2,region31,region32,region33,region34,region35,region41,region42,region43,region44,region45,region46,region47};
	 }
	 
	 private int createRowHeader(ExcelWriter writer, ResponseSplitVO responseVO,UserVO user) throws IOException{
		 int row=0;
		 //插入第一行
		 writer.insertRow(row++);
		 writer.createCell(5, user.getEnterpriseName());
		 writer.endRow();
		 //插入第二行
		 writer.insertRow(row++);
		 writer.createCell(5, "药品拆零");
		 writer.endRow();
		 //插入第三行
		 writer.insertRow(row++);
		 writer.createCell(0,"拆零单号: "+(responseVO.getCode()==null?"":responseVO.getCode()));
		 writer.createCell(3,"分拆日期: "+(DateUtils.DateToString(responseVO.getSplitDate(), DateUtils.FMT_DATE_TIME)==null?"":DateUtils.DateToString(responseVO.getSplitDate(), DateUtils.FMT_DATE_TIME)));
		 writer.createCell(5,"拆零人员: "+(responseVO.getSplitManName()==null?"":responseVO.getSplitManName()));
		 writer.createCell(7,"复核人员: "+(responseVO.getAuditManName()==null?"":responseVO.getAuditManName()));
		 writer.createCell(10,"流通监管码: "+(responseVO.getFlowCode()==null?"":responseVO.getFlowCode()));
		 writer.endRow();
		 //插入第四行
		 writer.insertRow(row++);
		 writer.createCell(0, "序号");
		 writer.createCell(1, "商品编码");
		 writer.createCell(2, "通用名称");
		 writer.createCell(3, "剂型");
		 writer.createCell(4, "生产厂商");
		 writer.createCell(7, "整盒");
		 writer.createCell(12, "拆零");
		 writer.endRow();
		 //插入第五行
		 writer.insertRow(row++);
		 writer.createCell(5, "规格");
		 writer.createCell(6, "单位");
		 writer.createCell(7, "货位");
		 writer.createCell(8, "数量");
		 writer.createCell(9, "质量状况");
		 writer.createCell(10, "编码");
		 writer.createCell(11, "规格");
		 writer.createCell(12, "单位");
		 writer.createCell(13, "货位");
		 writer.createCell(14, "数量");
		 writer.createCell(15, "质量状况");
		 writer.endRow();
		 return row;
	 }
	 
}
