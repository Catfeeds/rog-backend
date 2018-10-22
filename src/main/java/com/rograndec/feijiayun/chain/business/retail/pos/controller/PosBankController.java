package com.rograndec.feijiayun.chain.business.retail.pos.controller;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.rograndec.feijiayun.chain.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
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

import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosBankMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.service.PosBankService;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosBankSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosBankVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.string.ChineseString;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

 /**
 * 
 * @ClassName: PosBankController   
 * @Description:  零售管理-POS管理-开户银行-Rest接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:34:42
 */
@Api(value="PosBankController",description = "零售管理-POS管理-开户银行")
@RestController
@RequestMapping("/retail/pos/bank")
public class PosBankController {

	private static final Logger logger = LoggerFactory.getLogger(PosBankController.class);
	
	@Autowired
	private PosBankService posBankService;
	
	@Autowired
	private PosBankMapper posBankMapper;

	
	@ApiOperation(value = "查看开户银行", notes = "查看数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getBankData", method = RequestMethod.GET)
	public Result<List<PosBankVO>> getData(HttpSession session) throws Exception {
		Result<List<PosBankVO>> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            List<PosBankVO> list = posBankService.getBankData(userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        } catch (Exception e) {
            logger.error("查看开户银行数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
		
	@ApiOperation(value = "添加开户银行", notes = "添加数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<String> save(HttpSession session,@Valid @RequestBody PosBankSaveOrupdateVO bean) {
		Result<String> result = new Result<>();
		ChineseString.checkCode(bean.getCode());
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            // 验证编码
            if(StringUtils.isNoneBlank(bean.getCode())) {
            	Result<String> check = checkCode(bean.getCode(),userVO.getEnterpriseId(),result);
            	if(check != null) {
            		return result;
            	}
            }
            // 验证名称
            Long checkName = posBankMapper.findByName(bean.getName(), userVO.getEnterpriseId());
            if(0 != checkName.longValue()) {
            	result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), bean.getName()+"名称已存在！");
    			return result;
            }
            // 验证账号
            if(StringUtils.isNotBlank(bean.getAccount())) {
            	Long account = posBankMapper.findByAccount(bean.getAccount(), userVO.getEnterpriseId());
            	if(0 != account.longValue()) {
            		result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), bean.getAccount()+"账号已存在！");
        			return result;
                }
            }
            posBankService.save(bean, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"添加成功");
        } catch (Exception e) {
            logger.error("添加开户银行数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	@ApiOperation(value = "更新开户银行", notes = "更新数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<String> update(HttpSession session,@Valid @RequestBody PosBankSaveOrupdateVO posBank) {
		Result<String> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
        	// 验证名称
            Long checkName = posBankMapper.findUpdateByName(posBank.getName(),posBank.getId(),userVO.getEnterpriseId());
            if(1 < checkName.longValue()) {
            	result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), posBank.getName()+"名称已存在！");
    			return result;
            }
            // 验证账号
            if(StringUtils.isNotBlank(posBank.getAccount())) {
            	Long account = posBankMapper.findUpdateByAccount(posBank.getAccount(), posBank.getId(), userVO.getEnterpriseId());
            	if(1 < account.longValue()) {
            		result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), posBank.getAccount()+"账号已存在！");
        			return result;
                }
            }
            posBankService.update(posBank, userVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"更新成功");
        } catch (Exception e) {
            logger.error("更新开户银行数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
	@ApiOperation(value = "根据ID删除开户银行", notes = "删除数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public Result<Object> remove(HttpSession session,@ApiParam(value = "根据id删除", required = true)@RequestParam(name = "id", required = true) String id) {
		Result<Object> result = new Result<>();
		if(StringUtils.isBlank(id)) {
			result.setBizSetCodeFallInfo(SysCode.SYS_PARAM_ERROR, "id不能为空！");
            return result;
		}
        try {
            posBankService.delete(Long.valueOf(id));
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"删除成功");
        }catch (BusinessException e) {
			logger.error("删除零售管理-POS管理-开户银行数据失败:", e);
			result.setBizCodeFallInfo(SysCode.FAIL,e.getMessage());
			return result;
		} catch (Exception e) {
            logger.error("删除零售管理-POS管理-开户银行数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}

	// 编码验证
	private Result<String> checkCode(String code,Long enterpriseId, Result<String> result) throws Exception {
		Long check = posBankService.findByCode(code,enterpriseId);
		if (0 != check.longValue()) {
			result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), "编码已存在！");
			return result;
		}
		return null;
	}
	
}
