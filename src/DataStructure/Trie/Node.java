package DataStructure.Trie;

import DataStructure.LinkedList.LinkedList;

/**
 * Created by Saman A.Mirhoseini on 09/12/2016.
 */
public class Node {
    public static final int AlphabetSize = 26;
    Node [] children;
    private boolean endOFTheWord;

    private LinkedList index;

    public Node() {
        children = new Node[AlphabetSize];
        for(int i = 0;i < AlphabetSize;i++)
            children[i] = null;
        endOFTheWord = false;
        index = new LinkedList();
    }
    private int indexDefiner (char c){
        if(c < 'a') {
            return c - 'A';
        }
        return c - 'a';
    }
    public boolean isEndOFTheWord() {
        return endOFTheWord;
    }

    public void setEndOFTheWord(boolean endOFTheWord) {
        this.endOFTheWord = endOFTheWord;
    }
    public LinkedList getIndex() {
        return index;
    }

    public void setIndex(LinkedList index) {
        this.index = index;
    }
}
