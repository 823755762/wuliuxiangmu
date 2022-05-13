package com.hz.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
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

    /**
     * 调用百度地图地理编码服务接口，根据地址获取坐标（经度、纬度）
     *
     * @param address
     * @return
     */
    public  String getCoord(String address) {
        String httpUrl = "http://api.map.baidu.com/geocoding/v3/?address=" + address + "&output=json&ak=" + AK;
        String json = loadJSON(httpUrl);
        Map map = JSON.parseObject(json, Map.class);

        String status = map.get("status").toString();
        if (status.equals("0")) {
            //返回结果成功，能够正常解析地址信息
            Map result = (Map) map.get("result");
            Map location = (Map) result.get("location");
            String lng = location.get("lng").toString();
            String lat = location.get("lat").toString();

            DecimalFormat df = new DecimalFormat("#.######");
            String lngStr = df.format(Double.parseDouble(lng));
            String latStr = df.format(Double.parseDouble(lat));
            String r = latStr + "," + lngStr;
            return r;
        }

        return null;
    }

    /**
     * 调用百度地图驾车路线规划服务接口，根据寄件人地址和收件人地址坐标计算订单距离
     *
     * @param origin
     * @param destination
     * @return
     */
    public  Double getDistance(String origin, String destination) {
        String httpUrl = "http://api.map.baidu.com/directionlite/v1/driving?origin="
                + origin + "&destination="
                + destination + "&ak=" + AK;

        String json = loadJSON(httpUrl);

        Map map = JSON.parseObject(json, Map.class);
        if ("0".equals(map.getOrDefault("status", "500").toString())) {
            Map childMap = (Map) map.get("result");
            JSONArray jsonArray = (JSONArray) childMap.get("routes");
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);
            double distance = Double.parseDouble(jsonObject.get("distance") == null ? "0" : jsonObject.get("distance").toString());
            return distance;
        }

        return null;
    }

    /**
     * 调用百度地图驾车路线规划服务接口，根据寄件人地址和收件人地址坐标计算订单距离
     *
     * @param origin
     * @param destination
     * @return
     */
    public  Integer getTime(String origin, String destination) {
        String httpUrl = "http://api.map.baidu.com/directionlite/v1/driving?origin="
                + origin + "&destination="
                + destination + "&ak=" + AK;

        String json = loadJSON(httpUrl);

        Map map = JSON.parseObject(json, Map.class);
        if ("0".equals(map.getOrDefault("status", "500").toString())) {
            Map childMap = (Map) map.get("result");
            JSONArray jsonArray = (JSONArray) childMap.get("routes");
            JSONObject jsonObject = (JSONObject) jsonArray.get(0);
            int time = Integer.parseInt(jsonObject.get("duration") == null ? "0" : jsonObject.get("duration").toString());
            return time;
        }

        return null;
    }

    /**
     * 调用服务接口，返回百度地图服务端的结果
     *
     * @param httpUrl
     * @return
     */
    public static String loadJSON(String httpUrl) {
        StringBuilder json = new StringBuilder();
        try {
            URL url = new URL(httpUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        System.out.println(json.toString());
        return json.toString();
    }
}

