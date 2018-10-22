package com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.controller;

import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.service.PaymentService;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.vo.*;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 *
 * @ClassName: AccountsDtlController
 * @Description: 财务管理-收付款-付款
 * @author lei.su
 * @version 1.0
 * @date 2018年1月3日 下午11:32
 */
@Api(value = "PayMoneyController",description = "财务管理-收付款-付款")
@RestController
@RequestMapping("finance/payMoney")
public class PaymentController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PaymentService paymentService;

    @ApiOperation(value = "付款分页列表", notes = "获取数据 | 开发者 苏磊 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PostMapping(value = "/getPayPage")
    @ApiImplicitParam(name = "requestPayPageVO", value = "检索条件", required = true, dataType = "RequestPayPageVO")
    public Result<Page<PayTotalPageVO>> getReceivePage(HttpSession session, @RequestBody RequestPayPageVO requestPayPageVO){
        Result<Page<PayTotalPageVO>> result = new Result<>();
        try{
            if(requestPayPageVO.getPageNo() <= 0 || requestPayPageVO.getPageSize() <= 0){
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(requestPayPageVO.getPageNo(), requestPayPageVO.getPageSize());
            UserVO loginUser = (UserVO) session.getAttribute("user");
            page = paymentService.getPayPage(page,requestPayPageVO,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        }catch(BusinessException e){
            logger.error("付款分页列表查询错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("付款分页列表查询错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "付款查看详细", notes = "付款查看详细 | 开发者 苏磊 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @GetMapping(value = "/getPayDetail")
    public Result<PayFormVO> getPayDetail(HttpSession session, @ApiParam(value = "当前需要查看的ID", required = true) @RequestParam Long id){
        Result<PayFormVO> result = new Result<>();
        try{
            UserVO loginUser = (UserVO) session.getAttribute("user");
            PayFormVO payFormVO = paymentService.getPayDetail(id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,payFormVO);
        }catch(BusinessException e){
            logger.error("付款查看详细:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("付款查看详细:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "付款查看修改记录", notes = "付款查看修改记录 | 开发者 苏磊 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PostMapping(value = "/getPayUpdateRecord")
    @ApiImplicitParam(name = "requestModifyPageVO", value = "分页及参数条件", required = true, dataType = "RequestModifyPageVO")
    public Result<Page<List<PayUpdateRecordVO>>> getPayUpdateRecord(HttpSession session, @RequestBody RequestModifyPageVO requestModifyPageVO){
        Result<Page<List<PayUpdateRecordVO>>> result = new Result<>();
        try{
            if(requestModifyPageVO.getPageNo() <= 0 || requestModifyPageVO.getPageSize() <= 0){
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(requestModifyPageVO.getPageNo(), requestModifyPageVO.getPageSize());
            UserVO loginUser = (UserVO) session.getAttribute("user");
            page = paymentService.getPayUpdateRecord(page,requestModifyPageVO,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        }catch(BusinessException e){
            logger.error("付款查看修改记录:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("付款查看修改记录:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "付款修改记录导出", notes = "付款修改记录导出 | 开发者 苏磊 | 开发中")
    @RequestMapping(value="/exportUpdateRecord/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "当前需要查看的收款的主键ID", required = true, dataType = "Long", paramType="path")
    public void exportUpdateRecord(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @PathVariable Long id) throws Exception {

        try {
            OutputStream output = null;
            output = response.getOutputStream();
            String name = "付款修改记录信息";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<PayUpdateRecordVO> list = paymentService.getUpdateRecordWithNoPage(id);
            paymentService.exportUpdateRecord(output, list,loginUser);
        }catch(Exception e){
            logger.error("导出付款修改记录信息错误:"+e.getMessage(),e);
        }
    }

    @ApiOperation(value = "付款详细信息导出", notes = "付款详细信息导出 | 开发者 苏磊 | 开发中")
    @RequestMapping(value="/exportPayDetail/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "当前需要查看的付款详细信息", required = true, dataType = "Long", paramType="path")
    public void exportReceiveDetail(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @PathVariable Long id) throws Exception {

        try {
            OutputStream output = null;
            output = response.getOutputStream();
            String name = "付款信息";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            PayFormVO payFormVO = paymentService.getPayDetail(id);
            paymentService.export(output, payFormVO,loginUser);
        }catch(Exception e){
            logger.error("导出付款信息错误:"+e.getMessage(),e);
        }
    }

    @ApiOperation(value = "新增/修改付款详细", notes = "新增/修改付款详细 | 开发者 苏磊 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PostMapping(value = "/addOrUpdatePayDetail")
    @ApiImplicitParam(name = "payFormVO", value = "付款实体对象", required = true, dataType = "PayFormVO")
    public Result<String> addOrUpdatePayDetail(HttpSession session,@RequestBody PayFormVO payFormVO){
        Result<String> result = new Result<>();
        try{
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String msg = paymentService.addOrUpdatePayDetail(payFormVO,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,msg);
        }catch(BusinessException e){
            logger.error("新增/修改付款详细:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("新增/修改付款详细:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "批量/单个冲销", notes = "批量/单个冲销 | 开发者 苏磊 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PostMapping(value = "/sterilisation")
    public Result<String> sterilisation(HttpSession session,
                                        @ApiParam(value = "id集合", required = true) @RequestBody List<Long> list){
        Result<String> result = new Result<>();
        try{
            UserVO loginUser = (UserVO) session.getAttribute("user");
            paymentService.sterilisation(list,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("批量/单个冲销:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("批量/单个冲销:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看付款详细信息草稿", notes = "查看付款详细信息草稿 | 开发者 苏磊 | 开发中")
    @RequestMapping(value = "/daftCache/{companyId}/{companyType}", method = RequestMethod.GET)
    public Result<List<DraftCacheVO>> daftCacheVO(@ApiIgnore UserVO userVO,@PathVariable("companyId")Long companyId,@PathVariable("companyType")Integer companyType) {

        Result<List<DraftCacheVO>> result = new Result<>();
        List<DraftCacheVO> draftCacheVO = paymentService.getDraftCacheVO(userVO,companyId,companyType);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,draftCacheVO);
        return  result;
    }

    @ApiOperation(value = "保存付款详细草稿缓存", notes = "保存付款详细草稿缓存 | 开发者 苏磊 | 开发中")
    @RequestMapping(value = "/save/daftCache", method = RequestMethod.POST)
    public Result<DraftCacheVO<String>> saveDraftCache(@ApiIgnore UserVO userVO, @RequestBody DraftCacheVO<String> draftCacheVO) throws Exception {

        Result<DraftCacheVO<String>> result = new Result<>();
        try{
            draftCacheVO = paymentService.saveDraftCache(userVO,draftCacheVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,draftCacheVO);
            return result;
        }catch(BusinessException e){
            logger.error("保存付款详细草稿缓存错误:"+e.getMessage(),e);
            result.setBizCodeSuccessInfo(SysCode.FAIL.getCode(), e.getMessage(),null);
            return result;
        }catch(Exception e){
            logger.error("保存付款详细草稿缓存错误:"+e.getMessage(),e);
            result.setBizCodeSuccessInfo(SysCode.FAIL.getCode(), e.getMessage(), null);
            return result;
        }
    }

    @ApiOperation(value = "新增中的应付单据查询", notes = "新增中的应付单据查询 | 开发者 苏磊 | 开发中")
    @RequestMapping(value="/getPayDocuments", method=RequestMethod.POST)
    @ApiImplicitParam(name = "requestPayDocuments", value = "检索条件", required = true, dataType = "RequestPayDocuments")
    public Result<Page<PayDocumentsVO>> getPayDocuments(HttpServletRequest request,
                                                        @RequestBody RequestPayDocuments requestPayDocuments){
        Result<Page<PayDocumentsVO>> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            Page page = new Page(requestPayDocuments.getPageNo(), requestPayDocuments.getPageSize());
            page = paymentService.getPayDocuments(page,requestPayDocuments,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(BusinessException e){
            logger.error("新增中的应付单据查询错误:"+e.getMessage(),e.getMessage());
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }catch(Exception e){
            logger.error("新增中的应付单据查询错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "删除付款草稿缓存", notes = "删除付款草稿缓存 | 开发者 苏磊 | 已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "redisKeyValue", value = "redis map key"
                    , required = true, paramType="path")
    })
    @RequestMapping(value = "/remove/daftCache/{redisKeyValue}/{companyId}/{companyType}", method = RequestMethod.GET)
    public Result removeDraftCach(@ApiIgnore UserVO userVO, @PathVariable(value = "redisKeyValue", required = true) String redisKeyValue,
                                  @PathVariable(value = "companyId",required = true) Long companyId,
                                  @PathVariable(value = "companyType",required = true) Integer companyType) {

        Result result = new Result<>();
        paymentService.removeDraftCach(userVO.getEnterpriseId(), OrderRule.FINANCE_PAYMENT.getCodePrefix(),redisKeyValue,companyId,companyType);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return  result;
    }

}
