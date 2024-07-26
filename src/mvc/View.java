package mvc;

import javax.swing.*;

public abstract class View extends JPanel implements Subscriber {
    protected Model model;

    public View(Model model) {
        this.model = model;
        model.subscribe(this);
    }

    abstract public void update();

    public void setView(Model model) {
        this.model.unsubscribe(this);
        this.model = model;
        this.model.subscribe(this);
    }
}
