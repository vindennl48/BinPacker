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
    
    public void set(double x, double y){
        setX(x);
        setY(y);
    }
    public void set(Point p){
        setX(p.getX());
        setY(p.getY());
    }
    
    
//MEMBERS
}
