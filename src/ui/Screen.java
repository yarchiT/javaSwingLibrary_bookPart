package ui;

import utils.DatabaseConnector;

import javax.swing.*;

public class Screen {

    private JFrame frame;
    private ViewManager viewManager;

    public Screen (){
        frame = new JFrame();
        viewManager = new ViewManager(this);

        initUI();
        DatabaseConnector.initDatabaseCredentials();
    }

    public void initUI() {
        frame.setJMenuBar(viewManager.createMenuBar());

        frame.setResizable(false);
        frame.setTitle("Library");
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }


    public void AddPanel (JPanel panel) {
        frame.getContentPane().add(panel);
        frame.revalidate();
    }

    public void displaySearchedBooks(JScrollPane pane){
        frame.add(pane);
        frame.revalidate();
    }

    public void removeSearchedBooks(JScrollPane pane){
        frame.remove(pane);
        frame.revalidate();
    }

}
