package com.rograndec.feijiayun.chain.business.storage.move.vo.transfer;

import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.storage.move.vo.OtherOutDetailFormVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * Created by zhaiwei on 2017/9/29.
 */
public class OtherOutDetailParam {

    private UserVO userVO ;

    private OtherOutDetailFormVO otherOutDetailFormVO;

//    private Cost cost;

//    private Stock stock;

    private Goods goods;

    private GoodsTaxRate goodsTaxRate;

    private Integer lineNum;

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public OtherOutDetailFormVO getOtherOutDetailFormVO() {
        return otherOutDetailFormVO;
    }

    public void setOtherOutDetailFormVO(OtherOutDetailFormVO otherOutDetailFormVO) {
        this.otherOutDetailFormVO = otherOutDetailFormVO;
    }

//    public Cost getCost() {
//        return cost;
//    }
//
//    public void setCost(Cost cost) {
//        this.cost = cost;
//    }


    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public GoodsTaxRate getGoodsTaxRate() {
        return goodsTaxRate;
    }

    public void setGoodsTaxRate(GoodsTaxRate goodsTaxRate) {
        this.goodsTaxRate = goodsTaxRate;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }


    public OtherOutDetailParam(UserVO userVO, OtherOutDetailFormVO otherOutDetailFormVO, Goods goods, GoodsTaxRate goodsTaxRate, Integer lineNum) {
        this.userVO = userVO;
        this.otherOutDetailFormVO = otherOutDetailFormVO;
        this.goods = goods;
        this.goodsTaxRate = goodsTaxRate;
        this.lineNum = lineNum;
    }

    public OtherOutDetailParam() {
    }
}
