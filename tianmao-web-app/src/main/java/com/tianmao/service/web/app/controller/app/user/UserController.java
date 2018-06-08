package com.tianmao.service.web.app.controller.app.user;

import com.github.pagehelper.PageInfo;
import com.tianmao.service.common.HttpCode;
import com.tianmao.service.constant.ModuleConstant;
import com.tianmao.service.quartz.QuartzTaskService;
import com.tianmao.service.type.quartz.TaskStatus;
import com.tianmao.service.web.app.util.Rest;
import com.tianmao.service.web.app.api.ApiMallGroup;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tianmao.service.model.user.User;
import com.tianmao.service.app.UserService;

import java.util.List;


@ApiMallGroup
@Api(tags = "用户", description = "UserController")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private QuartzTaskService quartzTaskService;

    @ApiOperation(value = "用户集合", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "timestamp", value = "时间戳", dataType = "string"),
            @ApiImplicitParam(name = "sign", value = "签名", dataType = "string"),
            @ApiImplicitParam(name = "token", value = "用户token", dataType = "string"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功")
    })
    @RequestMapping("/list")
    public Rest userList() {
        Rest.Builder rest = Rest.newBuilder();
        List<User> list =  userService.selectList();
        rest.put("list",list);
        return rest.code(HttpCode.OK).build();
    }

    @ApiOperation(value = "用户公共的集合", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "timestamp", value = "时间戳", dataType = "string"),
            @ApiImplicitParam(name = "sign", value = "签名", dataType = "string"),
            @ApiImplicitParam(name = "token", value = "用户token", dataType = "string")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功")
    })
    @RequestMapping("/common")
    public Rest commonList() {
        Rest.Builder rest = Rest.newBuilder();
        List<User> list = userService.selectList();
        rest.put("list",list);
        return rest.code(HttpCode.OK).build();
    }


    @RequestMapping("/common/quartz")
    public Rest quartz() {
        Rest.Builder rest = Rest.newBuilder();
        int i  = quartzTaskService.totalByModule(ModuleConstant.WEB_APP, TaskStatus.RUNNING);
        rest.put("int",i);
        return rest.code(HttpCode.OK).build();
    }






}
