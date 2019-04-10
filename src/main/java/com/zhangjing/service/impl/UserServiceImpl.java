package com.zhangjing.service.impl;

import com.zhangjing.dao.UserMapper;
import com.zhangjing.entity.User;
import com.zhangjing.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> selectByName(String name) {
        return userMapper.selectByName(name);
    }
}
