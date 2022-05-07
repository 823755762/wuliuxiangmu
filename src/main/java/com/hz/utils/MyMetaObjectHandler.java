package com.hz.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = getFieldValByName("createTime", metaObject);
        if (null == createTime) {
            //字段为空，可以进行填充
            setFieldValByName("createTime", new Date(), metaObject);
        }

        Object updateTime = getFieldValByName("updateTime", metaObject);
        if (null == updateTime) {
            //字段为空，可以进行填充
            setFieldValByName("updateTime", new Date(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //更新数据时，直接更新字段
        setFieldValByName("updateTime", new Date(), metaObject);
    }
}
