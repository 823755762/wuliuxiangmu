package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.pojo.VehicleSafety;
import com.hz.service.VehicleSafetyService;
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
@RequestMapping("/vehicleSafety")
public class VehicleSafetyController {
    @Autowired
    private VehicleSafetyService vehicleSafetyService;


    @RequestMapping("/VehicleSafetyList")
    @ResponseBody
    public JsonMassage<List<VehicleSafety>> VehicleSafetyList(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            String waybillInfoId,
            String start_time,
            String end_time
    ) {
        QueryWrapper<VehicleSafety> queryWrap = new QueryWrapper<VehicleSafety>();
        if (waybillInfoId != null) {
            queryWrap.like("waybill_info_id", waybillInfoId);
        }
        if (start_time != null) {
            queryWrap.apply("date_format(create_time,'%Y-%m-%d') >= date_format( '" + start_time + " ','%Y-%m-%d')");
        }
        if (end_time != null) {
            queryWrap.apply("date_format(create_time,'%Y-%m-%d') <= date_format( '" + end_time + " ','%Y-%m-%d')");
        }
        queryWrap.orderByDesc("create_time");
        Page<VehicleSafety> page = new Page<VehicleSafety>(pageNo, pageSize);
        Page<VehicleSafety> list = vehicleSafetyService.page(page, queryWrap);
        JsonMassage<List<VehicleSafety>> jsonMassage = new JsonMassage<List<VehicleSafety>>(200, "ok", Math.toIntExact(page.getTotal()), list.getRecords());
        return jsonMassage;

    }

}

