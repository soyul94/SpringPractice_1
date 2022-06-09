package com.exam.member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOJdbc implements MemberDao {
	String url = "jdbc:oracle:thin:@localhost:1521:xe";	//데이터베이스 서버 주소
	String user = "web"; 		//DB사용자 ID				  
	String password = "web1234";//DB사용자 비밀번호
	
	//초기화 블럭 : 객체 생성시 한번만 작동함. 이렇게 말고 생성자 안에 추가해서 하는 방식도 있음.
//	{
//		try {
//		Class.forName("oracle.jdbc.OracleDriver");	
//		} 
//		catch (ClassNotFoundException e){ 
//		e.printStackTrace();		  
//		}
//	}//프로그램의 전체에서 단 한번만 실행되면 되는데 이렇게 클래스에 들어 있으면 그렇지 않음.
	
	//DB 테이블을 담은 list를 출력		
	@Override
	public  List<MemberVO> selectMemberList() {
		String id;	String pw;	String name;	int point;
		MemberVO member;
		String all_select_SQL= "select MEM_ID, MEM_PW, MEM_NAME, MEM_POINT from member_table";
		ArrayList<MemberVO> list= new ArrayList<MemberVO>(); 
		
		try(							   													
			Connection conn = DriverManager.getConnection(url, user, password); 										
			PreparedStatement pre_state = conn.prepareStatement(all_select_SQL);																
			ResultSet result = pre_state.executeQuery(); 
		){	
				while (result.next()) {				
					id 	= result.getString("mem_id"); 
					pw	= result.getString("mem_pw");
					name  = result.getString("mem_name");
					point 	= result.getInt("mem_point"); 
					member= new MemberVO(id,pw,name,point);
					list.add(member);
				}				
		}
		catch(SQLException e) {																   
			e.printStackTrace();	
		}		
		return list;	
	}
	
	//입력받은 MemberVO를 DB 테이블에 삽입 
	@Override
	public int insertMember(MemberVO member) {
		String inset_SQL= "INSERT INTO MEMBER_TABLE(MEM_ID, MEM_PW, MEM_NAME, MEM_POINT) "
				 +"VALUES(?, ?, ?, ? )";
		int num;	
		try(							   
			Connection conn = DriverManager.getConnection(url, user, password);										
			PreparedStatement pre_state = conn.prepareStatement(inset_SQL);																
		){	
			pre_state.setString(1, member.getMemId());		
			pre_state.setString(2, member.getMemPW());		
			pre_state.setString(3, member.getMemName());	
			pre_state.setInt(4, member.getMemPoint());		
				
			num = pre_state.executeUpdate(); 
		}
		catch(SQLException e) {			
			num=0;
			e.printStackTrace();	
		}
		return num;
	}

	//입력받은 ID와 동일한 id값이 있는 행을 테이블에서 삭제
	@Override
	public int deleteMember(String id) {
		String delete_SQL= "delete from member_table where MEM_ID = ?";
		int num;
		try(							   
			Connection conn = DriverManager.getConnection(url, user, password);										
			PreparedStatement pre_state = conn.prepareStatement(delete_SQL);																
		){		
			pre_state.setString(1, id);		
			num = pre_state.executeUpdate(); 			
		}
		catch(SQLException e) {	
			num=0;
			e.printStackTrace();	
		}
		return num;
	}

	@Override
	public MemberVO selectMember(String memId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberVO selectLoginMember(MemberVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}//class끝
