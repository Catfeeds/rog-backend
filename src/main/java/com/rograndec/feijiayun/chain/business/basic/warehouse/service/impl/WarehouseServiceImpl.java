package com.rograndec.feijiayun.chain.business.basic.warehouse.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseAreaMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseCargoAreaMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.Warehouse;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseArea;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseCargoArea;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.basic.warehouse.service.WarehouseService;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.*;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.CodeComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.ShelfStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ST on 2017/8/23.
 */
@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private WarehouseShelfMapper warehouseShelfMapper;

    @Autowired
    private WarehouseMapper warehouseMapper;

    @Autowired
    private WarehouseAreaMapper warehouseAreaMapper;

    @Autowired
    private WarehouseCargoAreaMapper warehouseCargoAreaMapper;

    @Autowired
    private StockMapper stockMapper;
    
    @Autowired
    private CodeComponent codeComponent;
    /**
     *
     * @param name
     * @param enterpriseId
     * @return
     */
    @Override
    public WarehouseShelf getWarehouseByName(String name, Long enterpriseId){
        return warehouseShelfMapper.getShelfByName(name,enterpriseId);
    }

    /**
     * 根据企业id 查询仓库--》库区--》货区--》货位的级联树
     * @param type 0=仓库；1=库区；2=货区；3=货位； 根据type展示以type为最高级的级联树
     * @param enterpriseId
     */
    @Override
    public List<TreePOJO<WarehouseVO>> getWarehouseExclusiveNullTreeByParam(Integer type, Long enterpriseId, Long parentId, Integer status){
        // TODO: 2017/8/24 暂时实现type=0的级联树
        List<TreePOJO<WarehouseVO>> treePOJOList = null;
            switch (type){
                case 0 :
                    treePOJOList = getWarehouseStandardExclusiveNullTree(enterpriseId, parentId,status);
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
            }
        return treePOJOList;
    }

    /**
     * 根据企业id 查询仓库--》库区--》货区--》货位的级联树(含空树)
     * @param type 0=仓库；1=库区；2=货区；3=货位； 根据type展示以type为最高级的级联树
     * @param enterpriseId
     * @param parentId
     * @param status
     * @return
     */
    @Override
    public List<TreePOJO<WarehouseVO>> getWarehouseTreeIncludeNullByParam(Integer type, Long enterpriseId, Long parentId, Integer status){
        // TODO: 2017/8/24 暂时实现type=0的级联树
        List<TreePOJO<WarehouseVO>> treeList = new ArrayList<>();
        switch (type){
            case 0 :
                treeList = getWarehouseStandardTree(enterpriseId,parentId,status);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
        return treeList;
    }

    //根据企业id 查询仓库--》库区--》货区--》货位的级联树
//    @Override
    public List<TreePOJO<WarehouseVO>> getWarehouseStandardExclusiveNullTree(Long enterpriseId, Long parentId, Integer status){
        List<WarehouseVO> warehouseList = warehouseMapper.getWarehouseExclusiveNullTreeByParam(enterpriseId,parentId,status);

        return setWarehouseVOTree(warehouseList);
    }
    //根据企业id 查询仓库--》库区--》货区--》货位的级联树(含空树)
	public List<TreePOJO<WarehouseVO>> getWarehouseStandardTree(Long enterpriseId, Long parentId, Integer status){
	    List<WarehouseVO> warehouseList = warehouseMapper.getWarehouseTreeIncludeNullByParam(enterpriseId,parentId,status);
	
	    return setWarehouseVOTree(warehouseList);
	}

    private List<TreePOJO<WarehouseVO>> setWarehouseVOTree(List<WarehouseVO> warehouseList) {
		
    	List<TreePOJO<WarehouseVO>> firstTree = new ArrayList<>();
    	
    	if(warehouseList != null && warehouseList.size() > 0){
	        for(WarehouseVO warehouseVO : warehouseList){
	        	TreePOJO warehouseVOTreePOJO = new TreePOJO<>();
	            warehouseVOTreePOJO.setId(warehouseVO.getId());
	            warehouseVOTreePOJO.setParentId(null);
	            warehouseVOTreePOJO.setLeaf(false);
//		          warehouseVOTreePOJO.setData(warehouseVO);
	            warehouseVOTreePOJO.setLabel(warehouseVO.getCode() + "-" + warehouseVO.getName());
                warehouseVOTreePOJO.setSupplierShow(false);
	            warehouseVOTreePOJO.setNodeShowDelete(false);
	            List<WarehouseAreaVO> warehouseAreaVOList = warehouseVO.getWarehouseAreaVOList();
	            List<TreePOJO<WarehouseAreaVO>> secondTree = new ArrayList<>();
	            for(WarehouseAreaVO warehouseAreaVO : warehouseAreaVOList){
	            	TreePOJO warehouseAreaTreePOJO = new TreePOJO<>();
	                warehouseAreaTreePOJO.setId(warehouseAreaVO.getId());
	                warehouseAreaTreePOJO.setLabel(warehouseAreaVO.getCode() + "-" + warehouseAreaVO.getName());
	                warehouseAreaTreePOJO.setParentId(warehouseVO.getId());
	                warehouseAreaTreePOJO.setLeaf(false);
                    warehouseAreaTreePOJO.setSupplierShow(false);
//		            warehouseAreaTreePOJO.setData(warehouseAreaVO);
	                warehouseAreaTreePOJO.setNodeShowDelete(false);
	                List<WarehouseCargoAreaVO> warehouseCargoAreaVOList = warehouseAreaVO.getWarehouseCargoAreaVOList();
	                List<TreePOJO<WarehouseCargoAreaVO>> thirdTree = new ArrayList<>();
	                for(WarehouseCargoAreaVO warehouseCargoAreaVO : warehouseCargoAreaVOList){
	                	TreePOJO warehouseCargoAreaVOTreePOJO = new TreePOJO<>();
	                    warehouseCargoAreaVOTreePOJO.setId(warehouseCargoAreaVO.getId());
	                    warehouseCargoAreaVOTreePOJO.setParentId(warehouseAreaVO.getId());
	                    warehouseCargoAreaVOTreePOJO.setLeaf(false);
	                    warehouseCargoAreaVOTreePOJO.setLabel(warehouseCargoAreaVO.getCode() + "-" + warehouseCargoAreaVO.getName());
//		                warehouseCargoAreaVOTreePOJO.setData(warehouseCargoAreaVO);
                        warehouseCargoAreaVOTreePOJO.setSupplierShow(false);
	                    warehouseCargoAreaVOTreePOJO.setNodeShowDelete(false);
	                    List<WarehouseShelfVO> list =  warehouseCargoAreaVO.getWarehouseShelfVOList();
	                    //第四级
	                    List<TreePOJO<WarehouseShelfVO>> fourthTree = new ArrayList<>();
	                    for(WarehouseShelfVO warehouseShelfVO : list){
	                    	TreePOJO warehouseShelfVOTreePOJO = new TreePOJO<>();
	                        warehouseShelfVOTreePOJO.setId(warehouseShelfVO.getId());
	                        warehouseShelfVOTreePOJO.setParentId(warehouseCargoAreaVO.getId());
	                        warehouseShelfVOTreePOJO.setLabel(warehouseShelfVO.getCode() + "-" + warehouseShelfVO.getName());
	                        warehouseShelfVOTreePOJO.setLeaf(true);
//		                    warehouseShelfVOTreePOJO.setData(warehouseShelfVO);
	                        warehouseShelfVOTreePOJO.setNodeShowDelete(false);
	                        warehouseShelfVOTreePOJO.setChildren(new ArrayList<>());
	                        fourthTree.add(warehouseShelfVOTreePOJO);
	                    }
	                    warehouseCargoAreaVOTreePOJO.setChildren(fourthTree);
	                    thirdTree.add(warehouseCargoAreaVOTreePOJO);
	                }
	                warehouseAreaTreePOJO.setChildren(thirdTree);
	                secondTree.add(warehouseAreaTreePOJO);
	            }
	            warehouseVOTreePOJO.setChildren(secondTree);
	            firstTree.add(warehouseVOTreePOJO);
	        }
		}
		
    	return firstTree;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
    public void addWarehouse(Warehouse warehouse) {
        warehouse.setId(null);

        warehouseMapper.insertSelective(warehouse);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateWarehouse(Warehouse warehouse) {
        warehouseMapper.updateByPrimaryKeySelective(warehouse);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteWarehouse(Long id) {
        warehouseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Warehouse getDefaultWarehouse(Long enterpriseId,String enterpriseCode) {
        return warehouseMapper.getWarehouseByCode(enterpriseId,enterpriseCode);
    }

    /**
     * 根据企业id,上级企业id,仓库id 查询仓库
     * @param
     * @param
     * @param status
     * @return
     */
    @Override
    public void getWarehouseByParam(Page<List<ResponseWarehouseVO>> page, UserVO userVO, Integer status, String sortOrder) {
        Long enterpriseId = userVO.getEnterpriseId();
        Long parentId = userVO.getParentId();
        Integer chainType = userVO.getChainType();
        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<ResponseWarehouseVO>  list = warehouseMapper.getWarehouseByParam(enterpriseId,parentId, status,sortOrder);

        if(chainType == ChainType.Headquarters.getCode()){
            //总部
            boolean canUpdate = true;

            //查询所有的仓库
            List<ResponseWarehouseVO> warehouseList = getWarehouseList(enterpriseId, parentId, null);
            //查询门店的所有仓库
            List<ResponseWarehouseVO> storeWhouseList = getWarehouseList(null, enterpriseId, null);
            warehouseList.addAll(storeWhouseList);
            for (ResponseWarehouseVO warehouse : warehouseList) {
                //设置编码规则
                Long warehouseId = warehouse.getId();

                Integer whouseCount = warehouseMapper.getWhouseCount(warehouseId);
                Integer whAreaCount = warehouseMapper.getWhAreaCount(warehouseId);
                Integer whCargoAreaCount = warehouseMapper.getWhCargoAreaCount(warehouseId);
                Integer whShelfCount = warehouseMapper.getWhShelfCount(warehouseId);

                if(whouseCount > 0){
                    canUpdate = false;
                }

                if(whAreaCount > 0 || whCargoAreaCount > 0 || whShelfCount > 0 ){
                    canUpdate = false;
                }

            }
            for (ResponseWarehouseVO item : list) {
                if (item.getSysType() == 1 && canUpdate) {
                    item.setCanUpdateCodeRule(canUpdate);
                }
            }
        }

        for (ResponseWarehouseVO warehouse : list) {
            //设置仓库是否可以禁用,只要这个仓库下的所有货位的库存数量为0,就可以修改为禁用
            List<Long> shelfIdList = warehouseShelfMapper.getShelfIdSByWarehouseID(warehouse.getId(),enterpriseId);
            if(!CollectionUtils.isEmpty(shelfIdList)){
                Integer quantityByShelfIdS = stockMapper.getQuantityByShelfIdS(enterpriseId, shelfIdList);
                if(quantityByShelfIdS == null ||  quantityByShelfIdS ==  0){
                    warehouse.setIsDisabled(1);
                }
            } else {
                warehouse.setIsDisabled(1);
            }
        }

        page.setResult(list);
        page.setTotalRecord((int) objects.getTotal());

    }


    @Override
    public List<ResponseWarehouseVO> getWarehouseList(Long enterpriseId, Long parentId, Integer status) {
        return warehouseMapper.getWarehouseByParam(enterpriseId,parentId, status,"desc");
    }



    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addWarehouseArea(WarehouseArea warehouseArea) {
        warehouseArea.setId(null);
        warehouseAreaMapper.insertSelective(warehouseArea);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateWarehouseArea(WarehouseArea warehouseArea) {
        warehouseAreaMapper.updateByPrimaryKeySelective(warehouseArea);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteWarehouseArea(Long id) {
        warehouseAreaMapper.deleteByPrimaryKey(id);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addWarehouseCargoArea(WarehouseCargoArea warehouseCargoArea,UserVO userVO) throws Exception {
    	//判断编码与名称是否重复
    	Map mapName=new HashMap();
    	mapName.put("name", warehouseCargoArea.getName());
    	mapName.put("enterpriseId", userVO.getEnterpriseId());
    	Map mapCode=new HashMap();
    	mapCode.put("code", warehouseCargoArea.getCode());
    	mapCode.put("enterpriseId", userVO.getEnterpriseId());
    	List<WarehouseCargoArea> nameList=warehouseCargoAreaMapper.selectCargoAreaByNameOrCode(mapName);
    	List<WarehouseCargoArea> codeList=warehouseCargoAreaMapper.selectCargoAreaByNameOrCode(mapCode);
    	if(CollectionUtils.isNotEmpty(nameList)){
    		throw new BusinessException("名称不能重复！");
    	}
    	if(CollectionUtils.isNotEmpty(codeList)){
    		throw new BusinessException("编码不能重复！");
    	}
        warehouseCargoArea.setId(null);
        warehouseCargoAreaMapper.insertSelective(warehouseCargoArea);
        WarehouseShelf warehouseShelf = new WarehouseShelf();
        setWarehouseShelf(warehouseCargoArea, warehouseShelf);
        warehouseShelfMapper.insertSelective(warehouseShelf);
    }
    
    private void setWarehouseShelf(WarehouseCargoArea warehouseCargoArea, WarehouseShelf warehouseShelf) throws Exception {
    	warehouseShelf.setEnterpriseId(warehouseCargoArea.getEnterpriseId());
    	warehouseShelf.setParentId(warehouseCargoArea.getParentId());
    	warehouseShelf.setSysType(warehouseCargoArea.getSysType());
    	warehouseShelf.setWarehouseId(warehouseCargoArea.getWarehouseId());
    	warehouseShelf.setWarehouseAreaId(warehouseCargoArea.getWarehouseAreaId());
    	warehouseShelf.setCargoAreaId(warehouseCargoArea.getId());
    	 //获取货位的编码
    	String codeName = "";
        codeName = WarehouseShelf.class.getSimpleName();
        Warehouse warehouse = getWarehouseByKey(warehouseCargoArea.getWarehouseId());//仓库对象
        String code = "";
        if(warehouse != null){
        	code = codeComponent.generateWarehouseCode(codeName, warehouse.getShelfCodeLength(), 
            		warehouseCargoArea.getEnterpriseId(),warehouseCargoArea.getCode(),warehouse.getSpacer());
        }
        warehouseShelf.setCode(code);
        warehouseShelf.setName(warehouseCargoArea.getName()+"货位");
        warehouseShelf.setStatus(warehouseCargoArea.getStatus());
        warehouseShelf.setCreaterId(warehouseCargoArea.getCreaterId());
        warehouseShelf.setCreaterCode(warehouseCargoArea.getCreaterCode());
        warehouseShelf.setCreaterName(warehouseCargoArea.getCreaterName());
        warehouseShelf.setCreateTime(warehouseCargoArea.getCreateTime());
        warehouseShelf.setModifierId(warehouseCargoArea.getModifierId());
        warehouseShelf.setModifierCode(warehouseCargoArea.getModifierCode());
        warehouseShelf.setModifierName(warehouseCargoArea.getModifierName());
        warehouseShelf.setUpdateTime(warehouseCargoArea.getUpdateTime());
        
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateWarehouseCargoArea(WarehouseCargoArea warehouseCargoArea,UserVO userVO) {
    	Map mapName=new HashMap();
    	mapName.put("name", warehouseCargoArea.getName());
    	mapName.put("enterpriseId", userVO.getEnterpriseId());
    	Map mapCode=new HashMap();
    	mapCode.put("code", warehouseCargoArea.getCode());
    	mapCode.put("enterpriseId", userVO.getEnterpriseId());
    	List<WarehouseCargoArea> nameList=warehouseCargoAreaMapper.selectCargoAreaByNameOrCode(mapName);
    	List<WarehouseCargoArea> codeList=warehouseCargoAreaMapper.selectCargoAreaByNameOrCode(mapCode);
    	if(CollectionUtils.isNotEmpty(nameList)){
    		if(!nameList.get(0).getId().equals(warehouseCargoArea.getId())){
    			throw new BusinessException("名称不能重复！");
    		}
    	}
    	if(CollectionUtils.isNotEmpty(codeList)){
    		if(!codeList.get(0).getId().equals(warehouseCargoArea.getId())){
    		throw new BusinessException("编码不能重复！");
    		}
    	}
        warehouseCargoAreaMapper.updateByPrimaryKeySelective(warehouseCargoArea);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteWarehouseCargoArea(Long id) {
        warehouseCargoAreaMapper.deleteByPrimaryKey(id);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addWarehouseShelf(WarehouseShelf warehouseShelf) {
        warehouseShelf.setId(null);
        //如果所在层级为空的话给它设置一个默认层级1
        if(warehouseShelf.getLayer()==null){
        	warehouseShelf.setLayer(1);
        }
        warehouseShelfMapper.insertSelective(warehouseShelf);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addBatchWarehouseShelf(List<WarehouseShelf> shelfList) {
        warehouseShelfMapper.addBatchWarehouseShelf(shelfList);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateWarehouseShelf(WarehouseShelf warehouseShelf) {
        warehouseShelfMapper.updateByPrimaryKeySelective(warehouseShelf);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteWarehouseShelf(Long id) {
        warehouseShelfMapper.deleteByPrimaryKey(id);
    }




    @Override
    public Warehouse getWarehouseByKey(Long warehouseId) {
        return warehouseMapper.selectByPrimaryKey(warehouseId);
    }

    @Override
    public WarehouseArea getWarehouseAreaByKey(Long warehouseAreaId) {
        return warehouseAreaMapper.selectByPrimaryKey(warehouseAreaId);
    }

    @Override
    public WarehouseCargoArea getWarehouseCargoAreaByKey(Long warehouseCargoAreaId) {
        return warehouseCargoAreaMapper.selectByPrimaryKey(warehouseCargoAreaId);
    }

    /**
     * 根据企业id,上级企业id,仓库id 查询库区
     * @param enterpriseId
     * @param parentId
     * @param warehouseId
     * @param status
     * @return
     */
    @Override
    public void  getWarehouseAreaByParam(Page<List<ResponseWarehouseAreaVO>> page,Long enterpriseId, Long parentId, Long warehouseId, String enterpriseCode,RequestQueryWarehouseAreaVO areaVO){
        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<ResponseWarehouseAreaVO> list = warehouseAreaMapper.getWarehouseAreaByParam(enterpriseId,parentId,warehouseId,areaVO);
        for (ResponseWarehouseAreaVO responseAreaVO: list) {
            //设置库区是否可以禁用,只要这个库区下的所有货位的库存数量为0,就可以修改为禁用
            List<Long> shelfIdList = warehouseShelfMapper.getShelfIdSByWarehouseAreaID(responseAreaVO.getId(),enterpriseId);
            if(!CollectionUtils.isEmpty(shelfIdList)){
                Integer quantityByShelfIdS = stockMapper.getQuantityByShelfIdS(enterpriseId, shelfIdList);
                if(quantityByShelfIdS == null || quantityByShelfIdS ==  0){
                    responseAreaVO.setIsDisabled(1);
                }
            } else {
                responseAreaVO.setIsDisabled(1);
            }

        }
        page.setResult(list);
        page.setTotalRecord((int) objects.getTotal());
    }

    @Override
    public List<ResponseWarehouseAreaVO> getWarehouseAreaList(Long enterpriseId, Long parentId, Long warehouseId, RequestQueryWarehouseAreaVO requestWarehouseCommonVO) {
        List<ResponseWarehouseAreaVO> list = warehouseAreaMapper.getWarehouseAreaByParam(enterpriseId,parentId,warehouseId,requestWarehouseCommonVO);
        for (ResponseWarehouseAreaVO responseAreaVO: list) {
            responseAreaVO.setIsDelete(warehouseMapper.getCargoCountByAreaId(responseAreaVO.getId()) > 0 ? 0 : 1);
        }
        return list;
    }




    /**
     * 根据企业id,上级企业id,库区id 查询货区
     * @param enterpriseId
     * @param parentId
     * @param warehouseAreaId 库区id
     * @param status
     * @return
     */
    @Override
    public void  getWarehouseCargoAreaByParam(Page<List<ResponseWarehouseCargoAreaVO>> page,Long enterpriseId, Long parentId, Long warehouseAreaId, String enterpriseCode,RequestQueryWarehouseCargoAreaVO commonVO) {
        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<ResponseWarehouseCargoAreaVO> list = warehouseCargoAreaMapper.getWarehouseCargoAreaByParam(enterpriseId,parentId,warehouseAreaId,commonVO);
        for(ResponseWarehouseCargoAreaVO cargoAreaVO : list){
            //设置仓库是否可以禁用,只要这个仓库下的所有货位的库存数量为0,就可以修改为禁用
            List<Long> shelfIdList = warehouseShelfMapper.getShelfIdSByWarehouseCargoID(cargoAreaVO.getId(),enterpriseId);
            if(!CollectionUtils.isEmpty(shelfIdList)){
                Integer quantityByShelfIdS = stockMapper.getQuantityByShelfIdS(enterpriseId, shelfIdList);
                if(quantityByShelfIdS == null || quantityByShelfIdS ==  0){
                    cargoAreaVO.setIsDisabled(1);
                }
            } else {
                cargoAreaVO.setIsDisabled(1);
            }

        }
        page.setResult(list);
        page.setTotalRecord((int) objects.getTotal());

    }

    @Override
    public List<ResponseWarehouseCargoAreaVO> getWarehouseCargoAreaByParam(Long enterpriseId, Long parentId, Long warehouseAreaId, RequestQueryWarehouseCargoAreaVO commonVO) {
        List<ResponseWarehouseCargoAreaVO> list = warehouseCargoAreaMapper.getWarehouseCargoAreaByParam(enterpriseId,parentId,warehouseAreaId,commonVO);
        for(ResponseWarehouseCargoAreaVO cargoAreaVO : list){
            cargoAreaVO.setIsDelete(warehouseMapper.getShelfCountByCargoId(cargoAreaVO.getId()) > 0 ? 0 : 1);
        }
        return list;
    }

    /**
     * 根据货位id 查询库存数量
     * @param shelfId
     * @return
     */
    @Override
    public Integer getStockCountByShelfId(Long shelfId){
        return stockMapper.getStockCountByShelfId(shelfId);
    }
    /**
     * 根据货区id查询货位的数量
     * @param cargoId
     * @return
     */
    @Override
    public Integer getShelfCountByCargoId(Long cargoId) {
        return warehouseMapper.getShelfCountByCargoId(cargoId);
    }
    /**
     * 根据库区数量查货区数量
     * @param areaId
     * @return
     */
    @Override
    public Integer getCargoCountByAreaId(Long areaId) {
        return warehouseMapper.getCargoCountByAreaId(areaId);
    }
    /**
     * 根据仓库id 查询库区数量
     * @param houseId
     * @return
     */
    @Override
    public Integer getAreaCountByHouseId(Long houseId) {
        return warehouseMapper.getAreaCountByHouseId(houseId);
    }

    @Override
    public ResponseWarehouseShelfVO getWarehouseShelf(Long id) {
        ResponseWarehouseShelfVO warehouseShelfVO = new ResponseWarehouseShelfVO();
        WarehouseShelf warehouseShelf = warehouseShelfMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(warehouseShelf,warehouseShelfVO);
        Warehouse warehouse = warehouseMapper.selectByPrimaryKey(warehouseShelf.getWarehouseId());
        warehouseShelfVO.setWarehouseName(warehouse.getName());

        WarehouseArea warehouseArea = warehouseAreaMapper.selectByPrimaryKey(warehouseShelf.getWarehouseAreaId());
        warehouseShelfVO.setWarehouseAreaName(warehouseArea.getName());

        WarehouseCargoArea warehouseCargoArea = warehouseCargoAreaMapper.selectByPrimaryKey(warehouseShelf.getCargoAreaId());
        warehouseShelfVO.setCargoAreaName(warehouseCargoArea.getName());
        return warehouseShelfVO;
    }

	@Override
	public List<TreePOJO<WarehouseVO>> getWarehouseShelfTreeByParam(
			Long enterpriseId, Integer status, Integer type) {
		List<WarehouseVO> warehouseList = warehouseMapper.getWarehouseTreeByEnterpriseIdForSingle(enterpriseId,status,type);
		
		return setLeafWarehouseVO(warehouseList);
	}

    @Override
    public List<TreePOJO<WarehouseVO>> getWarehouseTreeByEnterpriseIdForDouble(Long enterpriseId, Integer status) {
        //查询出从储存区域中获得的
        List<WarehouseVO> warehouseList = warehouseMapper.getWarehouseTreeByEnterpriseIdForDouble(enterpriseId,status);
        List<TreePOJO<WarehouseVO>> list = setLeafWarehouseVO(warehouseList);
        //将树的模型装换成为前端需要的模型
        for (TreePOJO t:list) {
            t.setOpen(true);
            t.setNodeShowDelete(false);
            //二级树
            if (t.getChildren() != null){
                List<TreePOJO> secondChildren = t.getChildren();
                for (TreePOJO st:secondChildren) {
                    st.setOpen(true);
                    st.setNodeShowDelete(false);
                    //三级树//货区的树，必须选择存储区域
                    if (st.getChildren() != null){
                        List<TreePOJO> thirdChildren = st.getChildren();
                        for (TreePOJO tt:thirdChildren) {
                            tt.setOpen(true);
                            tt.setNodeShowDelete(false);
                            //四级树
                            if (tt.getChildren() != null){
                                List<TreePOJO> fourthChildren = tt.getChildren();
                                for (TreePOJO ft:fourthChildren) {
                                    ft.setOpen(true);
                                    ft.setSupplierShow(false);
                                    ft.setNodeShowDelete(false);
                                    WarehouseShelf warehouseShelf = selectByKeyId(ft.getId());
                                    WarehouseCargoArea warehouseCargoArea = selectByCargoId(warehouseShelf.getCargoAreaId());
                                    /*if (warehouseCargoArea.getJobArea() == ShelfStatus.QUALIFIED.getCode()){
                                        ft.setData("合格");
                                    }else if (warehouseCargoArea.getJobArea() == ShelfStatus.UNQULIFIED.getCode()){
                                        ft.setData("不合格");
                                    }else{
                                        ft.setData("");
                                    }*/
                                    ft.setData(ShelfStatus.getName(warehouseCargoArea.getJobArea()));
                                }
                            }
                        }
                    }
                }
            }
        }
        return list;
    }



    public List<TreePOJO<WarehouseVO>> setLeafWarehouseVO(List<WarehouseVO> warehouseList){
		List<TreePOJO<WarehouseVO>> firstTree = new ArrayList<>();
		
		if(warehouseList != null && warehouseList.size() > 0){
	        for(WarehouseVO warehouseVO : warehouseList){
	        	TreePOJO warehouseVOTreePOJO = new TreePOJO<>();
	            warehouseVOTreePOJO.setId(warehouseVO.getId());
	            warehouseVOTreePOJO.setParentId(null);
	            warehouseVOTreePOJO.setLeaf(false);
	            warehouseVOTreePOJO.setSupplierShow(false);
//		          warehouseVOTreePOJO.setData(warehouseVO);
	            warehouseVOTreePOJO.setLabel(warehouseVO.getName());
	            //warehouseVOTreePOJO.setNodeShowDelete(warehouseMapper.getWhAreaCount(warehouseVO.getId()) <= 0);
                warehouseVOTreePOJO.setNodeShowDelete(false);
	             List<WarehouseAreaVO> warehouseAreaVOList = warehouseVO.getWarehouseAreaVOList();
	            List<TreePOJO<WarehouseAreaVO>> secondTree = new ArrayList<>();
	            for(WarehouseAreaVO warehouseAreaVO : warehouseAreaVOList){
	            	TreePOJO warehouseAreaTreePOJO = new TreePOJO<>();
	                warehouseAreaTreePOJO.setId(warehouseAreaVO.getId());
	                warehouseAreaTreePOJO.setLabel(warehouseAreaVO.getName());
	                warehouseAreaTreePOJO.setParentId(warehouseVO.getId());
	                warehouseAreaTreePOJO.setLeaf(false);
	                warehouseAreaTreePOJO.setSupplierShow(false);
//		            warehouseAreaTreePOJO.setData(warehouseAreaVO);
	               // warehouseAreaTreePOJO.setNodeShowDelete(warehouseMapper.getCargoCountByAreaId(warehouseAreaVO.getId()) <= 0);
                    warehouseAreaTreePOJO.setNodeShowDelete(false);
	                 List<WarehouseCargoAreaVO> warehouseCargoAreaVOList = warehouseAreaVO.getWarehouseCargoAreaVOList();
	                List<TreePOJO<WarehouseCargoAreaVO>> thirdTree = new ArrayList<>();
	                for(WarehouseCargoAreaVO warehouseCargoAreaVO : warehouseCargoAreaVOList){
	                	TreePOJO warehouseCargoAreaVOTreePOJO = new TreePOJO<>();
	                    warehouseCargoAreaVOTreePOJO.setId(warehouseCargoAreaVO.getId());
	                    warehouseCargoAreaVOTreePOJO.setParentId(warehouseAreaVO.getId());
	                    warehouseCargoAreaVOTreePOJO.setLeaf(false);
	                    warehouseCargoAreaVOTreePOJO.setSupplierShow(false);
	                    warehouseCargoAreaVOTreePOJO.setLabel(warehouseCargoAreaVO.getName());
//		                warehouseCargoAreaVOTreePOJO.setData(warehouseCargoAreaVO);
	                    //warehouseCargoAreaVOTreePOJO.setNodeShowDelete(warehouseMapper.getShelfCountByCargoId(warehouseCargoAreaVO.getId()) <= 0);
                        warehouseCargoAreaVOTreePOJO.setNodeShowDelete(false);//// TODO: 2017/9/2 货位的是否可删除的条件判断

                        List<WarehouseShelfVO> list =  warehouseCargoAreaVO.getWarehouseShelfVOList();
	                    //第四级
	                    List<TreePOJO<WarehouseShelfVO>> fourthTree = new ArrayList<>();
	                    for(WarehouseShelfVO warehouseShelfVO : list){
	                    	TreePOJO warehouseShelfVOTreePOJO = new TreePOJO<>();
	                        warehouseShelfVOTreePOJO.setId(warehouseShelfVO.getId());
	                        warehouseShelfVOTreePOJO.setParentId(warehouseCargoAreaVO.getId());
	                        warehouseShelfVOTreePOJO.setLabel(warehouseShelfVO.getName());
	                        warehouseShelfVOTreePOJO.setLeaf(true);
	                        warehouseShelfVOTreePOJO.setSupplierShow(false);
	                        warehouseShelfVOTreePOJO.setNodeShowDelete(false);//// TODO: 2017/9/2 货位的是否可删除的条件判断
	                        warehouseShelfVOTreePOJO.setChildren(new ArrayList<>());
                            /**
                             * 添加质量状况
                             */
                            WarehouseShelf warehouseShelf = selectByKeyId(warehouseShelfVO.getId());
                            WarehouseCargoArea warehouseCargoArea = selectByCargoId(warehouseShelf.getCargoAreaId());
                            warehouseShelfVOTreePOJO.setData(ShelfStatus.getName(warehouseCargoArea.getJobArea()));
	                        fourthTree.add(warehouseShelfVOTreePOJO);
	                    }
	                    warehouseCargoAreaVOTreePOJO.setChildren(fourthTree);
	                    thirdTree.add(warehouseCargoAreaVOTreePOJO);
	                }
	                warehouseAreaTreePOJO.setChildren(thirdTree);
	                secondTree.add(warehouseAreaTreePOJO);
	            }
	            warehouseVOTreePOJO.setChildren(secondTree);
	            firstTree.add(warehouseVOTreePOJO);
	        }
		}
		
		return firstTree;
	}

	@Override
	public List<PosWarehouseCargoAreaVO> findByEnterpriseIdPosgz(Long enterpriseId) throws Exception {
		return warehouseCargoAreaMapper.findByEnterpriseIdPosgz(enterpriseId);
	}

    @Override
    public WarehouseShelf selectByKeyId(Long id) {
        WarehouseShelf warehouseShelf = warehouseShelfMapper.selectByPrimaryKey(id);
        return warehouseShelf;
    }

    @Override
    public WarehouseCargoArea selectByCargoId(Long cargoAreaId) {
        WarehouseCargoArea warehouseCargoArea = warehouseCargoAreaMapper.selectByPrimaryKey(cargoAreaId);
        return warehouseCargoArea;
    }

}