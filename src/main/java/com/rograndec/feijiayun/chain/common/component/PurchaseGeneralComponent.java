package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.common.ExcelPic;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
import com.rograndec.feijiayun.chain.utils.reflect.ReflectUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

@Component
public class PurchaseGeneralComponent<T> {


	/**
	 * @param output
	 * @param rowHeaderList 导出列名,key是英文列名,value是对应的中文列名
	 * @param ts            需要导出的数据,不要序号
	 * @param name          第一行的名字,文件名
	 * @param titleSecond   标题栏下的非列表的字段
	 * @param end           合计行 的值,以分号隔开
	 * @param flag          合计行如果是单行合计 false,多行合计 true
	 * @param needTotalName 如果flag为true传空的集合,如果false,传列名的英文名集合
	 */
	public void commExcelExport(OutputStream output, LinkedHashMap<String, String> rowHeaderList, List<T> ts, List<String> name, List<String> titleSecond, String end, boolean flag, List<String> needTotalName) {
		try {
			ExcelWriter writer = new ExcelWriter() {
				@Override
				public void generate() throws Exception {
					this.beginWorkSheet();
					this.beginSheet();
					int contextStartRowNum = createRowHeader(this, rowHeaderList.values(), name, titleSecond, rowHeaderList.size());
					List<Integer> needStaticIndex = new ArrayList<Integer>();
					if (ts != null) {
						for (int rowNum = 0; rowNum < ts.size(); rowNum++) {
							// 插入新行
							this.insertRow(contextStartRowNum ++);
							T t = ts.get(rowNum);
							Set set = rowHeaderList.keySet();
							Object[] objSet = set.toArray();
							//序号
							this.createCell(0, rowNum + 1);
							// 建立新单元格,索引值从0开始,表示第一列
							for (int k = 0; k < objSet.length; k++) {
								for (String s : needTotalName) {
									if (s.equals(objSet[k].toString())) {
										needStaticIndex.add(k + 1);
									}
								}
								Object objValue = ReflectUtils.getValueOfGet(t, objSet[k].toString());
								if (objValue instanceof Date) {
									Date date = (Date) objValue;
									String s = DateUtils.DateToString(date, DateUtils.FMT_DATE_TIME);
									this.createCell(k + 1, s);
								} else {
									this.createCell(k + 1, null == objValue ? "" : objValue.toString());
								}

							}
							// 结束行
							this.endRow();
						}
					}
					//插入尾部的信息()
					if (!StringUtils.isEmpty(end)) {
						createRowEnd(this, contextStartRowNum, rowHeaderList, end, flag, needStaticIndex);
					}

                    // 电子表格结束
                    this.endSheet();
                    //合并单元格
                    this.beginMergerCell();
                    this.setMergeCell(0, 0, 0, rowHeaderList.values().size());
                    this.setMergeCell(1, 0, 1, rowHeaderList.values().size());
                    if (titleSecond != null && titleSecond.size() > 0){
                        int titleSize = titleSecond.size();
                        for (int i = 2; i < (2 + titleSize); i ++){
                            this.setMergeCell(i, 0, i, rowHeaderList.size());
                        }
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
	
	/**
	 * @param output
	 * @param rowHeaderList 导出列名,key是英文列名,value是对应的中文列名
	 * @param ts            需要导出的数据,不要序号
	 * @param name          第一行的名字,文件名
	 * @param titleSecond   标题栏下的非列表的字段
	 * @param end           合计行 的值,以分号隔开
	 * @param flag          合计行如果是单行合计 false,多行合计 true
	 * @param needTotalName 如果flag为true传空的集合,如果false,传列名的英文名集合
	 */
	public void commMultipleExcelExport(OutputStream output, LinkedHashMap<String, String> rowHeaderList, List<T> ts, 
			List<String> name, List<String> titleSecond, String end, boolean flag, List<String> needTotalName) {
		try {
			ExcelWriter writer = new ExcelWriter() {
				@Override
				public void generate() throws Exception {
					this.beginWorkSheet();
					this.beginSheet();
					
					List<Integer> row = new ArrayList<>();
					
					List<Integer> needStaticIndex = new ArrayList<Integer>();
					if (ts != null) {
						
						int contextStartRowNum = 0;
						Map<String, XSSFCellStyle> styleMap = this.getCellStyles();
						short cellStringBorder = styleMap.get("cell_string_border").getIndex();
						
						contextStartRowNum = createRowHeader(this, null, name, null, rowHeaderList.size(), contextStartRowNum);
						
						for (int rowNum = 0; rowNum < ts.size(); rowNum++) {
							
							contextStartRowNum = createRowHeader(this, rowHeaderList.values(), null, titleSecond.get(rowNum), rowHeaderList.size(), contextStartRowNum);
							
							row.add(contextStartRowNum-2);
							
							// 插入新行
							T t = ts.get(rowNum);
							if(t instanceof List){
								List<T> dtlList = (List<T>) t;
								for (int i = 0; i < dtlList.size(); i++) {
									
									this.insertRow(contextStartRowNum ++);
									
									T dtl = dtlList.get(i);
									
									Set set = rowHeaderList.keySet();
									Object[] objSet = set.toArray();
									//序号
									this.createCell(0, i + 1, cellStringBorder);
									// 建立新单元格,索引值从0开始,表示第一列
									for (int k = 0; k < objSet.length; k++) {
										for (String s : needTotalName) {
											if (s.equals(objSet[k].toString())) {
												needStaticIndex.add(k + 1);
											}
										}
										Object objValue = ReflectUtils.getValueOfGet(dtl, objSet[k].toString());
										if (objValue instanceof Date) {
											Date date = (Date) objValue;
											String s = DateUtils.DateToString(date, DateUtils.FMT_DATE_TIME);
											this.createCell(k + 1, s, cellStringBorder);
										} else {
											this.createCell(k + 1, null == objValue ? "" : objValue.toString(), cellStringBorder);
										}
		
									}
									// 结束行
									this.endRow();
									
								}
							}	
							//插入尾部的信息()
							if (!StringUtils.isEmpty(end)) {
								contextStartRowNum = createMultipleRowEnd(this, contextStartRowNum, rowHeaderList, end, flag, needStaticIndex);
								row.add(contextStartRowNum-1);
								contextStartRowNum++;
							}
						}
					}

                    // 电子表格结束
                    this.endSheet();
                    //合并单元格
                    this.beginMergerCell();
                    this.setMergeCell(0, 0, 0, rowHeaderList.size());
                    this.setMergeCell(1, 0, 1, rowHeaderList.size());
                    
                    for (Integer r : row) {
                    	this.setMergeCell(r, 0, r, rowHeaderList.size());
					}
                    /*if (titleSecond != null && titleSecond.size() > 0){
                        int titleSize = titleSecond.size();
                        for (int i = 2; i < (2 + titleSize); i ++){
                            this.setMergeCell(i, 0, i, rowHeaderList.size());
                        }
                    }*/
                    this.endMergerCell();
                    this.endWorkSheet();
                }
            };
            writer.process(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * @param output
	 * @param name           文件名
	 * @param titleSecond   标题栏下的非列表的字段
	 * @param excelPic      excel需要的位置
	 */
	public void commExcelExportPic(OutputStream output, LinkedHashMap<String, String> rowHeaderList, List<T> ts, List<String> name, List<String> titleSecond, String end, boolean flag, List<String> needTotalName, ExcelPic excelPic) {
		try {
			ExcelWriter writer = new ExcelWriter() {
				@Override
				public void generate() throws Exception {
					this.beginWorkSheet();
					this.beginSheet();
					int contextStartRowNum = createRowHeader(this, rowHeaderList.values(), name, titleSecond, rowHeaderList.size());
					List<Integer> needStaticIndex = new ArrayList<Integer>();
					if (ts != null) {
						for (int rowNum = 0; rowNum < ts.size(); rowNum++) {
							// 插入新行
							this.insertRow(contextStartRowNum ++);
							T t = ts.get(rowNum);
							Set set = rowHeaderList.keySet();
							Object[] objSet = set.toArray();
							//序号
							this.createCell(0, rowNum + 1);
							// 建立新单元格,索引值从0开始,表示第一列
							for (int k = 0; k < objSet.length; k++) {
								for (String s : needTotalName) {
									if (s.equals(objSet[k].toString())) {
										needStaticIndex.add(k + 1);
									}
								}
								Object objValue = ReflectUtils.getValueOfGet(t, objSet[k].toString());
								if (objValue instanceof Date) {
									Date date = (Date) objValue;
									String s = DateUtils.DateToString(date, DateUtils.FMT_DATE_TIME);
									this.createCell(k + 1, s);
								} else {
									this.createCell(k + 1, null == objValue ? "" : objValue.toString());
								}

							}
							// 结束行
							this.endRow();
						}
					}
					//插入尾部的信息()
					if (!StringUtils.isEmpty(end)) {
						createRowEnd(this, contextStartRowNum, rowHeaderList, end, flag, needStaticIndex);
					}

					// 电子表格结束
					this.endSheet();
					//合并单元格
					this.beginMergerCell();
					this.setMergeCell(0, 0, 0, 10);
					this.setMergeCell(1, 0, 1, 10);
					if (titleSecond != null && titleSecond.size() > 0){
						int titleSize = titleSecond.size();
						for (int i = 2; i < (2 + titleSize); i ++){
							this.setMergeCell(i, 0, i, 10);
						}
					}
					this.endMergerCell();
					this.createPic(1);
					this.endWorkSheet();
				}
			};
			writer.process(output,excelPic);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * 只适用与投诉管理导出
     * @param output
     * @param rowUserList
     * @param rowGoodsList
     * @param name
     * @param titleSecond
     * @param titleOther
     * @param end
     * @param flag
     * @param needTotalName
     * @param ts
     * @param titleOtherTwo
     * @param titleOtherThree
     * @param titleOtherFour
     * @param titleOtherFive
     * @param titleOtherSix
     */
    public void commExcelExportByTrainPlan(OutputStream output, LinkedHashMap<String, String> rowUserList, LinkedHashMap<String, String> rowGoodsList,List<String> name, List<String> titleSecond, List<String> titleOther,String end, boolean flag, List<String> needTotalName, T ts,
                                           List<String> titleOtherTwo, List<String> titleOtherThree, List<String> titleOtherFour, List<String> titleOtherFive, List<String> titleOtherSix){
        try {
            ExcelWriter writer = new ExcelWriter() {
                @Override
                public void generate() throws Exception {
                    this.beginWorkSheet();
                    this.beginSheet();
                    Set setUser = rowUserList.keySet();
                    Object[] objSetUser = setUser.toArray();

                    Set setGoods = rowGoodsList.keySet();
                    Object[] objSetGoods = setGoods.toArray();

                    int contextStartRowNum = createRowHeaderByTrainPlan(this,rowUserList.values(),rowGoodsList.values(),name,titleSecond,titleOther,ts,objSetUser,objSetGoods,
                            titleOtherTwo,titleOtherThree, titleOtherFour, titleOtherFive, titleOtherSix);
                    List<Integer> needStaticIndex = new ArrayList<Integer>();

                    //插入尾部的信息()
                    if(!StringUtils.isEmpty(end)){
                        createRowEnd(this, contextStartRowNum, rowUserList, end, flag, needStaticIndex);
                    }
                    // 电子表格结束
                    this.endSheet();
                    //合并单元格
                    this.beginMergerCell();
                    this.setMergeCell(0, 0, 0, rowUserList.size());
                    this.setMergeCell(1, 0, 1, rowUserList.size());
                    this.setMergeCell(2, 0, 2, rowUserList.size());
                    this.setMergeCell(3, 0, 3, rowUserList.size());

                    this.setMergeCell(4, 0, 7, 0);
                    this.setMergeCell(8, 0, 10, 0);

                    this.setMergeCell(11, 0, 11, rowUserList.size());
                    this.setMergeCell(12, 0, 12, rowUserList.size());
                    this.setMergeCell(13, 0, 13, rowUserList.size());
                    this.setMergeCell(14, 0, 14, rowUserList.size());
                    this.setMergeCell(15, 0, 15, rowUserList.size());
                    this.setMergeCell(16, 0, 16, rowUserList.size());
                    this.setMergeCell(17, 0, 17, rowUserList.size());
                    this.setMergeCell(18, 0, 18, rowUserList.size());
                    this.setMergeCell(19, 0, 19, rowUserList.size());
                    this.endMergerCell();
                    this.endWorkSheet();
                }
            };
            writer.process(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  适用与款员交班界面的导出    zeshi.sun
     * @param output
     * @param rowHeaderList
     * @param ts
     * @param name
     * @param titleSecond
     * @param end
     * @param flag
     */
    public void commExcelExportSecond(OutputStream output, LinkedHashMap<String, String> rowHeaderList, List<Map> ts, List<String> name, List<String> titleSecond, String end, boolean flag){
        try {
            ExcelWriter writer = new ExcelWriter() {
                @Override
                public void generate() throws Exception {
                    this.beginWorkSheet();
                    this.beginSheet();
                    int contextStartRowNum = createRowHeader(this,rowHeaderList.values(),name,titleSecond,rowHeaderList.size());
                    List<Integer> needStaticIndex = new ArrayList<Integer>();
                    if (ts != null){
                        for (int rowNum = 0; rowNum < ts.size(); rowNum++) {
                            // 插入新行
                            this.insertRow(contextStartRowNum ++);
                            Map m = ts.get(rowNum);

                            Set set = rowHeaderList.keySet();
                            Object[] objSet = set.toArray();
                            //序号
                            this.createCell(0, rowNum+1);
                            // 建立新单元格,索引值从0开始,表示第一列
                            for(int k = 0 ; k < objSet.length ; k ++){
                                Object objValue = m.get(objSet[k].toString());
                                this.createCell(k+1, null == objValue ? "" : objValue.toString());
                            }
                            // 结束行
                            this.endRow();
                        }
                    }
                    // 电子表格结束
                    this.endSheet();
                    //合并单元格
                    this.beginMergerCell();
                    this.setMergeCell(0, 0, 0, rowHeaderList.size());
                    this.setMergeCell(1, 0, 1, rowHeaderList.size());
                    if (titleSecond != null && titleSecond.size() > 0){
                        int titleSize = titleSecond.size();
                        for (int i = 2; i < (2 + titleSize); i ++){
                            this.setMergeCell(i, 0, i, rowHeaderList.size());
                        }
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

	/**
	 * 配送模块模板          结尾处同时有合计也有右下角的统计信息
	 * @param
	 * @param rowHeaderList
	 * @param end
	 * @throws IOException
	 */
	public void commExcelDistrExport(OutputStream output, LinkedHashMap<String, String> rowHeaderList, List<T> ts, List<String> name, List<String> titleSecond, String end, String total, List<String> needTotalName) {
		try {
			ExcelWriter writer = new ExcelWriter() {
				@Override
				public void generate() throws Exception {
					this.beginWorkSheet();
					this.beginSheet();
					int contextStartRowNum = createRowHeader(this, rowHeaderList.values(), name, titleSecond, rowHeaderList.size());
					List<Integer> needStaticIndex = new ArrayList<Integer>();
					if (ts != null) {
						for (int rowNum = 0; rowNum < ts.size(); rowNum++) {
							// 插入新行
							this.insertRow(contextStartRowNum ++);
							T t = ts.get(rowNum);
							Set set = rowHeaderList.keySet();
							Object[] objSet = set.toArray();
							//序号
							this.createCell(0, rowNum + 1);
							// 建立新单元格,索引值从0开始,表示第一列
							for (int k = 0; k < objSet.length; k++) {
								for (String s : needTotalName) {
									if (s.equals(objSet[k].toString())) {
										needStaticIndex.add(k + 1);
									}
								}
								Object objValue = ReflectUtils.getValueOfGet(t, objSet[k].toString());
								if (objValue instanceof Date) {
									Date date = (Date) objValue;
									String s = DateUtils.DateToString(date, DateUtils.FMT_DATE_TIME);
									this.createCell(k + 1, s);
								} else {
									this.createCell(k + 1, null == objValue ? "" : objValue.toString());
								}

							}
							// 结束行
							this.endRow();
						}
					}
					//插入尾部的信息()
					if (!StringUtils.isEmpty(total)) {
						createRowEnd(this, contextStartRowNum, rowHeaderList, total, false, needStaticIndex);
					}
					if (!StringUtils.isEmpty(end)) {
						createRowEnd(this, contextStartRowNum + 1, rowHeaderList, end, true, needStaticIndex);
					}

                    // 电子表格结束
                    this.endSheet();
                    //合并单元格
                    this.beginMergerCell();
                    this.setMergeCell(0, 0, 0, rowHeaderList.size());
                    this.setMergeCell(1, 0, 1, rowHeaderList.size());
                    if (titleSecond != null && titleSecond.size() > 0){
                        int titleSize = titleSecond.size();
                        for (int i = 2; i < (2 + titleSize); i ++){
                            this.setMergeCell(i, 0, i, rowHeaderList.size());
                        }
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


	private void createRowEnd(ExcelWriter excelWriter, int rowLine, LinkedHashMap<String, String> rowHeaderList, String end, Boolean flag, List<Integer> needStaticIndex) throws IOException {
		int startEndRow = rowLine;//最后一行内容的行数
		//如果是ture则返回的是最后一列长的结尾模板[内容以;隔开]
		if (flag) {
			String[] endContext = end.split(";");
			for (String s : endContext) {
				s = (StringUtils.isEmpty(s) || "null".equals(s.trim())) ? "" : s;
				excelWriter.insertRow(startEndRow + 1);
				startEndRow++;
				excelWriter.createCell(rowHeaderList.size() - 1, s);
				excelWriter.endRow();
			}
		} else {
			//这种情况就是一行的合计
			String[] endContext = end.split(";");
			excelWriter.insertRow(startEndRow + 1);
			excelWriter.createCell(0, "合计");

			if(!needStaticIndex.isEmpty()){
				for (int i = 0; i < endContext.length; i++) {
					String s = (StringUtils.isEmpty(endContext[i]) || "null".equals(endContext[i].trim())) ? "" : endContext[i];
					excelWriter.createCell(needStaticIndex.get(i), s);
				}
			}

			excelWriter.endRow();
		}
	}
	
	private int createMultipleRowEnd(ExcelWriter excelWriter, int startEndRow, LinkedHashMap<String, String> rowHeaderList, 
			String end, Boolean flag, List<Integer> needStaticIndex) throws IOException {
		//如果是ture则返回的是最后一列长的结尾模板[内容以;隔开]
		if (flag) {
			String[] endContext = end.split(";");
			for (String s : endContext) {
				s = (StringUtils.isEmpty(s) || "null".equals(s.trim())) ? "" : s;
				excelWriter.insertRow(startEndRow++);
				excelWriter.createCell(0, s);
				excelWriter.endRow();
			}
		} else {
			//这种情况就是一行的合计
			String[] endContext = end.split(";");
			excelWriter.insertRow(startEndRow++);
			excelWriter.createCell(0, "合计");
			for (int i = 0; i < endContext.length; i++) {
				String s = (StringUtils.isEmpty(endContext[i]) || "null".equals(endContext[i].trim())) ? "" : endContext[i];
				excelWriter.createCell(needStaticIndex.get(i), s);
			}
			excelWriter.endRow();
		}
		return startEndRow;
	}

	private Integer createRowHeader(ExcelWriter writer, Collection<String> rowHeaderList, List<String> name, List<String> titleSecond, int size) throws IOException {
		int RowNum = 0;
		Map<String, XSSFCellStyle> styleMap = writer.getCellStyles();
		short cellStrIndex = styleMap.get("cell_string").getIndex();
		// 插入第一行---XX药店XX清单
		for (String title : name) {
			writer.insertRow(RowNum++);
			writer.createCell(0, title, cellStrIndex);
			writer.endRow();
		}
		//插入第三行---供货单位信息
		if (titleSecond != null) {
			for (String s : titleSecond) {
				s = (StringUtils.isEmpty(s) || "null".equals(s.trim())) ? "" : s;
				writer.insertRow(RowNum++);
				writer.createCell(0, s, cellStrIndex);
				writer.endRow();
			}
		}
		// 插入数据行的序号
		if(rowHeaderList!=null && !rowHeaderList.isEmpty()){
			writer.insertRow(RowNum++);
			Object[] rows = rowHeaderList.toArray();
			writer.createCell(0, "序号");
			for (int i = 0; i < rows.length; i++) {
				// 建立新单元格,索引值从0开始,表示第一列
				writer.createCell(i + 1, rows[i].toString());
			}
			writer.endRow();
		}
		// 结束行
		return RowNum;
	}
	
	private Integer createRowHeader(ExcelWriter writer, Collection<String> rowHeaderList, List<String> name, String titleSecond, int size, int RowNum) throws IOException {
//		int RowNum = 0;
		Map<String, XSSFCellStyle> styleMap = writer.getCellStyles();
		short cellStrIndex = styleMap.get("cell_string").getIndex();
		short cellStrLeftIndex = styleMap.get("cell_string_left").getIndex();
		short cellStringBorder = styleMap.get("cell_string_border").getIndex();
		// 插入第一行---XX药店XX清单
		if(name != null){
			for (String title : name) {
				writer.insertRow(RowNum++);
				writer.createCell(0, title, cellStrIndex);
				writer.endRow();
			}
		}
		//插入第三行---供货单位信息
		if (titleSecond != null) {
			titleSecond = (StringUtils.isEmpty(titleSecond) || "null".equals(titleSecond.trim())) ? "" : titleSecond;
			writer.insertRow(RowNum++);
			writer.createCell(0, titleSecond, cellStrLeftIndex);
			writer.endRow();
		}
		// 插入数据行的序号
		if(rowHeaderList!=null && !rowHeaderList.isEmpty()){
			writer.insertRow(RowNum++);
			Object[] rows = rowHeaderList.toArray();
			writer.createCell(0, "序号",cellStringBorder);
			for (int i = 0; i < rows.length; i++) {
				// 建立新单元格,索引值从0开始,表示第一列
				writer.createCell(i + 1, rows[i].toString(),cellStringBorder);
			}
			writer.endRow();
		}
		// 结束行
		return RowNum;
	}

    /**
     * 只适用与投诉管理导出
     * @param writer
     * @param rowUserList
     * @param rowGoddsList
     * @param name
     * @param titleSecond
     * @param titleOther
     * @param ts
     * @param objSetUser
     * @param objSetGoods
     * @param titleOtherTwo
     * @param titleOtherThree
     * @param titleOtherFour
     * @param titleOtherFive
     * @param titleOtherSix
     * @return
     * @throws Exception
     */
    private Integer createRowHeaderByTrainPlan(ExcelWriter writer, Collection<String> rowUserList, Collection<String> rowGoddsList, List<String> name, List<String> titleSecond,
                                               List<String> titleOther, T ts, Object[] objSetUser, Object[] objSetGoods, List<String> titleOtherTwo, List<String> titleOtherThree, List<String> titleOtherFour,
                                               List<String> titleOtherFive, List<String> titleOtherSix) throws Exception {
        int RowNum = 0;
        Map<String, XSSFCellStyle> styleMap = writer.getCellStyles();
        short cellStrIndex = styleMap.get("cell_string").getIndex();
        short cellStrRightIndex = styleMap.get("cell_string_right").getIndex();
        short cellStrLeftIndex = styleMap.get("cell_string_left").getIndex();
        // 插入第一行---XX药店XX清单
        for (String title:name) {
            writer.insertRow(RowNum ++);
            writer.createCell(0,title,cellStrIndex);
            writer.endRow();
        }
        //插入第三行---供货单位信息
        if (titleSecond != null){
            for (String s:titleSecond) {
                s = (StringUtils.isEmpty(s) || "null".equals(s.trim())) ? "" : s;
                writer.insertRow(RowNum ++);
                writer.createCell(0,s,cellStrLeftIndex);
                writer.endRow();
            }
        }

        // 插入投诉人员信息
        writer.insertRow(RowNum ++);
        Object[] rows = rowUserList.toArray();
        writer.createCell(0,"投诉人员");
        // 建立新单元格,表示第一列
        writer.createCell(1, rows[0].toString());
        Object objValue1 = ReflectUtils.getValueOfGet(ts,objSetUser[0].toString());
        writer.createCell(2, String.valueOf(objValue1).equals("null") ? "": String.valueOf(objValue1));
        writer.createCell(3, rows[1].toString());
        objValue1 = ReflectUtils.getValueOfGet(ts,objSetUser[1].toString());
        writer.createCell(4, String.valueOf(objValue1).equals("null") ? "": String.valueOf(objValue1));
        writer.endRow();
        writer.insertRow(RowNum ++);
        writer.createCell(1, rows[2].toString());
        objValue1 = ReflectUtils.getValueOfGet(ts,objSetUser[2].toString());
        writer.createCell(2, String.valueOf(objValue1).equals("null") ? "": String.valueOf(objValue1));
        writer.createCell(3, rows[3].toString());
        objValue1 = ReflectUtils.getValueOfGet(ts,objSetUser[3].toString());
        writer.createCell(4, String.valueOf(objValue1).equals("null") ? "": String.valueOf(objValue1));
        writer.endRow();
        writer.insertRow(RowNum ++);
        writer.createCell(1, rows[4].toString());
        objValue1 = ReflectUtils.getValueOfGet(ts,objSetUser[4].toString());
        writer.createCell(2, String.valueOf(objValue1).equals("null") ? "": String.valueOf(objValue1));
        writer.createCell(3, rows[5].toString());
        objValue1 = ReflectUtils.getValueOfGet(ts,objSetUser[5].toString());
        writer.createCell(4, String.valueOf(objValue1).equals("null") ? "": String.valueOf(objValue1));
        writer.endRow();
        writer.insertRow(RowNum ++);
        writer.createCell(1, rows[6].toString());
        objValue1 = ReflectUtils.getValueOfGet(ts,objSetUser[6].toString());
        writer.createCell(2, String.valueOf(objValue1).equals("null") ? "": String.valueOf(objValue1));
        writer.createCell(3, rows[7].toString());
        objValue1 = ReflectUtils.getValueOfGet(ts,objSetUser[7].toString());
        writer.createCell(4, String.valueOf(objValue1).equals("null") ? "": String.valueOf(objValue1));
        writer.endRow();
        // 插入商品信息
        writer.insertRow(RowNum ++);
        Object[] rowss = rowGoddsList.toArray();
        writer.createCell(0,"商品信息");
        // 建立新单元格
        writer.createCell(1, rowss[0].toString());
        Object objValue2 = ReflectUtils.getValueOfGet(ts,objSetGoods[0].toString());
        writer.createCell(2, String.valueOf(objValue2).equals("null") ? "": String.valueOf(objValue2));
        writer.createCell(3, rowss[1].toString());
        objValue2 = ReflectUtils.getValueOfGet(ts,objSetGoods[1].toString());
        writer.createCell(4, String.valueOf(objValue2).equals("null") ? "": String.valueOf(objValue2));
        writer.endRow();
        writer.insertRow(RowNum ++);
        writer.createCell(1, rowss[2].toString());
        objValue2 = ReflectUtils.getValueOfGet(ts,objSetGoods[2].toString());
        writer.createCell(2, String.valueOf(objValue2).equals("null") ? "": String.valueOf(objValue2));
        writer.createCell(3, rowss[3].toString());
        objValue2 = ReflectUtils.getValueOfGet(ts,objSetGoods[3].toString());
        writer.createCell(4, String.valueOf(objValue2).equals("null") ? "": String.valueOf(objValue2));
        writer.endRow();
        writer.insertRow(RowNum ++);
        writer.createCell(1, rowss[4].toString());
        objValue2 = ReflectUtils.getValueOfGet(ts,objSetGoods[4].toString());
        writer.createCell(2, String.valueOf(objValue2).equals("null") ? "": String.valueOf(objValue2));
        writer.createCell(3, rowss[5].toString());
        objValue2 = ReflectUtils.getValueOfGet(ts,objSetGoods[5].toString());
        writer.createCell(4, String.valueOf(objValue2).equals("null") ? "": String.valueOf(objValue2));
        writer.endRow();

        //插入行---信息  左对齐
        if (titleOther != null){
            for (String s:titleOther) {
                s = (StringUtils.isEmpty(s) || "null".equals(s.trim())) ? "" : s;
                writer.insertRow(RowNum ++);
                writer.createCell(0,s,cellStrLeftIndex);
                writer.endRow();
            }
        }

        //插入行---信息   右对齐
        if (titleOtherTwo != null){
            for (String s:titleOtherTwo) {
                s = (StringUtils.isEmpty(s) || "null".equals(s.trim())) ? "" : s;
                writer.insertRow(RowNum ++);
                writer.createCell(0,s,cellStrRightIndex);
                writer.endRow();
            }
        }

        //插入行---信息  左对齐
        if (titleOtherThree != null){
            for (String s:titleOtherThree) {
                s = (StringUtils.isEmpty(s) || "null".equals(s.trim())) ? "" : s;
                writer.insertRow(RowNum ++);
                writer.createCell(0,s,cellStrLeftIndex);
                writer.endRow();
            }
        }

        //插入行---信息   右对齐
        if (titleOtherFour != null){
            for (String s:titleOtherFour) {
                s = (StringUtils.isEmpty(s) || "null".equals(s.trim())) ? "" : s;
                writer.insertRow(RowNum ++);
                writer.createCell(0,s,cellStrRightIndex);
                writer.endRow();
            }
        }

        //插入行---信息  左对齐
        if (titleOtherFive != null){
            for (String s:titleOtherFive) {
                s = (StringUtils.isEmpty(s) || "null".equals(s.trim())) ? "" : s;
                writer.insertRow(RowNum ++);
                writer.createCell(0,s,cellStrLeftIndex);
                writer.endRow();
            }
        }

        //插入行---信息   右对齐
        if (titleOtherSix != null){
            for (String s:titleOtherSix) {
                s = (StringUtils.isEmpty(s) || "null".equals(s.trim())) ? "" : s;
                writer.insertRow(RowNum ++);
                writer.createCell(0,s,cellStrRightIndex);
                writer.endRow();
            }
        }

        return RowNum;
    }

    
    /**
     * 只适用于交易日记账凭证
     * @Description: TODO(描述该方法做什么)
     * @author liuqun
     * @version 1.0 
     * @date 2018年1月15日 下午3:17:09 
     * @param output
     * @param rowHeaderList
     * @param ts
     * @param name
     * @param titleSecond
     * @param end
     * @param flag
     * @param needTotalName 
     * @return void
     */
	public void commFinanceVoucherListExcelExport(OutputStream output, LinkedHashMap<String, String> rowHeaderList,
			List<T> ts, List<String> name, List<String> titleSecond, String end, boolean flag, 
			List<String> needTotalName) {
		try {
			ExcelWriter writer = new ExcelWriter() {
				@Override
				public void generate() throws Exception {
					this.beginWorkSheet();
					this.beginSheet();
					
					List<Integer> headRow = new ArrayList<>();
					List<Integer> comRow = new ArrayList<>();
					List<Integer> row = new ArrayList<>();
					
					List<Integer> needStaticIndex = new ArrayList<Integer>();
					if (ts != null) {
						
						int contextStartRowNum = 0;
						
						Map<String, XSSFCellStyle> styleMap = this.getCellStyles();
						short cellStringBorder = styleMap.get("cell_string_border").getIndex();
						
						contextStartRowNum = createRowHeader(this, null, name, null, rowHeaderList.size(), contextStartRowNum);
						
						for (int rowNum = 0; rowNum < ts.size(); rowNum++) {
							
							contextStartRowNum = createFinanceVoucherListRowHeader(this, rowHeaderList.values(), null, titleSecond.get(rowNum), rowHeaderList.size(), contextStartRowNum);
							
							headRow.add(contextStartRowNum-2);
							
							// 插入新行
							T t = ts.get(rowNum);
							if(t instanceof List){
								List<T> dtlList = (List<T>) t;
								for (int i = 0; i < dtlList.size(); i++) {
									
									this.insertRow(contextStartRowNum ++);
									
									if(i == dtlList.size()-1){
										comRow.add(contextStartRowNum-1);
									}
									
									T dtl = dtlList.get(i);
									
									Set set = rowHeaderList.keySet();
									Object[] objSet = set.toArray();
									//序号
									this.createCell(0, i + 1, cellStringBorder);
									// 建立新单元格,索引值从0开始,表示第一列
									for (int k = 0; k < objSet.length; k++) {
										for (String s : needTotalName) {
											if (s.equals(objSet[k].toString())) {
												needStaticIndex.add(k + 1);
											}
										}
										Object objValue = ReflectUtils.getValueOfGet(dtl, objSet[k].toString());
										if (objValue instanceof Date) {
											Date date = (Date) objValue;
											String s = DateUtils.DateToString(date, DateUtils.FMT_DATE_TIME);
											this.createCell(k + 1, s, cellStringBorder);
										} else {
											this.createCell(k + 1, null == objValue ? "" : objValue.toString(), cellStringBorder);
										}
		
									}
									// 结束行
									this.endRow();
									
								}
							}	
							//插入尾部的信息()
							if (!StringUtils.isEmpty(end)) {
								contextStartRowNum = createMultipleRowEnd(this, contextStartRowNum, rowHeaderList, end, flag, needStaticIndex);
								row.add(contextStartRowNum-1);
								contextStartRowNum++;
							}
						}
					}

                    // 电子表格结束
                    this.endSheet();
                    //合并单元格
                    this.beginMergerCell();
                    this.setMergeCell(0, 0, 0, rowHeaderList.size());
                    this.setMergeCell(1, 0, 1, rowHeaderList.size());
                    
                    for (Integer r : headRow) {
                    	this.setMergeCell(r, 0, r, rowHeaderList.size()-1);
					}
                    for (Integer r : comRow) {
                    	this.setMergeCell(r, 2, r, 3);
					}
                    for (Integer r : row) {
                    	this.setMergeCell(r, 0, r, rowHeaderList.size());
                    }
                    /*if (titleSecond != null && titleSecond.size() > 0){
                        int titleSize = titleSecond.size();
                        for (int i = 2; i < (2 + titleSize); i ++){
                            this.setMergeCell(i, 0, i, rowHeaderList.size());
                        }
                    }*/
                    this.endMergerCell();
                    this.endWorkSheet();
                }
            };
            writer.process(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	
	
	private Integer createFinanceVoucherListRowHeader(ExcelWriter writer, Collection<String> rowHeaderList, List<String> name, String titleSecond, int size, int RowNum) throws IOException {
//		int RowNum = 0;
		Map<String, XSSFCellStyle> styleMap = writer.getCellStyles();
		short cellStrIndex = styleMap.get("cell_string").getIndex();
		short cellStrLeftIndex = styleMap.get("cell_string_left").getIndex();
		short cellStringBorder = styleMap.get("cell_string_border").getIndex();
		// 插入第一行---XX药店XX清单
		if(name != null){
			for (String title : name) {
				writer.insertRow(RowNum++);
				writer.createCell(0, title, cellStrIndex);
				writer.endRow();
			}
		}
		//插入第三行---供货单位信息
		if (titleSecond != null) {
			titleSecond = (StringUtils.isEmpty(titleSecond) || "null".equals(titleSecond.trim())) ? "" : titleSecond;
			writer.insertRow(RowNum++);
			if(titleSecond.contains(";")){
				Object[] rows = rowHeaderList.toArray();
				writer.createCell(0, titleSecond.split(";")[0], cellStrLeftIndex);
				writer.createCell(rows.length, titleSecond.split(";")[1], cellStrIndex);
				writer.endRow();
			}else{
				writer.createCell(0, titleSecond, cellStrLeftIndex);
				writer.endRow();
			}	
		}
		// 插入数据行的序号
		if(rowHeaderList!=null && !rowHeaderList.isEmpty()){
			writer.insertRow(RowNum++);
			Object[] rows = rowHeaderList.toArray();
			writer.createCell(0, "序号", cellStringBorder);
			for (int i = 0; i < rows.length; i++) {
				// 建立新单元格,索引值从0开始,表示第一列
				writer.createCell(i + 1, rows[i].toString(), cellStringBorder);
			}
			writer.endRow();
		}
		// 结束行
		return RowNum;
	}
}
