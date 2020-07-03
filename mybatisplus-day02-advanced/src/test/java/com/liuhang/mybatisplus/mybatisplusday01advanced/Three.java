package com.liuhang.mybatisplus.mybatisplusday01advanced;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.liuhang.mybatisplus.mybatisplusday01advanced.dao.UserMapper;
import com.liuhang.mybatisplus.mybatisplusday01advanced.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Three {

    @Autowired
    private UserMapper userMapper;

    /**
     * 插件三：乐观锁，指定乐观锁的版本，则自动设置乐观锁条件，同时自动更新乐观锁版本
     *     该特性仅仅支持 updateById(user) 和 update(user,eq)
     *     其中update(user,eq)里的第二个参数 Wrapper，不能复用
     *     UPDATE user_advance SET name=?, age=?, update_time=?, version=2 WHERE id=? AND version=1 AND deleted=0
     */
    @Test
    void updateById() {
        User user = new User();
        user.setId(1278630151382441985L);
        user.setName("里iiddddddddddddd");
        user.setAge(22);
        // 需要指定乐观锁版本
        user.setVersion(1);
        int insert = userMapper.updateById(user);
        System.out.println("影响的行数：" +insert);
    }

    /**
     * 插件三：乐观锁，指定乐观锁的版本，则自动设置乐观锁条件，同时自动更新乐观锁版本
     * 其中update(user,eq)里的第二个参数 Wrapper，不能复用
     */
    @Test
    void update() {
        User user = new User();
        user.setAge(11);
        // 需要指定乐观锁版本
        user.setVersion(2);

        // 这个条件被复用两次
        LambdaQueryWrapper<User> eq = Wrappers.<User>lambdaQuery().eq(User::getName,"里iiddddddddddddd");

        int update = userMapper.update(user,eq);
        System.out.println("影响的行数：" +update);

        User user1 = new User();
        user1.setAge(99);
        // 需要指定乐观锁版本
        user1.setVersion(3);
        // 复用第二个参数将导致更新错误
        update = userMapper.update(user,eq);
        System.out.println("影响的行数：" +update);
    }

}
