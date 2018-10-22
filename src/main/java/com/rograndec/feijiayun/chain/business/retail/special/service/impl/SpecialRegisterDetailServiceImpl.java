package com.rograndec.feijiayun.chain.business.retail.special.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.rograndec.feijiayun.chain.business.retail.special.service.SpecialRegisterDetailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rograndec.feijiayun.chain.business.retail.special.entity.SpecialRegisterDetail;
import com.rograndec.feijiayun.chain.business.retail.special.vo.SpecialRegisterDetailVO;
import com.rograndec.feijiayun.chain.business.retail.special.vo.SpecialRegisterDetailSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.special.dao.SpecialRegisterDetailMapper;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;


 /**
 * 
 * @ClassName: SpecialRegisterDetailServiceImpl   
 * @Description:  零售管理-专管登记单品种明细-实现接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-09-22 16:34:07
 */
@Service
public class SpecialRegisterDetailServiceImpl implements SpecialRegisterDetailService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SpecialRegisterDetailServiceImpl.class);
	
	@Autowired
	private SpecialRegisterDetailMapper specialRegisterDetailMapper;
	
	@Override
	public List<SpecialRegisterDetailVO> getSpecialRegisterDetailData(UserVO userVO) throws Exception {
		List<SpecialRegisterDetailVO> list = null; //需修改
		return list;
	}

	@Override
	public int save(SpecialRegisterDetailSaveOrupdateVO specialRegisterDetail, UserVO userVO) throws Exception {
		SpecialRegisterDetail copy = (SpecialRegisterDetail)EntityUtils.reflectAddSetDefaultValue(new SpecialRegisterDetail().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialRegisterDetail,copy);
		return specialRegisterDetailMapper.insertSelective(copy);
	}

	@Override
	public int update(SpecialRegisterDetailSaveOrupdateVO specialRegisterDetail,UserVO userVO) throws Exception {
		SpecialRegisterDetail copy = (SpecialRegisterDetail)EntityUtils.reflectUpdateSetDefaultValue(new SpecialRegisterDetail().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialRegisterDetail,copy);
		return specialRegisterDetailMapper.updateByPrimaryKeySelective(copy);
	}

	@Override
	public int delete(Long id) throws Exception {
		return specialRegisterDetailMapper.deleteByPrimaryKey(id);
	}
	
}
