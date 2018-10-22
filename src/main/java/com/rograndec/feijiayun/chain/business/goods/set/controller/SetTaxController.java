package com.rograndec.feijiayun.chain.business.goods.set.controller;

import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.goods.set.service.SetTaxService;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsTaxRateVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zeshi.sun on 2017/9/2.
 */

@Api(value = "goods_set", description = "商品设置-税率", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/goods/set/setTax")
public class SetTaxController {

    private static final Log logger = LogFactory.getLog(SetTaxController.class);

    @Autowired
    private SetTaxService setTaxService;

    @ApiOperation(value = "获取商品设置-税率", notes = "获取商品设置-税率 |开发者 杜东阳 | 已联调")
    @RequestMapping(value = "/getSetTax", method = RequestMethod.GET)
    public Result<List<GoodsTaxRateVO>> getSetTax(HttpSession session) throws Exception {
        Result<List<GoodsTaxRateVO>> result = new Result<>();
        UserVO userVO = (UserVO) session.getAttribute("user");
        Long enterpriseId = userVO.getEnterpriseId();
        result.setBizCodeSuccessInfo(SysCode.SUCCESS, setTaxService.getSetTaxVO(enterpriseId));
        return result;
    }

    @ApiOperation(value = "新增商品-税率", notes = "新增商品-税率 | 开发者 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/insertSetTax", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> insertSetTax(HttpServletRequest request,
                                       @ApiParam(value = "新增商品-税率", required = true) @RequestBody GoodsTaxRate goodsTaxRate) throws Exception {
        Result<String> result = new Result<>();
        HttpSession session = request.getSession(true);
        UserVO loginUser = (UserVO) session.getAttribute("user");
        result = setTaxService.insertSetTax(loginUser, goodsTaxRate);
        return result;
    }

//    @ApiOperation(value = "修改商品-税率", notes = "修改商品-税率 | 开发者 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
//    @RequestMapping(value = "/updateSetTax", method = RequestMethod.POST)
//    @ResponseBody
//    public Result<String> updateSetTax(HttpServletRequest request,
//                                       @ApiParam(value = "需要修改的商品-税率", required = true) @RequestBody GoodsTaxRate goodsTaxRate) throws Exception {
//        Result<String> result = new Result<>();
//        HttpSession session = request.getSession(true);
//        UserVO loginUser = (UserVO) session.getAttribute("user");
//        setTaxService.updateSetTax(loginUser, goodsTaxRate);
//        result.setBizCodeSuccessInfo(SysCode.SUCCESS, "修改商品-税率成功");
//        return result;
//    }

//	@ApiOperation(value = "删除商品-税率", notes = "删除商品-税率 | 开发者 杜东阳 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
//	@RequestMapping(value = "/deleteSetTax", method = RequestMethod.GET)
//	@ResponseBody
//	public Result<String> deleteSetTax(HttpServletRequest request,
//			@ApiParam(value = "需要删除的商品-税率ID", required = true) @RequestParam Long id) throws Exception {
//		Result<String> result = new Result<>();
//			HttpSession session = request.getSession(true);
//			UserVO userVO = (UserVO) session.getAttribute("user");
//			Long enterpriseId = userVO.getEnterpriseId();
//			if (setTaxService.canDelete(id)) {
//				setTaxService.deleteSetTax(id);
//				result.setBizCodeSuccessInfo(SysCode.SUCCESS, "删除商品-税率成功");
//			} else {
//				result.setBizCodeFallInfo(SysCode.FAIL.getCode(), "当前商品-税率存在关联数据,不能直接删除!若要删除,请先删除当前商品税率有关联的所有数据~");
//			}
//		return result;
//	}

}
