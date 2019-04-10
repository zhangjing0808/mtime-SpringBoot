package com.zhangjing.dao;

import com.zhangjing.entity.User;
import com.zhangjing.mapper.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends Mapper<User> {
	/**
	 * 按名字,手机号查询用户
	 * @param name
	 * @return
	 */
	@Select("select * from user where " +
			"nickName like concat(concat('%',#{name}),'%') " +
			"or number like concat(concat('%',#{name}),'%') ")
	List<User> selectByName(String name);
}