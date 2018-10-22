package com.rograndec.feijiayun.chain.business.quality.file.controller;

import com.rograndec.feijiayun.chain.business.quality.file.service.QualityManageSystemFileService;
import com.rograndec.feijiayun.chain.business.quality.file.vo.GetQualityManageSystemFileVO;
import com.rograndec.feijiayun.chain.business.quality.file.vo.QualityManageSystemFilePageVO;
import com.rograndec.feijiayun.chain.business.quality.file.vo.SaveQualityManageSystemFileVO;
import com.rograndec.feijiayun.chain.business.quality.file.vo.UpdateQualityManageSystemFileVO;
import com.rograndec.feijiayun.chain.business.quality.system.vo.GetQualityManageSystemVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Api(value = "QualityManageSystemFile", description = "质量管理-质量管理体系文件接口")
@RestController
@RequestMapping("quality/file/qualityManageSystemFile")
public class QualityManageSystemFileController {

    private static final Log logger = LogFactory.getLog(QualityManageSystemFileController.class);

    @Autowired
    QualityManageSystemFileService qualityManageSystemFileService;

    @ApiOperation(value = "保存质量管理体系文件单据", notes = "保存质量管理体系文件单据 | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/saveQualityManageSystemFile", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> saveQualityManageSystemFile(HttpServletRequest request,
                                                  @ApiParam(value = "保存质量管理体系文件单据", required = true) @RequestBody SaveQualityManageSystemFileVO saveQualityManageSystemFileVO) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            qualityManageSystemFileService.saveQualityManageSystemFile(loginUser, saveQualityManageSystemFileVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存质量管理体系文件单据成功");

        }  catch (BusinessException e) {
            logger.error("保存质量管理体系文件单据错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("保存质量管理体系文件单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "分页获取质量管理体系文件列表信息", notes = "分页获取质量管理体系文件列表信息 | 开发者 zeshi.sun | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getQualityManageSystemFilePage", method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<List<QualityManageSystemFilePageVO>>> getQualityManageSystemFilePage(HttpServletRequest request,
                                                                                    @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                                    @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                                    @ApiParam(value = "查询类型 （0-质量管理制度；1-部门职责；2-岗位职责；3-操作规程；4-档案；5-报告；6-记录和凭证；7-全部）", required = true) @RequestParam String type,
                                                                                    @ApiParam(value = "查询状态 （0-生效；1-失效；2-废止；3-撤销；4-替换；5-销毁；6-全部）", required = true) @RequestParam String status,
                                                                                    @ApiParam(value = "查询条件", required = false) @RequestParam(required = false) String key,
                                                                                    @ApiParam(value = "排序参数,就是列名", required = false) @RequestParam(required = false) String orderName,
                                                                                    @ApiParam(value = "排序方式,ASC或者DESC", required = false) @RequestParam(required = false) String orderType
    ) {
        Result<Page<List<QualityManageSystemFilePageVO>>> result = new Result<Page<List<QualityManageSystemFilePageVO>>>();
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
            List<QualityManageSystemFilePageVO> distrReturnCheckVoList = qualityManageSystemFileService
                    .getQualityManageSystemFilePage(pageNo, pageSize, enterpriseId, page, type, status, key, orderName, orderType);
            page.setResult(distrReturnCheckVoList);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        } catch (Exception e) {
            logger.error("分页获取质量管理体系文件列表信息错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "查看质量管理体系文件数据", notes = "查看质量管理体系文件数据 | 开发者 zeshi.sun  | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getQualityManageSystemFile", method = RequestMethod.GET)
    @ResponseBody
    public Result<GetQualityManageSystemFileVO> getQualityManageSystemFile(HttpSession session,
                                                                           @ApiParam(value = "质量管理体系文件单据ID", required = true) @RequestParam Long id
    ) {
        Result<GetQualityManageSystemFileVO> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            int chainType = userVO.getChainType();
            Long enterpriseId = userVO.getEnterpriseId();
            if(chainType != 0){
                enterpriseId = userVO.getParentId();
            }
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, qualityManageSystemFileService.getQualityManageSystemFile(enterpriseId, id, userVO));
        } catch (BusinessException e) {
            logger.error("查看质量管理体系文件数据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS, e.getMessage());
        } catch (Exception e) {
            logger.error("查看质量管理体系文件数据错误:" + e.getMessage(), e);
            result.setBizCodeSuccessInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "修改质量管理体系文件单据", notes = "修改质量管理体系文件单据 | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateQualityManageSystemFile", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> updateQualityManageSystemFile(HttpServletRequest request,
                                                    @ApiParam(value = "修改质量管理体系文件单据", required = true) @RequestBody GetQualityManageSystemFileVO getQualityManageSystemFileVO) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            qualityManageSystemFileService.updateQualityManageSystemFile(loginUser, getQualityManageSystemFileVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改质量管理体系文件单据成功");

        } catch (BusinessException e) {
            logger.error("修改质量管理体系文件单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("修改质量管理体系文件单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "删除质量管理体系文件列表数据", notes = "删除质量管理体系文件列表数据 | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/deleteQualityManageSystemFile", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> deleteQualityManageSystemFile(HttpServletRequest request,
                                                    @ApiParam(value = "质量管理体系文件列表ID", required = true) @RequestParam Long id) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            qualityManageSystemFileService.deleteQualityManageSystemFile(loginUser.getEnterpriseId(), id, loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "删除质量管理体系文件列表数据成功");

        } catch (BusinessException e) {
            logger.error("删除质量管理体系文件列表数据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("删除质量管理体系文件列表数据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "撤销/替换/销毁 操作接口", notes = "撤销/替换/销毁 操作接口 | 开发者 zeshi.sun| 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/updateData", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> updateData(HttpServletRequest request,
                                     @ApiParam(value = "修改质量管理体系文件单据", required = true) @RequestBody UpdateQualityManageSystemFileVO updateQualityManageSystemFileVO) {
        Result<String> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            qualityManageSystemFileService.updateData(loginUser, updateQualityManageSystemFileVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改单据成功");

        } catch (BusinessException e) {
            logger.error("修改单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("修改单据错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

}
