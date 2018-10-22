package com.rograndec.feijiayun.chain.business.distr.branch.valid;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.exception.BaseException;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.purchase.addinstorage.vo.AddInstorageDtlVO;
import com.rograndec.feijiayun.chain.business.purchase.addinstorage.vo.AddInstorageGoodsLotNumberVO;
import com.rograndec.feijiayun.chain.business.purchase.addinstorage.vo.AddInstorageVO;
import com.rograndec.feijiayun.chain.common.component.ManageConfigComponent;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 盘点单字段校验
 * Created by sunteng on 2017/8/26.
 */
public class AddInstorageVOValidator implements ConstraintValidator<AddInstorageVOValid, AddInstorageVO>  {

    private Logger logger = LoggerFactory.getLogger(AddInstorageVOValidator.class);

    @Autowired
    private ManageConfigComponent manageConfigComponent;


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodsMapper goodsMapper;


    @Override
    public void initialize(AddInstorageVOValid valid) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(AddInstorageVO value, ConstraintValidatorContext context) throws BaseException {

        List<AddInstorageDtlVO> addInstorageDtlVOList = value.getAddInstorageDtlVOList();

//        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
//        if(goods == null) throw new BusinessException("商品不存在");
//        if(goods.getSpecialDrug() != -1 && addInstorageVO.getSecondReceiverId() == null){
//            throw new BusinessException("第"+addInstorageDtlVO.getLineNum()+"行的"+goods.getGenericName()+"为特管商品,必须有第二收货人!");
//        }

        for(AddInstorageDtlVO dtlVO : addInstorageDtlVOList){
            BigDecimal unitPrice = dtlVO.getUnitPrice();
//            if(BigDecimal.ZERO.compareTo(unitPrice) >= 0) throw new BusinessException("单价不能为0");
            Long goodsId = dtlVO.getGoodsId();
            Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
            if(goods == null) throw new BusinessException("商品不存在");
            if(goods.getSpecialDrug() != null && goods.getSpecialDrug() != -1){
                if(value.getSecondReceiverId() == null){
                    throw new BusinessException("商品编码"+goods.getCode()+"为特管商品,必须有第二收货人!");
                }
                if(value.getSecondCheckerId() == null){
                    throw new BusinessException("商品编码"+goods.getCode()+"为特管商品,必须有第二验收人!");
                }
            }

            List<AddInstorageGoodsLotNumberVO> goodsLotNumberVOList = dtlVO.getGoodsLotNumberVOList();
            for(AddInstorageGoodsLotNumberVO lotNumberVO : goodsLotNumberVOList){
                String lotNumber = lotNumberVO.getLotNumber();
                if(lotNumber == null) throw new BusinessException("商品编码" + goods.getCode() + "批号不能为空");
                Date productDate = lotNumberVO.getProductDate();
                if(productDate == null) throw new BusinessException("商品编码" + goods.getCode() + "生产日期不能为空");
                Date validDate = lotNumberVO.getValidDate();
                if(validDate == null) throw new BusinessException("商品编码" + goods.getCode() + "有效期不能为空");

            }
        }

//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        HttpSession session = servletRequestAttributes.getRequest().getSession();
//        UserVO userVO = (UserVO) session.getAttribute("user");
//
//        Long checkerId = value();
//        if(checkerId == null){
//            throw new BaseException(InventoryBizException.VALUE_CHECK,"验收员ID为必填字段");
//        }
//        if(userMapper.getUserByIdAndEnterpriseId(checkerId,userVO.getEnterpriseId()) == null){
//            throw new InventoryBizException(InventoryBizException.VALUE_CHECK,"验收员用户不存在");
//        }
//
//        Date checkDate = value.getCheckDate();
//        if(checkDate == null){
//            throw new BaseException(InventoryBizException.VALUE_CHECK,"验收日期为必填字段");
//        }
//
//
//        Long secondCheckerId = value.getSecondCheckerId();
//        value.getDetailVOList().stream().forEach(item->{
//            Goods goods = goodsMapper.selectByPrimaryKey(item.getGoodsId());
//            item.setIsSpiritDrugType(goods.getSpecialDrug());
//        });
//        long count = value.getDetailVOList().stream().filter(item -> item.getIsSpiritDrugType() != -1).count();
//        if(count > 0){//必填字段
//            if(secondCheckerId == null){
//                throw new BaseException(InventoryBizException.VALUE_CHECK,"商品中特殊药品，第二验收员ID为必填字段");
//            }
//            if(userMapper.getUserByIdAndEnterpriseId(secondCheckerId,userVO.getEnterpriseId()) == null){
//                throw new InventoryBizException(InventoryBizException.VALUE_CHECK,"第二验收员用户不存在");
//            }
//        } else {
//            if(secondCheckerId != null){
//                if(userMapper.getUserByIdAndEnterpriseId(secondCheckerId,userVO.getEnterpriseId()) == null){
//                    throw new InventoryBizException(InventoryBizException.VALUE_CHECK,"第二验收员用户不存在");
//                }
//            }
//        }


        return true;
    }
}
