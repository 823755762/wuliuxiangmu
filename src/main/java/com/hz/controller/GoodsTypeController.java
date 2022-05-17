package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.mapper.GoodsTypeMapper;
import com.hz.pojo.GoodsType;
import com.hz.service.GoodsTypeService;
import com.hz.utils.JsonMassage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
@RequestMapping("/goodstype")
public class GoodsTypeController {
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;

    @Autowired
    private GoodsTypeService goodsTypeService;

    /**
     * VUE 调用 分页查询  多条件
     * @return
     */
    /**
     * 分页查询  多条件
     *
     * @param pageNo   当前页
     * @param pageSize 每页显示条数
     * @return
     */
    @RequestMapping(value = "/selectgt")
    public JsonMassage<List<GoodsType>> selectgt(
            @RequestParam(value = "pageNo", defaultValue = "1")
                    Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10")
                    Integer pageSize,
            String goodsTypeName,
            Date createTime,
            Date updateTime

    ) {
        QueryWrapper<GoodsType> queryWrapper = new QueryWrapper<GoodsType>();
        if (goodsTypeName != null) {
            queryWrapper.like("goods_type_name", goodsTypeName);
        }

        if (createTime != null) {
            queryWrapper.like("create_time", createTime);
        }
        if (updateTime != null) {
            queryWrapper.like("update_time", updateTime);
        }
        Page<GoodsType> page = new Page<>(pageNo, pageSize);

        Page<GoodsType> page2 = goodsTypeService.page(page, queryWrapper);

        JsonMassage<List<GoodsType>> jsonMassage = new JsonMassage<List<GoodsType>>
                (200, "ok", Math.toIntExact(page.getTotal()), page2.getRecords());


        return jsonMassage;


    }

    //添加
    @RequestMapping("/addtype")
    @ResponseBody
    public JsonMassage add(GoodsType goodsType) {

        int i = goodsTypeMapper.insert(goodsType);
        JsonMassage jsonMassage = new JsonMassage();
        jsonMassage.setCode(200);
        jsonMassage.setMsg("ok");
        jsonMassage.setDataCount(i);
        jsonMassage.setData(i);
        return jsonMassage;
    }


    //查询
    @RequestMapping("/findid")
    @ResponseBody
    public JsonMassage<GoodsType> findbyid(Long id) {
        System.out.println("id======" + id);
        GoodsType goodsType = goodsTypeMapper.selectById(id);

        JsonMassage<GoodsType> jsonMassage = new JsonMassage<>();
        jsonMassage.setMsg("ok");
        jsonMassage.setData(goodsType);
        jsonMassage.setCode(200);
        return jsonMassage;
    }


    //删除
    @RequestMapping("/deletetype")
    @ResponseBody
    public JsonMassage deletetypebyid(Integer goodsTypeId) {
        int i = goodsTypeMapper.deleteById(goodsTypeId);
        JsonMassage jsonMassage = new JsonMassage<>();
        jsonMassage.setCode(200);
        jsonMassage.setMsg("ok");
        jsonMassage.setData(i);
        jsonMassage.setDataCount(i);
        return jsonMassage;

    }


    //修改
    @RequestMapping("/updatetype")
    public JsonMassage updatebyid(GoodsType goodsType) {
//          DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
//          LocalDateTime now = LocalDateTime.now();
//          String format = dtf.format(now);    //  2022-04-27 15:46:30
//          goodsType.setUpdateTime(format);
        int i = goodsTypeMapper.updateById(goodsType);
        JsonMassage jsonMassage = new JsonMassage<>();
        jsonMassage.setCode(200);
        jsonMassage.setMsg("ok");
        jsonMassage.setData(goodsType);
        jsonMassage.setDataCount(1);

        return jsonMassage;
    }
    @RequestMapping("/goodsTypeAll")
    public  JsonMassage<List<GoodsType>> goodsTypeAll() {
        List<GoodsType> list = goodsTypeService.list();
        JsonMassage<List<GoodsType>> jsonMassage = new JsonMassage<List<GoodsType>>(200,"ok",null,list);
        return jsonMassage;
    }
}