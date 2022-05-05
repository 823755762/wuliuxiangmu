package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.mapper.WarehouseLocationMapper;
import com.hz.mapper.WarehouseMapper;
import com.hz.pojo.EntrepotWarning;
import com.hz.pojo.Warehouse;
import com.hz.pojo.WarehouseLocation;
import com.hz.service.WarehouseLocationService;
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
@RequestMapping("/warehouselocation")
public class WarehouseLocationController {
    @Autowired
    private WarehouseLocationMapper warehouseLocationMapper;
    @Autowired
    private WarehouseLocationService warehouseService;
    /**
     * VUE 调用 分页查询  多条件
     * @return
     */
    @RequestMapping(value = "/locationPage",method = RequestMethod.GET)
    @ResponseBody
    public JsonMassage<List<WarehouseLocation>> selectPageList(
            @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
            @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize,
            Integer WarehouseId,
            String warehouseLocationNumber,
            Integer warehouseLocationState

    ) {

        QueryWrapper<WarehouseLocation> queryWrap = new QueryWrapper<WarehouseLocation>();
        if (WarehouseId != null){
            queryWrap.like("warehouse_id", WarehouseId);
        }
        if (warehouseLocationNumber != null) {
            queryWrap.like("warehouse_location_number", warehouseLocationNumber);
        }
        if (warehouseLocationState != null) {
            queryWrap.like("warehouse_location_state", warehouseLocationState);
        }
        queryWrap.orderByDesc("create_time");
        Page<WarehouseLocation> page = new Page<WarehouseLocation>(pageNo, pageSize);
        Page<WarehouseLocation> list = warehouseService.page(page, queryWrap);
        Integer total = Math.toIntExact(page.getTotal());
        List<WarehouseLocation> records = page.getRecords();
        JsonMassage<List<WarehouseLocation>> jsonMassage = new JsonMassage<List<WarehouseLocation>>(200, "ok",total,records);
        return jsonMassage;
    }
    /**
     * 增加数据
     */
    @RequestMapping(value = "/locationInsert",method = RequestMethod.GET)
    @ResponseBody
    public JsonMassage<String> WaybillInfoInsert(WarehouseLocation warehouseLocation){
        int insert = warehouseLocationMapper.insert(warehouseLocation);
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
    @RequestMapping(value = "/locationUpd",method = RequestMethod.POST)
    @ResponseBody
    public JsonMassage<String> WaybillInfoUpd(WarehouseLocation warehouseLocation){

        boolean insert = warehouseService.updateById(warehouseLocation);
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
    @RequestMapping(value = "/locationById",method = RequestMethod.GET)
    @ResponseBody
    public JsonMassage<WarehouseLocation> WaybillInfoById(WarehouseLocation warehouseLocation){
        //添加条件
        QueryWrapper<WarehouseLocation> queryWrapper = new QueryWrapper<WarehouseLocation>();
        queryWrapper.eq("warehouse_location_id", warehouseLocation);
        //查询指定条件的数据
        WarehouseLocation waybil =  warehouseService.getById(warehouseLocation);
        JsonMassage<WarehouseLocation> jsonMassage = new JsonMassage<WarehouseLocation>(200,"请求成功",null,waybil);
        return jsonMassage;
    }
    /**
     * 删除数据
     */
    @RequestMapping(value = "/locationDelById",method = RequestMethod.POST)
    @ResponseBody
    public JsonMassage WaybillInfoDelById(Integer warehouseLocationId){
        int i = warehouseLocationMapper.deleteById(warehouseLocationId);
        JsonMassage jsonMap = new JsonMassage<>(200,"请求成功",null,null);
        return jsonMap;

    }
}

