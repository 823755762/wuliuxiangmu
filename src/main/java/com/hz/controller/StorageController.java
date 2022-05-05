package com.hz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.mapper.StorageMapper;
import com.hz.pojo.Storage;
import com.hz.pojo.Unit;
import com.hz.service.StorageService;
import com.hz.utils.JsonMassage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/storage")
public class StorageController {
     @Autowired
     private StorageMapper storageMapper;
     @Autowired
     private StorageService storageService;

     /**
      * VUE 调用 分页查询  多条件
      * @return
      */
     /**
      * 分页查询  多条件
      * @param pageNo  当前页
      * @param pageSize 每页显示条数
      * @return
      */

     @RequestMapping("/storagelike")
     public JsonMassage<List<Storage>> storagelike(
             @RequestParam(value = "pageNo",defaultValue = "1")
                     Integer pageNo,
             @RequestParam(value = "pageSize",defaultValue = "10")
                     Integer pageSize,

             Integer goodsId

     ){
          QueryWrapper<Storage> queryWrapper = new QueryWrapper<Storage>();
          if (goodsId !=null) {

               queryWrapper.like("goods_id", goodsId);

          }
          Page<Storage> page = new Page<>(pageNo,pageSize);

          Page<Storage> page1 = storageService.page(page,queryWrapper);

          JsonMassage<List<Storage>> jsonMassage = new JsonMassage<List<Storage>>
                  (200,"ok",Math.toIntExact(page.getTotal()),page1.getRecords());
                  return  jsonMassage;

          }

          //添加
          @RequestMapping("addStorage")
          @ResponseBody
          public JsonMassage add(Storage storage){
          int i = storageMapper.insert(storage);
          JsonMassage jsonMassage = new JsonMassage<>();
          jsonMassage.setMsg("ok");
          jsonMassage.setCode(200);
          jsonMassage.setData(i);
          jsonMassage.setDataCount(1);
          return  jsonMassage;

          }

          //查询
          @RequestMapping("/selectStorage")
          @ResponseBody
          public JsonMassage<Storage> findid(Integer id){

          Storage storage = storageMapper.selectById(id);

          JsonMassage<Storage> jsonMassage = new JsonMassage<>();
          jsonMassage.setDataCount(1);
          jsonMassage.setCode(200);
          jsonMassage.setMsg("ok");
          jsonMassage.setData(storage);
          return  jsonMassage;
          }

          //删除
          @RequestMapping("/deleteid")
          @ResponseBody
          public JsonMassage<Storage>  deletebyid(Integer storageId){
          int i = storageMapper.deleteById(storageId);
          JsonMassage jsonMassage = new JsonMassage<>();
          jsonMassage.setData(i);
          jsonMassage.setMsg("ok");
          jsonMassage.setCode(200);
          jsonMassage.setDataCount(1);
          return  jsonMassage;
          }


          //修改
         @RequestMapping("/updatestorage")
         public JsonMassage updatebyid(Storage storage){
          //获取时间
              DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
              LocalDateTime now = LocalDateTime.now();
              String format = dtf.format(now); //2022-04-27 15:46:30
              storage.setUpdateTime(format);
              int i = storageMapper.updateById(storage);
              JsonMassage jsonMassage = new JsonMassage<>();
              jsonMassage.setDataCount(i);
              jsonMassage.setCode(200);
              jsonMassage.setMsg("ok");
              jsonMassage.setData(i);
              return  jsonMassage;
         }
     }






