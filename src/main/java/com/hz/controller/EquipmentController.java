package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.mapper.EquipmentMapper;
import com.hz.pojo.Equipment;
import com.hz.service.EquipmentService;
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
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private EquipmentMapper equipmentMapper;


    //    删除数据
    @RequestMapping(value = "shanchu")
    public ResultJson shanchu(Equipment equipment){

        int i = equipmentMapper.deleteById(equipment);
        ResultJson resultJson = new ResultJson(i);
        resultJson.setCode(200);
        return resultJson;
    }



    //    新增数据
    @RequestMapping(value = "tianjia")
    public ResultJson tianjia(Equipment equipment){
        int i = equipmentMapper.insert(equipment);
        ResultJson resultJson = new ResultJson(i);
        resultJson.setCode(200);
        return resultJson;
    }

    //修改单个对象
    @RequestMapping(value = "/xiugai")
    public ResultJson xiugaiid(Equipment equipment){
        int i = equipmentMapper.updateById(equipment);
        ResultJson resultJson = new ResultJson(i);
        resultJson.setCode(200);
        return resultJson;
    }

    //    //查询单个对象
    @RequestMapping(value = "/chaxunid")
    public JsonMassage<Equipment> chaxunid(Integer id){
        Equipment equipment = equipmentMapper.selectById(id);
        System.out.println("id=========="+equipment.getEquipmentId());
        JsonMassage<Equipment> json = new JsonMassage<Equipment>(200,"ok",1,equipment);
        return json;
    }


    @RequestMapping(value = "/chaxun")
    public JsonMassage<List<Equipment>> getList(
            //defaultValue=当前页  defaultValue==每页条数
            @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
            String equipmentName,
            String equipmentId
    ) {

        //直接调用父类的baseMapper对象方法查询并返回数据
        QueryWrapper qw = new QueryWrapper();
//        如果equipmentFireName不是null就查询数据，是null的话默认搜索全部
        if (equipmentName!=null){
            //equipment_fire_name是数据库名字
            qw.like("equipment_name",equipmentName);
        }
        if (equipmentId!=null){
            qw.eq("equipment_id",equipmentId);
        }
        Page<Equipment> page1 = new Page<Equipment>(pageNo,pageSize);
//        equipmentService.page(page1, qw);的page是一个方法叫page
        Page page2 = equipmentService.page(page1, qw);
        Integer count = Math.toIntExact(page2.getTotal());
        JsonMassage<List<Equipment>>  json = new JsonMassage<List<Equipment>>(200,"ok",count,page2.getRecords());
        return json;
    }












}

