package com.exam.exSpring.reply;

import java.util.List;

public interface ReplyService {

	int insertReply(ReplyVO replyVo);

	List<ReplyVO> selectListReply(ReplyVO replyVo);

}
