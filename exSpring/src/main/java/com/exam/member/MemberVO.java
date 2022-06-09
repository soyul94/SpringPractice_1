package com.exam.member;

public class MemberVO {
	
	private String memId;
	private String memPW;
	private String memName;
	int memPoint;
	
	public MemberVO() {
	}

	public MemberVO(String memId, String memPW, String memName, int memPoint) {
		this.memId = memId;
		this.memPW = memPW;
		this.memName = memName;
		this.memPoint = memPoint;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPW() {
		return memPW;
	}

	public void setMemPW(String memPW) {
		this.memPW = memPW;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public int getMemPoint() {
		return memPoint;
	}

	public void setMemPoint(int memPoint) {
		this.memPoint = memPoint;
	}

	@Override
	public String toString() {
		return "- 회원 ID= " + memId + ",	회원 PW= " + memPW + ",	회원 Name= " + memName + ",	회원 Point= " + memPoint+ "";
	}
	

}
