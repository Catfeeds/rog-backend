package com.rograndec.feijiayun.chain.business.system.opening.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.apache.shiro.util.CollectionUtils;

import com.rograndec.feijiayun.chain.common.constant.status.GoodTaxTypeStatus;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * @author dongdongzhang
 * 
 * 2018-01-15
 */
public class OpeningTaxDetailVO implements Serializable {
	
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 总单ID
     */
    @ApiModelProperty(value = "总单ID")
    private Long taxId;
    
    /**
     * 税类型（0-进项税；1-销项税）
     */
    @ApiModelProperty(value = "税类型ID（0-进项税；1-销项税）")
    @NotNull(message = "税类型不能为空")
    private Integer taxType = GoodTaxTypeStatus.TAX_IN.getCode();
    
    /**
     * 税类型（0-进项税；1-销项税）
     */
    @ApiModelProperty(value = "税类型（0-进项税；1-销项税）")
    private String taxTypeName;

    /**
     * 税率ID
     */
    @ApiModelProperty(value = "税率ID")
    @NotNull(message = "税率id不能为空")
    private Long taxRateId;

    /**
     * 税率
     */
    @ApiModelProperty(value = "税率")
    private String taxRate;
    
    private BigDecimal taxRateSelf;
    
    /**
     * 平,借,贷 方向
     */
    private String direction;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    @NotNull(message = "金额不能为空")
    private String amount = "0.00";
    
    private BigDecimal amountSelf = BigDecimal.ZERO;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


    /**
     * saas_opening_tax_detail
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * 总单ID
     * @return tax_id 总单ID
     */
    public Long getTaxId() {
        return taxId;
    }

    /**
     * 总单ID
     * @param taxId 总单ID
     */
    public void setTaxId(Long taxId) {
        this.taxId = taxId;
    }

    /**
     * 税类型（0-进项税；1-销项税）
     * @return tax_type 税类型（0-进项税；1-销项税）
     */
    public Integer getTaxType() {
        return taxType;
    }

    /**
     * 税类型（0-进项税；1-销项税）
     * @param taxType 税类型（0-进项税；1-销项税）
     */
    public void setTaxType(Integer taxType) {
        this.taxType = taxType;
    }

    /**
     * 税率ID
     * @return tax_rate_id 税率ID
     */
    public Long getTaxRateId() {
        return taxRateId;
    }

    /**
     * 税率ID
     * @param taxRateId 税率ID
     */
    public void setTaxRateId(Long taxRateId) {
        this.taxRateId = taxRateId;
    }

    /**
     * 税率
     * @return tax_rate 税率
     */
    public String getTaxRate() {
        return taxRate;
    }

    /**
     * 税率
     * @param taxRate 税率
     */
    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * 金额
     * @return amount 金额
     */
    public String getAmount() {
        return amount;
    }

    /**
     * 金额
     * @param amount 金额
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getTaxTypeName() {
		if(taxType == -1) {
			return taxTypeName;
		}
		return GoodTaxTypeStatus.getName(taxType);
	}

	public void setTaxTypeName(String taxTypeName) {
		this.taxTypeName = taxTypeName;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public BigDecimal getTaxRateSelf() {
		return taxRateSelf;
	}

	public void setTaxRateSelf(BigDecimal taxRateSelf) {
		this.taxRateSelf = taxRateSelf;
	}

	public BigDecimal getAmountSelf() {
		return amountSelf;
	}

	public void setAmountSelf(BigDecimal amountSelf) {
		this.amountSelf = amountSelf;
	}

	public static Map<String, OpeningTaxDetailVO> getMap(List<OpeningTaxDetailVO> list){
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		list = getList(list);
		Map<String, OpeningTaxDetailVO> map = new HashMap<>(list.size());
		for(OpeningTaxDetailVO vo:list) {
			//税类型前缀加税率确定唯一性
			map.put(GoodTaxTypeStatus.getFlag(vo.getTaxType())+vo.getTaxRate()+"%", vo);
		}
		return map;
	}
	
	public static List<OpeningTaxDetailVO> getList(List<OpeningTaxDetailVO> list){
		if(CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<OpeningTaxDetailVO> resList = new ArrayList<>();
		
		resList.addAll(list);
		for(OpeningTaxDetailVO vo:list) {
			vo.setTaxRate(getTaxRateWithOutZero(vo.getTaxRateSelf()));
			OpeningTaxDetailVO openingTaxDetailVO = vo.clone();
			resList.add(openingTaxDetailVO);
			
		}
		return resList;
	}
	
	public OpeningTaxDetailVO clone() {
		OpeningTaxDetailVO openingTaxDetailVO = new OpeningTaxDetailVO();
		openingTaxDetailVO.setTaxRateId(this.taxRateId);
		openingTaxDetailVO.setTaxRate(this.taxRate);
		openingTaxDetailVO.setTaxRateSelf(this.taxRateSelf);
		openingTaxDetailVO.setTaxType(GoodTaxTypeStatus.TAX_OUT.getCode());
		return openingTaxDetailVO;
	}
	
	public static String getTaxRateWithOutZero(BigDecimal taxRate) {
		if(taxRate == null) taxRate = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
		/*String b = taxRate.doubleValue()+"";
	    if(b.contains(".") && b.endsWith("0")) {
	    	int n = b.indexOf(".");
	    	b = b.substring(0, n);
	    }*/
		String str = taxRate.doubleValue()+"";
    	if (str.contains(".")) {
    	    String[] split = str.split("\\.");
    	    str = split[0];
    	    String decimal = split[1];
    	    while (decimal.endsWith("0")) {
    	        decimal = decimal.substring(0, decimal.length() - 1);
    	    }
    	    decimal = decimal.length() == 0 ? "" : "." + decimal;
    	    str = str + decimal;
    	}
		return str;
	}
	

}