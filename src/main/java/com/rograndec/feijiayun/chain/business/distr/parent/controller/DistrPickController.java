package com.rograndec.feijiayun.chain.business.distr.parent.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrPickService;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrPickRequestVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrPickVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrSendOperationSlfVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrSendOperationVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrSendRequestVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrSendVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.pick.DistrSendViewVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.exception.InventoryBizException;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;



@Api(value = "distrPickController", description = "配货单-捡货", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/distr/pick")
public class DistrPickController {
	
	private static final Log logger = LogFactory.getLog(DistrPickController.class);
	
	@Autowired
	private DistrPickService distrPickService;
	
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="分页获取-待检货数据", notes = "分页获取-待检货数据 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getStayDistrPickPage", method = RequestMethod.POST)
	@ResponseBody
	public Result<Page<List<DistrSendVO>>> getStayDistrPickPage(HttpServletRequest request,
			@RequestBody DistrSendRequestVO vo
			){
		Result<Page<List<DistrSendVO>>> result = new Result<Page<List<DistrSendVO>>>();
        try{
        	if(vo == null){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
    			return result;
        	}
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	Page page = new Page(vo.getPageNo(), vo.getPageSize());
        	
        	distrPickService.getStayDistrPickPage(vo, page, loginUser);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
        	logger.error("分页获取-待检货数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="待检货数据-查看", notes = "待检货数据-查看 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getStayDistrPickView", method = RequestMethod.GET)
	@ResponseBody
	public Result<DistrSendViewVO> getStayDistrPickView(HttpServletRequest request,
			@ApiParam(value = "待拣货ID", required = true) @RequestParam Long id
			){
		Result<DistrSendViewVO> result = new Result<DistrSendViewVO>();
        try{
        	if(id == null){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
    			return result;
        	}
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	DistrSendViewVO vo = distrPickService.getStayDistrPickView(id, loginUser);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, vo);
        }catch(Exception e){
        	logger.error("获取待检货查看数据错误:"+e.getMessage(),e);
        	if(e instanceof InventoryBizException){
                result.setBizCodeFallInfo("111111",e.getMessage());
            } else {
                result.setBizCodeFallInfo(SysCode.FAIL);
            }
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="待检货数据-拣货", notes = "待检货数据-拣货 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getStayDistrPickOperation", method = RequestMethod.GET)
	@ResponseBody
	public Result<DistrSendOperationVO> getStayDistrPickOperation(HttpServletRequest request,
			@ApiParam(value = "待拣货ID", required = true) @RequestParam Long id
			){
		Result<DistrSendOperationVO> result = new Result<DistrSendOperationVO>();
        try{
        	if(id == null){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
    			return result;
        	}
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	DistrSendOperationVO vo = distrPickService.getStayDistrPickOperation(id, loginUser);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, vo);
        }catch(Exception e){
        	logger.error("获取待检货-拣货数据错误:"+e.getMessage(),e);
        	if(e instanceof InventoryBizException){
                result.setBizCodeFallInfo("111111",e.getMessage());
            } else {
                result.setBizCodeFallInfo(SysCode.FAIL);
            }
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="拣货-选择货位", notes = "拣货-选择货位 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getStayDistrPickShelf", method = RequestMethod.GET)
	@ResponseBody
	public Result<List<DistrSendOperationSlfVO>> getStayDistrPickShelf(HttpServletRequest request,
			@ApiParam(value = "配货单明细ID", required = true) @RequestParam Long sendDetailId
			){
		Result<List<DistrSendOperationSlfVO>> result = new Result<List<DistrSendOperationSlfVO>>();
        try{
        	if(sendDetailId == null){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
    			return result;
        	}
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	List<DistrSendOperationSlfVO> voList = distrPickService.getStayDistrPickShelf(sendDetailId, loginUser);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, voList);
        }catch(Exception e){
        	logger.error("获取拣货数据-选择货位明细错误:"+e.getMessage(),e);
        	if(e instanceof InventoryBizException){
                result.setBizCodeFallInfo("111111",e.getMessage());
            } else {
                result.setBizCodeFallInfo(SysCode.FAIL);
            }
			return result;
        }
		return result;
	}
	
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="保存检货数据", notes = "炼货-保存数据 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/saveDistrPick", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> saveDistrPick(HttpServletRequest request,
			@RequestBody DistrSendOperationVO vo
			){
		Result<String> result = new Result<String>();
        try{
        	if(vo == null){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
    			return result;
        	}
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	distrPickService.saveDistrPick(vo, loginUser);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, "");
        }catch(Exception e){
        	logger.error("拣货-保存拣货数据错误:"+e.getMessage(),e);
        	if(e instanceof InventoryBizException || e instanceof BusinessException){
                result.setBizCodeFallInfo("111111",e.getMessage());
            } else {
				result.setBizCodeFallInfo(SysCode.FAIL);
				return result;
            }	
        }
		return result;
	}
	
	
	@SuppressWarnings("rawtypes")
	@ApiOperation(value="分页获取-已检货数据", notes = "分页获取-已检货数据 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getAlreadyDistrPickPage", method = RequestMethod.POST)
	@ResponseBody
	public Result<Page<List<DistrPickVO>>> getAlreadyDistrPickPage(HttpServletRequest request,
			@RequestBody DistrPickRequestVO vo
			){
		Result<Page<List<DistrPickVO>>> result = new Result<Page<List<DistrPickVO>>>();
        try{
        	if(vo == null){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
    			return result;
        	}
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	Page page = new Page(vo.getPageNo(), vo.getPageSize());
        	
        	distrPickService.getAlreadyDistrPickPage(vo, page, loginUser);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
        	logger.error("分页获取-已检货数据错误:"+e.getMessage(),e);
			result.setBizCodeFallInfo(SysCode.FAIL);
			return result;
        }
		return result;
	}
	
	
	@ApiOperation(value="已检货数据-查看", notes = "已检货数据-查看 | 开发者:刘群 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value = "/getAlreadyDistrPickView", method = RequestMethod.GET)
	@ResponseBody
	public Result<DistrSendOperationVO> getAlreadyDistrPickView(HttpServletRequest request,
			@ApiParam(value = "已拣货ID", required = true) @RequestParam Long id
			){
		Result<DistrSendOperationVO> result = new Result<DistrSendOperationVO>();
        try{
        	if(id == null){
        		result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
    			return result;
        	}
        	HttpSession session = request.getSession(true);
        	UserVO loginUser = (UserVO) session.getAttribute("user");
        	
        	DistrSendOperationVO vo = distrPickService.getAlreadyDistrPickView(id, loginUser);
        	
        	result.setBizCodeSuccessInfo(SysCode.SUCCESS, vo);
        }catch(Exception e){
        	logger.error("获取已检货查看数据错误:"+e.getMessage(),e);
        	if(e instanceof InventoryBizException){
                result.setBizCodeFallInfo("111111",e.getMessage());
            } else {
                result.setBizCodeFallInfo(SysCode.FAIL);
            }
			return result;
        }
		return result;
	}
	
	@ApiOperation(value = "导出拣货单信息", notes = "导出拣货单信息信息 | 开发者 刘群 | 已完成 ")
    @RequestMapping(value="/ExportHasBeenInstorage/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "当前需要查看拣货单单据ID", required = true, dataType = "Long", paramType="path")
    public void ExportHasBeenInstorage(HttpServletRequest request, HttpServletResponse response,@PathVariable Long id){
        OutputStream output = null;
        try {
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            String name = "拣货单";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            DistrSendOperationVO vo = distrPickService.getAlreadyDistrPickView(id, loginUser);
            distrPickService.exportExcel(output,vo,loginUser);
        }catch(Exception e){
            logger.error("导出拣货单信息错误:"+e.getMessage(),e);
        }
    }
	

}
