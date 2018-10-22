package com.rograndec.feijiayun.chain.business.system.opening.service.impl;

import com.alibaba.fastjson.JSON;
import com.rograndec.feijiayun.chain.app.SpringBeanFactory;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatus;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.opening.common.ExcelErrorCodeEnum;
import com.rograndec.feijiayun.chain.business.system.opening.common.OpeningConstant;
import com.rograndec.feijiayun.chain.business.system.opening.dao.OpeningReceivableDetailMapper;
import com.rograndec.feijiayun.chain.business.system.opening.dao.OpeningReceivableMapper;
import com.rograndec.feijiayun.chain.business.system.opening.draft.DraftCache;
import com.rograndec.feijiayun.chain.business.system.opening.entity.OpeningReceivable;
import com.rograndec.feijiayun.chain.business.system.opening.entity.OpeningReceivableDetail;
import com.rograndec.feijiayun.chain.business.system.opening.excel.OpeningPaymentReceivableIExcelUtils;
import com.rograndec.feijiayun.chain.business.system.opening.excel.OpeningPaymentReceivableIRowReader;
import com.rograndec.feijiayun.chain.business.system.opening.service.OpeningReceivableService;
import com.rograndec.feijiayun.chain.business.system.opening.vo.*;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.*;
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
public class OpeningReceivableServiceImpl implements OpeningReceivableService {

    @Autowired
    private OpeningReceivableMapper openingReceivableMapper;
    @Autowired
    private OpeningReceivableDetailMapper openingReceivableDetailMapper;
    @Autowired
    private EnterpriseMapper enterpriseMapper;
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
    private UserComponent userComponent;
    @Autowired
    private FinanceComponent financeComponent;
    @Autowired
    private CommonComponent commonComponent;

    @Override
    public OpenningPaymentReceivableListVO getOpeningReceivableList(UserVO userVO) {
        OpenningPaymentReceivableListVO openningReceivableListVO = new OpenningPaymentReceivableListVO();
        OpeningReceivable openingReceivable = openingReceivableMapper.selectByEnterpriseId(userVO.getEnterpriseId());
        if (null != openingReceivable) {
            openningReceivableListVO.setCode(openingReceivable.getCode());
            openningReceivableListVO.setDate(openingReceivable.getReceivableDate());
            openningReceivableListVO.setManName(openingReceivable.getReceivableManName());
            openningReceivableListVO.setManId(openingReceivable.getReceivableManId());
            openningReceivableListVO.setAmountTotal(openingReceivable.getAmountTotal());
            openningReceivableListVO.setRemark(openingReceivable.getRemark());
            openningReceivableListVO.setIsGenerate(YesAndNo.YES.getCode());
            openningReceivableListVO.setDetailVOS(openingReceivableDetailMapper.selectByReceivableId(openingReceivable.getId()));
        } else {
            getOpeningReceivableDetail(userVO, openningReceivableListVO);
        }
        return openningReceivableListVO;
    }


    @Override
    public void saveDraftCache(UserVO userVO, OpenningPaymentReceivableListVO openningReceivableListVO) {
        draftCache.saveDraftCache(userVO, openningReceivableListVO, OrderRule.OPENING_RECEIVABLE.getCodePrefix());
    }

    @Override
    public ResponsePaymentReceivableExcelVO excelImport(HttpServletRequest request) throws Exception {
        OpeningPaymentReceivableIRowReader iRowReader = SpringBeanFactory.getBean(OpeningPaymentReceivableIRowReader.class);
        HttpSession session = request.getSession(true);
        UserVO userVO = (UserVO) session.getAttribute("user");
        Long enterpriseId = userVO.getEnterpriseId();
        Map<String, OpeningPaymentReceivableDetailVO> map = OpeningPaymentReceivableDetailVO.getMap(getFranchiseesList(userVO));
        //合格数据
        List<OpeningPaymentReceivableExcelVO> qualifiedList = new ArrayList<>();
        //不合格数据
        List<OpeningPaymentReceivableExcelVO> disqualificationList = new ArrayList<>();
        iRowReader.setUnitMap(map);
        iRowReader.setCodeSet(new HashSet<>());
        iRowReader.setQualifiedList(qualifiedList);
        iRowReader.setDisqualificationList(disqualificationList);
        iRowReader.setUnitInvalid(ExcelErrorCodeEnum.INVALID_FRANCHISEES_CODE.getName());
        iRowReader.setUnitrRepetition(ExcelErrorCodeEnum.REPETITION_FRANCHISEES_CODE.getName());

        Part part = request.getPart("file");
        InputStream inputStream = part.getInputStream();
        ExcelReaderUtil.excelToArrayList(iRowReader, FileUtils.getFileName(part), inputStream, 4, 0);

        ResponsePaymentReceivableExcelVO responseExcelVO = new ResponsePaymentReceivableExcelVO();
        responseExcelVO.setQualifiedCount(qualifiedList.size());
        responseExcelVO.setDisqualificationCount(disqualificationList.size());

        Long key = System.currentTimeMillis();
        redisComponent.set(OpeningConstant.OPENING_RECEIVABLE_QUALIFIED + enterpriseId + key, JSON.toJSONString(qualifiedList));
        redisComponent.set(OpeningConstant.OPENING_RECEIVABLE_DISQUALIFIED + enterpriseId + key, JSON.toJSONString(disqualificationList));
        responseExcelVO.setKey(key.toString());
        return responseExcelVO;
    }

    @Override
    public void exportUnqualified(OutputStream output, String key, Integer type, Long enterpriseId) {
        List<OpeningPaymentReceivableExcelVO> list;
        if (type == 2) {
            list = JSON.parseArray((String) redisComponent.get(OpeningConstant.OPENING_RECEIVABLE_DISQUALIFIED + enterpriseId + key), OpeningPaymentReceivableExcelVO.class);
        } else {
            list = JSON.parseArray((String) redisComponent.get(OpeningConstant.OPENING_RECEIVABLE_QUALIFIED + enterpriseId + key), OpeningPaymentReceivableExcelVO.class);
        }
        excelUtils.exportParityData(list, output, "购");
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
        List<OpeningPaymentReceivableExcelVO> excelVOS = JSON.parseArray((String) redisComponent.get(OpeningConstant.OPENING_RECEIVABLE_QUALIFIED + userVO.getEnterpriseId() + key), OpeningPaymentReceivableExcelVO.class);
        List<OpeningPaymentReceivableDetailVO> tableDetailVOS = getFranchiseesList(userVO);
        return excelUtils.integration(openningPaymentListVO, excelVOS, tableDetailVOS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(OpenningPaymentReceivableSaveVO saveVO, UserVO userVO) throws Exception {
        Long enterpriseId = userVO.getEnterpriseId();
        //验证期初应收是否存在
        if (null != openingReceivableMapper.selectByEnterpriseId(enterpriseId)) {
            throw new BusinessException("期初应收数据已存在，不允许保存！");
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
        String code = orderCodeComponent.generate(OrderRule.OPENING_RECEIVABLE.getCodePrefix(), enterpriseId, userVO.getEnterpriseCode());

        OpeningReceivable openingReceivable = (OpeningReceivable) EntityUtils.reflectAddSetDefaultValue(OpeningReceivable.class, userVO);
        openingReceivable.setCode(code);
        openingReceivable.setEnterpriseId(enterpriseId);
        openingReceivable.setParentId(parentId);
        openingReceivable.setReceivableDate(saveVO.getDate());
        openingReceivable.setOrderType(OrderRule.OPENING_RECEIVABLE.getType());
        openingReceivable.setReceivableManId(user.getId());
        openingReceivable.setReceivableManCode(user.getCode());
        openingReceivable.setReceivableManName(user.getName());
        openingReceivable.setStatus(status);
        openingReceivable.setRemark(remark);
        openingReceivable.setAmountTotal(clearAmount);
        openingReceivable.setClearAmountTotal(clearAmount);
        openingReceivable.setUnclearAmountTotal(clearAmount);
        openingReceivableMapper.insertSelective(openingReceivable);


        List<OpeningReceivableDetail> openingReceivableDetails = new ArrayList<>();
        List<OpeningPaymentReceivableDetailVO> franchiseesList = getFranchiseesList(userVO);
        Map<String, OpeningPaymentReceivableDetailVO> detailVOMap = OpeningPaymentReceivableDetailVO.getMap(saveVO.getDetailVOS());
        BigDecimal amountTotal = new BigDecimal(0.00);

        if (!CollectionUtils.isEmpty(franchiseesList) && detailVOMap.size() <= franchiseesList.size()) {
            int financeAccountType = FinanceAccountType.LEAGUE.getType();
            for (OpeningPaymentReceivableDetailVO franchisees : franchiseesList) {
                //明细实体
                OpeningReceivableDetail openingReceivableDetail = (OpeningReceivableDetail) EntityUtils.reflectAddSetDefaultValue(OpeningReceivableDetail.class, userVO);
                openingReceivableDetail.setEnterpriseId(enterpriseId);
                openingReceivableDetail.setParentId(parentId);
                openingReceivableDetail.setFinanceAccountType(financeAccountType);
                openingReceivableDetail.setReceivableId(openingReceivable.getId());
                openingReceivableDetail.setPurchaseUnitId(franchisees.getId());
                openingReceivableDetail.setPurchaseUnitCode(franchisees.getCode());
                openingReceivableDetail.setPurchaseUnitName(franchisees.getName());
                openingReceivableDetail.setRemark(remark);
                openingReceivableDetail.setStatus(status);
                openingReceivableDetail.setClearAmount(clearAmount);

                OpeningPaymentReceivableDetailVO detailVO = detailVOMap.get(franchisees.getCode());
                if (null == detailVO) {
                    openingReceivableDetail.setAmount(clearAmount);
                    openingReceivableDetail.setUnclearAmount(clearAmount);
                } else {
                    BigDecimal amount = new BigDecimal(detailVO.getAmount());
                    openingReceivableDetail.setAmount(amount);
                    openingReceivableDetail.setUnclearAmount(amount);
                    amountTotal = amountTotal.add(amount);
                }
                openingReceivableDetails.add(openingReceivableDetail);
            }
        } else {
            throw new BusinessException("购货单位错误,请检查！");
        }
        openingReceivableDetailMapper.insertBatch(openingReceivableDetails);

        openingReceivable.setAmountTotal(amountTotal);
        openingReceivable.setUnclearAmountTotal(amountTotal);
        openingReceivable.setClearAmountTotal(clearAmount);
        openingReceivableMapper.updateByPrimaryKeySelective(openingReceivable);
        //期初应收保存后，对应财务余额表和凭证表影响
        financeComponent.openingReceivableToBalanceAndVoucher(userVO, openingReceivable, openingReceivableDetails);
        //期初应收操作完成后清空草稿
        draftCache.removerDraftCache(enterpriseId,OrderRule.OPENING_RECEIVABLE.getCodePrefix());
        return code;
    }

    @Override
    public void exportOpeningReceivable(OutputStream outputStream, UserVO userVO) {
        OpeningReceivable openingReceivable = openingReceivableMapper.selectByEnterpriseId(userVO.getEnterpriseId());
        List<OpeningPaymentReceivableDetailVO> receivableDetailVOS = new ArrayList<>();
        String code = null;
        String manName = null;
        Date date = null;
        String remark = null;
        BigDecimal amountTotal = null;
        if (openingReceivable != null) {
            receivableDetailVOS = openingReceivableDetailMapper.selectByReceivableId(openingReceivable.getId());
            code = openingReceivable.getCode();
            manName = openingReceivable.getReceivableManName();
            date = openingReceivable.getReceivableDate();
            remark = openingReceivable.getRemark();
            amountTotal = openingReceivable.getAmountTotal();
        }
        excelUtils.exportData(outputStream, receivableDetailVOS, code, manName, date, remark, amountTotal, false, userVO.getEnterpriseName());
    }

    @Override
    public void exporTtemplate(OutputStream outputStream, UserVO userVO) {
        List<OpeningPaymentReceivableExcelVO> excelVOS = excelUtils.detailVO2Excel(getFranchiseesList(userVO), FinanceDirection.DEBIT.getDesc());
        excelUtils.exportParityData(excelVOS, outputStream, "购");
    }

    /**
     * 验证供货单位是否有效
     *
     * @param detailVOS
     * @param userVO
     */
    private void validationDetail(List<OpeningPaymentReceivableDetailVO> detailVOS, UserVO userVO) {
        Map<String, OpeningPaymentReceivableDetailVO> map = OpeningPaymentReceivableDetailVO.getMap(getFranchiseesList(userVO));
        for (OpeningPaymentReceivableDetailVO detailVO : detailVOS) {
            OpeningPaymentReceivableDetailVO detailVO1 = map.get(detailVO.getCode());
            if (null == detailVO1) {
                throw new BusinessException("购货单位错误 （购货单位编码 ：" + detailVO.getCode() + "； 购货单位名称 ： " + detailVO.getName() + "） 请检查！");
            }
        }
    }


    /**
     * 由购货单位list集合（总部下属的加盟店）和草稿缓存数据生成期初应收数据
     */
    private void getOpeningReceivableDetail(UserVO userVO, OpenningPaymentReceivableListVO openningReceivableListVO) {

        List<OpeningPaymentReceivableDetailVO> openingReceivableDetailVOS = getFranchiseesList(userVO);
        draftCache.integrationCacheAndTable(userVO, OrderRule.OPENING_RECEIVABLE.getCodePrefix(), openningReceivableListVO, openingReceivableDetailVOS);

    }

    /**
     * 获取当总部所有结算方式是“应收账款”的门店
     *
     * @param userVO
     * @return
     */
    private List<OpeningPaymentReceivableDetailVO> getFranchiseesList(UserVO userVO) {
        return enterpriseMapper.selectByParentIdAndTypeAndStatusAndApproveStatus(userVO.getEnterpriseId(), ChainType.Division.getCode(),EnableStatus.ENABLE.getStatus(), ApprovalFlowAuditStatus.DETAIL_STATUS_PASS.getValue());
    }

}
