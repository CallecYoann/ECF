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
public class Proprietaire extends Personne {
    
    int id_proprietaire;

    public Proprietaire(int id, String nom, String prenom, int age, int numLicence, String nomClub) {
        super(id, nom, prenom, age, numLicence, nomClub);
    }

    
    public Proprietaire(String nom, String prenom, int age, int numLicence, String nomClub) {
        super(nom, prenom, age, numLicence, nomClub);
    }

    public Proprietaire(String nom, String prenom) {
        super(nom, prenom);
    }

    public Proprietaire(int id_proprietaire, String nom, String prenom) {
        super(nom, prenom);
        this.id_proprietaire = id_proprietaire;
    }

    public int getId_proprietaire() {
        return id_proprietaire;
    }

    public void setId_proprietaire(int id_proprietaire) {
        this.id_proprietaire = id_proprietaire;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumLicence() {
        return numLicence;
    }

    public void setNumLicence(int numLicence) {
        this.numLicence = numLicence;
    }

    public String getNomClub() {
        return nomClub;
    }

    public void setNomClub(String nomClub) {
        this.nomClub = nomClub;
    }

    @Override
    public String toString() {
        return "" + nom + " " + prenom;
    }

    
    
    
    
}
