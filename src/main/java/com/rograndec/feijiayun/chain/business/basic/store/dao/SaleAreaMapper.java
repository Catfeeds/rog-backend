package com.rograndec.feijiayun.chain.business.basic.store.dao;

import com.rograndec.feijiayun.chain.business.basic.store.entity.SaleArea;
import com.rograndec.feijiayun.chain.business.basic.store.vo.ExportVO;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreVO;
import com.rograndec.feijiayun.chain.common.NewSelectBean;
import com.rograndec.feijiayun.chain.common.SelectBean;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SaleAreaMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(SaleArea record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(SaleArea record);

    /**
     *
     * @mbg.generated
     */
    SaleArea selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SaleArea record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(SaleArea record);

    
    /**
     * @Description: 根据总部ID查询所有销售片区数据
     * @author liuqun
     * @version 1.0 
     * @date 2017年8月29日 上午10:03:12 
     * @param enterpriseId
     * @return 
     * @return List<SelectBean>
     */
	List<NewSelectBean> selectSaleAreaByEnterpriseId(Long enterpriseId);

	List<SaleArea> selectByEnterpriseId(Long enterpriseId);

    List<SaleArea> selectByEnterpriseIdStatus(@Param("enterpriseId") Long enterpriseId,@Param("status")Integer status);

    List<StoreVO> selectByPrimaryKeyNotExistsShopIds(Map param);

    int deleteByPrimaryKeys(List<Long> ids);

    List<SaleArea> getByEnterpriseId(Long enterpriseId);

    List<ExportVO> selectExport(Long enterpriseId);

    List<Long> selectStoreMapBySaleAreaId(Long enterpriseId);
}