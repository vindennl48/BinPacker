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
public class Table {
//DATA
    String name;
    Rect size;
    double price;
    boolean rotation;

//CONSTRUCTORS
    private void init(){
        name = "";
        size = new Rect();
        price = 0;
        rotation = false;
    }
    Table(){
        init();
    }
    Table(Table t){
        init();
        this.set(t);
    }

//GETTERS SETTERS
    public Rect getRect(){
        return size;
    }
    public void set(Table t){
        name = t.name;
        size.set(t.getRect());
        price = t.price;
        rotation = t.rotation;
    }

//MEMBERS
    public void setOrigin(Point p){
        double width = (size.getTopRight().getX() - size.getBtmLeft().getX());
        double height = (size.getTopRight().getY() - size.getBtmLeft().getY());
        
        size.setBtmLeft(p);
        size.setTopRight(new Point(
                width + p.getX(),
                height + p.getY()
        ));
    }
    public void setOriginTopRight(){
        
    }
    public void rotate(){
        double topRightX = size.getTopRight().getX();
        double topRightY = size.getTopRight().getY();
        
        size.setTopRight(new Point(
                topRightY,
                topRightX
        ));
        
        if(!rotation)
            rotation = true;
        else
            rotation = false;
    }
    
    public void printTable(){
        System.out.print(
        String.format(
            "----------------------------\n"
          + "Table: %s \n"
          + "BtmLeft: %.4f, %.4f \n"
          + "TopRight: %.4f, %.4f \n"
          + "Orient: %s \n"
          + "Price: %.2f \n"
          + "----------------------------\n",
            name, 
            size.getBtmLeft().getX(), 
            size.getBtmLeft().getY(),
            size.getTopRight().getX(),
            size.getTopRight().getY(),
            Boolean.toString(rotation), 
            price
        )
        );
    }
    public void printACAD(double offset){
        System.out.print(String.format(
              "rectangle\n"
            + "%.4f,%.4f\n"
            + "%.4f,%.4f\n",
                size.getBtmLeft().getX() + offset,
                size.getBtmLeft().getY(),
                size.getTopRight().getX() + offset,
                size.getTopRight().getY()
        ));
    }
    public void printACAD(double offsetX, double offsetY){
        System.out.print(String.format(
              "rectangle\n"
            + "%.4f,%.4f\n"
            + "%.4f,%.4f\n",
                size.getBtmLeft().getX() + offsetX,
                size.getBtmLeft().getY() + offsetY,
                size.getTopRight().getX() + offsetX,
                size.getTopRight().getY() + offsetY
        ));
    }
    
}
