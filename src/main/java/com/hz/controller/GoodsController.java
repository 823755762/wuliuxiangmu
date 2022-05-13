package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.mapper.GoodsMapper;
import com.hz.pojo.Goods;
import com.hz.service.GoodsService;
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
 * 前端控制器
 * </p>
 *
 * @author UserG
 * @since 2022-04-26
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsService goodsService;


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
    @RequestMapping(value = "/chaxun")
    public JsonMassage<List<Goods>> chaxun(
            //defaultValue=当前页  defaultValue==每页条数
            @RequestParam(value = "pageNo", defaultValue = "1")
                    Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10")
                    Integer pageSize,
            String goodsNumber

    ) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<Goods>();
        if (goodsNumber != null) {
            queryWrapper.like("goods_Number", goodsNumber);
        }
        queryWrapper.orderByDesc("create_time");
        Page<Goods> page = new Page<>(pageNo, pageSize);

        Page<Goods> page1 = goodsService.page(page, queryWrapper);

        JsonMassage<List<Goods>> jsonMassage = new JsonMassage<List<Goods>>
                (200, "ok", Math.toIntExact(page.getTotal()), page1.getRecords());

        return jsonMassage;


//        //直接调用父类的baseMapper对象方法查询并返回数据
//        QueryWrapper qw = new QueryWrapper();
//       // Wrapper为条件查询构造器，
//       // QueryWrapper为Wrapper的一个实现方法。主要是用来进行多个where条件的拼接。
////        如果equipmentFireName不是null就查询数据，是null的话默认搜索全部
//        if (goodsNumber != null) {
//            //equipment_fire_name是数据库名字
//            System.out.println("进入if111111111111111111111111111111111111111111111111");
//            qw.like("goods_number", goodsNumber);
//        }
////        if (goods_id != null) {
////            System.out.println("进入if222222222222");
////            qw.eq("goods_id", goods_id);
////        }
//        Page<Goods> page1 = new Page<Goods>(pageNo, pageSize);
//
////        goodsService.page(page1, qw);的page是一个方法叫page
//        Page page2 = goodsService.page(page1, qw);
//        Integer count = Math.toIntExact(page1.getTotal());
//        JsonMassage<List<Goods>> json = new JsonMassage<List<Goods>>
//                (200, "ok", count, page1.getRecords());
//        System.out.println("page1===========" + page1.getRecords());
//        return json;
//


    }

    //添加
    @RequestMapping("/addlist")
    @ResponseBody
    public JsonMassage add(Goods goods) {
        int i = goodsMapper.insert(goods);
        JsonMassage jsonMassage = new JsonMassage();
        jsonMassage.setCode(200);
        jsonMassage.setData(i);
        jsonMassage.setDataCount(i);
        jsonMassage.setMsg("ok");
        return jsonMassage;
    }

    //查询
    @RequestMapping("/findid")
    @ResponseBody
    public JsonMassage<Goods> findbyid(Integer id) {
        Goods goods = goodsMapper.selectById(id);
        JsonMassage<Goods> jsonMassage = new JsonMassage<>();
        jsonMassage.setDataCount(1);
        jsonMassage.setMsg("ok");
        jsonMassage.setData(goods);
        jsonMassage.setCode(200);
        return jsonMassage;
    }


    //删除
    @RequestMapping("/shanchuid")
    @ResponseBody
    public JsonMassage shanchubyid(Integer goodsId) {
        int i = goodsMapper.deleteById(goodsId);
        JsonMassage jsonMassage = new JsonMassage<>();
        jsonMassage.setCode(200);
        jsonMassage.setMsg("ok");
        jsonMassage.setData(i);
        jsonMassage.setDataCount(1);
        return jsonMassage;
    }

    //修改
    @RequestMapping("/updateid")
    public JsonMassage updateid(Goods goods) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String format = dtf.format(now);    //  2022-04-27 15:46:30
        goods.setUpdateTime(format);
        int i = goodsMapper.updateById(goods);
        JsonMassage jsonMassage = new JsonMassage<>();
        jsonMassage.setCode(200);
        jsonMassage.setMsg("ok");
        jsonMassage.setData(i);
        jsonMassage.setDataCount(1);
        return jsonMassage;

    }

    @RequestMapping("/goodsAll")
    public JsonMassage<List<Goods>> goodsAll() {
        List<Goods> list = goodsService.list();
        JsonMassage<List<Goods>> jsonMassage = new JsonMassage<List<Goods>>(200,"ok",null,list);
        return jsonMassage;
    }

}

