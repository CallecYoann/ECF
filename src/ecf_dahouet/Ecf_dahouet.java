/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecf_dahouet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import models.Commissaire;
import models.Licencie;
import models.Personne;
import models.Proprietaire;

/**
 *
 * @author callec
 */
public class Ecf_dahouet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //fonction 1
        Personne p = new Personne("Henaff", "Gouegoue", "pouet", 1985);
        System.out.println(p.calculAge());  
                
        //fonction 2
        Licencie l1 = new Licencie(15, 10, 2016, "callec", "yoann", "pouet", 1989);
        Calendar c = Calendar.getInstance();
        c.set(2017,06,22);
        
        try {
            l1.calculPoints(5, c);
            System.out.println("Score mis a jour");
        } catch (Exception e) {
            System.out.println(e);
        }
        
        //fonction 3
        Proprietaire pro1 = new Proprietaire("Glaziou", "Antoine", "email1", 1991);
        Proprietaire pro2 = new Proprietaire("Tallegas", "Erwan", "email2", 1995);
        
        Licencie l2 = new Licencie(1, 10, 2017, "Calvez", "Antoine", "email3", 1993);
        Licencie l3 = new Licencie(2, 15, 2017, "Dilasser", "Manu", "email4", 1992);
        Licencie l4 = new Licencie(3, 20, 2017, "Le Roux", "Mathieu", "email5", 1994);
        
        Commissaire c1 = new Commissaire("comite de Bretagne", "Chaussec", "Gaetan", "email6", 1984);
        
        List <Personne> personnes = new ArrayList<>();
        personnes.add(pro1);
        personnes.add(pro2);
        personnes.add(l2);
        personnes.add(l3);
        personnes.add(l4);
        personnes.add(c1);
        
        for(int i=0; i<personnes.size(); i++ ){
            System.out.println(personnes.get(i));
        }
        
        System.out.println("La moyenne d'age du groupe est : " +calculMoyenneAge(personnes));
        
        System.out.println("La mediane d'age du groupe est : " +calculMedianeAge(personnes));
        
    }
    
        //fonction 4 
        
        //Moyenne d'age
        public static double calculMoyenneAge(List <Personne> personnes) {
           
            int sommeAge =0;
            double moyenneAge;
            
            for(int i=0; i<personnes.size(); i++){
                 sommeAge += personnes.get(i).calculAge();
                 //sommeAge = sommeAge + personnes.get(i).calculAge();
            }
            
                moyenneAge = sommeAge/personnes.size();
                return moyenneAge;   
        } 
        
        //Mediane d'age
        public static double calculMedianeAge(List <Personne> personnes) {
            
            //Initialiasation du tableau de int qui va contenir les ages
            int[] listeAges = new int[personnes.size()];
            
            //Remplissage du tableau avec les ages du groupe  
            for(int j=0; j < personnes.size(); j++){
                listeAges[j] = personnes.get(j).calculAge();
            }
            
            //Tri du tableau
            Arrays.sort(listeAges);
           
           // On stocke le milieu de la longueur du tableau dans la variable j
           int j = listeAges.length / 2;
           
           if (listeAges.length%2 == 1) {
               return listeAges[j];
           } else {
               return (listeAges[j-1] + listeAges[j]) / 2.0;
           }
        }
        
}
