package com.rograndec.feijiayun.chain.business.finance.set.period.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.finance.set.period.dao.AccountingPeriodDetailMapper;
import com.rograndec.feijiayun.chain.business.finance.set.period.dao.AccountingPeriodMapper;
import com.rograndec.feijiayun.chain.business.finance.set.period.entity.AccountingPeriod;
import com.rograndec.feijiayun.chain.business.finance.set.period.entity.AccountingPeriodDetail;
import com.rograndec.feijiayun.chain.business.finance.set.period.service.AccountingPeriodService;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.AccountingFreshVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.AccountingPeriodDetailVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.AccountingPeriodFreshVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.AccountingPeriodVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.FinalSettleVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.RequestAccountingPeriodDetailVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.RequestAccountingPeriodVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.RequestFinalSettleVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.ResponseFinalSettleVO;
import com.rograndec.feijiayun.chain.business.finance.set.period.vo.YearAndMonthVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.status.PeriodStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.finance.PeriodDateUtils;


@Service
public class AccountingPeriodServiceImpl implements AccountingPeriodService {
	
	@Autowired
	AccountingPeriodMapper accountingPeriodMapper;
	@Autowired
	AccountingPeriodDetailMapper accountingPeriodDetailMapper;
	@Autowired
	CommonComponent commonComponent;
	@Autowired
	private UserMapper userMapper;

	@Override
	public Integer getPeriodYear(UserVO userVO) {
		Integer year = accountingPeriodMapper.getPeriodYear(userVO.getEnterpriseId());
		if(year !=null ) year++;
		return year;
	}

	@Override
	public String getPeriodYearStartDate(UserVO userVO) {
		Date date = accountingPeriodMapper.getPeriodYearStartDate(userVO.getEnterpriseId());
		String strDate = null;
		if(date != null) {
			Calendar c = Calendar.getInstance();  
			c.setTime(date);  
			c.add(Calendar.DAY_OF_MONTH, 1);// +1天 
			date = c.getTime();
			strDate = DateUtils.DateToString(date,DateUtils.FMT_DATE);
		}
		return strDate;
	}

	@Override
	public YearAndMonthVO getPeriodYearAndMonth(UserVO userVO) {
		YearAndMonthVO yearAndMonthVO = new YearAndMonthVO();
		Integer year = accountingPeriodMapper.getPeriodYear(userVO.getEnterpriseId());
		if(year !=null ) {
			year++;
			yearAndMonthVO.setYear(year+"");
		}else {
			yearAndMonthVO.setYear(null);
		}
		Date date = accountingPeriodMapper.getPeriodYearStartDate(userVO.getEnterpriseId());
		String strDate = null;
		if(date != null) {
			Calendar c = Calendar.getInstance();  
			c.setTime(date);  
			c.add(Calendar.DAY_OF_MONTH, 1);// +1天 
			date = c.getTime();
			strDate = DateUtils.DateToString(date,DateUtils.FMT_DATE);
		}
		yearAndMonthVO.setStartDate(strDate);
		return yearAndMonthVO;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addPeriod(UserVO userVO, RequestAccountingPeriodVO requestAccountingPeriodVO) {
		if(ChainType.Selfoperatedshop.getCode() == userVO.getChainType())  throw new BusinessException("总部控制，自营店不允许新增");
		validParams(requestAccountingPeriodVO, userVO);
		AccountingPeriod accountingPeriod = new AccountingPeriod();
		Integer year = requestAccountingPeriodVO.getYear();
		accountingPeriod.setYear(year);
		//总单状态
		Integer status = requestAccountingPeriodVO.getStatus();
		accountingPeriod.setStatus(status);
		accountingPeriod.setRemark(requestAccountingPeriodVO.getRemark());
		setBaseInfo(accountingPeriod, userVO);
		//添加总单
		accountingPeriodMapper.insert(accountingPeriod);
		List<AccountingPeriodDetail> paramList = new ArrayList<>();
		List<RequestAccountingPeriodDetailVO> list = requestAccountingPeriodVO.getDetailList();
		Integer month = 1;
		int n = 0;
		for(RequestAccountingPeriodDetailVO vo:list) {
			n++;
			if(n == 12 && PeriodStatus.CLOSE.getCode().equals(vo.getStatus())) {
				throw new BusinessException("至少有一个未结账");
			}
			AccountingPeriodDetail accountingPeriodDetail = new AccountingPeriodDetail();
			BeanUtils.copyProperties(accountingPeriod, accountingPeriodDetail);
			//总单若是已结账则细单为已结账
			if(PeriodStatus.CLOSE.getCode().equals(status)) vo.setStatus(status);
			//如果添加的是已结账状态则默认当前登陆人为结账人员
			if(PeriodStatus.CLOSE.getCode().equals(vo.getStatus())) {
				accountingPeriodDetail.setAccountManId(userVO.getUserId());
				accountingPeriodDetail.setAccountManName(userVO.getUserName());
				accountingPeriodDetail.setAccountManCode(userVO.getUserCode());
				accountingPeriodDetail.setAccountDate(new Date());
			}
			accountingPeriodDetail.setRemark(null);
			accountingPeriodDetail.setPeriodId(accountingPeriod.getId());
			accountingPeriodDetail.setStartDate(vo.getStartDate());
			accountingPeriodDetail.setEndDate(vo.getEndDate());
			//设置月份
			accountingPeriodDetail.setMonth(month++);
			accountingPeriodDetail.setId(null);
			accountingPeriodDetail.setStatus(vo.getStatus());
			//设置年份
			accountingPeriodDetail.setYear(year);
			paramList.add(accountingPeriodDetail);
		}
		for(AccountingPeriodDetail detail:paramList) {
			accountingPeriodDetailMapper.insert(detail);
		}
		
	}
	
	private void setBaseInfo(AccountingPeriod accountingPeriod, UserVO userVO) {
		accountingPeriod.setEnterpriseId(userVO.getEnterpriseId());
		accountingPeriod.setParentId(userVO.getParentId());
		Date date = new Date();
		accountingPeriod.setCreaterId(userVO.getUserId());
		accountingPeriod.setCreaterName(userVO.getUserName());
		accountingPeriod.setCreaterCode(userVO.getUserCode());
		accountingPeriod.setCreateTime(date);
		accountingPeriod.setUpdateTime(date);
		accountingPeriod.setModifierId(userVO.getUserId());
		accountingPeriod.setModifierName(userVO.getUserName());
		accountingPeriod.setModifierCode(userVO.getUserCode());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updatePeriod(UserVO userVO, RequestAccountingPeriodVO requestAccountingPeriodVO) {
		if(ChainType.Selfoperatedshop.getCode() == userVO.getChainType())  throw new BusinessException("总部控制，自营店不允许修改");
		List<Long> list =  new ArrayList<>();
		if(requestAccountingPeriodVO.getId() == null) throw new BusinessException("会计年度修改时必填id");
		List<RequestAccountingPeriodDetailVO> reqList = requestAccountingPeriodVO.getDetailList();
		for(RequestAccountingPeriodDetailVO v:reqList) {
			if(v.getId() == null) throw new BusinessException("会计年度修改时详情单必填id");
		}
		list.add(requestAccountingPeriodVO.getId());
		List<AccountingPeriodVO> resList = accountingPeriodMapper.getPeriodList(list);
		AccountingPeriodVO accountingPeriodVO = null;
		if(!CollectionUtils.isEmpty(resList)) { 
			accountingPeriodVO = resList.get(0);
		}else {
			throw new BusinessException("会计年度修改数据不存在");
		}
		List<AccountingPeriodDetailVO> detailList = accountingPeriodVO.getDetailList();
		
		//验证参数
		validParams(requestAccountingPeriodVO, userVO);
		
		//验证开始时间
		if(!equalsDate(detailList.get(0).getStartDate(), reqList.get(0).getStartDate())) throw new BusinessException("当前信息已发生修改，请重新查看");
		 
		AccountingPeriod accountingPeriod = new AccountingPeriod();
		accountingPeriod.setId(requestAccountingPeriodVO.getId());
		accountingPeriod.setRemark(requestAccountingPeriodVO.getRemark());
		accountingPeriod.setUpdateTime(new Date());
		accountingPeriod.setModifierId(userVO.getUserId());
		accountingPeriod.setModifierName(userVO.getUserName());
		accountingPeriod.setModifierCode(userVO.getUserCode());
		//更改主单
		accountingPeriodMapper.updateByPrimaryKeySelective(accountingPeriod);
		for(int i= 0;i<12;i++) {
			AccountingPeriodDetailVO oldValue = detailList.get(i);
			RequestAccountingPeriodDetailVO newValue = reqList.get(i);
			//如果是未结账的可修改
			if(oldValue.getStatus().equals(PeriodStatus.OPEN.getCode())) {
				//如果上个月为已结
				if(i > 0 && oldValue.getStatus().equals(PeriodStatus.CLOSE.getCode())) {
					//判断开始日期是否正确
					if(!equalsDate(returnDateAddOne(oldValue.getEndDate()), newValue.getStartDate())) new BusinessException((i+1)+"月开始日期不正确");
				}
				AccountingPeriodDetail accountingPeriodDetail = new AccountingPeriodDetail();
				accountingPeriodDetail.setId(newValue.getId());
				accountingPeriodDetail.setStartDate(newValue.getStartDate());
				accountingPeriodDetail.setEndDate(newValue.getEndDate());
				accountingPeriodDetail.setUpdateTime(accountingPeriod.getUpdateTime());
				accountingPeriodDetail.setModifierId(accountingPeriod.getModifierId());
				accountingPeriodDetail.setModifierName(accountingPeriod.getModifierName());
				accountingPeriodDetail.setModifierCode(accountingPeriod.getModifierCode());
				accountingPeriodDetailMapper.updateByPrimaryKeySelective(accountingPeriodDetail);
				//更改下一年度会计期间的开始日期
				if(i == 11) {
					List<Long> id =  new ArrayList<>();
					Integer year = accountingPeriodVO.getYear()+1;
					Long pId = accountingPeriodMapper.getPeriodId(year, userVO.getEnterpriseId());
					if(pId == null) return;
					/*id.add(pId);
					List<AccountingPeriodVO> accountingPeriodVOs = accountingPeriodMapper.getPeriodList(id);
					if(!CollectionUtils.isEmpty(accountingPeriodVOs)) { 
						AccountingPeriodVO accounting = accountingPeriodVOs.get(0);
						List<AccountingPeriodDetailVO> dtl = accounting.getDetailList();
						AccountingPeriodDetailVO accountingPeriodDetailVO = dtl.get(0);
						AccountingPeriodDetail accountingPeriodDtl = new AccountingPeriodDetail();
						accountingPeriodDtl.setId(accountingPeriodDetailVO.getId());
						accountingPeriodDtl.setStartDate(returnDateAddOne(accountingPeriodDetail.getEndDate()));
						accountingPeriodDtl.setUpdateTime(accountingPeriod.getUpdateTime());
						accountingPeriodDtl.setModifierId(accountingPeriod.getModifierId());
						accountingPeriodDtl.setModifierName(accountingPeriod.getModifierName());
						accountingPeriodDtl.setModifierCode(accountingPeriod.getModifierCode());
						accountingPeriodDetailMapper.updateByPrimaryKeySelective(accountingPeriodDtl);
						
					}*/
					updateRelatedPeriod(year, accountingPeriodDetail.getEndDate(), userVO, accountingPeriod.getUpdateTime());
				}
			}
		}
	}
	
	/**
	 * 更新修改年度之后的年度
	 * @param year			修改年度
	 * @param endDate		修改年度的12月结束日期
	 * @param userVO
	 */
	private void updateRelatedPeriod(Integer year, Date endDate, UserVO userVO, Date updateDate) {
		List<Long> id =  new ArrayList<>();
		Long pId = accountingPeriodMapper.getPeriodId(year, userVO.getEnterpriseId());
		if(pId == null) return;
		id.add(pId);
		List<AccountingPeriodVO> accountingPeriodVOs = accountingPeriodMapper.getPeriodList(id);
		AccountingPeriodVO accounting = accountingPeriodVOs.get(0);
		List<AccountingPeriodDetailVO> dtls = accounting.getDetailList();
		AccountingFreshVO accountingFreshVO = new AccountingFreshVO();
		accountingFreshVO.setYear(year);
		List<AccountingPeriodFreshVO> detailList = new ArrayList<>();
		for(AccountingPeriodDetailVO dtl:dtls) {
			AccountingPeriodFreshVO accountingPeriodFreshVO = new AccountingPeriodFreshVO();
			accountingPeriodFreshVO.setId(dtl.getId());
			accountingPeriodFreshVO.setStartDate(dtl.getStartDate());
			accountingPeriodFreshVO.setEndDate(dtl.getEndDate());
			detailList.add(accountingPeriodFreshVO);
		}
		AccountingPeriodFreshVO f = detailList.get(0);
		f.setBegin(1);
		f.setStartDate(returnDateAddOne(endDate));
		f.setEndDate(PeriodDateUtils.getFixDayOfMonth(year, 0, PeriodDateUtils.getDayOfMonth(endDate)));
		accountingFreshVO.setDetailList(detailList);
		getPeriodDetailForFresh(userVO, accountingFreshVO);
		for(AccountingPeriodFreshVO vo : detailList) {
			AccountingPeriodDetail accountingPeriodDtl = new AccountingPeriodDetail();
			accountingPeriodDtl.setId(vo.getId());
			accountingPeriodDtl.setStartDate(vo.getStartDate());
			accountingPeriodDtl.setEndDate(vo.getEndDate());
			accountingPeriodDtl.setUpdateTime(updateDate);
			accountingPeriodDtl.setModifierId(userVO.getUserId());
			accountingPeriodDtl.setModifierName(userVO.getUserName());
			accountingPeriodDtl.setModifierCode(userVO.getUserCode());
			accountingPeriodDetailMapper.updateByPrimaryKeySelective(accountingPeriodDtl);
		}
		
		year++;
		endDate = detailList.get(11).getEndDate();
		updateRelatedPeriod(year, endDate, userVO,updateDate);
	}

	@Override
	public void getPeriodPage(UserVO userVO, Page<List<AccountingPeriodVO>> page) {
		Map<String,Object> map = new HashMap<>();
		
		if(ChainType.Headquarters.getCode() != userVO.getChainType()) {
			//若是加盟店查看自己
			if(ChainType.Division.getCode() == userVO.getChainType()) {
				map.put("enterpriseId", userVO.getEnterpriseId());
			}else {//否则为总部
				map.put("enterpriseId", userVO.getParentId());
				Integer totalRecord = accountingPeriodMapper.getTotalPeriodPage(map);
				page.setTotalRecord(totalRecord);
				if(totalRecord != null && totalRecord != 0) {
					map.put("start", page.getStart());
					map.put("pageSize", page.getPageSize());
					List<Long> list =  accountingPeriodMapper.getPeriodPage(map);
					if(list != null && !list.isEmpty()) {
						List<AccountingPeriodVO> resList = accountingPeriodMapper.getPeriodList(list);
						List<AccountingPeriodVO> finalList = new ArrayList<>();
						for(Long pId : list) {
							for(AccountingPeriodVO vo : resList) {
								if(pId.equals(vo.getId())) {
									finalList.add(vo);
									break;
								}
							}
						}
						page.setResult(finalList);
						return;
					}
				}else {
					page.setResult(new ArrayList<>());
					return;
				}
			}
		}else {
			map.put("enterpriseId", userVO.getEnterpriseId());
		}
		Integer totalRecord = accountingPeriodMapper.getTotalPeriodPage(map);
		page.setTotalRecord(totalRecord);
		if(totalRecord != null && totalRecord != 0) {
			map.put("start", page.getStart());
			map.put("pageSize", page.getPageSize());
			List<Long> list =  accountingPeriodMapper.getPeriodPage(map);
			if(list != null && !list.isEmpty()) {
				List<AccountingPeriodVO> resList = accountingPeriodMapper.getPeriodList(list);
				List<AccountingPeriodVO> finalList = new ArrayList<>();
				for(AccountingPeriodVO vo:resList) {
					List<AccountingPeriodDetailVO> detailList = vo.getDetailList();
					//一月份未结账则可随意更改
					if(detailList.get(0).getStatus().equals(PeriodStatus.OPEN.getCode())) {
						vo.setUpdateFlag(true);
						vo.setDeleteFlag(true);
						vo.setRemarkFlag(true);
					}
					//十二月份的已结账则不可编辑
					if(detailList.get(11).getStatus().equals(PeriodStatus.CLOSE.getCode())) {
						vo.setUpdateFlag(false);
						vo.setDeleteFlag(false);
						vo.setRemarkFlag(false);
					}else {
						vo.setRemarkFlag(true);
					}
				}
				for(Long pId : list) {
					for(AccountingPeriodVO vo : resList) {
						if(pId.equals(vo.getId())) {
							finalList.add(vo);
							break;
						}
					}
				}
				page.setResult(finalList);
			}
		}else {
			page.setResult(new ArrayList<>());
		}
		
	}
	
	@Override
	public AccountingPeriodVO getPeriodDetail(UserVO userVO, Long id) {
		List<Long> list =  new ArrayList<>();
		list.add(id);
		List<AccountingPeriodVO> resList = accountingPeriodMapper.getPeriodList(list);
		AccountingPeriodVO accountingPeriodVO = null;
		if(!CollectionUtils.isEmpty(resList)) { 
			accountingPeriodVO = resList.get(0);
		}else {
			return null;
		}
		List<AccountingPeriodDetailVO> detailList = accountingPeriodVO.getDetailList();
		accountingPeriodVO.setStartDate(detailList.get(0).getStartDate());
		
		//总部控制的
		if(ChainType.Selfoperatedshop.getCode() == userVO.getChainType()) {
			return accountingPeriodVO;
		}		
		
		//一月份未结账则可随意更改
		if(detailList.get(0).getStatus().equals(PeriodStatus.OPEN.getCode())) {
			accountingPeriodVO.setUpdateFlag(true);
			accountingPeriodVO.setDeleteFlag(true);
			accountingPeriodVO.setRemarkFlag(true);
		}
		//十二月份的已结账则不可编辑
		if(detailList.get(11).getStatus().equals(PeriodStatus.CLOSE.getCode())) {
			accountingPeriodVO.setUpdateFlag(false);
			accountingPeriodVO.setDeleteFlag(false);
			accountingPeriodVO.setRemarkFlag(false);
		}else {
			accountingPeriodVO.setRemarkFlag(true);
		}
		for(AccountingPeriodDetailVO vo:detailList) {
			if(vo.getStatus().equals(PeriodStatus.OPEN.getCode())) {
				vo.setUpdateFlag(true);
			}
		}
		return accountingPeriodVO;
	}

	@Override
	public void removePeriod(UserVO userVO, Long id) {
		if(ChainType.Selfoperatedshop.getCode() == userVO.getChainType()) throw new BusinessException("总部控制，门店不允许删除");
		List <Long> list = new ArrayList<>();
		list.add(id);
		List<AccountingPeriodVO> resList = accountingPeriodMapper.getPeriodList(list);
		Long newId = accountingPeriodMapper.getNearestPeriodId(userVO.getEnterpriseId());
		if(!newId.equals(id))  throw new BusinessException("只能倒序删除");
		AccountingPeriodVO accountingPeriodVO = null;
		if(!CollectionUtils.isEmpty(resList)) { 
			accountingPeriodVO = resList.get(0);
		}else {
			throw new BusinessException("已删除");
		}
		List<AccountingPeriodDetailVO> detailList = accountingPeriodVO.getDetailList();
		if(accountingPeriodVO.getStatus().equals(PeriodStatus.CLOSE.getCode())) throw new BusinessException("已结账，不允许删除");
		for(AccountingPeriodDetailVO vo:detailList) {
			if(vo.getStatus().equals(PeriodStatus.CLOSE.getCode())) throw new BusinessException("已结账，不允许删除");
		}
		accountingPeriodMapper.deleteByPrimaryKey(id);
		
		accountingPeriodDetailMapper.deleteByPeriodId(id);
	}
	
	private void validParams(RequestAccountingPeriodVO value,UserVO userVO){
		Integer year = null;
		Integer newYear = getPeriodYear(userVO);
		year = value.getYear();
		//如果是新增
		if(value.getId() == null) {
        	//验证年份
        	if(newYear != null) {
        		if(!newYear.equals(year)) throw new BusinessException("年份错误");
        	}
        }else {
        	AccountingPeriod accountingPeriod = accountingPeriodMapper.selectByPrimaryKey(value.getId());
        	year = accountingPeriod.getYear();
        }
        //验证状态
        Integer status = value.getStatus();
        if(PeriodStatus.CLOSE.getCode().equals(status)) throw new BusinessException("总单为已结帐");

       List<RequestAccountingPeriodDetailVO> list = value.getDetailList();
        if(list == null || list.size()!=12) {
        	throw new BusinessException("月份数据不足");
        }
        
        for(int i=0;i<12;i++) {
        	if(i>0) {
        		RequestAccountingPeriodDetailVO prevo = list.get(i-1);
        		RequestAccountingPeriodDetailVO vo = list.get(i);
        		if(PeriodStatus.CLOSE.getCode().equals(status)) {
        			if(!PeriodStatus.CLOSE.getCode().equals(vo.getStatus())) throw new BusinessException("总单为已结帐"+(i+1)+"月份不能为已激活状态");
        		}
        		if(PeriodStatus.CLOSE.getCode().equals(vo.getStatus())) {
        			if(!PeriodStatus.CLOSE.getCode().equals(prevo.getStatus()))throw  new BusinessException(i+"月份未结帐"+(i+1)+"月份不能为结账状态");
        		}
        		Date oldEndDate = prevo.getEndDate();
        		oldEndDate = returnDateAddOne(oldEndDate);//oldEndDate加一天
        		Date startDate = vo.getStartDate();
        		Date endDate =  vo.getEndDate();
        		if(!equalsDate(startDate, oldEndDate)) throw new BusinessException((i+1)+"月份开始日期只能为"+oldEndDate);
        		
        		if(DateUtils.before(endDate, startDate)) throw new BusinessException((i+1)+"月份开始日期不能大于"+(i+1)+"月份结束日期");
        		//验证是否在本月内
        		PeriodDateUtils.validBetweenThisMonth(endDate, year, i);
        		/*//如果不是最后一天，则不能大于27
    		 	if(PeriodDateUtils.validLastDayOfMonth(endDate, year, i)) {
    		 		if(PeriodDateUtils.getDayOfMonth(endDate) > 27 ) throw new BusinessException((i+1)+"月份开始日期不能大于"+(i+1)+"月份27号");
    		 	}*/
        		
        	}else {
        		RequestAccountingPeriodDetailVO vo = list.get(i);
        		Date startDate = vo.getStartDate();
        		String newStartDate = null;
        		if(value.getId() == null) {
        			newStartDate = getPeriodYearStartDate(userVO);
        			if(PeriodStatus.CLOSE.getCode().equals(status)) {
        				if(!PeriodStatus.CLOSE.getCode().equals(vo.getStatus()))  new BusinessException("总单为已结帐"+(i+1)+"月份不能为已激活状态");
        			}
        			if(newStartDate != null) {
        				Date newStartDate1 = DateUtils.StringToDate(newStartDate, DateUtils.FMT_DATE);
        				if(!equalsDate(startDate, newStartDate1)) throw new BusinessException("1月份开始日期只能为"+newStartDate);
        			}
                }
        		Date endDate =  vo.getEndDate();
        		if(!DateUtils.before(startDate, endDate)) throw new BusinessException("1月份开始日期不能大于1月份结束日期");
        		
        		//验证是否在本月内
        		PeriodDateUtils.validBetweenThisMonth(endDate, year, i);
       		 	/*//如果不是最后一天，则不能大于27
       		 	if(PeriodDateUtils.validLastDayOfMonth(endDate, year, i)) {
       		 		if(PeriodDateUtils.getDayOfMonth(endDate) > 27 ) throw new BusinessException("1月份开始日期不能大于1月份27号");
       		 	}*/
        	}
        }
	}
	
	private boolean equalsDate(Date a,Date b) {
		Calendar c = Calendar.getInstance();
		c.setTime(a);
		c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    c.set(Calendar.MILLISECOND, 0);
	    Calendar d = Calendar.getInstance();
		d.setTime(b);
		d.set(Calendar.HOUR_OF_DAY, 0);
	    d.set(Calendar.MINUTE, 0);
	    d.set(Calendar.SECOND, 0);
	    d.set(Calendar.MILLISECOND, 0);
	    return c.getTimeInMillis() == d.getTimeInMillis();
	}
   private Date returnDateAddOne(Date a) {
	   if(a != null) {
			Calendar c = Calendar.getInstance();  
			c.setTime(a);  
			c.add(Calendar.DAY_OF_MONTH, 1);// +1天 
			a = c.getTime();
			return a;
		}
	   return null;
   }

	@Override
	public FinalSettleVO getFinalSettlePeriod(UserVO userVO) {
		
		return accountingPeriodMapper.getFinalSettlePeriod(userVO.getEnterpriseId());
	}

	@Override
	public void addFinalSettle(UserVO userVO, RequestFinalSettleVO requestFinalSettleVO) {
		//总部控制的
		if(ChainType.Selfoperatedshop.getCode() == userVO.getChainType()) throw new BusinessException("总部控制，门店不允许添加");
			
		FinalSettleVO finalSettleVO = accountingPeriodMapper.getFinalSettlePeriod(userVO.getEnterpriseId());
		if(!requestFinalSettleVO.getId().equals(finalSettleVO.getId())) throw new BusinessException("已结账");
		AccountingPeriodDetail accountingPeriodDetail = new AccountingPeriodDetail();
		accountingPeriodDetail.setId(finalSettleVO.getId());
		accountingPeriodDetail.setUpdateTime(new Date());
		accountingPeriodDetail.setModifierId(userVO.getUserId());
		accountingPeriodDetail.setModifierName(userVO.getUserCode());
		accountingPeriodDetail.setModifierCode(userVO.getUserName());
		
		/*ManageConfig manage = manageConfigMapper.selectByCurrentUser(userVO);//判断质量流程是否开启
        Boolean falg= manage.getBusinessControl()==0?false:true;//等于0的时候是关闭
        if(falg) {//质量流程开启则为当前登陆人员
        	accountingPeriodDetail.setAccountManId(userVO.getUserId());
    		accountingPeriodDetail.setAccountManCode(userVO.getUserCode());
    		accountingPeriodDetail.setAccountManName(userVO.getUserName());
    		accountingPeriodDetail.setAccountDate(new Date());
        }else {*/
        	User user=userMapper.selectByPrimaryKey(requestFinalSettleVO.getAccountManId());
        	if(user==null){
        		throw new BusinessException("找不到该人员，请确认人员ID"+requestFinalSettleVO.getAccountManId()+"是否正确");
        	}
        	accountingPeriodDetail.setAccountManId(user.getId());
    		accountingPeriodDetail.setAccountManCode(user.getCode());
    		accountingPeriodDetail.setAccountManName(user.getName());
    		accountingPeriodDetail.setStatus(PeriodStatus.CLOSE.getCode());
    		accountingPeriodDetail.setRemark(requestFinalSettleVO.getRemark());
    		Date accountDate = requestFinalSettleVO.getAccountDate();
    		if(!PeriodDateUtils.betweenDate(finalSettleVO.getStartDate(), finalSettleVO.getEndDate(), accountDate)) throw new BusinessException("时间必须在会计期间内");
    		accountingPeriodDetail.setAccountDate(accountDate);
       /* }*/
        accountingPeriodDetailMapper.updateByPrimaryKeySelective(accountingPeriodDetail);
        //如果是十二月则更新主单状态
        if(finalSettleVO.getMonth().equals(12)) {
        	AccountingPeriod accountingPeriod = new AccountingPeriod();
        	accountingPeriod.setId(finalSettleVO.getPeriodId());
        	accountingPeriod.setStatus(PeriodStatus.CLOSE.getCode());
    		accountingPeriod.setUpdateTime(new Date());
    		accountingPeriod.setModifierId(userVO.getUserId());
    		accountingPeriod.setModifierName(userVO.getUserName());
    		accountingPeriod.setModifierCode(userVO.getUserCode());
    		//更改主单
    		accountingPeriodMapper.updateByPrimaryKeySelective(accountingPeriod);
        }
	}
	
	@Override
	public List<ResponseFinalSettleVO> getFinalSettlePage(UserVO userVO,Page<List<ResponseFinalSettleVO>> page) {
		Map<String,Object> map = new HashMap<>();
		map.put("start", page.getStart());
		map.put("pageSize", page.getPageSize());
		if(ChainType.Selfoperatedshop.getCode() == userVO.getChainType()) {
			map.put("enterpriseId", userVO.getParentId());
		}else {
			map.put("enterpriseId", userVO.getEnterpriseId());
		}
		Integer totalRecord = accountingPeriodMapper.getFinalSettleTotalRecord(map);
		page.setTotalRecord(totalRecord);
		if(totalRecord != null && totalRecord != 0) {
			page.setResult(accountingPeriodMapper.getFinalSettlePage(map));
		}else {
			page.setResult(new ArrayList<>());
		}
		return null;
	}

	@Override
	public List<User> getFinalSettleUser(UserVO userVO) {
		return userMapper.findUserByEnterpriseId(userVO.getEnterpriseId());
	}

	@Override
	public void getPeriodDetailForFresh(UserVO userVO, AccountingFreshVO accountingFreshVO) {
		int year = accountingFreshVO.getYear();
		List<AccountingPeriodFreshVO> detailList = accountingFreshVO.getDetailList();
		boolean beignFlag = false;
		for(int i = 0;i<12;i++) {
			AccountingPeriodFreshVO svo = detailList.get(i);
			if(i == 0) PeriodDateUtils.validBetweenThisMonth(svo.getEndDate(), year, i);
			Integer begin = svo.getBegin();
			if(begin != null && begin == 1) beignFlag = true;
			//如果是修改的起始位置
			if(beignFlag) {
				//如果是一月份
				if(i == 0) {
					svo.setBegin(0);
					//验证一月份的开始日期是否在上一年度的12-02号与这一年度的01-30号之间
					PeriodDateUtils.validFirstMonthStartDate(accountingFreshVO.getYear(), svo.getStartDate());
					//如果一月份起始日期为本月第一天且结束日期为月底，默认结束日期为月底
					if(PeriodDateUtils.validFirstDayOfMonth(svo.getStartDate(), year, i) && PeriodDateUtils.validLastDayOfMonth(svo.getEndDate(), year, i)) {
						
						for(AccountingPeriodFreshVO vo:detailList) {
							vo.setStartDate(PeriodDateUtils.getFirstDayOfMonth(year, i));
							vo.setEndDate(PeriodDateUtils.getLastDayOfMonth(year, i));
							i++;
						}
						break;
					}else {
						int day1 = PeriodDateUtils.getDayOfMonth(svo.getEndDate());
						int day2 = PeriodDateUtils.getDayOfMonth(PeriodDateUtils.getLastDayOfMonth(year, i+1));
						//如果为2月的最后一天
						if(day1 >= day2) {
							//跳过一二月，设置一下月份
							AccountingPeriodFreshVO vo2 = detailList.get(i+1);
							vo2.setStartDate(PeriodDateUtils.getAfterDay(detailList.get(0).getEndDate()));
							vo2.setEndDate(PeriodDateUtils.getLastDayOfMonth(year, i+1));
							for(int n = i+2;n<12;n++) {
								AccountingPeriodFreshVO vo = detailList.get(n);
								vo.setStartDate(PeriodDateUtils.getFirstDayOfMonth(year, n));
								vo.setEndDate(PeriodDateUtils.getLastDayOfMonth(year, n));
							}
							break;
						}else {
							//跳过一月，设置一下月份
							for(int n = i+1;n<12;n++) {
								AccountingPeriodFreshVO vo = detailList.get(n);
								vo.setStartDate(PeriodDateUtils.getAfterDay(detailList.get(n-1).getEndDate()));
								vo.setEndDate(PeriodDateUtils.getFixDayOfMonth(year, n, day1));
							}
							break;
						}
					}
				}else if(i == 11) {//如果是十二月份跳过
					svo.setBegin(0);
					break;
				}else if(i == 1) {//二月
					svo.setBegin(0);
					//如过结束日期为月底则均为月底
					if(PeriodDateUtils.validLastDayOfMonth(svo.getEndDate(), year, i)) {
						//跳过本月，设置一下月份
						for(int n = i+1;n<12;n++) {
							AccountingPeriodFreshVO vo = detailList.get(n);
							vo.setStartDate(PeriodDateUtils.getFirstDayOfMonth(year, n));
							vo.setEndDate(PeriodDateUtils.getLastDayOfMonth(year, n));
						}
						break;
					}else {
						int day = PeriodDateUtils.getDayOfMonth(svo.getEndDate());
						//跳过本月，设置一下月份
						for(int n = i+1;n<12;n++) {
							AccountingPeriodFreshVO vo = detailList.get(n);
							vo.setStartDate(PeriodDateUtils.getAfterDay(detailList.get(n-1).getEndDate()));
							vo.setEndDate(PeriodDateUtils.getFixDayOfMonth(year, n, day));
						}
						break;
					}
				}else {//其他月份
					svo.setBegin(0);
					//如过结束日期为月第则均为月底
					if(PeriodDateUtils.validLastDayOfMonth(svo.getEndDate(), year, i)) {
						//跳过本月，设置一下月份
						for(int n = i+1;n<12;n++) {
							AccountingPeriodFreshVO vo = detailList.get(n);
							vo.setStartDate(PeriodDateUtils.getFirstDayOfMonth(year, n));
							vo.setEndDate(PeriodDateUtils.getLastDayOfMonth(year, n));
						}
						break;
					}else {
						int day = PeriodDateUtils.getDayOfMonth(svo.getEndDate());
						Date startDate = null;
						Date endDate = null;
						boolean lastFlag = false;
						//跳过本月，设置一下月份
						for(int n = i+1;n<12;n++) {
							AccountingPeriodFreshVO vo = detailList.get(n);
							if(!lastFlag) {
								startDate = PeriodDateUtils.getAfterDay(detailList.get(n-1).getEndDate());
								endDate = PeriodDateUtils.getFixDayOfMonth(year, n, day);
								//验证是否是当月的最后一天
								if(PeriodDateUtils.validLastDayOfMonth(endDate, year, n)) {
									lastFlag = true;
								}
								vo.setStartDate(startDate);
								vo.setEndDate(endDate);
							}else {
								vo.setStartDate(PeriodDateUtils.getFirstDayOfMonth(year, n));
								vo.setEndDate(PeriodDateUtils.getLastDayOfMonth(year, n));
							}
						}
						break;
					}
				}
			}
		}
		
	}
	
}
