package com.exam.exSpring.reply;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyDAO {

	int insertReply(ReplyVO replyVo);

	List<ReplyVO> selectListReply(int repBbsNo);

	int deleteReply(ReplyVO replyVo);

}
