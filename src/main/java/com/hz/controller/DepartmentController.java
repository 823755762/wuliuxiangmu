package com.hz.controller;


import com.hz.pojo.Department;
import com.hz.service.DepartmentService;
import com.hz.utils.JsonMassage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/findAll")
    @ResponseBody
    public JsonMassage<List<Department>> findAll(String DepartmentName,String CreateTime){
        List<Department> list=departmentService.findAll(DepartmentName,CreateTime);
        Integer count=departmentService.findcount(DepartmentName,CreateTime);
        JsonMassage<List<Department>> jsonMassage=new JsonMassage<>();
        jsonMassage.setCode(200);
        jsonMassage.setDataCount(count);
        jsonMassage.setData(list);
        jsonMassage.setMsg("ok");
        return jsonMassage;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public int delete(Integer id){

        return  departmentService.deleteder(id);
    }

    @RequestMapping("/add")
    @ResponseBody
    public int add(){
        return departmentService.adddepar();
    }
}

