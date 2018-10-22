package com.rograndec.feijiayun.chain.utils.excel;

import com.rograndec.feijiayun.chain.common.ExcelPic;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Excel超大数据写入，抽象excel2007读入器，先构建.xlsx一张模板，改写模板中的sheet.xml,
 * 使用这种方法 写入.xlsx文件，不需要太大的内存
 */
public abstract class ExcelWriter {

	private SpreadsheetWriter sw;

	private Map<String, XSSFCellStyle> styleMap;

	/**
	 * 写入电子表格的主要流程
	 *
	 * @param fileName
	 * @throws Exception
	 */
	public void process(String fileName) throws Exception {

		// 建立工作簿和电子表格对象
		XSSFWorkbook wb = new XSSFWorkbook();
		styleMap = createStyles(wb);
		XSSFSheet sheet = wb.createSheet("sheet1");
		sheet.setDefaultColumnWidth(20);
		sheet.setDefaultRowHeightInPoints(20);

		// 持有电子表格数据的xml文件名 例如 /xl/worksheets/sheet1.xml
		String sheetRef = sheet.getPackagePart().getPartName().getName();

		// 保存模板
		FileOutputStream os = new FileOutputStream("template.xlsx");
		wb.write(os);
		os.close();

		// 生成xml文件
		File tmp = File.createTempFile("sheet", ".xml");
		Writer fw = new FileWriter(tmp);
		sw = new SpreadsheetWriter(fw);

		generate();
		fw.close();

		// 使用产生的数据替换模板
		File templateFile = new File("template.xlsx");
		FileOutputStream out = new FileOutputStream(fileName);
		substitute(templateFile, tmp, sheetRef.substring(1), out);
		out.close();
		// 删除文件之前调用一下垃圾回收器，否则无法删除模板文件
		System.gc();
		// 删除临时模板文件
		if (templateFile.isFile() && templateFile.exists()) {
			templateFile.delete();
		}
	}

	/**
	 * 写入电子表格的主要流程
	 *
	 * @param out
	 * @throws Exception
	 */
	public OutputStream process(OutputStream out) throws Exception {

		// 建立工作簿和电子表格对象
		XSSFWorkbook wb = new XSSFWorkbook();
		//生产cell样式
		styleMap = createStyles(wb);
		XSSFSheet sheet = wb.createSheet("sheet1");
		sheet.setDefaultColumnWidth(20);
		sheet.setDefaultRowHeightInPoints(10000);
		// 持有电子表格数据的xml文件名 例如 /xl/worksheets/sheet1.xml
		String sheetRef = sheet.getPackagePart().getPartName().getName();

		// 保存模板
		FileOutputStream os = new FileOutputStream("template.xlsx");
		wb.write(os);
		os.close();

		// 生成xml文件
		File tmp = File.createTempFile("sheet", ".xml");
		Writer fw = new FileWriter(tmp);
		sw = new SpreadsheetWriter(fw);
		generate();
		fw.close();

		// 使用产生的数据替换模板
		File templateFile = new File("template.xlsx");
//		FileOutputStream out = new FileOutputStream(fileName);
		substitute(templateFile, tmp, sheetRef.substring(1), out);
//		out.close();
		// 删除文件之前调用一下垃圾回收器，否则无法删除模板文件
		System.gc();
		// 删除临时模板文件
		if (templateFile.isFile() && templateFile.exists()) {
			templateFile.delete();
		}
		return out;
	}

	/**
	 * 写入电子表格的主要流程
	 *
	 * @param out
	 * @throws Exception
	 */
	public OutputStream processForVerificationForm(OutputStream out,short columnSize) throws Exception {

		// 建立工作簿和电子表格对象
		XSSFWorkbook wb = new XSSFWorkbook();
		//生产cell样式
		styleMap = createStyles(wb);
		XSSFSheet sheet = wb.createSheet("sheet1");

		sheet.setDefaultRowHeightInPoints(10000);
        sheet.autoSizeColumn(4);
		// 持有电子表格数据的xml文件名 例如 /xl/worksheets/sheet1.xml
		String sheetRef = sheet.getPackagePart().getPartName().getName();
		// 保存模板
		FileOutputStream os = new FileOutputStream("template.xlsx");
		wb.write(os);
		os.close();

		// 生成xml文件
		File tmp = File.createTempFile("sheet", ".xml");
		Writer fw = new FileWriter(tmp);
		sw = new SpreadsheetWriter(fw);
		generate();
		fw.close();

		// 使用产生的数据替换模板
		File templateFile = new File("template.xlsx");
//		FileOutputStream out = new FileOutputStream(fileName);
		substitute(templateFile, tmp, sheetRef.substring(1), out);
//		out.close();
		// 删除文件之前调用一下垃圾回收器，否则无法删除模板文件
		System.gc();
		// 删除临时模板文件
		if (templateFile.isFile() && templateFile.exists()) {
			templateFile.delete();
		}
		return out;
	}

	/**
	 * 带合并excel头方法
	 * @param out
	 * @param regions 数组 合并单元格的列  四个参数分别是：起始行，起始列，结束行，结束列
	 * @return
	 * @throws Exception
	 */
	public OutputStream process(OutputStream out,CellRangeAddress... regions) throws Exception {

		// 建立工作簿和电子表格对象
		XSSFWorkbook wb = new XSSFWorkbook();
		//生产cell样式
		styleMap = createStyles(wb);
		XSSFSheet sheet = wb.createSheet("sheet1");

		for(CellRangeAddress region : regions){
			// 四个参数分别是：起始行，起始列，结束行，结束列
			sheet.addMergedRegion(region);
		}


		// 持有电子表格数据的xml文件名 例如 /xl/worksheets/sheet1.xml
		String sheetRef = sheet.getPackagePart().getPartName().getName();

		// 保存模板
		FileOutputStream os = new FileOutputStream("template.xlsx");
		wb.write(os);
		os.close();

		// 生成xml文件
		File tmp = File.createTempFile("sheet", ".xml");
		Writer fw = new FileWriter(tmp);
		sw = new SpreadsheetWriter(fw);
		generate();
		fw.close();

		// 使用产生的数据替换模板
		File templateFile = new File("template.xlsx");
//		FileOutputStream out = new FileOutputStream(fileName);
		substitute(templateFile, tmp, sheetRef.substring(1), out);
//		out.close();
		// 删除文件之前调用一下垃圾回收器，否则无法删除模板文件
		System.gc();
		// 删除临时模板文件
		if (templateFile.isFile() && templateFile.exists()) {
			templateFile.delete();
		}
		return out;
	}

	public OutputStream process(OutputStream out, ExcelPic excelPic) throws Exception {
		// 建立工作簿和电子表格对象
		XSSFWorkbook wb = new XSSFWorkbook();
		//生产cell样式
		styleMap = createStyles(wb);
		XSSFSheet sheet = wb.createSheet("sheet1");
		sheet.setDefaultColumnWidth(20);
		sheet.setDefaultRowHeightInPoints(10000);
		// 持有电子表格数据的xml文件名 例如 /xl/worksheets/sheet1.xml
		String sheetRef = sheet.getPackagePart().getPartName().getName();
		//插入图片
		String pic=excelPic.getPic();
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b = decoder.decodeBuffer(pic);
		FileOutputStream write = new FileOutputStream(new File("template.jpeg"));
		write.write(b);
		BufferedImage bufferImg = null;
		ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
		bufferImg = ImageIO.read(new File("template.jpeg"));
		ImageIO.write(bufferImg, "jpeg", byteArrayOut);
		XSSFDrawing patriarch = sheet.createDrawingPatriarch();
		XSSFClientAnchor anchor = new XSSFClientAnchor (excelPic.getDx1(), excelPic.getDy1(),
				excelPic.getDx2(), excelPic.getDy2(),
				(short) excelPic.getCol1(), excelPic.getRow1(),
				(short) excelPic.getCol2(), excelPic.getRow2());
		patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), XSSFWorkbook.PICTURE_TYPE_JPEG));

		// 保存模板
		FileOutputStream os = new FileOutputStream("template.xlsx");
		wb.write(os);
		os.close();

		// 生成xml文件
		File tmp = File.createTempFile("sheet", ".xml");
		Writer fw = new FileWriter(tmp);
		sw = new SpreadsheetWriter(fw);
		generate();
		fw.close();

		// 使用产生的数据替换模板
		File templateFile = new File("template.xlsx");
//		FileOutputStream out = new FileOutputStream(fileName);
		substitute(templateFile, tmp, sheetRef.substring(1), out);
		File templatePic = new File("template.jpeg");
//		out.close();
		// 删除文件之前调用一下垃圾回收器，否则无法删除模板文件
		System.gc();
		// 删除临时模板文件
		if (templateFile.isFile() && templateFile.exists()) {
			templateFile.delete();
		}
		if (templatePic.isFile() && templatePic.exists()) {
			templatePic.delete();
		}
		return out;
	}

	/**
	 * 类使用者应该使用此方法进行写操作
	 * 
	 * @throws Exception
	 */
	public abstract void generate() throws Exception;

	public void beginWorkSheet() throws IOException {
		sw.beginWorkSheet();
	}

	public void endWorkSheet() throws IOException {
		sw.endWorkSheet();
	}

	public void createPic(int i) throws IOException {
		sw.createPic(i);
	}

	public void beginSheet() throws IOException {
		sw.beginSheet();
	}

	public void insertRow(int rowNum) throws IOException {
		sw.insertRow(rowNum);
	}

	public void createCell(int columnIndex, String value) throws IOException {
		sw.createCell(columnIndex, value, -1);
	}

	public void createCell(int columnIndex, String value,short styleIndex) throws IOException {
		sw.createCell(columnIndex, value, styleIndex);
	}
	public void createCell(int columnIndex, double value) throws IOException {
		sw.createCell(columnIndex, value, -1);
	}
	public void createCell(int columnIndex, double value,short styleIndex) throws IOException {
		sw.createCell(columnIndex, value, styleIndex);
	}

	public void endRow() throws IOException {
		sw.endRow();
	}

	public void endSheet() throws IOException {
		sw.endSheet();
	}
	//合并单元格

	public void beginMergerCell() throws IOException {
		sw.beginMergerCell();
	}

	public void endMergerCell() throws IOException {
		sw.endMergerCell();
	}
	public void setMergeCell(int beginRow, int beginColumn, int endRow,
							 int endColumn) throws IOException {
		sw.setMergeCell(beginRow, beginColumn, endRow,endColumn);
	}


	public Map<String, XSSFCellStyle> getCellStyles() throws IOException {
		 return styleMap;
	}
	/**
	 * 
	 * @param zipfile
	 *            the template file
	 * @param tmpfile
	 *            the XML file with the sheet data
	 * @param entry
	 *            the name of the sheet entry to substitute, e.g.
	 *            xl/worksheets/sheet1.xml
	 * @param out
	 *            the stream to write the result to
	 */
	private static void substitute(File zipfile, File tmpfile, String entry,
			OutputStream out) throws IOException {
		//
		ZipFile zip = new ZipFile(zipfile);
		ZipOutputStream zos = new ZipOutputStream(out);

		@SuppressWarnings("unchecked")
		Enumeration<ZipEntry> en = (Enumeration<ZipEntry>) zip.entries();
		while (en.hasMoreElements()) {
			ZipEntry ze = en.nextElement();
			if (!ze.getName().equals(entry)) {
				zos.putNextEntry(new ZipEntry(ze.getName()));
				InputStream is = zip.getInputStream(ze);
				copyStream(is, zos);
				is.close();
			}
		}
		zos.putNextEntry(new ZipEntry(entry));
		InputStream is = new FileInputStream(tmpfile);
		copyStream(is, zos);
		is.close();
		zos.close();
	}

	private static void copyStream(InputStream in, OutputStream out)
			throws IOException {
		byte[] chunk = new byte[1024];
		int count;
		while ((count = in.read(chunk)) >= 0) {
			out.write(chunk, 0, count);
		}
	}





	// XML Encode
	private static final String[] xmlCode = new String[256];

	static {
		// Special characters
		xmlCode['\''] = "'";
		xmlCode['\"'] = "\""; // double quote
		xmlCode['&'] = "&"; // ampersand
		xmlCode['<'] = "<"; // lower than
		xmlCode['>'] = ">"; // greater than
	}

	/**
	 * <p>
	 * Encode the given text into xml.
	 * </p>
	 * 
	 * @param string
	 *            the text to encode
	 * @return the encoded string
	 */
	public static String encoderXML(String string) {
		if (string == null)
			return "";
		int n = string.length();
		char character;
		String xmlchar;
		StringBuffer buffer = new StringBuffer();
		// loop over all the characters of the String.
		for (int i = 0; i < n; i++) {
			character = string.charAt(i);
			// the xmlcode of these characters are added to a StringBuffer
			// one by one
			try {
				xmlchar = xmlCode[character];
				if (xmlchar == null) {
					buffer.append(character);
				} else {
					buffer.append(xmlCode[character]);
				}
			} catch (ArrayIndexOutOfBoundsException aioobe) {
				buffer.append(character);
			}
		}
		return buffer.toString();
	}

	// 创建Excel样式
	private  Map<String, XSSFCellStyle> createStyles(XSSFWorkbook wb) {
		Map<String, XSSFCellStyle> stylesMap = new HashMap<String, XSSFCellStyle>();
		XSSFDataFormat fmt = wb.createDataFormat();
		XSSFCellStyle style = wb.createCellStyle();
		style.setDataFormat(fmt.getFormat("@"));
		style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		stylesMap.put("cell_string", style);

		XSSFCellStyle style_right = wb.createCellStyle();
		style.setDataFormat(fmt.getFormat("@"));
		style_right.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
		style_right.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		stylesMap.put("cell_string_right", style_right);

		XSSFCellStyle style_left = wb.createCellStyle();
		style.setDataFormat(fmt.getFormat("@"));
		style_left.setAlignment(XSSFCellStyle.ALIGN_LEFT);
		style_left.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		stylesMap.put("cell_string_left", style_left);


		XSSFCellStyle style2 = wb.createCellStyle();
		style2.setDataFormat(fmt.getFormat("0"));
		style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		stylesMap.put("cell_long", style2);
		XSSFCellStyle style3 = wb.createCellStyle();
		style3.setDataFormat(fmt.getFormat("0.00"));
		style3.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style3.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		stylesMap.put("cell_double", style3);
		XSSFCellStyle style4 = wb.createCellStyle();
		style4.setDataFormat(fmt.getFormat("yyyy-MM-dd"));
		style4.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style4.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		stylesMap.put("cell_date", style4);
		XSSFCellStyle style5 = wb.createCellStyle();
		style5.setFillForegroundColor(IndexedColors.AQUA.getIndex());
		style5.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
		style5.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style5.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		stylesMap.put("sheet_title", style5);

		XSSFCellStyle style6 = wb.createCellStyle();
		style6.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		style6.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		style6.setBorderBottom(CellStyle.BORDER_THIN);
		style6.setBorderTop(CellStyle.BORDER_THIN);
		style6.setBorderLeft(CellStyle.BORDER_THIN);
		style6.setBorderRight(CellStyle.BORDER_THIN);
		stylesMap.put("cell_string_border", style6);

		return stylesMap;
	}
	/**
	 * 测试方法
	 */
	public static void main(String[] args) throws Exception {

	String file = "D:\\4.xlsx";
		long starttime = System.currentTimeMillis();
	ExcelWriter writer = new ExcelWriter() {
		public void generate() throws Exception {

			this.beginWorkSheet();
			// 电子表格开始
			this.beginSheet();

			Map<String, XSSFCellStyle> styleMap = this.getCellStyles();
			styleMap.get("cell_string");

			for (int rownum = 0; rownum < 10; rownum++) {
				// 插入新行
				this.insertRow(rownum);

				// 建立新单元格,索引值从0开始,表示第一列
				this.createCell(0, "第 " + rownum + " 行");
				this.createCell(1, 34343.123456789);
				this.createCell(2, "23.67%",styleMap.get("cell_string").getIndex());
				this.createCell(3, "12:12:23", styleMap.get("cell_string_border").getIndex());
				this.createCell(4, "2014-10-11 12:12:23");
				this.createCell(5, "true");
				this.createCell(6, "false");
				this.createCell(7, "false");
				this.createCell(8, "");

				// 结束行
				this.endRow();
			}

			// 电子表格结束
			this.endSheet();
			this.endWorkSheet();
		}
	};
	writer.process(file);

}
		
}
