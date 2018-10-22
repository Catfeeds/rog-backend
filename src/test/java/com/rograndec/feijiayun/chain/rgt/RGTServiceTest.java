//package com.rograndec.feijiayun.chain.rgt;
//
//import com.alibaba.fastjson.JSONObject;
//import com.rograndec.feijiayun.chain.inf.rogrand.service.RGTService;
//import com.rograndec.feijiayun.chain.utils.http.HttpClientUtil;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.*;
//
///**
// * 功能描述：
// * Created by ST on 2017/11/2 22:54
// */
//@SpringBootApplication
//@RunWith(SpringJUnit4ClassRunner.class)
//@ComponentScan("com.rograndec.feijiayun.chain")
//@MapperScan("com.rograndec.feijiayun.chain.**.dao")
//@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class RGTServiceTest {
//
//    @Autowired
//    RGTService rgtService;
//
//
//    @Test
//    public void registerUserTest(){
////        RGTUserVO rgtUserVO = new RGTUserVO();
////        rgtUserVO.setMobile("15666655");
////        rgtUserVO.setEmail("1112222211@qq.com");
////        rgtUserVO.setUsername("zhangsanst");
////        rgtUserVO.setPassword("111111");
////        rgtUserVO.setUserType(1);
////        rgtUserVO.seteId(0);
////        rgtUserVO.setStaffType(0);
//
//        Map<String,Object> map = new HashMap<>();
//        map.put("username","dd");
//        map.put("mobile","12111");
//        map.put("email","2233322@qq.com");
//        map.put("password",333333);
//        map.put("eId",0);
//        map.put("userType",1);
//        map.put("staffType",0);
//
//        Integer id = rgtService.registerRGTUser(map,2l);
//        System.out.println(id);
//
//    }
//
//    @Test
//    public void updateUserTest(){
////        RGTUserVO rgtUserVO = new RGTUserVO();
//////        rgtUserVO.setMobile("156666");
//////        rgtUserVO.setEmail("1112@qq.com");
////        rgtUserVO.setUsername("123sunteng");
////        rgtUserVO.setUserType(1);
////        rgtUserVO.seteId(0);
////        rgtUserVO.setUid(9403580);
//
//        Map<String,Object> map = new HashMap<>();
//        map.put("username","dd");
////        map.put("mobile","12111");
////        map.put("email","2233322@qq.com");
//        map.put("password",333333);
//        map.put("eId",0);
//        map.put("userType",1);
//        map.put("staffType",0);
//        String id = rgtService.updateRGTUser(map);
//        System.out.println(id);
//
//    }
//
//    /**
//     * 修改密码
//     */
//    @Test
//    public void updateUserPwdTest(){
//        Map<String,Object> map = new HashMap<>();
//        map.put("uid","9403581");
//        map.put("password","222222");
//        map.put("oldPassword","111111");
//        map.put("flag",3);
//        String id = rgtService.updateUserPwd(map);
//        System.out.println(id);
//
//    }
//
//    /**
//     * 查询用户
//     */
//    @Test
//    public void getUserTest(){
//         Map<String,String> map = new HashMap<>();
//         map.put("username","123sunteng");
//        JSONObject rgtUser = rgtService.getRGTUser(map);
//        System.out.println(rgtUser);
//
//    }
//
//    /**
//     * 查询用户
//     */
//    @Test
//    public void getEnterpriseTest(){
//        Map<String,String> map = new HashMap<>();
//        map.put("eId","6005881");
//        JSONObject rgtUser = rgtService.getRGTEnterprise(map);
//        System.out.println(rgtUser);
//
//    }
//    /**
//     * 登录
//     */
//    @Test
//    public void loginTest(){
//        Map<String,String> map = new HashMap<>();
//        map.put("username","zhangsanst");
//        map.put("password","222222");
//
//        JSONObject rgtUser = rgtService.loginUser(map);
//        System.out.println(rgtUser);
//
//    }
//    @Test
//    public void addEnterpriseTest(){
////        RGTEnterpriseVO rgtEnterpriseVO = new RGTEnterpriseVO();
////        rgtEnterpriseVO.seteTel("22222");
////        rgtEnterpriseVO.seteParentId(0);
////        rgtEnterpriseVO.seteMobile("1212121");
////        rgtEnterpriseVO.seteName("东方梅地亚中心");
////        rgtEnterpriseVO.seteType(2);
////        rgtEnterpriseVO.setUid(9403580);
//
//
//        Map<String,Object> map = new HashMap<>();
//        map.put("eTel","222222");
//        map.put("eParentId","0");
//        map.put("eMobile","1212121");
//        map.put("eName","东方梅地亚中心");
//        map.put("eType","2");
//        map.put("uid","9403580");
//        map.put("eProvince","110000");
//        map.put("eCity","110100");
//        map.put("eRegion","110102");
//        map.put("eAddress","北京市");
//        map.put("isCheckEnterpriseELicenseNo",1);//校验重复
//        map.put("eLicenseNo",1234569000);
//
//
//
//        List<Map<String,Object>> list = new ArrayList<>();
//        Map<String,Object> qualificationMap = new HashMap<>();
//        qualificationMap.put("epType","1");
//        qualificationMap.put("epNo","1");
//        qualificationMap.put("epPic","1");
//
//        qualificationMap.put("epStartTime",new Date());
//        qualificationMap.put("epEndTime",new Date());
//        list.add(qualificationMap);
//
//
//        Map<String,Object> qualificationMap1 = new HashMap<>();
//        qualificationMap1.put("epType","2");
//        qualificationMap1.put("epNo","2");
//        qualificationMap1.put("epPic","2");
//
//        qualificationMap1.put("epStartTime",new Date());
//        qualificationMap1.put("epEndTime",new Date());
//        list.add(qualificationMap1);
//
//        map.put("epList",list);
//
//        rgtService.addEnterprise(map,2L);
//    }
//
//    @Test
//    public void test(){
////        String json = "{\"head\":{\"sysVersion\":\"6.0.1\",\"appVersion\":\"5.2.6\",\"appType\":\"2\",\"method\":\"142005\",\"imei\":\"861414034058884\",\"terminalstate\":\"0\",\"serialNumber\":\"120319181305000011\",\"appSys\":\"2\",\"macAddress\":\"02:00:00:00:00:00\",\"version\":\"1\",\"appId\":8,\"appSecret\":\"175906d1a85aeff2eeb5ad7732f6ba98\"},\"mac\":\"ac7f3560\",\"body\":{\"appid\":\"1803592\",\"channelid\":\"3612398030020462276\",\"password\":\"888888\",\"sourceCode\":\"wdyy-app\",\"username\":\"zxw_bj\",\"versionFlag\":\"1\",\"userid\":\"603430474901625968\"}}";
////        String url = "http://api.test.rograndec.net/userInfos/login.html?dataJson={dataJson}";
////        JSONObject post = restTemplateComponent.get(url, json);
//////        String result = HttpClientUtil.sendPostRequest(url,json, true);
////        System.out.println(post);
//
//        String json = "dataJson={\"body\":{\"eProvince\":\"110000\",\"eAddress\":\"fuhhv\",\"uid\":\"9024749\",\"eStatus\":\"2\",\"eName\":\"fhhhhjj\",\"eType\":7,\"eParentId\":0,\"eRegion\":\"110101\",\"password\":\"qeewwwee\",\"eContactor\":\"thick\",\"eMobile\":\"13655875558\",\"eCity\":\"110100\",\"eLicenseNo\":\"ryuuiiioo\",\"epList\":[{\"epPic\":\"2017\\/10\\/17\\/mph_71c2f8fe4c274800a630759a3d0d5bc5_watermark.jpg\",\"epType\":13}],\"username\":\"yuyyyyhh\"},\"head\":{\"method\":\"112003\",\"serialNumber\":\"12032114314473700001\",\"version\":\"1\",\"sysVersion\":\"iPhoneOS9.2\",\"appVersion\":\"5.3.13\",\"imei\":\"74FD10EE-5427-455A-8D07-6C57A88674DF\",\"terminalstate\":12,\"appType\":2,\"appSys\":1,\"appId\":8,\"appSecret\":\"175906d1a85aeff2eeb5ad7732f6ba98\",\"sourceCode\":\"wdyy-app\"},\"mac\":\"D89AF733\"}";
//        String url = "https://api-test-rgec.rograndec.com/biz/v4.2.6/addEnterpriseAndPic.html?";
//        JSONObject post = HttpClientUtil.sendRGTHttpsPostRequest(url,json, true);
//        System.out.println(post+"---------------------");
//    }
//}