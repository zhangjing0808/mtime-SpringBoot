package com.zhangjing.service;

import com.zhangjing.entity.UserLike;

public interface LikedService{
    /**
     * 保存点赞记录
     * @param userLike
     * @return
     */
    void save(UserLike userLike);

    /**
     * 查询点赞记录
     * @param likeRemarkid
     * @param likeUserid
     * @return UserLike
     */
    UserLike status(String likeRemarkid, String likeUserid);

    /**
     * 更新点赞记录
     * @param userLike
     * @return
     */
    void update(UserLike userLike);
}
