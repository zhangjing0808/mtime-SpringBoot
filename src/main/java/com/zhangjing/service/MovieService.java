package com.zhangjing.service;

import com.zhangjing.entity.Movie;

import java.util.List;

public interface MovieService extends Service<Movie> {


	/**
	 * 通过电影名查询
	 * @param name
	 * @return
	 */
	List<Movie> selectByName(String name);

}
