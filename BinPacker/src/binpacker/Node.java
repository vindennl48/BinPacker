/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binpacker;

import static binpacker.HandyFunctions.pauseCmd;
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
    private double branchValue;
    private String tableOut;
    
    
//CONSTRUCTORS
    public Node(){
        super();
        numChildren++;
        tables = new ArrayList<Table>();
        children = new ArrayList<Node>();
        emptySpace = new ArrayList<Rect>();
        branchValue = 0;
        tableOut = "";
    }
    public Node(String name, Rect size, int rotation, double price){
        super(name, size, rotation, price);
        tables = new ArrayList<Table>();
        children = new ArrayList<Node>();
        emptySpace = new ArrayList<Rect>();
        branchValue = 0;
        tableOut = "";
    }
    public Node(Table t){
        super(t);
        tables = new ArrayList<Table>();
        children = new ArrayList<Node>();
        emptySpace = new ArrayList<Rect>();
        branchValue = 0;
        tableOut = "";
    }
    
    
//GETTERS SETTERS
    public void setTables(List<Table> a){
        tables.clear();
        
        for(Table t : a){
            Table nt = new Table(t);
            tables.add(nt);
        }
    }
    public void setChildren(List<Node> a){
        children.clear();
        
        for(Node n : a){
            Node nn = new Node(n);
            children.add(nn);
        }
    }
    public void setEmptySpace(List<Rect> a){
        emptySpace.clear();
        
        for(Rect r : a){
            Rect nr = new Rect(r);
            emptySpace.add(nr);
        }
    }
    
    public int getTableListSize(){
        return tables.size();
    }
    public Table getTable(int itr){
        Table t = new Table();
        
        if(!tables.isEmpty()){
            t.set(tables.get(itr));
        }
        
        return t;
    }
    public void addTable(Table t){
        tables.add(t);
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
        
        setTables(tList);
        
        return deleted;
    }
    
    public void setBranchValue(double newVal){
        branchValue = newVal;
    }
    public double getBranchValue(){
        return branchValue;
    }
    
    public void setTableOut(String s){
        tableOut = s;
    }
    public String getTableOut(){
        return tableOut;
    }
    
    public int getEmptySpaceSize(){
        return emptySpace.size();
    }
    public Rect getEmptySpace(int itr){
        Rect r = new Rect();
        
        if(!emptySpace.isEmpty()){
            r.set(emptySpace.get(itr));
        }
        
        return r;
    }
    public void addEmptySpace(Rect r){
        emptySpace.add(r);
    }
    public boolean removeEmptySpace(Point location){
        List<Rect> esList = new ArrayList<Rect>();
        boolean deleted = false;
        
        if(!emptySpace.isEmpty()){
            for(Rect r : emptySpace){
                
                Rect nr = new Rect(r);
                
                if(!nr.getP1().isEqual(location)){
                    esList.add(nr);
                }
                else{
                    if(!deleted){
                        deleted = true;
                    }
                    else{
                        esList.add(nr);
                    }
                }
            }
        }
        this.setEmptySpace(esList);
        
        return deleted;
    }
    
    public int getChildListSize(){
        return children.size();
    }
    public Node getChild(int itr){
        Node n = new Node();
        
        if(!children.isEmpty()){
            n.set(children.get(itr));
        }
        
        return n;
    }
    public void addChild(Node n){
        children.add(n);
    }
    public void makeChild(Table t){
        
        Node child = new Node(t);
        
        child.setTables(tables);
        child.removeTable(t.getName());
        
        child.setEmptySpace(emptySpace);
        
        child.checkEmptySpace();
        
        children.add(child);
    }
    public void makeChildren(){
        if(!emptySpace.isEmpty()){
            for(int j = 0; j < emptySpace.size(); j++){
                Rect empty = new Rect(emptySpace.get(j));

                if(!tables.isEmpty()){
                    for(int i = 0; i < tables.size(); i++){
                        Table t1 = new Table(tables.get(i));
                        t1.setLocation(empty.getLocation());
                        
                        Table t2 = new Table(tables.get(i));
                        t2.rotate();
                        t2.setLocation(empty.getLocation());

                        if(empty.fits(t1.getRect())){
                            makeChild(t1);
                        }
                        if(empty.fits(t2.getRect())){
                            makeChild(t2);
                        }
                    }
                }
            }
        }
        
        if(!children.isEmpty()){
            for(int i = 0; i < children.size(); i++){
                children.get(i).makeChildren();
            }
        }
    }
    
    
//MEMBERS
    
    public void checkEmptySpace(){
        if(!emptySpace.isEmpty()){
            
            boolean ifOverlapped = false;
            Rect exSp = new Rect();
            
            for(int i = 0;  i < emptySpace.size(); i++){
                
                Rect empty = new Rect(emptySpace.get(i));
                
                //if points p2 and p4 are inside
                if(empty.pointsInside(this.getRect()) == P2_ID + P4_ID){
                    ifOverlapped = true;
                    
                    //split intersecting empty space up
                    Rect s1 = new Rect(
                            empty.getP1().getX(),
                            empty.getP1().getY(),
                            empty.getP2().getX(),
                            this.getP1().getY()
                    );
                    Rect s2 = new Rect(
                            this.getP2().getX(),    //p1 x
                            empty.getP1().getY(), //p1 y
                            empty.getP2().getX(), //p2 x
                            empty.getP2().getY()  //p2 y
                    );
                    
                    this.removeEmptySpace(empty.getLocation());
                    this.addEmptySpace(s1);
                    this.addEmptySpace(s2);
                    
                    i = -1; //reset loop
                    
                }
                else if(empty.pointsInside(this.getRect()) == P2_ID + P3_ID){
                    ifOverlapped = true;
                    
                    //split intersecting empty space up
                    Rect s1 = new Rect(
                            empty.getP1().getX(),
                            empty.getP1().getY(),
                            this.getP2().getX(),
                            empty.getP1().getY()
                    );
                    Rect s2 = new Rect(
                            empty.getP2().getX(),    //p1 x
                            this.getP1().getY(), //p1 y
                            empty.getP2().getX(), //p2 x
                            empty.getP2().getY()  //p2 y
                    );
                    
                    this.removeEmptySpace(empty.getLocation());
                    this.addEmptySpace(s1);
                    this.addEmptySpace(s2);
                    
                    i = -1; //reset loop
                    
                }
                else if(empty.getP1().isEqual(this.getP1())){
                    
                    Rect s1 = new Rect(empty);
                    exSp.set(empty);
                    
                    if(this.getRotation() == 90){
                        
                        Point tP1 = new Point(this.getP1());
                        
                        tP1.setX(this.getP2().getX());
                        
                        s1.setP1(tP1);
                        exSp = new Rect(
                                this.getP2().getX(),
                                empty.getP1().getY(),
                                empty.getP2().getX(),
                                empty.getP2().getY()
                        );
                    }
                    else{
                        
                        Point tP1 = new Point(this.getP1());
                        
                        tP1.setY(this.getP2().getY());
                        
                        s1.setP1(tP1);
                        exSp = new Rect(
                                this.getP2().getX(),
                                empty.getP1().getY(),
                                empty.getP2().getX(),
                                empty.getP2().getY()
                        );
                    }
                    
                    this.removeEmptySpace(empty.getLocation());
                    this.addEmptySpace(s1);
                    
                    i = -1; //reset loop
                    
                }
                
            }
            
            if(!ifOverlapped){
                this.addEmptySpace(exSp);
            }
            
        }
    }
    public void calculateBranchValue(){
        
        double topValue = 0;
        int itr = 0;
        
        if(!children.isEmpty()){
            for(int i = 0; i < children.size(); i++){

                Node n = children.get(i);

                if(n.getBranchValue() == 0){
                    n.calculateBranchValue();
                }

                if(n.getBranchValue() > topValue){
                    topValue = n.getBranchValue();
                    itr = i;
                }
            }
            
            tableOut = String.format(
                "%s: %.4f, %.4f | %.4f, %.4f | R: %d | Size: %.4f, %.4f\n",
                getName(), getP1().getX(), getP1().getY(),
                getP2().getX(),getP2().getY(), getRotation(),
                getWidth(), getHeight()
            );
            tableOut += children.get(itr).getTableOut();
            
            setBranchValue(getPrice() + children.get(itr).getBranchValue());

        }
        else{
            setBranchValue(getPrice());
            tableOut = String.format(
                "%s: %.4f, %.4f | %.4f, %.4f | Size: %.4f, %.4f\n",
                getName(), getP1().getX(), getP1().getY(),
                getP2().getX(),getP2().getY(),
                getWidth(), getHeight()
            );
        }
        
    }
    
    //print functions
    public void printEmptySpace(){
        System.out.print(String.format(
            "############################\n"
          + "EmptySpace:\n"
        ));
        
        for(Rect r : emptySpace){
            r.print("Empty Space");
        }
        
        System.out.print(String.format(
            "############################\n"
        ));
    }
    public void printNode(){
        
        System.out.print(
        String.format(
            "############################\n"
          + "Node: \n"
          + "Table: %s\n"
          + "Rotation: %d\n"
          + "Num Children: %d\n"
          + "Num of Sizes: %d\n"
          + "Location: %.4f, %.4f\n",
            this.getName(), this.getRotation(), children.size(), emptySpace.size(),
            this.getLocation().getX(), this.getLocation().getY()
        )
        );
        
        printTableList();
        printSizes();
        printChildren();
        
        System.out.print("############################\n");
        //pauseCmd();
    }
    public void printTableList(){
        
        System.out.print(
            "----------------------------\n"
          + "Table List:\n"
        );
        
        if(!tables.isEmpty()){
            for(Table t : tables){
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
    public void printSizes(){
        System.out.print(
            "----------------------------\n"
          + "Size List:\n"
        );
        if(!emptySpace.isEmpty()){
            for(Rect r : emptySpace){
                r.print("Empty Space Sizes");
            }
        }
        else{
            System.out.print("Table Empty\n");
        }
        
        System.out.print(
            "----------------------------\n"
        );
    }
    public void printChildren(){
        System.out.print(
            "----------------------------\n"
          + "Children:\n"
        );
        if(!children.isEmpty()){
            for(Node n : children){
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
    public void printAnswer(){
        System.out.print(String.format(
                "=========================================\n"
              + "%s\n"
              + "Sales: $%.2f\n"
              + "=========================================\n",
                tableOut, branchValue
        ));
    }
    
}
