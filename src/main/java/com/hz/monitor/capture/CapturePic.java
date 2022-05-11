package com.hz.monitor.capture;

import com.alibaba.fastjson.JSON;
import com.hz.monitor.api.AbstractAPI;
import com.hz.monitor.api.RequestInfo;
import com.hz.monitor.api.ServerConstant;
import com.hz.monitor.api.http.HttpPostMethod;
import com.hz.utils.monitor.BasicResponse;
import com.hz.utils.monitor.CapturePicture;
import com.hz.utils.monitor.HttpUtil;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.Map;

/**
 * 抓拍
 */
public class CapturePic extends AbstractAPI {
    private String deviceSerial;
    private int channelNo;
    private HttpPostMethod httpPostMethod;

    public CapturePic(String accessToken, String deviceSerial) {
        this.url = ServerConstant.CAPTURE_PICTURE;
        this.accessToken = accessToken;
        this.deviceSerial = deviceSerial;
        this.channelNo = 1;
        this.method = RequestInfo.Method.POST;
        this.host = ServerConstant.HOST;
        this.contentType = "application/x-www-form-urlencoded";

        //设置http的head
        HttpUtil httpUtil = new HttpUtil();
        Map<String,Object> headMap = httpUtil.setHeadMap(host,contentType);
        httpPostMethod = new HttpPostMethod(method);
        httpPostMethod.setHeader(headMap);

        //设置http的
        Map<String,Object> bodyMap = httpUtil.setBodyMap(accessToken,deviceSerial,null);
        if (channelNo == 1) {
            bodyMap.put("channelNo",1);
        }
        httpPostMethod.setCompleteUrl(url,bodyMap);
    }

    public BasicResponse<CapturePicture> executeApi() {
        BasicResponse response = null;
        HttpResponse httpResponse = httpPostMethod.execute();

        try {
            response = JSON.parseObject(httpResponse.getEntity().getContent(), BasicResponse.class);
            response.setJson(JSON.toJSONString(response));
            Object data = JSON.parseObject(JSON.toJSONString(response.getDataInternal()), CapturePicture.class);
            response.setData(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            httpPostMethod.httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
