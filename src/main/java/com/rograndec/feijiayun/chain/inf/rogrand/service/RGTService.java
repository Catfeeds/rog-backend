package com.rograndec.feijiayun.chain.inf.rogrand.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseQualificationConfigMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.dao.EnterpriseQualificationMapper;
import com.rograndec.feijiayun.chain.common.component.RestTemplateComponent;
import com.rograndec.feijiayun.chain.config.RGTPropertiles;
import com.rograndec.feijiayun.chain.inf.rogrand.constant.RGTResponseCode;
import com.rograndec.feijiayun.chain.inf.rogrand.exception.RGTBusinessException;
import com.rograndec.feijiayun.chain.inf.rogrand.utils.EncryptUtil;
import com.rograndec.feijiayun.chain.inf.rogrand.utils.http.RequestEntity;
import com.rograndec.feijiayun.chain.inf.rogrand.utils.http.RequestHeadEntity;
import com.rograndec.feijiayun.chain.utils.MD5.MD5Utils;
import com.rograndec.feijiayun.chain.utils.http.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 * 功能描述：调用融贯通接口服务
 * Created by ST on 2017/10/31 16:59
 */
@Component
public class RGTService {

    private static Logger logger = LoggerFactory.getLogger(RGTService.class);

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EnterpriseQualificationMapper enterpriseQualificationMapper;

    @Autowired
    private EnterpriseQualificationConfigMapper enterpriseQualificationConfigMapper;

    @Autowired
    private RGTPropertiles propertiles;

    @Autowired
    private RestTemplateComponent restTemplateComponent;

    @Value("${rgt.api.url}")
    private String rgtApiUrl;


    /**
     * 新增企业\企业资质\用户
     * Map<String,Object> map = new HashMap<>();
     map.put("eTel","222222");
     map.put("eParentId","0");
     map.put("eMobile","1212121");
     map.put("eName","东方梅地亚中心");
     map.put("eType","2");
     map.put("uid","9403580");
     map.put("eProvince","110000");
     map.put("eCity","110100");
     map.put("eRegion","110102");
     map.put("eAddress","北京市");
     map.put("isCheckEnterpriseELicenseNo",1);//校验重复
     map.put("eLicenseNo",1234569000);



     List<Map<String,Object>> list = new ArrayList<>();
     Map<String,Object> qualificationMap = new HashMap<>();
     qualificationMap.put("epType","1");
     qualificationMap.put("epNo","1");
     qualificationMap.put("epPic","1");

     qualificationMap.put("epStartTime",new Date());
     qualificationMap.put("epEndTime",new Date());
     list.add(qualificationMap);


     Map<String,Object> qualificationMap1 = new HashMap<>();
     qualificationMap1.put("epType","2");
     qualificationMap1.put("epNo","2");
     qualificationMap1.put("epPic","2");

     qualificationMap1.put("epStartTime",new Date());
     qualificationMap1.put("epEndTime",new Date());
     list.add(qualificationMap1);

     map.put("epList",list);
     *
     * @param enterpriseId
     */
    public void addEnterprise(Map<String,Object> map , Long enterpriseId){
        //融贯同企业新增
        String api = "/biz/v4.2.6/addEnterpriseAndPic.html";
        String paramStr = getParamString(map);

        //成功时返回企业ID，否则返回0
        JSONObject resultJson = HttpClientUtil.sendRGTHttpsPostRequest(rgtApiUrl + api + "?", paramStr,true);
        logger.info("融贯同企业新增返回结果={}",resultJson);
        //企业注册
        excuteRGTEnterprise(enterpriseId,resultJson);

    }

//    /**
//     *  Map<String,Object> objectMap = new HashMap<>();
//     objectMap.put("eLicenseNo","1234569000");
//     Boolean aBoolean = rgtService.checkELicenseNo(objectMap);
//     System.out.println(aBoolean);
//     * 工商注册号校验
//     *
//     * @param map
//     * @return true 已存在 false 不存在
//     */
//    public Boolean checkELicenseNo(Map<String,Object> map){
//        //工商注册号校验
//        String api = "/biz/checkELicenseNo.html";
//        String paramStr = getParamString(map);
//
//        //成功时返回企业ID，否则返回0
//        JSONObject resultJson = HttpClientUtil.sendRGTHttpsPostRequest(rgtApiUrl + api + "?", paramStr,true);
//        logger.info("工商注册号校验返回结果={}",resultJson);
//        String codeTmp = resultJson.getString("code");
//        if(RGTResponseCode.SUCCESS.getCode().equals(codeTmp)){
//            //工商注册号校验请求成功,
//            return resultJson.getBoolean("result");
//        } else {
//            //重复和异常
//            throw new RGTBusinessException(RGTResponseCode.getName(codeTmp));
//        }
//
//
//    }
    private void excuteRGTEnterprise(Long enterpriseId,JSONObject resultJson) {

        String code = resultJson.getString("code");
        if(RGTResponseCode.SUCCESS.getCode().equals(code)){
            JSONObject resultJSON = resultJson.getJSONObject("result");
            Integer rgtEnterpriseId = resultJSON.getInteger("eId");
            if(rgtEnterpriseId != null && rgtEnterpriseId != 0){
                //新增融贯通企业成功
                //同步融贯通返回的企业id
                Enterprise enterprise = new Enterprise();
                enterprise.setId(enterpriseId);
                enterprise.setRgtEnterpriseId(rgtEnterpriseId);
                enterpriseMapper.updateByPrimaryKeySelective(enterprise);
            } else {
                //失败
                logger.warn("新增融贯通企业失败={}",RGTResponseCode.getName(code));
                throw new RGTBusinessException(RGTResponseCode.getName(code));
            }
        } else if(RGTResponseCode.LICENSENO_EXIST.getCode().equals(code)){
            logger.warn("新增融贯通企业失败--证件号已存在={}",RGTResponseCode.getName(code));
            throw new RGTBusinessException(RGTResponseCode.getName(code));
        } else {
            logger.warn("新增融贯通企业失败={}",RGTResponseCode.getName(code));
            throw new RGTBusinessException(RGTResponseCode.getName(code));
        }
    }

    /**
     * 用户注册
     *    Map<String,Object> map = new HashMap<>();
         map.put("username","usertest031");
         map.put("mobile","139111111121");
         map.put("email","9855529451@qq.com");
         map.put("password",333333);
         map.put("eId",0);
         map.put("userType",1);
         map.put("staffType",0);
     * @param rgtUserVO
     * @return
     */
    public Integer registerRGTUser(Map<String,Object> rgtUserVO,Long userId){

        String validateEmail_api = "/userInfos/validateEmail.html";
        String validateUsername_api = "/userInfos/validateUsername.html";
        String validateMobile_api = "/userInfos/validateMobile.html";
        String register_api = "/userInfos/register.html";//注册用户
        Map<String,String> userMap = new HashMap<>();
        userMap.put("username", String.valueOf(rgtUserVO.get("username")));
        String userNameParam = getParamString(userMap);
        Map<String,String> emailMap = new HashMap<>();
        emailMap.put("email",rgtUserVO.get("email").toString());
        String emailParam = getParamString(emailMap);
        Map<String,String> mobileMap = new HashMap<>();
        mobileMap.put("mobile",rgtUserVO.get("mobile").toString());
        String mobilePhoneParam = getParamString(mobileMap);


        JSONObject userNameJson = HttpClientUtil.sendRGTHttpsPostRequest(rgtApiUrl + validateUsername_api + "?",userNameParam, true);
        logger.info("用户名校验结果={}",userNameJson);
        String codeTmp = userNameJson.getString("code");
        if(RGTResponseCode.SUCCESS.getCode().equals(codeTmp)){
            //验证用户名接口请求成功,不重复
        } else {
            //重复和异常
            throw new RGTBusinessException(RGTResponseCode.getName(codeTmp));
        }
/*
        JSONObject emailJson = HttpClientUtil.sendRGTHttpsPostRequest("http://192.168.22.0:8088"  + validateEmail_api + "?",emailParam, true);
*/
        JSONObject emailJson = HttpClientUtil.sendRGTHttpsPostRequest(rgtApiUrl + validateEmail_api + "?",emailParam, true);
        logger.info("邮箱校验结果={}",emailJson);
        codeTmp = emailJson.getString("code");
        if(RGTResponseCode.SUCCESS.getCode().equals(codeTmp)){
            //验证邮箱接口请求成功
            String result = emailJson.getString("result");
            if("true".equals(result)){
                //result等于true 表示重复
                throw new RGTBusinessException(RGTResponseCode.EMAIL_USED.getName());
            }
        } else {
            throw new RGTBusinessException(RGTResponseCode.getName(codeTmp));
        }
        JSONObject mobilePhoneJson = HttpClientUtil.sendRGTHttpsPostRequest(rgtApiUrl + validateMobile_api + "?",mobilePhoneParam, true);
        logger.info("手机号校验结果={}",mobilePhoneJson);
        codeTmp = mobilePhoneJson.getString("code");
        if(RGTResponseCode.SUCCESS.getCode().equals(codeTmp)){
            //验证手机号接口请求成功
        } else {
            throw new RGTBusinessException(RGTResponseCode.getName(codeTmp));
        }

        //插入融贯通库
        String registerParam = getParamString(rgtUserVO);
        JSONObject registerUserJson = HttpClientUtil.sendRGTHttpsPostRequest(rgtApiUrl + register_api + "?",registerParam, true);
/*
        JSONObject registerUserJson = HttpClientUtil.sendRGTHttpsPostRequest("http://192.168.22.0:8088" + register_api + "?",registerParam, true);
*/
        logger.info("注册结果={}",registerUserJson);
        codeTmp = registerUserJson.getString("code");
        if(RGTResponseCode.SUCCESS.getCode().equals(codeTmp)){
            Integer rgtUserId = registerUserJson.getInteger("result");
            //注册成功
            //融贯通用户id绑定saas用户
            User user = new User();
            user.setId(userId);
            user.setRgtUserId(rgtUserId);
            userMapper.updateByPrimaryKeySelective(user);
            return rgtUserId;
        } else {
            //注册失败
            throw new RGTBusinessException(RGTResponseCode.getName(codeTmp));
        }
    }

    /**
     * 用户账号校验重复性
     *  Map<String,Object> map = new HashMap<>();
        map.put("username","ddr3");
     * @param map
     * @return
     */
    public boolean validateRGTUserName(Map<String,Object> map){

        String validateUsername_api = "/userInfos/validateUsername.html";
        Map<String,Object> userMap = new HashMap<>();
        userMap.put("username",map.get("username"));
        String userNameParam = getParamString(userMap);
        JSONObject userNameJson = HttpClientUtil.sendRGTHttpsPostRequest(rgtApiUrl + validateUsername_api + "?",userNameParam, true);
        logger.info("用户名校验结果={}",userNameJson);
        String codeTmp = userNameJson.getString("code");
        if(RGTResponseCode.SUCCESS.getCode().equals(codeTmp)){
            //验证用户名接口请求成功,不重复
            return true ;
        } else  {
            //重复和异常
            throw new RGTBusinessException(RGTResponseCode.getName(codeTmp));
        }
    }
    /**
     * 用户email校验重复性
     *  Map<String,Object> map = new HashMap<>();
        map.put("email","985552943@qq.com");
     * @param map
     * @return
     */
    public boolean validateRGTEmail(Map<String,Object> map){
        String validateEmail_api = "/userInfos/validateEmail.html";

        Map<String,Object> emailMap = new HashMap<>();
        emailMap.put("email",map.get("email"));
        String emailParam = getParamString(emailMap);


        JSONObject emailJson = HttpClientUtil.sendRGTHttpsPostRequest(rgtApiUrl + validateEmail_api + "?",emailParam, true);
        logger.info("邮箱校验结果={}",emailJson);
        String codeTmp = emailJson.getString("code");
        if(RGTResponseCode.SUCCESS.getCode().equals(codeTmp)){
            //验证邮箱接口请求成功
            String result = emailJson.getString("result");
            if("true".equals(result)){
                //result等于true 表示重复
                throw new RGTBusinessException(RGTResponseCode.EMAIL_USED.getName());
            }
            return true;
        } else {
            throw new RGTBusinessException(RGTResponseCode.getName(codeTmp));
        }
    }

    /**
     * 校验用户手机号唯一
     * Map<String,Object> map = new HashMap<>();
        map.put("mobile","12111");
     * @param map
     * @return
     */
    public boolean validateRGTMobile(Map<String,Object> map){

        String validateMobile_api = "/userInfos/validateMobile.html";

        Map<String,Object> mobileMap = new HashMap<>();
        mobileMap.put("mobile",map.get("mobile"));
        String mobilePhoneParam = getParamString(mobileMap);

        JSONObject mobilePhoneJson = HttpClientUtil.sendRGTHttpsPostRequest(rgtApiUrl + validateMobile_api + "?",mobilePhoneParam, true);
        logger.info("手机号校验结果={}",mobilePhoneJson);
        String codeTmp = mobilePhoneJson.getString("code");
        if(RGTResponseCode.SUCCESS.getCode().equals(codeTmp)){
            //验证手机号接口请求成功
            return true;
        } else {
            throw new RGTBusinessException(RGTResponseCode.getName(codeTmp));
        }

    }



    /**
     * 用户更新
     * 更新用户名使用userName :"3333"
     * Map<String,Object> map = new HashMap<>();
     map.put("uid","9403980");
     map.put("userName","ddr32");
     map.put("mobile","12111");
     map.put("email","2233322@qq.com");
     map.put("eId",0);
     map.put("userType",1);
     map.put("staffType",0);
     * @param rgtUserVO
     * @return
     */
    public String updateRGTUser(Map<String,Object> rgtUserVO){

        String validateEmail_api = "/userInfos/validateEmail";
        String validateUsername_api = "/userInfos/validateUsername";
        String validateMobile_api = "/userInfos/validateMobile";
        String update_api = "/userInfos/updateUser.html";//更新用户


//        JSONObject userNameJson = restTemplateComponent.get(rgtApiUrl + validateUsername_api, userNameParam);
//        String codeTmp = userNameJson.getString("code");
//        if(RGTResponseCode.SUCCESS.getCode().equals(codeTmp)){
//            //验证用户名接口请求成功
//            String userNameResult = userNameJson.getString("result");
//            if(RGTResponseCode.USERNAME_EXIST.getCode().equals(userNameResult)){
//                //用户名重复
//                throw new RGTBusinessException(RGTResponseCode.getName(codeTmp));
//            }
//        }
//        JSONObject emailJson = restTemplateComponent.post(rgtApiUrl + validateEmail_api, emailParam);
//        codeTmp = emailJson.getString("code");
//        if(RGTResponseCode.SUCCESS.getCode().equals(codeTmp)){
//            //验证邮箱接口请求成功
//            String emailResult = emailJson.getString("result");
//            if(RGTResponseCode.EMAIL_USED.getCode().equals(emailResult)){
//                //邮箱重复
//                throw new RGTBusinessException(RGTResponseCode.getName(codeTmp));
//            }
//        }
//        JSONObject mobilePhoneJson = restTemplateComponent.post(rgtApiUrl + validateMobile_api, mobilePhoneParam);
//        codeTmp = mobilePhoneJson.getString("code");
//        if(RGTResponseCode.SUCCESS.getCode().equals(codeTmp)){
//            //验证手机号接口请求成功
//            String mobilePhoneResult = mobilePhoneJson.getString("result");
//            if(RGTResponseCode.PHONE_USED.getCode().equals(mobilePhoneResult)){
//                //手机号重复
//                throw new RGTBusinessException(RGTResponseCode.getName(codeTmp));
//            }
//        }

        //插入融贯通库
        String updateParam = getParamString(rgtUserVO);
        JSONObject registerUserJson = HttpClientUtil.sendRGTHttpsPostRequest(rgtApiUrl + update_api + "?", updateParam,true);
        String codeTmp = registerUserJson.getString("code");
        if(RGTResponseCode.SUCCESS.getCode().equals(codeTmp)){
            //注册成功
            return registerUserJson.getString("result");
        } else {
            //注册失败
            throw new RGTBusinessException(RGTResponseCode.getName(codeTmp));
        }
    }

    /**
     * 用户更新密码
     *  Map<String,Object> map = new HashMap<>();
         map.put("uid","9403581");
         map.put("password","222222");
         map.put("oldPassword","111111");
         map.put("flag",3);
     * @return
     */
    public String updateUserPwd(Map<String,Object> map){

        String update_api = "/userInfos/updateUser.html";//更新用户
        String updateParam = getParamString(map);
        JSONObject registerUserJson = HttpClientUtil.sendRGTHttpsPostRequest(rgtApiUrl + update_api + "?", updateParam,true);
        String codeTmp = registerUserJson.getString("code");
        if(RGTResponseCode.SUCCESS.getCode().equals(codeTmp)){
            //密码成功
            logger.info("密码修改成功");
            return registerUserJson.getString("result");
        } else {
            //注册失败
            throw new RGTBusinessException(RGTResponseCode.getName(codeTmp));
        }
    }

    /**
     * 重置密码
     * Map<String,Object> map = new HashMap<>();
        map.put("username","usertest031");
        map.put("password","21111199");
     * @return
     */
    public String updateUserPwdByUserName(Map<String,Object> paramMap){

        Map<String,Object> map = new HashMap<>();
        map.put("username",paramMap.get("username"));
        JSONObject rgtUser = getRGTUser(map);
        String uid = rgtUser.getString("uid");
        logger.info("重置密码接口中,根据用户名获取用户信息,用户id={}",uid);
        Map<String,Object> map1 = new HashMap<>();
        map1.put("uid",uid);
        map1.put("password",paramMap.get("password"));
        String  modifyPasswordKey = MD5Utils.getMD5String(uid+"saas"+"mph");
        map1.put("modifyPasswordKey",modifyPasswordKey);
        map1.put("flag",1);
        return updateUserPwd(map1);
    }

    /**
     * 根据账号名称获取用户
     *  Map<String,Object> map = new HashMap<>();
     *  map.put("username","ddr32");
     * @param map
     * @return
     */
    public JSONObject getRGTUser(Map<String,Object> map){

        String getUser_api = "/userInfos/queryUser.html";

        //插入融贯通库
        String updateParam = getParamString(map);
        JSONObject registerUserJson = HttpClientUtil.sendRGTHttpsPostRequest(rgtApiUrl + getUser_api + "?", updateParam,true);
        String codeTmp = registerUserJson.getString("code");
        if(RGTResponseCode.SUCCESS.getCode().equals(codeTmp)){
            //成功
            return registerUserJson.getJSONObject("result");
        } else {
            //注册失败
            throw new RGTBusinessException(RGTResponseCode.getName(codeTmp));
        }
    }


    /**
     * 根据账号id获取用户
     * @param map
     * @return
     */
    public JSONObject getRGTUserByID(Map<String,String> map){

        String getUser_api = "/userInfos/queryUserByUid.html";

        //插入融贯通库
        String updateParam = getParamString(map);
        JSONObject registerUserJson = HttpClientUtil.sendRGTHttpsPostRequest(rgtApiUrl + getUser_api + "?", updateParam,true);
        String codeTmp = registerUserJson.getString("code");
        if(RGTResponseCode.SUCCESS.getCode().equals(codeTmp)){
            //密码成功
            return registerUserJson.getJSONObject("result");
        } else {
            //注册失败
            throw new RGTBusinessException(RGTResponseCode.getName(codeTmp));
        }
    }

    /**
     *获取企业资质
     * @param map
     * @return
     */
    public JSONObject getRGTEnterprisePics(Map<String,String> map){

        String getEnterprise_api = "/biz/queryEnterprisePics.html";

        //插入融贯通库
        String updateParam = getParamString(map);
        JSONObject registerEnterpriseJson = HttpClientUtil.sendRGTHttpsPostRequest(rgtApiUrl + getEnterprise_api + "?", updateParam,true);
        logger.info("企业信息={}",registerEnterpriseJson);
        String codeTmp = registerEnterpriseJson.getString("code");
        if(RGTResponseCode.SUCCESS.getCode().equals(codeTmp)){
            //密码成功
            return registerEnterpriseJson.getJSONObject("result");
        } else {
            //注册失败
            throw new RGTBusinessException(RGTResponseCode.getName(codeTmp));
        }
    }

    public JSONObject getRGTEnterprise(Map<String,String> map){

        String getEnterprise_api = "/biz/queryEnterprise.html";

        //插入融贯通库
        String updateParam = getParamString(map);
        JSONObject registerEnterpriseJson = HttpClientUtil.sendRGTHttpsPostRequest(rgtApiUrl + getEnterprise_api + "?", updateParam,true);
        logger.info("企业信息={}",registerEnterpriseJson);
        String codeTmp = registerEnterpriseJson.getString("code");
        if(RGTResponseCode.SUCCESS.getCode().equals(codeTmp)){
            //密码成功
            return registerEnterpriseJson.getJSONObject("result");
        } else {
            //注册失败
            throw new RGTBusinessException(RGTResponseCode.getName(codeTmp));
        }
    }



    /**
     * 登录
     * Map<String,String> map = new HashMap<>();
         map.put("username","zhaiweitest20171114");
         map.put("password","123456");
     * @param map
     * @return
     */
    public JSONObject loginUser(Map<String,String> map){

        String login_api = "/userInfos/login.html";
        String updateParam = getParamString(map);
        JSONObject registerUserJson = HttpClientUtil.sendRGTHttpsPostRequest(rgtApiUrl + login_api + "?", updateParam,true);
        String codeTmp = registerUserJson.getString("code");
        if(RGTResponseCode.SUCCESS.getCode().equals(codeTmp)){
            //登录成功
            return registerUserJson.getJSONObject("result");
        } else {
            //登录失败
            throw new RGTBusinessException(RGTResponseCode.getName(codeTmp));
        }
    }

    /**
     * 获取请求消息体
     * @param param 接口请求参数
     * @return
     */
    private String getParamString(Object param) {
        RequestEntity requestEntity = new RequestEntity();
        RequestHeadEntity head = new RequestHeadEntity();
        head.setAppId("11");
        head.setMethod("142005");
        head.setAppSecret("1a8637b25d9369ae5ea2cb6c8160b181");
        head.setSerialNumber("120319181305000011");
        head.setVerison("1");
        head.setSourceCode("saas");
        head.setTerminalstate("0");
        head.setImei("000000000000000");
        head.setSysVersion("5.1");
        head.setAppType("3");
        head.setMacAddress("08:00:27:01:b2:d7");
        head.setAppVersion("5.3.13");

        requestEntity.setBody(param);//接口参数
        requestEntity.setHead(head);//

        String mac = EncryptUtil.encrypt(JSON.toJSONString(head), JSON.toJSONString(param));
        requestEntity.setMac(mac);
        //请求的参数
        return "dataJson=" + JSON.toJSONString(requestEntity);
    }



    public static void main(String[] args) {
        RGTService rgtService = new RGTService();
        System.out.println(rgtService.getParamString(123));

//        String url = "http://api.test.rograndec.net/userInfos/login.html?dataJson=";
//        restTemplateComponent.get(url,"");

    }

}