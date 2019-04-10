package com.zhangjing.web;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangjing.entity.Actor;
import com.zhangjing.service.ActorService;
import com.zhangjing.util.Result;
import com.zhangjing.util.ResultGenerator;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/actor")
public class ActorController {
    @Resource
    private ActorService actorService;

    @PostMapping
    public Result add(Actor actor) {
        actorService.save(actor);
        return ResultGenerator.genSuccessResult("演员信息上传成功");
    }

    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id) {
        actorService.deleteById(id);
        return ResultGenerator.genSuccessResult("演员信息删除成功");
    }

    @PutMapping
    public Result update(@RequestBody Actor actor) {
        actorService.update(actor);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Actor actor = actorService.findById(id);
        return ResultGenerator.genSuccessResult(actor);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        System.out.println("获取所有演员信息 "+"page:" + page + "," + "size:" + size);
        Condition condition = new Condition(Actor.class);
        condition.setOrderByClause("actorId desc");

        PageHelper.startPage(page, size);
        List<Actor> list = actorService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);

        return ResultGenerator.genSuccessResult(pageInfo);
    }

}
