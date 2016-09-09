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
        Scanner scan = new Scanner(System.in);
        BinPacker bp = new BinPacker();
        
        System.out.println("Please Enter Data");
        
//        System.out.println("Size of Aluminum Sheet "
//                + "(width 'space' height 'enter'):");
        bp.tree.size.width = scan.nextInt();
        bp.tree.size.height = scan.nextInt();
        
//        System.out.println("Number of Tables:");
        int numTables = scan.nextInt();
        scan.nextLine();  //prepare for string input
        
//        System.out.println("List Out All Tables (Table Name 'space' "
//                + "Table Width 'space' Table Height 'space' "
//                + "Table Price 'enter'):");
        
        for(int i = 0; i < numTables; i++){
            
            String tName = scan.nextLine();
            double tWidth = scan.nextDouble();
            double tHeight = scan.nextDouble();
            double tPrice = scan.nextDouble();
            scan.nextLine();
            
            if(i == 0){
                bp.setFirstTable(tName, tWidth, tHeight, tPrice);
            }
            else{
                bp.addTable(tName, tWidth, tHeight, tPrice);
            }
            
        }
        
//        for(int i = 0; i < numTables; i++){
//            System.out.println(
//                    bp.tree.tableList.get(i).name + " "
//                    + Double.toString(bp.tree.tableList.get(i).sellingPrice)
//            );
//        }

        bp.run();
        
    }
    
/*############################################################################*/
    
//start of BinPacker Class
    
//DATA
    Node tree;
    Table t;
    
//CONSTRUCTOR
    public BinPacker(){
        tree = new Node();
        tree.numChildren = 0;
        t = new Table();
    }
    
//MEMBERS
    void addTable(String newName, double newWidth, 
            double newHeight, double newPrice){
        
        tree.tableList.add(
            new Table(
                newName,
                0,
                newWidth,
                newHeight,
                newPrice
            )
        );
        
        addTableInv(newName, newWidth, newHeight, newPrice);
    }
    
    void addTableInv(String newName, double newWidth, 
            double newHeight, double newPrice){
        
        tree.tableList_inv.add(
            new Table(
                newName,
                90,
                newHeight,
                newWidth,
                newPrice
            )
        );
    }
    
    void setFirstTable(String newName, double newWidth, 
            double newHeight, double newPrice){
        tree.table = new Table(newName, 0, newWidth, newHeight,newPrice);
    }
    
    void run(){
        System.out.println("setting children");
        tree.setChildren();
        System.out.println("finished setting children");
        
        System.out.println("running tree");
        tree.runTree();
        System.out.println("finished running tree");
    }

}
