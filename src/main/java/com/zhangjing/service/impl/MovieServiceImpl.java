package com.zhangjing.service.impl;

import com.zhangjing.dao.MovieMapper;
import com.zhangjing.entity.Movie;
import com.zhangjing.service.MovieService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class MovieServiceImpl extends AbstractService<Movie> implements MovieService {
    @Resource
    private MovieMapper movieMapper;

    @Override
    public List<Movie> selectByName(String name) {
        return movieMapper.selectByName(name);
    }

}
