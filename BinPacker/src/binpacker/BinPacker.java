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
 * @author SHOP
 */
public class BinPacker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    
    class Node{
        
    //DATA
        //list of tables
        List<Table> tableList;
        //inverted list of tables (rotated 90deg)
        List<Table> tableList_inv;

        //children list
        List<Node> children;
        
        //size of empty space available for this node
        Rect size;
        
        //location of empty space within the original allotted space
        Rect location;
        
        //table that fits, if any
        Table table;
        
    //MEMBERS CONSTRUCTORS DESTRUCTORS
        Node(List<Table> newList, List<Table> newInvList){
            tableList = newList;
            tableList_inv = newInvList;
            size = new Rect();
            location = new Rect();
            table = new Table();
        }
        
        void setChildren(){
            if(!table.name.equals("NULL")){
                
                //get first rectangle size
                Rect rect1 = new Rect(
                    size.width,
                    (size.height - table.size.height)
                );
                //get first rectangle location
                Rect rect1Loc = new Rect(
                    location.width,
                    (table.size.height + location.height)
                );
                //create children for this rectangle size and 0 orientation
                setChildList(tableList, rect1, rect1Loc);
                
                
                //get second rectangle size
                Rect rect2 = new Rect(
                    (size.width - table.size.width),
                    size.height
                );
                //get second rectangle location
                Rect rect2Loc = new Rect(
                    (table.size.width + location.width),
                    location.height
                );
                //create children for this rectangle size and 90 orientation
                setChildList(tableList_inv, rect2, rect2Loc);
                
            }
            //if table is null then no more tables can be inserted
            //into this space so children are left as null
            //or a table has not been inserted yet
            
            //run through all children the same way
            runThroughChildren();
        }
        
        void setChildList(List<Table> newList, Rect newRect, Rect newLocation){
            //create children for this rectangle size and 0 orientation
            for(Table t : newList){

                //if table can fit
                if(t.size.width <= newRect.width &&
                   t.size.height <= newRect.height){

                    //create child
                    Node child = new Node(tableList,tableList_inv);
                    //set empty space
                    child.size = newRect;
                    //set location of child
                    child.location = newLocation;
                    //add table to child
                    child.table = t;
                    //add child to children list
                    children.add(child);

                }
            }
        }
        
        void runThroughChildren(){
            if(children.size() != 0){
                for(Node n : children){
                    n.setChildren();
                }
            }
        }
        
    //GETTERS SETTERS
        void setTable(Table newTable){
            table = newTable;
        }
        void setSize(Rect newSize){
            size = newSize;
        }
    }   

    
//STRUCTURES
    class Rect{
        double width;
        double height;
        
        Rect(){
            width = 0;
            height = 0;
        }
        Rect(double newWidth, double newHeight){
            width = newWidth;
            height = newHeight;
        }
    }
    
    class Table{
        //if no table exists, name = "NULL"
        String name;
        Rect size;
        int orientation;
        
        Table(){
            name = "NULL";
            orientation = 0;
        }
    }

}
