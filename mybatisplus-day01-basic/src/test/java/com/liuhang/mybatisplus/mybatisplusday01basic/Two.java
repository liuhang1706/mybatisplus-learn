package com.liuhang.mybatisplus.mybatisplusday01basic;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.liuhang.mybatisplus.mybatisplusday01basic.dao.UserMapper;
import com.liuhang.mybatisplus.mybatisplusday01basic.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class Two {

    @Autowired
    private UserMapper userMapper;

    /**
     * 对象的属性为null，则不会进行更新
     * updateById
     */
    @Test
    public void updateById() {
        User user = new User();
        user.setId(1278535475551821825L);
        user.setAge(66);
        user.setEmail("test@qq.com");
        int i = userMapper.updateById(user);
        System.out.println("影响记录数：" +i);
    }

    /**
     * 对象的属性为null，则不会进行更新
     * update
     */
    @Test
    public void updateByWrapper() {
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("name","李启暗");
        User user = new User();
        // 主键字段设置无效
        user.setId(111L);
        user.setEmail("@@@@@@");
        int i = userMapper.update(user,userUpdateWrapper);
        System.out.println("影响记录数：" +i);
    }

    /**
     * deleteById
     */
    @Test
    public void deleteById() {
        int i = userMapper.deleteById(1278535475551821825L);
        System.out.println(i);
    }

    /**
     * deleteByMap
     * delete from user where name = ? and age = ?
     */
    @Test
    public void deleteByMap() {
        Map<String,Object> map = new HashMap<>();
        map.put("name","李启暗");
        map.put("age",66);
        int i = userMapper.deleteByMap(map);
        System.out.println(i);
    }

    /**
     * deleteBatchIds
     */
    @Test
    public void deleteBatchIds() {
        int i = userMapper.deleteBatchIds(Arrays.asList(111L,222L));
        System.out.println(i);
    }

    /**
     * deleteBatchIds
     */
    @Test
    public void delete() {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getName,"里斯");
        int i = userMapper.delete(userLambdaQueryWrapper);
        System.out.println(i);
    }

}
