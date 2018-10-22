package com.rograndec.feijiayun.chain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rograndec.feijiayun.chain.business.system.set.entity.QualitySet;
import com.rograndec.feijiayun.chain.business.system.set.service.QualitySettingsService;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

//@SpringBootApplication
//@RunWith(SpringJUnit4ClassRunner.class)
//@ComponentScan("com.rograndec.feijiayun.chain")
//@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChainBackendApplicationTests {

	//@Autowired
	private QualitySettingsService qualitySettingsService;
	//@Test
	public void contextLoads() {
		UserVO u = new UserVO();
		u.setChainType(0);
		u.setEnterpriseId(1L);
		QualitySet q =  new QualitySet();
		q.setCode("1005");
		q.setDescription("aaaa");
		q.setRemark("aaa");
		q.setStatus(1);
		q.setSetType(0);
		/*qualitySettingsService.addQualitySettings(q, u);*/
	}

}
