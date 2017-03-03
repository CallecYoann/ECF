/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intGraphDAO;

import intGraph.Proprietaire;
import intGraph.Voilier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author callec
 */
public class ProprietaireDAO {

    public static List<Proprietaire> findAllProprietaire() {

        Connection connection = ConnectDB.getConnection();

        List<Proprietaire> proprietaires = new ArrayList<>();
        Statement stm;

        try {
            stm = connection.createStatement();

            String sql = "select * from proprietaire p INNER JOIN personne per ON p.id_personne=per.id_personne ";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("p.id_proprietaire");
                String nom = rs.getString("nom_personne");
                String prenom = rs.getString("prenom_personne");
                

                Proprietaire p = new Proprietaire(id, nom, prenom);

                proprietaires.add(p);
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return proprietaires;

    }

     public static void saveProprietaire(Proprietaire p) throws Exception {
        Connection connection = ConnectDB.getConnection();

        PreparedStatement stmCreateProprietaire;
        PreparedStatement stmCreatePersonne;

        try {

            stmCreatePersonne = connection.prepareStatement("INSERT INTO personne (nom_personne, prenom_personne, age_personne, num_licence, nom_club ) VALUES(?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            stmCreatePersonne.setString(1, p.getNom());
            stmCreatePersonne.setString(2, p.getPrenom());
            stmCreatePersonne.setInt(3, p.getAge());
            stmCreatePersonne.setInt(4, p.getNumLicence());
            stmCreatePersonne.setString(5, p.getNomClub());

            stmCreatePersonne.execute();

            ResultSet rs = stmCreatePersonne.getGeneratedKeys();
            if (!rs.next()) {
                throw new Exception("humm cannot get generated voilier id");
            }
            p.setId(rs.getInt(1));
            
            stmCreateProprietaire = connection.prepareStatement("INSERT INTO proprietaire (id_personne)VALUES(?);", Statement.RETURN_GENERATED_KEYS);
            stmCreateProprietaire.setInt(1, p.getId());
            stmCreateProprietaire.execute();
            
            stmCreatePersonne.close();
            stmCreateProprietaire.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lol");
            throw new Exception("error while creating proprietaire " + e.getMessage());
        }

    }
    
}
