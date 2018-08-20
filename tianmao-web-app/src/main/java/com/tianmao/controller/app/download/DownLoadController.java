package com.tianmao.controller.app.download;

import com.tianmao.app.config.AppContext;
import com.tianmao.app.util.FileDownloadUtils;
import com.tianmao.app.util.MyFileInfo;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class DownLoadController {


    /**
     * 导出录音
     */
    @RequestMapping(value = "/download")
    public ResponseEntity<byte[]> download() {
        List<MyFileInfo> fileList = new ArrayList<>();
        fileList.add(MyFileInfo.builder()
                .fileName("test.wav")
                .filePath(AppContext.storagePrefix + "/upload/voice/test.wav")
                .build());
        if(fileList.size() == 1) {
            return FileDownloadUtils.download("sdfsdf.jpg","F:/storage/robot/upload/u=4108197885,1524416658&fm=27&gp=0.jpg");
        }
        return FileDownloadUtils.multiFileDownload("test.zip", fileList);
    }
}
