package com.rograndec.feijiayun.chain.business.basic.user.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.EducationMajorMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.EducationMajor;
import com.rograndec.feijiayun.chain.business.basic.user.service.EducationMajorService;
import com.rograndec.feijiayun.chain.business.basic.user.vo.EducationMajorReturnVO;
import com.rograndec.feijiayun.chain.business.basic.user.vo.EducationMajorVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaiwei on 2017/8/28.
 */
@Service
public class EducationMajorServiceImpl implements EducationMajorService{

    @Autowired
    private EducationMajorMapper educationMajorMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveEducationMajor(UserVO userVO, EducationMajorVO educationMajorVO, String type){

        EducationMajor educationMajor = EducationMajor.getEducationMajor4VO(educationMajorVO,type,userVO);

        educationMajorMapper.insertSelective(educationMajor);

    }

    @Override
    public  List<EducationMajorReturnVO> queryEducationMajorByUser(UserVO userVO, String type){
        Map<String,Object> map = new HashMap<>();
        map.put("enterpriseId",userVO.getEnterpriseId());
        map.put("type",type);
        List<EducationMajor> educationMajors = educationMajorMapper.selectByenterpriseIdAndType(map);
        List<EducationMajorReturnVO> vos = EducationMajorReturnVO.getEducationMajorReturnVOs4EducationMajors(educationMajors);
        return vos;
    }

}
