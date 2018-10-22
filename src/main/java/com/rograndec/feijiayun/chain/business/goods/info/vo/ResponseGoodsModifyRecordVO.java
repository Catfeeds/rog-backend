package com.rograndec.feijiayun.chain.business.goods.info.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class ResponseGoodsModifyRecordVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 表名
     */
    @ApiModelProperty(value = "表名")
    private String tableName;

    /**
     * 商品的主键
     */
    @ApiModelProperty(value = "商品的主键")
    private Long goodsId;

    /**
     * 修改原因
     */
    @ApiModelProperty(value = "修改原因")
    private String reason;

    /**
     * 修改人ID
     */
    @ApiModelProperty(value = "修改人ID")
    private Long modifierId;

    /**
     * 修改人编码
     */
    @ApiModelProperty(value = "修改人编码")
    private String modifierCode;

    /**
     * 修改人名称
     */
    @ApiModelProperty(value = "修改人名称")
    private String modifierName;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 修改字段英文名称
     */
    @ApiModelProperty(value = "修改字段英文名称")
    private String columnEnName;

    /**
     * 修改字段中文名称（修改项目）
     */
    @ApiModelProperty(value = "修改字段中文名称（修改项目）")
    private String columnChName;

    /**
     * 原内容
     */
    @ApiModelProperty(value = "原内容")
    private String oldContent;

    /**
     * 新内容
     */
    @ApiModelProperty(value = "新内容")
    private String newContent;



    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 表名
     * @return table_name 表名
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 表名
     * @param tableName 表名
     */
    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    /**
     * 主键ID
     * @return goods_id 主键ID
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 主键ID
     * @param goodsId 主键ID
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 修改原因
     * @return reason 修改原因
     */
    public String getReason() {
        return reason;
    }

    /**
     * 修改原因
     * @param reason 修改原因
     */
    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    /**
     * 修改人ID
     * @return modifier_id 修改人ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 修改人ID
     * @param modifierId 修改人ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 修改人编码
     * @return modifier_code 修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 修改人编码
     * @param modifierCode 修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 修改人名称
     * @return modifier_name 修改人名称
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 修改人名称
     * @param modifierName 修改人名称
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 修改时间
     * @return update_time 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public String getColumnEnName() {
        return columnEnName;
    }

    public void setColumnEnName(String columnEnName) {
        this.columnEnName = columnEnName;
    }

    public String getColumnChName() {
        return columnChName;
    }

    public void setColumnChName(String columnChName) {
        this.columnChName = columnChName;
    }

    public String getOldContent() {
        return oldContent;
    }

    public void setOldContent(String oldContent) {
        this.oldContent = oldContent;
    }

    public String getNewContent() {
        return newContent;
    }

    public void setNewContent(String newContent) {
        this.newContent = newContent;
    }
}