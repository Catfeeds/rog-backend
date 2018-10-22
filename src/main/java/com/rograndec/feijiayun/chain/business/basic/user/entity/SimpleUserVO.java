package com.rograndec.feijiayun.chain.business.basic.user.entity;


import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SimpleUserVO implements Serializable {


	/**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 企业ID
     */
    @ApiModelProperty("企业ID")
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty("上级企业ID")
    private Long parentId;

    /**
     * 编码
     */
    @ApiModelProperty("编码")
    private String code;

    /**
     * 检索码
     */
    @ApiModelProperty("检索码")
    private String pinyin;

    /**
     * 员工名称
     */
    @ApiModelProperty("员工名称")
    private String name;
    
    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty("状态（0-禁用；1-启用）")
    private Integer status;

    public static List<SimpleUserVO> getSimpleUserVOs(List<User> users){

        List<SimpleUserVO> simpleUserVOS = new ArrayList<>();
        for(User user : users){
            simpleUserVOS.add(getSimpleUserVO(user));
        }
        return simpleUserVOS;
    }

    public static SimpleUserVO getSimpleUserVO(User user){

        SimpleUserVO userVO = new SimpleUserVO();
        userVO.setId(user.getId());
        userVO.setCode(user.getCode());
        userVO.setEnterpriseId(user.getEnterpriseId());
        userVO.setName(user.getName());
        userVO.setParentId(user.getParentId());
        userVO.setPinyin(user.getPinyin());
        userVO.setStatus(user.getStatus());

        return userVO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}