package com.rograndec.feijiayun.chain.business.purchase.instorage.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseCargoAreaMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseCargoArea;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.purchase.check.dao.PurchaseCheckMapper;
import com.rograndec.feijiayun.chain.business.purchase.check.entity.PurchaseCheck;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageShelfMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorage;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageDetail;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageShelf;
import com.rograndec.feijiayun.chain.business.purchase.instorage.service.PurchaseInStorageService;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.*;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderOtherMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrder;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderOther;
import com.rograndec.feijiayun.chain.business.purchase.receive.dao.PurchaseReceiveMapper;
import com.rograndec.feijiayun.chain.business.purchase.receive.dao.PurchaseReceiveOtherMapper;
import com.rograndec.feijiayun.chain.business.purchase.receive.entity.PurchaseReceive;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.ExcelComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseExcelComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class PurchaseInStorageServiceImpl implements PurchaseInStorageService{

    @Autowired
    private PurchaseInStorageMapper purchaseInStorageMapper;

    @Autowired
    private PurchaseInStorageShelfMapper purchaseInStorageShelfMapper;

    @Autowired
    private PurchaseInStorageDetailMapper purchaseInStorageDetailMapper;

    @Autowired
    private PurchaseReceiveOtherMapper purchaseReceiveOtherMapper;

    @Autowired
    private PurchaseReceiveMapper purchaseReceiveMapper;

    @Autowired
    private PurchaseOrderOtherMapper purchaseOrderOtherMapper;

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    @Autowired
    private PurchaseCheckMapper purchaseCheckMapper;

    @Autowired
    private WarehouseShelfMapper warehouseShelfMapper;

    @Autowired
    private WarehouseCargoAreaMapper warehouseCargoAreaMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Override
    public Page getHasBeenInstoragePage(Page page, Date startDate, Date endDate, String supplierCode, String supplierName, String code, String storageManName, String order, String sort, Long enterpriseId) {
        if("inStorageDate".equals(order)){
            order = "in_storage_date";
        }else if("code".equals(order)){
            order = "code";
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("supplierCode",supplierCode);
        map.put("supplierName",supplierName);
        map.put("code",code);
        map.put("storageManName",storageManName);
        map.put("order",order);
        map.put("sort",sort);
        map.put("enterpriseId",enterpriseId);
        map.put("pageSize",page.getPageSize());
        map.put("start",(page.getPageNo()-1)*page.getPageSize());
        List<PurchaseInStorage> list = purchaseInStorageMapper.selectHasBeenInstoragePage(map);
        List<HasBeenInstoragePageVO> convertList = new ArrayList<HasBeenInstoragePageVO>();
        HasBeenInstoragePageVO hasBeenInstorage = new HasBeenInstoragePageVO();
        //统计当前页合计金额(如果前端要这个数据，就传给他)
        BigDecimal totalMoney = new BigDecimal(0);
        //统计当前页不含税合计金额(如果前端要这个数据，就传给他)
        BigDecimal noTaxTotalMoney = new BigDecimal(0);
        //当前税额合计
        BigDecimal taxTotalMoney = new BigDecimal(0);
        PurchaseInStorageStasticVO stasticVO = purchaseInStorageMapper.selectStasticMoney(map);
        for (PurchaseInStorage purchase : list) {
            hasBeenInstorage = HasBeenInstoragePageVO.convertToVO(purchase);
            if (stasticVO == null){
                hasBeenInstorage.setPageRealAmountTotal(BigDecimal.ZERO);
                hasBeenInstorage.setPageNotaxRealAmountTotal(BigDecimal.ZERO);
                hasBeenInstorage.setPagetaxAmountTotal(BigDecimal.ZERO);
            }else {
                hasBeenInstorage.setPageRealAmountTotal(stasticVO.getTotalMoney());
                hasBeenInstorage.setPageNotaxRealAmountTotal(stasticVO.getNoTaxTotalMoney());
                hasBeenInstorage.setPagetaxAmountTotal(stasticVO.getTaxTotalMoney());
            }
            convertList.add(hasBeenInstorage);
        }
        page.setResult(convertList);
        Integer totalRecord = purchaseInStorageMapper.getTotalRecord(map);
        page.setTotalRecord(totalRecord);
        return page;
    }

    @Override
    public HasBeenInstorageFormVO getHasBeenInstorageForm(Long enterpriseId, Long id) {
        PurchaseInStorage purchase = purchaseInStorageMapper.selectByPrimaryKey(id);
        //基本参数获得
        HasBeenInstorageFormVO hasBeenInstorageFormVO = new HasBeenInstorageFormVO();
        List<PurchaseInStorageDetail> detailList = purchaseInStorageDetailMapper.selectByEnterpriseIdAndId(enterpriseId,id);
        if (purchase != null){
            hasBeenInstorageFormVO = HasBeenInstorageFormVO.convertToPurchaseInStorageVO(purchase);
            List<PurchaseInstorageDetailVO> detailVOList = new ArrayList<PurchaseInstorageDetailVO>();
            PurchaseInstorageDetailVO purchaseInstorageDetailVO = new PurchaseInstorageDetailVO();
            for (PurchaseInStorageDetail pd : detailList) {
                //这时插入的VO还差明货位明细的list集合
                purchaseInstorageDetailVO = purchaseInstorageDetailVO.convertToVO(pd);
                //插入到明细中的货位明细List
                //#####################################
                //获得入库货位相关明细
                List<PurchaseInStorageShelf> purchaseInStorageShelf = purchaseInStorageShelfMapper.selectByEnterpriseIdAndId(enterpriseId,pd.getId());
                //需要放入最终VO的入库明细List
                List<PurchaseInstorageShelfVO> purchaseInstorageShelfVOList = new ArrayList<PurchaseInstorageShelfVO>();
                if (purchaseInStorageShelf != null){
                    for (PurchaseInStorageShelf ps : purchaseInStorageShelf) {
                        //获得货位对象
                        WarehouseShelf warehouseShelf = new WarehouseShelf();
                        if (ps != null){
                            warehouseShelf = warehouseShelfMapper.selectByPrimaryKey(ps.getShelfId());
                        }
                        WarehouseCargoArea warehouseCargoArea = new WarehouseCargoArea();
                        if (warehouseShelf != null){
                            warehouseCargoArea = warehouseCargoAreaMapper.selectByPrimaryKey(warehouseShelf.getCargoAreaId());
                        }
                        PurchaseInStorageDetail purchaseInStorageDetail = new PurchaseInStorageDetail();
                        if (ps != null){
                            purchaseInStorageDetail = purchaseInStorageDetailMapper.selectByPrimaryKey(ps.getInStorageDtlId());
                        }
                        PurchaseInstorageShelfVO purchaseInstorageShelfVO = new PurchaseInstorageShelfVO();
                        if (purchaseInStorageDetail != null){
                            purchaseInstorageShelfVO = PurchaseInstorageShelfVO.convertToVO(ps,purchaseInStorageDetail,warehouseCargoArea);
                        }
                        purchaseInstorageShelfVOList.add(purchaseInstorageShelfVO);
                    }
                }
                //#####################################
                purchaseInstorageDetailVO.setPurchaseInstorageShelfVO(purchaseInstorageShelfVOList);
                detailVOList.add(purchaseInstorageDetailVO);
            }
            hasBeenInstorageFormVO.setPurchaseInstorageDetailVO(detailVOList);
        }
        //从采购订单中获得采购人名称以及订单单号(以及其他基本信息)
        PurchaseOrder purchaseOrder = null;
        PurchaseOrderOther purchaseOrderOther = null;
        PurchaseReceive purchaseReceive = null;
        PurchaseCheck purchaseCheck = null;
        if (purchase != null && purchase.getPurchaseOrderId() != null){
            purchaseOrder = purchaseOrderMapper.selectByPrimaryKey(purchase.getPurchaseOrderId());
            //获取合同单号
            purchaseOrderOther = purchaseOrderOtherMapper.selectByEnterPriseId(enterpriseId,purchase.getPurchaseOrderId());
            //获取收货人员
            purchaseReceive = purchaseReceiveMapper.selectByEnterpriseId(enterpriseId,purchase.getPurchaseOrderId());
        }
        if (purchaseReceive != null){
            //货区验收人员
            purchaseCheck = purchaseCheckMapper.selectByEnterpriseId(enterpriseId,purchaseReceive.getId());
        }
        PurchaseOrderOtherVO purchaseOrderOtherVO = PurchaseOrderOtherVO.convertToVO(purchaseOrder,purchaseOrderOther,purchaseReceive,purchaseCheck);
        hasBeenInstorageFormVO.setPurchaseOrderOtherVO(purchaseOrderOtherVO);
        return hasBeenInstorageFormVO;
    }

    @Override
    public void exportExcel(OutputStream output, HasBeenInstorageFormVO hasBeenInstorageFormVO, UserVO loginUser) {
        //转换一下显示日期
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("goodsCode","商品编码");
        map.put("goodsName","通用名称");
        map.put("dosageName","剂型");
        map.put("goodsSpecification","规格");
        map.put("manufacturer","生产厂商");
        map.put("unitName","单位");
        map.put("jobArea","质量状况");
        map.put("lotNumber","批号");
        map.put("productDate","生产日期");
        map.put("validDate","有效期至");
        map.put("shelfName","货位");
        //#####
        map.put("quantity","数量");
        map.put("unitPrice","单价");
        map.put("lineDiscount","折扣");
        map.put("amount","金额");
        map.put("taxRate","税率");
        map.put("notaxRealPrice","不含税单价");
        map.put("notaxRealAmount","不含税金额");
        map.put("taxAmount","税额");
        map.put("remark","备注");
        List<PurchaseInstorageExcelVO> contextList = new ArrayList<PurchaseInstorageExcelVO>();
        List<PurchaseInstorageDetailVO> purchaseInstorageDetailVO = hasBeenInstorageFormVO.getPurchaseInstorageDetailVO();
        if (purchaseInstorageDetailVO != null && purchaseInstorageDetailVO.size() > 0) {
            for (PurchaseInstorageDetailVO detailVO : purchaseInstorageDetailVO) {
                List<PurchaseInstorageShelfVO> purchaseInstorageShelfVO = detailVO.getPurchaseInstorageShelfVO();
                for (PurchaseInstorageShelfVO shelf : purchaseInstorageShelfVO) {
                    PurchaseInstorageExcelVO excelVO = new PurchaseInstorageExcelVO();
                    excelVO = PurchaseInstorageExcelVO.convertTO(shelf,detailVO);
                    contextList.add(excelVO);
                }
            }
        }
        List<String> secondTitle = new ArrayList<String>();
        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("供货单位编码:");
        titleSecondRow.append(hasBeenInstorageFormVO.getSupplierCode() == null ? "":hasBeenInstorageFormVO.getSupplierCode());
        titleSecondRow.append("  供货单位名称:");
        titleSecondRow.append(hasBeenInstorageFormVO.getSupplierName() == null ? "":hasBeenInstorageFormVO.getSupplierName());
        titleSecondRow.append("  供货单位销售人员:");
        titleSecondRow.append(hasBeenInstorageFormVO.getSupplierSalerName() == null ? "":hasBeenInstorageFormVO.getSupplierSalerName());
        titleSecondRow.append("  联系电话:");
        titleSecondRow.append(hasBeenInstorageFormVO.getSupplierSalerPhone() == null ? "":hasBeenInstorageFormVO.getSupplierSalerPhone());
        titleSecondRow.append("  入库日期:");
        titleSecondRow.append(hasBeenInstorageFormVO.getInStorageDate() == null ? "":new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(hasBeenInstorageFormVO.getInStorageDate()));
        titleSecondRow.append("  入库人员:");
        titleSecondRow.append(hasBeenInstorageFormVO.getStorageManName() == null ? "":hasBeenInstorageFormVO.getStorageManName());
        titleSecondRow.append("  流通监管码:");
        titleSecondRow.append(hasBeenInstorageFormVO.getFlowCode() == null ? "":hasBeenInstorageFormVO.getFlowCode());
        secondTitle.add(titleSecondRow.toString());
        StringBuilder end = new StringBuilder();
        end.append(hasBeenInstorageFormVO.getQuantityTotal() == null ? 0L : hasBeenInstorageFormVO.getQuantityTotal());
        end.append("件商品  ");
        end.append("总金额为：");
        end.append(hasBeenInstorageFormVO.getAmountTotal());
        //每行以分号隔开
        end.append(";");
        end.append("折扣：");
        end.append(hasBeenInstorageFormVO.getWholeDiscount());
        end.append("%");
        end.append(";");
        end.append("优惠：");
        end.append(hasBeenInstorageFormVO.getWholeDiscountAmount());
        end.append(";");
        end.append("总额：");
        end.append(hasBeenInstorageFormVO.getRealAmountTotal());
        end.append(";");
        end.append("不含税总额：");
        end.append(hasBeenInstorageFormVO.getNotaxRealAmountTotal());
        end.append(";");
        end.append("税额：");
        end.append(hasBeenInstorageFormVO.getTaxAmountTotal());
        end.append(";");
        List<String> name = new ArrayList<String>();
//      //第一行的企业名
        name.add(enterpriseMapper.selectByPrimaryKey(purchaseInStorageMapper.selectByPrimaryKey(hasBeenInstorageFormVO.getId()).getEnterpriseId()).getName());
        //第二行的
        name.add("采购入库单");
        //purchaseExcelComponent.commExcelExport(output, map, contextList,name,titleSecondRow.toString(),end.toString());
        purchaseGeneralComponent.commExcelExport(output,map,contextList,name,secondTitle,end.toString(),true,new ArrayList<String>());
    }

    @Override
    public StayInstorageSaveVO getHasBeenInstorage(Long enterpriseId, Long id) {
        PurchaseInStorage purchase = purchaseInStorageMapper.selectByPrimaryKey(id);
        //基本参数获得
        StayInstorageSaveVO stayInstorageSaveVO = new StayInstorageSaveVO();
        List<PurchaseInStorageDetail> detailList = purchaseInStorageDetailMapper.selectByEnterpriseIdAndId(enterpriseId,id);
        if (purchase != null){
            stayInstorageSaveVO = StayInstorageSaveVO.convertToPurchaseInStorageVO(purchase);
            List<StayInstorageDetailSaveVO> detailVOList = new ArrayList<StayInstorageDetailSaveVO>();
            StayInstorageDetailSaveVO stayInstorageDetailSaveVO = new StayInstorageDetailSaveVO();
            for (PurchaseInStorageDetail pd : detailList) {
                //这时插入的VO还差明货位明细的list集合
                stayInstorageDetailSaveVO = StayInstorageDetailSaveVO.convertToVO(pd);
                //插入到明细中的货位明细List
                //#####################################
                //获得入库货位相关明细
                List<PurchaseInStorageShelf> purchaseInStorageShelf = purchaseInStorageShelfMapper.selectByEnterpriseIdAndId(enterpriseId,pd.getId());
                //需要放入最终VO的入库明细List
                List<CheckLotDetailVO> checkLotDetailVOList = new ArrayList<CheckLotDetailVO>();
                if (purchaseInStorageShelf != null){
                    for (PurchaseInStorageShelf ps : purchaseInStorageShelf) {
                        //获得货位对象
                        WarehouseShelf warehouseShelf = new WarehouseShelf();
                        if (ps != null){
                            warehouseShelf = warehouseShelfMapper.selectByPrimaryKey(ps.getShelfId());
                        }
                        WarehouseCargoArea warehouseCargoArea = new WarehouseCargoArea();
                        if (warehouseShelf != null){
                            warehouseCargoArea = warehouseCargoAreaMapper.selectByPrimaryKey(warehouseShelf.getCargoAreaId());
                        }
                        PurchaseInStorageDetail purchaseInStorageDetail = new PurchaseInStorageDetail();
                        if (ps != null){
                            purchaseInStorageDetail = purchaseInStorageDetailMapper.selectByPrimaryKey(ps.getInStorageDtlId());
                        }
                        CheckLotDetailVO checkLotDetailVO = new CheckLotDetailVO();
                        if (purchaseInStorageDetail != null){
                            checkLotDetailVO = CheckLotDetailVO.convertToVO(ps,purchaseInStorageDetail,warehouseCargoArea);
                        }
                        checkLotDetailVOList.add(checkLotDetailVO);
                    }
                }
                //#####################################
                stayInstorageDetailSaveVO.setCheckLotDetailList(checkLotDetailVOList);
                detailVOList.add(stayInstorageDetailSaveVO);
            }
            stayInstorageSaveVO.setStayInstorageDetailSaveVO(detailVOList);
        }
        //从采购订单中获得采购人名称以及订单单号(以及其他基本信息)
        PurchaseOrder purchaseOrder = null;
        PurchaseOrderOther purchaseOrderOther = null;
        PurchaseReceive purchaseReceive = null;
        PurchaseCheck purchaseCheck = null;
        if (purchase != null && purchase.getPurchaseOrderId() != null){
            purchaseOrder = purchaseOrderMapper.selectByPrimaryKey(purchase.getPurchaseOrderId());
            //获取合同单号
            purchaseOrderOther = purchaseOrderOtherMapper.selectByEnterPriseId(enterpriseId,purchase.getPurchaseOrderId());
            //获取收货人员
            purchaseReceive = purchaseReceiveMapper.selectByEnterpriseId(enterpriseId,purchase.getPurchaseOrderId());
        }
        if (purchaseReceive != null){
            //货区验收人员
            purchaseCheck = purchaseCheckMapper.selectByEnterpriseId(enterpriseId,purchaseReceive.getId());
        }
        PurchaseOrderOtherVO purchaseOrderOtherVO = PurchaseOrderOtherVO.convertToVO(purchaseOrder,purchaseOrderOther,purchaseReceive,purchaseCheck);
        stayInstorageSaveVO.setPurchaseOrderOtherVO(purchaseOrderOtherVO);
        return stayInstorageSaveVO;
    }
}
