package com.zhangjing.service.impl;

import com.zhangjing.dao.ActorMapper;
import com.zhangjing.dao.MovieMapper;
import com.zhangjing.entity.Actor;
import com.zhangjing.service.ActorService;
import com.zhangjing.util.ServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ActorServiceImpl extends AbstractService<Actor> implements ActorService {
    @Resource
    private ActorMapper actorMapper;
    @Resource
    private MovieMapper movieMapper;
    //todo 查询电影的演员

    @Override
    public List<Actor> selectByName(String name) {
        List<Actor> actors = actorMapper.selectByName(name);
        if (null == actors || actors.size() == 0) {
            throw new ServiceException("此演员信息未收录");
        }
        return actors;
    }
}
