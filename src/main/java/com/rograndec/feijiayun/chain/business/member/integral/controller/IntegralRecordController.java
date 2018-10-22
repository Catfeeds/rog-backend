package com.rograndec.feijiayun.chain.business.member.integral.controller;

import com.rograndec.feijiayun.chain.business.member.info.controller.MemberInfoController;
import com.rograndec.feijiayun.chain.business.member.info.entity.MemberInfo;
import com.rograndec.feijiayun.chain.business.member.info.vo.MemberInfoPageVO;
import com.rograndec.feijiayun.chain.business.member.integral.service.IntegralRecordService;
import com.rograndec.feijiayun.chain.business.member.set.entity.MemberCardType;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.HasBeenInstorageFormVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SelectBean;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(value = "memberIntegral", description = "会员管理-积分管理", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/member/integral")
public class IntegralRecordController {

    private static final Log logger = LogFactory.getLog(IntegralRecordController.class);

    @Autowired
    private IntegralRecordService integralRecordService;

    @ApiOperation(value="按条件搜索查询会员信息", notes = "按条件搜索查询会员信息[会员积分状态&&当前页面只显示状态正常的&&只显示类型是积分+积分加储值] | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getMemberInfoPage", method = RequestMethod.GET)
    public Result<Page<List<MemberInfoPageVO>>> getMemberInfoPage(HttpServletRequest request,
                                                                  @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                  @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                  @ApiParam(value = "会员卡号/手机号/姓名", required = false) @RequestParam(required=false) String param,
                                                                  @ApiParam(value = "会员卡类型", required = false) @RequestParam(required=false) Long cardTypeId,
                                                                  @ApiParam(value = "会员卡级别", required = false) @RequestParam(required=false) Long cardLevelId,
                                                                  @ApiParam(value = "发卡起始时间", required = false) @RequestParam(required=false) Date startSendCardTime,
                                                                  @ApiParam(value = "发卡终止时间", required = false) @RequestParam(required=false) Date endSendCardTime,
                                                                  @ApiParam(value = "发卡门店类型(1-自营店 2-加盟店)", required = false) @RequestParam(required=false) Integer chainType,
                                                                  @ApiParam(value = "发卡门店编码", required = false) @RequestParam(required=false) String sendCardStorerCode,
                                                                  @ApiParam(value = "发卡门店名称", required = false) @RequestParam(required=false) String sendCardStorerName,
                                                                  @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort){
        Result<Page<List<MemberInfoPageVO>>> result = new Result<Page<List<MemberInfoPageVO>>>();
        try{
            if(pageNo <= 0 || pageSize <= 0){
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            page = integralRecordService.getMemberInfoPage(page,param,cardTypeId,cardLevelId,startSendCardTime,endSendCardTime,chainType,sendCardStorerCode,sendCardStorerName,sort,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
            logger.error("按条件搜索查询会员信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="会员积分[增分/减分/清零/换卡]操作", notes = "会员积分[增分/减分]操作 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/addOrSubIntegral", method = RequestMethod.POST)
    @ApiImplicitParam(name = "memberInfoPageVO", value = "给前台穿的MemberInfoPageVO对象", required = true, dataType = "MemberInfoPageVO")
    @ResponseBody
    public Result<Object> addOrSubIntegral(HttpServletRequest request,@RequestBody MemberInfoPageVO memberInfoPageVO){
        Result<Object> result = new Result<Object>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String operation = memberInfoPageVO.getOperation();
            MemberInfo memberInfo = integralRecordService.getMemberInfo(memberInfoPageVO.getId());
            if ("add".equals(operation)){
                if (memberInfoPageVO.getAddPoint() == null || "".equals(memberInfoPageVO.getAddPoint())){
                    result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"增分时增加积分不能为空！");
                    return result;
                }
                if(memberInfoPageVO.getAddPoint() != null) {
                	if(BigDecimal.ZERO.compareTo(memberInfoPageVO.getAddPoint()) >= 0) {
                		 result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"增分时增加积分不能小于0！");
                         return result;
                	}
                }
            }
            if ("sub".equals(operation)){
                if (memberInfoPageVO.getSubPoint() == null || "".equals(memberInfoPageVO.getSubPoint())){
                    result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"减分时减少积分不能为空！");
                    return result;
                }
                if (memberInfo.getCurrentIntegral() != null && (memberInfoPageVO.getSubPoint().compareTo(memberInfo.getCurrentIntegral()) == 1)){
                    result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"减分时减少积分不能大于当前积分！");
                    return result;
                }
            }
            if ("change".equals(operation)){
                if (memberInfoPageVO.getChangeCardCode() == null || "".equals(memberInfoPageVO.getChangeCardCode())){
                    result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"换卡时换卡的卡号不能为空！");
                    return result;
                }
                /**
                 * 当为换卡状态----用户填入的卡号必须存在&&状态必须是未发卡状态
                 */
                integralRecordService.judgeCardCode(memberInfoPageVO.getChangeCardCode(),loginUser,memberInfo.getCardTypeId());

            }
            integralRecordService.addOrSubIntegral(operation,memberInfoPageVO,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(BusinessException e){
            logger.error("会员积分[增分/减分/清零/换卡]操作错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("会员积分[增分/减分/清零/换卡]操作错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="积分管理中的会员卡类型下拉框", notes = "会员卡类型下拉框[积分管理里面的---只显示积分 + 积分储值] | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCardType", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<SelectBean>> getCardType(HttpServletRequest request){
        Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<MemberCardType> cardTypeList = integralRecordService.getCardType(loginUser.getEnterpriseId());
            List<SelectBean> list = new ArrayList<SelectBean>();
            for (MemberCardType s : cardTypeList){
                SelectBean bean = new SelectBean();
                bean.setId(String.valueOf(s.getId()));
                bean.setText(String.valueOf(s.getName()));
                list.add(bean);
            }
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        }catch(Exception e){
            logger.error("积分管理中的会员卡类型下拉框错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "导出积分管理信息", notes = "导出积分管理信息 | 开发者 苏磊 | 已联调 ")
    @RequestMapping(value="/exportMemberIntegral",method= RequestMethod.GET)
    public void Export(HttpServletRequest request, HttpServletResponse response,
                       @ApiParam(value = "会员卡号/手机号/姓名", required = false) @RequestParam(required=false) String param,
                       @ApiParam(value = "会员卡类型", required = false) @RequestParam(required=false) Long cardTypeId,
                       @ApiParam(value = "会员卡级别", required = false) @RequestParam(required=false) Long cardLevelId,
                       @ApiParam(value = "发卡起始时间", required = false) @RequestParam(required=false) Date startSendCardTime,
                       @ApiParam(value = "发卡终止时间", required = false) @RequestParam(required=false) Date endSendCardTime,
                       @ApiParam(value = "发卡门店类型(1-自营店 2-加盟店)", required = false) @RequestParam(required=false) Integer chainType,
                       @ApiParam(value = "发卡门店编码", required = false) @RequestParam(required=false) String sendCardStorerCode,
                       @ApiParam(value = "发卡门店名称", required = false) @RequestParam(required=false) String sendCardStorerName,
                       @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            String name = "会员积分管理单";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<MemberInfoPageVO> list = integralRecordService.exportMemberIntegral(param,cardTypeId,cardLevelId,startSendCardTime,endSendCardTime,chainType,sendCardStorerCode,sendCardStorerName,sort,loginUser);
            integralRecordService.exportExcel(output,list,loginUser);
        }catch(Exception e){
            logger.error("导出采购入库单信息错误:"+e.getMessage(),e);
        }
    }

}
