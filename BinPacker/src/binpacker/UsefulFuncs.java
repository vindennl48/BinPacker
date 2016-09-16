/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binpacker;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SHOP
 */
public class UsefulFuncs {
    
    static void pauseCmd(){
        System.out.print("\n\nPaused: Press Enter To Continue...\n\n");
        try {
            System.in.read();
        } catch (IOException ex) {
            Logger.getLogger(UsefulFuncs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //-----------------------------------
    private static int madeItCount = 0;
    static void madeIt(){
        System.out.println("Made it to: " + madeItCount);
        madeItCount++;
    }
    static void madeIt(String a){
        System.out.println("Made it to: " + a + " " + madeItCount);
        madeItCount++;
    }
    //-----------------------------------
    
    
    
    //-----------------------------------
    private static boolean verbose = true;
    static void verboseOn(boolean b){
        verbose = b;
    }
    //-----------------------------------
    
    
    
    //-----------------------------------
    static boolean Print(String s){
        if(!verbose)
            return false;
        
        System.out.print(s);
        
        return true;
    }
    static boolean PrintP(String s){
        if(!verbose)
            return false;
        
        Print(s);
        pauseCmd();
        
        return true;
    }
    //-----------------------------------
    
    
    
    //-----------------------------------
    static void StringToClipboard(String s){
        StringSelection ss = new StringSelection(s);
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
        cb.setContents(ss, null);
    }
    //-----------------------------------
}
