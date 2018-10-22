package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnCheckLot;
import com.rograndec.feijiayun.chain.business.system.set.entity.QualitySet;
import com.rograndec.feijiayun.chain.utils.string.StringSplit;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_distr_return_check_lot
 * 
 * 
 * @author Asze
 * 
 * 2017-10-08
 */
public class DistrReturnCheckLot4ReturnInVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long checkLotId;


    /**
     * 配后退回验收品种单ID
     */
    @ApiModelProperty(value = "配后退回验收品种单ID")
    private Long checkDtId;

    /**
     * 配进验收单ID
     */
    @ApiModelProperty(value = "配进验收单ID")
    private Long checkId;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNumber;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    private Date validDate;


    /**
     * 检验项目ID集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "检验项目ID集合，多个用逗号分隔")
    private String checkProjectIds;

    @ApiModelProperty(value = "检验项目描述，多个用逗号分隔")
    private String checkProjectDescs;

    /**
     * 收货数量
     */
    @ApiModelProperty(value = "收货数量")
    private BigDecimal receiveQuantity;

    /**
     * 抽样数量
     */
    @ApiModelProperty(value = "抽样数量")
    private BigDecimal samplingQuantity;

    /**
     * 验收合格数量
     */
    @ApiModelProperty(value = "验收合格数量")
    private BigDecimal qualifiedQuantity;

    /**
     * 验收结论ID集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "验收结论ID集合，多个用逗号分隔")
    private String conclusionIds;

    @ApiModelProperty(value = "检验结论描述，多个用逗号分隔")
    private String conclusionDescs;

    /**
     * 验收不合格数量
     */
    @ApiModelProperty(value = "验收不合格数量")
    private BigDecimal unqualifiedQuantity;

    /**
     * 验收不合格原因ID集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "验收不合格原因ID集合，多个用逗号分隔")
    private String unqualifiedReasonIds;

    @ApiModelProperty(value = "验收不合格原因描述集合，多个用逗号分隔")
    private String unqualifiedReasonDescs;

    /**
     * 处置措施ID集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "处置措施ID集合，多个用逗号分隔")
    private String measuresIds;

    @ApiModelProperty(value = "处置措施描述集合，多个用逗号分隔")
    private String measuresDescs;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


    public static DistrReturnCheckLot4ReturnInVO getDistrReturnCheckLot4ReturnInVO(DistrReturnCheckLot distrReturnCheckLot, List<QualitySet> qualitySets){

        DistrReturnCheckLot4ReturnInVO distrReturnCheckLot4ReturnInVO = new DistrReturnCheckLot4ReturnInVO();

        /**
         * 主键ID
         */
        distrReturnCheckLot4ReturnInVO.setCheckLotId(distrReturnCheckLot.getId());

        /**
         * 配后退回验收品种单ID
         */
        distrReturnCheckLot4ReturnInVO.setCheckDtId(distrReturnCheckLot.getDtlId());

        /**
         * 配进验收单ID
         */
        distrReturnCheckLot4ReturnInVO.setCheckId(distrReturnCheckLot.getCheckId());

        /**
         * 批号
         */
        distrReturnCheckLot4ReturnInVO.setLotNumber(distrReturnCheckLot.getLotNumber());

        /**
         * 生产日期
         */
        distrReturnCheckLot4ReturnInVO.setProductDate(distrReturnCheckLot.getProductDate());

        /**
         * 有效期
         */
        distrReturnCheckLot4ReturnInVO.setValidDate(distrReturnCheckLot.getValidDate());

        /**
         * 检验项目ID集合，多个用逗号分隔
         */
        distrReturnCheckLot4ReturnInVO.setCheckProjectIds(distrReturnCheckLot.getCheckProjectIds());

        String checkProjectIds = distrReturnCheckLot.getCheckProjectIds();
        List<Long> checkProjectIdList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(checkProjectIdList)){
            checkProjectIdList = StringSplit.strSplit(checkProjectIds);

            List<String> checkProjectDescs = new ArrayList<>();

            checkProjectIdList.forEach(cp -> {
                qualitySets.forEach(qf -> {
                    //校验项目类型
                    if(qf.getId().equals(cp)){
                        checkProjectDescs.add(qf.getDescription());
                    }

                });

            });

            /**
             * 检验项目描述，多个用逗号分隔
             */
            distrReturnCheckLot4ReturnInVO.setCheckProjectDescs(StringSplit.StringAppendSymbol(checkProjectDescs));
        }



        /**
         * 收货数量
         */
        distrReturnCheckLot4ReturnInVO.setReceiveQuantity(distrReturnCheckLot.getReceiveQuantity());

        /**
         * 抽样数量
         */
        distrReturnCheckLot4ReturnInVO.setSamplingQuantity(distrReturnCheckLot.getSamplingQuantity());

        /**
         * 验收合格数量
         */
        distrReturnCheckLot4ReturnInVO.setQualifiedQuantity(distrReturnCheckLot.getQualifiedQuantity());

        /**
         * 验收结论ID集合，多个用逗号分隔
         */
        distrReturnCheckLot4ReturnInVO.setConclusionIds(distrReturnCheckLot.getConclusionIds());

        String conclusionIds = distrReturnCheckLot.getConclusionIds();

        if(!StringUtils.isEmpty(conclusionIds)){
            List<Long> list = StringSplit.strSplit(conclusionIds);

            List<String> conclusionDescs = new ArrayList<>();

            list.forEach( l -> {
                qualitySets.forEach(qf -> {
                    //验收结论
                    if(qf.getId().equals(l)){
                        conclusionDescs.add(qf.getDescription());
                    }

                });
            });

            /**
             * 检验结论描述，多个用逗号分隔
             */
            distrReturnCheckLot4ReturnInVO.setConclusionDescs(StringSplit.StringAppendSymbol(conclusionDescs));

        }



        /**
         * 验收不合格数量
         */
        distrReturnCheckLot4ReturnInVO.setUnqualifiedQuantity(distrReturnCheckLot.getUnqualifiedQuantity());

        /**
         * 验收不合格原因ID集合，多个用逗号分隔
         */
        distrReturnCheckLot4ReturnInVO.setUnqualifiedReasonIds(distrReturnCheckLot.getUnqualifiedReasonIds());



        String unqualifiedReasonIds = distrReturnCheckLot.getUnqualifiedReasonIds();

        if(!StringUtils.isEmpty(unqualifiedReasonIds)){
            List<Long> list = StringSplit.strSplit(unqualifiedReasonIds);

            List<String> unqualifiedReasonDescs = new ArrayList<>();

            list.forEach( l -> {
                qualitySets.forEach(qf -> {
                    //验收结论
                    if(qf.getId().equals(l)){
                        unqualifiedReasonDescs.add(qf.getDescription());
                    }

                });
            });

            /**
             * 验收不合格原因描述，多个用逗号分隔
             */
            distrReturnCheckLot4ReturnInVO.setUnqualifiedReasonDescs(StringSplit.StringAppendSymbol(unqualifiedReasonDescs));

        }


        /**
         * 处置措施ID集合，多个用逗号分隔
         */
        distrReturnCheckLot4ReturnInVO.setMeasuresIds(distrReturnCheckLot.getMeasuresIds());


        String measuresIds = distrReturnCheckLot.getMeasuresIds();

        if(!StringUtils.isEmpty(measuresIds)){
            List<Long> list = StringSplit.strSplit(measuresIds);

            List<String> measuresDescs = new ArrayList<>();

            list.forEach( l -> {
                qualitySets.forEach(qf -> {
                    //验收结论
                    if(qf.getId().equals(l)){
                        measuresDescs.add(qf.getDescription());
                    }

                });
            });

            /**
             * 处置措施描述集合，多个用逗号分隔
             */
            distrReturnCheckLot4ReturnInVO.setMeasuresDescs(StringSplit.StringAppendSymbol(measuresDescs));

        }

        /**
         * 状态
         */
        distrReturnCheckLot4ReturnInVO.setStatus(distrReturnCheckLot.getStatus());

        /**
         * 备注
         */
        distrReturnCheckLot4ReturnInVO.setRemark(distrReturnCheckLot.getRemark());

        return distrReturnCheckLot4ReturnInVO;
    }


    public Long getCheckLotId() {
        return checkLotId;
    }

    public void setCheckLotId(Long checkLotId) {
        this.checkLotId = checkLotId;
    }

    public Long getCheckDtId() {
        return checkDtId;
    }

    public void setCheckDtId(Long checkDtId) {
        this.checkDtId = checkDtId;
    }

    public Long getCheckId() {
        return checkId;
    }

    public void setCheckId(Long checkId) {
        this.checkId = checkId;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public String getCheckProjectIds() {
        return checkProjectIds;
    }

    public void setCheckProjectIds(String checkProjectIds) {
        this.checkProjectIds = checkProjectIds;
    }

    public String getCheckProjectDescs() {
        return checkProjectDescs;
    }

    public void setCheckProjectDescs(String checkProjectDescs) {
        this.checkProjectDescs = checkProjectDescs;
    }

    public BigDecimal getReceiveQuantity() {
        return receiveQuantity;
    }

    public void setReceiveQuantity(BigDecimal receiveQuantity) {
        this.receiveQuantity = receiveQuantity;
    }

    public BigDecimal getSamplingQuantity() {
        return samplingQuantity;
    }

    public void setSamplingQuantity(BigDecimal samplingQuantity) {
        this.samplingQuantity = samplingQuantity;
    }

    public BigDecimal getQualifiedQuantity() {
        return qualifiedQuantity;
    }

    public void setQualifiedQuantity(BigDecimal qualifiedQuantity) {
        this.qualifiedQuantity = qualifiedQuantity;
    }

    public String getConclusionIds() {
        return conclusionIds;
    }

    public void setConclusionIds(String conclusionIds) {
        this.conclusionIds = conclusionIds;
    }

    public String getConclusionDescs() {
        return conclusionDescs;
    }

    public void setConclusionDescs(String conclusionDescs) {
        this.conclusionDescs = conclusionDescs;
    }

    public BigDecimal getUnqualifiedQuantity() {
        return unqualifiedQuantity;
    }

    public void setUnqualifiedQuantity(BigDecimal unqualifiedQuantity) {
        this.unqualifiedQuantity = unqualifiedQuantity;
    }

    public String getUnqualifiedReasonIds() {
        return unqualifiedReasonIds;
    }

    public void setUnqualifiedReasonIds(String unqualifiedReasonIds) {
        this.unqualifiedReasonIds = unqualifiedReasonIds;
    }

    public String getUnqualifiedReasonDescs() {
        return unqualifiedReasonDescs;
    }

    public void setUnqualifiedReasonDescs(String unqualifiedReasonDescs) {
        this.unqualifiedReasonDescs = unqualifiedReasonDescs;
    }

    public String getMeasuresIds() {
        return measuresIds;
    }

    public void setMeasuresIds(String measuresIds) {
        this.measuresIds = measuresIds;
    }

    public String getMeasuresDescs() {
        return measuresDescs;
    }

    public void setMeasuresDescs(String measuresDescs) {
        this.measuresDescs = measuresDescs;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}