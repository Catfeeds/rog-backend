package com.rograndec.feijiayun.chain.business.finance.set.period.valid;

import com.rograndec.feijiayun.chain.business.auth.constant.SessionKey;
import com.rograndec.feijiayun.chain.business.finance.set.period.service.AccountingPeriodService;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.RequestAccountingPeriodDetailVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.RequestAccountingPeriodVO;
import com.rograndec.feijiayun.chain.common.constant.status.PeriodStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dongdong.zhang on 2018/01/04.
 */
public class AccountingPeriodCheckValidator implements ConstraintValidator<AccountingPeriodCheck, RequestAccountingPeriodVO> {
	
	@Autowired
    private AccountingPeriodService accountingPeriodService;
    private Logger logger = LoggerFactory.getLogger(AccountingPeriodCheckValidator.class);

    @Override
    public void initialize(AccountingPeriodCheck accountingPeriod) {
        //初始化，得到注解数据
    }

    @Override
    public boolean isValid(RequestAccountingPeriodVO value, ConstraintValidatorContext context) {


        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UserVO userVO = (UserVO) request.getSession().getAttribute(SessionKey.USER.getCode());

        if(value.getId() == null) {
        	//验证年份
        	Integer year = value.getYear();
        	Integer newYear = accountingPeriodService.getPeriodYear(userVO);
        	if(newYear != null) {
        		if(!newYear.equals(year)) throw new ValidationException("年份错误");
        	}
        }
        //验证状态
        Integer status = value.getStatus();

        List<RequestAccountingPeriodDetailVO> list = value.getDetailList();
        if(list == null || list.size()!=12) {
        	throw new ValidationException("月份数据不足");
        }
        
        for(int i=0;i<12;i++) {
        	if(i>0) {
        		RequestAccountingPeriodDetailVO oo = list.get(i-1);
        		RequestAccountingPeriodDetailVO vo = list.get(i);
        		if(PeriodStatus.CLOSE.getCode().equals(status)) {
        			if(!PeriodStatus.CLOSE.getCode().equals(vo.getStatus()))  new ValidationException("总单为已结帐"+(i+1)+"月份不能为已激活状态");
        		}
        		if(PeriodStatus.CLOSE.getCode().equals(vo.getStatus())) {
        			if(!PeriodStatus.CLOSE.getCode().equals(oo.getStatus()))  new ValidationException(i+"月份未结帐"+(i+1)+"月份不能为结账状态");
        		}
        		Date oldEndDate = oo.getEndDate();
        		Date startDate = vo.getStartDate();
        		Date endDate =  vo.getEndDate();
        		if(!DateUtils.before(startDate, oldEndDate)) throw new ValidationException((i+1)+"月份开始日期不能小于"+i+"月份结束日期");
        		
        		if(!DateUtils.before(endDate, startDate)) throw new ValidationException((i+1)+"月份开始日期不能大于"+(i+1)+"月份结束日期");
        		
        	}else {
        		RequestAccountingPeriodDetailVO vo = list.get(i);
        		Date startDate = vo.getStartDate();
        		String newStartDate = accountingPeriodService.getPeriodYearStartDate(userVO);
        		if(PeriodStatus.CLOSE.getCode().equals(status)) {
        			if(!PeriodStatus.CLOSE.getCode().equals(vo.getStatus()))  new ValidationException("总单为已结帐"+(i+1)+"月份不能为已激活状态");
        		}
        		if(newStartDate != null) {
        			Date newStartDate1 = DateUtils.StringToDate(newStartDate, DateUtils.FMT_DATE);
        			if(!DateUtils.before(startDate, newStartDate1)) throw new ValidationException("1月份开始日期不能小于"+newStartDate);
        		}
        		Date endDate =  vo.getEndDate();
        		if(!DateUtils.before(endDate, startDate)) throw new ValidationException("1月份开始日期不能大于1月份结束日期");
        	}
        }

        return true;
    }
}
