package smartbox;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;

import javax.swing.*;
import mvc.*;

public class ContainerPanel extends AppPanel {
    java.awt.List components;
    public ContainerPanel(AppFactory factory) {
        super(factory);
        // set up controls
        JButton button = new JButton("Add");
        controlPanel.add(button);
        button.addActionListener(this);

        button = new JButton("Rem");
        controlPanel.add(button);
        button.addActionListener(this);

        button = new JButton("Run");
        controlPanel.add(button);
        button.addActionListener(this);
    }

    // this override needed to re-initialize component fields table:
    public void setModel(Model m) {
        super.setModel(m);
        ((Container) m).initContainer(); // restore fields of components
    }

    public static void main(String[] args) {
        AppPanel panel = new ContainerPanel(new ContainerFactory());
        panel.display();
    }
}