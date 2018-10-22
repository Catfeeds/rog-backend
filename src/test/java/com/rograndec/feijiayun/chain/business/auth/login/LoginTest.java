package com.rograndec.feijiayun.chain.business.auth.login;

//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rograndec.feijiayun.chain.business.auth.login.service.LoginService;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

//@SpringBootApplication
//@RunWith(SpringJUnit4ClassRunner.class)
//@ComponentScan("com.rograndec.feijiayun.chain")
//@MapperScan("com.rograndec.feijiayun.chain.**.dao")
//@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginTest {
	
//	@Autowired
	private LoginService loginService;
	
	
//	@Test
	public void test(){
		UserVO user = loginService.findUserByLoginAccount("admin");
		System.out.println(user);
	}
	
	
}
