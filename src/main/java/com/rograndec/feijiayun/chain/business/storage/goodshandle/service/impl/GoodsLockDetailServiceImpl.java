package com.rograndec.feijiayun.chain.business.storage.goodshandle.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.service.GoodsLockDetailService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsLockDetail;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsLockDetailVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsLockDetailSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.dao.GoodsLockDetailMapper;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;


 /**
 * 
 * @ClassName: GoodsLockDetailServiceImpl   
 * @Description:  储存管理-商品处理-药品锁定明细-实现接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-09-27 17:26:57
 */
@Service
public class GoodsLockDetailServiceImpl implements GoodsLockDetailService {
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(GoodsLockDetailServiceImpl.class);
	
	@Autowired
	private GoodsLockDetailMapper goodsLockDetailMapper;
	
	@Override
	public List<GoodsLockDetailVO> getGoodsLockDetailData(UserVO userVO) throws Exception {
		List<GoodsLockDetailVO> list = null; //需修改
		return list;
	}

	@Override
	public int save(GoodsLockDetailSaveOrupdateVO goodsLockDetail, UserVO userVO) throws Exception {
		GoodsLockDetail copy = (GoodsLockDetail)EntityUtils.reflectAddSetDefaultValue(new GoodsLockDetail().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsLockDetail,copy);
		return goodsLockDetailMapper.insertSelective(copy);
	}

	@Override
	public int update(GoodsLockDetailSaveOrupdateVO goodsLockDetail,UserVO userVO) throws Exception {
		GoodsLockDetail copy = (GoodsLockDetail)EntityUtils.reflectUpdateSetDefaultValue(new GoodsLockDetail().getClass(),userVO);
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsLockDetail,copy);
		return goodsLockDetailMapper.updateByPrimaryKeySelective(copy);
	}

	@Override
	public int delete(Long id) throws Exception {
		return goodsLockDetailMapper.deleteByPrimaryKey(id);
	}
	
}
