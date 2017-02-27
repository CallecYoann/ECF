/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intGraphDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author callec
 */
public class ConnectDB {

    public static Connection getConnection() {
        //Connection à la base de donnée 
        String url = "jdbc:mysql://localhost/ecf_regate";
        String login = "root";
        String passwd = "pouet";
        Connection cn = null;
        try {
            // Etape 1 : Chargement du driver
            Class.forName("com.mysql.jdbc.Driver");
            // Etape 2 : récupération de la connexion
            cn = (Connection) DriverManager.getConnection(url, login, passwd);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return cn;
    }

}
