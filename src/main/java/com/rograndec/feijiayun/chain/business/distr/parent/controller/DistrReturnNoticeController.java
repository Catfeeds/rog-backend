package com.rograndec.feijiayun.chain.business.distr.parent.controller;

import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrReturnNoticeService;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnNoticeFormVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnNoticePageVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 *
 * @author leisu
 *
 */
@Api(value = "distr_return_notice",description = "配送管理-总部-配后退回-配后退回通知单接口服务")
@RestController
@RequestMapping("distr/return/notice")
@Validated
public class DistrReturnNoticeController {

    private static final Log logger = LogFactory.getLog(DistrReturnNoticeController.class);

    @Autowired
    private DistrReturnNoticeService distrReturnNoticeService;

    @ApiOperation(value="按条件搜索配后退回page信息", notes = "按条件搜索配后退回page信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getReturnNoticePage", method = RequestMethod.GET)
    public Result<Page<List<DistrReturnNoticePageVO>>> getReturnNoticePage(HttpServletRequest request,
                                                                      @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                      @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                      @ApiParam(value = "起始日期", required = false) @RequestParam(required=false) Date startTime,
                                                                      @ApiParam(value = "终止日期", required = false) @RequestParam(required=false) Date endTime,
                                                                      @ApiParam(value = "要货单位编码", required = false) @RequestParam(required=false) String requestUnitCode,
                                                                      @ApiParam(value = "要货单位名称", required = false) @RequestParam(required=false) String requestUnitName,
                                                                      @ApiParam(value = "配后退回单号", required = false) @RequestParam(required=false) String code,
                                                                      @ApiParam(value = "配货类型0-总部配送；3-分店间调剂；4-直调配送", required = false) @RequestParam(required=false) Integer distrType,
                                                                      @ApiParam(value = "状态(不填的话就搜全部 30-待收货 31-待验收 32-待入库 33-已完成)", required = false) @RequestParam(required=false) Integer status,
                                                                      @ApiParam(value = "按某一列排序(noticeDate或者code)", required = false) @RequestParam(required=false) String order,
                                                                      @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort){
        Result<Page<List<DistrReturnNoticePageVO>>> result = new Result<Page<List<DistrReturnNoticePageVO>>>();
        try{
            if(pageNo <= 0 || pageSize <= 0){
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            page = distrReturnNoticeService.getReturnNoticePage(page,startTime,endTime,requestUnitCode,requestUnitName,code,distrType,status,order,sort,loginUser.getChainType() == ChainType.Headquarters.getCode() ? loginUser.getEnterpriseId() : loginUser.getParentId());
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
            logger.error("按条件搜索配后退回page信息:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看按钮--查看配后退回通知详细信息", notes = "查看按钮--查看配后退回通知详细信息 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/getReturnNoticeForm", method=RequestMethod.GET)
    public Result<DistrReturnNoticeFormVO> getReturnNoticeDeatil(HttpServletRequest request,
                                                            @ApiParam(value = "当前需要查看的ID", required = true) @RequestParam Long id){
        Result<DistrReturnNoticeFormVO> result = new Result<DistrReturnNoticeFormVO>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            DistrReturnNoticeFormVO distrReturnNoticeFormVO = distrReturnNoticeService.getReturnNoticeDeatil(loginUser.getChainType() == ChainType.Headquarters.getCode() ? loginUser.getEnterpriseId() : loginUser.getParentId(),id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,distrReturnNoticeFormVO);
        }catch(Exception e){
            logger.error("查看按钮--查看配后退回通知详细信息:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "配后退回信息导出", notes = "配后退回信息导出 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/export/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "当前需要查看的配后退回信息", required = true, dataType = "Long", paramType="path")
    public void exportMemberSuccessAndFail(HttpServletRequest request,
                                           HttpServletResponse response,
                                           @PathVariable Long id) throws Exception {

        try {
            OutputStream output = null;
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            String name = "配后退回";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            DistrReturnNoticeFormVO distrReturnNoticeFormVO = distrReturnNoticeService.getReturnNoticeDeatil(loginUser.getEnterpriseId(),id);
            distrReturnNoticeService.export(output,distrReturnNoticeFormVO,loginUser);
        }catch(Exception e){
            logger.error("导出配后退回信息错误:"+e.getMessage(),e);
        }

    }

}
