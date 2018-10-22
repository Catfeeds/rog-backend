package com.rograndec.feijiayun.chain.business.opening;

import com.rograndec.feijiayun.chain.business.system.opening.entity.OpeningInventoryDetail;
import com.rograndec.feijiayun.chain.business.system.opening.service.OpeningInventoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ST on 2017/8/31.
 */

//@SpringBootApplication
//@RunWith(SpringJUnit4ClassRunner.class)
//@ComponentScan("com.rograndec.feijiayun.chain")
//@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OpeningInventoryTest {

    //@Autowired
    private OpeningInventoryService openingInventoryService;

    //@Test
    public void test(){
        List<OpeningInventoryDetail> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            OpeningInventoryDetail openingInventoryDetail = new OpeningInventoryDetail();
            openingInventoryDetail.setAmount(new BigDecimal(100));
            openingInventoryDetail.setDosageName("test" + i);
            openingInventoryDetail.setGoodsCode("kk" + i);
            openingInventoryDetail.setGoodsGenericName("kk2" + i);
            openingInventoryDetail.setEnterpriseId(22l);
            openingInventoryDetail.setParentId(22l);
            openingInventoryDetail.setCreaterCode("2");
            openingInventoryDetail.setCreaterId(22l);
            openingInventoryDetail.setCreaterName("333");
            openingInventoryDetail.setCreateTime(new Date());
            openingInventoryDetail.setModifierId(22l);
            openingInventoryDetail.setModifierCode("333");
            openingInventoryDetail.setOrderId(333l);
            openingInventoryDetail.setGoodsId(233l);
            openingInventoryDetail.setGoodsName("nk");
            openingInventoryDetail.setDosageId(222l);
            openingInventoryDetail.setUnitId(222l);
            openingInventoryDetail.setUnitName("fdfdf");
            openingInventoryDetail.setLotNumber("33333");
            openingInventoryDetail.setProductDate(new Date());
            openingInventoryDetail.setValidDate(new Date());
            openingInventoryDetail.setQuantity(new BigDecimal(222));
            openingInventoryDetail.setUnitPrice(new BigDecimal(3333));
            openingInventoryDetail.setTaxRate(new BigDecimal(22));
            openingInventoryDetail.setNotaxPrice(new BigDecimal(222));
            openingInventoryDetail.setNotaxAmount(new BigDecimal(22));
            openingInventoryDetail.setTaxAmount(new BigDecimal(2));
            openingInventoryDetail.setShelfId(11l);
            openingInventoryDetail.setShelfName("222");
            openingInventoryDetail.setStatus(1);
            list.add(openingInventoryDetail);
        }
        openingInventoryService.batchInsert(list);
        for(OpeningInventoryDetail op : list){
            System.out.println(op.getId());
        }

    }


}