/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intGraphDAO;

import intGraph.Voilier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author callec
 */
public class VoilierDAO {

    public static void saveVoilier(Voilier v) throws Exception {
        Connection connection = ConnectDB.getConnection();

        PreparedStatement stmCreateVoilier;

        try {
            connection.setAutoCommit(false);

            stmCreateVoilier = connection.prepareStatement("INSERT INTO Voilier (nom_voilier, num_voile) VALUES(?, ?);", Statement.RETURN_GENERATED_KEYS);
            stmCreateVoilier.setString(1, v.getNom_voilier());
            stmCreateVoilier.setInt(2, v.getNum_voile());

            stmCreateVoilier.execute();

        } catch (SQLException e) {
            connection.rollback();
            throw new Exception("error while creating personne " + e.getMessage());
        }

    }

}
