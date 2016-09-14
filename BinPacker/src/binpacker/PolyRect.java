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
    Rect env;
    List<Table> fullSpace;
    
    
//CONSTRUCTORS
    private void init(){
        env = new Rect();
        fullSpace = new ArrayList<>();
    }
    PolyRect(Rect env){
        init();
        this.env.set(env);
    }

    
//GETTERS SETTERS
    public Rect getEnv(){
        return env;
    }
    public void setEnv(Rect r){
        env.set(r);
    }
    public boolean addFullSpace(Table t){
        if(checkSpace(t.getRect())){
            fullSpace.add(t);
            return true;
        }
        return false;
    }
    public void setFullSpace(List<Table> tList){
        fullSpace.clear();
        for(Table t : tList){
            Table tNew = new Table();
            tNew.set(t);
            fullSpace.add(tNew);
        }
    }
    public void set(PolyRect p){
        setFullSpace(p.fullSpace);
        this.env = p.env;
    }
    
    
//MEMBERS
    public boolean checkSpace(Rect newRect){
        if(fullSpace.isEmpty()){
            return newRect.isInsideOf(env);
        }
        else{
            for(Table t : fullSpace){
                if (newRect.isOntopOf(t.getRect()))
                    return false;
                if (newRect.isInsideOf(t.getRect()))
                    return false;
                if (!newRect.isInsideOf(env))
                    return false;
            }
            return true;
        }
    }
    void pauseCmd(){
        System.out.print("\n\n\nPress Enter To Continue...\n\n");
        try {
            System.in.read();
        } catch (IOException ex) {
            Logger.getLogger(PolyRect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void printPolyRect(){
        System.out.println("---------------------");
        System.out.println("PolyRect: ");
        System.out.println(String.format("env: %.4f, %.4f | %.4f, %.4f", 
                env.getBtmLeft().getX(), env.getBtmLeft().getY(),
                env.getTopRight().getX(), env.getTopRight().getY()));
        System.out.println("    -----------------");
        System.out.println("fullSpace: ");
        for(Table t : fullSpace){
            t.printTable();
        }
        System.out.println("    -----------------");
        System.out.println("---------------------");
    }
    
}
