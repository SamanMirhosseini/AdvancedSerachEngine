package DataStructure.LinkedList;
import DataStructure.DataStructure;
import org.omg.CORBA.Current;

import  java.io.File;
/**
 * Created by Saman A.Mirhoseini on 08/12/2016.
 */
public class LinkedList {
    private Node root;
    private Node last;



    public LinkedList(){
        root = null;
        last = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
    public Node getLast() {
        return last;
    }

    public void setLast(Node last) {
        this.last = last;
    }
    public void insert(Node newNode){
        if(root == null){
            root = newNode;
            last = root;
        }
        else{
            //System.out.println(contains(newNode.getData()));

            if(!this.contains(newNode.getData())) {
                last.setNext(newNode);
                last = last.getNext();
            }
//            else
               // System.out.println("already in there");
        }
    }
    public void insert(File file){
        Node n = new Node(file);
        this.insert(n);
    }
    public boolean contains(File file){
        Node current = root;
        while(current != null) {
            if (current.getData().getPath().compareToIgnoreCase(file.getPath())==0) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
    public boolean delete(File file){
        if(isEmpty())
            return false;
        Node current = root;
        Node prev = null;
        while(current != null) {
            if (current.getData().getPath().compareToIgnoreCase(file.getPath())==0) {
                if(prev == null){
                    root = current.getNext();
                }
                else{
                    prev.setNext(current.getNext());
                }
                return true;
            }
            prev = current;
            current = current.getNext();
        }
        return false;
    }
    public void p(){
        Node current = root;
        while(current!=null){
            System.out.println(current.getData().getName()+"   "+this.toString()+"    ");
            current = current.getNext();
        }
    }
    public String makeStringprintable(String in){
        Node current;
        int size;
        size = in.length();
        for(current = root;current != null;current = current.getNext()){
            in += current.getData().getName();
            in += "  ---  ";
            if(in.length() - size > 90){
                in += "\n";
                size = in.length();
            }
        }
        return in;
    }
    public boolean isEmpty(){
        if(root == null)
            return true;
        return false;
    }
}
