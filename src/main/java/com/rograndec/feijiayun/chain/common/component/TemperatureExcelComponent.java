package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.business.storage.storehouse.vo.ResponseTempHumVO;
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
 * Created by dongdong.zhang@rograndec.com on 2017/9/28
 * 会员管理公用导出模板
 */
@Component
public class TemperatureExcelComponent<T> {
	
	
	
	 public void commExcelExport(OutputStream output, ResponseTempHumVO responseVO,LinkedHashMap<String, String> rowHeaderList, List<T> ts,UserVO user){
	        try {
	            ExcelWriter writer = new ExcelWriter() {
	                public void generate() throws Exception {
	                	this.beginWorkSheet();
	                    this.beginSheet();
	                    int contextStartRowNum =6;
	                    createRowHeader(this,responseVO,user);
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
	                               /* if(objValue instanceof Date){
	                                	objValue=DateUtils.DateToString(responseVO.getRecordDate(), DateUtils.FMT_DATE_TIME).substring(11);
	                                }*/
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
		 CellRangeAddress region1=new CellRangeAddress(0,0,0,12);
		 //合并第二行
		 CellRangeAddress region2=new CellRangeAddress(1,1,0,12);
		 /* //合并第三行
			 CellRangeAddress region3=new CellRangeAddress(2,2,1,2);*/
		 //合并第三行
		 CellRangeAddress region3=new CellRangeAddress(2,2,0,12);
		 //合并第四行操作
		 CellRangeAddress region41=new CellRangeAddress(3,5,0,0);
		 CellRangeAddress region42=new CellRangeAddress(3,3,1,6);
		 CellRangeAddress region43=new CellRangeAddress(3,3,7,12);
		 //合并第五行操作
		 CellRangeAddress region50=new CellRangeAddress(4,5,1,1);
		 CellRangeAddress region51=new CellRangeAddress(4,5,2,2);
		 CellRangeAddress region52=new CellRangeAddress(4,5,3,3);
		 CellRangeAddress region53=new CellRangeAddress(4,5,4,4);
		 CellRangeAddress region54=new CellRangeAddress(4,4,5,6);
		 CellRangeAddress region55=new CellRangeAddress(4,5,7,7);
		 CellRangeAddress region56=new CellRangeAddress(4,5,8,8);
		 CellRangeAddress region57=new CellRangeAddress(4,5,9,9);
		 CellRangeAddress region58=new CellRangeAddress(4,5,10,10);
		 CellRangeAddress region59=new CellRangeAddress(4,4,11,12);
		 return new CellRangeAddress[]{region1,region2,region3,region41,region42,region43,region50
				 ,region51,region52,region53,region54,region55,region56,region57,region58,region59};
	 }
	 
	 private void createRowHeader(ExcelWriter writer, ResponseTempHumVO responseVO,UserVO user) throws IOException{
		 //插入第一行
		 writer.insertRow(0);
		 writer.createCell(5,user.getEnterpriseName());
		 writer.endRow();
		 //插入第二行
		 writer.insertRow(1);
		 writer.createCell(5, "温湿度记录");
		 writer.endRow();
		//插入第三行
		 writer.insertRow(2);
		 StringBuffer content=new StringBuffer();
		 content.append("记录日期: ").append(DateUtils.DateToString(responseVO.getRecordDate(), DateUtils.FMT_DATE_TIME))
		 .append("  ").append("记录人员: ").append(responseVO.getRecordManName())
		 .append("  ").append("仓库: ").append(responseVO.getWarehouseName())
		 .append("  ").append("库区: ").append(responseVO.getWarehouseAreaName())
		 .append("  ").append("要求温度℃: ").append(responseVO.getTempLowLimit()+"-"+responseVO.getTempHighLimit())
		 .append("  ").append("要求相对湿度%: ").append(responseVO.getHumidityLowLimit()+"-"+responseVO.getHumidityHighLimit());
		 writer.createCell(0, content.toString());
		 writer.endRow();
		 //插入第四行
		 writer.insertRow(3);
		 writer.createCell(0, "序号");
		 writer.createCell(4, "上午");
		 writer.createCell(10, "下午");
		 writer.endRow();
		 //插入第五行
		 writer.insertRow(4);
		 writer.createCell(1, "时间");
		 writer.createCell(2, "温度℃");
		 writer.createCell(3, "相对湿度%");
		 writer.createCell(4, "调控措施");
		 writer.createCell(5, "采取调控措施后");
		 writer.createCell(7, "时间");
		 writer.createCell(8, "温度℃");
		 writer.createCell(9, "相对湿度%");
		 writer.createCell(10, "调控措施");
		 writer.createCell(11, "采取调控措施后");
		 writer.endRow();
		//插入第六行
		 writer.insertRow(5);
		 writer.createCell(5, "温度℃");
		 writer.createCell(6, "相对湿度%");
		 writer.createCell(11, "温度℃");
		 writer.createCell(12, "相对湿度%");
		 writer.endRow();
	 }
	 
}
