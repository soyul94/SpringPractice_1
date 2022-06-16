package com.exam.exSpring.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service	//스프링 컨테이너에 서비스 객체임을 등록함
public class MemverServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;

	@Override
	public List<MemberVO> selectMemberList() {
		return memberDao.selectMemberList();
	}

	@Override
	public int insertMember(MemberVO member) {
		return memberDao.insertMember(member);
	}

	@Override
	public int deleteMember(String id) {
		return memberDao.deleteMember(id);
	}

	@Override
	public MemberVO selectMember(String memId) {
		return memberDao.selectMember(memId);
	}

	@Override
	public int updateMember(MemberVO member) {
		return memberDao.updateMember(member);
	}

	@Override
	public MemberVO selectLoginMember(MemberVO vo) {
		return memberDao.selectLoginMember(vo);
	}

}
