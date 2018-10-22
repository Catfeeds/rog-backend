package com.rograndec.feijiayun.chain.business.storage.move.vo.transfer;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherOutDetail;
import com.rograndec.feijiayun.chain.business.storage.move.vo.OtherOutFormVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;

/**
 * Created by zhaiwei on 2017/9/29.
 */
public class OtherOutHeaderParam {

    private UserVO userVO ;

    private OtherOutFormVO otherOutFormVO;

    private String code;

    private User outMan;

    private User auditMan;

    private FlowUnit flowUnit;

    private List<OtherOutDetail> otherOutDetails;

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public OtherOutFormVO getOtherOutFormVO() {
        return otherOutFormVO;
    }

    public void setOtherOutFormVO(OtherOutFormVO otherOutFormVO) {
        this.otherOutFormVO = otherOutFormVO;
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

    public User getAuditMan() {
        return auditMan;
    }

    public void setAuditMan(User auditMan) {
        this.auditMan = auditMan;
    }

    public FlowUnit getFlowUnit() {
        return flowUnit;
    }

    public void setFlowUnit(FlowUnit flowUnit) {
        this.flowUnit = flowUnit;
    }

    public List<OtherOutDetail> getOtherOutDetails() {
        return otherOutDetails;
    }

    public void setOtherOutDetails(List<OtherOutDetail> otherOutDetails) {
        this.otherOutDetails = otherOutDetails;
    }
}
