package com.rograndec.feijiayun.chain.business.member.info.constant;

public class MemberInfoConstant {

    //redis中存储会员信息的key
    public final static String MEMBER_INFO_KEY = "member_info";
    //redis中存储合格会员信息的field
    public final static String QUALIFIED_FIELD = "memberInfoQualified";
    //redis中存储不合格会员信息的field
    public final static String DISQUALIFIED_FIELD = "memberInfoDisqualified";
    /*//redis中导入成功数量的计数器
    public final static String MEMBER_COUNTER = "memberInfoCounter";
    //redis中导入成功数据异常信息
    public final static String ERROR_MESSAGE = "errorMessage";*/
}
