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
    
    
//CONSTRUCTORS
    public Point(Point p){
        x = p.getX();
        y = p.getY();
    }
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    public Point(){
        x = 0;
        y = 0;
    }
    
    
//GETTERS SETTERS
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void set(Point p){
        x = p.getX();
        y = p.getY();
    }
    public void set(double x, double y){
        this.x = x;
        this.y = y;
    }
    
    
//MEMBERS
    public void add(Point p){
        x += p.getX();
        y += p.getY();
    }
    public void subt(Point p){
        x -= p.getX();
        y -= p.getY();
    }
    public boolean isEqual(Point p){
        if(p.getX() == x && p.getY() == y){
            return true;
        }
        else{
            return false;
        }
    }
    
    
}
