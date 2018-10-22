package com.rograndec.feijiayun.chain.business.distr.parent.vo.transfer;

import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnCheckLot;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnIn;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnInDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnInShelfFormVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public class DistrReturnInShelfParam {

    private UserVO userVO;

    private DistrReturnInShelfFormVO distrReturnInShelfFormVO;

    private DistrReturnInDetail distrReturnInDetail;

    private DistrReturnIn distrReturnIn;

    private DistrReturnCheckLot distrReturnCheckLot;

    private WarehouseShelf warehouseShelf;


    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public DistrReturnInShelfFormVO getDistrReturnInShelfFormVO() {
        return distrReturnInShelfFormVO;
    }

    public void setDistrReturnInShelfFormVO(DistrReturnInShelfFormVO distrReturnInShelfFormVO) {
        this.distrReturnInShelfFormVO = distrReturnInShelfFormVO;
    }

    public DistrReturnInDetail getDistrReturnInDetail() {
        return distrReturnInDetail;
    }

    public void setDistrReturnInDetail(DistrReturnInDetail distrReturnInDetail) {
        this.distrReturnInDetail = distrReturnInDetail;
    }

    public DistrReturnIn getDistrReturnIn() {
        return distrReturnIn;
    }

    public void setDistrReturnIn(DistrReturnIn distrReturnIn) {
        this.distrReturnIn = distrReturnIn;
    }


    public WarehouseShelf getWarehouseShelf() {
        return warehouseShelf;
    }

    public void setWarehouseShelf(WarehouseShelf warehouseShelf) {
        this.warehouseShelf = warehouseShelf;
    }

    public DistrReturnCheckLot getDistrReturnCheckLot() {
        return distrReturnCheckLot;
    }

    public void setDistrReturnCheckLot(DistrReturnCheckLot distrReturnCheckLot) {
        this.distrReturnCheckLot = distrReturnCheckLot;
    }
}
