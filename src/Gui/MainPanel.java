package Gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Saman A.Mirhoseini on 14/12/2016.
 */
public class MainPanel extends JPanel {
    public MainPanel() {
        setLayout(new BorderLayout());
        setSize(1000,980);
        setLocation(0,0);
//        add(new MyTextarea(),BorderLayout);
        setVisible(true);
    }
}
class MyTextarea extends JTextArea{
    public MyTextarea() {
        this.setRows(40);
        this.setColumns(70);
       // setBorder(BorderLayout.CENTER);
    }
}
