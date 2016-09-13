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
    private Point btmLeft;
    private Point topRight;
    
//CONSTRUCTORS
    private void init(){
        btmLeft = new Point();
        topRight = new Point();
    }
    Rect(){
        init();
    }
    Rect(double x1, double y1, double x2, double y2){
        init();
        btmLeft.set(x1, y1);
        topRight.set(x2, y2);
    }
    Rect(Point btmLeft, Point topRight){
        init();
        this.btmLeft.set(btmLeft);
        this.topRight.set(topRight);
    }
    
//GETTERS SETTERS
    public Point getBtmLeft(){
        return btmLeft;
    }
    public Point getTopRight(){
        return topRight;
    }
    public Point getTopLeft(){
        
    }
    public Point getBtmRight(){
        
    }
    public void setBtmLeft(Point p){
        btmLeft.set(p);
    }
    public void setTopRight(Point p){
        topRight.set(p);
    }
    public void set(Rect r){
        btmLeft.set(r.getBtmLeft());
        topRight.set(r.getTopRight());
    }
    
//MEMBERS
    /*Returns True if any corner of 'this.Rect' is on top of Rect r*/
    public boolean isOntopOf(Rect r){
        if(r.getBtmLeft().getX() < btmLeft.getX() && btmLeft.getX() < r.getTopRight().getX()){
            return true;
        }
        else if(r.getBtmLeft().getX() < topRight.getX() && topRight.getX() < r.getTopRight().getX()){
            return true;
        }
        else if(r.getBtmLeft().getY()< btmLeft.getY()&& btmLeft.getY()< r.getTopRight().getY()){
            return true;
        }
        else if(r.getBtmLeft().getY()< topRight.getY()&& topRight.getY()< r.getTopRight().getY()){
            return true;
        }
        return false;
    }
    /*Returns True if 'this.Rect' is Inside of Rect r*/
    public boolean isInsideOf(Rect r){
        if(r.getBtmLeft().getX() < btmLeft.getX() && btmLeft.getX() < r.getTopRight().getX() &&
           r.getBtmLeft().getX() < topRight.getX() && topRight.getX() < r.getTopRight().getX() &&
           r.getBtmLeft().getY()< btmLeft.getY()&& btmLeft.getY()< r.getTopRight().getY() &&
           r.getBtmLeft().getY()< topRight.getY()&& topRight.getY()< r.getTopRight().getY()){
            return true;
        }
        return false;
    }
    
}
