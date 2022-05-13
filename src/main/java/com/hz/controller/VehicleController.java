package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.pojo.Vehicle;
import com.hz.service.VehicleService;
import com.hz.utils.JsonMassage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 * 前端控制器    车辆信息列表
 * </p>
 *
 * @author UserG
 * @since 2022-04-26
 */
@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @RequestMapping(value = "findVehicleList", method = RequestMethod.GET)
    @ResponseBody
    public JsonMassage<List<Vehicle>> findVehicleList(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            String vehicleStatus,
            String start_time,
            String end_time
    ) {
        Integer Status = null;


        //条件构造器
        QueryWrapper<Vehicle> queryWrap = new QueryWrapper<Vehicle>();
        if ("空闲".equals(vehicleStatus) || "空".equals(vehicleStatus) || "闲".equals(vehicleStatus)) {
            Status = 1;
        }
        if ("运送".equals(vehicleStatus) || "运".equals(vehicleStatus) || "送".equals(vehicleStatus)) {
            Status = 2;
        }
        if ("维修".equals(vehicleStatus) || "维".equals(vehicleStatus) || "修".equals(vehicleStatus)) {
            Status = 3;
        }
        if ("报废".equals(vehicleStatus) || "报".equals(vehicleStatus) || "废".equals(vehicleStatus)) {
            Status = 4;
        }

        if (vehicleStatus != null) {
            queryWrap.like("vehicle_status", Status);
        }
        if ("" != start_time && start_time != null) {

            queryWrap.apply("date_format(create_time,'%Y-%m-%d') >= date_format( '" + start_time + " ','%Y-%m-%d')");
        }
        if ("" != end_time && end_time != null) {
            queryWrap.apply("date_format(create_time,'%Y-%m-%d') <= date_format( '" + end_time + " ','%Y-%m-%d')");

        }
        queryWrap.orderByDesc("create_time");
        //使用分页工具
        Page<Vehicle> page = new Page<Vehicle>(pageNo, pageSize);
        Page<Vehicle> page1 = vehicleService.page(page, queryWrap);

        JsonMassage<List<Vehicle>> jsonMassage = new JsonMassage<List<Vehicle>>(200, "ok", Math.toIntExact(page.getTotal()), page1.getRecords());


        return jsonMassage;

    }

    @RequestMapping(value = "/findVehicle", method = RequestMethod.POST)
    @ResponseBody

    public JsonMassage<Vehicle> findVehicle(Integer vehicleId) {
        Vehicle Vehicle = vehicleService.getById(vehicleId);

        JsonMassage<Vehicle> jsonMassage = new JsonMassage<Vehicle>(200, "ok", null, Vehicle);


        return jsonMassage;

    }

    /**
     * 根据id修改状态
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/updateById", method = RequestMethod.POST)
    @ResponseBody
    public JsonMassage<String> updateById(Vehicle vehicle) {
        //获取时间
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String format = dtf.format(now);    //  2022-04-27 15:46:30
        vehicle.setUpdateTime(format);

        //穿对象修改用户信息
        boolean b = vehicleService.updateById(vehicle);

        JsonMassage<String> jsonMas = new JsonMassage<String>(200, "ok", null, null);
        return jsonMas;
    }

    /**
     * 根据id删除
     *
     * @param vehicleId
     * @return
     */
    @RequestMapping(value = "/deleteVehicleById", method = RequestMethod.POST)
    @ResponseBody
    public JsonMassage deleteVehicleById(Integer vehicleId) {
        boolean b = vehicleService.removeById(vehicleId);
        JsonMassage jsonMas = new JsonMassage<>(200, "ok", null, null);
        return jsonMas;
    }

    /**
     * 新增用户
     *
     * @param vehicle
     * @return
     */
    /*@RequestMapping(value = "/insertVehicle", method = RequestMethod.POST)*/
    @RequestMapping(value = "/insertVehicle")
    @ResponseBody
    public JsonMassage<String> insertVehicle(Vehicle vehicle) {


        vehicle.setUrl("[" + vehicle.getUrl() + "]");
        boolean save = vehicleService.save(vehicle);
        JsonMassage<String> jsonMas = new JsonMassage<String>(200, "ok", null, null);
        return jsonMas;
    }

    /**
     * 查询所有信息
     */

    @RequestMapping("/vehicleList")
    @ResponseBody
    public JsonMassage<List<Vehicle>> vehicleList() {


        List<Vehicle> list = vehicleService.list();
        for (Vehicle vehicle : list) {
            System.out.println(vehicle.getUrl());
        }
        JsonMassage<List<Vehicle>> jsonMas = new JsonMassage<List<Vehicle>>(200, "ok", null, list);
        return jsonMas;
    }
    //查询车辆状态为空闲的车辆

    @RequestMapping("/IdleVehicleList")
    public JsonMassage<List<Vehicle>> IdleVehicleList(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            Integer vehicleTypeId
    ) {

        QueryWrapper<Vehicle> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("vehicle_status", "1");
        if(vehicleTypeId !=null){
            queryWrapper.eq("vehicle_type_id",vehicleTypeId);
        }


        Page page = new Page(pageNo, pageSize);
        Page page1 = vehicleService.page(page, queryWrapper);

        JsonMassage<List<Vehicle>> jsonMas = new JsonMassage<List<Vehicle>>(200, "ok", (int) page.getTotal(), page1.getRecords());
        return jsonMas;

    }

}

