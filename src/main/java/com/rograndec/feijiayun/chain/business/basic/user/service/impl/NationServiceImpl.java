package com.rograndec.feijiayun.chain.business.basic.user.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.NationMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.Nation;
import com.rograndec.feijiayun.chain.business.basic.user.service.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaiwei on 2017/8/28.
 */
@Service
public class NationServiceImpl implements NationService{



    @Autowired
    private NationMapper nationMapper;


    @Override
    public List<Nation> queryNationsAll(){
       return nationMapper.selectAll();
    }


}
