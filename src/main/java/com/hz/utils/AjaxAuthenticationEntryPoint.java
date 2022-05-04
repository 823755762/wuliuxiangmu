package com.hz.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        String code = httpServletRequest.getAttribute("code").toString();
        JsonMassage Json = new JsonMassage();
        if(code.equals("1"))
        {
            Json.setCode(1);
            Json.setMsg("未登录");
        }else if(code.equals("2")){
            Json.setCode(2);
            Json.setMsg("权限不足");
        }else{
            Json.setCode(3);
            Json.setMsg("系统异常");
        }
        httpServletResponse.setCharacterEncoding("utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.write(JSONObject.toJSONString(Json));


    }
}
