package haunted;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;

/**
 *
 * @author Mal
 */
public class Message {

    private Calendar timeStamp;
    private String text;
    private Player player;

    /**
     * Returns the timestamp when the message was created
     *
     * @return timestamp
     */
    public Calendar getTimeStamp() {
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
            this.timeStamp = Calendar.getInstance();
            this.text = text;
            this.player = player;
        }
    }
    private String timestamp(){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(timeStamp.getTime());
    }

    /**
     * returns this message in a string string will be in this way: "[timestamp]
     * player: text"
     *
     * @return string
     */
    public String toString() {
        return "[" + timestamp() + "]" + " " + this.player.getName() + ": " + this.getText();
    }

}
