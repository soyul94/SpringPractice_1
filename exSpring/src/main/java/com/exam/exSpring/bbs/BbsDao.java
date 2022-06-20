package com.exam.exSpring.bbs;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

//@Mapper("boardMapper")전자정부에서 했던 이 형식은 못쓰는건가 ?

@Mapper
public interface BbsDao {

	BbsVO selectBbs(String bbsNo);

	List<BbsVO> selectBbsList(); //ArrayList가 아니라 List라는 인터페이스를 사용

	int insertBbs(BbsVO bbs);

	int updateBbs(BbsVO bbs);

	int deleteBbs(String bbsNo);

}
