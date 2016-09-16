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
public class Table extends Rect {
//DATA
    private String name;
    private double price;

//CONSTRUCTORS
    private void init(){
        name = "";
        price = 0;
    }
    Table(){
        super();
        init();
    }
    Table(Table t){
        super();
        init();
        setTable(t);
    }

//GETTERS SETTERS
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public Rect getSize(){
        return getRect();
    }
    
    public void setName(String name){
        this.name = name;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public void setTable(Table t){
        name = t.name;
        setSize(t.getSize());
        price = t.price;
    }
    public void setSize(Rect r){
        setRect(r);
    }

//MEMBERS    
    public void printTable(){
        System.out.print(
        String.format(
            "----------------------------\n"
          + "Table: %s \n"
          + "BtmLeft: %.4f, %.4f \n"
          + "TopRight: %.4f, %.4f \n"
          + "Orient: %s \n"
          + "Origin Pos: %d \n"
          + "Price: %.2f \n"
          + "----------------------------\n",
            name, 
            getBL().getX(), 
            getBL().getY(),
            getTR().getX(),
            getTR().getY(),
            Boolean.toString(getRotation()), 
            getOriginPos(),
            price
        )
        );
    }
    public void printTableACAD(){
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
    public void printTableACAD(double offsX, double offsY){
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
    
    public String StringTableACAD(double offsX, double offsY){
        return (String.format(
              "rectangle\n"
            + "%.4f,%.4f\n"
            + "%.4f,%.4f\n",
                getBL().getX() + offsX,
                getBL().getY() + offsY,
                getTR().getX() + offsX,
                getTR().getY() + offsY
        ));
    }
    
    public String printStringTableACAD(double offsX, double offsY){
        return (String.format(
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
