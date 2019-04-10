package com.zhangjing.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangjing.entity.Actor;
import com.zhangjing.entity.Movie;
import com.zhangjing.service.ActorService;
import com.zhangjing.service.MovieService;
import com.zhangjing.util.Result;
import com.zhangjing.util.ResultGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SelectByName {
	@Resource
	private MovieService movieService;
	@Resource
	private ActorService actorService;

	@GetMapping("/movieName/{name}")
	public Result selectMovie(@PathVariable("name") String name,
							  @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "12") Integer size) {
		System.out.println(name);

		if (name == null) {
			return ResultGenerator.genFailResult("搜索内容不能为空");
		}
		PageHelper.startPage(page, size);
		List<Movie> movies = movieService.selectByName(name);
		PageInfo pageInfo = new PageInfo(movies);

		return ResultGenerator.genSuccessResult(pageInfo);
	}

	@GetMapping("/actorName/{name}")
	public Result selectActor(@PathVariable("name") String name,
							  @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "12") Integer size) {
		System.out.println(name);

		PageHelper.startPage(page, size);
		List<Actor> actors = actorService.selectByName(name);
		PageInfo pageInfo = new PageInfo(actors);

		return ResultGenerator.genSuccessResult(pageInfo);
	}
}
