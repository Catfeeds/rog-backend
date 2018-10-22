package com.rograndec.feijiayun.chain.business.purchase.check.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zeshi.sun on 2017/9/19.
 */
public class SaveCheckVO implements Serializable {

    private SaveCheckHeadVO saveCheckHeadVO;

    private List<SaveCheckDetailVO> saveCheckDetailVO;



    public SaveCheckHeadVO getSaveCheckHeadVO() {
        return saveCheckHeadVO;
    }

    public void setSaveCheckHeadVO(SaveCheckHeadVO saveCheckHeadVO) {
        this.saveCheckHeadVO = saveCheckHeadVO;
    }

    public List<SaveCheckDetailVO> getSaveCheckDetailVO() {
        return saveCheckDetailVO;
    }

    public void setSaveCheckDetailVO(List<SaveCheckDetailVO> saveCheckDetailVO) {
        this.saveCheckDetailVO = saveCheckDetailVO;
    }



    @Override
    public String toString() {
        return "SaveCheckVO[" +
                "saveCheckHeadVO=" + saveCheckHeadVO +
                ", saveCheckDetailVO=" + saveCheckDetailVO +
                ']';
    }
}
