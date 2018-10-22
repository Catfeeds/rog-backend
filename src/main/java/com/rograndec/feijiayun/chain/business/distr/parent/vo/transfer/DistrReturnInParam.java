package com.rograndec.feijiayun.chain.business.distr.parent.vo.transfer;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnCheck;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnInFormVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public class DistrReturnInParam {

    private UserVO userVO;

    private DistrReturnInFormVO distrReturnInFormVO;

    private String code;

    private DistrReturnCheck distrReturnCheck;

    private User storageMan;


    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public DistrReturnInFormVO getDistrReturnInFormVO() {
        return distrReturnInFormVO;
    }

    public void setDistrReturnInFormVO(DistrReturnInFormVO distrReturnInFormVO) {
        this.distrReturnInFormVO = distrReturnInFormVO;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DistrReturnCheck getDistrReturnCheck() {
        return distrReturnCheck;
    }

    public void setDistrReturnCheck(DistrReturnCheck distrReturnCheck) {
        this.distrReturnCheck = distrReturnCheck;
    }


    public User getStorageMan() {
        return storageMan;
    }

    public void setStorageMan(User storageMan) {
        this.storageMan = storageMan;
    }


}
