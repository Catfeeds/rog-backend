package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.purchase.check.vo.TestReportIdsVO;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class DistrReturnInFromNoticeSheltDtlVO implements Serializable{

    //  ---------验收----------------

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID", required = true)
    private Long goodsId;

    /**
     * 批号ID
     */
    @ApiModelProperty(value = "批号ID", required = true)
    private Long lotId;

    /**
     * 批号
     */
    @ApiModelProperty(value = "批号", required = true)
    private String lotNumber;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期", required = true)
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期", required = true)
    private Date validDate;

    /**
     * 检验项目ID集合，多个用逗号分隔
     */
    private String[] checkProjectIds;

    @ApiModelProperty(value = "检验项目ID集合，多个用逗号分隔", required = true)
    private String checkProIds;

    public String getCheckProIds() {
        return checkProIds;
    }

    public void setCheckProIds(String checkProIds) {
        this.checkProIds = checkProIds;
    }

    /**
     * 检验报告书（附件ID）集合
     */
    @ApiModelProperty(value = "检验报告书（附件ID）集合", required = true)
    private String testReportIds;

    /**
     * 检验报告书（附件ID）集合　
     */
    @ApiModelProperty(value = "检验报告书（附件ID）集合", required = true)
    private List<TestReportIdsVO> testReportIdsVO;

    /**
     * 收货数量
     */
    @ApiModelProperty(value = "收货数量", required = true)
    private BigDecimal receiveQuantity;



    /**
     * 抽样数量
     */
    @ApiModelProperty(value = "抽样数量", required = true)
    private BigDecimal samplingQuantity;

    /**
     * 验收合格数量
     */
    @ApiModelProperty(value = "验收合格数量", required = true)
    private BigDecimal qualifiedQuantity;

    /**
     * 验收结论ID集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "验收结论ID集合，多个用逗号分隔", required = true)
    private String conclusionIds;

    /**
     * 验收不合格数量
     */
    @ApiModelProperty(value = "验收不合格数量", required = true)
    private BigDecimal unqualifiedQuantity;

    /**
     * 验收不合格原因ID集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "验收不合格原因ID集合，多个用逗号分隔", required = true)
    private String[] unqualifiedReasonIds;

    /**
     * 处置措施ID集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "处置措施ID集合，多个用逗号分隔", required = true)
    private String measuresIds;


    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = true)
    private String remark;



    // ----------入库----------------
    /**
     * 货位ID
     */
    @ApiModelProperty(value = "不合格货位ID",required = true)
    private Long unqualifiedShelfId;

    /**
     * 货位ID
     */
    @ApiModelProperty(value = "不合格货位名称",required = true)
    private String unqualifiedShelfName;


    @ApiModelProperty(value = "合格货位ID",required = true)
    private Long shelfId;


    /**
     * 货位名称
     */
    @ApiModelProperty(value = "货位名称")
    private String shelfName;


    @ApiParam(value = "质量状况0-合格品，2不合格品", required = true)
    private Integer jobArea;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号",required = true)
    private Integer lineNum;


    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }


    public Integer getJobArea() {
        return jobArea;
    }

    public void setJobArea(Integer jobArea) {
        this.jobArea = jobArea;
    }

    public Integer getLineNum() {
        return lineNum;
    }

    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }


    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
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

    public String[] getCheckProjectIds() {
        return checkProjectIds;
    }

    public void setCheckProjectIds(String[] checkProjectIds) {
        this.checkProjectIds = checkProjectIds;
    }

    public String getTestReportIds() {
        return testReportIds;
    }

    public void setTestReportIds(String testReportIds) {
        this.testReportIds = testReportIds;
    }

    public List<TestReportIdsVO> getTestReportIdsVO() {
        return testReportIdsVO;
    }

    public void setTestReportIdsVO(List<TestReportIdsVO> testReportIdsVO) {
        this.testReportIdsVO = testReportIdsVO;
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

    public BigDecimal getUnqualifiedQuantity() {
        return unqualifiedQuantity;
    }

    public void setUnqualifiedQuantity(BigDecimal unqualifiedQuantity) {
        this.unqualifiedQuantity = unqualifiedQuantity;
    }

    public String[] getUnqualifiedReasonIds() {
        return unqualifiedReasonIds == null? new String[]{} : unqualifiedReasonIds;
    }

    public void setUnqualifiedReasonIds(String[] unqualifiedReasonIds) {
        this.unqualifiedReasonIds = unqualifiedReasonIds;
    }

    public String getMeasuresIds() {
        return measuresIds;
    }

    public void setMeasuresIds(String measuresIds) {
        this.measuresIds = measuresIds;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Long getUnqualifiedShelfId() {
        return unqualifiedShelfId;
    }

    public void setUnqualifiedShelfId(Long unqualifiedShelfId) {
        this.unqualifiedShelfId = unqualifiedShelfId;
    }


    public String getUnqualifiedShelfName() {
        return unqualifiedShelfName;
    }

    public void setUnqualifiedShelfName(String unqualifiedShelfName) {
        this.unqualifiedShelfName = unqualifiedShelfName;
    }
}
