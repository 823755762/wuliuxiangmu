package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.mapper.DriverMapper;
import com.hz.pojo.Department;
import com.hz.pojo.Driver;
import com.hz.utils.JsonMassage;
import com.hz.utils.ResultJson;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/driver")
public class DriverController {
    @Autowired
    private DriverMapper driverMapper;

    @RequestMapping("/findAll")
    public JsonMassage<List<Driver>> findAll(
            @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
            String driverName,Integer Idcard){
        Page<Driver> page = new Page<Driver>(pageNo, pageSize);
        QueryWrapper<Driver> queryWrap = new QueryWrapper<>();
        queryWrap.eq("driver_name", driverName).or().eq("driver_Idcard",Idcard);
        if (driverName != null || Idcard != null){
            driverMapper.selectPage(page,queryWrap);// 输出page对象分页查询信息
        }else {
            driverMapper.selectPage(page, null);
        }
        queryWrap.orderByDesc("driver_id");
        Integer total = Math.toIntExact(page.getTotal());
        List<Driver> records = page.getRecords();
        JsonMassage<List<Driver>> jsonMassage=new JsonMassage<>();
        System.out.println("当前页：" + page.getCurrent());
        jsonMassage.setDataCount(total);
        jsonMassage.setMsg("ok");
        jsonMassage.setData(records);
        jsonMassage.setCode(200);
        return jsonMassage;
    }

    @RequestMapping("/updatedriver")
    public JsonMassage updatedriver(Driver driver){
        int i=driverMapper.updateById(driver);
        System.out.println("受影响行数+======="+i);
        JsonMassage resultJson=new JsonMassage();
        resultJson.setCode(200);
        resultJson.setMsg("ok");
         return resultJson;
    }

    @RequestMapping("/adddirver")
    public JsonMassage adddriver(String driverName, String driverIdcard,Integer driverState,String driverPhone){
        Driver driver=new Driver();
        driver.setDriverName(driverName);
        driver.setDriverIdcard(driverIdcard);
        driver.setDriverState(driverState);
        driver.setDriverPhone(driverPhone);
        int i=driverMapper.insert(driver);
        JsonMassage resultJson=new JsonMassage();
        resultJson.setCode(200);
        resultJson.setMsg("ok");
        return resultJson;
    }

    @RequestMapping("/deletedriver")
    public JsonMassage deletedriver(int id){

        int i=driverMapper.deleteById(id);
        JsonMassage jsonMassage=new JsonMassage<>();
        jsonMassage.setDataCount(i);
        jsonMassage.setCode(200);
        jsonMassage.setData(i);
        jsonMassage.setMsg("ok");
        return jsonMassage;
    }
    @RequestMapping("/driverid")
    public JsonMassage<Driver> findByid(Integer id){
        Driver driver=driverMapper.selectById(id);
        JsonMassage<Driver> jsonMassage=new JsonMassage<>();
        jsonMassage.setCode(200);
        jsonMassage.setMsg("ok");
        jsonMassage.setData(driver);
        jsonMassage.setDataCount(1);
        return  jsonMassage;
    }
}

