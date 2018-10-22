package com.rograndec.feijiayun.chain.business.storage.move.service;

import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherOut;
import com.rograndec.feijiayun.chain.business.storage.move.vo.OtherOutFormVO;
import com.rograndec.feijiayun.chain.business.storage.move.vo.OtherOutGoodsPageVO;
import com.rograndec.feijiayun.chain.business.storage.move.vo.OtherOutPageVO;
import com.rograndec.feijiayun.chain.business.storage.move.vo.StockAndShelfPageVO;
import com.rograndec.feijiayun.chain.business.storage.move.vo.transfer.OtherOutListParam;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

public interface OtherOutService {
    void save(UserVO userVO, OtherOutFormVO otherOutFormVO) throws Exception;


    List<OtherOut> getOtherOuts(OtherOutListParam otherOutListParam, com.rograndec.feijiayun.chain.common.Page page);

    OtherOutPageVO getOtherOutPageVO(Long outId);

    List<OtherOutGoodsPageVO> getGoods(UserVO userVO, String name);

    void export(OutputStream output, Long outId, UserVO loginUser);

    List<StockAndShelfPageVO> getStockAndShelfPageVOs(UserVO userVO, Long lotId, Long goodsId);
}
