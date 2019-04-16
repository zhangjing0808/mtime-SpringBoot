package com.zhangjing.web;

import com.zhangjing.entity.Remark;
import com.zhangjing.entity.UserLike;
import com.zhangjing.enums.LikedStatusEnum;
import com.zhangjing.service.LikedService;
import com.zhangjing.service.RemarkService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhangjing.util.DateReplaceUtil;
import com.zhangjing.util.Result;
import com.zhangjing.util.ResultGenerator;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/remark")
public class RemarkController {
    @Resource
    private RemarkService remarkService;

    @Resource
    private LikedService likedService;

    @PostMapping
    public Result add(String content,Integer score, Integer movieId, Integer userId) {
        System.out.println("评论"+content+" "+
                "评分"+score+
                " 电影："+movieId.intValue()+
                " 用户："+userId.intValue());
        remarkService.addRemark(content,score.floatValue(), movieId.intValue(), userId.intValue());
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 查询电影的评论
     * @param id
     * @return
     */
    @GetMapping("/{id}/{userId}")
    public Result movieReamrk(@PathVariable("id") Integer id, @PathVariable("userId") Integer userId) {
        List<Remark> remarks = remarkService.remarkByMovieId(id, userId);
        DateReplaceUtil.remarkDateUtil(remarks);
        return ResultGenerator.genSuccessResult(remarks);
    }

    @GetMapping("/my/{id}")
    public Result userReamrk(@PathVariable Integer id) {
        List<Remark> remarks = remarkService.remarkByUserId(id);
        DateReplaceUtil.remarkDateUtil(remarks);
        return ResultGenerator.genSuccessResult(remarks);
    }

    @GetMapping("/all")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Remark> list = remarkService.remarkAll();
        System.out.println("list:" + list);
        DateReplaceUtil.remarkDateUtil(list);
        PageInfo pageInfo = new PageInfo(list);
        System.out.println("pageInfo:" + pageInfo);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        remarkService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 插入一条点赞信息
     * @param
     * @return
     */
    @PostMapping("/like")
    public Result addUserLike(UserLike userLike) {
        System.out.println("进入addUserLike");
        likedService.save(userLike);
        return ResultGenerator.genSuccessResult("点赞成功！");
    }

    /**
     * 查询评论的点赞信息
     * @param remarkId
     * @param userId
     * @return
     */
    @GetMapping("/like")
    public Result userLikeStatus(String remarkId,String userId) {
        System.out.println("remarkId:" + remarkId);
        System.out.println("userId:" + userId);
        UserLike userLike = likedService.status(remarkId, userId);
        return ResultGenerator.genSuccessResult(userLike);
    }

    @PutMapping("/like")
    public Result update(UserLike userLike) {
        System.out.println("进入update");
        likedService.update(userLike);
        if(userLike.getStatus() == 1) {
            return ResultGenerator.genSuccessResult("点赞成功！");
        }else {
            return ResultGenerator.genSuccessResult("取消点赞成功！");
        }
    }

   /* @PutMapping
    public Result update(@RequestBody Remark remark) {
        remarkService.update(remark);
        return ResultGenerator.genSuccessResult();
    }*/


    /*@GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Remark> list = remarkService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }*/
}
