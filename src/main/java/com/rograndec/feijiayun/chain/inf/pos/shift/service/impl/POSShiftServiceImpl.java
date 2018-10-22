package com.rograndec.feijiayun.chain.inf.pos.shift.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosPayeeMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosPayee;
import com.rograndec.feijiayun.chain.business.retail.receipt.dao.ReceiptDetailMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.Sale;
import com.rograndec.feijiayun.chain.business.retail.shift.dao.PayeeOpeningShiftDetailMapper;
import com.rograndec.feijiayun.chain.business.retail.shift.dao.PayeeOpeningShiftMapper;
import com.rograndec.feijiayun.chain.business.retail.shift.entity.PayeeOpeningShift;
import com.rograndec.feijiayun.chain.business.retail.shift.entity.PayeeOpeningShiftDetail;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.inf.pos.shift.dao.POSShiftMapper;
import com.rograndec.feijiayun.chain.inf.pos.shift.service.POSShiftService;
import com.rograndec.feijiayun.chain.inf.pos.shift.vo.POSAddShiftVO;
import com.rograndec.feijiayun.chain.inf.pos.shift.vo.POSCommitShiftDetailVO;
import com.rograndec.feijiayun.chain.inf.pos.shift.vo.POSCommitShiftVO;
import com.rograndec.feijiayun.chain.inf.pos.shift.vo.POSPaymentListVO;
import com.rograndec.feijiayun.chain.inf.pos.shift.vo.POSShiftAddReturnVO;
import com.rograndec.feijiayun.chain.inf.pos.shift.vo.POSShiftVO;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;

@Service
public class POSShiftServiceImpl implements POSShiftService{
	
	
	
	@Autowired
	private POSShiftMapper pOSShiftMapper;
	
	@Autowired
    private ReceiptDetailMapper receiptDetailMapper;
	
	@Autowired
    private PayeeOpeningShiftMapper payeeOpeningShiftMapper;
	
	@Autowired
	private PayeeOpeningShiftDetailMapper payeeOpeningShiftDetailMapper;
	
	@Autowired
	private PosPayeeMapper posPayeeMapper;
	
	@Autowired
	private SaleMapper saleMapper;
	
	@SuppressWarnings("rawtypes")
	@Override
	public POSShiftVO getShiftByEnterpriseId(Long enterpriseId) throws Exception {
		POSShiftVO svo = pOSShiftMapper.getShiftByEnterpriseId(enterpriseId);
		BigDecimal totalPay = BigDecimal.ZERO;
		if(null != svo) {
			List<Map> list = receiptDetailMapper.selectPayTypeAmountByShiftId(svo.getId()+"");
			for(Map map : list) {
				POSPaymentListVO pvo = new POSPaymentListVO();
				pvo.setPayTypeId(Long.valueOf(map.get("pay_type_id")+""));
				pvo.setPayTypeName(map.get("pay_type_name")+"");
				pvo.setPayAmount(new BigDecimal(map.get("amount")+""));
				totalPay = totalPay.add(pvo.getPayAmount());
				svo.getPaymentList().add(pvo);
			}
			svo.setTotalPay(totalPay);
		}
		return svo;
	}
	
	
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public int saveShift(POSAddShiftVO svo,UserVO posLoginUser) throws Exception {
		PayeeOpeningShift psf = new PayeeOpeningShift();
		psf = (PayeeOpeningShift)EntityUtils.reflectAddSetDefaultValue(psf.getClass(), posLoginUser);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(svo,psf);
		psf.setStatus(EnableStatus.ENABLE.getStatus());
		PosPayee payee = posPayeeMapper.selectByPrimaryKey(svo.getPayeeId());
		if(null != payee) {
			psf.setPayeeCode(payee.getPayeeCode());
			psf.setPayeeName(payee.getPayeeName());
		} else {
			throw new BusinessException("款员id不存在");
		}
		return payeeOpeningShiftMapper.insertSelective(psf);
	}
	
	private ExecutorService fixedThread = Executors.newFixedThreadPool(50);

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public int updateShift(POSCommitShiftVO svo, UserVO posLoginUser) throws Exception {
		Date date = svo.getOpeningTime();
		String openingTime = DateUtils.DateToString(date, DateStyle.YYYY_MM_DD_HH_MM_SS);
		if(!DateUtils.isDateStr(openingTime)) {
			throw new BusinessException("开班的日期格式不对！必须是yyyy-MM-dd HH:mm:ss");
		}
		// 返回的主键id
		Long check = pOSShiftMapper.checkShift(posLoginUser.getEnterpriseId(), svo.getPayeeId(), openingTime);
		int result = 0;
		PayeeOpeningShift psf = new PayeeOpeningShift();
		if(null == check) {
			psf = (PayeeOpeningShift)EntityUtils.reflectAddSetDefaultValue(psf.getClass(), posLoginUser);
			CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(svo,psf);
			psf.setStatus(EnableStatus.ENABLE.getStatus());
			payeeOpeningShiftMapper.insertSelective(psf);
			result = psf.getId().intValue();
		} else {
			PayeeOpeningShift osf = payeeOpeningShiftMapper.selectByPrimaryKey(check);
			// 交班人员验证
			if(svo.getPayeeId().longValue() != osf.getPayeeId().longValue()) {
				throw new BusinessException("交班人员与开班人员不一致！");
			}
			psf = (PayeeOpeningShift)EntityUtils.reflectUpdateSetDefaultValue(psf.getClass(), posLoginUser);
			CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(svo,psf);
			psf.setId(osf.getId());
			payeeOpeningShiftMapper.updateByPrimaryKeySelective(psf);
			result = osf.getId().intValue();
		}
		svo.setId(psf.getId());
		
		if(null != svo.getOrderList()) {
			// 由于数据量大，异步处理
			fixedThread.execute(()->{
				try {
					// 生成交班细表数据
					for(POSCommitShiftDetailVO dtlvo : svo.getOrderList()) {
						PayeeOpeningShiftDetail pdtl = new PayeeOpeningShiftDetail();
						pdtl = (PayeeOpeningShiftDetail)EntityUtils.reflectAddSetDefaultValue(pdtl.getClass(), posLoginUser);
						CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(dtlvo,pdtl);
						pdtl.setDocId(svo.getId());
						pdtl.setStatus(EnableStatus.ENABLE.getStatus());
						// 设置销售单
						List<Sale> saleList = saleMapper.queryByCodeAndEnterpriseId(dtlvo.getBaseOrderCode(),posLoginUser.getEnterpriseId());
						if(null != saleList && saleList.size() > 0) {
							Sale sale = saleList.get(0);
							sale.setShiftFlag(EnableStatus.ENABLE.getStatus());
							saleMapper.updateByPrimaryKeySelective(sale);
						}else {
							throw new BusinessException("销售单数据未上传！");
						}
						Long conunt = pOSShiftMapper.checkDetailOrder(svo.getId(), dtlvo.getBaseOrderCode(), posLoginUser.getEnterpriseId());//验证重复数据
						if(null != saleList && 0 == conunt) {
							Sale sale = saleList.get(0);
							pdtl.setBaseOrderId(sale.getId());
							pdtl.setBaseOrderType(sale.getSaleType());
							pdtl.setBaseOrderCode(sale.getCode());
							pdtl.setBaseOrderDate(sale.getSaleDate());
							pdtl.setRemark("POS交班销售单数据");
							payeeOpeningShiftDetailMapper.insertSelective(pdtl);
						}
					}
				} catch (Exception e) {
					throw new BusinessException("生成交班细表数据错误");
				}
			});
		}
		return result;
	}


	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public POSShiftAddReturnVO saveReturnShift(POSAddShiftVO svo, UserVO posLoginUser) throws Exception {
		Date date = svo.getOpeningTime();
		String openingTime = DateUtils.DateToString(date, DateStyle.YYYY_MM_DD_HH_MM_SS);
		if(!DateUtils.isDateStr(openingTime)) {
			throw new BusinessException("开班的日期格式不对！必须是yyyy-MM-dd HH:mm:ss");
		}
		// 返回的主键id
		Long check = pOSShiftMapper.checkShift(posLoginUser.getEnterpriseId(), svo.getPayeeId(), openingTime);
		if(null == check) {
			PayeeOpeningShift psf = new PayeeOpeningShift();
			psf = (PayeeOpeningShift)EntityUtils.reflectAddSetDefaultValue(psf.getClass(), posLoginUser);
			CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(svo,psf);
			psf.setStatus(EnableStatus.ENABLE.getStatus());
			payeeOpeningShiftMapper.insertSelective(psf);
			check = psf.getId();
		}
		PayeeOpeningShift addpsf = payeeOpeningShiftMapper.selectByPrimaryKey(check);
		POSShiftAddReturnVO returnPsf = new POSShiftAddReturnVO();
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(addpsf,returnPsf);
		return returnPsf;
	}
	
	
	
}
