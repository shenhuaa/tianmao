package com.tianmao.app.controller.app.user;

import com.tianmao.api.app.UserClient;
import com.tianmao.api.quartz.QuartzTaskClient;
import com.tianmao.utils.HttpCode;
import com.tianmao.service.constant.ModuleConstant;
import com.tianmao.service.type.quartz.TaskStatus;
import com.tianmao.app.api.ApiMallGroup;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tianmao.service.model.user.User;
import com.tianmao.util.Rest;

import java.util.List;


@ApiMallGroup
@Api(tags = "用户", description = "UserController")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserClient userClient;

    @Autowired
    private QuartzTaskClient quartzTaskClient;

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
        List<User> list =  userClient.selectList();
        rest.put("list",list);
        return rest.code(HttpCode.OK).build();
    }

    @ApiOperation(value = "测试抛用户异常", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "timestamp", value = "时间戳", dataType = "string"),
            @ApiImplicitParam(name = "sign", value = "签名", dataType = "string"),
            @ApiImplicitParam(name = "token", value = "用户token", dataType = "string")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功")
    })
    @RequestMapping("/com/tianmao/exception")
    public Rest commonList() {
        Rest.Builder rest = Rest.newBuilder();
        userClient.getException();
        return rest.code(HttpCode.OK).build();
    }


    /**
     * 获取定时器数据
     * @return
     */
    @RequestMapping("/com/tianmao/common/quartz")
    public Rest quartz() {
        Rest.Builder rest = Rest.newBuilder();
        int i  = quartzTaskClient.totalByModule(ModuleConstant.WEB_APP, TaskStatus.RUNNING);
        rest.put("int",i);
        return rest.code(HttpCode.OK).build();
    }






}
