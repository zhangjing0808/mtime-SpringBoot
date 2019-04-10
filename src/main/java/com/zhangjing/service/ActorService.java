package com.zhangjing.service;


import com.zhangjing.entity.Actor;

import java.util.List;

public interface ActorService extends Service<Actor> {

	/**
	 * 通过演员名查询
	 * @param name
	 * @return
	 */
	List<Actor> selectByName(String name);
}
