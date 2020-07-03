package com.liuhang.mybatisplus.mybatisplusday01basic;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.liuhang.mybatisplus.mybatisplusday01basic.entity.User;
import com.liuhang.mybatisplus.mybatisplusday01basic.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class Four {

    @Autowired
    private IUserService userService;

    @Test
    public void getOne() {
        User one = userService.getOne(Wrappers.<User>lambdaQuery().gt(User::getAge, 25),false);
        System.out.println(one);
    }

    /**
     * 批量插入
     */
    @Test
    public void batch() {
        User user = new User();
        user.setName("xuxu");
        user.setAge(22);
        User user2 = new User();
        user2.setName("llll");
        user2.setAge(33);
        List<User> users = Arrays.asList(user, user2);
        boolean b = userService.saveBatch(users);
        System.out.println(b);
    }

    /**
     * 批量更新或者插入
     */
    @Test
    public void saveOrUpdateBatch() {
        // 执行插入
        User user = new User();
        user.setName("xuxu");
        user.setAge(22);
        // 执行更新
        User user2 = new User();
        user2.setId(1278610665753395202L);
        user2.setAge(332);
        List<User> users = Arrays.asList(user, user2);
        boolean b = userService.saveOrUpdateBatch(users);
        System.out.println(b);
    }

    @Test
    public void lambdaQuery() {
        List<User> list = userService.lambdaQuery().gt(User::getAge, 22).like(User::getName, "雨").list();
        list.forEach(System.out::println);
    }

    @Test
    public void lambdaUpdate() {
        boolean res = userService.lambdaUpdate().gt(User::getAge, 22).like(User::getName, "雨")
                .set(User::getAge, 26).update();
        System.out.println(res);
    }
}
