package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.mapper.DepartmentMapper;
import com.hz.pojo.Department;
import com.hz.pojo.Driver;
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
 *  前端控制器
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
            @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
            String departmentName,Integer departmentId){
        QueryWrapper<Department> wrapper=new QueryWrapper<Department>();
        if(departmentName!=null){
            wrapper.like("department_Name",departmentName);
        }
        if(departmentId!=null){
            wrapper.like("department_id",departmentId);
        }
        Integer count=departmentMapper.selectCount(wrapper);
        Page<Department> page = new Page<>(pageNo, pageSize);
        IPage<Department> iPage=departmentMapper.selectPage(page,wrapper);
        iPage.getPages();
        iPage.getTotal();
        List<Department> list=iPage.getRecords();
        JsonMassage<List<Department>> jsonMassage=new JsonMassage<>();
        jsonMassage.setCode(200);
        jsonMassage.setDataCount(count);
        jsonMassage.setData(list);
        jsonMassage.setMsg("ok");
        return jsonMassage;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JsonMassage delete(Integer id){
        int i=departmentMapper.deleteById(id);
        JsonMassage jsonMassage=new JsonMassage<>();
        jsonMassage.setMsg("ok");
        jsonMassage.setCode(200);
        jsonMassage.setData(i);
        jsonMassage.setDataCount(i);
        return  jsonMassage;
    }

    @RequestMapping("/add")
    @ResponseBody
    public JsonMassage add(int name){
        Department department=new Department();
        if(name==0){

            department.setDepartmentName("请选择");
        }else if(name==1){
            department.setDepartmentName("财务部门");
        }else if(name==2){
            department.setDepartmentName("人事部门");
        }else if(name==3){

            department.setDepartmentName("技术部门");
        }



       int i= departmentMapper.insert(department);
       JsonMassage jsonMassage=new JsonMassage<>();
        jsonMassage.setMsg("ok");
        jsonMassage.setCode(200);
        jsonMassage.setData(i);
        jsonMassage.setDataCount(i);

        return jsonMassage;
    }

    @RequestMapping("/update")
    @ResponseBody
    public JsonMassage update(int name){
        Department department=new Department();
        if(name==0){
            department.setDepartmentName("请选择");
        }else if(name==1){
            department.setDepartmentName("财务部门");
        }else if(name==2){
            department.setDepartmentName("人事部门");
        }else if(name==3){
            department.setDepartmentName("技术部门");
        }

        int i= departmentMapper.updateById(department);
        JsonMassage jsonMassage=new JsonMassage<>();
        jsonMassage.setMsg("ok");
        jsonMassage.setCode(200);
        jsonMassage.setData(i);
        jsonMassage.setDataCount(i);
        return jsonMassage;
    }

    @RequestMapping("/findId")
    @ResponseBody
    public  JsonMassage<Department> findId(Integer id){
       Department department= departmentMapper.selectById(id);
       JsonMassage<Department> jsonMassage=new JsonMassage<Department>();
        jsonMassage.setMsg("ok");
        jsonMassage.setCode(200);
        jsonMassage.setData(department);
        jsonMassage.setDataCount(1);
        return jsonMassage;
    }




}

