package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.mapper.EmployeesMapper;
import com.hz.pojo.Employees;
import com.hz.service.EmployeesService;
import com.hz.utils.JsonMassage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeesMapper employeesMapper;
    @Autowired
    private EmployeesService employeesService;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    //  2022-04-27 15:46:30
    String nowTime = dtf.format(now);

    @RequestMapping("/findall")
    public JsonMassage<List<Employees>> findall(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            Integer employeesId, String employeesName
    ) {
        Page<Employees> page = new Page<>(pageNo, pageSize);
        QueryWrapper<Employees> wrapper = new QueryWrapper<>();
        wrapper.like("employees_id", employeesId).or().like("employees_name", employeesName);
        if (employeesId != null || employeesName != null) {
            employeesMapper.selectPage(page, wrapper);
        } else {
            employeesMapper.selectPage(page, null);
        }
        wrapper.orderByDesc("employees_id");
        Integer count = Math.toIntExact(page.getTotal());
        List<Employees> list = page.getRecords();
        JsonMassage<List<Employees>> jsonMassage = new JsonMassage<List<Employees>>();
        jsonMassage.setCode(200);
        jsonMassage.setMsg("ok");
        jsonMassage.setData(list);
        jsonMassage.setDataCount(count);
        return jsonMassage;
    }

    @RequestMapping("/upd")
    public JsonMassage update(Employees employees){
        employees.setCreateTime(nowTime);
        employees.setUpdateTime(nowTime);
        employees.setEmployeesCreateTime(nowTime);
        employees.setAdminId(1);
        int i=employeesMapper.updateById(employees);
        JsonMassage jsonMassage=new JsonMassage();
        jsonMassage.setDataCount(i);
        jsonMassage.setData(i);
        jsonMassage.setCode(200);
        jsonMassage.setMsg("ok");
        return jsonMassage;
    }

    @RequestMapping("/add")
    public JsonMassage add(Employees employees){
        employees.setCreateTime(nowTime);
        employees.setUpdateTime(nowTime);
        employees.setEmployeesCreateTime(nowTime);
        employees.setAdminId(1);
        int i=employeesMapper.insert(employees);
        JsonMassage jsonMassage=new JsonMassage<>();
        jsonMassage.setCode(200);
        jsonMassage.setMsg("ok");
        jsonMassage.setData(i);
        jsonMassage.setDataCount(i);
        return jsonMassage;
    }

    @RequestMapping("/delete")
    public JsonMassage delete(Integer employeesId){
        int i = employeesMapper.deleteById(employeesId);
        JsonMassage jsonMassage=new JsonMassage<>();
        jsonMassage.setCode(200);
        jsonMassage.setMsg("ok");
        jsonMassage.setData(i);
        jsonMassage.setDataCount(i);
        return jsonMassage;
    }

    @RequestMapping("/findbyid")
    public JsonMassage<Employees> fidnbyid(Integer employeesId){
        Employees employees= employeesMapper.selectById(employeesId);
        JsonMassage<Employees> jsonMassage=new JsonMassage<>();
        jsonMassage.setCode(200);
        jsonMassage.setMsg("ok");
        jsonMassage.setData(employees);
        jsonMassage.setDataCount(1);
        return jsonMassage;
    }
    @RequestMapping("/employeesList")
    public JsonMassage<List<Employees>> employeesList() {
        List<Employees> list = employeesService.list();
        JsonMassage<List<Employees>> jsonMas = new JsonMassage<List<Employees>>(200, "ok", null, list);
        return jsonMas;
    }

}