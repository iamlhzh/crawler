package cn.study.lhzh.crawler.bean;

import java.util.Map;

/**s16
 * @author 11875
 *
 */
public class Quiz {
private String chapterId;
private String contentId;
private String contentType;//2
private String id;
private String name;
private String termId;
private Map<String ,Unit>units;//null

/**
 * s17
 */
private Test test;

public String getChapterId() {
	return chapterId;
}

public void setChapterId(String chapterId) {
	this.chapterId = chapterId;
}

public String getContentId() {
	return contentId;
}

public void setContentId(String contentId) {
	this.contentId = contentId;
}

public String getContentType() {
	return contentType;
}

public void setContentType(String contentType) {
	this.contentType = contentType;
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

public Test getTest() {
	return test;
}

public void setTest(Test test) {
	this.test = test;
}

@Override
public String toString() {
	return "Quiz [chapterId=" + chapterId + ", contentId=" + contentId + ", contentType=" + contentType + ", id=" + id
			+ ", name=" + name + ", termId=" + termId + ", units=" + units + ", test=" + test + "]";
}



}
