package com.rograndec.feijiayun.chain.business.storage.goodshandle.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhengbin.jin
 * @version 1.0
 * @ClassName: GoodsHandleSaveOrupdateVO
 * @Description: 储存管理-商品处理-药品处理-Rest接口
 * @date 2017-09-27 17:27:38
 */
@ApiModel(value = "RequestGoodsHandleListVo", description = "储存管理-商品处理-药品处理")
public class RequestGoodsHandleListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "每页显示数据不能为空")
    @ApiModelProperty(value = "每页显示数据", required = true)
    private Integer pageSize;

    @NotNull(message = "页码不能为空")
    @ApiModelProperty(value = "页码", required = true)
    private Integer pageNo;

    @ApiModelProperty(value = "查询开始时间", required = false)
    private String startDate;

    @ApiModelProperty(value = "查询结束时间", required = false)
    private String endDate;
    /**
     * 处理单号
     */
    @ApiModelProperty(required = false, value = "处理单号")
    private String code;

    /**
     * 处理人员名称
     */
    @ApiModelProperty(required = false, value = "处理人员名称")
    private String handleManName;

    @ApiModelProperty(required = false, value = "企业id")
    private Long enterpriseId;

    /**
     * 处理结果（0-解除锁定；1-移动到不合格品货位）
     */
    @ApiModelProperty(required = false, value = "处理结果（0-解除锁定；1-移动到不合格品货位）")
    private Integer handleResult;

    @ApiModelProperty(required = false, value = "按日期排序0-升序 1-降序")
    private Integer sortDate;
    @ApiModelProperty(required = false, value = "按单号排序0-升序 1-降序")
    private Integer sortCode;
    private String sort;

    public Integer getSortDate() {
        return sortDate;
    }

    public void setSortDate(Integer sortDate) {
        this.sortDate = sortDate;
    }

    public Integer getSortCode() {
        return sortCode;
    }

    public void setSortCode(Integer sortCode) {
        this.sortCode = sortCode;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHandleManName() {
        return handleManName;
    }

    public void setHandleManName(String handleManName) {
        this.handleManName = handleManName;
    }

    public Integer getHandleResult() {
        return handleResult;
    }

    public void setHandleResult(Integer handleResult) {
        this.handleResult = handleResult;
    }
}