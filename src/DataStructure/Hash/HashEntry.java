package DataStructure.Hash;

import DataStructure.LinkedList.LinkedList;

import java.io.File;

/**
 * Created by Saman A.Mirhoseini on 17/01/2017.
 */
public class HashEntry {
    String data;
    LinkedList index;
    HashEntry next;

    public HashEntry(String data,File file) {
        this.data = data;
        index = new LinkedList();
        index.insert(file);
        next = null;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public LinkedList getIndex() {
        return index;
    }

    public void setIndex(LinkedList index) {
        this.index = index;
    }

    public HashEntry getNext() {
        return next;
    }

    public void setNext(HashEntry next) {
        this.next = next;
    }
}
