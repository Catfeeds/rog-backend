
package com.rograndec.feijiayun.chain.business.member.excel;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.member.info.entity.MemberInfo;
import com.rograndec.feijiayun.chain.business.member.info.vo.MemberExcelVO;
import com.rograndec.feijiayun.chain.business.member.set.entity.MemberCardType;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.opening.common.ExcelErrorCodeEnum;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.ExcelMethodUtils;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.excel.IRowReader;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Scope("prototype")
public class MemberInfoIRowReader extends IRowReader {

    private static final Logger logger = LoggerFactory.getLogger(MemberInfoIRowReader.class);

    //正确会员信息
    private List<MemberExcelVO> qualifiedList;

    //不正确会员信息
    private List<MemberExcelVO> disqualificationList;

    //插入成功的数据
    private List<MemberInfo> insertList;

    //会员卡类型
    private List<MemberCardType> cardTypeList;

    //发卡门店类型
    private List<Enterprise> enterprisesList;

    //发卡人员类型
    private List<User> usersList;

    //当前企业下的卡号
    private List<MemberInfo> memberInfoList;

    private List<String> excelCardCode = new ArrayList<>();

    //当前登陆用户
    private UserVO user;

    public List<MemberExcelVO> getQualifiedList() {
        return qualifiedList;
    }

    public void setQualifiedList(List<MemberExcelVO> qualifiedList) {
        this.qualifiedList = qualifiedList;
    }

    public List<MemberExcelVO> getDisqualificationList() {
        return disqualificationList;
    }

    public void setDisqualificationList(List<MemberExcelVO> disqualificationList) {
        this.disqualificationList = disqualificationList;
    }

    public List<MemberCardType> getCardTypeList() {
        return cardTypeList;
    }

    public void setCardTypeList(List<MemberCardType> cardTypeList) {
        this.cardTypeList = cardTypeList;
    }

    public List<Enterprise> getEnterprisesList() {
        return enterprisesList;
    }

    public void setEnterprisesList(List<Enterprise> enterprisesList) {
        this.enterprisesList = enterprisesList;
    }

    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }

    public List<MemberInfo> getInsertList() {
        return insertList;
    }

    public void setInsertList(List<MemberInfo> insertList) {
        this.insertList = insertList;
    }

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }

    public List<MemberInfo> getMemberInfoList() {
        return memberInfoList;
    }

    public void setMemberInfoList(List<MemberInfo> memberInfoList) {
        this.memberInfoList = memberInfoList;
    }

    public List<String> getExcelCardCode() {
        return excelCardCode;
    }

    public void setExcelCardCode(List<String> excelCardCode) {
        this.excelCardCode = excelCardCode;
    }

    @Override
    public void getRows(int sheetIndex, int curRow, List<String> rowList) {

        Long enterPriseId = 0L;

        /**
         * 会员卡类型的类别 类别（0-积分+储值；1-仅积分；2-仅储值）
         */
        Integer type = 0;

        if (logger.isDebugEnabled()) {
            logger.debug("Sheet={},Row={},Data={}", sheetIndex, curRow, rowList);
        }
        int k = 0;
        if (curRow > 3) {//从第四行开始计算（excel行号从0开始）
            if (rowList != null && rowList.size() > 0) {

                String cardTypeName = rowList.get(k++);//会员卡类型名称
                String cardCode = rowList.get(k++);//会员卡号
                String sendCardTime = rowList.get(k++);//发卡时间
                String sendCardManName = rowList.get(k++);//发卡人姓名
                String sendCardStorerName = rowList.get(k++);//发卡门店名称
                String totalIntegral = rowList.get(k++);//累积积分
                String currentIntegral = rowList.get(k++);//当前积分
                String totalStoredAmount = rowList.get(k++);//累积储值
                String currentStoredAmount = rowList.get(k++);//当前储值
                String memberName = rowList.get(k++);//会员姓名
                String sex = rowList.get(k++);//性别
                String birthDate = rowList.get(k++);//出生日期
                String idNum = rowList.get(k++);//身份证号
                String mobilePhone = rowList.get(k++);//手机
                String email = rowList.get(k++);//邮箱
                String wechatNum = rowList.get(k++);//微信
                String qqNum = rowList.get(k++);//QQ
                String adderss = rowList.get(k++);//住址

                //excel操作的使用的会员信息实体
                MemberExcelVO memberExcelVO = new MemberExcelVO();
                //正确的数据按照能保存入库的方式构建
                MemberInfo memberInfo = new MemberInfo();
                boolean isOK = true;

                //会员卡类型
                if(StringUtils.isBlank(cardTypeName)){
                    isOK = false;
                    memberExcelVO.setCardTypeName(ExcelErrorCodeEnum.BLANK.getName());
                } else {
                    boolean flag = false;
                    for (MemberCardType m: cardTypeList) {
                        if (m.getName().equals(cardTypeName)){
                            memberInfo.setCardTypeName(cardTypeName);
                            memberInfo.setCardTypeId(m.getId());
                            memberInfo.setCardCodeType(0);//默认导入会员卡号类型
                            memberExcelVO.setCardTypeName(cardTypeName);
                            type = m.getType();
                            flag = true;
                        }
                    }
                    if (!flag){
                        isOK = false;
                        memberExcelVO.setCardTypeName(ExcelErrorCodeEnum.NOT_CARDTYPE.getName());
                    }

                }

                //会员卡号
                if (StringUtils.isBlank(cardCode)){
                    isOK = false;
                    memberExcelVO.setCardCode(ExcelErrorCodeEnum.BLANK.getName());
                }else {
                    if (cardCode.length() > 20 || cardCode.length() < 4){
                        isOK = false;
                        memberExcelVO.setCardCode(ExcelErrorCodeEnum.OUT_NUMBER.getName());
                    }else {
                        if (cardCode.substring(cardCode.length() - 4,cardCode.length()).matches("[0-9]+")){
                            boolean flagCode = true;
                            boolean flagExcel = true;
                            if (excelCardCode.contains(cardCode)){
                                isOK = false;
                                memberExcelVO.setCardCode(ExcelErrorCodeEnum.CARDCODE_ONLIST_EXISIT.getName());
                                flagExcel = false;
                            }
                            if (flagExcel){
                                for (MemberInfo m:memberInfoList) {
                                    if (cardCode.equals(m.getCardCode())){
                                        flagCode = false;
                                    }
                                }
                                if (flagCode){
                                    memberExcelVO.setCardCode(cardCode);
                                    memberInfo.setCardCode(cardCode);
                                    excelCardCode.add(cardCode);
                                }else{
                                    isOK = false;
                                    memberExcelVO.setCardCode(ExcelErrorCodeEnum.CARDCODE_EXSIST.getName());
                                }
                            }
                        }else{
                            isOK = false;
                            memberExcelVO.setCardCode(ExcelErrorCodeEnum.MUST_NUM.getName());
                        }
                    }
                }
                if (StringUtils.isBlank(sendCardTime)){
                    isOK = false;
                    memberExcelVO.setSendCardTime(ExcelErrorCodeEnum.BLANK.getName());
                }else{
                    if (!ExcelMethodUtils.checkPattern(sendCardTime)){
                        isOK = false;
                        memberExcelVO.setSendCardTime(ExcelErrorCodeEnum.DATE_FORMAT.getName());
                    }else{
                        String date = ExcelMethodUtils.getDate(sendCardTime);
                        memberExcelVO.setSendCardTime(date);
                        memberInfo.setSendCardTime(DateUtils.StringToDate(date, DateStyle.YYYY_MM_DD_HH_MM_SS));
                    }
                }
                //发卡门店名称
                if (StringUtils.isBlank(sendCardStorerName)){
                    isOK = false;
                    memberExcelVO.setSendCardStorerName(ExcelErrorCodeEnum.BLANK.getName());
                }else {
                    boolean flag = false;
                    for (Enterprise e:enterprisesList) {
                        if (e.getName().equals(sendCardStorerName)){
                            memberExcelVO.setSendCardStorerName(sendCardStorerName);
                            memberInfo.setSendCardStorerId(e.getId());
                            memberInfo.setSendCardStorerCode(e.getCode());
                            memberInfo.setSendCardStorerName(sendCardStorerName);
                            enterPriseId = e.getId();
                            flag = true;
                        }
                    }
                    if (!flag) {
                        isOK = false;
                        memberExcelVO.setSendCardStorerName(ExcelErrorCodeEnum.MUST_ENTERPRISE.getName());
                    }
                }
                //发卡人姓名
                if (StringUtils.isBlank(sendCardManName)){
                    isOK = false;
                    memberExcelVO.setSendCardManName(ExcelErrorCodeEnum.BLANK.getName());
                }else {
                    boolean flag = false;
                    for (User u:usersList) {
                        if (u.getEnterpriseId().equals(enterPriseId) && u.getName().equals(sendCardManName)){
                            memberExcelVO.setSendCardManName(sendCardManName);
                            memberInfo.setSendCardManId(u.getId());
                            memberInfo.setSendCardManCode(u.getCode());
                            memberInfo.setSendCardManName(u.getName());
                            flag = true;
                        }
                    }
                    if (!flag){
                        isOK = false;
                        memberExcelVO.setSendCardManName(ExcelErrorCodeEnum.MUST_MEMBER.getName());
                    }
                }
                //累计积分
                if (isDecimals(totalIntegral) || isPureDigital(totalIntegral)){
                    /**
                     * 当前是储值卡类型就不能填
                     */
                    if (type == 2 && !"0".equals(totalIntegral.trim())){
                        isOK = false;
                        memberExcelVO.setTotalIntegral(ExcelErrorCodeEnum.STORE_MUSTBE_ZERO.getName());
                    }else{
                        if (isDecimals(totalIntegral)){
                            if (!hasTwoBeats(totalIntegral)){
                                isOK = false;
                                memberExcelVO.setTotalIntegral(ExcelErrorCodeEnum.MUSTBE_TWOBEATS.getName());
                            }else {
                                memberExcelVO.setTotalIntegral(totalIntegral);
                                memberInfo.setTotalIntegral(new BigDecimal(totalIntegral));
                            }
                        }else {
                            memberExcelVO.setTotalIntegral(totalIntegral);
                            memberInfo.setTotalIntegral(new BigDecimal(totalIntegral));
                        }
                    }
                }else {
                    isOK = false;
                    memberExcelVO.setTotalIntegral(ExcelErrorCodeEnum.NUMBER_FORMAT.getName());
                }
                //当前积分
                if (isDecimals(currentIntegral) || isPureDigital(currentIntegral)){
                    if (type == 2 && !"0".equals(currentIntegral.trim())){
                        isOK = false;
                        memberExcelVO.setCurrentIntegral(ExcelErrorCodeEnum.STORE_MUSTBE_ZERO.getName());
                    }else {
                        if (isDecimals(currentIntegral)){
                            if (!hasTwoBeats(currentIntegral)){
                                isOK = false;
                                memberExcelVO.setCurrentIntegral(ExcelErrorCodeEnum.MUSTBE_TWOBEATS.getName());
                            }else {
                                memberExcelVO.setCurrentIntegral(currentIntegral);
                                memberInfo.setCurrentIntegral(new BigDecimal(currentIntegral));
                            }
                        }else {
                            memberExcelVO.setCurrentIntegral(currentIntegral);
                            memberInfo.setCurrentIntegral(new BigDecimal(currentIntegral));
                        }
                    }
                }else {
                    isOK = false;
                    memberExcelVO.setCurrentIntegral(ExcelErrorCodeEnum.NUMBER_FORMAT.getName());
                }
                //累计储值
                if (isDecimals(totalStoredAmount) || isPureDigital(totalStoredAmount)){
                    if (1 == type && !"0".equals(totalStoredAmount.trim())){
                        isOK = false;
                        memberExcelVO.setTotalStoredAmount(ExcelErrorCodeEnum.INTEGRAL_MUSTBE_ZERO.getName());
                    }else {
                        if (isDecimals(totalStoredAmount)){
                            if (!hasTwoBeats(totalStoredAmount)){
                                isOK = false;
                                memberExcelVO.setTotalStoredAmount(ExcelErrorCodeEnum.MUSTBE_TWOBEATS.getName());
                            }else {
                                memberExcelVO.setTotalStoredAmount(totalStoredAmount);
                                memberInfo.setTotalStoredAmount(new BigDecimal(totalStoredAmount));
                            }
                        }else {
                            memberExcelVO.setTotalStoredAmount(totalStoredAmount);
                            memberInfo.setTotalStoredAmount(new BigDecimal(totalStoredAmount));
                        }
                    }
                }else {
                    isOK = false;
                    memberExcelVO.setTotalStoredAmount(ExcelErrorCodeEnum.NUMBER_FORMAT.getName());
                }
                //当前储值
                if (isDecimals(currentStoredAmount) || isPureDigital(currentStoredAmount)){
                    if (1 == type && !"0".equals(currentStoredAmount.trim())){
                        isOK = false;
                        memberExcelVO.setCurrentStoredAmount(ExcelErrorCodeEnum.INTEGRAL_MUSTBE_ZERO.getName());
                    }else{
                        if (isDecimals(currentStoredAmount)){
                            if (!hasTwoBeats(currentStoredAmount)){
                                isOK = false;
                                memberExcelVO.setCurrentStoredAmount(ExcelErrorCodeEnum.MUSTBE_TWOBEATS.getName());
                            }else {
                                memberExcelVO.setCurrentStoredAmount(currentStoredAmount);
                                memberInfo.setCurrentStoredAmount(new BigDecimal(currentStoredAmount));
                            }
                        }else {
                            memberExcelVO.setCurrentStoredAmount(currentStoredAmount);
                            memberInfo.setCurrentStoredAmount(new BigDecimal(currentStoredAmount));
                        }
                    }
                 }else {
                    isOK = false;
                    memberExcelVO.setCurrentStoredAmount(ExcelErrorCodeEnum.NUMBER_FORMAT.getName());
                }
                //会员姓名[必填项]
                if (StringUtils.isBlank(memberName)){
                    isOK = false;
                    memberExcelVO.setMemberName(ExcelErrorCodeEnum.BLANK.getName());
                }else {
                    if (specialTxt(memberName)){
                        isOK = false;
                        memberExcelVO.setMemberName(ExcelErrorCodeEnum.SPECIAL_TXT.getName());
                    }else {
                        memberExcelVO.setMemberName(memberName.trim());
                        memberInfo.setMemberName(memberName.trim());
                    }
                }
                //性别
                if (sex.equals("男") || sex.equals("女")){
                    memberExcelVO.setSex(sex);
                    memberInfo.setSex(sex.equals("男") ? 0 : 1);
                }else{
                    isOK = false;
                    memberExcelVO.setSex(ExcelErrorCodeEnum.SEX.getName());
                }
                //出生日期
                if (ExcelMethodUtils.checkPattern(birthDate)){
                    String date = ExcelMethodUtils.getDate(birthDate);
                    memberExcelVO.setBirthDate(date);
                    memberInfo.setBirthDate(DateUtils.StringToDate(date, DateStyle.YYYY_MM_DD_HH_MM_SS));
                }else {
                    isOK = false;
                    memberExcelVO.setBirthDate(ExcelErrorCodeEnum.DATE_FORMAT.getName());
                }
                //身份证号
                if (idNum.length() <= 18){
                    memberExcelVO.setIdNum(idNum);
                    memberInfo.setIdNum(idNum);
                }else{
                    isOK = false;
                    memberExcelVO.setIdNum(ExcelErrorCodeEnum.ID_NUM.getName());
                }
                //手机
                if (mobilePhone.length() <= 20){
                    memberExcelVO.setMobilePhone(mobilePhone);
                    memberInfo.setMobilePhone(mobilePhone);
                }else {
                    isOK = false;
                    memberExcelVO.setMobilePhone(ExcelErrorCodeEnum.MOBILE_PHONE_LENGTH.getName());
                }
                //邮箱
                if (email.length() <= 50){
                    memberExcelVO.setEmail(email);
                    memberInfo.setEmail(email);
                }else {
                    isOK = false;
                    memberExcelVO.setEmail(ExcelErrorCodeEnum.EMAIL_LENGTH.getName());
                }
                //微信
                if (wechatNum.length() <= 20){
                    memberExcelVO.setWechatNum(wechatNum);
                    memberInfo.setWechatNum(wechatNum);
                }else {
                    isOK = false;
                    memberExcelVO.setWechatNum(ExcelErrorCodeEnum.WEHCAT_LENGTH.getName());
                }
                //QQ
                if (qqNum.length() <= 20){
                    memberExcelVO.setQqNum(qqNum);
                    memberInfo.setQqNum(qqNum);
                }else {
                    isOK = false;
                    memberExcelVO.setQqNum(ExcelErrorCodeEnum.QQ_LENGTH.getName());
                }
                //住址
                if (adderss.length() > 200){
                    isOK = false;
                    memberExcelVO.setAdderss(ExcelErrorCodeEnum.ADDRESS_NUM.getName());
                }else{
                    memberExcelVO.setAdderss(adderss);
                    memberInfo.setAdderss(adderss);
                }

                if(isOK){
                    qualifiedList.add(memberExcelVO);
//                    //enterprise
//                    memberInfo.setEnterpriseId(user.getEnterpriseId());
//                    //parentID
//                    memberInfo.setParentId(user.getParentId());
                    //password
                    memberInfo.setPassword("000000");
                    //passwordConfirm
                    memberInfo.setPasswordConfirm("000000");
                    //status
                    memberInfo.setStatus(0);
                    insertList.add(memberInfo);
                } else {
                    disqualificationList.add(memberExcelVO);
                }
            }
        }
    }

    /**
     * 时间格式匹配返回true
     * @param source
     * @return
     */

private static boolean checkPattern(String source) {
        boolean isOK = true;
        if(Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", source)
                || Pattern.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}-\\d{2}-\\d{2}$", source)
                || Pattern.matches("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$", source)
                || Pattern.matches("^\\d{4}/\\d{2}/\\d{2}$", source)
                || Pattern.matches("^\\d{4}/\\d{2}/\\d{2} \\d{2}/\\d{2}/\\d{2}$", source)
                || Pattern.matches("^\\d{4}\\d{2}\\d{2}$", source)
                || Pattern.matches("^\\d{4}\\d{2}\\d{2} \\d{2}\\d{2}\\d{2}$", source)
                || Pattern.matches("^\\d{4}\\.\\d{2}\\.\\d{2}$", source)
                || Pattern.matches("^\\d{4}\\.\\d{2}\\.\\d{2} \\d{2}\\.\\d{2}\\.\\d{2}$", source)){
        } else {
            isOK = false;
        }
        return isOK;
    }


    /**
     * 校验一个字符串是否为整数
     * @param string
     * @return true 为整数
     */


private  boolean isPureDigital(String string) {
        String regEx1 = "[1-9]\\d*";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches()) {
            return true;
        }else {
            return false;
        }
    }



    /**
     * 校验一个字符串是否小数
     * @param string
     * @return true 为小数
     */


private  boolean isDecimals(String string) {
        String regEx1 = "\\d+(\\.\\d{1,4})?";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches()) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 判断字符串是否小数保留两位
     */
    private boolean hasTwoBeats(String s){
        Pattern p;
        p = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
        Matcher match = p.matcher(s);
        if(match.matches() == false){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 判断是否有特殊字符
     */
    private boolean specialTxt(String s){
        //String trg = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\u4e00-\\u9fa5_a-zA-Z0-9]";
        String trg = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\u4e00-\\u9fa5_a-zA-Z0-9]";
        Pattern p = Pattern.compile(trg);
        Matcher m = p.matcher(s);
        String result = m.replaceAll("").trim();
        if (result.length() > 0){
            return true;
        }
        return false;
    }

}
