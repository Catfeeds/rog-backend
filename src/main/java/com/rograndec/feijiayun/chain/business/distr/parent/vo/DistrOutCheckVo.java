package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOut;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
*
* @ClassName: DistrOutVO
* @Description:  总部-配货出库-配货出库单
* @author zhengbin.jin
* @version 1.0
* @date 2017-10-07 15:57:42
*/
@ApiModel(value = "DistrOutCheckVo", description = "总部-配货出库-配货出库单-复核")
public class DistrOutCheckVo implements Serializable {

   private static final long serialVersionUID = 1L;

   @NotNull(message = "配货出库单id不能为空")
   @ApiModelProperty(required = true, value = "配货出库单ID")
   private Long id;

   @NotNull(message = "复核状态不能为空")
   @ApiModelProperty(required = true, value = "复核状态0-驳回 1-同意")
   private Integer status;

   @ApiModelProperty(required = false, value = "复核意见")
   private String remark;

   public static DistrOutCheckVo getDistrOutCheckVo(DistrOut distrOut){

	   DistrOutCheckVo distrOutCheckVo = new DistrOutCheckVo();
	   distrOutCheckVo.setId(distrOut.getId());

	   distrOutCheckVo.setStatus(1);
	   distrOutCheckVo.setRemark("同意");

	   return distrOutCheckVo;
   }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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