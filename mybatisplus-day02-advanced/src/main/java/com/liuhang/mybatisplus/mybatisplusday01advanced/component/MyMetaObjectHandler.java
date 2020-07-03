package com.liuhang.mybatisplus.mybatisplusday01advanced.component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 插件二：自动填充属性
 *      比如：在插入数据时，自动填充 createTime 属性
 *           在修改数据时，自动填充 update_time 属性
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        boolean createTime = metaObject.hasSetter("createTime");
        if (createTime) {
            System.out.println("InsertFill");
            strictInsertFill(metaObject,"createTime",LocalDateTime.class,LocalDateTime.now());
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        boolean update_time = metaObject.hasSetter("update_time");
        Object value = getFieldValByName("update_time", metaObject);
        // 存在update_time属性的setter方法，同时没有设置update_time的值，则自动填充
        if (update_time && value == null) {
            System.out.println("UpdateFill");
            strictUpdateFill(metaObject,"update_time", LocalDateTime.class,LocalDateTime.now());
        }
    }
}
