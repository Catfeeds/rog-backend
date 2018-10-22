package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.business.goods.price.vo.PriceOrderExcelVO;
import com.rograndec.feijiayun.chain.business.goods.price.vo.PriceOrderReturnVO;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpeningTaxDetailVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
import com.rograndec.feijiayun.chain.utils.reflect.ReflectUtils;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by dongdong.zhang@rograndec.com on 2018/01/27
 * 价格清单导出
 */
@Component
public class PriceExcelComponent<T> {

	public void commPriceOrderExcelExport(OutputStream output, LinkedHashMap<String, String> rowHeaderList, List<PriceOrderExcelVO> ts, UserVO user){
		try {
			ExcelWriter writer = new ExcelWriter() {
				public void generate() throws Exception {
					this.beginWorkSheet();
					this.beginSheet();
					int contextStartRowNum =1;
					//创建表头
					this.insertRow(0);
					int i = 0 ;
					this.createCell(i++,"序号");
					for(Map.Entry<String, String> entry : rowHeaderList.entrySet()){
						this.createCell(i++,entry.getValue());
					}
					this.endRow();
					if (ts != null&&!ts.isEmpty()){
						for (int rowNum = 0; rowNum < ts.size(); rowNum++) {
							// 插入新行
							this.insertRow(contextStartRowNum++);
							PriceOrderExcelVO t = ts.get(rowNum);
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
	
	
	
	 public void commExcelExport(OutputStream output,LinkedHashMap<String, String> rowHeaderList, List<T> ts,UserVO user,PriceOrderReturnVO priceOrderReturnVO,Integer sign){
	        try {
	            ExcelWriter writer = new ExcelWriter() {
	                public void generate() throws Exception {
	                	this.beginWorkSheet();
	                    this.beginSheet();
	                    int contextStartRowNum = createRowHeader(this,user,rowHeaderList,priceOrderReturnVO,sign); //创建表头
						Map<String, XSSFCellStyle> styleMap = this.getCellStyles();
						short cellStrIndex = styleMap.get("cell_string").getIndex();
	                    
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
	                                if(objValue instanceof  BigDecimal) {
	                                	objValue = OpeningTaxDetailVO.getTaxRateWithOutZero((BigDecimal)objValue);
	                                }
	                                this.createCell(k+1, null == objValue ? "" : objValue.toString(),cellStrIndex);
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
	 
	 private int createRowHeader(ExcelWriter writer,UserVO user,LinkedHashMap<String, String> rowHeaderList, PriceOrderReturnVO priceOrderReturnVO,Integer sign) throws IOException{
		int row = 0;
		if(sign == 0) {
			int size = rowHeaderList.size();
			size = size/2;
			//插入第一行
			writer.insertRow(row++);
			writer.createCell(size,priceOrderReturnVO.getEnterpriseName());
			writer.endRow();
			//插入第二行
			writer.insertRow(row++);
			writer.createCell(size, "价格清单");
			writer.endRow();
			//插入第三行
			writer.insertRow(row++);
			writer.createCell(0,"清单编码："+priceOrderReturnVO.getCode());
			writer.createCell(2,"清单名称："+priceOrderReturnVO.getName());
			writer.createCell(5,"基础价格清单："+priceOrderReturnVO.getParentOrderName());
			writer.createCell(8,"状态："+priceOrderReturnVO.getStatusDesc());
			writer.endRow();
		}
		 //插入第四行
		 writer.insertRow(row++);
		 int cellNum=0;
		 writer.createCell(cellNum++,"序号");
		 for(Map.Entry<String, String> entey:rowHeaderList.entrySet()){
			 writer.createCell(cellNum++,entey.getValue());
		 }
		 writer.endRow();
		 return row;
	 }
	 
}
