package com.rograndec.feijiayun.chain.business.distr.parent.dao;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrLackDetail;
import com.rograndec.feijiayun.chain.business.purchase.plan.vo.PurchasePlanGoodsVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

//@SpringBootApplication
//@RunWith(SpringJUnit4ClassRunner.class)
//@ComponentScan("com.rograndec.feijiayun.chain")
//@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DistrLackDetailMapperTest {

    //@Autowired
    private DistrLackDetailMapper distrLackDetailMapper;

    //@Test
    public void getByLackIdGroupByGoodsId() throws Exception {

        List<PurchasePlanGoodsVO> byLackIdGroupByGoodsId = distrLackDetailMapper.getByLackIdGroupByGoodsId(new String[]{"1"});
        System.out.println(byLackIdGroupByGoodsId);
    }

}