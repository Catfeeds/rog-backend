package com.rograndec.feijiayun.chain.business.system.enterprise.service;

import java.util.List;

import com.rograndec.feijiayun.chain.business.system.enterprise.vo.SelectBeanVO;
import com.rograndec.feijiayun.chain.common.SelectBeanWithCode;


public interface LocationService {
	
	List<SelectBeanVO> selectProvinceLocation();

	List<SelectBeanVO> selectCityLocationByProvinceId(String provinceId);

	List<SelectBeanVO> selectAreaLocationByCityId(String cityId);
}
