///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package binpacker;
//
//import java.util.Scanner;
//
///**
// *
// * @author SHOP
// */
//public class BinPacker2 {
//
///*############################################################################*/
//    public static void main(String[] args) {
//        BinPacker bp = new BinPacker();
//        bp.getInfo();
//        bp.run();
//    }
//    
///*############################################################################*/
//    
////start of BinPacker Class
//    
////DATA
//    Node2 tree;
//    Table2 t;
//    
////CONSTRUCTOR
//    public BinPacker2(){
//        tree = new Node2();
//        tree.resetNumChildren();
//        t = new Table2();
//    }
//    
////MEMBERS
//    void getInfo(){
//        Scanner scan = new Scanner(System.in);
//        
//        System.out.println("Please Enter Data");
//        
//        //set size of aluminum sheet
//        setWidth(scan.nextDouble());
//        setHeight(scan.nextDouble());
//        
//        //set number of tables
//        int numTables = scan.nextInt();
//        scan.nextLine();  //prepare for string input
//        
//        //list out all tables
//        for(int i = 0; i < numTables; i++){
//            
//            String tName = scan.nextLine();
//            double tWidth = scan.nextDouble();
//            double tHeight = scan.nextDouble();
//            double tPrice = scan.nextDouble();
//            scan.nextLine();
//            
//            if(i == 0){
//                setFirstTable(tName, tWidth, tHeight, tPrice);
//            }
//            else{
//                addTable(tName, tWidth, tHeight, tPrice);
//            }
//            
//        }
//    }
//    
//    void addTable(String newName, double newWidth, 
//            double newHeight, double newPrice){
//        
//        tree.tableList.add(
//            new Table2(
//                newName,
//                0,
//                newWidth,
//                newHeight,
//                newPrice
//            )
//        );
//    }
//    
//    void setFirstTable(String newName, double newWidth, 
//            double newHeight, double newPrice){
//        tree.setTable(
//            new Table2(
//                newName, 0, newWidth, newHeight, newPrice
//            )
//        );
//    }
//    
//    void setWidth(double newWidth){
//        tree.setNodeSizeWidth(newWidth);
//    }
//    
//    void setHeight(double newHeight){
//        tree.setNodeSizeHeight(newHeight);
//    }
//    
//    void run(){
//        System.out.println("setting children");
//        tree.setChildren();
//        System.out.println("finished setting children");
//        
//        //tree.pauseCmd();
//        
//        System.out.println("running tree");
//        tree.runTree();
//        System.out.println("finished running tree");
//    }
//
//}
