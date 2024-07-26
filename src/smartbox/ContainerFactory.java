package smartbox;

import mvc.AppFactory;
import mvc.*;

public class ContainerFactory implements AppFactory {
    public Model makeModel() { return new Container(); }
    public String getTitle() { return "Smartbox";}

    @Override
    public String[] getHelp() {
        return new String[] {"Click add to be prompted to add a new application to the SmartBox. Click run to run any SmartBox app that has already been entered. Click Rem to remove any given SmartBox app."};
    }

    @Override
    public String about() {
        return "This program will have the capacity to add, run, and remove various SmartBox apps like a calculator.";
    }

    @Override
    public String[] getEditCommands() {
        return new String[]{"Add", "Rem", "Run"};
    }

    @Override
    public Command makeEditCommand(Model m, String type, Object source) {
        switch(type) {
            case "Add": return new AddCommand(m);
            case "Rem": return new RemCommand(m);
            case "Run": return new RunCommand(m);
            default: return null;
        }
    }

    public View makeView(Model m) {
        return new ContainerView((Container) m);
    }
}
