package com.rograndec.feijiayun.chain.business.storage.move.vo.transfer;

import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.keytable.entity.LotNumber;
import com.rograndec.feijiayun.chain.business.keytable.entity.Stock;
import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherOutDetail;
import com.rograndec.feijiayun.chain.business.storage.move.vo.OtherOutShelfFormVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * Created by zhaiwei on 2017/9/29.
 */
public class OtherOutShelfParam {

    private UserVO userVO ;

    private OtherOutShelfFormVO otherOutShelfFormVO;

    private LotNumber lotNumber;

    private Stock stock;

    private WarehouseShelf warehouseShelf;

//    private WarehouseCargoArea warehouseCargoArea;

    private OtherOutDetail otherOutDetail;

//    private OtherOut otherOut;

    private Integer lineNum;

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public OtherOutShelfFormVO getOtherOutShelfFormVO() {
        return otherOutShelfFormVO;
    }

    public void setOtherOutShelfFormVO(OtherOutShelfFormVO otherOutShelfFormVO) {
        this.otherOutShelfFormVO = otherOutShelfFormVO;
    }

    public OtherOutDetail getOtherOutDetail() {
        return otherOutDetail;
    }

    public void setOtherOutDetail(OtherOutDetail otherOutDetail) {
        this.otherOutDetail = otherOutDetail;
    }

//    public OtherOut getOtherOut() {
//        return otherOut;
//    }
//
//    public void setOtherOut(OtherOut otherOut) {
//        this.otherOut = otherOut;
//    }

    public LotNumber getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(LotNumber lotNumber) {
        this.lotNumber = lotNumber;
    }

    public WarehouseShelf getWarehouseShelf() {
        return warehouseShelf;
    }

    public void setWarehouseShelf(WarehouseShelf warehouseShelf) {
        this.warehouseShelf = warehouseShelf;
    }

//    public WarehouseCargoArea getWarehouseCargoArea() {
//        return warehouseCargoArea;
//    }
//
//    public void setWarehouseCargoArea(WarehouseCargoArea warehouseCargoArea) {
//        this.warehouseCargoArea = warehouseCargoArea;
//    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }
}
