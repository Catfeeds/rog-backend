package com.rograndec.feijiayun.chain.business.storage.inventory.vo.post;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_inventory
 * 
 * 
 * @author ST
 * 
 * 2017-09-29
 */
public class InventoryForPostDetailVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 盘点单号
     */
    @ApiModelProperty(value = "盘点单号")
    private String code;

    /**
     * 过账日期
     */
    @ApiModelProperty(value = "过账日期")
    private Date postDate;

    /**
     * 过账人员ID
     */
    @ApiModelProperty(value = "过账人员ID")
    private Long postManId;


    /**
     * 过账人员名称
     */
    @ApiModelProperty(value = "过账人员名称")
    private String postManName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


    private List<InventoryDetailForPostVO> postVOList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Long getPostManId() {
        return postManId;
    }

    public void setPostManId(Long postManId) {
        this.postManId = postManId;
    }

    public String getPostManName() {
        return postManName;
    }

    public void setPostManName(String postManName) {
        this.postManName = postManName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<InventoryDetailForPostVO> getPostVOList() {
        return postVOList;
    }

    public void setPostVOList(List<InventoryDetailForPostVO> postVOList) {
        this.postVOList = postVOList;
    }
}