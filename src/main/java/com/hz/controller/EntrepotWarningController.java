package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.mapper.EntrepotWarningMapper;
import com.hz.mapper.WarehouseMapper;
import com.hz.pojo.EntrepotWarning;
import com.hz.pojo.User;
import com.hz.pojo.Warehouse;
import com.hz.service.EntrepotWarningService;
import com.hz.service.WarehouseService;
import com.hz.utils.JsonMassage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author UserG
 * @since 2022-04-26
 */
@RestController
@RequestMapping("/entrepotWarning")
public class EntrepotWarningController {
    @Autowired
    private EntrepotWarningMapper entrepotWarningMapper;
    @Autowired
    private EntrepotWarningService entrepotWarningService;

    /**
     * VUE 调用 分页查询  多条件
     * @return
     */
    @RequestMapping(value = "/entwarPage",method = RequestMethod.GET)
    @ResponseBody
    public JsonMassage<List<EntrepotWarning>> selectPageList(
            @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
            @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize,
            String equipmentNumber,
            String entrepotDispose,
            String startTime,
            String endTime
    ) {
        QueryWrapper<EntrepotWarning> queryWrap = new QueryWrapper<EntrepotWarning>();
       if (equipmentNumber != null){
           queryWrap.like("equipment_number", equipmentNumber);
       }
       if (entrepotDispose != null) {
           queryWrap.like("entrepot_dispose", entrepotDispose);
       }
        if ("" != startTime && startTime != null) {
            queryWrap.apply("date_format(create_time,'%Y-%m-%d') >= date_format( '" + startTime + " ','%Y-%m-%d')");
        }
        if ("" != endTime && endTime != null) {
            queryWrap.apply("date_format(create_time,'%Y-%m-%d') <= date_format( '" + endTime + " ','%Y-%m-%d')");
        }
        queryWrap.orderByDesc("create_time");
        Page<EntrepotWarning> page = new Page<EntrepotWarning>(pageNo, pageSize);
        Page<EntrepotWarning> list = entrepotWarningService.page(page, queryWrap);
        JsonMassage<List<EntrepotWarning>> jsonMassage = new JsonMassage<List<EntrepotWarning>>(200, "ok", Math.toIntExact(page.getTotal()), list.getRecords());
        return jsonMassage;

    }
    /**
     * 增加数据
     */
    @RequestMapping(value = "/entwarInsert",method = RequestMethod.GET)
    @ResponseBody
    public JsonMassage<String> WaybillInfoInsert(EntrepotWarning entrepotWarning){
        int insert = entrepotWarningMapper.insert(entrepotWarning);
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
    @RequestMapping(value = "/entwarUpd",method = RequestMethod.POST)
    @ResponseBody
    public JsonMassage<String> WaybillInfoUpd(EntrepotWarning entrepotWarning){
        boolean insert = entrepotWarningService.updateById(entrepotWarning);
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
    @RequestMapping(value = "/entwarById",method = RequestMethod.GET)
    @ResponseBody
    public JsonMassage<EntrepotWarning> WaybillInfoById(EntrepotWarning entrepotWarning){
        //添加条件
        QueryWrapper<EntrepotWarning> queryWrapper = new QueryWrapper<EntrepotWarning>();
        queryWrapper.eq("entrepot_id", entrepotWarning);
        //查询指定条件的数据
        EntrepotWarning waybil =  entrepotWarningService.getById(entrepotWarning);
        JsonMassage<EntrepotWarning> jsonMassage = new JsonMassage<EntrepotWarning>(200,"请求成功",null,waybil);
        return jsonMassage;
    }
    /**
     * 删除数据
     */
    @RequestMapping(value = "/entwarDelById",method = RequestMethod.POST)
    @ResponseBody
    public JsonMassage WaybillInfoDelById(Integer entrepotId){
        int i = entrepotWarningMapper.deleteById(entrepotId);
        JsonMassage jsonMap = new JsonMassage<>(200,"请求成功",null,null);
        return jsonMap;

    }
}

