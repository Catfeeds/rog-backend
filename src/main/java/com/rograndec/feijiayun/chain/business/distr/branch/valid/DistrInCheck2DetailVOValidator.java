package com.rograndec.feijiayun.chain.business.distr.branch.valid;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.exception.BaseException;
import com.rograndec.feijiayun.chain.business.distr.branch.exception.InventoryBizException;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.DistrInCheck2DetailVO;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.common.component.ManageConfigComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

/**
 * 盘点单字段校验
 * Created by sunteng on 2017/8/26.
 */
public class DistrInCheck2DetailVOValidator implements ConstraintValidator<DistrInCheck2DetailValid, DistrInCheck2DetailVO>  {

    private Logger logger = LoggerFactory.getLogger(DistrInCheck2DetailVOValidator.class);

    @Autowired
    private ManageConfigComponent manageConfigComponent;


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodsMapper goodsMapper;


    @Override
    public void initialize(DistrInCheck2DetailValid addGoodsValid) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(DistrInCheck2DetailVO value, ConstraintValidatorContext context) throws BaseException {

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        UserVO userVO = (UserVO) session.getAttribute("user");

        Long checkerId = value.getCheckerId();
        if(checkerId == null){
            throw new BaseException(InventoryBizException.VALUE_CHECK,"验收员ID为必填字段");
        }
        if(userMapper.getUserByIdAndEnterpriseId(checkerId,userVO.getEnterpriseId()) == null){
            throw new InventoryBizException(InventoryBizException.VALUE_CHECK,"验收员用户不存在");
        }

        Date checkDate = value.getCheckDate();
        if(checkDate == null){
            throw new BaseException(InventoryBizException.VALUE_CHECK,"验收日期为必填字段");
        }


        Long secondCheckerId = value.getSecondCheckerId();
        value.getDetailVOList().stream().forEach(item->{
            Goods goods = goodsMapper.selectByPrimaryKey(item.getGoodsId());
            item.setIsSpiritDrugType(goods.getSpecialDrug());
        });
        long count = value.getDetailVOList().stream().filter(item -> item.getIsSpiritDrugType() != -1).count();
        if(count > 0){//必填字段
            if(secondCheckerId == null){
                throw new BaseException(InventoryBizException.VALUE_CHECK,"商品中特殊药品，第二验收员ID为必填字段");
            }
            if(userMapper.getUserByIdAndEnterpriseId(secondCheckerId,userVO.getEnterpriseId()) == null){
                throw new InventoryBizException(InventoryBizException.VALUE_CHECK,"第二验收员用户不存在");
            }
        } else {
            if(secondCheckerId != null){
                if(userMapper.getUserByIdAndEnterpriseId(secondCheckerId,userVO.getEnterpriseId()) == null){
                    throw new InventoryBizException(InventoryBizException.VALUE_CHECK,"第二验收员用户不存在");
                }
            }
        }


        return true;
    }
}
