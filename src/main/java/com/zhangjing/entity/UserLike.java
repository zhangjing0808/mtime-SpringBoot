package com.zhangjing.entity;

import com.zhangjing.enums.LikedStatusEnum;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UserLike {

    /**
     * 主键id
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键自增
    private Integer id;

    /**
     * 被点赞的评论的id
     * */
    private String likeRemarkid;

    /**
     * 点赞的用户的id
     * */
    private String likeUserid;

    /**
     * 点赞的状态.默认未点赞
     * */
    private Integer status = LikedStatusEnum.UNLIKE.getCode();

    public UserLike() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLikeRemarkid() {
        return likeRemarkid;
    }

    public void setLikeRemarkid(String likeRemarkid) {
        this.likeRemarkid = likeRemarkid;
    }

    public String getLikeUserid() {
        return likeUserid;
    }

    public void setLikeUserid(String likeUserid) {
        this.likeUserid = likeUserid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public UserLike(String likeRemarkid, String likeUserid, Integer status) {
        this.likeRemarkid = likeRemarkid;
        this.likeUserid = likeUserid;
        this.status = status;
    }
}
