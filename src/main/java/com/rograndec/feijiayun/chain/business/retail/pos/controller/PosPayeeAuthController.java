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

import com.rograndec.feijiayun.chain.business.retail.pos.service.PosPayeeAuthService;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayeeAuthSaveOrupdateListVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayeeAuthSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayeeAuthVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * 
 * @ClassName: PosPayeeAuthController   
 * @Description:  零售管理-POS管理-款员权限-Rest接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:52:35
 */
@Api(value="PosPayeeAuthController",description = "零售管理-POS管理-款员权限")
@RestController
@RequestMapping("/retail/pos/payeeAuth")
public class PosPayeeAuthController {

	private static final Logger logger = LoggerFactory.getLogger(PosPayeeAuthController.class);
	
	@Autowired
	private PosPayeeAuthService posPayeeAuthService;
		

	@ApiOperation(value = "查看款员权限", notes = "查看数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getPayeeAuthData", method = RequestMethod.GET)
	public Result<List<PosPayeeAuthVO>> getPosPayeeAuthData(HttpSession session) throws Exception {
		Result<List<PosPayeeAuthVO>> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            List<PosPayeeAuthVO> list = posPayeeAuthService.getPosPayeeAuthData(userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        } catch (Exception e) {
            logger.error("查看款员权限数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
		
	@ApiOperation(value = "添加(id=null)or更新((id!=null)款员权限", notes = "添加or更新数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/saveOrupdate", method = RequestMethod.POST)
	public Result<String> save(HttpSession session,@Valid @RequestBody PosPayeeAuthSaveOrupdateListVO posPayeeAuth) {
		Result<String> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            List<PosPayeeAuthSaveOrupdateVO> listVO = posPayeeAuth.getListVO();
            if(null != listVO && listVO.size() > 0) {
            	for(PosPayeeAuthSaveOrupdateVO vo : listVO) {
            		if(null == vo.getId()) {
                    	posPayeeAuthService.save(vo,userVO);
                    }else {
                    	posPayeeAuthService.update(vo,userVO);
                    }
            	}
            	result.setBizCodeSuccessInfo(SysCode.SUCCESS,"操做权限成功");
            }else {
            	result.setBizCodeSuccessInfo(SysCode.SUCCESS,"数据不存在");
            }
        } catch (Exception e) {
            logger.error("添加款员权限数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	/*@ApiOperation(value = "更新款员权限", notes = "更新数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBodyEntity
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<String> update(HttpSession session,@Valid @RequestBody PosPayeeAuthSaveOrupdateVO posPayeeAuth) {
		Result<String> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            int upresult = posPayeeAuthService.update(posPayeeAuth,userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,upresult == 1 ? "更新成功" : "更新数据不存在");
        } catch (Exception e) {
            logger.error("更新款员权限数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}*/
	
	
}
