package com.rograndec.feijiayun.chain.business.storage.inventory.vo;

import com.rograndec.feijiayun.chain.business.storage.inventory.vo.diff.InventoryShelfSimpleForDiffVO;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * 功能描述：
 * Created by ST on 2017/10/6 19:49
 */

public class InventoryForDiffVO {
    @ApiModelProperty(value = "盘点单主键")
    private Long id;

    /**
     * 处理日期
     */
    @ApiModelProperty(value = "处理日期")
    private Date handleDate;

    /**
     * 处理人员ID
     */
    @ApiModelProperty(value = "处理人员ID")
    private Long handleManId;

    @ApiModelProperty(value = "盘点明细")
    private List<InventoryShelfSimpleForDiffVO> detailForAddVOList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(Date handleDate) {
        this.handleDate = handleDate;
    }

    public Long getHandleManId() {
        return handleManId;
    }

    public void setHandleManId(Long handleManId) {
        this.handleManId = handleManId;
    }

    public List<InventoryShelfSimpleForDiffVO> getDetailForAddVOList() {
        return detailForAddVOList;
    }

    public void setDetailForAddVOList(List<InventoryShelfSimpleForDiffVO> detailForAddVOList) {
        this.detailForAddVOList = detailForAddVOList;
    }
}