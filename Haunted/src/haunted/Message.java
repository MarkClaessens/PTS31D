package haunted;

import java.sql.Time;

public class Message {

	private Time timeStamp;
	private String text;

        /**
         * Returns the timestamp when the message was created
         * @return timestamp
         */
	public Time getTimeStamp() {
		return this.timeStamp;
	}

        /**
         * Sets the timestamp when the message was created
         * @param timeStamp 
         */
	public void setTimeStamp(Time timeStamp) {
		this.timeStamp = timeStamp;
	}

        /**
         * Return the text contained in the message
         * @return 
         */
	public String getText() {
		return this.text;
	}

        /**
         * Sets the text contained in the message
         * @param text 
         */
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