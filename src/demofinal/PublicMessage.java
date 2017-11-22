package demofinal;

public class PublicMessage extends Message {

	private long timeout;
	private long startTime;
	private String header;	//header ex: info-10m=bla bla.. or sport-1h=mare meci.. //
	private String topicType;
	
    public PublicMessage(String header, String message) {
        super(message);
        this.header = header;
        setTopic();
        setStartTime();
    }

    private void setStartTime() {
    	this.startTime = System.currentTimeMillis();
	}

	private void setTopic() {
    	String[] headers = header.split("-");
    	setTopicType(headers[0]);
    	setTimeout(headers[1]);
	}

	private void setTimeout(String string) {
		long t = Long.parseLong(string);
		if(t > 0 && t < 1000000) {
			timeout = t;
		} else {
			timeout = 10000;
		}
	}

	private void setTopicType(String type) {
		topicType = type;
	}

	public String getTopicType() {
		return topicType;
	}
	
	public long getTimeout() {
		return timeout+startTime;
	}
	
	public boolean timeoutExpired(long time) {
		return time - (startTime+timeout) > 0 ? true : false;
    }
}
