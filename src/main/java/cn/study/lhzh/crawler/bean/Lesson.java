package cn.study.lhzh.crawler.bean;

import java.util.Map;

/**
 * s10
 * 
 * @author 11875
 *
 */
public class Lesson {
	private String chapterId;
	private String id;
	private String name;
	private String termId;
	private String contentType;
	
	private Map<String ,Unit>units;

	public String getChapterId() {
		return chapterId;
	}

	public void setChapterId(String chapterId) {
		this.chapterId = chapterId;
	}

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

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public Map<String, Unit> getUnits() {
		return units;
	}

	public void setUnits(Map<String, Unit> units) {
		this.units = units;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	public String toString() {
		return "Lesson [chapterId=" + chapterId + ", id=" + id + ", name=" + name + ", termId=" + termId
				+ ", contentType=" + contentType + ", units=" + units + "]";
	}




}
