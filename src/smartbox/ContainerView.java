package smartbox;
import mvc.*;

import java.beans.PropertyChangeEvent;
import java.util.Collection;

public class ContainerView extends View {

    private java.awt.List components;

    public ContainerView(Model model) {
        super(model);
        components = new java.awt.List(10);
        this.add(components);
    }

    @Override
    public void update() {
        components.removeAll();
        Container container = (Container)model;
        Collection<smartbox.components.Component> appsToShow = container.getComponents();
        for(smartbox.components.Component eachComponent : appsToShow) {
            components.add("" + eachComponent);
        }
        repaint();
    }
}