package cn.study.lhzh.crawler.bean;

public class Result {
	private Integer  code;
	private String lessonUnitId;
	private String lessonUnitName;
	private String contentType;
	private VideoSignDto videoSignDto;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getLessonUnitId() {
		return lessonUnitId;
	}
	public void setLessonUnitId(String lessonUnitId) {
		this.lessonUnitId = lessonUnitId;
	}
	public String getLessonUnitName() {
		return lessonUnitName;
	}
	public void setLessonUnitName(String lessonUnitName) {
		this.lessonUnitName = lessonUnitName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public VideoSignDto getVideoSignDto() {
		return videoSignDto;
	}
	public void setVideoSignDto(VideoSignDto videoSignDto) {
		this.videoSignDto = videoSignDto;
	}
	@Override
	public String toString() {
		return "Result [code=" + code + ", lessonUnitId=" + lessonUnitId + ", lessonUnitName=" + lessonUnitName
				+ ", contentType=" + contentType + ", videoSignDto=" + videoSignDto + "]";
	}
	
	
}
