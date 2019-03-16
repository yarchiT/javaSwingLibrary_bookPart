package ui;

import java.awt.*;

public class App{

    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            Screen screen = new Screen();
            screen.initUI();
        });
    }

}
