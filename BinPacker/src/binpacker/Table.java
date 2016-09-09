/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binpacker;

/**
 *
 * @author SHOP
 */
public class Table {
        //if no table exists, name = "NULL"
        String name;
        Rect size;
        int orientation;
        double sellingPrice;
        
        public Table(){
            name = "NULL";
            orientation = 0;
            size = new Rect();
            sellingPrice = 0;
        }
        
        public Table(String newName, int newOrient, double newWidth, 
            double newHeight, double newPrice){
            name = newName;
            orientation = newOrient;
            size = new Rect(newWidth,newHeight);
            sellingPrice = newPrice;
        }
        
}
