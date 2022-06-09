package com.exam.member;

import java.util.ArrayList;
import java.util.List;

public interface MemberDao {

	//DB 테이블을 담은 list를 출력		
	List<MemberVO> selectMemberList(); //ArrayList가 아니라 List라는 인터페이스를 사용

	//입력받은 MemberVO를 DB 테이블에 삽입 
	int insertMember(MemberVO member);

	//입력받은 ID와 동일한 id값이 있는 행을 테이블에서 삭제
	int deleteMember(String id);

	MemberVO selectMember(String memId);

	int updateMember(MemberVO member);

	MemberVO selectLoginMember(MemberVO vo);

}