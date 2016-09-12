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
    private int rotation;
    private double price;
    
    
//CONSTRUCTORS
    public Table(){
        super();
        name = "NULL";
        rotation = 0;
        price = 0;
    }
    public Table(String name, Rect size, int rotation, double price){
        super(size);
        this.name = name;
        this.rotation = rotation;
        this.price = price;
    }
    public Table(Table t){
        super(t.getP1(), t.getP2());
        this.name = t.getName();
        this.rotation = t.getRotation();
        this.price = t.getPrice();
    }
    
    
//GETTERS SETTERS
    public String getName() {
        return name;
    }
    public int getRotation() {
        return rotation;
    }
    public String getRotationString(){
        return Integer.toString(rotation);
    }
    public double getPrice() {
        return price;
    }
    public String getPriceString(){
        return String.format("%.2f", price);
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setRotation(int rotation) {
        this.rotation = rotation;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    
    
//MEMBERS
    public void rotate(){
        if(rotation == 0){
            rotation = 90;
        }
        else{
            rotation = 0;
        }
        
        this.setP2(
            this.getP2().getY(),
            this.getP2().getX()
        );
        
    }

    
    
}
