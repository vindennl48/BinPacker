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
public class Rect2 {
    
//DATA
    private double width;
    private double height;
    private double locWidth;
    private double locHeight;

    
//CONSTRUCTORS
    public Rect2(){
        setWidth(0);
        setHeight(0);
        setLocWidth(0);
        setLocHeight(0);
    }
    public Rect2(double newWidth, double newHeight){
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
    void set(Rect2 newRect){
        setWidth(newRect.width);
        setHeight(newRect.height);
    }
    
    double getLocWidth(){
        return locWidth;
    }
    double getLocHeight(){
        return locHeight;
    }
    Rect2 getLoc(){
        Rect2 r = new Rect2();
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
    void setLoc(Rect2 newRect){
        setLocWidth(newRect.width);
        setLocHeight(newRect.height);
    }
    
    void setAll(Rect2 newRect){
        set(newRect);
        setLoc(newRect);
    }
    
    Rect2 getAbsWH(){
        Rect2 r = new Rect2();
        r.setWidth(getLocWidth()+getWidth());
        r.setHeight(getLocHeight()+getHeight());
        return r;
    }
    
    boolean checkOverlap(Rect2 r){
        
        double lw = r.getLocWidth();
        double lh = r.getLocHeight();
        double absW = r.getAbsWH().getWidth();
        double absH = r.getAbsWH().getHeight();
        
        double this_lw = getLocWidth();
        double this_lh = getLocHeight();
        double this_absW = getAbsWH().getWidth();
        double this_absH = getAbsWH().getHeight();
        
        if(this_lw < lw && lw < this_absW &&
           this_lh < lh && lh < this_absH){
            //location corner is inside area
            return true;
        }
        else if(this_lw < absW && absW < this_absW &&
                this_lh < absH && absH < this_absH){
            //absolute top left corner is inside area
            return true;
        }
        else{
            return false;
        }
    }
}
