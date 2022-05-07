package com.hz.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.mapper.AuthorityMapper;
import com.hz.pojo.Authority;
import com.hz.utils.JsonMassage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/authority")
public class AuthorityController {
    @Autowired
    private AuthorityMapper authorityMapper;
    @RequestMapping("/authorityList")
    public JsonMassage<List<Authority>> AuthorityList(
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            String authorityName
            ) {
        QueryWrapper<Authority> queryWrapper = null;
        if (authorityName != null) {
            queryWrapper = new QueryWrapper();
            queryWrapper.like("authority_name",authorityName);
        }
        if (queryWrapper!=null){
            Page<Authority> page = new Page<Authority>(pageNo, pageSize);
            Page<Authority> authorityPage = authorityMapper.selectPage(page,queryWrapper);
            JsonMassage<List<Authority>> jsonMassage = new JsonMassage<List<Authority>>(200, "ok", Math.toIntExact(page.getTotal()), page.getRecords());
            return jsonMassage;
        }
        Page<Authority> page = new Page<Authority>(pageNo, pageSize);
        Page<Authority> authorityPage = authorityMapper.selectPage(page, null);
        JsonMassage<List<Authority>> jsonMassage = new JsonMassage<List<Authority>>(200, "ok", Math.toIntExact(page.getTotal()), page.getRecords());
        return jsonMassage;
    }
    @RequestMapping("/findAuthorityById")
    public JsonMassage<Authority> findAuthorityById(Long id){
        Authority authority = authorityMapper.selectById(id);
        JsonMassage<Authority> jsonMassage = new JsonMassage<Authority>();
        jsonMassage.setCode(200);
        jsonMassage.setMsg("ok");
        jsonMassage.setData(authority);
        return jsonMassage;
    }
    @RequestMapping("/updateAuthorityById")
    public JsonMassage updateAuthorityById(Authority authority){
        //获取时间
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        //  2022-04-27 15:46:30
        String format = dtf.format(now);
        authority.setUpdateTime(format);
        int i = authorityMapper.updateById(authority);
        JsonMassage jsonMassage = new JsonMassage();
        jsonMassage.setCode(200);
        jsonMassage.setMsg("ok");
        jsonMassage.setData(i);
        return jsonMassage;
    }
    @RequestMapping("/insertAuthority")
    public JsonMassage insertAuthority(Authority authority){
        //获取时间
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        //  2022-04-27 15:46:30
        String format = dtf.format(now);
        authority.setCreateTime(format);
        authority.setUpdateTime(format);
        authority.setDeleted(0);
        int i = authorityMapper.insert(authority);
        JsonMassage jsonMassage = new JsonMassage();
        jsonMassage.setCode(200);
        jsonMassage.setMsg("ok");
        jsonMassage.setData(i);
        return jsonMassage;
    }
    @RequestMapping("/deleteauthorityById")
    public JsonMassage deleteauthorityById(Long authorityId){
        int i = authorityMapper.deleteById(authorityId);
        JsonMassage jsonMassage = new JsonMassage();
        if (i > 0) {
            jsonMassage.setCode(200);
            jsonMassage.setMsg("ok");
            jsonMassage.setData(i);
        }
        return jsonMassage;
    }
}

