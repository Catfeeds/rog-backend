package com.rograndec.feijiayun.chain.business.retail.prescription.controller;

import com.rograndec.feijiayun.chain.business.basic.user.entity.SimpleUserVO;
import com.rograndec.feijiayun.chain.business.basic.user.vo.UserForPrescVO;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.PharmacySetForPrescVO;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsTaxRateVO;
import com.rograndec.feijiayun.chain.business.member.info.vo.MemberForPrescVO;
import com.rograndec.feijiayun.chain.business.retail.prescription.entity.PrescriptionRegister;
import com.rograndec.feijiayun.chain.business.retail.prescription.service.PrescriptionRegisterService;
import com.rograndec.feijiayun.chain.business.retail.prescription.valid.AddUpdateRegisterValid;
import com.rograndec.feijiayun.chain.business.retail.prescription.valid.AddUpdateSignatureValid;
import com.rograndec.feijiayun.chain.business.retail.prescription.vo.*;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SelectPricingGoodsVO;
import com.rograndec.feijiayun.chain.business.retail.saleflow.vo.SaleForPrescrVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.GoodsComponent;
import com.rograndec.feijiayun.chain.common.component.ManageConfigComponent;
import com.rograndec.feijiayun.chain.common.component.UserComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.PrescriptionOperateType;
import com.rograndec.feijiayun.chain.common.constant.PrescriptionType;
import com.rograndec.feijiayun.chain.common.constant.status.PrescriptionStatus;
import com.rograndec.feijiayun.chain.common.vo.RequestBaseParamVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 * Created by ST on 2017/9/19 15:38
 */
@Api(value = "retail_prescription",description = "处方管理")
@RequestMapping("retail/prescription")
@RestController
@Validated
public class PrescriptionController {
    private static final Logger logger = LoggerFactory.getLogger(PrescriptionController.class);

    @Autowired
    private PrescriptionRegisterService prescriptionRegisterService;


    @Autowired
    private ManageConfigComponent manageConfigComponent;

    @Autowired
    private GoodsComponent goodsComponent;

    @Autowired
    private UserComponent userComponent;



    @ApiOperation(value = "处方登记单查询", notes = "处方单查询 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getPrescriptionList",method= RequestMethod.POST)
    @ApiImplicitParam(name = "paramForListVO", value = "检索条件", required = true, dataType = "RequestParamForListVO")
    public Result<Page<List<ResponsePrescriptionRegisterForListVO>>> getPrescriptionList(HttpSession session, @RequestBody RequestParamForListVO paramForListVO){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<Page<List<ResponsePrescriptionRegisterForListVO>>> result = new Result<>();
        Integer pageNo = paramForListVO.getPageNo();
        Integer pageSize = paramForListVO.getPageSize();
        if(pageNo == null || pageSize == null || pageNo <= 0 || pageSize <= 0){
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        Page<List<ResponsePrescriptionRegisterForListVO>> page = new Page<>(paramForListVO.getPageNo(),paramForListVO.getPageSize());
        paramForListVO.setStart(page.getStart());
        prescriptionRegisterService.getPrescriptionList(page,paramForListVO,userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }

    @ApiOperation(value = "查询处方登记单详情", notes = "查询处方详情 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getPrescriptionDetail/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "处方登记单id", required = true, paramType = "path")
    public Result<ResponsePrescriptionRegisterForDetailVO> getPrescriptionDetail(HttpSession session, @PathVariable("id")Long id){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<ResponsePrescriptionRegisterForDetailVO> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,prescriptionRegisterService.getDetailById(id,null));
        return result;
    }



    @ApiOperation(value = "处方单登记新增", notes = "处方单登记新增 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/addPrescription",method= RequestMethod.POST)
    @ApiImplicitParam(name = "registerForAddUpdateVO", value = "新增的实体", required = true, dataType = "RequestPrescriptionRegisterForAddUpdateVO")
    public Result addPrescription(HttpSession session,@RequestBody @AddUpdateRegisterValid  RequestPrescriptionRegisterForAddUpdateVO registerForAddUpdateVO) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result result = new Result();
        try {
            prescriptionRegisterService.addPrescription(registerForAddUpdateVO,userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("处方单登记新增错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("处方单登记新增错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "处方单修改", notes = "处方单修改 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/updatePrescription",method= RequestMethod.POST)
    @ApiImplicitParam(name = "registerForAddUpdateVO", value = "修改的实体", required = true, dataType = "RequestPrescriptionRegisterForAddUpdateVO")
    public Result updatePrescription(HttpSession session,@RequestBody RequestPrescriptionRegisterForAddUpdateVO registerForAddUpdateVO) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result result = new Result();
        prescriptionRegisterService.updatePrescription(registerForAddUpdateVO,userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value = "处方登记商品查询", notes = "获取数据 | 开发者 孙腾 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @RequestMapping(value = "/getSalePricingGoodsByParam", method = RequestMethod.GET)
    public Result<List<SelectPricingGoodsVO>> getSalePricingGoodsByParam(HttpSession session,
      @ApiParam(name = "param", value = "检索条件:商品编码/条形码/检索码/名称/通用名称/批准文号", required = false) @RequestParam(required = false)String param
    ,@ApiParam(name = "prescriptionType", value = "处方类型(中药=0;其他=1)", required = true) @RequestParam(required = true)Integer prescriptionType) throws Exception {
        Result<List<SelectPricingGoodsVO>> result = new Result<>();
        // 当前登录用户数据
        UserVO userVO = (UserVO) session.getAttribute("user");
        List<SelectPricingGoodsVO> page = prescriptionRegisterService.selectGoods(userVO, param, prescriptionType);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);

        return result;
    }

    @ApiOperation(value = "病人查询", notes = "病人查询 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getPatientList/{param}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "param", value = "检索条件", required = true, paramType = "path")
    public Result<List<MemberForPrescVO>> getPatientList(HttpSession session, @PathVariable("param")String param){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<List<MemberForPrescVO>> result = new Result<>();
        List<MemberForPrescVO> memberInfoForPresc = prescriptionRegisterService.getMemberInfoForPresc(userVO.getEnterpriseId(), param);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,memberInfoForPresc);
        return result;
    }

    @ApiOperation(value = "查询药品用法、用量、单次剂量、注意事项", notes = "药品用法 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getPharmacySetInfo/{setType}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "setType", value = "设置类型（0-用法；1-用量；2-单次计量；3-注意事项）", required = true, paramType = "path")
    public Result<List<PharmacySetForPrescVO>> getPharmacySetInfo(HttpSession session,@PathVariable("setType")Integer setType){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<List<PharmacySetForPrescVO>> result = new Result<>();
        List<PharmacySetForPrescVO> pharmacySetInfo = prescriptionRegisterService.getPharmacySetInfo(userVO.getChainType() == ChainType.Headquarters.getCode() ? userVO.getEnterpriseId():userVO.getParentId(), setType);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,pharmacySetInfo);
        return result;
    }


    @ApiOperation(value = "查询登记人员", notes = "查询登记人员 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getRegister",method= RequestMethod.GET)
    public Result<List<SimpleUserVO>> getRegister(HttpSession session){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<List<SimpleUserVO>> result = new Result<>();
        List<SimpleUserVO> simpleUserVOList = userComponent.getSimpleUserVOByEnterpriseId(userVO.getEnterpriseId(), EnableStatus.ENABLE.getStatus());
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,simpleUserVOList);
        return result;
    }


    @ApiOperation(value = "导出处方信息", notes = "导出处方信息 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/exportPrescriptionRecord/{id}",method= RequestMethod.GET)
    public Result exportPrescriptionRecord(HttpSession session, HttpServletResponse response,@PathVariable Long id) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result result = new Result<>();
        PrescriptionRegister prescriptionRegister = prescriptionRegisterService.selectByPrimaryKey(id);
        if(prescriptionRegister == null){
            result.setBizCodeSuccessInfo(SysCode.FAIL,"导出失败，商品不存在");
            return result;
        }
        Integer status = prescriptionRegister.getStatus();
        Integer prescriptionType = prescriptionRegister.getPrescriptionType();

        String name = "";
        if(status == PrescriptionStatus.PENDING_AUDIT){
            if(prescriptionType == PrescriptionType.TCM.getCode()){
                name = "中药处方待审核.xlsx";
            } else {
                name = "常规处方待审核.xlsx";
            }
        } else if(status == PrescriptionStatus.WAIT_MIX || status == PrescriptionStatus.CANCELED) {
            if(prescriptionType == PrescriptionType.TCM.getCode()){
                name = "中药处方待调配.xlsx";
            } else {
                name = "常规处方待调配.xlsx";
            }
        } else if(status == PrescriptionStatus.MIXED || status == PrescriptionStatus.FINISHED){
            if(prescriptionType == PrescriptionType.TCM.getCode()){
                name = "中药处方已调配.xlsx";
            } else {
                name = "常规处方已调配.xlsx";
            }
        } else {
            result.setBizCodeSuccessInfo(SysCode.FAIL,"导出失败，商品不存在");
            return result;
        }
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        //输出Excel文件
        OutputStream output = response.getOutputStream();
        prescriptionRegisterService.exportPrescriptionRecord(output,userVO.getEnterpriseId(),id);
        output.close();
        output.flush();
        return result;
    }

    @ApiOperation(value = "处方补登中从已售处方药品中查询记录", notes = "处方补登中从已售处方药品中查询记录 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getDrugFromSold",method= RequestMethod.POST)
    public Result<Page<List<SaleForPrescrVO>>> getDrugFromSold(HttpSession session, @RequestBody RequestBaseParamVO baseParamVO){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<Page<List<SaleForPrescrVO>>> result = new Result();
        Page<List<SaleForPrescrVO>> page = new Page<>(baseParamVO.getPageNo(),baseParamVO.getPageSize());
        Integer pageNo = baseParamVO.getPageNo();
        Integer pageSize = baseParamVO.getPageSize();
        if(pageNo <= 0 || pageSize <= 0){
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        prescriptionRegisterService.getSaleForPrescrList(page,baseParamVO.getStartDate(),baseParamVO.getEndDate(),userVO.getEnterpriseId());
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }

    @ApiOperation(value = "处方补登中根据已售处方单id查询订单详情", notes = "处方补登中根据已售处方单id查询订单详情 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getSaleDetailForPrescrBySaleId/{saleId}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "saleId", value = "销售订单id", required = true, paramType = "path")
    public Result<List<ResponsePrescriptionRegisterDetailForDetailVO>> getSaleDetailForPrescrBySaleId(HttpSession session, @PathVariable Long saleId){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<List<ResponsePrescriptionRegisterDetailForDetailVO>> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,prescriptionRegisterService.getSaleDetailForPrescrBySaleId(userVO.getEnterpriseId(),saleId));
        return result;
    }

    @ApiOperation(value = "查询调配、核对、发药人员", notes = "查询调配、核对、发药人员 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getSwapCheckerSendInfo",method= RequestMethod.GET)
    public Result<ResponseSwapCheckerSendVO> getSwapCheckerSendInfo(HttpSession session){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<ResponseSwapCheckerSendVO> result = new Result<>();
        ResponseSwapCheckerSendVO swapCheckerSendInfo = prescriptionRegisterService.getSwapCheckerSendInfo(userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,swapCheckerSendInfo);
        return result;
    }


    @ApiOperation(value = "处方登记审核", notes = "处方登记审核 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/updateAuditorDrug",method= RequestMethod.POST)
    @ApiImplicitParam(name = "operateParamVO", value = "处方登记操作传递的参数", required = true, dataType = "RequestOperateParamVO")
    public Result updateAuditorDrug(HttpSession session,@RequestBody RequestOperateParamVO operateParamVO){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result result = new Result();
        int val = prescriptionRegisterService.getCountSignatureByUserId(userVO.getUserId(), userVO.getEnterpriseId(), PrescriptionOperateType.AUDIT.getCode());
        if(val == 0){
            result.setBizCodeFallInfo(SysCode.FAIL,"该用户没有审批权限");
            return result;
        }
        prescriptionRegisterService.updateAuditorDrug(userVO,operateParamVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }


    @ApiOperation(value = "处方登记调剂", notes = "处方登记调剂 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/updateSwapDrug",method= RequestMethod.POST)
    @ApiImplicitParam(name = "operateParamVO", value = "处方登记操作传递的参数", required = true, dataType = "RequestOperateParamVO")
    public Result updateSwapDrug(HttpSession session,@RequestBody RequestOperateParamVO operateParamVO){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result result = new Result();
        int val = prescriptionRegisterService.getCountSignatureByUserId(operateParamVO.getSwapManId(), userVO.getEnterpriseId(),PrescriptionOperateType.SWAP.getCode());
        if(val == 0){
            result.setBizCodeFallInfo(SysCode.FAIL,"该用户没有调剂权限");
            return result;
        }
        int val1 = prescriptionRegisterService.getCountSignatureByUserId(operateParamVO.getCheckerId(), userVO.getEnterpriseId(),PrescriptionOperateType.CHECKER.getCode());
        if(val1 == 0){
            result.setBizCodeFallInfo(SysCode.FAIL,"该用户没有核对权限");
            return result;
        }
        if(operateParamVO.getId() == null){
            result.setBizCodeFallInfo(SysCode.FAIL,"参数错误！");
            return result;
        }
        prescriptionRegisterService.updateSwapDrug(userVO,operateParamVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value = "处方登记取消", notes = "处方登记取消 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/updateForCancel/{id}",method= RequestMethod.POST)
    @ApiImplicitParam(name = "id", value = "处方登记单id", required = true, paramType = "path")
    public Result updateForCancel(HttpSession session,@PathVariable Long id){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result result = new Result();
        prescriptionRegisterService.updateForCancel(userVO,id);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }


    //签章查询
    //签章增加
    //签章查询
    //签章修改
    //签章删除
    //签章增加用户的校验

    @ApiOperation(value = "签章查询", notes = "签章查询 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getPrescriptionSignatureList/{pageNO}/{pageSize}",method= RequestMethod.GET)
    @ApiImplicitParams ({@ApiImplicitParam(name = "pageNO", value = "页码", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数据", required = true, paramType = "path")})
    public Result<Page<List<ResponsePrescriptionSignatureForListVO>>> getPrescriptionSignatureList(HttpSession session,@PathVariable Integer pageNO,@PathVariable Integer pageSize){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<Page<List<ResponsePrescriptionSignatureForListVO>>> result = new Result<>();
        Page<List<ResponsePrescriptionSignatureForListVO>> page = new Page<List<ResponsePrescriptionSignatureForListVO>>(pageNO,pageSize);
        prescriptionRegisterService.getSignatureList(userVO,page);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }

    @ApiOperation(value = "签章管理增加时，用户查询", notes = "签章管理增加时，用户查询 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getUserInfoForSignature/{prescriptionType}",method= RequestMethod.GET)
    @ApiImplicitParams ({@ApiImplicitParam(name = "prescriptionType", value = "处方操作类型(0-审核；1-调配；2-核对)", required = true, paramType = "path"),
            @ApiImplicitParam(name = "param", value = "参数", required = true, paramType = "path")})
    public Result<List<UserForPrescVO>> getUserInfo(HttpSession session, @PathVariable("prescriptionType")Integer prescriptionType){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<List<UserForPrescVO>> result = new Result<>();
        // param 直接赋值 null
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,prescriptionRegisterService.getUserInfo(null,userVO.getEnterpriseId(),prescriptionType));
        return result;
    }

    @ApiOperation(value = "签章增加", notes = "签章增加 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/addPrescriptionSignature",method= RequestMethod.POST)
    @ApiImplicitParam(name = "signatureForAddVO", value = "签章", required = true, dataType = "ResponsePrescriptionSignatureForAddVO")
    public Result addPrescriptionSignature(HttpSession session,@AddUpdateSignatureValid @RequestBody  ResponsePrescriptionSignatureForAddVO signatureForAddVO) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result result = new Result ();
        prescriptionRegisterService.addPrescriptionSignature(signatureForAddVO,userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value = "签章修改", notes = "签章修改 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/updatePrescriptionSignature",method= RequestMethod.POST)
    @ApiImplicitParam(name = "signatureForAddVO", value = "签章", required = true, dataType = "ResponsePrescriptionSignatureForAddVO")
    public Result updatePrescriptionSignature(HttpSession session,@RequestBody   ResponsePrescriptionSignatureForAddVO signatureForAddVO) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result result = new Result();
        int val = prescriptionRegisterService.getCountSignatureByUserId(signatureForAddVO.getUserId(), userVO.getEnterpriseId(),signatureForAddVO.getPrescriptionType());
        if(val > 1){
            result.setBizCodeFallInfo(SysCode.FAIL,"该用户已经绑定在该处方操作类型上，不能再绑定！");
            return result;
        }
        prescriptionRegisterService.updatePrescriptionSignature(signatureForAddVO,userVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value = "签章删除", notes = "签章删除 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/deletePrescriptionSignature/{id}",method= RequestMethod.POST)
    @ApiImplicitParam(name = "id", value = "签章id", required = true, paramType = "path")
    public Result deletePrescriptionSignature(HttpSession session,@PathVariable Long id) throws Exception {
        Result result = new Result<>();
        int val = prescriptionRegisterService.deletePrescriptionSignature(id);
        if(val == 1){
            result.setBizCodeFallInfo(SysCode.FAIL,"已关联数据，该记录不能删除");
            return result;
        }
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value = "增加签章用户校验", notes = "签章用户校验 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/checkAddSignatureUser/{prescriptionType}/{userId}",method= RequestMethod.POST)
    @ApiImplicitParams ({@ApiImplicitParam(name = "prescriptionType", value = "处方操作类型(0-审核；1-调配；2-核对)", required = true, paramType = "path"),
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "path")})
    public Result checkSignatureUser(HttpSession session,@PathVariable Integer prescriptionType,@PathVariable("userId")Long userId) throws Exception {
        Result<Integer> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        int val = prescriptionRegisterService.getCountSignatureByUserId(userId, userVO.getEnterpriseId(),prescriptionType);
        if(val > 0){
            result.setBizCodeFallInfo(SysCode.FAIL,"该用户已经绑定在该处方操作类型上，不能再绑定！");
            return result;
        }
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value = "修改签章用户校验", notes = "修改签章用户校验 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/checkUpdateSignatureUser/{prescriptionType}/{userId}",method= RequestMethod.POST)
    @ApiImplicitParams ({@ApiImplicitParam(name = "prescriptionType", value = "处方操作类型(0-审核；1-调配；2-核对)", required = true, paramType = "path"),
            @ApiImplicitParam(name = "userId", value = "用户id", required = true, paramType = "path")})
    public Result checkUpdateSignatureUser(HttpSession session,@PathVariable Integer prescriptionType,@PathVariable("userId")Long userId) throws Exception {
        Result<Integer> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        int val = prescriptionRegisterService.getCountSignatureByUserId(userId, userVO.getEnterpriseId(),prescriptionType);
        if(val > 1){
            result.setBizCodeFallInfo(SysCode.FAIL,"该用户已经绑定在该处方操作类型上，不能再绑定！");
            return result;
        }
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }

    @ApiOperation(value = "查看当前企业一些配置控制信息", notes = "查看当前企业一些配置控制信息 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/checkCode",method= RequestMethod.GET)
    public Result<ManageConfig> checkCode(HttpSession session){
        Result<ManageConfig> result = new Result<ManageConfig>();
        try{
            UserVO loginUser = (UserVO) session.getAttribute("user");
            ManageConfig manageConfig = manageConfigComponent.getMangeConfigByEPId(loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,manageConfig);
        }catch(Exception e){
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查询税率", notes = "查询税率 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getTax",method= RequestMethod.GET)
    public Result<List<GoodsTaxRateVO>> getTax(HttpSession session){
        Result<List<GoodsTaxRateVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsComponent.getGoodsTaxRateVO(userVO.getEnterpriseId(), EnableStatus.ENABLE.getStatus()));
        return result;
    }

    @ApiOperation(value = "查询当前审核用户的签章类型", notes = "查询当前审核用户的签章类型 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getAuditUser",method= RequestMethod.GET)
    public Result<Map> getAuditUser(@ApiIgnore UserVO userVO){
        Result<Map> result = new Result<>();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,prescriptionRegisterService.getAuditUser(userVO));
        return result;
    }

}