package com.rograndec.feijiayun.chain.business.quality.system.service;

import com.rograndec.feijiayun.chain.business.quality.system.vo.GetQualityManageSystemVO;
import com.rograndec.feijiayun.chain.business.quality.system.vo.QualityManageSystemPageVO;
import com.rograndec.feijiayun.chain.business.quality.system.vo.SaveQualityManageSystemVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;

public interface QualityManageSystemService {

    void saveQualityManageSystem(UserVO loginUser, SaveQualityManageSystemVO saveQualityManageSystemVO) throws Exception;

    List<QualityManageSystemPageVO> getQualityManageSystemPage(int pageNo, int pageSize, Long enterpriseId, Page page, String type, String status, String key, String orderName, String orderType) throws Exception;

    GetQualityManageSystemVO getQualityManageSystem(Long enterpriseId, Long id, UserVO userVO);

    void updateQualityManageSystem(UserVO loginUser, GetQualityManageSystemVO getQualityManageSystemVO) throws Exception;

    void deleteQualityManageSystem(Long enterpriseId, Long id, UserVO userVO) throws Exception;
}
