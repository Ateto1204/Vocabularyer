/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

/**
 *
 * @author Ateto
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
