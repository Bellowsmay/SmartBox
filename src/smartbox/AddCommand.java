package smartbox;

import mvc.*;

import javax.swing.*;

public class AddCommand extends Command {
    public AddCommand(Model m) {
        super(m);
    }
    @Override
    public void execute() throws Exception {
        try {
            Container container = (Container)model;
            String name = JOptionPane.showInputDialog("Input the name of the app you wish to run in the future:");
            container.addComponent(name);
        } catch(ClassNotFoundException e) {
            Utilities.error("An app with that name does not exist.");
        }
    }
}
