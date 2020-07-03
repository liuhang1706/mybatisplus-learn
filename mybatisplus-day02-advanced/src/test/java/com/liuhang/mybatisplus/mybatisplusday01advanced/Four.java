package com.liuhang.mybatisplus.mybatisplusday01advanced;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.liuhang.mybatisplus.mybatisplusday01advanced.dao.UserMapper;
import com.liuhang.mybatisplus.mybatisplusday01advanced.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Four {

    @Autowired
    private UserMapper userMapper;

    /**
     * 插件四：多租户 SQL 解析器
     * INSERT INTO user_advance (id, name, age, create_time, manager_id)
     * VALUES
     * (1278874040265670658, 'xxxxxxxxxxx', 11, '2020-07-03T10:11:46.192', 1087982257332887553)
     *
     * 由于配置了插件填充 crateTime属性，同时设置了插件管理租户 manager_id=1087982257332887553
     * 所以自动填充 createTime和 manager_id=1087982257332887553
     */
    @Test
    void insert() {
        User user = new User();
        user.setName("xxxxxxxxxxx");
        user.setAge(11);
        int insert = userMapper.insert(user);
        System.out.println("影响的行数：" +insert);
    }

    /**
     * 插件四：多租户 SQL 解析器
     * 由于配置了过滤器，过滤了selectById方法，则调用该方法时，没有租户信息
     * SELECT id,name,age,email,manager_id,create_time,update_time,version
     * FROM user_advance
     * WHERE id=1088248166370832385 AND deleted=0
     */
    @Test
    void selectById() {
        User user = userMapper.selectById(1088248166370832385L);
        System.out.println(user);
    }

    /**
     * 插件四：多租户 SQL 解析器
     * 由于配置 @SqlParser(filter=true)注解，所以该查询语句没有租户信息
     */
    @Test
    void selectAll() {
        List<User> users = userMapper.selectAll(Wrappers.<User>lambdaQuery().gt(User::getAge, 22));
        users.forEach(System.out::println);
    }

}
