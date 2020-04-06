package cn.study.lhzh.crawler.bean;

/**s17
 * @author 11875
 *
 */
public class Test {
private String examId;
private String id;
private String name;
public String getExamId() {
	return examId;
}
public void setExamId(String examId) {
	this.examId = examId;
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
@Override
public String toString() {
	return "Test [examId=" + examId + ", id=" + id + ", name=" + name + "]";
}

}
