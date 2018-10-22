package com.rograndec.feijiayun.chain.business.storage.move.vo;

import com.rograndec.feijiayun.chain.business.storage.move.entity.ShelfMove;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class ShelfMovePageVO implements Serializable{

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 移动单号
     */
    @ApiModelProperty(value = "移动单号")
    private String code;

    /**
     * 移动日期
     */
    @ApiModelProperty(value = "移动日期")
    private Date moveDate;

    /**
     * 移动人员名称
     */
    @ApiModelProperty(value = "移动人员名称")
    private String moveManName;

    /**
     * 接收人员名称
     */
    @ApiModelProperty(value = "接收人员名称")
    private String receiverName;

    /**
     * 移动原因
     */
    @ApiModelProperty(value = "移动原因")
    private String moveReason;

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

    public Date getMoveDate() {
        return moveDate;
    }

    public void setMoveDate(Date moveDate) {
        this.moveDate = moveDate;
    }

    public String getMoveManName() {
        return moveManName;
    }

    public void setMoveManName(String moveManName) {
        this.moveManName = moveManName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getMoveReason() {
        return moveReason;
    }

    public void setMoveReason(String moveReason) {
        this.moveReason = moveReason;
    }

    public static ShelfMovePageVO convertToPageVO(ShelfMove s) {
        ShelfMovePageVO pageVO = new ShelfMovePageVO();
        if (s != null){
            pageVO.setId(s.getId());
            pageVO.setCode(s.getCode());
            pageVO.setMoveDate(s.getMoveDate());
            pageVO.setMoveManName(s.getMoveManName());
            pageVO.setReceiverName(s.getReceiverName());
            pageVO.setMoveReason(s.getMoveReason());
        }
        return pageVO;
    }
}
