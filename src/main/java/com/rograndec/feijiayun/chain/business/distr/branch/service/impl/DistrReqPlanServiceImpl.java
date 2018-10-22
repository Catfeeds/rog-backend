package com.rograndec.feijiayun.chain.business.distr.branch.service.impl;

import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.distr.branch.approvalProcessor.DistrReqPlanApprovalProcessor;
import com.rograndec.feijiayun.chain.business.distr.branch.constant.DistrType;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrReqPlanDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrReqPlanMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrReqPlan;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrReqPlanDetail;
import com.rograndec.feijiayun.chain.business.distr.branch.service.DistrReqPlanService;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.*;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrLackDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrLackMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrLack;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrLackDetail;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.init.model.ApprovalFlowContentModel;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrReqPlanReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.OrderReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrReqPlan;
import com.rograndec.feijiayun.chain.business.system.approval.vo.SubmitApprovalFlowVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.business.system.set.service.ManageConfigService;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.DistributionType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.DistrLackStatus;
import com.rograndec.feijiayun.chain.common.constant.status.DistrReqPlanStatus;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.builder.BuilderException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * Created by dudy on 2017/10/7.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class DistrReqPlanServiceImpl implements DistrReqPlanService {
	@Autowired
	private DistrReqPlanMapper distrReqPlanMapper;
	@Autowired
	private DistrReqPlanDetailMapper distrReqPlanDetailMapper;
    @Autowired
    private OrderCodeComponent orderCodeComponent;
    @Autowired
    private ManageConfigMapper manageConfigMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired 
    private GoodsMapper goodsMapper;
    @Autowired
	private EnterpriseMapper enterpriseMapper;
    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
	@Autowired
	private ManageConfigService manageConfigService;
	@Autowired
	private ApprovalFlowComponent approvalFlowComponent;
	@Autowired
	private DistrReqPlanApprovalProcessor distrReqPlanApprovalProcessor;
	@Autowired
	private SupplierMapper supplierMapper;
	@Autowired
    private RedisComponent redisComponent;

	@Autowired
	private DistrLackDetailMapper distrLackDetailMapper;

	@Autowired
	private DistrLackMapper distrLackMapper;


	@Autowired
	private StockMapper stockMapper;

	@Autowired
	private CommonComponent commonComponent;

	@Autowired
	private EnterpriseBusinessMapper enterpriseBusinessMapper;

	@Autowired
	private PriceOrderDetailMapper priceOrderDetailMapper;
	/*@override
	public list<goodsdistrreqplananalysisvo> getgoodsdistrreqplanvolist(map map, analysestockvo analysestockvo) {
		// 返回值
		list<goodsdistrreqplananalysisvo> goodsdistrreqplananalysisvolist=new arraylist<goodsdistrreqplananalysisvo>();
		//按照安全库存采购
		list<goodsdistrreqplananalysisvo> goodsdistrreqplananalysisvolistsafety=new arraylist<goodsdistrreqplananalysisvo>();
		//按照缺断货数量采购
		list<goodsdistrreqplananalysisvo> goodsdistrreqplananalysisvolistlimit=new arraylist<goodsdistrreqplananalysisvo>();
		//按照动态存量采购
		list<goodsdistrreqplananalysisvo> goodsdistrreqplananalysisvolisthalf=new arraylist<goodsdistrreqplananalysisvo>();
		if(!analysestockvo.getrequesttype().equals(distributiontype.distribution_head.getcode()) &&
				!analysestockvo.getrequesttype().equals(distributiontype.direct_distribution.getcode())) {
			return goodsdistrreqplananalysisvolist;
		}
		if(analysestockvo.getrequesttype().equals(distributiontype.direct_distribution.getcode())) {
			//如果是直调配送
			supplier supplier = suppliermapper.selectbyprimarykey(analysestockvo.getoutboundunitid());
 			if(supplier == null)  throw new businessexception("供货单位不存在");
		}
		if(map.get("safety")!=null){//按照安全库存采购
			if(map.get("safety").equals("true")){
				goodsdistrreqplananalysisvolistsafety= distrreqplanmapper.selectgoodslistbysafetystock(map);
				if(collectionutils.isnotempty(goodsdistrreqplananalysisvolistsafety)){
                for(goodsdistrreqplananalysisvo vo:goodsdistrreqplananalysisvolistsafety){
                	vo.setquantity(vo.getsalequantitytotal().subtract(vo.getstockquantitytotal()));//安全库存-库存为要货的数量
                }
				goodsdistrreqplananalysisvolist=goodsdistrreqplananalysisvolistsafety;
				}
			}
		}
        if(map.get("lack")!=null){//按照缺断货数量采购
        	if(map.get("lack").equals("true")){
        	   goodsdistrreqplananalysisvolistlimit= distrreqplanmapper.selectgoodslistbyweeklimit(map);
        	   //处理多选时的逻辑
        	   if(collectionutils.isnotempty(goodsdistrreqplananalysisvolistlimit)){//如果按照缺断货数量采购有值的情况
        		   iterator<goodsdistrreqplananalysisvo> listlimit = goodsdistrreqplananalysisvolistlimit.iterator();
        		   iterator<goodsdistrreqplananalysisvo> listanalysis = goodsdistrreqplananalysisvolist.iterator();
        		   while(listlimit.hasnext()){
        			   goodsdistrreqplananalysisvo limit= listlimit.next();
        			   while(listanalysis.hasnext()){
        				   goodsdistrreqplananalysisvo analysis= listanalysis.next();
        				   if(limit.getgoodsid().equals(analysis.getgoodsid())){
        					   if(limit.getquantity()!=null && analysis.getquantity()!=null){
        						   if(limit.getquantity().compareto(analysis.getquantity())!=-1){
        							   listanalysis.remove();
        						   }
        					   }
        				   }
        			   }
        		   }
        		   goodsdistrreqplananalysisvolist.addall(goodsdistrreqplananalysisvolistlimit);
        	}
		}
        if(map.get("dynamicstock")!=null){//按照动态存量采购
        	bigdecimal undynamicstockquantity=(bigdecimal) map.get("undynamicstockquantity");//不满足多少天的销售
        	if(map.get("dynamicstock").equals("true")){
        	   goodsdistrreqplananalysisvolisthalf=distrreqplanmapper.selectgoodslistbyweeklimithalfday(map);
       		if(collectionutils.isnotempty(goodsdistrreqplananalysisvolisthalf)){
       			iterator<goodsdistrreqplananalysisvo> listhalf = goodsdistrreqplananalysisvolisthalf.iterator();
       			while(listhalf.hasnext()){
       				goodsdistrreqplananalysisvo vo=listhalf.next();
       				if(vo!=null){
       					bigdecimal salequantitytotal=vo.getsalequantitytotal();//30天内销售总额
       					bigdecimal stockquantitytotal=vo.getstockquantitytotal();//剩余库存
       					bigdecimal averagequantity=salequantitytotal.divide(new bigdecimal(30), 2, bigdecimal.round_half_up);//每天的销售量保留两位小数
       					bigdecimal stockday=stockquantitytotal.divide(averagequantity,0,bigdecimal.round_half_down);//可销售的库存件数，除不尽的时候不保留小数
       					if(stockday.compareto(undynamicstockquantity)==1){//如果可销售的大于15天的时候，不需要要货，从列表中去除
       						listhalf.remove();
          				 }else{
          					  bigdecimal limitquantity=undynamicstockquantity.subtract(stockday);//与15比较差多少天
         					  bigdecimal reqquantity=limitquantity.subtract(averagequantity);//需要采购的商品数量
         					  vo.setquantity(reqquantity);
          				 }
          				 }
       				}
       			}
       		  }
       		//处理多选时的逻辑
       	   if(collectionutils.isnotempty(goodsdistrreqplananalysisvolisthalf)){//如果按照缺断货数量采购有值的情况
       	   iterator<goodsdistrreqplananalysisvo> listhalf = goodsdistrreqplananalysisvolisthalf.iterator();
  		   iterator<goodsdistrreqplananalysisvo> listanalysis = goodsdistrreqplananalysisvolist.iterator();
  		   while(listhalf.hasnext()){
  			 goodsdistrreqplananalysisvo half=listhalf.next();
  			   while(listanalysis.hasnext()){
  				 goodsdistrreqplananalysisvo anlysis=listanalysis.next();
  				   if(half.getgoodsid().equals(anlysis.getgoodsid())){
  					   if(half.getquantity()!=null && anlysis.getquantity()!=null){
  						   if(half.getquantity().compareto(anlysis.getquantity())==-1){
  							 listanalysis.remove();
  						   }else{
  							 listhalf.remove();
  						   }
  					   }
  				   }
  			   }
  		   }
    		   goodsdistrreqplananalysisvolist.addall(goodsdistrreqplananalysisvolisthalf);
    	     }
           }
		}
        if(map.get("onpassage")!=null && map.get("onpassage").equals("true")){//是否考虑在途库存
			map map_ = new hashmap();
			map.put("enterpriseid", map_);
			list<onpassagestockvo> onpassagestocklist = distrreqplanmapper.selectonpassagestock(map);
			if (collectionutils.isnotempty(onpassagestocklist)) {
				iterator<onpassagestockvo> onpassage = onpassagestocklist.iterator();
				iterator<goodsdistrreqplananalysisvo> listanalysis = goodsdistrreqplananalysisvolist.iterator();
				while (onpassage.hasnext()) {
					onpassagestockvo op = onpassage.next();
					while (listanalysis.hasnext()) {
						goodsdistrreqplananalysisvo vo = listanalysis.next();
						if (vo.getgoodsid().equals(op.getgoodsid())) {
							// 如果在途库存的存量没有计划的多，那么用计划数量减去在途库存量
							if (vo.getquantity().compareto(op.getquantity()) == 1) {
								vo.setquantity(vo.getquantity().subtract(op.getquantity()));
							} else {
								listanalysis.remove();
							}
						}
					}
				}
			}
		}
		return goodsdistrreqplananalysisvolist;
	}*/

	@Override
	public AnalysisVO getAnalysisVO(List<GoodsDistrReqPlanAnalysisVO> goodsDistrReqPlanVOList, UserVO userVO,
			AnalyseStockVO analyseStockVO) {
		AnalysisVO analysisVO=new AnalysisVO();
		//设置配货单位为总部
		analysisVO.setDistrUnitId(userVO.getParentId());
		analysisVO.setDistrUnitCode(userVO.getParentCode());
		analysisVO.setDistrUnitName(userVO.getParentName());
	    //设置配货类型及商品详情
    	analysisVO.setRequestType(analyseStockVO.getRequestType());
        analysisVO.setRequestTypeDesc(DistributionType.getName(analysisVO.getRequestType()));
		analysisVO.setDistrReqPlanDetailVOList(goodsDistrReqPlanVOList);
		//设置供货单位
		if(analyseStockVO.getRequestType().equals(DistributionType.DIRECT_DISTRIBUTION.getCode())) {
			//如果是直调配送
			Supplier supplier = supplierMapper.selectByPrimaryKey(analyseStockVO.getOutboundUnitId());
 			if(supplier == null)  throw new BusinessException("出库单位不存在");
 			analysisVO.setOutboundUnitId(supplier.getId());
 			analysisVO.setOutboundUnitCode(supplier.getCode());
 			analysisVO.setOutboundUnitName(supplier.getName());
		}else {
			//其他为总部
			analysisVO.setOutboundUnitId(userVO.getParentId());
 			analysisVO.setOutboundUnitCode(userVO.getParentCode());
 			analysisVO.setOutboundUnitName(userVO.getParentName());
		}
		return analysisVO;
	}

	@Override
	public DistrLack getDistrLackDetailList(Long id, UserVO loginUser) {

		DistrLack distrLack = distrLackMapper.selectByPrimaryKey(id);
		distrLack.setDistrLackDetailList(distrLackDetailMapper.getDistrLackDetailByLackId(id));

		for(Iterator<DistrLackDetail> it = distrLack.getDistrLackDetailList().iterator();it.hasNext();){
			DistrLackDetail temp = it.next();
			BigDecimal orgUsableQuantity = stockMapper.getUsableQuantityGroupByGoodsId(loginUser.getParentId(),temp.getGoodsId());
			temp.setOrgUsableQuantity(orgUsableQuantity == null ? BigDecimal.ZERO:orgUsableQuantity);
			BigDecimal  usableQuantity = stockMapper.getUsableQuantityGroupByGoodsId(loginUser.getEnterpriseId(),temp.getGoodsId());
			temp.setUsableQuantity(usableQuantity == null? BigDecimal.ZERO:usableQuantity);
			temp.setQuantity(temp.getLackQuantity());
		}
		return distrLack;
	}


	@Override
	public List<GoodsDistrReqPlanVO> getGoodsList(Map map,UserVO userVO) {

		ManageConfig manage = manageConfigMapper.selectByCurrentUser(userVO);//判断质量流程是否开启
//        Boolean zl= manage.getBusinessControl()==0?false:true;//等于0的时候是关闭
		//若是开启则限定商品必须在门店的经营范围内
		if(manage.getBusinessControl() == 1) map.put("sign", "sign");

		List<GoodsDistrReqPlanVO> planVOList = distrReqPlanMapper.selectGoodsList(map);

		// 查询总部的
		Integer requestType = (Integer) map.get("requestType");

		if(DistributionType.SWAP_BETWEEN_STORES.getCode() == requestType ) {// 门店间调剂
			planVOList = distrReqPlanMapper.queryQuantityGoodsList(map);
		}
		Iterator<GoodsDistrReqPlanVO> it = planVOList.iterator();
		while(it.hasNext()){
			GoodsDistrReqPlanVO planVO = it.next();

			// 获取总部的库存
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("enterpriseId",map.get("distrUnitId"));
			paramMap.put("goodsId",planVO.getGoodsId());
			paramMap.put("today",new Date());
			BigDecimal orgUsableQuantity = stockMapper.getQualifiedUsableQuantity(paramMap);
			planVO.setOrgUsableQuantity(orgUsableQuantity == null?BigDecimal.ZERO:orgUsableQuantity);

			paramMap.put("enterpriseId",userVO.getEnterpriseId());
			BigDecimal usableQuantity = stockMapper.getQualifiedUsableQuantity(paramMap);
			planVO.setUsableQuantity(usableQuantity == null?BigDecimal.ZERO:usableQuantity);

			// 获取配货价格
			BigDecimal distrPrice = commonComponent.getDistrPrice(userVO.getParentId(),userVO.getEnterpriseId(),planVO.getGoodsId(),null,BigDecimal.ONE);
			planVO.setDistrPrice(distrPrice);
		}

		return planVOList;
	}
	@Override
	public DistrReqPlanVO getById(Long id, UserVO loginUser) {
		DistrReqPlanVO distrReqPlanVO = distrReqPlanMapper.selectById(id);
		if(distrReqPlanVO == null){
			throw new BusinessException("要货计划不存在");
		}
		distrReqPlanVO.setEnterpriseName(loginUser.getEnterpriseName());
		distrReqPlanVO.setRequestTypeName(distrReqPlanVO.getRequestType()!=null?DistrType.getValue(distrReqPlanVO.getRequestType()):"");
		return distrReqPlanVO;
	}

	/**
	 * 获取要货计划主单id集合 查询多个要货明细
	 *
	 * @param ids
	 */
	@Override
	public List<DistrReqPlanDetailVO> getReqPlanDtlByIds(String ids,UserVO userVO) {
		String[] splits = ids.split(",");
		if(splits == null || splits.length == 0){
			throw new BusinessException("参数错误");
		}
		List<DistrReqPlanDetailVO> reqPlanList = distrReqPlanMapper.getReqPlanDtlByIds(splits, userVO.getEnterpriseId());
		return reqPlanList;
	}

	@Override
	public List<EnterpriseReqPlanVO> getEnterprise(Map map) {
		return distrReqPlanMapper.selectEnterprise(map);
	}
	@Override
	public void getList(Page page,Map map) {
		Long totalRecord=distrReqPlanMapper.selectCount(map);
    	List<DistrReqPlanVO>  list=distrReqPlanMapper.selectList(map);
    	if(list != null && !list.isEmpty()) {
			for(DistrReqPlanVO vo:list) {
				vo.setRequestTypeDesc(DistributionType.getName(vo.getRequestType()));
				vo.setStatusDesc(DistrReqPlanStatus.getStatusDesc(vo.getStatus()));
			}
		}
    	page.setTotalRecord(totalRecord.intValue());
    	page.setResult(list);
	}
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public void save(UserVO userVO,DistrReqPlanVO distrReqPlanVO) throws Exception,BusinessException{
		if(distrReqPlanVO.getRequestType()==null) throw new BusinessException("缺少配货类型");
     	/*ManageConfig manageConfig = manageConfigService.getManageConfig(userVO);*/
		DistrReqPlan distrReqPlan=new DistrReqPlan();
     	List<DistrReqPlanDetailVO> distrReqPlanDetailVOList=distrReqPlanVO.getDistrReqPlanDetailVOList();
		BeanUtils.copyProperties(distrReqPlanVO,distrReqPlan);//从vo里面往实体复制
		distrReqPlan.setStatus(DistrReqPlanStatus.PENDING_AUDIT);
 	/*	if (manageConfig.getApprovalControl() == 1) {// 如果审批流开启
 			distrReqPlan.setStatus(DistrReqPlanStatus.PENDING_AUDIT);
 		}else{
 			distrReqPlan.setStatus(DistrReqPlanStatus.WAIT_DISTR);//现在默认新增之后直接给
 		}*/

 		//配货单位ID
 		Enterprise enterprisevo=enterpriseMapper.selectByPrimaryKey(userVO.getParentId());
 		if(enterprisevo==null){
 			throw new BusinessException("找不到配货单位");
 		}
 		distrReqPlan.setDistrUnitId(enterprisevo.getId());
 		distrReqPlan.setDistrUnitCode(enterprisevo.getCode());
 		distrReqPlan.setDistrUnitName(enterprisevo.getName());

 		//若为直接调配则保存供货单位信息
 		if(distrReqPlan.getRequestType().equals(DistributionType.DIRECT_DISTRIBUTION.getCode())) {
 			if(distrReqPlanVO.getOutboundUnitId() == null)  throw new BusinessException("缺少出库单位");
 			Supplier supplier = supplierMapper.selectByPrimaryKey(distrReqPlanVO.getOutboundUnitId());
 			if(supplier == null)  throw new BusinessException("出库单位不存在");
 			distrReqPlan.setOutboundUnitId(supplier.getId());
 			distrReqPlan.setOutboundUnitCode(supplier.getCode());
 			distrReqPlan.setOutboundUnitName(supplier.getName());
 		}else if(distrReqPlan.getRequestType().equals(DistributionType.SWAP_BETWEEN_STORES.getCode())){//若是门店间调剂则出库单位为其他门店
 			Enterprise ent=enterpriseMapper.selectByPrimaryKey(distrReqPlanVO.getOutboundUnitId());
 			if(ent==null) throw new BusinessException("出库单位不存在");
 			distrReqPlan.setOutboundUnitId(ent.getId());
 			distrReqPlan.setOutboundUnitCode(ent.getCode());
 			distrReqPlan.setOutboundUnitName(ent.getName());
 		}else {//若是总部配送则是总部
 			distrReqPlan.setOutboundUnitId(enterprisevo.getId());
 			distrReqPlan.setOutboundUnitCode(enterprisevo.getCode());
 			distrReqPlan.setOutboundUnitName(enterprisevo.getName());
 		}

 		Date date=new Date();
		distrReqPlan.setEnterpriseId(userVO.getEnterpriseId());
		distrReqPlan.setParentId(userVO.getParentId());//此处先用订单的编码方式进行编码
		distrReqPlan.setCreateTime(date);
		distrReqPlan.setUpdateTime(date);
		distrReqPlan.setCreaterId(userVO.getUserId());
		distrReqPlan.setCreaterName(userVO.getUserName());
		distrReqPlan.setCreaterCode(userVO.getUserCode());
		distrReqPlan.setModifierId(userVO.getUserId());
		distrReqPlan.setModifierCode(userVO.getUserCode());
		distrReqPlan.setModifierName(userVO.getUserName());
		
		String code=orderCodeComponent.generate(OrderRule.REQUIRE_PLAN.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode());
		distrReqPlan.setCode(code);
		distrReqPlan.setOrderType(OrderRule.REQUIRE_PLAN.getType());//单据类型
    	ManageConfig manage = manageConfigMapper.selectByCurrentUser(userVO);//判断质量流程是否开启
        Boolean zl= manage.getBusinessControl()==0?false:true;//等于0的时候是关闭
         if(zl){//质量流程开启的时候
        	 distrReqPlan.setPlannerId(userVO.getUserId());
        	 distrReqPlan.setPlannerCode(userVO.getUserCode());
        	 distrReqPlan.setPlannerName(userVO.getUserName());
         }else{
    		 User user=userMapper.selectByPrimaryKey(distrReqPlan.getPlannerId());
    		 if(user==null){
    			throw new BusinessException("找不到该人员，请确认人员ID"+distrReqPlan.getPlannerId()+"是否正确");
    		 }
    		 distrReqPlan.setPlannerId(user.getId());
    		 distrReqPlan.setPlannerName(user.getName());
    		 distrReqPlan.setPlannerCode(user.getCode());
         }
         BigDecimal quantityTotal=new BigDecimal(0);
         if(distrReqPlanDetailVOList!=null){
        	 for(int i=0;i<distrReqPlanDetailVOList.size();i++){
				 BigDecimal quantity = distrReqPlanDetailVOList.get(i).getQuantity();
				 if(BigDecimal.ZERO.compareTo(quantity) == 0) throw new BusinessException("要货数量不能为0");
				 quantityTotal=quantityTotal.add(quantity);
        	 }
        	 distrReqPlan.setQuantityTotal(quantityTotal);
        	 distrReqPlan.setVarietiesQuantity(distrReqPlanDetailVOList.size());//商品种类数
          }
         distrReqPlanMapper.insert(distrReqPlan);//插入销毁单主表
    	 if(distrReqPlanDetailVOList!=null){
         for(int i=0;i<distrReqPlanDetailVOList.size();i++){
        	 DistrReqPlanDetail distrReqPlanDetail=new DistrReqPlanDetail();
             BeanUtils.copyProperties(distrReqPlanDetailVOList.get(i),distrReqPlanDetail);//从vo里面往实体复制
            // EntityUtils.reflectAddSetDefaultValue(goodsDestroyDetail.getClass(),userVO);//复制用户基本信息到基础表
			 if(distrReqPlanDetailVOList.get(i).getLineNum() == null){
			 	throw new BusinessException("缺失保存必要字段数据：行号");
			 }
             distrReqPlanDetail.setEnterpriseId(userVO.getEnterpriseId());
             distrReqPlanDetail.setOrderType(distrReqPlan.getOrderType());
             distrReqPlanDetail.setParentId(userVO.getParentId());
             distrReqPlanDetail.setPlanId(distrReqPlan.getId());
             distrReqPlanDetail.setPlanCode(distrReqPlan.getCode());
             distrReqPlanDetail.setCreaterCode(distrReqPlan.getCode());
             distrReqPlanDetail.setStatus(distrReqPlan.getStatus());
             distrReqPlanDetail.setCreateTime(new Date());
             distrReqPlanDetail.setUpdateTime(new Date());
             distrReqPlanDetail.setCreaterId(userVO.getUserId());
             distrReqPlanDetail.setCreaterCode(userVO.getUserCode());
             distrReqPlanDetail.setCreaterName(userVO.getUserName());
             distrReqPlanDetail.setModifierId(userVO.getUserId());
             distrReqPlanDetail.setModifierCode(userVO.getUserCode());
             distrReqPlanDetail.setModifierName(userVO.getUserName());
             //根据商品ID获取商品信息
          	 Goods goods=goodsMapper.selectByPrimaryKey(distrReqPlanDetail.getGoodsId());//根据商品id获取商品信息
         	 if(goods==null){
         		 throw new BusinessException("找不到该商品");
         	 }
         	distrReqPlanDetail.setPlanDate(distrReqPlan.getPlanDate());//销毁单日期
         	distrReqPlanDetail.setGoodsId(goods.getId());//商品ID
         	distrReqPlanDetail.setGoodsCode(goods.getCode());//商品编码
         	distrReqPlanDetail.setGoodsName(goods.getName());//商品名称
         	distrReqPlanDetail.setDosageId(goods.getDosageId());//剂型ID
         	distrReqPlanDetail.setDosageName(goods.getDosageName());//剂型名称
         	distrReqPlanDetail.setGoodsSpecification(goods.getSpecification());//商品规格
         	distrReqPlanDetail.setManufacturerId(goods.getManufacturerId());//生产厂商ID
         	distrReqPlanDetail.setManufacturer(goods.getManufacturer());//生产厂商
         	distrReqPlanDetail.setUnitId(goods.getUnitId());//单位ID
         	distrReqPlanDetail.setUnitName(goods.getUnitName());//单位名称 库存计量单位名称
         	distrReqPlanDetail.setApprovalNumber(goods.getApprovalNumber());//批准文号
         	distrReqPlanDetail.setGoodsGenericName(goods.getGenericName());//商品通用名称
         	distrReqPlanDetail.setGoodsPlace(goods.getPlace());//商品产地

			 // 获取本店和供应单位的的库存
			 Map<String,Object> paramMap = new HashMap<>();
			 paramMap.put("enterpriseId",userVO.getEnterpriseId());
			 paramMap.put("goodsId",goods.getId());
			 paramMap.put("today",new Date());
			 BigDecimal  usableQuantity = stockMapper.getQualifiedUsableQuantity(paramMap);
			 distrReqPlanDetail.setUsableQuantity(usableQuantity == null?BigDecimal.ZERO:usableQuantity);
			 paramMap.put("enterpriseId",distrReqPlan.getDistrUnitId());
			 BigDecimal  orgUsableQuantity = stockMapper.getQualifiedUsableQuantity(paramMap);
			 distrReqPlanDetail.setOrgUsableQuantity(orgUsableQuantity == null?BigDecimal.ZERO:orgUsableQuantity);

         	distrReqPlanDetail.setId(null);
         	distrReqPlanDetailMapper.insert(distrReqPlanDetail);//插入商品销毁单明细
           

           }
       }
    	 //删除草稿
    	 removeDraftCach(userVO.getEnterpriseId(), OrderRule.REQUIRE_PLAN.getCodePrefix(), distrReqPlanVO.getRedisKeyValue());
    
    	 // 提交流程审批
  		/*if (manageConfig.getApprovalControl() == 1) {// 如果审批流开启*/
			 Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(userVO.getEnterpriseId());
			 SubmitApprovalFlowVO submitApprovalFlowVO = new SubmitApprovalFlowVO(userVO.getEnterpriseId(), enterprise.getName(),
				userVO.getUserId(), userVO.getUserName(), userVO.getChainType(), userVO.getParentId(),
				userVO.getChainType().equals(ChainType.Headquarters.getCode()) ? userVO.getEnterpriseId() : userVO.getParentId(),
				 ApprovalFlowContentModel.getDistrReqPlanCode(), distrReqPlan.getId(), distrReqPlan.getCode(), enterprise.getName()+"店要货计划");
			 approvalFlowComponent.apply(submitApprovalFlowVO, userVO);
  	        /*}*/

       // 更新缺配单状态
		if(distrReqPlanVO.getBaseOrderId() != null){
			updateDistrLackStatus(userVO, distrReqPlanVO,distrReqPlan);
		}
	}

	/**
	 * 更新缺配单状态
	 * @param userVO
	 * @param distrReqPlanVO
	 */
	private void updateDistrLackStatus(UserVO userVO, DistrReqPlanVO distrReqPlanVO,DistrReqPlan distrReqPlan) {

		DistrLack distrLack = distrLackMapper.selectByPrimaryKey(distrReqPlanVO.getBaseOrderId());
		distrLack.setStatus(DistrLackStatus.FINISHED);
		distrLack.setModifierId(userVO.getUserId());
		distrLack.setModifierCode(userVO.getUserCode());
		distrLack.setModifierName(userVO.getUserName());
		distrLack.setUpdateTime(new Date());
		distrLack.setRemark("要货计划更新缺配单状态，baseOrderId:" + distrReqPlan.getId());
		if (distrLackMapper.updateByPrimaryKeySelective(distrLack) != 1) {
			throw new BuilderException("更新缺配单状态失败！");
		}

		for (DistrReqPlanDetailVO  planDetailVO:distrReqPlanVO.getDistrReqPlanDetailVOList()) {
			DistrLackDetail distrLackDetail = distrLackDetailMapper.selectByPrimaryKey(planDetailVO.getBaseOrderDtlId());

			//已清数量，
			BigDecimal clearQuantity = distrLackDetail.getUnclearQuantity().add(distrLackDetail.getClearQuantity());
			if (clearQuantity.compareTo(BigDecimal.ZERO) < 0) {
				clearQuantity = BigDecimal.ZERO;
			}
			distrLackDetail.setSendQuantity(clearQuantity);
			distrLackDetail.setClearQuantity(clearQuantity);

			//未清数量直接设置为0，不允许下次再调用
			distrLackDetail.setUnclearQuantity(BigDecimal.ZERO);
			distrLackDetail.setLackQuantity(BigDecimal.ZERO);
			//更新人信息
			distrLackDetail.setModifierId(userVO.getUserId());
			distrLackDetail.setModifierCode(userVO.getUserCode());
			distrLackDetail.setModifierName(userVO.getUserName());
			distrLackDetail.setUpdateTime(new Date());
			//更新明细
			if (distrLackDetailMapper.updateByPrimaryKeySelective(distrLackDetail) != 1) {
				throw new BuilderException("更新缺配单未清数量、已清数量失败！");
			}
		}


	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public void update(UserVO userVO,DistrReqPlanVO distrReqPlanVO) throws Exception,BusinessException{
		if(distrReqPlanVO.getId()==null){
			throw new BusinessException("ID不能为空"); 	
		}
		DistrReqPlan distrReqPlanParam=distrReqPlanMapper.selectByPrimaryKey(distrReqPlanVO.getId());
		if(distrReqPlanParam == null) throw new BusinessException("计划单不存在");
		DistrReqPlan distrReqPlan=new DistrReqPlan();
     	List<DistrReqPlanDetailVO> distrReqPlanDetailVOList=distrReqPlanVO.getDistrReqPlanDetailVOList();
		distrReqPlan.setStatus(DistrReqPlanStatus.PENDING_AUDIT);
     	/*ManageConfig manageConfig = manageConfigService.getManageConfig(userVO);*/
	/*	if (manageConfig !=null && manageConfig.getApprovalControl() == 1) {// 如果审批流开启
 			distrReqPlan.setStatus(DistrReqPlanStatus.PENDING_AUDIT);
 		}else{
 			distrReqPlan.setStatus(DistrReqPlanStatus.WAIT_DISTR);//现在默认新增之后直接给
 		}*/
		distrReqPlan.setId(distrReqPlanVO.getId());//设置计划单id
		distrReqPlan.setRemark(distrReqPlanVO.getRemark());
		distrReqPlan.setUpdateTime(new Date());
		distrReqPlan.setModifierId(userVO.getUserId());
		distrReqPlan.setModifierName(userVO.getUserName());
		distrReqPlan.setModifierCode(userVO.getUserCode());
		distrReqPlan.setStatus(DistrReqPlanStatus.WAIT_DISTR);//现在默认新增之后直接给
		Enterprise ent=enterpriseMapper.selectByPrimaryKey(distrReqPlanVO.getDistrUnitId());//配货单位ID
		if(ent==null){
		 	throw new BusinessException("找不到配货单位"); 
		}
		distrReqPlan.setId(ent.getId());
		distrReqPlan.setDistrUnitCode(ent.getCode());
		distrReqPlan.setDistrUnitName(ent.getName());
		String code=orderCodeComponent.generate(OrderRule.REQUIRE_PLAN.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode());
		distrReqPlan.setCode(code);
		distrReqPlan.setOrderType(OrderRule.REQUIRE_PLAN.getType());//单据类型
		 distrReqPlan.setPlanDate(distrReqPlanParam.getPlanDate());//计划日期
    	ManageConfig manage = manageConfigMapper.selectByCurrentUser(userVO);//判断质量流程是否开启
        Boolean zl= manage.getBusinessControl()==0?false:true;//等于0的时候是关闭
         if(!zl){//质量流程关闭的时候
        	 User user=userMapper.selectByPrimaryKey(distrReqPlanVO.getPlannerId());
        	 if(user==null){
        		 throw new BusinessException("找不到该人员，请确认人员ID"+distrReqPlan.getPlannerId()+"是否正确");
        	 }
        	 distrReqPlan.setPlannerId(user.getId());
        	 distrReqPlan.setPlannerName(user.getName());
        	 distrReqPlan.setPlannerCode(user.getCode());
        	 distrReqPlan.setPlanDate(distrReqPlanVO.getPlanDate());//计划日期
         } else {
			 distrReqPlan.setPlannerId(userVO.getUserId());
			 distrReqPlan.setPlannerName(userVO.getUserName());
			 distrReqPlan.setPlannerCode(userVO.getUserCode());
			 distrReqPlan.setPlanDate(new Date());//计划日期
		 }
         BigDecimal quantityTotal=new BigDecimal(0);
         if(distrReqPlanDetailVOList!=null){
        	 for(int i=0;i<distrReqPlanDetailVOList.size();i++){
        		 quantityTotal=quantityTotal.add(distrReqPlanDetailVOList.get(i).getQuantity());
        	 }
        	 distrReqPlan.setQuantityTotal(quantityTotal);
        	 distrReqPlan.setVarietiesQuantity(distrReqPlanDetailVOList.size());//商品种类数
          }
         distrReqPlanMapper.updateByPrimaryKeySelective(distrReqPlan);//插入销毁单主表
         //详情插入之前先进行删除操作
         distrReqPlanDetailMapper.deleteByPlanId(distrReqPlanParam.getId());
    	 if(distrReqPlanDetailVOList!=null){
			for (int i = 0; i < distrReqPlanDetailVOList.size(); i++) {
				DistrReqPlanDetail distrReqPlanDetail = new DistrReqPlanDetail();
				BeanUtils.copyProperties(distrReqPlanDetailVOList.get(i), distrReqPlanDetail);// 从vo里面往实体复制
				// 根据商品ID获取商品信息
				Goods goods = goodsMapper.selectByPrimaryKey(distrReqPlanDetail.getGoodsId());// 根据商品id获取商品信息
				if (goods == null) {
					throw new BusinessException("找不到该商品");
				}
				distrReqPlanDetail.setId(null);
				distrReqPlanDetail.setEnterpriseId(userVO.getEnterpriseId());
				distrReqPlanDetail.setParentId(userVO.getParentId());
				distrReqPlanDetail.setOrderType(distrReqPlanParam.getOrderType());
				distrReqPlanDetail.setPlanId(distrReqPlanParam.getId());
				distrReqPlanDetail.setPlanCode(distrReqPlanParam.getCode());// 要货计划单编码
				distrReqPlanDetail.setCreateTime(distrReqPlanParam.getCreateTime());
				distrReqPlanDetail.setCreaterId(distrReqPlanParam.getCreaterId());
				distrReqPlanDetail.setCreaterCode(distrReqPlanParam.getCreaterCode());
				distrReqPlanDetail.setCreaterName(distrReqPlanParam.getCreaterName());
				distrReqPlanDetail.setModifierId(userVO.getUserId());
				distrReqPlanDetail.setModifierName(userVO.getUserName());
				distrReqPlanDetail.setModifierCode(userVO.getUserCode());
				distrReqPlanDetail.setUpdateTime(new Date());
				distrReqPlanDetail.setPlanDate(distrReqPlan.getPlanDate());// 要货计划单日期
				distrReqPlanDetail.setGoodsId(goods.getId());// 商品ID
				distrReqPlanDetail.setGoodsCode(goods.getCode());// 商品编码
				distrReqPlanDetail.setGoodsName(goods.getName());// 商品名称
				distrReqPlanDetail.setDosageId(goods.getDosageId());// 剂型ID
				distrReqPlanDetail.setDosageName(goods.getDosageName());// 剂型名称
				distrReqPlanDetail.setGoodsSpecification(goods.getSpecification());// 商品规格
				distrReqPlanDetail.setManufacturerId(goods.getManufacturerId());// 生产厂商ID
				distrReqPlanDetail.setManufacturer(goods.getManufacturer());// 生产厂商
				distrReqPlanDetail.setUnitId(goods.getUnitId());// 单位ID
				distrReqPlanDetail.setUnitName(goods.getUnitName());// 单位名称 库存计量单位名称
				distrReqPlanDetail.setApprovalNumber(goods.getApprovalNumber());// 批准文号
				distrReqPlanDetail.setGoodsGenericName(goods.getGenericName());// 商品通用名称
				distrReqPlanDetail.setGoodsPlace(goods.getPlace());// 商品产地
				distrReqPlanDetail.setStatus(distrReqPlan.getStatus());//要货计划单状态

				// 获取本店和供应单位的的库存
				Map<String,Object> paramMap = new HashMap<>();
				paramMap.put("enterpriseId",userVO.getEnterpriseId());
				paramMap.put("goodsId",goods.getId());
				paramMap.put("today",new Date());
				BigDecimal  usableQuantity = stockMapper.getQualifiedUsableQuantity(paramMap);
				distrReqPlanDetail.setUsableQuantity(usableQuantity == null?BigDecimal.ZERO:usableQuantity);
				paramMap.put("enterpriseId",distrReqPlan.getDistrUnitId());
				BigDecimal  orgUsableQuantity = stockMapper.getQualifiedUsableQuantity(paramMap);
				distrReqPlanDetail.setOrgUsableQuantity(orgUsableQuantity == null?BigDecimal.ZERO:orgUsableQuantity);

				distrReqPlanDetailMapper.insert(distrReqPlanDetail);// 插入要货计划单明细
			}
      // 提交流程审批
 		/*if (manageConfig.getApprovalControl() == 1) {*/// 如果审批流开启
 			 Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(userVO.getEnterpriseId());
         SubmitApprovalFlowVO submitApprovalFlowVO = new SubmitApprovalFlowVO(userVO.getEnterpriseId(), enterprise.getName(),
         		userVO.getUserId(), userVO.getUserName(), userVO.getChainType(), userVO.getParentId(),
         		userVO.getChainType().equals(ChainType.Headquarters.getCode()) ? userVO.getEnterpriseId() : userVO.getParentId(),
                 ApprovalFlowContentModel.getDistrReqPlanCode(), distrReqPlan.getId(), distrReqPlan.getCode(), enterprise.getName()+"店的要货计划");
         approvalFlowComponent.apply(submitApprovalFlowVO, userVO);
 	        /*}*/
       }
	}
	/**
	 * 修改计划的状态
	 * @throws Exception 
	 * */
	@Override
	public void changeStatus(UserVO userVO,DistrReqPlan plan) throws Exception{
		EntityUtils.reflectUpdateSetDefaultValue(plan.getClass(),userVO);//复制用户基本信息到基础表
		distrReqPlanMapper.updateByPrimaryKeySelective(plan);
		Map map=new HashMap();
		map.put("planId", plan.getId());
		map.put("status", plan.getStatus());
		distrReqPlanDetailMapper.updateStatusByPlanId(map);
		// 提交流程审批
	}
	/**
	 * 导出
	 * */
	@Override
	public void exportExcel(OutputStream output, Long id, UserVO loginUser) {
		// TODO Auto-generated method stub
        //转换一下显示日期
		DistrReqPlanVO distrReqPlanVO=distrReqPlanMapper.selectById(id);
	List<DistrReqPlanDetailVO> distrReqPlanDetailVOList=distrReqPlanVO.getDistrReqPlanDetailVOList();
	try {
        ExcelWriter writer = new ExcelWriter() {
            public void generate() throws Exception {
            	        //开启excel
            	        this.beginWorkSheet();
                        this.beginSheet();
                        //第一行
                        this.insertRow(0);
                        this.createCell(8,"融贯医药");
                        this.endRow();
                        //第二行
                        this.insertRow(1);
                        this.createCell(8,"要货计划");//头部
                        this.endRow();
                        //第三行
                        StringBuffer titleSecondRow=new StringBuffer();
                        titleSecondRow.append("配货单位编码:");
            	        titleSecondRow.append(distrReqPlanVO.getDistrUnitCode() ==null? "":distrReqPlanVO.getDistrUnitCode());
            	        titleSecondRow.append("  配货单位名称:");
            	        titleSecondRow.append(distrReqPlanVO.getDistrUnitName() == null ? "":distrReqPlanVO.getDistrUnitName());
            	        titleSecondRow.append("  计划日期:");
            	        titleSecondRow.append(distrReqPlanVO.getPlanDate() == null ? "":DateUtils.DateToString(distrReqPlanVO.getPlanDate(),"yyyy-mm-dd"));
            	        titleSecondRow.append("  计划人员:");
            	        titleSecondRow.append(distrReqPlanVO.getPlannerName() == null ? "":distrReqPlanVO.getPlannerName());
            	        titleSecondRow.append("  计划单号:");
            	        titleSecondRow.append(distrReqPlanVO.getCode() == null ? "":distrReqPlanVO.getCode());
            	        titleSecondRow.append("  配货类型:");
            	        titleSecondRow.append(distrReqPlanVO.getRequestType() == null ? "":distrReqPlanVO.getRequestType());
            	        titleSecondRow.append("  备注:");
            	        titleSecondRow.append(distrReqPlanVO.getRemark() == null ? "":distrReqPlanVO.getRemark());
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
                  	    this.createCell(8,"配出机构库存可用量");
                  	    this.createCell(9,"配进机构库存可用量");
                  	    this.createCell(10,"要货数量");
                  	    this.createCell(11,"备注");
                  	    this.endRow();
                  	    //列表开始
                        for(int i=0;i<distrReqPlanDetailVOList.size();i++){
                        	  this.insertRow(i+4);
                        	  this.createCell(0,i);
                        	  this.createCell(1,distrReqPlanDetailVOList.get(i).getGoodsCode()==null?"":distrReqPlanDetailVOList.get(i).getGoodsCode());
                        	  this.createCell(2,distrReqPlanDetailVOList.get(i).getGoodsGenericName()==null?"":distrReqPlanDetailVOList.get(i).getGoodsGenericName());
                        	  this.createCell(3,distrReqPlanDetailVOList.get(i).getDosageName()==null?"":distrReqPlanDetailVOList.get(i).getDosageName());
                        	  this.createCell(4,distrReqPlanDetailVOList.get(i).getGoodsSpecification()==null?"":distrReqPlanDetailVOList.get(i).getGoodsSpecification());
                        	  this.createCell(5,distrReqPlanDetailVOList.get(i).getUnitName()==null?"":distrReqPlanDetailVOList.get(i).getUnitName());
                        	  this.createCell(6,distrReqPlanDetailVOList.get(i).getManufacturer()==null?"":distrReqPlanDetailVOList.get(i).getManufacturer());
                        	  this.createCell(7,distrReqPlanDetailVOList.get(i).getGoodsPlace()==null?"":distrReqPlanDetailVOList.get(i).getGoodsPlace());
                        	  this.createCell(8,distrReqPlanDetailVOList.get(i).getOrgUsableQuantity()==null?"":distrReqPlanDetailVOList.get(i).getOrgUsableQuantity().toString());
                        	  this.createCell(9,distrReqPlanDetailVOList.get(i).getUsableQuantity()==null?"":distrReqPlanDetailVOList.get(i).getUsableQuantity().toString());
                        	  this.createCell(10,distrReqPlanDetailVOList.get(i).getQuantity()==null?"":distrReqPlanDetailVOList.get(i).getQuantity().toString());
                        	  this.createCell(11,distrReqPlanDetailVOList.get(i).getRemark()==null?"":distrReqPlanDetailVOList.get(i).getRemark());
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
	public void getReqPlanOrderList(Page<OrderReportVo<DistrReqPlanReportVo>> page, RequestDistrReqPlan requestDistrReqPlan) {
		OrderReportVo orderReportVo=new OrderReportVo();
		if(requestDistrReqPlan.getPageNo()!=null &&requestDistrReqPlan.getPageSize()!=null){
			requestDistrReqPlan.setPageNo(page.getStart());
		}
		int count=distrReqPlanMapper.getReqPlanOrderListCount(requestDistrReqPlan);
		Integer sortDate=requestDistrReqPlan.getSortDate();
		Integer sortCode=requestDistrReqPlan.getSortCode();
		String sort="";
		if(sortDate==null&&sortCode==null){
			sort="";
		}
		if(sortDate!=null&&sortDate==0){
			sort+="a.plan_date,";
		}
		if(sortDate!=null&&sortDate==1){
			sort+="a.plan_date desc,";
		}
		if(sortCode!=null&&sortCode==0){
			sort+="a.code,";
		}
		if(sortCode!=null&&sortCode==1){
			sort+="a.code desc,";
		}
		if(!"".equals(sort)){
			sort=sort.substring(0,sort.length()-1);
		}
		requestDistrReqPlan.setSort(sort);
		List<DistrReqPlanReportVo> distrReqPlanVOS=distrReqPlanMapper.getReqPlanOrderList(requestDistrReqPlan);
		requestDistrReqPlan.setPageNo(null);
		requestDistrReqPlan.setPageSize(null);
		List<DistrReqPlanReportVo> distrReqPlanVOS2=distrReqPlanMapper.getReqPlanOrderList(requestDistrReqPlan);
		BigDecimal quantity=new BigDecimal(0);
		for (DistrReqPlanReportVo distrReqPlanVO : distrReqPlanVOS2) {
			quantity=quantity.add(distrReqPlanVO.getQuantity());
		}
		page.setTotalRecord(count);
		orderReportVo.setDataList(distrReqPlanVOS);
		orderReportVo.setQuantity(quantity);
		page.setResult(orderReportVo);
	}

	@Override
	public void excelExport(OutputStream output, List<DistrReqPlanReportVo> distrReqPlanVOS, UserVO userVO) {
		//标题数据
		List<String> names = new ArrayList<>();
		names.add(userVO.getEnterpriseName());
		names.add("要货计划");
		//内容数据
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		map.put("planDateStr", "日期");
		map.put("code", "单号");
		map.put("enterpriseCode", "要货单位编码");
		map.put("enterpriseName", "要货单位名称");
		map.put("plannerName", "要货人员");
		map.put("requestTypeStr", "配货类型");
		map.put("goodsCode", "商品编码");
		map.put("barcode", "条形码");
		map.put("goodsGenericName", "通用名称");
		map.put("goodsName", "商品名称");
		map.put("dosageName", "剂型");
		map.put("goodsSpecification", "规格");
		map.put("unitName", "单位");
		map.put("manufacturer", "生产厂商");
		map.put("goodsPlace", "产地");
		map.put("approvalNumber", "批准文号");
		map.put("quantity", "数量");
		map.put("statusStr", "状态");
		map.put("businessVarietyName", "品种类型");
		map.put("categoryName", "商品分类");
		map.put("goodsAttributeName", "商品属性");
		map.put("domesticImportDesc", "国产/进口");
		map.put("managementScopeName", "经营范围");
		map.put("specialDrugName", "特殊管理药品");
		map.put("inChargeDrugName", "专管药品");
		map.put("limitQuantity", "限购数量");
		map.put("storageTempDesc", "贮藏温度");
		map.put("storageConditionName", "贮藏条件");
		map.put("qualityPeriodDesc", "保质期");
		BigDecimal quantity=new BigDecimal(0);
		for (DistrReqPlanReportVo distrReqPlanVO : distrReqPlanVOS) {
			quantity=quantity.add(distrReqPlanVO.getQuantity());
		}
		List<String> needTotalName=new ArrayList<>();
		needTotalName.add("quantity");
		purchaseGeneralComponent.commExcelExport(output, map, distrReqPlanVOS, names, null, quantity.toString(), false, needTotalName);
	}

	@Override
	public List<EnterpriseReqPlanVO> getEnterpriseByDistrType(UserVO userVO, Integer distrType) {
		if(distrType==null || !distrType.equals(DistributionType.SWAP_BETWEEN_STORES.getCode())) throw new BusinessException(SysCode.SYS_PARAM_ERROR.getCode(),"配货类型不匹配");
		Map<String,Object> param=new HashMap<>();
		param.put("id", userVO.getEnterpriseId());
		param.put("parentId", userVO.getParentId());
		return distrReqPlanMapper.getEnterpriseByDistrType(param);
	}

	@Override
	public List<GoodsDistrReqPlanVO> getGoodsListByDistrType(UserVO userVO, Integer distrType, Long supplierId,String param) {
		if(distrType==null || !distrType.equals(DistributionType.DIRECT_DISTRIBUTION.getCode())) throw new BusinessException(SysCode.SYS_PARAM_ERROR.getCode(),"配货类型不匹配");
		Map<String,Object> paramap=new HashMap<>();
		
		paramap.put("enterpriseId",userVO.getEnterpriseId());
		paramap.put("supplierId", supplierId);
		paramap.put("parentId", userVO.getParentId());
		paramap.put("param", param);
		paramap.put("chainType",userVO.getChainType());

		Supplier supplier = supplierMapper.selectByPrimaryKey(supplierId);
		if(supplier == null) throw  new BusinessException("供货单位不存在");
		if(StringUtils.isNotBlank(supplier.getBusinessScopeId())){
			paramap.put("scopeList",Arrays.asList(supplier.getBusinessScopeId().split(",")));
		}

		List<GoodsDistrReqPlanVO> resList = distrReqPlanMapper.getGoodsListByDistrType(paramap);
		if(!resList.isEmpty()) {
			List<Long> goodsIds=new ArrayList<>(resList.size());
			for(GoodsDistrReqPlanVO vo:resList) {
				goodsIds.add(vo.getGoodsId());
			}
			paramap.put("list", goodsIds);
			//获取门店的库存可用量
			List<DistrReqPlanSumVO> sumList1 = distrReqPlanMapper.getStockSumQuantity(paramap);
			if(!sumList1.isEmpty()) {
				for(GoodsDistrReqPlanVO vo:resList) {
					for(DistrReqPlanSumVO s:sumList1) {
						if(s.getGoodsId().equals(vo.getGoodsId())) {
							vo.setUsableQuantity(s.getSumQuantity());
							break;
						}
					}
					// 获取配货价格
					BigDecimal distrPrice = commonComponent.getDistrPrice(userVO.getParentId(),userVO.getEnterpriseId(),vo.getGoodsId(),null,BigDecimal.ONE);
					vo.setDistrPrice(distrPrice);
				}
			}
			paramap.put("enterpriseId",userVO.getParentId());
			//直接调配默认配货单位为总部
			//获取配送单位的库存可用量
			/*List<DistrReqPlanSumVO> sumList2 = distrReqPlanMapper.getStockSumQuantity(paramap);
			if(!sumList2.isEmpty()) {
				for(GoodsDistrReqPlanVO vo:resList) {
					for(DistrReqPlanSumVO s:sumList1) {
						if(s.getGoodsId().equals(vo.getGoodsId())) {
							vo.setOrgUsableQuantity(s.getSumQuantity());
							break;
						}
					}
				}
			}*/
		}
		return resList;
	}

	@Override
	public List<DistrReqPlanOutVO> getSupplierList(UserVO userVO,Integer distrType) {
		if(distrType == null ) throw new BusinessException("缺少配货类型");
		List<DistrReqPlanOutVO> list=new ArrayList<>();
		DistrReqPlanOutVO outVO = null;
		if(distrType.equals(DistributionType.SWAP_BETWEEN_STORES.getCode()) || distrType.equals(DistributionType.DIRECT_DISTRIBUTION.getCode())) {
			if(distrType.equals(DistributionType.DIRECT_DISTRIBUTION.getCode())) {
				Map<String,Object> paramap = new HashMap<>();
				paramap.put("parentId",userVO.getParentId());
				paramap.put("enterpriseId",userVO.getEnterpriseId());
				paramap.put("chainType",userVO.getChainType());
				List<Supplier> suppliers = distrReqPlanMapper.getSupplierList(paramap);
				for(Supplier s:suppliers) {
					outVO = new DistrReqPlanOutVO();
					outVO.setOutboundUnitId(s.getId());
					outVO.setOutboundUnitCode(s.getCode());
					outVO.setOutboundUnitName(s.getName());
					list.add(outVO);
				}
			}else {
				List<EnterpriseReqPlanVO> reslist = getEnterpriseByDistrType(userVO, distrType);
				for(EnterpriseReqPlanVO s:reslist) {
					outVO = new DistrReqPlanOutVO();
					outVO.setOutboundUnitId(s.getId());
					outVO.setOutboundUnitCode(s.getCode());
					outVO.setOutboundUnitName(s.getName());
					list.add(outVO);
				}
			}
			return list;
		}else {
			return new ArrayList<>();
		}
	}
	@Override
	public Integer getEnterpriseDistrType(UserVO userVO) {
		Integer distributionManage = distrReqPlanMapper.getEnterpriseDistrType(userVO.getEnterpriseId());
		if(distributionManage == null)  throw new BusinessException("配货管理设置不存在");
		return distributionManage;
	}
	  /**
     * 分页获取商品列表
     * @throws ParseException
     * */
    @Override
    public void getGoodsByPage(Page page, Map map, UserVO userVO) throws ParseException{

    	Long totalRecord = 0L;
    	List<GoodsDistrReqPlanVO> goodsList = new ArrayList<>();

		Integer requestType = (Integer) map.get("requestType");

		ManageConfig manage = manageConfigMapper.selectByCurrentUser(userVO);//判断质量流程是否开启
		if(manage.getBusinessControl() == 1) map.put("sign", "sign");

		if(DistributionType.SWAP_BETWEEN_STORES.getCode() == requestType ) {// 门店间调剂
			goodsList = distrReqPlanMapper.queryQuantityGoodsList(map);
			totalRecord = distrReqPlanMapper.queryQuantityGoodsCount(map);
		} else { //
			totalRecord=distrReqPlanMapper.queryGoodsCount(map);
			goodsList=distrReqPlanMapper.queryGoodsList(map);
		}

		// 查询总部的
		for(GoodsDistrReqPlanVO planVO:goodsList){
			// 获取总部的库存
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("enterpriseId",map.get("distrUnitId"));
			paramMap.put("goodsId",planVO.getGoodsId());
			paramMap.put("today",new Date());
			BigDecimal orgUsableQuantity = stockMapper.getQualifiedUsableQuantity(paramMap);
			planVO.setOrgUsableQuantity(orgUsableQuantity);

			paramMap.put("enterpriseId",userVO.getEnterpriseId());
			BigDecimal usableQuantity = stockMapper.getQualifiedUsableQuantity(paramMap);
			planVO.setUsableQuantity(usableQuantity);

			// 获取配货价格
			BigDecimal distrPrice = commonComponent.getDistrPrice(userVO.getParentId(),userVO.getEnterpriseId(),planVO.getGoodsId(),null,BigDecimal.ONE);
			planVO.setDistrPrice(distrPrice);

		}

    	page.setTotalRecord(totalRecord.intValue());
    	page.setResult(goodsList);
    }
    @Override
    public DraftCacheVO<DistrReqPlanVO> saveDraftCache(UserVO userVO, DraftCacheVO<DistrReqPlanVO> draftCache) throws BusinessException{
    	if(draftCache.getOrderData()==null){
    		throw new BusinessException("实体不能为空");
    	}
        String redisKeyValue = draftCache.getId();

        DraftCacheVO<DistrReqPlanVO> draftCacheVO = new DraftCacheVO();

        draftCacheVO.setOrderCode(OrderRule.REQUIRE_PLAN.getCodePrefix());

        draftCacheVO.setEnterpriseId(userVO.getEnterpriseId());
        DistrReqPlanVO vo=draftCache.getOrderData();
        User user=userMapper.selectByPrimaryKey(vo.getPlannerId());
		 if(user==null){
			throw new BusinessException("找不到该人员，请确认人员ID"+vo.getPlannerId()+"是否正确");
		 }
		 vo.setPlannerId(user.getId());
		 vo.setPlannerName(user.getName());
		 vo.setPlannerCode(user.getCode());
        draftCacheVO.setOrderData(draftCache.getOrderData());
        draftCacheVO.setId(redisKeyValue);
		draftCacheVO = redisComponent.saveDraftCacheVO(draftCacheVO);

		return draftCacheVO;
    }

    @Override
    public void removeDraftCach(Long enterpriseId, String type, String redisKeyValue) throws BusinessException{

        redisComponent.removeDraftCacheVO(enterpriseId,type,redisKeyValue);
    }

    @Override
    public List<DraftCacheVO> getDraftCacheVO(UserVO userVO){
		Class<DistrReqPlanVO> clazz = DistrReqPlanVO.class;
        return redisComponent.getDraftCacheVO(userVO.getEnterpriseId(),OrderRule.REQUIRE_PLAN.getCodePrefix(),clazz);
    }
	@Override
	public void getGoodsListByDistrTypePage(Page page, Integer pageNO, Integer pageSize, String sortField, String sort,
			UserVO userVO, Integer distrType, Long supplierId, String param) {
		if(distrType==null || !distrType.equals(DistributionType.DIRECT_DISTRIBUTION.getCode())) throw new BusinessException(SysCode.SYS_PARAM_ERROR.getCode(),"配货类型不匹配");
		Map<String,Object> paramap=new HashMap<>();

		paramap.put("enterpriseId",userVO.getEnterpriseId());
		paramap.put("supplierId", supplierId);
		paramap.put("parentId", userVO.getParentId());
		paramap.put("pageNo", pageNO);
		paramap.put("pageStart",(pageNO - 1) * pageSize);

		paramap.put("pageSize", pageSize);
		paramap.put("sortField", sortField);
		paramap.put("sort", sort);
		paramap.put("param", param);
		paramap.put("chainType",userVO.getChainType());
		List<GoodsDistrReqPlanVO> resList = distrReqPlanMapper.getGoodsListByDistrTypePage(paramap);
		Long count=distrReqPlanMapper.getGoodsListByDistrTypeCount(paramap);
		if(!resList.isEmpty()) {
			List<Long> goodsIds=new ArrayList<>(resList.size());
			for(GoodsDistrReqPlanVO vo:resList) {
				goodsIds.add(vo.getGoodsId());
			}
			paramap.put("list", goodsIds);
			//获取门店的库存可用量
			List<DistrReqPlanSumVO> sumList1 = distrReqPlanMapper.getStockSumQuantity(paramap);
			if(!sumList1.isEmpty()) {
				for(GoodsDistrReqPlanVO vo:resList) {
					for(DistrReqPlanSumVO s:sumList1) {
						if(s.getGoodsId().equals(vo.getGoodsId())) {
							vo.setUsableQuantity(s.getSumQuantity());
							break;
						}
					}
					// 获取配货价格
					BigDecimal distrPrice = commonComponent.getDistrPrice(userVO.getParentId(),userVO.getEnterpriseId(),vo.getGoodsId(),null,BigDecimal.ONE);
					vo.setDistrPrice(distrPrice);
				}
			}
			paramap.put("enterpriseId",userVO.getParentId());
		}
		if(count==null){
    		count=0L;
    	}
    	page.setTotalRecord(count.intValue());
    	page.setResult(resList);
	}
	
	@Override
	public List<GoodsDistrReqPlanAnalysisVO> getGoodsDistrReqPlanVOlist(Map map, AnalyseStockVO analyseStockVO) {
		// 返回值
		List<GoodsDistrReqPlanAnalysisVO> goodsDistrReqPlanAnalysisVOList=new ArrayList<GoodsDistrReqPlanAnalysisVO>();
		//按照安全库存采购
		List<GoodsDistrReqPlanAnalysisVO> goodsDistrReqPlanAnalysisVOListSafety=new ArrayList<GoodsDistrReqPlanAnalysisVO>();
		//按照缺断货数量采购
		List<GoodsDistrReqPlanAnalysisVO> goodsDistrReqPlanAnalysisVOListLimit=new ArrayList<GoodsDistrReqPlanAnalysisVO>();
		//按照动态存量采购
		List<GoodsDistrReqPlanAnalysisVO> goodsDistrReqPlanAnalysisVOListHalf=new ArrayList<GoodsDistrReqPlanAnalysisVO>();
		if(!analyseStockVO.getRequestType().equals(DistributionType.DISTRIBUTION_HEAD.getCode()) &&	
				!analyseStockVO.getRequestType().equals(DistributionType.DIRECT_DISTRIBUTION.getCode())) {
			return goodsDistrReqPlanAnalysisVOList;
		}
		if(analyseStockVO.getRequestType().equals(DistributionType.DIRECT_DISTRIBUTION.getCode())) {
			//如果是直调配送
			Supplier supplier = supplierMapper.selectByPrimaryKey(analyseStockVO.getOutboundUnitId());
 			if(supplier == null)  throw new BusinessException("供货单位不存在");
		}
		if(map.get("safety")!=null){//按照安全库存采购
			if(map.get("safety").equals("true")){
				goodsDistrReqPlanAnalysisVOListSafety= distrReqPlanMapper.selectGoodsListBySafetyStock(map);
				if(CollectionUtils.isNotEmpty(goodsDistrReqPlanAnalysisVOListSafety)){
			    //筛选出安全库存大于可用库存的商品
				Iterator<GoodsDistrReqPlanAnalysisVO> listAnalySis = goodsDistrReqPlanAnalysisVOListSafety.iterator();
				while(listAnalySis.hasNext()) {
					GoodsDistrReqPlanAnalysisVO vo = listAnalySis.next();
					//若库存可用量不为null或是不小于安全库存则去除
					if(vo.getUsableQuantity() != null && vo.getUsableQuantity().compareTo(vo.getSafetyStock()) != -1) {
						listAnalySis.remove();
					}
				}
                for(GoodsDistrReqPlanAnalysisVO vo:goodsDistrReqPlanAnalysisVOListSafety){
                	vo.setQuantity(vo.getSafetyStock().subtract(vo.getStockQuantityTotal()));//安全库存-库存为要货的数量
                }
				goodsDistrReqPlanAnalysisVOList=goodsDistrReqPlanAnalysisVOListSafety;
				}
			}
		}
        if(map.get("lack")!=null){//按照缺断货数量采购
        	if(map.get("lack").equals("true")){
        		int lackQuantityDay = analyseStockVO.getLackQuantity().negate().intValue();
				Calendar begin=Calendar.getInstance();
				begin.setTime(new Date());
				begin.add(Calendar.DAY_OF_MONTH,lackQuantityDay);
				Date startTime = begin.getTime();
				analyseStockVO.setStartTime(startTime);
				map.put("startTime", startTime);
        	   goodsDistrReqPlanAnalysisVOListLimit= distrReqPlanMapper.selectGoodsListByWeekLimit(map);
        	   //处理多选时的逻辑
        	   if(CollectionUtils.isNotEmpty(goodsDistrReqPlanAnalysisVOListLimit)){//如果按照缺断货数量采购有值的情况
        		   Iterator<GoodsDistrReqPlanAnalysisVO> listLimit = goodsDistrReqPlanAnalysisVOListLimit.iterator();
        		   Iterator<GoodsDistrReqPlanAnalysisVO> listAnalySis = null;
        		   while(listLimit.hasNext()){
        			   GoodsDistrReqPlanAnalysisVO limit= listLimit.next();
        			   listAnalySis = goodsDistrReqPlanAnalysisVOList.iterator();
        			   while(listAnalySis.hasNext()){
        				   GoodsDistrReqPlanAnalysisVO analySis= listAnalySis.next();
        				   if(limit.getGoodsId().equals(analySis.getGoodsId())){
        					   if(limit.getQuantity()!=null && analySis.getQuantity()!=null){
        						   if(limit.getQuantity().compareTo(analySis.getQuantity())!=-1){
        							   listAnalySis.remove();
        						   }else {
        							   listLimit.remove();
        						   }
        						   break;
        					   }
        				   }
        			   }
        		   }
        		   goodsDistrReqPlanAnalysisVOList.addAll(goodsDistrReqPlanAnalysisVOListLimit);
        	}
		}
        if(map.get("dynamicStock")!=null){//按照动态存量采购
        	int dynamicStockQuantity=analyseStockVO.getDynamicStockQuantity().negate().intValue();
        	Calendar begin=Calendar.getInstance();
			begin.setTime(new Date());
			begin.add(Calendar.DAY_OF_MONTH,dynamicStockQuantity);
			Date startTime = begin.getTime();
			analyseStockVO.setStartTime(startTime);
			map.put("startTime", startTime);
        	BigDecimal undynamicStockQuantity=analyseStockVO.getUndynamicStockQuantity();//不满足多少天的销售
        	if(map.get("dynamicStock").equals("true")){
        	   goodsDistrReqPlanAnalysisVOListHalf=distrReqPlanMapper.selectGoodsListByWeekLimitHalfDay(map);
       		if(CollectionUtils.isNotEmpty(goodsDistrReqPlanAnalysisVOListHalf)){
       			Iterator<GoodsDistrReqPlanAnalysisVO> listHalf = goodsDistrReqPlanAnalysisVOListHalf.iterator();
       			while(listHalf.hasNext()){
       				GoodsDistrReqPlanAnalysisVO vo=listHalf.next();
       				if(vo!=null){
       					BigDecimal saleQuantityTotal=vo.getSaleQuantityTotal();//30天内销售总额
       					BigDecimal usableQuantity=vo.getUsableQuantity();//剩余库存可用量
       					BigDecimal averageQuantity=saleQuantityTotal.divide(analyseStockVO.getDynamicStockQuantity(), 2, BigDecimal.ROUND_HALF_UP);//每天的销售量保留两位小数
       					BigDecimal stockDay=usableQuantity.divide(averageQuantity,0,BigDecimal.ROUND_HALF_DOWN);//可销售的库存件数，除不尽的时候不保留小数
       					if(stockDay.compareTo(undynamicStockQuantity) >= 0){//如果可销售的大于15天的时候，不需要要货，从列表中去除
       						listHalf.remove();
          				 }else{
          					  BigDecimal limitQuantity=undynamicStockQuantity.subtract(stockDay);//与15比较差多少天
         					  BigDecimal reqQuantity=limitQuantity.multiply(averageQuantity).setScale(0, BigDecimal.ROUND_HALF_UP);//需要采购的商品数量
         					  vo.setQuantity(reqQuantity);
          				 }
          				 }
       				}
       			}
       		  }
       		//处理多选时的逻辑
       	   if(CollectionUtils.isNotEmpty(goodsDistrReqPlanAnalysisVOListHalf)){//如果按照缺断货数量采购有值的情况
       	   Iterator<GoodsDistrReqPlanAnalysisVO> listHalf = goodsDistrReqPlanAnalysisVOListHalf.iterator();
  		   Iterator<GoodsDistrReqPlanAnalysisVO> listAnalySis = null;
  		   while(listHalf.hasNext()){
  			 GoodsDistrReqPlanAnalysisVO half=listHalf.next();
  			 listAnalySis = goodsDistrReqPlanAnalysisVOList.iterator();
  			   while(listAnalySis.hasNext()){
  				 GoodsDistrReqPlanAnalysisVO anlySis=listAnalySis.next();
  				   if(half.getGoodsId().equals(anlySis.getGoodsId())){
  					   if(half.getQuantity()!=null && anlySis.getQuantity()!=null){
  						   if(half.getQuantity().compareTo(anlySis.getQuantity()) != -1){
  							 listAnalySis.remove();
  						   }else {
  							 listHalf.remove();
  						   }
  						   break;
  					   }
  				   }
  			   }
  		   }
    		   goodsDistrReqPlanAnalysisVOList.addAll(goodsDistrReqPlanAnalysisVOListHalf);
    	     }
           }
		}
        //默认考虑在途库存
		List<OnPassageStockVO> onPassageStockList = distrReqPlanMapper.selectOnPassageStock(map);
		if (CollectionUtils.isNotEmpty(onPassageStockList)) {
			Iterator<OnPassageStockVO> onPassage = onPassageStockList.iterator();
			Iterator<GoodsDistrReqPlanAnalysisVO> listAnalySis = null;
			while (onPassage.hasNext()) {
				OnPassageStockVO op = onPassage.next();
				listAnalySis = goodsDistrReqPlanAnalysisVOList.iterator();
				while (listAnalySis.hasNext()) {
					GoodsDistrReqPlanAnalysisVO vo = listAnalySis.next();
					if (vo.getGoodsId().equals(op.getGoodsId())) {
						// 如果在途库存的存量没有计划的多，那么用计划数量减去在途库存量
						if (vo.getQuantity().compareTo(op.getQuantity()) == 1) {
							vo.setQuantity(vo.getQuantity().subtract(op.getQuantity()).setScale(0, BigDecimal.ROUND_HALF_UP));
						} else {
							listAnalySis.remove();
						}
						break;
					}
				}
			}
		}
		return goodsDistrReqPlanAnalysisVOList;
	}
}
