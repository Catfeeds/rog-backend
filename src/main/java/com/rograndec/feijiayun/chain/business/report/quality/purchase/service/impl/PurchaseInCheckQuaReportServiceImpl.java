package com.rograndec.feijiayun.chain.business.report.quality.purchase.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsCategoryMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsCategory;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inCheckQua.RequestGetInCheckQuaParamVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.PurchaseInCheckQuaReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.inCheckQua.PurchaseInCheckQuaGoodsReportVO;
import com.rograndec.feijiayun.chain.business.system.set.dao.QualitySetMapper;
import com.rograndec.feijiayun.chain.business.system.set.vo.QualitySetVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.ExcelComponent;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.StorageTemp;
import com.rograndec.feijiayun.chain.common.file.dao.FileMapper;
import com.rograndec.feijiayun.chain.common.file.entity.File;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

/**
 * 功能描述：门店配进退出
 * Created by ST on 2017/10/19 13:44
 */
@Service
public class PurchaseInCheckQuaReportServiceImpl implements PurchaseInCheckQuaReportService {


    @Autowired
    private ExcelComponent excelComponent;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;
    @Autowired
    private QualitySetMapper qualitySetMapper;

    @Autowired
    private FileMapper fileMapper;


    @Override
    public void getInCheckQuaGoodsList(Page<List<PurchaseInCheckQuaGoodsReportVO>> page, RequestGetInCheckQuaParamVO getStorageParamVO, UserVO userVO) {

        List<QualitySetVO> qualitySetVOList = qualitySetMapper.getQualitySetVOList(userVO.getEnterpriseId(), null, null, EnableStatus.ENABLE.getStatus());
        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Long enterpriseId = userVO.getEnterpriseId();
        List<PurchaseInCheckQuaGoodsReportVO> inCheckQuaGoodsReportVOS = getInCheckQuaTotalVO(getStorageParamVO, enterpriseId,qualitySetVOList);
        page.setResult(inCheckQuaGoodsReportVOS);
        page.setTotalRecord((int) objects.getTotal());
    }

    @Override
    public void exportInCheckQuaGoodsList(OutputStream output, UserVO userVO, RequestGetInCheckQuaParamVO paramForListVO) throws Exception {
        Long enterpriseId = userVO.getEnterpriseId();
        List<QualitySetVO> qualitySetVOList = qualitySetMapper.getQualitySetVOList(enterpriseId, null, null, EnableStatus.ENABLE.getStatus());
        List<PurchaseInCheckQuaGoodsReportVO> inCheckQuaGoodsReportVOS = getInCheckQuaTotalVO(paramForListVO, enterpriseId,qualitySetVOList);

        String firstTitle = userVO.getEnterpriseName();
        String secondTitle = "总部药品验收资质单";
        LinkedHashMap<String,String> headerHashMap = new LinkedHashMap<>();
        headerHashMap.put("checkDate","日期");
        headerHashMap.put("code","单号");
        headerHashMap.put("supplierCode","配货单位编码");
        headerHashMap.put("goodsCode","商品编码");
        headerHashMap.put("barcode","条形码");
        headerHashMap.put("goodsGenericName","通用名称");
        headerHashMap.put("goodsName","商品名称");
        headerHashMap.put("dosageName","剂型");
        headerHashMap.put("goodsSpecification","规格");
        headerHashMap.put("unitName","单位");
        headerHashMap.put("manufacturer","生产厂商");
        headerHashMap.put("goodsPlace","产地");
        headerHashMap.put("approvalNumber","批准文号");
        headerHashMap.put("lotNum","批号");
        headerHashMap.put("productDate","生产日期");
        headerHashMap.put("validDate","有效期至");
        headerHashMap.put("checkProjectIdNames","验收项目");

        headerHashMap.put("returnOutCount","品种类型");
        headerHashMap.put("categoryName","商品分类");
        headerHashMap.put("goodsAttributeDetail","商品属性");
        headerHashMap.put("domesticImportString","国产/进口");
        headerHashMap.put("managementScopeName","经营范围");

        headerHashMap.put("specialDrugDetail","特殊管理药品");
        headerHashMap.put("inChargeDrugDetail","专管药品");
        headerHashMap.put("limitQuantity","限购数量");
        headerHashMap.put("storageTempString","贮藏温度");

        headerHashMap.put("storageConditionName","贮藏条件");
        headerHashMap.put("qualityPeriod","保质期");


        excelComponent.exportExcel(output,firstTitle,secondTitle,new ArrayList<>(),headerHashMap,inCheckQuaGoodsReportVOS,null);
    }


    private List<PurchaseInCheckQuaGoodsReportVO> getInCheckQuaTotalVO(RequestGetInCheckQuaParamVO getStorageParamVO, Long enterpriseId,List<QualitySetVO> qualitySetVOList) {

        List<PurchaseInCheckQuaGoodsReportVO> inCheckQuaGoodsReportVOS = goodsMapper.getPurchaseInCheckQuaGoodsReport(getStorageParamVO, enterpriseId);


        for (PurchaseInCheckQuaGoodsReportVO item : inCheckQuaGoodsReportVOS) {
            item.converTOStr(item);
            //商品分类
            Long categoryId = item.getCategoryId();
            GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(categoryId);
            if(goodsCategory != null){
                item.setCategoryName(goodsCategory.getName());
            }

            String checkProjectIds = item.getCheckProjectIds();//检验项目集合

            if(checkProjectIds != null){
                String[] checkProjectIdArr = checkProjectIds.split(",");

                Stream.of(checkProjectIdArr).forEach((String project) ->{
                    String name = qualitySetVOList.stream().filter(q->project.equals(q.getId()+"")).findFirst().orElse(new QualitySetVO()).getDescription();
                    String names = item.getCheckProjectNames();
                    if(name != null){
                        item.setCheckProjectNames(names == null ? name : names + "," + name);
                    }
                });
            }


            String testReportIds = item.getTestReportIds();
            if(testReportIds != null) {
                if (testReportIds.contains(":")) {
                    StringBuilder sb = new StringBuilder();
                    if(testReportIds.contains(",")) {
                        String[] testReportIdsArr = testReportIds.split(",");
                        for (int i = 0; i < testReportIdsArr.length; i++) {
                            String[] project2ReportArr = testReportIdsArr[i].split(":");
                            //File file = fileMapper.selectByPrimaryKey(Long.valueOf(project2ReportArr[1]));
//                            if (file == null) {
//                                throw new BusinessException("文件ID:" + project2ReportArr[1] + "没有记录!");
//                            }
                            sb.append(project2ReportArr[1]);
                            //sb.append(":");
                            //sb.append(file.getFileName());
                            if(i != testReportIdsArr.length-1){
                                sb.append(",");
                            }
                        }
                    } else {
                        String[] project2ReportArr = testReportIds.split(":");
//                        File file = fileMapper.selectByPrimaryKey(Long.valueOf(project2ReportArr[1]));
//                        if (file == null) {
//                            throw new BusinessException("文件ID:" + project2ReportArr[1] + "没有记录!");
//                        }
                        sb.append(project2ReportArr[1]);
//                        sb.append(":");
//                        sb.append(file.getFileName());
                    }
                    item.setTestReportIds(sb.toString());
                }
            }
            int storageTemp = item.getStorageTemp();
            //贮藏温度
            item.setStorageTempString(String.valueOf(storageTemp) == null?null: StorageTemp.getName(storageTemp));
        }

        return inCheckQuaGoodsReportVOS;
    }


}
