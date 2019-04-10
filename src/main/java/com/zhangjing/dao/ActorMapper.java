package com.zhangjing.dao;

import com.zhangjing.entity.Actor;
import com.zhangjing.mapper.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ActorMapper extends Mapper<Actor> {
	/**
	 * 按名称查询
	 * @param name
	 * @return
	 */
	@Select("select * from actor" +
			" where nameCn like concat(concat('%',#{name}),'%') " +
			"or nameEn like concat(concat('%',#{name}),'%')")
	List<Actor> selectByName(String name);
}