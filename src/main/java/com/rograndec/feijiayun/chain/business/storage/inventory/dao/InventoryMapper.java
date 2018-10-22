package com.rograndec.feijiayun.chain.business.storage.inventory.dao;

import com.rograndec.feijiayun.chain.business.storage.inventory.entity.Inventory;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.InventoryForOrderDetailVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.InventoryForOrderListVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.RequestParamForListVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.diff.InventoryForDiffDetailVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.diff.RequestParamForDiffListVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.post.InventoryForPostDetailVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.post.InventoryForPostVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.post.InventoryPostVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.post.RequestParamForPostListVO;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.register.InventoryForRegisterVO2;
import com.rograndec.feijiayun.chain.business.storage.inventory.vo.register.RequestParamForHadRegisterListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InventoryMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int insert(Inventory record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(Inventory record);

    /**
     *
     * @mbg.generated
     */
    Inventory selectByPrimaryKey(Long id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Inventory record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Inventory record);


    /**
     * 查询盘点单
     * @param paramForListVO
     * @param enterpriseId
     * @return
     */
    List<InventoryForOrderListVO> getInventoryList(@Param("paramForListVO")RequestParamForListVO paramForListVO, @Param("enterpriseId")Long enterpriseId);

    /**
     * 查询已登记盘点单
     * @param paramForListVO
     * @param enterpriseId
     * @return
     */
    List<InventoryForRegisterVO2> getHadRegisterInventoryOrderList(@Param("paramForListVO")RequestParamForHadRegisterListVO paramForListVO, @Param("enterpriseId")Long enterpriseId);
    /**
     * 查询差异处理已处理
     * @param paramForListVO
     * @param enterpriseId
     * @return
     */
    List<InventoryForDiffDetailVO> getHadHandlerInventoryList(@Param("paramForListVO")RequestParamForDiffListVO paramForListVO, @Param("enterpriseId")Long enterpriseId);
    Integer getHadHandlerInventoryListCount(@Param("paramForListVO")RequestParamForDiffListVO paramForListVO, @Param("enterpriseId")Long enterpriseId);

    InventoryForOrderDetailVO getInventoryById(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    Inventory getInventoryById4ExportExcel(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    InventoryForRegisterVO2 getInventoryByIdForRegister(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    InventoryForDiffDetailVO getInventoryByIdForDiff(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    InventoryForPostDetailVO getInventoryByIdForPost(@Param("enterpriseId")Long enterpriseId, @Param("id")Long id);

    /**
     * 查询已过账列表
     * @param paramForListVO
     * @param enterpriseId
     * @return
     */
    List<InventoryForPostVO> getInventoryPostOrderList(@Param("paramForListVO") RequestParamForPostListVO paramForListVO, @Param("enterpriseId") Long enterpriseId);

    Integer getInventoryPostOrderListCount(@Param("paramForListVO") RequestParamForPostListVO paramForListVO, @Param("enterpriseId") Long enterpriseId);

    InventoryPostVO getTotalForPost();

    void updateInventory(@Param("id")Long id,@Param("status")Integer status);

}