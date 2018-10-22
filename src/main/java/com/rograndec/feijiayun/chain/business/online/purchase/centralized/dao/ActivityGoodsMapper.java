package com.rograndec.feijiayun.chain.business.online.purchase.centralized.dao;

import com.rograndec.feijiayun.chain.business.online.purchase.centralized.entity.ActivityGoods;
import com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo.SelectActivityGoodsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityGoodsMapper {
    /**
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int insert(ActivityGoods record);

    /**
     *
     * @mbg.generated
     */
    int insertSelective(ActivityGoods record);

    /**
     *
     * @mbg.generated
     */
    ActivityGoods selectByPrimaryKey(Integer id);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(ActivityGoods record);

    /**
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(ActivityGoods record);

    Long queryParams(@Param("start")int start, @Param("pageSize")int pageSize, @Param("id")Long id, @Param("searchValues")String searchValues, @Param("gcName1")String gcName1,
                     @Param("gcName2")String gcName2, @Param("manufacturer")String manufacturer);

    List<SelectActivityGoodsVO> selectByActivityId(@Param("start")int start, @Param("pageSize")int pageSize, @Param("id")Long id, @Param("searchValues")String searchValues, @Param("gcName1")String gcName1,
                                                   @Param("gcName2")String gcName2, @Param("manufacturer")String manufacturer);

    int checkRepeat(@Param("dataS")String dataS, @Param("dataTwo")String dataTwo);

    ActivityGoods selectByActivityIdAndGoodsId(@Param("activityId")Integer activityId, @Param("goodsId")String goodsId);
}