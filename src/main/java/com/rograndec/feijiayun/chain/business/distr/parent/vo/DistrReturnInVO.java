package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnIn;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnInDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnInShelf;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * saas_distr_return_in
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-10-08
 */
public class DistrReturnInVO implements Serializable {

    private DistrReturnIn distrReturnIn;

    private List<DistrReturnInDetail> distrReturnInDetails;

    private List<DistrReturnInShelf> distrReturnInShelfs;

    /*private Map<Long, List<DistrInReturnOutShelf>> shelfsGoodsMap;*/

    private Supplier supplier;

    private Long returnManId;

    private Long qualitySetId;

    private EnterpriseBusiness bus;

    private Enterprise enterprise;

    private Enterprise parentEnterprise;

    private UserVO sourceUser;

    /**
     * goodsid_lotnum 与 配后退回入库生成批号表后生成的lotid map 例如:GOODSID_LOTNUM : LOTID
     */
    private Map<String,Long> goodsLotNumMap = new HashMap<>();

    public DistrReturnIn getDistrReturnIn() {
        return distrReturnIn;
    }

    public void setDistrReturnIn(DistrReturnIn distrReturnIn) {
        this.distrReturnIn = distrReturnIn;
    }

    public List<DistrReturnInDetail> getDistrReturnInDetails() {
        return distrReturnInDetails;
    }

    public void setDistrReturnInDetails(List<DistrReturnInDetail> distrReturnInDetails) {
        this.distrReturnInDetails = distrReturnInDetails;
    }

    public List<DistrReturnInShelf> getDistrReturnInShelfs() {
        return distrReturnInShelfs;
    }

    public void setDistrReturnInShelfs(List<DistrReturnInShelf> distrReturnInShelfs) {
        this.distrReturnInShelfs = distrReturnInShelfs;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Long getReturnManId() {
        return returnManId;
    }

    public void setReturnManId(Long returnManId) {
        this.returnManId = returnManId;
    }

    public Long getQualitySetId() {
        return qualitySetId;
    }

    public void setQualitySetId(Long qualitySetId) {
        this.qualitySetId = qualitySetId;
    }


    /*public Map<Long, List<DistrInReturnOutShelf>> getShelfsGoodsMap() {
        return shelfsGoodsMap;
    }

    public void setShelfsGoodsMap(Map<Long, List<DistrInReturnOutShelf>> shelfsGoodsMap) {
        this.shelfsGoodsMap = shelfsGoodsMap;
    }*/

    public EnterpriseBusiness getBus() {
        return bus;
    }

    public void setBus(EnterpriseBusiness bus) {
        this.bus = bus;
    }

    public UserVO getSourceUser() {
        return sourceUser;
    }

    public void setSourceUser(UserVO sourceUser) {
        this.sourceUser = sourceUser;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public Enterprise getParentEnterprise() {
        return parentEnterprise;
    }

    public void setParentEnterprise(Enterprise parentEnterprise) {
        this.parentEnterprise = parentEnterprise;
    }

    public Map<String, Long> getGoodsLotNumMap() {
        return goodsLotNumMap;
    }

    public void setGoodsLotNumMap(Map<String, Long> goodsLotNumMap) {
        this.goodsLotNumMap = goodsLotNumMap;
    }
}