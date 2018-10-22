package com.rograndec.feijiayun.chain.business.retail.special.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.rograndec.feijiayun.chain.business.retail.special.service.SpecialRegisterShelfService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rograndec.feijiayun.chain.business.retail.special.entity.SpecialRegisterShelf;
import com.rograndec.feijiayun.chain.business.retail.special.vo.SpecialRegisterShelfVO;
import com.rograndec.feijiayun.chain.business.retail.special.vo.SpecialRegisterShelfSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.special.dao.SpecialRegisterShelfMapper;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;


 /**
 * 
 * @ClassName: SpecialRegisterShelfServiceImpl   
 * @Description:  零售管理-专管登记单货位明细-实现接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-09-22 16:34:35
 */
@Service
public class SpecialRegisterShelfServiceImpl implements SpecialRegisterShelfService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SpecialRegisterShelfServiceImpl.class);
	
	@Autowired
	private SpecialRegisterShelfMapper specialRegisterShelfMapper;
	
	@Override
	public List<SpecialRegisterShelfVO> getSpecialRegisterShelfData(UserVO userVO) throws Exception {
		List<SpecialRegisterShelfVO> list = null; //需修改
		return list;
	}

	@Override
	public int save(SpecialRegisterShelfSaveOrupdateVO specialRegisterShelf, UserVO userVO) throws Exception {
		SpecialRegisterShelf copy = (SpecialRegisterShelf)EntityUtils.reflectAddSetDefaultValue(new SpecialRegisterShelf().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialRegisterShelf,copy);
		return specialRegisterShelfMapper.insertSelective(copy);
	}

	@Override
	public int update(SpecialRegisterShelfSaveOrupdateVO specialRegisterShelf,UserVO userVO) throws Exception {
		SpecialRegisterShelf copy = (SpecialRegisterShelf)EntityUtils.reflectUpdateSetDefaultValue(new SpecialRegisterShelf().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(specialRegisterShelf,copy);
		return specialRegisterShelfMapper.updateByPrimaryKeySelective(copy);
	}

	@Override
	public int delete(Long id) throws Exception {
		return specialRegisterShelfMapper.deleteByPrimaryKey(id);
	}
	
}
