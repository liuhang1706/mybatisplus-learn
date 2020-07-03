package com.liuhang.mybatisplus.mybatisplusday01basic.service.imple;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuhang.mybatisplus.mybatisplusday01basic.dao.UserMapper;
import com.liuhang.mybatisplus.mybatisplusday01basic.entity.User;
import com.liuhang.mybatisplus.mybatisplusday01basic.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
