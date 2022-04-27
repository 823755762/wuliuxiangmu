package com.hz.utils;

import lombok.Data;

@Data
public class ResultJson {
    private Integer code; // 错误代码  0代码成功
    private String msg;
    public ResultJson(Integer code){
        this.code = code;
        this.msg = this.code>0?"操作成功":"操作失败";
    }
}
