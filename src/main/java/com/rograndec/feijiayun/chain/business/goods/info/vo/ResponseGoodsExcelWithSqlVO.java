package com.rograndec.feijiayun.chain.business.goods.info.vo;


/**
 * 商品导入返回的商品信息实体
 * Created by ST on 2017/9/10.
 */
public class ResponseGoodsExcelWithSqlVO extends ResponseGoodsExcelVO {

    private String updateSql;

    public String getUpdateSql() {
        return updateSql;
    }

    public void setUpdateSql(String updateSql) {
        this.updateSql = updateSql;
    }
}