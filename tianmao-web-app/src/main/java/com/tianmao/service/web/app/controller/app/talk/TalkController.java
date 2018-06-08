package com.tianmao.service.web.app.controller.app.talk;

import com.tianmao.service.common.HttpCode;
import com.tianmao.service.web.app.util.Rest;
import com.tianmao.service.type.user.MessageTemplateType;
import com.tianmao.service.web.app.config.AppContext;
import com.tianmao.service.web.app.controller.app.common.MessageComponent;
import com.tianmao.service.web.app.util.upload.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("talk")
public class TalkController {

    @Autowired
    private MessageComponent messageComponent;
    /**
     * 微说上传图片
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadImg")
    public Rest uploadImg(MultipartFile file) {
        Rest.Builder rest = Rest.newBuilder();
        if (file == null) {
            return rest.code(HttpCode.MISSING_PARAMETERS).build();
        }
        String filepath = FileUploadUtil.upload(file, AppContext.imgurl);
        rest.put("upload", AppContext.imageUrl+filepath);

        //极光推送
        messageComponent.senJPushUtil(MessageTemplateType.FEEDBACK,2L,0L,"很好!极光推送");

        return rest.code(HttpCode.OK).build();
    }

    /**
     * 微说上传视频
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadVideo")
    public Rest uploadVideo(MultipartFile file) {
        Rest.Builder rest = Rest.newBuilder();
        if (file == null) {
            return rest.code(HttpCode.MISSING_PARAMETERS).build();
        }
        String filepath = FileUploadUtil.upload(file, AppContext.videourl);
        rest.put("upload", filepath);
        return rest.code(HttpCode.OK).build();
    }

    /**
     * H5页面上传Base64格式文件
     */
    @RequestMapping(value = "/baseUploadImg")
    public Rest baseUploadImg(String file, String extension) {
        Rest.Builder rest = Rest.newBuilder();
        if (StringUtils.isEmpty(file)) {
            return rest.code(HttpCode.MISSING_PARAMETERS).message("文件不能空").build();
        }
        if (extension == null) {
            return rest.code(HttpCode.MISSING_PARAMETERS).message("后缀不能空").build();
        }
        String filepath = FileUploadUtil.upload(file, AppContext.imgurl, extension);
        rest.put("upload", AppContext.imageUrl+filepath);
        return rest.code(HttpCode.OK).build();
    }



}
