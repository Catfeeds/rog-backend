package com.rograndec.feijiayun.chain.business.storage.inventory.excel;

import com.rograndec.feijiayun.chain.business.storage.inventory.constant.InvType;
import com.rograndec.feijiayun.chain.business.storage.inventory.dao.InventoryDetailMapper;
import com.rograndec.feijiayun.chain.business.storage.inventory.dao.InventoryShelfMapper;
import com.rograndec.feijiayun.chain.business.storage.inventory.entity.InventoryDetail;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.excel.GoodsShelfForRegisterExcelVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.excel.GoodsShelfForRegisterOKVO;
import com.rograndec.feijiayun.chain.business.system.opening.common.ExcelErrorCodeEnum;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.ExcelMethodUtils;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.excel.IRowReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 盘点登记信息导入行解析器
 * Created by ST on 2017/9/7.
 */
@Component
@Scope("prototype")
public class InventoryRegisterIRowReader extends IRowReader {

    private static final Logger logger = LoggerFactory.getLogger(InventoryRegisterIRowReader.class);

    private Integer invType;

    //盘点单id
    private Long invId;

    private UserVO userVO;
    //合格产品
    private List<GoodsShelfForRegisterExcelVO> qualifiedList;
    //不合格产品
    private List<GoodsShelfForRegisterExcelVO> disqualificationList;

    //标准入库实体list
    private List<GoodsShelfForRegisterOKVO> qualifiedGVOList;


    @Autowired
    private InventoryShelfMapper inventoryShelfMapper;

    @Autowired
    private InventoryDetailMapper inventoryDetailMapper;

    private List<Long> inventoryShelfIdList = new ArrayList<>();



    /**
     * 校验业务
     *
     * @param sheetIndex
     * @param curRow
     * @param rowList
     */
    @Override
    public void getRows(int sheetIndex, int curRow, List<String> rowList) {
        if (logger.isDebugEnabled()) {
            logger.debug("Sheet={},Row={},Data={}", sheetIndex, curRow, rowList);
        }
        int k = 0;
//        boolean isOK = true;
        if (curRow > 2) {//从第三行开始计算（excel行号从0开始）
            if (rowList != null && rowList.size() > 0) {
                String num = rowList.get(k++);//序号
                String goodsCode = rowList.get(k++);//商品编码*
                String genericName = rowList.get(k++);//通用名称*
                String specification = rowList.get(k++);//规格*
                String unitName = rowList.get(k++);//库存计量单位*
                String manufacturer = rowList.get(k++);//生产厂商*
                String place = rowList.get(k++);//产地*
                String lotNum = "";
                String productDate = "";
                String validUntil = "";
                String shelfName = "";
                if (invType == 0) {
                    lotNum = rowList.get(k++);//批号
                    productDate = rowList.get(k++);//生产日期
                    validUntil = rowList.get(k++);//有效期至*
                    shelfName = rowList.get(k++);//货位
                }
                String invQuantity = rowList.get(k);//实盘数量

                //excel操作的使用的商品实体
                GoodsShelfForRegisterExcelVO registerExcelVO = new GoodsShelfForRegisterExcelVO();
                //正确的数据按照能保存入库的方式构建
                GoodsShelfForRegisterOKVO registerOKVO = new GoodsShelfForRegisterOKVO();
                boolean isOK = true;
                GoodsShelfForRegisterOKVO stockGoodsForExcel = null;
                Long enterpriseId = userVO.getEnterpriseId();
                if(invType == InvType.SHELF.getCode()){
                    //按照货位盘点
                    stockGoodsForExcel = inventoryShelfMapper.getStockGoodsForExcel(enterpriseId,invId,goodsCode, lotNum, shelfName);
                    if(stockGoodsForExcel == null){
                        isOK = false;
                        registerExcelVO.setGoodsCode(ExcelErrorCodeEnum.INVENTORY_NOT_EXSIST.getName() + "--" + goodsCode);
                        registerExcelVO.setGoodsGenericName(genericName);
                        registerExcelVO.setUnitName(unitName);
                        registerExcelVO.setManufacturer(manufacturer);
                        registerExcelVO.setGoodsPlace(place);
                        registerExcelVO.setGoodsSpecification(specification);
                        registerExcelVO.setInvQuantity(invQuantity);
                        registerExcelVO.setLotNumber(lotNum);
                        registerExcelVO.setProductDate(productDate);
                        registerExcelVO.setValidDate(validUntil);
                    } else {
                        if(inventoryShelfIdList.contains(stockGoodsForExcel.getInventoryShelfId())){
                            //商品重复
                            isOK = false;
                            registerExcelVO.setGoodsCode(ExcelErrorCodeEnum.INVENTORY_GOODS_REPEAT.getName() + "--" + goodsCode);
                        } else {
                            //正常的商品
                            inventoryShelfIdList.add(stockGoodsForExcel.getInventoryShelfId());

                            InventoryDetail inventoryDetailByParam = inventoryDetailMapper.getInventoryDetailByParam(enterpriseId, invId, null, goodsCode);


                            registerExcelVO.setGoodsCode(inventoryDetailByParam.getGoodsCode());
                            registerExcelVO.setGoodsGenericName(inventoryDetailByParam.getGoodsGenericName());
                            registerExcelVO.setUnitName(inventoryDetailByParam.getUnitName());
                            registerExcelVO.setManufacturer(inventoryDetailByParam.getManufacturer());
                            registerExcelVO.setGoodsPlace(inventoryDetailByParam.getGoodsPlace());
                            registerExcelVO.setDosageName(inventoryDetailByParam.getDosageName());
                            registerExcelVO.setGoodsSpecification(inventoryDetailByParam.getGoodsSpecification());
                            registerExcelVO.setInvQuantity(invQuantity);
                            registerExcelVO.setLotNumber(stockGoodsForExcel.getLotNumber());
                            registerExcelVO.setValidDate(DateUtils.DateToString(stockGoodsForExcel.getValidDate(), DateStyle.YYYY_MM_DD_HH_MM_SS));
                            registerExcelVO.setProductDate(DateUtils.DateToString(stockGoodsForExcel.getProductDate(), DateStyle.YYYY_MM_DD_HH_MM_SS));
                            registerExcelVO.setShelfName(stockGoodsForExcel.getShelfName());




                            //标准入库的商品信息
                            registerOKVO.setGoodsCode(inventoryDetailByParam.getGoodsCode());
                            registerOKVO.setGoodsId(inventoryDetailByParam.getId());
                            registerOKVO.setGoodsGenericName(inventoryDetailByParam.getGoodsGenericName());
                            registerOKVO.setUnitName(inventoryDetailByParam.getUnitName());
                            registerOKVO.setUnitId(inventoryDetailByParam.getUnitId());
                            registerOKVO.setManufacturerId(inventoryDetailByParam.getManufacturerId());
                            registerOKVO.setManufacturer(inventoryDetailByParam.getManufacturer());
                            registerOKVO.setGoodsPlace(inventoryDetailByParam.getGoodsPlace());
                            registerOKVO.setDosageName(inventoryDetailByParam.getDosageName());
                            registerOKVO.setDosageId(inventoryDetailByParam.getDosageId());
                            registerOKVO.setGoodsSpecification(inventoryDetailByParam.getGoodsSpecification());
                            registerOKVO.setQuantity(stockGoodsForExcel.getQuantity());
                            registerOKVO.setLotId(stockGoodsForExcel.getLotId());
                            registerOKVO.setLotNumber(stockGoodsForExcel.getLotNumber());
                            registerOKVO.setValidDate(stockGoodsForExcel.getValidDate());
                            registerOKVO.setProductDate(stockGoodsForExcel.getProductDate());
                            registerOKVO.setShelfName(stockGoodsForExcel.getShelfName());
                            registerOKVO.setShelfId(stockGoodsForExcel.getShelfId());
                            registerOKVO.setInventoryShelfId(stockGoodsForExcel.getInventoryShelfId());
                            registerOKVO.setId(stockGoodsForExcel.getId());
                            //
                            if(ExcelMethodUtils.isPureDigital(invQuantity) || ExcelMethodUtils.isDecimals(invQuantity)){
                                registerExcelVO.setInvQuantity(invQuantity);
                                registerOKVO.setInvQuantity(new BigDecimal(invQuantity));
                            } else {
                                //false
                                isOK = false;
                                registerExcelVO.setInvQuantity(ExcelErrorCodeEnum.NUMBER_FORMAT.getName());
                            }

                        }
                    }


                } else if(invType == InvType.GOODS.getCode()){
                    //按照商品

                    InventoryDetail inventoryDetailByParam = inventoryDetailMapper.getInventoryDetailByParam(enterpriseId, invId, null, goodsCode);

                    if(inventoryDetailByParam != null){

                        registerExcelVO.setGoodsCode(inventoryDetailByParam.getGoodsCode());
                        registerExcelVO.setGoodsGenericName(inventoryDetailByParam.getGoodsGenericName());
                        registerExcelVO.setUnitName(inventoryDetailByParam.getUnitName());
                        registerExcelVO.setManufacturer(inventoryDetailByParam.getManufacturer());
                        registerExcelVO.setGoodsPlace(inventoryDetailByParam.getGoodsPlace());
                        registerExcelVO.setDosageName(inventoryDetailByParam.getDosageName());
                        registerExcelVO.setGoodsSpecification(inventoryDetailByParam.getGoodsSpecification());
                        registerExcelVO.setInvQuantity(invQuantity);

                        if(inventoryShelfIdList.contains(inventoryDetailByParam.getGoodsId())){
                            //商品重复
                            isOK = false;
                            registerExcelVO.setGoodsCode(ExcelErrorCodeEnum.INVENTORY_GOODS_REPEAT.getName() + "--" + goodsCode);
                        } else {
                            //正常
                            inventoryShelfIdList.add(inventoryDetailByParam.getGoodsId());

                            //标准入库的商品信息
                            registerOKVO.setGoodsCode(inventoryDetailByParam.getGoodsCode());
                            registerOKVO.setGoodsId(inventoryDetailByParam.getId());
                            registerOKVO.setGoodsGenericName(inventoryDetailByParam.getGoodsGenericName());
                            registerOKVO.setUnitName(inventoryDetailByParam.getUnitName());
                            registerOKVO.setUnitId(inventoryDetailByParam.getUnitId());
                            registerOKVO.setManufacturerId(inventoryDetailByParam.getManufacturerId());
                            registerOKVO.setManufacturer(inventoryDetailByParam.getManufacturer());
                            registerOKVO.setGoodsPlace(inventoryDetailByParam.getGoodsPlace());
                            registerOKVO.setDosageName(inventoryDetailByParam.getDosageName());
                            registerOKVO.setDosageId(inventoryDetailByParam.getDosageId());
                            registerOKVO.setGoodsSpecification(inventoryDetailByParam.getGoodsSpecification());
                            registerOKVO.setId(inventoryDetailByParam.getId());

                            //
                            if(ExcelMethodUtils.isPureDigital(invQuantity)){
                                registerExcelVO.setInvQuantity(invQuantity);
                                registerOKVO.setInvQuantity(new BigDecimal(invQuantity));
                            } else {
                                //false
                                isOK = false;
                                registerExcelVO.setInvQuantity(ExcelErrorCodeEnum.NUMBER_FORMAT.getName());
                            }

                        }
                    } else {
                        isOK = false;
                        registerExcelVO.setGoodsCode(ExcelErrorCodeEnum.INVENTORY_NOT_EXSIST.getName() + "--" + goodsCode);
                        registerExcelVO.setGoodsCode(goodsCode);
                        registerExcelVO.setGoodsGenericName(genericName);
                        registerExcelVO.setUnitName(unitName);
                        registerExcelVO.setManufacturer(manufacturer);
                        registerExcelVO.setGoodsPlace(place);
                        registerExcelVO.setInvQuantity(invQuantity);

                    }

                }

                if (isOK) {
                    qualifiedList.add(registerExcelVO);
                    qualifiedGVOList.add(registerOKVO);
                } else {
                    disqualificationList.add(registerExcelVO);
                }
            }
        }
    }


    public List<GoodsShelfForRegisterExcelVO> getQualifiedList() {
        return qualifiedList;
    }

    public void setQualifiedList(List<GoodsShelfForRegisterExcelVO> qualifiedList) {
        this.qualifiedList = qualifiedList;
    }

    public List<GoodsShelfForRegisterExcelVO> getDisqualificationList() {
        return disqualificationList;
    }

    public void setDisqualificationList(List<GoodsShelfForRegisterExcelVO> disqualificationList) {
        this.disqualificationList = disqualificationList;
    }

    public void setQualifiedGVOList(List<GoodsShelfForRegisterOKVO> qualifiedGVOList) {
        this.qualifiedGVOList = qualifiedGVOList;
    }



    public List<GoodsShelfForRegisterOKVO> getQualifiedGVOList() {
        return qualifiedGVOList;
    }
    public Integer getInvType() {
        return invType;
    }

    public void setInvType(Integer invType) {
        this.invType = invType;
    }


    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public Long getInvId() {
        return invId;
    }

    public void setInvId(Long invId) {
        this.invId = invId;
    }

    public List<Long> getInventoryShelfIdList() {
        return inventoryShelfIdList;
    }

    public void setInventoryShelfIdList(List<Long> inventoryShelfIdList) {
        this.inventoryShelfIdList = inventoryShelfIdList;
    }


}