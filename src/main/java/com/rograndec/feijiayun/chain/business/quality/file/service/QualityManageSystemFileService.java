package com.rograndec.feijiayun.chain.business.quality.file.service;

import com.rograndec.feijiayun.chain.business.quality.file.vo.GetQualityManageSystemFileVO;
import com.rograndec.feijiayun.chain.business.quality.file.vo.QualityManageSystemFilePageVO;
import com.rograndec.feijiayun.chain.business.quality.file.vo.SaveQualityManageSystemFileVO;
import com.rograndec.feijiayun.chain.business.quality.file.vo.UpdateQualityManageSystemFileVO;
import com.rograndec.feijiayun.chain.business.quality.system.vo.GetQualityManageSystemVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;

public interface QualityManageSystemFileService {

    void saveQualityManageSystemFile(UserVO loginUser, SaveQualityManageSystemFileVO saveQualityManageSystemFileVO) throws Exception;

    List<QualityManageSystemFilePageVO> getQualityManageSystemFilePage(int pageNo, int pageSize, Long enterpriseId, Page page, String type, String status, String key, String orderName, String orderType);

    GetQualityManageSystemFileVO getQualityManageSystemFile(Long enterpriseId, Long id, UserVO userVO) throws Exception;

    void updateQualityManageSystemFile(UserVO loginUser, GetQualityManageSystemFileVO getQualityManageSystemFileVO) throws Exception;

    void deleteQualityManageSystemFile(Long enterpriseId, Long id, UserVO loginUser);

    void updateData(UserVO loginUser, UpdateQualityManageSystemFileVO updateQualityManageSystemFileVO) throws Exception;
}
