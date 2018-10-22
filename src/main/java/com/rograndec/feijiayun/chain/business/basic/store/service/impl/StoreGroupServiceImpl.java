package com.rograndec.feijiayun.chain.business.basic.store.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.basic.store.dao.EnterpriseGroupMapper;
import com.rograndec.feijiayun.chain.business.basic.store.entity.EnterpriseGroup;
import com.rograndec.feijiayun.chain.business.basic.store.service.StoreGroupService;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreGroupTreeVO;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreGroupVO;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.SimpleEnterpriseVO;
import com.rograndec.feijiayun.chain.common.SelectBean;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;

@Service
public class StoreGroupServiceImpl implements StoreGroupService{

	private static final Log logger = LogFactory.getLog(StoreGroupServiceImpl.class);
	
	@Autowired
	private EnterpriseGroupMapper enterpriseGroupMapper;
	
	@Autowired
	private EnterpriseMapper enterpriseMapper;
	
	@Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<StoreGroupTreeVO> selectStroeGroupTreeByEnterpriseId(
			Long enterpriseId) {
		List<EnterpriseGroup> list = enterpriseGroupMapper.selectStroeGroupTreeByEnterpriseId(enterpriseId);
		
		Map<Long,List<EnterpriseGroup>> map = new HashMap<Long,List<EnterpriseGroup>>();
		
		if(list != null){
			for (EnterpriseGroup group : list) {
				if(map.containsKey(group.getParentId())){
					
					List<EnterpriseGroup> mapList = map.get(group.getParentId());
					mapList.add(group);
					
					map.put(group.getParentId(), mapList);
				}else{
					
					List<EnterpriseGroup> mapList = new ArrayList<>();
					mapList.add(group);
					
					map.put(group.getParentId(), mapList);
				}
			}
		}
		
		List<StoreGroupTreeVO> voList = new ArrayList<StoreGroupTreeVO>();
		
		setTree(voList, map, 0L, new StoreGroupTreeVO());
		
		return voList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setTree(List<StoreGroupTreeVO> voList,
			Map<Long, List<EnterpriseGroup>> map, Long key, StoreGroupTreeVO vo1) {
		
		if(map.containsKey(key)){
			
			List<EnterpriseGroup> groupList = map.get(key);
			
			for (EnterpriseGroup enterpriseGroup : groupList) {
				vo1.setLeaf(false);
				
				StoreGroupTreeVO vo = setStoreGroupVo(enterpriseGroup, key);
				
				if(key != 0){
					setStoreChildren(vo.getChildren(), enterpriseGroup, vo);
				}
				voList.add(vo);
			}
			
			if(key == 0){
				for (StoreGroupTreeVO vo : voList) {//树级递归
					setTree(vo.getChildren(), map, vo.getId(), vo); 
				}
			}
		}
		
	}

	@SuppressWarnings("rawtypes")
	private void setStoreChildren(List<StoreGroupTreeVO> children,
			EnterpriseGroup enterpriseGroup, StoreGroupTreeVO vo) {
		
		List<StoreVO> storeList = enterpriseMapper.selectShopByBusinessId(enterpriseGroup.getEnterpriseId(), enterpriseGroup.getId(), "group");
		
		if(storeList != null){
			for (StoreVO storevo : storeList) {
				vo.setLeaf(false);
				vo.setNodeShowDelete(false);
				vo.setNodeShowRemove(true);
				StoreGroupTreeVO vo1 = setStoreGroupVo(storevo, enterpriseGroup.getId());
				children.add(vo1);
			}
		}
		
		/*if(enterpriseGroup!=null && StringUtils.isNotBlank(enterpriseGroup.getStoreIds())){
			for (String storeId : enterpriseGroup.getStoreIds().split(",")) {
				if(StringUtils.isNotBlank(storeId)){
					try {
						list.add(Long.parseLong(storeId));
					} catch (Exception e) {
						logger.error("门店分组绑定门店ID转Long失败！", e);
						e.printStackTrace();
					}
				}
			}
			
			List<SimpleEnterpriseVO> simpleList = enterpriseMapper.selectSimpleEnterpriseByIds(list);
			if(simpleList != null){
				for (SimpleEnterpriseVO simpleEnterpriseVO : simpleList) {
					vo.setLeaf(false);
					vo.setNodeShowDelete(false);
					vo.setNodeShowRemove(true);
					StoreGroupTreeVO vo1 = setStoreGroupVo(simpleEnterpriseVO, enterpriseGroup.getId());
					children.add(vo1);
				}
			}
		}*/
	}

	@SuppressWarnings("rawtypes")
	private StoreGroupTreeVO setStoreGroupVo(StoreVO storevo, Long id) {
		StoreGroupTreeVO vo = new StoreGroupTreeVO();
		vo.setId(storevo.getId());
		vo.setParentId(id);
		vo.setLabel(storevo.getCode()+"-"+storevo.getName());
		vo.setLeaf(true);
		vo.setNodeShowDelete(false);
		vo.setNodeShowRemove(true);
		vo.setNodeShowRelateBranch(false);
		vo.setSupplierShow(false);
		vo.setLevel(3);
		return vo;
	}

	@SuppressWarnings("rawtypes")
	private StoreGroupTreeVO setStoreGroupVo(
			SimpleEnterpriseVO simpleEnterpriseVO, Long id) {
		StoreGroupTreeVO vo = new StoreGroupTreeVO();
		vo.setId(simpleEnterpriseVO.getId());
		vo.setParentId(id);
		vo.setLabel(simpleEnterpriseVO.getCode()+"-"+simpleEnterpriseVO.getName());
		vo.setLeaf(true);
		vo.setNodeShowDelete(false);
		vo.setNodeShowRemove(true);
		vo.setNodeShowRelateBranch(false);
		vo.setSupplierShow(false);
		vo.setLevel(3);
		return vo;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private StoreGroupTreeVO setStoreGroupVo(EnterpriseGroup enterpriseGroup, Long key) {
		StoreGroupTreeVO vo = new StoreGroupTreeVO();
		vo.setId(enterpriseGroup.getId());
		vo.setParentId(enterpriseGroup.getParentId());
		if(key == 0){
			vo.setLabel(enterpriseGroup.getName());
			vo.setLeaf(true);
			vo.setNodeShowRemove(false);
			vo.setNodeShowDelete(false);
			vo.setNodeShowRelateBranch(false);
			vo.setSupplierShow(false);
			vo.setLevel(1);
		}else{
			vo.setLabel(enterpriseGroup.getCode()+"-"+enterpriseGroup.getName());
			vo.setLeaf(true);
			vo.setNodeShowRemove(false);
			vo.setNodeShowDelete(true);
			vo.setNodeShowRelateBranch(true);
			vo.setSupplierShow(true);
			vo.setLevel(2);
			vo.setData(enterpriseGroup);
		}
		return vo;
	}

	@Override
	public List<SelectBean> selectStoreGroupType(Long enterpriseId) {
		return enterpriseGroupMapper.selectRootStoreGroupByEnterpriseId(enterpriseId);
	}

	@Override
	public EnterpriseGroup saveOrUpdate(StoreGroupVO vo, UserVO loginUser) throws Exception {
		
		if(vo.getId() == null || vo.getId() == 0){
			Long count = enterpriseGroupMapper.queryCountByParentIdAndCodeName(loginUser.getEnterpriseId(), 
					vo.getParentId(), vo.getCode(), vo.getName());
			if(count > 0){
				throw new Exception("该编码或名称已存在;");
			}
		}
		
		EnterpriseGroup group = setEnterpriseGroup(vo, loginUser);
		if(group.getId() == null || group.getId() == 0){
			enterpriseGroupMapper.insertSelective(group);
		}else{
			if(group.getStatus()!=null){
				if(group.getStatus().equals(0)){
			Map map=new HashMap();
			map.put("groupId", group.getId());
			Long count= enterpriseMapper.queryCountByGroupId(map);
			if(count>0){//如果数量大于0被门店引用
				throw new Exception("门店分组被引用不允许禁用");
			}
			if(group.getEnterpriseId()!=null){//如果门店分组等于0的时候是系统默认分组不允许删除
				if(group.getEnterpriseId()==0){
					throw new Exception("门店分组为系统默认不允许禁用");
				    }
			      }
				}
			}
			enterpriseGroupMapper.updateByPrimaryKeySelective(group);
		}
		return group;
	}

	private EnterpriseGroup setEnterpriseGroup(StoreGroupVO vo, UserVO user) {
		
		EnterpriseGroup parentGroup = enterpriseGroupMapper.selectByPrimaryKey(vo.getParentId());
		
		EnterpriseGroup group = new EnterpriseGroup();
		group.setId(vo.getId());
		group.setParentId(vo.getParentId());
		group.setEnterpriseId(user.getEnterpriseId());
		group.setType(parentGroup!=null?parentGroup.getType():0);
		group.setCode(vo.getCode());
		group.setName(vo.getName());
		group.setStatus(vo.getStatus());
		group.setRemark(vo.getRemark());
		if(group.getId() == null || group.getId() == 0){
			group.setCreaterId(user.getUserId());
			group.setCreaterCode(user.getUserCode());
			group.setCreaterName(user.getUserName());
			group.setCreateTime(new Date());
			group.setModifierId(user.getUserId());
			group.setModifierCode(user.getUserCode());
			group.setModifierName(user.getUserName());
			group.setUpdateTime(new Date());
		}else {
			group.setModifierId(user.getUserId());
			group.setModifierCode(user.getUserCode());
			group.setModifierName(user.getUserName());
		}
		return group;
	}

	@Override
	public List<SimpleEnterpriseVO> selectRelationStore(Long enterpriseId, Integer chainType) {
		List<SimpleEnterpriseVO> voList = new ArrayList<>();
		
		List<Enterprise> enList = enterpriseMapper.selectChildrenByPrimaryKey(enterpriseId);
		
		if(enList != null){
			
//			List<EnterpriseGroup> groupList = enterpriseGroupMapper.selectStroeGroupTreeByEnterpriseId(enterpriseId);
			
			Map<String,String> map = new HashMap<>();
			/*if(groupList != null){
				for (EnterpriseGroup enterpriseGroup : groupList) {
					if(StringUtils.isNotBlank(enterpriseGroup.getStoreIds())){
						for (String storeId : enterpriseGroup.getStoreIds().split(",")) {
							map.put(storeId, storeId);
						}
					}
				}
			}*/
			
			SimpleEnterpriseVO vo = null;
			for (Enterprise en : enList) {
				if(!map.containsKey(en.getId().toString()) && en.getParentId() != null && en.getParentId() != 0
						&& en.getChainType()!= null && en.getChainType() == chainType && (en.getGroupId() == null || en.getGroupId() == 0)){
					vo = setStoreGroupVo(en, vo);
					
					voList.add(vo);
				}
			}
		
		}
		return voList;
	}

	private SimpleEnterpriseVO setStoreGroupVo(Enterprise en, SimpleEnterpriseVO vo) {
		vo = new SimpleEnterpriseVO();
		vo.setId(en.getId());
		vo.setParentId(en.getParentId());
		vo.setCode(en.getCode());
		vo.setName(en.getName());
		return vo;
	}

	@Override
	public int saveRelationStore(Long id, String storeIds, UserVO loginUser) {
		EnterpriseGroup group = enterpriseGroupMapper.selectByPrimaryKey(id);
		if(group == null){
			return 3;
		}
		
		if(StringUtils.isNotBlank(storeIds)){
			for (String storeId : storeIds.split(",")) {
				if(StringUtils.isNotBlank(storeId)){
					Long storeid = Long.parseLong(storeId);
					enterpriseMapper.updateBusinessStore(group.getEnterpriseId(), storeid, "group", id);
				}
			}
		}
		return 1;
	}

	@Override
	public int removeRelationStore(Long id, Long parentId, Long level, Long enterpriseId) {
		if(level == 2){
			EnterpriseGroup group = enterpriseGroupMapper.selectByPrimaryKey(id);
			if(group == null || !group.getEnterpriseId().equals(enterpriseId) || !group.getParentId().equals(parentId)){
				return 2;
			}
			
			enterpriseMapper.removeBusinessStore(group.getEnterpriseId(), null, "group", group.getId());
			return 1;
//			enterpriseGroupMapper.deleteByPrimaryKey(id);
			
		}else if(level == 3){
			EnterpriseGroup group = enterpriseGroupMapper.selectByPrimaryKey(parentId);
			if(group == null || !group.getEnterpriseId().equals(enterpriseId)){
				return 2;
			}
			
			List<StoreVO> enVOlList = enterpriseMapper.selectShopByBusinessId(group.getEnterpriseId(), group.getId(), "group");
			if(enVOlList == null || enVOlList.size() == 0){
				return 2;
			}
			
			return enterpriseMapper.removeBusinessStore(group.getEnterpriseId(), id, "group", group.getId());
		}
		return 2;
	}
	

	@Override
	public int deleteRelationStore(Long id, Long parentId, Long level,
			Long enterpriseId) {
		if(level == 2){
			EnterpriseGroup group = enterpriseGroupMapper.selectByPrimaryKey(id);
			Map map=new HashMap();
			map.put("groupId", id);
			Long count= enterpriseMapper.queryCountByGroupId(map);
			if(count>0){//如果数量大于0被门店引用
				return 5;
			}
			if(group.getEnterpriseId()!=null){//如果门店分组等于0的时候是系统默认分组不允许删除
				if(group.getEnterpriseId()==0){
			       return 0;
				}
			}
			if(group == null || !enterpriseId.equals(group.getEnterpriseId())){
				return 2;
			}
			
			List<StoreVO> enVOlList = enterpriseMapper.selectShopByBusinessId(group.getEnterpriseId(), group.getId(), "group");
			if(enVOlList != null && enVOlList.size() > 0){
				return 3;
			}
			
			return enterpriseGroupMapper.deleteByPrimaryKey(id);
			
		}
		return 4;
	}
	
	@Override
	public List<EnterpriseGroup> selectExportEnterpriseGroup(Long enterpriseId) {
		List<EnterpriseGroup> list = enterpriseGroupMapper.selectStroeGroupHasStoreIdsByEnterpriseId(enterpriseId);
		
		List<EnterpriseGroup> returnList = new ArrayList<>();
		
		if(list != null){
			for (EnterpriseGroup enterpriseGroup : list) {
				if(enterpriseGroup != null){
					
					List<StoreVO> enVOlList = enterpriseMapper.selectShopByBusinessId(enterpriseGroup.getEnterpriseId(), enterpriseGroup.getId(), "group");
					
					EnterpriseGroup group = null;
					if(enVOlList != null && enVOlList.size() > 0){
						for (StoreVO simpleEnterpriseVO : enVOlList) {
							
							group = new EnterpriseGroup();
							group.setType(enterpriseGroup.getType());
							group.setName(enterpriseGroup.getCode()+"-"+enterpriseGroup.getName());
							group.setCreaterName(simpleEnterpriseVO.getCode()+"-"+simpleEnterpriseVO.getName());
							
							returnList.add(group);
						}
					}else{
						enterpriseGroup.setName(enterpriseGroup.getCode()+"-"+enterpriseGroup.getName());
						enterpriseGroup.setCreaterName("");
						returnList.add(enterpriseGroup);
					}
					
				}
			}
			
		}
		
		return returnList;
	}

	@Override
	public void excelExport(OutputStream output,
			List<EnterpriseGroup> enterpriseGroupList, UserVO loginUser) {
		
		//转换一下显示日期
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("modifierName","类型");
        map.put("name","分组");
        map.put("createrName","门店");

        if(enterpriseGroupList != null){
        	for (EnterpriseGroup enterpriseGroup : enterpriseGroupList) {
				enterpriseGroup.setModifierName(enterpriseGroup.getType()!=null&&enterpriseGroup.getType()==0?"自营店":"加盟店");
			}
        }
        
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("门店分组导出");
        
        
        List<String> needTotalName = new ArrayList<>();
        purchaseGeneralComponent.commExcelExport(output, map, enterpriseGroupList, name, null, "", false, needTotalName);

	}
	
	private void createRowHeader(ExcelWriter writer) throws IOException {
        // 插入新行
        writer.insertRow(0);
        // 建立新单元格,索引值从0开始,表示第一列
        int k = 0;
        writer.createCell(k++, "类型");
        writer.createCell(k++, "分组");
        writer.createCell(k, "门店");
        // 结束行
        writer.endRow();
    }

}
