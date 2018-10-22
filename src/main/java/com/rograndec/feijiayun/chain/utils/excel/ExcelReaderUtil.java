package com.rograndec.feijiayun.chain.utils.excel;

import com.rograndec.feijiayun.chain.business.goods.price.exception.PriceOrderBizException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ST on 2017/8/22.
 */
public class ExcelReaderUtil {
    private static final Logger logger = LoggerFactory.getLogger(ExcelReaderUtil.class);
    private ExcelReaderUtil() {
    }

    // excel2003扩展名
    public static final String EXCEL03_EXTENSION = ".xls";
    // excel2007扩展名
    public static final String EXCEL07_EXTENSION = ".xlsx";


//    public static void readExcel(ImageReader reader,String filename) throws Exception {
//        if(filename.endsWith(EXCEL07_EXTENSION)){
//            Excel2007Reader excel07 = new Excel2007Reader();
//            excel07.process(filename);
//        }
//    }

    /**
     * 读取Excel文件，可能是03也可能是07版本
     *
     * @param reader
     * @param fileName
     * @param inputStream
     * @throws Exception
     */
    public static void readExcel(IRowReader reader, String fileName, InputStream inputStream,int minColumns) throws Exception {
        // 处理excel2003文件
        if (fileName.endsWith(EXCEL03_EXTENSION)) {
            Excel03Reader excel03 = new Excel03Reader(reader);
            excel03.process(inputStream);
        } else if (fileName.endsWith(EXCEL07_EXTENSION)) {
            // 处理excel2007文件
            OPCPackage p = OPCPackage.open(inputStream);
            Excel07Reader excel07Reader = new Excel07Reader(p, System.out, minColumns ,reader);
            excel07Reader.process();
        } else {
            throw new PriceOrderBizException(PriceOrderBizException.VALUE_CHECK,"文件格式错误，fileName的扩展名只能是xls或xlsx。");
        }
    }


    public static final List<String[]> excelToArrayList(String filePath,int minColumns,int... sheetNumber) throws Exception{
        List<String[]> resultList = null;
        InputStream is = null;
        is = new FileInputStream(filePath);
        resultList = excelToArrayList(filePath, is, minColumns,sheetNumber);

        return resultList;
    }

    public static final List<String[]> excelToArrayList(String fileName,InputStream is,int minColumns,final int... sheetNumber) throws Exception {
        final ArrayList<String[]> resultList = new ArrayList<>();
        try {
            readExcel(new IRowReader() {
                @Override
                public void getRows(int sheetIndex, int curRow, List<String> rowList) {
                    if(sheetNumber == null){
                        resultList.add(rowList.toArray(new String[]{}));
                    } else {
                        for(int k = 0;k < sheetNumber.length;k++ ){
                            if(sheetIndex == sheetNumber[k]){
                                resultList.add(rowList.toArray(new String[] {}));
                            }
                        }
                    }
                }
            },fileName, is,minColumns);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return resultList;
    }

    /**
     *
     * @param rowReader
     * @param fileName
     * @param is
     * @param minColumns excel列数 ，用来控制excel单元格最后几列为空值的情况 (从0开始计算)
     * @param sheetNumber
     * @throws Exception
     */
    public static final void excelToArrayList(IRowReader rowReader,String fileName,InputStream is,int minColumns,final int... sheetNumber) throws Exception {
        try {
            readExcel(rowReader,fileName, is,minColumns);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) {
        String fileName = "D:\\aa.xlsx";
        try {
           List<String[]> list
             = ExcelReaderUtil.excelToArrayList(fileName,0);
            System.out.println(list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}