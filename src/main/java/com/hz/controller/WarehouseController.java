package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.hz.mapper.WarehouseMapper;
import com.hz.pojo.Warehouse;
import com.hz.service.WarehouseService;
import com.hz.utils.JsonMassage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseMapper warehouseMapper;
    @Autowired
    private WarehouseService warehouseService;

    /**
     * VUE 调用 分页查询  多条件
     * @return
     */
    @RequestMapping(value = "/warehousePage",method = RequestMethod.GET)
    @ResponseBody
    public JsonMassage<List<Warehouse>> selectPageList(
            @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
            @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize,
            Integer warehouseType,
            String warehouseNumber,
            Integer warehouseState
    ) {
        Page<Warehouse> page = new Page<Warehouse>(pageNo, pageSize);
        QueryWrapper<Warehouse> queryWrap = new QueryWrapper<Warehouse>();
        queryWrap.eq("warehouse_type", warehouseType)
                .or().eq("warehouse_number",warehouseNumber)
                .or().eq("warehouse_state",warehouseState);
        if ("正常".equals(warehouseState)) {
            warehouseState=1;
            System.out.println("warehouseState===="+warehouseState);
        }else if ("已满".equals(warehouseState)) {
            warehouseState=2;
        }else if ("维修".equals(warehouseState)) {
            warehouseState=3;
        }
        if (warehouseType != null || warehouseNumber != null || warehouseState != null){
            warehouseMapper.selectPage(page,queryWrap);// 输出page对象分页查询信息
        }else {
            warehouseMapper.selectPage(page, null);
        }

        Integer total = Math.toIntExact(page.getTotal());
        List<Warehouse> records = page.getRecords();
        JsonMassage<List<Warehouse>> jsonMassage = new JsonMassage<List<Warehouse>>();
        System.out.println("当前页：" + page.getCurrent());
        jsonMassage.setCode(200);
        jsonMassage.setMsg("请求成功");
        jsonMassage.setData(records);
        jsonMassage.setDataCount(total);
        return jsonMassage;
    }
    /**
     * 增加数据
     */
    @RequestMapping(value = "/warehouseInsert",method = RequestMethod.GET)
    @ResponseBody
    public JsonMassage<String> WaybillInfoInsert(Warehouse warehouse){


        int insert = warehouseMapper.insert(warehouse);
        JsonMassage<String> jsonMassage = new JsonMassage<String>();
        if(insert != 0){
            jsonMassage.setCode(200);
        }else{
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
    @RequestMapping(value = "/warehouseUpd",method = RequestMethod.POST)
    @ResponseBody
    public JsonMassage<String> WaybillInfoUpd(Warehouse warehouse){

        boolean insert = warehouseService.updateById(warehouse);
        JsonMassage<String> jsonMassage = new JsonMassage<String>();
        if(insert){
            jsonMassage.setCode(200);
        }else{
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
    @RequestMapping(value = "/warehouseById",method = RequestMethod.GET)
    @ResponseBody
    public JsonMassage<Warehouse> WaybillInfoById(Warehouse warehouse){
        //添加条件
        QueryWrapper<Warehouse> queryWrapper = new QueryWrapper<Warehouse>();
        queryWrapper.eq("warehouse_id", warehouse);
        //查询指定条件的数据
        Warehouse waybil =  warehouseService.getById(warehouse);
        JsonMassage<Warehouse> jsonMassage = new JsonMassage<Warehouse>();
        jsonMassage.setCode(200);
        jsonMassage.setMsg("请求成功");
        jsonMassage.setData(waybil);
        jsonMassage.setDataCount(null);
        return jsonMassage;
    }
    /**
     * 删除数据
     */
    @RequestMapping(value = "/warehouseDelById",method = RequestMethod.POST)
    @ResponseBody
    public JsonMassage WaybillInfoDelById(Integer warehouseId){
        int i = warehouseMapper.deleteById(warehouseId);
        JsonMassage jsonMap = new JsonMassage<>(200,"请求成功",null,null);
        return jsonMap;

    }
}

