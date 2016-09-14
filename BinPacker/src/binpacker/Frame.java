/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binpacker;

import static binpacker.UsefulFuncs.madeIt;
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
    public boolean addPlacePoint(Point pNew){
        //search for existing placepoint
        for(Point p : placePoints){
            if(p.isEqualTo(pNew)){
               return false; 
            }
        }
        placePoints.add(pNew);
        return true;
    }
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
    public boolean addTable(Table t, Point p){
        
        Table tNew = new Table();
        tNew.set(t);
        tNew.setOrigin(p);
        
        if(emptySpace.addFullSpace(tNew)){
            removeTable(tNew);
            removePlacePoint(p);
            addPlacePoint(tNew.size.getBtmRight());
            addPlacePoint(tNew.size.getTopLeft());
            
            return true;
        }
        return false;
    }
    public boolean removeTable(Table t){
        
        if(tables.isEmpty()){
            return false;
        }
        
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
            
            if(f1.tryTable(t1, new Point(0,0))){
                f1.addTable(t1, new Point(0,0));
            
                Tree.add(f1);
            
            }
            
            //90deg
            Table t2 = new Table();
            t2.set(t);
            t2.rotate();
            
            Frame f2 = new Frame(env);
            f2.setTables(TableMasterList);
            
            if(f2.tryTable(t2, new Point(0,0))){
                f2.addTable(t2, new Point(0,0));
                
                Tree.add(f2);
                
            }
        }
    }
    
    
    //temp
    static int count = 0;
    static int yoffset = 0;
    
    public void runTree(){
        List<Frame> temp_tree = new ArrayList<>();
        
        for(Frame f : Tree){
            Frame fNew = new Frame(f);
            temp_tree.add(fNew);
        }
        
        Tree.clear();
        int tcount = 0;
        
        for(Frame f : temp_tree){
            
            for(Point p : f.placePoints){
                if(f.tables.isEmpty())
                    break;
                for(Table t : f.tables){
                    
                    Frame f1 = new Frame(f);
                    Table t1 = new Table(t);
                    
                    if(f1.tryTable(t1, p)){
                        f1.addTable(t1, p);
                        Tree.add(f1);
                        tcount++;
                    }
                    
                    Frame f2 = new Frame(f);
                    Table t2 = new Table(t);
                    t2.rotate();
                    
                    if(f2.tryTable(t2, p)){
                        f2.addTable(t2, p);
                        Tree.add(f2);
                    }
                }
            }
        }
        
        
//        if(count == 3){
//            printTreeACAD(1);
//            Tree.get(1).printPlacePointsACAD();
//            pauseCmd();
//        }
        
        count++;
        
        double xoffset = 0;
        
        if(Tree.isEmpty())
            System.out.println("Tree is Empty");
        for(Frame f : Tree){
            System.out.print(String.format(
                    "rectangle\n"
                  + "%.4f,%.4f\n"
                  + "%.4f,%.4f\n",
                    env.getBtmLeft().getX() + xoffset,
                    env.getBtmLeft().getY() + yoffset,
                    env.getTopRight().getX() + xoffset,
                    env.getTopRight().getY() + yoffset
            ));
            for(Table t : f.emptySpace.fullSpace){
                t.printACAD(xoffset,yoffset);
            }
            xoffset += 50;
            System.out.println("");
        }
        yoffset+=80;
        
        //printTreeACAD();
        //pauseCmd();
        
        if(!Tree.isEmpty())
            runTree();
        else{
            setTree(temp_tree);
            //printTree();
            //printTreeACAD();
        }

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
        if(Tree.isEmpty())
            System.out.println("Tree is Empty");
        for(Frame f : Tree){
            f.printFrame();
        }
    }
    
    public void printPlacePointsACAD(){
        for(Point p : placePoints){
            System.out.print(String.format(
                    "circle\n"
                  + "%.4f,%.4f\n"
                  + "d\n"
                  + "1\n",
                    p.getX(),p.getY()
            ));
        }
    }
    
    public void printTreeACAD(int itr){
        
        double offset = 0;
        
        if(Tree.isEmpty())
            System.out.println("Tree is Empty");
        
        Frame f = new Frame(Tree.get(itr));
        System.out.print(String.format(
                "rectangle\n"
              + "%.4f,%.4f\n"
              + "%.4f,%.4f\n",
                env.getBtmLeft().getX() + offset,
                env.getBtmLeft().getY(),
                env.getTopRight().getX() + offset,
                env.getTopRight().getY()
        ));
        for(Table t : f.emptySpace.fullSpace){
            t.printACAD(offset);
        }
        offset += 50;
        System.out.println("");
        
    }
    public void printTreeACAD(){
        
        double offset = 0;
        
        if(Tree.isEmpty())
            System.out.println("Tree is Empty");
        for(Frame f : Tree){
            System.out.print(String.format(
                    "rectangle\n"
                  + "%.4f,%.4f\n"
                  + "%.4f,%.4f\n",
                    env.getBtmLeft().getX() + offset,
                    env.getBtmLeft().getY(),
                    env.getTopRight().getX() + offset,
                    env.getTopRight().getY()
            ));
            for(Table t : f.emptySpace.fullSpace){
                t.printACAD(offset);
            }
            offset += 50;
            System.out.println("");
        }
    }
}
