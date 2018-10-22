package com.rograndec.feijiayun.chain.business.goods.pharmacy.service.impl;

import com.rograndec.feijiayun.chain.business.goods.pharmacy.dao.IncompatibilityGoodsOneMapper;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.dao.IncompatibilityGoodsTwoMapper;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.dao.IncompatibilityMapper;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.entity.Incompatibility;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.entity.IncompatibilityGoodsOne;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.entity.IncompatibilityGoodsTwo;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.service.IncompatibilityService;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.CommonParamSupplierAndGoods;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.param.ParamUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zeshi.sun on 2017/9/7.
 */

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
public class IncompatibilityServiceImpl implements IncompatibilityService {

    @Autowired
    IncompatibilityMapper incompatibilityMapper;

    @Autowired
    IncompatibilityGoodsOneMapper incompatibilityGoodsOneMapper;

    @Autowired
    IncompatibilityGoodsTwoMapper incompatibilityGoodsTwoMapper;

    @Override
    public List<SelectGoodsVO> selectGoodsVoPage(int pageNo, int pageSize, String key, Long enterpriseId, Page page,UserVO userVO) {

        CommonParamSupplierAndGoods param = new CommonParamSupplierAndGoods();
        ParamUtils.packParam(userVO,param);
        Long totalRecord = incompatibilityMapper.queryCountByParams(enterpriseId, page.getStart(), pageSize, key,param);
        List<SelectGoodsVO> list = incompatibilityMapper.selectGoodsVoByParams(enterpriseId, page.getStart(), pageSize, key,param);
        page.setTotalRecord(totalRecord == null ? 0 : totalRecord.intValue());
        return list;
    }


    @Override
    public List<IncompatibilityVO3> incompatibilityVoPage(int pageNo, int pageSize, String key, Long enterpriseId, Page page, Long type, String orderType,UserVO loginUser) {
        List<IncompatibilityVO> listVO;
        List<IncompatibilityVO2> listVO2;

        Long parentId=null;
        //若是门店，查看自己和总部，若是总部查看自己和门店
        if(loginUser.getChainType() != 0) parentId=loginUser.getParentId();
        Long totalRecord = incompatibilityMapper.queryCountByParam(enterpriseId, page.getStart(), pageSize, key, type,parentId);
        
        List<IncompatibilityVO3> listVO3 = incompatibilityMapper.IncompatibilityVo3ByParam(enterpriseId, page.getStart(), pageSize, key, orderType, type,parentId);
        //若是总部或是加盟店则判断是否可编辑
        if(loginUser.getChainType() == 2 || loginUser.getChainType() == 0 ) validUpdateFlag(listVO3, loginUser);
        
        for (int i = 0; i < listVO3.size(); i++) {
            Long id = listVO3.get(i).getId();
            listVO2 = incompatibilityGoodsOneMapper.IncompatibilityVo2ByParam(enterpriseId, id);
            for (int a = 0; a < listVO2.size(); a++) {
                String goodsNames = listVO2.get(a).getGoodsNames();
                String goodsName[] = goodsNames.split(",");
                if (goodsName.length == 1) {
                    String name = goodsName[0];
                    String goodsName_s[] = name.split("-");
                    if (goodsName_s.length > 1) {
                        listVO2.get(a).setGoodsNames(goodsName_s[1]);
                    }
                } else {
                    String returnNames_s = "";
                    String returnNames = "";
                    for (int c = 0; c < goodsName.length; c++) {
                        String name = goodsName[c];
                        String goodsName_s[] = name.split("-");
                        if (goodsName_s.length > 1) {
                            String name_s = goodsName_s[1];
                            returnNames += name_s + ",";
                            returnNames_s = returnNames.substring(0, returnNames.length() - 1);
                        }
                    }
                    listVO2.get(a).setGoodsNames(returnNames_s);
                }
            }
            for (int j = 0; j < listVO2.size(); j++) {
                Long id2 = listVO2.get(j).getId();
                listVO = incompatibilityGoodsTwoMapper.IncompatibilityVoByParam(enterpriseId, id2);
                for (int b = 0; b < listVO.size(); b++) {
                    String goodsNamesVO = listVO.get(b).getCompatibilityGoodsNames();
                    String goodsNameVO[] = goodsNamesVO.split(",");
                    if (goodsNameVO.length == 1) {
                        String name = goodsNameVO[0];
                        String goodsName_s[] = name.split("-");
                        if (goodsName_s.length > 1) {
                            listVO.get(b).setCompatibilityGoodsNames(goodsName_s[1]);
                        }
                    } else {
                        String returnNames_s = "";
                        String returnNames_2 = "";
                        for (int c = 0; c < goodsNameVO.length; c++) {
                            String name = goodsNameVO[c];
                            String goodsName_s[] = name.split("-");
                            if (goodsName_s.length > 1) {
                                String name_s = goodsName_s[1];
                                returnNames_2 += name_s + ",";
                                returnNames_s = returnNames_2.substring(0, returnNames_2.length() - 1);
                            }
                        }
                        listVO.get(b).setCompatibilityGoodsNames(returnNames_s);
                    }
                }
                listVO2.get(j).setCompatibilityGoodsList(listVO);
            }
            listVO3.get(i).setGoodsList(listVO2);
        }
        page.setTotalRecord(totalRecord == null ? 0 : totalRecord.intValue());
        return listVO3;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteIncompatibility(Long id,UserVO loginUser) throws Exception {
    	//判断更改权限
    	validUpdateOrDelete(id, loginUser);
        //先删除saas_incompatibility_goods_two（配伍禁忌-配伍药品-子表）
        deleteIncompatibilityGoodsTwo(id);
        //再删除saas_incompatibility_goods_one（配伍禁忌-药品-子表）
        deleteIncompatibilityGoodsOne(id);
        //最后删除saas_incompatibility（配伍禁忌-基表）
        incompatibilityMapper.deleteByPrimaryKey(id);
        return 0;
    }


    public int deleteIncompatibilityGoodsOne(Long id) throws Exception {
        return incompatibilityGoodsOneMapper.deleteByKey(id);
    }


    public int deleteIncompatibilityGoodsTwo(Long id) throws Exception {
        return incompatibilityGoodsTwoMapper.deleteByKey(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void saveIncompatibility(UserVO loginUser, SaveIncompatibilityVO saveIncompatibilityVO) throws Exception, BusinessException {

        Incompatibility incompatibility = new Incompatibility();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveIncompatibilityVO.getIncompatibilityJson(), incompatibility);
        Long enterpriseId = loginUser.getEnterpriseId();
        String varityName = incompatibility.getVarity();
        
        if(varityName==null ||varityName.trim().length()==0)	throw new BusinessException("种类必填");
        
        //种类判重
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("varityName", varityName);
        param.put("enterpriseId", enterpriseId);
        List<Incompatibility> incompatibilityList = incompatibilityMapper.selectByCode(param);
        if (!incompatibilityList.isEmpty())
            throw new BusinessException("种类重复,请重新填写");
        //验证药品重复，配伍结果必填验证
        validGoodsIds(saveIncompatibilityVO);
        //固定数据
        UserEnterpriseUtils.setUserCreateOrModify(incompatibility, loginUser, true);
        incompatibility.setEnterpriseId(loginUser.getEnterpriseId());
        incompatibility.setParentId(loginUser.getParentId());
        incompatibility.setId(null);
        incompatibilityMapper.insertSelective(incompatibility);

        saveIncompatibilityGoodsOne(loginUser, incompatibility, saveIncompatibilityVO);

    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void saveIncompatibilityGoodsOne(UserVO loginUser, Incompatibility incompatibility, SaveIncompatibilityVO saveIncompatibilityVO) throws Exception, BusinessException {

        IncompatibilityGoodsOne incompatibilityGoodsOne = new IncompatibilityGoodsOne();
        List<SaveIncompatibilityGoodsOneVO> saveIncompatibilityGoodsOneVOS = saveIncompatibilityVO.getIncompatibilityJson().getGoodsList();
        for (SaveIncompatibilityGoodsOneVO saveIncompatibilityGoodsOneVO : saveIncompatibilityGoodsOneVOS) {
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveIncompatibilityGoodsOneVO, incompatibilityGoodsOne);
            incompatibilityGoodsOne.setEnterpriseId(loginUser.getEnterpriseId());
            incompatibilityGoodsOne.setParentId(loginUser.getParentId());
            incompatibilityGoodsOne.setId(null);
            incompatibilityGoodsOne.setIncompatibilityId(incompatibility.getId());
            if (incompatibilityGoodsOne.getGoodsCodes() != null && incompatibilityGoodsOne.getGoodsNames() != null) {
                String[] codes = incompatibilityGoodsOne.getGoodsCodes().split(",");
                String[] names = incompatibilityGoodsOne.getGoodsNames().split(",");
                String codeNames = "";
                for (int i = 0; i < codes.length; i++) {
                    String codeName = codes[i] + "-" + names[i];
                    codeNames += codeName + ",";
                }
                incompatibilityGoodsOne.setGoodsNames(codeNames.substring(0, codeNames.length() - 1));
            } else {
                throw new BusinessException("药物为必填字段,否则无法保存");
            }
            incompatibilityGoodsOneMapper.insertSelective(incompatibilityGoodsOne);

            IncompatibilityGoodsTwo incompatibilityGoodsTwo = new IncompatibilityGoodsTwo();
            for (SaveIncompatibilityGoodsTwoVO saveIncompatibilityGoodsTwoVO : saveIncompatibilityGoodsOneVO.getCompatibilityGoodsList()) {
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveIncompatibilityGoodsTwoVO, incompatibilityGoodsTwo);
                incompatibilityGoodsTwo.setEnterpriseId(loginUser.getEnterpriseId());
                incompatibilityGoodsTwo.setParentId(loginUser.getParentId());
                incompatibilityGoodsTwo.setIncompatibilityId(incompatibility.getId());
                incompatibilityGoodsTwo.setOneId(incompatibilityGoodsOne.getId());
                if (incompatibilityGoodsTwo.getCompatibilityGoodsCodes() != null && incompatibilityGoodsTwo.getCompatibilityGoodsNames() != null) {
                    String[] codes = incompatibilityGoodsTwo.getCompatibilityGoodsCodes().split(",");
                    String[] names = incompatibilityGoodsTwo.getCompatibilityGoodsNames().split(",");
                    String codeNames = "";
                    for (int i = 0; i < codes.length; i++) {
                        String codeName = codes[i] + "-" + names[i];
                        codeNames += codeName + ",";
                    }
                    incompatibilityGoodsTwo.setCompatibilityGoodsNames(codeNames.substring(0, codeNames.length() - 1));
                } else {
                    throw new BusinessException("配伍药物为必填字段,否则无法保存");
                }
                incompatibilityGoodsTwo.setId(null);
                incompatibilityGoodsTwoMapper.insertSelective(incompatibilityGoodsTwo);
            }
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void updateIncompatibilitys(UserVO loginUser, SaveIncompatibilityVO saveIncompatibilityVO) throws Exception, BusinessException {
	   
    	String varityName=saveIncompatibilityVO.getIncompatibilityJson().getVarity();
    	if(varityName==null || varityName.trim().length()==0) throw new BusinessException("种类不能为空");
    	
    	Long id=saveIncompatibilityVO.getIncompatibilityJson().getId();
    	if(id==null) throw new BusinessException("参数错误");
    	//判断更改权限
    	validUpdateOrDelete(saveIncompatibilityVO.getIncompatibilityJson().getId(), loginUser);
    	//种类判重
    	Map<String,Object> param = new HashMap<String,Object>();
    	param.put("varityName",varityName);
    	param.put("enterpriseId", loginUser.getEnterpriseId());
    	param.put("id", id);
    	List<Incompatibility> incompatibilityList = incompatibilityMapper.selectByCode(param);
    	if (!incompatibilityList.isEmpty()) throw new BusinessException("种类重复,请重新填写");

    	//验证药品重复，配伍结果必填验证
        validGoodsIds(saveIncompatibilityVO);
        //删除行信息
        deleteInfo(saveIncompatibilityVO, loginUser);
        
        Incompatibility incompatibility = new Incompatibility();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveIncompatibilityVO.getIncompatibilityJson(),incompatibility);
        UserEnterpriseUtils.setUserCreateOrModify(incompatibility, loginUser, false);
        incompatibilityMapper.updateByPrimaryKeySelective(incompatibility);

        updateIncompatibilityGoodsOnes(loginUser, incompatibility, saveIncompatibilityVO);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void updateIncompatibilityGoodsOnes(UserVO loginUser, Incompatibility incompatibility, SaveIncompatibilityVO saveIncompatibilityVO) throws Exception {

        List<SaveIncompatibilityGoodsOneVO> saveIncompatibilityGoodsOneVOS = saveIncompatibilityVO.getIncompatibilityJson().getGoodsList();
        for (SaveIncompatibilityGoodsOneVO saveIncompatibilityGoodsOneVO : saveIncompatibilityGoodsOneVOS) {
        	IncompatibilityGoodsOne incompatibilityGoodsOne = new IncompatibilityGoodsOne();
        	
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveIncompatibilityGoodsOneVO, incompatibilityGoodsOne);
            
            if (incompatibilityGoodsOne.getId() != null) {//更新
                if (incompatibilityGoodsOne.getGoodsCodes() != null && incompatibilityGoodsOne.getGoodsNames() != null) {
                    String[] codes = incompatibilityGoodsOne.getGoodsCodes().split(",");
                    String[] names = incompatibilityGoodsOne.getGoodsNames().split(",");
                    String codeNames = "";
                    for (int i = 0; i < codes.length; i++) {
                        String codeName = codes[i] + "-" + names[i];
                        codeNames += codeName + ",";
                    }
                    incompatibilityGoodsOne.setGoodsNames(codeNames.substring(0, codeNames.length() - 1));
                } else {
                    throw new BusinessException("药物为必填字段,否则无法保存");
                }
                //添加修改人信息
                UserEnterpriseUtils.setUserCreateOrModify(incompatibilityGoodsOne, loginUser, false);
                incompatibilityGoodsOneMapper.updateByPrimaryKeySelective(incompatibilityGoodsOne);
            } else {
            	//添加创建人及修改人信息
                UserEnterpriseUtils.setUserCreateOrModify(incompatibilityGoodsOne, loginUser, true);
                incompatibilityGoodsOne.setEnterpriseId(loginUser.getEnterpriseId());
                incompatibilityGoodsOne.setParentId(loginUser.getParentId());
                incompatibilityGoodsOne.setId(null);
                if (incompatibilityGoodsOne.getGoodsCodes() != null && incompatibilityGoodsOne.getGoodsNames() != null) {
                    String[] codes = incompatibilityGoodsOne.getGoodsCodes().split(",");
                    String[] names = incompatibilityGoodsOne.getGoodsNames().split(",");
                    String codeNames = "";
                    for (int i = 0; i < codes.length; i++) {
                        String codeName = codes[i] + "-" + names[i];
                        codeNames += codeName + ",";
                    }
                    incompatibilityGoodsOne.setGoodsNames(codeNames.substring(0, codeNames.length() - 1));
                } else {
                    throw new BusinessException("药物为必填字段,否则无法保存");
                }
                incompatibilityGoodsOne.setIncompatibilityId(incompatibility.getId());
                incompatibilityGoodsOneMapper.insertSelective(incompatibilityGoodsOne);
            }
            for (SaveIncompatibilityGoodsTwoVO saveIncompatibilityGoodsTwoVO : saveIncompatibilityGoodsOneVO.getCompatibilityGoodsList()) {
            	IncompatibilityGoodsTwo incompatibilityGoodsTwo = new IncompatibilityGoodsTwo();
            	CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveIncompatibilityGoodsTwoVO, incompatibilityGoodsTwo);
                if (incompatibilityGoodsTwo.getId() != null) {
                    if (incompatibilityGoodsTwo.getCompatibilityGoodsCodes() != null && incompatibilityGoodsTwo.getCompatibilityGoodsNames() != null) {
                        String[] codes = incompatibilityGoodsTwo.getCompatibilityGoodsCodes().split(",");
                        String[] names = incompatibilityGoodsTwo.getCompatibilityGoodsNames().split(",");
                        String codeNames = "";
                        for (int i = 0; i < codes.length; i++) {
                            String codeName = codes[i] + "-" + names[i];
                            codeNames += codeName + ",";
                        }
                        incompatibilityGoodsTwo.setCompatibilityGoodsNames(codeNames.substring(0, codeNames.length() - 1));
                    } else {
                        throw new BusinessException("配伍药物为必填字段,否则无法保存");
                    }
                    //添加修改人信息
                    UserEnterpriseUtils.setUserCreateOrModify(incompatibilityGoodsTwo, loginUser, false);
                    incompatibilityGoodsTwoMapper.updateByPrimaryKeySelective(incompatibilityGoodsTwo);
                } else {
                    incompatibilityGoodsTwo.setEnterpriseId(loginUser.getEnterpriseId());
                    incompatibilityGoodsTwo.setParentId(loginUser.getParentId());
                    incompatibilityGoodsTwo.setIncompatibilityId(incompatibility.getId());
                    incompatibilityGoodsTwo.setOneId(incompatibilityGoodsOne.getId());
                    if (incompatibilityGoodsTwo.getCompatibilityGoodsCodes() != null && incompatibilityGoodsTwo.getCompatibilityGoodsNames() != null) {
                        String[] codes = incompatibilityGoodsTwo.getCompatibilityGoodsCodes().split(",");
                        String[] names = incompatibilityGoodsTwo.getCompatibilityGoodsNames().split(",");
                        String codeNames = "";
                        for (int i = 0; i < codes.length; i++) {
                            String codeName = codes[i] + "-" + names[i];
                            codeNames += codeName + ",";
                        }
                        incompatibilityGoodsTwo.setCompatibilityGoodsNames(codeNames.substring(0, codeNames.length() - 1));
                    } else {
                        throw new BusinessException("配伍药物为必填字段,否则无法保存");
                    }
                    //添加创建人及修改人信息
                    UserEnterpriseUtils.setUserCreateOrModify(incompatibilityGoodsTwo, loginUser, true);
                    incompatibilityGoodsTwo.setId(null);
                    incompatibilityGoodsTwoMapper.insertSelective(incompatibilityGoodsTwo);
                }
            }
        }
    }
    
    /**
     * 验证重复，配伍结果必填验证
     */
    private void validGoodsIds(SaveIncompatibilityVO saveIncompatibilityVO) {
    	 List<SaveIncompatibilityGoodsOneVO> saveIncompatibilityGoodsOneVOS = saveIncompatibilityVO.getIncompatibilityJson().getGoodsList();
    	 
         if(saveIncompatibilityGoodsOneVOS==null || saveIncompatibilityGoodsOneVOS.size()==0) throw new BusinessException("请至少添加一行信息，或整体删除");
    	 
    	 for (SaveIncompatibilityGoodsOneVO saveIncompatibilityGoodsOneVO : saveIncompatibilityGoodsOneVOS) {
    		 //判断药物是否重复
    		 String oneGoodsIdsStr=saveIncompatibilityGoodsOneVO.getGoodsIds();
    		 if(oneGoodsIdsStr==null||oneGoodsIdsStr.length()==0) {
    			 throw new BusinessException("药物不能为空");
    		 }
    		 String[] oneStr=oneGoodsIdsStr.split(",");
    		 List<Long> oneGoodsIds=new ArrayList<Long>();
    		 Long oneGoodsId=0L;
    		 for(String id:oneStr) {
    			 oneGoodsId=Long.parseLong(id);
    			 if(oneGoodsIds.contains(oneGoodsId)) {
    				 throw new BusinessException("药物重复添加");
    			 }
    			 oneGoodsIds.add(oneGoodsId);
    		 }
    		 
    		 List<SaveIncompatibilityGoodsTwoVO> twoList= saveIncompatibilityGoodsOneVO.getCompatibilityGoodsList();
    		 //判断配伍药品是否重复
    		 List<Long> twoGoodsIdsList=new ArrayList<Long>();
    		 for (SaveIncompatibilityGoodsTwoVO saveIncompatibilityGoodsTwoVO :twoList) {
    			 //配伍结果必填验证
    			 String result=saveIncompatibilityGoodsTwoVO.getCompatibilityResult();
    			 if(result==null || result.trim().length()==0) {
    				 throw new BusinessException("配伍结果不能为空");
    			 }
    			 
    			 String twoGoodsIdsStr=saveIncompatibilityGoodsTwoVO.getCompatibilityGoodsIds();
        		 if(twoGoodsIdsStr==null||twoGoodsIdsStr.length()==0) {
        			 throw new BusinessException("配伍药物不能为空");
        		 }
        		 String[] twoStr=twoGoodsIdsStr.split(",");
        		 List<Long> twoGoodsIds=new ArrayList<Long>();
        		 Long goodsId=0L;
        		 for(String id:twoStr) {
        			 goodsId=Long.parseLong(id);
        			 if(twoGoodsIds.contains(goodsId)) {
        				 throw new BusinessException("配伍药物重复添加");
        			 }
        			 twoGoodsIds.add(goodsId);
        		 }
        		 for(Long id:twoGoodsIds) {
        			 if(twoGoodsIdsList.contains(id)) {
        				 throw new BusinessException("配伍药物重复添加");
        			 }
        		 }
        		 twoGoodsIdsList.addAll(twoGoodsIds);
    		 }
    		 
    		 //判断药品与配伍药品是否重复
    		 for(Long id:oneGoodsIds) {
    			 if(twoGoodsIdsList.contains(id)) {
    				 if(twoGoodsIdsList.contains(id)) {
        				 throw new BusinessException("配伍药物与药物重复");
        			 }
    			 }
    		 }
         }
    }
    
    /**
     * 判断修改时需要删除的行信息，并删除
     */
    private void deleteInfo(SaveIncompatibilityVO saveIncompatibilityVO,UserVO loginUser) {
    	 List<SaveIncompatibilityGoodsOneVO> saveIncompatibilityGoodsOneVOS = saveIncompatibilityVO.getIncompatibilityJson().getGoodsList();
    	 
    	 List<Long> oneIds=new ArrayList<>();
    	 List<Long> twoIds=new ArrayList<>();
    	 for (SaveIncompatibilityGoodsOneVO saveIncompatibilityGoodsOneVO : saveIncompatibilityGoodsOneVOS) {
    		 Long oneId=saveIncompatibilityGoodsOneVO.getId();
    		 if(oneId!=null) oneIds.add(oneId);
    		 List<SaveIncompatibilityGoodsTwoVO> twoList= saveIncompatibilityGoodsOneVO.getCompatibilityGoodsList();
    		 for (SaveIncompatibilityGoodsTwoVO saveIncompatibilityGoodsTwoVO :twoList) {
    			 Long twoId=saveIncompatibilityGoodsTwoVO.getId();
    			 if(twoId!=null) twoIds.add(twoId);
    		 }
         }
    	 oneIds=oneIds.isEmpty()?null:oneIds;
    	 twoIds=twoIds.isEmpty()?null:twoIds;
    	 Map<String,Object> param=new HashMap<>();
    	 param.put("enterpriseId", loginUser.getEnterpriseId());
    	 param.put("id", saveIncompatibilityVO.getIncompatibilityJson().getId());
    	 param.put("list", oneIds);
    	 incompatibilityMapper.deleteFormGoodsOne(param);
    	 param.put("list", twoIds);
    	 incompatibilityMapper.deleteFormGoodsTwo(param);
    }
    
    /**
     * 若是加盟店则添加是否可编辑标签
     * @param listVO3
     * @param loginUser
     */
    private void validUpdateFlag(List<IncompatibilityVO3> listVO3,UserVO loginUser) {
    	Long enterpriseId=loginUser.getEnterpriseId();
    	for(IncompatibilityVO3 v:listVO3) {
    		v.setUpdateFlag(enterpriseId.equals(v.getEnterpriseId()));
    	}
    }
    
    /**
     * 判断是否可以删除
     */
    private void validUpdateOrDelete(Long id,UserVO loginUser) {
    	Incompatibility Incompatibility= incompatibilityMapper.selectByPrimaryKey(id);
    	if(Incompatibility==null)  throw new BusinessException("已删除");
    	if(!Incompatibility.getEnterpriseId().equals(loginUser.getEnterpriseId())) throw new BusinessException("无操作权限");
    }

    @Override
    public List<IncompatibilityGoodsVO> getIncompatibilityGoodsByGoodsId(UserVO loginUser, String goodsId) {
        Map map=new HashMap();
        map.put("goodsId",goodsId);
        if (loginUser.getChainType().equals(ChainType.Division.getCode())){
            //如果是加盟店
            map.put("parentId",loginUser.getParentId());
            map.put("enterpriseId",loginUser.getEnterpriseId());
        }else if (loginUser.getChainType().equals(ChainType.Selfoperatedshop.getCode())){
            //如果是自营店
            map.put("parentId",loginUser.getParentId());
            map.put("enterpriseId",loginUser.getEnterpriseId());
        }else if (loginUser.getChainType().equals(ChainType.Headquarters.getCode())){
            //如果是总店
            map.put("enterpriseId",loginUser.getEnterpriseId());
        }
        List<IncompatibilityGoodsVO> orginList=incompatibilityMapper.getIncompatibilityGoodsByGoodsId(map);
        List<IncompatibilityGoodsVO> newList=new ArrayList<>();
        orginList.forEach(item->{
            String[] ids =item.getCompatibilityGoodsId().split(",");
            for(int i=0;i<ids.length;i++){
                IncompatibilityGoodsVO incompatibilityGoodsVO=new IncompatibilityGoodsVO();
                incompatibilityGoodsVO.setCompatibilityGoodsId(ids[i]);
                incompatibilityGoodsVO.setCompatibilityResult(item.getCompatibilityResult());
                newList.add(incompatibilityGoodsVO);
            }
        });
        return newList;
    }
}
