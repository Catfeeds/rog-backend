package com.rograndec.feijiayun.chain.business.retail.special.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsVO;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.vo.GoodsInfoStockShelfVO;
import com.rograndec.feijiayun.chain.business.member.info.dao.MemberInfoMapper;
import com.rograndec.feijiayun.chain.business.member.info.entity.MemberInfo;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleDetailMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleShelfMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.Sale;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.SaleDetail;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.SaleShelf;
import com.rograndec.feijiayun.chain.business.retail.special.dao.SpecialRegisterDetailMapper;
import com.rograndec.feijiayun.chain.business.retail.special.dao.SpecialRegisterMapper;
import com.rograndec.feijiayun.chain.business.retail.special.dao.SpecialRegisterShelfMapper;
import com.rograndec.feijiayun.chain.business.retail.special.entity.SpecialRegister;
import com.rograndec.feijiayun.chain.business.retail.special.entity.SpecialRegisterDetail;
import com.rograndec.feijiayun.chain.business.retail.special.entity.SpecialRegisterShelf;
import com.rograndec.feijiayun.chain.business.retail.special.service.SpecialRegisterService;
import com.rograndec.feijiayun.chain.business.retail.special.vo.*;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.CalculateComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.ShelfStatus;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;


/**
 * @author zhengbin.jin
 * @version 1.0
 * @ClassName: SpecialRegisterServiceImpl
 * @Description: 零售管理-专管登记-实现接口
 * @date 2017-09-22 16:25:37
 */
@Service
public class SpecialRegisterServiceImpl implements SpecialRegisterService {

    private static final Logger logger = LoggerFactory.getLogger(SpecialRegisterServiceImpl.class);
    @Autowired
    private OrderCodeComponent orderCodeComponent;
    @Autowired
    private SpecialRegisterMapper specialRegisterMapper;
    @Autowired
    private SpecialRegisterDetailMapper specialRegisterDetailMapper;
    @Autowired
    private SpecialRegisterShelfMapper specialRegisterShelfMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsTaxRateMapper goodsTaxRateMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private SaleMapper saleMapper;
    @Autowired
    private SaleDetailMapper saleDetailMapper;
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    @Autowired
    private SaleShelfMapper saleShelfMapper;
    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
    @Autowired
    private ManageConfigMapper manageConfigMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WarehouseShelfMapper warehouseShelfMapper;


    @Override
    public SpecialRegister getRegisterDataById(Long id) {
        return specialRegisterMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SpecialRegisterVO> listRegisterData(RequestSpecialRegisterVo requestSpecialRegisterVo, Page<List<SpecialRegisterVO>> page) throws Exception {
        Integer sortDate=requestSpecialRegisterVo.getSortDate();
        Integer sortCode=requestSpecialRegisterVo.getSortCode();
        String sort="";
        if(sortDate==null&&sortCode==null){
            sort += "ssr.register_date desc,";
        }
        if(sortDate!=null&&sortDate==0){
            sort+="ssr.register_date,";
        }
        if(sortDate!=null&&sortDate==1){
            sort+="ssr.register_date desc,";
        }
        if(sortCode!=null&&sortCode==0){
            sort+="ssr.code,";
        }
        if(sortCode!=null&&sortCode==1){
            sort+="ssr.code desc,";
        }
        if(!"".equals(sort)){
            sort=sort.substring(0,sort.length()-1);
        }
        requestSpecialRegisterVo.setSort(sort);
        List<SpecialRegisterVO> list = specialRegisterMapper.listSpecialRegisterData(requestSpecialRegisterVo);
        int count = specialRegisterMapper.countSpecialRegisterData(requestSpecialRegisterVo);
        page.setTotalRecord(count);
        page.setResult(list);
        return list;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int save(SpecialRegisterSaveOrupdateVO specialRegisterVo, UserVO userVO) throws Exception {
        SpecialRegister specialRegister = (SpecialRegister) EntityUtils.reflectAddSetDefaultValue(new SpecialRegister().getClass(), userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialRegisterVo, specialRegister);
        ManageConfig manage = manageConfigMapper.selectByCurrentUser(userVO);//判断质量流程是否开启
        Boolean zl= manage.getBusinessControl()==0?true:false;
        if(zl){
            //质量流程开的时候前台必须提供采购人员id
            User user=userMapper.selectByPrimaryKey(specialRegisterVo.getUserId());
            if(user==null){
                throw new BusinessException("没有该员工，请核实员工id");
            }
            if(user.getEnterpriseId().intValue()!=userVO.getEnterpriseId().intValue()){
                throw new BusinessException("没有该员工，请核实员工id");
            }
            specialRegister.setRegisterDate(specialRegisterVo.getRegisterDate());
            specialRegister.setRegisterManId(user.getId());
            specialRegister.setRegisterManCode(user.getCode());
            specialRegister.setRegisterManName(user.getName());
        }else{
            specialRegister.setRegisterDate(new Date());
            specialRegister.setRegisterManId(userVO.getUserId());
            specialRegister.setRegisterManCode(userVO.getUserCode());
            specialRegister.setRegisterManName(userVO.getUserName());
        }
        if(specialRegisterVo.getSaleId()!=null){
            Sale sale = saleMapper.selectByPrimaryKey(specialRegisterVo.getSaleId());
            if (sale == null || sale.getSaleType() != 0) {
                throw new BusinessException("补登失败,销售单不存在");
            }
            specialRegister.setBaseOrderType(sale.getOrderType());
            specialRegister.setBaseOrderDate(sale.getSaleDate());
            specialRegister.setBaseOrderCode(sale.getCode());
            specialRegister.setBaseOrderId(sale.getId());
        }
        //设置登记人员信息
        specialRegister.setEnterpriseId(userVO.getEnterpriseId());
        Long memberId = specialRegister.getMemberId();
        if (memberId != null) {
            //查询会员信息
            MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(memberId);
            if (memberInfo != null) {
                specialRegister.setMemberCardCode(memberInfo.getCardCode());
                specialRegister.setMemberName(memberInfo.getMemberName());
            }
        }
        //设置登记单号
        specialRegister.setCode(orderCodeComponent.generate(OrderRule.SPECIAL_REGISTER.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode()));
        specialRegister.setOrderType(OrderRule.SPECIAL_REGISTER.getType());
        specialRegister.setStatus(PurchaseStatus.WAIT_PAY.getStatus());
        //设置登记单明细信息
        List<SpecialRegisterDetailSaveOrupdateVO> specialRegisterDetailSaveOrupdateVOList = specialRegisterVo.getSpecialRegisterDetailSaveOrupdateVOList();
        BigDecimal amountTotal = new BigDecimal(0);//金额合计
        BigDecimal quantityTotal = new BigDecimal(0);//数量合计
        List<SpecialRegisterDetail> specialRegisterDetailList = new ArrayList<>();
        for (SpecialRegisterDetailSaveOrupdateVO specialRegisterDetailSaveOrupdateVO :
                specialRegisterDetailSaveOrupdateVOList) {
            SpecialRegisterDetail specialRegisterDetail = new SpecialRegisterDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialRegisterDetailSaveOrupdateVO, specialRegisterDetail);

            long gid = specialRegisterDetail.getGoodsId();
            //查询药品信息
            GoodsVO goods = goodsMapper.selectGoodsInfoById(gid);
            if (goods == null) {
                throw new BusinessException("没有该商品，请核实商品ID=" + gid);
            }
            //填充药品信息
            this.fillGoodsInfo(goods, specialRegisterDetail);
            //填充登记单信息
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialRegister, specialRegisterDetail);

            if (specialRegisterDetail.getUnitPrice().compareTo(BigDecimal.ZERO) == -1) {//如果单价小于0
                throw new BusinessException("单价不能小于0");
            } else if (specialRegisterDetail.getUnitPrice().compareTo(BigDecimal.ZERO) == 0) {//如果单价等于0
                throw new BusinessException("单价不能等于0");
            }
            if (specialRegisterDetail.getQuantity().compareTo(BigDecimal.ZERO) == -1) {//如果数量小于0
                throw new BusinessException("数量不能小于0");
            }
            if (specialRegisterDetail.getLineDiscount().compareTo(BigDecimal.ZERO) == -1) {//如果折扣小于0
                throw new BusinessException("折扣不能小于0");
            } else if (specialRegisterDetail.getLineDiscount().compareTo(BigDecimal.ZERO) == 0) {//如果折扣等于0
                throw new BusinessException("折扣不能等于0");
            }
            if (specialRegisterDetail.getTaxRateId() == null) {
                throw new BusinessException("税率ID不能为空");
            } else {
                GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(specialRegisterDetail.getTaxRateId());
                if (goodsTaxRate == null) {
                    throw new BusinessException("没有该税率值，请核实税率ID");
                } else {
                    specialRegisterDetail.setTaxRate(goodsTaxRate.getTaxRate());
                }

            }
            //计算金额合计
            BigDecimal amount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(specialRegisterDetail.getQuantity(), specialRegisterDetail.getUnitPrice(), specialRegisterDetail.getLineDiscount());
            amountTotal = amountTotal.add(amount);
            //药品数量合计
            quantityTotal = quantityTotal.add(specialRegisterDetail.getQuantity());
            //封装装货位信息
            List<SpecialRegisterShelf> specialRegisterShelfList = new ArrayList<>();
            for (SpecialRegisterShelfSaveOrupdateVO specialRegisterShelfSaveOrupdateVO : specialRegisterDetailSaveOrupdateVO.getSpecialRegisterShelfSaveOrupdateVOList()) {
                SpecialRegisterShelf specialRegisterShelf = new SpecialRegisterShelf();
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialRegisterShelfSaveOrupdateVO, specialRegisterShelf);
                specialRegisterShelfList.add(specialRegisterShelf);
            }
            specialRegisterDetail.setSpecialRegisterShelfList(specialRegisterShelfList);
            specialRegisterDetailList.add(specialRegisterDetail);

        }
        specialRegister.setSpecialRegisterDetailList(specialRegisterDetailList);
        if (specialRegister.getWholeDiscount().compareTo(BigDecimal.ZERO) == -1) {//整单折扣不能小于0
            throw new BusinessException("整单折扣不能小于0");
        }
        if (specialRegister.getWholeDiscountAmount().compareTo(BigDecimal.ZERO) == -1) {//整单优惠不能小于0
            throw new BusinessException("整单优惠不能小于0");
        }
        //以下是计算不含税金额与税额的（明细表）
        //定义不含税金额，与税额
        BigDecimal notaxRealAmountTotal = new BigDecimal(0);//不含税总额
        BigDecimal taxAmountTotal = new BigDecimal(0);//税额
        for (SpecialRegisterDetail specialRegisterDetail :
                specialRegisterDetailList) {//计算合计
            //根据数量、单价、行折扣获取金额（整单折扣金额）：数量*单价*行折扣 amount 有可能是0
            BigDecimal amount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(specialRegisterDetail.getQuantity(), specialRegisterDetail.getUnitPrice(), specialRegisterDetail.getLineDiscount());
            BigDecimal lineDiscountAmount = new BigDecimal(0);

            if (amount.compareTo(BigDecimal.ZERO) == 1) {//大于0
                lineDiscountAmount = specialRegister.getWholeDiscountAmount().multiply(amount.divide(amountTotal, 2, BigDecimal.ROUND_HALF_UP));
            }
            //计算实际金额，根据数量、单价、行折扣、整单折扣、行舍入获取实际金额：数量*单价*行折扣*整单折扣-行舍入
            BigDecimal realAmount = CalculateComponent.getRealAmountByQuantityAndPriceAndLineDiscount(specialRegisterDetail.getQuantity(), specialRegisterDetail.getUnitPrice(), specialRegisterDetail.getLineDiscount(), specialRegister.getWholeDiscount(), lineDiscountAmount);
            //计算实际单价   ：实际金额/数量
            BigDecimal realPrice = new BigDecimal(0);
            if (realPrice.compareTo(BigDecimal.ZERO) == 1) {//大于0
                realPrice = CalculateComponent.getRealPriceByRealAmountAndQuantity(realAmount, specialRegisterDetail.getQuantity());
            }
            //获取不含税金额
            BigDecimal val1 = new BigDecimal(1);
            BigDecimal notaxRealAmount = realAmount.divide(val1.add(specialRegisterDetail.getTaxRate().divide(new BigDecimal(100))), 2, BigDecimal.ROUND_HALF_UP);
            //计算不含税单价
            BigDecimal notaxRealPrice = new BigDecimal(0);
            if (notaxRealPrice.compareTo(BigDecimal.ZERO) == 1) {//如果不含税金额大于0的时候
                notaxRealPrice = CalculateComponent.getNotaxPriceByNotaxAmountAndQuantity(notaxRealAmount, specialRegisterDetail.getQuantity());
            }
            //获取税额   实际金额-不含税金额
            BigDecimal taxAmount = realAmount.subtract(notaxRealAmount);
            specialRegisterDetail.setNotaxRealAmount(notaxRealAmount);//不含税金额
            specialRegisterDetail.setNotaxRealPrice(notaxRealPrice);//不含税实际单价
            specialRegisterDetail.setRealAmount(realAmount);//实际金额
            specialRegisterDetail.setRealPrice(realPrice);//实际单价
            specialRegisterDetail.setTaxAmount(taxAmount);//税额
            specialRegisterDetail.setProfit(new BigDecimal(0));
            specialRegisterDetail.setNotaxProfit(new BigDecimal(0));
            specialRegisterDetail.setProfitRate(new BigDecimal(0));
            specialRegisterDetail.setNotaxProfitRate(new BigDecimal(0));
            specialRegisterDetail.setUnclearQuantity(specialRegisterDetail.getQuantity());
            specialRegisterDetail.setClearQuantity(new BigDecimal(0));
            //计算订单详情里面的金额
            specialRegisterDetail.setAmount(amount);
            //行优惠（行舍入）优惠分摊    计算优惠分摊
            specialRegisterDetail.setWholeDiscount(specialRegister.getWholeDiscount());//整单折扣
            specialRegisterDetail.setLineDiscountAmount(lineDiscountAmount);

            notaxRealAmountTotal = notaxRealAmountTotal.add(notaxRealAmount);//不含税金额总额
            taxAmountTotal = taxAmountTotal.add(taxAmount);
        }
        //实际金额合计
        BigDecimal realAmountTotal = amountTotal.multiply(specialRegister.getWholeDiscount()).subtract(specialRegister.getWholeDiscountAmount());
        //不含税总额
        //税额
        specialRegister.setAmountTotal(amountTotal);//总额（优惠前金额合计）
        specialRegister.setRealAmountTotal(realAmountTotal);//实际金额合计
        specialRegister.setNotaxRealAmountTotal(notaxRealAmountTotal);
        specialRegister.setTaxAmountTotal(taxAmountTotal);
        specialRegister.setQuantityTotal(quantityTotal);
        specialRegister.setVarietiesQuantity(specialRegisterDetailList.size());
        //利润相关暂时不计算
        specialRegister.setProfitTotal(new BigDecimal(0));
        specialRegister.setNotaxProfitTotal(new BigDecimal(0));
        specialRegister.setProfitRate(new BigDecimal(0));
        specialRegister.setNotaxProfitRate(new BigDecimal(0));
        //插入主表数据
        specialRegisterMapper.insertSelective(specialRegister);
        Long specialRegisterId = specialRegister.getId();
        for (SpecialRegisterDetail specialRegisterDetail :
                specialRegisterDetailList) {
            specialRegisterDetail.setTotalQuantity(specialRegister.getQuantityTotal());
            specialRegisterDetail.setRegisterId(specialRegisterId);
            specialRegisterDetail.setRegisterCode(specialRegister.getCode());
            specialRegisterDetail.setRegisterDate(new Date());
            specialRegisterDetailMapper.insertSelective(specialRegisterDetail);
            Long registerDetailId = specialRegisterDetail.getId();
            List<SpecialRegisterShelf> specialRegisterShelfList = specialRegisterDetail.getSpecialRegisterShelfList();
            for (SpecialRegisterShelf specialRegisterShelf :
                    specialRegisterShelfList) {
                //填充登记单明细信息
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialRegisterDetail, specialRegisterShelf);
                if (specialRegisterShelf.getQuantity().compareTo(BigDecimal.ONE) == -1) {
                    throw new BusinessException("货位数量不能小于1");
                }
                //根据货位id查询货位信息
                GoodsInfoStockShelfVO goodsInfoStockShelfVO = stockMapper.getGoodsStockInfoByGoodsIdAndShelfId(
                        userVO.getEnterpriseId(), specialRegisterShelf.getGoodsId(),
                        specialRegisterShelf.getShelfId(),specialRegisterShelf.getLotId());
                if (goodsInfoStockShelfVO == null) {
                    throw new BusinessException("货位信息不存在,请检查货位ID=" + specialRegisterShelf.getShelfId());
                }
                specialRegisterShelf.setProductDate(goodsInfoStockShelfVO.getProductDate());
                specialRegisterShelf.setValidDate(goodsInfoStockShelfVO.getValidUtil());
                specialRegisterShelf.setShelfName(goodsInfoStockShelfVO.getShelfName());
                specialRegisterShelf.setLotNumber(goodsInfoStockShelfVO.getLotNum());
                specialRegisterShelf.setRegisterDtlId(registerDetailId);
                specialRegisterShelfMapper.insertSelective(specialRegisterShelf);
            }

        }
        return 1;
    }

    @Override
    public SpecialRegister calculate(SpecialRegisterSaveOrupdateVO specialRegisterVo, UserVO userVO) throws Exception {
        SpecialRegister specialRegister = (SpecialRegister) EntityUtils.reflectAddSetDefaultValue(new SpecialRegister().getClass(), userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialRegisterVo, specialRegister);
        //设置登记单明细信息
        List<SpecialRegisterDetailSaveOrupdateVO> specialRegisterDetailSaveOrupdateVOList = specialRegisterVo.getSpecialRegisterDetailSaveOrupdateVOList();
        BigDecimal amountTotal = new BigDecimal(0);//金额合计
        BigDecimal quantityTotal = new BigDecimal(0);//数量合计
        List<SpecialRegisterDetail> specialRegisterDetailList = new ArrayList<>();
        for (SpecialRegisterDetailSaveOrupdateVO specialRegisterDetailSaveOrupdateVO :
                specialRegisterDetailSaveOrupdateVOList) {
            SpecialRegisterDetail specialRegisterDetail = new SpecialRegisterDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialRegisterDetailSaveOrupdateVO, specialRegisterDetail);

            long gid = specialRegisterDetail.getGoodsId();
            //查询药品信息
            GoodsVO goods = goodsMapper.selectGoodsInfoById(gid);
            if (goods == null) {
                throw new BusinessException("没有该商品，请核实商品ID=" + gid);
            }
            //填充药品信息
            this.fillGoodsInfo(goods, specialRegisterDetail);
            //填充登记单信息
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialRegister, specialRegisterDetail);

            if (specialRegisterDetail.getUnitPrice().compareTo(BigDecimal.ZERO) == -1) {//如果单价小于0
                throw new BusinessException("单价不能小于0");
            } else if (specialRegisterDetail.getUnitPrice().compareTo(BigDecimal.ZERO) == 0) {//如果单价等于0
                throw new BusinessException("单价不能等于0");
            }
            if (specialRegisterDetail.getQuantity().compareTo(BigDecimal.ZERO) == -1) {//如果数量小于0
                throw new BusinessException("数量不能小于0");
            }
            if (specialRegisterDetail.getLineDiscount().compareTo(BigDecimal.ZERO) == -1) {//如果折扣小于0
                throw new BusinessException("折扣不能小于0");
            } else if (specialRegisterDetail.getLineDiscount().compareTo(BigDecimal.ZERO) == 0) {//如果折扣等于0
                throw new BusinessException("折扣不能等于0");
            }
            if (specialRegisterDetail.getTaxRateId() == null) {
                throw new BusinessException("税率ID不能为空");
            } else {
                GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(specialRegisterDetail.getTaxRateId());
                if (goodsTaxRate == null) {
                    throw new BusinessException("没有该税率值，请核实税率ID");
                } else {
                    specialRegisterDetail.setTaxRate(goodsTaxRate.getTaxRate());
                }

            }
            //计算金额合计
            BigDecimal amount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(specialRegisterDetail.getQuantity(), specialRegisterDetail.getUnitPrice(), specialRegisterDetail.getLineDiscount());
            amountTotal = amountTotal.add(amount);
            //药品数量合计
            quantityTotal = quantityTotal.add(specialRegisterDetail.getQuantity());
            //封装装货位信息
            List<SpecialRegisterShelf> specialRegisterShelfList = new ArrayList<>();
            for (SpecialRegisterShelfSaveOrupdateVO specialRegisterShelfSaveOrupdateVO : specialRegisterDetailSaveOrupdateVO.getSpecialRegisterShelfSaveOrupdateVOList()) {
                SpecialRegisterShelf specialRegisterShelf = new SpecialRegisterShelf();
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialRegisterShelfSaveOrupdateVO, specialRegisterShelf);
                specialRegisterShelfList.add(specialRegisterShelf);
            }
            specialRegisterDetail.setSpecialRegisterShelfList(specialRegisterShelfList);
            specialRegisterDetailList.add(specialRegisterDetail);

        }
        specialRegister.setSpecialRegisterDetailList(specialRegisterDetailList);
        if (specialRegister.getWholeDiscount().compareTo(BigDecimal.ZERO) == -1) {//整单折扣不能小于0
            throw new BusinessException("整单折扣不能小于0");
        }
        if (specialRegister.getWholeDiscountAmount().compareTo(BigDecimal.ZERO) == -1) {//整单优惠不能小于0
            throw new BusinessException("整单优惠不能小于0");
        }
        //以下是计算不含税金额与税额的（明细表）
        //定义不含税金额，与税额
        BigDecimal notaxRealAmountTotal = new BigDecimal(0);//不含税总额
        BigDecimal taxAmountTotal = new BigDecimal(0);//税额
        for (SpecialRegisterDetail specialRegisterDetail :
                specialRegisterDetailList) {//计算合计
            //根据数量、单价、行折扣获取金额（整单折扣金额）：数量*单价*行折扣 amount 有可能是0
            BigDecimal amount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(specialRegisterDetail.getQuantity(), specialRegisterDetail.getUnitPrice(), specialRegisterDetail.getLineDiscount());
            BigDecimal lineDiscountAmount = new BigDecimal(0);

            if (amount.compareTo(BigDecimal.ZERO) == 1) {//大于0
                lineDiscountAmount = specialRegister.getWholeDiscountAmount().multiply(amount.divide(amountTotal, 2, BigDecimal.ROUND_HALF_UP));
            }
            //计算实际金额，根据数量、单价、行折扣、整单折扣、行舍入获取实际金额：数量*单价*行折扣*整单折扣-行舍入
            BigDecimal realAmount = CalculateComponent.getRealAmountByQuantityAndPriceAndLineDiscount(specialRegisterDetail.getQuantity(), specialRegisterDetail.getUnitPrice(), specialRegisterDetail.getLineDiscount(), specialRegister.getWholeDiscount(), lineDiscountAmount);
            //计算实际单价   ：实际金额/数量
            BigDecimal realPrice = new BigDecimal(0);
            if (realAmount.compareTo(BigDecimal.ZERO) == 1) {//大于0
                realPrice = CalculateComponent.getRealPriceByRealAmountAndQuantity(realAmount, specialRegisterDetail.getQuantity());
            }
            //获取不含税金额
            BigDecimal val1 = new BigDecimal(1);
            BigDecimal notaxRealAmount = realAmount.divide(val1.add(specialRegisterDetail.getTaxRate().divide(new BigDecimal(100))), 2, BigDecimal.ROUND_HALF_UP);
            //计算不含税单价
            BigDecimal notaxRealPrice = new BigDecimal(0);
            if (notaxRealAmount.compareTo(BigDecimal.ZERO) == 1) {//如果不含税金额大于0的时候
                notaxRealPrice = CalculateComponent.getNotaxPriceByNotaxAmountAndQuantity(notaxRealAmount, specialRegisterDetail.getQuantity());
            }
            //获取税额   实际金额-不含税金额
            BigDecimal taxAmount = realAmount.subtract(notaxRealAmount);
            specialRegisterDetail.setNotaxRealAmount(notaxRealAmount);//不含税金额
            specialRegisterDetail.setNotaxRealPrice(notaxRealPrice);//不含税实际单价
            specialRegisterDetail.setRealAmount(realAmount);//实际金额
            specialRegisterDetail.setRealPrice(realPrice);//实际单价
            specialRegisterDetail.setTaxAmount(taxAmount);//税额
            specialRegisterDetail.setProfit(new BigDecimal(0));
            specialRegisterDetail.setNotaxProfit(new BigDecimal(0));
            specialRegisterDetail.setProfitRate(new BigDecimal(0));
            specialRegisterDetail.setNotaxProfitRate(new BigDecimal(0));
            specialRegisterDetail.setUnclearQuantity(specialRegisterDetail.getQuantity());
            specialRegisterDetail.setClearQuantity(new BigDecimal(0));
            //计算订单详情里面的金额
            specialRegisterDetail.setAmount(amount);
            //行优惠（行舍入）优惠分摊    计算优惠分摊
            specialRegisterDetail.setWholeDiscount(specialRegister.getWholeDiscount());//整单折扣
            specialRegisterDetail.setLineDiscountAmount(lineDiscountAmount);

            notaxRealAmountTotal = notaxRealAmountTotal.add(notaxRealAmount);//不含税金额总额
            taxAmountTotal = taxAmountTotal.add(taxAmount);
        }
        //实际金额合计
        BigDecimal realAmountTotal = amountTotal.multiply(specialRegister.getWholeDiscount()).subtract(specialRegister.getWholeDiscountAmount());
        //不含税总额
        //税额
        specialRegister.setAmountTotal(amountTotal);//总额（优惠前金额合计）
        specialRegister.setRealAmountTotal(realAmountTotal);//实际金额合计
        specialRegister.setNotaxRealAmountTotal(notaxRealAmountTotal);
        specialRegister.setTaxAmountTotal(taxAmountTotal);
        specialRegister.setQuantityTotal(quantityTotal);
        specialRegister.setVarietiesQuantity(specialRegisterDetailList.size());
        //利润相关暂时不计算
        specialRegister.setProfitTotal(new BigDecimal(0));
        specialRegister.setNotaxProfitTotal(new BigDecimal(0));
        specialRegister.setProfitRate(new BigDecimal(0));
        specialRegister.setNotaxProfitRate(new BigDecimal(0));
        Long specialRegisterId = specialRegister.getId();
        for (SpecialRegisterDetail specialRegisterDetail :
                specialRegisterDetailList) {
            specialRegisterDetail.setTotalQuantity(specialRegister.getQuantityTotal());
            specialRegisterDetail.setRegisterId(specialRegisterId);
            specialRegisterDetail.setRegisterCode(specialRegister.getCode());
            specialRegisterDetail.setRegisterDate(new Date());
            List<SpecialRegisterShelf> specialRegisterShelfList = specialRegisterDetail.getSpecialRegisterShelfList();
            for (SpecialRegisterShelf specialRegisterShelf :
                    specialRegisterShelfList) {
                //填充登记单明细信息
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialRegisterDetail, specialRegisterShelf);
                if (specialRegisterShelf.getQuantity().compareTo(BigDecimal.ONE) == -1) {
                    throw new BusinessException("货位数量不能小于1");
                }
                //根据货位id查询货位信息
                GoodsInfoStockShelfVO goodsInfoStockShelfVO = stockMapper.getGoodsStockInfoByGoodsIdAndShelfId(
                        userVO.getEnterpriseId(), specialRegisterShelf.getGoodsId(),
                        specialRegisterShelf.getShelfId(),specialRegisterShelf.getLotId());
                if (goodsInfoStockShelfVO == null) {
                    throw new BusinessException("货位信息不存在,请检查货位ID=" + specialRegisterShelf.getShelfId());
                }
                specialRegisterShelf.setProductDate(goodsInfoStockShelfVO.getProductDate());
                specialRegisterShelf.setValidDate(goodsInfoStockShelfVO.getValidUtil());
                specialRegisterShelf.setShelfName(goodsInfoStockShelfVO.getShelfName());
                specialRegisterShelf.setLotNumber(goodsInfoStockShelfVO.getLotNum());
            }
            specialRegisterDetail.setSpecialRegisterShelfList(specialRegisterShelfList);

        }
        specialRegister.setSpecialRegisterDetailList(specialRegisterDetailList);
        return specialRegister;
    }

    /**
     * <专管登记单明细填充药品基础信息>
     *
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/23 16:38
     */
    private void fillGoodsInfo(GoodsVO goods, SpecialRegisterDetail specialRegisterDetail) {
        specialRegisterDetail.setGoodsId(goods.getId());
        specialRegisterDetail.setGoodsCode(goods.getCode());
        specialRegisterDetail.setBarcode(goods.getBarcode());
        specialRegisterDetail.setGoodsName(goods.getName());
        specialRegisterDetail.setGoodsGenericName(goods.getGenericName());
        specialRegisterDetail.setDosageId(goods.getDosageId());
        specialRegisterDetail.setDosageName(goods.getDosageName());
        specialRegisterDetail.setUnitId(goods.getUnitId());
        specialRegisterDetail.setUnitName(goods.getUnitName());
        specialRegisterDetail.setGoodsSpecification(goods.getSpecification());
        specialRegisterDetail.setManufacturerId(goods.getManufacturerId());
        specialRegisterDetail.setManufacturer(goods.getManufacturer());
        specialRegisterDetail.setGoodsPlace(goods.getPlace());
        specialRegisterDetail.setApprovalNumber(goods.getApprovalNumber());

    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int update(SpecialRegisterSaveOrupdateVO specialRegisterVo, UserVO userVO) throws Exception {
        SpecialRegister specialRegister = (SpecialRegister) EntityUtils.reflectAddSetDefaultValue(new SpecialRegister().getClass(), userVO);
        if (specialRegisterVo.getId() == null) {
            throw new BusinessException("更新失败,数据不存在");
        }

        SpecialRegister sr = this.getRegisterDataById(specialRegisterVo.getId());
        if (sr == null) {
            throw new BusinessException("更新失败,数据不存在");
        }

        if (sr.getStatus().intValue() != PurchaseStatus.WAIT_PAY.getStatus().intValue()) {
            throw new BusinessException("更新失败,登记单已完成无法修改");
        }

        //封装主表信息
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialRegisterVo, specialRegister);
        specialRegister.setOrderType(sr.getOrderType());
        specialRegister.setStatus(sr.getStatus());
        specialRegister.setCode(sr.getCode());
        Long memberId = specialRegister.getMemberId();
        if (memberId != null) {
            //查询会员信息
            MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(memberId);
            if (memberInfo != null) {
                specialRegister.setMemberCardCode(memberInfo.getCardCode());
                specialRegister.setMemberName(memberInfo.getMemberName());
            }
        }
        //设置登记单明细信息
        List<SpecialRegisterDetailSaveOrupdateVO> specialRegisterDetailSaveOrupdateVOList = specialRegisterVo.getSpecialRegisterDetailSaveOrupdateVOList();
        BigDecimal amountTotal = new BigDecimal(0);//金额合计
        BigDecimal quantityTotal = new BigDecimal(0);//数量合计
        List<SpecialRegisterDetail> specialRegisterDetailList = new ArrayList<>();
        for (SpecialRegisterDetailSaveOrupdateVO specialRegisterDetailSaveOrupdateVO :
                specialRegisterDetailSaveOrupdateVOList) {
            SpecialRegisterDetail specialRegisterDetail = new SpecialRegisterDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialRegisterDetailSaveOrupdateVO, specialRegisterDetail);

            long gid = specialRegisterDetail.getGoodsId();
            //查询药品信息
            GoodsVO goods = goodsMapper.selectGoodsInfoById(gid);
            if (goods == null) {
                throw new BusinessException("没有该商品，请核实商品ID=" + gid);
            }
            //填充药品信息
            this.fillGoodsInfo(goods, specialRegisterDetail);
            //填充登记单信息
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialRegister, specialRegisterDetail);

            if (specialRegisterDetail.getUnitPrice().compareTo(BigDecimal.ZERO) == -1) {//如果单价小于0
                throw new BusinessException("单价不能小于0");
            } else if (specialRegisterDetail.getUnitPrice().compareTo(BigDecimal.ZERO) == 0) {//如果单价等于0
                throw new BusinessException("单价不能等于0");
            }
            if (specialRegisterDetail.getQuantity().compareTo(BigDecimal.ZERO) == -1) {//如果数量小于0
                throw new BusinessException("数量不能小于0");
            }
            if (specialRegisterDetail.getLineDiscount().compareTo(BigDecimal.ZERO) == -1) {//如果折扣小于0
                throw new BusinessException("折扣不能小于0");
            } else if (specialRegisterDetail.getLineDiscount().compareTo(BigDecimal.ZERO) == 0) {//如果折扣等于0
                throw new BusinessException("折扣不能等于0");
            }
            if (specialRegisterDetail.getTaxRateId() == null) {
                throw new BusinessException("税率ID不能为空");
            } else {
                GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(specialRegisterDetail.getTaxRateId());
                if (goodsTaxRate == null) {
                    throw new BusinessException("没有该税率值，请核实税率ID");
                } else {
                    specialRegisterDetail.setTaxRate(goodsTaxRate.getTaxRate());
                }

            }
            //计算金额合计
            BigDecimal amount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(specialRegisterDetail.getQuantity(), specialRegisterDetail.getUnitPrice(), specialRegisterDetail.getLineDiscount());
            amountTotal = amountTotal.add(amount);
            //药品数量合计
            quantityTotal = quantityTotal.add(specialRegisterDetail.getQuantity());
            //封装装货位信息
            List<SpecialRegisterShelf> specialRegisterShelfList = new ArrayList<>();
            for (SpecialRegisterShelfSaveOrupdateVO specialRegisterShelfSaveOrupdateVO : specialRegisterDetailSaveOrupdateVO.getSpecialRegisterShelfSaveOrupdateVOList()) {
                SpecialRegisterShelf specialRegisterShelf = new SpecialRegisterShelf();
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialRegisterShelfSaveOrupdateVO, specialRegisterShelf);
                specialRegisterShelfList.add(specialRegisterShelf);
            }
            specialRegisterDetail.setSpecialRegisterShelfList(specialRegisterShelfList);
            specialRegisterDetailList.add(specialRegisterDetail);

        }
        specialRegister.setSpecialRegisterDetailList(specialRegisterDetailList);
        if (specialRegister.getWholeDiscount().compareTo(BigDecimal.ZERO) == -1) {//整单折扣不能小于0
            throw new BusinessException("整单折扣不能小于0");
        }
        if (specialRegister.getWholeDiscountAmount().compareTo(BigDecimal.ZERO) == -1) {//整单优惠不能小于0
            throw new BusinessException("整单优惠不能小于0");
        }
        //以下是计算不含税金额与税额的（明细表）
        //定义不含税金额，与税额
        BigDecimal notaxRealAmountTotal = new BigDecimal(0);//不含税总额
        BigDecimal taxAmountTotal = new BigDecimal(0);//税额
        for (SpecialRegisterDetail specialRegisterDetail :
                specialRegisterDetailList) {//计算合计
            //根据数量、单价、行折扣获取金额（整单折扣金额）：数量*单价*行折扣 amount 有可能是0
            BigDecimal amount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(specialRegisterDetail.getQuantity(), specialRegisterDetail.getUnitPrice(), specialRegisterDetail.getLineDiscount());
            BigDecimal lineDiscountAmount = new BigDecimal(0);

            if (amount.compareTo(BigDecimal.ZERO) == 1) {//大于0
                lineDiscountAmount = specialRegister.getWholeDiscountAmount().multiply(amount.divide(amountTotal, 2, BigDecimal.ROUND_HALF_UP));
            }
            //计算实际金额，根据数量、单价、行折扣、整单折扣、行舍入获取实际金额：数量*单价*行折扣*整单折扣-行舍入
            BigDecimal realAmount = CalculateComponent.getRealAmountByQuantityAndPriceAndLineDiscount(specialRegisterDetail.getQuantity(), specialRegisterDetail.getUnitPrice(), specialRegisterDetail.getLineDiscount(), specialRegister.getWholeDiscount(), lineDiscountAmount);
            //计算实际单价   ：实际金额/数量
            BigDecimal realPrice = new BigDecimal(0);
            if (realPrice.compareTo(BigDecimal.ZERO) == 1) {//大于0
                realPrice = CalculateComponent.getRealPriceByRealAmountAndQuantity(realAmount, specialRegisterDetail.getQuantity());
            }
            //获取不含税金额
            BigDecimal val1 = new BigDecimal(1);
            BigDecimal notaxRealAmount = realAmount.divide(val1.add(specialRegisterDetail.getTaxRate().divide(new BigDecimal(100))), 2, BigDecimal.ROUND_HALF_UP);
            //计算不含税单价
            BigDecimal notaxRealPrice = new BigDecimal(0);
            if (notaxRealPrice.compareTo(BigDecimal.ZERO) == 1) {//如果不含税金额大于0的时候
                notaxRealPrice = CalculateComponent.getNotaxPriceByNotaxAmountAndQuantity(notaxRealAmount, specialRegisterDetail.getQuantity());
            }
            //获取税额   实际金额-不含税金额
            BigDecimal taxAmount = realAmount.subtract(notaxRealAmount);
            specialRegisterDetail.setNotaxRealAmount(notaxRealAmount);//不含税金额
            specialRegisterDetail.setNotaxRealPrice(notaxRealPrice);//不含税实际单价
            specialRegisterDetail.setRealAmount(realAmount);//实际金额
            specialRegisterDetail.setRealPrice(realPrice);//实际单价
            specialRegisterDetail.setTaxAmount(taxAmount);//税额
            specialRegisterDetail.setProfit(new BigDecimal(0));
            specialRegisterDetail.setNotaxProfit(new BigDecimal(0));
            specialRegisterDetail.setProfitRate(new BigDecimal(0));
            specialRegisterDetail.setNotaxProfitRate(new BigDecimal(0));
            specialRegisterDetail.setUnclearQuantity(specialRegisterDetail.getQuantity());
            specialRegisterDetail.setClearQuantity(new BigDecimal(0));
            //计算订单详情里面的金额
            specialRegisterDetail.setAmount(amount);
            //行优惠（行舍入）优惠分摊    计算优惠分摊
            specialRegisterDetail.setWholeDiscount(specialRegister.getWholeDiscount());//整单折扣
            specialRegisterDetail.setLineDiscountAmount(lineDiscountAmount);

            notaxRealAmountTotal = notaxRealAmountTotal.add(notaxRealAmount);//不含税金额总额
            taxAmountTotal = taxAmountTotal.add(taxAmount);
        }
        //实际金额合计
        BigDecimal realAmountTotal = amountTotal.multiply(specialRegister.getWholeDiscount()).subtract(specialRegister.getWholeDiscountAmount());
        //不含税总额
        //税额
        specialRegister.setAmountTotal(amountTotal);//总额（优惠前金额合计）
        specialRegister.setRealAmountTotal(realAmountTotal);//实际金额合计
        specialRegister.setNotaxRealAmountTotal(notaxRealAmountTotal);
        specialRegister.setTaxAmountTotal(taxAmountTotal);
        specialRegister.setQuantityTotal(quantityTotal);
        specialRegister.setVarietiesQuantity(specialRegisterDetailList.size());
        //利润相关暂时不计算
        specialRegister.setProfitTotal(new BigDecimal(0));
        specialRegister.setNotaxProfitTotal(new BigDecimal(0));
        specialRegister.setProfitRate(new BigDecimal(0));
        specialRegister.setNotaxProfitRate(new BigDecimal(0));
        //更新主表数据
        specialRegisterMapper.updateByPrimaryKeySelective(specialRegister);
        //删除相关子表数据
        specialRegisterDetailMapper.deleteByRegisterId(specialRegister.getId());
        specialRegisterShelfMapper.deleteByRegisterId(specialRegister.getId());
        //组装登记单明细信息
        Long specialRegisterId = specialRegister.getId();
        for (SpecialRegisterDetail specialRegisterDetail :
                specialRegisterDetailList) {
            specialRegisterDetail.setTotalQuantity(specialRegister.getQuantityTotal());
            specialRegisterDetail.setRegisterId(specialRegisterId);
            specialRegisterDetail.setRegisterCode(specialRegister.getCode());
            specialRegisterDetail.setRegisterDate(new Date());
            specialRegisterDetailMapper.insertSelective(specialRegisterDetail);
            Long registerDetailId = specialRegisterDetail.getId();
            List<SpecialRegisterShelf> specialRegisterShelfList = specialRegisterDetail.getSpecialRegisterShelfList();
            for (SpecialRegisterShelf specialRegisterShelf :
                    specialRegisterShelfList) {
                //填充登记单明细信息
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialRegisterDetail, specialRegisterShelf);
                if (specialRegisterShelf.getQuantity().compareTo(BigDecimal.ONE) == -1) {
                    throw new BusinessException("货位数量不能小于1");
                }
                //根据货位id查询货位信息
                GoodsInfoStockShelfVO goodsInfoStockShelfVO = stockMapper.getGoodsStockInfoByGoodsIdAndShelfId(
                        userVO.getEnterpriseId(), specialRegisterShelf.getGoodsId(),
                        specialRegisterShelf.getShelfId(),specialRegisterShelf.getLotId());
                if (goodsInfoStockShelfVO == null) {
                    throw new BusinessException("货位信息不存在,请检查货位ID=" + specialRegisterShelf.getShelfId());
                }
                specialRegisterShelf.setProductDate(goodsInfoStockShelfVO.getProductDate());
                specialRegisterShelf.setValidDate(goodsInfoStockShelfVO.getValidUtil());
                specialRegisterShelf.setShelfName(goodsInfoStockShelfVO.getShelfName());
                specialRegisterShelf.setLotId(goodsInfoStockShelfVO.getLotNumberId());
                specialRegisterShelf.setLotNumber(goodsInfoStockShelfVO.getLotNum());
                specialRegisterShelf.setRegisterDtlId(registerDetailId);
                specialRegisterShelfMapper.insertSelective(specialRegisterShelf);
            }

        }
        return 1;
    }


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void fillByOrder(Long saleId, Long registerId, UserVO userVO) {
        SpecialRegister specialRegister = this.getRegisterDataById(registerId);
        if (specialRegister == null) {
            throw new BusinessException("回填失败,登记单不存在");
        }
        if (specialRegister.getStatus().intValue() != PurchaseStatus.WAIT_PAY.getStatus().intValue()) {
            throw new BusinessException("回填失败,登记单已完成");
        }
        Sale sale = this.getSaleById(saleId);
        if (sale == null || sale.getSaleType() != 0) {
            throw new BusinessException("回填失败,销售单不存在");
        }
        specialRegister.setBaseOrderId(sale.getId());
        specialRegister.setBaseOrderCode(sale.getCode());
        specialRegister.setBaseOrderDate(sale.getSaleDate());
        specialRegister.setBaseOrderType(sale.getOrderType());
        specialRegister.setModifierCode(userVO.getUserCode());
        specialRegister.setModifierId(userVO.getUserId());
        specialRegister.setModifierName(userVO.getUserName());
        specialRegister.setUpdateTime(new Date());
        specialRegister.setStatus(PurchaseStatus.FINISHED.getStatus());
        specialRegisterMapper.updateByPrimaryKeySelective(specialRegister);
    }

    /**
     * <根据id查询销售单>
     *
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/9/25 15:52
     */
    private Sale getSaleById(Long saleId) {
        return saleMapper.selectByPrimaryKey(saleId);
    }

    @Override
    public int delete(Long id) throws Exception {
        return specialRegisterMapper.deleteByPrimaryKey(id);
    }

    @Override
    public SpecialRegister getRegisterAndDetailDataById(Long id) {
        SpecialRegister specialRegister = specialRegisterMapper.selectByPrimaryKey(id);
        if (specialRegister == null) {
            return null;
        }
        List<SpecialRegisterDetail> specialRegisterDetailList = specialRegisterDetailMapper.listSpecialRegisterDetailByRegisterId(specialRegister.getId());
        specialRegister.setSpecialRegisterDetailList(specialRegisterDetailList);
        for (SpecialRegisterDetail specialRegisterDetail :
                specialRegisterDetailList) {
            List<SpecialRegisterShelf> specialRegisterShelfList = specialRegisterShelfMapper.listSpecialRegisterShelfByRegisterDetailId(specialRegisterDetail.getId());
            specialRegisterShelfList.forEach(item->{
                Map<String,Object> warehouseShelf = warehouseShelfMapper.getShelfInfoById(item.getShelfId());
                if(warehouseShelf != null){
                    item.setShelfName(StringUtil.transferTrimStr(warehouseShelf.get("name")));
                    Object jobArea = warehouseShelf.get("jobArea");
                    if(jobArea != null){
                        item.setShelfStatusDesc(ShelfStatus.getName((Integer) jobArea));
                    }
                }

            });
            specialRegisterDetail.setSpecialRegisterShelfList(specialRegisterShelfList);
        }
        return specialRegister;
    }

    @Override
    public void listUnRegisterSale(Page<List<Sale>> page, RequestSaleVo requestSaleVo) {
        requestSaleVo.setPageNo(page.getStart());
        int count = saleMapper.countUnRegisterSale(requestSaleVo);
        List<Sale> list = saleMapper.listUnRegisterSale(requestSaleVo);
        page.setTotalRecord(count);
        page.setResult(list);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public SpecialRegister registeSale(RequestSaleRegisteVo requestSaleRegisteVo, UserVO userVO) throws Exception {
        Sale sale = saleMapper.selectByPrimaryKey(requestSaleRegisteVo.getSaleId());
        if (sale == null || sale.getSaleType() != 0) {
            throw new BusinessException("补登失败,销售单不存在");
        }
        //根据销售单id查询登记单
        SpecialRegister sr = specialRegisterMapper.getSpecialRegisterBySaleId(requestSaleRegisteVo.getSaleId());
        if (sr != null) {
            throw new BusinessException("补登失败,此销售单已登记");
        }
        SpecialRegister specialRegister = new SpecialRegister();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(sale, specialRegister);
        specialRegister.setCode(orderCodeComponent.generate(OrderRule.SPECIAL_REGISTER.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode()));
        specialRegister.setOrderType(OrderRule.SPECIAL_REGISTER.getType());
        specialRegister.setStatus(PurchaseStatus.FINISHED.getStatus());
        specialRegister.setRegisterDate(new Date());
        specialRegister.setRegisterManCode(userVO.getUserCode());
        specialRegister.setRegisterManId(userVO.getUserId());
        specialRegister.setRegisterManName(userVO.getUserName());
        Long memberId = sale.getMemberId();
        if (memberId != null) {
            //查询会员信息
            MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(memberId);
            if (memberInfo != null) {
                specialRegister.setMemberCardCode(memberInfo.getCardCode());
                specialRegister.setMemberName(memberInfo.getMemberName());
            }
        }
        List<SaleDetail> saleDetailList = saleDetailMapper.getSaleDetailBySaleId(sale.getId());
        if (saleDetailList.isEmpty()) {
            throw new BusinessException("销售单数据异常,销售单无明细信息");
        }
        BigDecimal wholeDiscountAmount = new BigDecimal(0);
        BigDecimal realAmountTotal = new BigDecimal(0);
        BigDecimal notaxRealAmountTotal = new BigDecimal(0);
        BigDecimal taxAmountTotal = new BigDecimal(0);
        BigDecimal amountTotal = new BigDecimal(0);
        BigDecimal quantityTotal = new BigDecimal(0);
        List<SpecialRegisterDetail> specialRegisterDetailList = new ArrayList<>();
        for (int i = 0; i < saleDetailList.size(); i++) {
            //封装登记单明细数据对象
            SaleDetail saleDetail = saleDetailList.get(i);
            SpecialRegisterDetail specialRegisterDetail = new SpecialRegisterDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saleDetail, specialRegisterDetail);
            specialRegisterDetail.setTaxRateId(saleDetail.getTaxId());
            specialRegisterDetail.setRegisterDate(new Date());
            specialRegisterDetail.setRegisterCode(specialRegister.getCode());
            specialRegisterDetail.setLineNum(i);
            specialRegisterDetail.setCreaterCode(userVO.getUserCode());
            specialRegisterDetail.setCreaterId(userVO.getUserId());
            specialRegisterDetail.setCreaterName(userVO.getUserName());
            specialRegisterDetail.setCreateTime(new Date());
            specialRegisterDetail.setModifierCode(userVO.getUserCode());
            specialRegisterDetail.setModifierId(userVO.getUserId());
            specialRegisterDetail.setModifierName(userVO.getUserName());
            specialRegisterDetail.setUpdateTime(new Date());
            wholeDiscountAmount = wholeDiscountAmount.add(specialRegisterDetail.getLineDiscountAmount());
            realAmountTotal = realAmountTotal.add(specialRegisterDetail.getRealAmount());
            notaxRealAmountTotal = notaxRealAmountTotal.add(specialRegisterDetail.getNotaxRealAmount());
            taxAmountTotal = taxAmountTotal.add(specialRegisterDetail.getTaxAmount());
            amountTotal = amountTotal.add(specialRegisterDetail.getAmount());
            quantityTotal = quantityTotal.add(specialRegisterDetail.getQuantity());
            //封装货位明细对象
            List<SpecialRegisterShelf> specialRegisterShelfList = new ArrayList<>();
            List<SaleShelf> saleShelfList = saleShelfMapper.getSaleShelfByDetailId(saleDetail.getId());
            if (saleShelfList.isEmpty()) {
                throw new BusinessException("销售单数据异常,销售单明细无货位信息");
            }
            for (int j = 0; j < saleShelfList.size(); j++) {
                SaleShelf saleShelf = saleShelfList.get(j);
                SpecialRegisterShelf specialRegisterShelf = new SpecialRegisterShelf();
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saleShelf, specialRegisterShelf);
                specialRegisterShelf.setModifierCode(userVO.getUserCode());
                specialRegisterShelf.setModifierId(userVO.getUserId());
                specialRegisterShelf.setModifierName(userVO.getUserName());
                specialRegisterShelf.setCreaterCode(userVO.getUserCode());
                specialRegisterShelf.setCreaterId(userVO.getUserId());
                specialRegisterShelf.setCreaterName(userVO.getUserName());
                specialRegisterShelf.setCreateTime(new Date());
                specialRegisterShelf.setUpdateTime(new Date());
                specialRegisterShelf.setLotId(saleShelf.getLotId());
                specialRegisterShelfList.add(specialRegisterShelf);
            }
            specialRegisterDetail.setSpecialRegisterShelfList(specialRegisterShelfList);
            specialRegisterDetailList.add(specialRegisterDetail);
        }
        specialRegister.setWholeDiscountAmount(wholeDiscountAmount);
        specialRegister.setRealAmountTotal(realAmountTotal);
        specialRegister.setNotaxRealAmountTotal(notaxRealAmountTotal);
        specialRegister.setTaxAmountTotal(taxAmountTotal);
        specialRegister.setAmountTotal(amountTotal);
        specialRegister.setQuantityTotal(quantityTotal);
        specialRegister.setVarietiesQuantity(saleDetailList.size());
        for (int i = 0; i < specialRegisterDetailList.size(); i++) {
            SpecialRegisterDetail specialRegisterDetail = specialRegisterDetailList.get(i);
            List<SpecialRegisterShelf> specialRegisterShelfList = specialRegisterDetail.getSpecialRegisterShelfList();
            for (int j = 0; j < specialRegisterShelfList.size(); j++) {
                SpecialRegisterShelf specialRegisterShelf = specialRegisterShelfList.get(j);
                specialRegisterShelf.setRegisterId(specialRegisterDetail.getRegisterId());
                specialRegisterShelf.setTotalQuantity(specialRegisterDetail.getTotalQuantity());
            }
        }
        specialRegister.setSpecialRegisterDetailList(specialRegisterDetailList);
        return  specialRegister;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int cancelRegister(UserVO userVO, Long rid) {
        SpecialRegister specialRegister = new SpecialRegister();
        specialRegister.setModifierName(userVO.getUserName());
        specialRegister.setModifierId(userVO.getUserId());
        specialRegister.setModifierCode(userVO.getUserCode());
        specialRegister.setUpdateTime(new Date());
        specialRegister.setId(rid);
        specialRegister.setStatus(PurchaseStatus.CANCELED.getStatus());
        return specialRegisterMapper.updateByPrimaryKeySelective(specialRegister);
    }

    @Override
    public void exportExcel(SpecialRegister specialRegister, UserVO userVO, OutputStream output, String name, Long rid) {
        //标题数据
        List<String> names = new ArrayList<>();
        names.add(userVO.getEnterpriseName());
        names.add("专管登记");
        //表头数据
        List<String> titleSecond = new ArrayList<>();
        StringBuffer title=new StringBuffer();
        title.append("登记单号:").append(specialRegister.getCode())
                .append("   登记日期:").append(DateUtils.DateToString(specialRegister.getRegisterDate(), "yyyy-MM-dd"))
                .append("   登记人员:").append(specialRegister.getRegisterManName())
                .append("   会员卡号:").append(specialRegister.getMemberCardCode() == null ? "--" : specialRegister.getMemberCardCode())
                .append("   购药者姓名:").append(specialRegister.getConsumerName())
                .append("   性别:").append(specialRegister.getSex() == 0 ? "女" : "男")
                .append("   年龄:").append(specialRegister.getAge())
                .append("   身份证号:").append(specialRegister.getIdNum());
        titleSecond.add(title.toString());
        //内容数据
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("goodsCode", "商品编码");
        map.put("goodsGenericName", "通用名称");
        map.put("dosageName", "剂型");
        map.put("goodsSpecification", "规格");
        map.put("manufacturer", "生产厂商");
        map.put("unitName", "单位");
        map.put("lotNumber", "批号");
        map.put("productDateStr", "生产日期");
        map.put("validDateStr", "有效期至");
        map.put("shelfName", "货位");
        map.put("quantity", "数量");
        map.put("unitPrice", "单价");
        map.put("lineDiscount", "折扣");
        map.put("amount", "金额");
        map.put("taxRate", "税率");
        map.put("notaxRealPrice", "不含税单价");
        map.put("notaxRealAmount", "不含税金额");
        map.put("taxAmount", "税额");
        map.put("remark", "备注");
        List<SpecialRegisterExportVo> specialRegisterExportVoList = new ArrayList<>();
        List<SpecialRegisterDetail> specialRegisterDetailList = specialRegister.getSpecialRegisterDetailList();
        for (SpecialRegisterDetail specialRegisgerDetail :
                specialRegisterDetailList) {
            List<SpecialRegisterShelf> specialRegisterShelfList = specialRegisgerDetail.getSpecialRegisterShelfList();
            for (SpecialRegisterShelf specialRegisterShelf :
                    specialRegisterShelfList) {
                SpecialRegisterExportVo specialRegisterExportVo=new SpecialRegisterExportVo();
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialRegisterShelf, specialRegisterExportVo);
                specialRegisterExportVo.setGoodsCode(specialRegisgerDetail.getGoodsCode());
                specialRegisterExportVo.setGoodsGenericName(specialRegisgerDetail.getGoodsGenericName());
                specialRegisterExportVo.setGoodsSpecification(specialRegisgerDetail.getGoodsSpecification());
                specialRegisterExportVo.setUnitName(specialRegisgerDetail.getUnitName());
                specialRegisterExportVo.setDosageName(specialRegisgerDetail.getDosageName());
                specialRegisterExportVo.setManufacturer(specialRegisgerDetail.getManufacturer());
                specialRegisterExportVoList.add(specialRegisterExportVo);
            }
        }
        purchaseGeneralComponent.commExcelExport(output, map, specialRegisterExportVoList, names, titleSecond, "", true, new ArrayList<>());
    }
}
