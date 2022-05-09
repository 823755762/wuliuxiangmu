package com.hz.utils.monitor;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 抓拍
 */
public class CapturePicture {

    @JSONField(name="picUrl")
    public String picUrl;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
