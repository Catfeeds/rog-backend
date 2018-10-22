package com.rograndec.feijiayun.chain.business.basic.user.vo;

import com.rograndec.feijiayun.chain.business.basic.user.entity.UserModifyRecord;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserModifyRecordWithBLOBs;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel
public class UserModifyRecordWithReturnVO extends UserModifyRecord implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 员工ID
     */
    @ApiModelProperty(value = "员工ID")
    private Long userId;

    /**
     * 表名
     */
    @ApiModelProperty(value = "表名")
    private String tableName;


    /**
     * 修改字段英文名称
     */
    @ApiModelProperty(value = "修改字段英文名称")
    private String columnEnName;

    /**
     * 修改字段中文名称（修改项目）
     */
    @ApiModelProperty(value = "修改字段英文名称")
    private String columnChName;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "修改时间描述")
    private String updateTimeDesc;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "修改人员id")
    private Long createrId;

    /**
     * 创建人编码
     */
    @ApiModelProperty(value = "修改人员编码")
    private String createrCode;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "修改人员名称")
    private String createrName;

    /**
     * 原内容
     */
    @ApiModelProperty(value = "原内容")
    private String oldContent;

    /**
     * 新内容
     */
    @ApiModelProperty(value = "新内容")
    private String newContent;

    /**
     * 备注
     */
    @ApiModelProperty(value = "修改原因")
    private String remark;

    public static List<UserModifyRecordWithReturnVO> getRecordVO4Record(List<UserModifyRecordWithBLOBs> userModifyRecords){

        List<UserModifyRecordWithReturnVO> list = new ArrayList<>();
        for(UserModifyRecordWithBLOBs userModifyRecordWithBLOBs : userModifyRecords){

            UserModifyRecordWithReturnVO userModifyRecordWithReturnVO = getRecordVO4Record(userModifyRecordWithBLOBs);
            list.add(userModifyRecordWithReturnVO);
        }

        return list;
    }

    public static UserModifyRecordWithReturnVO getRecordVO4Record(UserModifyRecordWithBLOBs userModifyRecord){

        UserModifyRecordWithReturnVO userModifyRecordWithReturnVO = new UserModifyRecordWithReturnVO();


        userModifyRecordWithReturnVO.setId(userModifyRecord.getId());
        userModifyRecordWithReturnVO.setUserId(userModifyRecord.getUserId());
        userModifyRecordWithReturnVO.setTableName(userModifyRecord.getTableName());
        userModifyRecordWithReturnVO.setColumnEnName(userModifyRecord.getColumnEnName());
        userModifyRecordWithReturnVO.setColumnChName(userModifyRecord.getColumnChName());


        userModifyRecordWithReturnVO.setUpdateTime(userModifyRecord.getUpdateTime());

        String timeDesc = DateUtils.DateToString(userModifyRecord.getUpdateTime(),DateUtils.FMT_DATE_TIME);
        userModifyRecordWithReturnVO.setUpdateTimeDesc(timeDesc);

        userModifyRecordWithReturnVO.setCreaterId(userModifyRecord.getUserId());
        userModifyRecordWithReturnVO.setCreaterCode(userModifyRecord.getCreaterCode());
        userModifyRecordWithReturnVO.setCreaterName(userModifyRecord.getCreaterName());

        userModifyRecordWithReturnVO.setOldContent(userModifyRecord.getOldContent());
        userModifyRecordWithReturnVO.setNewContent(userModifyRecord.getNewContent());
        userModifyRecordWithReturnVO.setRemark(userModifyRecord.getRemark());

        return userModifyRecordWithReturnVO;
    }

    /**
     * saas_user_modify_record
     */
    private static final long serialVersionUID = 1L;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public String getColumnEnName() {
        return columnEnName;
    }

    @Override
    public void setColumnEnName(String columnEnName) {
        this.columnEnName = columnEnName;
    }

    @Override
    public String getColumnChName() {
        return columnChName;
    }

    @Override
    public void setColumnChName(String columnChName) {
        this.columnChName = columnChName;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public Long getCreaterId() {
        return createrId;
    }

    @Override
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    @Override
    public String getCreaterCode() {
        return createrCode;
    }

    @Override
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode;
    }

    @Override
    public String getCreaterName() {
        return createrName;
    }

    @Override
    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public String getOldContent() {
        return oldContent;
    }

    public void setOldContent(String oldContent) {
        this.oldContent = oldContent;
    }

    public String getNewContent() {
        return newContent;
    }

    public void setNewContent(String newContent) {
        this.newContent = newContent;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getUpdateTimeDesc() {
        return updateTimeDesc;
    }

    public void setUpdateTimeDesc(String updateTimeDesc) {
        this.updateTimeDesc = updateTimeDesc;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", oldContent=").append(oldContent);
        sb.append(", newContent=").append(newContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}