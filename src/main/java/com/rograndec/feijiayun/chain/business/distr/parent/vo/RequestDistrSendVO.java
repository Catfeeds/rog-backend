package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOut;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnIn;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnInDetail;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/9/30 <br/>
 * 描述：配送单新增-保存传参
 */
public class RequestDistrSendVO {

    @ApiModelProperty(value = "配货人员ID",required = true)
    private Long distrManId;

    @ApiModelProperty(value = "配货日期",required = true)
    private Date distrDate;

    @ApiModelProperty(value = "要货单位ID",required = true)
    private Long requestUnitId;

    @ApiModelProperty(value = "整单折扣",required = true)
    private BigDecimal wholeDiscount;

    @ApiModelProperty(value = "整单优惠",required = true)
    private BigDecimal wholeDiscountAmount;

    @ApiModelProperty(value = "商品列表",required = true)
    List<RequestDistrGoods> distrGoogsList;

    @ApiModelProperty(value = "修改配货单时，配货单ID",required = false)
    private Long id;

    @ApiModelProperty(value = "配货规则（0-按要货顺序；1-按库存平均；2-按要货数量占比），前端不需传",required = true)
    private Integer distrRule = new Integer(0);

    @ApiModelProperty(value = "缺配处理（0-生成缺配单；1-不处理），前端不需传",required = true)
    private Integer lackHandle = new Integer(1);

    @ApiModelProperty(value = "redisKey 如果不是通过暂存调用的,前端不用传")
    private String redisKeyValue;


    @ApiModelProperty(value = "备注")
    private String remark;

    public static RequestDistrSendVO getRequestDistrSendVO(DistrReturnInVO distrReturnInVO,DistrInReturnOut distrInReturnOu){

        DistrReturnIn distrReturnIn = distrReturnInVO.getDistrReturnIn();
        List<DistrReturnInDetail> distrReturnInDetails = distrReturnInVO.getDistrReturnInDetails();

        RequestDistrSendVO requestDistrSendVO = new RequestDistrSendVO();

        requestDistrSendVO.setDistrManId(distrReturnIn.getStorageManId());

        requestDistrSendVO.setDistrDate(new Date());

        requestDistrSendVO.setRequestUnitId(distrInReturnOu.getOutboundUnitId());

        requestDistrSendVO.setWholeDiscount(BigDecimal.TEN.multiply(BigDecimal.TEN));

        requestDistrSendVO.setWholeDiscountAmount(BigDecimal.ZERO);

        List<RequestDistrGoods> requestDistrGoods = RequestDistrGoods.getRequestDistrGoods(distrReturnInDetails);
        requestDistrSendVO.setDistrGoogsList(requestDistrGoods);

        return requestDistrSendVO;
    }

    public RequestDistrSendVO() {
    }

    public RequestDistrSendVO(Long distrManId, Date distrDate, Long requestUnitId, BigDecimal wholeDiscount,
                              BigDecimal wholeDiscountAmount) {
        this.distrManId = distrManId;
        this.distrDate = distrDate;
        this.requestUnitId = requestUnitId;
        this.wholeDiscount = wholeDiscount;
        this.wholeDiscountAmount = wholeDiscountAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDistrManId() {
        return distrManId;
    }

    public void setDistrManId(Long distrManId) {
        this.distrManId = distrManId;
    }

    public Date getDistrDate() {
        return distrDate;
    }

    public void setDistrDate(Date distrDate) {
        this.distrDate = distrDate;
    }

    public Long getRequestUnitId() {
        return requestUnitId;
    }

    public void setRequestUnitId(Long requestUnitId) {
        this.requestUnitId = requestUnitId;
    }

    public BigDecimal getWholeDiscount() {
        if (wholeDiscount == null) {
            return new BigDecimal(100);
        }
        return wholeDiscount;
    }

    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    public BigDecimal getWholeDiscountAmount() {
        if (wholeDiscountAmount == null) {
            return BigDecimal.ZERO;
        }
        return wholeDiscountAmount;
    }

    public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
        this.wholeDiscountAmount = wholeDiscountAmount;
    }

    public List<RequestDistrGoods> getDistrGoogsList() {
        return distrGoogsList;
    }

    public void setDistrGoogsList(List<RequestDistrGoods> distrGoogsList) {
        this.distrGoogsList = distrGoogsList;
    }

    public Integer getDistrRule() {
        return distrRule;
    }

    public void setDistrRule(Integer distrRule) {
        this.distrRule = distrRule;
    }

    public Integer getLackHandle() {
        return lackHandle;
    }

    public void setLackHandle(Integer lackHandle) {
        this.lackHandle = lackHandle;
    }

    public String getRedisKeyValue() {
        return redisKeyValue;
    }

    public void setRedisKeyValue(String redisKeyValue) {
        this.redisKeyValue = redisKeyValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
