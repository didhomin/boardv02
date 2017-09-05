package com.wf.dto;

public class BoardDto {

	/**
	 * seq 글번호
	 * subject 글제목
	 * content 글내용
	 * insertdate 등록일시
	 * updatedate 수정일시*/
	private String seq;
	private String subject;
	private String content;
	private String insertdate;
	private String updatedate;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getInsertdate() {
		return insertdate;
	}
	public void setInsertdate(String insertdate) {
		this.insertdate = insertdate;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	
}
