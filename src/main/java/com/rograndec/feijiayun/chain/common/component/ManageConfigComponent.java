package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by ST on 2017/8/26.
 */
@Component
public class ManageConfigComponent {

    @Autowired
    private ManageConfigMapper manageConfigMapper;


    @Autowired
    EnterpriseBusinessMapper businessMapper;

    /**
     * 根据企业id查询企业设置信息
     * @param user
     * @return
     */
    public ManageConfig getMangeConfigByEPId(UserVO user){

        Long enterpriseId = user.getEnterpriseId();

        return manageConfigMapper.getMangeConfigByEPId(enterpriseId);
    }

    /**
     * 根据企业id查询企业设置信息
     * @param user
     * @return
     */
    public ManageConfig getMangeConfigByHeadquarters(UserVO user){

        Long enterpriseId = ChainType.getHeadEnterpriseId(user);

        return manageConfigMapper.getMangeConfigByEPId(enterpriseId);
    }

    public ManageConfig getCurrentUser(UserVO user){
        return manageConfigMapper.selectByCurrentUser(user);
    }

    public ManageConfig getMangeConfigByEnterpriseId(Long enterpriseId){
        return manageConfigMapper.getMangeConfigByEPId(enterpriseId);
    }
}