package com.rograndec.feijiayun.chain.business.storage.chgoods.service;

import com.rograndec.feijiayun.chain.business.basic.user.entity.SimpleUserVO;
import com.rograndec.feijiayun.chain.business.purchase.check.vo.PurchaseCheckDeatilVO;
import com.rograndec.feijiayun.chain.business.storage.chgoods.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

public interface ChGoodsLoadService {

    List<SimpleUserVO> getAuditMan(UserVO loginUser);

    List<ChGoodsLoadOrderListVO> getChGoodsLoadOrderList(int pageNo, int pageSize, Long enterpriseId, Page page, Date startTime, Date endTime, String code, String loadManName, String auditManName, String orderName, String orderType);

    ChGoodsLoadOrderDtlListVO getChGoodsLoadOrderDtlList(Long enterpriseId, Long id);

    void exportExcel(OutputStream output, ChGoodsLoadOrderDtlListVO chGoodsLoadOrderDtlListVO, UserVO loginUser);

    int saveChGoodsLoadOrder(UserVO loginUser, SaveChGoodsLoadOrderVO saveChGoodsLoadOrderVO) throws Exception;

    List<GoodsStockListVO> getGoodsStockList(Long enterpriseId, String param, Long type);

    GoodsShelfStatusDescVO getGoodsShelfStatusDesc(Long enterpriseId, Long shelfId);
}
