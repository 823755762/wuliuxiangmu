package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.mapper.EquipmentFireMapper;
import com.hz.pojo.EquipmentFire;
import com.hz.service.EquipmentFireService;
import com.hz.utils.JsonMassage;
import com.hz.utils.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/equipment-fire")
public class EquipmentFireController {

    @Autowired
    private EquipmentFireService equipmentFireService;

    @Autowired
    private EquipmentFireMapper equipmentFireMapper;


    //    删除数据
    @RequestMapping(value = "shanchu")
    public ResultJson shanchu(EquipmentFire equipmentFire){
        System.out.println("进入shanchu**********************");

        int i = equipmentFireMapper.deleteById(equipmentFire);
        ResultJson resultJson = new ResultJson(i);
        resultJson.setCode(200);
        System.out.println("受影响行数==================================================================================================="+i);
        return resultJson;
    }

    //    新增数据
    @RequestMapping(value = "tianjia")
    public ResultJson tianjia(EquipmentFire equipmentFire){
        System.out.println("进入tianjia");
        int i = equipmentFireMapper.insert(equipmentFire);
        ResultJson resultJson = new ResultJson(i);
        resultJson.setCode(200);
        System.out.println("受影响行数==================================================================================================="+i);
        return resultJson;
    }

    //修改单个对象
    @RequestMapping(value = "/xiugai")
    public ResultJson xiugaiid(EquipmentFire equipmentFire){
        System.out.println("进入xiugai============");
        System.out.println("equipment========================"+equipmentFire);
        System.out.println("id============================="+equipmentFire.getEquipmentFireId());
        int i = equipmentFireMapper.updateById(equipmentFire);
        ResultJson resultJson = new ResultJson(i);
        resultJson.setCode(200);
        return resultJson;
    }

    //    //查询单个对象
    @RequestMapping(value = "/chaxunid")
    public JsonMassage<EquipmentFire> chaxunid(Integer id){
        System.out.println("进入chaxunid===========================================");
        EquipmentFire equipmentFire = equipmentFireMapper.selectById(id);
        System.out.println("id=========="+equipmentFire.getEquipmentFireId());
        JsonMassage<EquipmentFire> json = new JsonMassage<EquipmentFire>(200,"ok",1,equipmentFire);
        return json;
    }


    @RequestMapping(value = "/chaxun")
    @ResponseBody
    public JsonMassage<List<EquipmentFire>> getList(
            //defaultValue=当前页  defaultValue==每页条数
            @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
            String equipmentFireName,
             String equipmentFireId,
            String createTime,
            String updateTime
    ) {
        //直接调用父类的baseMapper对象方法查询并返回数据
        QueryWrapper qw = new QueryWrapper();
//        如果equipmentFireName不是null就查询数据，是null的话默认搜索全部
        if (equipmentFireName!=null){
            //equipment_fire_name是数据库名字
            qw.like("equipment_fire_name",equipmentFireName);
        }
        if (equipmentFireId!=null){
            qw.eq("equipment_fire_id",equipmentFireId);
        }
        if ("" != createTime && createTime != null) {
            qw.apply("date_format(create_time,'%Y-%m-%d') >= date_format( '" + createTime + " ','%Y-%m-%d')");

        }
        if ("" != updateTime && updateTime != null) {
            qw.apply("date_format(update_time,'%Y-%m-%d') <= date_format( '" + updateTime + " ','%Y-%m-%d')");

        }
        qw.orderByDesc("create_time");
        Page <EquipmentFire> page1 = new Page<EquipmentFire>(pageNo,pageSize);
//        equipmentFireService.page(page1, qw);的page是一个方法叫page
        Page page2 = equipmentFireService.page(page1, qw);
        Integer count = Math.toIntExact(page1.getTotal());
        JsonMassage<List<EquipmentFire>>  json = new JsonMassage<List<EquipmentFire>>(200,"ok",count,page2.getRecords());
        System.out.println("page2==========="+page2.getRecords());
        return json;
    }

}

