package com.tianmao.web.app.controller.app.user;

import com.github.pagehelper.PageInfo;
import com.tianmao.common.HttpCode;
import com.tianmao.web.app.api.ApiMallGroup;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tianmao.model.user.User;
import com.tianmao.service.app.UserService;
import com.tianmao.web.app.util.Rest;


@ApiMallGroup
@Api(tags = "用户", description = "UserController")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

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
        PageInfo<User> userPage =  userService.selectList();
        rest.put("list",userPage.getList());
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
        PageInfo<User> userPage =  userService.selectList();
        rest.put("list",userPage.getList());
        return rest.code(HttpCode.OK).build();
    }



}
