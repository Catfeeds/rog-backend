package com.rograndec.feijiayun.chain.inf.pos.syncdata.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: RequestDataSyncVO   
 * @Description: POS同步请求参数
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月11日 下午1:26:46
 */
@ApiModel(value = "RequestDataSyncVO", description = "POS同步请求参数")
public class RequestDataSyncVO implements Serializable{
	
	
	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2017年10月11日 下午1:46:58 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull(message="同步类型不能为空")
	@ApiModelProperty(value = "0全部同步 1部分同步")
	private Integer kind; // 0全部同步 1部分同步
	
	@ApiModelProperty(value = "同步的关键字，表名，主键，其他值")
	private String key;//同步的关键字，表名，主键，其他值
	
	@ApiModelProperty(value = "最后同步时间,yyyy-MM-dd")
	private String lastSyncTime;
	
	@ApiModelProperty(value = "起始页,默认是第一页")
	private Integer startRow;
	
	@ApiModelProperty(value = "结束页,每页显示多少记录")
	private Integer endRow;

	public Integer getKind() {
		return kind;
	}

	public void setKind(Integer kind) {
		this.kind = kind;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLastSyncTime() {
		return lastSyncTime;
	}

	public void setLastSyncTime(String lastSyncTime) {
		this.lastSyncTime = lastSyncTime;
	}

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	public Integer getEndRow() {
		return endRow;
	}

	public void setEndRow(Integer endRow) {
		this.endRow = endRow;
	}
	
	

}
