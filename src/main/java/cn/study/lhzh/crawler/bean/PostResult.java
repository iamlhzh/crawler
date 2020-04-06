package cn.study.lhzh.crawler.bean;

public class PostResult {
	
	private Integer  code;
	private String message;
	private String traceId;
	private boolean sampled;
	private Object result;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTraceId() {
		return traceId;
	}
	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}
	public boolean isSampled() {
		return sampled;
	}
	public void setSampled(boolean sampled) {
		this.sampled = sampled;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "PostResult [code=" + code + ", message=" + message + ", traceId=" + traceId + ", sampled=" + sampled
				+ ", result=" + result + "]";
	}
	
}
