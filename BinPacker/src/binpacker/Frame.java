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
    static List<Frame> Tree = new ArrayList<>();
    

//CONSTRUCTORS
    private void init(){
        tables = new ArrayList<>();
        emptySpace = new PolyRect();
        env = new Rect();
        placePoints = new ArrayList<>();
    }
    Frame(Rect env){
        init();
        this.env.set(env);
        placePoints.add(env.getBtmLeft());
    }
    Frame(Frame f){
        init();
        
        setTables(f.tables);
        emptySpace.set(f.emptySpace);
        env.set(f.env);
        setPlacePoints(f.placePoints);
    }
    

//GETTERS SETTERS
    public boolean tryTable(Table t){
        
        if(placePoints.isEmpty())
            return false;
        
        for(Point p : placePoints){

            Table t2 = new Table();
            t2.set(t);

            t2.setOrigin(p);
            if (emptySpace.checkSpace(t2.getRect())){
                return true;
            }
        }
        return false;
    }
    public void addTable(Table t, Point placepoint){
        
        Table tNew = new Table();
        tNew.set(t);
        tNew.setOrigin(placepoint);
        
        if(emptySpace.addFullSpace(tNew.getRect()))
            removeTable(tNew);
        
        removePlacePoint(placepoint);
        placePoints.add(tNew.size.getTopLeft());
        placePoints.add(tNew.size.getBtmRight());
        
    }
    public boolean removeTable(Table t){
        
        if(tables.isEmpty())
            return false;
        
        for(int i = 0; i < tables.size(); i++){
            if(tables.get(i).name.equals(t.name)){
                tables.remove(i);
                return true;
            }
        }
        return false;
    }
    public void setTables(List<Table> tList){
        tables.clear();
        for(Table t : tList){
            Table tNew = new Table();
            tNew.set(t);
            tables.add(tNew);
        }
    }
    public void setPlacePoints(List<Point> pList){
        placePoints.clear();
        for(Point p : pList){
            Point pNew = new Point();
            pNew.set(p);
            placePoints.add(pNew);
        }
    }
    public boolean removePlacePoint(Point p){
        if(placePoints.isEmpty())
            return false;
        
        for(int i = 0; i < placePoints.size(); i++){
            if(placePoints.get(i).isEqualTo(p)){
                placePoints.remove(i);
                return true;
            }
        }
        return false;
    }
    
    
//MEMBERS
    public void initTree(){
        
    }
    public void startBuildingTree(List<Table> TableMasterList){
        for(Table t : TableMasterList){
            
            Frame f = new Frame(env);
            f.setTables(TableMasterList);
            
            if(f.tryTable(t))
                f.addTable(t, new Point(0,0));
            
            f.buildTree();
        }
    }
    public void buildTree(){
        Frame f = new Frame(this);
        
    }
    
}
