package com.rograndec.feijiayun.chain.business.distr.parent.service;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnNotice;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnNoticeFormVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;

public interface DistrReturnNoticeService {

    Page getReturnNoticePage(Page page, Date startTime, Date endTime, String requestUnitCode, String requestUnitName, String code, Integer distrType, Integer status, String order, String sort, Long enterpriseId);

    DistrReturnNoticeFormVO getReturnNoticeDeatil(Long enterpriseId, Long id);

    void export(OutputStream output, DistrReturnNoticeFormVO distrReturnNoticeFormVO, UserVO loginUser);

    void insert(DistrReturnNoticeFormVO distrReturnNoticeFormVO, UserVO loginUser) throws Exception;
}
