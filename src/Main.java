import Build.Directory;
import Build.MainEvent;
import DataStructure.BST.BST;
import DataStructure.LinkedList.LinkedList;
import DataStructure.*;
import DataStructure.Stack.Stack;
import DataStructure.TST.TST;
import DataStructure.Trie.Node;
import DataStructure.Trie.Trie;
import Gui.MyFrame;

import java.io.File;
import java.io.IOException;

/**
 * Created by Saman A.Mirhoseini on 08/12/2016.
 */
public class Main {
    public static void  main(String[]args) throws IOException {
        File f = new File("C:\\Users\\Saman A.Mirhoseini\\Desktop\\test\\docs");
        System.out.println(f.getPath());
        MainEvent mainEvent = new MainEvent(null,null);
        MyFrame myFrame = new MyFrame(mainEvent);
        mainEvent.setPanel(myFrame.getMyPanel());
//        mainEvent.addThisDirectorytoTheDictionary(f);
      //  String s = mainEvent.getDictioary().searchinDataStructure("he");
       // mainEvent.sysoutPrint(s);
//        mainEvent.getDictioary().deleteThisFile(new File("C:\\Users\\Saman A.Mirhoseini\\Desktop\\test\\docs\\d757.txt"));
//        mainEvent.getDictioary().deleteThisFile(new File("C:\\Users\\Saman A.Mirhoseini\\Desktop\\test\\docs\\d758.txt"));
//        mainEvent.getDictioary().deleteThisFile(new File("C:\\Users\\Saman A.Mirhoseini\\Desktop\\test\\docs\\d761.txt"));
//        mainEvent.getDictioary().deleteThisFile(new File("C:\\Users\\Saman A.Mirhoseini\\Desktop\\test\\docs\\d763.txt"));
//        mainEvent.getDictioary().deleteThisFile(new File("C:\\Users\\Saman A.Mirhoseini\\Desktop\\test\\docs\\d767.txt"));
//        mainEvent.getDictioary().deleteThisFile(new File("C:\\Users\\Saman A.Mirhoseini\\Desktop\\test\\docs\\d774.txt"));
//        mainEvent.getDictioary().deleteThisFile(new File("C:\\Users\\Saman A.Mirhoseini\\Desktop\\test\\docs\\d754.txt"));
        //d.ds.deleteThisFile(new File("C:\\Users\\Saman A.MirhoseinBi\\Desktop\\test\\docs\\d109.txt"));
       // System.out.print(d.ds.searchinDataStructure("university"));
//        Trie b = (Trie) mainEvent.getDictioary();
//        b.nodeCount(b.getRoot());
//        System.out.println(b.getNodeCount());
    }
}
//    LinkedList l = new LinkedList();
//l.insert(new File("D:\\zapya\\Music\\Ain't In Fun.mp3"));
//        l.insert(new File("D:\\zapya\\Music\\Alesso ft Tove lo - Heroes(We could be).mp3"));
//        l.insert(new File("D:\\zapya\\Music\\Joe Satriani Surfing with the alien!.mp3"));
//        l.delete(new File("D:\\zapya\\Music\\Joe Satriani Surfing with the alien!.mp3"));
//        l.delete(new File("D:\\zapya\\Music\\Ain't In Fun.mp3"));
//        l.delete(new File("D:\\zapya\\Music\\Alesso ft Tove lo - Heroes(We could be).mp3"));
//        System.out.println("knlk");
//        l.p();
//        System.out.println(l.isEmpty());
