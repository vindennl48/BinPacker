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
    
//DATA
    private double width;
    private double height;

    
//CONSTRUCTORS
    public Rect(){
        setWidth(0);
        setHeight(0);
    }
    public Rect(double newWidth, double newHeight){
        setWidth(newWidth);
        setHeight(newHeight);
    }

    
//MEMBERS
    void print(String name){
        System.out.print(
        String.format(
            "----------------------------\n"
          + "Rect: %s \n"
          + "Size: %.4f, %.4f \n"
          + "----------------------------\n", 
            name, width, height
        )
        );
    }
    
    
//GETTERS SETTERS
    double getWidth(){
        return width;
    }
    double getHeight(){
        return height;
    }
    void setWidth(double newWidth){
        width = newWidth;
    }
    void setHeight(double newHeight){
        height = newHeight;
    }
    void set(Rect newRect){
        setWidth(newRect.width);
        setHeight(newRect.height);
    }
}
