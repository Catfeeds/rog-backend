package com.rograndec.feijiayun.chain.business.set.manageConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.business.system.set.service.ManageConfigService;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

@SpringBootApplication
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan("com.rograndec.feijiayun.chain")
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestManageConfig {
	
	@Autowired
	private ManageConfigService manageConfigService;
	@Test
	public void select(){
		UserVO user = new UserVO();
		user.setChainType(0);
		user.setEnterpriseId(1L);
		user.setParentId(123455L);
		ManageConfig manageConfig = manageConfigService.getManageConfig(user);
		System.out.println(manageConfig.getUserCodeRule());
		System.out.println(manageConfig.getPriceControl());
	}
	@Test
	public void update(){
		UserVO user = new UserVO();
		user.setChainType(0);
		user.setEnterpriseId(1L);
		user.setParentId(123455L);
		ManageConfig manageConfig = new ManageConfig();
		manageConfig.setRequesterName("分店的人员我试试");
		manageConfig.setRequesterCode("666666");
		manageConfig.setRequesterId(89899L);
		manageConfig.setRemark("我新修改的信息");
		manageConfig.setId(2L);
		manageConfig.setPurchaserName("我修改的总部人员");
		manageConfig.setSupplierCodeRule(1111);
		manageConfig.setGoodsCodeRule(2222);
		manageConfig.setUserCodeRule(3333);
		
	}

}
