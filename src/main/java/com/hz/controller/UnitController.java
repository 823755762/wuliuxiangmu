package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.mapper.UnitMapper;
import com.hz.pojo.Unit;
import com.hz.service.UnitService;
import com.hz.utils.JsonMassage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping("/unit")
public class UnitController {
    @Autowired
    private UnitMapper unitMapper;
    @Autowired
    private UnitService unitService;

    /**
     * VUE 调用 分页查询  多条件
     * @return
     */
    /**
     * 分页查询  多条件
     * @param pageNo  当前页
     * @param pageSize 每页显示条数
     * @return
     */

    @RequestMapping("/unitlike")
    public JsonMassage<List<Unit>> unitlike(
          @RequestParam(value = "pageNo",defaultValue = "1")
            Integer pageNo,
          @RequestParam(value = "pageSize",defaultValue = "10")
            Integer pageSize,

          String unitName
    ){

        QueryWrapper<Unit> queryWrapper = new QueryWrapper<Unit>();
        if(unitName !=null){
            queryWrapper.like("unit_name",unitName);
        }

        Page<Unit> page = new Page<>(pageNo,pageSize);

        Page<Unit> page1 = unitService.page(page,queryWrapper);

        JsonMassage<List<Unit>> jsonMassage = new JsonMassage<List<Unit>>
                (200,"ok",Math.toIntExact(page.getTotal()),page1.getRecords());

        return  jsonMassage;


    }



    //添加
    @RequestMapping("addunit")
    @ResponseBody
    public JsonMassage add(Unit unit){
        int i = unitMapper.insert(unit);
        JsonMassage jsonMassage = new JsonMassage<>();
        jsonMassage.setCode(200);
        jsonMassage.setMsg("ok");
        jsonMassage.setDataCount(1);
        jsonMassage.setData(i);
        return jsonMassage;
    }


    //查询
    @RequestMapping("/findid")
    @ResponseBody
    public  JsonMassage<Unit> findid(Integer id){

     Unit unit =   unitMapper.selectById(id);

     JsonMassage<Unit> jsonMassage = new JsonMassage<>();
     jsonMassage.setData(unit);
     jsonMassage.setMsg("ok");
     jsonMassage.setDataCount(1);
     jsonMassage.setCode(200);
     return jsonMassage;
    }


    //删除
    @RequestMapping("/deleteid")
    @ResponseBody
    public  JsonMassage<Unit> deletebyid(Integer unitId){
        int i = unitMapper.deleteById(unitId);
        JsonMassage jsonMassage = new JsonMassage<>();
        jsonMassage.setData(i);
        jsonMassage.setMsg("ok");
        jsonMassage.setDataCount(1);
        jsonMassage.setCode(200);
        return jsonMassage;
    }

    //修改
    @RequestMapping("/updateunit")
    public  JsonMassage updatebyid(Unit unit){
        //获取时间
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String format = dtf.format(now);    //  2022-04-27 15:46:30
        unit.setUpdateTime(format);
        int i = unitMapper.updateById(unit);
        JsonMassage jsonMassage = new JsonMassage();
        jsonMassage.setCode(200);
        jsonMassage.setDataCount(i);
        jsonMassage.setData(i);
        jsonMassage.setMsg("ok");
        return  jsonMassage;

    }


    @RequestMapping("/unitAll")
    public JsonMassage<List<Unit>> unitAll(){
        List<Unit> list = unitService.list();
        JsonMassage<List<Unit>> jsonMassage = new JsonMassage<List<Unit>>(200,"ok",null,list);
        return jsonMassage;
    }


}

