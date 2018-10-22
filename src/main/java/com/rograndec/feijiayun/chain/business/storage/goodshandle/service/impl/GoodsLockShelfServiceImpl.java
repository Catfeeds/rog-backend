package com.rograndec.feijiayun.chain.business.storage.goodshandle.service.impl;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.dao.GoodsLockShelfMapper;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsLockShelf;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.service.GoodsLockShelfService;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsLockShelfSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsLockShelfVO;
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
 * @ClassName: GoodsLockShelfServiceImpl   
 * @Description:  储存管理-商品处理-药品锁定货位明细-实现接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-09-27 17:27:10
 */
@Service
public class GoodsLockShelfServiceImpl implements GoodsLockShelfService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(GoodsLockShelfServiceImpl.class);
	
	@Autowired
	private GoodsLockShelfMapper goodsLockShelfMapper;
	
	@Override
	public List<GoodsLockShelfVO> getGoodsLockShelfData(UserVO userVO) throws Exception {
		List<GoodsLockShelfVO> list = null; //需修改
		return list;
	}

	@Override
	public int save(GoodsLockShelfSaveOrupdateVO goodsLockShelf, UserVO userVO) throws Exception {
		GoodsLockShelf copy = (GoodsLockShelf)EntityUtils.reflectAddSetDefaultValue(new GoodsLockShelf().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsLockShelf,copy);
		return goodsLockShelfMapper.insertSelective(copy);
	}

	@Override
	public int update(GoodsLockShelfSaveOrupdateVO goodsLockShelf,UserVO userVO) throws Exception {
		GoodsLockShelf copy = (GoodsLockShelf)EntityUtils.reflectUpdateSetDefaultValue(new GoodsLockShelf().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsLockShelf,copy);
		return goodsLockShelfMapper.updateByPrimaryKeySelective(copy);
	}

	@Override
	public int delete(Long id) throws Exception {
		return goodsLockShelfMapper.deleteByPrimaryKey(id);
	}
	
}
