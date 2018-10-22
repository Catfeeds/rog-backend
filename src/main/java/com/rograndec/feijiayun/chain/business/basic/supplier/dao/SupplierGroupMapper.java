package com.rograndec.feijiayun.chain.business.basic.supplier.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierGroup;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierGroupTreeVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface SupplierGroupMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SupplierGroup record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SupplierGroup record);

    /**
     *
     * @mbg.generated
     */
    SupplierGroup selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SupplierGroup record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SupplierGroup record);


	SupplierGroup addSupplierGroup(Map<String, Object> map);

	List<SupplierGroup> selectSupplierGroupSelector(@Param("enterpriseId")Long enterpriseId,@Param("type")int type);

	List<SupplierGroup> selectSupplierTree(Long enterpriseId);

	void updateSupplerGroup(Map<String, Object> map);

    SupplierGroup hasCodeGroup(@Param("code") String code, @Param("enterpriseId") Long enterpriseId);

    SupplierGroup hasNameGroup(@Param("name")String name, @Param("enterpriseId")Long enterpriseId);
}