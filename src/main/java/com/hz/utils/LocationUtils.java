package com.hz.utils;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取地址经纬度的api
 */
@Component
public class LocationUtils {
    /**
     * http://lbsyun.baidu.com/apiconsole/key
     * <百度开发者>用户申请注册的key，自v2开始参数修改为“ak”，之前版本参数为“key” 申请ak
     */
    final static String AK = "F8uowntDdgHPf5ccsI6uzOUQZGYTx7jF";//百度开发者ak
    final static String ADDRESS_TO_LONGITUDEA_URL = "http://api.map.baidu.com/geocoding/v3/?output=json&location=showLocation";



    public  Map<String, Double> AddressTolongitudea(String address) {
        /**StringUtils的isBlank()方法可以一次性校验这三种情况，返回值都是true,否则为false
         * 是否为 null
         * 是否为 “”
         * 是否为空字符串(引号中间有空格) 如： " "。
         */
        if (StringUtils.isBlank(address)) {
            return null;
        }
        String url = ADDRESS_TO_LONGITUDEA_URL + "&ak=" + AK + "&address=" + address;
        HttpClient client = HttpClients.createDefault(); // 创建默认http连接
        HttpPost post = new HttpPost(url);// 创建一个post请求

        try {
            HttpResponse response = client.execute(post);// 用http连接去执行get请求并且获得http响应
            HttpEntity entity = response.getEntity();// 从response中取到响实体
            String html = EntityUtils.toString(entity);// 把响应实体转成文本
            // JSON转对象
            Map map = JSON.parseObject(html, Map.class);
            Map result = (Map) map.get("result");
            Map results = (Map) result.get("location");
            HashMap<String, Double> hashMap = new HashMap<String, Double>();

            //Java在java.math包中提供的API类BigDecimal，用来对超过16位有效位的数进行精确的运算。
            BigDecimal lng = (BigDecimal) results.get("lng");
            BigDecimal lat = (BigDecimal) results.get("lat");
            //经度
            hashMap.put("lng", lng.doubleValue());
            //维度
            hashMap.put("lat", lat.doubleValue());
            return hashMap;
        } catch (Exception e) {
            return null;
        }
    }
    //逆地理编码主函数
    public  String inverseGeocoding(String lng,String lat){
        String location=lng+","+lat;
        //bd09坐标系（百度经纬度坐标）
        String url="http://api.map.baidu.com/reverse_geocoding/v3/?ak="+AK+"&output=json&coordtype=bd09ll&location="+location;
        String res = acquire(url);
        /**
         * JSON中有一个静态方法parseObject（String text），将text解析为一个JSONObject对象并返回
         * 使用getJSONObject时,如果返回的对象不是JSONObject,抛出JSONException异常
         */
        String addressLocation= JSON.parseObject(res).getJSONObject("result").getString("formatted_address");
        System.out.println(addressLocation);
        return addressLocation;
    }
    //Http处理函数
    public static String acquire(String url){
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient= HttpClientBuilder.create().build();
        HttpGet httpGet=new HttpGet(url);
        CloseableHttpResponse response=null;
        try {
            response=httpClient.execute(httpGet);
            HttpEntity responseEntity=response.getEntity();
            if (responseEntity!=null){
                return EntityUtils.toString(responseEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(httpClient!=null){
                    httpClient.close();
                }
                if (response!=null){
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private  final double EARTH_RADIUS = 6371393; // 地球平均半径,单位：m
    /**
     * 通过AB点经纬度获取距离
     *
     * @param pointA A点(经，纬)
     * @param pointB B点(经，纬)
     * @return 距离(单位：米)
     */
    public  double getDistance(Point2D pointA, Point2D pointB) {
        // 经纬度（角度）转弧度。弧度用作参数，以调用Math.cos和Math.sin
        double radiansAX = Math.toRadians(pointA.getX()); // A经弧度
        double radiansAY = Math.toRadians(pointA.getY()); // A纬弧度
        double radiansBX = Math.toRadians(pointB.getX()); // B经弧度
        double radiansBY = Math.toRadians(pointB.getY()); // B纬弧度

        // cosβ1cosβ2cos（α1-α2）+sinβ1sinβ2
        double cos = Math.cos(radiansAY) * Math.cos(radiansBY) * Math.cos(radiansAX - radiansBX)
                + Math.sin(radiansAY) * Math.sin(radiansBY);
        double acos = Math.acos(cos); // 反余弦值
        return EARTH_RADIUS * acos; // 最终结果
    }
}

