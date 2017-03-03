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
public class Classe {
    
    int id_classe;
    String nom_classe;
    int coeff;

    public Classe(int id_classe, String nom_classe, int coeff) {
        this.id_classe = id_classe;
        this.nom_classe = nom_classe;
        this.coeff = coeff;
    }

    public Classe(String nom_classe) {
        this.nom_classe = nom_classe;
    }

    public Classe(int id_classe, String nom_classe) {
        this.id_classe = id_classe;
        this.nom_classe = nom_classe;
    }
    
    

    public int getId_classe() {
        return id_classe;
    }

    public void setId_classe(int id_classe) {
        this.id_classe = id_classe;
    }

    public String getNom_classe() {
        return nom_classe;
    }

    public void setNom_classe(String nom_classe) {
        this.nom_classe = nom_classe;
    }

    public int getCoeff() {
        return coeff;
    }

    public void setCoeff(int coeff) {
        this.coeff = coeff;
    }

    @Override
    public String toString() {
        return "" +nom_classe;
    }
    
    
    
}
