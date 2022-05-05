package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.mapper.OrderStatusRecordMapper;
import com.hz.mapper.VehicleTollMapper;
import com.hz.pojo.OrderStatusRecord;
import com.hz.pojo.VehicleToll;
import com.hz.service.OrderStatusRecordService;
import com.hz.service.VehicleTollService;
import com.hz.utils.JsonMassage;
import com.hz.utils.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/order-status-record")
public class OrderStatusRecordController {

    @Autowired
    private OrderStatusRecordMapper orderStatusRecordMapper;

    @Autowired
    private OrderStatusRecordService orderStatusRecordService;

    @RequestMapping("chaxun")
    public JsonMassage<List<OrderStatusRecord>> chaxun(
            @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
            String recordId,
            String userId
    ){
        System.out.println("进入chaxun");
        QueryWrapper<OrderStatusRecord> qw = new QueryWrapper<OrderStatusRecord>();
        if(recordId!=null){
            qw.eq("record_id",recordId);
        }
        if(userId!=null){
            qw.eq("user_id",userId);
        }
        Page<OrderStatusRecord> page1 = new Page<OrderStatusRecord>(pageNo,pageSize);
//        equipmentService.page(page1, qw);的page是一个方法叫page
        Page page2 = orderStatusRecordService.page(page1, qw);
        Integer count = Math.toIntExact(page2.getTotal());
        System.out.println("count==========="+count);
        JsonMassage<List<OrderStatusRecord>> json = new JsonMassage<List<OrderStatusRecord>>(200,"ok",count,page2.getRecords());

        return json;
    }


    //    删除数据
    @RequestMapping(value = "shanchu")
    public ResultJson shanchu(OrderStatusRecord orderStatusRecord){
        System.out.println("进入shanchu**********************");
        System.out.println("equipment========================"+orderStatusRecord);
        System.out.println("id============================="+orderStatusRecord.getRecordId());
        int i = orderStatusRecordMapper.deleteById(orderStatusRecord);
        ResultJson resultJson = new ResultJson(i);
        resultJson.setCode(200);
        System.out.println("受影响行数==================================================================================================="+i);
        return resultJson;
    }



    //    新增数据
    @RequestMapping(value = "tianjia")
    public ResultJson tianjia(OrderStatusRecord orderStatusRecord){
        System.out.println("进入tianjia");
        System.out.println("equipment========================"+orderStatusRecord);
        System.out.println("id============================="+orderStatusRecord.getRecordId());
        //获取时间
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        //  2022-04-27 15:46:30
        String format = dtf.format(now);
        orderStatusRecord.setTime(format);
        int i = orderStatusRecordMapper.insert(orderStatusRecord);

        ResultJson resultJson = new ResultJson(i);
        resultJson.setCode(200);
        System.out.println("受影响行数==================================================================================================="+i);
        return resultJson;
    }

    //修改单个对象
    @RequestMapping(value = "/xiugai")
    public ResultJson xiugaiid(OrderStatusRecord orderStatusRecord){
        System.out.println("进入xiugai============");
        System.out.println("equipment========================"+orderStatusRecord);
        System.out.println("id============================="+orderStatusRecord.getRecordId());
        int i = orderStatusRecordMapper.updateById(orderStatusRecord);
        ResultJson resultJson = new ResultJson(i);
        resultJson.setCode(200);

        return resultJson;
    }

    //    //查询单个对象
    @RequestMapping(value = "/chaxunid")
    public JsonMassage<OrderStatusRecord> chaxunid(Integer id){
        System.out.println("进入chaxunid===========================================");
        OrderStatusRecord orderStatusRecord = orderStatusRecordMapper.selectById(id);
        System.out.println("id=========="+orderStatusRecord.getRecordId());
        JsonMassage<OrderStatusRecord> json = new JsonMassage<OrderStatusRecord>(200,"ok",1,orderStatusRecord);
        return json;
    }













}

