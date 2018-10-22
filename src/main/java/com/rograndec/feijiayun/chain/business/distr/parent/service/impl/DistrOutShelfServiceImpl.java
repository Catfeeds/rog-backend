package com.rograndec.feijiayun.chain.business.distr.parent.service.impl;

import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrOutShelfMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOutShelf;
import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrOutShelfService;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrOutShelfSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrOutShelfVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


 /**
 * 
 * @ClassName: DistrOutShelfServiceImpl   
 * @Description:  总部-配货出库-配货出库货位明细-实现接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-10-07 15:58:23
 */
@Service
public class DistrOutShelfServiceImpl implements DistrOutShelfService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(DistrOutShelfServiceImpl.class);
	
	@Autowired
	private DistrOutShelfMapper distrOutShelfMapper;
	
	@Override
	public List<DistrOutShelfVO> getDistrOutShelfData(UserVO userVO) throws Exception {
		List<DistrOutShelfVO> list = null; //需修改
		return list;
	}

	@Override
	public int save(DistrOutShelfSaveOrupdateVO distrOutShelf, UserVO userVO) throws Exception {
		DistrOutShelf copy = (DistrOutShelf)EntityUtils.reflectAddSetDefaultValue(new DistrOutShelf().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(distrOutShelf,copy);
		copy.setVerificationQuantity(distrOutShelf.getClearQuantity());
		copy.setUnverificationQuantity(distrOutShelf.getUnclearQuantity());
		return distrOutShelfMapper.insertSelective(copy);
	}

	@Override
	public int update(DistrOutShelfSaveOrupdateVO distrOutShelf,UserVO userVO) throws Exception {
		DistrOutShelf copy = (DistrOutShelf)EntityUtils.reflectUpdateSetDefaultValue(new DistrOutShelf().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(distrOutShelf,copy);
		return distrOutShelfMapper.updateByPrimaryKeySelective(copy);
	}

	@Override
	public int delete(Long id) throws Exception {
		return distrOutShelfMapper.deleteByPrimaryKey(id);
	}
	
}
