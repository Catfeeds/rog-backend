package com.rograndec.feijiayun.chain.business.distr.parent.vo.transfer;

import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.Date;

public class DistrReturnCheckListParam {


    /**
     * 登录uservo
     */
    private UserVO userVO;

    /**
     * 要货单位编码
     */
    private String requestUnitCode;

    /**
     * 要货单位名称
     */
    private String requestUnitName;
    /**
     * 配后退回验收/入库单号
     */
    private String code;

    /**
     * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
     */
    private Integer distrType;

    /**
     * 验收人员名称1/入库人员
     */
    private String checkerName;

    /**
     * 第二验收人员名称
     */
    private String secondCheckerName;

    private Date startTime;

    private Date endTime;

    private String orderName;
    private String orderType;

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public String getRequestUnitCode() {
        return requestUnitCode;
    }

    public void setRequestUnitCode(String requestUnitCode) {
        this.requestUnitCode = requestUnitCode;
    }

    public String getRequestUnitName() {
        return requestUnitName;
    }

    public void setRequestUnitName(String requestUnitName) {
        this.requestUnitName = requestUnitName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDistrType() {
        return distrType;
    }

    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    public String getCheckerName() {
        return checkerName;
    }

    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }

    public String getSecondCheckerName() {
        return secondCheckerName;
    }

    public void setSecondCheckerName(String secondCheckerName) {
        this.secondCheckerName = secondCheckerName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
