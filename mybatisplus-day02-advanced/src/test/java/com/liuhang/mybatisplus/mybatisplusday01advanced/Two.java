package com.liuhang.mybatisplus.mybatisplusday01advanced;

import com.liuhang.mybatisplus.mybatisplusday01advanced.dao.UserMapper;
import com.liuhang.mybatisplus.mybatisplusday01advanced.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class Two {

    @Autowired
    private UserMapper userMapper;

    /**
     * 插件二：自动填充，需要配置 MyMetaObjectHandler
     *      比如：在插入数据时，自动填充 createTime 属性
     *           在修改数据时，自动填充 updateTime 属性
     */
    @Test
    void insert() {
        User user = new User();
        user.setName("里ii");
        user.setAge(22);
        int insert = userMapper.insert(user);
        System.out.println("影响的行数：" +insert);
    }

    /**
     * 插件二：自动填充
     *      比如：在插入数据时，自动填充 createTime 属性
     *           在修改数据时，自动填充 updateTime 属性
     *
     *     UPDATE user_advance SET name=?, age=?, update_time=? WHERE id=? AND deleted=0
     */
    @Test
    void updateById() {
        User user = new User();
        user.setId(1278858080276189186L);
        user.setName("里ii");
        user.setAge(33);
        int insert = userMapper.updateById(user);
        System.out.println("影响的行数：" +insert);
    }
    /**
     * 插件二：自动填充
     *      比如：在插入数据时，自动填充 createTime 属性
     *           在修改数据时，自动填充 updateTime 属性
     */
    @Test
    void updateById2() {
        User user = new User();
        user.setId(1278858080276189186L);
        user.setName("里ii");
        user.setAge(11);
        user.setUpdate_time(LocalDateTime.now());
        int insert = userMapper.updateById(user);
        System.out.println("影响的行数：" +insert);
    }

    /**
     * 插件二：自动填充
     *      比如：在插入数据时，自动填充 createTime 属性
     *           在修改数据时，自动填充 updateTime 属性
     *
     *     此时不会自动填充 update_time
     *     UPDATE user_advance SET deleted=1 WHERE id=? AND deleted=0
     */
    @Test
    void deleteById() {
        int insert = userMapper.deleteById(1278858080276189186L);
        System.out.println("影响的行数：" +insert);
    }




}
