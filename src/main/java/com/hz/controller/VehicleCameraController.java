package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.mapper.VehicleCameraMapper;
import com.hz.mapper.VehicleMapper;
import com.hz.pojo.Vehicle;
import com.hz.pojo.VehicleCamera;
import com.hz.service.VehicleCameraService;
import com.hz.utils.JsonMassage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
@RequestMapping("/vehicle-camera")
public class VehicleCameraController {
    @Autowired
    private VehicleMapper vehicleMapper;
    @Autowired
    private VehicleCameraMapper vehicleCameraMapper;

    @Autowired
    private VehicleCameraService vehicleCameraService;

    @RequestMapping("/vehicleCameraList")
    public JsonMassage<List<VehicleCamera>> VehicleCameraList(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            String vehicleCard,
            String start_time,
            String end_time) {
        QueryWrapper<Vehicle> queryWrapper = new QueryWrapper<>();
        if ("" != vehicleCard && vehicleCard != null) {
            queryWrapper.like("vehicle_card", vehicleCard);
            System.out.println("vehicleCard:==========================" + vehicleCard);
        }
        List<Vehicle> vehicles = vehicleMapper.selectList(queryWrapper);
        QueryWrapper<VehicleCamera> queryWrap = new QueryWrapper<VehicleCamera>();
        for (Vehicle vehicle : vehicles) {
            queryWrap.or().eq("vehicle_id", vehicle.getVehicleId());
        }

        for (Vehicle vehicle : vehicles) {
            if (vehicle != null) {
                queryWrap.eq("vehicle_id", vehicle.getVehicleId()).or().eq("vehicle_id", vehicle.getVehicleId());
            }
        }
        if ("" != start_time && start_time != null) {
            queryWrap.apply("date_format(create_time,'%Y-%m-%d') >= date_format( '" + start_time + " ','%Y-%m-%d')");
        }
        if ("" != end_time && end_time != null) {
            queryWrap.apply("date_format(create_time,'%Y-%m-%d') <= date_format( '" + end_time + " ','%Y-%m-%d')");

        }
        queryWrap.orderByDesc("create_time");
        Page<VehicleCamera> page = new Page<VehicleCamera>(pageNo, pageSize);
        Page<VehicleCamera> list = vehicleCameraMapper.selectPage(page, queryWrap);
        JsonMassage<List<VehicleCamera>> jsonMassage = new JsonMassage<List<VehicleCamera>>(200, "ok", Math.toIntExact(page.getTotal()), list.getRecords());
        return jsonMassage;
    }

    @RequestMapping(value = "/selectVehicleCameraById", method = RequestMethod.POST)
    @ResponseBody
    public JsonMassage<VehicleCamera> selectVehicleCameraById(Integer vehicleCameraId) {
        VehicleCamera vehicleCamera = vehicleCameraMapper.selectById(vehicleCameraId);
        JsonMassage<VehicleCamera> jsonMas = new JsonMassage<VehicleCamera>(200, "查询成功", null, vehicleCamera);
        return jsonMas;
    }

    @RequestMapping(value = "/updateVehicleCameraById", method = RequestMethod.POST)
    @ResponseBody
    public JsonMassage<String> upState(VehicleCamera vehicleCamera) {
        //获取时间
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        //  2022-04-27 15:46:30
        String format = dtf.format(now);
        vehicleCamera.setUpdateTime(format);
        int i = vehicleCameraMapper.updateById(vehicleCamera);
        JsonMassage<String> jsonMas = new JsonMassage<String>(200, "ok", i, null);
        return jsonMas;
    }

    @RequestMapping(value = "/insertVehicleCamera", method = RequestMethod.POST)
    @ResponseBody
    public JsonMassage<String> insertVehicleCamera(VehicleCamera vehicleCamera) {
        //获取时间
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        //  2022-04-27 15:46:30
        String format = dtf.format(now);
        vehicleCamera.setDeleted(0);
        vehicleCamera.setUpdateTime(format);
        int i = vehicleCameraMapper.insert(vehicleCamera);
        JsonMassage<String> jsonMas = new JsonMassage<String>(200, "ok", i, null);
        return jsonMas;
    }

    @RequestMapping(value = "/deleteVehicleCameraId", method = RequestMethod.POST)
    @ResponseBody
    public JsonMassage deleteVehicleCameraId(Integer vehicleCameraId) {
        int i = vehicleCameraMapper.deleteById(vehicleCameraId);
        JsonMassage jsonMas = new JsonMassage<>(200, "ok", i, null);
        return jsonMas;
    }

    @RequestMapping("/all")
    public JsonMassage<List<VehicleCamera>> all() {
        List<VehicleCamera> list = vehicleCameraService.list();
        JsonMassage<List<VehicleCamera>> json = new JsonMassage<List<VehicleCamera>>(200, "ok", null, list);
        return json;
    }
}

