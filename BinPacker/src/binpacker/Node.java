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
    private static int numChildren;

    //list of tables
    public  List<Table> tableList;

    //children list
    public List<Node> children;

    //branch selling amount
    private double branchValue;
    private String tablesOut;

    //size of empty space available for this node
    private Rect size;

    //location of empty space within the original allotted space
    private Rect location;

    //table that fits, if any
    private Table table;

//MEMBERS CONSTRUCTORS DESTRUCTORS
    public Node(List<Table> newList){
        tableList = newList;
        children = new ArrayList<Node>();

        branchValue = 0;
        tablesOut = "";
        size = new Rect();
        location = new Rect();
        table = new Table();
    }
    public Node(){
        tableList = new ArrayList<Table>();
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

            String newBranch = "fail";//default incase of failure

            int ch_si = children.size();
            for(int i = 0; i < ch_si; i++){
                //if true, no calculation has been done yet
                if(children.get(i).getBranchValue() == 0){
                    children.get(i).setBranchValue();
                    
                    double newBVal = children.get(i).getBranchValue();

                    if(newBVal > branchValue){
                        branchValue = newBVal;
                        newBranch = children.get(i).getTablesOut();
                    }
                }

            }

            branchValue += table.getPrice();

            tablesOut = String.format(
                "%s: %.4f, %.4f\n%s", 
                table.getName(), location.getWidth(), 
                location.getHeight(), newBranch
            );

        }
        else{

            branchValue = table.getPrice();
            
            tablesOut = String.format(
                "%s: %.4f, %.4f\n", 
                table.getName(), location.getWidth(), 
                location.getHeight()
            );

        }

    }
    void printAnswer(){
        System.out.print(
        String.format(
            "############################\n"
          + "%s\n"
          + "############################\n", 
            tablesOut
        )
        );
    }


//SETTING UP THE TREE
    void setChildren(){
        if(!tableList.isEmpty()){
            //get first rectangle size
            Rect rect1 = new Rect(
                size.getWidth(),
                (size.getHeight() - table.getSize().getHeight())
            );
            //get first rectangle location
            Rect rect1Loc = new Rect(
                location.getWidth(),
                (table.getSize().getHeight() + location.getHeight())
            );
            //create children for this rectangle size and 0 orientation
            rect1.print("rect1");
            rect1Loc.print("rect1Loc");

            System.out.println("set Child List 1");
            setChildList(rect1, rect1Loc);
            System.out.println("Finished Child List 1");

            //get second rectangle size
            Rect rect2 = new Rect(
                (size.getWidth() - table.getSize().getWidth()),
                size.getHeight()
            );
            //get second rectangle location
            Rect rect2Loc = new Rect(
                (table.getSize().getWidth() + location.getWidth()),
                location.getHeight()
            );
            //create children for this rectangle size and 90 orientation
            rect2.print("rect2");
            rect2Loc.print("rect2Loc");

            System.out.println("set Child List 2");
            setChildList(rect2, rect2Loc);
            System.out.println("Finished Child List 2");


            //if table is null then no more tables can be inserted
            //into this space so children are left as null
            //or a table has not been inserted yet
            //run through all children the same way

            pauseCmd();

            runThroughChildren();
        }
    }
    void setChildList(Rect newRect, Rect newLocation){
        //create children for this rectangle size and 0 orientation
        for(Table t : tableList){

            //if table can fit at 0deg
            if(t.getWidth() <= newRect.getWidth() &&
               t.getHeight() <= newRect.getHeight()){
                
                createChild(t, newRect, newLocation);
                
            }//else if 90deg fits
            else if(t.getHeight()<= newRect.getWidth() &&
                    t.getWidth()<= newRect.getHeight()){
                
                Table invTable = new Table();
                invTable.set(t);
                invTable.invertSize();
                
                createChild(invTable, newRect, newLocation);
                
            }
            else{
                System.out.print("No Children Added\n");
            }
        }
    }
    void createChild(Table t, Rect newRect, Rect newLocation){
        
        //create child
        Node child = new Node();

        boolean isGone = false;
        int tb_sz = tableList.size();
        for(int i = 0; i < tb_sz; i++){

            Table tNext = tableList.get(i);

            if(tNext.getName().equals(t.getName()) && !isGone){

                isGone = true;
                System.out.println("removed");

            }
            else{
                Table tNew = tNext;

                child.tableList.add(tNew);

                System.out.println("added");

            }
        }

        child.printTableList();

        //set empty space
        child.setSize(newRect);

        //set location of child
        child.setLocation(newLocation);

        //add table to child
        child.table.set(t);

        //add child to children list
        children.add(child);
        numChildren += 1;
        System.out.print(
        String.format(
            "\nNum Children Update: %d\n", 
            numChildren
        )
        );
    }
    void runThroughChildren(){
        
        System.out.println("Printing Children-----------");
        for(Node n : children){
            n.printNode();
        }
        System.out.println("Done Printing Children------");
        
        System.out.println("Run Through Children.. Press Enter");
        pauseCmd();
        
        for(Node n : children){
            n.setChildren();
        }
    }


//GETTERS SETTERS
    void setTable(Table newTable){
        table.set(newTable);
    }
    void setSize(Rect newSize){
        size.set(newSize);
    }
    void setWidth(double newWidth){
        size.setWidth(newWidth);
    }
    void setHeight(double newHeight){
        size.setHeight(newHeight);
    }
    void setLocation(Rect newLoc){
        location.set(newLoc);
    }
    double getBranchValue(){
        return branchValue;
    }
    String getTablesOut(){
        return tablesOut;
    }
    void resetNumChildren(){
        numChildren = 0;
    }
    int getNumChildren(){
        return numChildren;
    }
    

//OTHER
    void printNode(){
        
        System.out.print(
        String.format(
            "----------------------------\n"
          + "Node: \n"
          + "Table: %s\n"
          + "Num Children: %d\n"
          + "Size of Space: %.4f, %.4f\n"
          + "Location: %.4f, %.4f\n",
            table.getName(), children.size(),size.getWidth(), size.getHeight(),
            location.getWidth(), location.getHeight()
        )
        );
        
        printTableList();
        
        System.out.print("----------------------------\n");
    }
    void printTableList(){
        
        System.out.print(
            "----------------------------\n"
          + "Table List:\n"
        );
        
        if(!tableList.isEmpty()){
            for(Table t : tableList){
                t.print();
            }
        }
        else{
            System.out.print("Table Empty\n");
        }
        
        System.out.print(
            "----------------------------\n"
        );
    }
    void pauseCmd(){
        System.out.print("\n\n\nPress Enter To Continue...\n\n");
        try {
            System.in.read();
        } catch (IOException ex) {
            Logger.getLogger(Node.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
