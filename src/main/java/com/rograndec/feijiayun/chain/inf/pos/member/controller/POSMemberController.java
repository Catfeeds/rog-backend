package com.rograndec.feijiayun.chain.inf.pos.member.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.member.info.service.MemberInfoService;
import com.rograndec.feijiayun.chain.business.member.set.dao.MemberCardTypeMapper;
import com.rograndec.feijiayun.chain.business.member.set.entity.MemberCardType;
import com.rograndec.feijiayun.chain.common.NewSelectBean;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.inf.pos.member.service.POSMemberService;
import com.rograndec.feijiayun.chain.inf.pos.member.vo.POSSetMemberInfoVO;
import com.rograndec.feijiayun.chain.inf.pos.member.vo.SelectPOSMemberVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value="POSMemberController",description = "POS会员接口")
@RestController
@RequestMapping("/inf/pos/member")
public class POSMemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(POSMemberController.class);
	
	@Autowired
	private POSMemberService pOSMemberService;
	
	@Autowired
    private MemberInfoService memberInfoService;
	
	@Autowired
    private MemberCardTypeMapper memberCardTypeMapper;
	
	@ApiOperation(value = "获取会员", notes = "获取会员 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/getMemberData", method = RequestMethod.POST)
    public Result<List<SelectPOSMemberVO>> getMemberData(HttpSession session,@ApiParam(required = false, value = "检索条件:卡号/姓名/手机/QQ/微信")@RequestBody String param) {
		Result<List<SelectPOSMemberVO>> result = new Result<>();
        try {
        	if("{}".equals(param)) {
        		param = "";
        	}
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("posLoginUser");
            List<SelectPOSMemberVO> list = pOSMemberService.searchMember(param,userVO.getEnterpriseId(),userVO.getParentId());
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
		} catch (Exception e) {
			logger.error("POS获取会员数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
		}
        return result;
    }
	
	@ApiOperation(value="会员卡类型下拉框", notes = "会员卡类型下拉框 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getCardType", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<NewSelectBean>> getCardType(HttpServletRequest request){
        Result<List<NewSelectBean>> result = new Result<List<NewSelectBean>>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("posLoginUser");
            List<MemberCardType> cardTypeList = memberInfoService.getCardType(loginUser);
            List<NewSelectBean> list = new ArrayList<NewSelectBean>();
            for (MemberCardType s : cardTypeList){
                NewSelectBean bean = new NewSelectBean();
                bean.setId(s.getId());
                bean.setText(String.valueOf(s.getName()));
                list.add(bean);
            }
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        }catch(Exception e){
            logger.error("会员卡类型下拉框信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	
	@ApiOperation(value = "POS会员开卡", notes = "POS会员开卡 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    @RequestMapping(value = "/setMemberData", method = RequestMethod.POST)
    public Result<String> setMemberData(HttpSession session,@ApiParam(required = true, value = "开卡信息")@Valid @RequestBody POSSetMemberInfoVO madeCardInfoVO) {
		Result<String> result = new Result<String>();
        try{
            UserVO loginUser = (UserVO) session.getAttribute("posLoginUser");
            MemberCardType memberCardType = memberCardTypeMapper.selectByPrimaryKey(madeCardInfoVO.getCardTypeId());
            // 0-积分+储值 2-仅储值 验证密码
            if(memberCardType.getType() == 0 || memberCardType.getType() == 2) {
            	if (StringUtils.isBlank(madeCardInfoVO.getPassword())){
                    result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"密码不能为空!");
                    return result;
                }
                if (StringUtils.isBlank(madeCardInfoVO.getPasswordConfirm())){
                    result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"确认密码不能为空!");
                    return result;
                }
                if (madeCardInfoVO.getPassword().length() < 6 || madeCardInfoVO.getPassword().length() > 20){
                    result.setBizCodeFallInfo(SysCode.FAIL.getCode(),"密码必须在6-20位!");
                    return result;
                }
                if (!madeCardInfoVO.getPassword().equals(madeCardInfoVO.getPasswordConfirm())){
                    result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(),"密码与确认密码不一致");
                    return result;
                }
            }
            if (madeCardInfoVO.getCardCode() == null || "".equals(madeCardInfoVO.getCardCode())){
            	result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(),"制卡的初始卡号不能为空！");
                return result;
            }
            if (madeCardInfoVO.getCardCode().length() < 4){
            	result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(),"制卡的初始卡号不能小于四位！");
                return result;
            }
            String msg = pOSMemberService.saveMember(madeCardInfoVO, loginUser);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,msg);
        }catch(Exception e){
            logger.error("POS会员制卡保存错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	

}
