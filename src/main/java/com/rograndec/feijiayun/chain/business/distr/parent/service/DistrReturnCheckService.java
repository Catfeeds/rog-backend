package com.rograndec.feijiayun.chain.business.distr.parent.service;

import com.rograndec.feijiayun.chain.business.distr.parent.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DistrReturnCheckService {

    ClickCheckHeadVO getClickCheckHead(Long enterpriseId, Long id, UserVO userVO) throws Exception;

    List<ClickCheckDetailVO> ClickCheckDetail(Long enterpriseId, Long id);

    Result<Map<String,Object>> saveDistrReturnCheck(UserVO loginUser, SaveDistrReturnCheckVO saveDistrReturnCheckVO) throws Exception;

    List<DistrReturnCheckVO> getDistrReturnCheckPage(int pageNo, int pageSize, Long enterpriseId, Page page, Date startTime, Date endTime, String requestUnitCode, String requestUnitName, String code, Integer distrType, String checkerName, String secondCheckerName, String orderName, String orderType, Long type);

    DistrReturnCheckHeadVO getDistrReturnCheckHead(Long enterpriseId, Long id, UserVO userVO) throws Exception;

    List<DistrReturnCheckDetailVO> getDistrReturnCheckDetail(Long enterpriseId, Long id, UserVO loginUser) throws Exception;

    ExportDistrReturnCheckVO exportDistrReturnCheck(UserVO loginUser, Long id) throws Exception;

    void exportExcel(OutputStream output, ExportDistrReturnCheckVO exportDistrReturnCheckVo, UserVO loginUser);
}
