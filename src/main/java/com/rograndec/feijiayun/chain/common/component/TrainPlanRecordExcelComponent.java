package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.business.basic.user.entity.TrainPlan;
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
 * Created by dongdong.zhang@rograndec.com on 2017/10/17
 * 会员管理公用导出模板
 */
@Component
public class TrainPlanRecordExcelComponent<T> {
	
	
	
	 public void commExcelExport(OutputStream output,LinkedHashMap<String, String> rowHeaderList, List<T> ts,UserVO user,TrainPlan trainPlan){
	        try {
	            ExcelWriter writer = new ExcelWriter() {
	                public void generate() throws Exception {
	                	this.beginWorkSheet();
	                    this.beginSheet();
	                    int contextStartRowNum =7;
	                    //创建表头
	                    createRowHeader(this,user,trainPlan,rowHeaderList);
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
	 
	 private void createRowHeader(ExcelWriter writer,UserVO user,TrainPlan trainPlan,LinkedHashMap<String, String> rowHeaderList) throws IOException{
		 //插入第一行
		 writer.insertRow(0);
		 writer.createCell(5,user.getEnterpriseName());
		 writer.endRow();
		 //插入第二行
		 writer.insertRow(1);
		 writer.createCell(5, "培训记录");
		 writer.endRow();
		 //插入第三行
		 writer.insertRow(2);
		 writer.createCell(0, "组织机构编码:"+trainPlan.getEnterpriseCode());
		 writer.createCell(3, "组织机构名称:"+trainPlan.getEnterpriseName());
		 writer.createCell(7, "日期:"+DateUtils.DateToString(trainPlan.getPlanDate(),DateUtils.FMT_DATE));
		 writer.createCell(9, "计划人员:"+trainPlan.getPlannerName());
		 writer.createCell(11, "年度:"+(trainPlan.getPlanYear()==null?"":trainPlan.getPlanYear()));
		 writer.endRow();
		 //插入第四行
		 writer.insertRow(3);
		 writer.createCell(0, "编号:"+trainPlan.getCode());
		 String startTime=DateUtils.DateToString(trainPlan.getStartDate(),DateUtils.FMT_DATE);
		 writer.createCell(3, "开始日期:"+(startTime==null?"":startTime));
		 String endTime=DateUtils.DateToString(trainPlan.getEndDate(),DateUtils.FMT_DATE);
		 writer.createCell(7, "结束日期:"+(endTime==null?"":endTime));
		 writer.endRow();
		 //插入第五行
		 writer.insertRow(4);
		 writer.createCell(0, "标题:"+(trainPlan.getPlanTitle()==null?"":trainPlan.getPlanTitle()));
		 /*"培训类型（0-岗前培训；1-继续培训）"*/
		 String trainType="";
		 if(trainPlan.getTrainType()!=null){
			 switch(trainPlan.getTrainType()){
			 case 0:trainType="岗前培训";break;
			 case 1:trainType="继续培训";break;
			 }
			 writer.createCell(7, "培训类型:"+trainType);
		 }else{
			 writer.createCell(7, "培训类型:"+trainType);
		 }
		 writer.endRow();
		 //插入第六行
		 writer.insertRow(5);
		/* 培训内容（0-法律法规；1-药品专业知识及技能；2-质量管理制度；3-职责及岗位操作规程）*/
		 String trainContent="";
		 if(trainPlan.getTrainContent()!=null){
			 switch(trainPlan.getTrainContent()){
			 case 0:trainContent="法律法规";break;
			 case 1:trainContent="药品专业知识及技能"; break;
			 case 2:trainContent="质量管理制度";break;
			 case 3:trainContent="职责及岗位操作规程";break;
			 }
			 writer.createCell(0, "培训内容:"+trainContent);
		 }else{
			 writer.createCell(0, "培训内容:"+trainContent);
		 }
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
