/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binpacker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SHOP
 */
public class BinPacker {

/*############################################################################*/
    public static void main(String[] args) {
        
        BinPacker bp = new BinPacker();
        
        bp.getInfo();
        bp.run();
    }
    
/*############################################################################*/
    
//start of BinPacker Class
//DATA
    List<Node> nodes;
    List<Table> tables;
    Rect plateSize;
    
//CONSTRUCTOR
    public BinPacker(){
        nodes = new ArrayList<Node>();
        tables = new ArrayList<Table>();
        plateSize = new Rect();
    }
    
//MEMBERS
    void getInfo(){
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Please Enter Data");
        
        //set size of aluminum sheet
        plateSize.setWidth(scan.nextDouble());
        plateSize.setHeight(scan.nextDouble());
        
        //set number of tables
        int numTables = scan.nextInt();
        scan.nextLine();  //prepare for string input
        
        //list out all tables
        for(int i = 0; i < numTables; i++){
            
            String tName = scan.nextLine();
            double tWidth = scan.nextDouble();
            double tHeight = scan.nextDouble();
            double tPrice = scan.nextDouble();
            scan.nextLine();
            addTable(tName, tWidth, tHeight, tPrice);
        }
    }
    
    void addTable(String newName, double newWidth, 
            double newHeight, double newPrice){
        
        Rect size = new Rect();
        size.setWidth(newWidth);
        size.setHeight(newHeight);
        
        Table t = new Table(
                newName,
                size,
                0,
                newPrice
        );
        
        tables.add(t);
    }
    
    void run(){
        
        System.out.println("Initializing Starting Nodes");
        for(int i = 0; i < tables.size(); i++){
            
            Table t = new Table(tables.get(i));
            
            Node n = new Node(t);
            n.setTables(tables);
            n.removeTable(t.getName());
            
            Rect s1 = new Rect(
                    0, 
                    t.getP2().getY(),
                    plateSize.getP2().getX(),
                    plateSize.getP2().getY()
            );
            
            Rect s2 = new Rect(
                    t.getP2().getX(),
                    0,
                    plateSize.getP2().getX(),
                    plateSize.getP2().getY()
            );
            
            n.addEmptySpace(s1);
            n.addEmptySpace(s2);
            
            nodes.add(n);
            
        }
        System.out.println("Finished Initializing Starting Nodes\n");
        
        System.out.println("setting children");
        for(int i = 0; i < nodes.size(); i++){
            nodes.get(i).makeChildren();
        }
        System.out.println("finished setting children\n");
        
//        for(Node n : nodes){
//            n.printNode();
//        }
        
//        System.out.println("running tree");
//        tree.runTree();
//        System.out.println("finished running tree\n");
    }
}
