package Gui;

import Build.MainEvent;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Saman A.Mirhoseini on 10/12/2016.
 */
public class MyFrame extends JFrame {
    MyPanel myPanel;
    MainPanel mainPanel;
    public MyFrame(MainEvent mainEvent){
        mainPanel= new MainPanel();
        myPanel = new MyPanel(mainEvent);
        setLayout(new BorderLayout());
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(1000, 960);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocation(d.width/2 - 420,d.height/2 - 540);
        this.getContentPane().add(myPanel);
        this.setVisible(true);
        this.addKeyListener(myPanel);
    }

    public MyPanel getMyPanel() {
        return myPanel;
    }

    public void setMyPanel(MyPanel myPanel) {
        this.myPanel = myPanel;
    }
}
