package com.hz.controller;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.mapper.DriverMapper;
import com.hz.mapper.OrderssMapper;
import com.hz.mapper.WaybillInfoMapper;
import com.hz.pojo.Driver;
import com.hz.pojo.Orderss;
import com.hz.pojo.Vehicle;
import com.hz.pojo.WaybillInfo;
import com.hz.service.VehicleService;
import com.hz.service.impl.WaybillInfoServiceImpl;
import com.hz.utils.JsonMassage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author UserG
 * @since 2022-04-26
 */
@RestController
@RequestMapping("/waybill-info")
public class WaybillInfoController {
    @Autowired
    private WaybillInfoMapper waybillInfoMapper;
    @Autowired
    private WaybillInfoServiceImpl waybillInfoService;
    @Autowired
    private DriverMapper driverMapper;
    @Autowired
    private OrderssMapper orderssMapper;
    @Autowired
    private VehicleService vehicleService;

    /**
     * VUE 调用 分页查询  多条件
     *
     * @return
     */
    @RequestMapping(value = "/waybill", method = RequestMethod.GET)
    @ResponseBody
    public JsonMassage<List<WaybillInfo>> selectPageList(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            String driverName, Orderss orderss
    ) {
        QueryWrapper<WaybillInfo> queryWrap = new QueryWrapper<>();

        if (driverName != null) {
            QueryWrapper<Driver> wrap = new QueryWrapper<>();
            wrap.like("driver_name", driverName);
            List<Driver> drivers = driverMapper.selectList(wrap);
            for (Driver driver : drivers) {
                Long driverId = driver.getDriverId();
                queryWrap.eq("driver_id", driverId);
            }
        }

        if (orderss.getWaybillId() != null) {
            QueryWrapper<Orderss> wrap = new QueryWrapper<Orderss>();
            wrap.like("waybill_id", orderss.getWaybillId());
            List<Orderss> orders = orderssMapper.selectList(wrap);
            for (Orderss order : orders) {
                Long orderId = order.getOrderId();
                queryWrap.eq("order_id", orderId);
            }
        }
        queryWrap.orderByDesc("create_time");
        Page<WaybillInfo> page = new Page<WaybillInfo>(pageNo, pageSize);
        Page<WaybillInfo> records = waybillInfoService.page(page, queryWrap);
        JsonMassage<List<WaybillInfo>> jsonMassage = new JsonMassage<List<WaybillInfo>>(200, "请求成功", Math.toIntExact(page.getTotal()), records.getRecords());
        return jsonMassage;
    }

    /**
     * 增加数据
     */
    @RequestMapping(value = "/waybillInfoInsert", method = RequestMethod.GET)
    @ResponseBody
    public JsonMassage<String> WaybillInfoInsert(WaybillInfo waybillInfo
    ) {
        int insert = waybillInfoMapper.insert(waybillInfo);
        JsonMassage<String> jsonMassage = new JsonMassage<String>();
        if (insert != 0) {
            jsonMassage.setCode(200);
        } else {
            jsonMassage.setCode(201);
        }
        jsonMassage.setMsg("请求成功");
        jsonMassage.setData(null);
        jsonMassage.setDataCount(null);
        return jsonMassage;
    }

    /**
     * 修改数据
     */
    @RequestMapping(value = "/waybillInfoUpd", method = RequestMethod.POST)
    @ResponseBody
    public JsonMassage<String> WaybillInfoUpd(WaybillInfo waybillInfo) {

        boolean insert = waybillInfoService.updateById(waybillInfo);
        JsonMassage<String> jsonMassage = new JsonMassage<String>();
        if (insert) {
            jsonMassage.setCode(200);
        } else {
            jsonMassage.setCode(201);
        }
        jsonMassage.setCode(200);
        jsonMassage.setMsg("请求成功");
        jsonMassage.setData(null);
        jsonMassage.setDataCount(null);
        return jsonMassage;
    }

    /**
     * 查询数据
     */
    @RequestMapping(value = "/waybillInfoById", method = RequestMethod.GET)
    @ResponseBody
    public JsonMassage<WaybillInfo> WaybillInfoById(WaybillInfo WaybillInfo) {
        //添加条件
        QueryWrapper<WaybillInfo> queryWrapper = new QueryWrapper<WaybillInfo>();
        queryWrapper.eq("waybill_info_id", WaybillInfo);
        //查询指定条件的数据
        WaybillInfo waybil = waybillInfoService.getById(WaybillInfo);
        JsonMassage<WaybillInfo> jsonMassage = new JsonMassage<WaybillInfo>(200, "请求成功", null, waybil);
        return jsonMassage;
    }

    /**
     * 删除数据
     */
    @RequestMapping(value = "/waybillInfoDelById", method = RequestMethod.POST)
    @ResponseBody
    public JsonMassage WaybillInfoDelById(Integer waybillInfoId) {
        int i = waybillInfoMapper.deleteById(waybillInfoId);
        JsonMassage jsonMap = new JsonMassage<>(200, "请求成功", null, null);
        return jsonMap;

    }

    @RequestMapping("/ListByOrderId")
    public JsonMassage ListByVehicleId(Integer orderId){
        System.out.println(orderId);
        QueryWrapper<WaybillInfo> queryWrap = new QueryWrapper<>();
        queryWrap.eq("order_id", orderId);
        JSONArray pareMap = new JSONArray();
        List<WaybillInfo> list = waybillInfoService.list(queryWrap);
        if (list.size() > 0) {
            Driver driver = driverMapper.selectById(list.get(0).getDriverId());
            Vehicle vehicle = vehicleService.getById(driver.getDriverAttributionId());

            for (WaybillInfo waybillInfo : list) {
                JSONObject jsonObject = new JSONObject();


                jsonObject.putOnce("driverName", "司机姓名：" + driver.getDriverName());

                jsonObject.putOnce("vehicleCard", "车辆信息：" + vehicle.getVehicleCard());
                //打卡地址
                jsonObject.putOnce("adds", "打卡：" + waybillInfo.getWaybillInfoSpendTime());

                //打卡时间
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  // 实例化模板对象
                String format = sdf1.format(waybillInfo.getCreateTime());
                jsonObject.putOnce("time", "打卡时间：" + format);
                JSONObject jsonObject1 = new JSONObject();

                jsonObject1.putOnce("lng", waybillInfo.getWaybillInfoFinallyX());
                jsonObject1.putOnce("lat", waybillInfo.getWaybillInfoFinallyY());

                jsonObject.putOnce("markerPoint", jsonObject1);
                pareMap.put(jsonObject);

            }
        }
        JsonMassage jsonMap = new JsonMassage(200, "ok", null, pareMap);
        return jsonMap;
    }


}

