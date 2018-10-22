package com.rograndec.feijiayun.chain.business.retail.royalty.valid;

import com.rograndec.feijiayun.chain.business.purchase.ret.dao.PurchaseReturnMapper;
import com.rograndec.feijiayun.chain.business.purchase.retout.dao.PurchaseReturnOutMapper;
import com.rograndec.feijiayun.chain.business.retail.royalty.exception.RoyaltyBizException;
import com.rograndec.feijiayun.chain.business.retail.royalty.vo.SaveOrUpdateSaleRoyaltyVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 校验修改时候 单据未被引用 和审批被驳回时才可以修改价格调整
 * Created by zhaiwei on 2017/8/26.
 */
public class ExtractCheckValidator implements ConstraintValidator<ExtractCheck, List<SaveOrUpdateSaleRoyaltyVO>> {
    private Logger logger = LoggerFactory.getLogger(ExtractCheckValidator.class);


    @Autowired
    private PurchaseReturnMapper purchaseReturnMapper;

    @Autowired
    private PurchaseReturnOutMapper purchaseReturnOutMapper;

    @Override
    public void initialize(ExtractCheck priceAdjustUpdateCheck) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(List<SaveOrUpdateSaleRoyaltyVO> value, ConstraintValidatorContext context) {


        Set<Long> set = new HashSet<>();
        for(SaveOrUpdateSaleRoyaltyVO saveOrUpdateSaleRoyaltyVO : value){
            set.add(saveOrUpdateSaleRoyaltyVO.getEnterpriseId());
        }

        if(set.size() > 1){
            throw new RoyaltyBizException(RoyaltyBizException.DEFULT_ERRO_CODE,"无法进行多个门店,营业人员提成");
        }

        return true;
    }


}
