package com.rograndec.feijiayun.chain.business.basic.user.controller;

import com.rograndec.feijiayun.chain.business.basic.user.service.EducationMajorService;
import com.rograndec.feijiayun.chain.business.basic.user.vo.EducationMajorReturnVO;
import com.rograndec.feijiayun.chain.business.basic.user.vo.EducationMajorVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by zhaiwei on 2017/8/28.
 */
@Api(description = "学历,专业管理接口")
@RestController
@RequestMapping("basic/educationmajor")
@Slf4j
public class EducationMajorController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
    private EducationMajorService educationMajorService;
	
    @ApiOperation(value="新增学历或者专业 | 开发者 翟伟 | 已联调", notes = "新增学历或者专业 | 开发者 翟伟 | 已联调"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "新增类型,学历:education,专业:major"
                    , required = true, paramType="path"),
            @ApiImplicitParam(name = "educationMajorVO",dataType = "EducationMajorVO",value = "新增学历or专业信息实体"
                    , required = true, paramType="body")

    })
    @RequestMapping(value = "{type}/add", method = RequestMethod.POST)
    @ResponseBody
    public Result addEducationOrMajor(
            @PathVariable String type,
            @Valid @RequestBody EducationMajorVO educationMajorVO,@ApiIgnore UserVO userVO
    ){

        Result result = new Result();

        educationMajorService.saveEducationMajor(userVO,educationMajorVO,type);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, null);
        return result;
    }

    @ApiOperation(value="查询学历或者专业 | 开发者 翟伟 | 已联调", notes = "查询学历或者专业 | 开发者 翟伟 | 已联调"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "0-学历；1-专业"
                    , required = true, paramType="path"),

    })
    @RequestMapping(value = "{type}/list", method = RequestMethod.GET)
    @ResponseBody
    public Result< List<EducationMajorReturnVO> > queryEducationOrMajors(
            @PathVariable String type,@ApiIgnore UserVO userVO
    ){

        Result< List<EducationMajorReturnVO> > result = new Result<>();

        List<EducationMajorReturnVO> educationMajorReturnVOS = educationMajorService.queryEducationMajorByUser(userVO,type);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, educationMajorReturnVOS);
        return result;
    }


}
