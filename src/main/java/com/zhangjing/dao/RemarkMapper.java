package com.zhangjing.dao;

import com.zhangjing.entity.Remark;
import org.apache.ibatis.annotations.*;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface RemarkMapper  {

	/**
	 * 插入一条评论
	 * @param
	 * @return
	 */
	@Insert("INSERT INTO remark(content) VALUE(#{remark.content})")
	@Options(useGeneratedKeys = true, keyProperty = "remark.remarkid")
	void addRemark(@Param("remark") Remark remark);

	/**
	 * 更新关系表
	 * @param remarkId
	 * @param movieId
	 * @param userId
	 * @return
	 */
	@Insert("INSERT INTO umr(remarkId,movieId,userId) VALUE(#{remarkId},#{movieId},#{userId})")
	boolean addUmr(@Param("remarkId") int remarkId,@Param("movieId") int movieId,@Param("userId") int userId);

	/**
	 * 查询电影的评论
	 * @param
	 * @return RemarkMap结果集
	 */
	List<Remark> selectRemarkByMovie(@Param("movieId") int movieId,@Param("likeuserId") int likeuserId);

	/**
	 * 查询我的评论
	 * @param id
	 * @return RemarkMap结果集
	 */
	List<Remark> selectRemarkByUser(int id);

	/**
	 * 所有评论
	 * @return
	 */
	List<Remark> selectAll();

	/**
	 * 统计电影评论数量
	 * @param movieId
	 * @return
	 */
	@Select("select count(*) from umr where movieId=#{id}")
	int count(@Param("id") int movieId);

	@Delete("delete from remark where remarkId=#{remarkId}")
	void deleteById(@Param("remarkId") int remarkId);

}