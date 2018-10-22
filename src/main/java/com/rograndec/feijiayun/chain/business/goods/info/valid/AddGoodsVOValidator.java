package com.rograndec.feijiayun.chain.business.goods.info.valid;

import com.rograndec.feijiayun.chain.business.basic.warehouse.exception.BaseException;
import com.rograndec.feijiayun.chain.business.goods.info.exception.GoodsBizException;
import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsQualificationConfigVO;
import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.GoodsQualification;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.business.system.set.service.ScopeQualificationService;
import com.rograndec.feijiayun.chain.common.component.ManageConfigComponent;
import com.rograndec.feijiayun.chain.common.constant.GoodsAttribute2DrugsA;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;
import java.util.List;

/**
 * 商品信息增加的字段校验
 * Created by sunteng on 2017/8/26.
 */
public class AddGoodsVOValidator  implements ConstraintValidator<AddGoodsValid, GoodsVO>  {

    private Logger logger = LoggerFactory.getLogger(AddGoodsVOValidator.class);

    @Autowired
    private ManageConfigComponent manageConfigComponent;

    @Autowired
    private ScopeQualificationService scopeQualificationService;


    @Override
    public void initialize(AddGoodsValid addGoodsValid) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(GoodsVO value, ConstraintValidatorContext context) throws BaseException {

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        UserVO userVO = (UserVO) session.getAttribute("user");

        ManageConfig manageConfig = manageConfigComponent.getMangeConfigByEPId(userVO);

        //商品信息编码规则（0-商品分组+4位流水码；1-自定义编码）
        Integer goodsCodeRule = manageConfig.getGoodsCodeRule();//商品信息规则
        //基础数据质量控制（0关闭；1-开启）
        Integer qualityControl = manageConfig.getQualityControl();
        //
        if(value.getCategoryId() == null){
            throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"商品分类为必填字段");
        }
        if(goodsCodeRule == 1 && StringUtils.isBlank(value.getCode())){
            throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"商品编码为必填字段");
        }

//        if(StringUtils.isBlank(value.getName()) && qualityControl == 1){
//            throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"商品名称为必填字段");
//        }

        if(StringUtils.isBlank(value.getGenericName())){
            throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"通用名称为必填字段");
        }
        if(StringUtils.isBlank(value.getSpecification()) && qualityControl == 1){
            throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"规格为必填字段");
        }

        if((StringUtils.isBlank(value.getManufacturer()) && value.getManufacturerId() == null) && qualityControl == 1){
            throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"生成厂商为必填字段");
        }
        if((StringUtils.isBlank(value.getManagementScopeName()) || value.getManagementScopeId() == null) && qualityControl == 1){
            throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"经营范围为必填字段");
        }
        if((StringUtils.isBlank(value.getCheckTypeName()) || value.getCheckTypeId() == null) && qualityControl == 1){
            throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"验收分类为必填字段");
        }
        if((StringUtils.isBlank(value.getCheckTypeName()) || value.getCheckTypeId() == null) && qualityControl == 1){
            throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"验收分类为必填字段");
        }

        if(StringUtils.isBlank(value.getCheckTypeName()) || value.getCheckTypeId() == null ){
            throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"验收分类为必填字段");
        }


        if((StringUtils.isBlank(value.getFirstGoodsCode()) && goodsCodeRule == 1) && qualityControl == 1){
            throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"首营品种为必填字段");
        }
        if((StringUtils.isBlank(value.getApplicantName()) || value.getApplicantId() == null) && qualityControl == 1){
            throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"申请人员为必填字段");
        }
        if(StringUtils.isBlank(value.getApplicationOpinion())  && qualityControl == 1){
            throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"申请意见为必填字段");
        }
        if(value.getApplicationTime() == null  && qualityControl == 1){
            throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"申请日期为必填字段");
        }

        if(value.getStorageMaintenanceVO().getNearEffectPeriod() == null  && qualityControl == 1){
            throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"近效期为必填字段");
        }
        if(value.getStorageMaintenanceVO().getUnsalableCycle() == null  && qualityControl == 1){
            throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"滞销周期为必填字段");
        }
        if(value.getStorageMaintenanceVO().getMaintenanceCycle() == null  && qualityControl == 1){
            throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"养护周期为必填字段");
        }
        if(value.getBusinessVariety() == 0 && value.getGoodsAttribute() == GoodsAttribute2DrugsA.CHINESE_MEDICINE_DECOCTION_PIECES.getCode() && StringUtils.isBlank(value.getPlace())){
            throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"商品属性为中药饮片的时，产地为必填项");
        }


        List<GoodsQualificationConfigVO> list = value.getGoodsQualificationConfigVOList();
        list.forEach(item->{
            Long qualificationId = item.getQualificationId();
            //资质编号
            String qualificationCode = item.getQualificationCode();
            //有效期
            Date validUntil = item.getValidUntil();
            //附件id
            Long fileId = item.getFileId();
            //根据主键查询该资质的信息
            GoodsQualification goodsQualification = scopeQualificationService.getGoodQuaInfoByKey(qualificationId);
            if(goodsQualification != null){
                //资质编号是否必填（0-只读；1-必填）
                Integer codeMust = goodsQualification.getCodeMust();
                //有效期至是否必填（0-只读；1-必填）
                Integer validUntilMust = goodsQualification.getValidUntilMust();
                //附件是否必填（0-非必填；1-必填）
                Integer fileMust = goodsQualification.getFileMust();
                if(StringUtils.isBlank(qualificationCode) && codeMust == 1){
                    throw new GoodsBizException(GoodsBizException.VALUE_CHECK,goodsQualification.getName() + "资质编号为必填字段");
                }
                if(validUntil == null && validUntilMust == 1  ){
                    throw new GoodsBizException(GoodsBizException.VALUE_CHECK,goodsQualification.getName() + "有效期为必填字段");
                }
                if(fileId == null &&  fileMust == 1 ){
                    throw new GoodsBizException(GoodsBizException.VALUE_CHECK,goodsQualification.getName() + "附件为必填字段");
                }

            }
        });

        return true;
    }
}
