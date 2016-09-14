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
        
        Rect small = new Rect(20.0802,20.0802,44.0974,59.8456);
        Rect big = new Rect(20.0802,20.0802,40.1604,51.9715);
        
//        20.0802,20.0802 | 44.0974,59.8456
//
//        20.0802,20.0802 | 40.1604,51.9715
        
        if(small.isInsideOf(big))
            System.out.println("inside true");
        else
            System.out.println("inside false");
        
        if(small.isOntopOf(big))
            System.out.println("ontop true");
        else
            System.out.println("ontop false");
        
        
//        getInfo(
//            "48.5\n" +
//            "72.5\n" +
//            "7\n" +
//            "10060GAL\n" +
//            "39.7654\n" +
//            "24.0172\n" +
//            "2421.5\n" +
//            "8050GAL\n" +
//            "31.8913\n" +
//            "20.0802\n" +
//            "1561\n" +
//            "7550GAL\n" +
//            "29.9228\n" +
//            "20.0802\n" +
//            "1438.5\n" +
//            "7050GAL\n" +
//            "27.9543\n" +
//            "20.0802\n" +
//            "1385\n" +
//            "6050GAL\n" +
//            "24.0172\n" +
//            "20.0802\n" +
//            "1244.65\n" +
//            "5050GAL\n" +
//            "20.0802\n" +
//            "20.0802\n" +
//            "1106.2\n" +
//            "3020GAL\n" +
//            "12.2061\n" +
//            "8.2691\n" +
//            "400\n"
//        );
//        run();
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
    
    public static void getInfo(String s){
        
        InputStream stream = new ByteArrayInputStream(s.getBytes());
        
        Scanner scan = new Scanner(stream);
        
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
