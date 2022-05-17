package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.mapper.*;
import com.hz.pojo.*;
import com.hz.service.DriverService;
import com.hz.service.OrderStatusRecordService;
import com.hz.utils.JsonMassage;
import jdk.nashorn.internal.runtime.linker.JavaAdapterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import javax.xml.bind.util.JAXBSource;
import java.lang.System;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
@RequestMapping("/driver")
public class DriverController {
    @Autowired
    private OrderStatusRecordMapper orderStatusRecordMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private OrderssMapper orderssMapper;
    @Autowired
    private DriverMapper driverMapper;
    @Autowired
    private WaybillInfoMapper waybillInfoMapper;
    @Autowired
    private DriverService driverService;
    @Autowired
    private  VehicleMapper vehicleMapper;
    @RequestMapping("/zhuangtai")
    public JsonMassage zhuangtai(int index,Long driverId){
        JsonMassage jsonMassage = new JsonMassage();
        Driver driver = driverMapper.selectById(driverId);

        Vehicle vehicle = vehicleMapper.selectById(driver.getDriverAttributionId());
        if (vehicle!=null) {
            vehicle.setVehicleStatus(index);
            int i = vehicleMapper.updateById(vehicle);
            if (i == 1) {
                jsonMassage.setCode(200);
                return jsonMassage;
            }
        }
        jsonMassage.setCode(500);
        return jsonMassage;
    }
    @RequestMapping("/findOrderStatusRecord")
    public JsonMassage<OrderStatusRecord> findOrderStatusRecord(Integer id){
        OrderStatusRecord orderStatusRecord = orderStatusRecordMapper.selectById(id);
        JsonMassage<OrderStatusRecord> jsonMassage = new JsonMassage<>(200,"ok",1,orderStatusRecord);
        return jsonMassage;
    }
    @RequestMapping("/allorderss2")
    public JsonMassage allOrderss2(Long driverId){
        JsonMassage jsonMassage = new JsonMassage();
        Driver driver = driverMapper.selectById(driverId);
        if (driver == null) {
            jsonMassage.setCode(500);
            return jsonMassage;
        }
        QueryWrapper<Orderss> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("vehicle_id",driver.getDriverAttributionId());
        queryWrapper.eq("order_state",3).or().eq("order_state",4);
        queryWrapper.eq("vehicle_id",driver.getDriverAttributionId());
        List<Orderss> ordersses = orderssMapper.selectList(queryWrapper);

        jsonMassage.setCode(200);
        jsonMassage.setData(ordersses);
        jsonMassage.setMsg("true");
        return jsonMassage;
    }
    @RequestMapping("/allOrderss")
    public JsonMassage allOrderss(){
        JsonMassage jsonMassage = new JsonMassage();
        List<Orderss> ordersses = orderssMapper.selectList(null);
        if (ordersses == null) {
            jsonMassage.setCode(500);
            return jsonMassage;
        }
        jsonMassage.setData(ordersses);
        jsonMassage.setCode(200);
        return jsonMassage;
    }
    @RequestMapping("/findGoods")
    public JsonMassage findGoods(Long id){
        JsonMassage jsonMassage = new JsonMassage();
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id",id);
        Goods goods = goodsMapper.selectOne(queryWrapper);
        if (goods == null) {
            jsonMassage.setCode(500);
            return jsonMassage;
        }
        jsonMassage.setCode(200);
        jsonMassage.setData(goods);
        return jsonMassage;
    }
    @RequestMapping("/findOrderss")
    public JsonMassage findOrderss(Long id){
        JsonMassage jsonMassage = new JsonMassage();
        Orderss orderss = orderssMapper.selectById(id);
        if (orderss == null) {
            jsonMassage.setCode(500);
            return jsonMassage;
        }
        jsonMassage.setData(orderss);
        jsonMassage.setCode(200);
        return jsonMassage;
    }
    @RequestMapping("/tihuo")
    public JsonMassage tihuo(Long id,Long driverId,int type){
        JsonMassage jsonMassage = new JsonMassage();
        OrderStatusRecord orderStatusRecord = new OrderStatusRecord();
        Orderss orderss = orderssMapper.selectById(id);
        if (orderss == null) {
            jsonMassage.setCode(500);
            return jsonMassage;
        }
        Driver driver = driverMapper.selectById(driverId);
        if (driver == null) {
            jsonMassage.setCode(500);
            return jsonMassage;
        }
        if(type==0){
            orderss.setOrderState(2);
            orderss.setVehicleId(driver.getDriverAttributionId());
            orderStatusRecord.setStatusDescription("运输中");
        }
        if (type == 2) {
            orderss.setOrderState(3);
            orderStatusRecord.setStatusDescription("待付尾款");
        }
        int i = orderssMapper.updateById(orderss);
        if (i != 0) {
            orderStatusRecord.setOrderId(id);
            //获取时间
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            //  2022-04-27 15:46:30
            String format = dtf.format(now);
            orderStatusRecord.setTime(format);
            int insert = orderStatusRecordMapper.insert(orderStatusRecord);
            if(insert > 0){
                jsonMassage.setCode(200);
            }
        }
        return jsonMassage;
    }
    @RequestMapping("/getgoods")
    public JsonMassage getGoods(Long id){
        JsonMassage jsonMassage = new JsonMassage();
        Orderss orderss = orderssMapper.selectById(id);
        if (orderss!=null){
            jsonMassage.setCode(200);
            jsonMassage.setData(orderss);
            return jsonMassage;
        }
        return jsonMassage;
    }

    @RequestMapping("/diverallorders")
    public JsonMassage diverAllOrders(Long driverId){
        Driver driver = driverMapper.selectById(driverId);
        QueryWrapper<Orderss> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("vehicle_id",driver.getDriverAttributionId())
        .eq("order_state",0).or().eq("vehicle_id",driver.getDriverAttributionId()).eq("order_state",1).or();
        queryWrapper.eq("vehicle_id",driver.getDriverAttributionId()).eq("order_state",2);
        List<Orderss> ordersses = orderssMapper.selectList(queryWrapper);
        JsonMassage jsonMassage = new JsonMassage();
        if (ordersses.size() > 0) {
            jsonMassage.setData(ordersses);
            jsonMassage.setCode(200);
            jsonMassage.setMsg("ok");
            return jsonMassage;
        }
        jsonMassage.setCode(500);
        return jsonMassage;
    }
    @RequestMapping("/kaoqin")
    public JsonMassage driverKaoQin(Long id) {
        QueryWrapper<WaybillInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id", id).orderByDesc("create_time");
        List<WaybillInfo> waybillInfos = waybillInfoMapper.selectList(queryWrapper);
        JsonMassage jsonMassage = new JsonMassage();
        if (waybillInfos.size() > 0) {
            jsonMassage.setData(waybillInfos);
            jsonMassage.setCode(200);
            jsonMassage.setMsg("ok");
            return jsonMassage;
        }
        jsonMassage.setCode(500);
        return jsonMassage;
    }

    @RequestMapping("/sijidaka")
    public JsonMassage driverWayBillInfo(String checkaddress, String searchtime, String latandlon, String latitude, String longitude, Long driverId) {
        WaybillInfo waybillInfo = new WaybillInfo();
        waybillInfo.setDriverId(driverId);
        waybillInfo.setWaybillInfoSpendTime(checkaddress);
        waybillInfo.setWaybillInfoFinallyX(longitude);
        waybillInfo.setWaybillInfoFinallyY(latitude);
        Driver driver = driverMapper.selectById(driverId);
        QueryWrapper<Orderss> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("vehicle_id", driver.getDriverAttributionId()).eq("order_state",2);
        List<Orderss> ordersses = orderssMapper.selectList(queryWrapper);
        JsonMassage jsonMassage = new JsonMassage();
        int insert = 0;
        if (ordersses != null) {
            System.out.println("orderssesSize==============="+ordersses.size());
            for(Orderss orderss:ordersses){
                waybillInfo.setOrderId(orderss.getOrderId());
                insert = waybillInfoMapper.insert(waybillInfo);
            }
        }
        if (insert == 1) {
            jsonMassage.setCode(200);
            return jsonMassage;
        }
        jsonMassage.setCode(500);
        return jsonMassage;
    }

    @RequestMapping("/login")
    public JsonMassage driverLogin(String phone, String password) {
        QueryWrapper<Driver> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_phone", phone).eq("driver_password", password);
        Driver driver = driverMapper.selectOne(queryWrapper);
        JsonMassage jsonMassage = new JsonMassage();
        if (driver != null) {
            jsonMassage.setCode(200);
            jsonMassage.setData(driver);
            return jsonMassage;
        }
        jsonMassage.setCode(0);
        return jsonMassage;
    }

    @RequestMapping("/findAll")
    public JsonMassage<List<Driver>> findAll(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            String driverName, Integer Idcard) {
        Page<Driver> page = new Page<Driver>(pageNo, pageSize);
        QueryWrapper<Driver> queryWrap = new QueryWrapper<>();
        if (driverName != null || Idcard != null) {
            queryWrap.orderByDesc("driver_id");
            queryWrap.like("driver_name", driverName).or().eq("driver_Idcard", Idcard);
            // 输出page对象分页查询信息
            driverMapper.selectPage(page, queryWrap);
        } else {
            queryWrap.orderByDesc("driver_id");
            driverMapper.selectPage(page, queryWrap);
        }
        Integer total = Math.toIntExact(page.getTotal());
        List<Driver> records = page.getRecords();
        JsonMassage<List<Driver>> jsonMassage = new JsonMassage<>();
        jsonMassage.setDataCount(total);
        jsonMassage.setMsg("ok");
        jsonMassage.setData(records);
        jsonMassage.setCode(200);
        return jsonMassage;
    }

    @RequestMapping("/updatedriver")
    public JsonMassage updatedriver(Driver driver) {
        System.out.println(driver.toString());
        int i = driverMapper.updateById(driver);
        System.out.println("受影响行数+=======" + i);
        JsonMassage resultJson = new JsonMassage();
        resultJson.setCode(200);
        resultJson.setMsg("ok");
        return resultJson;
    }

    @RequestMapping("/adddirver")
    public JsonMassage adddriver(Driver driver) {

        int i = driverMapper.insert(driver);
        JsonMassage resultJson = new JsonMassage();
        resultJson.setCode(200);
        resultJson.setMsg("ok");
        return resultJson;
    }

    @RequestMapping("/deletedriver")
    public JsonMassage deletedriver(int id) {

        int i = driverMapper.deleteById(id);
        JsonMassage jsonMassage = new JsonMassage<>();
        jsonMassage.setDataCount(1);
        jsonMassage.setCode(200);
        jsonMassage.setData(i);
        jsonMassage.setMsg("ok");
        return jsonMassage;
    }

    @RequestMapping("/driverid")
    public JsonMassage<Driver> findByid(Integer id) {
        Driver driver = driverMapper.selectById(id);
        JsonMassage<Driver> jsonMassage = new JsonMassage<>();
        jsonMassage.setCode(200);
        jsonMassage.setMsg("ok");
        jsonMassage.setData(driver);
        jsonMassage.setDataCount(1);
        return jsonMassage;
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping("/driverList")
    @ResponseBody
    public JsonMassage<List<Driver>> driverList() {
        List<Driver> list = driverService.list();
        JsonMassage<List<Driver>> jsonMassage = new JsonMassage<List<Driver>>(200, "ok", null, list);
        return jsonMassage;

    }

    @RequestMapping("/findvehicle")
    public JsonMassage<Vehicle> findvehicle(Integer id){
        Driver driver=driverMapper.selectById(id);
        Long i=driver.getDriverAttributionId();
        Vehicle vehicle=vehicleMapper.selectById(i);
        JsonMassage<Vehicle> jsonMassage=new JsonMassage<Vehicle>(200,"ok",1,vehicle);

        return jsonMassage;
    }

}

