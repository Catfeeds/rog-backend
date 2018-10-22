package com.rograndec.feijiayun.chain.business.report.quality.purchase.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsQualificationConfigMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsQualificationConfigVO;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsCategoryMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsCategory;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.FirstGoodsReviewReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.goods.FirstGoodsReviewPageVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.goods.FirstGoodsVO;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.business.system.approval.dao.ApprovalFlowActionDetailMapper;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowActionDetail;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
import com.rograndec.feijiayun.chain.utils.param.ParamUtils;
import com.rograndec.feijiayun.chain.utils.string.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FirstGoodsReviewReportServiceImpl implements FirstGoodsReviewReportService{

    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsQualificationConfigMapper goodsQualificationConfigMapper;
    @Autowired
    ApprovalFlowActionDetailMapper approvalFlowActionDetailMapper;
    @Autowired
    PurchaseGeneralComponent purchaseGeneralComponent;
    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public Page<List<FirstGoodsVO>> getGoodsInfo(UserVO loginUser, FirstGoodsReviewPageVO pageVO) throws Exception {
        //企业ID,加盟店允许查看总部和仅属于本门店的首营品种审批

        pageVO.setChainType(loginUser.getChainType());
        ParamUtils.packParam(loginUser, pageVO);

        Page<List<FirstGoodsVO>> page = new Page<>(pageVO.getPageNo(), pageVO.getPageSize());
        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<Goods> goodsList = goodsMapper.getFirstGoodsReviewReport(pageVO);
        List<FirstGoodsVO> goodsVOS = getFirstGoodsVOList(goodsList);
        page.setResult(goodsVOS);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
        page.setTotalPage(hPage.getPages());
        return page;
    }

    private List<FirstGoodsVO> getFirstGoodsVOList(List<Goods> goodsList) throws Exception{
        List<FirstGoodsVO> goodsVOS = new ArrayList<>();
        for(Goods goods : goodsList){
            FirstGoodsVO goodsVO = new FirstGoodsVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goods,goodsVO);
            goodsVO.setBusinessVarietyName(BusinessVariety.getName(goodsVO.getBusinessVariety()));
            //商品分类
            GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(goods.getCategoryId());
            goodsVO.setCategoryName(goodsCategory==null?null:goodsCategory.getName());
            //商品属性
            goodsVO.setGoodsAttributeName(goodsVO.getGoodsAttribute()==null?null:GoodsAttribute2DrugsA.getName(goodsVO.getGoodsAttribute()));
            //国产/进口
            goodsVO.setDomesticImportName(goodsVO.getDomesticImport()==null?null: DomesticImport.getName(goodsVO.getDomesticImport()));
            //特殊管理药品
            goodsVO.setSpecialDrugName(goodsVO.getSpecialDrug()==null?null: SpecialDrugs.getName(goodsVO.getSpecialDrug()));
            //专管药品
            goodsVO.setInChargeDrugName(goodsVO.getInChargeDrug()==null?null:ChargeDrugs.getName(goodsVO.getInChargeDrug()));
            //贮藏温度
            goodsVO.setStorageTempName(goodsVO.getStorageTemp()==null?null:StorageTemp.getName(goodsVO.getStorageTemp()));
            //保质期
            goodsVO.setQualityPeriodUnitName(goodsVO.getQualityPeriod().toString()+GoodsQualityPeriodUnit.getName(goodsVO.getQualityPeriodUnit()));
            goodsVOS.add(goodsVO);
        }
        return goodsVOS;
    }

    @Override
    public FirstGoodsVO getGoodsInfoDetail(UserVO loginUser, Long goodsId) throws Exception {
        return getFirstGoodsVO(loginUser, goodsId);

    }

    private FirstGoodsVO getFirstGoodsVO(UserVO loginUser, Long goodsId) throws Exception {
        //企业ID,加盟店允许查看总部和仅属于本门店的首营品种审批
        FirstGoodsReviewPageVO pageVO = new FirstGoodsReviewPageVO();
        pageVO.setGoodsId(goodsId);
        pageVO.setChainType(loginUser.getChainType());
        ParamUtils.packParam(loginUser, pageVO);

        //查询商品资质信息 并set
        List<Goods> goodsList = goodsMapper.getFirstGoodsReviewReport(pageVO);
        if(goodsList.isEmpty()) throw new Exception("查无此商品");
        FirstGoodsVO firstGoodsVO = new FirstGoodsVO();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsList.get(0),firstGoodsVO);
        List<GoodsQualificationConfigVO> goodsQualificationConfigVOS = goodsQualificationConfigMapper.getQualificationConfigList(firstGoodsVO.getId());
        firstGoodsVO.setGoodsQualificationConfigVOList(goodsQualificationConfigVOS==null?new ArrayList<>():goodsQualificationConfigVOS);
        List<ApprovalFlowActionDetail> approvalFlowActionDetails = approvalFlowActionDetailMapper.selectFirstApprovalAction(goodsList.get(0).getEnterpriseId(),"首营品种审批",firstGoodsVO.getId(), ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue());
        if(!CollectionUtils.isEmpty(approvalFlowActionDetails)){
            Long approvalFlowActionId = approvalFlowActionDetails.get(0).getApprovalFlowActionId();
            approvalFlowActionDetails = approvalFlowActionDetails.stream().filter(item->item.getApprovalFlowActionId().equals(approvalFlowActionId)).collect(Collectors.toList());
            firstGoodsVO.setApprovalFlowActionDetails(approvalFlowActionDetails);
        }else {
            firstGoodsVO.setApprovalFlowActionDetails(new ArrayList<>());
        }
        return firstGoodsVO;
    }

    @Override
    public void exportExcel(OutputStream output, UserVO loginUser,FirstGoodsReviewPageVO pageVO) throws Exception {
        //企业ID,加盟店允许查看总部和仅属于本门店的首营品种审批
        pageVO.setChainType(loginUser.getChainType());
        ParamUtils.packParam(loginUser, pageVO);
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("businessVarietyName","品种类型");
        map.put("categoryName","商品分类");
        map.put("code","商品编码");
        map.put("barcode","条形码");
        map.put("genericName","通用名称");
        map.put("name","商品名称");
        map.put("dosageName","剂型");
        map.put("specification","规格");
        map.put("unitName","单位");
        map.put("manufacturer","生产厂商");
        map.put("place","产地");
        map.put("approvalNumber","批准文号");
        map.put("validUntil","有效期至");
        map.put("goodsAttributeName","商品属性");
        map.put("domesticImportName","国产/进口");
        map.put("managementScopeName","经营范围");
        map.put("specialDrugName","特殊管理药品");
        map.put("inChargeDrugName","专管药品");
        map.put("limitQuantity","限购数量");
        map.put("storageTempName","贮藏温度");
        map.put("storageConditionName","贮藏条件");
        map.put("qualityPeriodUnitName","保质期");
        List<String> titleSecond = new ArrayList<>();
        StringBuilder end = new StringBuilder();
        List<String> needTotalName = new ArrayList<>();
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("首营品种");
        pageVO.setPageNo(null);
        pageVO.setPageSize(null);
        List<Goods> goodsList = goodsMapper.getFirstGoodsReviewReport(pageVO);
        purchaseGeneralComponent.commExcelExport(output,map,getFirstGoodsVOList(goodsList),name,titleSecond,end.toString(),false,needTotalName);
    }



    @Override
    public void exportDetailExcel(OutputStream output, UserVO loginUser, Long goodsId) throws Exception {
        FirstGoodsVO firstGoodsVO = getFirstGoodsVO(loginUser, goodsId);
        ExcelWriter writer = new ExcelWriter() {
            public void generate() throws Exception {
                int kk = 0;
                // 电子表格开始
                this.beginWorkSheet();
                this.beginSheet();
                Map<String, XSSFCellStyle> styleMap = this.getCellStyles();
                short cellStrIndex = styleMap.get("cell_string").getIndex();
                //标题行
                this.insertRow(kk++);
                this.createCell(0, loginUser.getEnterpriseName(),cellStrIndex);
                this.endRow();
                //表名
                this.insertRow(kk++);
                this.createCell(0,"首营品种审批表",cellStrIndex);
                this.endRow();
                //第一行
                this.insertRow(kk++);
                this.createCell(0, "编码",cellStrIndex);
                this.createCell(1, StringUtil.transferTrimStr(firstGoodsVO.getCode()),cellStrIndex);
                this.createCell(2,"通用名称",cellStrIndex);
                this.createCell(3,StringUtil.transferTrimStr(firstGoodsVO.getGenericName()),cellStrIndex);
                this.endRow();
                //第二行
                this.insertRow(kk++);
                this.createCell(0, "条形码",cellStrIndex);
                this.createCell(1, StringUtil.transferTrimStr(firstGoodsVO.getBarcode()),cellStrIndex);
                this.createCell(2,"商品名称",cellStrIndex);
                this.createCell(3,StringUtil.transferTrimStr(firstGoodsVO.getName()),cellStrIndex);
                this.endRow();
                //第三行
                this.insertRow(kk++);
                this.createCell(0, "剂型：",cellStrIndex);
                this.createCell(1, StringUtil.transferTrimStr(firstGoodsVO.getDosageName()),cellStrIndex);
                this.createCell(2, "规格：",cellStrIndex);
                this.createCell(3, StringUtil.transferTrimStr(firstGoodsVO.getSpecification()),cellStrIndex);
                this.createCell(2, "单位：",cellStrIndex);
                this.createCell(3, StringUtil.transferTrimStr(firstGoodsVO.getUnitName()),cellStrIndex);
                this.endRow();
                //第四行
                this.insertRow(kk++);
                this.createCell(0, "生产厂商：" ,cellStrIndex);
                this.createCell(1,  StringUtil.transferTrimStr(firstGoodsVO.getManufacturer()),cellStrIndex);
                this.createCell(2, "产地：",cellStrIndex);
                this.createCell(3, StringUtil.transferTrimStr(firstGoodsVO.getPlace()),cellStrIndex);
                this.createCell(4, "国产/进口：",cellStrIndex);
                this.createCell(5, StringUtil.transferTrimStr(firstGoodsVO.getDomesticImportName()),cellStrIndex);
                this.endRow();
                //第五行
                this.insertRow(kk++);
                this.createCell(0, "批准文号：" ,cellStrIndex);
                this.createCell(1,StringUtil.transferTrimStr(firstGoodsVO.getApprovalNumber()),cellStrIndex);
                this.createCell(2, "有效期至：" ,cellStrIndex);
                this.createCell(3,StringUtil.transferTrimStr(firstGoodsVO.getValidUntil()),cellStrIndex);
                this.createCell(4, "经营范围：",cellStrIndex);
                this.createCell(5,StringUtil.transferTrimStr(firstGoodsVO.getManagementScopeName()),cellStrIndex);
                this.endRow();
                //第六行
                this.insertRow(kk++);
                this.createCell(0, "特殊管理药品：" ,cellStrIndex);
                this.createCell(1,StringUtil.transferTrimStr(firstGoodsVO.getSpecialDrugName()),cellStrIndex);
                this.createCell(2, "专管药品：" ,cellStrIndex);
                this.createCell(3,StringUtil.transferTrimStr(firstGoodsVO.getInChargeDrugName()),cellStrIndex);
                this.createCell(4, "限购数量：",cellStrIndex);
                this.createCell(5,StringUtil.transferTrimStr(firstGoodsVO.getLimitQuantity()),cellStrIndex);
                this.endRow();
                //第7行
                this.insertRow(kk++);
                this.createCell(0, "贮藏温度：" ,cellStrIndex);
                this.createCell(1,StringUtil.transferTrimStr(firstGoodsVO.getStorageTempName()),cellStrIndex);
                this.createCell(2, "贮藏条件：" ,cellStrIndex);
                this.createCell(3,StringUtil.transferTrimStr(firstGoodsVO.getStorageConditionName()),cellStrIndex);
                this.createCell(4, "保质期：" ,cellStrIndex);
                this.createCell(5,StringUtil.transferTrimStr(firstGoodsVO.getQualityPeriod()+firstGoodsVO.getQualityPeriodUnit()),cellStrIndex);
                this.endRow();
                //第8行
                this.insertRow(kk++);
                this.createCell(0, "商品属性：" ,cellStrIndex);
                this.createCell(1,StringUtil.transferTrimStr(firstGoodsVO.getGoodsAttributeName()),cellStrIndex);
                this.endRow();
                //第9行
                this.insertRow(kk++);
                this.createCell(0, "企业资质" ,cellStrIndex);
                this.createCell(1,"资质名称",cellStrIndex);
                this.createCell(2, "资质编号",cellStrIndex);
                this.createCell(3,"有效期至",cellStrIndex);
                this.endRow();
                int qcK = 10;
                //企业资质
                List<GoodsQualificationConfigVO> qualificationConfigVOS = firstGoodsVO.getGoodsQualificationConfigVOList();
                for (GoodsQualificationConfigVO configVO : qualificationConfigVOS) {
                    qcK++;
                    this.insertRow(kk++);
                    this.createCell(0, "企业资质" ,cellStrIndex);
                    this.createCell(1,StringUtil.transferTrimStr(configVO.getQualificationName()),cellStrIndex);
                    this.createCell(2, StringUtil.transferTrimStr(configVO.getQualificationCode()),cellStrIndex);
                    this.createCell(3,StringUtil.transferTrimStr(configVO.getValidUntil()),cellStrIndex);
                    this.endRow();
                }
                this.insertRow(kk++);
                int businessRow = kk;
                this.createCell(0, "业务部门意见" ,cellStrIndex);
                this.createCell(1, "申请意见" ,cellStrIndex);
                this.endRow();

                this.insertRow(kk++);
                this.createCell(0, "业务部门意见" ,cellStrIndex);
                this.endRow();
                this.insertRow(kk++);
                this.createCell(0, "业务部门意见" ,cellStrIndex);
                this.createCell(2,"申请日期：" ,cellStrIndex);
                this.createCell(3, StringUtil.transferTrimStr(firstGoodsVO.getApplicationTime()),cellStrIndex);
                this.createCell(4,"申请人员：" ,cellStrIndex);
                this.createCell(5, StringUtil.transferTrimStr(firstGoodsVO.getApplicantName()),cellStrIndex);
                this.endRow();

                //审批
                List<ApprovalFlowActionDetail> approvalFlowActionDetailList = firstGoodsVO.getApprovalFlowActionDetails();
                int approvalRow = 0;//审批合并行号
                for (ApprovalFlowActionDetail flowActionDetail : approvalFlowActionDetailList) {
                    this.insertRow(kk++);
                    this.createCell(0, StringUtil.transferTrimStr(flowActionDetail.getApprovalStage()),cellStrIndex);
                    this.createCell(1,"审批意见：",cellStrIndex);
                    this.endRow();

                    this.insertRow(kk++);
                    this.createCell(1, StringUtil.transferTrimStr(flowActionDetail.getApprovalOpinion()),cellStrIndex);
                    this.endRow();
                    this.insertRow(kk++);
                    this.createCell(2,"审批日期：");
                    this.createCell(3, StringUtil.transferTrimStr(flowActionDetail.getApprovalTime()),cellStrIndex);
                    this.createCell(4,"审批人员：");
                    this.createCell(5, StringUtil.transferTrimStr(flowActionDetail.getApproverName()),cellStrIndex);
                    this.endRow();
                    approvalRow = kk;
                }
                // sheet表格结束
                this.endSheet();
//                合并单元格
                this.beginMergerCell();
                //标题列合并
                this.setMergeCell(0,0,0,5);
                //名字合并
                this.setMergeCell(1,0,1,5);
                //第一列合并编码列
                this.setMergeCell(2,3,2,5);
                //第二列合并条形码列
                this.setMergeCell(3,3,3,5);
                //第八列合并商品属性列
                this.setMergeCell(9,1,0,5);

//                this.setMergeCell(8, 0, qcK, 0);
//                this.setMergeCell(qcK + 1, 0, qcK + 5, 0);
                //业务部门意见合并
                this.setMergeCell(businessRow-1, 0, businessRow + 1, 0);
//                this.setMergeCell(businessRow + 1, 1, businessRow + 1, 5);
//                this.setMergeCell(businessRow + 2, 1, businessRow + 2, 5);

                //审批表格合并
//                int flowK = (qcK + 5 + 3 ) + 1;
                int flowK = approvalRow + 1;
                int tmp = 0;
                for(int g = 0; g < approvalFlowActionDetailList.size(); g++){
                    this.setMergeCell(flowK + tmp, 0, flowK  + 2 + tmp, 0);

                    this.setMergeCell(flowK  + tmp, 2, flowK + tmp, 5);

                    this.setMergeCell(flowK  + 1 + tmp, 1, flowK  + 1 + tmp, 5);
                    tmp+=3;
                }
                this.endMergerCell();
                this.endWorkSheet();

            }

        };
        writer.process(output);
    }
}
