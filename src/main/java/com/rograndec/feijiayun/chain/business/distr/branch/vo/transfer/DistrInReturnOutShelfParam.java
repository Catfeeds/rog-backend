package com.rograndec.feijiayun.chain.business.distr.branch.vo.transfer;

import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnDetail;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOutDetail;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReturnOutShelfFormVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;

public class DistrInReturnOutShelfParam {

    private UserVO userVO;

    private DistrInReturnOutDetail distrInReturnOutDetail;

    private DistrInReturnOutShelfFormVO distrInReturnOutShelfFormVO;

    private List<DistrInReturnDetail> distrInReturnDetails;

    private WarehouseShelf warehouseShelf;


    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public DistrInReturnOutShelfFormVO getDistrInReturnOutShelfFormVO() {
        return distrInReturnOutShelfFormVO;
    }

    public void setDistrInReturnOutShelfFormVO(DistrInReturnOutShelfFormVO distrInReturnOutShelfFormVO) {
        this.distrInReturnOutShelfFormVO = distrInReturnOutShelfFormVO;
    }

    public DistrInReturnOutDetail getDistrInReturnOutDetail() {
        return distrInReturnOutDetail;
    }

    public void setDistrInReturnOutDetail(DistrInReturnOutDetail distrInReturnOutDetail) {
        this.distrInReturnOutDetail = distrInReturnOutDetail;
    }

    public List<DistrInReturnDetail> getDistrInReturnDetails() {
        return distrInReturnDetails;
    }

    public void setDistrInReturnDetails(List<DistrInReturnDetail> distrInReturnDetails) {
        this.distrInReturnDetails = distrInReturnDetails;
    }

    public WarehouseShelf getWarehouseShelf() {
        return warehouseShelf;
    }

    public void setWarehouseShelf(WarehouseShelf warehouseShelf) {
        this.warehouseShelf = warehouseShelf;
    }

}
