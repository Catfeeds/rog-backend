package com.rograndec.feijiayun.chain.business.init.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: GoodsQualificationModel  
 * @Description: 商品资质初始化数据模型 
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月4日 下午7:20:49  
 *
 */
public class GoodsQualificationModel {

	private Long checkTypeId;// 验收类型ID
	private Integer typeMust;
	private String code;
	private String name;
	private Integer controlType;
	private Integer codeMust;
	private Integer validUntilMust;
	private Integer fileMust;
	
	public GoodsQualificationModel(){}

	public GoodsQualificationModel(Long checkTypeId, Integer typeMust, String code, String name,
			Integer controlType, Integer codeMust, Integer validUntilMust, Integer fileMust) {
		super();
		this.checkTypeId = checkTypeId;
		this.typeMust = typeMust;
		this.code = code;
		this.name = name;
		this.controlType = controlType;
		this.codeMust = codeMust;
		this.validUntilMust = validUntilMust;
		this.fileMust = fileMust;
	}

	public static List<GoodsQualificationModel> build(){
		List<GoodsQualificationModel> gqList = new ArrayList<GoodsQualificationModel>();
		gqList.add(new GoodsQualificationModel(0L, 1, "01", "生产企业营业执照", 0, 1, 1, 0));
		gqList.add(new GoodsQualificationModel(0L, 1, "02", "药品生产许可证", 0, 1, 1, 0));
		gqList.add(new GoodsQualificationModel(0L, 1, "03", "药品生产质量管理规范认证证书", 0, 1, 1, 0));
		gqList.add(new GoodsQualificationModel(0L, 0, "04", "新药证书", 0, 1, 0, 0));
		gqList.add(new GoodsQualificationModel(0L, 0, "05", "药品(再)注册批件", 0, 1, 1, 0));
		gqList.add(new GoodsQualificationModel(0L, 0, "06", "药品注册证", 0, 1, 0, 0));
		return gqList;
	}

	public Long getCheckTypeId() {
		return checkTypeId;
	}

	public void setCheckTypeId(Long checkTypeId) {
		this.checkTypeId = checkTypeId;
	}

	public Integer getTypeMust() {
		return typeMust;
	}

	public void setTypeMust(Integer typeMust) {
		this.typeMust = typeMust;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getControlType() {
		return controlType;
	}

	public void setControlType(Integer controlType) {
		this.controlType = controlType;
	}

	public Integer getCodeMust() {
		return codeMust;
	}

	public void setCodeMust(Integer codeMust) {
		this.codeMust = codeMust;
	}

	public Integer getValidUntilMust() {
		return validUntilMust;
	}

	public void setValidUntilMust(Integer validUntilMust) {
		this.validUntilMust = validUntilMust;
	}

	public Integer getFileMust() {
		return fileMust;
	}

	public void setFileMust(Integer fileMust) {
		this.fileMust = fileMust;
	}

}
