package com.rograndec.feijiayun.chain.business.system.opening.common;


/**
 * Created by ST on 2017/8/23.
 */
public enum ExcelErrorCodeEnum {
    BLANK("E001","ERROR:该字段为必填字段"),
    DATE_FORMAT("E002","ERROR:日期格式输入不正确"),
    NUMBER_FORMAT("E003","ERROR:数字格式输入不正确"),
    NOT_DIGIT("E004","ERROR:不允许输入字母"),
    NOT_EXIST("E005","ERROR:输入了不存在的数值"),
    MAXVALUE("E006","ERROR:超出允许输入最大值"),
    NO_SHELF("E007","ERROR:货位不存在"),
    NO_SUPPLiER("E008","ERROR:供货单位不存在"),
    INVALID_CODE("E009","ERROR:商品编码无效"),
    INVALID_OLD_CODE("E011","ERROR:原始商品编码无效"),
    GOODSATTRIBUTE_INCONFORMITY("E012","ERROR:商品属性不存在或者与品种分类不一致"),

    DOSAGE_INEXISTENCE("E013","ERROR:商品剂型不存在"),
    DOMESTICIMPORT_INEXISTENCE("E014","ERROR:国产进口不存在"),
    UNITNAME_INEXISTENCE("E015","ERROR:单位不存在"),
    BUSINESS_SCOPE_INEXISTENCE("E016","ERROR:经营范围不存在"),
    CHECK_TYPE_INEXISTENCE("E017","ERROR:验收分类不存在"),
    TIME_UNIT_INEXISTENCE("E018","ERROR:日期类型不存在"),
    SPECIAL_DRUGS_INEXISTENCE("E019","ERROR:特殊管理药品/专管药品不存在"),
    SPECIAL_DRUGS_CANNOTWRITE("E020","ERROR:非处方药该字段不能填写"),
    GOODS_CATEGORY_INCONFORMITY("E021","ERROR:商品分类不一致"),
    MEDICALINSURANCETYPE_INCONFORMITY("E022","ERROR:医保类别不存在"),
    EDICALINSURANCETYPE_INCONFORMITY2("E023","ERROR:该要为医保药品，医保类型必填"),
    GOODS_NATURE_INCONFORMITY("E024","ERROR:商品性质不存在"),
    CONFIGURATIONFLAG_INCONFORMITY("E025","ERROR:配置标志不存在"),
    ANY_INEXISTENCE("E026","ERROR:该值不存在"),
    NOT_CARDTYPE("E027","ERROR:当前会员卡类型不存在"),
    MUST_NUM("E028","ERROR:卡号后四位必须为数字"),
    MUST_MEMBER("E029","ERROR:必须是当前企业下的员工"),
    OUT_NUMBER("E030","ERROR:卡号不能超过20位并且需要大于等于4位"),
    MUST_ENTERPRISE("E031","ERROR:必须是组织机构下的企业"),
    SEX("E032","ERROR:性别只能是男或女"),
    ID_NUM("E032","ERROR:身份证号码不能超过18位"),
    ADDRESS_NUM("E033","ERROR:住址不能超过200位"),
    CARDCODE_EXSIST("E034","ERROR:当前卡号在本企业下已经存在"),
    LOTNUM_NOT_EXSIST("E035","ERROR:批号不存在"),
    SHELF_NOT_EXSIST("E036","ERROR:货位名称不存在"),
    INVENTORY_NOT_EXSIST("E037","ERROR:盘点单中不存在该商品"),
    INVENTORY_GOODS_REPEAT("E038","ERROR:盘点单商品重复"),
    TAXRATE_NOT_EXSIST("E039","ERROR:税率不存在"),
    PERIOD_NOT_EXSIST("E040","ERROR:周期类型不存在"),
    MAINTENANCETYPE_NOT_EXSIST("E041","ERROR:养护类型不存在"),
    USER_NOT_EXSIST("E042","ERROR:申请人员不存在"),
    GOODSEXSIST_CODE("E043","ERROR:商品编码重复"),
    MUSTBE_TWOBEATS("E044","ERROR:小数只能是两位"),
    CARDCODE_ONLIST_EXISIT("E045","ERROR:当前卡号已经在录入EXCEL中出现过"),
    STORE_MUSTBE_ZERO("E046","ERROR:储值卡类型中积分必须为0"),
    INTEGRAL_MUSTBE_ZERO("E047","ERROR:积分卡类型中储值必须为0"),
    INVALID_SUPPLiER_CODE("E048","ERROR:供货单位编码无效"),
    REPETITION_SUPPLIER_CODE("E049","ERROR:供货单位编码重复"),
    INVALID_FRANCHISEES_CODE("E050","ERROR:购货单位编码无效"),
    REPETITION_FRANCHISEES_CODE("E051","ERROR:购货单位编码重复"),
    INVALID_DIRECTION_VALUE("E052","ERROR:方向选择不正确"),
    DIRECTION_AMOUNT_INCONFORMITY("E053","ERROR:方向为平,金额应为0"),
    AMOUNT_FORMAT_ERROR("E054","ERROR:金额格式错误"),
    AMOUNT_LT_ZERO("E055","ERROR:金额不允许小于0"),
    AMOUNT_DECIMALS_ERROR("E056","ERROR:金额最多保留两位小数"),
    GOODS_CODE_NULL("E057","ERROR:商品编码为空"),
    GOODS_GENERICNAME_NULL("E058","ERROR:通用名称为空"),
    GOODS_PRICE_NULL("E059","ERROR:单价为空"),
    PRICE_SIZE_ERROR("E056","ERROR:单价最多保留六位小数"),
    TAXRATE_NULL("E057","ERROR:税率为空"),
    TAXRATE_SIZE_ERROR("E058","ERROR:金额最多保留四位小数"),
    PRICE_ORDER_NOT_HAS_GOODS("E059","ERROR:商品所属店铺与当前门店不匹配,不允许导入"),
    TAXRATE_IS_NOT_NUMBER("E060","ERROR:税率不是一个数字类型"),
    PRICE_IS_NOT_NUMBER("E061","ERROR:价格不是一个数字类型"),
    APPROVAL_NUMBER_LENGTH("E060","ERROR:批准文号的长度不能大于100"),
    MOBILE_PHONE_LENGTH("E071","ERROR:手机号长度不能超过20位"),
    EMAIL_LENGTH("E072","ERROR:邮箱长度不能超过50位"),
    WEHCAT_LENGTH("E073","ERROR:微信号长度不能超过20位"),
    QQ_LENGTH("E074","ERROR:QQ长度不能超过20位"),
    SPECIAL_TXT("E075","ERROR:当前文本有特殊字符,请清空重新输入"),
    OLD_GOODS_CODE_NULL("E076","ERROR:原始商品编码为空"),
    REMARK_LENGTH("E077","ERROR:备注长度不能大于200"),
    DISTR_FLAG_INEXISTENCE("E078","ERROR:配货标志不存在"),
    PRICINGPOLICYTYPE_INEXISTENCE("E079","ERROR:销售定价不存在"),
    ;

    private String code;
    private String name;

    ExcelErrorCodeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
