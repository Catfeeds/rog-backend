package com.rograndec.feijiayun.chain.business.report.quality.purchase.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierBusinessMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierQualificationConfigMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierBusiness;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierDetailVO;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierSalerVO;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.QualificationConfigVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.service.SupplierFirstApproReportService;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.RequestParamSupplierReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.SupplierFirstApproReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.SupplierQualificationReportVO;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.business.system.approval.dao.ApprovalFlowActionDetailMapper;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowActionDetail;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.ExcelComponent;
import com.rograndec.feijiayun.chain.common.component.SupplierComponent;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
import com.rograndec.feijiayun.chain.utils.param.ParamUtils;
import com.rograndec.feijiayun.chain.utils.string.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 功能描述：
 * Created by ST on 2017/10/19 13:44
 */
@Service
public class SupplierFirstApproReportServiceImpl implements SupplierFirstApproReportService{


    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private ExcelComponent excelComponent;

    @Autowired
    private SupplierQualificationConfigMapper supplierQualificationConfigMapper;

    @Autowired
    private SupplierComponent supplierComponent;

    @Autowired
    private ApprovalFlowActionDetailMapper approvalFlowActionDetailMapper;

    @Autowired
    private SupplierBusinessMapper supplierBusinessMapper;


    @Override
    public void getSupplierFirstApproList(Page<List<SupplierDetailVO>> page, RequestParamSupplierReportVO supplierReportVO, UserVO userVO){
        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        ParamUtils.packParam(userVO,supplierReportVO);
        List<SupplierDetailVO> supplierFileReportDetailVOS = getSupplierFirstReportDetailVOS(supplierReportVO, userVO);
        page.setResult(supplierFileReportDetailVOS);
        page.setTotalRecord((int) objects.getTotal());
    }

    private List<SupplierDetailVO> getSupplierFirstReportDetailVOS(RequestParamSupplierReportVO supplierReportVO, UserVO userVO) {

        Integer status = EnableStatus.ENABLE.getStatus();
        List<SupplierQualificationReportVO> reportVOS = supplierMapper.getSupplierQualificationReportList(supplierReportVO,status);
        List<SupplierDetailVO> supplierDetailVOS = new ArrayList<>();
        reportVOS.forEach(item->{
            Long id = item.getId();
            supplierDetailVOS.add(supplierComponent.refactorSupplierDetail(id));
        });
        return supplierDetailVOS;
    }

    /**
     *
     * @param userVO
     * @param supplierId
     */
    @Override
    public SupplierFirstApproReportVO getSupplierFirstApproDetail(UserVO userVO, Long supplierId){
        Long enterpriseId = userVO.getEnterpriseId();
        Integer status = EnableStatus.ENABLE.getStatus();
        SupplierDetailVO detailSupplier = supplierComponent.refactorSupplierDetail(supplierId);
        SupplierFirstApproReportVO supplierFirstApproReportVO = null;
        if(detailSupplier != null){
            enterpriseId = detailSupplier.getEnterpriseId();
            supplierFirstApproReportVO = new SupplierFirstApproReportVO();
            BeanUtils.copyProperties(detailSupplier,supplierFirstApproReportVO);
            //quality_manage_sys 质量管理体系
            SupplierBusiness supplierBusiness = supplierBusinessMapper.getSupplierBuseinessBySupplierId(supplierId);
            supplierFirstApproReportVO.setQualityManageSys(supplierBusiness.getQualityManageSys());
            supplierFirstApproReportVO.setApplicantName(supplierBusiness.getApplicantName());
            supplierFirstApproReportVO.setApplicationTime(supplierBusiness.getApplicationTime());
            //设置企业资质
            List<QualificationConfigVO> qualificationConfigReport = supplierQualificationConfigMapper.getQualificationConfigReport(supplierId);
            supplierFirstApproReportVO.setQualificationConfigVOS(qualificationConfigReport);
            //设置销售人员
            List<SupplierSalerVO> supplierSalerVOList = supplierComponent.getSupplierSalerVOS(enterpriseId,status,supplierId);
            SupplierSalerVO salerVO = supplierSalerVOList.stream().filter(item->item.getId().equals(detailSupplier.getSaleManId())).findFirst().orElse(new SupplierSalerVO());
            supplierFirstApproReportVO.setSupplierSalerVO(salerVO);
            //审批流
            List<ApprovalFlowActionDetail> approvalFlowActionDetailList = approvalFlowActionDetailMapper.selectFirstApprovalAction(enterpriseId, "首营企业审批", supplierId, ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue());
            if(!CollectionUtils.isEmpty(approvalFlowActionDetailList)){
                Long approvalFlowActionId = approvalFlowActionDetailList.get(0).getApprovalFlowActionId();
                approvalFlowActionDetailList = approvalFlowActionDetailList.stream().filter(item->item.getApprovalFlowActionId().equals(approvalFlowActionId)).collect(Collectors.toList());
                supplierFirstApproReportVO.setApprovalFlowActionDetailList(approvalFlowActionDetailList);
            }


        }
        return supplierFirstApproReportVO;

    }

    @Override
    public void exportSupplierFirstApproList(OutputStream output, UserVO userVO, RequestParamSupplierReportVO paramForListVO) throws Exception {

        ParamUtils.packParam(userVO,paramForListVO);
        List<SupplierDetailVO> supplierFileReportDetailVOS = getSupplierFirstReportDetailVOS(paramForListVO, userVO);

        String firstTitle = userVO.getEnterpriseName();
        String secondTitle = "首营企业审批";
        LinkedHashMap<String,String> headerHashMap = new LinkedHashMap<>();
        headerHashMap.put("natureName","企业类型");
        headerHashMap.put("code","供货单位编码");
        headerHashMap.put("name","供货单位名称");
        headerHashMap.put("groupName","分组");
        headerHashMap.put("economicTypeName","经济类型");
        headerHashMap.put("businessModeName","经营方式");
        headerHashMap.put("registeredCapital","注册资金");
        headerHashMap.put("regionalism","行政区域");
        headerHashMap.put("companyAddress","公司地址");
        headerHashMap.put("postcode","邮政编码");
        headerHashMap.put("tel","公司电话");
        headerHashMap.put("fax","公司传真");
        headerHashMap.put("email","公司邮箱");
        headerHashMap.put("site","公司网址");
        headerHashMap.put("storageAddress","仓库地址");
        headerHashMap.put("businessManName","企业负责人");
        headerHashMap.put("legalManName","法定代表人");
        headerHashMap.put("qualityOfficerName","质量负责人");
        headerHashMap.put("bankName","开户银行");
        headerHashMap.put("bankAccount","开户账号");
        headerHashMap.put("bankAccountName","开户户名");
        headerHashMap.put("businessVarietyName","经营品种");
        headerHashMap.put("businessScopeName","经营范围");

        excelComponent.exportExcel(output,firstTitle,secondTitle,new ArrayList<>(),headerHashMap,supplierFileReportDetailVOS,null);
    }

    @Override
    public void exportSupplierFirstDetail(OutputStream output, UserVO userVO, Long supplierId) throws Exception {
        SupplierFirstApproReportVO supplierFirstApproDetail = getSupplierFirstApproDetail(userVO, supplierId);

        ExcelWriter writer = new ExcelWriter() {
            public void generate() throws Exception {

                int kk = 0;
                // 电子表格开始
                this.beginWorkSheet();
                this.beginSheet();
                Map<String, XSSFCellStyle> styleMap = this.getCellStyles();
                short cellStrIndex = styleMap.get("cell_string").getIndex();
                //第一行
                this.insertRow(kk++);
                this.createCell(0, "编码",cellStrIndex);
                this.createCell(1, StringUtil.transferTrimStr(supplierFirstApproDetail.getCode()),cellStrIndex);
                this.createCell(2,"名称",cellStrIndex);
                this.createCell(3,StringUtil.transferTrimStr(supplierFirstApproDetail.getName()),cellStrIndex);
                this.endRow();
//                //第二行
                this.insertRow(kk++);
                this.createCell(0, "类型",cellStrIndex);
                this.createCell(1, StringUtil.transferTrimStr(supplierFirstApproDetail.getNatureName()),cellStrIndex);
                this.createCell(2,"注册资金",cellStrIndex);
                this.createCell(3,StringUtil.transferTrimStr(supplierFirstApproDetail.getRegisteredCapital()),cellStrIndex);
                this.createCell(4,"行政区域",cellStrIndex);
                this.createCell(5,StringUtil.transferTrimStr(supplierFirstApproDetail.getRegionalism()),cellStrIndex);
                this.endRow();
//                //第三行
                this.insertRow(kk++);
                this.createCell(0, "公司网址：",cellStrIndex);
                this.createCell(1, StringUtil.transferTrimStr(supplierFirstApproDetail.getSite()),cellStrIndex);
                this.createCell(2, "公司地址：",cellStrIndex);
                this.createCell(3, StringUtil.transferTrimStr(supplierFirstApproDetail.getCompanyAddress()),cellStrIndex);
                this.endRow();


                //第四行
                this.insertRow(kk++);
                this.createCell(0, "公司电话：" ,cellStrIndex);
                this.createCell(1,  StringUtil.transferTrimStr(supplierFirstApproDetail.getTel()),cellStrIndex);
                this.createCell(2, "公司传真：",cellStrIndex);
                this.createCell(3, StringUtil.transferTrimStr(supplierFirstApproDetail.getFax()),cellStrIndex);
                this.createCell(4, "公司邮箱：",cellStrIndex);
                this.createCell(5, StringUtil.transferTrimStr(supplierFirstApproDetail.getEmail()),cellStrIndex);
                this.endRow();
                //第五行
                this.insertRow(kk++);
                this.createCell(0, "法定代表人：" ,cellStrIndex);
                this.createCell(1,StringUtil.transferTrimStr(supplierFirstApproDetail.getLegalManName()),cellStrIndex);
                this.createCell(2, "企业负责人：" ,cellStrIndex);
                this.createCell(3,StringUtil.transferTrimStr(supplierFirstApproDetail.getBusinessManName()),cellStrIndex);
                this.createCell(4, "质量负责人：",cellStrIndex);
                this.createCell(5,StringUtil.transferTrimStr(supplierFirstApproDetail.getQualityOfficerName()),cellStrIndex);
                this.endRow();
                //第六行
                this.insertRow(kk++);
                this.createCell(0, "开户银行：" ,cellStrIndex);
                this.createCell(1,StringUtil.transferTrimStr(supplierFirstApproDetail.getBankName()),cellStrIndex);
                this.createCell(2, "开户账户：" ,cellStrIndex);
                this.createCell(3,StringUtil.transferTrimStr(supplierFirstApproDetail.getBankAccountName()),cellStrIndex);
                this.createCell(4, "开户户名：",cellStrIndex);
                this.createCell(5,StringUtil.transferTrimStr(supplierFirstApproDetail.getBankAccount()),cellStrIndex);
                this.endRow();

                //第7行
                this.insertRow(kk++);
                this.createCell(0, "经营品种：" ,cellStrIndex);
                this.createCell(1,StringUtil.transferTrimStr(supplierFirstApproDetail.getBusinessVarietyName()),cellStrIndex);
                this.createCell(2, "经营范围：" ,cellStrIndex);
                this.createCell(3,StringUtil.transferTrimStr(supplierFirstApproDetail.getBusinessScopeName()),cellStrIndex);
                this.endRow();

                //第8行
                this.insertRow(kk++);
                this.createCell(0, "企业资质" ,cellStrIndex);
                this.createCell(1,"资质名称",cellStrIndex);
                this.createCell(2, "资质编号",cellStrIndex);
                this.createCell(3,"有效期至",cellStrIndex);
                this.endRow();

                int qcK = 7;
                //企业资质
                List<QualificationConfigVO> qualificationConfigVOS = supplierFirstApproDetail.getQualificationConfigVOS();
                for (QualificationConfigVO configVO : qualificationConfigVOS) {
                    qcK++;
                    this.insertRow(kk++);
                    this.createCell(0, "企业资质" ,cellStrIndex);
                    this.createCell(1,StringUtil.transferTrimStr(configVO.getQualificationName()),cellStrIndex);
                    this.createCell(2, StringUtil.transferTrimStr(configVO.getQualificationCode()),cellStrIndex);
                    this.createCell(3,StringUtil.transferTrimStr(configVO.getValidDate()),cellStrIndex);
                    this.endRow();
                }



                SupplierSalerVO supplierSalerVO = supplierFirstApproDetail.getSupplierSalerVO();

                this.insertRow(kk++);
                this.createCell(0, "销售人员" ,cellStrIndex);
                this.createCell(1,"姓名",cellStrIndex);
                this.createCell(2, StringUtil.transferTrimStr(supplierSalerVO.getName()),cellStrIndex);
                this.createCell(3,"电话",cellStrIndex);
                this.createCell(4,StringUtil.transferTrimStr(supplierSalerVO.getMobilePhone()),cellStrIndex);
                this.endRow();

                this.insertRow(kk++);
                this.createCell(0, "销售人员" ,cellStrIndex);
                this.createCell(1,"身份证号",cellStrIndex);
                this.createCell(2, StringUtil.transferTrimStr(supplierSalerVO.getIdNum()),cellStrIndex);
                this.createCell(3,"有效期至",cellStrIndex);
                this.createCell(4,StringUtil.transferTrimStr(supplierSalerVO.getIdValidUntil()),cellStrIndex);
                this.endRow();

                this.insertRow(kk++);
                this.createCell(0, "销售人员" ,cellStrIndex);
                this.createCell(1,"授权书号",cellStrIndex);
                this.createCell(2, StringUtil.transferTrimStr(supplierSalerVO.getCertificateNum()),cellStrIndex);
                this.createCell(3,"有效期至",cellStrIndex);
                this.createCell(4,StringUtil.transferTrimStr(supplierSalerVO.getCertificateValidUntil()),cellStrIndex);
                this.endRow();

                this.insertRow(kk++);
                this.createCell(0, "销售人员" ,cellStrIndex);
                this.createCell(1,"授权品种",cellStrIndex);
                this.createCell(2, StringUtil.transferTrimStr(supplierSalerVO.getAuthorizedVarietyNames()),cellStrIndex);
                this.createCell(3,"授权地域",cellStrIndex);
                this.createCell(4,StringUtil.transferTrimStr(supplierSalerVO.getAuthorizedRegion()),cellStrIndex);
                this.endRow();

                this.insertRow(kk++);
                this.createCell(0, "销售人员" ,cellStrIndex);
                this.createCell(1,"授权范围",cellStrIndex);
                this.createCell(2, StringUtil.transferTrimStr(supplierSalerVO.getAuthorizedVarietyScopeNames()),cellStrIndex);
                this.endRow();

                this.insertRow(kk++);
                this.createCell(0, "业务部门意见" ,cellStrIndex);
                this.createCell(1,"供货单位质量管理体系",cellStrIndex);
                this.endRow();

                this.insertRow(kk++);
                this.createCell(0, "业务部门意见" ,cellStrIndex);
                this.createCell(1, StringUtil.transferTrimStr(supplierFirstApproDetail.getQualityManageSys()),cellStrIndex);
                this.endRow();
                this.insertRow(kk++);
                this.createCell(0, "业务部门意见" ,cellStrIndex);
                this.createCell(2,"申请日期：");
                this.createCell(3, StringUtil.transferTrimStr(supplierFirstApproDetail.getApplicationTime()),cellStrIndex);
                this.createCell(4,"申请人员：");
                this.createCell(5, StringUtil.transferTrimStr(supplierFirstApproDetail.getApplicantName()),cellStrIndex);
                this.endRow();


                //审批
                List<ApprovalFlowActionDetail> approvalFlowActionDetailList = supplierFirstApproDetail.getApprovalFlowActionDetailList();
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
                }


                // sheet表格结束
                this.endSheet();

//                合并单元格
                this.beginMergerCell();
                this.setMergeCell(7, 0, qcK, 0);
                this.setMergeCell(qcK + 1, 0, qcK + 5, 0);
                //业务部门意见合并
                this.setMergeCell(qcK + 5 + 1, 0, qcK + 5 + 3, 0);
                this.setMergeCell(qcK + 5 + 1, 2, qcK + 5 + 1, 5);
                this.setMergeCell(qcK + 5 + 2, 1, qcK + 5 + 2, 5);

                //审批表格合并
                int flowK = (qcK + 5 + 3 ) + 1;
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
