package DataStructure.TST;

import DataStructure.DataStructure;
import DataStructure.LinkedList.LinkedList;

import java.io.File;

/**
 * Created by Saman A.Mirhoseini on 09/12/2016.
 */
public class TST extends DataStructure{
    private Node root;
    private int nodeCount;
    public TST(){
        root = null;
        nodeCount=0;
    }
    public Node insert(int order,String token,Node node,File file){
        if(node == null){
            node = new Node(token.charAt(order));
        }
        if(root == null){
            root = node;
        }
        /*
        now we have to search for the right place travelling from the left to
        center to right just to find the right place with the famous recursive aproach
        * */
        if (token.charAt(order) < node.getData()) {
     //       System.out.println("left");
            node.left = insert( order, token ,node.left,file);
        }
        // eqkid, go center
        else if (token.charAt(order) == node.getData())
        {
     //       System.out.println("middle");
            // end of word?
            if ((order+1) == token.length()) {
                node.setEndOfTheWord(true);
                node.getIndex().insert(file);
            }
            else {
                order++; // next letter!
                node.equal = insert( order, token,node.equal,file);
            }
        }
        // hikid - go right
        else if (token.charAt(order) > node.getData()) {
        //    System.out.println("right");
            node.right = insert(order, token, node.right,file);
        }
        return node;
    }
    public Node search (Node node,String token,int index){
//        if (key == null) throw new NullPointerException();
//        if (key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
//        if (x == null) return null;
        if(token == null || token.length() <= 0||node == null)
            return null;
        char temp = token.charAt(index);
        if  (temp < node.getData()) {
            return search(node.left, token, index);
        }
        else if (temp > node.getData()) {
            return search(node.right, token, index);
        }
        else if (index < token.length() - 1) {
            return search(node.equal, token, index+1);
        }
        else {
            return node;
        }
    }
    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    @Override
    public void insertinDataStructure(String s, File file) {
        this.insert(0,s,this.root,file);
    }

    @Override
    public String searchinDataStructure(String s) {
        Node result = this.search(root,s.toLowerCase(),0);
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
        Node result = this.search(root,s,0);
        return result.getIndex();
    }

    @Override
    public boolean deleteToken(String token) {
        return false;
    }

    @Override
    public void deleteThisFile(File file) {
        tstDeleteFile(root,file);
    }
    public void printTree(Node start){
        //It is the implemention of the in order traversal
        //But the print is different for mty own purpose
        if(start == null)
            return;
        if(start.isEndOfTheWord()) {
            System.out.print(start.getData()+"  ");
            System.out.print(start.getIndex().makeStringprintable(" ")+"\n");
        }
        if(start.getLeft() != null)
            printTree(start.getLeft());
        if(start.getEqual() != null)
            printTree(start.getEqual());
        if(start.getRight() != null)
            printTree(start.getRight());
    }
    public void nodeCount(Node start){
        if(start != null){
            if(start.isEndOfTheWord())
                nodeCount++;
        }
        if(start!=null) {
            if (start.getLeft() != null)
                nodeCount(start.getLeft());
            if (start.getEqual() != null)
                nodeCount(start.getEqual());
            if (start.getRight() != null)
                nodeCount(start.getRight());
        }
    }
    public void tstDeleteFile(Node start,File file){
        if(start != null){
            if(start.isEndOfTheWord())
                start.getIndex().delete(file);
        }
        if(start!=null) {
            if (start.getLeft() != null)
                tstDeleteFile(start.getLeft(),file);
            if (start.getEqual() != null)
                tstDeleteFile(start.getEqual(),file);
            if (start.getRight() != null)
                tstDeleteFile(start.getRight(),file);
        }
    }
    public int getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(int nodeCount) {
        this.nodeCount = nodeCount;
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
    private int height(Node p){
        if(p == null) return -1;
        else
            return 1 + Math.max(height(p.getEqual()),Math.max( height(p.getLeft()), height(p.getRight())));
    }

    @Override
    public Integer height() {
        return new Integer (height(root));
    }
}
