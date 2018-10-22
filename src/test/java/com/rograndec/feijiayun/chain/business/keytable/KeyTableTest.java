package com.rograndec.feijiayun.chain.business.keytable;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rograndec.feijiayun.chain.business.keytable.entity.LotNumber;
import com.rograndec.feijiayun.chain.business.keytable.service.LotNumberService;

//@SpringBootApplication
//@RunWith(SpringJUnit4ClassRunner.class)
//@ComponentScan("com.rograndec.feijiayun.chain")
//@MapperScan("com.rograndec.feijiayun.chain.**.dao")
//@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KeyTableTest {
	
//	@Autowired
	private LotNumberService lotNumberService;
	
//	@Test
	public void testSaveLotNumber() throws ParseException{
		LotNumber lotNumber = new LotNumber();
		lotNumber.setEnterpriseId(100L);
		lotNumber.setParentId(0L);
		lotNumber.setGoodsId(1L);
		lotNumber.setLotNum("20170824");
		lotNumber.setProductDate(DateUtils.parseDate("2017-08-24", new String[]{"yyyy-MM-dd"}));
		lotNumber.setValidUntil(DateUtils.parseDate("2018-08-24", new String[]{"yyyy-MM-dd"}));
		lotNumber.setCreaterId(1L);
		lotNumber.setCreaterCode("admin");
		lotNumber.setCreateTime(new Date());
		
		lotNumberService.saveLotNumber(lotNumber);
	}
	
//	@Test
	public void testGetLotNumberInfo(){
		LotNumber lotNumberInfo = lotNumberService.getLotNumberInfo(1L);
		System.out.println(lotNumberInfo);
	}
	
	
}
