package com.rograndec.feijiayun.chain.business.basic.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by dongdong.zhang on 2017/12/15.
 */
@ApiModel(value = "CargoAreaVO", description = "员工关联柜组所用")
public class CargoAreaVO implements Serializable {

    @ApiModelProperty(value = "柜组id")
    private Long id;
    
    @ApiModelProperty(value = "柜组名称")
    private String name;
    
    /*@ApiModelProperty(value = "柜组是否可删除标识   true-可删，false-不可删")
    private boolean flag;*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}*/
    
}