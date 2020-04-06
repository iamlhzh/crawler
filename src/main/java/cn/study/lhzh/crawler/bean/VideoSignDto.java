package cn.study.lhzh.crawler.bean;

public class VideoSignDto {
private Integer status;
private String videoId;
private Integer duration;
private String videoImgUrl;
private String signature;
private String name;
public Integer getStatus() {
	return status;
}
public void setStatus(Integer status) {
	this.status = status;
}
public String getVideoId() {
	return videoId;
}
public void setVideoId(String videoId) {
	this.videoId = videoId;
}
public Integer getDuration() {
	return duration;
}
public void setDuration(Integer duration) {
	this.duration = duration;
}
public String getVideoImgUrl() {
	return videoImgUrl;
}
public void setVideoImgUrl(String videoImgUrl) {
	this.videoImgUrl = videoImgUrl;
}
public String getSignature() {
	return signature;
}
public void setSignature(String signature) {
	this.signature = signature;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
@Override
public String toString() {
	return "VideoSignDto [status=" + status + ", videoId=" + videoId + ", duration=" + duration + ", videoImgUrl="
			+ videoImgUrl + ", signature=" + signature + ", name=" + name + "]";
}

}
