package com.tianmao.controller.app.weixin.downloadcode;

import lombok.Data;

@Data
public class Scene {
    private String scene_str;

    public Scene(String scene_str) {
        this.scene_str = scene_str;
    }
}
