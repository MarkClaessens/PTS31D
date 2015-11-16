package haunted;

import javafx.scene.Node;

/**
 * 
 * @author jvdwi
 */
public interface Controller {

    /**
     * 
     * @return the view
     */
    Node getView();

    /**
     * sets the view
     * @param view 
     */
    void setView(Node view);

    /**
     * show the view
     */
    void show();
}
