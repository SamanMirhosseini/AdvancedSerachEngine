package Build;

import DataStructure.*;
import DataStructure.BST.BST;
import DataStructure.Hash.Hash;
import DataStructure.LinkedList.LinkedList;
import DataStructure.LinkedList.Node;
import DataStructure.Stack.Stack;
import DataStructure.TST.TST;
import DataStructure.Trie.Trie;
import Gui.MyPanel;

import java.io.File;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Created by Saman A.Mirhoseini on 15/12/2016.
 */
public class MainEvent {
    Vector <Directory> directories;
    DataStructure dictioary;
    Type currentSavingmethod;
    STOP stop;
    MyPanel panel;
    Directory currentDirecrtory;
    LinkedList allFiles;
    Stack prevCommands;
    Stack forwCommands;
    public String currentCommand;
    public MainEvent(Type type,MyPanel myPanel) {
        this.currentSavingmethod = type;
        prevCommands = new Stack();
        forwCommands = new Stack();
        allFiles = new LinkedList();
        directories = new Vector<Directory>();
        stop = new STOP(new File("StopWords.txt"));
        panel = myPanel;
        if(type!=null) {
            switch (currentSavingmethod) {
                case BinarySearchTree:
                    dictioary = new BST();
                    break;
                case TernarySearchTree:
                    dictioary = new TST();
                    break;
                case Trie:
                    dictioary = new Trie();
                    break;
            }
        }
    }
    public void addThisDirectorytoTheDictionary(File file){
        panel.getCommand().setEnabled(false);
        panel.getFilePath().setEnabled(false);
        panel.getBrowse().setEnabled(false);
        Directory directory = new Directory(dictioary,file,stop,panel);
        directories.add(directory);
        currentDirecrtory = directory;
        panel.getCommand().setEnabled(true);
        panel.getFilePath().setEnabled(true);
        panel.getBrowse().setEnabled(true);
    }
    public void handell(File file){
        addThisDirectorytoTheDictionary(file);
    }
    public void sysoutPrint(String string){
        this.panel.getOutput().append("\n");
        this.panel.getOutput().append("-------------------------------------------------------------");
        this.panel.getOutput().append("\n");
        this.panel.getOutput().append(string);
    }
    public void processCommand(String command){
//        try {
            StringTokenizer commandWords = new StringTokenizer(command);
            String firstPartoftheCommand = commandWords.nextToken();
            String secondPartoftheCommand, thirdPartoftheCommand;
            if (firstPartoftheCommand.compareToIgnoreCase("search") == 0) {
                Long time = new Long(System.currentTimeMillis());
                secondPartoftheCommand = commandWords.nextToken();
                if (secondPartoftheCommand.compareToIgnoreCase("-w") == 0) {
                    thirdPartoftheCommand = command.substring(command.indexOf('"', 0) + 1, command.lastIndexOf('"'));
                    StringTokenizer wordAnalyser = new StringTokenizer(thirdPartoftheCommand);
                    if (wordAnalyser.countTokens() > 1) {
                        sysoutPrint("Wrong command: Search -w is for searching a single token");
                    } else {
                        String temp = dictioary.searchinDataStructure(wordAnalyser.nextToken());
                        sysoutPrint(temp);
                    }
                }
                else if (secondPartoftheCommand.compareToIgnoreCase("-s") == 0){
                    boolean found = false;
                    thirdPartoftheCommand = command.substring(command.indexOf('"', 0) + 1, command.lastIndexOf('"'));
                    StringTokenizer wordAnalyser = new StringTokenizer(thirdPartoftheCommand);
                    Vector <Vector<String>> wordSets = new Vector<Vector<String>>();
                    while(wordAnalyser.hasMoreTokens()){
                        String ui = wordAnalyser.nextToken();
                        System.out.println(ui);
                        if(stop.isValid(ui)) {
                            LinkedList temp = dictioary.searcher(ui);
                            if (temp != null) {
                                if (!temp.isEmpty()) {
                                    found = true;
                                    Vector<String> filenames = new Vector<String>();
                                    for (Node n = temp.getRoot(); n != null; n = n.getNext()) {
                                        filenames.add(n.getData().getName());
                                    }
                                    wordSets.add(filenames);
                                }
                            } else {
                                found = false;
                            }
                        }
                    }
                    if(found) {
                        String printable = "Search result for the query " + command + ":\n";
                        Vector<String> answer = grandInterSect(wordSets);
                        for (String s : answer) {
                            printable += s + "\n";
                        }
                        sysoutPrint(printable);
                    }
                    else{
                        sysoutPrint("this query doesn't match any of the files");
                    }
                }
                Long t = new Long((System.currentTimeMillis() - time));
                Float f = ((float)t)/1000;
                sysoutPrint("The search process took:  "+f.toString()+"seconds\n");
            }
            else if(firstPartoftheCommand.compareToIgnoreCase("list")==0){
                secondPartoftheCommand = commandWords.nextToken();
                if(secondPartoftheCommand.compareToIgnoreCase("-l")==0){
                    sysoutPrint("List of all files in all of the directories\n");
                    for(Directory d:directories){
                        panel.getOutput().append("In the Directory  "+d.directory.getPath()+"  :\n");
                        panel.getOutput().append(d.docs.makeStringprintable("   "));
                        sysoutPrint(" ");
                    }
                }
                else if(secondPartoftheCommand.compareToIgnoreCase("-f")==0){
                    sysoutPrint("List of all files in the current Directory:  "+currentDirecrtory.directory.getPath()+"\n");
                    panel.getOutput().append(currentDirecrtory.docs.makeStringprintable("   "));
                    sysoutPrint(" ");
                }
                else if(secondPartoftheCommand.compareToIgnoreCase("-w")==0){
                    sysoutPrint("list of words\n");
                    panel.getOutput().append(dictioary.travelAll());
                }
                panel.getOutput().append("\n");
            }
            else if(firstPartoftheCommand.compareToIgnoreCase("add")==0){
                thirdPartoftheCommand = command.substring(command.indexOf('<', 0) + 1, command.lastIndexOf('>'));
                StringTokenizer fileNameAnalyzer = new StringTokenizer(thirdPartoftheCommand);
                String filePath = currentDirecrtory.directory.getPath()+"\\";
                while(fileNameAnalyzer.hasMoreTokens()){
                    filePath += fileNameAnalyzer.nextToken();
                }
                File file = new File(filePath);
                currentDirecrtory.MakeTheIndex(file);
                sysoutPrint(file.getPath()+" was succesfully added to the dictionary -> \n"+
                "Total number of words in the dictionary"+"  "+dictioary.nodeCount().toString());
            }
            else if(firstPartoftheCommand.compareToIgnoreCase("del")==0){
                thirdPartoftheCommand = command.substring(command.indexOf('<', 0) + 1, command.lastIndexOf('>'));
                StringTokenizer fileNameAnalyzer = new StringTokenizer(thirdPartoftheCommand);
                String filePath = currentDirecrtory.directory.getPath()+"\\";
                while(fileNameAnalyzer.hasMoreTokens()){
                    filePath += fileNameAnalyzer.nextToken();
                }
                File file = new File(filePath);
                currentDirecrtory.ds.deleteThisFile(file);
                sysoutPrint(file.getPath()+" was succesfully removed from the dictionary -> \n");
            }
            else if(firstPartoftheCommand.compareToIgnoreCase("update")==0){
                thirdPartoftheCommand = command.substring(command.indexOf('<', 0) + 1, command.lastIndexOf('>'));
                StringTokenizer fileNameAnalyzer = new StringTokenizer(thirdPartoftheCommand);
                String filePath = currentDirecrtory.directory.getPath()+"\\";
                while(fileNameAnalyzer.hasMoreTokens()){
                    filePath += fileNameAnalyzer.nextToken();
                }
                File file = new File(filePath);
                currentDirecrtory.ds.deleteThisFile(file);
                currentDirecrtory.MakeTheIndex(file);
                sysoutPrint("file  "+ file.getPath()+ "  was succesfully updated \n");
            }
//        }
//        catch (Exception e){
//            sysoutPrint("Wrong command");
//        }
    }

    public DataStructure getDictioary() {
        return dictioary;
    }

    public void setDictioary(DataStructure dictioary) {
        this.dictioary = dictioary;
    }

    public MyPanel getPanel() {
        return panel;
    }

    public void setPanel(MyPanel panel) {
        this.panel = panel;
    }
  //  public void

    public Stack getForwCommands() {
        return forwCommands;
    }

    public void setForwCommands(Stack forwCommands) {
        this.forwCommands = forwCommands;
    }

    public Stack getPrevCommands() {
        return prevCommands;
    }

    public void setPrevCommands(Stack prevCommands) {
        this.prevCommands = prevCommands;
    }
    public Vector<String> intersect (Vector<String> s1,Vector<String> s2){
        Vector <String> temp = new Vector<String>();
        for(int i = 0;i < s1.size();i++){
            for(int j = 0;j < s2.size();j++){
                if(s1.get(i).compareToIgnoreCase(s2.get(j))==0){
                    temp.add(s1.get(i));
                }
            }
        }
        return temp;
    }
    public Vector<String> grandInterSect(Vector<Vector<String>>sets){
        Vector <String> temp = sets.get(0);
        for(int i = 0;i <sets.size();i++){
            temp = intersect(temp,sets.get(i));
        }
        return temp;
    }

    public Type getCurrentSavingmethod() {
        return currentSavingmethod;
    }

    public void setCurrentSavingmethod(Type currentSavingmethod) {
        switch (currentSavingmethod) {
            case BinarySearchTree:
                dictioary = new BST();
                break;
            case TernarySearchTree:
                dictioary = new TST();
                break;
            case Trie:
                dictioary = new Trie();
                break;
            case Hash:
                dictioary = new Hash();
                break;
        }
        this.currentSavingmethod = currentSavingmethod;
    }
}
