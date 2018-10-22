package com.rograndec.feijiayun.chain.business.retail.pos.controller;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosPayeeMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.service.PosPayeeService;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayeeSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayeeVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

 /**
 * 
 * @ClassName: PosPayeeController   
 * @Description:  零售管理-POS管理-收款人员-Rest接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:52:09
 */
@Api(value="PosPayeeController",description = "零售管理-POS管理-收款人员")
@RestController
@RequestMapping("/retail/pos/payee")
public class PosPayeeController {

	private static final Logger logger = LoggerFactory.getLogger(PosPayeeController.class);
	
	@Autowired
	private PosPayeeService posPayeeService;
	
	@Autowired
	PosPayeeMapper posPayeeMapper;
		
	
	@ApiOperation(value = "查看收款人员", notes = "查看数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getPayeeData", method = RequestMethod.GET)
	public Result<List<PosPayeeVO>> getData(HttpSession session) throws Exception {
		Result<List<PosPayeeVO>> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            List<PosPayeeVO> list = posPayeeService.getPosPayeeData(userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        } catch (Exception e) {
            logger.error("查看收款人员数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
		
	@ApiOperation(value = "添加收款人员", notes = "添加数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<String> save(HttpSession session,@Valid @RequestBody PosPayeeSaveOrupdateVO posPayee) {
		Result<String> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            Map<String,Object> check = posPayeeMapper.getPayeeIdAndEnterpriseId(userVO.getEnterpriseId(), posPayee.getPayeeId());
            if(null != check && check.size() > 0) {
            	result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), "收款人员名称已存在！");
    			return result;
            }
            posPayeeService.save(posPayee,userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"添加成功");
        } catch (BusinessException e){
        	result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), e.getMessage());
            return result;
        } catch (Exception e) {
            logger.error("添加收款人员数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	@ApiOperation(value = "更新收款人员", notes = "更新数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<String> update(HttpSession session,@Valid @RequestBody PosPayeeSaveOrupdateVO posPayee) {
		Result<String> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            int upresult = posPayeeService.update(posPayee,userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,upresult == 1 ? "更新成功" : "更新数据不存在");
        } catch (Exception e) {
            logger.error("更新收款人员数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	@ApiOperation(value = "根据ID删除收款人员", notes = "删除数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Result<Object> remove(HttpSession session,@ApiParam(value = "根据id删除", required = true)@RequestParam(name = "id", required = true) String id) {
		Result<Object> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            int delresult = posPayeeService.delete(Long.valueOf(id),userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,delresult == 1 ? "删除成功" : "删除数据不存在");
        } catch (BusinessException e){
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch (Exception e) {
            logger.error("删除收款人员数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
}
