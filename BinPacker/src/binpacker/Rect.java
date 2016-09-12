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
public class Rect {
    
    
//DATA
    private Point p1;
    private Point p2;
    
    
//CONSTRUCTORS
    public Rect(Point p1, Point p2){
        this.p1 = new Point(p1);
        this.p2 = new Point(p2);
    }
    public Rect(double x1, double y1, double x2, double y2){
        this.p1 = new Point(x1, y1);
        this.p2 = new Point(x2, y2);
    }
    public Rect(Rect r){
        this.p1 = new Point(r.getP1());
        this.p2 = new Point(r.getP2());
    }
    public Rect(){
        p1 = new Point();
        p2 = new Point();
    }
    
    
//GETTERS SETTERS
    public Point getP1() {
        return p1;
    }
    public Point getLocation() {
        return p1;
    }
    public Point getP2() {
        return p2;
    }
    
    public Point getP3(){
        Point p = new Point(
            this.p1.getX(),
            this.p2.getY()
        );
        return p;
    }
    public Point getP4(){
        Point p = new Point(
            this.p2.getX(),
            this.p1.getY()
        );
        return p;
    }
    
    public void setP1(Point p1) {
        this.p1.set(p1);
    }
    public void setP1(double x, double y){
        this.p1.set(x,y);
    }
    
    public void setP2(Point p2) {
        this.p2.set(p2);
    }
    public void setP2(double x, double y){
        this.p2.set(x,y);
    }
    
    public void set(Rect r){
        this.p1.set(r.getP1());
        this.p2.set(r.getP2());
    }
    
    
//MEMBERS
    public Point getSize(){
        Point p = new Point(
            getP2().getX() - getP1().getX(),
            getP2().getY() - getP1().getY()
        );
        return p;
    }
    public void setSize(Point p){
        this.setWidth(p.getX());
        this.setHeight(p.getY());
    }
    
    public double getWidth(){
        return this.getSize().getX();
    }
    public double getHeight(){
        return this.getSize().getY();
    }
    
    public void setWidth(double width){
        this.p2.setX(
            this.p1.getX() + width
        );
    }
    public void setHeight(double height){
        this.p2.setY(
            this.p1.getY() + height
        );
    }
    
    public void setLocation(Point p){
        double width = this.getWidth();
        double height = this.getHeight();
        
        this.setP1(p);
        this.setWidth(width);
        this.setHeight(height);
    }
    public void setLocation(double x, double y){
        double width = this.getWidth();
        double height = this.getHeight();
        
        this.setP1(x, y);
        this.setWidth(width);
        this.setHeight(height);
    }
    
    public boolean isInside(Point p){
        
        if (this.p1.getX() < p.getX() && p.getX() < this.p2.getX()){
            if (this.p1.getY() < p.getY() && p.getY() < this.p2.getY()){
                return true;
            }
        }
        return false;
        
    }
    public boolean isInsideOrTouching(Point p){
        
        if (this.p1.getX() <= p.getX() && p.getX() <= this.p2.getX()){
            if (this.p1.getY() <= p.getY() && p.getY() <= this.p2.getY()){
                return true;
            }
        }
        return false;
        
    }
    
    public boolean isInside(Rect r){
        if(isInsideOrTouching(r.getP1()) &&
           isInsideOrTouching(r.getP2())){
            return true;
        }
        return false;
    }
    
    public boolean fits(Rect r){
        return isInside(r);
    }
    
    /*this returns the points of a given rectangle that are found inside this 
      rectangle. P1 = 1, P2, = 2, P3 = 4, P4 = 8 */
    public static final int P1_ID = 1, P2_ID = 2, P3_ID = 4, P4_ID =8;
    public int pointsInside(Rect r){
        int out = 0;
        
        if (this.isInside(r.getP1())){
            out += 1;
        }
        if (this.isInside(r.getP2())){
            out += 2;
        }
        if (this.isInside(r.getP3())){
            out += 4;
        }
        if (this.isInside(r.getP4())){
            out += 8;
        }
        
        return out;
    }
    
    public void print(String name){
        System.out.print(String.format("----------------------------\n"
          + "Rect: %s \n"
          + "Point 1: %.4f, %.4f \n"
          + "Point 2: %.4f, %.4f \n"
          + "Size: %.4f, %.4f \n"
          + "----------------------------\n", 
            name, getP1().getX(), getP1().getY(),
            getP2().getX(), getP2().getY(), 
            this.getSize().getX(), this.getSize().getY()
        )
        );
    }
    
    
}
