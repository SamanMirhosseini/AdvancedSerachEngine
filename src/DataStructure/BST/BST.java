package DataStructure.BST;

import DataStructure.DataStructure;
import DataStructure.LinkedList.*;

import java.io.File;

/**
 * Created by Saman A.Mirhoseini on 08/12/2016.
 */
//BST is all set and done
public class BST extends DataStructure{
    private Node root;
    String wordlist = " ";
    public BST() {
        super();
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
    public Node search(String s){
        Node current = root;
        while(current.getData().compareToIgnoreCase(s) != 0){
            if(current != null){
                if(s.compareToIgnoreCase(current.getData()) < 0)
                    current = current.getLeft();
                else
                    current = current.getRight();
                if(current == null)
                    return  null;
            }
        }
        return current;
    }

    public void insert (String s,File file){
        Node current = null;
        Node parent = null;
        Node n;
        if(root == null){
          //  System.out.println("yes");
            n = new Node(s,file);
            root = n;
            return;
        }
        else{
            current = root;
            parent = null;
            while(true){
                parent = current;
                if(parent != null) {
                    if (parent.getData().compareToIgnoreCase(s) > 0) {
                        current = current.getLeft();
                        if (current == null) {
                            n = new Node(s, file);
                            parent.setLeft(n);
                            n.setParent(parent);
                            return;
                        }
                    }
                    else if(parent.getData().compareToIgnoreCase(s) < 0){
                        current = current.getRight();
                        if(current == null){
                            n = new Node(s, file);
                            parent.setRight(n);
                            n.setParent(parent);
                            return;
                        }
                    }
                    else if(parent.getData().compareToIgnoreCase(s) == 0){
                        parent.getIndex().insert(file);
                        return;
                    }
                }
            }
        }
    // End of the insert;
        //Begin of rotation

    }
    public int height(Node p)
    {
        if(p == null) return -1;
        else
            return 1 + Math.max( height(p.getLeft()), height(p.getRight()));
    }
    public static int h(Node p){
        if(p == null) return -1;
        else
            return 1 + Math.max( h(p.getLeft()), h(p.getRight()));
    }
    public void b(Node p){
        if(p==null)
            return;
        if(p.getLeft() != null)
        b(p.getLeft());
        if(p.getRight() != null)
        b(p.getRight());
        p.balancer();

    }
    public void printer(Node p){
        System.out.println(p.getData());
        p.getIndex().p();
        printer(p.getLeft());
        printer(p.getRight());
    }
    public int nodeCount(Node node){
        if(node==null)
                return 0;
        return nodeCount(node.getLeft()) + nodeCount(node.getRight()) + 1;
    }
    public int getBalanceFactor(Node n){
        if(n == null) {
            System.out.println("there");
            return 0;
        }
        else {
            System.out.println("therett");
            return height(n.getLeft()) - height(n.getRight());
        }
    }
    public void printTree(Node start){
        //It is the implemention of the in order traversal
        //But the print is different for mty own purpose
        if(start == null)
            return;
        if(start.getLeft() != null)
        printTree(start.getLeft());
        wordlist += start.getData()+"\n";
        if(start.getRight() != null)
        printTree(start.getRight());
    }

    @Override
    public void insertinDataStructure(String s, File file) {
        this.insert(s,file);
    }

    @Override
    public String searchinDataStructure(String s) {
        Node result = this.search(s);
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
        Node result = this.search(s);
        if(result!=null)
        return result.getIndex();
        return null;
    }

    @Override
    public boolean deleteToken(String token) {
        Node n = search(token);
        n.setIndex(null);
        n.setIndex(new LinkedList());
        return false;
    }
    public void deletefilefromBST(File file,Node start){
        if(start == null)
            return;
        if(start.getLeft() != null)
            deletefilefromBST(file,start.getLeft());
            System.out.println(start.getIndex().delete(file));
        if(start.getRight() != null)
            deletefilefromBST(file,start.getRight());
    }
    public void bstFileDelete(Node start,File file){
        if(start == null)
            return;
        if(start.getLeft() != null)
            bstFileDelete(start.getLeft(),file);
            start.getIndex().delete(file);
        if(start.getRight() != null)
            bstFileDelete(start.getRight(),file);
    }

    @Override
    public void deleteThisFile(File file) {
        bstFileDelete(root,file);
    }

    @Override
    public String travelAll() {
        wordlist = "  ";
        printTree(root);
        return wordlist;
    }

    @Override
    public Integer nodeCount() {
        return new Integer(nodeCount(root));
    }

    @Override
    public Integer height() {
        return new Integer(height(root));
    }

    private Node RR (Node n){
        Node a = n.getLeft();
        Node b = a.getRight();
        //we now perform the simple rotation
        a.setRight(n);
        n.setLeft(b);
        return a;
    }
    private Node LR (Node n){
        Node a = n.getRight();
        Node b = n.getLeft();
        //we now perdorm the rotation
        a.setLeft(n);
        n.setRight(b);
        return a;
    }
    public Node avlinsert(String s,File file){
        Node current = null;
        Node parent = null;
        Node n = null;
        boolean insertionDone = false;
        if(root == null){
            //  System.out.println("yes");
            n = new Node(s,file);
            root = n;
            return n;
        }
        else{
            current = root;
            parent = null;
            while(!insertionDone){
                parent = current;
                if(parent != null) {
                    if (parent.getData().compareToIgnoreCase(s) > 0) {
                        current = current.getLeft();
                        if (current == null) {
                            n = new Node(s, file);
                            parent.setLeft(n);
                            insertionDone = true;
                        }
                    }
                    else if(parent.getData().compareToIgnoreCase(s) < 0){
                        current = current.getRight();
                        if(current == null){
                            n = new Node(s, file);
                            parent.setRight(n);
                            insertionDone = true;
                        }
                    }
                    else if(parent.getData().compareToIgnoreCase(s) == 0){
                        parent.getIndex().insert(file);
                        return n;
                    }
                }
            }

        }
        //n = parent;
        parent.balancer();
//        System.out.println("here");
//        int balancefactor = this.getBalanceFactor(n);
//        System.out.println(balancefactor);
//        if (balancefactor > 1 && s.compareToIgnoreCase(n.getLeft().getData()) < 0) {
//            System.out.println("this case 1");
//            return RR(n);
//        }
//
//        // Right Right Case
//        if (balancefactor < -1 && s.compareToIgnoreCase(n.getRight().getData()) > 0) {
//            System.out.println("this case 2");
//            return LR(n);
//        }
//
//        // Left Right Case
//        if (balancefactor > 1 && s.compareToIgnoreCase(n.getLeft().getData()) > 0) {
//            System.out.println("this case 3");
//            n.setLeft(LR(n.getLeft()));
//            return RR(n);
//        }
//
//        // Right Left Case
//        if (balancefactor < -1 && s.compareToIgnoreCase(n.getRight().getData()) < 0) {
//            System.out.println("this case 4");
//            n.setRight(RR(n.getRight()));
//            return LR(n);
//        }
//        System.out.println("herecjhvbjkn");
        return n;
    }
    private Node insert(Node where, Node node,File file) {
        if (where == null) {
            return node;
        }

        int comp = where.getData().compareToIgnoreCase(node.getData());
        if (comp == 0) {
            where.getIndex().insert(file);
        } else if (comp < 0) {
            where.setLeft(insert(where.getLeft(), node,file));
        } else {
            where.setRight(insert(where.getRight(), node,file));
        }

        return where.balancer();
    }
}
