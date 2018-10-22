package com.rograndec.feijiayun.chain.business.storage.move.vo.transfer;

import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherOutDetail;
import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherOutShelf;

import java.util.List;

/**
 * Created by zhaiwei on 2017/9/30.
 */
public class AfterOtherOutDetailAndShlefs {

    private OtherOutDetail otherOutDetail;

    private List<OtherOutShelf> otherOutShelves;

    public OtherOutDetail getOtherOutDetail() {
        return otherOutDetail;
    }

    public void setOtherOutDetail(OtherOutDetail otherOutDetail) {
        this.otherOutDetail = otherOutDetail;
    }

    public List<OtherOutShelf> getOtherOutShelves() {
        return otherOutShelves;
    }

    public void setOtherOutShelves(List<OtherOutShelf> otherOutShelves) {
        this.otherOutShelves = otherOutShelves;
    }
}
