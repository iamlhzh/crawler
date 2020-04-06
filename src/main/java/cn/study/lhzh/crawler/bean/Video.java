package cn.study.lhzh.crawler.bean;

public class Video {
	private String id;
	private String name;
	
	private Integer size;
	private String videoUrl;
	private Integer quality;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", name=" + name + ", size=" + size + ", videoUrl=" + videoUrl + ", quality="
				+ quality + "]";
	}

	
}
