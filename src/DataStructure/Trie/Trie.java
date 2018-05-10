package DataStructure.Trie;

import DataStructure.DataStructure;
import DataStructure.LinkedList.LinkedList;

import java.io.File;

/**
 * Created by Saman A.Mirhoseini on 10/12/2016.
 */
public class Trie extends DataStructure {
    private Node root;
    private int nodeCount = 0;
    public Trie() {
        root = new Node();
    }
    public void insert(String token, File file){
        int tokenLength = token.length();
        int index;
        Node current = root;
        for(int i = 0;i < tokenLength;i++){
            index = indexDefiner(token.charAt(i));
            if(current.children[index]==null) {
                current.children[index] = new Node();
            }
            current = current.children[index];
        }
        current.setEndOFTheWord(true);
        current.getIndex().insert(file);
    }
    public Node Search (String token){
        int tokenLength = token.length();
        int index;
        Node current = root;
        for(int i = 0;i < tokenLength;i++){
            index = indexDefiner(token.charAt(i));
            if(current.children[index]==null) {
                return null;
            }
            current = current.children[index];
        }
        if(current != null && current.isEndOFTheWord()){
            return  current;
        }
    return null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    private int indexDefiner (char c){
        if(c < 'a') {
            return c - 'A';
        }
        return c - 'a';
    }

    @Override
    public void insertinDataStructure(String s, File file) {
        this.insert(s,file);
    }

    @Override
    public String searchinDataStructure(String s) {
        Node result = this.Search(s);
        String string = "\n";
        string = string + "Files below contain the word :"+s+"\n";
        if(result == null){
            return string+"No such word in the docs";
        }
        LinkedList files = result.getIndex();
        if(files.getRoot() == null){
            return string+"No such word in the docs";
        }
        string = files.makeStringprintable(string);
        return string +"\n";
    }

    @Override
    public LinkedList searcher(String s) {
        Node result = this.Search(s);
        return  result.getIndex();
    }

    @Override
    public boolean deleteToken(String token) {
        return false;
    }

    @Override
    public void deleteThisFile(File file) {
        trieDeleteFile(root,file);
    }
    public void nodeCount(Node start){
        if(start != null){
            if(start.isEndOFTheWord())
                nodeCount++;
        }
        if(start!=null) {
            for(int i = 0; i < Node.AlphabetSize;i++){
                if(start.children[i]!=null){
                    nodeCount(start.children[i]);
                }
            }
        }
    }
    public void trieDeleteFile(Node start,File file){
        if(start != null){
            if(start.isEndOFTheWord())
                start.getIndex().delete(file);
        }
        if(start!=null) {
            for(int i = 0; i < Node.AlphabetSize;i++){
                if(start.children[i]!=null){
                    trieDeleteFile(start.children[i],file);
                }
            }
        }
    }
    @Override
    public String travelAll() {
        return null;
    }

    @Override
    public Integer nodeCount() {
        nodeCount(root);
        return new Integer(nodeCount);
    }
    public int height (Node root){
        if(root == null)
            return -1;
        else
        {
            int max = 0;
            for(int i = 0;i< 25;i++){
            max = Math.max(height(root.children[i]),height(root.children[i+1]));
            }
            return max + 1;
        }
    }
    @Override
    public Integer height() {
        return new Integer(0);
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(int nodeCount) {
        this.nodeCount = nodeCount;
    }
}
