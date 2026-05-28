package com.kh.study.notice.model.dto;

//@Data
//@NoArgsConstructor
public class NoticeDto {


	private String noticeTitle;
	private String noticeWriter;
	private String noticeContent;
	private String createDate;
	

	public NoticeDto() {
		super();
	}
	
	

	public NoticeDto(String noticeTitle, String noticeWriter, String noticeContent, String createDate, Long noticeNo) {
		super();
		this.noticeTitle = noticeTitle;
		this.noticeWriter = noticeWriter;
		this.noticeContent = noticeContent;
		this.createDate = createDate;
		this.noticeNo = noticeNo;
	}



	public NoticeDto(Long noticeNo, String noticeTitle, String noticeContent, String noticeWriter) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeWriter = noticeWriter;
	}

	public Long getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(Long noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticeWriter() {
		return noticeWriter;
	}

	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}
	private Long noticeNo;
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "NoticeDto [noticeTitle=" + noticeTitle + ", noticeWriter=" + noticeWriter + ", noticeContent="
				+ noticeContent + ", createDate=" + createDate + ", noticeNo=" + noticeNo + "]";
	}



	
	
	
}
