package com.exam.exSpring.reply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImple implements ReplyService {

	@Autowired
	private ReplyDAO replyDao;
	
	@Override
	public int insertReply(ReplyVO replyVo) {
		return replyDao.insertReply(replyVo);
	}

	@Override
	public List<ReplyVO> selectListReply(int repBbsNo) {
		return replyDao.selectListReply(repBbsNo);
	}

	@Override
	public int deleteReply(ReplyVO replyVo) {
		return replyDao.deleteReply(replyVo);
	}

}
