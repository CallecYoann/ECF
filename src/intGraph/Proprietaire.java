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
public class Proprietaire {
    
    int id_proprietaire;

    public Proprietaire(int id_proprietaire) {
        this.id_proprietaire = id_proprietaire;
    }

    public int getId_proprietaire() {
        return id_proprietaire;
    }

    public void setId_proprietaire(int id_proprietaire) {
        this.id_proprietaire = id_proprietaire;
    }

    @Override
    public String toString() {
        return "Proprietaire{" + "id_proprietaire=" + id_proprietaire + '}';
    }
    
    
    
}
