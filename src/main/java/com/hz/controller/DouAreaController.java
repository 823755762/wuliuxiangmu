package com.hz.controller;

import com.hz.pojo.DouArea;
import com.hz.service.DouAreaService;
import com.hz.utils.JsonMassage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/json")
public class DouAreaController {

    @Autowired
    private DouAreaService douAreaService;


    @RequestMapping("/json")
    public JsonMassage<LinkedList<LinkedHashMap>> json() {
        //拿到数据，DouArea为实体类，
        List<DouArea> DouArea = douAreaService.list();

        //第一层
        long parentId = 0;
        LinkedList<LinkedHashMap> list = new LinkedList<>();
        for (DouArea city : DouArea) {
            if (parentId == city.getParentId()) {
                LinkedHashMap<String, Object> Map = new LinkedHashMap<>();
                Map.put("value", city.getAreaId());
                Map.put("label", city.getName());
                LinkedList<LinkedHashMap> list2 = new LinkedList<>();
                Map.put("children", list2);
                list.add(Map);
                //第二层
                for (DouArea city2 : DouArea) {
                    if (city.getAreaId() == city2.getParentId()) {
                        LinkedHashMap<String, Object> Map2 = new LinkedHashMap<>();
                        Map2.put("value", city2.getAreaId());
                        Map2.put("label", city2.getName());
                        LinkedList<LinkedHashMap> list3 = new LinkedList<>();
                        Map2.put("children", list3);
                        list2.add(Map2);
                        //第三层
                        for (DouArea city3 : DouArea) {
                            if (city2.getAreaId() == city3.getParentId()) {
                                LinkedHashMap<String, Object> Map3 = new LinkedHashMap<>();
                                Map3.put("value", city3.getAreaId());
                                Map3.put("label", city3.getName());
                                list3.add(Map3);
                            }
                        }

                    }
                }
            }

        }
        JsonMassage<LinkedList<LinkedHashMap>> jsonMap =
                new JsonMassage<LinkedList<LinkedHashMap>>(200, "ok", null, list);

        return jsonMap;
    }
}




