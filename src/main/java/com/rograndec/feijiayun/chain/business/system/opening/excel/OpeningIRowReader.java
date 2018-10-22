package com.rograndec.feijiayun.chain.business.system.opening.excel;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.supplier.service.SupplierService;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.basic.warehouse.service.WarehouseService;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.service.GoodsService;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsTaxRateVO;
import com.rograndec.feijiayun.chain.business.system.opening.common.ExcelErrorCodeEnum;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpeningGoodsExcelVO;
import com.rograndec.feijiayun.chain.common.component.CalculateComponent;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.CommonParamSupplierAndGoods;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.ExcelMethodUtils;
import com.rograndec.feijiayun.chain.utils.excel.IRowReader;
import com.rograndec.feijiayun.chain.utils.param.ParamUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * 期初庫存的行讀取器
 * Created by ST on 2017/8/22.
 */
@Component
@Scope("prototype")
public class OpeningIRowReader extends IRowReader {

    private static final Logger logger = LoggerFactory.getLogger(OpeningIRowReader.class);

    @Autowired
    private CommonComponent commonComponent;
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private SupplierService supplierService;

    //合格产品
    private List<OpeningGoodsExcelVO> qualifiedList;
    //不合格产品
    private List<OpeningGoodsExcelVO> disqualificationList;

    //税收
    private List<GoodsTaxRateVO> taxRateVOList;


//    private Long enterpriseId;
//
//    private Integer chainType;

    private UserVO userVO;

    public OpeningIRowReader() {
    }

    @Override
    public void getRows(int sheetIndex, int curRow, List<String> rowList) {
        if(logger.isDebugEnabled()){
            logger.debug("Sheet={},Row={},Data={}",sheetIndex,curRow,rowList);
        }
        int k = 0;
        boolean isOk = true;
        if(curRow > 1){//从第三行开始计算（excel行号从0开始）
            if(rowList != null && rowList.size() > 0){
                String code = rowList.get(k++);//商品编码
                String oldCode = rowList.get(k++);//原商品编码
                String genericName = rowList.get(k++);//通用名称*
                String dosage = rowList.get(k++);//剂型*
                String unit = rowList.get(k++);//单位*
                String specification = rowList.get(k++);//规格
                String manufacturer = rowList.get(k++);//生成商
                String lotNum = rowList.get(k++);//批号*
                String productDate = rowList.get(k++);//生成日期*
                String validUntil = rowList.get(k++);//有效期至*
                String quantity = rowList.get(k++);//数量*
                String realPrice = rowList.get(k++);//单价*
                String taxRate = rowList.get(k++);//税率*
                String shelf = rowList.get(k++);//货位*
                String retailPrice = rowList.get(k++);//零售单价*
                String memberPrice = rowList.get(k++);//会员单价*
                String supplierCode = rowList.get(k++);//供货单位编码*
                String supplierName =  rowList.get(k);//供货单位名称*

                OpeningGoodsExcelVO goodsVO = new OpeningGoodsExcelVO();
                goodsVO.setCode(code);
                goodsVO.setOldCode(oldCode);
                goodsVO.setGenericName(genericName);
                goodsVO.setDosageName(dosage);
                goodsVO.setUnitName(unit);
                goodsVO.setSpecification(specification);
                goodsVO.setManufacturer(manufacturer);
                goodsVO.setLotNumber(lotNum);
                goodsVO.setProductDate(productDate);
                goodsVO.setValidDate(validUntil);
                goodsVO.setQuantity(quantity);
                goodsVO.setUnitPrice(realPrice);
                //金额
                if(StringUtils.isNoneBlank(taxRate)){
                    if(taxRate.contains("%")){
                        taxRate = taxRate.substring(0,taxRate.length()-1);
                    }
                    BigDecimal decimalTaxRate = new BigDecimal(taxRate);
                    Optional<GoodsTaxRateVO> first = taxRateVOList.stream().filter(item -> item.getTaxRate().compareTo(decimalTaxRate) == 0).findFirst();
                    if(first.isPresent()){
                        goodsVO.setTaxRate(taxRate);
                        goodsVO.setTaxRateId(first.get().getId());
                    } else {
                        isOk = false;
                        goodsVO.setTaxRate(ExcelErrorCodeEnum.TAXRATE_NOT_EXSIST.getName());
                    }
                } else {
                    taxRate = "0";
                    BigDecimal decimalTaxRate = new BigDecimal(taxRate);
                    Optional<GoodsTaxRateVO> first = taxRateVOList.stream().filter(item -> item.getTaxRate().compareTo(decimalTaxRate) == 0).findFirst();
                    if(first.isPresent()){
                        goodsVO.setTaxRate(taxRate);
                        goodsVO.setTaxRateId(first.get().getId());
                    } else {
                        isOk = false;
                        goodsVO.setTaxRate(ExcelErrorCodeEnum.TAXRATE_NOT_EXSIST.getName());
                    }
                }


                goodsVO.setShelfName(shelf);
                goodsVO.setRetailPrice(StringUtils.isBlank(retailPrice) ? "0" : retailPrice);
                goodsVO.setMemberPrice(StringUtils.isBlank(memberPrice) ? "0" : memberPrice);
                goodsVO.setSupplierCode(supplierCode);
                goodsVO.setSupplierName(supplierName);


                if(StringUtils.isBlank(code) && StringUtils.isBlank(oldCode)){
                    //都为空，为不合格数据
                    isOk = false;
                    goodsVO.setCode(ExcelErrorCodeEnum.BLANK.getName());
                    goodsVO.setOldCode(ExcelErrorCodeEnum.BLANK.getName());
                } else {
                    if(StringUtils.isBlank(code) && StringUtils.isNoneBlank(oldCode)){
                        //商品编码为空，原始编码不为空
                        Goods goods = goodsService.getGoodsByOldCode(oldCode,ChainType.getHeadEnterpriseId(userVO));
                        if(goods != null){
                            isOk = setGoodsVOProperty(isOk, goodsVO, goods);
                        } else {
                            //根据原始编码未查询到商品信息，说明此原商品编码无效
                            goodsVO.setOldCode(ExcelErrorCodeEnum.INVALID_OLD_CODE.getName());
                            isOk = false;

                        }
                    } else if(StringUtils.isNoneBlank(code)){
                        //商品编号不为空
                        CommonParamSupplierAndGoods paramSupplierAndGoods = new CommonParamSupplierAndGoods();
                        ParamUtils.packParam(userVO,paramSupplierAndGoods);
                        Goods goods = goodsService.getGoodsByCode2(paramSupplierAndGoods,userVO,code);
                        if(goods == null){
                            goodsVO.setCode(ExcelErrorCodeEnum.INVALID_CODE.getName());
                            isOk = false;
                        } else {
                            isOk = setGoodsVOProperty(isOk, goodsVO, goods);
                        }
                    }
                }

                isOk = checkParameter(isOk,lotNum, productDate, validUntil, quantity, realPrice, shelf, supplierCode, taxRate,goodsVO);

                if(isOk){
                    BigDecimal amount = new BigDecimal(quantity).multiply(new BigDecimal(realPrice));
                    goodsVO.setAmount(amount);//数量*单价
                    //goodsVO.setTaxRate(taxRate);
//                    //不含税单价
//                    BigDecimal notaxParice = new BigDecimal(realPrice).divide(new BigDecimal(100).add(new BigDecimal(taxRate)),6,BigDecimal.ROUND_HALF_UP);

//                    //不含税金额
//                    BigDecimal notaxAmount = notaxParice.multiply(new BigDecimal(quantity));
//                    goodsVO.setNotaxAmount(notaxAmount);

                    //不含税金额
                    BigDecimal notaxAmount = BigDecimal.ZERO;
                    //不含税单价
                    BigDecimal notaxPrice = BigDecimal.ZERO;
                    if(taxRate != null && taxRate.equals(BigDecimal.ZERO)){
                        notaxAmount = amount;
                        notaxPrice = new BigDecimal(realPrice);
                    }else{
                        notaxAmount = CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(amount,new BigDecimal(taxRate));
                        notaxPrice = CalculateComponent.getNotaxPriceByNotaxAmountAndQuantity(notaxAmount, new BigDecimal(quantity));
                    }
                    goodsVO.setNotaxPrice(notaxPrice);
                    goodsVO.setNotaxAmount(notaxAmount);
                    //税额
                    goodsVO.setTaxAmount(amount.subtract(notaxAmount));
                }

                if(isOk){
                    qualifiedList.add(goodsVO);
                } else {
                    disqualificationList.add(goodsVO);
                }
            }
        }


    }


    /**
     * 根據查詢的商品信息設置goodsVO
     * @param isOk
     * @param goodsVO
     * @param goods
     * @return
     */
    private boolean setGoodsVOProperty(boolean isOk, OpeningGoodsExcelVO goodsVO, Goods goods) {
        if(StringUtils.isBlank(goods.getCode())){
            //
            isOk = false;
        } else {
            //设置从数据库中查询的商品编码
            goodsVO.setCode(goods.getCode());
            goodsVO.setGenericName(goods.getGenericName());
            //根据查询出的商品记录设置该行记录的的指定的基本属性
            goodsVO.setDosageName(goods.getDosageName());
            goodsVO.setUnitName(goods.getUnitName());
            goodsVO.setSpecification(goods.getSpecification());
            goodsVO.setManufacturer(goods.getManufacturer());
            goodsVO.setGoodsId(goods.getId());
            goodsVO.setName(goods.getName());
        }
        return isOk;
    }

    private boolean checkParameter(boolean isOk,String lotNum, String productDate, String validUntil, String quantity, String realPrice, String shelf, String supplierCode,String taxRate, OpeningGoodsExcelVO goodsVO) {
        if(StringUtils.isNotBlank(lotNum)){
            if(lotNum.length() > 20){
                //批号长度不应大于20
                isOk = false;
                goodsVO.setLotNumber(ExcelErrorCodeEnum.MAXVALUE.getName());
            }
        } else {
            isOk = false;
            goodsVO.setLotNumber(ExcelErrorCodeEnum.BLANK.getName());
        }

        if(StringUtils.isBlank(productDate)){
            isOk = false;
            goodsVO.setProductDate(ExcelErrorCodeEnum.BLANK.getName());
        } else {
            if(!ExcelMethodUtils.checkPattern(productDate)){
                isOk = false;
                goodsVO.setProductDate(ExcelErrorCodeEnum.DATE_FORMAT.getName());
            } else {
                goodsVO.setProductDate(ExcelMethodUtils.getDate(productDate));
            }
        }
        if(StringUtils.isBlank(validUntil)){
            isOk = false;
            goodsVO.setValidDate(ExcelErrorCodeEnum.BLANK.getName());
        } else {
            if(!ExcelMethodUtils.checkPattern(validUntil)){
                isOk = false;
                goodsVO.setValidDate(ExcelErrorCodeEnum.DATE_FORMAT.getName());
            } else {
                goodsVO.setValidDate(ExcelMethodUtils.getDate(validUntil));
            }
        }
        if(!ExcelMethodUtils.isDecimals(quantity)){
            isOk = false;
            goodsVO.setQuantity(ExcelErrorCodeEnum.NUMBER_FORMAT.getName());
        }
        if(!ExcelMethodUtils.isDecimals(realPrice)){
            isOk = false;
            goodsVO.setUnitPrice(ExcelErrorCodeEnum.NUMBER_FORMAT.getName());
        }
        if(StringUtils.isBlank(shelf)){
            isOk = false;
            goodsVO.setShelfName(ExcelErrorCodeEnum.BLANK.getName());
        } else {
            WarehouseShelf warehouseShelf = warehouseService.getWarehouseByName(shelf,userVO.getEnterpriseId());
            if(warehouseShelf == null){
                isOk = false;
                goodsVO.setShelfName(ExcelErrorCodeEnum.NO_SHELF.getName());
            } else {
                goodsVO.setShelfId(warehouseShelf.getId());
                goodsVO.setShelfName(warehouseShelf.getName());
            }
        }

        if(StringUtils.isNoneBlank(supplierCode)){
            //填写了供货单位
            if(userVO.getChainType() == ChainType.Division.getCode()){
                //加盟店
                if("0000".equals(supplierCode)){
                    //总部的编码都是0000
                    goodsVO.setSupplierId(userVO.getParentId());
                    goodsVO.setSupplierCode(userVO.getParentCode());
                    goodsVO.setSupplierName(userVO.getParentName());
                    goodsVO.setType(1);
                } else {
                    CommonParamSupplierAndGoods paramSupplierAndGoods = new CommonParamSupplierAndGoods();
                    ParamUtils.packParam(userVO,paramSupplierAndGoods);
                    Supplier supplier = supplierService.getSupplierByCode2(supplierCode,paramSupplierAndGoods);
                    if(supplier == null){
                        isOk = false;
                        goodsVO.setSupplierCode(ExcelErrorCodeEnum.NO_SUPPLiER.getName());
                    } else {
                        goodsVO.setType(2);
                        goodsVO.setSupplierId(supplier.getId());
                        goodsVO.setSupplierCode(supplier.getCode());
                        goodsVO.setSupplierName(supplier.getName());
                    }
                }
            } else {
                //总部 和 自营店
                Supplier supplier = supplierService.getSupplierByCode(supplierCode,ChainType.getHeadEnterpriseId(userVO));
                if(supplier == null){
                    isOk = false;
                    goodsVO.setSupplierCode(ExcelErrorCodeEnum.NO_SUPPLiER.getName());
                } else {
                    goodsVO.setType(2);
                    goodsVO.setSupplierId(supplier.getId());
                    goodsVO.setSupplierCode(supplier.getCode());
                    goodsVO.setSupplierName(supplier.getName());
                }
            }
        }

        return isOk;
    }


    public List<OpeningGoodsExcelVO> getQualifiedList() {
        return qualifiedList;
    }

    public void setQualifiedList(List<OpeningGoodsExcelVO> qualifiedList) {
        this.qualifiedList = qualifiedList;
    }

    public List<OpeningGoodsExcelVO> getDisqualificationList() {
        return disqualificationList;
    }

    public void setDisqualificationList(List<OpeningGoodsExcelVO> disqualificationList) {
        this.disqualificationList = disqualificationList;
    }

    public List<GoodsTaxRateVO> getTaxRateVOList() {
        return taxRateVOList;
    }

    public void setTaxRateVOList(List<GoodsTaxRateVO> taxRateVOList) {
        this.taxRateVOList = taxRateVOList;
    }


    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }
}