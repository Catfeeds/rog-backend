package com.rograndec.feijiayun.chain.business.report.quality.storage.controller;

import com.rograndec.feijiayun.chain.business.goods.info.service.GoodsService;
import com.rograndec.feijiayun.chain.business.goods.info.vo.ResponseStorageConditionVO;
import com.rograndec.feijiayun.chain.business.goods.set.service.SetCategoryService;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsCategoryVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.service.ShelfMoveReportService;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.*;
import com.rograndec.feijiayun.chain.business.report.quality.user.vo.UserReportExcelVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.business.system.set.vo.BusinessScopeVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.*;
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
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author lizhongyi
 *
 */
@Api(value = "report_quality_storage_shelfMove",description = "报表-质量管理-存储与养护-货位移动")
@RestController
@RequestMapping("report/quality/storage/shelfMove")
@Validated
public class ShelfMoveReportController {

    private static final Log logger = LogFactory.getLog(ShelfMoveReportController.class);

    @Autowired
    private ShelfMoveReportService shelfMoveReportService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private SetCategoryService setCategoryService;

    @ApiOperation(value="按条件搜索货位移动报表信息", notes = "按条件搜索货位移动报表信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getShelfMovePage", method = RequestMethod.POST)
    @ApiImplicitParam(name = "requestShelfMoveVO", value = "检索条件", required = true, dataType = "RequestShelfMoveVO")
    public Result<ShelfMovePageVO> getShelfMovePage(HttpServletRequest request, @RequestBody RequestShelfMoveVO requestShelfMoveVO){
        Result<ShelfMovePageVO> result = new Result<ShelfMovePageVO>();
        try{
            if(requestShelfMoveVO.getPageNo() <= 0 || requestShelfMoveVO.getPageSize() <= 0){
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(requestShelfMoveVO.getPageNo(), requestShelfMoveVO.getPageSize());
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            ShelfMovePageVO vo  = shelfMoveReportService.getShelfMovePage(page,requestShelfMoveVO,loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, vo);
        }catch(Exception e){
            logger.error("按条件搜索货位移动报表信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "根据品种类别查询经营范围---第一个参数不传就是查的所有的经营范围", notes = "根据品种分类查询经营范围 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getBusinessScope/{businessVariety}/{status}",method= RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "businessVariety", value = "品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）",paramType = "path"),
            @ApiImplicitParam(name = "status", value = "状态（0-禁用；1-启用；2-全部）", required = true,paramType = "path")
    })
    public Result<List<BusinessScopeVO>> getBusinessScope(HttpSession session, @PathVariable Integer businessVariety, @PathVariable Integer status){
        Result<List<BusinessScopeVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsService.getBusinessScopeVOList(businessVariety,status == 2 ? null : status,userVO.getEnterpriseId()));
        return result;
    }

    @ApiOperation(value = "获取商品设置-分类基本信息", notes = "获取商品设置-分类基本信息,不可维护 | 开发者 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getClassify", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<TreePOJO<GoodsCategoryVO>>> getClassify(HttpServletRequest request) {
        Result<List<TreePOJO<GoodsCategoryVO>>> result = new Result<>();
        try {

            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");

            List<TreePOJO<GoodsCategoryVO>> goodsCategorys = setCategoryService.getClassify(loginUser.getEnterpriseId(), false);
            if (goodsCategorys == null) {
                result.setBizCodeSuccessInfo(SysCode.FAIL, goodsCategorys);
                return result;
            }
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, goodsCategorys);
        } catch (Exception e) {
            logger.error("获取商品设置-分类基本信息错误:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "货位移动信息报表导出", notes = "货位移动信息报表导出 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/exportShelfMove",method = RequestMethod.POST,produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiImplicitParam(name = "requestShelfMoveVO", value = "检索条件", required = true, dataType = "RequestShelfMoveVO")
    public void exportMemberInfo(HttpServletResponse response, HttpServletRequest request,RequestShelfMoveVO requestShelfMoveVO) throws Exception {

        try {
            OutputStream output = null;
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            String name = "货位移动信息";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            ShelfMoveExcelPageVO shelfMoveExcelPageVO = shelfMoveReportService.getExcelForm(requestShelfMoveVO,loginUser);
            shelfMoveReportService.export(output,shelfMoveExcelPageVO,loginUser);
        }catch(Exception e){
            logger.error("员工信息报表导出:"+e.getMessage(),e);
        }

    }

}
