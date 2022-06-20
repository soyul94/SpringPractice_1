package com.exam.exSpring.bbs;

import java.util.List;

public interface BbsService {

	BbsVO selectBbs(String bbsNo);

	List<BbsVO> selectBbsList(); //ArrayList가 아니라 List라는 인터페이스를 사용

	int insertBbs(BbsVO bbs);

	int updateBbs(BbsVO bbs);

	int deleteBbs(String bbsNo);

}
