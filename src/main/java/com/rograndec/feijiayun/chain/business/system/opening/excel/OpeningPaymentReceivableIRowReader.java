package com.rograndec.feijiayun.chain.business.system.opening.excel;

import com.rograndec.feijiayun.chain.business.system.opening.common.ExcelErrorCodeEnum;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpeningPaymentReceivableDetailVO;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpeningPaymentReceivableExcelVO;
import com.rograndec.feijiayun.chain.utils.excel.IRowReader;
import com.rograndec.feijiayun.chain.utils.finance.DirectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 期初应付（应收）的行讀取器
 */
@Component
@Scope("prototype")
public class OpeningPaymentReceivableIRowReader extends IRowReader {

    private static final Logger logger = LoggerFactory.getLogger(OpeningPaymentReceivableIRowReader.class);


    //合格数据
    private List<OpeningPaymentReceivableExcelVO> qualifiedList;
    //合格数据的供货单位编码set
    private Set<String> codeSet;
    //不合格数据
    private List<OpeningPaymentReceivableExcelVO> disqualificationList;

    //供货（购货）单位key为单位编码
    private Map<String, OpeningPaymentReceivableDetailVO> unitMap;
    //单位无效提示
    private String unitInvalid;
    //单位重复提示
    private String unitrRepetition;

    private static final String DEBIT = "借";
    private static final String CREDIT = "贷";
    private static final String BALANCE = "平";

    public OpeningPaymentReceivableIRowReader() {
    }

    @Override
    public void getRows(int sheetIndex, int curRow, List<String> rowList) {
        if (logger.isDebugEnabled()) {
            logger.debug("Sheet={},Row={},Data={}", sheetIndex, curRow, rowList);
        }
        int k = 0;
        boolean isOk = true;
        if (curRow > 0) {
            //从第二行开始计算（excel行号从0开始）
            if (rowList != null && rowList.size() > 0) {
                int size = rowList.size();
                String code = rowList.get(k++);
                String name = null;
                String direction = null;
                String amount = null;
                if (k < size) {
                    //单位名称
                    name = rowList.get(k++);
                }
                if (k < size) {
                    //方向
                    direction = rowList.get(k++);
                }
                if (k < size) {
                    //金额
                    amount = rowList.get(k);
                }

                //单位编码
                if (StringUtils.isBlank(code)) {
                    isOk = false;
                    code = ExcelErrorCodeEnum.BLANK.getName();
                } else {
                    if (CollectionUtils.isEmpty(unitMap) || unitMap.get(code) == null) {
                        isOk = false;
                        code = code + "--" + unitInvalid;
                    } else if (codeSet.contains(code)) {
                        isOk = false;
                        code = code + "--" + unitrRepetition;
                    }
                }
                //金额
                if (StringUtils.isBlank(amount)) {
                    isOk = false;
                    amount = ExcelErrorCodeEnum.BLANK.getName();
                } else if (DirectionUtils.validAmount(amount)) {
                    //目前excel读取金额时候可能出现金额的值与excel表中的值不一致，无限接近
                    BigDecimal big = new BigDecimal(amount);
                    switch (big.compareTo(BigDecimal.ZERO)) {
                        case -1: {
                            isOk = false;
                            amount = amount + "--" + ExcelErrorCodeEnum.AMOUNT_LT_ZERO.getName();
                        }
                        break;
                        case 0: {
                            direction = BALANCE;
                            amount = "0.00";
                        }
                        break;
                        case 1: {
                            //验证金额是否超过两位小数
                            boolean flag = true;
                            if (amount.contains(".")) {
                                String[] split = amount.split("\\.");
                                if (split[1].length() > 2) {
                                    flag = false;
                                    isOk = false;
                                    amount = amount + "--" + ExcelErrorCodeEnum.AMOUNT_DECIMALS_ERROR.getName();
                                }
                            }
                            if (flag) {
                                amount = big.setScale(2).toString();
                                //方向
                                if (StringUtils.isBlank(direction)) {
                                    isOk = false;
                                    direction = ExcelErrorCodeEnum.BLANK.getName();
                                } else {
                                    if (!DEBIT.equals(direction) && !CREDIT.equals(direction)) {
                                        isOk = false;
                                        direction = direction + "--" + ExcelErrorCodeEnum.INVALID_DIRECTION_VALUE.getName();
                                    }
                                }
                            }
                        }
                        break;
                        default:
                            break;
                    }
                } else {
                    isOk = false;
                    amount = amount + "--" + ExcelErrorCodeEnum.AMOUNT_FORMAT_ERROR.getName();
                }

                OpeningPaymentReceivableExcelVO paymentVO = new OpeningPaymentReceivableExcelVO(code, name, direction, amount);
                if (isOk) {
                    codeSet.add(code);
                    qualifiedList.add(paymentVO);
                } else {
                    disqualificationList.add(paymentVO);
                }
            }
        }
    }


    public List<OpeningPaymentReceivableExcelVO> getQualifiedList() {
        return qualifiedList;
    }

    public void setQualifiedList(List<OpeningPaymentReceivableExcelVO> qualifiedList) {
        this.qualifiedList = qualifiedList;
    }

    public List<OpeningPaymentReceivableExcelVO> getDisqualificationList() {
        return disqualificationList;
    }

    public void setDisqualificationList(List<OpeningPaymentReceivableExcelVO> disqualificationList) {
        this.disqualificationList = disqualificationList;
    }

    public Map<String, OpeningPaymentReceivableDetailVO> getUnitMap() {
        return unitMap;
    }

    public void setUnitMap(Map<String, OpeningPaymentReceivableDetailVO> unitMap) {
        this.unitMap = unitMap;
    }

    public String getUnitInvalid() {
        return unitInvalid;
    }

    public void setUnitInvalid(String unitInvalid) {
        this.unitInvalid = unitInvalid;
    }

    public String getUnitrRepetition() {
        return unitrRepetition;
    }

    public void setUnitrRepetition(String unitrRepetition) {
        this.unitrRepetition = unitrRepetition;
    }

    public Set<String> getCodeSet() {
        return codeSet;
    }

    public void setCodeSet(Set<String> codeSet) {
        this.codeSet = codeSet;
    }
}