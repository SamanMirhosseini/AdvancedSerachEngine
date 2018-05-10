package Build;

import DataStructure.Hash.Hash;
import DataStructure.Hash.HashEntry;
import DataStructure.Trie.Node;
import DataStructure.Trie.Trie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import static java.util.Arrays.binarySearch;

/**
 * Created by Saman A.Mirhoseini on 10/12/2016.
 */
public class STOP {
    private String [] stop_words;
    private File file;
    private Hash stop;
    public STOP(File f){
        stop = new Hash();
        this.file = f;
        this.MakeTheStop();
    }
    public void MakeTheStop(){
        Vector<String>stop_wordss = new Vector<String>();
        try {
            Scanner s = new Scanner (this.file);
            while(s.hasNextLine()){
                String temp = s.next();
                String[]ss = new String[0];
                if(temp.length()>0)
                ss= temp.split("[^a-zA-Z]+");
                if(temp.length() > 0 ) {
                    stop_wordss.add(temp);
                    for(int i = 0;i<ss.length;i++)
                    stop.insertinDataStructure(ss[i],file);
                }
            }
//            this.stop_words = (String[]) stop_wordss.toArray();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public Boolean isValid(String s){
        if(s.length() <= 0)
            return false;
        HashEntry temp = stop.search(s);
        if(temp == null)
            return true;
        else
            return false;
    }
}
