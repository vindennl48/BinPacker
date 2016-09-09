/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binpacker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SHOP
 */
public class Node {

//DATA
    //number of total children
    public static long numChildren;

    //list of tables
    List<Table> tableList;

    //inverted list of tables (rotated 90deg)
    List<Table> tableList_inv;

    //children list
    List<Node> children;

    //branch selling amount
    double branchValue;
    String tablesOut;

    //size of empty space available for this node
    Rect size;

    //location of empty space within the original allotted space
    Rect location;

    //table that fits, if any
    Table table;

//MEMBERS CONSTRUCTORS DESTRUCTORS
    public Node(List<Table> newList, List<Table> newInvList){
        tableList = newList;
        tableList_inv = newInvList;
        children = new ArrayList<Node>();

        branchValue = 0;
        tablesOut = "";
        size = new Rect();
        location = new Rect();
        table = new Table();
    }
    public Node(){
        tableList = new ArrayList<Table>();
        tableList_inv = new ArrayList<Table>();
        children = new ArrayList<Node>();

        branchValue = 0;
        tablesOut = "";
        size = new Rect();
        location = new Rect();
        table = new Table();
    };


//RUNNING THROUGH THE TREE
    void runTree(){
        setBranchValue();
        printAnswer();
    }

    void setBranchValue(){

        if(!children.isEmpty()){

            String newBranch = "fail";

            int ch_si = children.size();
            for(int i = 0; i < ch_si; i++){

                if(children.get(i).branchValue == 0){
                    children.get(i).setBranchValue();

                    if(children.get(i).branchValue > this.branchValue)
                        this.branchValue = children.get(i).branchValue;
                        newBranch = children.get(i).tablesOut;
                }

            }

            this.branchValue += this.table.sellingPrice;

            tablesOut = String.format(
                    ","+this.table.name+": %.4f,%.4f ",
                    this.location.width, 
                    this.location.height
            );
            tablesOut += newBranch;

        }
        else{

            this.branchValue = this.table.sellingPrice;
            this.tablesOut = String.format(
                    ","+this.table.name+": %.4f,%.4f ",
                    this.location.width, 
                    this.location.height
            );

        }

    }

    void printAnswer(){
        System.out.println(tablesOut);
    }


//SETTING UP THE TREE
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
            rect1.printRect("rect1");
            rect1Loc.printRect("rect1Loc");
            
            System.out.println("One");
            setChildList(rect1, rect1Loc);
            System.out.println("Two");
            setChildListInv(rect1, rect1Loc);


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
            rect2.printRect("rect2");
            rect2Loc.printRect("rect2Loc");
            
            System.out.println("Three");
            setChildList(rect2, rect2Loc);
            System.out.println("Four");
            setChildListInv(rect2, rect2Loc);
            System.out.println("Done");

        }
        //if table is null then no more tables can be inserted
        //into this space so children are left as null
        //or a table has not been inserted yet
        //run through all children the same way
        
        try {
            System.in.read();
        } catch (IOException ex) {
            Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        runThroughChildren();
    }

    void setChildList(Rect newRect, Rect newLocation){
        //create children for this rectangle size and 0 orientation
        if(!tableList.isEmpty() && !tableList_inv.isEmpty()){

            for(Table t : tableList){

                //if table can fit
                if(t.size.width <= newRect.width &&
                   t.size.height <= newRect.height){

                    //create child
                    Node child = new Node();

                    //set table lists
                    boolean removed = false;
                    for(Table a : tableList){
                        if(a.name.equals(t.name) && !removed){
                            removed = true;
                            System.out.println("removed 0deg");
                        }
                        else{
                            child.tableList.add(a);
                        }
                    }

                    removed = false;
                    for(Table a : tableList_inv){
                        child.tableList_inv.add(a);
                       if(a.name.equals(t.name) && !removed){
                            removed = true;
                        }
                        else{
                            child.tableList_inv.add(a);
                            System.out.println("removed 90deg");
                        }
                    }

                    //set empty space
                    child.size = newRect;

                    //set location of child
                    child.location = newLocation;

                    //add table to child
                    child.table = t;

                    //add child to children list
                    children.add(child);
                    numChildren += 1;
                    System.out.println(String.format(
                            "Num 1 Children: %d", numChildren));
                }
                else{
                    System.out.println("Num 1 no children added");
                }
                
            }

        }
        else{
            System.out.println("Num 1 empty!");
        }
    }

    void setChildListInv(Rect newRect, Rect newLocation){
        //create children for this rectangle size and 0 orientation
        if(!tableList.isEmpty() && !tableList_inv.isEmpty()){

            for(Table t : this.tableList_inv){

                //if table can fit
                if(t.size.width <= newRect.width &&
                   t.size.height <= newRect.height){

                    //create child
                    Node child = new Node();

                    //set table lists
                    boolean removed = false;
                    for(Table a : tableList){
                        if(a.name.equals(t.name) && !removed){
                            removed = true;
                            System.out.println("removed 0deg");
                        }
                        else{
                            child.tableList.add(a);
                        }
                    }
                    
                    System.out.println("-aa-");
                    removed = false;
                    for(Table a : tableList_inv){
                        child.tableList_inv.add(a);
                       if(a.name.equals(t.name) && !removed){
                            removed = true;
                            System.out.println("removed 90deg");
                        }
                        else{
                            child.tableList_inv.add(a);
                            System.out.println("Added " + a.name);
                        }
                    }
                    System.out.println("-bb-");

                    //set empty space
                    child.size = newRect;

                    //set location of child
                    child.location = newLocation;

                    //add table to child
                    child.table = t;

                    //add child to children list
                    children.add(child);
                    numChildren += 1;
                    System.out.println(String.format(
                            "Num 2 Children: %d", numChildren));
                }
                else{
                    System.out.println("Num 2 no children added");
                }
            }

        }
        else{
            System.out.println("Num 2 empty!");
        }
    }

    void runThroughChildren(){
        if(!children.isEmpty()){
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


//OTHER
    void printNode(){
        System.out.println("Node: " + Long.toString(numChildren));
        System.out.println(
                "    Table That Fits: " + table.name
        );
        printList(tableList, "tableList");
        printList(tableList_inv, "tableList_inv");
    }
    
    void printList(List<Table> tables, String name){
        System.out.print("List " + name + ": ");
        
        for(Table t : tables){
            System.out.print(t.name + " ");
        }
    }
}
