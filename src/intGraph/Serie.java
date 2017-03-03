/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intGraph;

/**
 *
 * @author callec
 */
public class Serie {
    
    int id_serie;
    String nom_serie;

    public Serie(int id_serie, String nom_serie) {
        this.id_serie = id_serie;
        this.nom_serie = nom_serie;
    }

    public int getId_serie() {
        return id_serie;
    }

    public void setId_serie(int id_serie) {
        this.id_serie = id_serie;
    }

    public String getNom_serie() {
        return nom_serie;
    }

    public void setNom_serie(String nom_serie) {
        this.nom_serie = nom_serie;
    }

    

    @Override
    public String toString() {
        return "" + nom_serie;
    }
    
    
    
}
