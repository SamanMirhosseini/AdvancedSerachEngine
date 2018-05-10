package Build;

import DataStructure.*;
import DataStructure.BST.BST;
import DataStructure.LinkedList.LinkedList;
import DataStructure.TST.TST;
import DataStructure.Trie.Trie;
import Gui.MyPanel;
//import com.sun.java.util.jar.pack.Instruction;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by Saman A.Mirhoseini on 09/12/2016.
 */
public class Directory {
    public DataStructure ds;
    MyPanel panel;
    File directory;
    LinkedList docs;
    STOP stop;
    public Directory(Type type,File file,MyPanel myPanel) {
        this.directory = file;
        this.panel = myPanel;
        docs = new LinkedList();
        stop = new STOP(new File("StopWords.txt"));
        switch(type){
            case BinarySearchTree:
                ds = new BST();
                break;
            case TernarySearchTree:
                ds = new TST();
                break;
            case Trie:
                ds = new Trie();
                break;
        }
        readFromFiles(this.directory);
    }

    public Directory(DataStructure ds, File directory, STOP stop, MyPanel panel) {
        docs = new LinkedList();
        this.ds = ds;
        this.directory = directory;
        this.stop = stop;
        this.panel = panel;
        readFromFiles(this.directory);
    }

    public void readFromFiles(File mydir){
        long time = System.currentTimeMillis();
        for (File f: mydir.listFiles()){
            if (!f.isDirectory()){
                panel.getOutput().setText(null);
                panel.getOutput().setText("Reading the file "+ f.getName());
                docs.insert(f);
                MakeTheIndex(f);
                panel.getOutput().setText(null);
                panel.getOutput().setText("successfully added to the dictionary "+ f.getName());
            }
            else{
                //ctu
            }
        }
        Long t = new Long((System.currentTimeMillis() - time));
        Float f = ((float)t)/1000;
        sysoutPrint("The process of creating the dictionary took   "   +f.toString()+"   seconds"+"\n" +
                "Total number of  "+ds.nodeCount().toString()+"  exist in the dictionary");
        Integer h = ds.height();
        if(h!=0){
            sysoutPrint("Tree height:   "+h.toString()+"\n");
        }
//        docs = (File[]) temp.toArray();
//        BST b = (BST) ds;
//        b.b(b.getRoot());
//        System.out.println(b.height(b.getRoot().getLeft())- b.height(b.getRoot().getRight()));
//        System.out.println(b.nodeCount());
    }
    public void MakeTheIndex(File file){
        try {
            Scanner s = new Scanner(file);
            s.useDelimiter("\\Z");
            while(s.hasNext()){
                String string = s.next();
                String [] s_array = string.split("[^a-zA-Z]+");
                for(int i = 0;i < s_array.length;i++){
                    String temp = s_array[i].toLowerCase();
                    if(stop.isValid(temp)) {
                       // System.out.println(s_array[i]);
                        ds.insertinDataStructure(temp, file);
                    }
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void sysoutPrint(String string){
        this.panel.getOutput().append("\n");
        this.panel.getOutput().append("-------------------------------------------------------------");
        this.panel.getOutput().append("\n");
        this.panel.getOutput().append(string);
    }

}
