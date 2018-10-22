package com.rograndec.feijiayun.chain.utils.excel;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.DateUtil;

import java.io.IOException;
import java.io.Writer;
import java.util.Calendar;

/**
 * 在写入器中写入电子表格
 */
public class SpreadsheetWriter {
    private final Writer _out;
    private int _rownum;
    private static String LINE_SEPARATOR = System
            .getProperty("line.separator");

    public SpreadsheetWriter(Writer out) {
        _out = out;
    }

    public void beginWorkSheet() throws IOException {
        _out.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<worksheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\"  xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\">");
    }

    public void createPic(int i) throws IOException {
        _out.write("<drawing r:id=\"rId" + i + "\"/>");
    }

    public void endWorkSheet() throws IOException {
        _out.write("</worksheet>");
    }

    public void beginSheet() throws IOException {
        _out.write("<sheetData>" + LINE_SEPARATOR);
    }

    public void endSheet() throws IOException {
        _out.write("</sheetData>");
    }

    /**
     * 插入新行
     *
     * @param rownum 以0开始
     */
    public void insertRow(int rownum) throws IOException {
        _out.write("<row r=\"" + (rownum + 1) + "\">" + LINE_SEPARATOR);
        this._rownum = rownum;
    }

    /**
     * 插入行结束标志
     */
    public void endRow() throws IOException {
        _out.write("</row>" + LINE_SEPARATOR);
    }

    /**
     * 插入新列
     *
     * @param columnIndex
     * @param value
     * @param styleIndex
     * @throws IOException
     */
    public void createCell(int columnIndex, String value, int styleIndex)
            throws IOException {
        String ref = new CellReference(_rownum, columnIndex)
                .formatAsString();
        ref = removeTheChar(ref);
        _out.write("<c r=\"" + ref + "\" t=\"str\"");
        if (styleIndex != -1)
            _out.write(" s=\"" + styleIndex + "\"");
        _out.write(">");
        _out.write("<v>" + getValue(value) + "</v>");
        _out.write("</c>");
    }

    public void createCell(int columnIndex, String value)
            throws IOException {
        createCell(columnIndex, value, -1);
    }

    public void createCell(int columnIndex, double value, int styleIndex)
            throws IOException {
        String ref = new CellReference(_rownum, columnIndex)
                .formatAsString();
        ref = removeTheChar(ref);
        _out.write("<c r=\"" + ref + "\" t=\"n\"");
        if (styleIndex != -1)
            _out.write(" s=\"" + styleIndex + "\"");
        _out.write(">");
        _out.write("<v>" + value + "</v>");
        _out.write("</c>");
    }

    public void createCell(int columnIndex, double value)
            throws IOException {
        createCell(columnIndex, value, -1);
    }

    public void createCell(int columnIndex, Calendar value, int styleIndex)
            throws IOException {
        createCell(columnIndex, DateUtil.getExcelDate(value, false),
                styleIndex);
    }

    //合并单元格开始
    public void beginMergerCell() throws IOException {
        this._out.write("<mergeCells>\n");
    }

    //合并单元格结束
    public void endMergerCell() throws IOException {
        this._out.write("</mergeCells>\n");
    }

    // 合并单元格 下标从0开始
    public void setMergeCell(int beginRow, int beginColumn, int endRow,
                             int endColumn) throws IOException {
        this._out.write("<mergeCell ref=\"" + getExcelName(beginColumn)
                + (beginRow + 1) + ":" + getExcelName(endColumn)
                + (endRow + 1) + "\"/>\n");// 列行:列行
    }



    private String removeTheChar(String ref){
        String s = ref.replaceAll("\\$", "");
        return s;
    }

//10 进制转26进制
//		private String getExcelName(int i) {
//			char[] allChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
//			StringBuilder sb = new StringBuilder();
//			while (i > 0) {
//				sb.append(allChar[i % 26 - 1]);
//				i /= 26;
//			}
//			return sb.reverse().toString();
//		}

    public  String getExcelName(int num) {
        return Columns.getIndexLabel(num);
    }


    private String getValue(String value){
        //<用&lt;,>用&gt;,&用&amp;，"用&quot;
        if(value != null){
            value = value.replaceAll("<","&lt;").replaceAll(">","&gt;")
                    .replaceAll("&","&amp;").replaceAll("\"","&quot;");
        }

        return value;
    }




}