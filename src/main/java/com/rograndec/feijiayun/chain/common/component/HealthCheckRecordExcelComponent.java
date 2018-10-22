package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.business.report.quality.user.vo.ResponseHealthCheckVO;
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
 * Created by dongdong.zhang@rograndec.com on 2017/10/18
 * 会员管理公用导出模板
 */
@Component
public class HealthCheckRecordExcelComponent<T> {
	
	
	
	 public void commExcelExport(OutputStream output,LinkedHashMap<String, String> rowHeaderList, List<T> ts,UserVO user,ResponseHealthCheckVO responseHealthCheckVO){
	        try {
	            ExcelWriter writer = new ExcelWriter() {
	                public void generate() throws Exception {
	                	this.beginWorkSheet();
	                    this.beginSheet();
	                    int contextStartRowNum =7;
	                    //创建表头
	                    createRowHeader(this,user,responseHealthCheckVO,rowHeaderList);
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
	                                if(objValue instanceof Date){
	                                	objValue=DateUtils.DateToString((Date)objValue, DateUtils.FMT_DATE);
	                                 }
	                                this.createCell(k+1, null == objValue ? "" : objValue.toString());
	                            }
	                            // 结束行
	                            this.endRow();
	                        }
	                    }
	                    // 电子表格结束
	                    this.endSheet();
	               /*   //合并单元格
	                    this.beginMergerCell();
	                    *//**
	                     * 合并前两行头信息
	                     *//*
	                    this.setMergeCell(0, 0, 0, rowHeaderList.size());
	                    this.setMergeCell(1, 0, 1, rowHeaderList.size());
	                    this.endMergerCell();*/
	                    this.endWorkSheet();
	                }
	            };
	            CellRangeAddress[] regions=getRegions();
	            writer.process(output,regions);
	          /*  writer.process(output);*/
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 
	 private CellRangeAddress[]  getRegions(){
		 //合并第一行
		 CellRangeAddress region1=new CellRangeAddress(0,0,0,11);
		 //合并第二行
		 CellRangeAddress region2=new CellRangeAddress(1,1,0,11);
		 return new CellRangeAddress[]{region1,region2};
	 }
	 
	 private void createRowHeader(ExcelWriter writer,UserVO user,ResponseHealthCheckVO responseHealthCheckVO,LinkedHashMap<String, String> rowHeaderList) throws IOException{
		 //插入第一行
		 writer.insertRow(0);
		 writer.createCell(5,user.getEnterpriseName());
		 writer.endRow();
		 //插入第二行
		 writer.insertRow(1);
		 writer.createCell(5, "健康检查");
		 writer.endRow();
		 //插入第三行
		 writer.insertRow(2);
		 writer.createCell(0, "组织机构编码:"+responseHealthCheckVO.getEnterpriseCode());
		 writer.createCell(3, "组织机构名称:"+responseHealthCheckVO.getEnterpriseName());
		 writer.createCell(7, "日期:"+DateUtils.DateToString(responseHealthCheckVO.getPlanDate(),DateUtils.FMT_DATE));
		 writer.createCell(9, "计划人员:"+(responseHealthCheckVO.getPlanManName()==null?"":responseHealthCheckVO.getPlanManName()));
		 
		 writer.endRow();
		 //插入第四行
		 writer.insertRow(3);
		 writer.createCell(0, "编号:"+responseHealthCheckVO.getCode());
		 String startTime=DateUtils.DateToString(responseHealthCheckVO.getStartDate(),DateUtils.FMT_DATE);
		 writer.createCell(3, "开始日期:"+(startTime==null?"":startTime));
		 String endTime=DateUtils.DateToString(responseHealthCheckVO.getEndDate(),DateUtils.FMT_DATE);
		 writer.createCell(7, "结束日期:"+(endTime==null?"":endTime));
		 writer.createCell(9, "检查年度:"+responseHealthCheckVO.getPlanYear());
		 writer.endRow();
		 //插入第五行
		 writer.insertRow(4);
		 writer.createCell(0, "检查机构:"+(responseHealthCheckVO.getCheckOrg()==null?"":responseHealthCheckVO.getCheckOrg()));
		 writer.createCell(7, "检查类型:"+(responseHealthCheckVO.getCheckTypeName()==null?"":responseHealthCheckVO.getCheckTypeName()));
		 writer.endRow();
		 //插入第六行
		 writer.insertRow(5);
		 writer.createCell(0, "检查地址:"+(responseHealthCheckVO.getCheckPlace()==null?"":responseHealthCheckVO.getCheckPlace()));
		 writer.endRow();
		 
		 writer.insertRow(6);
		 int colum=0;
		 writer.createCell(colum++,"序号");
		 for(Map.Entry<String, String> entry : rowHeaderList.entrySet()){
			 writer.createCell(colum++,entry.getValue());
		 }
		 writer.endRow();
		 
	 }
	 
}
