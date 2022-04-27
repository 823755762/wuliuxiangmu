package com.hz.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonMassage<T> {
    // 错误代码  0代码成功
    private Integer code;
    private String msg;
    //总条数
    private Integer dataCount;
    //数据
    private T data;
}
