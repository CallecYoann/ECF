/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecf_dahouet;

import models.Personne;

/**
 *
 * @author callec
 */
public class Ecf_dahouet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Personne p = new Personne("Henaff", "Gouegoue", "pouet", 1985);
        System.out.println(p.calculAge());  
                
    }
    
}
