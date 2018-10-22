package com.rograndec.feijiayun.chain.business.storage.move.service;

import com.rograndec.feijiayun.chain.business.storage.move.vo.OtherInDetailVO;
import com.rograndec.feijiayun.chain.business.storage.move.vo.OtherInFormVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SelectBeanWithCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

public interface OtherInService {

    Page getOtherInPage(Page page, Date startTime, Date endTime, String code, String inManName, Integer inType, String order, String sort, Long enterpriseId,Integer approveStatus);

    OtherInFormVO getOtherInDetail(Long enterpriseId, Long id);

    List<SelectBeanWithCode> getSrcUnit(UserVO loginUser, Integer srcUnitType);

    List<OtherInDetailVO> getGoodsList(UserVO loginUser, String operation, String param) throws InterruptedException;

    void insertOtherIn(UserVO loginUser, OtherInFormVO otherInFormVO) throws Exception;

    void export(OutputStream output, OtherInFormVO otherInFormVO, UserVO loginUser);

    void handleKeyTable(Long id) throws Exception;
}
