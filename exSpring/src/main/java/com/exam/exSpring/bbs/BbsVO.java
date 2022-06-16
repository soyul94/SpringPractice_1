package com.exam.exSpring.bbs;

import java.util.Date;

//2022년 06월 16일 과제
//게시글 목록(글번호, 글제목, 작성자, 작성일) 	/bbs/list.do
//게시글 추가(글번호, 글제목, 작성자) 		/bbs/add.do
//게시글 변경(제목, 내용) 				/bbs/edit.do
//게시글 삭제(변경화면에서 삭제버튼 클릭 시) 	/bbs/delete.do
//위 기능을 수행하도록 BbsController.java, BbsService.java, BbsServiceImpl.java, BbsDao.java, 
//				 BbsMapper.xml,
//				 bbsList.jsp, bbsAdd.jsp, bbsEdit.jsp,

public class BbsVO {
	private int bbsNo ;
	private String bbsTitle ;
	private String bbsContent ;
	private String bbsWriter ;
	private Date bbsRegDate;
	private int bbsCount ;
	
	
	public int getBbsNo() {
		return bbsNo;
	}
	public void setBbsNo(int bbsNo) {
		this.bbsNo = bbsNo;
	}
	public String getBbsTitle() {
		return bbsTitle;
	}
	public void setBbsTitle(String bbsTitle) {
		this.bbsTitle = bbsTitle;
	}
	public String getBbsContent() {
		return bbsContent;
	}
	public void setBbsContent(String bbsContent) {
		this.bbsContent = bbsContent;
	}
	public String getBbsWriter() {
		return bbsWriter;
	}
	public void setBbsWriter(String bbsWriter) {
		this.bbsWriter = bbsWriter;
	}
	public Date getBbsRegDate() {
		return bbsRegDate;
	}
	public void setBbsRegDate(Date bbsRegDate) {
		this.bbsRegDate = bbsRegDate;
	}
	public int getBbsCount() {
		return bbsCount;
	}
	public void setBbsCount(int bbsCount) {
		this.bbsCount = bbsCount;
	}
}
