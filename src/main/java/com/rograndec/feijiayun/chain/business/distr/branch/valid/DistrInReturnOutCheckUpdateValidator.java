package com.rograndec.feijiayun.chain.business.distr.branch.valid;

import com.rograndec.feijiayun.chain.business.basic.warehouse.exception.BaseException;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInReturnDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInReturnOutMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnDetail;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOut;
import com.rograndec.feijiayun.chain.business.distr.branch.exception.InventoryBizException;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReturnOutDetailFormVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReturnOutFormVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReturnOutShelfFormVO;
import com.rograndec.feijiayun.chain.business.goods.info.exception.GoodsBizException;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.status.DistrInReturnStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.List;

/**
 * 盘点单字段校验
 * Created by sunteng on 2017/8/26.
 */
public class DistrInReturnOutCheckUpdateValidator implements ConstraintValidator<DistrInReturnOutCheckUpdatelValid, DistrInReturnOutFormVO>  {

    private Logger logger = LoggerFactory.getLogger(DistrInReturnOutCheckUpdatelValid.class);

    @Autowired
    private DistrInReturnOutMapper distrInReturnOutMapper;

    @Autowired
    private DistrInReturnDetailMapper distrInReturnDetailMapper;

    @Autowired
    private ManageConfigMapper manageConfigMapper;

    @Override
    public void initialize(DistrInReturnOutCheckUpdatelValid distrInReturnOutCheckUpdatelValid) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(DistrInReturnOutFormVO value, ConstraintValidatorContext context) throws BaseException {


        List<DistrInReturnOutDetailFormVO> dsf = value.getDistrInReturnOutDetailFormVOS();
        for(DistrInReturnOutDetailFormVO distrInReturnOutDetailFormVO : dsf){
            if(distrInReturnOutDetailFormVO.getQuantity().compareTo(BigDecimal.ZERO) <= 0){
                throw new GoodsBizException(GoodsBizException.VALUE_CHECK,"数量不能小于等于0");
            }
        }

        /**
         * 如果id不为空则表示是修改
         */
        if(null != value.getId()){

            DistrInReturnOut distrInReturnOut = distrInReturnOutMapper.selectByPrimaryKey(value.getId());

            //只有出库中数据可以修改,出库中数据是由于复核被驳回,其他情况分店购进退出出库不允许修改
            if(!distrInReturnOut.getStatus().equals(DistrInReturnStatus.OUTTING)){
                throw new InventoryBizException(InventoryBizException.VALUE_CHECK,"非出库中数据,不可修改");
            }
        }

        if(CollectionUtils.isEmpty(value.getDistrInReturnOutDetailFormVOS())){
            throw new InventoryBizException(InventoryBizException.VALUE_CHECK,"出库明细为空");
        }

        value.getDistrInReturnOutDetailFormVOS().forEach(
                detail ->{
                    if(detail.getQuantity().compareTo(BigDecimal.ZERO) <= 0){
                        throw new InventoryBizException(InventoryBizException.VALUE_CHECK,"数量不能空");
                    }

                    detail.getDistrInReturnOutShelfFormVOS().forEach(
                            shelf ->{
                                if(shelf.getQuantity().compareTo(BigDecimal.ZERO) <= 0){
                                    throw new InventoryBizException(InventoryBizException.VALUE_CHECK,"数量不能空");
                                }
                            }
                    );
                }
        );

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        UserVO userVO = (UserVO) session.getAttribute("user");

        ManageConfig manageConfig = manageConfigMapper.selectByCurrentUser(userVO);


        if(null == manageConfig){
            return true;
        }

        /**
         * 业务流程质量控制（0关闭；1-开启）
         */
        Integer businessControl = manageConfig.getBusinessControl();

        if(businessControl == EnableStatus.ENABLE.getStatus()){
            //如果业务流程开启 配进退出出库的批号对应的商品和配进退出的批号对应的商品数量必须一致
            List<DistrInReturnOutDetailFormVO> distrInReturnOutDetailFormVOS = value.getDistrInReturnOutDetailFormVOS();

            for(DistrInReturnOutDetailFormVO distrInReturnOutDetailFormVO : distrInReturnOutDetailFormVOS){
                DistrInReturnDetail distrInReturnDetail = distrInReturnDetailMapper.selectByPrimaryKey(distrInReturnOutDetailFormVO.getReturnDetailId());
                if(distrInReturnDetail.getQuantity().compareTo(distrInReturnOutDetailFormVO.getQuantity()) != 0){
                    throw new InventoryBizException(InventoryBizException.VALUE_CHECK,"质量控制开启,配进退出出库单的数量必须和配进退出单的数量一致");
                }

                List<DistrInReturnOutShelfFormVO> distrInReturnOutShelfFormVOS = distrInReturnOutDetailFormVO.getDistrInReturnOutShelfFormVOS();

                BigDecimal qu = distrInReturnOutShelfFormVOS.stream().map(dr -> dr.getQuantity()).reduce((a, b) -> {

                    BigDecimal sum = a.add(b).setScale(2, BigDecimal.ROUND_HALF_UP);

                    return sum;
                }).get();

                if(qu.compareTo(distrInReturnDetail.getQuantity()) != 0){
                    throw new InventoryBizException(InventoryBizException.VALUE_CHECK,"质量控制开启,配进退出出库单的数量必须和配进退出单的数量一致");
                }

            }

        }

        return true;
    }
}
