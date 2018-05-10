package DataStructure;

import DataStructure.BST.Node;
import DataStructure.LinkedList.LinkedList;

import java.io.File;

/**
 * Created by Saman A.Mirhoseini on 09/12/2016.
 */
public abstract class DataStructure {
    public abstract void insertinDataStructure(String s, File file);
    public abstract String searchinDataStructure(String s);
    public abstract LinkedList searcher(String s);
    public abstract boolean deleteToken(String token);
    public abstract void deleteThisFile(File file);
    public  abstract String travelAll();
    public abstract Integer nodeCount();
    public abstract Integer height();
//    public abstract String searchQuery(String query);
}
