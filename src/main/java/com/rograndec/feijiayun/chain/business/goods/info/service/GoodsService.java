package com.rograndec.feijiayun.chain.business.goods.info.service;

import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.vo.*;
import com.rograndec.feijiayun.chain.business.storage.split.vo.CanSpitGoodVO;
import com.rograndec.feijiayun.chain.business.storage.split.vo.SplitSetSaveVO;
import com.rograndec.feijiayun.chain.business.system.set.vo.BusinessScopeVO;
import com.rograndec.feijiayun.chain.business.system.set.vo.QualitySetVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.CommonParamSupplierAndGoods;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by ST on 2017/8/23.
 */
public interface GoodsService {
    /**
     * 判断商品编码是否存在
     * @param code
     * @return
     */

    Goods getGoodsByCode(Long enterpriseId, String code);

    Goods getGoodsByCode2(CommonParamSupplierAndGoods paramSupplierAndGoods, UserVO userVO,String code);

    Goods getGoodsByOldCode(String code, Long enterpriseId);


    Page<List<GoodsVO>> getGoodsListByParam(UserVO userVO, RequestGoodsVO goodsVO, Page<List<GoodsVO>> page);

    /**
     * 添加商品
     * @param goodsVO
     * @param exlImport
     * @return
     */
    int addGoods(UserVO userVO, GoodsVO goodsVO, boolean exlImport) throws Exception;

    /**
     * 商品还拆零生成新的商品
     * @param userVO 用户信息
     * @param splitSetSaveVO 拆零设置信息
     * @return
     * @throws Exception
     */
    int addSplitGoods(UserVO userVO, SplitSetSaveVO splitSetSaveVO) throws Exception;

    /**
     * 查询生产企业
     * @param enterpriseId
     * @return
     */
    List<ResponseManufacturerVO> getManufacturer(Long enterpriseId);

    /**
     * 查询储存条件
     * @param enterpriseId
     * @return
     */
    List<ResponseStorageConditionVO> getStorageCondition(Long enterpriseId);

    /**
     * 查询经营范围
     * @param businessVariety
     * @param status
     * @param enterpriseId
     * @return
     */
    List<BusinessScopeVO> getBusinessScopeVOList(Integer businessVariety,Integer status,Long enterpriseId);

    /**
     * 查询验收分类
     * @param enterpriseId
     * @param setType
     * @param type
     * @param status
     * @return
     */
    List<QualitySetVO> getQualitySetVOList(@Param("enterpriseId")Long enterpriseId, @Param("setType")Integer setType, @Param("type")Integer type, @Param("status")Integer status );


    /**
     * 修改商品信息
     * @param userVO
     * @param goodsVO
     * @return
     */
    int updateGoods(UserVO userVO,GoodsVO goodsVO) throws Exception;


    /**
     * 根据商品id 查询商品资质配置
     * @param goodsId
     * @return
     */
    List<GoodsQualificationConfigVO> getQualificationConfigList(Long goodsId);


    /**
     * 商品excel 导入
     * @param request
     * @return
     * @throws Exception
     */
    ResponseGoodsExcelVO excelImport(HttpServletRequest request,boolean update) throws Exception;

    /**
     * 导入成功数据
     * @param key
     * @return
     * @throws Exception
     */
    void importSuccessGoods(UserVO userVO,String key) throws Exception;

    /**
     * 导出成功数据
     * @param key
     * @param type 1--导出成功数据；2--导出失败数据
     * @return
     * @throws Exception
     */
    void exportGoods(OutputStream output,String key,Integer type) throws Exception;


    void exportGoodsInfo(OutputStream outputStream, RequestGoodsVO requestGoodsVO,UserVO userVO);

    Page<List<ResponseGoodsModifyRecordVO>> getGoodsModifyRecord(Long enterpriseId, Long parentId, Page<List<ResponseGoodsModifyRecordVO>> page, Long goodsId);

    /**
     * 根据条件查询与商品未关联的商品资质
     * @param enterpriseId
     * @param goodsId
     * @param typeMust
     * @param status
     * @return
     */
    List<GoodsQualificationConfigVO> getQualificationNotRelateGoods(Long enterpriseId,Long goodsId,Integer typeMust,Integer status,Long checkTypeId);

    /**
     * 储存管理-拆零设置
     * 通过商品id查询拆零设置展示的基本信息
     * @param id 商品id
     * @return
     */
    CanSpitGoodVO getSplitGoodInfoById(Long id, Long enterpriseId);

    Page<List<GoodsVO>> getGoodsListByParamForSale(UserVO userVO, RequestGoodsVO goodsVO, Page<List<GoodsVO>> page);

    ResponseGoodsExcelWithSqlVO excelImportUpdate(HttpServletRequest request) throws Exception;

}
