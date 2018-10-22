package com.rograndec.feijiayun.chain.business.auth.register.controller;

import com.alibaba.fastjson.JSONObject;
import com.rograndec.feijiayun.chain.business.auth.constant.VerificationCode;
import com.rograndec.feijiayun.chain.business.auth.register.exception.RegisterBizException;
import com.rograndec.feijiayun.chain.business.auth.register.service.RegisterService;
import com.rograndec.feijiayun.chain.business.auth.register.valid.RegisterUserCheck;
import com.rograndec.feijiayun.chain.business.auth.register.vo.CheckRegisterVO;
import com.rograndec.feijiayun.chain.business.auth.register.vo.RegisterEnterpriseNotBindVO;
import com.rograndec.feijiayun.chain.business.auth.register.vo.RegisterEnterpriseVO;
import com.rograndec.feijiayun.chain.business.auth.register.vo.RegisterUserVO;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.EnterpriseComponent;
import com.rograndec.feijiayun.chain.common.vo.VerificationCodeVO;
import com.rograndec.feijiayun.chain.inf.rogrand.service.RGTService;
import com.rograndec.feijiayun.chain.utils.MD5.MD5Utils;
import com.rograndec.feijiayun.chain.utils.verificationCode.VerificationCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *翟伟
 */
@Api(value = "register",description = "注册管理")
@RestController
@RequestMapping("/register")
@Validated
public class RegisterContoller {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private RGTService rgtService;

    @Autowired
    private EnterpriseComponent enterpriseComponent;


/*    @ApiOperation(value="注册账户 | 开发者 翟伟 | 已完成", notes = "注册账户 | 开发者 翟伟 | 已完成"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    @ResponseBody
    public Result register(
            HttpServletRequest request,
            @ApiParam(name="registerVO", value="注册信息" , required=true)
            @RequestBody
            @Valid @RegisterCheckValid RegisterVO registerVO) throws Exception {
        Result result = new Result<>();

        registerService.register(registerVO);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);

        return result;
    }*/


   /* @ApiOperation(value="注册 | 开发者 翟伟 | 已完成", notes = "注册 | 开发者 翟伟 | 已完成"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    @ResponseBody
    public Result register(
            HttpServletRequest request,
            @ApiParam(name="registerVO", value="注册信息" , required=true)
            @RequestBody
            @Valid @RegisterCheckValid RegisterVO registerVO) throws Exception {
        Result result = new Result<>();

        registerService.register(registerVO);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);

        return result;
    }*/


    @ApiOperation(value="获取注册二维码 | 开发者 翟伟 | 已完成", notes = "注册二维码 | 开发者 翟伟 | 已完成")
    @RequestMapping(value = "/verificationCode",method = RequestMethod.GET)
    public Result drawVerificationCode(
            HttpServletResponse response,
            HttpSession session) throws Exception{
        Result result = new Result<>();
        VerificationCodeVO verificationCodeVO = VerificationCodeUtils.drawVerificationCode();

        System.out.println("============================================");
        System.out.println(verificationCodeVO.getNumber());
        System.out.println("============================================");
        //将图片字符存入session,用于验证码检查使用
        session.setAttribute(VerificationCode.SCODE.getCode(), verificationCodeVO.getNumber());

        response.setContentType("image/jpeg");
        OutputStream ops = response.getOutputStream();
        ImageIO.write(verificationCodeVO.getImage(), "jpeg", ops);
        ops.close();

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);

        return result;
    }

    @ApiOperation(value="用户账号校验重复性 | 开发者 翟伟 | 已完成", notes = "用户账号校验重复性 | 开发者 翟伟 | 已完成")
    @RequestMapping(value = "/validate",method = RequestMethod.GET)
    public Result validateUserName(
            @ApiParam(name="type", value="修改类型 1 : 用户账号，2 : 手机号码，3 : 邮箱" , required=true)
            @RequestParam(value = "type")
                    String type,
            @ApiParam(name="value", value="需要校验的值" , required=true)
            @RequestParam(value = "value")
                    String value) throws Exception{

        Result result = new Result<>();
        Map<String,Object> userMap = new HashMap<>();

        boolean isSuccess = false;

        if(Integer.parseInt(type) == 1){
            userMap.put("username",value);
            isSuccess = rgtService.validateRGTUserName(userMap);
        }else if(Integer.parseInt(type) == 2){
            userMap.put("mobile",value);
            isSuccess = rgtService.validateRGTMobile(userMap);
        }else if(Integer.parseInt(type) == 3){
            userMap.put("email",value);
            isSuccess = rgtService.validateRGTEmail(userMap);
        }

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);

        return result;
    }


    @ApiOperation(value="注册用户 | 开发者 翟伟 | 已完成", notes = "注册用户 | 开发者 翟伟 | 已完成")
    @RequestMapping(value = "/registerUser",method = RequestMethod.POST)
    public Result<User> registerUser(
            @RequestHeader
            @ApiParam(name="token", value="md5校验码, RegisterUserVO json串md5后进行base64", required=true)
                    String token,
            @ApiParam(name="registerVO", value="注册信息" , required=true)
            @RequestBody
            @Valid @RegisterUserCheck RegisterUserVO registerVO) throws Exception{

        JSONObject registerVOJSONObject = (JSONObject) JSONObject.toJSON(registerVO);
        boolean b = MD5Utils.checkMd5Base64(registerVOJSONObject, token);
        if(!b){
            throw new RegisterBizException(SysCode.FORBIDDEN.getCode(),"校验参数不正确");
        }

        Result<User> result = new Result<>();

        User user = registerService.registerUser(registerVO,false);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,user);

        return result;
    }


    @ApiOperation(value="校验并返回用户 | 开发者 翟伟 | 已完成", notes = "校验并返回用户 | 开发者 翟伟 | 已完成")
    @RequestMapping(value = "/checkUser",method = RequestMethod.GET)
    public Result<CheckRegisterVO> checkUser(
            @RequestParam(name = "loginAccount") String loginAccount) throws Exception{

        Result<CheckRegisterVO> result = new Result<>();

        CheckRegisterVO checkRegisterVO = registerService.checkUser(loginAccount);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,checkRegisterVO);

        return result;
    }



    @ApiOperation(value="注册企业 | 开发者 翟伟 | 已完成", notes = "注册企业 | 开发者 翟伟 | 已完成")
    @RequestMapping(value = "/registerEnterprise",method = RequestMethod.POST)
    public Result<Enterprise> registerEnterprise(
            @RequestHeader
            @ApiParam(name="token", value="md5校验码, RegisterEnterpriseVO json串md5后进行base64", required=true)
                    String token,
            @ApiParam(name="registerVO", value="注册信息" , required=true)
            @RequestBody
            @Valid RegisterEnterpriseVO registerVO) throws Exception{

       /* JSONObject registerVOJSONObject = (JSONObject) JSONObject.toJSON(registerVO, new ParserConfig());
        System.out.println(registerVOJSONObject.toJSONString());*/
        boolean b = MD5Utils.checkMd5Base64(registerVO, token);
        if(!b){
            throw new RegisterBizException(SysCode.FORBIDDEN.getCode(),"校验参数不正确");
        }

        Result<Enterprise> result = new Result<>();

        Enterprise enterprise = registerService.registerEnterprise(registerVO,false);

        if(null == enterprise){
            result.setBizCodeSuccessInfo(SysCode.RGT_BIND_ERROR);
        }else {
            enterprise.setPassWord(null);
            enterprise.setPassWordConfirmation(null);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,enterprise);
        }


        return result;
    }

    @ApiOperation(value="同步融贯通企业信息 | 开发者 翟伟 | 已完成", notes = "同步融贯通企业信息 | 开发者 翟伟 | 已完成")
    @RequestMapping(value = "/synchronizeRGTEnterprise",method = RequestMethod.GET)
    public Result<Enterprise> synchronizeRGTEnterprise(
            @RequestHeader
            @ApiParam(name="token", value="md5校验码, RegisterEnterpriseVO json串md5后进行base64", required=true)
                    String token,
            @ApiParam(name="userId", value="saas系统用户id" , required=true)
            @RequestParam(value = "userId")
                    Long userId) throws Exception{
        Result<Enterprise> result = new Result<>();

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("userId",userId);
        JSONObject registerVOJSONObject = (JSONObject) JSONObject.toJSON(paramMap);
        boolean b = MD5Utils.checkMd5Base64(registerVOJSONObject, token);
        if(!b){
            throw new RegisterBizException(SysCode.FORBIDDEN.getCode(),"校验参数不正确");
        }

        Enterprise enterprise = registerService.synchronizeRGTEnterprise(userId);
        enterprise.setPassWord(null);
        enterprise.setPassWordConfirmation(null);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,enterprise);

        return result;
    }

    @ApiOperation(value="查询融贯通系统和saas未同步的数据 | 开发者 翟伟 | 已完成", notes = "查询融贯通系统和saas未同步的数据 | 开发者 翟伟 | 已完成")
    @RequestMapping(value = "/notBindEnterprises",method = RequestMethod.GET)
    public Result<Page<List<RegisterEnterpriseNotBindVO>>> notBindEnterprises(
            @RequestParam(required = false)
            @ApiParam(name = "pageNo", value = "当前页码", required = false)
                    Integer pageNo,
            @RequestParam(required = false)
            @ApiParam(name = "pageSize", value = "显示条数", required = false)
                    Integer pageSize) throws Exception{
        Result<Page<List<RegisterEnterpriseNotBindVO>>> result = new Result<>();

        Page page = new Page(pageNo, pageSize);
        List<RegisterEnterpriseNotBindVO> notBindEnterprise = registerService.getNotBindEnterprise(page);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);

        return result;
    }


    @ApiOperation(value="查询所有总部,包含未启用 | 开发者 翟伟 | 已完成", notes = "查询所有总部,包含未启用 | 开发者 翟伟 | 已完成")
    @RequestMapping(value = "/allParent",method = RequestMethod.GET)
    public Result<List<Enterprise>> getAllParent(
            @RequestParam(required = false)
            @ApiParam(name = "eName", value = "企业名称,支持模糊查询 " +
                    "", required = false)
                    String eName
    ) throws Exception{
        Result<List<Enterprise>> result = new Result<>();
        List<Enterprise> allParent = enterpriseComponent.getAllParent(eName);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,allParent);
        return result;
    }

}
