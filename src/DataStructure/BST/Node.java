package DataStructure.BST;
import DataStructure.LinkedList.LinkedList;

import java.io.File;

/**
 * Created by Saman A.Mirhoseini on 08/12/2016.
 */
public class Node {
    private String data;
    private Node left;
    private Node right;
    private Node parent;
    private int height;
    private LinkedList index;
    public Node(String data){
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
        height  = 0;
        index = new LinkedList();
    }
    public Node(String data,File file){
        this.data = data;
        this.left = null;
        this.right = null;
        index = new LinkedList();
        index.insert(file);
    }
    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public LinkedList getIndex() {
        return index;
    }

    public void setIndex(LinkedList index) {
        this.index = index;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    public Node RR(){
        Node temp = this.left;
        this.left = temp.right;
        this.height = BST.h(this);
        temp.right = this;
        temp.height = BST.h(temp);
        temp.parent = this.parent;
        return temp;
    }
    public Node LR(){
        Node temp = this.right;
        this.right = temp.left;
        this.height = BST.h(this);
        temp.left = this;
        temp.height = BST.h(this);
        return temp;
    }
    public int getBalanceFactor(){
        if(this == null) {
            System.out.println("there");
            return 0;
        }
        else {
            System.out.println("therett");
            return BST.h(left) - BST.h(right);
        }
    }
    public Node balancer(){
        this.height = BST.h(this);
            int balance = getBalanceFactor();
            if (balance > 1) {
                if (left.getBalanceFactor() < 0) {
                    left = left.LR();
                }
                return RR();
            } else if (balance < -1) {
                if (right.getBalanceFactor() > 0) {
                    right = right.RR();
                }
                return LR();
            }

            return this;
    }

}
