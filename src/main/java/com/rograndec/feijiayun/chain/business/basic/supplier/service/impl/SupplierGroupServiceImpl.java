package com.rograndec.feijiayun.chain.business.basic.supplier.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.string.ChineseString;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierGroupMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierGroup;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierTreePOJO;
import com.rograndec.feijiayun.chain.business.basic.supplier.service.SupplierGroupService;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierBasicVO;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierGroupConnectVO;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierGroupTreeVO;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierGroupVO;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.Nature;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.springframework.util.StringUtils;


@Service
public class SupplierGroupServiceImpl implements SupplierGroupService{

	@Autowired
    private SupplierGroupMapper supplierGroupMapper;
	
	@Autowired
	private SupplierMapper supplierMapper;
	
	@Autowired
	private ManageConfigMapper manageConfigMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addSupplierGroup(UserVO user, SupplierGroupVO supplierGroupVO) throws Exception{
		//判断当前供货单位分组的编码和名称不可重复
		String code = supplierGroupVO.getCode();
		String name = supplierGroupVO.getName();
		if (StringUtils.isEmpty(code)){
			throw new BusinessException(SysCode.FAIL.getCode(),"编码不能为空!");
		}
		if (ChineseString.isChinese(code)){
			throw new BusinessException(SysCode.FAIL.getCode(),"编码不能含有中文!");
		}
		SupplierGroup hasCodeGroup = supplierGroupMapper.hasCodeGroup(code,user.getChainType() == ChainType.Headquarters.getCode() ? user.getEnterpriseId() : user.getParentId());
		if (hasCodeGroup != null){
			throw new BusinessException(SysCode.FAIL.getCode(),"当前编码已存在!");
		}
		if (StringUtils.isEmpty(name)){
			throw new BusinessException(SysCode.FAIL.getCode(),"名称不能为空!");
		}
		SupplierGroup hasNameGroup = supplierGroupMapper.hasNameGroup(name,user.getChainType() == ChainType.Headquarters.getCode() ? user.getEnterpriseId() : user.getParentId());
		if (hasNameGroup != null){
			throw new BusinessException(SysCode.FAIL.getCode(),"当前名称已存在!");
		}
		SupplierGroup sg = new SupplierGroup();
		Long enterpriseId = ChainType.getHeadEnterpriseId(user);
		sg.setEnterpriseId(enterpriseId);
		sg.setCode(supplierGroupVO.getCode());
		sg.setName(supplierGroupVO.getName());
		sg.setStatus(supplierGroupVO.getStatus());
		sg.setRemark(supplierGroupVO.getRemark());
		sg.setEnterpriseType(supplierGroupVO.getNature());
		UserEnterpriseUtils.setUserCreateOrModify(sg,user,true);
		supplierGroupMapper.insertSelective(sg);
		//供货单位只能总部人员进行添加和修改
		//supplierGroupMapper.addSupplierGroup(map);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<SupplierGroup> selectSupplierGroupSelector(UserVO user,int type) {
		Long enterpriseId = ChainType.getHeadEnterpriseId(user);
		List<SupplierGroup> list = supplierGroupMapper.selectSupplierGroupSelector(enterpriseId,type);
		return list;
	}

	@Override
	public List<SupplierGroupTreeVO> selectSupplierTree(Long enterpriseId) {
		List<SupplierGroupTreeVO> listVO = new ArrayList<SupplierGroupTreeVO>();
		List<SupplierGroup> list = supplierGroupMapper.selectSupplierTree(enterpriseId);
		for (SupplierGroup s : list) {
			List<SupplierBasicVO> supplier = supplierMapper.getCurrentGroupSupplier(s.getId(),enterpriseId);
			SupplierGroupTreeVO sg = new SupplierGroupTreeVO();
			sg.setId(s.getId());
			sg.setEnterpriseId(s.getEnterpriseId());
			sg.setCode(s.getCode());
			sg.setEnterpriseType(s.getEnterpriseType());
			sg.setName(s.getName());
			sg.setSupplierList(supplier);
			sg.setStatus(s.getStatus());
			sg.setRemark(s.getRemark());
			listVO.add(sg);
		}
		return listVO;
	}

	@Override
	public List<SupplierTreePOJO> structTree(List<SupplierGroupTreeVO> list, UserVO userVO) {
		int ruleType = manageConfigMapper.judgeCodeStyle(userVO.getChainType() == ChainType.Headquarters.getCode() ? userVO.getEnterpriseId() : userVO.getParentId());
		List<SupplierTreePOJO> TreePOJOList = new ArrayList<>();
		for (Nature n : Nature.values()) {
			SupplierTreePOJO t = new SupplierTreePOJO<>();
			//先设置父节点的属性
			t.setLabel(n.getName());
			t.setNodeShowDelete(false);
			t.setNodeShowRemove(false);
			t.setSupplierShow(false);
			t.setNodeShowRelateSupplier(false);
			t.setLeaf(true);
			t.setDataType(0);
			List<SupplierTreePOJO> children = new ArrayList<SupplierTreePOJO>();
			//有一个分组那么就不是叶子
			for (SupplierGroupTreeVO sg : list) {
				if (sg.getEnterpriseType() == n.getCode()) {
					t.setLeaf(false);
					//设置父节点的孩子（分组）
					SupplierTreePOJO group = new SupplierTreePOJO();
					group.setId(sg.getId());
					group.setLabel(sg.getCode() + "-" + sg.getName());
					//1-代表是供货单位分组 0-代表的是供货单位
					group.setDataType(1);
					group.setData(sg);
					List<SupplierBasicVO> supplierList = sg.getSupplierList();
					group.setLeaf(true);
					//根据saas_manage_config表中供货单位编码规则筛选功能
					//0-供货单位分组+4位流水码[可修改其他不可以]
					//1-4位流水码[都可以]
  					//2-自定义编码[都可以]
					switch (ruleType) {
						case 0:
							group.setNodeShowDelete(false);
							group.setNodeShowRemove(false);
							group.setSupplierShow(true);
							group.setNodeShowRelateSupplier(false);
							break;
						case 1:
							group.setNodeShowDelete(true);
							group.setNodeShowRemove(true);
							group.setSupplierShow(true);
							group.setNodeShowRelateSupplier(true);
							break;
						case 2:
							group.setNodeShowDelete(true);
							group.setNodeShowRemove(true);
							group.setSupplierShow(true);
							group.setNodeShowRelateSupplier(true);
							break;
						default:
							break;
					}
					List<SupplierTreePOJO> childrenSon = new ArrayList<SupplierTreePOJO>();
					for (SupplierBasicVO supplierBasicVO : supplierList) {
						if (supplierBasicVO.getGroupId().equals(sg.getId()) && sg.getEnterpriseType() == Integer.parseInt(supplierBasicVO.getNature())) {
							//放第三层
							group.setLeaf(false);
							group.setNodeShowDelete(false);
							SupplierTreePOJO supplier = new SupplierTreePOJO();
							supplier.setId(supplierBasicVO.getId());
							supplier.setLabel(supplierBasicVO.getCode() + "-" + supplierBasicVO.getName());
							supplier.setLeaf(true);
							supplier.setNodeShowDelete(false);
							/**
							 * 如果当前编码是供货单位分组+4位流水码不可以移除
							 */
							if (ruleType == 0){
								supplier.setNodeShowRemove(false);
							}else{
								supplier.setNodeShowRemove(true);
							}

							supplier.setSupplierShow(false);
							supplier.setNodeShowRelateSupplier(false);
							supplier.setDataType(0);

							if(ChainType.Division.getCode() == supplierBasicVO.getChainType()){
								/**
								 * 如果供货单位是加盟店企业类型则只允许查看 不允许有任何修改删除等操作
								 */
								supplier.setNodeShowRemove(false);
								supplier.setSupplierShow(false);
								supplier.setNodeShowRelateSupplier(false);
								supplier.setNodeShowDelete(false);
							}
							childrenSon.add(supplier);
						}
					}
					//当前供货单位分组中没有供货单位时 就没有移除选项
					if (childrenSon.size() == 0){
						group.setNodeShowRemove(false);
					}
					group.setChildren(childrenSon);
					/**
					 * 如果是默认的供货单位分组，则不能修改删除
					 */
					if (0 == sg.getEnterpriseId()){
						group.setNodeShowDelete(false);
						group.setNodeShowRemove(false);
						group.setSupplierShow(false);
					}
					children.add(group);
				}
			}
			t.setChildren(children);
			TreePOJOList.add(t);
		}
		return TreePOJOList;
	}

	@Override
	public List<SupplierGroupConnectVO> selectConnectSupplier(Long enterpriseId,Long id) {
		List<SupplierGroupConnectVO> list = supplierMapper.selectConnectSupplier(enterpriseId,id);
		return list;
	}

}
