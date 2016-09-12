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
public class HandyFunctions {
    
    static void pauseCmd(){
        System.out.print("\n\nPaused: Press Enter To Continue...\n\n");
        try {
            System.in.read();
        } catch (IOException ex) {
            Logger.getLogger(HandyFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
