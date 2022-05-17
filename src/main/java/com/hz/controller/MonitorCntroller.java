package com.hz.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hz.monitor.PTZ.StartPTZ;
import com.hz.monitor.PTZ.StopPTZ;
import com.hz.monitor.api.ServerConstant;
import com.hz.monitor.api.token.GetToken;
import com.hz.monitor.capture.CapturePic;
import com.hz.monitor.equipment.AddDevice;
import com.hz.monitor.equipment.ChangeDeviceName;
import com.hz.monitor.equipment.DeleteDevice;
import com.hz.monitor.equipment.GetDeviceInfo;
import com.hz.pojo.Driver;
import com.hz.pojo.Orderss;
import com.hz.pojo.VehicleSafety;
import com.hz.service.DriverService;
import com.hz.service.OrderssService;
import com.hz.service.VehicleSafetyService;
import com.hz.utils.JsonMassage;
import com.hz.utils.behaviors.DriverBehavior;
import com.hz.utils.monitor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orderss")
public class MonitorCntroller {

    private ServerConstant serverConstant;
    @Autowired
    private VehicleSafetyService vehicleSafetyService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private OrderssService orders;

    /**
     * 启动云台
     *
     * @param accessToken  令牌
     * @param deviceSerial 设备编号
     * @param direction    操作命令：0-上，1-下，2-左，3-右
     * @return
     */
    @RequestMapping("/startPTZ")
    public BasicResponse<BaseDeviceResponse> startPTZ(String accessToken, String deviceSerial, int direction) {
        accessToken = getToken();
        StartPTZ startPTZ = new StartPTZ(accessToken, deviceSerial, 1, direction, 1);
        BasicResponse<BaseDeviceResponse> basicResponse = startPTZ.executeApi();
        return basicResponse;

    }

    /**
     * 停止云台
     *
     * @param accessToken  令牌
     * @param deviceSerial 设备编号
     * @param direction    操作命令：0-上，1-下，2-左，3-右
     * @return
     */
    @RequestMapping("/stopPTZ")

    public BasicResponse<BaseDeviceResponse> stopPTZ(String accessToken, String deviceSerial, int direction) {
        accessToken = getToken();
        StopPTZ stopPTZ = new StopPTZ(accessToken, deviceSerial, direction);
        BasicResponse<BaseDeviceResponse> basicResponse = stopPTZ.executeApi();
        System.out.println(getToken());
        return basicResponse;
    }

    /**
     * 照片手动抓拍
     *
     * @param accessToken  令牌
     * @param deviceSerial 设备编号
     * @return
     */
    @RequestMapping("/CapturePic")
    public BasicResponse<CapturePicture> CapturePic(String accessToken, String deviceSerial, Long vehicleId) {
        CapturePic capturePicApi = new CapturePic(accessToken, deviceSerial);
        BasicResponse<CapturePicture> basicResponse = capturePicApi.executeApi();


        //行为分析
        String s = DriverBehavior.driver_behavior(basicResponse.getData().getPicUrl());
        System.out.println(s);
        JSONObject jsonObject = JSONObject.parseObject(s);
        JSONArray eyes = JSON.parseArray(jsonObject.getString("person_info"));
        if (eyes.size()!=0) {
            JSONObject attributes = (JSONObject) eyes.getJSONObject(0).get("attributes");
            //驾驶员属性行为内容
            /*JSONObject both_hands_leaving_wheel= (JSONObject) attributes.get("both_hands_leaving_wheel");*/

            //eyes_closed   闭眼
            JSONObject eyes_closed = (JSONObject) attributes.get("eyes_closed");
            Object closedScore = eyes_closed.get("score");
            Object closedThreshold = eyes_closed.get("threshold");

            String substring = closedScore.toString().substring(0, 5);
            Double eyesScore = Double.parseDouble(substring);
            Double eyesThreshold = Double.parseDouble(closedThreshold.toString());

            //eyes_closed   安全带
            JSONObject not_buckling_up = (JSONObject) attributes.get("not_buckling_up");
            Object bucklingScore = not_buckling_up.get("score");
            Object bucklingThreshold = not_buckling_up.get("threshold");
            Double notBucklingScore = Double.parseDouble(bucklingScore.toString());
            Double notBucklingThreshold = Double.parseDouble(bucklingThreshold.toString());

            List list = new ArrayList<>();

            //查询车辆信息
            Driver driver = driverService.getById(vehicleId);
            VehicleSafety safety = new VehicleSafety();
            safety.setVehicleId(vehicleId);
            safety.setDriverId(driver.getDriverId());
            Orderss orders = this.orders.getById(vehicleId);
            safety.setWaybillInfoId(orders.getWaybillId());
            safety.setImage(basicResponse.getData().getPicUrl());

            if (eyesScore > eyesThreshold) {
                list.add(1);
                safety.setVehicleSafetyId(1L);
            } else {
                list.add(0);
                safety.setVehicleSafetyId(0L);
            }
            if (notBucklingScore > notBucklingThreshold) {
                list.add(2);
                safety.setVehicleSafetyId(2L);
            } else {
                list.add(0);
                safety.setVehicleSafetyId(0L);
            }
            vehicleSafetyService.save(safety);
        }
        return basicResponse;
    }

    /**
     * 添加设备
     *
     * @param accessToken  令牌
     * @param deviceSerial 设备编号
     * @param validateCode 验证码
     * @return
     */
    @RequestMapping("/AddDevice")
    public BasicResponse<BaseDeviceResponse> AddDevice(String accessToken, String deviceSerial, String validateCode) {
        accessToken = getToken();
        AddDevice addDevice = new AddDevice(accessToken, deviceSerial, validateCode);
        BasicResponse<BaseDeviceResponse> basicResponse = addDevice.executeApi();
        return basicResponse;
    }

    /**
     * 删除设备
     *
     * @param accessToken  令牌
     * @param deviceSerial 设备序号
     * @return
     */
    @RequestMapping("/DeleteDevice")
    public BasicResponse<BaseDeviceResponse> DeleteDevice(String accessToken, String deviceSerial) {
        accessToken = getToken();
        DeleteDevice deleteDevice = new DeleteDevice(accessToken, deviceSerial);
        BasicResponse<BaseDeviceResponse> basicResponse = deleteDevice.executeApi();
        return basicResponse;
    }

    /**
     * 获取设备信息
     *
     * @param accessToken  令牌
     * @param deviceSerial 设备序号
     * @return
     */
    @RequestMapping("/GetDeviceInfo")
    public BasicResponse<DeviceInfoResponse> GetDeviceInfo(String accessToken, String deviceSerial) {
        accessToken = getToken();
        GetDeviceInfo get = new GetDeviceInfo(ServerConstant.GET_DEVICE_INFO, accessToken, deviceSerial);
        BasicResponse<DeviceInfoResponse> basicResponse = get.executeApi();
        return basicResponse;
    }

    /**
     * 修改设备名称
     *
     * @param accessToken
     * @param deviceSerial
     * @param deviceName   新名字
     * @return
     */

    @RequestMapping("/ChangeDeviceName")
    public BasicResponse<BaseDeviceResponse> ChangeDeviceName(String accessToken, String deviceSerial, String deviceName) {
        accessToken = getToken();
        ChangeDeviceName changeDeviceName = new ChangeDeviceName(ServerConstant.CHANGE_DEVICE_NAME, accessToken, deviceSerial, deviceName);
        BasicResponse<BaseDeviceResponse> basicResponse = changeDeviceName.executeApi();
        return basicResponse;

    }


    /**
     * 解决Token七天过期问题
     * <p>
     * appKey    自己账号的key
     * appSecret 自己账号的Secret
     * 当前写死数据
     *
     * @return
     */
    private static String getToken() {
        GetToken getToken = new GetToken("85062adab25e4db29ce73d162de73027", "95ff75bc9a7ad9772b947f02577c26aa");
        BasicResponse<AccessToken> response = getToken.executeApi();
        AccessToken dataInternal = response.getData();
        String accessToken = dataInternal.getAccessToken();
        return accessToken;
    }

    /**
     * 获取token
     */
    @RequestMapping("/token")
    public JsonMassage<String> token() {
        JsonMassage<String> jsonMassage = new JsonMassage<String>(200, "ok", null, getToken());
        return jsonMassage;
    }


}
