package com.liuhang.mybatisplus.mybatisplusday01advanced;

import com.liuhang.mybatisplus.mybatisplusday01advanced.component.MyBatisPlusConfiguration;
import com.liuhang.mybatisplus.mybatisplusday01advanced.dao.UserMapper;
import com.liuhang.mybatisplus.mybatisplusday01advanced.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Five {

    @Autowired
    private UserMapper userMapper;

    /**
     * 插件五：动态表名 SQL 解析器
     * SELECT id,name,age,email,manager_id,create_time,update_time,version
     * FROM user_2020
     * WHERE deleted=0
     *
     * 查询中的 user_advance 被替换成了设置的 user_2020
     * 又表 user_2020 并不存在，故而SQL执行报错
     */
    @Test
    void selectList() {
        MyBatisPlusConfiguration.myTableName.set("user_2020");
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

}
