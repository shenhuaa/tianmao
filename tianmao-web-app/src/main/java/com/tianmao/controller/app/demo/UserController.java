package com.tianmao.controller.app.demo;

import com.tianmao.api.ApiMallGroup;
import com.tianmao.api.app.UserClient;
import com.tianmao.app.util.Rest;
import com.tianmao.service.model.user.User;
import com.tianmao.utils.HttpCode;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;


@ApiMallGroup
@Api(tags = "用户", description = "UserController")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserClient userClient;


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
    @RequestMapping("/down")
    public void commonList() throws Exception {

    }
    public static void  down111() {
        String path = "http://localhost/upload/u=4108197885,1524416658&fm=27&gp=0.jpg";
        URL url = null;
        //从网络上下载一张图片
        InputStream inputStream = null;
        OutputStream outputStream = null;
        //建立一个网络链接
        HttpURLConnection con = null;
        try {
            url = new URL(path);
            con = (HttpURLConnection) url.openConnection();
            inputStream = con.getInputStream();
            outputStream = new FileOutputStream(new File("E:\\"+new Date().getTime()+"b.jpg"));
            int n = -1;
            byte b [] = new byte[1024];
            while ((n = inputStream.read(b)) != -1) {
                outputStream.write(b, 0, n);
            }
            outputStream.flush();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
