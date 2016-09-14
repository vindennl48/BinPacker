/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binpacker;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SHOP
 */
public class UsefulFuncs {
    
    static int madeItCount = 0;
    
    static void pauseCmd(){
        System.out.print("\n\nPaused: Press Enter To Continue...\n\n");
        try {
            System.in.read();
        } catch (IOException ex) {
            Logger.getLogger(UsefulFuncs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void madeIt(){
        System.out.println("Made it to: " + madeItCount);
        madeItCount++;
    }
    static void madeIt(String a){
        System.out.println("Made it to: " + a + " " + madeItCount);
        madeItCount++;
    }
}
