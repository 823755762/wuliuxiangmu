package com.hz.monitor.PTZ;

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
 * 停止云台
 */

public class StopPTZ extends AbstractAPI {

    private String deviceSerial;

    private int channelNo = 1;
    //操作命令：0-上，1-下，2-左，3-右，4-左上，5-左下，6-右上，7-右下，8-放大，9-缩小，10-近焦距，11-远焦距
    private int direction;



    private HttpPostMethod httpPostMethod;//请求方式

    public StopPTZ(String accessToken,String deviceSerial,int direction){
        this(accessToken, deviceSerial,1, direction);
    }

    public StopPTZ(String accessToken,String deviceSerial,int channelNo,int direction){
        this.url = ServerConstant.STOP_PTZ;
        this.accessToken = accessToken;
        this.deviceSerial = deviceSerial;
        this.direction = direction;
        this.channelNo = channelNo;
        HttpUtil httpUtil = new HttpUtil();
        Map<String,Object> headMap = httpUtil.setHeadMap(host,contentType);
        httpPostMethod = new HttpPostMethod(method);
        httpPostMethod.setHeader(headMap);

        Map<String,Object> bodyMap = httpUtil.setBodyMap(accessToken,deviceSerial,null);
        bodyMap.put("direction",direction);
        bodyMap.put("channelNo",channelNo);
        httpPostMethod.setCompleteUrl(url,bodyMap);
    }

    public BasicResponse<BaseDeviceResponse> executeApi(){
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
