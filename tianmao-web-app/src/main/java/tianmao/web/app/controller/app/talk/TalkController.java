package tianmao.web.app.controller.app.talk;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tianmao.common.HttpCode;
import tianmao.web.app.config.AppContext;
import tianmao.web.app.util.Rest;
import tianmao.web.app.util.upload.FileUploadUtil;

@RestController
@RequestMapping("talk")
public class TalkController {
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
        return rest.code(HttpCode.OK).build();
    }

}
