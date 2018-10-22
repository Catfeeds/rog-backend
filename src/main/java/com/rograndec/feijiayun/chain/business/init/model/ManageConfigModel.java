package com.rograndec.feijiayun.chain.business.init.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: ManageConfigModel
 * @Description: 管理设置初始化数据模型
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年9月8日 下午7:54:23  
 *
 */
public class ManageConfigModel {

	/**
	 * 供货单位编码规则（0-供货单位分组+4位流水码；1-4位流水码；2-自定义编码）
	 */
	private Integer supplierCodeRule;

	/**
	 * 商品信息编码规则（0-商品分组+4位流水码；1-自定义编码）
	 */
	private Integer goodsCodeRule;

	/**
	 * 员工信息编码规则（0-部门+4位流水码；1-4位流水码；2-自定义编码）
	 */
	private Integer userCodeRule;

	/**
	 * 业务单据价格（0-含税单价；1-不含税单价）
	 */
	private Integer priceControl;

	/**
	 * 业务单据输入顺序（0-顺序；1-倒叙，默认值1）
	 */
	private Integer enterOrder;

	/**
	 * 零成本入库（0禁止；1-允许）
	 */
	private Integer zeroCostIn;

	/**
	 * 库存占用量控制（0关闭；1-开启）
	 */
	private Integer inventoryOccupancy;

	/**
	 * 基础数据质量控制（0关闭；1-开启）
	 */
	private Integer qualityControl;

	/**
	 * 业务流程质量控制（0关闭；1-开启）
	 */
	private Integer businessControl;

	/**
	 * Pos质量控制（0关闭；1-开启）
	 */
	private Integer posControl;

	/**
	 * 审批流程控制（0关闭；1-开启）
	 */
	private Integer approvalControl;

	public ManageConfigModel(Integer supplierCodeRule, Integer goodsCodeRule, Integer userCodeRule, Integer priceControl,
							 Integer enterOrder, Integer zeroCostIn, Integer inventoryOccupancy, Integer qualityControl,
							 Integer businessControl, Integer posControl, Integer approvalControl) {
		this.supplierCodeRule = supplierCodeRule;
		this.goodsCodeRule = goodsCodeRule;
		this.userCodeRule = userCodeRule;
		this.priceControl = priceControl;
		this.enterOrder = enterOrder;
		this.zeroCostIn = zeroCostIn;
		this.inventoryOccupancy = inventoryOccupancy;
		this.qualityControl = qualityControl;
		this.businessControl = businessControl;
		this.posControl = posControl;
		this.approvalControl = approvalControl;
	}

	public ManageConfigModel(){}


	public static List<ManageConfigModel> build(){
		List<ManageConfigModel> manageConfigModelList = new ArrayList<ManageConfigModel>();
		return manageConfigModelList;
	}


}
