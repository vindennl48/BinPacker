/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binpacker;

import static binpacker.UsefulFuncs.pauseCmd;
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
        env = new Rect();
        emptySpace = new PolyRect(env);
        placePoints = new ArrayList<>();
    }
    Frame(Rect env){
        init();
        this.env.set(env);
        emptySpace.setEnv(env);
        placePoints.add(env.getBtmLeft());
    }
    Frame(Frame f){
        init();
        
        setTables(f.tables);
        emptySpace.set(f.emptySpace);
        this.env.set(f.env);
        setPlacePoints(f.placePoints);
    }
    

//GETTERS SETTERS
    public boolean tryTable(Table t, Point p){
        
        if(placePoints.isEmpty())
            return false;
        
        Table t2 = new Table();
        t2.set(t);

        t2.setOrigin(p);
        if (emptySpace.checkSpace(t2.getRect()))
            return true;
        
        return false;
    }
    public void addTable(Table t, Point p){
        
        Table tNew = new Table();
        tNew.set(t);
        tNew.setOrigin(p);
        
        if(emptySpace.addFullSpace(tNew)){
            removeTable(tNew);
            removePlacePoint(p);
            placePoints.add(tNew.size.getTopLeft());
            placePoints.add(tNew.size.getBtmRight());
        }
    }
    public boolean removeTable(Table t){
        System.out.println("Removing Table");
        
        if(tables.isEmpty()){
            System.out.println("False1");
            pauseCmd();
            return false;
        }
        
        for(int i = 0; i < tables.size(); i++){
            if(tables.get(i).name.equals(t.name)){
                tables.remove(i);
                System.out.println("True1");
                for(Table tt : tables){
                    tt.printTable();
                }
                pauseCmd();
                return true;
            }
        }
        System.out.println("False2");
        pauseCmd();
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
    public void setTree(List<Frame> LF){
        Tree.clear();
        for(Frame f : LF){
            Frame fNew = new Frame(f);
            Tree.add(fNew);
        }
    }
    
    
//MEMBERS
    public void buildTree(List<Table> TableMasterList){
        for(Table t : TableMasterList){
            
            Table t1 = new Table();
            t1.set(t);
            
            //0deg
            Frame f1 = new Frame(env);
            f1.setTables(TableMasterList);
            
            if(f1.tryTable(t1, new Point(0,0)))
                f1.addTable(t1, new Point(0,0));
            
            System.out.println("argh");
            f1.printFrame();
            System.out.println("argh");
            
            Tree.add(f1);
            
            
            //90deg
            Table t2 = new Table();
            t2.set(t);
            t2.rotate();
            
            Frame f2 = new Frame(env);
            f1.setTables(TableMasterList);
            
            if(f2.tryTable(t2, new Point(0,0)))
                f2.addTable(t2, new Point(0,0));
            
            Tree.add(f2);
            
        }
        printTree();
        pauseCmd();
    }
    public void runTree(){
        
        System.out.println("Hey");
        
        List<Frame> temp_tree = new ArrayList<>();
        
        for(Frame f : Tree){
            Frame fNew = new Frame(f);
            temp_tree.add(fNew);
        }
        
        Tree.clear();
        
        for(Frame f : temp_tree){
            
            if(f.placePoints.isEmpty())
                break;
            
            for(Point p : f.placePoints){
                
                if(f.tables.isEmpty())
                    break;
                
                for(int i = 0; i < tables.size(); i++){
                    
                    Table t1 = new Table();
                    t1.set(f.tables.get(i));
                    
                    if(f.tryTable(t1, p)){
                        f.addTable(t1, p);
                        i = -1;
                    }
                    
                    Table t2 = new Table();
                    t2.set(f.tables.get(i));
                    t2.rotate();
                    
                    if(f.tryTable(t2, p)){
                        f.addTable(t2, p);
                        i = -1;
                    }
                    
                }
                
            }
            
        }
        
        if(!Tree.isEmpty())
            runTree();
        else
            setTree(temp_tree);
        
        printTree();
    }
    
    public void printFrame(){
        System.out.print(
              "-------------------------\n"
            + "Frame: \n"
        );
        if(!tables.isEmpty()){
            for(Table t : tables){
                t.printTable();
            }
        }
        else{
            System.out.println("Tables Empty");
        }
        emptySpace.printPolyRect();
        System.out.println("----------Points----------");
        for(Point p : placePoints){
            p.printPoint();
        }
        System.out.println("-------------------------");
        
    }
    public void printTree(){
        for(Frame f : Tree){
            f.printFrame();
        }
    }
    
}
