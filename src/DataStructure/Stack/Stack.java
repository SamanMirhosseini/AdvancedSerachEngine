package DataStructure.Stack;

/**
 * Created by Saman A.Mirhoseini on 16/12/2016.
 */
public class Stack {
    private Node root;
    private Node last;
    private Node behindLast;

    public Stack() {
        root = null;
        last = null;
        behindLast = null;
    }
    public void push(String data){
        Node newNode = new Node(data);
        if(root == null){
            root = newNode;
            last = root;
        }
        else{
                last.setNext(newNode);
                behindLast = last;
                last = last.getNext();
                last.setPrevious(behindLast);
        }
    }
    public Node pop(){
        if(last == root){
            return root;
        }
        Node temp = last;
        last = last.getPrevious();
        return temp;
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
    public Node getBehindLast() {
        return behindLast;
    }

    public void setBehindLast(Node behindLast) {
        this.behindLast = behindLast;
    }
    public boolean isEmpty(){
        if(root==null)
            return true;
        return false;
    }
}
