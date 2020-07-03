package com.liuhang.mybatisplus.mybatisplusday01basic;

import com.liuhang.mybatisplus.mybatisplusday01basic.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Three {

    /**
     * 测试AR 模式，通过操作对象操作数据库
     */

    /**
     * insert
     */
    @Test
    public void insert() {
        User user = new User();
        user.setName("zzzzz");
        user.setAge(66);
        user.setEmail("test@qq.com");

        boolean insert = user.insert();
        System.out.println(insert);
    }

    /**
     * selectById
     */
    @Test
    public void selectById() {
        User user = new User();
        User user1 = user.selectById(1278601184487161858L);
        System.out.println(user1);
        // false
        System.out.println(user1 == user);
    }

    /**
     * selectById
     */
    @Test
    public void selectById2() {
        User user = new User();
        user.setId(1278601184487161858L);
        User user1 = user.selectById(user);
        System.out.println(user1);
        // false
        System.out.println(user1 == user);
    }

    /**
     * updateById
     */
    @Test
    public void updateById() {
        User user = new User();
        user.setId(1278601184487161858L);
        user.setName("zhangsan");
        boolean b = user.updateById();
        System.out.println(b);
    }

    /**
     * deleteById
     */
    @Test
    public void deleteById() {
        User user = new User();
        user.setId(1278601184487161858L);
        boolean b = user.deleteById();
        System.out.println(b);
    }
}
