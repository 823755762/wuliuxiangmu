package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.mapper.DriverMapper;
import com.hz.mapper.VehicleTollMapper;
import com.hz.pojo.Driver;
import com.hz.pojo.Equipment;
import com.hz.pojo.VehicleToll;
import com.hz.service.VehicleTollService;
import com.hz.utils.JsonMassage;
import com.hz.utils.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author UserG
 * @since 2022-04-26
 */
@RestController
@RequestMapping("/vehicle-toll")




public class VehicleTollController {
    @Autowired
    private VehicleTollMapper vehicleTollMapper;

    @Autowired
    private VehicleTollService vehicleTollService;

    @RequestMapping("chaxun")
    public JsonMassage<List<VehicleToll>> chaxun(
            @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
            String vehicleTollWeight
    ){
        System.out.println("进入chaxun");
        QueryWrapper<VehicleToll> qw = new QueryWrapper<VehicleToll>();
        if(vehicleTollWeight!=null){
            qw.like("vehicle_toll_weight",vehicleTollWeight);
        }
        Page<VehicleToll> page1 = new Page<VehicleToll>(pageNo,pageSize);
//        equipmentService.page(page1, qw);的page是一个方法叫page
        Page page2 = vehicleTollService.page(page1, qw);
        Integer count = Math.toIntExact(page2.getTotal());
        JsonMassage<List<VehicleToll>> json = new JsonMassage<List<VehicleToll>>(200,"ok",count,page2.getRecords());

        return json;
    }


    //    删除数据
    @RequestMapping(value = "shanchu")
    public ResultJson shanchu(VehicleToll vehicleToll){
        System.out.println("进入shanchu**********************");
        System.out.println("equipment========================"+vehicleToll);
        System.out.println("id============================="+vehicleToll.getVehicleTollId());
        int i = vehicleTollMapper.deleteById(vehicleToll);
        ResultJson resultJson = new ResultJson(i);
        resultJson.setCode(200);
        System.out.println("受影响行数==================================================================================================="+i);
        return resultJson;
    }



    //    新增数据
    @RequestMapping(value = "tianjia")
    public ResultJson tianjia(VehicleToll vehicleToll){
        System.out.println("进入tianjia");
        System.out.println("equipment========================"+vehicleToll);
        System.out.println("id============================="+vehicleToll.getVehicleTollId());
        int i = vehicleTollMapper.insert(vehicleToll);
        ResultJson resultJson = new ResultJson(i);
        resultJson.setCode(200);
        System.out.println("受影响行数==================================================================================================="+i);
        return resultJson;
    }

    //修改单个对象
    @RequestMapping(value = "/xiugai")
    public ResultJson xiugaiid(VehicleToll vehicleToll){
        System.out.println("进入xiugai============");
        System.out.println("equipment========================"+vehicleToll);
        System.out.println("id============================="+vehicleToll.getVehicleTollId());
        int i = vehicleTollMapper.updateById(vehicleToll);
        ResultJson resultJson = new ResultJson(i);
        resultJson.setCode(200);
        System.out.println("");
        return resultJson;
    }

    //    //查询单个对象
    @RequestMapping(value = "/chaxunid")
    public JsonMassage<VehicleToll> chaxunid(Integer id){
        System.out.println("进入chaxunid===========================================");
        VehicleToll vehicleToll = vehicleTollMapper.selectById(id);
        System.out.println("id=========="+vehicleToll.getVehicleTollId());
        JsonMassage<VehicleToll> json = new JsonMassage<VehicleToll>(200,"ok",1,vehicleToll);
        return json;
    }


















}

