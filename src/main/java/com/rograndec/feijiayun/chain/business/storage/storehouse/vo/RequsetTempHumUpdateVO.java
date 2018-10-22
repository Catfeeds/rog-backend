package com.rograndec.feijiayun.chain.business.storage.storehouse.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_temperature_humidity
 * 
 * 返回详情页使用
 * @author DD
 * 
 * 2017-09-27
 */
public class RequsetTempHumUpdateVO implements Serializable {
	
	 /**
     * 主键ID
     */
    @ApiModelProperty(value = "温湿度记录单主键ID")
    private Long recordId;
    
    /**
     * 记录日期
     */
    @ApiModelProperty(value = "记录日期，若是更换则为新的时间,若不变则为null")
    private Date recordDate;
    
    /**
     * 记录人员ID
     */
    @ApiModelProperty(value = "记录人员ID，若是更换则为新的记录员id,若不变则为null")
    private Long recordManId;
    
    /**
     * 附件ID(若是修改附件则为新的附件的id,若是删除附件则为null)
     */
    @ApiModelProperty(value = "附件ID(若是修改附件则为新的附件的id,若是删除附件则为null)")
    private Long fileId;
    
    /**
     * 需要删除的温湿度详情的id集合
     */
    @ApiModelProperty(value = "需要删除的温湿度详情的id集合")
    private List<Long> deleteIds;

    /**
     * 每行的记录信息
     */
    @ApiModelProperty(value = "具体的每条信息")
    private List<RequestTempHumDetUpdateVO> detailList;

    public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public Long getRecordManId() {
		return recordManId;
	}

	public void setRecordManId(Long recordManId) {
		this.recordManId = recordManId;
	}

	/**
     * saas_temperature_humidity
     */
    private static final long serialVersionUID = 1L;

    public List<Long> getDeleteIds() {
		return deleteIds;
	}

	public void setDeleteIds(List<Long> deleteIds) {
		this.deleteIds = deleteIds;
	}

	/**
     * 附件ID
     * @return file_id 附件ID
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * 附件ID
     * @param fileId 附件ID
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }
	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public List<RequestTempHumDetUpdateVO> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<RequestTempHumDetUpdateVO> detailList) {
		this.detailList = detailList;
	}

}