package com.liuhang.mybatisplus.mybatisplusday01advanced.dao;


import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.liuhang.mybatisplus.mybatisplusday01advanced.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    /**
     * 也可以自动方法设置SQL语句，SQL语句还可以配置在xml映射文件中
     * @Param(Constants.WRAPPER) Wrapper<User> wrapper 是固定写法
     *
     *  @SqlParser(filter=true) 表示过滤该方法，即没有租户信息
     *                           注意：该配置对动态表名SQL也起到了过滤作用，即导致动态表名SQL不生效
     */
    @SqlParser(filter=true)
    @Select("select * from user ${ew.customSqlSegment}")
    List<User> selectAll(@Param(Constants.WRAPPER) Wrapper<User> wrapper);
}
