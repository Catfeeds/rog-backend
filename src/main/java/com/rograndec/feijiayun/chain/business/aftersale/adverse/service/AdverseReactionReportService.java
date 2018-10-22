package com.rograndec.feijiayun.chain.business.aftersale.adverse.service;

import com.rograndec.feijiayun.chain.business.aftersale.adverse.vo.GoodsReportVO;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.vo.ReportRequestVO;
import com.rograndec.feijiayun.chain.business.aftersale.adverse.vo.ReportVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public interface AdverseReactionReportService {
    Page getPageList(ReportRequestVO requestVO, UserVO loginUser);

    void save(ReportVO report, UserVO loginUser) throws Exception;

    void delete(Long id);

    void update(ReportVO report, UserVO loginUser);

    void export(Long id, OutputStream output, UserVO loginUser) throws Exception;

    ReportVO get(Long id);

    List<GoodsReportVO> getGoods(Map<String, Object> map);
}
