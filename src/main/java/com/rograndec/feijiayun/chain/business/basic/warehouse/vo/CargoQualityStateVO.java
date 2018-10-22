package com.rograndec.feijiayun.chain.business.basic.warehouse.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 功能描述：
 * Created by ST on 2017/9/16 17:10
 */

public class CargoQualityStateVO {
    /**
     * 作业类型（0-存储作业区域；1-辅助作业区域；2-存储设备）
     */
    @ApiModelProperty(value = "作业类型（0-存储作业区域；1-辅助作业区域；2-存储设备）")
    private Integer jobType;

    /**
     * 作业区域：作业区域：
     当job_type=0（存储作业区）时，job_area=0，含义为“合格品区”；
     job_area=1，含义为“待处理区”；
     job_area=2，含义为“不合格品区”；

     当job_type=1（辅助作业区）时，job_area=0，含义为“收货区”；
     job_area=1，含义为“验收区”；
     job_area=2，含义为“退货区”；
     job_area=3，含义为“复核区”；
     job_area=4，含义为“零货拣选区区”；
     job_area=5，含义为“拼箱发货区”；
     job_area=6，含义为“包装物料发货区”；

     当job_type=2（存储设备）时，job_area=0，含义为“保温设备”；
     job_area=1，含义为“冷藏设备”；
     job_area=2，含义为“冷冻设备”；

     */
    @ApiModelProperty(value = "作业区域：作业区域：\n" +
            " 当job_type=0（存储作业区）时，job_area=0，含义为“合格品区”；\n" +
            " job_area=1，含义为“待处理区”；\n" +
            " job_area=2，含义为“不合格品区”；\n" +
            " \n" +
            " 当job_type=1（辅助作业区）时，job_area=0，含义为“收货区”；\n" +
            " job_area=1，含义为“验收区”；\n" +
            " job_area=2，含义为“退货区”；\n" +
            " job_area=3，含义为“复核区”；\n" +
            " job_area=4，含义为“零货拣选区区”；\n" +
            " job_area=5，含义为“拼箱发货区”；\n" +
            " job_area=6，含义为“包装物料发货区”；" +
            "当job_type=2（存储设备）时，job_area=0，含义为“保温设备”；\n" +
            "     job_area=1，含义为“冷藏设备”；\n" +
            "     job_area=2，含义为“冷冻设备”；")
    private Integer jobArea;



    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

    public Integer getJobArea() {
        return jobArea;
    }

    public void setJobArea(Integer jobArea) {
        this.jobArea = jobArea;
    }

}