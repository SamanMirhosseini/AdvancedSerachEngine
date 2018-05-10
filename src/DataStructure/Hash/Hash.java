package DataStructure.Hash;

import DataStructure.DataStructure;
import DataStructure.LinkedList.LinkedList;

import java.io.File;

/**
 * Created by Saman A.Mirhoseini on 17/01/2017.
 */
public class Hash extends DataStructure {
    final static int Table_Size = 200;
    HashEntry [] hashtable = new HashEntry[Table_Size];

    public Hash() {
        for(int i = 0;i < Table_Size;i++){
            hashtable[i] = null;
        }
    }

    @Override
    public void insertinDataStructure(String s, File file) {
        insert(s,file);
    }

    @Override
    public String searchinDataStructure(String s) {
        HashEntry result = this.search(s);
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
        HashEntry h = search(s);
        if(h!=null)
            return  h.getIndex();
        return null;
    }

    @Override
    public boolean deleteToken(String token) {
        return false;
    }

    @Override
    public void deleteThisFile(File file) {
        for(int i = 0;i < Table_Size;i++){
            if(hashtable[i]!=null){
                HashEntry prev = null;
                HashEntry temp = hashtable[i];
                while(temp != null){
                    temp.getIndex().delete(file);
                    if(temp.getIndex().isEmpty()){
                        if(prev!=null)
                        prev.next = temp.next;
                        else hashtable[i] = null;
                        return;
                    }
                    prev = temp;
                    temp = temp.getNext();
                }
            }
        }
    }

    @Override
    public String travelAll() {
        String s = " ";
        for(int i = 0;i < Table_Size;i++){
            if(hashtable[i]!=null){
                HashEntry temp = hashtable[i];
                while(temp != null){
                    s += temp.getData()+"\n";
                    temp = temp.getNext();
                }
            }
        }
        return s;
    }

    @Override
    public Integer nodeCount() {
        int ans = 0;
        for(int i = 0;i < Table_Size;i++){
            if(hashtable[i]!=null){
                HashEntry temp = hashtable[i];
                while(temp != null){
                    ans++;
                    temp = temp.getNext();
                }
            }
        }
        return new Integer(ans);
    }

    @Override
    public Integer height() {
        return new Integer(0);
    }

    public void insert(String s,File file){
        int a = hashFunc(s);
        if(hashtable[a]==null){
            hashtable[a] = new HashEntry(s,file);
        }
        else{
            HashEntry temp = hashtable[a];
            while(temp.next != null && temp.getData().compareToIgnoreCase(s) != 0){
                temp = temp.getNext();
            }
            if(temp.getData().compareToIgnoreCase(s) == 0){
                temp.index.insert(file);
            }
            else if(temp.getNext()==null){
                temp.setNext(new HashEntry(s,file));
            }
        }
    }
    public HashEntry search(String s){
        int a = hashFunc(s);
        if(hashtable[a] == null)
            return null;
        else{
            HashEntry temp = hashtable[a];
            while(temp.next != null && temp.getData().compareToIgnoreCase(s) != 0){
                temp = temp.getNext();
            }
            if(temp.getData().compareToIgnoreCase(s) == 0){
                return temp;
            }
            else if(temp.getNext()==null){
                return null;
            }
        }
        return  null;
    }
    public int hashFunc(String s){
        s = s.toLowerCase();
        int ans = 0;
        for(int i= 0;i<s.length();i++){
            ans += s.charAt(i);
        }
        return ans % Table_Size;
    }
}
