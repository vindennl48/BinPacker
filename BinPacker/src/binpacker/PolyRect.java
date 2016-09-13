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
    private List<Rect> fullSpace;
    
    
//CONSTRUCTORS
    private void init(){
        env = new Rect();
        fullSpace = new ArrayList<>();
    }
    PolyRect(){
        init();
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
    public void addFullSpace(Rect r){
        if(checkSpace(r)){
            fullSpace.add(r);
        }
    }
    
    
//MEMBERS
    public boolean checkSpace(Rect newRect){
        for(Rect r : fullSpace){
            if (newRect.isOntopOf(r) || newRect.isInsideOf(r) || 
                !newRect.isInsideOf(env)){
                
                //throw a fit
//                System.out.println("Error in: "
//                        + "PolyRect.checkSpace(Rect newRect)");
//                pauseCmd();
                return false;
            }
        }
        
        return true;
    }
    void pauseCmd(){
        System.out.print("\n\n\nPress Enter To Continue...\n\n");
        try {
            System.in.read();
        } catch (IOException ex) {
            Logger.getLogger(PolyRect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
