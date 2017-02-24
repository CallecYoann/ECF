/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author callec
 */
public class Commissaire extends Personne {
    
    String comite;

    public Commissaire(String comite, String nom, String prenom, String email) {
        super(nom, prenom, email);
        this.comite = comite;
    }

    public String getComite() {
        return comite;
    }

    public void setComite(String comite) {
        this.comite = comite;
    }

    @Override
    public String toString() {
        return "Commissaire{" + "comite=" + comite + '}';
    }

    
}
