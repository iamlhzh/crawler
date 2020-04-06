package cn.study.lhzh.crawler.bean;

/**
 * @author 11875
 *
 */
public class Unit {
	private String chapterId;
	private String contentId;
	private String id;
	private String name;
	private String termId;
	private String contentType;

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

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Override
	public String toString() {
		return "Unit [chapterId=" + chapterId + ", contentId=" + contentId + ", id=" + id + ", name=" + name
				+ ", termId=" + termId + ", contentType=" + contentType + "]";
	}



}
