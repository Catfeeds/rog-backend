package com.rograndec.feijiayun.chain.business.distr.branch.vo.transfer;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturn;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReturnOutFormVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public class DistrInReturnOutParam {

    private UserVO userVO;

    private DistrInReturnOutFormVO distrInReturnOutFormVO;

    private DistrInReturn distrInReturn;

    private String code;

    private User outMan;

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public DistrInReturnOutFormVO getDistrInReturnOutFormVO() {
        return distrInReturnOutFormVO;
    }

    public void setDistrInReturnOutFormVO(DistrInReturnOutFormVO distrInReturnOutFormVO) {
        this.distrInReturnOutFormVO = distrInReturnOutFormVO;
    }

    public DistrInReturn getDistrInReturn() {
        return distrInReturn;
    }

    public void setDistrInReturn(DistrInReturn distrInReturn) {
        this.distrInReturn = distrInReturn;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getOutMan() {
        return outMan;
    }

    public void setOutMan(User outMan) {
        this.outMan = outMan;
    }
}
