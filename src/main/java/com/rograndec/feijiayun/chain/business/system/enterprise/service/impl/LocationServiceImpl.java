package com.rograndec.feijiayun.chain.business.system.enterprise.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.common.SelectBean;
import com.rograndec.feijiayun.chain.common.SelectBeanWithCode;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.LocationMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Location;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.LocationService;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.SelectBeanVO;
@Service
public class LocationServiceImpl implements LocationService{

	@Autowired
	LocationMapper locationMapper;
	
	@Override
	public List<SelectBeanVO> selectProvinceLocation() {
		List<Location> list = locationMapper.selectProvinceLocation();
		return locationToSelectBeanWithCode(list);
	}

	private List<SelectBean> locationToSelectBean(List<Location> list) {
		List<SelectBean> selectList = new ArrayList<SelectBean>();
		if(list != null){
			for (Location location : list) {
				SelectBean selectBean = new SelectBean();
				selectBean.setId(String.valueOf(location.getId()));
				selectBean.setText(location.getName());
				selectList.add(selectBean);
			}
		}
		return selectList;
	}
	
	private List<SelectBeanVO> locationToSelectBeanWithCode(List<Location> list) {
		List<SelectBeanVO> selectList = new ArrayList<SelectBeanVO>();
		if(list != null){
			for (Location location : list) {
				SelectBeanVO selectBean = new SelectBeanVO();
				selectBean.setId(location.getId().longValue());
				selectBean.setText(location.getName());
				selectList.add(selectBean);
			}
		}
		return selectList;
	}

	@Override
	public List<SelectBeanVO> selectCityLocationByProvinceId(String provinceId) {
		List<Location> list = locationMapper.selectCityLocationByProvinceId(provinceId);
		return locationToSelectBeanWithCode(list);
	}

	@Override
	public List<SelectBeanVO> selectAreaLocationByCityId(String cityId) {
		List<Location> list = locationMapper.selectAreaLocationByCityId(cityId);
		return locationToSelectBeanWithCode(list);
	}

}
