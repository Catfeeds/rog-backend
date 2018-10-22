package com.rograndec.feijiayun.chain.business.storage.chgoods.service;

import com.rograndec.feijiayun.chain.business.storage.chgoods.vo.ChGoodsClearOrderDtlListVO;
import com.rograndec.feijiayun.chain.business.storage.chgoods.vo.ChGoodsClearOrderListVO;
import com.rograndec.feijiayun.chain.business.storage.chgoods.vo.SaveChGoodsClearOrderVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

public interface ChGoodsClearService {

    List<ChGoodsClearOrderListVO> getChGoodsClearOrderList(int pageNo, int pageSize, Long enterpriseId, Page page, Date startTime, Date endTime, String code, String clearManName, String auditManName, String orderName, String orderType);

    ChGoodsClearOrderDtlListVO getChGoodsClearOrderDtlList(Long enterpriseId, Long id);

    void exportExcel(OutputStream output, ChGoodsClearOrderDtlListVO chGoodsClearOrderDtlListVO, UserVO loginUser);

    int saveChGoodsClearOrder(UserVO loginUser, SaveChGoodsClearOrderVO saveChGoodsClearOrderVO) throws Exception;
}
