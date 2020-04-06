package cn.study.lhzh.crawler.bean;

import java.util.List;
import java.util.Map;

/**
 * s3
 * 
 * @author 11875
 *
 */
public class Chapter {
	private String id;
	private String name;
	private String termId;
	private String contentType;
	
	/**
	 * s8
	 */
	private Map<String,Lesson>lessons;
	
	/**
	 * s9
	 */
	private Map<String,Quiz>quizs;

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


	public Map<String, Lesson> getLessons() {
		return lessons;
	}

	public void setLessons(Map<String, Lesson> lessons) {
		this.lessons = lessons;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	public String toString() {
		return "Chapter [id=" + id + ", name=" + name + ", termId=" + termId + ", contentType=" + contentType
				+ ", lessons=" + lessons + "]";
	}


}
