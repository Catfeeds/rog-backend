package com.rograndec.feijiayun.chain.business.storage.move.controller;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseCargoArea;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.basic.warehouse.service.WarehouseService;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseVO;
import com.rograndec.feijiayun.chain.business.storage.move.constant.ShelfMoveCondition;
import com.rograndec.feijiayun.chain.business.storage.move.service.ShelfMoveService;
import com.rograndec.feijiayun.chain.business.storage.move.vo.ShelfMoveDetailVO;
import com.rograndec.feijiayun.chain.business.storage.move.vo.ShelfMoveFormVO;
import com.rograndec.feijiayun.chain.business.storage.move.vo.ShelfMovePageVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.common.NewSelectBean;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.UserComponent;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.ShelfStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import io.swagger.annotations.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import java.util.Date;
import java.util.List;

/**
 * 
 * @author sulei
 *
 */
@Api(value = "storage_move_shelfMove",description = "储存管理-货物移动-货位移动")
@RestController
@RequestMapping("storage/move/shelfMove")
@Validated
public class ShelfMoveController {

    private static final Log logger = LogFactory.getLog(ShelfMoveController.class);

    @Autowired
    private ShelfMoveService shelfMoveService;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private UserComponent userComponent;


    @ApiOperation(value="按条件搜索货位移动信息", notes = "按条件搜索货位移动信息 | 开发者 苏磊 | 已联调", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "/getShelfMovePage", method = RequestMethod.GET)
    public Result<Page<List<ShelfMovePageVO>>> getShelfMovePage(HttpServletRequest request,
                                                                 @ApiParam(value = "页码", required = true) @RequestParam int pageNo,
                                                                 @ApiParam(value = "每页显示的记录数", required = true) @RequestParam int pageSize,
                                                                 @ApiParam(value = "起始日期", required = false) @RequestParam(required=false) Date startTime,
                                                                 @ApiParam(value = "终止日期", required = false) @RequestParam(required=false) Date endTime,
                                                                 @ApiParam(value = "移动单号", required = false) @RequestParam(required=false) String code,
                                                                 @ApiParam(value = "移动人员", required = false) @RequestParam(required=false) String moveManName,
                                                                 @ApiParam(value = "接收人员", required = false) @RequestParam(required=false) String receiverName,
                                                                 @ApiParam(value = "按某一列排序(moveDate或者code)", required = false) @RequestParam(required=false) String order,
                                                                 @ApiParam(value = "排序方式（升序：asc,降序：desc）", required = false) @RequestParam(required=false) String sort){
        Result<Page<List<ShelfMovePageVO>>> result = new Result<>();
        try{
            if(pageNo <= 0 || pageSize <= 0){
                result.setBizCodeFallInfo(SysCode.SYS_PARAM_ERROR);
                return result;
            }
            Page page = new Page(pageNo, pageSize);
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            page = shelfMoveService.getShelfMovePage(page,startTime,endTime,code,moveManName,receiverName,order,sort,loginUser.getEnterpriseId());
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, page);
        }catch(Exception e){
            logger.error("按条件搜索货位移动信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	@ApiOperation(value = "查看按钮--查看货位移动详细信息", notes = "查看按钮--查看货位移动详细信息 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/getMoveShelfDetail", method=RequestMethod.GET)
    public Result<ShelfMoveFormVO> getMoveShelfDetail(HttpServletRequest request,
                                                      @ApiParam(value = "当前需要查看的ID", required = true) @RequestParam Long id){
        Result<ShelfMoveFormVO> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            ShelfMoveFormVO shelfMoveFormVO = shelfMoveService.getMoveShelfDetail(loginUser.getEnterpriseId(),id);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,shelfMoveFormVO);
        }catch(Exception e){
            logger.error("查看按钮--查看货位移动详细信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	@ApiOperation(value = "新增货位移动信息", notes = "新增货位移动信息 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/insertShelfMove", method=RequestMethod.POST)
    @ApiImplicitParam(name = "shelfMoveFormVO", value = "需要保存的货位移动实体", required = true, dataType = "ShelfMoveFormVO")
    public Result<String> insertShelfMove(HttpServletRequest request,@RequestBody ShelfMoveFormVO shelfMoveFormVO){
        Result<String> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            shelfMoveService.insertShelfMove(loginUser,shelfMoveFormVO);
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,"添加成功");
        }catch(BusinessException e){
            logger.error("新增货位移动信息信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL.getCode(),e.getMessage());
            return result;
        }catch(Exception e){
            logger.error("新增货位移动信息信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "新增时的移动人员是当前登录人需要我传给前台", notes = "新增时的移动人员是当前登录人需要我传给前台--新增时我就不需要这个ID | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/selectRemoveMan", method=RequestMethod.POST)
    public Result<NewSelectBean> selectRemoveMan(HttpServletRequest request){
        Result<NewSelectBean> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            NewSelectBean n = new NewSelectBean();
            n.setId(loginUser.getUserId());
            n.setText(loginUser.getUserName());
            result.setBizCodeSuccessInfo(SysCode.SUCCESS,n);
        }catch(Exception e){
            logger.error("查询新增时的移动人员错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	@ApiOperation(value = "查询新增/下架商品/锁定商品/过期商品商品列表", notes = "查询新增/下架商品/锁定商品/过期商品商品列表 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/getGoodsList", method=RequestMethod.GET)
    public Result<List<ShelfMoveDetailVO>> getGoodsList(HttpServletRequest request,
                                                     @ApiParam(value = "当前情况下的商品列别--新增时传add---下架商品传down---锁定商品传lock---过期商品传over", required = true) @RequestParam String operation,
                                                        @ApiParam(name = "param", value = "检索条件:商品编码/条形码/检索码/名称/通用名称/批准文号", required = false) @RequestParam(required = false)String param){
        Result<List<ShelfMoveDetailVO>> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            if (ShelfMoveCondition.ADD.equals(operation) ||
                    ShelfMoveCondition.DOWN.equals(operation) ||
                    ShelfMoveCondition.LOCK.equals(operation) ||
                    ShelfMoveCondition.OVER.equals(operation)){
                /**
                 * 进行商品列表查询
                 */
                List<ShelfMoveDetailVO> list = shelfMoveService.getGoodsList(loginUser,operation,param);
                result.setBizCodeSuccessInfo(SysCode.SUCCESS,list);
            }else{
                result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS,"输入操作信息有误");
            }
        }catch(Exception e){
            logger.error("查询新增/下架商品/锁定商品/过期商品商品列表信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "仓库->库区->货区->货位--树形展示", notes = "仓库-》库区-》货区-》货位--树形展示（如果子级没有数据，则父级不显示，PS:仓库A下的库区A货区A,如果货区A下没有子集那么仓库A库区A货区A都“不显示”,） | 开发者 苏磊 | 已联调")
    @RequestMapping(value = "/getWarehouseExclusiveNullTreeByParam/{type}/{status}", method = RequestMethod.GET)
    @ApiImplicitParams({@ApiImplicitParam(name = "type", value = "type 0=仓库；1=库区；2=货区；3=货位； 根据type展示以type为最高级的级联树(此接口已修改不论这两个参数是否填入--都会查找出属于存储区域的货区所关联的仓库》库区》货区》货位展示并且是启用的)", required = true, paramType = "path"),
            @ApiImplicitParam(name = "status", value = "查询状态（0/禁用；1/启用；2/全部）", required = true, paramType = "path")})
    public Result<List<TreePOJO<WarehouseVO>>> getWarehouseExclusiveNullTreeByParam(HttpSession session, @PathVariable Integer type, @PathVariable Integer status) {
        Result<List<TreePOJO<WarehouseVO>>> result = new Result<>();
        try {
            com.rograndec.feijiayun.chain.common.vo.UserVO userVO = (com.rograndec.feijiayun.chain.common.vo.UserVO) session.getAttribute("user");
            //List<TreePOJO<WarehouseVO>> list = warehouseService.getWarehouseExclusiveNullTreeByParam(type, userVO.getEnterpriseId(), userVO.getParentId(), status == 2 ? null : status);
            //获得存储区域中的合格或者不合格货位--此方法为了应对前端的树形展示--摘用原货位树形展示方法再进行遍历
            List<TreePOJO<WarehouseVO>> list = warehouseService.getWarehouseTreeByEnterpriseIdForDouble(userVO.getEnterpriseId(), EnableStatus.ENABLE.getStatus());
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        } catch (Exception e) {
            logger.error("仓库->库区->货区->货位--树形展示:" + e.getMessage(), e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }
	
	@ApiOperation(value = "接收人员下拉框", notes = "查询接收人员下拉框 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/getRecivePersonList", method=RequestMethod.GET)
    public Result<List<NewSelectBean>> getRecivePersonList(HttpServletRequest request){
        Result<List<NewSelectBean>> result = new Result<>();
        try{
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            List<User> userList = userComponent.findUserByEnterpriseId(loginUser.getEnterpriseId());
            List<NewSelectBean> list = new ArrayList<>();
            for (User s : userList){
                NewSelectBean bean = new NewSelectBean();
                bean.setId(s.getId());
                bean.setText(String.valueOf(s.getName()));
                list.add(bean);
            }
            result.setBizCodeSuccessInfo(SysCode.SUCCESS, list);
        }catch(Exception e){
            logger.error("接收人员下拉框信息错误:"+e.getMessage(),e);
            result.setBizCodeFallInfo(SysCode.FAIL);
            return result;
        }
        return result;
    }

    @ApiOperation(value = "货位移动信息导出", notes = "货位移动信息导出 | 开发者 苏磊 | 已联调")
    @RequestMapping(value="/exportShelfMove/{id}",method= RequestMethod.GET)
    @ApiImplicitParam(name = "id", value = "当前需要查看的货位移动信息", required = true, dataType = "Long", paramType="path")
    public void exportMemberSuccessAndFail(HttpServletRequest request,
                                           HttpServletResponse response,
                                           @PathVariable Long id) throws Exception {

        try {
            OutputStream output = null;
            output = response.getOutputStream();
            //这段代码一定要放在流关闭之前，否会下载的文件会变成zip格式
            String name = "货位移动";
            response.setContentType("application/octet-stream;charset=utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8")+".xlsx");
            HttpSession session = request.getSession(true);
            UserVO loginUser = (UserVO) session.getAttribute("user");
            ShelfMoveFormVO shelfMoveFormVO = shelfMoveService.getMoveShelfDetail(loginUser.getEnterpriseId(),id);
            shelfMoveService.export(output,shelfMoveFormVO,loginUser);
        }catch(Exception e){
            logger.error("导出货位移动信息错误:"+e.getMessage(),e);
        }
    }
	
}
