package myproject;

/**
 *
 * @author Ateto
 *
 * Description: Main class
 *
 */

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main{
    public static void main(String[] args) {
        
        try{
            Action action = new Action();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
