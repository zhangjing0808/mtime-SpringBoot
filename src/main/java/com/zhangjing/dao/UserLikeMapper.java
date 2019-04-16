package com.zhangjing.dao;

import com.zhangjing.entity.UserLike;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserLikeMapper {
    /**
     * 插入一条点赞信息
     * @param userLike
     * @return
     */
    @Insert("INSERT INTO user_like(like_remarkId,like_userId,status) VALUE(#{userLike.likeRemarkid},#{userLike.likeUserid},#{userLike.status})")
    void addUserLike(@Param("userLike") UserLike userLike);

    /**
     * 查询点赞信息
     * @param likeRemarkid
     * @param likeUserid
     * @return UserLike
     */
    @Select("select * from user_like where like_remarkId=#{likeRemarkid} and like_userId=#{likeUserid}")
    UserLike selectStatus(@Param("likeRemarkid") String likeRemarkid, @Param("likeUserid") String likeUserid);

    /**
     * 更新一条点赞信息
     * @param userLike
     * @return
     */
    @Update("UPDATE user_like SET status = #{userLike.status} WHERE like_remarkId = #{userLike.likeRemarkid} and like_userId = #{userLike.likeUserid}")
    void updateUserLike(@Param("userLike") UserLike userLike);


}