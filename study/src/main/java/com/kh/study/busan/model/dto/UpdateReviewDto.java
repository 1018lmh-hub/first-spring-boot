package com.kh.study.busan.model.dto;

public class UpdateReviewDto {
	private String content;
	private String updateContent;
	
	
	
	public UpdateReviewDto() {
		super();
	}



	public UpdateReviewDto(String content, String updateContent) {
		super();
		this.content = content;
		this.updateContent = updateContent;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getUpdateContent() {
		return updateContent;
	}



	public void setUpdateContent(String updateContent) {
		this.updateContent = updateContent;
	}
	
	

}
