/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intGraphDAO;

import intGraph.Classe;
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
public class VoilierDAO {

    public static void saveVoilier(Voilier v) throws Exception {
        Connection connection = ConnectDB.getConnection();

        PreparedStatement stmCreateVoilier;

        try {

            stmCreateVoilier = connection.prepareStatement("INSERT INTO voilier (nom_voilier, num_voile, id_proprietaire, id_classe ) VALUES(?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            stmCreateVoilier.setString(1, v.getNom_voilier());
            stmCreateVoilier.setInt(2, v.getNum_voile());
            stmCreateVoilier.setInt(3, v.getProprietaire().getId_proprietaire());
            stmCreateVoilier.setInt(4, v.getClasse().getId_classe());

            stmCreateVoilier.execute();

            ResultSet rs = stmCreateVoilier.getGeneratedKeys();
            if (!rs.next()) {
                throw new Exception("humm cannot get generated voilier id");
            }

            stmCreateVoilier.close();
            
        } catch (SQLException e) {
            System.out.println("Erreur lol");
            throw new Exception("error while creating voilier " + e.getMessage());
        }

    }
    
    
    public static List<Voilier> findAllVoilier() {

        Connection connection = ConnectDB.getConnection();

        List<Voilier> voiliers = new ArrayList<>();
        Statement stm;

        try {
            stm = connection.createStatement();

            String sql = "select * from voilier v INNER JOIN proprietaire pro ON v.id_proprietaire=pro.id_proprietaire INNER JOIN personne per ON pro.id_personne=per.id_personne INNER JOIN classe cla ON v.id_classe=cla.id_classe ";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                String nom = rs.getString("nom_voilier");
                int numero = rs.getInt("num_voile");
                Proprietaire proprietaire = new Proprietaire( rs.getString("nom_personne"), rs.getString("prenom_personne") );
                Classe classe = new Classe( rs.getString("cla.nom_classe"));
                
                Voilier v = new Voilier(nom, numero, proprietaire, classe);

                voiliers.add(v);
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return voiliers;

    }
    

}
