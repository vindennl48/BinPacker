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
    private double locWidth;
    private double locHeight;

    
//CONSTRUCTORS
    public Rect(){
        setWidth(0);
        setHeight(0);
        setLocWidth(0);
        setLocHeight(0);
    }
    public Rect(double newWidth, double newHeight){
        setWidth(newWidth);
        setHeight(newHeight);
        setLocWidth(0);
        setLocHeight(0);
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
    
    void printWithLoc(String name){
        System.out.print(
        String.format(
            "----------------------------\n"
          + "Rect: %s \n"
          + "Size: %.4f, %.4f \n"
          + "Loc: %.4f, %.4f \n"
          + "----------------------------\n", 
            name, width, height, locWidth, locHeight
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
    
    double getLocWidth(){
        return locWidth;
    }
    double getLocHeight(){
        return locHeight;
    }
    Rect getLoc(){
        Rect r = new Rect();
        r.setWidth(locWidth);
        r.setHeight(locHeight);
        return r;
    }
    void setLocWidth(double newLocWidth){
        locWidth = newLocWidth;
    }
    void setLocHeight(double newLocHeight){
        locHeight = newLocHeight;
    }
    void setLoc(Rect newRect){
        setLocWidth(newRect.width);
        setLocHeight(newRect.height);
    }
}
