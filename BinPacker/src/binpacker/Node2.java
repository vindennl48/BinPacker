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
public class Node2 {

//DATA
    //number of total children
    private static int numChildren;

    //list of tables
    public  List<Table2> tableList;

    //children list
    public List<Node2> children;

    //branch selling amount
    private double branchValue;
    private String tablesOut;

    //size of empty space available for this node
    public List<Rect2> sizes;
    public static Rect2 nodeSize;

    //location of empty space within the original allotted space
    private Rect2 location;

    //table that fits, if any
    private Table2 table;

//MEMBERS CONSTRUCTORS DESTRUCTORS
    public Node2(List<Table2> newList){
        numChildren++;
        
        tableList = newList;
        children = new ArrayList<Node2>();
        sizes = new ArrayList<Rect2>();

        nodeSize = new Rect2();
        branchValue = 0;
        tablesOut = "";
        location = new Rect2();
        table = new Table2();
    }
    public Node2(){
        numChildren++;
        
        tableList = new ArrayList<Table2>();
        children = new ArrayList<Node2>();
        sizes = new ArrayList<Rect2>();

        nodeSize = new Rect2();
        branchValue = 0;
        tablesOut = "";
        location = new Rect2();
        table = new Table2();
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
                "%s: %ddeg: %.4f, %.4f | %.4f, %.4f\n%s", 
                table.getName(), table.getOrient(), location.getWidth(), 
                location.getHeight(), (location.getWidth()+table.getWidth()),
                (location.getHeight()+table.getHeight()), newBranch
            );

        }
        else{

            branchValue = table.getPrice();
            
            tablesOut = String.format(
                "%s: %ddeg: %.4f, %.4f | %.4f, %.4f\n", 
                table.getName(), table.getOrient(), location.getWidth(), 
                location.getHeight(), (location.getWidth()+table.getWidth()),
                (location.getHeight()+table.getHeight())
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
            
            //if sizes are empty, we are the top node
            if(sizes.isEmpty()){
                sizes.add(getEmptyRect(1, table, nodeSize));
                sizes.add(getEmptyRect(2, table, nodeSize));
                //printSizes();
                //pauseCmd();
            }
                            
            int si_sz = sizes.size();
            for(int i = 0; i < si_sz; i++){
                
                int ta_sz = tableList.size();
                for(int j = 0; j < ta_sz; j++){
                    
                    if(tableList.get(j).getWidth() <= sizes.get(i).getWidth() &&
                       tableList.get(j).getHeight() <= sizes.get(i).getHeight()){
                                                
                        makeChild(tableList.get(j), i);
                    
                    }
                    
                    //90deg rotation check
                    if(tableList.get(j).getHeight()<= sizes.get(i).getWidth() &&
                       tableList.get(j).getWidth()<= sizes.get(i).getHeight()){
                        
                        Table2 t = new Table2();
                        t.set(tableList.get(j));
                        t.invertSize();
                        
                        makeChild(t, i);
                    
                    }
                }
            }
            
//            printNode();
//            pauseCmd();
            
            int ch_sz = children.size();
            for(int i = 0; i < ch_sz; i++){
                children.get(i).setChildren();
            }
            
        }
    }
    
    void makeChild(Table2 newTable, int sizeItr){
        
        Node2 child = new Node2();
        
        child.table.set(newTable);
        
        child.location.set(sizes.get(sizeItr).getLoc());
        
        int si_sz = sizes.size();
        for(int i = 0; i < si_sz; i++){
            if(i != sizeItr){
                Rect2 r = new Rect2();
                r.set(sizes.get(i));
                r.setLoc(sizes.get(i).getLoc());
                child.sizes.add(r);
            }
        }
        
        boolean tbl_rmvd = false;
        int tb_sz = tableList.size();
        for(int i = 0; i < tb_sz; i++){
            if(!tableList.get(i).getName().equals(newTable.getName())){
                child.tableList.add(tableList.get(i));
            }
            else{
                if(!tbl_rmvd){
                    tbl_rmvd = true;
                }
                else{
                    child.tableList.add(tableList.get(i));
                }
            }
        }
        
        
        child.sizes.add(getEmptyRect(1, newTable, sizes.get(sizeItr)));
        child.sizes.add(getEmptyRect(2, newTable, sizes.get(sizeItr)));
        
        checkOverlap(child);
        
        child.printNode();
        pauseCmd();
        
        children.add(child);
        
    }
    
    //1 for left rect, 2 for right rect
    Rect2 getEmptyRect(int firstOrSecond, Table2 t, Rect2 area){
        Rect2 r = new Rect2();
        
        if(firstOrSecond == 1){
            r.setWidth(area.getWidth());
            r.setHeight(area.getHeight()- t.getHeight());
            r.setLocWidth(area.getLocWidth());
            r.setLocHeight(area.getLocHeight() + t.getHeight());
            return r;
        }
        else if(firstOrSecond == 2){
            r.setWidth(area.getWidth() - t.getWidth());
            r.setHeight(area.getHeight());
            r.setLocWidth(area.getLocWidth()+ t.getWidth());
            r.setLocHeight(area.getLocHeight());
            return r;
        }
        else{
            return r;
        }
    }
    
    void checkOverlap(Node2 child){
        Table2 t = child.table;
        Rect2 tableLoc = new Rect2();
        tableLoc.setLoc(child.location);
        tableLoc.set(t.getSize());
        
        int si_sz = child.sizes.size();
        for(int i = 0; i < si_sz; i++){
            
            Rect2 size = child.sizes.get(i);
            
            if(size.checkOverlap(tableLoc)){
                
                System.out.println("Have Overlap");
                
                
            }
        }
    }


//GETTERS SETTERS
    void setNodeSize(Rect2 r){
        nodeSize.set(r);
    }
    void setNodeSizeWidth(double newWidth){
        nodeSize.setWidth(newWidth);
    }
    void setNodeSizeHeight(double newHeight){
        nodeSize.setHeight(newHeight);
    }
    void setTable(Table2 newTable){
        table.set(newTable);
    }
    void setLocation(Rect2 newLoc){
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
          + "  Orient: %d\n"
          + "Num Children: %d\n"
          + "Num of Sizes: %d\n"
          + "Location: %.4f, %.4f\n",
            table.getName(), table.getOrient(), children.size(), sizes.size(),
            location.getWidth(), location.getHeight()
        )
        );
        
        printTableList();
        printSizes();
        printChildren();
        
        System.out.print("----------------------------\n");
        //pauseCmd();
    }
    void printTableList(){
        
        System.out.print(
            "----------------------------\n"
          + "Table List:\n"
        );
        
        if(!tableList.isEmpty()){
            for(Table2 t : tableList){
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
    void printSizes(){
        System.out.print(
            "----------------------------\n"
          + "Size List:\n"
        );
        if(!sizes.isEmpty()){
            for(Rect2 r : sizes){
                r.printWithLoc("Empty Space Sizes");
            }
        }
        else{
            System.out.print("Table Empty\n");
        }
        
        System.out.print(
            "----------------------------\n"
        );
    }
    void printChildren(){
        System.out.print(
            "----------------------------\n"
          + "Children:\n"
        );
        if(!children.isEmpty()){
            for(Node2 n : children){
                n.printNode();
            }
        }
        else{
            System.out.print("Node Has No Children\n");
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
            Logger.getLogger(Node2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
