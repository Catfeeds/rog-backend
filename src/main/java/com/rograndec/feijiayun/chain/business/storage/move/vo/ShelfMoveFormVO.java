package com.rograndec.feijiayun.chain.business.storage.move.vo;

import com.rograndec.feijiayun.chain.business.storage.move.entity.ShelfMove;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ShelfMoveFormVO implements Serializable{
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 单据（货位移动）编码
     */
    @ApiModelProperty(value = "单据（货位移动）编码//移动单号")
    private String code;

    /**
     * 单据（货位移动）日期
     */
    @ApiModelProperty(value = "单据（货位移动）日期")
    private Date moveDate;

    /**
     * 移动人员名称
     */
    @ApiModelProperty(value = "移动人员名称")
    private Long moveManId;

    /**
     * 移动人员名称
     */
    @ApiModelProperty(value = "移动人员名称")
    private String moveManName;

    /**
     * 接收人员ID
     */
    @ApiModelProperty(value = "接收人员ID")
    private Long receiverId;
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

    /**
     * 是否是调用锁定状态货位移动
     */
    @ApiModelProperty(value = "是否是调用锁定状态商品的货位移动")
    private Boolean flag;

    /**
     * 右下角合计的数量
     */
    @ApiModelProperty(value = "右下角合计的数量---新增时不用给我传")
    private BigDecimal totalQuantity;

    /**
     * 明细的List<VO>
     */
    private List<ShelfMoveDetailVO> shelfMoveDetailVOList;

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

    public List<ShelfMoveDetailVO> getShelfMoveDetailVOList() {
        return shelfMoveDetailVOList;
    }

    public void setShelfMoveDetailVOList(List<ShelfMoveDetailVO> shelfMoveDetailVOList) {
        this.shelfMoveDetailVOList = shelfMoveDetailVOList;
    }

    public String getMoveReason() {
        return moveReason;
    }

    public void setMoveReason(String moveReason) {
        this.moveReason = moveReason;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public BigDecimal getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(BigDecimal totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Long getMoveManId() {
        return moveManId;
    }

    public void setMoveManId(Long moveManId) {
        this.moveManId = moveManId;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public static ShelfMoveFormVO converToVO(ShelfMove shelfMove) {
        ShelfMoveFormVO vo = new ShelfMoveFormVO();
        vo.setId(shelfMove.getId());
        vo.setCode(shelfMove.getCode());
        vo.setMoveDate(shelfMove.getMoveDate());
        vo.setMoveManName(shelfMove.getMoveManName());
        vo.setReceiverName(shelfMove.getReceiverName());
        vo.setMoveReason(shelfMove.getMoveReason());
        return vo;
    }
}
