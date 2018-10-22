package com.rograndec.feijiayun.chain.business.member.info.controller;

import com.rograndec.feijiayun.chain.business.member.info.constant.CardCodeConstant;
import com.rograndec.feijiayun.chain.business.member.info.service.MemberInfoService;
import com.rograndec.feijiayun.chain.business.member.info.vo.MadeCardInfoVO;
import com.rograndec.feijiayun.chain.business.member.info.vo.MemberInfoPageVO;
import com.rograndec.feijiayun.chain.business.member.info.vo.MemberInfoSaveVO;
import com.rograndec.feijiayun.chain.business.member.info.vo.ResponseMemberExcelVO;
import com.rograndec.feijiayun.chain.business.member.set.entity.MemberCardLevel;
import com.rograndec.feijiayun.chain.business.member.set.entity.MemberCardType;
import com.rograndec.feijiayun.chain.business.member.storedamount.vo.StoredAmountPageVO;
import com.rograndec.feijiayun.chain.common.*;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Api(value = "memberInfo", description = "会员管理-会员信息", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/member/info")
public class MemberInfoController {

    private static final Log logger = LogFactory.getLog(MemberInfoController.class);

    @Autowired
    private MemberInfoService memberInfoService;

    @ApiOperation(value="按条件搜索查询会员信息", notes = "按条件搜索查询会员信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
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
                                                                  @ApiParam(value = "发卡人员名称", required = false) @RequestParam(required=false) String sendCardManName,
                                                                  @ApiParam(value = "状态（如果是 全部就不传 0-正常；1-未发卡；2-挂失；3-注销）", required = false) @RequestParam(required=false) Integer status,
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
            page = memberInfoService.getMemberInfoPage(page,param,cardTypeId,cardLevelId,startSendCardTime,endSendCardTime,chainType,sendCardStorerCode,sendCardStorerName,sendCardManName,status,loginUser,sort);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
            logger.error("按条件搜索查询会员信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="会员卡类型下拉框", notes = "会员卡类型下拉框 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCardType", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<NewSelectBean>> getCardType(HttpServletRequest request){
        Result<List<NewSelectBean>> result = new Result<List<NewSelectBean>>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<MemberCardType> cardTypeList = memberInfoService.getCardType(loginUser);
            List<NewSelectBean> list = new ArrayList<NewSelectBean>();
            for (MemberCardType s : cardTypeList){
                NewSelectBean bean = new NewSelectBean();
                bean.setId(s.getId());
                bean.setText(String.valueOf(s.getName()));
                list.add(bean);
            }
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        }catch(Exception e){
            logger.error("会员卡类型下拉框信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="会员卡级别下拉框", notes = "会员卡级别下拉框 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCardLevel", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<SelectBean>> getCardLevel(HttpServletRequest request){
        Result<List<SelectBean>> result = new Result<List<SelectBean>>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<MemberCardLevel> cardLevelList = memberInfoService.getCardLevel(loginUser);
            List<SelectBean> list = new ArrayList<SelectBean>();
            for (MemberCardLevel s : cardLevelList){
                SelectBean bean = new SelectBean();
                bean.setId(String.valueOf(s.getId()));
                bean.setText(String.valueOf(s.getName()));
                list.add(bean);
            }
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        }catch(Exception e){
            logger.error("会员卡级别下拉框信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="会员制卡查询", notes = "会员制卡查询(如果type字段 = 1，那么前端页面不显示这三个字段[储值金额，密码，密码确认]) | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/selectMadeCard", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<MadeCardInfoVO>> selectMadeCard(HttpServletRequest request){
        Result<List<MadeCardInfoVO>> result = new Result<List<MadeCardInfoVO>>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<MadeCardInfoVO> voList = memberInfoService.selectMadeCard(loginUser.getEnterpriseId());
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,voList);
        }catch(Exception e){
            logger.error("会员制卡查询信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="会员制卡保存", notes = "会员制卡保存 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveMadeCard", method = RequestMethod.POST)
    @ApiImplicitParam(name = "madeCardInfoVO", value = "需要保存的制卡实体", required = true, dataType = "MadeCardInfoVO")
    @ResponseBody
    public Result<String> saveMadeCard(HttpServletRequest request,@RequestBody MadeCardInfoVO madeCardInfoVO){
        Result<String> result = new Result<String>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            // 0-积分+储值 2-仅储值 验证密码
            if(madeCardInfoVO.getType() == 0 || madeCardInfoVO.getType() == 2) {
            	if (StringUtils.isBlank(madeCardInfoVO.getPassword())){
                    result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"密码不能为空!");
                    return result;
                }
                if (StringUtils.isBlank(madeCardInfoVO.getPasswordConfirm())){
                    result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"确认密码不能为空!");
                    return result;
                }
                if (madeCardInfoVO.getPassword().length() < 6 || madeCardInfoVO.getPassword().length() > 20){
                    result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"密码必须在6-20位!");
                    return result;
                }
                if (!madeCardInfoVO.getPassword().equals(madeCardInfoVO.getPasswordConfirm())){
                    result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(),"密码与确认密码不一致");
                    return result;
                }
            }
            if (madeCardInfoVO.getStartCardCode() == null || "".equals(madeCardInfoVO.getStartCardCode())){
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"制卡的初始卡号不能为空!");
                return result;
            }
            if (madeCardInfoVO.getStartCardCode().length() < CardCodeConstant.CARD_CODE_LENGTH){
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"制卡的初始卡号不能小于四位!");
                return result;
            }
            String msg = memberInfoService.saveMadeCard(madeCardInfoVO,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,msg);
        }catch(Exception e){
            logger.error("会员制卡保存错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="会员新增中默认数据的查询", notes = "会员新增中默认数据的查询(如果type字段 = 1，那么前端页面不显示这三个字段[储值金额，密码，密码确认]) && 发卡时间随着系统变化，前端显示  | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/selectDefault", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<MemberInfoSaveVO>> selectDefault(HttpServletRequest request){
        Result<List<MemberInfoSaveVO>> result = new Result<List<MemberInfoSaveVO>>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<MemberInfoSaveVO> voList = memberInfoService.selectDefault(loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,voList);
        }catch(Exception e){
            logger.error("会员新增中默认数据的查询信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="会员修改中默认数据的查询", notes = "会员修改中默认数据的查询(如果type字段 = 1，那么前端页面不显示这三个字段[储值金额，密码，密码确认]) && 发卡时间随着系统变化，前端显示  | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/selectDefaultUpdate/{id}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "当前需要查询的修改数据", required = true, dataType = "Long", paramType="path")
    @ResponseBody
    public Result<MemberInfoSaveVO> selectDefaultUpdate(HttpServletRequest request,@PathVariable Long id){
        Result<MemberInfoSaveVO> result = new Result<MemberInfoSaveVO>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            MemberInfoSaveVO memberInfoSaveVO = memberInfoService.selectDefaultUpdate(loginUser,id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,memberInfoSaveVO);
        }catch(Exception e){
            logger.error("会员新增中默认数据的查询信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="会员新增中填写的卡号是否重复", notes = "会员新增中填写的卡号是否重复（如果重复会返回错误信息） | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/selectMemberCardExist", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> selectMemberCardExist(HttpServletRequest request,@ApiParam(value = "会员卡号", required = true) @RequestParam String cardCode){
        Result<String> result = new Result<String>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            int type = memberInfoService.selectMemberCardExist(loginUser,cardCode);
            if(type > 0){
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"此卡号已存在！请换一个卡号！");
                return result;
            }
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"该卡号可以用");
        }catch(Exception e){
            logger.error("会员新增中填写的卡号是否重复信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }return result;
    }

    @ApiOperation(value="会员新增/修改中的保存方法", notes = "会员新增/修改方法[新增时不传id,修改时传id] | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateOrAddMember", method = RequestMethod.POST)
    @ApiImplicitParam(name = "memberInfoSaveVO", value = "需要保存的制卡实体", required = true, dataType = "MemberInfoSaveVO")
    @ResponseBody
    public Result<String> updateOrAddMember(HttpServletRequest request,@RequestBody MemberInfoSaveVO memberInfoSaveVO){
        Result<String> result = new Result<String>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if (memberInfoSaveVO.getId() == null){
                if (memberInfoSaveVO.getPassword().length() < 5 || memberInfoSaveVO.getPassword().length() > 20){
                    result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"密码必须在5-20位!");
                    return result;
                }
                if (!memberInfoSaveVO.getPassword().equals(memberInfoSaveVO.getPasswordConfirm()) && memberInfoSaveVO.getType() != 1){
                    result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"密码与确认密码不一致");
                    return result;
                }
                int type = memberInfoService.selectMemberCardExist(loginUser,memberInfoSaveVO.getCardCode());
                if(type > 0){
                    result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"此卡号已存在！请换一个卡号");
                    return result;
                }
                if (memberInfoSaveVO.getCardCode().length() < CardCodeConstant.CARD_CODE_LENGTH){
                    result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"新增的卡号不能小于四位！");
                    return result;
                }
            }
            memberInfoService.updateOrAddMember(memberInfoSaveVO,loginUser);
            String msg = "";
            if (memberInfoSaveVO.getId() == null){
                msg = "新增成功";
            }else{
                msg = "修改成功";
            }
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,msg);
        }catch(Exception e){

            logger.error("会员新增/修改中的保存方法信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    /**
     * 开始导入相关
     */
    @ApiOperation(value = "会员信息Excel导入", notes = "会员信息Excel导入 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/importMemberInfo",method= RequestMethod.POST)
    public Result<ResponseMemberExcelVO> importMemberInfo(HttpServletRequest request) throws Exception {
        Result<ResponseMemberExcelVO> result = new Result<ResponseMemberExcelVO>();
        ResponseMemberExcelVO responseMemberExcelVO = memberInfoService.excelImport(request);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, responseMemberExcelVO);
        return result;
    }

    @ApiOperation(value="会员发卡操作查看", notes = "会员发卡操作查看[未发卡状态的信息才有发卡的操作 && type = 1的情况不显示储值金额 && 发卡时间随着系统时间变化 前台显示] | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/selectIssuingCards", method = RequestMethod.GET)
    @ResponseBody
    public Result<MemberInfoSaveVO> selectIssuingCards(HttpServletRequest request,@ApiParam(value = "当前发卡对象的ID", required = true) @RequestParam Long id){
        Result<MemberInfoSaveVO> result = new Result<MemberInfoSaveVO>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            MemberInfoSaveVO memberInfoSaveVO = memberInfoService.selectIssuingCards(id,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,memberInfoSaveVO);
        }catch(Exception e){
            logger.error("会员发卡操作查看错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="会员发卡操作根据ID查询剩余信息", notes = "会员发卡操作根据ID查询剩余信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/selectIssuingCardsById", method = RequestMethod.GET)
    @ResponseBody
    public Result<MemberInfoSaveVO> selectIssuingCardsById(HttpServletRequest request,@ApiParam(value = "当前会员卡类型的ID", required = true) @RequestParam Long id){
        Result<MemberInfoSaveVO> result = new Result<MemberInfoSaveVO>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            MemberInfoSaveVO memberInfoSaveVO = memberInfoService.selectIssuingCardsById(id,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,memberInfoSaveVO);
        }catch(Exception e){
            logger.error("会员发卡操作查看错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="会员发卡操作中的保存", notes = "会员发卡操作中的保存 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveIssuingCards", method = RequestMethod.POST)
    @ApiImplicitParam(name = "memberInfoSaveVO", value = "需要保存的发卡 实体", required = true, dataType = "MemberInfoSaveVO")
    @ResponseBody
    public Result<String> saveIssuingCards(HttpServletRequest request,@RequestBody MemberInfoSaveVO memberInfoSaveVO){
        Result<String> result = new Result<String>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            memberInfoService.saveIssuingCards(memberInfoSaveVO,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"发卡成功");
        }catch(Exception e){

            logger.error("会员发卡操作中的保存错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="会员挂失操作", notes = "会员挂失操作 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/reportedLoss", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> reportedLoss(HttpServletRequest request, @ApiParam(value = "当前会员信息的ID", required = true) @RequestParam Long id, @ApiIgnore UserVO userVO){
        Result<String> result = new Result<String>();
        try{
            memberInfoService.reportedLoss(id,userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(Exception e){
            logger.error("会员挂失操作错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="会员注销操作", notes = "会员注销操作 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/writeOff", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> writeOff(HttpServletRequest request,@ApiParam(value = "当前会员信息的ID", required = true) @RequestParam Long id,@ApiIgnore UserVO userVO){
        Result<String> result = new Result<String>();
        try{
            memberInfoService.writeOff(id,userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(Exception e){
            logger.error("会员注销操作错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="会员解挂操作", notes = "会员解挂操作 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/solutionsHanging", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> solutionsHanging(HttpServletRequest request,@ApiParam(value = "当前会员信息的ID", required = true) @RequestParam Long id,@ApiIgnore UserVO userVO){
        Result<String> result = new Result<String>();
        try{
            memberInfoService.solutionsHanging(id,userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        }catch(Exception e){
            logger.error("会员解挂操作错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="会员删除操作(只有在未发卡状态可以删除)", notes = "会员删除操作(只有在未发卡状态可以删除) | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "当前需要删除的会员信息ID", required = true, dataType = "Long", paramType="path")
    @ResponseBody
    public Result<String> delete(HttpServletRequest request,@PathVariable Long id){
        Result<String> result = new Result<String>();
        try{
            int status = memberInfoService.delete(id);
            if (status > 0){
                result.setBizCodeSuccessInfo(SysCode.SUCCESS,"删除成功");
            }else{
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"当前会员信息已经不存在");
            }

        }catch(Exception e){
            logger.error("会员删除操作错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "会员信息品导出(导出成功数据/导出失败数据)", notes = "会员信息品导出(导出成功数据/导出失败数据) | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/exportMemberSuccessAndFail/{type}/{key}",method= RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "1--导出成功数据；2--导出失败数据", required = true,paramType = "path"),
            @ApiImplicitParam(name = "key", value = "获取数据的key值", required = true,paramType = "path")
    })
    public void exportMemberSuccessAndFail(@PathVariable("type")Integer type,@PathVariable String key, HttpServletResponse response) throws Exception {
        type =  type == null ? 1 : type;
        String name = "成功的会员信息表单";
        if (type == 2) {
            name = "失败会员信息表单";
        }
        //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
        //输出Excel文件
        OutputStream output = response.getOutputStream();
        memberInfoService.exportGoods(output,key,type);
    }

    @ApiOperation(value = "导入成功数据", notes = "导入成功数据 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/importSuccessMember/{key}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "key", value = "获取数据的key值", required = true,paramType = "path")
    public Result importSuccessGoods(HttpSession session,
                                     @PathVariable("key")String key) throws Exception {
        Result<Object> result = new Result<Object>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            /*memberInfoService.saveAsync(userVO,key);*/
            memberInfoService.importSuccessMember(userVO,key);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS);
            /*result.setBizCodeSuccessInfo(SysCode.SUCCESS,"后台服务正在处理中，请稍后查询数据！");*/
            return result;
        }catch (BusinessException e){
            logger.error("导入成功数据:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }catch (Exception e){
            logger.error("导入成功数据:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }

    }

    /*@ApiOperation(value = "检测导入成功数据进度(返回整数不含%)", notes = "检测导入成功数据进度(返回整数不含%) | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/checkSchedule/{key}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "key", value = "获取数据的key值", required = true,paramType = "path")
    public Result checkSchedule(HttpSession session,@PathVariable("key")String key) throws Exception {
        Result<Double> result = new Result<Double>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            Double schedule = memberInfoService.checkSchedule(userVO,key);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,schedule);
            return result;
        }catch (BusinessException e){
            logger.error("检测导入成功数据进度(返回整数不含%):"+e.getMessage());
            result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
            return result;
        }catch (Exception e){
            logger.error("检测导入成功数据进度(返回整数不含%):"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }

    }*/

    @ApiOperation(value = "会员信息导出", notes = "会员信息导出 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/exportMemberInfo",method= RequestMethod.GET)
    public void Export(HttpServletRequest request, HttpServletResponse response,
                       @ApiParam(value = "会员卡号/手机号/姓名", required = false) @RequestParam(required=false) String param,
                       @ApiParam(value = "会员卡类型", required = false) @RequestParam(required=false) Long cardTypeId,
                       @ApiParam(value = "会员卡级别", required = false) @RequestParam(required=false) Long cardLevelId,
                       @ApiParam(value = "发卡起始时间", required = false) @RequestParam(required=false) Date startSendCardTime,
                       @ApiParam(value = "发卡终止时间", required = false) @RequestParam(required=false) Date endSendCardTime,
                       @ApiParam(value = "发卡门店类型(1-自营店 2-加盟店)", required = false) @RequestParam(required=false) Integer chainType,
                       @ApiParam(value = "发卡门店编码", required = false) @RequestParam(required=false) String sendCardStorerCode,
                       @ApiParam(value = "发卡门店名称", required = false) @RequestParam(required=false) String sendCardStorerName,
                       @ApiParam(value = "发卡人员名称", required = false) @RequestParam(required=false) String sendCardManName,
                       @ApiParam(value = "状态（如果是 全部就不传 0-正常；1-未发卡；2-挂失；3-注销）", required = false) @RequestParam(required=false) Integer status,
                       @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            String name = "会员信息";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<MemberInfoSaveVO> list = memberInfoService.exportMemberInfo(param,cardTypeId,cardLevelId,startSendCardTime,endSendCardTime,chainType,sendCardStorerCode,sendCardStorerName,sendCardManName,loginUser,status,sort);
            memberInfoService.exportExcel(output,list,loginUser);
        }catch(Exception e){
            logger.error("导出采购入库单信息错误:"+e.getMessage(),e);
        }
    }


    @ApiOperation(value = "根据会员卡ID获取会员信息", notes = "根据会员卡ID获取会员信息 | 开发者 蓝兴建 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getMemberInfoById", method = RequestMethod.GET)
    public Result<StoredAmountPageVO> getMemberInfoById(HttpServletRequest request,
                                             @ApiParam(value = "会员卡id", required = true) @RequestParam Long id) {
        Result<StoredAmountPageVO> result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            //通过id事实获取会员卡相关信息
            StoredAmountPageVO storedAmountPageVO = memberInfoService.selectCurrentStoredAmount(id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, storedAmountPageVO);
        } catch (Exception e) {
            logger.error("根据会员卡ID获取会员信息错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
}
