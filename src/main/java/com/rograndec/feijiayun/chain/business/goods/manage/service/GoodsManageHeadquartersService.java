package com.rograndec.feijiayun.chain.business.goods.manage.service;

import com.rograndec.feijiayun.chain.business.goods.manage.entity.GoodsManage;
import com.rograndec.feijiayun.chain.business.goods.manage.vo.EnterpriseHeadquartersDetailVO;
import com.rograndec.feijiayun.chain.business.goods.manage.vo.EnterpriseHeadquartersVO;
import com.rograndec.feijiayun.chain.business.goods.manage.vo.GoodsHeadquartersDetailVO;
import com.rograndec.feijiayun.chain.business.goods.manage.vo.GoodsHeadquartersVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;

public interface GoodsManageHeadquartersService {

	List<GoodsHeadquartersVO> selectGoodsHeadquartersVOPage(int pageNo,
			int pageSize, Long enterpriseId,String key, String order,
			String sort, Page page,UserVO userVO);

	List<GoodsHeadquartersDetailVO> selectGoodsHeadquartersDetail(
			Long enterpriseId, Long goodsId, Integer chainType,
			String key, String order, String sort);

	String saveGoodsHeadquartersDetail(UserVO loginUser,
			List<GoodsHeadquartersDetailVO> goodsDetailList)throws Exception;

	List<EnterpriseHeadquartersVO> selectEnterpriseHeadquartersVOPage(
			int pageNo, int pageSize, Long enterpriseId, Integer type, String key,
			String order, String sort, Page page);

	String saveGoodsDetailByEnterprise(EnterpriseHeadquartersDetailVO vo,
			UserVO loginUser)throws Exception;

	GoodsManage selectGoodsManageById(Long id);

}
