package com.hz.utils.monitor;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 设备响应信息工具类
 */
@Data
public class BasicResponse<T> {

    public String code; //返回类型码
    public String msg; //返回类型描述
    public T data; //返回数据data
    public PageResponse page; //返回分页信息
    // TODO 这里有重复的字段
    @JSONField(name="data")
    public Object dataInternal;
    @JSONField(name="page")
    public Object pageInternal;
    @JSONField(serialize = false)
    public String json;
}
