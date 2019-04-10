package com.zhangjing.service;

import com.zhangjing.entity.User;

import java.util.List;

public interface UserService extends Service<User> {

	/**
	 * 按名字查询用户
	 * @return
	 */
	List<User> selectByName(String name);
}
