package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.mapper.DriverMapper;
import com.hz.mapper.OrderssMapper;
import com.hz.mapper.VehicleMapper;
import com.hz.mapper.WaybillInfoMapper;
import com.hz.pojo.Driver;
import com.hz.pojo.Orderss;
import com.hz.pojo.WaybillInfo;
import com.hz.service.DriverService;
import com.hz.utils.JsonMassage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    private OrderssMapper orderssMapper;
    @Autowired
    private DriverMapper driverMapper;
    @Autowired
    private WaybillInfoMapper waybillInfoMapper;
    @Autowired
    private DriverService driverService;
    @RequestMapping("/jiedan")
    public JsonMassage jiedan(Long id,String type,Long driverId){
        Driver driver = driverMapper.selectById(driverId);
        Orderss orderss = orderssMapper.selectById(id);
        orderss.setVehicleId(driver.getDriverAttributionId());
        orderss.setOrderState(2);
        JsonMassage jsonMassage = new JsonMassage();
        int i = orderssMapper.updateById(orderss);
        if (i > 0) {
            jsonMassage.setCode(200);
            jsonMassage.setDataCount(i);
            return jsonMassage;
        }
        jsonMassage.setCode(500);
        return jsonMassage;
    }
    @RequestMapping("/allorderss")
    public JsonMassage allOrderss(){
        QueryWrapper<Orderss> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_state",0).or().eq("order_state",1);
        List<Orderss> ordersses = orderssMapper.selectList(queryWrapper);
        JsonMassage jsonMassage = new JsonMassage();
        jsonMassage.setCode(200);
        jsonMassage.setData(ordersses);
        jsonMassage.setMsg("true");
        return jsonMassage;
    }
    @RequestMapping("/kaoqin")
    public JsonMassage driverKaoQin(Long id){
        QueryWrapper<WaybillInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_id",id);
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
    public JsonMassage driverWayBillInfo(String checkaddress,String searchtime,String latandlon,String latitude,String longitude,Long driverId){
        WaybillInfo waybillInfo = new WaybillInfo();
        waybillInfo.setDriverId(driverId);
        waybillInfo.setWaybillInfoSpendTime(checkaddress);
        waybillInfo.setWaybillInfoFinallyX(longitude);
        waybillInfo.setWaybillInfoFinallyY(latitude);
        Driver driver = driverMapper.selectById(driverId);
        QueryWrapper<Orderss> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("vehicle_id",driver.getDriverAttributionId());
        Orderss orderss = orderssMapper.selectOne(queryWrapper);
        JsonMassage jsonMassage = new JsonMassage();
        if (orderss != null) {
            System.out.println(orderss.getOrderId());
            waybillInfo.setOrderId(orderss.getOrderId());
            int insert = waybillInfoMapper.insert(waybillInfo);
            if (insert == 1) {
                jsonMassage.setCode(200);
                return jsonMassage;
            }
        }
        jsonMassage.setCode(500);
        return jsonMassage;
    }

    @RequestMapping("/login")
    public JsonMassage driverLogin(String phone,String password){
        QueryWrapper<Driver> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("driver_phone",phone).eq("driver_password",password);
        Driver driver = driverMapper.selectOne(queryWrapper);
        JsonMassage jsonMassage = new JsonMassage();
        if (driver!=null){
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
        QueryWrapper<Driver> wrapper = new QueryWrapper<Driver>();
        if (driverName != null) {
            wrapper.like("driver_name", driverName);
        }
        if (Idcard != null) {
            wrapper.like("driver_Idcard", Idcard);
        }

        Page<Driver> page = new Page<>(pageNo, pageSize);

        Integer count = driverMapper.selectCount(wrapper);
        IPage<Driver> iPage = driverMapper.selectPage(page, wrapper);
        System.out.println("总页数:" + iPage.getPages());
        System.out.println("总记录数:" + iPage.getTotal());
        List<Driver> list = iPage.getRecords();
        list = driverMapper.selectList(wrapper);
        JsonMassage<List<Driver>> jsonMassage = new JsonMassage<List<Driver>>();
        jsonMassage.setDataCount(count);
        jsonMassage.setMsg("ok");
        jsonMassage.setData(list);
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
    public JsonMassage adddriver(String driverName, String driverIdcard, Integer driverState, String driverPhone) {
        Driver driver = new Driver();
        driver.setDriverName(driverName);
        driver.setDriverIdcard(driverIdcard);
        driver.setDriverState(driverState);
        driver.setDriverPhone(driverPhone);
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
     * @return
     */
    @RequestMapping("/driverList")
    @ResponseBody
    public JsonMassage<List<Driver>> driverList() {
        List<Driver> list = driverService.list();
        JsonMassage<List<Driver>> jsonMassage = new JsonMassage<List<Driver>>(200, "ok", null, list);
        return jsonMassage;

    }


}

