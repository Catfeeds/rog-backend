package com.rograndec.feijiayun.chain.business.system.opening.excel;

import com.rograndec.feijiayun.chain.business.system.opening.common.ExcelErrorCodeEnum;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpeningTaxDetailVO;
import com.rograndec.feijiayun.chain.common.constant.status.GoodTaxTypeStatus;
import com.rograndec.feijiayun.chain.utils.excel.IRowReader;
import com.rograndec.feijiayun.chain.utils.finance.DirectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 期初应付（应收）的行讀取器
 */
@Component
@Scope("prototype")
public class OpeningTaxRowReader extends IRowReader {

    private static final Logger logger = LoggerFactory.getLogger(OpeningTaxRowReader.class);


    //合格数据
    private List<OpeningTaxDetailVO> qualifiedList;
    //不合格数据
    private List<OpeningTaxDetailVO> disqualificationList;

    //税率key为税率+税率类型（英文标识）
    private Map<String, OpeningTaxDetailVO> unitMap;
    
    //税率key为税率+税率类型（英文标识）(去重判断)
    private Set<String> set = new HashSet<>();
    private static final String DEBIT = "借";
    private static final String CREDIT = "贷";
    private static final String BALANCE = "平";
    //单位错误提示
    private String unitError;

    public OpeningTaxRowReader() {
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
                String taxTypeName = null;
                String taxRate = null;
                String direction = null;
                String amount = null;
                if (k < size) {
                    //税类型
                	taxTypeName = rowList.get(k++);
                }
                if (k < size) {
                    //税率
                	taxRate = rowList.get(k++);
                }
                if (k < size) {
                    //方向
                    direction = rowList.get(k++);
                }
                if (k < size) {
                    //金额
                    amount = rowList.get(k);
                }

                OpeningTaxDetailVO detailVO = new OpeningTaxDetailVO();
                detailVO.setTaxTypeName(taxTypeName);
                detailVO.setTaxType(GoodTaxTypeStatus.getCode(taxTypeName));
                detailVO.setTaxRate(taxRate);
                detailVO.setDirection(direction);
                detailVO.setAmount(amount);
                
                //税率类型
                if(StringUtils.isBlank(taxTypeName)) {
                	isOk = false;
                	detailVO.setTaxTypeName(ExcelErrorCodeEnum.BLANK.getName());
                }else {
                	if(detailVO.getTaxType() == null || detailVO.getTaxType().equals(-1)) {
                		detailVO.setTaxTypeName(taxTypeName+"-- 不存在对应税类型");
                	}
                	
                }
                //税
                if(StringUtils.isBlank(taxRate)) {
                	isOk = false;
                	detailVO.setTaxRate(ExcelErrorCodeEnum.BLANK.getName());
                }else {
                	if (CollectionUtils.isEmpty(unitMap) || unitMap.get(GoodTaxTypeStatus.getFlag(detailVO.getTaxType())+detailVO.getTaxRate()) == null) {
                        isOk = false;
                        detailVO.setTaxRate(taxRate+"--"+unitError);
                   }
                }
              
               /* //金额
                if (StringUtils.isBlank(amount)) {
                    isOk = false;
                    detailVO.setAmount(ExcelErrorCodeEnum.BLANK.getName());
                } else {
                	try {
                		BigDecimal big = new BigDecimal(amount);
                		 if (big.compareTo(BigDecimal.ZERO) < 0){
                             isOk = false;
                             detailVO.setAmount(detailVO.getAmount()+"--"+ExcelErrorCodeEnum.AMOUNT_LT_ZERO.getName());
                         }else {
                             if(big.compareTo(BigDecimal.ZERO) == 0) {
                             	detailVO.setDirection("平");
                             }
                             BigDecimal odlBig = new BigDecimal(big.doubleValue());
                             big = big.setScale(2, BigDecimal.ROUND_HALF_UP);
                             detailVO.setAmountSelf(big);
                             detailVO.setAmount(big+"");
                             //如果四舍五入的值为0且不为平则为不合法值
                             if(big.compareTo(BigDecimal.ZERO) == 0 && !"平".equals(detailVO.getDirection())) {
                            	 isOk = false;
                                 detailVO.setAmount(detailVO.getAmount()+"--"+ExcelErrorCodeEnum.AMOUNT_FORMAT_ERROR.getName());
                              }
                             
                             if(big.compareTo(BigDecimal.ZERO) > 0) {
                            	 //方向
                            	 if (StringUtils.isBlank(direction)) {
                            		 isOk = false;
                            		 detailVO.setDirection(ExcelErrorCodeEnum.BLANK.getName());
                            	 } else {
                            		 if (!"借".equals(direction) && !"贷".equals(direction)) {
                            			 isOk = false;
                            			 detailVO
                            			 .setDirection(detailVO.getDirection()+"--"+ExcelErrorCodeEnum.INVALID_DIRECTION_VALUE.getName());
                            		 }
                            	 }
                              }
                         }
                		 
                         
                	}catch(Exception e) {
                		isOk = false;
                		detailVO.setAmount(detailVO.getAmount()+"-- 必须是数字");
                	}
                }*/
                
                //金额
                if (StringUtils.isBlank(amount)) {
                    isOk = false;
                    detailVO.setAmount(ExcelErrorCodeEnum.BLANK.getName());
                } else if (DirectionUtils.validAmount(amount)) {
                    //目前excel读取金额时候可能出现金额的值与excel表中的值不一致，无限接近
                    BigDecimal big = new BigDecimal(amount);
                    switch (big.compareTo(BigDecimal.ZERO)) {
                        case -1: {
                            isOk = false;
                            detailVO.setAmount(detailVO.getAmount()+"--"+ExcelErrorCodeEnum.AMOUNT_LT_ZERO.getName());
                        }
                        break;
                        case 0: {
                            direction = BALANCE;
                            big = big.setScale(2, BigDecimal.ROUND_HALF_UP);
                            detailVO.setAmountSelf(big);
                            detailVO.setAmount(big+"");
                            detailVO.setDirection(BALANCE);
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
                                    detailVO.setAmountSelf(big);
                                    detailVO.setAmount(amount);
                                }
                            }
                            if (flag) {
                            	 big = big.setScale(2, BigDecimal.ROUND_HALF_UP);
                                 detailVO.setAmountSelf(big);
                                 detailVO.setAmount(big+"");
                                //方向
                                if (StringUtils.isBlank(direction)) {
                                    isOk = false;
                                    direction = ExcelErrorCodeEnum.BLANK.getName();
                                    detailVO.setDirection(direction);
                                } else {
                                    if (!DEBIT.equals(direction) && !CREDIT.equals(direction)) {
                                        isOk = false;
                                        direction = direction + "--" + ExcelErrorCodeEnum.INVALID_DIRECTION_VALUE.getName();
                                        detailVO.setDirection(direction);
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
                    detailVO.setAmount(amount);
                }
                
                if (isOk) {
                    qualifiedList.add(detailVO);
                } else {
                    disqualificationList.add(detailVO);
                }
            }
        }
    }



    public List<OpeningTaxDetailVO> getQualifiedList() {
		return qualifiedList;
	}

	public void setQualifiedList(List<OpeningTaxDetailVO> qualifiedList) {
		this.qualifiedList = qualifiedList;
	}

	public List<OpeningTaxDetailVO> getDisqualificationList() {
		return disqualificationList;
	}

	public void setDisqualificationList(List<OpeningTaxDetailVO> disqualificationList) {
		this.disqualificationList = disqualificationList;
	}

	public Map<String, OpeningTaxDetailVO> getUnitMap() {
		return unitMap;
	}

	public void setUnitMap(Map<String, OpeningTaxDetailVO> unitMap) {
		this.unitMap = unitMap;
	}

	public String getUnitError() {
        return unitError;
    }

    public void setUnitError(String unitError) {
        this.unitError = unitError;
    }
}