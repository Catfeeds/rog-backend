package com.rograndec.feijiayun.chain.business.goods.httpInf.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: GoodsPharmacyVO   
 * @Description: 标准库用药检查返回对象说明
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月17日 下午8:45:46
 */
@ApiModel(value = "GoodsExaminationVO", description = "标准库用药检查返回对象说明")
public class GoodsExaminationVO {
	@JsonProperty(value="goodsId")
	@ApiModelProperty(value = "药品ID")
	private Long goodsId;
	@JsonProperty(value="goodsName")
	@ApiModelProperty(value = "药品名称")
	private String name;
	
	@JsonProperty(value="alarmName")
	@ApiModelProperty(value = "报警名称")
	private String warnName;
	
	@JsonProperty(value="simpleDesc")
	@ApiModelProperty(value = "简单描述")
	private String simpleDescription;
	
	@JsonProperty(value="complexDesc")
	@ApiModelProperty(value = "信息描述")
	private String detailedDescription;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWarnName() {
		return warnName;
	}

	public void setWarnName(String warnName) {
		this.warnName = warnName;
	}

	public String getSimpleDescription() {
		return simpleDescription;
	}

	public void setSimpleDescription(String simpleDescription) {
		this.simpleDescription = simpleDescription;
	}

	public String getDetailedDescription() {
		return detailedDescription;
	}

	public void setDetailedDescription(String detailedDescription) {
		this.detailedDescription = detailedDescription;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	
	

}
