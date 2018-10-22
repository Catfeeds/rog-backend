package com.rograndec.feijiayun.chain.business.storage.chgoods.vo;

import java.io.Serializable;
import java.util.List;

public class SaveChGoodsClearOrderVO implements Serializable {

    SaveChGoodsClearOrderHeadVO saveChGoodsClearOrderHead;

    List<SaveChGoodsClearOrderDtlVO> SaveChGoodsClearOrderDtl;

    public SaveChGoodsClearOrderHeadVO getSaveChGoodsClearOrderHead() {
        return saveChGoodsClearOrderHead;
    }

    public void setSaveChGoodsClearOrderHead(SaveChGoodsClearOrderHeadVO saveChGoodsClearOrderHead) {
        this.saveChGoodsClearOrderHead = saveChGoodsClearOrderHead;
    }

    public List<SaveChGoodsClearOrderDtlVO> getSaveChGoodsClearOrderDtl() {
        return SaveChGoodsClearOrderDtl;
    }

    public void setSaveChGoodsClearOrderDtl(List<SaveChGoodsClearOrderDtlVO> saveChGoodsClearOrderDtl) {
        SaveChGoodsClearOrderDtl = saveChGoodsClearOrderDtl;
    }

    @Override
    public String toString() {
        return "SaveChGoodsClearOrderVO[" +
                "saveChGoodsClearOrderHead=" + saveChGoodsClearOrderHead +
                ", SaveChGoodsClearOrderDtl=" + SaveChGoodsClearOrderDtl +
                ']';
    }
}
