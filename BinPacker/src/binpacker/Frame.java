/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binpacker;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mitch
 */
public class Frame {
    
//DATA
    List<Table> tables;
    PolyRect emptySpace;
    Rect env;
    List<Point> placePoints;
    

//CONSTRUCTORS
    private void init(){
        tables = new ArrayList<>();
        emptySpace = new PolyRect();
        env = new Rect();
        placePoints = new ArrayList<>();
    }
    Frame(){
        init();
    }
    

//GETTERS SETTERS
    public boolean tryTable(Table t){
        for(Point p : placePoints){
            
            Table t2 = new Table();
            t2.set(t);
            
            t2.setOrigin(p);
            return emptySpace.checkSpace(t2.getRect());
            
        }
    }
    public void addTable(Table t){
        tables.add(t);
        emptySpace.addFullSpace(t.getRect());
    }

//MEMBERS
    
    
}
