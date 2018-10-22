package com.rograndec.feijiayun.chain.common.component;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rograndec.feijiayun.chain.common.constant.OrderRule;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ComponentScan("com.rograndec.feijiayun.chain")
//@MapperScan("com.rograndec.feijiayun.chain.**.dao")
//@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RedisTest {
	
	@Autowired
	private OrderCodeComponent orderCodeComponent;
	
	
//	@Test
	public void test() throws Exception{
//		String enterpriseCode = codeComponent.generate("Enterprise", 5, 1L);
//		System.out.println(enterpriseCode);
//		String batchNum = codeComponent.generateBatchNum(1L,OrderRule.OPENING_INVENTORY,1,6);
		String code = orderCodeComponent.generate(OrderRule.RECEIVE.getCodePrefix(), 1L, "44412");
		System.out.println(code);
	}
	
	
}
