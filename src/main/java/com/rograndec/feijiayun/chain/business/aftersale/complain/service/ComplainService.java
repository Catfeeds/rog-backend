package com.rograndec.feijiayun.chain.business.aftersale.complain.service;

import com.rograndec.feijiayun.chain.business.aftersale.complain.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

public interface ComplainService {

    List<ComplainPageVO> getComplainPage(int pageNo, int pageSize, Long enterpriseId, Page page, Date startTime, Date endTime, String code, String acceptManName, String name, String orderName, String orderType);

    CheckComplainVO getCheckComplain(Long enterpriseId, Long id, UserVO userVO);

    void updateComplain(UserVO loginUser, ModifyComplainVO modifyComplainVO) throws Exception;

    Object getDeleteComplain(Long enterpriseId, Long id, UserVO userVO);

    void saveTrainPlan(UserVO loginUser, SaveTrainPlanVO saveTrainPlanVO) throws Exception;

    void exportExcel(OutputStream output, CheckComplainVO checkComplainVO, UserVO loginUser);

    List<GetGoodsVO> getGoodsVoPage(String key, UserVO userVO);
}
