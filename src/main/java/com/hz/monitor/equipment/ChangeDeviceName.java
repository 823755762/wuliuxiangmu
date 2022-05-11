package com.hz.monitor.equipment;

import com.alibaba.fastjson.JSON;
import com.hz.monitor.api.AbstractAPI;
import com.hz.monitor.api.RequestInfo;
import com.hz.monitor.api.http.HttpPostMethod;
import com.hz.utils.monitor.BaseDeviceResponse;
import com.hz.utils.monitor.BasicResponse;
import com.hz.utils.monitor.HttpUtil;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.Map;

/**
 * 更改设备名称
 */
public class ChangeDeviceName extends AbstractAPI {
    private String deviceSerial;
    private String deviceName;//设备名称
    private HttpPostMethod httpPostMethod;

    public ChangeDeviceName(String url, String accessToken, String deviceSerial, String deviceName ) {
        this.url = url;
        this.accessToken = accessToken;
        this.deviceSerial = deviceSerial;
        this.deviceName = deviceName;
        this.method = RequestInfo.Method.POST;
        this.host = ServerConstant.HOST;
        this.contentType = "application/x-www-form-urlencoded";

        //设置http的head
        HttpUtil httpUtil = new HttpUtil();
        Map<String,Object> headMap = httpUtil.setHeadMap(host,contentType);
        httpPostMethod = new HttpPostMethod(method);
        httpPostMethod.setHeader(headMap);

        //设置http的body
        Map<String,Object> bodyMap = httpUtil.setBodyMap(accessToken,deviceSerial,null);
        if(deviceName != null) {
            bodyMap.put("deviceName",deviceName);
        }
        httpPostMethod.setCompleteUrl(url,bodyMap);
    }

    public BasicResponse<BaseDeviceResponse> executeApi() {
        BasicResponse response = null;
        HttpResponse httpResponse = httpPostMethod.execute();
        try {
            response = JSON.parseObject(httpResponse.getEntity().getContent(),BasicResponse.class);
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
