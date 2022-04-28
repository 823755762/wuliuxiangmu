package com.hz.controller;


import com.hz.pojo.Role;
import com.hz.service.RoleService;
import com.hz.utils.JsonMassage;
import com.hz.utils.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/role")
public class RoleController {
    @Autowired
    public RoleService roleService;
    @RequestMapping("/list")
    public JsonMassage<List<Role>> list(String roleSupperAdmin){
        List<Role> listRole = roleService.findListRole(roleSupperAdmin);
        JsonMassage<List<Role>> jsonMassage = new JsonMassage<List<Role>>();
        jsonMassage.setCode(200);
        jsonMassage.setMsg("OK");
        jsonMassage.setData(listRole);
        return jsonMassage;
    }
    @RequestMapping("/update")
    public ResultJson updateRole(Role role){
        int i = roleService.updateRole(role);
        ResultJson resultJson = new ResultJson(i);
        resultJson.setCode(200);
        return resultJson;
    }
    @RequestMapping("/add")
    public ResultJson addRole(Role role) {
        int i = roleService.addRole(role);
        ResultJson resultJson = new ResultJson(i);
        resultJson.setCode(200);
        return resultJson;
    }
}

