package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.distr.branch.constant.DistrType;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnCheck;
import com.rograndec.feijiayun.chain.common.constant.status.DistrReturnStatus;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_distr_return_check
 * 
 * 
 * @author Asze
 * 
 * 2017-10-08
 */
public class DistrReturnCheck4ReturnInVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long checkId;


    /**
     * 单据类型（5424）
     */
    @ApiModelProperty(value = "单据类型（5424）")
    private Integer orderType;

    /**
     * 配后退回验收单号
     */
    @ApiModelProperty(value = "配后退回验收单号")
    private String code;

    /**
     * 验收日期
     */
    @ApiModelProperty(value = "验收日期")
    private Date checkDate;


    /**
     * 配后退回通知单ID
     */
    @ApiModelProperty(value = "配后退回通知单ID")
    private Long noticeId;

    /**
     * 要货单位ID
     */
    @ApiModelProperty(value = "要货单位ID")
    private Long requestUnitId;

    /**
     * 要货单位编码
     */
    @ApiModelProperty(value = "要货单位编码")
    private String requestUnitCode;

    /**
     * 要货单位名称
     */
    @ApiModelProperty(value = "要货单位名称")
    private String requestUnitName;

    /**
     * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
     */
    @ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
    private Integer distrType;

    @ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
    private String distrTypeDesc;

    /**
     * 验收人员ID
     */
    @ApiModelProperty(value = "验收人员ID")
    private Long checkerId;

    /**
     * 验收人员编码
     */
    @ApiModelProperty(value = "验收人员编码")
    private String checkerCode;

    /**
     * 验收人员名称
     */
    @ApiModelProperty(value = "验收人员名称")
    private String checkerName;

    /**
     * 第二验收人员ID
     */
    @ApiModelProperty(value = "第二验收人员ID")
    private Long secondCheckerId;

    /**
     * 第二验收人员编码
     */
    @ApiModelProperty(value = "第二验收人员编码")
    private String secondCheckerCode;

    /**
     * 第二验收人员名称
     */
    @ApiModelProperty(value = "第二验收人员名称")
    private String secondCheckerName;

    /**
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
    @ApiModelProperty(value = "品种数量")
    private Integer varietiesQuantity;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "状态描述")
    private String statusDesc;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "配后退回入库单的明细单集合,列表时为空,在详情页面才会有值")
    private List<DistrReturnCheckDetail4ReturnInVO> distrReturnCheckDetail4ReturnInVOS;

   public static List<DistrReturnCheck4ReturnInVO> getDistrReturnCheck4ReturnInVOs(List<DistrReturnCheck> distrReturnChecks){
       List<DistrReturnCheck4ReturnInVO> distrReturnCheck4ReturnInVOS = new ArrayList<>();

       distrReturnChecks.forEach( dr -> {
           DistrReturnCheck4ReturnInVO distrReturnCheck4ReturnInVO = getDistrReturnCheck4ReturnInVO(dr);
           distrReturnCheck4ReturnInVOS.add(distrReturnCheck4ReturnInVO);
       });

       return distrReturnCheck4ReturnInVOS;
   }

    public static DistrReturnCheck4ReturnInVO getDistrReturnCheck4ReturnInVO(DistrReturnCheck distrReturnCheck){

        DistrReturnCheck4ReturnInVO distrReturnCheck4ReturnInVO = new DistrReturnCheck4ReturnInVO();
        /**
         * 主键ID
         */
        distrReturnCheck4ReturnInVO.setCheckId(distrReturnCheck.getId());

        /**
         * 单据类型（5424）
         */
        distrReturnCheck4ReturnInVO.setOrderType(distrReturnCheck.getOrderType());

        /**
         * 配后退回验收单号
         */
        distrReturnCheck4ReturnInVO.setCode(distrReturnCheck.getCode());

        /**
         * 验收日期
         */
        distrReturnCheck4ReturnInVO.setCheckDate(distrReturnCheck.getCheckDate());

        /**
         * 配后退回通知单ID
         */
        distrReturnCheck4ReturnInVO.setNoticeId(distrReturnCheck.getNoticeId());

        /**
         * 要货单位ID
         */
        distrReturnCheck4ReturnInVO.setRequestUnitId(distrReturnCheck.getRequestUnitId());

        /**
         * 要货单位编码
         */
        distrReturnCheck4ReturnInVO.setRequestUnitCode(distrReturnCheck.getRequestUnitCode());

        /**
         * 要货单位名称
         */
        distrReturnCheck4ReturnInVO.setRequestUnitName(distrReturnCheck.getRequestUnitName());

        /**
         * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
         */
        distrReturnCheck4ReturnInVO.setDistrType(distrReturnCheck.getDistrType());
        distrReturnCheck4ReturnInVO.setDistrTypeDesc(DistrType.getValue(distrReturnCheck.getDistrType()));

        /**
         * 验收人员ID
         */
        distrReturnCheck4ReturnInVO.setCheckerId(distrReturnCheck.getCheckerId());

        /**
         * 验收人员编码
         */
        distrReturnCheck4ReturnInVO.setCheckerCode(distrReturnCheck.getCheckerCode());

        /**
         * 验收人员名称
         */
        distrReturnCheck4ReturnInVO.setCheckerName(distrReturnCheck.getCheckerName());

        /**
         * 第二验收人员ID
         */
        distrReturnCheck4ReturnInVO.setSecondCheckerId(distrReturnCheck.getSecondCheckerId());

        /**
         * 第二验收人员编码
         */
        distrReturnCheck4ReturnInVO.setSecondCheckerCode(distrReturnCheck.getSecondCheckerCode());

        /**
         * 第二验收人员名称
         */
        distrReturnCheck4ReturnInVO.setSecondCheckerName(distrReturnCheck.getSecondCheckerName());

        /**
         * 数量合计
         */
        distrReturnCheck4ReturnInVO.setQuantityTotal(distrReturnCheck.getQuantityTotal());

        /**
         * 品种数量
         */
        distrReturnCheck4ReturnInVO.setVarietiesQuantity(distrReturnCheck.getVarietiesQuantity());

        /**
         * 状态
         */
        distrReturnCheck4ReturnInVO.setStatus(distrReturnCheck.getStatus());

        distrReturnCheck4ReturnInVO.setStatusDesc(DistrReturnStatus.getStatusDesc(distrReturnCheck.getStatus()));

        distrReturnCheck4ReturnInVO.setRemark(distrReturnCheck.getRemark());

        return distrReturnCheck4ReturnInVO;
    }



    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public Long getRequestUnitId() {
        return requestUnitId;
    }

    public void setRequestUnitId(Long requestUnitId) {
        this.requestUnitId = requestUnitId;
    }

    public String getRequestUnitCode() {
        return requestUnitCode;
    }

    public void setRequestUnitCode(String requestUnitCode) {
        this.requestUnitCode = requestUnitCode;
    }

    public String getRequestUnitName() {
        return requestUnitName;
    }

    public void setRequestUnitName(String requestUnitName) {
        this.requestUnitName = requestUnitName;
    }

    public Integer getDistrType() {
        return distrType;
    }

    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    public Long getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(Long checkerId) {
        this.checkerId = checkerId;
    }

    public String getCheckerCode() {
        return checkerCode;
    }

    public void setCheckerCode(String checkerCode) {
        this.checkerCode = checkerCode;
    }

    public String getCheckerName() {
        return checkerName;
    }

    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName;
    }

    public Long getSecondCheckerId() {
        return secondCheckerId;
    }

    public void setSecondCheckerId(Long secondCheckerId) {
        this.secondCheckerId = secondCheckerId;
    }

    public String getSecondCheckerCode() {
        return secondCheckerCode;
    }

    public void setSecondCheckerCode(String secondCheckerCode) {
        this.secondCheckerCode = secondCheckerCode;
    }

    public String getSecondCheckerName() {
        return secondCheckerName;
    }

    public void setSecondCheckerName(String secondCheckerName) {
        this.secondCheckerName = secondCheckerName;
    }

    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public Integer getVarietiesQuantity() {
        return varietiesQuantity;
    }

    public void setVarietiesQuantity(Integer varietiesQuantity) {
        this.varietiesQuantity = varietiesQuantity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public List<DistrReturnCheckDetail4ReturnInVO> getDistrReturnCheckDetail4ReturnInVOS() {
        return distrReturnCheckDetail4ReturnInVOS;
    }

    public void setDistrReturnCheckDetail4ReturnInVOS(List<DistrReturnCheckDetail4ReturnInVO> distrReturnCheckDetail4ReturnInVOS) {
        this.distrReturnCheckDetail4ReturnInVOS = distrReturnCheckDetail4ReturnInVOS;
    }

    public Long getCheckId() {
        return checkId;
    }

    public void setCheckId(Long checkId) {
        this.checkId = checkId;
    }

    public String getDistrTypeDesc() {
        return distrTypeDesc;
    }

    public void setDistrTypeDesc(String distrTypeDesc) {
        this.distrTypeDesc = distrTypeDesc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}