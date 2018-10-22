//package com.rograndec.feijiayun.chain.business.system.opening.service;
//
//import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
//import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
//import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsBusinessMapper;
//import com.rograndec.feijiayun.chain.business.goods.info.entity.GoodsBusiness;
//import com.rograndec.feijiayun.chain.business.goods.info.service.GoodsService;
//import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsVO;
//import com.rograndec.feijiayun.chain.common.component.CalculateComponent;
//import com.rograndec.feijiayun.chain.common.vo.UserVO;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Random;
//
///**
// * Created by dudy on 2017/10/9.
// */
////@RunWith(SpringJUnit4ClassRunner.class)
////@ComponentScan("com.rograndec.feijiayun.chain")
////@MapperScan("com.rograndec.feijiayun.chain.**.dao")
////@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class OpeningInventoryServiceTest {
//
//
//    //@Autowired
//    private  OpeningInventoryService  service;
//
//    //@Autowired
//    private GoodsService goodsService;
//
//    //@Autowired
//    private GoodsBusinessMapper goodsBusinessMapper;
//
//
//    //@Autowired
//    private SupplierMapper  supplierMapper;
//
//    //@Test
//    public void saveGoodsToInventory() throws Exception {
//
//        UserVO user  = new UserVO();
//        user.setUserId(1L);
//        user.setEnterpriseId(1L);
//        user.setEnterpriseCode("RGRANDEC");
//        user.setEnterpriseName("融贯医药");
//        user.setParentId(0L);
//        user.setUserCode("0001");
//        user.setUserName("接口测试人员（总部）");
//        user.setLoginAccount("admin");
//        user.setPassword("123456");
//        user.setChainType(0);
//        OpeningGoodsVO openingGoodsVO = new OpeningGoodsVO();
//
//        BigDecimal taxRate = new BigDecimal(17);
//
//        BigDecimal amountTotal = new BigDecimal(0);
//        BigDecimal taxAmountTotal = new BigDecimal(0);
//        BigDecimal quantityTotal = new BigDecimal(0);
//        BigDecimal notaxAmountTotal = new BigDecimal(0);
//
//        List<OpeningGoodsVO> goodsVOList = new ArrayList<>();
//        for(int i = 112;i<116;i++){
//
//            //Goods goods = goodsMapper.selectByPrimaryKey(new Long(i));
//            GoodsVO  goods = goodsService.getGoodsInfoById(new Long(i));
//
//            OpeningGoodsVO goodsVO = new OpeningGoodsVO();
////            goodsVO.setStatus(EnableStatus.ENABLE.getStatus());
//            goodsVO.setGoodsName(goods.getName());
//            goodsVO.setGoodsId(goods.getId());
//            goodsVO.setCode(goods.getCode());
//            goodsVO.setOldCode(goods.getOldCode());
//            goodsVO.setName(goods.getName());
//            goodsVO.setBarCode(goods.getBarcode());
//            goodsVO.setGoodsGenericName(goodsVO.getGoodsGenericName());
//            goodsVO.setDosageId(goods.getDosageId());
//            goodsVO.setDosageName(goods.getDosageName());
//            goodsVO.setUnitId(goods.getUnitId());
//            goodsVO.setUnitName(goods.getUnitName());
//            goodsVO.setManufacturer(goods.getManufacturer());// 生产企业
//            goodsVO.setSpecification(goods.getSpecification());// 规格
//            goodsVO.setProductDate("2016-10-10 11:48:34");// 生产日期
//            goodsVO.setValidUntil("2018-10-10 11:48:43");// 有效期
//
//            goodsVO.setLotNumber("0001234" + i);  // 批号  要不要批号ID， 要不要插入批号表
//
//            BigDecimal quantity = new BigDecimal(new Random().nextInt(100));
//            BigDecimal unitPrice = new BigDecimal(new Random().nextInt(20));
//            goodsVO.setQuantity(quantity + "");// 数量
//            goodsVO.setUnitPrice(unitPrice + "");// 单价
//            goodsVO.setTaxRate(taxRate + "");// TODO: 税率ID
//            //goodsVO.setNotaxPrice();// 不含税单价
//            GoodsBusiness goodsBusiness = goodsBusinessMapper.getByGoodsId(new Long(i));
//            goodsVO.setRetailPrice(goodsBusiness.getRetailPrice().toString());
//            goodsVO.setMemberPrice(goodsBusiness.getMemberPrice().toString());
//
//            BigDecimal lineDiscount = new BigDecimal(100);
//
//            goodsVO.setAmount(CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(quantity, unitPrice, lineDiscount)); // 金额
//            goodsVO.setNotaxAmount(CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(goodsVO.getAmount(), taxRate));// 不含税金额
//            goodsVO.setNotaxPrice(CalculateComponent.getNotaxPriceByNotaxAmountAndQuantity(goodsVO.getNotaxAmount(), quantity)); // 不含税单价
//            goodsVO.setTaxAmount(
//                    CalculateComponent.getTaxAmountByRealAmountAndNotaxAmount(goodsVO.getAmount(), goodsVO.getNotaxAmount()));// 税额
//
//            amountTotal = amountTotal.add(goodsVO.getAmount());
//            taxAmountTotal = taxAmountTotal.add(goodsVO.getTaxAmount());
//            quantityTotal = quantityTotal.add(quantity);
//            notaxAmountTotal = notaxAmountTotal.add(goodsVO.getNotaxAmount());
//
//            goodsVO.setShelfId(new Long(301));
//            goodsVO.setShelfName("合格品货位");
//
//            // 供货单位
//            Supplier supplier = supplierMapper.selectByPrimaryKey(new Long(i));
//            goodsVO.setSupplierId(supplier.getId());
//            goodsVO.setSupplierCode(supplier.getCode());
//            goodsVO.setSupplierName(supplier.getName());
//
//
//            goodsVOList.add(goodsVO);
//
//
//        }
//
//        openingGoodsVO.setAmountTotal(amountTotal);
//        openingGoodsVO.setTaxAmountTotal(taxAmountTotal);
//        openingGoodsVO.setQuantityTotal(quantityTotal);
//        openingGoodsVO.setNotaxAmountTotal(notaxAmountTotal);
//
//        openingGoodsVO.setGoodsVOList(goodsVOList);
////        openingGoodsVO.setStatus(EnableStatus.ENABLE.getStatus());
////        openingGoodsVO.setOrderType(OrderRule.INITIAL_DEAL.getType());
////        openingGoodsVO.setStorageDate(new Date());
////        openingGoodsVO.setStorageManId(user.getUserId());
////        openingGoodsVO.setStorageManName(user.getUserName());
////        openingGoodsVO.setStorageManCode(user.getUserCode());
////        openingGoodsVO.setVarietiesQuantity(goodsVOList.size()); // 品种数量
//
//
////        service.saveGoodsToInventory(openingGoodsVO,user);
//
//    }
//
//}