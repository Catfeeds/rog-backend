package com.rograndec.feijiayun.chain.business.storage.chgoods.vo;

import java.io.Serializable;
import java.util.List;

public class SaveChGoodsLoadOrderVO implements Serializable {

    SaveChGoodsLoadOrderHeadVO saveChGoodsLoadOrderHeadVO;

    List<SaveChGoodsLoadOrderDtlVO> saveChGoodsLoadOrderDtlVO;

    public SaveChGoodsLoadOrderHeadVO getSaveChGoodsLoadOrderHeadVO() {
        return saveChGoodsLoadOrderHeadVO;
    }

    public void setSaveChGoodsLoadOrderHeadVO(SaveChGoodsLoadOrderHeadVO saveChGoodsLoadOrderHeadVO) {
        this.saveChGoodsLoadOrderHeadVO = saveChGoodsLoadOrderHeadVO;
    }

    public List<SaveChGoodsLoadOrderDtlVO> getSaveChGoodsLoadOrderDtlVO() {
        return saveChGoodsLoadOrderDtlVO;
    }

    public void setSaveChGoodsLoadOrderDtlVO(List<SaveChGoodsLoadOrderDtlVO> saveChGoodsLoadOrderDtlVO) {
        this.saveChGoodsLoadOrderDtlVO = saveChGoodsLoadOrderDtlVO;
    }

    @Override
    public String toString() {
        return "SaveChGoodsLoadOrderVO[" +
                "saveChGoodsLoadOrderHeadVO=" + saveChGoodsLoadOrderHeadVO +
                ", saveChGoodsLoadOrderDtlVO=" + saveChGoodsLoadOrderDtlVO +
                ']';
    }
}
