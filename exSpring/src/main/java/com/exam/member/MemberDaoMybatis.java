package com.exam.member;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.exam.comm.MyBatisUtil;

/*
	싱글톤(Singeton)패턴
	: 클래스의 인스턴스를 1개만 생성하여 애플리케이션 천제에서 공유하여 사용하고 싶을 때 사용하는 "싱글톤패턴"
	  외부에서 생성자 호출하는 것을 막음 -> 내부에서 인스턴스를 생성 및 보관 -> 외부에서 클래스가 필요할 때 내부에서 만들어 놓은 인스턴스를 제공
	  하는 형태로 구현하여 사용한다.
	+ 스프링을 사용하면 이는 이미 구현되어 있어 별도의 작업없이 싱글톤패턴을 사용할 수 있다. 
*/
public class MemberDaoMybatis implements MemberDao{
	// 모든 메소드에서 쓰기위해 밖으로 꺼냄.
	private SqlSessionFactory sqlSessionFactory= MyBatisUtil.getSqlSessionFactory(); 
	
	//MemberDaoMybatis클래스를 외부에서 생성할 수 없게 만든 뒤 내부에서 생성된 static을 외부에서 가져다 쓸 수 있게 함.
	private static MemberDaoMybatis memberDaoMybatis = new MemberDaoMybatis();
	public static MemberDaoMybatis getMemberDaoMybatis() {return memberDaoMybatis;}
	
	//클래스 외부에서 생성자 호출을 금지 : 생성자를 다른 곳에서 사용하지 못하게 막음
	private MemberDaoMybatis() {}
	
	
/*
	public MemberDaoMybatis() {//생성자.
		//파일이 없으면 에러가 나기 때문에 try-catch해줘야함
		try {
			String resource = "/mybatis/mybatis-config.xml"; //마이바티스 설정파일 위치 (귀찮으니 클래스패스 최상위에 만들기).
			InputStream inputStream = Resources.getResourceAsStream(resource); //마이바티스 설정파일을 읽을 수 있는 입력스트림
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			//마이바티스 설정 파일의 내용대로 마이바티스 본체(SqlSessionFactory)를 생성
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/	
	
	@Override
	public List<MemberVO> selectMemberList() {
		List<MemberVO> list =null; //try안에 선언하면 return이 불가능함.
		//SqlSession만드는 것은 이전에 커넥션 연결과 동일.드라이버 연결하는 것과 동일한 작업
		//마이바티스를 통한 데이터베이스와의 세션(연결)을 가져와서 해당세션을 통해 마이바티스에 등록된 SQL문을 실행
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) { //DB와의 연결을 하나 받아오는 느낌적인 느낌
			  list = sqlSession.selectList("com.exam.member.MemberDao.selectMemberList"); 
			}				//실행할 SQL의 종류에 따라 메소드 선택 //쿼리 실행에 필요한 인자는 ,로 구별후 입력한다.
							//실행할 SQL문이 있는 mapper의 namespace="com.exam.member.MemberDao"뒤에 수행할 쿼리문 id를 붙여줌
		//selectList : 쿼리의 결과가 여러행
		//selectOne : 쿼리의 결과가 단일행
		return list;
	}
	@Override
	public MemberVO selectMember(String memId) {
		MemberVO vo=null;
	
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) { 
			  vo = sqlSession.selectOne("com.exam.member.MemberDao.selectMember",memId);
		}
		return vo;
	}
	
	@Override
	public MemberVO selectLoginMember(MemberVO vo) {
		MemberVO result=null;
		
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) { 
			  result = sqlSession.selectOne("com.exam.member.MemberDao.selectLoginMember",vo);
		}																		// 인자는 1가지만 받을 수 있다.
		return result;
	}

	@Override
	public int insertMember(MemberVO member) {
		int result=0;
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) { //SQL문 실행에 필요한 데이터는 두번째 인자로 전달
			  result = sqlSession.insert("com.exam.member.MemberDao.insertMember",member);
			  sqlSession.commit();  //sqlSession의 insert메소드는 변경된 행의 수를 int형으로 반환한다.
			}			//JDBC에서는 오토커밋이 디폴트지만 mybatis는 직접 커밋을 해줘야한다.		
		return result;
	}
	
	@Override
	public int updateMember(MemberVO member) {
		int result=0;
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) { 
			  result = sqlSession.update("com.exam.member.MemberDao.updateMember",member);
			  sqlSession.commit();  
			}					
		return result;
	}

	@Override
	public int deleteMember(String id) {
		int result=0;
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) { 
			  result = sqlSession.delete("com.exam.member.MemberDao.deleteMember",id);
			  sqlSession.commit();  
			}//tmi: sqlSession의 메소드를 delete를 쓰던 insert를 쓰던 메소드 실행 주소를 실행시켜 수행결과는 동일하나 그래도 쿼리에 맞는 이름의 메소드를 쓰자.		
		return result;
	}

}
