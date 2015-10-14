package haunted;

import java.sql.Time;
import java.time.LocalTime;

/**
 *
 * @author Mal
 */
public class Message {

    private Time timeStamp;
    private String text;
    private Player player;

    /**
     * Returns the timestamp when the message was created
     *
     * @return timestamp
     */
    public Time getTimeStamp() {
        return this.timeStamp;
    }

    /**
     * Returns the text contained in the message
     *
     * @return
     */
    public String getText() {
        return this.text;
    }

    public Player getPlayer() {
        return this.player;
    }

    /**
     *
     * @param text
     * @param player
     */
    public Message(String text, Player player) {
        if (!text.isEmpty()) {
            this.timeStamp = Time.valueOf(LocalTime.MIN);
            this.text = text;
            this.player = player;
        }
    }

    /**
     * returns this message in a string string will be in this way: "[timestamp]
     * player: text"
     *
     * @return string
     */
    public String toString() {
        return "[" + this.timeStamp.toString() + "]" + " " + this.player.getName() + ": " + this.getText();
    }

}
