/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binpacker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Mitch
 */
public class BinPacker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
//        Rect small = new Rect(0,0,39.7654,24.0172);
//        Rect big = new Rect(0,0,48.5,72.5);
//        
//        if(small.isInsideOf(big))
//            System.out.println("true");
//        else
//            System.out.println("false");
        
//        t: 39.7654, 24.0172
//        p: 0.0000, 0.0000
        
        getInfo();
        run();
    }
    
    
    static Frame MainNode;
    
    public static void getInfo(){
        
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Please Enter Data");
        
        Rect r = new Rect();
        Point p = new Point();
        
        //set size of aluminum sheet
        p.setX(scan.nextDouble());
        p.setY(scan.nextDouble());
        
        r.setTopRight(p);
        
        MainNode = new Frame(r);
        
        //set number of tables
        int numTables = scan.nextInt();
        scan.nextLine();  //prepare for string input
        
        List<Table> newTables = new ArrayList<>();
        //list out all tables
        for(int i = 0; i < numTables; i++){
            
            String tName = scan.nextLine();
            double tWidth = scan.nextDouble();
            double tHeight = scan.nextDouble();
            double tPrice = scan.nextDouble();
            scan.nextLine();
            Table t = new Table();
            t.name = tName;
            t.size.setTopRight(new Point(tWidth, tHeight));
            t.price = tPrice;
            newTables.add(t);
        }
        
        MainNode.buildTree(newTables);
    }
    public static void run(){
        MainNode.runTree();
        
    }
}
