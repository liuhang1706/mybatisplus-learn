package com.liuhang.mybatisplus.mybatisplusday01advanced;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.liuhang.mybatisplus.mybatisplusday01advanced.dao.UserMapper;
import com.liuhang.mybatisplus.mybatisplusday01advanced.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class One {

    @Autowired
    private UserMapper userMapper;

    /**
     * 插件一：
     *      测试逻辑删除：通过设置表中特定字段，表示该记录已经删除；
     *          UPDATE user_advance SET deleted=1 WHERE id=? AND deleted=0
     */
    @Test
    void deleteById() {
        int i = userMapper.deleteById(1094592041087729666L);
        System.out.println(i);
    }

    /**
     * 设置了逻辑删除，则查询所有时，会自动添加条件来过滤已经逻辑删除的记录
     *  FROM user_advance WHERE deleted=0
     */
    @Test
    void selectList() {
        List<User> users = userMapper.selectList(null);
        // 逻辑上被删除的记录不会被查询
        users.forEach(System.out::println);
    }

    /**
     * 设置了逻辑删除，则更新操作时，只能更新逻辑未删除的记录
     *  UPDATE user_advance SET age=? WHERE id=? AND deleted=0
     */
    @Test
    void updateById() {
        User user = new User();
        user.setId(1088248166370832385L);
        user.setAge(55);
        int i = userMapper.updateById(user);
        System.out.println(i);
    }

    /**
     * 设置了逻辑删除，但是对自定义的方法，不生效
     *      解决方法：1.可以自己在SQL中添加逻辑删除条件
     *              2.可以在Wrappers中自己添加逻辑删除条件
     *      select * from user WHERE (age > ?)
     */
    @Test
    void selectAll() {
        List<User> users = userMapper.selectAll(Wrappers.<User>lambdaQuery().gt(User::getAge, 22));
        users.forEach(System.out::println);
    }



}
