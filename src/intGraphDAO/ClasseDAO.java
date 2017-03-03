/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intGraphDAO;

import intGraph.Classe;
import intGraph.Proprietaire;
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
public class ClasseDAO {

    public static List<Classe> findAllClasse() {
    
    Connection connection = ConnectDB.getConnection();

    List<Classe> classes = new ArrayList<>();
    Statement stm;

    
        try {
            stm = connection.createStatement();

        String sql = "select * from classe";
        ResultSet rs = stm.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("id_classe");
            String nom = rs.getString("nom_classe");
            int coeff = rs.getInt("coeff");
            
            Classe c = new Classe(id, nom, coeff );

            classes.add(c);
        }
        rs.close();

    }
    catch (SQLException e

    
        ) {
            throw new RuntimeException();
    }

    return classes ;

}

    public static List<Classe> findClasseBySerie(int serie) {
    
    Connection connection = ConnectDB.getConnection();

    List<Classe> classes = new ArrayList<>();
    Statement stm;

    
        try {
            stm = connection.createStatement();

        String sql = "select * from classe cla INNER JOIN serie s ON cla.id_serie=s.id_serie WHERE cla.id_serie="+serie;
        ResultSet rs = stm.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("id_classe");
            String nom = rs.getString("nom_classe");

            Classe c = new Classe(id, nom);

            classes.add(c);
        }
        rs.close();

    }
    catch (SQLException e

    
        ) {
            throw new RuntimeException();
    }

    return classes ;

}
    
    
}
