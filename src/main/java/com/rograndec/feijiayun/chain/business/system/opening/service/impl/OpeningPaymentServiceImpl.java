package com.rograndec.feijiayun.chain.business.system.opening.service.impl;

import com.alibaba.fastjson.JSON;
import com.rograndec.feijiayun.chain.app.SpringBeanFactory;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.finance.set.period.dao.AccountingPeriodDetailMapper;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatus;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.opening.common.ExcelErrorCodeEnum;
import com.rograndec.feijiayun.chain.business.system.opening.common.OpeningConstant;
import com.rograndec.feijiayun.chain.business.system.opening.dao.OpeningPaymentDetailMapper;
import com.rograndec.feijiayun.chain.business.system.opening.dao.OpeningPaymentMapper;
import com.rograndec.feijiayun.chain.business.system.opening.draft.DraftCache;
import com.rograndec.feijiayun.chain.business.system.opening.entity.OpeningPayment;
import com.rograndec.feijiayun.chain.business.system.opening.entity.OpeningPaymentDetail;
import com.rograndec.feijiayun.chain.business.system.opening.excel.OpeningPaymentReceivableIExcelUtils;
import com.rograndec.feijiayun.chain.business.system.opening.excel.OpeningPaymentReceivableIRowReader;
import com.rograndec.feijiayun.chain.business.system.opening.service.OpeningPaymentService;
import com.rograndec.feijiayun.chain.business.system.opening.vo.*;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.constant.status.PeriodStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.FileUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelReaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

@Service
public class OpeningPaymentServiceImpl implements OpeningPaymentService {

    @Autowired
    private OpeningPaymentMapper openingPaymentMapper;
    @Autowired
    private OpeningPaymentDetailMapper openingPaymentDetailMapper;
    @Autowired
    private SupplierMapper supplierMapper;
    @Autowired
    private AccountingPeriodDetailMapper accountingPeriodDetailMapper;
    @Autowired
    private RedisComponent redisComponent;
//    @Autowired
//    private OpeningPaymentReceivableIRowReader iRowReader;
    @Autowired
    private DraftCache draftCache;
    @Autowired
    private OrderCodeComponent orderCodeComponent;
    @Autowired
    private OpeningPaymentReceivableIExcelUtils excelUtils;
    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private FinanceComponent financeComponent;
    @Autowired
    private CommonComponent commonComponent;
    @Autowired
    private UserComponent userComponent;

    @Override
    public OpenningPaymentReceivableListVO getOpeningPaymentList(UserVO userVO) {
        OpenningPaymentReceivableListVO openningPaymentListVO = new OpenningPaymentReceivableListVO();
        OpeningPayment openingPayment = openingPaymentMapper.selectByEnterpriseId(userVO.getEnterpriseId());
        if (null != openingPayment) {
            openningPaymentListVO.setCode(openingPayment.getCode());
            openningPaymentListVO.setDate(openingPayment.getPaymentDate());
            openningPaymentListVO.setManName(openingPayment.getPaymentManName());
            openningPaymentListVO.setManId(openingPayment.getPaymentManId());
            openningPaymentListVO.setAmountTotal(openingPayment.getAmountTotal());
            openningPaymentListVO.setRemark(openingPayment.getRemark());
            openningPaymentListVO.setIsGenerate(YesAndNo.YES.getCode());
            openningPaymentListVO.setDetailVOS(openingPaymentDetailMapper.selectByPaymentId(openingPayment.getId()));
        } else {
            getOpeningPaymentDetail(userVO, openningPaymentListVO);
        }
        return openningPaymentListVO;
    }

    @Override
    public List<AccountingPeriodVO> getAccountingPeriod(UserVO userVO) {
        return accountingPeriodDetailMapper.findByEnterpriseId(userVO.getEnterpriseId(), PeriodStatus.OPEN.getCode());
    }

    @Override
    public void saveDraftCache(UserVO userVO, OpenningPaymentReceivableListVO openningPaymentListVO) {
        draftCache.saveDraftCache(userVO, openningPaymentListVO, OrderRule.OPENING_PAYMENT.getCodePrefix());
    }

    @Override
    public ResponsePaymentReceivableExcelVO excelImport(HttpServletRequest request) throws Exception {

        OpeningPaymentReceivableIRowReader iRowReader = SpringBeanFactory.getBean(OpeningPaymentReceivableIRowReader.class);

        HttpSession session = request.getSession(true);
        UserVO userVO = (UserVO) session.getAttribute("user");
        Long enterpriseId = userVO.getEnterpriseId();
        Map<String, OpeningPaymentReceivableDetailVO> supplierMap = OpeningPaymentReceivableDetailVO.getMap(getSupplierList(userVO));
        //合格数据
        List<OpeningPaymentReceivableExcelVO> qualifiedList = new ArrayList<>();
        //不合格数据
        List<OpeningPaymentReceivableExcelVO> disqualificationList = new ArrayList<>();
        iRowReader.setUnitMap(supplierMap);
        iRowReader.setCodeSet(new HashSet<>());
        iRowReader.setQualifiedList(qualifiedList);
        iRowReader.setDisqualificationList(disqualificationList);
        iRowReader.setUnitInvalid(ExcelErrorCodeEnum.INVALID_SUPPLiER_CODE.getName());
        iRowReader.setUnitrRepetition(ExcelErrorCodeEnum.REPETITION_SUPPLIER_CODE.getName());

        Part part = request.getPart("file");
        InputStream inputStream = part.getInputStream();
        ExcelReaderUtil.excelToArrayList(iRowReader, FileUtils.getFileName(part), inputStream, 4, 0);

        ResponsePaymentReceivableExcelVO responseExcelVO = new ResponsePaymentReceivableExcelVO();
        responseExcelVO.setQualifiedCount(qualifiedList.size());
        responseExcelVO.setDisqualificationCount(disqualificationList.size());

        Long key = System.currentTimeMillis();
        redisComponent.set(OpeningConstant.OPENING_PAYMENT_QUALIFIED + enterpriseId + key, JSON.toJSONString(qualifiedList));
        redisComponent.set(OpeningConstant.OPENING_PAYMENT_DISQUALIFIED + enterpriseId + key, JSON.toJSONString(disqualificationList));
        responseExcelVO.setKey(key.toString());
        return responseExcelVO;
    }
    @Override
    public void exporTtemplate(OutputStream outputStream, UserVO userVO) {
        List<OpeningPaymentReceivableExcelVO> excelVOS = excelUtils.detailVO2Excel(getSupplierList(userVO), FinanceDirection.CREDIT.getDesc());
        excelUtils.exportParityData(excelVOS, outputStream, "供");
    }
    @Override
    public void exportUnqualified(OutputStream output, String key, Integer type, Long enterpriseId) {
        List<OpeningPaymentReceivableExcelVO> list;
        if (type == 2) {
            list = JSON.parseArray((String) redisComponent.get(OpeningConstant.OPENING_PAYMENT_DISQUALIFIED + enterpriseId + key), OpeningPaymentReceivableExcelVO.class);
        } else {
            list = JSON.parseArray((String) redisComponent.get(OpeningConstant.OPENING_PAYMENT_QUALIFIED + enterpriseId + key), OpeningPaymentReceivableExcelVO.class);
        }
        excelUtils.exportParityData(list, output, "供");
    }

    /**
     * 继续导入
     *
     * @param openningPaymentListVO
     * @param key
     * @param userVO
     * @return
     */
    @Override
    public OpenningPaymentReceivableListVO continueToImport(OpenningPaymentReceivableListVO openningPaymentListVO, String key, UserVO userVO) {
        List<OpeningPaymentReceivableExcelVO> list = JSON.parseArray((String) redisComponent.get(OpeningConstant.OPENING_PAYMENT_QUALIFIED + userVO.getEnterpriseId() + key), OpeningPaymentReceivableExcelVO.class);
        List<OpeningPaymentReceivableDetailVO> tableDetailVOS = getSupplierList(userVO);
        return excelUtils.integration(openningPaymentListVO, list, tableDetailVOS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(OpenningPaymentReceivableSaveVO saveVO, UserVO userVO) throws Exception {
        Long enterpriseId = userVO.getEnterpriseId();
        //验证期初应付是否存在
        if (null != openingPaymentMapper.selectByEnterpriseId(enterpriseId)) {
            throw new BusinessException("期初应付数据已存在，不允许保存！");
        }
        //验证日期
        commonComponent.validationAccountingDate(DateUtils.DateToString(saveVO.getDate(),DateUtils.FMT_DATE),userVO);

        //验证人员
        User user = userComponent.validationUser(userVO.getEnterpriseId(), saveVO.getManId());

        //验证详情
        validationDetail(saveVO.getDetailVOS(), userVO);

        //总单实体
        int status = OpeningInventoryStatus.ENABLE.getCode();
        Long parentId = userVO.getParentId();
        BigDecimal clearAmount = new BigDecimal(0.00);
        String remark = saveVO.getRemark();
        String code = orderCodeComponent.generate(OrderRule.OPENING_PAYMENT.getCodePrefix(), enterpriseId, userVO.getEnterpriseCode());

        OpeningPayment openingPayment = (OpeningPayment) EntityUtils.reflectAddSetDefaultValue(OpeningPayment.class, userVO);
        openingPayment.setCode(code);
        openingPayment.setEnterpriseId(enterpriseId);
        openingPayment.setParentId(parentId);
        openingPayment.setPaymentDate(saveVO.getDate());
        openingPayment.setOrderType(OrderRule.OPENING_PAYMENT.getType());
        openingPayment.setPaymentManId(user.getId());
        openingPayment.setPaymentManCode(user.getCode());
        openingPayment.setPaymentManName(user.getName());
        openingPayment.setStatus(status);
        openingPayment.setRemark(remark);
        openingPayment.setAmountTotal(clearAmount);
        openingPayment.setClearAmountTotal(clearAmount);
        openingPayment.setUnclearAmountTotal(clearAmount);
        openingPaymentMapper.insertSelective(openingPayment);


        List<OpeningPaymentDetail> openingPaymentDetails = new ArrayList<>();
        List<OpeningPaymentReceivableDetailVO> supplierList = getSupplierList(userVO);
        Map<String, OpeningPaymentReceivableDetailVO> detailVOMap = OpeningPaymentReceivableDetailVO.getMap(saveVO.getDetailVOS());
        BigDecimal amountTotal = new BigDecimal(0.00);

        if (!CollectionUtils.isEmpty(supplierList) && detailVOMap.size() <= supplierList.size()) {
            for (OpeningPaymentReceivableDetailVO supplier : supplierList) {
                //明细实体
                OpeningPaymentDetail openingPaymentDetail = (OpeningPaymentDetail) EntityUtils.reflectAddSetDefaultValue(OpeningPaymentDetail.class, userVO);
                openingPaymentDetail.setEnterpriseId(enterpriseId);
                openingPaymentDetail.setParentId(parentId);
                openingPaymentDetail.setPaymentId(openingPayment.getId());
                openingPaymentDetail.setFinanceAccountType(supplier.getFinanceAccountType());
                openingPaymentDetail.setSupplierId(supplier.getId());
                openingPaymentDetail.setSupplierCode(supplier.getCode());
                openingPaymentDetail.setSupplierName(supplier.getName());
                openingPaymentDetail.setRemark(remark);
                openingPaymentDetail.setStatus(status);
                openingPaymentDetail.setClearAmount(clearAmount);

                OpeningPaymentReceivableDetailVO detailVO = detailVOMap.get(supplier.getCode());
                if (null == detailVO) {
                    openingPaymentDetail.setAmount(clearAmount);
                    openingPaymentDetail.setUnclearAmount(clearAmount);
                } else {
                    BigDecimal amount = new BigDecimal(detailVO.getAmount());
                    openingPaymentDetail.setAmount(amount);
                    openingPaymentDetail.setUnclearAmount(amount);
                    amountTotal = amountTotal.add(amount);
                }
                openingPaymentDetails.add(openingPaymentDetail);
            }
        } else {
            throw new BusinessException("供货单位错误,请检查！");
        }
        openingPaymentDetailMapper.insertBatch(openingPaymentDetails);

        openingPayment.setAmountTotal(amountTotal);
        openingPayment.setUnclearAmountTotal(amountTotal);
        openingPayment.setClearAmountTotal(clearAmount);
        openingPaymentMapper.updateByPrimaryKeySelective(openingPayment);
        //期初应付保存后，对应财务余额表和凭证表影响
        financeComponent.openingPaymentToBalanceAndVoucher(userVO, openingPayment, openingPaymentDetails);
        //期初应付操作完成后清空草稿
        draftCache.removerDraftCache(enterpriseId,OrderRule.OPENING_PAYMENT.getCodePrefix());
        return code;
    }

    @Override
    public void exportOpeningPayment(OutputStream outputStream, UserVO userVO) {
        OpeningPayment openingPayment = openingPaymentMapper.selectByEnterpriseId(userVO.getEnterpriseId());
        List<OpeningPaymentReceivableDetailVO> paymentDetailVOS = new ArrayList<>();
        String code = null;
        String manName = null;
        Date date = null;
        String remark = null;
        BigDecimal amountTotal = null;
        if (openingPayment != null) {
            paymentDetailVOS = openingPaymentDetailMapper.selectByPaymentId(openingPayment.getId());
            code = openingPayment.getCode();
            manName = openingPayment.getPaymentManName();
            date = openingPayment.getPaymentDate();
            remark = openingPayment.getRemark();
            amountTotal = openingPayment.getAmountTotal();
        }
        excelUtils.exportData(outputStream, paymentDetailVOS, code, manName, date, remark, amountTotal, true, userVO.getEnterpriseName());
    }

    /**
     * 验证供货单位是否有效
     *
     * @param detailVOS
     * @param userVO
     */
    private void validationDetail(List<OpeningPaymentReceivableDetailVO> detailVOS, UserVO userVO) {
        Map<String, OpeningPaymentReceivableDetailVO> map = OpeningPaymentReceivableDetailVO.getMap(getSupplierList(userVO));
        for (OpeningPaymentReceivableDetailVO detailVO : detailVOS) {
            OpeningPaymentReceivableDetailVO detailVO1 = map.get(detailVO.getCode());
            if (null == detailVO1) {
                throw new BusinessException("供货单位错误 （供货单位编码 ：" + detailVO.getCode() + "； 供货单位名称 ：" + detailVO.getName() + "） 请检查！");
            }
        }
    }


    /**
     * 由供货数据和草稿缓存数据生成期初应付数据
     *
     * @param userVO
     * @param openningPaymentListVO
     */
    private void getOpeningPaymentDetail(UserVO userVO, OpenningPaymentReceivableListVO openningPaymentListVO) {
        List<OpeningPaymentReceivableDetailVO> supplierList = getSupplierList(userVO);
        draftCache.integrationCacheAndTable(userVO, OrderRule.OPENING_PAYMENT.getCodePrefix(), openningPaymentListVO, supplierList);
    }

    /**
     * 获取当前企业的供应商列表
     *
     * @param userVO
     * @return
     */
    private List<OpeningPaymentReceivableDetailVO> getSupplierList(UserVO userVO) {
        List<OpeningPaymentReceivableDetailVO> supplierList = supplierMapper.selectByOwnerIdAndStatusAndApproveStatus(userVO.getEnterpriseId(),EnableStatus.ENABLE.getStatus(), ApprovalFlowAuditStatus.DETAIL_STATUS_PASS.getValue());
        if (userVO.getChainType() == ChainType.Division.getCode()) {
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(userVO.getParentId());
            OpeningPaymentReceivableDetailVO headquarters = new OpeningPaymentReceivableDetailVO();
            headquarters.setId(enterprise.getId());
            headquarters.setCode(enterprise.getCode());
            headquarters.setName(enterprise.getName());
            headquarters.setFinanceAccountType(FinanceAccountType.PARENT.getType());
            headquarters.setAmount("0.00");
            supplierList.add(headquarters);
        }
        return supplierList;
    }

}
