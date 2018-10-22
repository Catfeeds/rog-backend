package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.service.impl;

import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInShelfMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInShelf;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrOutShelfMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnInShelfMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOutShelf;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnInShelf;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.dao.VerificationFormDetailMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.dao.VerificationFormItemMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.dao.VerificationFormMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.dao.VerificationFormModifyRecordMapper;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.entity.VerificationForm;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.entity.VerificationFormDetail;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.entity.VerificationFormItem;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.entity.VerificationFormModifyRecord;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.service.VerificationFormService;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.verificationform.vo.*;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageShelfMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageShelf;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleShelfMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.SaleShelf;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.component.RedisComponent;
import com.rograndec.feijiayun.chain.common.component.VerificationFormExcelComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.FinanceAccountType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.VerificationFormStatus;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author jiaruifeng
 */
@Service
public class VerificationFormServiceImpl implements VerificationFormService {
    @Autowired
    private RedisComponent redisComponent;
    @Autowired
    private OrderCodeComponent orderCodeComponent;
    @Autowired
    private SupplierMapper supplierMapper;
    @Autowired
    private VerificationFormMapper verificationFormMapper;

    @Autowired
    private VerificationFormItemMapper verificationFormItemMapper;

    @Autowired
    private VerificationFormDetailMapper verificationFormDetailMapper;

    @Autowired
    VerificationFormModifyRecordMapper verificationFormModifyRecordMapper;
    @Autowired
    private PurchaseInStorageShelfMapper purchaseInStorageShelfMapper;
    @Autowired
    private DistrInShelfMapper distrInShelfMapper;

    @Autowired
    private  SaleShelfMapper saleShelfMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private DistrOutShelfMapper distrOutShelfMapper;
    @Autowired
    private DistrReturnInShelfMapper distrReturnInShelfMapper;


    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void save(UserVO userVO, VerificationFormVO verificationFormVO) throws Exception {
        // 验证供货单位是实销实结的供货单位
        // 验证核销日期是否属于会计周期

        verificationFormVO.setFinanceAccountType(FinanceAccountType.SUPPLIER.getType());

        verificationFormVO.setEnterpriseId(userVO.getEnterpriseId());
        verificationFormVO.setParentId(userVO.getParentId());
        // 生成核销单code
        verificationFormVO.setCode(orderCodeComponent.generate(OrderRule.VERIFICATION_FORM.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode()));
        // 核销单类型
        verificationFormVO.setOrderType(OrderRule.VERIFICATION_FORM.getType());
        // 状态已完成
        verificationFormVO.setStatus(VerificationFormStatus.FINISH.getCode());
        // 汇总数量和金额
        // 本次核销金额合计
        BigDecimal amountTotal = BigDecimal.ZERO;
        // 本次核销不含税金额合计
        BigDecimal taxAmountTotal = BigDecimal.ZERO;
        // 本次核销税额合计
        BigDecimal notaxAmountTotal = BigDecimal.ZERO;
        // 判断二级表非空
        if(null!=verificationFormVO.getVerificationFormItemVOList()){
            // 遍历二级表和三级表 统计二级表实际核销数量 并且记录采购入库单的货位明细和销售出库单的货位明细的已核销数量和未核销数量
            for(VerificationFormItemVO verificationFormItemVO:verificationFormVO.getVerificationFormItemVOList()){
                // 二级表的核销数量
                BigDecimal quantity = BigDecimal.ZERO;
                if(null!=verificationFormItemVO.getVerificationFormDetailList()){
                    for(VerificationFormDetailVO verificationFormDetailVO:verificationFormItemVO.getVerificationFormDetailList()){
                        BigDecimal qty = verificationFormDetailVO.getQuantity()==null?BigDecimal.ZERO:verificationFormDetailVO.getQuantity();
                        if(qty.compareTo(BigDecimal.ZERO)>0){
                            // 核销入库单计数
                            updateInStorageOrder(userVO,verificationFormDetailVO);
                            // 核销出库单计数
                            updateSaleOutOrder(userVO,verificationFormDetailVO,verificationFormItemVO.getGoodsId(),verificationFormVO);

                            // 二级表的核销数量
                            quantity=quantity.add(verificationFormDetailVO.getQuantity());
                            verificationFormDetailVO.setAmount(verificationFormDetailVO.getPrice().multiply(verificationFormDetailVO.getQuantity()));
                            verificationFormDetailVO.setNotaxAmount(verificationFormDetailVO.getNotaxPrice().multiply(verificationFormDetailVO.getQuantity()));
                            verificationFormDetailVO.setTaxAmount(verificationFormDetailVO.getAmount().subtract(verificationFormDetailVO.getNotaxAmount()));
                            //累加合计总金额
                            amountTotal = amountTotal.add(verificationFormDetailVO.getAmount());
                            //累加合计总的税额
                            taxAmountTotal = taxAmountTotal.add(verificationFormDetailVO.getTaxAmount());
                            //累加合计总的不含税金额
                            notaxAmountTotal = notaxAmountTotal.add(verificationFormDetailVO.getNotaxAmount());

                        }else{
                            //入库清单本次核销数量为0不做处理
                        }
                    }
                }
                verificationFormItemVO.setQuantity(quantity);
            }
        }else{
            throw new BusinessException("未查询到该核销单条不能为空！");
        }

        verificationFormVO.setAmountTotal(amountTotal);
        verificationFormVO.setTaxAmountTotal(taxAmountTotal);
        verificationFormVO.setNotaxAmountTotal(notaxAmountTotal);
        verificationFormVO.setCreaterCode(userVO.getUserCode());
        verificationFormVO.setCreaterId(userVO.getUserId());
        verificationFormVO.setCreateTime(new Date());
        verificationFormVO.setCreaterName(userVO.getUserName());
        verificationFormVO.setModifierCode(userVO.getUserCode());
        verificationFormVO.setModifierId(userVO.getUserId());
        verificationFormVO.setUpdateTime(new Date());
        verificationFormVO.setModifierName(userVO.getUserName());
        verificationFormVO.setEnterpriseId(userVO.getEnterpriseId());
        verificationFormVO.setParentId(userVO.getParentId());
        // 插入核销单一级表
        this.verificationFormMapper.insertSelective(verificationFormVO);
        int linenumber=0;
        for(VerificationFormItemVO verificationFormItemVO:verificationFormVO.getVerificationFormItemVOList()){
            verificationFormItemVO.setEnterpriseId(userVO.getEnterpriseId());
            verificationFormItemVO.setParentId(userVO.getParentId());
            verificationFormItemVO.setFormId(verificationFormVO.getId());
            verificationFormItemVO.setStatus(VerificationFormStatus.FINISH.getCode());
            verificationFormItemVO.setCreaterCode(userVO.getUserCode());
            verificationFormItemVO.setCreaterId(userVO.getUserId());
            verificationFormItemVO.setCreateTime(new Date());
            verificationFormItemVO.setCreaterName(userVO.getUserName());
            verificationFormItemVO.setModifierCode(userVO.getUserCode());
            verificationFormItemVO.setModifierId(userVO.getUserId());
            verificationFormItemVO.setUpdateTime(new Date());
            verificationFormItemVO.setModifierName(userVO.getUserName());
            verificationFormItemVO.setEnterpriseId(userVO.getEnterpriseId());
            verificationFormItemVO.setParentId(userVO.getParentId());
            verificationFormItemVO.setLineNum(++linenumber);
            // 插入核销单二级表
            this.verificationFormItemMapper.insertSelective(verificationFormItemVO);
            int lineNum=0;
            for(VerificationFormDetailVO verificationFormDetailVO:verificationFormItemVO.getVerificationFormDetailList()){
                verificationFormDetailVO.setEnterpriseId(userVO.getEnterpriseId());
                verificationFormDetailVO.setParentId(userVO.getParentId());
                verificationFormDetailVO.setFormId(verificationFormVO.getId());
                verificationFormDetailVO.setItemId(verificationFormItemVO.getId());
                verificationFormDetailVO.setStatus(VerificationFormStatus.FINISH.getCode());
                verificationFormDetailVO.setCreaterCode(userVO.getUserCode());
                verificationFormDetailVO.setCreaterId(userVO.getUserId());
                verificationFormDetailVO.setCreateTime(new Date());
                verificationFormDetailVO.setCreaterName(userVO.getUserName());
                verificationFormDetailVO.setModifierCode(userVO.getUserCode());
                verificationFormDetailVO.setModifierId(userVO.getUserId());
                verificationFormDetailVO.setUpdateTime(new Date());
                verificationFormDetailVO.setModifierName(userVO.getUserName());
                verificationFormDetailVO.setEnterpriseId(userVO.getEnterpriseId());
                verificationFormDetailVO.setParentId(userVO.getParentId());
                verificationFormDetailVO.setLineNum(++lineNum);
                // 插入核销单三级表
                this.verificationFormDetailMapper.insertSelective(verificationFormDetailVO);
            }
        }
    }
    // 核销入库单计数
    private void updateInStorageOrder(UserVO userVO,VerificationFormDetailVO verificationFormDetailVO) {

        Integer chainType = userVO.getChainType();
        if(ChainType.Division.getCode() == chainType){
            //加盟店核销配进入库单

            DistrInShelf distrInShelf=this.distrInShelfMapper.selectByPrimaryKey(verificationFormDetailVO.getBaseShelfDtlId());
            distrInShelf.setVerificationQuantity(distrInShelf.getVerificationQuantity().add(verificationFormDetailVO.getQuantity()));
            distrInShelf.setUnverificationQuantity(distrInShelf.getUnverificationQuantity().subtract(verificationFormDetailVO.getQuantity()));
            this.distrInShelfMapper.updateByPrimaryKeySelective(distrInShelf);
        } else if(ChainType.Headquarters.getCode() == chainType){
            //总部核销采购入库单

            PurchaseInStorageShelf purchaseInStorageShelf=this.purchaseInStorageShelfMapper.selectByPrimaryKey(verificationFormDetailVO.getBaseShelfDtlId());
            if(null==purchaseInStorageShelf)
                throw new BusinessException("入库单货位明细，未找到！");
            purchaseInStorageShelf.setVerificationQuantity(purchaseInStorageShelf.getVerificationQuantity().add(verificationFormDetailVO.getQuantity()));
            purchaseInStorageShelf.setUnverificationQuantity(purchaseInStorageShelf.getUnverificationQuantity().subtract(verificationFormDetailVO.getQuantity()));
            this.purchaseInStorageShelfMapper.updateByPrimaryKeySelective(purchaseInStorageShelf);
        }
    }

    // 核销出库(销出-销退+配出-配退)计数
    private void updateSaleOutOrder(UserVO userVO,VerificationFormDetailVO verificationFormDetailVO,Long goodsId,VerificationFormVO verificationFormVO) {
        Map<String,Object> map=new HashMap<>();
        map.put("lotNumber",verificationFormDetailVO.getLotNumber());
        map.put("goodsId",goodsId);
        map.put("startDate",verificationFormVO.getStartSaleDate());
        // 结束日期取整天的时候，时分秒被舍去，所以endDate需要+1
        map.put("endDate",DateUtils.addDay(verificationFormVO.getEndSaleDate(),1));
        Integer chainType = userVO.getChainType();
        if(ChainType.Division.getCode() == chainType){
            //加盟店查询自己的销售出库明细
            map.put("enterpriseId",verificationFormVO.getEnterpriseId());
        } else if(ChainType.Headquarters.getCode() == chainType){
            // 总部查询分店的销售明细
            map.put("parentId",verificationFormVO.getEnterpriseId());
        }


        List<SaleShelf> saleShelfList=this.saleShelfMapper.selectSaleShelfToVerificationByMap(map);
        List<DistrOutShelf> distrOutShelfList=this.distrOutShelfMapper.distrOutShelfToVerificationByMap(map);
        if(null==saleShelfList&&null==distrOutShelfList)
            throw new BusinessException("销售出库单货位明细和配货出库单货位明细都未找到！");

        BigDecimal toUpdateQuantity=verificationFormDetailVO.getQuantity();


        if(null!=saleShelfList){
            // 遍历所有销售出库货位明细并核销
            for(SaleShelf saleShelf:saleShelfList){
                // 如果待核销数量为0，则停止当前明细的核销
                if(toUpdateQuantity.compareTo(BigDecimal.ZERO)==0)
                    break;
                // 销售出库单明细的销退数量
                BigDecimal returnQuantity=getReturnQuantityBysaleShelfId(saleShelf.getId());

                // 可以核销的销出=待核销数量-配退数量
                BigDecimal canVerificationQuantity=saleShelf.getUnverificationQuantity().subtract(returnQuantity);

                // 如果(要核销的数量>可以核销的销出数量) 将可以核销的销出数量作为计算差值
                if(toUpdateQuantity.compareTo(canVerificationQuantity)>0){
                    // 待核销数量减小
                    saleShelf.setVerificationQuantity(saleShelf.getVerificationQuantity().add(canVerificationQuantity));
                    saleShelf.setUnverificationQuantity(saleShelf.getUnverificationQuantity().subtract(canVerificationQuantity));
                    toUpdateQuantity=toUpdateQuantity.subtract(canVerificationQuantity);
                }else{ //  将待核销的数量作为计算差值
                    saleShelf.setVerificationQuantity(saleShelf.getVerificationQuantity().add(toUpdateQuantity));
                    saleShelf.setUnverificationQuantity(saleShelf.getUnverificationQuantity().subtract(toUpdateQuantity));
                    toUpdateQuantity=BigDecimal.ZERO;
                }
                this.saleShelfMapper.updateByPrimaryKeySelective(saleShelf);
            }
        }
        // 核销配货出库
        if(null!=distrOutShelfList){
            // 遍历所有销售出库货位明细并核销
            for(DistrOutShelf distrOutShelf:distrOutShelfList){
                // 如果待核销数量为0，则停止当前明细的核销
                if(toUpdateQuantity.compareTo(BigDecimal.ZERO)==0)
                    break;
                // 配货出库单货位明细的配货退回数量
                BigDecimal returnQuantity=getReturnQuantityByDistrOutShelfId(distrOutShelf.getId());
                // 可以核销的配货出库=待核销数量-配退数量
                BigDecimal canVerificationQuantity=distrOutShelf.getUnverificationQuantity().subtract(returnQuantity);

                // 如果(要核销的数量>可以核销的销出数量) 将可以核销的销出数量作为计算差值
                if(toUpdateQuantity.compareTo(canVerificationQuantity)>0){
                    // 待核销数量减小
                    distrOutShelf.setVerificationQuantity(distrOutShelf.getVerificationQuantity().add(canVerificationQuantity));
                    distrOutShelf.setUnverificationQuantity(distrOutShelf.getUnverificationQuantity().subtract(canVerificationQuantity));
                    toUpdateQuantity=toUpdateQuantity.subtract(canVerificationQuantity);
                }else{ //  将待核销的数量作为计算差值
                    distrOutShelf.setVerificationQuantity(distrOutShelf.getVerificationQuantity().add(toUpdateQuantity));
                    distrOutShelf.setUnverificationQuantity(distrOutShelf.getUnverificationQuantity().subtract(toUpdateQuantity));
                    toUpdateQuantity=BigDecimal.ZERO;
                }
                this.distrOutShelfMapper.updateByPrimaryKeySelective(distrOutShelf);
            }
        }

        if(toUpdateQuantity.compareTo(BigDecimal.ZERO)>0){
            throw new BusinessException("待核销数量最终未能核销完!");
        }
    }

    /**
     * 获取配货退回数量
     * @param id
     * @return
     */
    private BigDecimal getReturnQuantityByDistrOutShelfId(Long id) {
        List<DistrReturnInShelf> distrOutShelfReturnList=this.distrReturnInShelfMapper.distrReturnInShelfByDistrOutShelfId(id);
        BigDecimal returnQuantity=BigDecimal.ZERO;
        if(null!=distrOutShelfReturnList){
            returnQuantity=distrOutShelfReturnList.stream().map(p->p.getQuantity()).reduce(BigDecimal.ZERO,(a,b)->a.add(b));
        }
        return returnQuantity;
    }

    /**
     * 销售出库货位明细对应的销退数量汇总
     *
     * @param id
     */
    private BigDecimal getReturnQuantityBysaleShelfId(Long id){
        List<SaleShelf> saleShelfReturnList=this.saleShelfMapper.saleShelfReturnBySaleOutShelfId(id);
        BigDecimal returnQuantity=BigDecimal.ZERO;
        if(null!=saleShelfReturnList){
            returnQuantity=saleShelfReturnList.stream().map(p->p.getQuantity()).reduce(BigDecimal.ZERO,(a,b)->a.add(b));
        }
        return returnQuantity;
    }

    @Override
    public void getVerificationFormVOPage(Page<VerificationFormCountVO> page, UserVO userVO, String supplierCode, String supplierName, String operatorName, String code, String sort, Long status, String sortField, Date startDate, Date endDate){
        Map<String,Object> map=new HashMap<>();

        map.put("enterpriseId",userVO.getEnterpriseId());

        map.put("supplierCode",supplierCode);
        map.put("supplierName",supplierName);
        map.put("operatorName",operatorName);
        map.put("code",code);
        map.put("sort",sort);
        map.put("status",status);
        if(null!=sortField){
            if(sortField.equals("orderDate")){
                sortField="order_date";
            }
        }
        map.put("sortField",sortField);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("start",page.getStart());
        map.put("pageSize",page.getPageSize());
        Integer totalRecord=verificationFormMapper.getVerificationFormVOCount(map);
        List<VerificationFormVO> verificationFormVOList=verificationFormMapper.getVerificationFormVOCountList(map);
        BigDecimal amountTotal = BigDecimal.ZERO;
        BigDecimal taxAmountTotal = BigDecimal.ZERO;
        BigDecimal notaxAmountTotal = BigDecimal.ZERO;
        for(VerificationFormVO verificationFormVO:verificationFormVOList){
            taxAmountTotal=taxAmountTotal.add(verificationFormVO.getTaxAmountTotal());
            amountTotal=amountTotal.add(verificationFormVO.getAmountTotal());
            notaxAmountTotal=notaxAmountTotal.add(verificationFormVO.getNotaxAmountTotal());
        }
        VerificationFormCountVO verificationFormCountVO =new VerificationFormCountVO();
        verificationFormCountVO.setAmountTotal(amountTotal);
        verificationFormCountVO.setNotaxRealAmountTotal(notaxAmountTotal);
        verificationFormCountVO.setTaxAmountTotal(taxAmountTotal);
        verificationFormCountVO.setVerificationFormListVO(verificationFormVOList);
        page.setResult(verificationFormCountVO);
        page.setTotalRecord(totalRecord==null?0:totalRecord.intValue());
    }

    @Override
    public List<Supplier> getActualSalesSettlementSuppliers(UserVO userVO) {
        Integer chainType = userVO.getChainType();
        List<Supplier> supplierList=null;
        if(ChainType.Division.getCode() == chainType){
            //加盟店获取加盟店的供货商单位  ownerId
            supplierList=supplierMapper.getActualSalesSettlementSuppliers(userVO.getEnterpriseId());
        } else if(ChainType.Headquarters.getCode() == chainType){
            //总部获取总部自己的供货单位  ownerId
            supplierList=supplierMapper.getActualSalesSettlementSuppliers(userVO.getEnterpriseId());
        }
        return supplierList;
    }

    /**
     * 获取销售清单
     * @param userVO
     * @param supplierId
     * @param startDate
     * @param endDate 在Controller中，结束日期取整天的时候，时分秒被舍去，所以已经对endDate需要+1天
     * @return
     */
    @Override
    public List<SaleOutORreturnVO> getSaleOutORreturn(UserVO userVO, String supplierId,Date startDate, Date endDate) {
        Map<String,Object> map=new HashMap<>();
        Integer chainType = userVO.getChainType();
        if(ChainType.Division.getCode() == chainType){
            //加盟店获取自己的销售清单
            map.put("enterpriseId",userVO.getEnterpriseId());
        } else if(ChainType.Headquarters.getCode() == chainType){
            //总部获取自营店的销售清单
            map.put("parentId",userVO.getEnterpriseId());
        }
        map.put("supplierId",supplierId);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        // 销售出库清单
        List<SaleOutORreturnVO> saleOutList=verificationFormMapper.getSaleOut(map);
        // 销退入库清单
        List<SaleOutORreturnVO> saleReturnList=verificationFormMapper.getSaleReturn(map);
        //配货出库清单
        List<SaleOutORreturnVO> distrOutList=null;
        //配货出库退回清单
        List<SaleOutORreturnVO> distrReturnList=null;
        if(ChainType.Headquarters.getCode() == chainType){
            distrOutList=verificationFormMapper.getDistrOut(map);
            distrReturnList=verificationFormMapper.getDistrReturn(map);
        }

        // 最终返回结果
        List<SaleOutORreturnVO> newSaleOutList=new ArrayList<>();
        for(SaleOutORreturnVO saleOut:saleOutList){
            // 销售清单中减去销退数量
            if(null!=saleReturnList) {
                for (SaleOutORreturnVO saleReturn : saleReturnList) {
                    if (saleOut.getLotNumber().equals(saleReturn.getLotNumber()) && saleOut.getGoodsId().equals(saleReturn.getGoodsId())) {
                        //实际销售数量=销出数量-销退数量
                        saleOut.setQuantity(saleOut.getQuantity().subtract(saleReturn.getQuantity()));
                        //实际未核销数量=未核销数量-销退数量
                        saleOut.setUnverificationQuantity(saleOut.getUnverificationQuantity().subtract(saleReturn.getQuantity()));
                    }
                }
            }
            if(saleOut.getQuantity().compareTo(BigDecimal.ZERO)>0&&saleOut.getUnverificationQuantity().compareTo(BigDecimal.ZERO)>0){
                newSaleOutList.add(saleOut);
            }
        }



        List<SaleOutORreturnVO> newDistrOutList=new ArrayList<>();
        if(CollectionUtils.isNotEmpty(distrOutList)) {
            for (SaleOutORreturnVO distrOut : distrOutList) {
                if (null != distrReturnList) {
                    for (SaleOutORreturnVO distrReturn : distrReturnList) {
                        if (distrOut.getLotNumber().equals(distrReturn.getLotNumber()) && distrOut.getGoodsId().equals(distrReturn.getGoodsId())) {
                            distrOut.setQuantity(distrOut.getQuantity().subtract(distrReturn.getQuantity()));
                            distrOut.setUnverificationQuantity(distrOut.getUnverificationQuantity().subtract(distrReturn.getQuantity()));
                        }
                    }
                }

                if (distrOut.getQuantity().compareTo(BigDecimal.ZERO) > 0 && distrOut.getUnverificationQuantity().compareTo(BigDecimal.ZERO) > 0) {
                    newDistrOutList.add(distrOut);
                }
            }
        }

        List<SaleOutORreturnVO> saleOutListResult=new ArrayList<>();
        if(CollectionUtils.isNotEmpty(newSaleOutList)){
            for(SaleOutORreturnVO newSaleOut:newSaleOutList){
                for(SaleOutORreturnVO newDistrOut:newDistrOutList){
                    if(newSaleOut.getLotNumber().equals(newDistrOut.getLotNumber())&&newSaleOut.getGoodsId().equals(newDistrOut.getGoodsId())){
                        newSaleOut.setQuantity(newSaleOut.getQuantity().add(newDistrOut.getQuantity()));
                        newSaleOut.setUnverificationQuantity(newSaleOut.getUnverificationQuantity().add(newDistrOut.getUnverificationQuantity()));
                        newSaleOut.setVerificationQuantity(newSaleOut.getVerificationQuantity().add(newDistrOut.getVerificationQuantity()));
                    }
                }
                saleOutListResult.add(newSaleOut);
            }
            for(SaleOutORreturnVO newDistrOut:newDistrOutList){
                boolean isContainNewDistrOut=Boolean.FALSE;
                    for(SaleOutORreturnVO newSaleOut:newSaleOutList){
                    if(newSaleOut.getLotNumber().equals(newDistrOut.getLotNumber())&&newSaleOut.getGoodsId().equals(newDistrOut.getGoodsId())){
                        isContainNewDistrOut=Boolean.TRUE;
                    }
                }
                if(!isContainNewDistrOut)
                saleOutListResult.add(newDistrOut);
            }

        }else{
            saleOutListResult=newDistrOutList;
        }

        return saleOutListResult;
    }

    @Override
    public List<PurchaseInStorageReportPageVO> getPurchaseInStorageReportPage(UserVO userVO, String supplierId, String lotNumber, Long goodsId) {
        Map<String,Object> map=new HashMap<>();
        map.put("enterpriseId",userVO.getEnterpriseId());
        map.put("supplierId",supplierId);
        map.put("lotNumber",lotNumber);
        map.put("goodsId",goodsId);
        Integer chainType = userVO.getChainType();
        if(ChainType.Division.getCode() == chainType){
            //加盟店获取配进入库的明细
            return verificationFormMapper.getDistrInStorageReportPage(map);
        } else if(ChainType.Headquarters.getCode() == chainType){
            //总部获取采购入库的明细
            return verificationFormMapper.getPurchaseInStorageReportPage(map);
        }
        return null;
    }

    @Override
    public VerificationFormVO getVerificationFormVOById(Long id) {
        VerificationForm verificationForm=this.verificationFormMapper.selectByPrimaryKey(id);
        VerificationFormVO verificationFormVO= new VerificationFormVO();
        try {
            BeanUtils.copyProperties(verificationFormVO,verificationForm);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        List<VerificationFormItem> itemList =this.verificationFormItemMapper.selectItemByFormId(id);
        List<VerificationFormItemVO> itemVOList =new ArrayList<>();
        itemList.forEach(item->{
            VerificationFormItemVO itemVO=new VerificationFormItemVO();
            try {
                BeanUtils.copyProperties(itemVO,item);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            List<VerificationFormDetail> detailList=this.verificationFormDetailMapper.selectDetailByItemId(item.getId());
            List<VerificationFormDetailVO> detailVOList=new ArrayList<>();
            detailList.forEach(detail->{
                VerificationFormDetailVO detailVO=new VerificationFormDetailVO();
                try {
                    BeanUtils.copyProperties(detailVO,detail);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                detailVOList.add(detailVO);
            });

            itemVO.setVerificationFormDetailList(detailVOList);
            itemVOList.add(itemVO);
        });
        verificationFormVO.setVerificationFormItemVOList(itemVOList);

        return verificationFormVO;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public String writeOffVerificationFormById(UserVO userVO,Long id) {
        VerificationFormVO verificationFormVO=getVerificationFormVOById(id);
        if(null==verificationFormVO)
            throw new BusinessException("未查询到该核销单！");

        if(!verificationFormVO.getStatus().equals(VerificationFormStatus.FINISH.getCode()))
            throw new BusinessException("该核销单状态错误，不可冲销！");

        List<VerificationFormItemVO> verificationFormItemVOList=verificationFormVO.getVerificationFormItemVOList();

        for(VerificationFormItemVO verificationFormItemVO:verificationFormItemVOList){
            for(VerificationFormDetailVO verificationFormDetailVO:verificationFormItemVO.getVerificationFormDetailList()){
                // 反核销入库单计数
                writeOffInStorageOrder(userVO,verificationFormDetailVO);
                // 反核销出库单计数
                writeOffSaleOutOrder(userVO,verificationFormDetailVO,verificationFormItemVO.getGoodsId(),verificationFormVO);
            }
        }

        //修改主单状态
        verificationFormVO.setStatus(VerificationFormStatus.WRITE_OFF.getCode());
        this.verificationFormMapper.updateByPrimaryKeySelective(verificationFormVO);
        return null;
    }

    private void writeOffSaleOutOrder(UserVO userVO,VerificationFormDetailVO verificationFormDetailVO, Long goodsId,VerificationFormVO verificationFormVO) {
        Map<String,Object> map=new HashMap<>();
        map.put("lotNumber",verificationFormDetailVO.getLotNumber());
        map.put("goodsId",goodsId);
        map.put("startDate",verificationFormVO.getStartSaleDate());
        // 结束日期取整天的时候，时分秒被舍去，所以endDate需要+1
        map.put("endDate",DateUtils.addDay(verificationFormVO.getEndSaleDate(),1));

        Integer chainType = userVO.getChainType();
        if(ChainType.Division.getCode() == chainType){
            // 加盟店冲销自己
            map.put("enterpriseId",userVO.getEnterpriseId());
        } else if(ChainType.Headquarters.getCode() == chainType){
            // 总店核冲销自营店
            map.put("parentId",userVO.getEnterpriseId());
        }

//        map.put("enterpriseId",verificationFormDetailVO.getEnterpriseId());
        List<SaleShelf> saleShelfList=this.saleShelfMapper.selectSaleShelfToWriteOffByMap(map);
        List<DistrOutShelf> distrOutShelfList=this.distrOutShelfMapper.selectDistrOutShelfToWriteOffByMap(map);
        BigDecimal toWriteOffUpdateQuantity=verificationFormDetailVO.getQuantity();
        // 遍历所有销售出库货位明细并冲销
        if(null!=saleShelfList){
            for(SaleShelf saleShelf:saleShelfList){
                // 如果待核销数量为0，则停止当前明细的核销
                if(toWriteOffUpdateQuantity.compareTo(BigDecimal.ZERO)==0)
                    break;

                // 可以反核销的销出=销出货位明细的数量-销退数量-已核销数量
                BigDecimal canWriteOffVerificationQuantity=saleShelf.getVerificationQuantity();

                // 如果(要反核销的数量>可以反核销的销出) 将可以核销的销出数量作为计算差值
                if(toWriteOffUpdateQuantity.compareTo(canWriteOffVerificationQuantity)>0){
                    // 待核销数量减小
                    saleShelf.setVerificationQuantity(saleShelf.getVerificationQuantity().subtract(canWriteOffVerificationQuantity));
                    saleShelf.setUnverificationQuantity(saleShelf.getUnverificationQuantity().add(canWriteOffVerificationQuantity));
                    toWriteOffUpdateQuantity=toWriteOffUpdateQuantity.subtract(canWriteOffVerificationQuantity);
                }else{ //  将待核销的数量作为计算差值
                    saleShelf.setVerificationQuantity(saleShelf.getVerificationQuantity().subtract(toWriteOffUpdateQuantity));
                    saleShelf.setUnverificationQuantity(saleShelf.getUnverificationQuantity().add(toWriteOffUpdateQuantity));
                    toWriteOffUpdateQuantity=BigDecimal.ZERO;
                }
                this.saleShelfMapper.updateByPrimaryKeySelective(saleShelf);
            }
        }

        // 所有配货出库货位明细并冲销
        if(null!=distrOutShelfList){
            // 遍历所有销售出库货位明细并核销
            for(DistrOutShelf distrOutShelf:distrOutShelfList){
                // 如果待核销数量为0，则停止当前明细的核销
                if(toWriteOffUpdateQuantity.compareTo(BigDecimal.ZERO)==0)
                    break;

                // 可以反核销的销出=销出货位明细的数量-销退数量-已核销数量
                BigDecimal canWriteOffVerificationQuantity=distrOutShelf.getVerificationQuantity();

                // 如果(要反核销的数量>可以反核销的销出) 将可以核销的销出数量作为计算差值
                if(toWriteOffUpdateQuantity.compareTo(canWriteOffVerificationQuantity)>0){
                    // 待核销数量减小
                    distrOutShelf.setVerificationQuantity(distrOutShelf.getVerificationQuantity().subtract(canWriteOffVerificationQuantity));
                    distrOutShelf.setUnverificationQuantity(distrOutShelf.getUnverificationQuantity().add(canWriteOffVerificationQuantity));
                    toWriteOffUpdateQuantity=toWriteOffUpdateQuantity.subtract(canWriteOffVerificationQuantity);
                }else{ //  将待核销的数量作为计算差值
                    distrOutShelf.setVerificationQuantity(distrOutShelf.getVerificationQuantity().subtract(toWriteOffUpdateQuantity));
                    distrOutShelf.setUnverificationQuantity(distrOutShelf.getUnverificationQuantity().add(toWriteOffUpdateQuantity));
                    toWriteOffUpdateQuantity=BigDecimal.ZERO;
                }
                this.distrOutShelfMapper.updateByPrimaryKeySelective(distrOutShelf);
            }
        }
        if(toWriteOffUpdateQuantity.compareTo(BigDecimal.ZERO)>0){
            throw new BusinessException("冲销失败，冲销销出数量未完成");
        }
    }
    private void writeOffInStorageOrder(UserVO userVO,VerificationFormDetailVO verificationFormDetailVO) {
        Integer chainType = userVO.getChainType();
        if(ChainType.Division.getCode() == chainType){
            //加盟店核销配进入库单
            DistrInShelf distrInShelf=this.distrInShelfMapper.selectByPrimaryKey(verificationFormDetailVO.getBaseShelfDtlId());
            distrInShelf.setVerificationQuantity(distrInShelf.getVerificationQuantity().subtract(verificationFormDetailVO.getQuantity()));
            distrInShelf.setUnverificationQuantity(distrInShelf.getUnverificationQuantity().add(verificationFormDetailVO.getQuantity()));
            this.distrInShelfMapper.updateByPrimaryKeySelective(distrInShelf);
        } else if(ChainType.Headquarters.getCode() == chainType){
            //总部核销采购入库单

            PurchaseInStorageShelf purchaseInStorageShelf=this.purchaseInStorageShelfMapper.selectByPrimaryKey(verificationFormDetailVO.getBaseShelfDtlId());
            purchaseInStorageShelf.setVerificationQuantity(purchaseInStorageShelf.getVerificationQuantity().subtract(verificationFormDetailVO.getQuantity()));
            purchaseInStorageShelf.setUnverificationQuantity(purchaseInStorageShelf.getUnverificationQuantity().add(verificationFormDetailVO.getQuantity()));
            this.purchaseInStorageShelfMapper.updateByPrimaryKeySelective(purchaseInStorageShelf);
        }

    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void modifyVerificationForm(UserVO userVO, VerificationFormVO verificationFormVO,String reason){

        VerificationForm orgVerificationForm=this.verificationFormMapper.selectByPrimaryKey(verificationFormVO.getId());

        if(null==orgVerificationForm)
            throw new BusinessException("未查询到该核销单！");
        if(orgVerificationForm.getOperatorId()!=null&&!orgVerificationForm.getOperatorId().equals(verificationFormVO.getOperatorId())){
            this.verificationFormModifyRecordMapper.insertSelective(createModifyRecord(verificationFormVO.getId(),reason,"核销人员id","operator_id","saas_verification_form",userVO,orgVerificationForm.getOperatorId().toString(),verificationFormVO.getOperatorId().toString()));
            this.verificationFormModifyRecordMapper.insertSelective(createModifyRecord(verificationFormVO.getId(),reason,"核销人员编码","operator_code","saas_verification_form",userVO,orgVerificationForm.getOperatorCode(),verificationFormVO.getOperatorCode()));
            this.verificationFormModifyRecordMapper.insertSelective(createModifyRecord(verificationFormVO.getId(),reason,"核销人员姓名","operator_name","saas_verification_form",userVO,orgVerificationForm.getOperatorName(),verificationFormVO.getOperatorName()));
        }
        if(orgVerificationForm.getRemark()!=null&&!orgVerificationForm.getRemark().equals(verificationFormVO.getRemark())){
            this.verificationFormModifyRecordMapper.insertSelective(createModifyRecord(verificationFormVO.getId(),reason,"核销备注","remark","saas_verification_form",userVO,orgVerificationForm.getRemark(),verificationFormVO.getRemark()));
        }
        if(orgVerificationForm.getOrderDate()!=null&&!orgVerificationForm.getOrderDate().equals(verificationFormVO.getOrderDate())){
            this.verificationFormModifyRecordMapper.insertSelective(createModifyRecord(verificationFormVO.getId(),reason,"核销日期","order_date","saas_verification_form",userVO,DateUtils.DateToString(orgVerificationForm.getOrderDate(), DateStyle.YYYY_MM_DD),DateUtils.DateToString(verificationFormVO.getOrderDate(), DateStyle.YYYY_MM_DD)));
        }
        verificationFormVO.setModifierCode(userVO.getUserCode());
        verificationFormVO.setModifierId(userVO.getUserId());
        verificationFormVO.setUpdateTime(new Date());
        verificationFormVO.setModifierName(userVO.getUserName());
        this.verificationFormMapper.updateByPrimaryKeySelective(verificationFormVO);
    }

    private VerificationFormModifyRecord createModifyRecord(Long keyId,String reason,String chName,String enName,String tableName,UserVO userVO,String newContent,String oldContent){
        VerificationFormModifyRecord verificationFormModifyRecord=new VerificationFormModifyRecord();
        verificationFormModifyRecord.setKeyId(keyId);
        verificationFormModifyRecord.setReason(reason);
        verificationFormModifyRecord.setColumnChName(chName);
        verificationFormModifyRecord.setColumnEnName(enName);
        verificationFormModifyRecord.setCreaterCode(userVO.getUserCode());
        verificationFormModifyRecord.setCreaterId(userVO.getUserId());
        verificationFormModifyRecord.setCreateTime(new Date());
        verificationFormModifyRecord.setCreaterName(userVO.getUserName());
        verificationFormModifyRecord.setModifierCode(userVO.getUserCode());
        verificationFormModifyRecord.setModifierId(userVO.getUserId());
        verificationFormModifyRecord.setUpdateTime(new Date());
        verificationFormModifyRecord.setModifierName(userVO.getUserName());
        verificationFormModifyRecord.setEnterpriseId(userVO.getEnterpriseId());
        verificationFormModifyRecord.setTableName(tableName);
        verificationFormModifyRecord.setNewContent(newContent);
        verificationFormModifyRecord.setOldContent(oldContent);
        verificationFormModifyRecord.setParentId(userVO.getParentId());
        return verificationFormModifyRecord;
    }

    @Override
    public void getModifyRecord(Long id,Page<List<VerificationFormModifyRecord>> page){
        Map<String,Object> map=new HashMap<>();
        map.put("formId",id);
        map.put("start",page.getStart());
        map.put("pageSize",page.getPageSize());
        Integer totalRecord=verificationFormModifyRecordMapper.selectByForCount(map);
        List<VerificationFormModifyRecord> list=verificationFormModifyRecordMapper.selectByForPage(map);
        page.setResult(list);
        page.setTotalRecord(totalRecord);
    }

    @Override
    public DraftCacheVO saveDraft(UserVO userVO, DraftCacheVO draftCache) throws Exception {
        String redisKeyValue = draftCache.getId();
        DraftCacheVO<VerificationFormVO> draftCacheVO = new DraftCacheVO();
        draftCacheVO.setOrderCode(OrderRule.VERIFICATION_FORM.getCodePrefix());
        draftCacheVO.setEnterpriseId(userVO.getEnterpriseId());
        draftCacheVO.setOrderStr(draftCache.getOrderStr());
        draftCacheVO.setId(redisKeyValue);
        return redisComponent.saveDraftCacheVO(draftCacheVO);
    }

    @Override
    public void removeDraftCach(Long enterpriseId, String type, String redisKeyValue) throws Exception {
        redisComponent.removeDraftCacheVO(enterpriseId,type,redisKeyValue);
    }
    @Override
    public List<DraftCacheVO> getDraftCacheVO(UserVO userVO) throws Exception {
        Class<VerificationFormVO> clazz = VerificationFormVO.class;
        return redisComponent.getDraftCacheVO(userVO.getEnterpriseId(),OrderRule.VERIFICATION_FORM.getCodePrefix(),clazz);
    }

    /**
     * 导出
     *
     * @param output
     */
    @Override
    public void exportExcel(OutputStream output,VerificationFormVO verificationFormVO, UserVO userVO){
        if(null==verificationFormVO)
            throw new BusinessException("未查询到该核销单！");

        LinkedHashMap<String,String> map = new LinkedHashMap<>();
//        序号（缺省）	商品编码	条形码	通用名称	商品名称
        map.put("goodsCode","商品编码");
        map.put("barcode","条形码");
        map.put("goodsGenericName","通用名称");
        map.put("goodsName","商品名称");
// 剂型	规格	单位	生产厂商	产地	批准文号	批号	生产日期	有效期至
        map.put("dosageName","剂型");
        map.put("goodsSpecification","规格");
        map.put("unitName","单位");
        map.put("manufacturer","生产厂商");
        map.put("goodsPlace","产地");
        map.put("approvalNumber","批准文号");
        map.put("lotNumber","批号");
        map.put("productDate","生产日期");
        map.put("validDate","有效期至");
// 	销售数量	入库日期	入库单号	入库数量	已核销数量	未核销数量	本次核销数量
        map.put("saleQuantity","销售数量");
        map.put("baseOrderDate","入库日期");
        map.put("baseOrderCode","入库单号");
        map.put("inQuantity","入库数量");
        map.put("verificationQuantity","已核销数量");
        map.put("unverificationQuantity","未核销数量");
        map.put("quantity","本次核销数量");

        List<String> secondTitle = new ArrayList<String>();
        StringBuilder titleSecondRow = new StringBuilder();
//        供货单位编码：                            供货单位名称：                           核销日期：                             核销人员：
//        销售日期（从）：                销售日期（至）：                        单号：                             备注：
        titleSecondRow.append("  供货单位编码:");
        titleSecondRow.append(verificationFormVO.getSupplierCode() == null ? "":verificationFormVO.getSupplierCode());
        titleSecondRow.append("  供货单位名称:");
        titleSecondRow.append(verificationFormVO.getSupplierName() == null ? "":verificationFormVO.getSupplierName());
        titleSecondRow.append("  日期:");
        titleSecondRow.append(verificationFormVO.getOrderDate() == null ? "":new SimpleDateFormat("yyyy-MM-dd").format(verificationFormVO.getOrderDate()));
        titleSecondRow.append("  核销人员:");
        titleSecondRow.append(verificationFormVO.getOperatorName() == null ? "":verificationFormVO.getOperatorName());
        titleSecondRow.append("  销售日期（从）:");
        titleSecondRow.append(verificationFormVO.getStartSaleDate() == null ? "":new SimpleDateFormat("yyyy-MM-dd").format(verificationFormVO.getStartSaleDate()));
        titleSecondRow.append("  销售日期（至）:");
        titleSecondRow.append(verificationFormVO.getEndSaleDate() == null ? "":new SimpleDateFormat("yyyy-MM-dd").format(verificationFormVO.getEndSaleDate()));
        titleSecondRow.append("  单号:");
        titleSecondRow.append(verificationFormVO.getCode() == null ? "":verificationFormVO.getCode());
        titleSecondRow.append("  备注:");
        titleSecondRow.append(verificationFormVO.getRemark() == null ? "":verificationFormVO.getRemark());
        secondTitle.add(titleSecondRow.toString());
        verificationFormExcelComponent.commExcelExport(output, map, userVO, verificationFormVO);

    }
    @Autowired
    private VerificationFormExcelComponent verificationFormExcelComponent;
    @Autowired
    PurchaseGeneralComponent<VerificationFormModifyRecord> purchaseGeneralComponent;
    @Override
    public List<VerificationFormModifyRecord> getVerificationFormModifyRecord(Long id) {
        return verificationFormModifyRecordMapper.selectByFormId(id);
    }

    @Override
    public void exportExcelModifyRecord(OutputStream output, List<VerificationFormModifyRecord> verificationFormModifyRecordList, UserVO loginUser) {

        //转换一下显示日期
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("updateTime", "修改时间");
        map.put("modifierName", "修改人员");
        map.put("reason", "修改原因");
        map.put("columnChName", "修改项目");
        map.put("oldContent", "原内容");
        map.put("newContent", "新内容");
        List<String> titleSecond = new ArrayList<>();
        StringBuilder end = new StringBuilder();

        List<String> needTotalName = new ArrayList<>();

        List<String> name = new ArrayList<>();
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(loginUser.getEnterpriseId());
        name.add(enterprise.getName());
        name.add("修改记录");
        purchaseGeneralComponent.commExcelExport(output, map, verificationFormModifyRecordList, name, titleSecond, end.toString(), false, needTotalName);

    }

    @Override
    public List<SaleOutORreturnVO> antoSaleOutORreturn(UserVO userVO, String supplierId, Date startDate, Date endDate) {
        List<SaleOutORreturnVO> saleOutORreturnVOList=getSaleOutORreturn(userVO, supplierId, startDate, endDate);
        if(null!=saleOutORreturnVOList){
            for(SaleOutORreturnVO saleOutORreturnVO:saleOutORreturnVOList){

                if(null==saleOutORreturnVO.getAutoVerificationQuantity())
                    saleOutORreturnVO.setAutoVerificationQuantity(BigDecimal.ZERO);
                //等待自动核销的数量
                BigDecimal saleQuantity2Verification=saleOutORreturnVO.getUnverificationQuantity();
                List<PurchaseInStorageReportPageVO> purchaseInStorageReportPageVOList=getPurchaseInStorageReportPage(userVO,supplierId,saleOutORreturnVO.getLotNumber(),saleOutORreturnVO.getGoodsId());
                if(null!=purchaseInStorageReportPageVOList){
                    for(PurchaseInStorageReportPageVO purchaseInStorageReportPageVO:purchaseInStorageReportPageVOList){
                        // 如果待核销数量为0，则停止当前明细的核销
                        if(saleQuantity2Verification.compareTo(BigDecimal.ZERO)==0)
                            break;


                        // 入库清单的未核销数量
                        if(null==purchaseInStorageReportPageVO.getAutoVerificationQuantity())
                            purchaseInStorageReportPageVO.setAutoVerificationQuantity(BigDecimal.ZERO);
                        BigDecimal canVerificationQuantity=purchaseInStorageReportPageVO.getUnverificationQuantity().subtract(purchaseInStorageReportPageVO.getAutoVerificationQuantity());

                        // 如果(要核销的数量>可以核销的销出数量) 将可以核销的销出数量作为计算差值
                        if(saleQuantity2Verification.compareTo(canVerificationQuantity)>0){
                            // 待核销数量减小
                            purchaseInStorageReportPageVO.setAutoVerificationQuantity(purchaseInStorageReportPageVO.getAutoVerificationQuantity().add(canVerificationQuantity));
                            saleOutORreturnVO.setAutoVerificationQuantity(saleOutORreturnVO.getAutoVerificationQuantity().add(canVerificationQuantity));


                            saleQuantity2Verification=saleQuantity2Verification.subtract(canVerificationQuantity);
                        }else{ //  将待核销的数量作为计算差值
                            purchaseInStorageReportPageVO.setAutoVerificationQuantity(purchaseInStorageReportPageVO.getAutoVerificationQuantity().add(saleQuantity2Verification));
                            saleOutORreturnVO.setAutoVerificationQuantity(saleOutORreturnVO.getAutoVerificationQuantity().add(saleQuantity2Verification));
                            saleQuantity2Verification=BigDecimal.ZERO;
                        }

                    }

                }
                saleOutORreturnVO.setInstorageList(purchaseInStorageReportPageVOList);

            }

        }
        return saleOutORreturnVOList;
    }
}
