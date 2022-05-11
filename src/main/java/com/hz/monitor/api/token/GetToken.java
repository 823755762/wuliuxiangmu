package com.hz.monitor.api.token;


import com.alibaba.fastjson.JSON;
import com.hz.monitor.api.AbstractAPI;
import com.hz.monitor.api.RequestInfo;
import com.hz.monitor.api.ServerConstant;
import com.hz.monitor.api.http.HttpPostMethod;
import com.hz.utils.monitor.AccessToken;
import com.hz.utils.monitor.BasicResponse;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取token
 */
public class GetToken extends AbstractAPI {

    //private String url = "https://open.ys7.com/api/lapp/token/get";
    private String appKey;
    private String appSecret;
    private HttpPostMethod httpPostMethod;

    public GetToken(String appKey,String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.url = ServerConstant.GET_TOKEN;
        this.method = RequestInfo.Method.POST;
        this.host = ServerConstant.HOST;
        this.contentType = "application/x-www-form-urlencoded";
        //设置https报文的头部
        Map<String,Object> headMap = new HashMap<String,Object>();
        headMap.put("Host",host);
        headMap.put("Content-Type",contentType);
        httpPostMethod = new HttpPostMethod(method);
        httpPostMethod.setHeader(headMap);

        //设置https请求报文的body部分
        Map<String,Object> bodyMap = new HashMap<String,Object>();
        if (appKey != null) {
            bodyMap.put("appKey",appKey);
        }
        if (appSecret != null) {
            bodyMap.put("appSecret",appSecret);
        }
        httpPostMethod.setCompleteUrl(url,bodyMap);
    }

    public BasicResponse<AccessToken> executeApi() {
        BasicResponse response = null;
        HttpResponse httpResponse = httpPostMethod.execute();

        try {
            //中间进行了一次字段转换，不清楚具体实现的效果
            response = JSON.parseObject(httpResponse.getEntity().getContent(), BasicResponse.class);
            response.setJson(JSON.toJSONString(response));
            Object data = JSON.parseObject(JSON.toJSONString(response.getDataInternal()), AccessToken.class);
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
