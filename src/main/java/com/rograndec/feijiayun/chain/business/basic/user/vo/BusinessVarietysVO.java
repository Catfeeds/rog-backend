package com.rograndec.feijiayun.chain.business.basic.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@ApiModel
public class BusinessVarietysVO implements Serializable {
    /**
     * 受限品种
     */
    @ApiModelProperty(value = "受限品种数组")
    @NotNull(message = "受限品种不能为空")
    @Size(message = "受限品种不能为空")
    private List<Integer> businessVarietys;

    /**
     * 类型
     */
    @ApiModelProperty(value = "员工管理:0;供应商员工:1,默认为0")
    private Integer type = 0;

    @ApiModelProperty(value = "如果查询类型是员工管理则传入企业id,如果选择是的供应商员工管理则传入的是供应商id")
    @NotNull(message = "企业id或者供应商id不能为空")
    private Long enterpriseId;

    public List<Integer> getBusinessVarietys() {
        return businessVarietys;
    }

    public void setBusinessVarietys(List<Integer> businessVarietys) {
        this.businessVarietys = businessVarietys;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}