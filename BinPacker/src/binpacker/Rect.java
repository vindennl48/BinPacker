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
public class Rect {
        double width;
        double height;
        
        public Rect(){
            width = 0;
            height = 0;
        }
        public Rect(double newWidth, double newHeight){
            width = newWidth;
            height = newHeight;
        }
        
        void printRect(String name){
            System.out.println(String.format(name + 
                    ": %.4f %.4f.", width, height));
        }
}
