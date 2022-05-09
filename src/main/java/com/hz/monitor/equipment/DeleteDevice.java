package com.hz.monitor.equipment;

import com.alibaba.fastjson.JSON;
import com.hz.monitor.api.AbstractAPI;
import com.hz.monitor.api.RequestInfo;
import com.hz.monitor.api.ServerConstant;
import com.hz.monitor.api.http.HttpPostMethod;
import com.hz.utils.monitor.BaseDeviceResponse;
import com.hz.utils.monitor.BasicResponse;
import com.hz.utils.monitor.HttpUtil;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.Map;

/**
 * 删除设备
 */
public class DeleteDevice extends AbstractAPI {

    //private String accessToken;
    private String deviceSerial;
    private HttpPostMethod httpPostMethod;

    public DeleteDevice(String accessToken, String deviceSerial) {
        this.url = ServerConstant.DELETE_DEVICE;
        this.accessToken = accessToken;
        this.deviceSerial = deviceSerial;
        this.method = RequestInfo.Method.POST;
        this.host = ServerConstant.HOST;
        this.contentType = "application/x-www-form-urlencoded";

        //设置http的header
        HttpUtil httpUtil = new HttpUtil();
        Map<String,Object> headMap = httpUtil.setHeadMap(host,contentType);
        httpPostMethod = new HttpPostMethod(method);
        httpPostMethod.setHeader(headMap);

        //设置http的body部分
        Map<String,Object> bodyMap = httpUtil.setBodyMap(accessToken,deviceSerial,null);

        //设置完整的url地址
        httpPostMethod.setCompleteUrl(url,bodyMap);
    }

    public BasicResponse<BaseDeviceResponse> executeApi() {
        BasicResponse response = null;
        HttpResponse httpResponse = httpPostMethod.execute();

        try {
            response = JSON.parseObject(httpResponse.getEntity().getContent(), BasicResponse.class);
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
