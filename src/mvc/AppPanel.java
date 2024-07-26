package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppPanel extends JPanel implements ActionListener, Subscriber {
    protected AppFactory appfactory;
    protected Model model;
    protected View view;
    protected JPanel controlPanel;
    private JFrame frame;
    public static int FRAME_WIDTH = 900;
    public static int FRAME_HEIGHT = 500;
    public AppPanel(AppFactory factory) {
        appfactory = factory;
        model = appfactory.makeModel();
        view =  appfactory.makeView(model);
        controlPanel = new JPanel();
        controlPanel.setBackground(Color.pink);


        this.setLayout((new GridLayout(1, 2)));

        this.add(controlPanel);
        this.add(view);

        frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle(appfactory.getTitle());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    public void display() {frame.setVisible(true);}

    public void update() {/* override for extensions if needed */}

    // called by file/open and file/new
    public void setModel(Model newModel) {
        this.model.unsubscribe(this);
        this.model = newModel;
        this.model.subscribe(this);
        // view must also unsubscribe then resubscribe:
        view.setView(this.model);
        model.changed();
    }


    public void actionPerformed(ActionEvent e) {
        String cmmd = e.getActionCommand();
        try {
            String[] editCommands = appfactory.getEditCommands();
            for (String c: editCommands) {
                if (cmmd.equals(c)) {
                    appfactory.makeEditCommand(model, cmmd, e.getSource()).execute();
                    return;
                }
            }

            switch (cmmd) {
                case "Save": {
                    Utilities.save(model, model.getUnsavedChanges());
                    break;
                }

                case "Open": {
                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        Model newModel = Utilities.open(model);
                        if (newModel != null) setModel(newModel);
                    }
                    break;
                }

                case "New": {
                    Utilities.saveChanges(model);
                    setModel(appfactory.makeModel());
                    // needed cuz setModel sets to true:
                    model.setUnsavedChanges(false);
                    break;
                }

                case "Quit": {
                    Utilities.saveChanges(model);
                    System.exit(0);
                    break;
                }

                case "About": {
                    Utilities.inform(appfactory.about());
                    break;
                }

                case "Help": {
                    Utilities.inform(appfactory.getHelp());
                    break;
                }

                default: {
                    throw new Exception("Unrecognized command: " + cmmd);
                }
            }

        } catch (Exception ex) {
            Utilities.error(ex);
        }
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", appfactory.getEditCommands(), this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }
}
