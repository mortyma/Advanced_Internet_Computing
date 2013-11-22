package simulator;

public class AnalyseRequest {
	private String keyword;
	private int timestamp;
	public AnalyseRequest(int timestamp, String keyword) {
		super();
		this.keyword = keyword;
		this.timestamp = timestamp;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}
	
	public String toString(){
		return "Request ["+this.keyword+"] at timestamp ["+this.timestamp+"]";
	}
}
