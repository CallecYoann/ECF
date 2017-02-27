/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Calendar;

/**
 *
 * @author callec
 */
public class Licencie extends Personne {
    
    int numLicence;
    double pointsFFV;
    int anneeLicence;

    public Licencie(int numLicence, double pointsFFV, int anneeLicence, String nom, String prenom, String email, int anneeNaissance) {
        super(nom, prenom, email, anneeNaissance);
        this.numLicence = numLicence;
        this.pointsFFV = pointsFFV;
        this.anneeLicence = anneeLicence;
    }
    

    public int getNumLicence() {
        return numLicence;
    }

    public void setNumLicence(int numLicence) {
        this.numLicence = numLicence;
    }

    public double getPointsFFV() {
        return pointsFFV;
    }

    public void setPointsFFV(double pointsFFV) {
        this.pointsFFV = pointsFFV;
    }

    public int getAnneeLicence() {
        return anneeLicence;
    }

    public void setAnneeLicence(int anneeLicence) {
        this.anneeLicence = anneeLicence;
    }

    @Override
    public String toString() {
        return "Licencie - " + super.toString() + " " + "numLicence=" + numLicence + ", pointsFFV=" + pointsFFV + ", anneeLicence=" + anneeLicence + '}';
    }
    
    public void  calculPoints(int nvxPoints, Calendar cal) throws Exception {
        
        if(anneeLicence != cal.get(Calendar.YEAR)) {
            throw new Exception("La date de licence n'est pas valide");
        }
            pointsFFV = pointsFFV + nvxPoints;
    }
    
}
