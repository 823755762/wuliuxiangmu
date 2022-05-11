package com.hz.monitor.equipment;

import com.alibaba.fastjson.JSON;
import com.hz.monitor.api.AbstractAPI;
import com.hz.monitor.api.ServerConstant;
import com.hz.monitor.api.http.HttpPostMethod;
import com.hz.utils.monitor.BaseDeviceResponse;
import com.hz.utils.monitor.BasicResponse;
import com.hz.utils.monitor.HttpUtil;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.Map;

/**
 * 添加设备
 */
public class AddDevice extends AbstractAPI {
    //private String accessToken;//授权过程获取的access_token
    private String deviceSerial;//设备的序列号
    private String validateCode;//设备验证码，设备机身上的六位大写字母
    private HttpPostMethod httpMethod;//请求方式


    public AddDevice(String accessToken, String deviceSerial, String validateCode) {
        this.url = ServerConstant.ADD_DEVICE;
        this.accessToken = accessToken;
        this.deviceSerial = deviceSerial;
        this.validateCode = validateCode;

        HttpUtil httpUtil = new HttpUtil();
        Map<String,Object> headMap = httpUtil.setHeadMap(host,contentType);
        httpMethod = new HttpPostMethod(method);
        httpMethod.setHeader(headMap);

        Map<String,Object> bodyMap = httpUtil.setBodyMap(accessToken,deviceSerial,validateCode);
        httpMethod.setCompleteUrl(url,bodyMap);
    }

    public BasicResponse<BaseDeviceResponse> executeApi() {
        BasicResponse response = null;
        HttpResponse httpResponse = httpMethod.execute();

        try {
            response = JSON.parseObject(httpResponse.getEntity().getContent(),BasicResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            httpMethod.httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

}
