package Gui;

import Build.MainEvent;
import DataStructure.Stack.Node;
import DataStructure.Type;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import static java.awt.Component.BOTTOM_ALIGNMENT;

/**
 * Created by Saman A.Mirhoseini on 10/12/2016.
 */
public class MyPanel extends JPanel implements KeyListener,ActionListener {
    JTextField command,filePath;
    Font commandFont;
    MainEvent mainEvent;
    JFileChooser fileBrowser;
    JButton insertCommand,Browse;
    JComboBox dataStructutreType;
    JButton exit,build,clear;
    JOptionPane ds;
    JTextArea output;
    JScrollPane jScrollPane ;
    MyMouselistener myMouselistener = new MyMouselistener(this);
    MykeyListener mykeyListener = new MykeyListener(this);
    public MyPanel(MainEvent mainEvent) {
        this.addKeyListener(this);
        this.mainEvent = mainEvent;
        commandFont = new Font("DIALOG",0,18);
        setLayout(null);
        setSize(1000,980);
        setLocation(0, 0);
        setBackground(Color.YELLOW);
        output = new JTextArea(20,20);
       // output.setEditable(false);
        output.setFont(commandFont);
        output.setEditable(false);
        output.setLocation(35,124);
        //output.setRows(40);
        jScrollPane = new JScrollPane(output,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setLocation(35,124);
        jScrollPane.setSize(920,600);
        DefaultCaret caret = (DefaultCaret)output.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        //jScrollPane.setHorizontalScrollBar(JScrollpane.);
        this.add(jScrollPane);
//        this.add(output);
        command = new JTextField();
        insertCommand = new JButton();
        insertCommand.setSize(100,60);
        insertCommand.addMouseListener(myMouselistener);
        insertCommand.setLocation(855,40);
        insertCommand.setText("Enter");
        this.add(insertCommand);
        command.setFont(commandFont);
        command.setSize(800,60);
        command.addKeyListener(mykeyListener);
//        command.setBackground(Color.BLACK);
        command.setColumns(300);
        command.setLocation(35,40);
        this.add(command);
        command.setEnabled(true);
        command.setVisible(true);
        Browse = new JButton();
        Browse.setText("Browse");
        Browse.setSize(100,60);
        Browse.setLocation(855,740);
        Browse.addActionListener(this);
        this.add(Browse);
        Browse.addMouseListener(myMouselistener);
        Browse.setVisible(true);
        fileBrowser = new JFileChooser();
        fileBrowser.setLocation(0,0);
        fileBrowser.setSize(600,600);
        fileBrowser.setVisible(true);
        fileBrowser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileBrowser.setAcceptAllFileFilterUsed(false);
        filePath = new JTextField();
        filePath.setSize(640,60);
        filePath.setLocation(195,740);
        filePath.setFont(commandFont);
        this.add(filePath);
        this.makeButtonsReady();
        repaint();
        setVisible(true);

    }
    public void makeButtonsReady(){
        build = new JButton();
        clear = new JButton();
        exit = new JButton();
        build.setSize(300,60);
        build.setText("Build");
        clear.setSize(300,60);
        clear.setText("Clear");
        exit.setSize(310,60);
        exit.setText("Exit");
        build.setLocation(35,820);
        clear.setLocation(340,820);
        exit.setLocation(645,820);
        build.addMouseListener(myMouselistener);
        clear.addMouseListener(myMouselistener);
        exit.addMouseListener(myMouselistener);
        this.add(build);
        this.add(clear);
        this.add(exit);
        ds = new JOptionPane();
        dataStructutreType = new JComboBox();
        dataStructutreType.setLocation(35,740);
        dataStructutreType.setSize(160,60);
        dataStructutreType.addItem(Type.BinarySearchTree);
        dataStructutreType.addItem(Type.TernarySearchTree);
        dataStructutreType.addItem(Type.Trie);
        dataStructutreType.addItem(Type.Hash);
        this.add(dataStructutreType);
    }

    public JButton getBrowse() {
        return Browse;
    }

    public void setBrowse(JButton browse) {
        Browse = browse;
    }

    public JTextField getFilePath() {
        return filePath;
    }

    public void setFilePath(JTextField filePath) {
        this.filePath = filePath;
    }

    public JTextArea getOutput() {
        return output;
    }

    public void setOutput(JTextArea output) {
        this.output = output;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("dxghfg");
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
            System.out.print("dgfhjhj");
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public JScrollPane getjScrollPane() {
        return jScrollPane;
    }

    public void setjScrollPane(JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
    }

    public JButton getInsertCommand() {
        return insertCommand;
    }

    public void setInsertCommand(JButton insertCommand) {
        this.insertCommand = insertCommand;
    }

    public MainEvent getMainEvent() {
        return mainEvent;
    }

    public void setMainEvent(MainEvent mainEvent) {
        this.mainEvent = mainEvent;
    }

    public Font getCommandFont() {
        return commandFont;
    }

    public void setCommandFont(Font commandFont) {
        this.commandFont = commandFont;
    }

    public JTextField getCommand() {
        return command;
    }

    public void setCommand(JTextField command) {
        this.command = command;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(fileBrowser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            filePath.setText(fileBrowser.getSelectedFile().getPath());
        }
    }
}
class MyMouselistener implements MouseListener{
    MyPanel panel;

    public MyMouselistener(MyPanel panel) {
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(panel.insertCommand)) {
            String s = panel.command.getText();
            System.out.println(s);
            panel.mainEvent.getPrevCommands().push(s);
            panel.command.setText(null);
            panel.mainEvent.processCommand(s);
        }
        else if(e.getSource().equals(panel.exit)){
            System.exit(0);
        }
        else if(e.getSource().equals(panel.clear)){
            panel.command.setText(null);
            panel.filePath.setText(null);
        }
        else if(e.getSource().equals(panel.build)){
            File file = new File(panel.getFilePath().getText());
            Type type = (Type) panel.dataStructutreType.getSelectedItem();
            panel.mainEvent.setCurrentSavingmethod(type);
            panel.getFilePath().setText(null);
            (new Thread(new adder(panel.getMainEvent(),file))).start();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
class adder implements Runnable{
    MainEvent m;
    File file;
    public adder(MainEvent m,File file) {
        this.m = m;
        this.file = file;
    }

    @Override
    public void run() {
        m.handell(file);
    }
}
class MykeyListener implements KeyListener{
    MyPanel panel;

    public MykeyListener(MyPanel panel) {
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            String s = panel.command.getText();
            System.out.println(s);
            panel.mainEvent.getPrevCommands().push(s);
            panel.command.setText(null);
            panel.mainEvent.processCommand(s);
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP){
            if(!panel.mainEvent.getPrevCommands().isEmpty()&&panel.mainEvent.getPrevCommands().getRoot().getNext()!=null) {
                Node temp = panel.mainEvent.getPrevCommands().pop();
                panel.mainEvent.getForwCommands().push(panel.mainEvent.currentCommand);
                panel.mainEvent.currentCommand = temp.getData();
                panel.getCommand().setText(temp.getData());
            }
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            if(!panel.mainEvent.getForwCommands().isEmpty()&&panel.mainEvent.getForwCommands().getRoot().getNext()!=null) {
                Node temp = panel.mainEvent.getForwCommands().pop();
                panel.mainEvent.getPrevCommands().push(panel.mainEvent.currentCommand);
                panel.mainEvent.currentCommand = temp.getData();
                panel.getCommand().setText(temp.getData());
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
