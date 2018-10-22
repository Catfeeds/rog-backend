package com.rograndec.feijiayun.chain.business.basic.user.service;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.business.basic.user.vo.HealthCheckUserVO;
import com.rograndec.feijiayun.chain.business.basic.user.vo.HealthCheckVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;

public interface HealthCheckService {
	
    /**
     * 获取详情
     * */
    HealthCheckVO getById(Long id);
    
    /**
     * 获取列表
     * */
    void getList(Page page,Map map);
    
    /**
     * 获取用户信息列表
     * */
    List<HealthCheckUserVO> getUserMessage(Long enterpriseId) throws Exception;

	/**
	 * 保存
	 * */
	void save(UserVO userVO,HealthCheckVO healthCheckVO) throws Exception,BusinessException;
	/**
	 * 修改
	 * */
	void update(UserVO userVO,HealthCheckVO healthCheckVO) throws Exception,BusinessException;
	
	void deleteByCheckId(Long id);
	
	void exportExcel(OutputStream output, Long id, UserVO loginUser);
}
