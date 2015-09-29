package haunted;

import java.sql.Time;

public class Message {

	private Time timeStamp;
	private String text;

	public Time getTimeStamp() {
		return this.timeStamp;
	}

	public void setTimeStamp(Time timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	/**
	 * 
	 * @param text
	 * @param player
	 */
	public Message(String text, Player player) {
		// TODO - implement Message.Message
		throw new UnsupportedOperationException();
	}

}