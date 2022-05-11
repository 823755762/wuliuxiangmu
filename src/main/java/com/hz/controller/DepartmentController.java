package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.mapper.DepartmentMapper;
import com.hz.pojo.Department;
import com.hz.service.DepartmentService;
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
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DepartmentMapper departmentMapper;

    @RequestMapping("/findAll")
    @ResponseBody
    public JsonMassage<List<Department>> findAll(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            String departmentName, Integer departmentId) {
        Page<Department> page = new Page<Department>(pageNo, pageSize);
        QueryWrapper<Department> queryWrap = new QueryWrapper<>();
        queryWrap.eq("department_id", departmentId).or().eq("department_name", departmentName);
        if (departmentId != null || departmentName != null) {
            departmentMapper.selectPage(page, queryWrap);// 输出page对象分页查询信息
        } else {
            departmentMapper.selectPage(page, null);
        }
        queryWrap.orderByDesc("department_id");
        Integer total = Math.toIntExact(page.getTotal());
        List<Department> records = page.getRecords();
        JsonMassage<List<Department>> jsonMassage = new JsonMassage<>();
        System.out.println("当前页：" + page.getCurrent());
        jsonMassage.setCode(200);
        jsonMassage.setDataCount(total);
        jsonMassage.setData(records);
        jsonMassage.setMsg("ok");
        return jsonMassage;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JsonMassage delete(Integer id) {
        int i = departmentMapper.deleteById(id);
        JsonMassage jsonMassage = new JsonMassage<>();
        jsonMassage.setMsg("ok");
        jsonMassage.setCode(200);
        jsonMassage.setData(i);
        jsonMassage.setDataCount(i);
        return jsonMassage;
    }

    @RequestMapping("/add")
    @ResponseBody
    public JsonMassage add(int name) {
        Department department = new Department();
        if (name == 0) {

            department.setDepartmentName("请选择");
        } else if (name == 1) {
            department.setDepartmentName("财务部门");
        } else if (name == 2) {
            department.setDepartmentName("人事部门");
        } else if (name == 3) {

            department.setDepartmentName("技术部门");
        }


        int i = departmentMapper.insert(department);
        JsonMassage jsonMassage = new JsonMassage<>();
        jsonMassage.setMsg("ok");
        jsonMassage.setCode(200);
        jsonMassage.setData(i);
        jsonMassage.setDataCount(i);

        return jsonMassage;
    }

    @RequestMapping("/update")
    @ResponseBody
    public JsonMassage update(int name) {
        Department department = new Department();
        if (name == 0) {
            department.setDepartmentName("请选择");
        } else if (name == 1) {
            department.setDepartmentName("财务部门");
        } else if (name == 2) {
            department.setDepartmentName("人事部门");
        } else if (name == 3) {
            department.setDepartmentName("技术部门");
        }

        int i = departmentMapper.updateById(department);
        JsonMassage jsonMassage = new JsonMassage<>();
        jsonMassage.setMsg("ok");
        jsonMassage.setCode(200);
        jsonMassage.setData(i);
        jsonMassage.setDataCount(i);
        return jsonMassage;
    }

    @RequestMapping("/findId")
    @ResponseBody
    public JsonMassage<Department> findId(Integer id) {
        Department department = departmentMapper.selectById(id);
        JsonMassage<Department> jsonMassage = new JsonMassage<Department>();
        jsonMassage.setMsg("ok");
        jsonMassage.setCode(200);
        jsonMassage.setData(department);
        jsonMassage.setDataCount(1);
        return jsonMassage;
    }

    @RequestMapping("/departmentList")
    public JsonMassage<List<Department>> departmentList() {
        List<Department> list = departmentService.list();
        JsonMassage<List<Department>> jsonMassage = new JsonMassage<List<Department>>(200, "ok", null, list);
        return jsonMassage;


    }


}

