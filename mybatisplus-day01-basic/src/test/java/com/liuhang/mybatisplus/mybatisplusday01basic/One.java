package com.liuhang.mybatisplus.mybatisplusday01basic;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuhang.mybatisplus.mybatisplusday01basic.dao.UserMapper;
import com.liuhang.mybatisplus.mybatisplusday01basic.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询方法测试
 */
@SpringBootTest
class One {

    @Autowired
    private UserMapper userMapper;

    @Test
    void select() {
        List<User> users = userMapper.selectList(null);
        Assert.assertEquals(5,users.size());
        users.forEach(System.out::println);
    }

    /**
     * 插入对象时，没有指定主键，则 mybatisplus 使用雪花算法生成一个唯一的主键ID
     */
    @Test
    void insert() {
        User user = new User();
        user.setName("李启暗");
        user.setAge(22);
        user.setManagerId(1087982257332887553L);
        user.setCreateTime(LocalDateTime.now());
        user.setRemark("这是一个标注，但是不是数据库中的字段");
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    @Test
    void selectById() {
        User user = userMapper.selectById(1094590409767661570L);
        System.out.println(user);
    }

    @Test
    void selectBatchIds() {
        List<Long> longs = Arrays.asList(1094590409767661570L, 1088248166370832385L, 1278531018927636481L);
        List<User> users = userMapper.selectBatchIds(longs);
        users.forEach(System.out::println);
    }

    /**
     * selectByMap方法中的Map的key，必须是表中真实字段，否则报错
     */
    @Test
    void selectByMap() {
        Map<String,Object> maps = new HashMap<>();
//        maps.put("name","王天风");
//        maps.put("age",11);
        // 查询失败，因为manageId字段在数据库表中不存在
//        maps.put("managerId",1087982257332887553L);
        // 查询成功
        maps.put("manager_id",1087982257332887553L);
        List<User> users = userMapper.selectByMap(maps);
        users.forEach(System.out::println);
    }

    /**
     *  name like '%雨%' and age < 40
     */
    @Test
    void selectList() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("name","雨").lt("age",40);

        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    /**
     *  name like '雨%' or age >= 25 order by age desc, id asc
     */
    @Test
    void selectList2() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.likeRight("name","雨").or()
                .ge("age",25)
                .orderByDesc("age").orderByAsc("id");

        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    /**
     *  name like '王%' and (age <40 or email is not null)
     */
    @Test
    void selectList3() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.
                likeRight("name","王").and(wq -> wq.lt("age",40).or().isNotNull("email"));

        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    /**
     *  name like '王%' or (age <40 and age > 20 and email is not null)
     */
    @Test
    void selectList4() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.
                likeRight("name","王")
                .or(wq -> wq.lt("age",40).gt("age",20).isNotNull("email"));

        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    /**
     *  (age <40 or email is not null) and name like '王%'
     */
    @Test
    void selectList5() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.nested(wq -> wq.lt("age",40).or().isNotNull("email"))
                .likeRight("name","王");

        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    /**
     *  age in (30, 31, 34, 35)
     */
    @Test
    void selectList6() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.in("age",30, 31, 34, 35);

        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    /**
     *  limit 1
     *  视优化规则直接拼接到 sql 的最后(有sql注入的风险,请谨慎使用)
     */
    @Test
    void selectList7() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.in("age",30, 31, 34, 35).last("limit 1");

        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    /**
     *  只获取部分字段
     *  select id, name fro user where name like '%雨%' and age < 40
     */
    @Test
    void selectList8() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("id","name")
                .like("name","雨").lt("age",40);

        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }
    /**
     *  只获取部分字段，通过排除不需要的字段来实现
     *  select id, name fro user where name like '%雨%' and age < 40
     */
    @Test
    void selectList9() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper
                .like("name","雨").lt("age",40)
                // 不获取create_time字段和manager_id字段
                .select(User.class,tableFieldInfo -> !tableFieldInfo.getColumn().equals("create_time")&&
                        !tableFieldInfo.getColumn().equals("manager_id"));
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    /**
     *  Condition
     */
    @Test
    void testCondition() {
        // 前端传入
        String name = "王";
//        Integer age = 11;
        Integer age = null;

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like(StringUtils.isNotBlank("name"),"name",name)
                .gt(age != null,"age",age);
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    /**
     *  new QueryWrapper<>(whereUser);
     *      会将对象属性作为参数，默认为等值判断
     *      可以通过实体类上的 @TableField 注解中的Condition进行修改
     */
    @Test
    void queryWrapper() {
        User whereUser = new User();
        whereUser.setName("刘红雨");
        whereUser.setAge(32);

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>(whereUser);

        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    /**
     *  allEq
     */
    @Test
    void queryWrapperAllEqual() {
        Map<String,Object> maps = new HashMap<>();
        maps.put("name","王天风");
//        maps.put("age",25);
        // 查询条件变成 age is null
        maps.put("age",null);

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.allEq(maps);
        List<User> users = userMapper.selectList(userQueryWrapper);
        users.forEach(System.out::println);
    }

    /**
     *  name like '%雨%' and age < 40
     */
    @Test
    void selectMaps() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("name","雨").lt("age",40);

        List<Map<String, Object>> list = userMapper.selectMaps(userQueryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 分组查询
     * 按照直属上级进行分组，查询每组的平均年龄、最大年龄、最小年龄
     * 并且只取年龄总和小于500的组
     *  select avg(age) avg_age, max(age) max_age, min(age) min_age from user
     *  group by manager_id
     *  having sum(age) < 500;
     */
    @Test
    void selectMaps2() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("avg(age) avg_age","max(age) max_age","min(age) min_age")
                        .groupBy("manager_id")
                        .having("sum(age) < {0}",500);

        List<Map<String, Object>> list = userMapper.selectMaps(userQueryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * lambdaQueryWrapper
     */
    @Test
    void lambdaQueryWrapper() {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.like(User::getName,"雨");
        List<User> list = userMapper.selectList(userLambdaQueryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * lambdaQueryWrapper
     */
    @Test
    void lambdaQueryWrapper2() {
        List<User> users = new LambdaQueryChainWrapper<User>(userMapper)
                .like(User::getName,"王").ge(User::getAge,20)
                .list();
        users.forEach(System.out::println);
    }

    /**
     * lambdaQueryWrapper
     */
    @Test
    void selectMy() {
//        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.lambdaQuery();
        userLambdaQueryWrapper.like(User::getName,"雨");
        List<User> list = userMapper.selectAll(userLambdaQueryWrapper);
        list.forEach(System.out::println);
    }

    /**
     * 分页查询
     *  name like '%雨%' and age < 40
     */
    @Test
    void selectPage() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("name","雨").lt("age",40);

        Page<User> userPage = new Page<>(1,2);

        Page<User> page = userMapper.selectPage(userPage, userQueryWrapper);
        System.out.println("总页数：" +page.getPages());
        System.out.println("总记录数：" +page.getTotal());
        List<User> records = page.getRecords();
        records.forEach(System.out::println);
    }

    /**
     * 分页查询
     *  name like '%雨%' and age < 40
     */
    @Test
    void selectPage2() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("name","雨").lt("age",40);

        // 查询总记录数
//        IPage<Map<String,Object>> page = new Page<>(1,2);
        // 不查询总记录数
        IPage<Map<String,Object>> page = new Page<>(1,2,false);
        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(page, userQueryWrapper);
        System.out.println("总页数：" +mapIPage.getPages());
        System.out.println("总记录数：" +mapIPage.getTotal());
        List<Map<String, Object>> records = mapIPage.getRecords();
        records.forEach(System.out::println);
    }

    /**
     * 分页查询：在xml文件中配置sql
     *  name like '%雨%' and age < 40
     */
    @Test
    void selectPage3() {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("name","雨").lt("age",40);

        // 查询总记录数
        Page<User> page = new Page<>(1,2);
        IPage<User> userIPage = userMapper.selectUserPage(page, userQueryWrapper);
        System.out.println("总页数：" +userIPage.getPages());
        System.out.println("总记录数：" +userIPage.getTotal());
        List<User> records = userIPage.getRecords();
        records.forEach(System.out::println);
    }
}
