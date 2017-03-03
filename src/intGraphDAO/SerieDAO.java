/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intGraphDAO;

import intGraph.Classe;
import intGraph.Proprietaire;
import intGraph.Serie;
import intGraph.Voilier;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author callec
 */
public class SerieDAO {
    
    public static List<Serie> findAllSerie() {

        Connection connection = ConnectDB.getConnection();

        List<Serie> series = new ArrayList<>();
        Statement stm;

        try {
            stm = connection.createStatement();

            String sql = "select * from serie";
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                int id_serie = rs.getInt("id_serie");
                String nom_serie = rs.getString("nom_serie");
                
                Serie s = new Serie(id_serie, nom_serie);

                series.add(s);
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return series;

    }
    
}
