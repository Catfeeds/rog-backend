package com.rograndec.feijiayun.chain.business.distr.parent.valid;

import com.rograndec.feijiayun.chain.business.basic.warehouse.exception.BaseException;
import com.rograndec.feijiayun.chain.business.distr.branch.exception.InventoryBizException;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrSendDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOut;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOutDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOutShelf;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrSendDetail;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * 盘点单字段校验
 * Created by sunteng on 2017/8/26.
 */
public class DistrOutValidator implements ConstraintValidator<DistrOutValid, DistrOut>  {

    private Logger logger = LoggerFactory.getLogger(DistrOutValidator.class);

    @Autowired
    private DistrSendDetailMapper distrSendDetailMapper;

    @Autowired
    private ManageConfigMapper manageConfigMapper;

    @Override
    public void initialize(DistrOutValid addGoodsValid) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(DistrOut value, ConstraintValidatorContext context) throws BaseException {

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        UserVO userVO = (UserVO) session.getAttribute("user");

        List<DistrOutDetail> distrOutDetailList = value.getDistrOutDetailList();

        ManageConfig manageConfig = manageConfigMapper.selectByCurrentUser(userVO);

        /**
         * 业务流程质量控制（0关闭；1-开启）
         */
        Integer businessControl = manageConfig.getBusinessControl();

        if(businessControl == EnableStatus.ENABLE.getStatus()) {
            for (DistrOutDetail distrOutDetail : distrOutDetailList) {
                Long baseOrderDtlId = distrOutDetail.getBaseOrderDtlId();

                DistrSendDetail distrSendDetail = distrSendDetailMapper.selectByPrimaryKey(baseOrderDtlId);

                BigDecimal sendDetailQuantity = distrSendDetail.getQuantity();

                List<DistrOutShelf> distrOutShelfList = distrOutDetail.getDistrOutShelfList();

                BigDecimal sumShelfQuantity = sumShelfQuantity(distrOutShelfList);

                if (sendDetailQuantity.compareTo(sumShelfQuantity) != 0) {
                    throw new InventoryBizException(InventoryBizException.VALUE_CHECK, "质量控制开启,出库数量与配送单不一致,请检查出库数量!");
                }

            }
        }


        return true;
    }

    public BigDecimal sumShelfQuantity(List<DistrOutShelf> distrOutShelfList){

        return distrOutShelfList.stream().filter(Objects::nonNull)
                .filter(c->c.getQuantity() != null).map(DistrOutShelf::getQuantity).reduce(BigDecimal.ZERO, BigDecimal::add);

    }
}
