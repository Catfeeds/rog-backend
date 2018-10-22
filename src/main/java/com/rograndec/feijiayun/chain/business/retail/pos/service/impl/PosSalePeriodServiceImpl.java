package com.rograndec.feijiayun.chain.business.retail.pos.service.impl;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseBusinessService;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosSalePeriodMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosSalePeriod;
import com.rograndec.feijiayun.chain.business.retail.pos.service.PosSalePeriodService;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosSalePeriodSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosSalePeriodVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;


 /**
 * 
 * @ClassName: PosSalePeriodServiceImpl   
 * @Description:  零售管理-POS管理-销售时段-实现接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-22 13:13:50
 */
@Service
public class PosSalePeriodServiceImpl implements PosSalePeriodService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PosSalePeriodServiceImpl.class);
	
	@Autowired
	private PosSalePeriodMapper posSalePeriodMapper;
	 @Autowired
	 private EnterpriseBusinessService enterpriseBusinessService;

	@Override
	public List<PosSalePeriodVO> getPosSalePeriodData(UserVO userVO) throws Exception {
		Long enterpriseId = userVO.getEnterpriseId();
		EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(userVO.getEnterpriseId());
		if (!userVO.getChainType().equals(ChainType.Headquarters.getCode())){
			//门店自主控制读取自己数据
			if(enterpriseBus.getPosSet() == 1) {
				userVO.setParentId(-1L);
			} else {
				enterpriseId = userVO.getParentId();
			}
		}
		List<PosSalePeriodVO> list = posSalePeriodMapper.findByEnterpriseId(enterpriseId,userVO.getParentId());
		return list;
	}

	@Override
	public int save(PosSalePeriodSaveOrupdateVO posSalePeriod, UserVO userVO) throws Exception {
		PosSalePeriod copy = (PosSalePeriod)EntityUtils.reflectAddSetDefaultValue(new PosSalePeriod().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(posSalePeriod,copy);
		copy.setStatus(EnableStatus.ENABLE.getStatus()); // 启用
		return posSalePeriodMapper.insertSelective(copy);
	}

	@Override
	public int update(PosSalePeriodSaveOrupdateVO posSalePeriod,UserVO userVO) throws Exception {
		PosSalePeriod copy = (PosSalePeriod)EntityUtils.reflectUpdateSetDefaultValue(new PosSalePeriod().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(posSalePeriod,copy);
		return posSalePeriodMapper.updateByPrimaryKeySelective(copy);
	}

	@Override
	public int delete(Long id) throws Exception {
		return posSalePeriodMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Long findByCode(String code,Long enterpriseId) throws Exception {
		return posSalePeriodMapper.findByCode(code,enterpriseId);
	}
	
	public String checkTime(Long enterpriseId,String startTime,String endTime) {
		StringBuilder sb = new StringBuilder();
		if(StringUtils.isBlank(startTime) && StringUtils.isBlank(endTime)) {
			sb.append("开始时间、结束时间不能为空！");
			return sb.toString();
		} else {
			// 前台时间
			Date std = DateUtils.StringToDate(startTime, DateStyle.HH_MM);
			Date etd = DateUtils.StringToDate(endTime, DateStyle.HH_MM);
			Map<String, String> map = posSalePeriodMapper.findMaxTime(enterpriseId);
			if(null != map && map.size() > 0) {
				String maxStart = map.get("start_time");
				String maxEnd = map.get("end_time");
				Date sd = DateUtils.StringToDate(maxStart, DateStyle.HH_MM);
				Date ed = DateUtils.StringToDate(maxEnd, DateStyle.HH_MM);
				// 前台开始时间比已有时间小
				if(std.getTime() < sd.getTime()) {
					sb.append("开始时间不能小于已有的开始["+maxStart+"]时间");
					return sb.toString();
				}
				// 前台结束时间比已有结束时间小
				if (etd.getTime() < ed.getTime()) {
					sb.append("结束时间不能小于已有的结束["+maxEnd+"]时间");
					return sb.toString();
				}
			} 
			// 前台时间验证
			if (std.getTime() >= etd.getTime()) {
				sb.append("开始时间不能大于或等于结束时间");
				return sb.toString();
			}
		}
		return sb.toString();
	}
}
