package com.rograndec.feijiayun.chain.utils.excel;


import java.util.List;

/**
 * Created by ST on 2017/8/22.
 */
public abstract class IRowReader {


    /**
     * 行 读取，具体业务逻辑实现
     * @param sheetIndex
     * @param curRow
     * @param rowList
     */
    public abstract void getRows(int sheetIndex, int curRow, List<String> rowList);


}