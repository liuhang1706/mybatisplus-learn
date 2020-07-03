package com.liuhang.mybatisplus.mybatisplusday01basic.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuhang.mybatisplus.mybatisplusday01basic.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    // @Param(Constants.WRAPPER) Wrapper<User> wrapper 是固定写法
    // 方法在xml映射文件中配置SQL
    List<User> selectAll(@Param(Constants.WRAPPER) Wrapper<User> wrapper);

    // 方法在xml映射文件中配置SQL
    IPage<User> selectUserPage(Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);
}
