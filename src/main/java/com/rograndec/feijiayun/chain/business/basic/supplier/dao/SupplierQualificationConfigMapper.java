package com.rograndec.feijiayun.chain.business.basic.supplier.dao;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierQualificationConfig;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierQulificationVO;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.QualificationConfigVO;

import java.util.List;
import java.util.Map;

public interface SupplierQualificationConfigMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SupplierQualificationConfig record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SupplierQualificationConfig record);

    /**
     *
     * @mbg.generated
     */
    SupplierQualificationConfig selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SupplierQualificationConfig record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SupplierQualificationConfig record);

	void saveQualificationSupplier(Map<String, Object> map);

	List<SupplierQulificationVO> getDefaultSupplierQulification(Long enterpriseId);

    List<QualificationConfigVO> getQualificationConfigReport(Long supplierId);

    Long checkDeteleEnterpriseData(Long id);

    void deleteBySupplierId(Long id);
}