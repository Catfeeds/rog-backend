package com.rograndec.feijiayun.chain.business.report.quality.storage.service;

import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestShelfMoveVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.ShelfMoveExcelPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.ShelfMovePageVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;

public interface ShelfMoveReportService {

    ShelfMovePageVO getShelfMovePage(Page page, RequestShelfMoveVO requestShelfMoveVO, UserVO loginUser);

    ShelfMoveExcelPageVO getExcelForm(RequestShelfMoveVO requestShelfMoveVO, UserVO loginUser);

    void export(OutputStream output, ShelfMoveExcelPageVO userReportExcelVO, UserVO loginUser);
}
