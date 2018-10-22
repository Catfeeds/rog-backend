package com.rograndec.feijiayun.chain.business.goods.set.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.SysType;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.string.ChineseString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsCategoryMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsCategory;
import com.rograndec.feijiayun.chain.business.goods.set.service.SetCategoryService;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsCategoryVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zeshi.sun on 2017/9/5.
 */
@Service
@Transactional(rollbackFor = Exception.class)

public class SetCategoryServiceImpl implements SetCategoryService {

	@Autowired
	GoodsCategoryMapper goodsCategoryMapper;

	@Override
	public List<GoodsCategory> getCategory(Long enterpriseId) {
		List<GoodsCategory> list = goodsCategoryMapper.getCategory(enterpriseId);
		return list;
	}

	@Override
	public Result<String> insertSetClassify(GoodsCategory goodsCategory, UserVO loginUser) throws Exception {

		Result<String> result = new Result<>();

		// 汉字检查

		ChineseString.checkCode(goodsCategory.getCode());

		checkRepeated(goodsCategory,loginUser);

		// 获取父级 type
		GoodsCategory category = goodsCategoryMapper.selectByPrimaryKey(goodsCategory.getParentCategoryId());


		if(category == null){
			throw new BusinessException("保存新增的商品设置-分类错误：父类不能为空");
		}
		goodsCategory.setType(category.getType());
		goodsCategory.setEnterpriseId(loginUser.getEnterpriseId());
		goodsCategory.setCreaterId(loginUser.getUserId());
		goodsCategory.setCreaterCode(loginUser.getUserCode());
		goodsCategory.setCreaterName(loginUser.getUserName());
		goodsCategory.setCreateTime(new Date());
		goodsCategory.setUpdateTime(new Date());
		goodsCategory.setModifierCode(loginUser.getUserCode());
		goodsCategory.setModifierId(loginUser.getUserId());
		goodsCategory.setModifierName(loginUser.getUserName());
		goodsCategory.setSysType(SysType.CUSTOMIZE.getCode());
		goodsCategory.setId(null);

		goodsCategoryMapper.insertSelective(goodsCategory);

		result.setBizCodeSuccessInfo(SysCode.SUCCESS, "保存新增的商品设置-分类成功");
		return result;
	}

	private void checkRepeated(GoodsCategory goodsCategory, UserVO userVO) {
		// 编码和名称不能重复
		List<GoodsCategory> categorys = goodsCategoryMapper.selectByCodeOrName(userVO.getEnterpriseId(),goodsCategory.getCode(),null,goodsCategory.getId());
		if (categorys.size() > 0) {
			throw new BusinessException("保存新增的商品设置-分类错误：编码重复");

		}

		List<GoodsCategory> categoryList = goodsCategoryMapper.selectByCodeOrName(userVO.getEnterpriseId(),null,goodsCategory.getName(),goodsCategory.getId());
		if (categoryList.size() > 0) {
			throw new BusinessException("保存新增的商品设置-分类错误：名称重复");

		}

		// 当前节点下 有关联商品，不允许创建分类

		List<Goods> goods = goodsCategoryMapper.selectGoodsByClassifyId(goodsCategory.getParentCategoryId(),userVO.getEnterpriseId());
		if(goods != null){
			if(goods.size() > 0){
				throw new BusinessException("保存新增的商品设置-分类错误：该分类下已经有关联商品，不允许添加下级分类");
			}
		}



	}

	@Override
	public int updateSetClassify(GoodsCategory goodsCategory, UserVO loginUser) throws Exception {

		checkRepeated(goodsCategory, loginUser);

		goodsCategory.setModifierId(loginUser.getUserId());
		goodsCategory.setModifierCode(loginUser.getUserCode());
		goodsCategory.setModifierName(loginUser.getUserName());
		goodsCategory.setUpdateTime(new Date());

		GoodsCategory goodsCategory1 = goodsCategoryMapper.selectByPrimaryKey(goodsCategory.getId());

		if(goodsCategory1.getSysType().equals(SysType.SYSTEM.getCode())){
			throw  new BusinessException(SysCode.FAIL.getCode(),"系统默认分类，不允许修改");
		}

		if(!goodsCategory1.getStatus().equals(goodsCategory.getStatus())){


			if(!canDelete(goodsCategory.getId(),loginUser.getEnterpriseId())){
				throw  new BusinessException(SysCode.FAIL.getCode(),"商品信息存在关联数据，不允许修改状态");
			}
		}

		return goodsCategoryMapper.updateByPrimaryKeySelective(goodsCategory);
	}

	@Override
	public int deleteSetClassify(Long id) throws Exception {

		GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(id);
		if(goodsCategory == null){
			return 1;
		}

		if(goodsCategory.getSysType().equals(SysType.SYSTEM.getCode())){
			throw  new BusinessException(SysCode.FAIL.getCode(),"系统默认分类，不允许删除");
		}

		return goodsCategoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public boolean canDelete(Long id,Long enterpriseId) throws Exception {
		// 是否有关联的商品
		List<Goods> goods = goodsCategoryMapper.selectGoodsByClassifyId(id, enterpriseId);
		return goods.size() == 0;
	}

	@Override
	public List<TreePOJO<GoodsCategoryVO>> getClassify(Long enterpriseId, Boolean maintain) throws Exception {
		List<TreePOJO<GoodsCategoryVO>> listParent = new ArrayList<>();

		List<GoodsCategoryVO> goodsCategoryVOs = new ArrayList<>();
		if (maintain){
			goodsCategoryVOs = goodsCategoryMapper.selectByEnterpriseId(enterpriseId,null);

		} else {
			goodsCategoryVOs = goodsCategoryMapper.selectByEnterpriseId(enterpriseId,EnableStatus.ENABLE.getStatus());

		}
		// 查找所有的父级
		for (GoodsCategoryVO goodsCategoryVO : goodsCategoryVOs) {
			if (0 == goodsCategoryVO.getParentCategoryId()) {
				TreePOJO<GoodsCategoryVO> t = new TreePOJO<GoodsCategoryVO>();
				t.setData(goodsCategoryVO);
				t.setId(goodsCategoryVO.getId());
				t.setParentId(null);
				t.setLabel(goodsCategoryVO.getCode() + "-" + goodsCategoryVO.getName());
				listParent.add(t);
			}
		}
		// 递归设置子级
		for (TreePOJO<GoodsCategoryVO> tree : listParent) {
			GoodsCategoryVO goodsCategoryVO = tree.getData();
			List<TreePOJO<GoodsCategoryVO>> chileren = getChild(goodsCategoryVO.getId(), goodsCategoryVOs);
			tree.setChildren(chileren == null ? new ArrayList<>() : chileren);
		}

		if (maintain) {
			return getListClassifyMain(listParent,enterpriseId);
		} else {
			return getListClassify(listParent);
		}
	}

	private List<TreePOJO<GoodsCategoryVO>> getChild(Long id, List<GoodsCategoryVO> p_area) {
		List<TreePOJO<GoodsCategoryVO>> childList = new ArrayList<>();
		// 遍历第一层的 子级
		for (GoodsCategoryVO goodsCategoryVO : p_area) {
			if (goodsCategoryVO.getParentCategoryId().equals(id)) {
				TreePOJO<GoodsCategoryVO> tree = new TreePOJO<GoodsCategoryVO>();
				tree.setData(goodsCategoryVO);
				tree.setId(goodsCategoryVO.getId());
				tree.setParentId(id);
				tree.setLabel(goodsCategoryVO.getCode() + "-" + goodsCategoryVO.getName());
				childList.add(tree);
			}
		}
		for (TreePOJO<GoodsCategoryVO> tree : childList) {
			for (GoodsCategoryVO goodsCategoryVO : p_area) {
				GoodsCategoryVO goodsCategorys = (GoodsCategoryVO) tree.getData();
				if (goodsCategorys.getId().equals(goodsCategoryVO.getParentCategoryId())) {
					tree.setChildren(getChild(goodsCategorys.getId(), p_area));
				}
			}
		}
		return childList;
	}

	/**
	 * 全部为false
	 * 
	 * @param listParent
	 * @return
	 */
	private List<TreePOJO<GoodsCategoryVO>> getListClassify(List<TreePOJO<GoodsCategoryVO>> listParent) {
		for (TreePOJO<GoodsCategoryVO> tree : listParent) {
			tree.setSupplierShow(false);
			tree.setOpen(true);// 默认全打开
			if (tree.getChildren() == null) {
				tree.setLeaf(true);
			} else if (tree.getChildren().isEmpty() || tree.getChildren().size() == 0) {
				tree.setLeaf(true);
			} else {
				getListClassify(tree.getChildren());
			}
		}
		return listParent;
	}

	private List<TreePOJO<GoodsCategoryVO>> getListClassifyMain(List<TreePOJO<GoodsCategoryVO>> classifyTree, Long enterpriseId) throws Exception {
		for (TreePOJO<GoodsCategoryVO> tree : classifyTree) {
			GoodsCategoryVO category = tree.getData();

			// 是否为叶子节点
			if (tree.getChildren() == null) {
				tree.setLeaf(true);
				tree.setNodeShowDelete(true);
			} else if (tree.getChildren().isEmpty() || tree.getChildren().size() == 0) {
				tree.setLeaf(true);
				tree.setNodeShowDelete(true);
			} else {
				getListClassifyMain(tree.getChildren(), enterpriseId);
			}

			// 是否为可以修改 和 删除
			if (category.getSysType() == 1) {
				tree.setSupplierShow(false);
				tree.setNodeShowDelete(false);
				tree.getData().setUpdateFlag(Boolean.FALSE);
				tree.getData().setDeleteFlag(Boolean.FALSE);

			} else if(!canDelete(category.getId(),enterpriseId)){// 有关联数据
				tree.setNodeShowDelete(Boolean.FALSE);
				tree.getData().setDeleteFlag(Boolean.FALSE);


			}
		}

		return classifyTree;
	}

}
