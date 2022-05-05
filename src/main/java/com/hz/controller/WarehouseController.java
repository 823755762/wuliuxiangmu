package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.hz.mapper.WarehouseMapper;
import com.hz.pojo.Warehouse;
import com.hz.pojo.WarehouseLocation;
import com.hz.service.WarehouseService;
import com.hz.utils.JsonMassage;
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
        QueryWrapper<Warehouse> queryWrap = new QueryWrapper<Warehouse>();
        if (warehouseType != null){
            queryWrap.like("warehouse_type", warehouseType);
        }
        if (warehouseNumber != null) {
            queryWrap.like("warehouse_number", warehouseNumber);
        }
        if (warehouseState != null) {
            queryWrap.like("warehouse_state", warehouseState);
        }
        queryWrap.orderByDesc("warehouse_id");
        Page<Warehouse> page = new Page<Warehouse>(pageNo, pageSize);
        Page<Warehouse> list = warehouseService.page(page, queryWrap);

        Integer total = Math.toIntExact(page.getTotal());
        List<Warehouse> records = page.getRecords();
        JsonMassage<List<Warehouse>> jsonMassage = new JsonMassage<List<Warehouse>>(200,"请求成功",total,records);
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
        JsonMassage<Warehouse> jsonMassage = new JsonMassage<Warehouse>(200,"请求成功",null,waybil);
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

