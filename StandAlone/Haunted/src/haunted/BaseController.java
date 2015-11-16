package haunted;

import javafx.scene.Node;

/**
 * 
 * @author jvdwi
 */
public class BaseController implements Controller {

    private Node view;

    /**
     * 
     * @return the view of basecontroller
     */
    @Override
    public Node getView() {
        return view;
    }

    /**
     * sets the view of basecontroller
     * @param view 
     */
    @Override
    public void setView(Node view) {
        this.view = view;
    }

    /**
     * shows the main screen of haunted
     */
    @Override
    public void show() {
        preShowing();
        Haunted.getNavigation().Show(this);
        postShowing();
    }

    /**
     * things to do before showing
     */
    public void preShowing() {

    }

    /**
     * things to do after showing
     */
    public void postShowing() {

    }

}
