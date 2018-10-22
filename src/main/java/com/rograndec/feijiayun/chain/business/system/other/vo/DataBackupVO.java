package com.rograndec.feijiayun.chain.business.system.other.vo;

import java.util.Date;

/**
 * Created by ST on 2017/9/2.
 */
public class DataBackupVO {
    /**
     * 备份时间
     */
    private Date backupTime;

    /**
     * 备份类型（0-完全备份；1-增量备份）
     */
    private Integer backupType;

    /**
     * 备份名称
     */
    private String backupName;

    /**
     * 备份路径
     */
    private String backupPath;

    public Date getBackupTime() {
        return backupTime;
    }

    public void setBackupTime(Date backupTime) {
        this.backupTime = backupTime;
    }

    public Integer getBackupType() {
        return backupType;
    }

    public void setBackupType(Integer backupType) {
        this.backupType = backupType;
    }

    public String getBackupName() {
        return backupName;
    }

    public void setBackupName(String backupName) {
        this.backupName = backupName;
    }

    public String getBackupPath() {
        return backupPath;
    }

    public void setBackupPath(String backupPath) {
        this.backupPath = backupPath;
    }
}