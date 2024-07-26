package smartbox;

import mvc.*;

import javax.swing.*;

public class RemCommand extends Command {
    public RemCommand(Model m) {
        super(m);
    }
    @Override
    public void execute() throws Exception {
        Container container = (Container)model;
        String name = JOptionPane.showInputDialog("Input the name of the app in the list that you wish to remove:");
        try {
            container.remComponent(name);
        } catch(ClassNotFoundException e) {
            Utilities.error("An app with that name does not exist in your list.");
        }
    }
}
