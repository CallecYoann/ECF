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
public class Personne {
    int id;
    String nom;
    String prenom;
    int age;
    int numLicence;
    String nomClub;

    public Personne(int id, String nom, String prenom, int age, int numLicence, String nomClub) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.numLicence = numLicence;
        this.nomClub = nomClub;
    }

    
    
    public Personne(String nom, String prenom, int age, int numLicence, String nomClub) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.numLicence = numLicence;
        this.nomClub = nomClub;
    }

    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    
    
}
