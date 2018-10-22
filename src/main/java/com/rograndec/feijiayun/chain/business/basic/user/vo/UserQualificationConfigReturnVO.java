package com.rograndec.feijiayun.chain.business.basic.user.vo;

import com.rograndec.feijiayun.chain.business.basic.user.entity.UserQualificationConfig;
import com.rograndec.feijiayun.chain.business.system.set.entity.UserQualification;
import com.rograndec.feijiayun.chain.common.constant.SysType;
import com.rograndec.feijiayun.chain.common.constant.UserQualificationControlType;
import com.rograndec.feijiayun.chain.common.constant.UserQualificationTypeMust;
import com.rograndec.feijiayun.chain.common.file.entity.File;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@ApiModel
public class UserQualificationConfigReturnVO implements Serializable {

    /**
     * user 资质id
     */
    @ApiModelProperty(value = "user 资质信息id,新增时需要传入,修改时不需要", required = false)
    private Long id;


    /**
     * 员工资质ID
     */
    @ApiModelProperty(value = " 员工资质ID", required = true)
    private Long qualificationId;

    /**
     * 等级
     */
    @ApiModelProperty(value = " 等级", required = false)
    private String grade;

    /**
     * 资格证书号
     */
    @ApiModelProperty(value = " 资格证书号", required = false)
    private String code;

    /**
     * 注册证书号
     */
    @ApiModelProperty(value = " 注册证书号", required = false)
    private String registerCode;

    /**
     * 适用地区
     */
    @ApiModelProperty(value = " 适用地区", required = false)
    private String region;

    /**
     * 适用类别
     */
    @ApiModelProperty(value = " 适用类别", required = false)
    private String category;

    /**
     * 适用范围
     */
    @ApiModelProperty(value = " 适用范围", required = false)
    private String range;

    /**
     * 附件ID
     */
    @ApiModelProperty(value = " 附件ID", required = false)
    private Long fileId;

    @ApiModelProperty(value = " 附件name", required = false)
    private String fileName;
    /**
     * 资质类型是否必选（0-可选；1-必选）
     */
    @ApiModelProperty(value = " 资质类型是否必选（0-可选；1-必选）", required = false)
    private Integer typeMust;

    /**
     * 资质类型描述
     */
    @ApiModelProperty(value = " 资质类型描述", required = false)
    private String typeMustDesc;
    /**
     * 资质描述
     */
    @ApiModelProperty(value = " 资质描述", required = false)
    private String qualificationName;

    @ApiModelProperty(value = " 资质描述code", required = false)
    private String qualificationCode;

    /**
     * 控制类型（0-质量控制；1-仅提示）
     */
    @ApiModelProperty(value = " 控制类型（0-质量控制；1-仅提示）", required = false)
    private Integer controlType;

    /**
     * 控制类型描述
     */
    @ApiModelProperty(value = " 控制类型描述", required = false)
    private String controlTypeDesc;

    /**
     * 创建类型 0：用户自定义；1-系统默认
     */
    @ApiModelProperty(value = "创建类型 0：用户自定义；1-系统默认", required = false)
    private Integer sysType;

    /**
     * 创建类型 描述
     */
    @ApiModelProperty(value = "创建类型 描述", required = false)
    private String sysTypeDesc;


    /**
     * 等级是否必填（0-非必填；1-必填）
     */
    @ApiModelProperty(value="等级是否必填（0-非必填；1-必填）",required=true)
    private Integer gradeMust;

    /**
     * 资格证书号是否必填（0-非必填；1-必填）
     */
    @ApiModelProperty(value="资格证书号是否必填（0-非必填；1-必填）",required=true)
    private Integer codeMust;

    /**
     * 注册证书号是否必填（0-非必填；1-必填）
     */
    @ApiModelProperty(value="注册证书号是否必填（0-非必填；1-必填）",required=true)
    private Integer registerCodeMust;

    /**
     * 适用地区是否必填（0-非必填；1-必填）
     */
    @ApiModelProperty(value="适用地区是否必填（0-非必填；1-必填）",required=true)
    private Integer regionMust;

    /**
     * 适用类别是否必填（0-非必填；1-必填）
     */
    @ApiModelProperty(value="适用类别是否必填（0-非必填；1-必填）",required=true)
    private Integer categoryMust;

    /**
     * 适用范围是否必填（0-只读；1-必填）
     */
    @ApiModelProperty(value="适用范围是否必填（0-只读；1-必填）",required=true)
    private Integer rangeMust;

    /**
     * 附件是否必填（0-非必填；1-必填）
     */
    @ApiModelProperty(value="附件是否必填（0-非必填；1-必填）",required=true)
    private Integer fileMust;


    /**
     * 根据用户资质配置 填充添加员工信息时资质配置信息
     * @param userQualifications
     * @return
     */
    public static List<UserQualificationConfigReturnVO> getAddUserInitVOs(List<UserQualification> userQualifications){

        List<UserQualificationConfigReturnVO> userQualificationConfigReturnVOS = new ArrayList<>();

        for(UserQualification userQualification : userQualifications){
            UserQualificationConfigReturnVO vo = UserQualificationConfigReturnVO.getAddUserInitVO(userQualification);
            userQualificationConfigReturnVOS.add(vo);
        }

        return userQualificationConfigReturnVOS;
    }
    /**
     * 根据用户资质配置 填充添加员工信息时资质配置信息
     * @param userQualification
     * @return
     */
    public static UserQualificationConfigReturnVO getAddUserInitVO(UserQualification userQualification){
        UserQualificationConfigReturnVO userQualificationConfigReturnVO = new UserQualificationConfigReturnVO();
        userQualificationConfigReturnVO.setId(userQualification.getId());
        userQualificationConfigReturnVO.setCode(userQualification.getCode());
        userQualificationConfigReturnVO.setTypeMust(userQualification.getTypeMust());
        userQualificationConfigReturnVO.setTypeMustDesc(
                UserQualificationTypeMust.getUserTypeMustEnum(userQualification.getTypeMust()).getValue()
        );

        userQualificationConfigReturnVO.setControlType(userQualification.getControlType());
        userQualificationConfigReturnVO.setControlTypeDesc(
                UserQualificationControlType.getUserQualificationControlTypeEnum(
                        userQualification.getControlType()
                ).getValue()
        );
        userQualificationConfigReturnVO.setQualificationCode(userQualification.getCode());
        userQualificationConfigReturnVO.setQualificationName(userQualification.getName());
        userQualificationConfigReturnVO.setSysType(userQualification.getSysType());
        userQualificationConfigReturnVO.setSysTypeDesc(
                SysType.getSysTypeEnum(userQualification.getSysType()).getValue()
        );

        userQualificationConfigReturnVO.setQualificationId(userQualification.getId());
        userQualificationConfigReturnVO.setGradeMust(userQualification.getGradeMust());
        userQualificationConfigReturnVO.setCodeMust(userQualification.getCodeMust());
        userQualificationConfigReturnVO.setRegisterCodeMust(userQualification.getRegisterCodeMust());
        userQualificationConfigReturnVO.setRegionMust(userQualification.getRegionMust());
        userQualificationConfigReturnVO.setCategoryMust(userQualification.getCategoryMust());
        userQualificationConfigReturnVO.setRangeMust(userQualification.getRangeMust());
        userQualificationConfigReturnVO.setFileMust(userQualification.getFileMust());

        return userQualificationConfigReturnVO;
    }

    /**
     * 根据用户资质配置 填充添加员工信息时资质配置信息
     * @return
     */
    public static UserQualificationConfigReturnVO getUserQualificationConfigReturnVO4Config(UserQualificationConfig config
    ,  List<File> files,List<UserQualification> userQualifications){
        UserQualificationConfigReturnVO userQualificationConfigReturnVO = new UserQualificationConfigReturnVO();

        userQualificationConfigReturnVO.setId(config.getId());
        userQualificationConfigReturnVO.setQualificationId(config.getQualificationId());
        userQualificationConfigReturnVO.setGrade(config.getGrade());
        userQualificationConfigReturnVO.setCode(config.getCode());
        userQualificationConfigReturnVO.setRegisterCode(config.getRegisterCode());
        userQualificationConfigReturnVO.setRegion(config.getRegion());
        userQualificationConfigReturnVO.setCategory(config.getCategory());
        userQualificationConfigReturnVO.setRange(config.getRange());
        userQualificationConfigReturnVO.setFileId(config.getFileId());
        for(File file : files){
            if(file.getId().equals(config.getFileId())){
                userQualificationConfigReturnVO.setFileName(file.getFileName());
            }
        }

        for(UserQualification userQualification : userQualifications){
            if(userQualification.getId().equals(config.getQualificationId())){
                userQualificationConfigReturnVO.setTypeMust(userQualification.getTypeMust());
                userQualificationConfigReturnVO.setTypeMustDesc(
                        UserQualificationTypeMust.getUserTypeMustEnum(userQualification.getTypeMust()).getValue()
                );
                userQualificationConfigReturnVO.setQualificationCode(userQualification.getCode());
                userQualificationConfigReturnVO.setQualificationName(userQualification.getName());


                userQualificationConfigReturnVO.setControlType(userQualification.getControlType());
                userQualificationConfigReturnVO.setControlTypeDesc(
                        UserQualificationControlType.getUserQualificationControlTypeEnum(
                                userQualification.getControlType()
                        ).getValue()
                );
                ;
                userQualificationConfigReturnVO.setSysType(userQualification.getSysType());
                userQualificationConfigReturnVO.setSysTypeDesc(
                        SysType.getSysTypeEnum(userQualification.getSysType()).getValue()
                );
                userQualificationConfigReturnVO.setQualificationId(userQualification.getId());
                userQualificationConfigReturnVO.setGradeMust(userQualification.getGradeMust());
                userQualificationConfigReturnVO.setCodeMust(userQualification.getCodeMust());
                userQualificationConfigReturnVO.setRegisterCodeMust(userQualification.getRegisterCodeMust());
                userQualificationConfigReturnVO.setRegionMust(userQualification.getRegionMust());
                userQualificationConfigReturnVO.setCategoryMust(userQualification.getCategoryMust());
                userQualificationConfigReturnVO.setRangeMust(userQualification.getRangeMust());
                userQualificationConfigReturnVO.setFileMust(userQualification.getFileMust());
            }
        }


        return userQualificationConfigReturnVO;
    }

    public Long getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(Long qualificationId) {
        this.qualificationId = qualificationId;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTypeMust() {
        return typeMust;
    }

    public void setTypeMust(Integer typeMust) {
        this.typeMust = typeMust;
    }

    public String getTypeMustDesc() {
        return typeMustDesc;
    }

    public void setTypeMustDesc(String typeMustDesc) {
        this.typeMustDesc = typeMustDesc;
    }


    public Integer getControlType() {
        return controlType;
    }

    public void setControlType(Integer controlType) {
        this.controlType = controlType;
    }

    public String getControlTypeDesc() {
        return controlTypeDesc;
    }

    public void setControlTypeDesc(String controlTypeDesc) {
        this.controlTypeDesc = controlTypeDesc;
    }

    public Integer getSysType() {
        return sysType;
    }

    public void setSysType(Integer sysType) {
        this.sysType = sysType;
    }

    public String getSysTypeDesc() {
        return sysTypeDesc;
    }

    public void setSysTypeDesc(String sysTypeDesc) {
        this.sysTypeDesc = sysTypeDesc;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public Integer getGradeMust() {
        return gradeMust;
    }

    public void setGradeMust(Integer gradeMust) {
        this.gradeMust = gradeMust;
    }

    public Integer getCodeMust() {
        return codeMust;
    }

    public void setCodeMust(Integer codeMust) {
        this.codeMust = codeMust;
    }

    public Integer getRegisterCodeMust() {
        return registerCodeMust;
    }

    public void setRegisterCodeMust(Integer registerCodeMust) {
        this.registerCodeMust = registerCodeMust;
    }

    public Integer getRegionMust() {
        return regionMust;
    }

    public void setRegionMust(Integer regionMust) {
        this.regionMust = regionMust;
    }

    public Integer getCategoryMust() {
        return categoryMust;
    }

    public void setCategoryMust(Integer categoryMust) {
        this.categoryMust = categoryMust;
    }

    public Integer getRangeMust() {
        return rangeMust;
    }

    public void setRangeMust(Integer rangeMust) {
        this.rangeMust = rangeMust;
    }

    public Integer getFileMust() {
        return fileMust;
    }

    public void setFileMust(Integer fileMust) {
        this.fileMust = fileMust;
    }

    public String getQualificationName() {
        return qualificationName;
    }

    public void setQualificationName(String qualificationName) {
        this.qualificationName = qualificationName;
    }

    public String getQualificationCode() {
        return qualificationCode;
    }

    public void setQualificationCode(String qualificationCode) {
        this.qualificationCode = qualificationCode;
    }
}