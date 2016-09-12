/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binpacker;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mitch
 */
public class Node extends Table{
    
//DATA
    private static int numChildren;
    private List<Table> tables;
    private List<Node> children;
    private List<Rect> emptySpace;
    
    
//CONSTRUCTORS
    public Node(){
        super();
        numChildren++;
        tables = new ArrayList<Table>();
        children = new ArrayList<Node>();
        emptySpace = new ArrayList<Rect>();
    }
    public Node(String name, Rect size, int rotation, double price){
        super(name, size, rotation, price);
        tables = new ArrayList<Table>();
        children = new ArrayList<Node>();
        emptySpace = new ArrayList<Rect>();
    }
    
    
//GETTERS SETTERS
    public void setTables(ArrayList<Table> a){
        for(Table t : a){
            tables.add(t);
        }
    }
    public void setChildren(ArrayList<Node> a){
        for(Node n : a){
            children.add(n);
        }
    }
    public void setEmptySpace(ArrayList<Rect> a){
        for(Rect r : a){
            emptySpace.add(r);
        }
    }
    
    public Table getTable(int itr){
        Table t = new Table();
        
        if(!tables.isEmpty()){
            t.set(tables.get(itr));
        }
        
        return t;
    }
    public boolean removeTable(String name){
        List<Table> tList = new ArrayList<Table>();
        boolean deleted = false;
        
        if(!tables.isEmpty()){
            for(Table t : tables){
                if(!t.getName().equals(name)){
                    tList.add(t);
                }
                else{
                    if(!deleted){
                        deleted = true;
                    }
                    else{
                        tList.add(t);
                    }
                }
            }
        }
        
        return deleted;
    }
    
    
//MEMBERS
    
    
    
    
}
