package com.exam.exSpring.member;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//servlet-context.xml을 통해 스프링 컨테이너에 등록하였기 때문에 해당 클래스는 더 이상 사용하지 않음.

//@Repository //스프링에 등록하기
public class MemberDaoMybatis implements MemberDao{
	
//	@Autowired //스프링이 자동으로 얘랑 매칭해줌
//	private SqlSessionFactory sqlSessionFactory; 
	@Autowired
	private SqlSession session;
	
	@Override
	public List<MemberVO> selectMemberList() {
//		List<MemberVO> list =null; //try안에 선언하면 return이 불가능함.		
//		list = session.selectList("com.exam.member.MemberDao.selectMemberList"); 	
//		return list;
		return session.selectList("com.exam.exSpring.member.MemberDao.selectMemberList"); 	
	}
	
	@Override
	public MemberVO selectMember(String memId) {
//		MemberVO vo=null;
//		vo = session.selectOne("com.exam.member.MemberDao.selectMember",memId);
//		return vo;
		return session.selectOne("com.exam.exSpring.member.MemberDao.selectMember",memId);
	}
	
	@Override
	public MemberVO selectLoginMember(MemberVO vo) {
//		MemberVO result=null;
//		result = session.selectOne("com.exam.member.MemberDao.selectLoginMember",vo);
//		return result;
		return session.selectOne("com.exam.exSpring.member.MemberDao.selectLoginMember",vo);
	}

	@Override
	public int insertMember(MemberVO member) {
//		int result=0;
//		result = session.insert("com.exam.member.MemberDao.insertMember",member);
//		return result;
		return session.insert("com.exam.exSpring.member.MemberDao.insertMember",member);
	}
	
	@Override
	public int updateMember(MemberVO member) {
//		int result=0;
//		result = session.update("com.exam.member.MemberDao.updateMember",member);
//		return result;
		return session.update("com.exam.exSpring.member.MemberDao.updateMember",member);
	}

	@Override
	public int deleteMember(String id) {
//		int result=0;
//		result = session.delete("com.exam.member.MemberDao.deleteMember",id);
//		return result;
		return session.delete("com.exam.exSpring.member.MemberDao.deleteMember",id);
	}

}
