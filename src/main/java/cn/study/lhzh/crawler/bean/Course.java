package cn.study.lhzh.crawler.bean;

import java.util.List;
import java.util.Map;

/**s0
 * @author 11875
 *
 */
public class Course {
	private String courseName;
	private String id;
	private String schoolId;
	private String videoId;
	/**
	 * s1
	 */
	private Map<String,Chapter>chapters;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public Map<String, Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(Map<String, Chapter> chapters) {
		this.chapters = chapters;
	}




}
