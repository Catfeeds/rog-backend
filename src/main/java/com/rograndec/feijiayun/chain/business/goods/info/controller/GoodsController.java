package com.rograndec.feijiayun.chain.business.goods.info.controller;

import com.rograndec.feijiayun.chain.business.basic.user.entity.SimpleUserVO;
import com.rograndec.feijiayun.chain.business.goods.info.service.GoodsService;
import com.rograndec.feijiayun.chain.business.goods.info.valid.AddGoodsValid;
import com.rograndec.feijiayun.chain.business.goods.info.vo.*;
import com.rograndec.feijiayun.chain.business.goods.set.service.SetDosageService;
import com.rograndec.feijiayun.chain.business.goods.set.service.SetUnitService;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsDosageVO;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsUnitVO;
import com.rograndec.feijiayun.chain.business.system.set.service.ScopeQualificationService;
import com.rograndec.feijiayun.chain.business.system.set.vo.BusinessScopeVO;
import com.rograndec.feijiayun.chain.business.system.set.vo.GoodsQualificationVO;
import com.rograndec.feijiayun.chain.business.system.set.vo.QualitySetVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.GoodsComponent;
import com.rograndec.feijiayun.chain.common.component.UserComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.QualitySetType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ST on 2017/8/21.
 */
@Api(value = "goods_info",description = "商品信息接口服务")
@RestController
@RequestMapping("goods/info")
@Validated
public class GoodsController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ScopeQualificationService scopeQualificationService;

    @Autowired
    private GoodsComponent goodsComponent;
    @Autowired
    private UserComponent userComponent;

    @Autowired
    private SetUnitService setUnitService;

    @Autowired
    private SetDosageService setDosageService;


    @ApiOperation(value = "简单搜索商品", notes = "根据编码/条形码/检索码搜索商品 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getGoodsByParam/{param}/{pageNo}/{pageSize}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "param", value = "检索条件", required = true, dataType = "String",paramType = "path")
    public Result<Page> getGoodsByParam(HttpSession session,@PathVariable String param,@ApiParam(value = "页码", required = true) @PathVariable int pageNo,
                                        @ApiParam(value = "每页显示的记录数", required = true) @PathVariable int pageSize){
        Result<Page> result = new Result<>();
        if(pageNo < 0 || pageSize <= 0){
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }

        Page page = new Page(pageNo, pageSize);
        UserVO userVO = (UserVO) session.getAttribute("user");
        RequestGoodsVO requestGoodsVO = new RequestGoodsVO();
        requestGoodsVO.setPageNo(pageNo);
        requestGoodsVO.setPageSize(pageSize);
        requestGoodsVO.setParam(param);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsService.getGoodsListByParam(userVO,requestGoodsVO,page));
        return result;
    }

    @ApiOperation(value = "简单+高级商品信息分页查询", notes = "简单+高级商品信息分页查询 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getGoodsList",method= RequestMethod.POST)
    @ApiImplicitParam(name = "requestGoodsVO", value = "检索条件", required = true, dataType = "RequestGoodsVO")
    public Result<Page<List<GoodsVO>>> getGoodsList(HttpSession session,@RequestBody RequestGoodsVO requestGoodsVO){
        Result<Page<List<GoodsVO>>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        Integer pageNo = requestGoodsVO.getPageNo();
        Integer pageSize = requestGoodsVO.getPageSize();
        if(pageNo <= 0 || pageSize <= 0){
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        Page<List<GoodsVO>> page = new Page(pageNo, pageSize);
        if(requestGoodsVO.getType() == 0){//商品信息中搜索商品
            goodsService.getGoodsListByParam(userVO,requestGoodsVO,page);
        } else if(requestGoodsVO.getType() == 1){////销售管理中搜索商品
            goodsService.getGoodsListByParamForSale(userVO,requestGoodsVO,page);
        }
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,page);
        return result;
    }






    @ApiOperation(value = "商品信息添加", notes = "商品信息添加 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/addGoods",method= RequestMethod.POST)
    @ApiImplicitParam(name = "goodsVO", value = "商品对象", required = true, dataType = "GoodsVO")
    public Result addGoods(HttpSession session,@AddGoodsValid @RequestBody GoodsVO goodsVO) throws Exception {
        Result result = new Result();
        UserVO userVO = (UserVO) session.getAttribute("user");
        goodsService.addGoods(userVO,goodsVO, false);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }




    @ApiOperation(value = "修改商品", notes = "修改商品 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/updateGoods",method= RequestMethod.POST)
    @ApiImplicitParam(name = "goodsVO", value = "需要修改的商品信息", required = true,dataType = "GoodsVO")
    public Result updateGoods(HttpSession session,@AddGoodsValid @RequestBody GoodsVO goodsVO) throws Exception {
        Result result = new Result();
        UserVO userVO = (UserVO) session.getAttribute("user");
        goodsService.updateGoods(userVO,goodsVO);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS);
        return result;
    }


    @ApiOperation(value = "根据品种类别查询经营范围", notes = "根据品种分类查询经营范围 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getBusinessScope/{businessVariety}/{status}",method= RequestMethod.GET)
    @ApiImplicitParams ({
            @ApiImplicitParam(name = "businessVariety", value = "品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）", required = true,paramType = "path"),
            @ApiImplicitParam(name = "status", value = "状态（0-禁用；1-启用；2-全部）", required = true,paramType = "path")
    })
    public Result<List<BusinessScopeVO>> getBusinessScope(HttpSession session,@PathVariable Integer businessVariety,@PathVariable Integer status){
        Result<List<BusinessScopeVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsService.getBusinessScopeVOList(businessVariety,status == 2 ? null : status,ChainType.getHeadEnterpriseId(userVO)));
        return result;
    }

    @ApiOperation(value = "根据品种类别查询验收分类", notes = "根据品种类别查询验收分类 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getCheckType/{businessVariety}/{status}",method= RequestMethod.GET)
    @ApiImplicitParams ({
            @ApiImplicitParam(name = "businessVariety", value = "品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）", required = true,paramType = "path"),
            @ApiImplicitParam(name = "status", value = "状态（0-禁用；1-启用；2-全部）", required = true,paramType = "path")
    })
    public Result<List<QualitySetVO>> getCheckType(HttpSession session,@PathVariable Integer businessVariety,@PathVariable Integer status ){
        Result<List<QualitySetVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsService.getQualitySetVOList(ChainType.getHeadEnterpriseId(userVO), QualitySetType.CHECK_TYPE.getSetType(),businessVariety,status == 2 ? null : status));
        return result;
    }

    @ApiOperation(value = "查询申请人信息", notes = "查询申请人信息 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getApplicantUserInfo",method= RequestMethod.GET)
    public Result<List<SimpleUserVO>> getApplicantUserInfo(HttpSession session){
        Result<List<SimpleUserVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,userComponent.getSimpleUserVOByEnterpriseId(userVO.getEnterpriseId(),1));
        return result;
    }

    @ApiOperation(value = "查询养护标准", notes = "查询养护标准(查询养护和检查/仅养护两种类别的养护标准) | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getConserve",method= RequestMethod.GET)
    public Result<List<QualitySetVO>> getConserve(HttpSession session ){
        Result<List<QualitySetVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsService.getQualitySetVOList(ChainType.getHeadEnterpriseId(userVO), QualitySetType.MAINTANCE_MEASURES.getSetType(),null,EnableStatus.ENABLE.getStatus()));
        return result;
    }

    @ApiOperation(value = "查询当前企业关联的生产厂商", notes = "查询当前企业关联的生产厂商 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getManufacturerList",method= RequestMethod.GET)
    public Result<List<ResponseManufacturerVO>> getManufacturerList(HttpSession session){
        Result<List<ResponseManufacturerVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsService.getManufacturer(userVO.getEnterpriseId()));
        return result;
    }


//资质类型是否必选（0-可选；1-必选）
    @ApiOperation(value = "商品信息增加/商品时获取商品资质信息", notes = "增加/商品信息中获取商品资质信息 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getGoodsQualificationInfo",method= RequestMethod.POST)
    public Result<List<GoodsQualificationVO>> getGoodsQualificationInfo2Add(HttpSession session,@RequestBody RequestQualificationVO requestQualificationVO){
        UserVO userVO = (UserVO) session.getAttribute("user");
        Result<List<GoodsQualificationVO>> result = new Result();
        Long checkTypeId = requestQualificationVO.getCheckTypeId();
        int type = requestQualificationVO.getType();
        Long goodsId = requestQualificationVO.getGoodsId();
        if(goodsId == null){
            List<GoodsQualificationVO> list = scopeQualificationService.getGoodQuaInfoVO(ChainType.getHeadEnterpriseId(userVO),checkTypeId,type, EnableStatus.ENABLE.getStatus(),null);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,list);
        } else {
            List<GoodsQualificationVO> fullQualificationList = scopeQualificationService.getGoodQuaInfoVO(ChainType.getHeadEnterpriseId(userVO),checkTypeId, 0, EnableStatus.ENABLE.getStatus(),null);
            //根据商品id 查询该商品已绑定的商品资质
            List<GoodsQualificationConfigVO> configVOList = goodsService.getQualificationConfigList(goodsId);
            List<Long> configIdList = new ArrayList<>();
            if(configVOList != null){
                configVOList.forEach(item->{
                    configIdList.add(item.getQualificationId());
                });
            }
            List<GoodsQualificationVO> qualificationVOList = new ArrayList<>();
            Iterator<GoodsQualificationVO> iterator = fullQualificationList.iterator();
            while (iterator.hasNext()){
                GoodsQualificationVO vo = iterator.next();
                if(!configIdList.contains(vo.getId())){
                    qualificationVOList.add(vo);
                }
            }
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,qualificationVOList);
        }

        return result;
    }



    @ApiOperation(value = "商品Excel导入", notes = "商品Excel导入 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/importGoods",method= RequestMethod.POST)
    public Result<ResponseGoodsExcelVO> importGoods(HttpServletRequest request) {
        Result<ResponseGoodsExcelVO> result = new Result<>();
        ResponseGoodsExcelVO responseGoodsExcelVO = null;
        try {
            responseGoodsExcelVO = goodsService.excelImport(request,false);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("商品导入失败!");
        }
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, responseGoodsExcelVO);
        return result;
    }

    @ApiOperation(value = "导入成功数据", notes = "导入成功数据 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/importSuccessGoods/{key}",method= RequestMethod.POST)
    @ApiImplicitParam(name = "key", value = "获取数据的key值", required = true,paramType = "path")
    public Result importSuccessGoods(HttpSession session,@PathVariable("key")String key) throws Exception {
        Result result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//
//        executorService.submit(() -> {
//            try {
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                throw new BusinessException("商品导入失败");
//            }
//        });
        goodsService.importSuccessGoods(userVO,key);
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,"");
        return result;
    }
    @ApiOperation(value = "商品导出", notes = "商品导出  | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/exportGoods",method= RequestMethod.POST,produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ApiImplicitParams({@ApiImplicitParam(name = "requestGoodsVO", value = "商品信息查询条件", required = true, dataType = "RequestGoodsVO")
    })
    public void exportGoods(HttpSession session,RequestGoodsVO requestGoodsVO, HttpServletRequest request,HttpServletResponse response) throws Exception {
        UserVO userVO = (UserVO) session.getAttribute("user");
        String name = "商品信息";
        name += ".xlsx";
        //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        //输出Excel文件
        OutputStream output = response.getOutputStream();
        goodsService.exportGoodsInfo(output,requestGoodsVO,userVO);
        output.close();
        output.flush();
    }




    @ApiOperation(value = "商品导出(导出成功数据/导出失败数据)", notes = "商品导出(导出成功数据/导出失败数据) | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/exportGoodsSuccessAndFail/{type}/{key}",method= RequestMethod.GET)
    @ApiImplicitParams ({
            @ApiImplicitParam(name = "type", value = "1--导出成功数据；2--导出失败数据", required = true,paramType = "path"),
            @ApiImplicitParam(name = "key", value = "获取数据的key值", required = true,paramType = "path")
    })
    public void exportGoodsSuccessAndFail(@PathVariable("type")Integer type,@PathVariable String key, HttpServletResponse response) throws Exception {
        type =  type == null ? 1 : type;
        String name = "成功的商品信息表单";
        if (type == 2) {
            name = "失败商品信息表单";
        }
        name += ".xlsx";
        //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
        //输出Excel文件
        OutputStream output = response.getOutputStream();
        goodsService.exportGoods(output,key,type);
        output.close();
        output.flush();
    }

    @ApiOperation(value = "查询商品的修改记录", notes = "查询商品的修改记录 | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getGoodsModifyRecord/{goodsId}/{pageNo}/{pageSize}",method= RequestMethod.GET)
//    @ApiImplicitParam(name = "param", value = "检索条件", required = true, dataType = "String",paramType = "path")
    public Result<Page<List<ResponseGoodsModifyRecordVO>>> getGoodsModifyRecord(HttpSession session,@ApiParam(value = "商品id", required = true) @PathVariable Long goodsId,@ApiParam(value = "页码", required = true) @PathVariable int pageNo,
                                        @ApiParam(value = "每页显示的记录数", required = true) @PathVariable int pageSize){
        Result<Page<List<ResponseGoodsModifyRecordVO>>> result = new Result<>();
        if(pageNo <= 0 || pageSize <= 0){
            result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
            return result;
        }
        Page<List<ResponseGoodsModifyRecordVO>> page = new Page<List<ResponseGoodsModifyRecordVO>>(pageNo, pageSize);
        UserVO userVO = (UserVO) session.getAttribute("user");
        result.setBizCodeSuccessInfo(SysCode.SUCCESS,goodsService.getGoodsModifyRecord(userVO.getEnterpriseId(),userVO.getParentId(),page,goodsId));
        return result;
    }

    @ApiOperation(value = "获取商品设置-单位", notes = "获取商品设置-单位 |开发者 杜东阳 | 已联调")
    @RequestMapping(value = "/getSetUnit", method = RequestMethod.GET)
    public Result<List<GoodsUnitVO>> getSetUnit(HttpSession session) {
        Result<List<GoodsUnitVO>> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            List<GoodsUnitVO> unitVOList = setUnitService.getSetUnit(ChainType.getHeadEnterpriseId(userVO),EnableStatus.ENABLE.getStatus());
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, unitVOList);
        } catch (Exception e) {
            logger.error("获取商品设置-单位:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "获取商品设置-剂型", notes = "获取商品设置-剂型 | 开发者 杜东阳 | 已联调")
    @RequestMapping(value = "/getSetDosage", method = RequestMethod.GET)
    public Result<List<GoodsDosageVO>> getSetDosage(HttpSession session) {
        Result<List<GoodsDosageVO>> result = new Result<>();
        try {
            UserVO userVO = (UserVO) session.getAttribute("user");
            List<GoodsDosageVO> dosageVOList = setDosageService.getSetDosage(ChainType.getHeadEnterpriseId(userVO), EnableStatus.ENABLE.getStatus());
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, dosageVOList);
        } catch (Exception e) {
            logger.error("获取商品设置-剂型:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "通过Excel导入获取更新sql", notes = "通过Excel导入获取更新sql | 开发者 孙腾 | 已联调")
    @RequestMapping(value="/getGoodsUpdateSql",method= RequestMethod.POST)
    public Result<ResponseGoodsExcelWithSqlVO> getGoodsUpdateSql(HttpServletRequest request) {
        Result<ResponseGoodsExcelWithSqlVO> result = new Result<>();
        ResponseGoodsExcelWithSqlVO responseGoodsExcelVO = null;
        try {
            responseGoodsExcelVO = goodsService.excelImportUpdate(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("商品导入失败!");
        }
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, responseGoodsExcelVO);
        return result;
    }




}
