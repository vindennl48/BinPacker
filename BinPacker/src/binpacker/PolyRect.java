/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binpacker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mitch
 */
public class PolyRect {
//DATA
    private Rect env;
    List<Table> fullSpace;
    
    
//CONSTRUCTORS
    private void init(){
        env = new Rect();
        fullSpace = new ArrayList<>();
    }
    PolyRect(Rect env){
        init();
        this.env.setRect(env);
    }
    PolyRect(PolyRect pr){
        init();
        env.setRect(pr.getEnv());
        setFullSpace(pr.fullSpace);
    }

    
//GETTERS SETTERS
    public Rect getEnv(){
        return env;
    }
    public Table getFullSpace(int i){
        return fullSpace.get(i);
    }
    
    public void setEnv(Rect r){
        env.setRect(r);
    }
    public void setFullSpace(List<Table> tList){
        fullSpace.clear();
        for(Table t : tList){
            Table tNew = new Table();
            tNew.setTable(t);
            fullSpace.add(tNew);
        }
    }
    public void setPolyRect(PolyRect p){
        setFullSpace(p.fullSpace);
        this.env = p.env;
    }
    
    
//MEMBERS
    //see if newRect can fit in the leftover space
    public boolean checkSpace(Rect newRect){
        if(fullSpace.isEmpty()){
            return newRect.isInsideOf(env);
        }
        else{
            for(Table t : fullSpace){
                if (newRect.isOntopOf(t.getSize()))
                    return false;
                if (newRect.isInsideOf(t.getSize()))
                    return false;
            }
            if (!newRect.isInsideOf(env))
                return false;
            return true;
        }
    }
    //add a table to the leftover space
    public boolean addTable(Table t){
        fullSpace.add(t);
        return true;
    }
        
    public void printPolyRect(){
        System.out.println("---------------------");
        System.out.println("PolyRect: ");
        System.out.println(String.format("env: %.4f, %.4f | %.4f, %.4f", 
                env.getBL().getX(), env.getBL().getY(),
                env.getTR().getX(), env.getTR().getY()));
        System.out.println("    -----------------");
        System.out.println("fullSpace: ");
        for(Table t : fullSpace){
            t.printTable();
        }
        System.out.println("    -----------------");
        System.out.println("---------------------");
    }
    
    
}
