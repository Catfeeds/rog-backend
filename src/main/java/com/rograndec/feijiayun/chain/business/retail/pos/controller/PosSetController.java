package com.rograndec.feijiayun.chain.business.retail.pos.controller;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseBusinessService;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.retail.pos.service.PosSetService;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosSetVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * 
 * @ClassName: PosSetController   
 * @Description:  零售管理-POS管理-系统设置-Rest接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-18 17:31:49
 */
@Api(value="PosSetController",description = "零售管理-POS管理-系统设置")
@RestController
@RequestMapping("/retail/pos/posSet")
public class PosSetController {

	private static final Logger logger = LoggerFactory.getLogger(PosSetController.class);
	
	@Autowired
	private PosSetService posSetService;
     @Autowired
     private EnterpriseBusinessService enterpriseBusinessService;

	
	@ApiOperation(value = "查看系统设置", notes = "查看数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getPosSetData", method = RequestMethod.GET)
	public Result<PosSetVO> getPosSetData(HttpSession session) throws Exception {
		Result<PosSetVO> result = new Result<PosSetVO>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            Long enterpriseId = userVO.getEnterpriseId();
            EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(userVO.getEnterpriseId());
            if (!userVO.getChainType().equals(ChainType.Headquarters.getCode())
                    && 0 == enterpriseBus.getPosSet()){
                enterpriseId = userVO.getParentId();
            }
            PosSetVO posSetVO = posSetService.getPosSetData(enterpriseId);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, posSetVO);
        } catch (Exception e) {
            logger.error("查看系统设置数据失败:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
		
	@ApiOperation(value = "添加or修改系统设置", notes = "添加or修改数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/saveORupdate", method = RequestMethod.POST)
	public Result<String> save(HttpSession session,@Valid @RequestBody PosSetVO bean) {
		Result<String> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            posSetService.saveORupdate(bean, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"添加数据成功");
        } catch (Exception e) {
            logger.error("添加系统设置失败:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	/*@ApiOperation(value = "更新系统设置", notes = "更新数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBodyEntity
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<String> update(HttpSession session,@Valid @RequestBody PosSetVO bean) {
		Result<String> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            posSetService.saveORupdate(bean, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"更新数据成功");
        } catch (Exception e) {
            logger.error("更新系统设置失败:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}*/
	
	
	
}

