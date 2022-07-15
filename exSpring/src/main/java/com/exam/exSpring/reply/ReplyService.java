package com.exam.exSpring.reply;

import java.util.List;

public interface ReplyService {

	int insertReply(ReplyVO replyVo);

	List<ReplyVO> selectListReply(int repBbsNo);

	int deleteReply(ReplyVO replyVo);

}
