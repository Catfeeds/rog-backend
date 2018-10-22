package com.rograndec.feijiayun.chain.business.keytable.controller;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrIn;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOut;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOut;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnIn;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.entity.PaymentInvoice;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.entity.PaymentInvoiceDetail;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.entity.PaymentVoucher;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.entity.PaymentVoucherDetail;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.entity.PrepayInvoice;
import com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.entity.PrepayInvoiceDetail;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.entity.AdvanceReceivableInvoice;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.advanceinvoice.entity.AdvanceReceivableInvoiceDetail;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.entity.ReceivableVoucher;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.receivablevoucher.entity.ReceivableVoucherDetail;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.entity.ReceivableInvoice;
import com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.entity.ReceivableInvoiceDetail;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.entity.FinancePayment;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.entity.FinanceReceivable;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.dailysettle.entity.RetailDailySettle;
import com.rograndec.feijiayun.chain.business.finance.shouldpayment.retailpayment.entity.RetailPayment;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorage;
import com.rograndec.feijiayun.chain.business.purchase.retout.entity.PurchaseReturnOut;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.SaleDetail;
import com.rograndec.feijiayun.chain.business.storage.chgoods.entity.GoodsClear;
import com.rograndec.feijiayun.chain.business.storage.chgoods.entity.GoodsLoad;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsDestroy;
import com.rograndec.feijiayun.chain.business.storage.inventory.entity.Inventory;
import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherIn;
import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherOut;
import com.rograndec.feijiayun.chain.business.storage.move.entity.ShelfMove;
import com.rograndec.feijiayun.chain.business.storage.split.entity.Split;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.opening.entity.*;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.FinanceComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ClassName: FinanceTestController
 * @Description: 财务管理-通用接口测试
 * @author zhongyi.li
 * @version 1.0
 * @date 2018年1月4日 上午10:32
 */
@Api(value = "finance-test",description = "财务管理-通用接口测试")
@RestController
@RequestMapping("finance/test")
public class FinanceTestController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FinanceComponent financeComponent;

    @ApiOperation(value = "测试初始化企业财务余额表数据", notes = "测试初始化企业财务余额表数据 | 开发者 李中义 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/initEnterpriseBalanceTest", method = RequestMethod.POST)
    public Result<String> initEnterpriseBalanceTest(@ApiIgnore UserVO userVO, @RequestParam("enterpriseId") Long enterpriseId){
        Result<String> result = new Result<>();
        try{
            Enterprise enterprise = financeComponent.getEnterpriseById(enterpriseId);
            if(enterprise == null){
                throw new BusinessException("没有获取到ID为"+enterpriseId+"的企业信息！");
            }
        	financeComponent.initEnterpriseBalance(userVO, enterprise);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("初始化企业财务余额表数据异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("初始化企业财务余额表数据异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试初始化供货单位财务余额表数据", notes = "测试初始化供货单位财务余额表数据 | 开发者 李中义 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/initSupplierBalanceTest", method = RequestMethod.POST)
    public Result<String> initSupplierBalanceTest(@ApiIgnore UserVO userVO, @RequestParam("supplierId") Long supplierId){
        Result<String> result = new Result<>();
        try{
            Supplier supplier = financeComponent.getSupplierById(supplierId);
            if(supplier == null){
                throw new BusinessException("没有获取到ID为"+supplierId+"的供货单位信息！");
            }
            financeComponent.initSupplierBalance(userVO, supplier);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("初始化供货单位财务余额表数据异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("初始化供货单位财务余额表数据异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试初始化税率财务余额表数据", notes = "测试初始化税率财务余额表数据 | 开发者 李中义 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/initTaxRateBalanceTest", method = RequestMethod.POST)
    public Result<String> initTaxRateBalanceTest(@ApiIgnore UserVO userVO, @RequestParam("supplierId") Long taxRateId){
        Result<String> result = new Result<>();
        try{
            GoodsTaxRate goodsTaxRate = financeComponent.getTaxRateById(taxRateId);
            if(goodsTaxRate == null){
                throw new BusinessException("没有获取到ID为"+taxRateId+"的税率信息！");
            }
            financeComponent.initTaxBalance(userVO, goodsTaxRate);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("初始化税率财务余额表数据异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("初始化税率财务余额表数据异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试【期初库存】对财务余额表和凭证表的影响", notes = "测试【期初库存】对财务余额表和凭证表的影响 | 开发者 李中义 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/openingInventoryToBalanceAndVoucherTest", method = RequestMethod.POST)
    public Result<String> openingInventoryToBalanceAndVoucherTest(@ApiIgnore UserVO userVO, @RequestParam("openingInventoryId") Long openingInventoryId){
        Result<String> result = new Result<>();
        try{
            OpeningInventory openingInventory = financeComponent.getOpeningInventoryById(openingInventoryId);
            if(openingInventory == null){
                throw new BusinessException("没有获取到ID为"+openingInventoryId+"的【期初库存】单信息！");
            }
            financeComponent.openingInventoryToBalanceAndVoucher(userVO, openingInventory);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【期初库存】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【期初库存】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试【期初应付】对财务余额表和凭证表的影响", notes = "测试【期初应付】对财务余额表和凭证表的影响 | 开发者 李中义 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/openingPaymentToBalanceAndVoucherTest", method = RequestMethod.POST)
    public Result<String> openingPaymentToBalanceAndVoucherTest(@ApiIgnore UserVO userVO, @RequestParam("openingPaymentId") Long openingPaymentId){
        Result<String> result = new Result<>();
        try{
            OpeningPayment openingPayment = financeComponent.getOpeningPaymentById(openingPaymentId);
            if(openingPayment == null){
                throw new BusinessException("没有获取到ID为"+openingPaymentId+"的【期初应付】单信息！");
            }
            List<OpeningPaymentDetail> openingPaymentDetailList = financeComponent.getOpeningPaymentDetailList(openingPaymentId);
            financeComponent.openingPaymentToBalanceAndVoucher(userVO, openingPayment, openingPaymentDetailList);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【期初应付】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【期初应付】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }


    @ApiOperation(value = "测试【期初应收】对财务余额表和凭证表的影响", notes = "测试【期初应收】对财务余额表和凭证表的影响 | 开发者 李中义 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/openingReceivableToBalanceAndVoucherTest", method = RequestMethod.POST)
    public Result<String> openingReceivableToBalanceAndVoucherTest(@ApiIgnore UserVO userVO, @RequestParam("openingReceivableId") Long openingReceivableId){
        Result<String> result = new Result<>();
        try{
            OpeningReceivable openingReceivable = financeComponent.getOpeningReceivableById(openingReceivableId);
            if(openingReceivable == null){
                throw new BusinessException("没有获取到ID为"+openingReceivableId+"的【期初应收】单信息！");
            }
            List<OpeningReceivableDetail> openingReceivableDetailList = financeComponent.getOpeningReceivableDetailList(openingReceivableId);
            financeComponent.openingReceivableToBalanceAndVoucher(userVO, openingReceivable, openingReceivableDetailList);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【期初应收】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【期初应收】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试【期初税额】对财务余额表和凭证表的影响", notes = "测试【期初税额】对财务余额表和凭证表的影响 | 开发者 李中义 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/openingTaxToBalanceAndVoucherTest", method = RequestMethod.POST)
    public Result<String> openingTaxToBalanceAndVoucherTest(@ApiIgnore UserVO userVO, @RequestParam("openingTaxId") Long openingTaxId){
        Result<String> result = new Result<>();
        try{
            OpeningTax openingTax = financeComponent.getOpeningTaxById(openingTaxId);
            if(openingTax == null){
                throw new BusinessException("没有获取到ID为"+openingTax+"的【期初税额】单信息！");
            }
            List<OpeningTaxDetail> openingTaxDetailList = financeComponent.getOpeningTaxDetailList(openingTaxId);
            financeComponent.openingTaxToBalanceAndVoucher(userVO, openingTax, openingTaxDetailList);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【期初税额】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【期初税额】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试【采购入库】对财务余额表和凭证表的影响", notes = "测试【采购入库】对财务余额表和凭证表的影响 | 开发者 李中义 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/purchaseToBalanceAndVoucherTest", method = RequestMethod.POST)
    public Result<String> purchaseToBalanceAndVoucherTest(@ApiIgnore UserVO userVO, @RequestParam("inId") Long inId){
        Result<String> result = new Result<>();
        try{
            PurchaseInStorage purchaseInStorage = financeComponent.getPurchaseInStorageById(inId);
            if(purchaseInStorage == null){
                throw new BusinessException("没有获取到ID为"+purchaseInStorage+"的【采购入库】单信息！");
            }
            financeComponent.purchaseToBalanceAndVoucher(userVO, purchaseInStorage);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【采购入库】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【采购入库】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试【购进退出出库】对财务余额表和凭证表的影响", notes = "测试【购进退出出库】对财务余额表和凭证表的影响 | 开发者 李中义 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/purchaseReturnOutToBalanceAndVoucherTest", method = RequestMethod.POST)
    public Result<String> purchaseReturnOutToBalanceAndVoucherTest(@ApiIgnore UserVO userVO, @RequestParam("outId") Long outId){
        Result<String> result = new Result<>();
        try{
            PurchaseReturnOut purchaseReturnOut = financeComponent.getPurchaseReturnOutById(outId);
            if(purchaseReturnOut == null){
                throw new BusinessException("没有获取到ID为"+purchaseReturnOut+"的【购进退出出库】单信息！");
            }
            financeComponent.purchaseReturnOutToBalanceAndVoucher(userVO, purchaseReturnOut);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【购进退出出库】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【购进退出出库】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试【其它入库】对财务余额表和凭证表的影响", notes = "测试【其它入库】对财务余额表和凭证表的影响 | 开发者 李中义 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/otherInToBalanceAndVoucherTest", method = RequestMethod.POST)
    public Result<String> otherInToBalanceAndVoucherTest(@ApiIgnore UserVO userVO, @RequestParam("outId") Long inId){
        Result<String> result = new Result<>();
        try{
            OtherIn otherIn = financeComponent.getOtherInById(inId);
            if(otherIn == null){
                throw new BusinessException("没有获取到ID为"+otherIn+"的【其它入库】单信息！");
            }
            financeComponent.otherInToBalanceAndVoucher(userVO, otherIn);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【其它入库】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【其它入库】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试【其它出库】对财务余额表和凭证表的影响", notes = "测试【其它出库】对财务余额表和凭证表的影响 | 开发者 李中义 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/otherOutToBalanceAndVoucherTest", method = RequestMethod.POST)
    public Result<String> otherOutToBalanceAndVoucherTest(@ApiIgnore UserVO userVO, @RequestParam("outId") Long outId){
        Result<String> result = new Result<>();
        try{
            OtherOut otherOut = financeComponent.getOtherOutById(outId);
            if(otherOut == null){
                throw new BusinessException("没有获取到ID为"+otherOut+"的【其它出库】单信息！");
            }
            financeComponent.otherOutToBalanceAndVoucher(userVO, otherOut);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【其它出库】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【其它出库】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }


    @ApiOperation(value = "测试【货位移动】对财务余额表和凭证表的影响", notes = "测试【货位移动】对财务余额表和凭证表的影响 | 开发者 李中义 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/shelfMoveToBalanceAndVoucherTest", method = RequestMethod.POST)
    public Result<String> shelfMoveToBalanceAndVoucherTest(@ApiIgnore UserVO userVO, @RequestParam("moveId") Long moveId){
        Result<String> result = new Result<>();
        try{
            ShelfMove shelfMove = financeComponent.getShelfMoveById(moveId);
            if(shelfMove == null){
                throw new BusinessException("没有获取到ID为"+shelfMove+"的【货位移动】单信息！");
            }
            financeComponent.shelfMoveToBalanceAndVoucher(userVO, shelfMove);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【货位移动】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【货位移动】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }


    @ApiOperation(value = "测试【中药装斗】对财务余额表和凭证表的影响", notes = "测试【中药装斗】对财务余额表和凭证表的影响 | 开发者 李中义 | 贷测试", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/goodsLoadToBalanceAndVoucherTest", method = RequestMethod.POST)
    public Result<String> goodsLoadToBalanceAndVoucherTest(@ApiIgnore UserVO userVO, @RequestParam("loadId") Long loadId){
        Result<String> result = new Result<>();
        try{
            GoodsLoad goodsLoad = financeComponent.getGoodsLoadById(loadId);
            if(goodsLoad == null){
                throw new BusinessException("没有获取到ID为"+goodsLoad+"的【货位移动】单信息！");
            }
            financeComponent.goodsLoadToBalanceAndVoucher(userVO, goodsLoad);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【中药装斗】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【中药装斗】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试【中药清斗】对财务余额表和凭证表的影响", notes = "测试【中药清斗】对财务余额表和凭证表的影响 | 开发者 李中义 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/goodsClearToBalanceAndVoucherTest", method = RequestMethod.POST)
    public Result<String> goodsClearToBalanceAndVoucherTest(@ApiIgnore UserVO userVO, @RequestParam("clearId") Long clearId){
        Result<String> result = new Result<>();
        try{
            GoodsClear goodsClear = financeComponent.getGoodsClearById(clearId);
            if(goodsClear == null){
                throw new BusinessException("没有获取到ID为"+goodsClear+"的【中药清斗】单信息！");
            }
            financeComponent.goodsClearToBalanceAndVoucher(userVO, goodsClear);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【中药清斗】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【中药清斗】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试【药品拆零】对财务余额表和凭证表的影响", notes = "测试【药品拆零】对财务余额表和凭证表的影响 | 开发者 李中义 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/splitToBalanceAndVoucherTest", method = RequestMethod.POST)
    public Result<String> splitToBalanceAndVoucherTest(@ApiIgnore UserVO userVO, @RequestParam("splitId") Long splitId){
        Result<String> result = new Result<>();
        try{
            Split split = financeComponent.getSplitById(splitId);
            if(split == null){
                throw new BusinessException("没有获取到ID为"+split+"的【药品拆零】单信息！");
            }
            financeComponent.splitToBalanceAndVoucher(userVO, split);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【药品拆零】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【药品拆零】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试【药品销毁】对财务余额表和凭证表的影响", notes = "测试【药品销毁】对财务余额表和凭证表的影响 | 开发者 李中义 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/goodsDestroyToBalanceAndVoucherTest", method = RequestMethod.POST)
    public Result<String> goodsDestroyToBalanceAndVoucherTest(@ApiIgnore UserVO userVO, @RequestParam("goodsDestroyId") Long goodsDestroyId){
        Result<String> result = new Result<>();
        try{
            GoodsDestroy goodsDestroy = financeComponent.getGoodsDestroyById(goodsDestroyId);
            if(goodsDestroy == null){
                throw new BusinessException("没有获取到ID为"+goodsDestroy+"的【药品销毁】单信息！");
            }
            financeComponent.goodsDestroyToBalanceAndVoucher(userVO, goodsDestroy);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【药品销毁】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【药品销毁】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试【盘点】对财务余额表和凭证表的影响", notes = "测试【盘点】对财务余额表和凭证表的影响 | 开发者 李中义 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/inventoryIdToBalanceAndVoucherTest", method = RequestMethod.POST)
    public Result<String> inventoryIdToBalanceAndVoucherTest(@ApiIgnore UserVO userVO, @RequestParam("inventoryId") Long inventoryId){
        Result<String> result = new Result<>();
        try{
            Inventory inventory = financeComponent.getInventoryById(inventoryId);
            if(inventory == null){
                throw new BusinessException("没有获取到ID为"+inventory+"的【盘点】单信息！");
            }
            financeComponent.inventoryToBalanceAndVoucher(userVO, inventory);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【盘点】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【盘点】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试【配货出库】对财务余额表和凭证表的影响", notes = "测试【配货出库】对财务余额表和凭证表的影响 | 开发者 李中义 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/distrOutToBalanceAndVoucherTest", method = RequestMethod.POST)
    public Result<String> distrOutToBalanceAndVoucherTest(@ApiIgnore UserVO userVO, @RequestParam("outId") Long outId){
        Result<String> result = new Result<>();
        try{
            DistrOut distrOut = financeComponent.getDistrOutById(outId);
            if(distrOut == null){
                throw new BusinessException("没有获取到ID为"+distrOut+"的【配货出库】单信息！");
            }
            financeComponent.distrOutToBalanceAndVoucher(userVO, distrOut);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【配货出库】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【配货出库】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试【配后退回入库】对财务余额表和凭证表的影响", notes = "测试【配后退回入库】对财务余额表和凭证表的影响 | 开发者 李中义 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/distrReturnInToBalanceAndVoucherTest", method = RequestMethod.POST)
    public Result<String> distrReturnInToBalanceAndVoucherTest(@ApiIgnore UserVO userVO, @RequestParam("inId") Long inId){
        Result<String> result = new Result<>();
        try{
            DistrReturnIn distrReturnIn = financeComponent.getDistrReturnInById(inId);
            if(distrReturnIn == null){
                throw new BusinessException("没有获取到ID为"+distrReturnIn+"的【配后退回入库】单信息！");
            }
            financeComponent.distrReturnInToBalanceAndVoucher(userVO, distrReturnIn);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【配后退回入库】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【配后退回入库】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试【配进入库】对财务余额表和凭证表的影响", notes = "测试【配进入库】对财务余额表和凭证表的影响 | 开发者 李中义 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/distrInToBalanceAndVoucherTest", method = RequestMethod.POST)
    public Result<String> distrInToBalanceAndVoucherTest(@ApiIgnore UserVO userVO, @RequestParam("inId") Long inId){
        Result<String> result = new Result<>();
        try{
            DistrIn distrIn = financeComponent.getDistrInById(inId);
            if(distrIn == null){
                throw new BusinessException("没有获取到ID为"+distrIn+"的【配进入库】单信息！");
            }
            financeComponent.distrInToBalanceAndVoucher(userVO, distrIn);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【配进入库】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【配进入库】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试【配进退出出库】对财务余额表和凭证表的影响", notes = "测试【配进退出出库】对财务余额表和凭证表的影响 | 开发者 李中义 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/distrInReturnOutToBalanceAndVoucherTest", method = RequestMethod.POST)
    public Result<String> distrInReturnOutToBalanceAndVoucherTest(@ApiIgnore UserVO userVO, @RequestParam("outId") Long outId){
        Result<String> result = new Result<>();
        try{
            DistrInReturnOut distrInReturnOut = financeComponent.getDistrInReturnOutById(outId);
            if(distrInReturnOut == null){
                throw new BusinessException("没有获取到ID为"+distrInReturnOut+"的【配进退出出库】单信息！");
            }
            financeComponent.distrInReturnOutToBalanceAndVoucher(userVO, distrInReturnOut);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【配进退出出库】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【配进退出出库】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试【预付发票-保存或冲销】对财务余额表和凭证表的影响", notes = "测试【预付发票-保存或冲销】对财务余额表和凭证表的影响 | 开发者 李中义 | 待测试", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/prepayInvoiceToBalanceAndVoucherWhenSaveOrWriteOffTest", method = RequestMethod.POST)
    public Result<String> prepayInvoiceToBalanceAndVoucherWhenSaveOrWriteOffTest(@ApiIgnore UserVO userVO,
                                                                                 @ApiParam(value = "预付发票ID",required = true) @RequestParam("invoiceId") Long invoiceId,
                                                                                 @ApiParam(value = "save：保存；writeOff：冲销",required = true) @RequestParam("type") String type){
        Result<String> result = new Result<>();
        try{
            PrepayInvoice prepayInvoice = financeComponent.getPrepayInvoiceById(invoiceId);
            if(prepayInvoice == null){
                throw new BusinessException("没有获取到ID为"+prepayInvoice+"的【预付发票-保存或冲销】单信息！");
            }
            List<PrepayInvoiceDetail> prepayInvoiceDetailList = financeComponent.getPrepayInvoiceDetailList(invoiceId);
            financeComponent.prepayInvoiceToBalanceAndVoucherWhenSaveOrWriteOff(userVO, prepayInvoice, prepayInvoiceDetailList, type);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【预付发票-保存或冲销】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【预付发票-保存或冲销】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试【预付发票-对账】对财务余额表和凭证表的影响", notes = "测试【预付发票-对账】对财务余额表和凭证表的影响 | 开发者 李中义 | 已完成", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/prepayInvoiceToBalanceAndVoucherWhenCheckAccountTest", method = RequestMethod.POST)
    public Result<String> prepayInvoiceToBalanceAndVoucherWhenCheckAccountTest(@ApiIgnore UserVO userVO,
                                                                                 @ApiParam(value = "预付发票ID",required = true) @RequestParam("invoiceId") Long invoiceId){
        Result<String> result = new Result<>();
        try{
            PrepayInvoice prepayInvoice = financeComponent.getPrepayInvoiceById(invoiceId);
            if(prepayInvoice == null){
                throw new BusinessException("没有获取到ID为"+prepayInvoice+"的【预付发票-对账】单信息！");
            }
            List<PrepayInvoiceDetail> prepayInvoiceDetailList = financeComponent.getPrepayInvoiceDetailList(invoiceId);
            financeComponent.prepayInvoiceToBalanceAndVoucherWhenCheckAccount(userVO, prepayInvoice, prepayInvoiceDetailList);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【预付发票-对账】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【预付发票-对账】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试【应付发票-保存或冲销】对财务余额表和凭证表的影响", notes = "测试【应付发票-保存或冲销】对财务余额表和凭证表的影响 | 开发者 李中义 | 待测试", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/paymentInvoiceToBalanceAndVoucherWhenSaveOrWirteOffTest", method = RequestMethod.POST)
    public Result<String> paymentInvoiceToBalanceAndVoucherWhenSaveOrWirteOffTest(@ApiIgnore UserVO userVO,
                                                                               @ApiParam(value = "应付发票ID",required = true) @RequestParam("invoiceId") Long invoiceId,
                                                                               @ApiParam(value = "save：保存；writeOff：冲销",required = true) @RequestParam("type") String type){
        Result<String> result = new Result<>();
        try{
            PaymentInvoice paymentInvoice = financeComponent.getPaymentInvoiceById(invoiceId);
            if(paymentInvoice == null){
                throw new BusinessException("没有获取到ID为"+paymentInvoice+"的【应付发票-保存或冲销】单信息！");
            }
            List<PaymentInvoiceDetail> paymentInvoiceDetailList = financeComponent.getPaymentInvoiceDetailList(invoiceId);
            financeComponent.paymentInvoiceToBalanceAndVoucherWhenSaveOrWirteOff(userVO, paymentInvoice, paymentInvoiceDetailList, type);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【应付发票-保存或冲销】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【应付发票-保存或冲销】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试【应付贷项凭证-保存或冲销】对财务余额表和凭证表的影响",
            notes = "测试【应付贷项凭证-保存或冲销】对财务余额表和凭证表的影响 | 开发者 李中义 | 待测试", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/paymentVoucherToBalanceAndVoucherWhenSaveOrWirteOffTest", method = RequestMethod.POST)
    public Result<String> paymentVoucherToBalanceAndVoucherWhenSaveOrWirteOffTest(@ApiIgnore UserVO userVO,
                                                                                  @ApiParam(value = "应付贷项凭证ID",required = true) @RequestParam("voucherId") Long voucherId,
                                                                                  @ApiParam(value = "save：保存；writeOff：冲销",required = true) @RequestParam("type") String type){
        Result<String> result = new Result<>();
        try{
            PaymentVoucher paymentVoucher = financeComponent.getPaymentVoucherById(voucherId, userVO.getEnterpriseId());
            if(paymentVoucher == null){
                throw new BusinessException("没有获取到ID为"+paymentVoucher+"的【应付贷项凭证-保存或冲销】单信息！");
            }
            List<PaymentVoucherDetail> paymentInvoiceDetailList = financeComponent.getPaymentVoucherDetailList(voucherId, userVO.getEnterpriseId());
            financeComponent.paymentVoucherToBalanceAndVoucherWhenSaveOrWirteOff(userVO, paymentVoucher, paymentInvoiceDetailList, type);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【应付贷项凭证-保存或冲销】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【应付贷项凭证-保存或冲销】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试【预收发票-保存或冲销】对财务余额表和凭证表的影响",
            notes = "测试【预收发票-保存或冲销】对财务余额表和凭证表的影响 | 开发者 李中义 | 待测试", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/advanceReceivableInvoiceToBalanceAndVoucherWhenSaveOrWirteOff", method = RequestMethod.POST)
    public Result<String> advanceReceivableInvoiceToBalanceAndVoucherWhenSaveOrWirteOff(@ApiIgnore UserVO userVO,
                                                                                  @ApiParam(value = "预收发票ID",required = true) @RequestParam("invoiceId") Long invoiceId,
                                                                                  @ApiParam(value = "save：保存；writeOff：冲销",required = true) @RequestParam("type") String type){
        Result<String> result = new Result<>();
        try{
            AdvanceReceivableInvoice advanceReceivableInvoice = financeComponent.getAdvanceReceivableInvoiceById(invoiceId);
            if(advanceReceivableInvoice == null){
                throw new BusinessException("没有获取到ID为"+advanceReceivableInvoice+"的【预收发票-保存或冲销】单信息！");
            }
            List<AdvanceReceivableInvoiceDetail> advanceReceivableInvoiceDetailList = financeComponent.getAdvanceReceivableInvoiceDetailList(invoiceId);
            financeComponent.advanceReceivableInvoiceToBalanceAndVoucherWhenSaveOrWirteOff(userVO, advanceReceivableInvoice, advanceReceivableInvoiceDetailList, type);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【预收发票-保存或冲销】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【预收发票-保存或冲销】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试【应收发票-保存或冲销】对财务余额表和凭证表的影响",
            notes = "测试【应收发票-保存或冲销】对财务余额表和凭证表的影响 | 开发者 李中义 | 待测试", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/receivableInvoiceToBalanceAndVoucherWhenSaveOrWirteOffTest", method = RequestMethod.POST)
    public Result<String> receivableInvoiceToBalanceAndVoucherWhenSaveOrWirteOffTest(@ApiIgnore UserVO userVO,
                                                                                        @ApiParam(value = "应收发票ID",required = true) @RequestParam("invoiceId") Long invoiceId,
                                                                                        @ApiParam(value = "save：保存；writeOff：冲销",required = true) @RequestParam("type") String type){
        Result<String> result = new Result<>();
        try{
            ReceivableInvoice receivableInvoice = financeComponent.getReceivableInvoiceId(invoiceId);
            if(receivableInvoice == null){
                throw new BusinessException("没有获取到ID为"+receivableInvoice+"的【应收发票-保存或冲销】单信息！");
            }
            List<ReceivableInvoiceDetail> receivableInvoiceDetailList = financeComponent.getReceivableInvoiceDetailList(invoiceId);
            financeComponent.receivableInvoiceToBalanceAndVoucherWhenSaveOrWirteOff(userVO, receivableInvoice, receivableInvoiceDetailList, type);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【应收发票-保存或冲销】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【应收发票-保存或冲销】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }


    @ApiOperation(value = "测试【应收发票贷项凭证-保存或冲销】对财务余额表和凭证表的影响",
            notes = "测试【应收发票贷项凭证-保存或冲销】对财务余额表和凭证表的影响 | 开发者 李中义 | 待测试", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/receivableVoucherToBalanceAndVoucherWhenSaveOrWirteOffTest", method = RequestMethod.POST)
    public Result<String> receivableVoucherToBalanceAndVoucherWhenSaveOrWirteOffTest(@ApiIgnore UserVO userVO,
                                                                                     @ApiParam(value = "应收发票贷项凭证ID",required = true) @RequestParam("voucherId") Long voucherId,
                                                                                     @ApiParam(value = "save：保存；writeOff：冲销",required = true) @RequestParam("type") String type){
        Result<String> result = new Result<>();
        try{
            ReceivableVoucher receivableVoucher = financeComponent.getReceivableVoucherById(voucherId);
            if(receivableVoucher == null){
                throw new BusinessException("没有获取到ID为"+receivableVoucher+"的【应收发票贷项凭证-保存或冲销】单信息！");
            }
            List<ReceivableVoucherDetail> receivableVoucherDetailList = financeComponent.getReceivableVoucherDetailList(voucherId);
            financeComponent.receivableVoucherToBalanceAndVoucherWhenSaveOrWirteOff(userVO, receivableVoucher, receivableVoucherDetailList, type);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【应收发票贷项凭证-保存或冲销】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【应收发票贷项凭证-保存或冲销】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试【零售日结-保存或冲销】对财务余额表和凭证表的影响",
            notes = "测试【零售日结-保存或冲销】对财务余额表和凭证表的影响 | 开发者 李中义 | 待测试", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/retailDailySettleToBalanceAndVoucherWhenSaveOrWriteOffTest", method = RequestMethod.POST)
    public Result<String> retailDailySettleToBalanceAndVoucherWhenSaveOrWriteOffTest(@ApiIgnore UserVO userVO,
                                                                                     @ApiParam(value = "零售日结ID",required = true) @RequestParam("id") Long id,
                                                                                     @ApiParam(value = "交班单ID",required = true) @RequestParam("shiftId")Long shiftId,
                                                                                     @ApiParam(value = "save：保存；writeOff：冲销",required = true) @RequestParam("type") String type){
        Result<String> result = new Result<>();
        try{
            RetailDailySettle retailDailySettle = financeComponent.getRetailDailySettleById(id);
            if(retailDailySettle == null){
                throw new BusinessException("没有获取到ID为"+retailDailySettle+"的【零售日结-保存或冲销】单信息！");
            }
            List<SaleDetail> saleDetailList = financeComponent.getSaleDetailList(shiftId);
            financeComponent.retailDailySettleToBalanceAndVoucherWhenSaveOrWriteOff(userVO, retailDailySettle, saleDetailList, type);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【零售日结-保存或冲销】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【零售日结-保存或冲销】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试【零售缴款-保存或冲销】对财务余额表和凭证表的影响",
            notes = "测试【零售缴款-保存或冲销】对财务余额表和凭证表的影响 | 开发者 李中义 | 待测试", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/retailPaymentToBalanceAndVoucherWhenSaveOrWriteOffTest", method = RequestMethod.POST)
    public Result<String> retailPaymentToBalanceAndVoucherWhenSaveOrWriteOffTest(@ApiIgnore UserVO userVO,
                                                                                     @ApiParam(value = "零售缴款ID",required = true) @RequestParam("id") Long id,
                                                                                     @ApiParam(value = "save：保存；writeOff：冲销",required = true) @RequestParam("type") String type){
        Result<String> result = new Result<>();
        try{
            RetailPayment retailPayment = financeComponent.getRetailPaymentById(id);
            if(retailPayment == null){
                throw new BusinessException("没有获取到ID为"+retailPayment+"的【零售缴款-保存或冲销】单信息！");
            }
            financeComponent.retailPaymentToBalanceAndVoucherWhenSaveOrWriteOff(userVO, retailPayment, type);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【零售缴款-保存或冲销】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【零售缴款-保存或冲销】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试【财务付款-保存或冲销】对财务余额表和凭证表的影响",
            notes = "测试【财务付款-保存或冲销】对财务余额表和凭证表的影响 | 开发者 李中义 | 待测试", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/financePaymentToBalanceAndVoucherWhenSaveOrWriteOffTest", method = RequestMethod.POST)
    public Result<String> financePaymentToBalanceAndVoucherWhenSaveOrWriteOffTest(@ApiIgnore UserVO userVO,
                                                                                  @ApiParam(value = "财务付款ID",required = true) @RequestParam("id") Long id,
                                                                                  @ApiParam(value = "save：保存；writeOff：冲销",required = true) @RequestParam("type") String type){
        Result<String> result = new Result<>();
        try{
            FinancePayment financePayment = financeComponent.getFinancePaymentById(id);
            if(financePayment == null){
                throw new BusinessException("没有获取到ID为"+financePayment+"的【财务付款-保存或冲销】单信息！");
            }
            financeComponent.financePaymentToBalanceAndVoucherWhenSaveOrWriteOff(userVO, financePayment, type);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【财务付款-保存或冲销】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【财务付款-保存或冲销】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "测试【财务收款-保存或冲销】对财务余额表和凭证表的影响",
            notes = "测试【财务收款-保存或冲销】对财务余额表和凭证表的影响 | 开发者 李中义 | 待测试", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/financeReceivableToBalanceAndVoucherWhenSaveOrWriteOffTest", method = RequestMethod.POST)
    public Result<String> financeReceivableToBalanceAndVoucherWhenSaveOrWriteOffTest(@ApiIgnore UserVO userVO,
                                                                                  @ApiParam(value = "财务收款ID",required = true) @RequestParam("id") Long id,
                                                                                  @ApiParam(value = "save：保存；writeOff：冲销",required = true) @RequestParam("type") String type){
        Result<String> result = new Result<>();
        try{
            FinanceReceivable financeReceivable = financeComponent.getFinanceReceivableById(id);
            if(financeReceivable == null){
                throw new BusinessException("没有获取到ID为"+financeReceivable+"的【财务收款-保存或冲销】单信息！");
            }
            financeComponent.financeReceivableToBalanceAndVoucherWhenSaveOrWriteOff(userVO, financeReceivable, type);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("测试【财务收款-保存或冲销】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("测试【财务收款-保存或冲销】对财务余额表和凭证表的影响异常:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }




}
