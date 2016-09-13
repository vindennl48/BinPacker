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

//GETTERS SETTERS
    public Rect getRect(){
        return size;
    }
    public void set(Table t){
        
    }

//MEMBERS
    public void setOrigin(Point p){
        
    }
    
}
