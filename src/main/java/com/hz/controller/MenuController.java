package com.hz.controller;








import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.hz.mapper.RoleMapper;
import com.hz.pojo.Menu;
import com.hz.pojo.Role;
import com.hz.service.MenuService;
import com.hz.utils.JsonMassage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashSet;
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
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
@Autowired
private RoleMapper roleMapper;
   /* @GetMapping("/menu")
    public JsonMassage getMenu(Long userId){
        List<Menu> menuListByUserid = menuService.findMenuListByUserid(userId);
        LinkedHashSet<Menu> hashSet = new LinkedHashSet<>(menuListByUserid);
        menuListByUserid = new ArrayList<>(hashSet);
        JSONArray pareMenu =new JSONArray();
        for (Menu menu : menuListByUserid) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.putOnce("id",menu.getMenuId());
            jsonObject.putOnce("name",menu.getMenuTitle());
            jsonObject.putOnce("icon",menu.getMenuIcon());
            jsonObject.putOnce("info","");
            JSONArray childMenu = new JSONArray();
            List<Menu> childldren = menuService.getClildren(menu.getMenuId());
            for (Menu menus:childldren){
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.putOnce("id",menus.getMenuId());
                jsonObject1.putOnce("name",menus.getMenuTitle());
                jsonObject1.putOnce("icon",menus.getMenuIcon());
                jsonObject1.putOnce("info","");
                jsonObject1.putOnce("url",menus.getMenuValue());
                childMenu.add(jsonObject1);
            }
            jsonObject.putOnce("childList",childMenu);
            pareMenu.put(jsonObject);
        }

        JsonMassage jsonMassage = new JsonMassage();
        jsonMassage.setCode(200);
        jsonMassage.setData(pareMenu);
        return jsonMassage;
    }*/


    @GetMapping("/menu")
    public JsonMassage getMenu(Long userId){
//        List<String> menuListByUserid = menuService.findMenuListByUserid(userId);
        String menuListByUserid = menuService.findMenuListByUserid(userId);
        String[] split1 = menuListByUserid.split(",");
        List<String> menuIds=new ArrayList<>();

       /* for (String string:menuListByUserid) {
            String[] split = string.split(",");
            for (String string2 : split) {
                menuIds.add(string2);
            }
        }*/

       for (String string:split1) {
           menuIds.add(string);
       }
        LinkedHashSet<String> hashSet = new LinkedHashSet<>(menuIds);
        menuIds = new ArrayList<>(hashSet);
        JsonMassage jsonMassage = new JsonMassage();
        jsonMassage.setCode(200);
        jsonMassage.setData(menuIds);
        return jsonMassage;
    }
    @RequestMapping("/getList")
    public JsonMassage getListMenu(Long roleId){
        Role role = roleMapper.selectById(roleId);
        String[] split = role.getRoleIds().split(",");
        JsonMassage jsonMassage = new JsonMassage();
        jsonMassage.setCode(200);
        jsonMassage.setData(split);
        return jsonMassage;
    }
}

