package com.rograndec.feijiayun.chain.business.retail.pos.service.impl;
import java.util.List;

import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleDetailMapper;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.deleteValid.DeleteValidSerivce;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseCargoArea;
import com.rograndec.feijiayun.chain.business.basic.warehouse.service.WarehouseService;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosClerkMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosClerk;
import com.rograndec.feijiayun.chain.business.retail.pos.service.PosClerkService;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosClerkSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosClerkVO;
import com.rograndec.feijiayun.chain.common.component.UserComponent;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;


 /**
 * 
 * @ClassName: PosClerkServiceImpl   
 * @Description:  零售管理-POS管理-营业人员-实现接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-21 15:59:33
 */
@Service
public class PosClerkServiceImpl implements PosClerkService,DeleteValidSerivce {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PosClerkServiceImpl.class);
	
	@Autowired
	private PosClerkMapper posClerkMapper;
	
	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private WarehouseService warehouseService;

	@Autowired
	private SaleDetailMapper saleDetailMapper;
	
	@Override
	public List<PosClerkVO> getPosClerkData(UserVO userVO) throws Exception {
		List<PosClerkVO> list = posClerkMapper.findByEnterpriseId(userVO.getEnterpriseId()); //需修改
		for(PosClerkVO vo : list) {
			vo.setShowStatus(vo.getStatus()==null?"":EnableStatus.getName(vo.getStatus()));
			Boolean valid = valid(vo);
			vo.setDeleteFlag(valid);
		}
		return list;
	}

	@Override
	public int save(PosClerkSaveOrupdateVO posClerk, UserVO userVO) throws Exception {
		PosClerk copy = (PosClerk)EntityUtils.reflectAddSetDefaultValue(new PosClerk().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(posClerk,copy);
		User user = userComponent.findUserByUserId(posClerk.getClerkId());
		copy.setClerkCode(user.getCode());
		copy.setClerkName(user.getName());
		setHq(posClerk.getCargoAreaId(), copy);
		return posClerkMapper.insertSelective(copy);
	}

	@Override
	public int update(PosClerkSaveOrupdateVO posClerk,UserVO userVO) throws Exception {
		PosClerk copy = (PosClerk)EntityUtils.reflectUpdateSetDefaultValue(new PosClerk().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(posClerk,copy);
		setHq(posClerk.getCargoAreaId(), copy);
		return posClerkMapper.updateByPrimaryKeySelective(copy);
	}

	@Override
	public int delete(Long id) throws Exception {
		if (saleDetailMapper.selectHasClerk(id) > 0){
			throw new BusinessException(SysCode.FAIL.getCode(),"当前营业人员被销售单引用,无法删除!");
		}
		return posClerkMapper.deleteByPrimaryKey(id);
	}
	
	private void setHq(Long id,PosClerk copy) {
		if(null != id) {
			WarehouseCargoArea wca = warehouseService.getWarehouseCargoAreaByKey(id);
			copy.setCargoAreaCode(wca.getCode());
			copy.setCargoAreaName(wca.getName());
		}
	}

	 @Override
	 public Boolean valid(Object object) {
		 PosClerkVO vo = (PosClerkVO)object;
		boolean flag = true;
		 if (saleDetailMapper.selectHasClerk(vo.getClerkId()) > 0){
			flag = false;
		 }
		 return flag;
	 }
 }
