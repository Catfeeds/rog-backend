package com.rograndec.feijiayun.chain.business.quality.system.controller;

import com.rograndec.feijiayun.chain.business.basic.user.vo.CheckWaitTrainPlanVO;
import com.rograndec.feijiayun.chain.business.basic.user.vo.WaitTrainPlanPageVO;
import com.rograndec.feijiayun.chain.business.purchase.check.vo.SaveCheckVO;
import com.rograndec.feijiayun.chain.business.quality.system.service.QualityManageSystemService;
import com.rograndec.feijiayun.chain.business.quality.system.vo.GetQualityManageSystemVO;
import com.rograndec.feijiayun.chain.business.quality.system.vo.QualityManageSystemPageVO;
import com.rograndec.feijiayun.chain.business.quality.system.vo.SaveQualityManageSystemVO;
import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier.SupplierFileReportDetailVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
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
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Api(value = "QualityManageSystem", description = "质量管理-质量管理体系服务接口")
@RestController
@RequestMapping("quality/system/qualityManageSystem")
public class QualityManageSystemController {

    private static final Log logger = LogFactory.getLog(QualityManageSystemController.class);

    @Autowired
    QualityManageSystemService qualityManageSystemService;

    @ApiOperation(value = "保存质量管理体系单据", notes = "保存质量管理体系单据 | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveQualityManageSystem", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> saveQualityManageSystem(HttpServletRequest request,
                                    @ApiParam(value = "保存质量管理体系单据", required = true) @RequestBody SaveQualityManageSystemVO saveQualityManageSystemVO) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            qualityManageSystemService.saveQualityManageSystem(loginUser, saveQualityManageSystemVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存质量管理体系单据成功");

        }  catch (BusinessException e) {
            logger.error("保存质量管理体系单据错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("保存质量管理体系单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "分页获取质量管理体系列表信息", notes = "分页获取质量管理体系列表信息 | 开发者 zeshi.sun | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getQualityManageSystemPage", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<List<QualityManageSystemPageVO>>> getQualityManageSystemPage(HttpServletRequest request,
                                                                              @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                              @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                              @ApiParam(value = "查询类型 （0-质量策划；1-质量控制；2-质量保证；3-质量改进；4-质量风险；5-全部）", required = true) @RequestParam String type,
                                                                              @ApiParam(value = "查询状态 （0-生效；1-失效；2-废止；3-全部）", required = true) @RequestParam String status,
                                                                              @ApiParam(value = "查询条件", required = false) @RequestParam(required = false) String key,
                                                                              @ApiParam(value = "排序参数,就是列名", required = false) @RequestParam(required = false) String orderName,
                                                                              @ApiParam(value = "排序方式,ASC或者DESC", required = false) @RequestParam(required = false) String orderType
    ) {
        Result<Page<List<QualityManageSystemPageVO>>> result = new Result<Page<List<QualityManageSystemPageVO>>>();
        try {
            if (pageNo <= 0 || pageSize <= 0) {
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            int chainType = loginUser.getChainType();
            Long enterpriseId = loginUser.getEnterpriseId();
            if(chainType != 0){
                enterpriseId = loginUser.getParentId();
            }
            List<QualityManageSystemPageVO> distrReturnCheckVoList = qualityManageSystemService
                    .getQualityManageSystemPage(pageNo, pageSize, enterpriseId, page, type, status, key, orderName, orderType);
            page.setResult(distrReturnCheckVoList);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("分页获取质量管理体系列表信息错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看质量管理体系数据", notes = "查看质量管理体系数据 | 开发者 zeshi.sun  | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getQualityManageSystem", method = RequestMethod.GET)
    @ResponseBody
    public Result<GetQualityManageSystemVO> getCheckWaitTrainPlan(HttpSession session,
                                                              @ApiParam(value = "质量管理体系单据ID", required = true) @RequestParam Long id
    ) {
        Result<GetQualityManageSystemVO> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            int chainType = userVO.getChainType();
            Long enterpriseId = userVO.getEnterpriseId();
            if(chainType != 0){
                enterpriseId = userVO.getParentId();
            }
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, qualityManageSystemService.getQualityManageSystem(enterpriseId, id, userVO));
        } catch (Exception e) {
            logger.error("查看质量管理体系数据错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "修改质量管理体系单据", notes = "修改质量管理体系单据 | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateQualityManageSystem", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> updateQualityManageSystem(HttpServletRequest request,
                                              @ApiParam(value = "修改质量管理体系单据", required = true) @RequestBody GetQualityManageSystemVO getQualityManageSystemVO) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            qualityManageSystemService.updateQualityManageSystem(loginUser, getQualityManageSystemVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改质量管理体系单据成功");

        } catch (BusinessException e) {
            logger.error("修改质量管理体系单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("修改质量管理体系单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "删除质量管理体系列表数据", notes = "删除质量管理体系列表数据 | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value="/deleteQualityManageSystem/{id}",method= RequestMethod.POST)
    @ApiImplicitParam(name = "id", value = "列表ID", required = true, paramType = "path")
    public Result<String> deleteQualityManageSystem(HttpSession session, @PathVariable("id") Long id){

        Result<String> result = new Result<>();
        try {

            UserVO userVO = (UserVO) session.getAttribute("user");

            qualityManageSystemService.deleteQualityManageSystem(userVO.getEnterpriseId(), id, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "删除质量管理体系列表数据成功");

        } catch (BusinessException e) {
            logger.error("删除质量管理体系列表数据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("删除质量管理体系列表数据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }


}
