/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binpacker;

import static binpacker.UsefulFuncs.Print;
import static binpacker.UsefulFuncs.PrintP;
import static binpacker.UsefulFuncs.madeIt;
import static binpacker.UsefulFuncs.pauseCmd;
import java.util.ArrayList;
import java.util.List;
import static binpacker.UsefulFuncs.madeIt;

/**
 *
 * @author Mitch
 */
public class Frame {
    
//DATA
    List<Table> tables;         //tables left
    PolyRect emptySpace;        //empty space left
    Rect env;                   //environment/plate size
    List<Point> placePoints;    //origins that tables can be placed
    
    //master list of Frames
    static List<PolyRect> Tree = new ArrayList<>();
    

//CONSTRUCTORS
    private void init(){
        tables = new ArrayList<>();
        env = new Rect();
        emptySpace = new PolyRect(env);
        placePoints = new ArrayList<>();
    }
    Frame(Rect env){
        init();
        this.env.setRect(env);
        emptySpace.setEnv(env);
        placePoints.add(env.getBL());
    }
    Frame(Frame f){
        init();
        
        setTables(f.tables);
        emptySpace.setPolyRect(f.emptySpace);
        this.env.setRect(f.env);
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
        t2.setTable(t);

        t2.setOrigin(p);
        if (emptySpace.checkSpace(t2.getSize()))
            return true;
        
        return false;
    }
    public boolean addTable(Table t, Point p){
        
        Table tNew = new Table();
        tNew.setTable(t);
        tNew.setOrigin(p);
        
        if(emptySpace.addTable(tNew)){
            removeTable(tNew);
            removePlacePoint(p);
            addPlacePoint(tNew.getBR());
            addPlacePoint(tNew.getTL());
            
            return true;
        }
        return false;
    }
    public boolean removeTable(Table t){
        
        if(tables.isEmpty()){
            return false;
        }
        
        boolean didDelete = false;
        
        for(int i = 0; i < tables.size(); i++){
            if(tables.get(i).getName().equals(t.getName())){
                tables.remove(i);
                didDelete = true;
                i = -1;
            }
        }
        return didDelete;
    }
    public void setTables(List<Table> tList){
        tables.clear();
        for(Table t : tList){
            Table tNew = new Table(t);
            tables.add(tNew);
        }
    }
    public void setPlacePoints(List<Point> pList){
        placePoints.clear();
        for(Point p : pList){
            Point pNew = new Point();
            pNew.setPoint(p);
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
    public void setTree(List<PolyRect> LF){
        Tree.clear();
        for(PolyRect f : LF){
            PolyRect fNew = new PolyRect(f);
            Tree.add(fNew);
        }
    }
    
    
//MEMBERS
    public void buildTree(List<Table> TableMasterList){
        setTables(TableMasterList);
        Print("finished building tree\n");
    }
    public boolean runTreeFirst(){
        
        PrintP("running tree\n");
        
        if(!tables.isEmpty()){

            for(Table t : tables){
                
                t.printTable();

                for(Point p : placePoints){

                    if(tryTable(t, p)){
                        
                        PrintP("table fit: " + t.getName() + "\n");
                        
                        Frame f = new Frame(this);
                        f.addTable(t, p);
                        f.runTree();
                    }
                }
            }
            addFinal();
        }
        else{
            addFinal();
        }
        
        return true;
    }
    public boolean runTree(){
        
        Print("running tree\n");
        
        if(!tables.isEmpty()){

            for(Table t : tables){

                for(Point p : placePoints){

                    if(tryTable(t, p)){
                        
                        Print("table fit: " + t.getName() + "\n");
                        
                        Frame f = new Frame(this);
                        f.addTable(t, p);
                        f.runTree();
                    }
                }
            }
            addFinal();
        }
        else{
            addFinal();
        }
        
        return true;
    }
    public void addFinal(){
        
        Print("adding frame\n");
        
        boolean addThis = false;
            
        if(!Tree.isEmpty()){
            
            for(int i = 0; i < Tree.size(); i++){
                PolyRect fPoly = Tree.get(i);

                if(this.emptySpace.getPrice() > fPoly.getPrice()){
                    Tree.remove(i);
                    addThis = true;
                    i= -1;
                }
                else if(this.emptySpace.getPrice() == fPoly.getPrice()){
                    addThis = true;
                }
                else{
                    addThis = false;
                    break;
                }
            }
        }
        else{
            addThis = true;
        }
        
        if(addThis){
            Tree.add(this.emptySpace);
            removeTreeDuplicates();
            Print("############################# ADDED FRAME TO TREE ##############################\n");
        }
    }
    public boolean removeTreeDuplicates(){
        
        if(Tree.isEmpty())
            return false;
        
        for(int i = 0; i < Tree.size(); i++){
            PolyRect pr = Tree.get(i);
            
            if(Tree.size() > 1){
                for(int j = i+1; j < Tree.size(); j++){

                    PolyRect pr2 = Tree.get(j);
                    
                    if (pr.isEqualTo(pr2)){
                        Tree.remove(j);
                        i = -1;
                        break;
                    }
                }
            }
        }
        
        return true;
    }
    
//PRINT
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
        for(PolyRect f : Tree){
            f.printPolyRect();
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
    public void printFrameACAD(){
        
        Frame f = new Frame(this);
        
        double offset = 0;
        
        System.out.print(String.format(
                "rectangle\n"
              + "%.4f,%.4f\n"
              + "%.4f,%.4f\n",
                env.getBL().getX() + offset,
                env.getBL().getY(),
                env.getTR().getX() + offset,
                env.getTR().getY()
        ));
        for(Table t : f.emptySpace.fullSpace){
            t.printTableACAD(offset, 0);
        }
        offset += 50;
        System.out.println("");
        
    }
    public void printTreeACAD(int itr){
        
        double offset = 0;
        
        if(Tree.isEmpty())
            System.out.println("Tree is Empty");
        
        PolyRect f = new PolyRect(Tree.get(itr));
        System.out.print(String.format(
                "rectangle\n"
              + "%.4f,%.4f\n"
              + "%.4f,%.4f\n",
                env.getBL().getX() + offset,
                env.getBL().getY(),
                env.getTR().getX() + offset,
                env.getTR().getY()
        ));
        for(Table t : f.fullSpace){
            t.printTableACAD(offset,0);
        }
        offset += 50;
        System.out.println("");
        
    }
    public void printTreeACAD(){
        
        double offset = 0;
        
        if(Tree.isEmpty())
            System.out.println("Tree is Empty");
        for(PolyRect f : Tree){
            System.out.print(String.format(
                    "rectangle\n"
                  + "%.4f,%.4f\n"
                  + "%.4f,%.4f\n",
                    env.getBL().getX() + offset,
                    env.getBL().getY(),
                    env.getTR().getX() + offset,
                    env.getTR().getY()
            ));
            for(Table t : f.fullSpace){
                t.printTableACAD(offset, 0);
            }
            offset += 50;
            System.out.println("");
        }
    }

    public String StringTreeACAD(){
        double offset = 0;
        
        String s = "";
        
        if(Tree.isEmpty()){
            s = ("Tree is Empty");
            return s;
        }
        
        for(PolyRect f : Tree){
            s += (String.format(
                    "rectangle\n"
                  + "%.4f,%.4f\n"
                  + "%.4f,%.4f\n",
                    env.getBL().getX() + offset,
                    env.getBL().getY(),
                    env.getTR().getX() + offset,
                    env.getTR().getY()
            ));
            for(Table t : f.fullSpace){
                s += t.StringTableACAD(offset, 0);
            }
            offset += 50;
            s += "\n";
        }
        
        
        
        return s;
    }
}
