package com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.controller;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.service.CollectMoneyService;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.vo.*;
import com.rograndec.feijiayun.chain.common.*;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
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
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @ClassName: AccountsDtlController
 * @Description: 财务管理-收付款-收款
 * @author lei.su
 * @version 1.0
 * @date 2018年1月3日 下午11:32
 */
@Api(value = "CollectMoneyController",description = "财务管理-收付款-收款")
@RestController
@RequestMapping("finance/collectMoney")
public class CollectMoneyController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CollectMoneyService collectMoneyService;

    @Autowired
    private CommonComponent commonComponent;

    @ApiOperation(value = "收款分页列表", notes = "获取数据 | 开发者 苏磊 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PostMapping(value = "/getReceivePage")
    @ApiImplicitParam(name = "requestReceivePageVO", value = "检索条件", required = true, dataType = "RequestReceivePageVO")
    public Result<Page<ReceiveTotalPageVO>> getReceivePage(HttpSession session, @RequestBody RequestReceivePageVO requestReceivePageVO){
        Result<Page<ReceiveTotalPageVO>> result = new Result<>();
        try{
            if(requestReceivePageVO.getPageNo() <= 0 || requestReceivePageVO.getPageSize() <= 0){
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(requestReceivePageVO.getPageNo(), requestReceivePageVO.getPageSize());
            UserVO loginUser = (UserVO) session.getAttribute("user");
            page = collectMoneyService.getReceivePage(page,requestReceivePageVO,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        }catch(BusinessException e){
            logger.error("收款分页列表查询错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("收款分页列表查询错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "收款查看详细", notes = "收款查看详细 | 开发者 苏磊 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @GetMapping(value = "/getReceiveDetail")
    public Result<ReceiveFormVO> getReceiveDetail(HttpSession session, @ApiParam(value = "当前需要查看的ID", required = true) @RequestParam Long id){
        Result<ReceiveFormVO> result = new Result<>();
        try{
            UserVO loginUser = (UserVO) session.getAttribute("user");
            ReceiveFormVO receiveFormVO = collectMoneyService.getReceiveDetail(id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, receiveFormVO);
        }catch(BusinessException e){
            logger.error("收款查看详细:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("收款查看详细:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "收款查看修改记录", notes = "收款查看修改记录 | 开发者 苏磊 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PostMapping(value = "/getReceiveUpdateRecord")
    @ApiImplicitParam(name = "requestModifyPageVO", value = "分页及参数条件", required = true, dataType = "RequestModifyPageVO")
    public Result<Page<List<ReceiveUpdateRecordVO>>> getReceiveUpdateRecord(HttpSession session, @RequestBody RequestModifyPageVO requestModifyPageVO){
        Result<Page<List<ReceiveUpdateRecordVO>>> result = new Result<>();
        try{
            if(requestModifyPageVO.getPageNo() <= 0 || requestModifyPageVO.getPageSize() <= 0){
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(requestModifyPageVO.getPageNo(), requestModifyPageVO.getPageSize());
            UserVO loginUser = (UserVO) session.getAttribute("user");
            page = collectMoneyService.getReceiveUpdateRecord(page,requestModifyPageVO,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        }catch(BusinessException e){
            logger.error("收款查看修改记录:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("收款查看修改记录:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "修改记录导出", notes = "修改记录导出 | 开发者 苏磊 | 开发中")
    @RequestMapping(value="/exportUpdateRecord/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "当前需要查看的收款的主键ID", required = true, dataType = "Long", paramType="path")
    public void exportUpdateRecord(HttpServletRequest request,
                                    HttpServletResponse response,
                                    @PathVariable Long id) throws Exception {

        try {
            OutputStream output = null;
            output = response.getOutputStream();
            String name = "收款修改记录信息";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<ReceiveUpdateRecordVO> list = collectMoneyService.getUpdateRecordWithNoPage(id);
            collectMoneyService.exportUpdateRecord(output, list,loginUser);
        }catch(Exception e){
            logger.error("导出收款修改记录信息错误:"+e.getMessage(),e);
        }
    }

    @ApiOperation(value = "收款详细信息导出", notes = "收款详细信息导出 | 开发者 苏磊 | 开发中")
    @RequestMapping(value="/exportReceiveDetail/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "当前需要查看的收款详细信息", required = true, dataType = "Long", paramType="path")
    public void exportReceiveDetail(HttpServletRequest request,
                                           HttpServletResponse response,
                                           @PathVariable Long id) throws Exception {

        try {
            OutputStream output = null;
            output = response.getOutputStream();
            String name = "收款信息";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            ReceiveFormVO receiveFormVO = collectMoneyService.getReceiveDetail(id);
            collectMoneyService.export(output, receiveFormVO,loginUser);
        }catch(Exception e){
            logger.error("导出收款信息错误:"+e.getMessage(),e);
        }
    }

    @ApiOperation(value = "收款人员模糊搜索下拉框", notes = "收款人员模糊搜索下拉框 | 开发者 苏磊 | 开发中")
    @RequestMapping(value="/getReceivableManNameSelector", method=RequestMethod.GET)
    public Result<List<NewSelectBean>> getReceivableManNameSelector(HttpServletRequest request,
                                                                    @ApiParam(value = "员工编码/员工检索码/员工名称模糊搜索") @RequestParam(required = false) String param){
        Result<List<NewSelectBean>> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<User> userList = commonComponent.getReceivableManNameSelector(loginUser,param);
            List<NewSelectBean> list = new ArrayList<>();
            for (User s : userList){
                NewSelectBean bean = new NewSelectBean();
                bean.setId(s.getId());
                bean.setText(s.getName());
                list.add(bean);
            }
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        }catch(Exception e){
            logger.error("收款人员模糊搜索下拉框错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    //付款单位编码：可搜索的选择器
    //允许按付款单位编码、检索码、名称模糊搜索
    //选择后显示付款单位编码
    @ApiOperation(value = "付款单位编码模糊搜索下拉框", notes = "付款单位编码模糊搜索下拉框 | 开发者 苏磊 | 开发中")
    @RequestMapping(value="/getPayCompanyCodeSelector", method=RequestMethod.GET)
    public Result<List<SelectBeanWithCode>> getPayCompanyCodeSelector(HttpServletRequest request,
                                                                    @ApiParam(value = "付款单位类型0-供货单位；1-购货单位 tips:" +
                                                                            "总部:供货单位->总部的供应商 购货单位->加盟店   加盟店:供货单位->总部+加盟店的供货单位(加盟店的这个下拉框只有供货单位这一个选项)", required = true) @RequestParam Integer companyType,
                                                                    @ApiParam(value = "付款单位编码/检索码/名称模糊搜索") String param){
        Result<List<SelectBeanWithCode>> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<SelectBeanWithCode> list = collectMoneyService.getPayCompanyCodeSelector(companyType,loginUser,param);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        }catch(Exception e){
            logger.error("付款单位编码模糊搜索下拉框:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "付款单位名称展示", notes = "付款单位名称展示 | 开发者 苏磊 | 开发中")
    @RequestMapping(value="/getPayCompanyName", method=RequestMethod.GET)
    public Result<String> getPayCompanyName(HttpServletRequest request,
                                                                 @ApiParam(value = "付款单位类型0-供货单位；1-购货单位 tips:", required = true) @RequestParam Integer companyType,
                                                                 @ApiParam(value = "付款单位主键ID") @RequestParam Long id){
        Result<String> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String companyName = collectMoneyService.getPayCompanyName(companyType,loginUser,id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, companyName);
        }catch(Exception e){
            logger.error("付款单位名称展示:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "开户银行下拉框", notes = "开户银行下拉框 | 开发者 苏磊 | 开发中")
    @RequestMapping(value="/getBankSelector", method=RequestMethod.GET)
    public Result<List<SelectThreeBean>> getBankSelector(HttpServletRequest request){
        Result<List<SelectThreeBean>> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<SelectThreeBean> list = collectMoneyService.getBankSelector(loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        }catch(Exception e){
            logger.error("开户银行下拉框:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "新增/修改收款详细", notes = "新增/修改收款详细 | 开发者 苏磊 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PostMapping(value = "/addOrUpdateReceiveDetail")
    @ApiImplicitParam(name = "receiveFormVO", value = "收款实体对象", required = true, dataType = "ReceiveFormVO")
    public Result<String> addOrUpdateReceiveDetail(HttpSession session,@RequestBody ReceiveFormVO receiveFormVO){
        Result<String> result = new Result<>();
        try{
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String msg = collectMoneyService.addOrUpdateReceiveDetail(receiveFormVO,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,msg);
        }catch(BusinessException e){
            logger.error("新增/修改收款详细:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("新增/修改收款详细:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "新增中的应收单据查询", notes = "新增中的应收单据查询 | 开发者 苏磊 | 开发中")
    @RequestMapping(value="/getReceivableDocuments", method=RequestMethod.POST)
    @ApiImplicitParam(name = "requestReceivableDocuments", value = "检索条件", required = true, dataType = "RequestReceivableDocuments")
    public Result<Page<ReceivableDocumentsVO>> getReceivableDocuments(HttpServletRequest request,
                                                                      @RequestBody RequestReceivableDocuments requestReceivableDocuments){
        Result<Page<ReceivableDocumentsVO>> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            Page page = new Page(requestReceivableDocuments.getPageNo(), requestReceivableDocuments.getPageSize());
            page = collectMoneyService.getReceivableDocuments(page,requestReceivableDocuments,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(BusinessException e){
            logger.error("新增中的应收单据查询错误:"+e.getMessage(),e.getMessage());
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }catch(Exception e){
            logger.error("新增中的应收单据查询错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "批量/单个冲销", notes = "批量/单个冲销 | 开发者 苏磊 | 开发中", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PostMapping(value = "/sterilisation")
    public Result<String> sterilisation(HttpSession session,
                                        @ApiParam(value = "id集合", required = true) @RequestBody  List<Long> list){
        Result<String> result = new Result<>();
        try{
            UserVO loginUser = (UserVO) session.getAttribute("user");
            collectMoneyService.sterilisation(list,loginUser);
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

    @ApiOperation(value = "查看收款详细信息草稿", notes = "查看收款详细信息草稿 | 开发者 苏磊 | 开发中")
    @RequestMapping(value = "/daftCache/{companyId}/{companyType}", method = RequestMethod.GET)
    public Result<List<DraftCacheVO>> daftCacheVO(@ApiIgnore UserVO userVO,@PathVariable("companyId")Long companyId,@PathVariable("companyType")Integer companyType) {

        Result<List<DraftCacheVO>> result = new Result<>();
        List<DraftCacheVO> draftCacheVO = collectMoneyService.getDraftCacheVO(userVO,companyId,companyType);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,draftCacheVO);
        return result;
    }

    @ApiOperation(value = "保存收款详细草稿缓存", notes = "保存收款详细草稿缓存 | 开发者 苏磊 | 开发中")
    @RequestMapping(value = "/save/daftCache", method = RequestMethod.POST)
    public Result<DraftCacheVO<String>> saveDraftCache(@ApiIgnore UserVO userVO, @RequestBody DraftCacheVO<String> cache) throws Exception {

        Result<DraftCacheVO<String>> result = new Result<>();
        try{
            cache = collectMoneyService.saveDraftCache(userVO,cache);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,cache);
            return result;
        }catch(BusinessException e){
            logger.error("保存收款详细草稿缓存错误:"+e.getMessage(),e);
            result.setBizCodeSuccessInfo(SysCode.FAIL.getCode(), e.getMessage(),null);
            return result;
        }catch(Exception e){
            logger.error("保存收款详细草稿缓存错误:"+e.getMessage(),e);
            result.setBizCodeSuccessInfo(SysCode.FAIL.getCode(), e.getMessage(), null);
            return result;
        }
    }

    @ApiOperation(value = "删除收款草稿缓存", notes = "删除收款草稿缓存 | 开发者 苏磊 | 开发中")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "redisKeyValue", value = "redis map key"
                    , required = true, paramType="path")
    })
    @RequestMapping(value = "/remove/daftCache/{redisKeyValue}/{companyId}/{companyType}", method = RequestMethod.GET)
    public Result removeDraftCach(@ApiIgnore UserVO userVO,
                                  @PathVariable(value = "redisKeyValue", required = true) String redisKeyValue,
                                  @PathVariable(value = "companyId", required = true) Long companyId,
                                  @PathVariable(value = "companyType",required = true) Integer companyType) {

        Result result = new Result<>();
        collectMoneyService.removeDraftCach(userVO.getEnterpriseId(), OrderRule.FINANCE_RECEIVABLE.getCodePrefix(),redisKeyValue,companyId,companyType);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return  result;
    }

}
