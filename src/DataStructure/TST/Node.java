package DataStructure.TST;
import DataStructure.LinkedList.LinkedList;


/**
 * Created by Saman A.Mirhoseini on 09/12/2016.
 */
public class Node {
    private char data;
    Node left;
    Node equal;
    Node right;
    private boolean endOfTheWord;
    private LinkedList index;
    public Node(char data) {
        this.data = data;
        right = null;
        equal = null;
        left = null;
        index = new LinkedList();
        endOfTheWord = false;
    }

    public Node(char data, boolean endOfTheWord) {
        this.data = data;
        this.endOfTheWord = endOfTheWord;
    }

    public LinkedList getIndex() {
        return index;
    }

    public void setIndex(LinkedList index) {
        this.index = index;
    }

    public boolean isEndOfTheWord() {
        return endOfTheWord;
    }

    public void setEndOfTheWord(boolean endOfTheWord) {
        this.endOfTheWord = endOfTheWord;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getEqual() {
        return equal;
    }

    public void setEqual(Node equal) {
        this.equal = equal;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public char getData() {
        return data;
    }

    public void setData(char data) {
        this.data = data;
    }
}
