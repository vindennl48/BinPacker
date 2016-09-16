/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binpacker;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
        UsefulFuncs.verboseOn(false);
        
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
        
        r.setTR(p);
        
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
            t.setName(tName);
            t.setTR(new Point(tWidth, tHeight));
            t.setPrice(tPrice);
            
            //0deg origin1
            Table t1 = new Table(t);
            newTables.add(t1);
            
            //0deg origin2
            t.setOriginPos(2);
            Table t2 = new Table(t);
            newTables.add(t2);
            
            //0deg origin3
            t.setOriginPos(3);
            Table t3 = new Table(t);
            newTables.add(t3);
            
            //0deg origin4
            t.setOriginPos(4);
            Table t4 = new Table(t);
            newTables.add(t4);
            
            
            if(t.getW() != t.getH()){
                //90deg origin1
                t.setOriginPos(1);
                t.setRotation(true);
                Table t5 = new Table(t);
                newTables.add(t5);

                //90deg origin 2
                t.setOriginPos(2);
                Table t6 = new Table(t);
                newTables.add(t6);

                //90deg origin 3
                t.setOriginPos(3);
                Table t7 = new Table(t);
                newTables.add(t7);

                //90deg origin 4
                t.setOriginPos(4);
                Table t8 = new Table(t);
                newTables.add(t8);
            }
            
        }
        
        MainNode.buildTree(newTables);
    }
    
    public static void getInfo(String s){
        
        InputStream stream = new ByteArrayInputStream(s.getBytes());
        
        Scanner scan = new Scanner(stream);
        
        System.out.println("Please Enter Data");
        
        Rect r = new Rect();
        Point p = new Point();
        
        //set size of aluminum sheet
        p.setX(scan.nextDouble());
        p.setY(scan.nextDouble());
        
        r.setTR(p);
        
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
            t.setName(tName);
            t.setTR(new Point(tWidth, tHeight));
            t.setPrice(tPrice);
            
            //0deg origin1
            Table t1 = new Table(t);
            newTables.add(t1);
            
            //0deg origin2
            t.setOriginPos(2);
            Table t2 = new Table(t);
            newTables.add(t2);
            
            //0deg origin3
            t.setOriginPos(3);
            Table t3 = new Table(t);
            newTables.add(t3);
            
            //0deg origin4
            t.setOriginPos(4);
            Table t4 = new Table(t);
            newTables.add(t4);
            
            
            if(t.getW() != t.getH()){
                //90deg origin1
                t.setOriginPos(1);
                t.setRotation(true);
                Table t5 = new Table(t);
                newTables.add(t5);

                //90deg origin 2
                t.setOriginPos(2);
                Table t6 = new Table(t);
                newTables.add(t6);

                //90deg origin 3
                t.setOriginPos(3);
                Table t7 = new Table(t);
                newTables.add(t7);

                //90deg origin 4
                t.setOriginPos(4);
                Table t8 = new Table(t);
                newTables.add(t8);
            }
            
        }
        
        MainNode.buildTree(newTables);
    }
    
    public static void run(){
        MainNode.runTreeFirst();
        MainNode.removeTreeDuplicates();
        
        System.out.println("Finished Calculating..");

        String s = MainNode.StringTreeACAD();
        
        UsefulFuncs.StringToClipboard(s);
        
        System.out.println("Result Copied to Clipboard\n");
        
        System.out.println(
            String.format(
                "\nSelling Price of Plate: $%.2f", 
                MainNode.Tree.get(0).getPrice()
            ));
    }
}
