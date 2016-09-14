/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binpacker;

/**
 *
 * @author Mitch
 */
public class Point {
    
//DATA
    private double x;
    private double y; 
    
    
//CONSTRUCTOR
    Point(){
        x = 0;
        y = 0;
    }
    Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    Point(Point p){
        x = p.getX();
        y = p.getY();
    }
    
    
//GETTERS SETTERS
    public double getX(){
        return x;
    }
    public void setX(double x){
        this.x = x;
    }
    public double getY(){
        return y;
    }
    public void setY(double y){
        this.y = y;
    }
    
    public void setPoint(double x, double y){
        setX(x);
        setY(y);
    }
    public void setPoint(Point p){
        setX(p.getX());
        setY(p.getY());
    }
    
    
//MEMBERS
    public boolean isEqualTo(Point p){
        if(x == p.getX() && y == p.getY())
            return true;

        return false;
    }
    public boolean isInsideOf(Rect r){
        if(r.getBL().getX() < x && x < r.getTR().getX() &&
           r.getBL().getY() < y && y < r.getTR().getY()){
            return true;
        }
        return false;
    }
    
    public void printPoint(){
        System.out.println(String.format(
              "---------------------\n"
            + "Point: \n"
            + "x: %.4f, y: %.4f"
            + "---------------------\n",
                x,y
        ));
    }
    public void printPointACAD(){
        System.out.print(String.format(
                "circle\n"
              + "%.4f,%.4f\n"
              + "d\n"
              + "1\n",
                getX(),getY()
        ));
    }
    public void printPointACAD(double offsX, double offsY){
        System.out.print(String.format(
                "circle\n"
              + "%.4f,%.4f\n"
              + "d\n"
              + "1\n",
                getX()+offsX,getY()+offsY
        ));
    }
}
