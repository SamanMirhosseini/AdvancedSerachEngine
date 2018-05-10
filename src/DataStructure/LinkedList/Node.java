package DataStructure.LinkedList;

import java.io.File;

/**
 * Created by Saman A.Mirhoseini on 08/12/2016.
 */
public class Node {
    private File data;
    private Node next;

    public Node(File data) {
        this.data = data;
        this.next = null;
    }

    public File getData() {
        return data;
    }

    public void setData(File data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
