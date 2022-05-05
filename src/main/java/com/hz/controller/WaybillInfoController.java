package com.hz.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.mapper.WaybillInfoMapper;
import com.hz.pojo.EntrepotWarning;
import com.hz.pojo.User;
import com.hz.pojo.WaybillInfo;
import com.hz.service.WaybillInfoService;
import com.hz.service.impl.WaybillInfoServiceImpl;
import com.hz.utils.JsonMassage;
import jdk.nashorn.internal.ir.IdentNode;
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
@RequestMapping("/waybill-info")
public class WaybillInfoController {
    @Autowired
    private WaybillInfoMapper waybillInfoMapper;
    @Autowired
    private WaybillInfoServiceImpl waybillInfoService;
    /**
     * VUE 调用 分页查询  多条件
     * @return
     */
    @RequestMapping(value = "/waybill",method = RequestMethod.GET)
    @ResponseBody
    public JsonMassage<List<WaybillInfo>> selectPageList(
            @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
            @RequestParam(value="pageSize",defaultValue = "10") Integer pageSize,
            Integer orderId,
            Integer driverId
    ) {
        QueryWrapper<WaybillInfo> queryWrap = new QueryWrapper<>();
        if (orderId != null) {
            queryWrap.like("order_id", orderId);
        }
        if (driverId != null) {
            queryWrap.like("driver_id", driverId);
        }
        queryWrap.orderByDesc("create_time");
        Page<WaybillInfo> page = new Page<WaybillInfo>(pageNo, pageSize);
        Page<WaybillInfo> records = waybillInfoService.page(page, queryWrap);
        JsonMassage<List<WaybillInfo>> jsonMassage = new JsonMassage<List<WaybillInfo>>(200,"请求成功",Math.toIntExact(page.getTotal()),records.getRecords());
        return jsonMassage;
    }
    /**
     * 增加数据
     */
    @RequestMapping(value = "/waybillInfoInsert",method = RequestMethod.GET)
    @ResponseBody
    public JsonMassage<String> WaybillInfoInsert(WaybillInfo waybillInfo
    ){
       int insert = waybillInfoMapper.insert(waybillInfo);
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
    @RequestMapping(value = "/waybillInfoUpd",method = RequestMethod.POST)
    @ResponseBody
    public JsonMassage<String> WaybillInfoUpd(WaybillInfo waybillInfo){

        boolean insert = waybillInfoService.updateById(waybillInfo);
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
    @RequestMapping(value = "/waybillInfoById",method = RequestMethod.GET)
    @ResponseBody
    public JsonMassage<WaybillInfo> WaybillInfoById(WaybillInfo WaybillInfo){
        //添加条件
        QueryWrapper<WaybillInfo> queryWrapper = new QueryWrapper<WaybillInfo>();
        queryWrapper.eq("waybill_info_id", WaybillInfo);
        //查询指定条件的数据
        WaybillInfo waybil =  waybillInfoService.getById(WaybillInfo);
        JsonMassage<WaybillInfo> jsonMassage = new JsonMassage<WaybillInfo>(200,"请求成功",null,waybil);
        return jsonMassage;
    }
    /**
     * 删除数据
     */
    @RequestMapping(value = "/waybillInfoDelById",method = RequestMethod.POST)
    @ResponseBody
    public JsonMassage WaybillInfoDelById(Integer waybillInfoId){
        int i = waybillInfoMapper.deleteById(waybillInfoId);
        JsonMassage jsonMap = new JsonMassage<>(200,"请求成功",null,null);
        return jsonMap;

    }
}

