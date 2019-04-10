package com.zhangjing.service.impl;

import com.zhangjing.dao.AdminMapper;
import com.zhangjing.entity.Admin;
import com.zhangjing.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class AdminServiceImpl extends AbstractService<Admin> implements AdminService {
    @Resource
    private AdminMapper adminMapper;

}
