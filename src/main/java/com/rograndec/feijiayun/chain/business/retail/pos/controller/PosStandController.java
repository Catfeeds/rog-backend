package com.rograndec.feijiayun.chain.business.retail.pos.controller;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.retail.pos.service.PosStandService;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosStandSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosStandVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * 
 * @ClassName: PosStandController   
 * @Description:  零售管理-POS管理-款台-Rest接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:40:37
 */
@Api(value="PosStandController",description = "零售管理-POS管理-款台")
@RestController
@RequestMapping("/retail/pos/stand")
public class PosStandController {

	private static final Logger logger = LoggerFactory.getLogger(PosStandController.class);
	
	@Autowired
	private PosStandService posStandService;
		
	
	@ApiOperation(value = "查看款台", notes = "查看数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getStandData", method = RequestMethod.GET)
	public Result<List<PosStandVO>> getData(HttpSession session) throws Exception {
		Result<List<PosStandVO>> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            List<PosStandVO> dataVO = posStandService.getStandData(userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, dataVO);
        } catch (Exception e) {
            logger.error("查看系统设置数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
		
	@ApiOperation(value = "添加款台", notes = "添加数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<String> save(HttpSession session,@Valid @RequestBody PosStandSaveOrupdateVO bean) {
		Result<String> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            posStandService.save(bean, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"添加成功");
        } catch (Exception e) {
            logger.error("添加款台数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	@ApiOperation(value = "更新款台", notes = "更新数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<String> update(HttpSession session,@Valid @RequestBody PosStandSaveOrupdateVO posStand) {
		Result<String> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            posStandService.update(posStand, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"更新成功");
        } catch (Exception e) {
            logger.error("更新款台数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}

	
}
