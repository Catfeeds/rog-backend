package com.rograndec.feijiayun.chain.business.goods.sale.dao;

import java.util.Map;

/**
 * Created by madong on 2017/9/12.
 */
public interface CheckCodeExistsMapper {
    Long isExistsCode(Map map);

    Long isExistsName(Map map);
}
