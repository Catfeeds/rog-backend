package com.rograndec.feijiayun.chain.business.basic.user.controller;

import com.rograndec.feijiayun.chain.business.basic.user.service.TrainPlanService;
import com.rograndec.feijiayun.chain.business.basic.user.vo.*;
import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import com.rograndec.feijiayun.chain.business.system.set.entity.Position;
import com.rograndec.feijiayun.chain.business.system.set.entity.Tree;
import com.rograndec.feijiayun.chain.business.system.set.service.OrganizationService;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@Api(value = "train_plan", description = "基础数据-员工管理-培训计划接口服务")
@RestController
@RequestMapping("train/plan")
@Validated
public class TrainPlanController {

    private static final Log logger = LogFactory.getLog(TrainPlanController.class);

    @Autowired
    TrainPlanService trainPlanService;
    @Autowired
    OrganizationService organizationService;

    @Autowired
    private CommonComponent commonComponent;

    @ApiOperation(value = "分页获取(待培训/已培训)列表信息", notes = "分页获取(待培训/已培训)列表信息 | 开发者 zeshi.sun | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getWaitTrainPlanPage", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<List<WaitTrainPlanPageVO>>> getWaitTrainPlanPage(HttpServletRequest request,
                                                                          @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                          @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                          @ApiParam(value = "查询类型 （0-待培训   1-已培训）", required = true) @RequestParam Long type,
                                                                          @ApiParam(value = "日期范围-开始时间", required = false) @RequestParam(required = false) Date startTime,
                                                                          @ApiParam(value = "日期范围-结束时间", required = false) @RequestParam(required = false) Date endTime,
                                                                          @ApiParam(value = "计划编号", required = false) @RequestParam(required = false) String code,
                                                                          @ApiParam(value = "计划人员", required = false) @RequestParam(required = false) String plannerName,
                                                                          @ApiParam(value = "计划年度", required = false) @RequestParam(required = false) Long planYear,
                                                                          @ApiParam(value = "计划标题", required = false) @RequestParam(required = false) String planTitle,
                                                                          @ApiParam(value = "培训类型", required = false) @RequestParam(required = false) Long trainType,
                                                                          @ApiParam(value = "培训内容", required = false) @RequestParam(required = false) Long trainContent,
                                                                          @ApiParam(value = "排序参数,就是列名", required = false) @RequestParam(required = false) String orderName,
                                                                          @ApiParam(value = "排序方式,ASC或者DESC", required = false) @RequestParam(required = false) String orderType
    ) {
        Result<Page<List<WaitTrainPlanPageVO>>> result = new Result<Page<List<WaitTrainPlanPageVO>>>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<WaitTrainPlanPageVO> distrReturnCheckVoList = trainPlanService
                    .getWaitTrainPlanPage(pageNo, pageSize, loginUser, page, startTime, endTime, code, plannerName, planYear,
                            planTitle, trainType, trainContent, type, orderName, orderType);
            page.setResult(distrReturnCheckVoList);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("分页获取(待培训/已培训)列表信息错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看待培训列表数据", notes = "查看待培训列表数据 | 开发者 zeshi.sun  | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCheckWaitTrainPlan", method = RequestMethod.GET)
    @ResponseBody
    public Result<CheckWaitTrainPlanVO> getCheckWaitTrainPlan(HttpSession session,
                                                                    @ApiParam(value = "培训计划单ID", required = true) @RequestParam Long id
    ) {
        Result<CheckWaitTrainPlanVO> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long enterpriseId = userVO.getEnterpriseId();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, trainPlanService.getCheckWaitTrainPlan(enterpriseId, id, userVO));
        } catch (Exception e) {
            logger.error("查看待培训列表数据错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "修改待培训单据", notes = "修改待培训单据 | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateWaitTrainPlan", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> updateWaitTrainPlan(HttpServletRequest request,
                                               @ApiParam(value = "修改配进验收单据", required = true) @RequestBody CheckWaitTrainPlanVO checkWaitTrainPlanVO) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            trainPlanService.updateWaitTrainPlan(loginUser, checkWaitTrainPlanVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改待培训单据成功");

        } catch (BusinessException e) {
            logger.error("修改待培训单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo("111112", e.getMessage());
        } catch (Exception e) {
            logger.error("修改待培训单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "删除待培训列表数据", notes = "删除待培训列表数据 | 开发者 zeshi.sun | 已联调")
    @RequestMapping(value="/getDeleteWaitTrainPlan/{id}",method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "id", value = "培训计划单ID", required = true, dataType = "Long", paramType="path")
    public Result getDeleteWaitTrainPlan(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id){

        Result result = new Result<>();
        try {
            HttpSession session = request.getSession(true);
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long enterpriseId = userVO.getEnterpriseId();
            trainPlanService.getDeleteWaitTrainPlan(enterpriseId, id, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "删除待培训列表数据成功");
        } catch (Exception e) {
            logger.error("删除待培训列表数据错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "点击培训按钮后待培训表头数据(也可用于已培训界面点击查看按钮后表头数据)", notes = "点击培训按钮后待培训表头数据(也可用于已培训界面点击查看按钮后表头数据) | 开发者 zeshi.sun  | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getClickWaitTrainPlanHead", method = RequestMethod.GET)
    @ResponseBody
    public Result<ClickWaitTrainPlanHeadVO> getClickWaitTrainPlanHead(HttpSession session,
                                                                      @ApiParam(value = "培训计划单ID", required = true) @RequestParam Long id
    ) {
        Result<ClickWaitTrainPlanHeadVO> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long enterpriseId = userVO.getEnterpriseId();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, trainPlanService.getClickWaitTrainPlanHead(enterpriseId, id, userVO));
        } catch (Exception e) {
            logger.error("点击培训按钮后待培训表头数据(也可用于已培训界面点击查看按钮后表头数据)错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "已培训界面点击查看按钮后明细数据", notes = "已培训界面点击查看按钮后明细数据 | 开发者 zeshi.sun  | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getClickTrainPlanDetail", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<ClickTrainPlanDetailVO>> getClickTrainPlanDetail(HttpSession session,
                                                                            @ApiParam(value = "培训计划单ID", required = true) @RequestParam Long id
    ) {
        Result<List<ClickTrainPlanDetailVO>> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long enterpriseId = userVO.getEnterpriseId();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, trainPlanService.getClickTrainPlanDetail(enterpriseId, id, userVO));
        } catch (Exception e) {
            logger.error("已培训界面点击查看按钮后明细数据错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "修改已培训单据", notes = "修改已培训单据 | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateTrainPlan", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> updateTrainPlan(HttpServletRequest request,
                                              @ApiParam(value = "修改配进验收单据", required = true) @RequestBody CheckTrainPlanVO checkTrainPlanVO) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            trainPlanService.updateTrainPlan(loginUser, checkTrainPlanVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改已培训单据成功");

        } catch (BusinessException e) {
            logger.error("修改已培训单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo("111112", e.getMessage());
        } catch (Exception e) {
            logger.error("修改已培训单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "点击培训按钮保存培训单据", notes = "点击培训按钮保存培训单据 | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveTrainPlan", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> saveTrainPlan(HttpServletRequest request,
                                          @ApiParam(value = "保存配进验收单据", required = true) @RequestBody CheckTrainPlanVO checkTrainPlanVO) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            trainPlanService.saveTrainPlan(loginUser, checkTrainPlanVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "点击培训按钮保存培训单据成功");

        } catch (BusinessException e) {
            logger.error("点击培训按钮保存培训单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo("111112", e.getMessage());
        } catch (Exception e) {
            logger.error("点击培训按钮保存培训单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "保存新增的计划培训单据", notes = "保存新增的计划培训单据 | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveTrainPlanHead", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> saveTrainPlanHead(HttpServletRequest request,
                                          @ApiParam(value = "保存新增的计划培训单据", required = true) @RequestBody CheckWaitTrainPlanVO checkWaitTrainPlanVO) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            trainPlanService.saveTrainPlanHead(loginUser, checkWaitTrainPlanVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存新增的计划培训单据成功");

        } catch (BusinessException e) {
            logger.error("保存新增的计划培训单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo("111112", e.getMessage());
        } catch (Exception e) {
            logger.error("保存新增的计划培训单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }


    @ApiOperation(value = "点击培训按钮后待培训明细数据", notes = "点击培训按钮后待培训明细数据 | 开发者 zeshi.sun  | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getClickWaitTrainPlanDetail", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<ClickWaitTrainPlanDetailVO>> getClickWaitTrainPlanDetail(HttpSession session,
                                                                            @ApiParam(value = "培训计划单ID", required = true) @RequestParam Long id
    ) {
        Result<List<ClickWaitTrainPlanDetailVO>> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long enterpriseId = userVO.getEnterpriseId();
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, trainPlanService.getClickWaitTrainPlanDetail(enterpriseId, id, userVO));
        } catch (Exception e) {
            logger.error("点击培训按钮后待培训明细数据错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "导出Excel", notes = "按照模版将已培训计划导出至Excel | 开发者 zeshi.sun | 已联调")
    @RequestMapping(value="/exportExcel/{id}",method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = "id", value = "当前需要查看的已培训计划ID", required = true, dataType = "Long", paramType="path")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            String name = loginUser.getEnterpriseName()+"已培训计划";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            ExportCheckTrainPlanVO  exportCheckTrainPlanVO  = trainPlanService.getCheckTrainPlanVoList(loginUser.getEnterpriseId(),id, loginUser);
            trainPlanService.exportExcel(output,exportCheckTrainPlanVO,loginUser);
        }catch(Exception e){
            logger.error("导出已培训计划错误:"+e.getMessage(),e);
        }
    }

    @ApiOperation(value = "分页获取组织结构信息", notes = "分页获取组织结构信息 | 开发者 zeshi.sun | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getEnterprisePage", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<List<EnterprisePageVO>>> getEnterprisePage(HttpServletRequest request,
                                                                        @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                        @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                        @ApiParam(value = "查询条件(组织机构编码/名称/检索码)", required = false) @RequestParam(required = false) String values

    ) {
        Result<Page<List<EnterprisePageVO>>> result = new Result<Page<List<EnterprisePageVO>>>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<EnterprisePageVO> distrReturnCheckVoList = trainPlanService
                    .getEnterprisePage(pageNo, pageSize, loginUser, page, values);
            page.setResult(distrReturnCheckVoList);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("分页获取组织结构信息错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="通过部门ID集合获取岗位信息(如果没部门ID显示全部岗位信息)", notes = "通过部门ID集合获取岗位信息(如果没部门ID显示全部岗位信息) | 开发者 zeshi.sun | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getPositionTree", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<Tree>> getPositionTree(HttpServletRequest request,
                                               @ApiParam(value = "部门ID集合(逗号分隔)", required = false) @RequestParam(required = false) String deptIds
    ){
        Result<List<Tree>> result = new Result<List<Tree>>();
        try{
            HttpSession session = request.getSession();
            UserVO user = (UserVO) session.getAttribute("user");
            if (user == null) {
                user = new UserVO();
            }
            List<Position> position = trainPlanService.getPositionTree(deptIds,user);
            List<Department> department = trainPlanService.getDeptTree(deptIds,user);
            List<Tree> departMentTree = commonComponent.structureTree(department, user);
            List<Tree> tree = organizationService.structurePositionTree(departMentTree,position, user);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, tree);
        }catch(Exception e){
            logger.error("通过部门ID集合获取岗位信息(如果没部门ID显示全部岗位信息)错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value="通过岗位ID集合获取参与人员信息数据(如果没岗位ID显示全部人员信息) ", notes = "通过岗位ID集合获取参与人员信息数据(如果没岗位ID显示全部人员信息) | 开发者 zeshi.sun | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getUserData", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<UserDataVO>> getUserData(HttpServletRequest request,
//
                                                @ApiParam(value = "部门数组", required = false) @RequestParam(required = false)  List<Long> ids,
                                                @ApiParam(value = "企业ID", required = false) @RequestParam(required = false) Long enterpriseId

    ) throws Exception {
        Result<List<UserDataVO>> result = new Result();

        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");

        List<UserDataVO> userDataVO = trainPlanService.getUserData(loginUser,ids, enterpriseId);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, userDataVO);
        return result;
    }
}
