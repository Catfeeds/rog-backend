package com.rograndec.feijiayun.chain.business.basic.user.service;

import com.rograndec.feijiayun.chain.business.basic.user.vo.EducationMajorReturnVO;
import com.rograndec.feijiayun.chain.business.basic.user.vo.EducationMajorVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;

/**
 * Created by zhaiwei on 2017/8/28.
 */
public interface EducationMajorService {
    void saveEducationMajor(UserVO userVO, EducationMajorVO educationMajorVO, String type);

    List<EducationMajorReturnVO> queryEducationMajorByUser(UserVO userVO, String type);

}
