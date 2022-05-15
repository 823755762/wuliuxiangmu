package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.mapper.EquipmentFireMapper;
import com.hz.mapper.EquipmentLogMapper;
import com.hz.pojo.Equipment;
import com.hz.pojo.EquipmentFire;
import com.hz.pojo.EquipmentLog;
import com.hz.service.EquipmentFireService;
import com.hz.service.EquipmentLogService;
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
@RequestMapping("/equipment-log")
public class EquipmentLogController {

    @Autowired
    private EquipmentLogService equipmentLogService;

    @Autowired
    private EquipmentLogMapper equipmentLogMapper;








//    //多表
//    @RequestMapping(value = "duobiao")
//    public JsonMassage<EquipmentFire> duobiao(){
//        System.out.println("duobiao===========================================");
//        EquipmentLog equipmentLog = equipmentLogMapper
//        JsonMassage<EquipmentFire> json = new JsonMassage<EquipmentFire>(200,"ok",1,equipmentLog);
//        return json;
//    }


    //    删除数据
    @RequestMapping(value = "shanchu")
    public ResultJson shanchu(EquipmentLog equipmentLog){
        System.out.println("进入shanchu**********************");
        int i = equipmentLogMapper.deleteById(equipmentLog);
        ResultJson resultJson = new ResultJson(i);
        resultJson.setCode(200);
        System.out.println("受影响行数==================================================================================================="+i);
        return resultJson;
    }

    //    新增数据
    @RequestMapping(value = "tianjia")
    public ResultJson tianjia(EquipmentLog equipmentLog){
        System.out.println("进入tianjiattttttttttttttttttt");
        int i = equipmentLogMapper.insert(equipmentLog);
        ResultJson resultJson = new ResultJson(i);
        resultJson.setCode(200);
        System.out.println("受影响行数==================================================================================================="+i);
        return resultJson;
    }

    //修改单个对象
    @RequestMapping(value = "/xiugai")
    public ResultJson xiugaiid(EquipmentLog equipmentLog){
        System.out.println("进入xiugai============");
        System.out.println("equipment========================"+equipmentLog);
        System.out.println("id============================="+equipmentLog.getEquipmentLogId());
        int i = equipmentLogMapper.updateById(equipmentLog);
        ResultJson resultJson = new ResultJson(i);
        resultJson.setCode(200);
        return resultJson;
    }

    //    //查询单个对象
    @RequestMapping(value = "/chaxunid")
    public JsonMassage<EquipmentLog> chaxunid(Integer id){
        System.out.println("进入chaxunid===========================================");
        EquipmentLog equipmentLog = equipmentLogMapper.selectById(id);
        System.out.println("id================================="+id);
        JsonMassage<EquipmentLog> json = new JsonMassage<EquipmentLog>(200,"ok",1,equipmentLog);
        return json;
    }


    @RequestMapping(value = "/chaxun")
    public JsonMassage<List<EquipmentLog>> getList(
            //defaultValue=当前页  defaultValue==每页条数
            @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
            String equipmentId,
            String createTime,
            String employeeId
    ) {
        System.out.println("************************************************************");
        //直接调用父类的baseMapper对象方法查询并返回数据
        QueryWrapper qw = new QueryWrapper();
//        如果equipmentFireName不是null就查询数据，是null的话默认搜索全部
        if (equipmentId!=null){
            //equipment_fire_name是数据库名字
            System.out.println("进入iffffffffffffffffffff");
            qw.eq("equipment_id",equipmentId);
        }
        if ("" != createTime && createTime != null) {
            qw.apply("date_format(create_time,'%Y-%m-%d') = date_format( '" + createTime + " ','%Y-%m-%d')");

        }
        if (employeeId!=null){
            qw.eq("employee_id",employeeId);
        }
        qw.orderByDesc("create_time");
        Page<EquipmentLog> page1 = new Page<EquipmentLog>(pageNo,pageSize);
//        equipmentFireService.page(page1, qw);的page是一个方法叫page
        Page page2 = equipmentLogService.page(page1,qw);
        Integer count = Math.toIntExact(page2.getTotal());
        JsonMassage<List<EquipmentLog>>  json = new JsonMassage<List<EquipmentLog>>(200,"ok",count,page2.getRecords());
        System.out.println("page2==========="+page2.getRecords());
        return json;
    }













}

