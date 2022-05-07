package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.pojo.VehicleType;
import com.hz.service.VehicleTypeService;
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
@RequestMapping("/vehicleType")
public class VehicleTypeController {

    @Autowired
    private VehicleTypeService vehicleTypeService;

    /**
     * 查询所有信息返回到页面
     *
     * @return
     */
    @RequestMapping("/listVehicleType")
    @ResponseBody
    public JsonMassage<List<VehicleType>> listVehicleType(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            String vehicleTypeName
    ) {
        QueryWrapper<VehicleType> queryWrapper = new QueryWrapper<VehicleType>();
        if (vehicleTypeName != null) {
            queryWrapper.like("vehicle_type_name", vehicleTypeName);
        }
        Page<VehicleType> page = new Page(pageNo,pageSize);
        Page<VehicleType> typePage = vehicleTypeService.page(page, queryWrapper);

        JsonMassage<List<VehicleType>> jsonMassage = new JsonMassage<List<VehicleType>>(200, "ok", Math.toIntExact(page.getTotal()), typePage.getRecords());

        return jsonMassage;
    }

    /**
     * 新增车辆类型信息
     */
    @RequestMapping("/saveVehicleType")
    @ResponseBody
    public JsonMassage<String> saveVehicleType(VehicleType vehicleType) {
        boolean save = vehicleTypeService.save(vehicleType);
        JsonMassage<String> jsonMassage = new JsonMassage<String>(200, "ok", null, null);
        return jsonMassage;
    }

    /**
     * 删除车辆类型信息
     */

    @RequestMapping("/removeById")
    @ResponseBody
    public JsonMassage<String> removeById(Integer vehicleTypeId) {
        boolean b = vehicleTypeService.removeById(vehicleTypeId);
        JsonMassage<String> jsonMassage = new JsonMassage<String>(200, "ok", null, null);
        return jsonMassage;
    }


}

