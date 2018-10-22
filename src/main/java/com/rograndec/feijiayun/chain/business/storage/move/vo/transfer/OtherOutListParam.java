package com.rograndec.feijiayun.chain.business.storage.move.vo.transfer;


import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.Date;

public class OtherOutListParam {

//    private Page page;

    private Date startTime;

    private Date endTime;

    private String code;

    private String outManName;

    private Integer outType;

    private UserVO userVO;
    private String order;
    private String sort;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public Integer getOutType() {
        return outType;
    }

    public void setOutType(Integer outType) {
        this.outType = outType;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public OtherOutListParam() {
    }

    public String getOutManName() {
        return outManName;
    }

    public void setOutManName(String outManName) {
        this.outManName = outManName;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public OtherOutListParam(Date startTime, Date endTime, String code, String outManName, Integer outType, UserVO userVO, String order, String sort) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.code = code;
        this.outManName = outManName;
        this.outType = outType;
        this.userVO = userVO;
        this.order = order;
        this.sort = sort;
    }
}
