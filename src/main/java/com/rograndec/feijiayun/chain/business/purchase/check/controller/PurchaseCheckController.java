package com.rograndec.feijiayun.chain.business.purchase.check.controller;

import com.rograndec.feijiayun.chain.business.basic.user.entity.SimpleUserVO;
import com.rograndec.feijiayun.chain.business.purchase.check.service.PurchaseCheckService;
import com.rograndec.feijiayun.chain.business.purchase.check.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
/**
 * Created by zeshi.sun on 2017/9/13.
 */
@Api(value = "PurchaseCheck", description = "采购管理-采购验收服务接口")
@RestController
@RequestMapping("purchase/check/purchaseCheck")
public class PurchaseCheckController {
    private static final Log logger = LogFactory.getLog(PurchaseCheckController.class);

    @Autowired
    PurchaseCheckService purchaseCheckService;
    @ApiOperation(value = "分页获取所有采购管理-采购验收已验收信息(采购入库待入库界面也可使用)", notes = "分页获取所有采购管理-采购验收已验收信息(采购入库待入库界面也可使用) | 开发者 zeshi.sun | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPurchaseCheckPage", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<List<PurchaseCheckVO>>> getPurchaseCheckPage(HttpServletRequest request,
                                             @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                             @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                             @ApiParam(value = "查询类型 0-验收功能查询  1-入库功能使用", required = true) @RequestParam(required = false) Long type,
                                             @ApiParam(value = "日期范围-开始时间", required = false) @RequestParam(required = false) Date startTime,
                                             @ApiParam(value = "日期范围-结束时间", required = false) @RequestParam(required = false) Date endTime,
                                             @ApiParam(value = "供货单位编码", required = false) @RequestParam(required = false) String supplierCode,
                                             @ApiParam(value = "供货单位名称", required = false) @RequestParam(required = false) String supplierName,
                                             @ApiParam(value = "验收单号", required = false) @RequestParam(required = false) String code,
                                             @ApiParam(value = "验收人员", required = false) @RequestParam(required = false) String checkerName,
                                             @ApiParam(value = "排序参数,就是列名", required = false) @RequestParam(required = false) String orderName,
                                             @ApiParam(value = "排序方式,ASC或者DESC", required = false) @RequestParam(required = false) String orderType
    ) {
        Result<Page<List<PurchaseCheckVO>>> result = new Result<Page<List<PurchaseCheckVO>>>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<PurchaseCheckVO> purchaseCheckVoList = purchaseCheckService
                    .getPurchaseCheckPage(pageNo, pageSize, loginUser.getEnterpriseId(), page, startTime, endTime,supplierCode, supplierName, code, checkerName, orderName, orderType, type);
            page.setResult(purchaseCheckVoList);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("获取获取所有采购管理-采购验收已验收信息错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看已验收信息的表头数据(采购入库待入库界面也可使用)", notes = "查看已验收信息的表头数据(采购入库待入库界面也可使用) | 开发者 zeshi.sun | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPurchaseCheckHead", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<PurchaseCheckHeadVO>> getPurchaseCheckHead(HttpSession session,
                                                                  @ApiParam(value = "验收单ID", required = true) @RequestParam Long id
    ) {
        Result<List<PurchaseCheckHeadVO>> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, purchaseCheckService.getPurchaseCheckHead(userVO,id));
        } catch (Exception e) {
            logger.error("查看已验收信息的表头数据(采购入库待入库界面也可使用)错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);  // 前端说改为成功
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看已验收信息的验收明细(采购入库待入库界面也可使用)", notes = "查看已验收信息的验收明细(采购入库待入库界面也可使用) | 开发者 zeshi.sun | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPurchaseCheckDetail", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<PurchaseCheckDeatilVO>> getPurchaseCheckDetail(HttpSession session,
                                                                  @ApiParam(value = "验收单ID", required = true) @RequestParam Long id
    ) {
        Result<List<PurchaseCheckDeatilVO>> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long enterpriseId = userVO.getEnterpriseId();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, purchaseCheckService.getPurchaseCheckDetail(enterpriseId,id));
        } catch (Exception e) {
            logger.error("查看已验收信息的验收明细(采购入库待入库界面也可使用)错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);//前端说改为成功
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看已验收/待验收信息的配送和结算数据(采购入库待入库界面也可使用)", notes = "查看已验收/待验收信息的配送和结算数据(采购入库待入库界面也可使用) | 开发者 zeshi.sun | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPurchaseCheckRight", method = RequestMethod.GET)
    @ResponseBody
    public Result<PurchaseCheckOtherVO> getPurchaseCheckRight(HttpSession session,
                                                                  @ApiParam(value = "单据ID", required = true) @RequestParam Long id,
                                                              @ApiParam(value = "类型  0：待验收  1：已验收", required = true) @RequestParam Long type
    ) {
        Result<PurchaseCheckOtherVO> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long enterpriseId = userVO.getEnterpriseId();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, purchaseCheckService.getPurchaseCheckRight(enterpriseId,id,type));
        } catch (Exception e) {
            logger.error("查看已验收/待验收信息的配送和结算数据(采购入库待入库界面也可使用)错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);// 前端说改为成功
            return result;
        }
        return result;
    }
    @ApiOperation(value = "分页获取所有采购管理-采购验收待验收信息", notes = "分页获取所有采购管理-采购验收待验收信息 | 开发者 zeshi.sun | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getWaitPurchaseCheckPage", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<List<PurchaseReceiveReVO>>> getWaitPurchaseCheckPage(HttpServletRequest request,
                                             @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                             @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                             @ApiParam(value = "日期范围-开始时间", required = false) @RequestParam(required = false) Date startTime,
                                             @ApiParam(value = "日期范围-结束时间", required = false) @RequestParam(required = false) Date endTime,
                                             @ApiParam(value = "供货单位编码", required = false) @RequestParam(required = false) String supplierCode,
                                             @ApiParam(value = "供货单位名称", required = false) @RequestParam(required = false) String supplierName,
                                             @ApiParam(value = "收货单号", required = false) @RequestParam(required = false) String code,
                                             @ApiParam(value = "收货人员", required = false) @RequestParam(required = false) String receiverName,
                                             @ApiParam(value = "排序参数,就是列名", required = false) @RequestParam(required = false) String orderName,
                                             @ApiParam(value = "排序方式,ASC或者DESC", required = false) @RequestParam(required = false) String orderType
    ) {
        Result<Page<List<PurchaseReceiveReVO>>> result = new Result<Page<List<PurchaseReceiveReVO>>>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<PurchaseReceiveReVO> purchaseCheckVoList = purchaseCheckService
                    .getWaitPurchaseCheckPage(pageNo, pageSize, loginUser.getEnterpriseId(), page, startTime, endTime,supplierCode, supplierName, code, receiverName, orderName, orderType);
            page.setResult(purchaseCheckVoList);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("获取获取所有采购管理-采购验收待验收信息错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看待验收信息的表头数据", notes = "查看待验收信息的表头数据 | 开发者 zeshi.sun  | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getWaitPurchaseCheckHead", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<WaitPurchaseCheckHeadVO>> getWaitPurchaseCheckHead(HttpSession session,
                                                                          @ApiParam(value = "收货单ID", required = true) @RequestParam Long id
    ) {
        Result<List<WaitPurchaseCheckHeadVO>> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long enterpriseId = userVO.getEnterpriseId();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, purchaseCheckService.getWaitPurchaseCheckHead(enterpriseId,id));
        } catch (Exception e) {
            logger.error("查看待验收信息的表头数据错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);// 前端说改为成功
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看待验收信息的收货明细", notes = "查看待验收信息的收货明细 | 开发者 zeshi.sun  | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getWaitPurchaseCheckDetail", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<WaitPurchaseCheckDeatilVO>> getWaitPurchaseCheckDetail(HttpSession session,
                                                                          @ApiParam(value = "收货单ID", required = true) @RequestParam Long id
    ) {
        Result<List<WaitPurchaseCheckDeatilVO>> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long enterpriseId = userVO.getEnterpriseId();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, purchaseCheckService.getWaitPurchaseCheckDetail(enterpriseId,id));
        } catch (Exception e) {
            logger.error("查看待验收信息的收货明细错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);// 前端说改为成功
            return result;
        }
        return result;
    }

    @ApiOperation(value = "点击验收按钮后的验收信息表头数据", notes = "点击验收按钮后的验收信息表头数据 | 开发者 zeshi.sun  | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCheckHead", method = RequestMethod.GET)
    @ResponseBody
    public Result<CheckHeadVO> getCheckHead(@ApiParam(value = "收货单ID", required = true) @RequestParam Long id,
                                            @ApiIgnore UserVO userVO
    ) {
        Result<CheckHeadVO> result = new Result<>();
        try {
            Long enterpriseId = userVO.getEnterpriseId();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, purchaseCheckService.getCheckHead(enterpriseId,id,userVO));
        } catch (Exception e) {
            logger.error("点击验收按钮后的验收信息表头数据错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "点击验收按钮后的验收信息验收明细数据", notes = "点击验收按钮后的验收信息验收明细数据 | 开发者 zeshi.sun  | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCheckDetail", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<CheckDeatilVO>> getCheckDetail(HttpSession session,
                                                  @ApiParam(value = "收货单ID", required = true) @RequestParam Long id
    ) {
        Result<List<CheckDeatilVO>> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long enterpriseId = userVO.getEnterpriseId();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, purchaseCheckService.getCheckDetail(userVO,enterpriseId,id));
        }  catch (BusinessException e) {
            logger.error("点击验收按钮后的验收信息验收明细数据错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("点击验收按钮后的验收信息验收明细数据错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "通过商品编码查询检验项目接口", notes = "通过商品编码查询检验项目接口 | 开发者 zeshi.sun  | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCheckProject", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<CheckProjectVO>> getCheckProject(HttpSession session,
                                                      @ApiParam(value = "商品编码", required = true) @RequestParam String code
    ) {
        Result<List<CheckProjectVO>> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long enterpriseId = userVO.getEnterpriseId();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, purchaseCheckService.getCheckProject(enterpriseId,code));
        } catch (Exception e) {
            logger.error("通过商品编码查询检验项目接口错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.SUCCESS);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "保存验收单据", notes = "保存验收单据 | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveCheck", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> saveCheck(HttpServletRequest request,
                                                @ApiParam(value = "保存验收单据", required = true) @RequestBody SaveCheckVO  saveCheckVO) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            purchaseCheckService.saveCheck(loginUser, saveCheckVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存验收单据成功");

        }  catch (BusinessException e) {
            logger.error("保存验收单据错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("保存验收单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查询验收结论接口,查询处置措施接口,查询验收不合格原因接口 ", notes = "查询验收结论接口,查询处置措施接口,查询验收不合格原因接口   | 开发者 zeshi.sun  | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getConclusion", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<ConclusionVO>> getConclusion(HttpServletRequest request,
                                                    @ApiParam(value = "设置类型（1-不合格原因；3-处置措施；6-验收结论）", required = true) @RequestParam Long  type
    ) {
        Result<List<ConclusionVO>> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            Long  enterpriseId = loginUser.getEnterpriseId();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, purchaseCheckService.getConclusion(enterpriseId,loginUser.getChainType(),type));
        } catch (Exception e) {
            logger.error("查询验收结论接口,查询处置措施接口,查询验收不合格原因接口错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="获取验收人员", notes = "获取验收人员 | 开发者:zeshi.sun | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getReceiver", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<SimpleUserVO>> getReceiver(HttpServletRequest request){
        Result<List<SimpleUserVO>> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,purchaseCheckService.getChecker(loginUser));
        }catch(Exception e){
            logger.error("获取验收人员错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "导出采购验收单信息", notes = "导出采购验收单信息 | 开发者:zeshi.szeshi.sun | 已联调 ")
    @RequestMapping(value="/exportPurchaseCheck/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "当前需要查看的已验收单据ID", required = true, dataType = "Long", paramType="path")
    public void exportPurchaseReceive(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName()+"采购验收单";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            PurchaseCheckRequestVO purchaseCheckRequestVO = purchaseCheckService.getCheckDetails(loginUser,id);
            purchaseCheckService.exportExcel(output,purchaseCheckRequestVO,loginUser);
        }catch(Exception e){
            logger.error("导出采购验收单信息错误:"+e.getMessage(),e);
        }
    }

    @ApiOperation(value = "查看采购验收草稿缓存", notes = "查看采购验收草稿缓存 | 开发者 zeshi.sun | 已完成")
    @RequestMapping(value = "/daftCache", method = RequestMethod.GET)
    public Result<DraftCacheVO> daftCacheVO(@ApiIgnore UserVO userVO,
                                                  @ApiParam(value = "收货单ID(上级单据ID)", required = true) @RequestParam Long  baseOrderId) {

        Result<DraftCacheVO> result = new Result<>();

        DraftCacheVO draftCacheVO = purchaseCheckService.getDraftCacheVO(userVO, baseOrderId);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,draftCacheVO);

        return  result;
    }

    @ApiOperation(value = "保存采购验收草稿缓存", notes = "保存采购验收草稿缓存 | 开发者 zeshi.sun | 已完成")
    @RequestMapping(value = "/save/daftCache", method = RequestMethod.POST)
    public Result saveDraftCache(@ApiIgnore UserVO userVO,@RequestBody DraftCacheVO<CheckHeadVO> draftCacheVO) {

        Result result = new Result<>();
        try {
        DraftCacheVO<CheckHeadVO> purchaseCheckDraftCacheVODraftCacheVO = purchaseCheckService.saveDraftCache(userVO,draftCacheVO);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS,purchaseCheckDraftCacheVODraftCacheVO);
        }  catch (BusinessException e) {
            logger.error("保存采购验收草稿缓存错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
        }catch(Exception e){
            logger.error("保存采购验收草稿缓存错误:"+e.getMessage(),e);
        }
        return  result;
    }

    @ApiOperation(value = "删除采购验收草稿缓存", notes = "删除采购验收草稿缓存 | 开发者 zeshi.sun | 已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "redisKeyValue", value = "redis map key"
                    , required = true, paramType="path")
    })
    @RequestMapping(value = "/remove/daftCache/{redisKeyValue}", method = RequestMethod.POST)
    public Result removeDraftCach(@ApiIgnore UserVO userVO, @PathVariable(required = true) String redisKeyValue) {

        Result result = new Result<>();

        purchaseCheckService.removeDraftCach(userVO.getEnterpriseId(), OrderRule.PURCHASE_CHECK.getCodePrefix(),redisKeyValue);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS);

        return  result;
    }
}