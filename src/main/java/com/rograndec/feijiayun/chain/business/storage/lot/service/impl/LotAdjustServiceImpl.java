package com.rograndec.feijiayun.chain.business.storage.lot.service.impl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ConnectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.keytable.dao.CostMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.LotNumberMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.Cost;
import com.rograndec.feijiayun.chain.business.keytable.entity.LotNumber;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.dao.GoodsDestroyMapper;
import com.rograndec.feijiayun.chain.business.storage.lot.dao.LotAdjustDetailMapper;
import com.rograndec.feijiayun.chain.business.storage.lot.dao.LotAdjustMapper;
import com.rograndec.feijiayun.chain.business.storage.lot.dao.LotAdjustShelfMapper;
import com.rograndec.feijiayun.chain.business.storage.lot.entity.LotAdjust;
import com.rograndec.feijiayun.chain.business.storage.lot.entity.LotAdjustDetail;
import com.rograndec.feijiayun.chain.business.storage.lot.entity.LotAdjustShelf;
import com.rograndec.feijiayun.chain.business.storage.lot.service.LotAdjustService;
import com.rograndec.feijiayun.chain.business.storage.lot.vo.GoodsLotNumberVO;
import com.rograndec.feijiayun.chain.business.storage.lot.vo.LotAdjustDetailVO;
import com.rograndec.feijiayun.chain.business.storage.lot.vo.LotAdjustShelfVO;
import com.rograndec.feijiayun.chain.business.storage.lot.vo.LotAdjustVO;
import com.rograndec.feijiayun.chain.business.storage.lot.vo.LotNumberVO;
import com.rograndec.feijiayun.chain.business.storage.lot.vo.LotStockVO;
import com.rograndec.feijiayun.chain.business.storage.move.dao.OtherInDetailMapper;
import com.rograndec.feijiayun.chain.business.storage.move.dao.OtherInMapper;
import com.rograndec.feijiayun.chain.business.storage.move.dao.OtherOutDetailMapper;
import com.rograndec.feijiayun.chain.business.storage.move.dao.OtherOutMapper;
import com.rograndec.feijiayun.chain.business.storage.move.service.OtherInService;
import com.rograndec.feijiayun.chain.business.storage.move.service.OtherOutService;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.CalculateComponent;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.component.InOutComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.OrderModelBuilder;
import com.rograndec.feijiayun.chain.common.constant.OrderDirection;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.TaxRateType;
import com.rograndec.feijiayun.chain.common.model.OrderModel;
import com.rograndec.feijiayun.chain.common.vo.GoodsDefTaxRateVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;

/**
 * @author 孙帮祥
 * Created by ST on 2017/9/25.
 */
@Service
public class LotAdjustServiceImpl implements LotAdjustService {

	@Autowired
	private LotAdjustMapper lotAdjustMapper;
	@Autowired
	private LotAdjustDetailMapper lotAdjustDetailMapper;
	@Autowired
	private OrderCodeComponent orderCodeComponent;
    @Autowired
	private ManageConfigMapper manageConfigMapper;
    @Autowired
    private UserMapper userMapper;
	@Autowired
	private GoodsDestroyMapper goodsDestroyMapper;
	@Autowired 
	private LotNumberMapper lotNumberMapper;
	@Autowired
	private OtherInMapper otherInMapper;
	@Autowired
	private OtherOutMapper otherOutMapper;
	@Autowired
	private OtherInDetailMapper otherInDetailMapper;
	@Autowired
	private OtherOutDetailMapper otherOutDetailMapper;
	@Autowired
	private CostMapper costMapper;
	@Autowired
	private InOutComponent inOutComponent;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private OtherOutService otherOutService;//其他出库
	@Autowired
	private OtherInService otherInService;//其他入库
	@Autowired
	private StockMapper stockMapper;
	@Autowired
	private CommonComponent commonComponent;
	@Autowired
	private LotAdjustShelfMapper lotAdjustShelfMapper;
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public void saveLotAdjust(UserVO userVO, LotAdjustVO lotAdjustVO) throws Exception,BusinessException {
		LotAdjust lotAdjust=new LotAdjust();
		LotAdjust lotAdjustInOut=new LotAdjust();
		//获取批号列表
		Map lotMap=new HashMap();
		lotMap.put("enterpriseId", userVO.getEnterpriseId());
		List<LotNumberVO> lotNumberVOList=lotAdjustMapper.selectLotNumberList(lotMap);
		List<LotAdjustShelf> lotAdjustShelfListInOut=new ArrayList<LotAdjustShelf>();
		BeanUtils.copyProperties(lotAdjustVO,lotAdjust);//从vo里面往实体复制
		List<LotAdjustDetailVO> lotAdjustDetailVOList=lotAdjustVO.getLotAdjustDetailVOList();
		lotAdjust.setEnterpriseId(userVO.getEnterpriseId());
		lotAdjust.setParentId(userVO.getParentId());
		//后边这个编码方式需要改变
		String code=orderCodeComponent.generate(OrderRule.LOT_ADJUST.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode());
		lotAdjust.setStatus(1);//给他一个默认的状态
		lotAdjust.setCode(code);
		lotAdjust.setOrderType(OrderRule.LOT_ADJUST.getType());//单据类型
		lotAdjust.setCreateTime(new Date());
		lotAdjust.setCreaterId(userVO.getUserId());
		lotAdjust.setCreaterCode(userVO.getUserCode());
		lotAdjust.setCreaterName(userVO.getUserName());
		ManageConfig manage = manageConfigMapper.selectByCurrentUser(userVO);//判断质量流程是否开启
        Boolean zl= manage.getBusinessControl()==0?false:true;//等于0的时候是关闭
         if(zl){//质量流程开启的时候不必提供销毁日期与销毁人员
        	 lotAdjust.setAdjustManId(userVO.getUserId());
        	 lotAdjust.setAdjustManName(userVO.getUserName());
        	 lotAdjust.setAdjustManCode(userVO.getUserCode());
         }else{
        		 User user=userMapper.selectByPrimaryKey(lotAdjust.getAdjustManId());
        		 if(user==null){
        			throw new BusinessException("找不到该人员，请确认人员ID"+lotAdjust.getAdjustManId()+"是否正确"); 
        		 }
        		 lotAdjust.setAdjustManId(user.getId());
        		 lotAdjust.setAdjustManName(user.getName());
        		 lotAdjust.setAdjustManCode(user.getCode());
         }
            //根据商品ID，批号,企业ID,总部ID,获取库存列表
			List<LotStockVO> stockList =new ArrayList<LotStockVO>();
			GoodsDefTaxRateVO taxRateVO=new GoodsDefTaxRateVO();
			List<Cost> costList=new ArrayList<Cost>();
			BigDecimal quantityTotal=new BigDecimal(0);//数量  库存数量
			BigDecimal amountTotal=new BigDecimal(0);//金额合计
			BigDecimal taxAmountTotal=new BigDecimal(0); //税额合计
			BigDecimal notaxAmountTotal=new BigDecimal(0);//不含税金额合计
			
			BigDecimal quantityTotalInOut=new BigDecimal(0);//数量  库存数量
			BigDecimal amountTotalInOut=new BigDecimal(0);//金额合计
			BigDecimal taxAmountTotalInOut=new BigDecimal(0); //税额合计
			BigDecimal notaxAmountTotalInOut=new BigDecimal(0);//不含税金额合计
			Set cearRepeat=new HashSet();//获取品种数量
	         for(int i=0;i<lotAdjustDetailVOList.size();i++){
	        	List<LotAdjustShelfVO> lotAdjustShelfVOList=new ArrayList<LotAdjustShelfVO>();
	            LotAdjustDetailVO lotAdjustDetailVO=lotAdjustDetailVOList.get(i);
	            Map map=new HashMap();
				map.put("enterpriseId", userVO.getEnterpriseId());
				map.put("goodsId", lotAdjustDetailVO.getGoodsId());
				map.put("lotId", lotAdjustDetailVO.getLotId());
	            stockList = lotAdjustMapper.selectStockVOByParam(map);
			    taxRateVO = commonComponent.getGoodsDefTaxRateInfo(userVO.getEnterpriseId(), lotAdjustDetailVO.getGoodsId(), TaxRateType.PURCHASE.getType());
			if(CollectionUtils.isNotEmpty(stockList)){
				BigDecimal quantityDetailTotal=new BigDecimal(0);//数量  库存数量
				BigDecimal amountDetailTotal=new BigDecimal(0);//金额
				BigDecimal taxAmountDetailTotal=new BigDecimal(0); //税额合计
				BigDecimal notaxAmountDetailTotal=new BigDecimal(0);//不含税金额合计
				for(LotStockVO stock:stockList){
					quantityDetailTotal=quantityDetailTotal.add(stock.getQuantity());//库存数量
				    Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("enterpriseId", userVO.getEnterpriseId());
					paramMap.put("parentId", userVO.getParentId());
					paramMap.put("goodsId",lotAdjustDetailVO.getGoodsId());
					paramMap.put("lotId", lotAdjustDetailVO.getLotId());
					//获取成本列表
				    costList = costMapper.selectByParamMap(paramMap);
					//计算
					BigDecimal amount_=new BigDecimal(0);
					BigDecimal quantityTotal_=new BigDecimal(0);
					if(CollectionUtils.isNotEmpty(costList)){
						for(Cost cost:costList){
							BigDecimal unitPrice=cost.getRealPrice();//实际单价
							amount_=unitPrice.multiply(cost.getUsableQuantity());//该成本单里的金额
							amount_=amount_.add(amount_);//累计金额
						}
					}
					quantityTotal_=stock.getQuantity();
					//构建货位明细单
					LotAdjustShelfVO lotAdjustShelfVO=new LotAdjustShelfVO();
					//根据计算获取的
					lotAdjustShelfVO.setQuantity(quantityTotal_);//数量
					lotAdjustShelfVO.setAmount(amount_);//金额
					BigDecimal unitPrice_=new BigDecimal(0);
					if(quantityTotal_.compareTo(BigDecimal.ZERO)==1){
						unitPrice_=CalculateComponent.getRealPriceByRealAmountAndQuantity(amount_, quantityTotal_);
					}
					lotAdjustShelfVO.setUnitPrice(unitPrice_);//单价
					BigDecimal notaxAmount_=CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(amount_, taxRateVO.getTaxRate());
					lotAdjustShelfVO.setNotaxAmount(notaxAmount_);//不含税金额
					BigDecimal notaxPrice_=new BigDecimal(0);
					if(quantityTotal_.compareTo(BigDecimal.ZERO)==1){
						 notaxPrice_=CalculateComponent.getNotaxPriceByNotaxAmountAndQuantity(notaxAmount_, quantityTotal_);//不含税单价
					}
					lotAdjustShelfVO.setNotaxPrice(notaxPrice_);//不含税单价
					BigDecimal taxAmount_=CalculateComponent.getTaxAmountByRealAmountAndNotaxAmount(amount_,notaxPrice_);
					lotAdjustShelfVO.setTaxAmount(taxAmount_);//税额
					lotAdjustShelfVO.setTaxRate(taxRateVO.getTaxRate());//税率
					lotAdjustShelfVO.setTaxRateId(taxRateVO.getTaxRateId());//税率ID
					lotAdjustShelfVO.setShelfId(stock.getShelfId());
					lotAdjustShelfVO.setShelfName(stock.getShelfName());
					lotAdjustShelfVOList.add(lotAdjustShelfVO);
			
					lotAdjustDetailVO.setLotAdjustShelfVOList(lotAdjustShelfVOList);
					amountDetailTotal=amountDetailTotal.add(amount_);//金额合计
					taxAmountDetailTotal=taxAmountDetailTotal.add(taxAmount_);
					notaxAmountDetailTotal=notaxAmountDetailTotal.add(notaxAmount_);
	      		}
				lotAdjustDetailVO.setQuantity(quantityDetailTotal);//数量
				lotAdjustDetailVO.setAmount(amountDetailTotal);//金额
				lotAdjustDetailVO.setTaxRate(taxRateVO.getTaxRate());//税率
				lotAdjustDetailVO.setTaxRateId(taxRateVO.getTaxRateId());//税率ID
				BigDecimal unitPrice=new BigDecimal(0);
				if(quantityDetailTotal.compareTo(BigDecimal.ZERO)==1){
					unitPrice=CalculateComponent.getRealPriceByRealAmountAndQuantity(amountDetailTotal, quantityDetailTotal);
				}
				lotAdjustDetailVO.setUnitPrice(unitPrice);//单价
				BigDecimal notaxAmount=CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(amountDetailTotal, taxRateVO.getTaxRate());
				lotAdjustDetailVO.setNotaxAmount(notaxAmount);
				BigDecimal notaxPrice=new BigDecimal(0);
				if(quantityDetailTotal.compareTo(BigDecimal.ZERO)==1){
					notaxPrice=CalculateComponent.getNotaxPriceByNotaxAmountAndQuantity(notaxAmount, quantityDetailTotal);//不含税单价
				}
			    notaxPrice=CalculateComponent.getNotaxPriceByNotaxAmountAndQuantity(notaxAmount, quantityDetailTotal);//不含税单价
				lotAdjustDetailVO.setNotaxPrice(notaxPrice);
				BigDecimal taxAmount=CalculateComponent.getTaxAmountByRealAmountAndNotaxAmount(amountDetailTotal,notaxPrice);
				lotAdjustDetailVO.setTaxAmount(taxAmount);//税额
				//以下是主表的金额相关的信息，此处做一个判断，如果批号修改的情况下就相加，如果批号不修改的时候跳过
				amountTotal=amountTotal.add(amountDetailTotal);//金额合计
				quantityTotal=quantityTotal.add(quantityDetailTotal);//数量合计
				taxAmountTotal=taxAmountTotal.add(taxAmountDetailTotal);//税额合计
				notaxAmountTotal=notaxAmountTotal.add(notaxAmountDetailTotal);//不含税金额合计
				
				LotNumber lotNumber=lotNumberMapper.selectByPrimaryKey(lotAdjustDetailVO.getLotId());
	          	if(lotNumber==null){
	          		 throw new BusinessException("该批号不存在");
	          	}
	        	if(!lotNumber.getLotNum().equals(lotAdjustDetailVO.getLotNum())){//如果批号被修改的情况
					amountTotalInOut=amountTotalInOut.add(amountDetailTotal);//金额合计
					quantityTotalInOut=quantityTotalInOut.add(quantityDetailTotal);//数量合计
					taxAmountTotalInOut=taxAmountTotalInOut.add(taxAmountDetailTotal);//税额合计
					notaxAmountTotalInOut=notaxAmountTotalInOut.add(notaxAmountDetailTotal);//不含税金额合计
	        	}
			}
			//计算品种数量
		       
		      Long goodsId=lotAdjustDetailVOList.get(i).getGoodsId();
		      cearRepeat.add(goodsId);
	      }
	         BeanUtils.copyProperties(lotAdjust,lotAdjustInOut);//从一个实体复制到另一个构建关键表数据的实体
	         lotAdjust.setVarietiesQuantity(cearRepeat.size());
	         lotAdjust.setQuantityTotal(quantityTotal);
	         lotAdjust.setAmountTotal(amountTotal);
	         lotAdjust.setTaxAmountTotal(taxAmountTotal);
	         lotAdjust.setNotaxAmountTotal(notaxAmountTotal);
	         
	         lotAdjustInOut.setVarietiesQuantity(cearRepeat.size());
	         lotAdjustInOut.setQuantityTotal(quantityTotalInOut);
	         lotAdjustInOut.setAmountTotal(amountTotalInOut);
	         lotAdjustInOut.setTaxAmountTotal(taxAmountTotalInOut);
	         lotAdjustInOut.setNotaxAmountTotal(notaxAmountTotalInOut);
             lotAdjustMapper.insert(lotAdjust);
         for(int i=0;i<lotAdjustDetailVOList.size();i++){
        	 LotAdjustDetail lotAdjustDetail=new LotAdjustDetail();
        	 LotAdjustDetailVO lotAdjustDetailVO=lotAdjustDetailVOList.get(i);
        	 BeanUtils.copyProperties(lotAdjustDetailVO,lotAdjustDetail);//从vo里面往实体复制
        	 List<LotAdjustShelfVO> lotAdjustShelfVOList=lotAdjustDetailVO.getLotAdjustShelfVOList();
        	 lotAdjustDetail.setEnterpriseId(userVO.getEnterpriseId());
        	 lotAdjustDetail.setParentId(userVO.getParentId());
        	 lotAdjustDetail.setAdjustCode(lotAdjust.getCode());
        	 lotAdjustDetail.setAdjustId(lotAdjust.getId());
        	 lotAdjustDetail.setCode(lotAdjust.getCode());
        	 lotAdjustDetail.setStatus(lotAdjust.getStatus());
        	 lotAdjustDetail.setOrderType(lotAdjust.getOrderType());
        	 lotAdjustDetail.setAdjustDate(lotAdjust.getAdjustDate());
        	  Map goodsMap=new HashMap();
              goodsMap.put("goodsId",lotAdjustDetail.getGoodsId());
          	 Goods goods=goodsMapper.selectByPrimaryKey(lotAdjustDetail.getGoodsId());//根据商品id获取商品信息
          	 if(goods==null){
          		 throw new BusinessException("找不到该商品");
          	 }
          	lotAdjustDetail.setGoodsId(goods.getId());//商品ID
          	lotAdjustDetail.setGoodsCode(goods.getCode());//商品编码
          	lotAdjustDetail.setGoodsName(goods.getName());//商品名称
          	//设置新的批号，生产日期，有效日期
      		lotAdjustDetail.setNewLotNum(lotAdjustDetail.getLotNum());//新的批号
      		lotAdjustDetail.setNewProductDate(lotAdjustDetail.getProductDate());//新的生产日期
      		lotAdjustDetail.setNewValidUntil(lotAdjustDetail.getValidUntil());//新的有效日期
          	//在此处处理批号修改或是没修改的逻辑
          	LotNumber lotNumber=lotNumberMapper.selectByPrimaryKey(lotAdjustDetail.getLotId());
          	if(lotNumber==null){
          		 throw new BusinessException("该批号不存在");
          	}
      		lotAdjustDetail.setLotNum(lotNumber.getLotNum());//原来的批号
      		lotAdjustDetail.setProductDate(lotNumber.getProductDate());//原来的生产日期
      		lotAdjustDetail.setValidUntil(lotNumber.getValidUntil());//原来的有效日期
      		lotAdjustDetail.setCreaterId(userVO.getUserId());
      		lotAdjustDetail.setCreaterCode(userVO.getUserCode());
      		lotAdjustDetail.setCreaterName(userVO.getUserName());
      		lotAdjustDetail.setCreateTime(new Date());
      		/**
      		 * 批号修改start
      		 * */
            		LotNumber lotNumber_=new LotNumber();
            		lotNumber_.setId(lotAdjustDetail.getLotId());
            		lotNumber_.setProductDate(lotAdjustDetail.getNewProductDate());
            		lotNumber_.setValidUntil(lotAdjustDetail.getNewValidUntil());
            	   	if(!lotNumber.getLotNum().equals(lotAdjustDetailVO.getLotNum())){//如果批号被修改的情况
                 		if(CollectionUtils.isNotEmpty(lotNumberVOList)){
                 			for(LotNumberVO lotVO:lotNumberVOList){
                 				if(lotVO.getLotNum().equals(lotAdjustDetailVO.getLotNum())){
                 					throw new BusinessException("批号"+lotAdjustDetailVO.getLotNum()+"批号已存在");
                 				}
                 			}
                 		}
                  lotNumber_.setId(null);
                  lotNumber_.setLotNum(lotAdjustDetail.getNewLotNum());
                  lotNumber_.setCreaterId(userVO.getUserId());
                  lotNumber_.setCreaterCode(userVO.getUserCode());
                  lotNumber_.setCreaterName(userVO.getUserName());
                  lotNumber_.setCreateTime(new Date());
                  lotNumber_.setEnterpriseId(userVO.getEnterpriseId());
                  lotNumber_.setGoodsId(lotAdjustDetail.getGoodsId());
                  lotNumber_.setGoodsCode(lotAdjustDetail.getGoodsCode());
                  lotNumber_.setGoodsName(lotAdjustDetail.getGoodsName());
                  lotNumber_.setModifierId(userVO.getUserId());
                  lotNumber_.setModifierCode(userVO.getUserCode());
                  lotNumber_.setModifierName(userVO.getUserName());
                  lotNumber_.setParentId(userVO.getParentId());
                  lotNumber_.setUpdateTime(new Date());
                  lotNumberMapper.insert(lotNumber_);
                  lotAdjustDetail.setNewLotId(lotNumber_.getId());
                  	}else{
                  		lotNumberMapper.updateByPrimaryKeySelective(lotNumber_);
                  	}
            		
            /**
             * 批号修改end
             * */	

      		lotAdjustDetailMapper.insert(lotAdjustDetail);
      		if(CollectionUtils.isNotEmpty(lotAdjustShelfVOList)){
      			List<LotAdjustShelf> LotAdjustShelfList=new ArrayList<LotAdjustShelf>();
         	for(LotAdjustShelfVO lotAdjustShelfVO:lotAdjustShelfVOList){
         		LotAdjustShelf lotAdjustShelf=new LotAdjustShelf();
         		BeanUtils.copyProperties(lotAdjustShelfVO,lotAdjustShelf);//从vo里面往实体复制
				lotAdjustShelf.setAdjustId(lotAdjust.getId());
				lotAdjustShelf.setDtlId(lotAdjustDetail.getId());
				lotAdjustShelf.setLotId(lotAdjustDetail.getLotId());
				lotAdjustShelf.setNewLotId(lotAdjustDetail.getNewLotId());
				lotAdjustShelf.setLotNum(lotAdjustDetail.getLotNum());
				lotAdjustShelf.setProductDate(lotAdjustDetail.getProductDate());
				lotAdjustShelf.setValidUntil(lotAdjustDetail.getValidUntil());
				lotAdjustShelf.setNewLotNum(lotAdjustDetail.getNewLotNum());
				lotAdjustShelf.setNewProductDate(lotAdjustDetail.getNewProductDate());
				lotAdjustShelf.setNewValidUntil(lotAdjustDetail.getNewValidUntil());
				lotAdjustShelf.setRemark(lotAdjustDetail.getRemark());
				lotAdjustShelf.setGoodsId(lotAdjustDetail.getGoodsId());
				lotAdjustShelf.setGoodsCode(lotAdjustDetail.getGoodsCode());
				lotAdjustShelf.setGoodsName(lotAdjustDetail.getGoodsName());
				lotAdjustShelf.setParentId(userVO.getParentId());
				lotAdjustShelf.setEnterpriseId(userVO.getEnterpriseId());
				lotAdjustShelf.setCreaterId(userVO.getUserId());
				lotAdjustShelf.setCreaterCode(userVO.getUserCode());
				lotAdjustShelf.setCreaterName(userVO.getUserName());
				lotAdjustShelf.setCreateTime(new Date());
				lotAdjustShelf.setStatus(1);
				lotAdjustShelf.setShelfStatusDesc("不合格");
				lotAdjustShelfMapper.insert(lotAdjustShelf);
				//出入库相关数据start
				lotAdjustShelf.setLotNum(lotAdjustDetailVO.getLotNum());
				LotAdjustShelfList.add(lotAdjustShelf);
				//出入库先关数据end
	      		}
        	if(lotNumber==null){
         		 throw new BusinessException("该批号不存在");
         	}
         	if(!lotNumber.getLotNum().equals(lotAdjustDetailVO.getLotNum())){//如果批号被修改的情况
         		if(CollectionUtils.isNotEmpty(lotNumberVOList)){
         			for(LotNumberVO lotVO:lotNumberVOList){
         				if(lotVO.getLotNum().equals(lotAdjustDetailVO.getLotNum())){
         					throw new BusinessException("批号"+lotAdjustDetailVO.getLotNum()+"批号已存在");
         				}
         			}
         		}
       		lotAdjustShelfListInOut.addAll(LotAdjustShelfList);
          	}
			}
         }
         if(CollectionUtils.isNotEmpty(lotAdjustShelfListInOut)){
            OrderModelBuilder builder = new OrderModelBuilder(userVO);
      		OrderModel outOrderModel = builder.buildLotAdjustModel(OrderRule.LOT_ADJUST, lotAdjust, lotAdjustShelfListInOut, OrderDirection.LOT_ADJUST_OUT.getDirection());
      		inOutComponent.generateKeyTableDatas(OrderDirection.OUT, outOrderModel);
      		for(LotAdjustShelf shelf:lotAdjustShelfListInOut){
      			shelf.setLotNum(shelf.getNewLotNum());
      			shelf.setLotId(shelf.getNewLotId());
      		}
      		OrderModel inOrderModel = builder.buildLotAdjustModel(OrderRule.LOT_ADJUST, lotAdjust, lotAdjustShelfListInOut, OrderDirection.LOT_ADJUST_IN.getDirection());
      		inOutComponent.generateKeyTableDatas(OrderDirection.IN, inOrderModel);
         }
	}
	@Override
	public LotAdjustVO getLotAdjustById(Long id) {
		return lotAdjustMapper.selectById(id);
	}

	@Override
	public void getLotAdjustList(Page page, Map map) {
		Long totalRecord=lotAdjustMapper.selectCount(map);
    	List<LotAdjustVO>  lotAdjustVOList=lotAdjustMapper.selectLotAdjustList(map);
    	page.setTotalRecord(totalRecord.intValue());
    	page.setResult(lotAdjustVOList);
	}

	@Override
	public void exportExcel(OutputStream output, Long id, UserVO loginUser) {
        //转换一下显示日期
		LotAdjustVO lotAdjustVO=lotAdjustMapper.selectById(id);
	List<LotAdjustDetailVO> lotAdjustDetailVOList=lotAdjustVO.getLotAdjustDetailVOList();
	try {
        ExcelWriter writer = new ExcelWriter() {
            public void generate() throws Exception {
            	        //开启excel
            	        this.beginWorkSheet();
                        this.beginSheet();
                        //第一行
                        this.insertRow(0);
                        this.createCell(8,"荣贯医药");
                        this.endRow();
                        //第二行
                        this.insertRow(1);
                        this.createCell(8,"批号调整");//头部
                        this.endRow();
                        //第三行
                        StringBuffer titleSecondRow=new StringBuffer();
                        titleSecondRow.append("调整单号:");
            	        titleSecondRow.append(lotAdjustVO.getCode() ==null? "":lotAdjustVO.getCode());
            	        titleSecondRow.append("  调整日期:");
            	        titleSecondRow.append(lotAdjustVO.getAdjustDate() == null ? "":DateUtils.DateToString(lotAdjustVO.getAdjustDate(),"yyyy-mm-dd"));
            	        titleSecondRow.append("  调整人员:");
            	        titleSecondRow.append(lotAdjustVO.getAdjustManName() == null ? "":lotAdjustVO.getAdjustManName());
            	        titleSecondRow.append("  调整原因:");
            	        titleSecondRow.append(lotAdjustVO.getAdjustReason() == null ? "":lotAdjustVO.getAdjustReason());
                        this.insertRow(2);
                        this.createCell(0,titleSecondRow.toString());
                        this.endRow();
                        //列表头开始
                        this.insertRow(3);
                  	    this.createCell(0,"序号");
                  	    this.createCell(1,"商品编码");
                  	    this.createCell(2,"通用名称");
                  	    this.createCell(3,"剂型");
                  	    this.createCell(4,"规格");
                  	    this.createCell(5,"单位");
                  	    this.createCell(6,"生产厂商");
                  	    this.createCell(7,"产地");
                  	    this.createCell(8,"原批号");
                  	    this.createCell(9,"新批号");
                  	    this.createCell(10,"原生产日期");
                  	    this.createCell(11,"新生产日期");
                      	this.createCell(12,"原有效日期");
                     	this.createCell(13,"新有效日期");
                  	    this.endRow();
                  	    //列表开始
                        for(int i=0;i<lotAdjustDetailVOList.size();i++){
                        	  this.insertRow(i+4);
                        	  this.createCell(0,i);
                        	  this.createCell(1,lotAdjustDetailVOList.get(i).getGoodsCode()==null?"":lotAdjustDetailVOList.get(i).getGoodsCode());
                        	  this.createCell(2,lotAdjustDetailVOList.get(i).getGoodsGenericName()==null?"":lotAdjustDetailVOList.get(i).getGoodsGenericName());
                        	  this.createCell(3,lotAdjustDetailVOList.get(i).getDosageName()==null?"":lotAdjustDetailVOList.get(i).getDosageName());
                        	  this.createCell(4,lotAdjustDetailVOList.get(i).getGoodsSpecification()==null?"":lotAdjustDetailVOList.get(i).getGoodsSpecification());
                        	  this.createCell(5,lotAdjustDetailVOList.get(i).getUnitName()==null?"":lotAdjustDetailVOList.get(i).getUnitName());
                        	  this.createCell(6,lotAdjustDetailVOList.get(i).getManufacturer()==null?"":lotAdjustDetailVOList.get(i).getManufacturer());
                        	  this.createCell(7,lotAdjustDetailVOList.get(i).getGoodsPlace()==null?"":lotAdjustDetailVOList.get(i).getGoodsPlace());
                        	  this.createCell(8,lotAdjustDetailVOList.get(i).getLotNum()==null?"":lotAdjustDetailVOList.get(i).getLotNum());
                        	  this.createCell(9,lotAdjustDetailVOList.get(i).getNewLotNum()==null?"":lotAdjustDetailVOList.get(i).getNewLotNum());
                        	  this.createCell(10,lotAdjustDetailVOList.get(i).getProductDate()==null?"":DateUtils.DateToString(lotAdjustDetailVOList.get(i).getProductDate(),"yyyy-mm-dd"));
                        	  this.createCell(11,lotAdjustDetailVOList.get(i).getNewProductDate()==null?"":DateUtils.DateToString(lotAdjustDetailVOList.get(i).getNewProductDate(),"yyyy-mm-dd"));
                        	  this.createCell(12,lotAdjustDetailVOList.get(i).getValidUntil()==null?"":DateUtils.DateToString(lotAdjustDetailVOList.get(i).getValidUntil(),"yyyy-mm-dd"));
                        	  this.createCell(13,lotAdjustDetailVOList.get(i).getNewValidUntil()==null?"":DateUtils.DateToString(lotAdjustDetailVOList.get(i).getNewValidUntil(),"yyyy-mm-dd"));
                        	  this.endRow();
                        }
                        //关闭excel
                        this.endSheet();
                        this.endWorkSheet();
            }
        };
        writer.process(output);
    } catch (Exception e) {
        e.printStackTrace();
    }
}
	@Override
	public List<GoodsLotNumberVO> getGoodsList(Map map) {
		return lotAdjustMapper.selectGoodsList(map);
	}
}