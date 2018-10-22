package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInNoticeDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInNoticeMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInNotice;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInNoticeDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnNoticeDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnNoticeMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnNotice;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnNoticeDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnNoticeDtlVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnNoticeFormVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnNoticeMapVO;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderMapper;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by dudy on 2017/10/10.
 * 配送公共组件
 */
@Component
public class DistrComponent {


    @Autowired
    private DistrInNoticeMapper inNoticeMapper;
    @Autowired
    private DistrInNoticeDetailMapper inNoticeDetailMapper;


    @Autowired
    private PriceOrderMapper priceOrderMapper;

    @Autowired
    private PriceOrderDetailMapper priceOrderDetailMapper;

    @Autowired
    private UserEnterpriseComponet userEnterpriseComponet;
    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private DistrReturnNoticeMapper distrReturnNoticeMapper;

    @Autowired
    private DistrReturnNoticeDetailMapper distrReturnNoticeDetailMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsTaxRateMapper goodsTaxRateMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommonComponent commonComponent;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    /**
     *
     * 修改分店 购进订单状态
     * @param inNoticeId  订单ID
     * @param status      订单状态
     * @author  dudy
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateInNoticeStatusForBranch(Long inNoticeId,Integer status){

        inNoticeMapper.updateStatus(inNoticeId,status);
        inNoticeDetailMapper.updateStatusByOrderId(inNoticeId,status);
    }


    /** 
    * @Description: 生成门店配进订单
    * @return:  
    * @Author: dongyang.du
    * @Date: 07/02/2018
     * @param  inNotice  通知单的enterpriseId 为要货门店的企业ID
    */
    @Transactional(rollbackFor = Exception.class)
    public DistrInNotice saveInNotice(UserVO userVO,DistrInNotice inNotice) throws  Exception{

        Long enterpriseId = inNotice.getEnterpriseId();
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);

        if(enterprise == null){
            throw  new BusinessException("当前门店【企业ID："+enterpriseId+"】没找到");
        }
        Long parentId = enterprise.getParentId();

        inNotice.setOrderType(OrderRule.DISTR_IN_ORDER.getType());// 类型编码
        String code = orderCodeComponent.generate(OrderRule.DISTR_IN_ORDER.getCodePrefix(), enterpriseId, enterprise.getCode());// 单号
        inNotice.setCode(code);

        inNotice.setParentId(parentId);

        User user = userMapper.selectByPrimaryKey(inNotice.getStorageManId());
        if (user != null){
            inNotice.setStorageManCode(user.getCode());
            inNotice.setStorageManName(user.getName());
        }

        userEnterpriseComponet.setUserEnterprise(inNotice,userVO,true);
        inNotice.setVarietiesQuantity(inNotice.getDetailList().size());// 品种数量

        inNoticeMapper.insertSelective(inNotice);

        for (DistrInNoticeDetail detail: inNotice.getDetailList()) {
            PriceOrderDetail priceOrderDetail = commonComponent.getBasePriceOrderDetail(enterpriseId,parentId,detail.getGoodsId());
            if (priceOrderDetail != null) {
                detail.setRetailPrice(priceOrderDetail.getRetailPrice() == null ? BigDecimal.ZERO : priceOrderDetail.getRetailPrice());// 零售单价
                detail.setMemberPrice(priceOrderDetail.getMemberPrice() == null ? BigDecimal.ZERO : priceOrderDetail.getMemberPrice());// 会员单价
            }

            detail.setUnclearQuantity(detail.getQuantity());
            detail.setClearQuantity(BigDecimal.ZERO);
            detail.setStatus(inNotice.getStatus());
            detail.setParentId(parentId);
            detail.setEnterpriseId( enterpriseId );
            detail.setOrderId(inNotice.getId());
            detail.setOrderCode(code);
            detail.setOrderType(inNotice.getOrderType());
            detail.setOrderDate(inNotice.getOrderDate());
            userEnterpriseComponet.setUserEnterprise(detail,userVO,true);

            inNoticeDetailMapper.insertSelective(detail);
        }

        return inNotice;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateInNoticeStatus4Parent(Long inNoticeId,Integer status,Long enterpriseId){

        distrReturnNoticeMapper.updateStatus(status,inNoticeId,enterpriseId);

        distrReturnNoticeDetailMapper.updateStatus(status,inNoticeId,enterpriseId);
    }
    /**
     * 新增总店 购进订单
     * @param distrReturnNoticeFormVO
     * @param loginUser
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public DistrReturnNoticeMapVO saveInNoticeStatus4Parent(DistrReturnNoticeFormVO distrReturnNoticeFormVO, UserVO loginUser) throws Exception {

        //构建主单
        DistrReturnNotice distrReturnNotice = new DistrReturnNotice();
        distrReturnNotice.setEnterpriseId(loginUser.getParentId());
        distrReturnNotice.setParentId(0L);
        distrReturnNotice.setOrderType(OrderRule.DISTR_RETURN_NOTICE.getType());
        //生成的移动单号
        String code = orderCodeComponent.
                generate(OrderRule.DISTR_RETURN_NOTICE.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode());
        distrReturnNotice.setCode(code);
        distrReturnNotice.setNoticeDate(new Date());
        //需要上游提供的数据以下#################################################
        distrReturnNotice.setBaseOrderId(distrReturnNoticeFormVO.getBaseOrderId());//--基础单据ID
        distrReturnNotice.setBaseOrderType(distrReturnNoticeFormVO.getBaseOrderType());//--基础单据类型
        distrReturnNotice.setBaseOrderCode(distrReturnNoticeFormVO.getBaseOrderCode());//--基础单据编码
        distrReturnNotice.setBaseOrderDate(distrReturnNoticeFormVO.getBaseOrderDate());//--基础单据日期
        distrReturnNotice.setRequestUnitId(distrReturnNoticeFormVO.getRequestUnitId());//--要货单位id
        distrReturnNotice.setRequestUnitCode(distrReturnNoticeFormVO.getRequestUnitCode());//--要货单位编码
        distrReturnNotice.setRequestUnitName(distrReturnNoticeFormVO.getRequestUnitName());//--要货单位名称
        distrReturnNotice.setDistrType(distrReturnNoticeFormVO.getDistrType());//配货类型（0-总部配送；3-分店间调剂；4-直调配送）
        distrReturnNotice.setQuantityTotal(distrReturnNoticeFormVO.getQuantityTotal());//数量合计
        //品种类型先置为0 我查看明细之后计算塞入
        distrReturnNotice.setVarietiesQuantity(0);//此项不需要上游来传
        distrReturnNotice.setAmountTotal(distrReturnNoticeFormVO.getAmountTotal());//金额合计（整单优惠前的金额合计）
        distrReturnNotice.setWholeDiscount(distrReturnNoticeFormVO.getWholeDiscount());//整单折扣（%）
        distrReturnNotice.setWholeDiscountAmount(distrReturnNoticeFormVO.getWholeDiscountAmount());//整单优惠金额
        distrReturnNotice.setRealAmountTotal(distrReturnNoticeFormVO.getRealAmountTotal());//实际金额合计
        distrReturnNotice.setNotaxRealAmountTotal(distrReturnNoticeFormVO.getNotaxRealAmountTotal());//不含税金额合计
        distrReturnNotice.setTaxAmountTotal(distrReturnNoticeFormVO.getTaxAmountTotal());//税额合计
        distrReturnNotice.setStatus(distrReturnNoticeFormVO.getStatus());//状态
        distrReturnNotice.setRemark(distrReturnNoticeFormVO.getRemark());//备注
        //需要上游数据提供以上################################################
        UserEnterpriseUtils.setUserCreateOrModify(distrReturnNotice,loginUser,true);
        distrReturnNoticeMapper.insertSelective(distrReturnNotice);
        //构建明细单插入
        List<DistrReturnNoticeDtlVO> distrReturnNoticeDtlVOList = distrReturnNoticeFormVO.getDistrReturnNoticeDtlVOList();
        Set set = new HashSet();//记录品种数量
        List<DistrReturnNoticeDetail> distrReturnNoticeDetails = new ArrayList<>();
        for (DistrReturnNoticeDtlVO d:distrReturnNoticeDtlVOList) {
            DistrReturnNoticeDetail detail = new DistrReturnNoticeDetail();
            detail.setEnterpriseId(loginUser.getParentId());
            detail.setParentId(0L);
            detail.setNoticeId(distrReturnNotice.getId());
            detail.setOrderType(OrderRule.DISTR_RETURN_NOTICE.getType());
            detail.setNoticeCode(code);
            detail.setNoticeDate(new Date());
            //由上游提供的数据
            detail.setBaseOrderDtlId(d.getBaseOrderDtlId());//基础单据明细ID
            detail.setBaseOrderId(distrReturnNoticeFormVO.getBaseOrderId());
            detail.setBaseOrderType(distrReturnNoticeFormVO.getBaseOrderType());
            detail.setBaseOrderCode(distrReturnNoticeFormVO.getBaseOrderCode());
            detail.setBaseOrderDate(distrReturnNoticeFormVO.getBaseOrderDate());
            detail.setGoodsId(d.getGoodsId());//商品ID
            set.add(d.getGoodsId());
            Goods goods = goodsMapper.selectByPrimaryKey(d.getGoodsId());
            if (goods != null){
                detail.setGoodsCode(goods.getCode());
                detail.setBarcode(goods.getBarcode());
                detail.setGoodsName(goods.getName());
                detail.setGoodsGenericName(goods.getGenericName());
                detail.setDosageId(goods.getDosageId());
                detail.setDosageName(goods.getDosageName());
                detail.setUnitId(goods.getUnitId());
                detail.setUnitName(goods.getUnitName());
                detail.setGoodsSpecification(goods.getSpecification());
                detail.setManufacturerId(goods.getManufacturerId());
                detail.setManufacturer(goods.getManufacturer());
                detail.setGoodsPlace(goods.getPlace());
                detail.setApprovalNumber(goods.getApprovalNumber());
            }
            detail.setQuantity(d.getQuantity());//数量
            detail.setUnitPrice(d.getUnitPrice());//单价
            detail.setLineDiscount(d.getLineDiscount());//行折扣（%）
            detail.setAmount(d.getAmount());//金额（整单优惠前金额）
            detail.setWholeDiscount(distrReturnNoticeFormVO.getWholeDiscount());
            detail.setLineDiscountAmount(d.getLineDiscountAmount());//行优惠（整单优惠分摊到行的金额）
            detail.setRealPrice(d.getRealPrice());//实际单价（实际金额/数量）
            detail.setRealAmount(d.getRealAmount());//实际金额
            detail.setTaxRateId(d.getTaxRateId());//税率ID
            GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(d.getTaxRateId());
            detail.setTaxRate(goodsTaxRate.getTaxRate());
            detail.setNotaxRealPrice(d.getNotaxRealPrice());//不含税实际单价
            detail.setNotaxRealAmount(d.getNotaxRealAmount());//不含税实际金额
            detail.setTaxAmount(d.getTaxAmount());//税额
            //先设置未清数量为当前数量--已清数量为0
            detail.setUnclearQuantity(d.getQuantity());
            detail.setClearQuantity(BigDecimal.ZERO);
            detail.setLineNum(d.getLineNum());//行号
            detail.setStatus(distrReturnNoticeFormVO.getStatus());
            detail.setRemark(d.getRemark());//备注
            UserEnterpriseUtils.setUserCreateOrModify(detail,loginUser,true);
            distrReturnNoticeDetailMapper.insertSelective(detail);

            distrReturnNoticeDetails.add(detail);
        }
        //修正总单的品种数量
        DistrReturnNotice newDistrReturnNotice = new DistrReturnNotice();
        newDistrReturnNotice.setId(distrReturnNotice.getId());
        newDistrReturnNotice.setVarietiesQuantity(set.size());
        distrReturnNoticeMapper.updateByPrimaryKeySelective(newDistrReturnNotice);

        distrReturnNotice.setVarietiesQuantity(set.size());


        DistrReturnNoticeMapVO distrReturnNoticeMapVO = new DistrReturnNoticeMapVO();
        distrReturnNoticeMapVO.setDistrReturnNotice(distrReturnNotice);
        distrReturnNoticeMapVO.setDistrReturnNoticeDetails(distrReturnNoticeDetails);

        return distrReturnNoticeMapVO;
    }
}
