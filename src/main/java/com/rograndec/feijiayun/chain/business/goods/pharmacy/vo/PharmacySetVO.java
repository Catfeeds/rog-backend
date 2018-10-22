package com.rograndec.feijiayun.chain.business.goods.pharmacy.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by zeshi.sun on 2017/9/9.
 */
public class PharmacySetVO implements Serializable {

    /**
     * ID
     */
    @ApiModelProperty(required = true, value = "ID")
    private Long id;

    /**
     * 编码
     */
    @ApiModelProperty(required = true, value = "编码")
    private String code;


    /**
     * 名称
     */
    @ApiModelProperty(required = true, value = "名称")
    private String name;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(required = true, value = "状态（0-禁用；1-启用）")
    private int status;

    /**
     * 0-用户自定义；1-系统默认
     */
    @ApiModelProperty(required = true, value = "0-用户自定义；1-系统默认")
    private int sysType;

    /**
     * 状态名称
     */
    @ApiModelProperty(required = true, value = "状态名称")
    private String statusName;

    /**
     * 备注
     */
    @ApiModelProperty(required = false, value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否可修改")
    private Boolean updateFlag;

    @ApiModelProperty(value = "是否可删除")
    private Boolean deleteFlag;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getSysType() {
        return sysType;
    }

    public void setSysType(int sysType) {
        this.sysType = sysType;
    }

    @Override
    public String toString() {
        return "PharmacySetVO[" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", statusName=" + statusName +
                ", remark=" + remark +
                ", sysType=" + sysType +
                ']';
    }

    public Boolean isUpdateFlag() {
      if(sysType == 1){
          return false;
      } else {
          return true;
      }
    }

    public void setUpdateFlag(Boolean updateFlag) {
        this.updateFlag = updateFlag;
    }

    public Boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
