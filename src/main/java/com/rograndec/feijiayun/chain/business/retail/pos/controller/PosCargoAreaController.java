package com.rograndec.feijiayun.chain.business.retail.pos.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rograndec.feijiayun.chain.business.basic.warehouse.service.WarehouseService;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.PosWarehouseCargoAreaVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

 /**
 * 
 * @ClassName: PosBankController   
 * @Description:  零售管理-POS管理-柜组-Rest接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:34:42
 */
@Api(value="PosCargoAreaController",description = "零售管理-POS管理-柜组")
@RestController
@RequestMapping("/retail/pos/bank")
public class PosCargoAreaController {

	private static final Logger logger = LoggerFactory.getLogger(PosCargoAreaController.class);
	
	@Autowired
    private WarehouseService warehouseService;
	
	@ApiOperation(value = "查看柜组", notes = "查看数据 | 开发者 yuting.li | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@RequestMapping(value = "/getCargoAreaData", method = RequestMethod.GET)
	public Result<List<PosWarehouseCargoAreaVO>> getData(HttpSession session, @ApiParam(value = "企业id", required = true)@RequestParam(name = "id", required = false) Long id) throws Exception {
		Result<List<PosWarehouseCargoAreaVO>> result = new Result<>();
        try {
        	// 当前登录用户数据
            UserVO userVO = (UserVO) session.getAttribute("user");
            //如果是总部则需要传id
            if(userVO.getChainType().equals(ChainType.Headquarters.getCode())) {
            	userVO.setEnterpriseId(id);
            }
            List<PosWarehouseCargoAreaVO> list = warehouseService.findByEnterpriseIdPosgz(userVO.getEnterpriseId());
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        } catch (Exception e) {
            logger.error("查看柜组数据失败:", e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
	}
	
}
