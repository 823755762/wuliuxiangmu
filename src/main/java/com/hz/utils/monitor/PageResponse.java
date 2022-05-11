package com.hz.utils.monitor;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 页面响应
 */
@Data
public class PageResponse {

    @JSONField(name="total")
    public int total;
    @JSONField(name="page")
    public int page;
    @JSONField(name="size")
    public int size;
}
