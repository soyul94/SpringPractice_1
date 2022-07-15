package com.exam.exSpring.reply;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

//댓글 기능을 위한 테이블
public class ReplyVO {
	private int repNo ; 		//댓글의 번호
	private int repBbsNo ;		//댓글이 위치한 게시글의 번호
	private String repWriter ;	//댓글 작성자
	//Date형식을 json으로 바꾸면 값이 알 수 없게 바뀌기 때문에 어노테이션으로 형식을 지정해주는 것
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date repRegDate ;	//댓글 작성일
	private String repContent ;	//댓글 내용
	
	
	public int getRepNo() {
		return repNo;
	}
	public void setRepNo(int repNo) {
		this.repNo = repNo;
	}
	public int getRepBbsNo() {
		return repBbsNo;
	}
	public void setRepBbsNo(int repBbsNo) {
		this.repBbsNo = repBbsNo;
	}
	public String getRepWriter() {
		return repWriter;
	}
	public void setRepWriter(String repWriter) {
		this.repWriter = repWriter;
	}
	public Date getRepRegDate() {
		return repRegDate;
	}
	public void setRepRegDate(Date repRegDate) {
		this.repRegDate = repRegDate;
	}
	public String getRepContent() {
		return repContent;
	}
	public void setRepContent(String repContent) {
		this.repContent = repContent;
	}
	
}
