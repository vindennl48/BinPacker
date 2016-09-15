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
    private Point btmLeft;
    private Point topRight;
    private int origin;
    private boolean rotation;
    
//CONSTRUCTORS
    private void init(){
        btmLeft = new Point();
        topRight = new Point();
        origin = 1;
        rotation = false;
    }
    Rect(){
        init();
    }
    Rect(Rect r){
        init();
        setRect(r);
    }
    Rect(double x1, double y1, double x2, double y2){
        init();
        btmLeft.setPoint(x1, y1);
        topRight.setPoint(x2, y2);
    }
    Rect(Point btmLeft, Point topRight){
        init();
        this.btmLeft.setPoint(btmLeft);
        this.topRight.setPoint(topRight);
    }
    
//GETTERS SETTERS
    //B = bottom, L = left, R = right, T = top
    public Point getBL(){
        Point p = new Point(btmLeft);
        return p;
    }
    public Point getTR(){
        Point p = new Point(topRight);
        return p;
    }
    public Point getTL(){
        Point p = new Point(btmLeft.getX(),topRight.getY());
        return p;
    }
    public Point getBR(){
        Point p = new Point(topRight.getX(),btmLeft.getY());
        return p;
    }
    public Rect getRect(){
        return this;
    }
    
    //get width, get height
    public double getW(){
        return (topRight.getX() - btmLeft.getX());
    }
    public double getH(){
        return (topRight.getY() - btmLeft.getY());
    }
    
    //1= bottom left, 2= top right, 3= top left, 4= bottom right
    public int getOriginPos(){
        return origin;
    }
    public Point getOrigin(){
        Point p = new Point();
        
        switch(origin){
            case 1:
            {
                return getBL();
            }
            case 2:
            {
                return getTR();
            }
            case 3:
            {
                return getTL();
            }
            case 4:
            {
                return getBR();
            }
        }
        
        return p;
    }
    public boolean getRotation(){
        return rotation;
    }
    
    public void setBL(Point p){
        btmLeft.setPoint(p);
    }
    public void setTR(Point p){
        topRight.setPoint(p);
    }
    public void setTL(Point p){
        Point newBL = new Point(
            p.getX(),getBL().getY()
        );
        Point newTR = new Point(
            getTR().getX(),p.getY()
        );
        setBL(newBL);
        setTR(newTR);
    }
    public void setBR(Point p){
        Point newBL = new Point(
            getBL().getX(),p.getY()
        );
        Point newTR = new Point(
            p.getX(),getTR().getY()
        );
        setBL(newBL);
        setTR(newTR);
    }
    
    public void setRect(Rect r){
        btmLeft.setPoint(r.getBL());
        topRight.setPoint(r.getTR());
        origin = r.getOriginPos();
        rotation = r.getRotation();
    }
    public void setOriginPos(int x){
        origin = x;
    }
    public void setOrigin(Point p){
        switch(origin){
            case 1:
            {
                double width = getW();
                double height = getH();
                
                setBL(new Point(p));
                setTR(new Point(
                    p.getX()+width,
                    p.getY()+height
                ));
                break;
            }
            case 2:
            {
                double width = 0-getW();
                double height = 0-getH();
            
                setTR(new Point(p));
                setBL(new Point(
                    p.getX()+width,
                    p.getY()+height
                ));
                break;
            }
            case 3:
            {
                double width = getW();
                double height = 0-getH();
            
                setTL(new Point(p));
                setBR(new Point(
                    p.getX()+width,
                    p.getY()+height
                ));
                break;
            }
            case 4:
            {
                double width = 0-getW();
                double height = getH();
            
                setBR(new Point(p));
                setTL(new Point(
                    p.getX()+width,
                    p.getY()+height
                ));
                break;
            }
        }
    }
    public void setRotation(boolean rotate){
        if(rotation != rotate){
            //reverse rotation
            double x1 = getBL().getX();
            double y1 = getBL().getY();
            double x2 = getTR().getX();
            double y2 = getTR().getY();

            setBL(new Point(
                y1,x1
            ));
            setTR(new Point(
                y2,x2
            ));
            rotation = rotate;
        }
    }
    
//MEMBERS
    //Returns True if any part of 'this.Rect' is on top of Rect r
    public boolean isOntopOf(Rect r){
        
        if(getTR().getX() <= r.getBL().getX())
            return false;
        if(getBL().getX() >= r.getTR().getX())
            return false;
        if(getBL().getY() >= r.getTR().getY())
            return false;
        if(getTR().getY() <= r.getBL().getY())
            return false;
        
        return true;
        
    }
    //Returns True if 'this.Rect' is Inside of or the same size as Rect r
    public boolean isInsideOf(Rect r){
        if(r.getBL().getX() <= getBL().getX() && getBL().getX() <= r.getTR().getX() &&
           r.getBL().getX() <= getTR().getX() && getTR().getX() <= r.getTR().getX() &&
           r.getBL().getY()<= getBL().getY()&& getBL().getY()<= r.getTR().getY() &&
           r.getBL().getY()<= getTR().getY()&& getTR().getY()<= r.getTR().getY()){
            return true;
        }
        return false;
    }
    
    public void printRect(String name){
        System.out.print(String.format(
            "---------------------------\n"
          + "Name: %s\n"
          + "BtmLeft: %.4f, %.4f\n"
          + "TopRight: %.4f, %.4f\n"
          + "Origin: %d\n"          
          + "---------------------------\n",
            name,
            btmLeft.getX(),btmLeft.getY(),
            topRight.getX(), topRight.getY(),
            origin
          
        ));
    }
    public void printRectACAD(){
        System.out.print(String.format(
              "rectangle\n"
            + "%.4f,%.4f\n"
            + "%.4f,%.4f\n",
                getBL().getX(),
                getBL().getY(),
                getTR().getX(),
                getTR().getY()
        ));
    }
    public void printRectACAD(double offsX, double offsY){
        System.out.print(String.format(
              "rectangle\n"
            + "%.4f,%.4f\n"
            + "%.4f,%.4f\n",
                getBL().getX() + offsX,
                getBL().getY() + offsY,
                getTR().getX() + offsX,
                getTR().getY() + offsY
        ));
    }
}
